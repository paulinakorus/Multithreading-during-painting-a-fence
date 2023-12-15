package org.example.GUI;

import org.example.model.Fence;
import org.example.model.PaintContainer;
import org.example.model.PaintSupplier;
import org.example.model.Painter;
import org.example.service.view.DisplayView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class FenceFrame extends JFrame{
    private JPanel fencePanel;
    private JLabel fenceLabel;
    private JLabel containerLabel;
    private JLabel paintersNamesLabel;
    private JLabel paintersBucketsLabel;
    private JLabel fenceLineLabel;
    private JScrollPane scrollPanelFence;

    private Fence fence;
    private PaintContainer container;
    List<Painter> painterList;

    public FenceFrame(Fence fence, PaintContainer container, List<Painter> painterList){
        this.setTitle("Fence");                                        // set title of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);           // exit out off application
        this.setResizable(false);                                      // preventing frame from being resized
        this.setSize(700, 500);                            // setting size
        this.setVisible(true);                                         // making frame visible
        this.add(fencePanel);

        this.fence = fence;
        this.container = container;
        this.painterList = painterList;

        setUpLabels();
    }

    private void setUpLabels(){
        DisplayView view = new DisplayView(fence, container);
        containerLabel.setText(view.firstLine());
        paintersNamesLabel.setText(view.paintersNamesLine());
        paintersBucketsLabel.setText(view.paintersBucketsLine());
        fenceLineLabel.setText(view.fenceLine());
    }
}
