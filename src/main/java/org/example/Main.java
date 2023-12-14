package org.example;

import org.example.model.*;
import org.example.model.enums.Status;
import org.example.service.view.DisplayView;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int paintersNumber;
        int numberOfSegments;
        int lenghtOfSegment;
        int volumeOfContainer;

        /*Scanner input = new Scanner(System.in);
        System.out.println("Laboratoria 5");
        System.out.print("\tnumbers of painters: ");
        paintersNumber = input.nextInt();
        System.out.print("\tnumber of segments: ");
        numberOfSegments = input.nextInt();
        System.out.print("\tlenght of the segment: ");
        lenghtOfSegment = input.nextInt();
        System.out.print("\tvolume of the container: ");
        volumeOfContainer = input.nextInt();
        System.out.print("\n");

        Painter a = new Painter();
        Painter b = new Painter();

        Fence fence = new Fence(numberOfSegments, lenghtOfSegment);
        PaintContainer container = new PaintContainer(volumeOfContainer);
        DisplayView view = new DisplayView(fence, container);
        view.viewData();*/

        Segment segment = new Segment(10);
        List<Plank> planks = segment.getPlankList();

        planks.get(0).setStatus(Status.Painted);
        planks.get(1).setStatus(Status.Painted);
        planks.get(2).setStatus(Status.Unpainted);
        planks.get(3).setStatus(Status.Unpainted);
        planks.get(4).setStatus(Status.Unpainted);
        planks.get(5).setStatus(Status.Painted);
        planks.get(6).setStatus(Status.Unpainted);
        planks.get(7).setStatus(Status.Painted);
        planks.get(8).setStatus(Status.Unpainted);
        planks.get(9).setStatus(Status.Painted);

        List<List<Plank>> unpaintedPlanksList = segment.getUnpaintedPlanksList();
    }
}