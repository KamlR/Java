public class Main {
    public static void main(String[] args) {

        TrafficPolice moscow = new MoscowTrafficPolice();
        moscow.setMessage("Остановить автомобиль с номер 509Р");
        moscow.notifyAllMembers();
        TrafficPolice petersburg = new StPetersburgTrafficPolice();
        petersburg.setMessage("Остановить автомобиль с номер 123Р");
        petersburg.notifyAllMembers();
        TrafficPolice novgorod = new NovgorodTrafficPolice();
        novgorod.setMessage("Остановить автомобиль с номер 003Р");
        novgorod.notifyAllMembers();
    }
}