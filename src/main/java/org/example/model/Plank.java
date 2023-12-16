package org.example.model;

import org.example.model.enums.Status;

public class Plank {
    private int id;
    private Status status;
    private Double progress;
    private Painter paintedBy = null;

    public Plank(int id){
        this.id = id;
        this.status = Status.Unpainted;
        this.progress = 0.0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public Painter getPaintedBy() {
        return paintedBy;
    }

    public void setPaintedBy(Painter paintedBy) {
        this.paintedBy = paintedBy;
    }
}
