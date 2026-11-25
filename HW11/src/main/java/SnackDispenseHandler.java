public abstract class SnackDispenseHandler {
    private SnackDispenseHandler nextHandler;

    public void setNextHandler(SnackDispenseHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public SnackDispenseHandler getNextHandler() {
        return nextHandler;
    }

    public abstract Snack dispense(String snackName, double insertedMoney);

    protected Snack forwardToNext(String snackName, double insertedMoney) {
        if (nextHandler != null) {
            return nextHandler.dispense(snackName, insertedMoney);
        }
        return null;
    }
}
