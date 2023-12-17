package org.example.model;

import org.example.GUI.FenceFrame;

import java.util.concurrent.TimeUnit;

public class PaintSupplier implements Runnable {
    private static FenceFrame fenceFrame;
    private static PaintContainer container;
    private static PaintSupplier INSTANCE = null;
    private boolean refilling;

    public PaintSupplier(PaintContainer container, FenceFrame fenceFrame) {
        this.fenceFrame = fenceFrame;
        this.container = container;
        this.refilling = false;
    }

    public void refillContainter() {
        try {
            synchronized (container) {
                TimeUnit.SECONDS.sleep(2);
                refilling = true;
                container.setLeftPaint(container.getVolume());
                System.out.println("Paint supplied");
                refilling = false;
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
                if (container.isEmpty()) {
                    refillContainter();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static PaintSupplier getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new PaintSupplier(container, fenceFrame);
        }
        return INSTANCE;
    }

    public boolean isRefilling() {
        return refilling;
    }

    public void setRefilling(boolean refilling) {
        this.refilling = refilling;
    }
}
