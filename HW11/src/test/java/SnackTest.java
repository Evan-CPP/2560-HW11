import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SnackTest {

    @Test
    void testIsAvailable() {
        Snack inStock = new Snack("Coke", 1.50, 2);
        Snack outOfStock = new Snack("Snickers", 1.75, 0);

        assertTrue(inStock.isAvailable());
        assertFalse(outOfStock.isAvailable());
    }

    @Test
    void testDecrementQuantity() {
        Snack snack = new Snack("Coke", 1.50, 2);
        snack.decrementQuantity();
        assertEquals(1, snack.getQuantity());

        snack.decrementQuantity();
        snack.decrementQuantity();
        assertEquals(0, snack.getQuantity());
    }
}
