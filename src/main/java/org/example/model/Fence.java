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
    private List<Segment> segmentList = Collections.synchronizedList(new ArrayList<>());

    public Fence(int segmentsNumber, int lenghtOfSegment, PaintContainer container){
        Fence.segmentsNumber = segmentsNumber;
        Fence.lenghtOfSegment = lenghtOfSegment;
        Fence.container = container;
        fenceStatus = Status.Unpainted;

        for(int i=0; i<segmentsNumber; i++){
            segmentList.add(new Segment(lenghtOfSegment));
        }
    }

    public synchronized List<Segment> findSegmentByStatus(Status segmentStatus){
        return segmentList.stream().filter(segment -> segment.getStatus().equals(segmentStatus)).toList();
    }

    public synchronized Segment findSegmentToWorkOn(){
        List<Segment> unpaintedSegments = findSegmentByStatus(Status.Unpainted);
        if(unpaintedSegments != null){
            return unpaintedSegments.get(0);
        } else {
            List<Segment> paintedSegments = findSegmentByStatus(Status.Painted);
            if (paintedSegments != null) {
                return paintedSegments.get(0);
            } else {
                fenceStatus = Status.Painted;
                return null;
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

    public void setSegmentList(List<Segment> segmentList) {
        this.segmentList = segmentList;
    }
}
