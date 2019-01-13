package eduardolivenza.instruments.Commands;

import eduardolivenza.instruments.modules.IModule;

public class CommandB implements ICommand {

    IModule module;

    public CommandB(IModule module){
        this.module = module;
    }

    public void exeOperation() {

        System.out.println(" Command B triggered. Can do some operations over module: " + module.getName());
    }
}
