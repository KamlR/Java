public abstract class CardDecorator implements Card {
    Card card;

    public CardDecorator(Card data) {
        this.card = data;
    }
}

