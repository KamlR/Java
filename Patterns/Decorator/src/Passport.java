public class Passport extends CardDecorator {
    public Passport(Card card) {
        super(card);
    }

    @Override
    public void addData() {
        card.addData();
        System.out.println(": данные паспорта добавлены");
    }
}
