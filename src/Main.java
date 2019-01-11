import instruments.AnaliticInstrument;
import instruments.InstrumentModuleManager;
import instruments.modules.Commands.CommandA;
import instruments.modules.Commands.CommandB;
import instruments.modules.IModule;
import instruments.modules.Module;

import java.util.ArrayList;

public class Main {

    private final static String instrumentName1 = "Instrument 1";
    private final static String instrumentName2 = "Instrument 2";

    public static void main(String[] args) {

        InstrumentModuleManager manager = new InstrumentModuleManager();
        manager.addNewInstrument( new AnaliticInstrument(instrumentName1, new ArrayList<IModule>()));
        manager.addNewInstrument( new AnaliticInstrument(instrumentName2, new ArrayList<IModule>()));

        IModule m = new Module();
        manager.addModuleToInstrument(instrumentName1, m);
        manager.addCommandToInstrument(instrumentName1, "MyCommand1", new CommandA (m) );
        manager.addCommandToInstrument(instrumentName1, "MyCommand2", new CommandB(m) );


        manager.getInstrumentByName(instrumentName1).getCommandByName("MyCommand1").exeOperation();
    }

}
