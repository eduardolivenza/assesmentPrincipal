package eduardolivenza.instruments;

import eduardolivenza.instruments.Commands.ICommand;
import eduardolivenza.instruments.modules.IModule;

import java.util.Collection;
import java.util.List;

public interface IInstrument {

    void addInstrumentCommand(String commandName, ICommand command);

    void addInstrumentModule (IModule m);

    String getInstrumentName();

    String getInstrumentType();

    List<IModule> getModules();

    ICommand getCommandByName(String myCommand);

    Collection<ICommand> getCommands();
}
