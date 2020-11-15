package actions;

import frames.MainFrame;
import methods.Protector;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class ImageProtectAction extends AbstractProgramMenuAction {

    public ImageProtectAction(MainFrame owner) {
        super(owner, "Protect image");
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        try {
            owner.protectImage(new Protector());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
