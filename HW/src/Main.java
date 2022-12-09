import java.io.File;
import java.util.Scanner;

import static java.lang.System.*;

public class Main {
    static Scanner input = new Scanner(System.in);
    static Library library;

    public static int Greeting() {
        String mode_str;
        int mode;
        out.println("Что вы хотите? (просто введите номер цифры)");
        out.println("1) Получить полный список книг");
        out.println("2) Узнать, есть ли в наличии книга с таким названием");
        out.println("3) Отдать книгу в библиотеку");
        do {
            mode_str = input.nextLine();
            try {
                mode = Integer.parseInt(mode_str);
                if (mode >= 1 && mode <= 3) {
                    break;
                } else {
                    out.println("Неверный ввод, повторите попытку");
                }
            } catch (NumberFormatException e) {
                out.println("Неверный ввод, повторите попытку");
            }
        } while (true);
        return mode;
    }

    public static void main(String[] args) {
        final File file = new File("C:\\Users\\Karina\\Documents\\Education\\Java\\books.txt");
        library = new Library(file);
        out.println("Приветствуем Вас в библиотеке!");
        do {
            int mode = Greeting();
            modes(mode);
            String line;
            out.println("Хотите ли вы продолжить работу с библиотекой (yes or no)");
            do {
                line = input.nextLine();
                if (!line.equals("yes") && !line.equals("no")) {
                    out.println("Повторите ввод, мы Вас не поняли");
                } else {
                    break;
                }
            } while (true);
            if (line.equals("no")) {
                break;
            }

        } while (true);

    }

    public static void modes(int mode) {
        if (mode == 1) {
            library.getAllBooks();
        } else if (mode == 2) {
            out.println("Введите название книги и фамилию автора с инициалами (через !)");
            String[] temp = input.nextLine().split("!");
            library.containBook(temp[0], temp[1], temp[2]);
        } else if (mode == 3) {
            out.println("Введите название книги и фамилию автора с инициалами (через !), которую хотите вернуть");
            String[] temp = input.nextLine().split("!");
            library.returnBook(temp[0], temp[1], temp[2]);
        }
    }

}