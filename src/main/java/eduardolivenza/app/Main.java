package eduardolivenza.app;

import eduardolivenza.instruments.AnaliticInstrument;
import eduardolivenza.instruments.InstrumentModuleManager;
import eduardolivenza.instruments.Commands.CommandA;
import eduardolivenza.instruments.Commands.CommandB;
import eduardolivenza.instruments.modules.IModule;
import eduardolivenza.instruments.modules.Module;

import java.util.ArrayList;

public class Main {

    private final static String instrumentName1 = "Instrument 1";
    private final static String instrumentName2 = "Instrument 2";

    public static void main(String[] args) {

        InstrumentModuleManager manager = InstrumentModuleManager.getInstance();
        manager.addNewInstrument( new AnaliticInstrument(instrumentName1, new ArrayList<IModule>()));
        manager.addNewInstrument( new AnaliticInstrument(instrumentName2, new ArrayList<IModule>()));

        IModule m = new Module();
        manager.addModuleToInstrument(instrumentName1, m);
        manager.addCommandToInstrument(instrumentName1, "MyCommand1", new CommandA (m) );
        manager.addCommandToInstrument(instrumentName1, "MyCommand2", new CommandB(m) );


        manager.getInstrumentByName(instrumentName1).getCommandByName("MyCommand1").exeOperation();
        manager.getInstrumentByName(instrumentName1).getCommandByName("MyCommand2").exeOperation();
    }

}
