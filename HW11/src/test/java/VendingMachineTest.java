import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class VendingMachineTest {

    private VendingMachine createMachine() {
        Snack coke = new Snack("Coke", 1.50, 2);
        Snack snickers = new Snack("Snickers", 1.75, 1);
        ConcreteSnackDispenseHandler h1 = new ConcreteSnackDispenseHandler(coke);
        ConcreteSnackDispenseHandler h2 = new ConcreteSnackDispenseHandler(snickers);
        h1.setNextHandler(h2);
        return new VendingMachine(List.of(coke, snickers), h1);
    }

    @Test
    void testFindSnack() {
        VendingMachine vm = createMachine();
        assertNotNull(vm.findSnack("coke"));
        assertEquals("Coke", vm.findSnack("COKE").getName());
        assertNull(vm.findSnack("Missing"));
    }

    @Test
    void testSelectSnack() {
        VendingMachine vm = createMachine();
        vm.selectSnack("Coke");
        assertEquals("Coke", vm.getSelectedSnack());
        assertInstanceOf(WaitingForMoneyState.class, vm.getState());
    }

    @Test
    void testInsertMoney() {
        VendingMachine vm = createMachine();
        vm.selectSnack("Coke");
        vm.insertMoney(1.00);
        assertEquals(1.00, vm.getInsertedMoney());
        assertInstanceOf(WaitingForMoneyState.class, vm.getState());
    }

    @Test
    void testDispenseSnack() {
        VendingMachine vm = createMachine();
        vm.selectSnack("Snickers");
        vm.insertMoney(2.00);
        assertEquals(0, vm.findSnack("Snickers").getQuantity());
        assertInstanceOf(IdleState.class, vm.getState());
    }
}
