package org.example.GUI;

import org.example.model.Fence;
import org.example.model.PaintContainer;
import org.example.model.PaintSupplier;
import org.example.model.Painter;
import org.example.service.DisplayView;
import org.example.service.Executor;

import javax.swing.*;
import java.util.List;

public class FenceFrame extends JFrame{
    private static FenceFrame INSTANCE = null;
    private JPanel fencePanel;
    private JLabel fenceLabel;
    private JLabel containerLabel;
    private JLabel paintersNamesLabel;
    private JLabel paintersBucketsLabel;
    private JLabel fenceLineLabel;
    private JScrollPane scrollPanelFence;

    private static Fence fence;
    private static PaintContainer container;
    private static List<Painter> painterList;

    public FenceFrame(Fence fence, PaintContainer container, List<Painter> painterList){
        this.setTitle("Fence");                                        // set title of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);           // exit out off application
        this.setResizable(false);                                      // preventing frame from being resized
        this.setSize(700, 500);                            // setting size
        this.setVisible(true);                                         // making frame visible
        this.add(fencePanel);

        FenceFrame.fence = fence;
        FenceFrame.container = container;
        FenceFrame.painterList = painterList;

        setUpLabels();
        //setUpSimulation();
    }

    public synchronized void setUpLabels(){
        DisplayView view = new DisplayView(fence, container);
        containerLabel.setText(view.firstLine());
        paintersNamesLabel.setText(view.paintersNamesLine());
        paintersBucketsLabel.setText(view.paintersBucketsLine());
        fenceLineLabel.setText(view.fenceLine());
    }

    public void setUpContainerLabel(DisplayView view){
        containerLabel.setText(view.firstLine());
    }

    public void setUpPaintersLabel(DisplayView view){
        paintersNamesLabel.setText(view.paintersNamesLine());
        paintersBucketsLabel.setText(view.paintersBucketsLine());
    }

    public void setUpFenceLabel(DisplayView view){
        fenceLineLabel.setText(view.fenceLine());
    }

    /*public void setUpSimulation(){
        Executor.execute(new PaintSupplier(container));
        for (Painter painter : Painter.getPainterList()) {
            Executor.execute(painter);
        }
    }*/

    public static FenceFrame getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new FenceFrame(fence, container, painterList);
        }
        return INSTANCE;
    }
}
