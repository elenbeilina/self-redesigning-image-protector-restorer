package actions;

import frames.MainFrame;
import methods.Protector;

import java.awt.event.ActionEvent;

public class ImageRestorerAction extends AbstractProgramMenuAction {

    public ImageRestorerAction(MainFrame owner) {
        super(owner, "Restore image");
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        try {
            owner.restoreImage(new Protector());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
