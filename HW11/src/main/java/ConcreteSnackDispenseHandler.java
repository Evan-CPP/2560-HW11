public class ConcreteSnackDispenseHandler extends SnackDispenseHandler {
    private final Snack snack;

    public ConcreteSnackDispenseHandler(Snack snack) {
        this.snack = snack;
    }

    public Snack getSnack() {
        return snack;
    }

    @Override
    public Snack dispense(String snackName, double insertedMoney) {
        if (!snack.getName().equalsIgnoreCase(snackName)) {
            return forwardToNext(snackName, insertedMoney);
        }

        if (!snack.isAvailable()) {
            System.out.println(snack.getName() + " is out of stock.");
            return null;
        }

        if (insertedMoney < snack.getPrice()) {
            System.out.println("Insufficient money for " + snack.getName());
            return null;
        }

        snack.decrementQuantity();
        return snack;
    }
}
