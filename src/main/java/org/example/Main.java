package org.example;

import org.example.model.Fence;
import org.example.model.PaintContainer;
import org.example.model.Painter;
import org.example.view.DisplayView;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int paintersNumber;
        int numberOfSegments;
        int lenghtOfSegment;
        int volumeOfContainer;

        Scanner input = new Scanner(System.in);
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
        view.viewData();
    }
}