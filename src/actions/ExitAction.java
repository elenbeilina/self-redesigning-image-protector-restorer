package actions;

import frames.MainFrame;

import java.awt.event.ActionEvent;

public class ExitAction extends AbstractProgramMenuAction {
	
	public ExitAction(MainFrame owner) {
		super(owner, "Exit");
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		owner.exit();
	}

}
