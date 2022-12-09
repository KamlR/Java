import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.*;

public class Library {
    ArrayList<Book> books = new ArrayList<Book>();
    File file;
    Scanner input = new Scanner(System.in);

    public Library(File file) {
        this.file = file;
        readFromFile();
    }

    public void getAllBooks() {
        for (Book book : books) {
            if (book.count_book != 0) {
                out.println(book.book_name + " " + book.author_surname + " " + book.author_initials + " " + book.count_book);
            }
        }
    }

    public void containBook(String book_name, String author_initials, String author_surname) {
        for (Book book : books) {
            if (book.count_book >= 1 && book.book_name.equals(book_name) && book.author_surname.equals(author_surname)
                    && book.author_initials.equals(author_initials)) {
                out.println("Такая книга есть, хотите ли вы её взять? (yes or no)");
                String answer;
                do {
                    answer = input.nextLine();
                    if (answer.equals("yes")) {
                        out.println("Книга переданы Вам на руки");
                        book.count_book--;
                        break;
                    } else if (answer.equals("no")) {
                        break;
                    } else {
                        out.println("Повторите ввод, мы Вас не поняли");
                    }
                } while (true);
                return;
            }
        }
        out.println("Такой книги нет в наличии");
    }

    public void returnBook(String book_name, String author_initials, String author_surname) {
        for (Book book : books) {
            if (book.book_name.equals(book_name) && book.author_surname.equals(author_surname)
                    && book.author_initials.equals(author_initials)) {
                book.count_book++;
                out.println("Ваша книга возвращена");
                return;
            }
        }
        out.println("Такой книги не было в библиотеке");
    }

    private void readFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] temp = line.split("!");
                books.add(new Book(temp[0], temp[1], temp[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
