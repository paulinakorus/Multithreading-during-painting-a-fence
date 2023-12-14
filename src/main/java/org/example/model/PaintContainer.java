package org.example.model;

public class PaintContainer {
    private int volume;
    private volatile int leftPaint;
    private volatile Boolean refilling;
    private volatile Painter usingBy;

    public PaintContainer(int volume){
        this.volume = volume;
        refilling = false;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getLeftPaint() {
        return leftPaint;
    }

    public void setLeftPaint(int leftPaint) {
        this.leftPaint = leftPaint;
    }

    public Boolean getRefilling() {
        return refilling;
    }

    public void setRefilling(Boolean refilling) {
        this.refilling = refilling;
    }

    public Painter getUsingBy() {
        return usingBy;
    }

    public void setUsingBy(Painter usingBy) {
        this.usingBy = usingBy;
    }
}
