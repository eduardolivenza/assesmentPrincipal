package instruments;

import instruments.modules.Commands.ICommand;
import instruments.modules.IModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnaliticInstrument extends Instrument {

    public AnaliticInstrument(String name,  ArrayList<IModule> modules){
        this.name = name;
        this.modules = modules;
        this.commandsList = new HashMap<String , ICommand>();
    }

    @Override
    public String getInstrumentType() {
        return "Analitic";
    }


}
