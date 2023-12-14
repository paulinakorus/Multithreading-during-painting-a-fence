package org.example.model;

import java.util.concurrent.TimeUnit;

public class PaintSupplier implements Runnable{
    Thread thread;
    private PaintContainer container;
    public PaintSupplier(PaintContainer container){
        this.container = container;
    }
    public void refillContainter(){
        try {
            container.setRefilling(true);
            TimeUnit.SECONDS.sleep(15);
            container.setLeftPaint(container.getVolume());
            container.setRefilling(false);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void run(){
        if(container.isEmpty())
            refillContainter();
    }
}
