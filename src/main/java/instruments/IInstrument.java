package instruments;

import instruments.modules.Commands.ICommand;
import instruments.modules.IModule;

import java.util.List;

public interface IInstrument {

    void addInstrumentCommand(String commandName, ICommand command);

    void addInstrumentModule (IModule m);

    String getInstrumentName();

    String getInstrumentType();

    List<IModule> getModules();

    ICommand getCommandByName(String myCommand);
}
