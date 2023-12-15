package org.example.model;

import org.example.model.enums.Status;

import java.util.*;
import java.util.stream.Collectors;

public class Segment {
    private static int id;
    private Status status;
    private int lenght;
    private List<Plank> plankList = Collections.synchronizedList(new ArrayList<>());
    public Segment(int lenght){
        id+=1;
        this.lenght = lenght;
        this.status = Status.Unpainted;

        for(int i=0; i<lenght; i++){
            plankList.add(new Plank(i+1));
        }
    }

    public List<Plank> getLongestUnpaintedPlanksList(){
        return getUnpaintedPlanksList().stream()
                .map(List::stream)
                .map(innerList -> innerList.collect(Collectors.toList()))
                .max(Comparator.comparingInt(List::size))
                .orElse(null);
    }

    public List<List<Plank>> getUnpaintedPlanksList(){
        List<List<Plank>> splittedList = null;
        List<Plank> onePart = null;

        for (Plank plank : plankList) {
            if(splittedList == null && onePart == null){
                if(plank.getStatus().equals(Status.Unpainted)){
                    splittedList = new ArrayList<>();
                    onePart = new ArrayList<>();
                    onePart.add(plank);
                }
            }else if(plank.getStatus().equals(Status.Unpainted)){
                onePart.add(plank);
            } else {
                splittedList.add(onePart);
                onePart = new ArrayList<>();
            }
        }
        if(!onePart.isEmpty())
            splittedList.add(onePart);
        return splittedList;
    }

    public int getIndexOfStart(List<Plank> plankList){
        int firstIndex = plankList.get(0).getId()-1;
        int lenght = plankList.size();

        if(lenght%2 == 0)
            return firstIndex + lenght/2;
        else
            return firstIndex + lenght/2 + 1;
    }

    //lastIndexForPainter

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Segment.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public List<Plank> getPlankList() {
        return plankList;
    }

    public void setPlankList(List<Plank> plankList) {
        this.plankList = plankList;
    }
}
