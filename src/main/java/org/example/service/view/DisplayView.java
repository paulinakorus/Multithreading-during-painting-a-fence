package org.example.service.view;

import org.example.model.*;

public class DisplayView {
    private Fence fence;
    private PaintContainer container;

    public DisplayView(Fence fence, PaintContainer container){
        this.fence = fence;
        this.container = container;
    }
    public void viewData(){

        Character refillingChar = container.getRefilling() ? 'S' : '.';
        Character refillingPainter = container.getUsingBy() != null ? container.getUsingBy().getName() : '.';
        System.out.println(refillingChar + "[" + container.getLeftPaint() + "]" + refillingPainter);
        for (Painter painter : Painter.getPainterList()) {
            System.out.print(painter.getName() + " ");
        }
        System.out.print("\n");

        for (Painter painter : Painter.getPainterList()) {
            System.out.print(painter.getBucket().getLeftPaint() + " ");
        }
        System.out.print("\n");

        Character painterChar;
        System.out.print("|");
        for (Segment segment : fence.getSegmentList()) {
            for (Plank plank : segment.getPlankList()) {
                painterChar = plank.getPaintedBy() != null ? plank.getPaintedBy().getName() : '.';
                System.out.print(painterChar);
            }
            System.out.print("|");
        }
    }
}
