import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DispensingSnackStateTest {

    private VendingMachine buildDispensingMachine(Snack snack) {
        VendingMachine vm = new VendingMachine(List.of(snack), new ConcreteSnackDispenseHandler(snack));
        vm.setSelectedSnack(snack.getName());
        vm.setInsertedMoney(2.00);
        vm.setState(new DispensingSnackState());
        return vm;
    }

    @Test
    void testSelectSnack() {
        VendingMachine vm = buildDispensingMachine(new Snack("Coke", 1.50, 2));
        vm.getState().selectSnack(vm, "Coke");
        assertInstanceOf(DispensingSnackState.class, vm.getState());
    }

    @Test
    void testInsertMoney() {
        VendingMachine vm = buildDispensingMachine(new Snack("Coke", 1.50, 2));
        vm.getState().insertMoney(vm, 1.00);
        assertEquals(2.00, vm.getInsertedMoney());
        assertInstanceOf(DispensingSnackState.class, vm.getState());
    }

    @Test
    void testDispenseSnack() {
        Snack coke = new Snack("Coke", 1.50, 2);
        VendingMachine vm = buildDispensingMachine(coke);

        vm.getState().dispenseSnack(vm);
        assertEquals(1, coke.getQuantity());
        assertNull(vm.getSelectedSnack());
        assertEquals(0.0, vm.getInsertedMoney());
        assertInstanceOf(IdleState.class, vm.getState());
    }
}
