package org.example.model;

import org.example.model.enums.Status;

import java.util.ArrayList;
import java.util.List;

public class Fence {
    private Status status;
    private int segmentsNumber;
    private int lenghtOfSegment;
    List<Segment> segmentList = new ArrayList<>();

    public Fence(int segmentsNumber, int lenghtOfSegment){
        this.segmentsNumber = segmentsNumber;
        this.lenghtOfSegment = lenghtOfSegment;
        status = Status.Unpainted;

        for(int i=0; i<segmentsNumber; i++){
            segmentList.add(new Segment(lenghtOfSegment));
        }
    }



    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Segment> getSegmentList() {
        return segmentList;
    }

    public void setSegmentList(List<Segment> segmentList) {
        this.segmentList = segmentList;
    }
}
