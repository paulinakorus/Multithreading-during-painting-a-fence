package org.example.GUI;

import org.example.model.Fence;
import org.example.model.PaintContainer;
import org.example.model.Painter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StarterFrame extends JFrame{
    private JPanel startPanel;
    private JTextField paintersNumberField;
    private JTextField segmentsNumberField;
    private JTextField segmentLenghtField;
    private JLabel paintingLabel;
    private JButton insertButton;
    private JTextField containerVolumeField;
    private JLabel segmentsNumberLabel;
    private JLabel segmentsLenghtLabel;
    private JLabel containerVolumeLabel;
    private JLabel paintersNumberLabel;

    public StarterFrame(){
        this.setTitle("StartPanel");                                   // set title of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);           // exit out off application
        this.setResizable(false);                                      // preventing frame from being resized
        this.setSize(700, 500);                            // setting size
        this.setVisible(true);                                         // making frame visible
        this.add(startPanel);

        setUpButtons();
    }

    private void setUpButtons(){
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==insertButton){
                    int numberOfSegments = Integer.parseInt(segmentsNumberField.getText());
                    int lenghtOfSegment = Integer.parseInt(segmentLenghtField.getText());
                    int volumeOfContainer = Integer.parseInt(containerVolumeField.getText());
                    int paintersNumber = Integer.parseInt(paintersNumberField.getText());

                    createPainters(paintersNumber);
                    Fence fence = new Fence(numberOfSegments, lenghtOfSegment);
                    PaintContainer container = new PaintContainer(volumeOfContainer);

                    FenceFrame view = new FenceFrame(fence, container, Painter.getPainterList());
                }
            }
        });
    }

    private void createPainters(int paintersNumber){
        for (int i=0; i<paintersNumber; i++){
            Painter painter = new Painter();
        }
    }
}
