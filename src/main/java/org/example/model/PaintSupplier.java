package org.example.model;

import org.example.GUI.FenceFrame;

import java.util.concurrent.TimeUnit;

public class PaintSupplier implements Runnable{
    Thread thread;
    private final FenceFrame fenceFrame;
    private final PaintContainer container;
    public PaintSupplier(PaintContainer container, FenceFrame fenceFrame){
        this.fenceFrame = fenceFrame;
        this.container = container;
    }
    public void refillContainter(){
        try {
            synchronized (container) {
                TimeUnit.SECONDS.sleep(2);
                container.setLeftPaint(container.getVolume());
                System.out.println("Paint supplied");
                //fenceFrame.setUpLabels();
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(2);
                if(container.isEmpty())
                    refillContainter();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
