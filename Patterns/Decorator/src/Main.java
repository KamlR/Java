public class Main {
    public static void main(String[] args) {
        Card electronicCard = new ElectronicCard();
        Card passport = new Passport(electronicCard);
        Card insurance = new InsurancePolicy(electronicCard);
        Card credit = new CreditCard(electronicCard);
        passport.addData();
        insurance.addData();
        credit.addData();
    }
}