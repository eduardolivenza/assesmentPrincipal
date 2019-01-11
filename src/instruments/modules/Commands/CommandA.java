package instruments.modules.Commands;

import instruments.modules.IModule;

public class CommandA implements ICommand {

    IModule module;


    public CommandA(IModule module){
        this.module = module;
    }

    @Override
    public void exeOperation() {
        System.out.println(" Excuting command a over module " + module.getName());
    }
}
