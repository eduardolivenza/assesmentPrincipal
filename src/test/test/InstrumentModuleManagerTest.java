package test;

import instruments.AnaliticInstrument;
import instruments.InstrumentModuleManager;
import instruments.modules.Commands.CommandA;
import instruments.modules.Commands.ICommand;
import instruments.modules.IModule;
import instruments.modules.Module;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;

public class InstrumentModuleManagerTest {

    private InstrumentModuleManager manager;

    private final static String instrumentName1 = "Instrument 1";
    private final static String instrumentName2 = "Instrument 2";

    @Before
    public void setUp() {
        this.manager = new InstrumentModuleManager();
        IModule m = new Module();
        ArrayList<IModule> modules = new ArrayList<IModule>();
        modules.add(m);
        manager.addNewInstrument( new AnaliticInstrument(instrumentName1, modules ));
    }

    @Test
    public void checkInitialStateAndAddingExtraModules() {

        assertThat("Check that  is created with one module", manager.getInstrumentByName(instrumentName1).getModules().size() ==1);
        IModule m2 = new Module();
        manager.addModuleToInstrument(instrumentName1, m2);
        assertThat("Checking that more modules can be added to existing instrument", manager.getInstrumentByName(instrumentName1).getModules().size() ==2);
    }

    @Test
    public void addingOperationOverAnInstrumentExistingModule() {
        IModule m = manager.getInstrumentByName(instrumentName1).getModules().get(0);
        ICommand commandA = new CommandA(m);
        manager.addCommandToInstrument(instrumentName1, "Sort tubes", commandA);
        assertThat("Checking that command was added in the instrument", manager.getInstrumentByName(instrumentName1).getCommands().size() ==1);
    }

    @Test
    public void executingAnOperationOverAnInstrumentExistingModule() {
        IModule m = manager.getInstrumentByName(instrumentName1).getModules().get(0);
        ICommand commandA = new CommandA(m);
        manager.addCommandToInstrument(instrumentName1, "Sort tubes", commandA);
        assertThat("Checking that command was added in the instrument", manager.getInstrumentByName(instrumentName1).getCommands().size() ==1);
    }
}