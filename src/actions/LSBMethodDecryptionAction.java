package actions;

import frames.MainFrame;
import methods.Descriptor;

import java.awt.event.ActionEvent;

public class LSBMethodDecryptionAction extends AbstractProgramMenuAction {

    public LSBMethodDecryptionAction(MainFrame owner) {
        super(owner, "LSB Method");
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        owner.decryptImage(new Descriptor());
    }
}
