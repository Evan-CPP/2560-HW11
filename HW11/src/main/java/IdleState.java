public class IdleState implements StateOfVendingMachine {

    @Override
    public void selectSnack(VendingMachine machine, String snackName) {
        Snack snack = machine.findSnack(snackName);
        if (snack == null) {
            System.out.println("Snack not found: " + snackName);
            return;
        }
        machine.setSelectedSnack(snackName);
        machine.setInsertedMoney(0.0);
        System.out.println("Selected: " + snack.getName() + " ($" + String.format("%.2f", snack.getPrice()) + ")");
        machine.setState(new WaitingForMoneyState());
    }

    @Override
    public void insertMoney(VendingMachine machine, double amount) {
        System.out.println("Please select a snack before inserting money.");
    }

    @Override
    public void dispenseSnack(VendingMachine machine) {
        System.out.println("Please select a snack and insert money first.");
    }
}
