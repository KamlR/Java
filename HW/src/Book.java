public class Book {
    int count_book;
    String author_initials;
    String author_surname;
    String book_name;

    public Book(String book_name, String author_initials, String author_surname) {
        this.book_name = book_name;
        this.author_initials = author_initials;
        this.author_surname = author_surname;
        count_book = 1;
    }
}
