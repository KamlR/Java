public class CreditCard extends CardDecorator {
    public CreditCard(Card card) {
        super(card);
    }

    @Override
    public void addData() {
        card.addData();
        System.out.println(": данные кредитной карты добавлены");
    }

}
