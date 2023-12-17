package org.example.model;

import org.example.service.Executor;

public class PaintContainer {
    private int volume;
    private volatile Integer leftPaint;
    private volatile Painter usingBy;

    public PaintContainer(int volume){
        this.volume = volume;
        leftPaint = volume;
        usingBy = null;
    }

    public int getVolume() {
        return volume;
    }

    /*public synchronized void refillContainer(){
        PaintSupplier supplier = new PaintSupplier(this);
        Executor.execute(supplier);
    }*/

    public int getPaint(Painter painter) {
        int bucketVolume = painter.getBucket().getVolume();
        if(!isEmpty()){
            if((leftPaint - bucketVolume) >= 0){
                leftPaint -= bucketVolume;
                return bucketVolume;
                //painter.getBucket().setLeftPaint(bucketVolume);
            } else {
//                painter.getBucket().setLeftPaint(leftPaint);
                int paint = leftPaint;
                leftPaint = 0;
                return paint;
            }
        }
        return 0;
    }
    public synchronized boolean isEmpty(){
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

    public Painter getUsingBy() {
        return usingBy;
    }

    public void setUsingBy(Painter usingBy) {
        this.usingBy = usingBy;
    }
}
