package org.example.model;

import org.example.model.enums.Status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fence {
    private Status fenceStatus;
    private static int segmentsNumber;
    private static int lenghtOfSegment;
    private static PaintContainer container;
    private static Fence INSTANCE = null;
    private final List<Segment> segmentList = Collections.synchronizedList(new ArrayList<>());

    public Fence(int segmentsNumber, int lenghtOfSegment, PaintContainer container){
        Fence.segmentsNumber = segmentsNumber;
        Fence.lenghtOfSegment = lenghtOfSegment;
        Fence.container = container;
        fenceStatus = Status.Unpainted;

        for(int i=0; i<segmentsNumber; i++){
            segmentList.add(new Segment(lenghtOfSegment, i));
        }
    }

    public List<Segment> findSegmentByStatus(Status segmentStatus){
        return segmentList.stream().filter(segment -> segment.getStatus().equals(segmentStatus)).toList();
    }

    public Segment findSegmentToWorkOn(){
        synchronized (segmentList) {
            List<Segment> unpaintedSegments = findSegmentByStatus(Status.Unpainted);
            if(!unpaintedSegments.isEmpty()) {
                return unpaintedSegments.get(0);
            } else {
                List<Segment> paintedSegments = findSegmentByStatus(Status.InProcces);
                if (!paintedSegments.isEmpty()) {
                    return paintedSegments.stream().filter(s -> s.getPlankList().stream().anyMatch(p -> p.getStatus().equals(Status.Unpainted))).findFirst().orElse(null);
                } else {
                    fenceStatus = Status.Painted;
                    return null;
                }
            }
        }
    }

    public static Fence getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Fence(segmentsNumber, lenghtOfSegment, container);
        }
        return INSTANCE;
    }

    public static PaintContainer getContainer() {
        return container;
    }

    public static void setContainer(PaintContainer container) {
        Fence.container = container;
    }

    public Status getStatus() {
        return fenceStatus;
    }

    public void setStatus(Status status) {
        this.fenceStatus = status;
    }

    public List<Segment> getSegmentList() {
        return segmentList;
    }

}
