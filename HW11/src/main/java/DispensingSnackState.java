public class DispensingSnackState implements StateOfVendingMachine {

    @Override
    public void selectSnack(VendingMachine machine, String snackName) {
        System.out.println("Currently dispensing a snack. Please wait.");
    }

    @Override
    public void insertMoney(VendingMachine machine, double amount) {
        System.out.println("Currently dispensing a snack. Cannot accept money now.");
    }

    @Override
    public void dispenseSnack(VendingMachine machine) {
        String snackName = machine.getSelectedSnack();
        double money = machine.getInsertedMoney();

        Snack dispensed = machine.getDispenseHandler().dispense(snackName, money);
        if (dispensed != null) {
            double change = money - dispensed.getPrice();
            System.out.println("Dispensed: " + dispensed.getName() +
                    (change > 0 ? " | Change returned: $" + String.format("%.2f", change) : ""));
        } else {
            System.out.println("Could not dispense " + snackName + ". Returning $" + String.format("%.2f", money));
        }

        machine.setSelectedSnack(null);
        machine.setInsertedMoney(0.0);
        machine.setState(new IdleState());
    }
}
