package eduardolivenza.instruments;

import eduardolivenza.instruments.Commands.CommandA;
import eduardolivenza.instruments.Commands.CommandB;
import eduardolivenza.instruments.Commands.ICommand;
import eduardolivenza.instruments.modules.IModule;
import eduardolivenza.instruments.modules.Module;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class InstrumentModuleManagerTest {

    private InstrumentModuleManager manager;

    @Mock
    private CommandA CommandA;
    @Mock
    private CommandB CommandB;
    @Mock
    private Module mockedModule;

    private final static String instrumentName1 = "Instrument 1";
    private final static String instrumentName2 = "PreAnalitic 2";
    private final static String instrumentName3 = "PostAnalitic 3";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.manager = InstrumentModuleManager.getInstance();
        IModule m = new Module();
        ArrayList<IModule> modules = new ArrayList<IModule>();
        modules.add(m);
        manager.addNewInstrument( new AnaliticInstrument(instrumentName1, modules ));
        ICommand commandA = CommandA;
        manager.addCommandToInstrument(instrumentName1, "Sort tubes", commandA);
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
    public void addNewOperationOverAnInstrumentExistingModule() {
        assertThat("Checking that instrument has already a command prepared", manager.getInstrumentByName(instrumentName1).getCommands().size() ==1);
        IModule m = manager.getInstrumentByName(instrumentName1).getModules().get(0);
        ICommand commandB = new CommandB(m);
        manager.addCommandToInstrument(instrumentName1, " Aliquot tube", commandB);
        assertThat("Checking that command was added in the instrument", manager.getInstrumentByName(instrumentName1).getCommands().size() ==2);
    }

    @Test
    public void addSameOperationToMoreThanOneModule() {

        IModule m = manager.getInstrumentByName(instrumentName1).getModules().get(0);
        IModule m2 = new Module();
        manager.addModuleToInstrument(instrumentName1, m2);

        ICommand commandB1 = new CommandB(m);
        ICommand commandB2 = new CommandB(m2);
        manager.addCommandToInstrument(instrumentName1, " P1", commandB1);
        manager.addCommandToInstrument(instrumentName1, " P2", commandB2);
        assertThat("Checking that both command of same type were added in the instrument", manager.getInstrumentByName(instrumentName1).getCommands().size() ==3);
    }

    @Test
    public void executingAnOperationOverAnInstrumentExistingModule() {
        ICommand m= manager.getInstrumentByName(instrumentName1).getCommandByName("Sort tubes");
        m.exeOperation();
        verify(CommandA, times(1)).exeOperation();

    }

    @Test
    public void addAnExecuteTwoCommands() {
        manager.addCommandToInstrument(instrumentName1, " P1", CommandB);
        manager.addCommandToInstrument(instrumentName1, " P2", CommandB);

        for (ICommand m: manager.getInstrumentByName(instrumentName1).getCommands()){
            m.exeOperation();
        }
        verify(CommandA, times(1)).exeOperation();
        verify(CommandB, times(2)).exeOperation();
    }

    @Test
    public void executeCommandOverAModule() {
        manager.addModuleToInstrument(instrumentName1, mockedModule);
        manager.addCommandToInstrument(instrumentName1, "ExeOverMockedModule", new CommandB(mockedModule) );
        manager.getInstrumentByName(instrumentName1).getCommandByName("ExeOverMockedModule").exeOperation();
        verify(mockedModule, times(1)).getName();
    }

    @Test
    public void addOtherTypesOfInstrument() {

        manager.addNewInstrument( new PreAnaliticInstrument(instrumentName2, new ArrayList<IModule>() ));
        manager.addNewInstrument( new PostAnaliticInstrument(instrumentName3, new ArrayList<IModule>() ));

        IModule m1 = new Module();
        manager.addModuleToInstrument(instrumentName2, m1);
        manager.addCommandToInstrument(instrumentName2, "CommandA", new CommandA(m1));

        assertThat("Check modules I1", manager.getInstrumentByName(instrumentName1).getModules().size() ==1);
        assertThat("Check modules I2", manager.getInstrumentByName(instrumentName2).getModules().size() ==1);
        assertThat("Check modules I3", manager.getInstrumentByName(instrumentName3).getModules().size() ==0);

        assertThat("Check commands number I2", manager.getInstrumentByName(instrumentName2).getCommands().size() ==1);
        assertThat("Check commands number I3", manager.getInstrumentByName(instrumentName3).getCommands().size() ==0);
    }

}