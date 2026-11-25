import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
    private StateOfVendingMachine state;
    private SnackDispenseHandler dispenseHandler;
    private final List<Snack> snacks;
    private String selectedSnack;
    private double insertedMoney;

    public VendingMachine(List<Snack> snacks, SnackDispenseHandler dispenseHandler) {
        this.snacks = new ArrayList<>(snacks);
        this.dispenseHandler = dispenseHandler;
        this.state = new IdleState();
        this.selectedSnack = null;
        this.insertedMoney = 0.0;
    }

    public StateOfVendingMachine getState() {
        return state;
    }

    public void setState(StateOfVendingMachine state) {
        this.state = state;
    }

    public SnackDispenseHandler getDispenseHandler() {
        return dispenseHandler;
    }

    public void setDispenseHandler(SnackDispenseHandler dispenseHandler) {
        this.dispenseHandler = dispenseHandler;
    }

    public List<Snack> getSnacks() {
        return snacks;
    }

    public String getSelectedSnack() {
        return selectedSnack;
    }

    public void setSelectedSnack(String selectedSnack) {
        this.selectedSnack = selectedSnack;
    }

    public double getInsertedMoney() {
        return insertedMoney;
    }

    public void setInsertedMoney(double insertedMoney) {
        this.insertedMoney = insertedMoney;
    }

    public Snack findSnack(String snackName) {
        if (snackName == null) {
            return null;
        }
        for (Snack snack : snacks) {
            if (snack.getName().equalsIgnoreCase(snackName)) {
                return snack;
            }
        }
        return null;
    }

    public void selectSnack(String snackName) {
        state.selectSnack(this, snackName);
    }

    public void insertMoney(double amount) {
        state.insertMoney(this, amount);
    }

    public void dispenseSnack() {
        state.dispenseSnack(this);
    }
}
