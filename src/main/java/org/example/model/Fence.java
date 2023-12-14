package org.example.model;

import org.example.model.enums.Status;

import java.util.ArrayList;
import java.util.List;

public class Fence {
    private Status status;
    List<Segment> segmentList = new ArrayList<>();

    public Fence(){
        status = Status.Unpainted;
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
