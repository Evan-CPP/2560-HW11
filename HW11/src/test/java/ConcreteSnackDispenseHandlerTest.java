import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConcreteSnackDispenseHandlerTest {

    @Test
    void testDispense() {
        Snack coke = new Snack("Coke", 1.50, 2);
        Snack snickers = new Snack("Snickers", 2.00, 0);

        ConcreteSnackDispenseHandler cokeHandler = new ConcreteSnackDispenseHandler(coke);
        ConcreteSnackDispenseHandler snickersHandler = new ConcreteSnackDispenseHandler(snickers);
        cokeHandler.setNextHandler(snickersHandler);

        assertNull(cokeHandler.dispense("Coke", 1.00));
        assertEquals(2, coke.getQuantity());

        Snack dispensed = cokeHandler.dispense("Coke", 2.00);
        assertEquals(coke, dispensed);
        assertEquals(1, coke.getQuantity());

        assertNull(cokeHandler.dispense("Snickers", 2.50));
        assertEquals(0, snickers.getQuantity());
    }
}
