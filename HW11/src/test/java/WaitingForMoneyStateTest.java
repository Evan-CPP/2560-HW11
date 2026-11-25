import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class WaitingForMoneyStateTest {

    @Test
    void testSelectSnack() {
        Snack coke = new Snack("Coke", 1.50, 2);
        Snack pepsi = new Snack("Pepsi", 1.50, 2);
        ConcreteSnackDispenseHandler h1 = new ConcreteSnackDispenseHandler(coke);
        h1.setNextHandler(new ConcreteSnackDispenseHandler(pepsi));
        VendingMachine vm = new VendingMachine(List.of(coke, pepsi), h1);

        vm.setState(new WaitingForMoneyState());
        vm.setSelectedSnack("Coke");
        vm.getState().selectSnack(vm, "Pepsi");

        assertEquals("Pepsi", vm.getSelectedSnack());
        assertInstanceOf(WaitingForMoneyState.class, vm.getState());
    }

    @Test
    void testInsertMoney() {
        Snack coke = new Snack("Coke", 1.50, 2);
        VendingMachine vm = new VendingMachine(List.of(coke), new ConcreteSnackDispenseHandler(coke));
        vm.setState(new WaitingForMoneyState());
        vm.setSelectedSnack("Coke");

        vm.getState().insertMoney(vm, 1.00);
        assertEquals(1.00, vm.getInsertedMoney());
        assertInstanceOf(WaitingForMoneyState.class, vm.getState());

        vm.getState().insertMoney(vm, 0.50);
        assertEquals(1, coke.getQuantity());
        assertInstanceOf(IdleState.class, vm.getState());
    }

    @Test
    void testDispenseSnack() {
        Snack coke = new Snack("Coke", 1.50, 2);
        VendingMachine vm = new VendingMachine(List.of(coke), new ConcreteSnackDispenseHandler(coke));
        vm.setState(new WaitingForMoneyState());
        vm.setSelectedSnack("Coke");

        vm.getState().dispenseSnack(vm);
        assertEquals(2, coke.getQuantity());
        assertInstanceOf(WaitingForMoneyState.class, vm.getState());
    }
}
