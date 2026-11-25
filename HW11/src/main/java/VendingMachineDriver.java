import java.util.List;

public class VendingMachineDriver {
    public static void main(String[] args) {
        Snack coke = new Snack("Coke", 1.50, 3);
        Snack pepsi = new Snack("Pepsi", 1.50, 3);
        Snack cheetos = new Snack("Cheetos", 1.25, 2);
        Snack doritos = new Snack("Doritos", 1.25, 2);
        Snack kitKat = new Snack("KitKat", 1.75, 2);
        Snack snickers = new Snack("Snickers", 1.75, 2);

        List<Snack> snacks = List.of(coke, pepsi, cheetos, doritos, kitKat, snickers);

        ConcreteSnackDispenseHandler cokeHandler = new ConcreteSnackDispenseHandler(coke);
        ConcreteSnackDispenseHandler pepsiHandler = new ConcreteSnackDispenseHandler(pepsi);
        ConcreteSnackDispenseHandler cheetosHandler = new ConcreteSnackDispenseHandler(cheetos);
        ConcreteSnackDispenseHandler doritosHandler = new ConcreteSnackDispenseHandler(doritos);
        ConcreteSnackDispenseHandler kitKatHandler = new ConcreteSnackDispenseHandler(kitKat);
        ConcreteSnackDispenseHandler snickersHandler = new ConcreteSnackDispenseHandler(snickers);

        cokeHandler.setNextHandler(pepsiHandler);
        pepsiHandler.setNextHandler(cheetosHandler);
        cheetosHandler.setNextHandler(doritosHandler);
        doritosHandler.setNextHandler(kitKatHandler);
        kitKatHandler.setNextHandler(snickersHandler);

        VendingMachine machine = new VendingMachine(snacks, cokeHandler);

        System.out.println("----- Buy coke with extra money -----");
        machine.selectSnack("Coke");
        machine.insertMoney(2.00);

        System.out.println("\n----- Partially pay for KitKat then top up -----");
        machine.selectSnack("KitKat");
        machine.insertMoney(1.00);
        machine.insertMoney(0.75);

        System.out.println("\n----- Buy all snickers -----");
        machine.selectSnack("Snickers");
        machine.insertMoney(1.75);

        machine.selectSnack("Snickers");
        machine.insertMoney(2.00);

        System.out.println("\n----- Try to buy snickers when amount is 0 -----");
        machine.selectSnack("Snickers");
        machine.insertMoney(1.75);
    }
}
