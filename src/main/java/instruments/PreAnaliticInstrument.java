package instruments;

import instruments.modules.Commands.ICommand;
import instruments.modules.IModule;

import java.util.ArrayList;
import java.util.HashMap;

public class PreAnaliticInstrument extends Instrument {

    public PreAnaliticInstrument(String name, ArrayList<IModule> modules){
        this.name = name;
        this.modules = modules;
        this.commandsList = new HashMap<String , ICommand>();
    }

    @Override
    public String getInstrumentType() {
        return "PreAnalitic";
    }
}
