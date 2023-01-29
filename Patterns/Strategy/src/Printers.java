public class Printers {
    PrintStrategy printStrategy;

    public Printers(PrintStrategy printStrategy) {
        this.printStrategy = printStrategy;
    }

    public void turnOnPrinter() {
        System.out.println("Ваш принтер включён");
    }

    public void turnOffPrinter() {
        System.out.println("Ваш принтер выключен");
    }

    public void print() {
        printStrategy.print();
    }
}
