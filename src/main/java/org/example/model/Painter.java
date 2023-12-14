package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Painter {
    private static int id;
    private Character name;
    private Double speed;
    private PaintBucket bucket;
    private static List<Painter> painterList = new ArrayList<>();
    private Thread thread;
    private Random random;

    public Painter(){
        speed = (random.nextInt(100)+1)/100.0;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Painter.id = id;
    }

    public Character getName() {
        return name;
    }

    public void setName(Character name) {
        this.name = name;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public PaintBucket getBucket() {
        return bucket;
    }

    public void setBucket(PaintBucket bucket) {
        this.bucket = bucket;
    }

    public static List<Painter> getPainterList() {
        return painterList;
    }

    public static void setPainterList(List<Painter> painterList) {
        Painter.painterList = painterList;
    }
}
