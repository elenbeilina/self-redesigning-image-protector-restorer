package actions;

import frames.MainFrame;

import javax.swing.*;


public abstract class AbstractProgramMenuAction extends AbstractAction {

    public AbstractProgramMenuAction(String name) {
        super(name);
        this.owner = null;
    }

    public AbstractProgramMenuAction(MainFrame owner, String name) {
        super(name);
        this.owner = owner;
    }

    protected MainFrame owner;

}
