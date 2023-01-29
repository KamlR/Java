public class ComputerFacade {
    final private StartStopComputer startStopComputer;

    public ComputerFacade() {
        startStopComputer = new StartStopComputer();
    }

    public void startComputer() {
        startStopComputer.turnOnLaptop();
        System.out.println("Работа пользователя...\n");
    }

    public void stopComputer() {
        startStopComputer.turnOffLaptop();
    }
}
