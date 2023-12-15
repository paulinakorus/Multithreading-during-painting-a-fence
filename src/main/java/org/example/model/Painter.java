package org.example.model;

import org.example.model.enums.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Painter implements Runnable{
    private static int id;
    private Character name;
    private Double speed;
    private PaintBucket bucket = new PaintBucket();
    private static List<Painter> painterList = new ArrayList<>();

    private Random random = new Random();
    private Fence fence = Fence.getInstance();

    public Painter(){
        id+=1;
        name = (char)((int)'A' + (id-1));
        speed = (random.nextInt(10)+1)*100.0;
        painterList.add(this);
    }

    @Override
    public void run(){
        /*
        try {
            while(fence.getStatus() != Status.Painted){

            }
        }catch (InterruptedException ex) {
            System.out.println("Painter " + this.getId() + " job interrupted.");
        }*/
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
