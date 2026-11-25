import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class IdleStateTest {

    private VendingMachine buildMachine() {
        Snack coke = new Snack("Coke", 1.50, 2);
        return new VendingMachine(List.of(coke), new ConcreteSnackDispenseHandler(coke));
    }

    @Test
    void testSelectSnack() {
        VendingMachine vm = buildMachine();
        vm.getState().selectSnack(vm, "Unknown");
        assertInstanceOf(IdleState.class, vm.getState());

        vm.getState().selectSnack(vm, "Coke");
        assertEquals("Coke", vm.getSelectedSnack());
        assertInstanceOf(WaitingForMoneyState.class, vm.getState());
    }

    @Test
    void testInsertMoney() {
        VendingMachine vm = buildMachine();
        vm.getState().insertMoney(vm, 2.00);
        assertEquals(0.0, vm.getInsertedMoney());
        assertInstanceOf(IdleState.class, vm.getState());
    }

    @Test
    void testDispenseSnack() {
        VendingMachine vm = buildMachine();
        vm.getState().dispenseSnack(vm);
        assertInstanceOf(IdleState.class, vm.getState());
    }
}
