    package instruments;


    import instruments.modules.Commands.ICommand;
    import instruments.modules.IModule;

    import java.util.ArrayList;
    import java.util.Collection;
    import java.util.HashMap;
    import java.util.List;

public abstract class Instrument implements IInstrument{

    ArrayList<IModule> modules;
    HashMap<String , ICommand> commandsList;
    String name;

    public void addInstrumentModule (IModule m){
        this.modules.add(m);
    }

    public void addInstrumentCommand(String commandName, ICommand command){
        this.commandsList.put(commandName, command);
    }

    @Override
    public String getInstrumentName() {
        return name;
    }

    public List<IModule> getModules(){
        return modules;
    }

    public ICommand getCommandByName(String myCommand){
        return this.commandsList.get(myCommand);
    };


    @Override
    public Collection<ICommand> getCommands() {
        return commandsList.values();
    }
}
