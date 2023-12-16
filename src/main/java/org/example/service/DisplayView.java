package org.example.service;

import org.example.model.*;

public class DisplayView {
    private final Fence fence;
    private final PaintContainer container;

    public DisplayView(Fence fence, PaintContainer container){
        this.fence = fence;
        this.container = container;
    }
    public String firstLine() {
        synchronized (container) {
            Character refillingChar = container.getRefilling() ? 'S' : '.';
            Character refillingPainter = container.getUsingBy() != null ? container.getUsingBy().getName() : '.';
            return (refillingChar + "[" + container.getLeftPaint() + "]" + refillingPainter);
        }
    }

    public String paintersNamesLine(){
        String line = "";
        for (Painter painter : Painter.getPainterList()) {
            line += painter.getName() + " ";
        }
        return line;
    }

    public String paintersBucketsLine(){
        String line = "";
        for (Painter painter : Painter.getPainterList()) {
            line += painter.getBucket().getLeftPaint() + "  ";
        }
        return line;
    }

    public String fenceLine() {
        Character painterChar;
        String line = "|";
        synchronized (fence) {
            for (Segment segment : fence.getSegmentList()) {
                for (Plank plank : segment.getPlankList()) {
                    painterChar = plank.getPaintedBy() != null ? plank.getPaintedBy().getName() : '.';
                    line += painterChar;
                }
                line += "|";
            }
        }
        return line;
    }
}
