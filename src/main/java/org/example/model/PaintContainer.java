package org.example.model;

import org.example.service.Executor;

public class PaintContainer {
    private int volume;
    private volatile int leftPaint;
    private volatile Boolean refilling;
    private volatile Painter usingBy = null;

    public PaintContainer(int volume){
        this.volume = volume;
        refilling = false;
        leftPaint = volume;
    }

    public int getVolume() {
        return volume;
    }

    /*public synchronized void refillContainer(){
        PaintSupplier supplier = new PaintSupplier(this);
        Executor.execute(supplier);
    }*/

    public synchronized void getPaint(Painter painter){
        int backetVolume = painter.getBucket().getVolume();
        if(!isEmpty()){
            if((leftPaint - backetVolume) > 0){
                leftPaint -= backetVolume;
                painter.getBucket().setLeftPaint(backetVolume);
            } else {
                painter.getBucket().setLeftPaint(leftPaint);
                leftPaint = 0;
            }
        }
    }
    public boolean isEmpty(){
        if(leftPaint == 0)
            return true;
        return false;
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
