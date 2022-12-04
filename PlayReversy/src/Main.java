import java.util.Scanner;

import static java.lang.System.*;

public class Main {
    // В массиве хранится максимальное кол-во очков за сессию.
    static int[] max_points = new int[]{-1, -1, -1, -1};

    // Приветствие для пользователя.
    public static int greeting() {
        Scanner input = new Scanner(System.in);
        String mode_str;
        int mode;
        do {
            out.println("Выберите режим игры, введите цифру от 1 до 3):");
            out.println("1) Лёгкий режим с компьютером");
            out.println("2) Продвинутый режим с компьютером");
            out.println("3) Игрок против игрока");
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

    // Основная точка входа в программу.
    public static void main(String[] args) {
        out.println("Добро пожаловать в Реверси!");
        do {
            int mode = greeting();
            if (mode == 1) {
                lightMode();
            } else if (mode == 2) {
                hardMode();
            } else {
                twoPeople();
            }
            if (playOneMoreTime().equals("n")) {
                break;
            }
        } while (true);
        out.println("Максимальное кол-во очков за сессию для каждого играющего:");
        if (max_points[0] > -1) {
            out.println("Компбютер максимально набрал: " + max_points[0]);
        }
        if (max_points[1] > -1) {
            out.println("Пользователь, играя с компьютером, максимально набрал: " + max_points[1]);
        }
        if (max_points[2] > -1) {
            out.println("Первый игрок (3 режим ) максимально набрал: " + max_points[2]);
        }
        if (max_points[3] > -1) {
            out.println("Второй игрок (3 режим ) максимально набрал: " + max_points[2]);
        }
    }

    // Запуск простого режима игры с компьютером.
    public static void lightMode() {
        out.println("Вы выбрали лёгкий режим игры с компьютером");
        out.println("Компьютер играет за чёрных, Вы за белых, чёрные ходят первыми");
        Play play = new Play("computer", "you");
        ComputerLight computer = new ComputerLight();
        Person person = new Person();
        int count = 0;
        while (true) {
            count++;
            if (computer.canWePlay("w")) {
                computer.computerRun();
            } else {
                out.println("Игра завершена!");
                break;
            }
            if (person.canWePlay("b")) {
                if (count < 2 || !person.cancelMove("b")) {
                    person.personRun("b");
                }
            } else {
                out.println("Игра завершена!");
                break;
            }
        }
        play.finalBoard();
        max_points = play.getPoints(max_points);
    }

    // Запуск сложного режима игры с компьютером.
    public static void hardMode() {
        out.println("Вы выбрали сложный режим игры с компьютером");
        out.println("Компьютер играет за чёрных, Вы за белых, чёрные ходят первыми");
        Play play = new Play("computer", "you");
        ComputerHard computer = new ComputerHard();
        Person person = new Person();
        int count = 0;
        while (true) {
            count++;
            if (computer.canWePlay("w")) {
                computer.computerRun();
            } else {
                out.println("Игра завершена!");
                break;
            }
            if (person.canWePlay("b")) {
                if (count < 2 || !person.cancelMove("b")) {
                    person.personRun("b");
                }
            } else {
                out.println("Игра завершена!");
                break;
            }
        }
        play.finalBoard();
        max_points = play.getPoints(max_points);
    }

    // Запуск режима игры двух людей.
    public static void twoPeople() {
        out.println("Вы выбрали режим игры с человеком");
        out.println("Чёрные фишки ходят первыми, затем белые");
        Play play = new Play("Первый игрок", "Второй игрок");
        Person player_first = new Person();
        Person player_second = new Person();
        int count = 0;
        while (true) {
            count++;
            if (player_first.canWePlay("w")) {
                out.println("Ход первого игрока");
                if (count < 2 || !player_first.cancelMove("w")) {
                    player_first.personRun("w");
                }
            } else {
                out.println("Игра завершена!");
                break;
            }
            if (player_second.canWePlay("b")) {
                out.println("Ход второго игрока");
                if (count < 2 || !player_first.cancelMove("b")) {
                    player_second.personRun("b");
                }
            } else {
                out.println("Игра завершена!");
                break;
            }
        }
        play.finalBoard();
        max_points = play.getPoints(max_points);
    }

    // Метод обработки повторной игры.
    public static String playOneMoreTime() {
        out.println("Хотите сыграть заново? (y- да, n-нет)");
        Scanner input = new Scanner(in);
        String next_play;
        do {
            next_play = input.nextLine();
            if (next_play.equals("y")) {
                return "y";
            } else if (next_play.equals("n")) {
                return "n";
            } else {
                out.println("Не понял Вас, повторите ввод");
            }
        } while (true);
    }
}