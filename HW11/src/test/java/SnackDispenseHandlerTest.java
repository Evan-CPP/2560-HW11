import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SnackDispenseHandlerTest {

    static class PassThroughHandler extends SnackDispenseHandler {
        boolean called = false;

        @Override
        public Snack dispense(String snackName, double insertedMoney) {
            called = true;
            return forwardToNext(snackName, insertedMoney);
        }
    }

    static class TerminalHandler extends SnackDispenseHandler {
        private final Snack snack;

        TerminalHandler(Snack snack) {
            this.snack = snack;
        }

        @Override
        public Snack dispense(String snackName, double insertedMoney) {
            if (snack.getName().equalsIgnoreCase(snackName)) {
                return snack;
            }
            return forwardToNext(snackName, insertedMoney);
        }
    }

    @Test
    void testSetNextHandler() {
        PassThroughHandler first = new PassThroughHandler();
        TerminalHandler second = new TerminalHandler(new Snack("Coke", 1.50, 3));
        first.setNextHandler(second);

        assertEquals(second, first.getNextHandler());
    }

    @Test
    void testDispense() {
        Snack coke = new Snack("Coke", 1.50, 3);
        PassThroughHandler first = new PassThroughHandler();
        TerminalHandler second = new TerminalHandler(coke);
        first.setNextHandler(second);

        Snack result = first.dispense("Coke", 2.00);
        assertTrue(first.called);
        assertEquals(coke, result);
        assertNull(first.dispense("Pepsi", 2.00));
    }

    @Test
    void testForwardToNext() {
        Snack pepsi = new Snack("Pepsi", 1.50, 2);
        PassThroughHandler first = new PassThroughHandler();
        TerminalHandler second = new TerminalHandler(pepsi);
        first.setNextHandler(second);

        assertEquals(pepsi, first.forwardToNext("Pepsi", 1.50));
    }
}
