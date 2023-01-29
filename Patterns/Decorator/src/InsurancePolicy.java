public class InsurancePolicy extends CardDecorator {
    public InsurancePolicy(Card card) {
        super(card);
    }

    @Override
    public void addData() {
        card.addData();
        System.out.println(": данные страхвого полиса добавлены");
    }

}
