public class WaitingForMoneyState implements StateOfVendingMachine {

    @Override
    public void selectSnack(VendingMachine machine, String snackName) {
        Snack snack = machine.findSnack(snackName);
        if (snack == null) {
            System.out.println("Snack not found: " + snackName);
            return;
        }
        machine.setSelectedSnack(snackName);
        System.out.println("Changed selection to: " + snack.getName());
    }

    @Override
    public void insertMoney(VendingMachine machine, double amount) {
        if (amount <= 0) {
            System.out.println("Please insert a positive amount.");
            return;
        }

        double total = machine.getInsertedMoney() + amount;
        machine.setInsertedMoney(total);
        Snack snack = machine.findSnack(machine.getSelectedSnack());

        if (snack == null) {
            System.out.println("Invalid snack selection. Returning $" + String.format("%.2f", total));
            machine.setInsertedMoney(0.0);
            machine.setState(new IdleState());
            return;
        }

        if (!snack.isAvailable()) {
            System.out.println(snack.getName() + " is out of stock. Returning $" + String.format("%.2f", total));
            machine.setSelectedSnack(null);
            machine.setInsertedMoney(0.0);
            machine.setState(new IdleState());
            return;
        }

        if (total < snack.getPrice()) {
            double remaining = snack.getPrice() - total;
            System.out.println("Inserted $" + String.format("%.2f", total) +
                    ". Please insert $" + String.format("%.2f", remaining) + " more.");
            return;
        }

        machine.setState(new DispensingSnackState());
        machine.dispenseSnack();
    }

    @Override
    public void dispenseSnack(VendingMachine machine) {
        System.out.println("Insufficient money inserted. Cannot dispense yet.");
    }
}
