package actions;

import frames.MainFrame;
import methods.Protector;

import java.awt.event.ActionEvent;

public class ImageAuthenticateAction extends AbstractProgramMenuAction {

    public ImageAuthenticateAction(MainFrame owner) {
        super(owner, "Authenticate image");
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        owner.authenticateImage(new Protector());
    }
}
