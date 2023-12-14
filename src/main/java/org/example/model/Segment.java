package org.example.model;

import org.example.model.enums.Status;

import java.util.ArrayList;
import java.util.List;

public class Segment {
    private static int id;
    private Status status;
    private int lenght;
    private List<Plank> plankList = new ArrayList<>();
    public Segment(int lenght){
        this.lenght = lenght;
        this.status = Status.Unpainted;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Segment.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public List<Plank> getPlankList() {
        return plankList;
    }

    public void setPlankList(List<Plank> plankList) {
        this.plankList = plankList;
    }
}
