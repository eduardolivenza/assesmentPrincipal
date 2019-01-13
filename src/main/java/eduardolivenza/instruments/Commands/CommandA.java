package eduardolivenza.instruments.Commands;

import eduardolivenza.instruments.modules.IModule;

public class CommandA implements ICommand {

    IModule module;


    public CommandA(IModule module){
        this.module = module;
    }

    public void exeOperation() {
        System.out.println(" Executing command A over module " + module.getName());
    }
}
