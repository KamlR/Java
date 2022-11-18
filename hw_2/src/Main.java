import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.*;

public class Main {
    static ArrayList<Student> students = new ArrayList<Student>();

    public static void FileReader() {
        File file = new File("C:\\Users\\Karina\\Documents\\Education\\Java\\input.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] temp = line.split(" ");
                students.add(new Student(temp[0], temp[1], temp[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileReader();
        int amount = PrintStudents();
        Asking(amount);
        PrintMarks();
        WriteInFile();
    }

    public static int PrintStudents() {
        int count = 1;
        for (Student obj : students) {
            out.println(count + ") " + obj.toString());
            count++;
        }
        out.println("Сколько студентов вы хотите спросить?");
        int amount;
        while (true) {
            Scanner console = new Scanner(in);
            amount = console.nextInt();
            if (amount < 0 || amount > count) {
                out.println("Неверное кол-во студентов, повторите ввод");
            } else {
                break;
            }
        }
        return amount;
    }

    public static void Asking(int amount) {
        for (int i = 0; i < amount; i++) {
            out.println("Введите номер студента, которого хотите спросить:");
            int number;
            while (true) {
                Scanner console = new Scanner(in);
                number = console.nextInt();
                if (number <= 0 || number > students.size()) {
                    out.println("Студента с таким номером нет в списке, повторите ввод");
                } else {
                    break;
                }
            }
            out.println("Отвечает " + students.get(number - 1).getName() + " " + students.get(number - 1).getSurname());
            if (students.get(number - 1).getPresence().equals("absent")) {
                out.println("Данного студента нет на паре, переходим дальше");
            } else if (students.get(number - 1).getMark() != -1) {
                out.println("У данного студента уже есть оценка, переходим дальше");
            } else {
                int random_mark = (int) (Math.random() * 5) + 1;
                students.get(number - 1).setMark(random_mark);
                out.println(students.get(number - 1).yourMark());
            }
        }
    }

    public static void PrintMarks() {
        out.println("Оценки студентов за семинар:");
        for (Student obj : students) {
            if (obj.getMark() != -1) {
                out.println(obj.getName() + " " + obj.getSurname() + " :" + obj.getMark());
            }
        }
    }

    public static void WriteInFile() {
        try (FileWriter writer = new FileWriter("C:\\Users\\Karina\\Documents\\Education\\Java\\output.txt", false)) {
            for (Student obj : students) {
                if (obj.getMark() != -1) {
                    writer.write(obj.getName() + " " + obj.getSurname() + ": " + obj.getMark() + "\n");
                }
            }
            writer.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}