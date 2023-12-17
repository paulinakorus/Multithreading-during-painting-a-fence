package org.example.model;

import org.example.GUI.FenceFrame;
import org.example.model.enums.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Painter implements Runnable{
    private int id;
    private Character name;
    private long speed;
    private PaintBucket bucket = new PaintBucket();
    private static List<Painter> painterList = new ArrayList<>();
    private static final Fence fence = Fence.getInstance();
    private static final PaintSupplier supplier = PaintSupplier.getInstance();
    private Segment segmentToPaint;

    private int lastIndex;
    private int startIndex;
    private int currentIndex;
    private final FenceFrame fenceFrame;
    private Random random = new Random();

    public Painter(FenceFrame frame, int id){
        this.fenceFrame = frame;
        this.id += id;
        name = (char)((int)'A' + (id));
        speed = (random.nextInt(10)+5)*100;
        painterList.add(this);
    }

    @Override
    public void run(){
        try {
            while(fence.getStatus() != Status.Painted){
                if(segmentToPaint != null){
                    if (currentIndex >= segmentToPaint.getLenght()) {
                        segmentToPaint.getPainterList().remove(this);
                        segmentToPaint = null;
                        startIndex = 0;
                    }
                }

                if(segmentToPaint == null){
                    segmentToPaint = fence.findSegmentToWorkOn();
                    if(segmentToPaint != null){
                        segmentToPaint.setStatus(Status.InProcces);
                    }
                }
                paintingSegment();
            }
        } catch (InterruptedException ex) {
            System.out.println("Painter " + this.getId() + " job interrupted.");
        }
    }

    public void paintingSegment() throws InterruptedException {
        Plank plankToPaint;
        synchronized (segmentToPaint) {
            if(segmentToPaint == null){
                return;
            }
            if(segmentToPaint.getUnpaintedPlanksList().isEmpty()){
                segmentToPaint.setStatus(Status.Painted);
                segmentToPaint.getPainterList().remove(this);
                segmentToPaint = null;
                startIndex = 0;
                return;
            } else if (segmentToPaint.getPainterList().isEmpty()){
                segmentToPaint.addPainter(this);
                startIndex = 0;
                lastIndex = segmentToPaint.getLenght() - 1;
                currentIndex = 0;
            } else if (!segmentToPaint.getPainterList().contains(this)){
                segmentToPaint.addPainter(this);
                List<Plank> longestPlanks = segmentToPaint.getLongestUnpaintedPlanksList();
                //segmentToPaint.getFragmentOfSegment(longestPlanks);

                int startIndex = segmentToPaint.getIndexOfStart(longestPlanks);
                this.startIndex = startIndex;
                this.lastIndex = segmentToPaint.getLastIndex(longestPlanks);
                currentIndex = startIndex;
            }

            plankToPaint = segmentToPaint.getPlankList().get(currentIndex);
            if (!plankToPaint.getStatus().equals(Status.Unpainted)) {
                segmentToPaint.getPainterList().remove(this);
                return;
            }
        }
        paintingPlank(plankToPaint);
    }

    public void paintingPlank(Plank plankToPaint) throws InterruptedException {
        if(!plankToPaint.getStatus().equals(Status.Unpainted)){
            segmentToPaint.getPainterList().remove(this);
            segmentToPaint = null;
            startIndex = 0;
            return;
        }
        if(getBucket() == null || getBucket().getLeftPaint()<=0) {
            PaintContainer container = Fence.getContainer();
            while (container.getUsingBy() != null || container.isEmpty()) {
                TimeUnit.SECONDS.sleep(2);
            }
            container.setUsingBy(this);
            try {
                int paintToAdd = container.getPaint(this);
                fenceFrame.setUpLabels(fenceLine());
                displayAll();
                TimeUnit.SECONDS.sleep(3);
                bucket.setLeftPaint(paintToAdd);
                container.setUsingBy(null);
            } catch (InterruptedException ex) {
                System.out.println("Getting paint by painter has been interrupted.");
            }
            fenceFrame.setUpLabels(fenceLine());
            displayAll();
        }
        plankToPaint.setStatus(Status.InProcces);
        System.out.println("painter " + id + " painting " + plankToPaint.getId());
        while(plankToPaint.getProgress() < 1.0){
            plankToPaint.setProgress(plankToPaint.getProgress() + 0.2);
            Thread.sleep(this.speed);
        }
        bucket.setLeftPaint(bucket.getLeftPaint() - 1);
        currentIndex++;
        plankToPaint.setPaintedBy(this);
        plankToPaint.setStatus(Status.Painted);
        displayAll();
        fenceFrame.setUpLabels(fenceLine());
    }

    public void displayAll(){
        System.out.println(firstLine());
        System.out.println(paintersNamesLine());
        System.out.println(paintersBucketsLine());
        System.out.println(fenceLine());
    }
    public String firstLine() {
        synchronized (FenceFrame.getContainer()) {
            Character refillingChar = supplier.isRefilling() ? 'S' : '.';
            Character refillingPainter = FenceFrame.getContainer().getUsingBy() != null ? FenceFrame.getContainer().getUsingBy().getName() : '.';
            return (refillingChar + "[" + FenceFrame.getContainer().getLeftPaint() + "]" + refillingPainter);
        }
    }

    public String paintersNamesLine(){
        String line = "";
        for (Painter painter : Painter.getPainterList()) {
            line += painter.getName() + " ";
        }
        return line;
    }

    public String paintersBucketsLine(){
        String line = "";
        for (Painter painter : Painter.getPainterList()) {
            line += painter.getBucket().getLeftPaint() + "  ";
        }
        return line;
    }

    public String fenceLine() {
        Character painterChar;
        String line = "|";
        var segments = fence.getSegmentList();
        synchronized (segments) {
            for (Segment segment : segments) {
                for (Plank plank : segment.getPlankList()) {
                    painterChar = plank.getPaintedBy() != null ? plank.getPaintedBy().getName() : '.';
                    line += painterChar;
                }
                line += "|";
            }
        }
        return line;
    }
    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }

    public  int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Character getName() {
        return name;
    }

    public void setName(Character name) {
        this.name = name;
    }

    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public PaintBucket getBucket() {
        return bucket;
    }

    public void setBucket(PaintBucket bucket) {
        this.bucket = bucket;
    }

    public static List<Painter> getPainterList() {
        return painterList;
    }

    public static void setPainterList(List<Painter> painterList) {
        Painter.painterList = painterList;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }
}
