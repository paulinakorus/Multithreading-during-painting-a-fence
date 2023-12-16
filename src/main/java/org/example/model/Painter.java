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
    //private static FenceFrame fenceFrame = FenceFrame.getInstance();
//    private Segment segmentToPaint = null;
    private int lastIndex;
    private int startIndex;
    private int currentIndex;
    private final FenceFrame fenceFrame;
    private Random random = new Random();

    public Painter(FenceFrame frame, int id){
        this.fenceFrame = frame;
        this.id += id;
        name = (char)((int)'A' + (id));
        speed = (random.nextInt(10)+1)*100;
        painterList.add(this);
    }

    @Override
    public void run(){
        try {
            while(fence.getStatus() != Status.Painted){
                Segment segmentToPaint;
                segmentToPaint = fence.findSegmentToWorkOn();
                segmentToPaint.setStatus(Status.InProcces);
                paintingSegment(segmentToPaint);
            }
        } catch (InterruptedException ex) {
            System.out.println("Painter " + this.getId() + " job interrupted.");
        }
    }

    public void paintingSegment(Segment segmentToPaint) throws InterruptedException {
        Plank plankToPaint;
        synchronized (segmentToPaint) {
            if(segmentToPaint.getUnpaintedPlanksList().isEmpty()){
                segmentToPaint.setStatus(Status.Painted);
                segmentToPaint.getPainterList().remove(this);
                return;
            } else if (segmentToPaint.getPainterList().isEmpty()){
                segmentToPaint.addPainter(this);
                startIndex = 0;
                lastIndex = segmentToPaint.getLenght() - 1;
                currentIndex = 0;
            } else if (!segmentToPaint.getPainterList().contains(this)){
                segmentToPaint.addPainter(this);
                List<Plank> longestPlanks = segmentToPaint.getLongestUnpaintedPlanksList();
                segmentToPaint.getFragmentOfSegment(longestPlanks);

                int startIndex = segmentToPaint.getIndexOfStart(longestPlanks);
                this.startIndex = startIndex;
                this.lastIndex = segmentToPaint.getLastIndex(longestPlanks);
                currentIndex = startIndex;
            } else {
                ++currentIndex;
            }
            plankToPaint = segmentToPaint.getPlankList().get(currentIndex);
        }

        paintingPlank(plankToPaint, segmentToPaint);
    }

    public void paintingPlank(Plank plank, Segment segmentToPaint) throws InterruptedException {
        synchronized (plank) {
            if(!plank.getStatus().equals(Status.Unpainted)){
                segmentToPaint.getPainterList().remove(this);
            } else {
                if(getBucket() == null || getBucket().getLeftPaint()<=0) {
                    PaintContainer container = Fence.getContainer();
                    while (container.getUsingBy() != null || container.isEmpty()) {
                        TimeUnit.SECONDS.sleep(1);
                    }
                    container.setUsingBy(this);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        container.getPaint(this);
                    } catch (InterruptedException ex) {
                        System.out.println("Getting paint by painter has been interrupted.");
                    }
                    container.setUsingBy(null);
                } else {
                    plank.setStatus(Status.InProcces);
                    System.out.println("painter " + id + " painting " + plank.getId());
                    while(plank.getProgress() < 1.0){
                        plank.setProgress(plank.getProgress() + 0.2);
                        Thread.sleep(this.speed);
                    }
                    bucket.setLeftPaint(bucket.getLeftPaint() - 1);
                    plank.setPaintedBy(this);
                    plank.setStatus(Status.Painted);
                }
            /* else {
                container.refillContainer();
                FenceFrame.setUpLabels();
            }*/
            }
            fenceFrame.setUpLabels();
        }
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
