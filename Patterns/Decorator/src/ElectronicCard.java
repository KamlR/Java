public class ElectronicCard implements Card {
    final String cardName = "Электоронная карта";

    public ElectronicCard() {
        System.out.println("Ваша электронная карта создана!");
        System.out.println("Теперь вы можете добавлять на неё данные!");
    }

    @Override
    public void addData() {
        System.out.print(cardName);
    }
}
