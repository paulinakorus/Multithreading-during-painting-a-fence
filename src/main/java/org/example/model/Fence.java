package org.example.model;

import org.example.model.enums.Status;

import java.util.ArrayList;
import java.util.List;

public class Fence {
    private Status fenceStatus;
    private int segmentsNumber;
    private int lenghtOfSegment;
    List<Segment> segmentList = new ArrayList<>();

    public Fence(int segmentsNumber, int lenghtOfSegment){
        this.segmentsNumber = segmentsNumber;
        this.lenghtOfSegment = lenghtOfSegment;
        fenceStatus = Status.Unpainted;

        for(int i=0; i<segmentsNumber; i++){
            segmentList.add(new Segment(lenghtOfSegment));
        }
    }

    public List<Segment> findSegmentByStatus(Status segmentStatus){
        return segmentList.stream().filter(segment -> segment.getStatus().equals(segmentStatus)).toList();
    }

    public Segment findSegmentToWorkOn(){
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
