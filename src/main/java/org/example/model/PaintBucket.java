package org.example.model;

import java.util.Random;

public class PaintBucket {
    private static int id = 0;
    private int volume;
    private volatile int leftPaint;
    private Random random = new Random();

    public PaintBucket(){
        id+=1;
        volume = random.nextInt(5)+1;
        leftPaint = volume;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        PaintBucket.id = id;
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
}
