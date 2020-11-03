package actions;


import frames.MainFrame;
import methods.Encryptor;

import java.awt.event.ActionEvent;

public class LSBMethodEncryptionAction extends AbstractProgramMenuAction {
	
	public LSBMethodEncryptionAction(MainFrame owner) {
		super(owner, "LSB Method");
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		owner.encryptImage(new Encryptor());
	}

}
