package org.example.service;

import org.example.GUI.FenceFrame;
import org.example.model.*;

public class DisplayView {
    private final Fence fence;
    private final PaintSupplier supplier = PaintSupplier.getInstance();

    public DisplayView(Fence fence, PaintContainer container){
        this.fence = fence;
    }
    public String firstLine() {
        var container = FenceFrame.getContainer();
        synchronized (container) {
            synchronized (supplier) {
                Character refillingChar = supplier.isRefilling() ? 'S' : '.';
                Character refillingPainter = container.getUsingBy() != null ? container.getUsingBy().getName() : '.';
                return (refillingChar + "[" + container.getLeftPaint() + "]" + refillingPainter);
            }
        }
    }

    public String paintersNamesLine(){
        String line = "";
        var painters = Painter.getPainterList();
        synchronized (painters) {
            for (Painter painter : painters) {
                line += painter.getName() + " ";
            }
            return line;
        }
    }

    public String paintersBucketsLine(){
        String line = "";
        var painters = Painter.getPainterList();
        synchronized (painters) {
            for (Painter painter : painters) {
                line += painter.getBucket().getLeftPaint() + "  ";
            }
            return line;
        }
    }

    public String fenceLine() {
        Character painterChar;
        String line = "|";
        var segments = fence.getSegmentList();
        synchronized (segments) {
            for (Segment segment : segments) {
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
