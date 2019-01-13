package instruments;

import instruments.modules.Commands.ICommand;
import instruments.modules.IModule;

import java.util.HashMap;

public class InstrumentModuleManager {

    HashMap<String, IInstrument> instruments;

    public  InstrumentModuleManager(){
        this.instruments = new HashMap<String, IInstrument>();;
    }
    public void addNewInstrument(IInstrument instrument){
        this.instruments.put(instrument.getInstrumentName(), instrument);
    }

    public void addModuleToInstrument(String instrumentName, IModule module)
    {
        IInstrument instrument = getInstrumentByName(instrumentName);
        instrument.getModules().add(module);
    }

    public IInstrument getInstrumentByName(String instrumentName){
        return this.instruments.get(instrumentName);
    }


    public void addCommandToInstrument(String instrumentName, String commandName, ICommand command) {
        IInstrument instrument = getInstrumentByName(instrumentName);
        instrument.addInstrumentCommand(commandName, command);
    }


}
