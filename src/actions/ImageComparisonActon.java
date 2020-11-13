package actions;

import frames.MainFrame;
import methods.Descriptor;
import methods.ImageComparison;

import java.awt.event.ActionEvent;

public class ImageComparisonActon extends AbstractProgramMenuAction {

    public ImageComparisonActon(MainFrame owner) {
        super(owner, "Perceptive hash and hamming distance");
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        owner.compareImage(new ImageComparison());
    }
}