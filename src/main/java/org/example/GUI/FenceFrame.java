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
    private final DisplayView displayView;

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
        displayView = new DisplayView(fence, container);

        setUpLabels();
        //setUpSimulation();
    }

    public void setUpLabels() {
        new Thread(() -> {
            String[] line = {displayView.firstLine(), displayView.paintersNamesLine(), displayView.paintersBucketsLine(), displayView.fenceLine()};
            SwingUtilities.invokeLater(() -> setTextToLabels(line));
        }).start();
    }

    public void setTextToLabels(String[] line){
        containerLabel.setText(line[0]);
        paintersNamesLabel.setText(line[1]);
        paintersBucketsLabel.setText(line[2]);
        fenceLineLabel.setText(line[3]);
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

    public static Fence getFence() {
        return fence;
    }

    public static void setFence(Fence fence) {
        FenceFrame.fence = fence;
    }

    public static PaintContainer getContainer() {
        return container;
    }

    public static void setContainer(PaintContainer container) {
        FenceFrame.container = container;
    }

    public static List<Painter> getPainterList() {
        return painterList;
    }

    public static void setPainterList(List<Painter> painterList) {
        FenceFrame.painterList = painterList;
    }
}
