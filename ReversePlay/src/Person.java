import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.System.*;

// Класс реализует игру человека
public class Person extends Play {
    String[][] field_plus;
    String[][] field_previous;
    ArrayList<Integer> pluses_i = new ArrayList<>();
    ArrayList<Integer> pluses_j = new ArrayList<>();

    // Запуск хода человека.
    // Вывод подсказок для пользоветеля.
    // Также вызов методов, которые отвечают за ход человека.
    public void personRun(String color) {
        options(color);
        visualization();
        Scanner input = new Scanner(System.in);
        String[] indexes;
        int i, j;
        do {
            out.print("Введите индексы ячейки, в которую хотите поставить фишку: ");
            indexes = input.nextLine().split(" ");
            if (indexes.length != 2) {
                out.println("Получены некорректные данные, повторите ввод");
                continue;
            }
            try {
                i = Integer.parseInt(indexes[0]);
                j = Integer.parseInt(indexes[1]);
                if (i < Collections.min(pluses_i) || i > Collections.max(pluses_i) ||
                        j < Collections.min(pluses_j) || j > Collections.max(pluses_j)) {
                    out.println("Получены некорректные данные, повторите ввод");
                } else {
                    i--;
                    j--;
                    break;
                }
            } catch (NumberFormatException e) {
                out.println("Получены некорректные данные, повторите ввод");
            }
        } while (true);
        String color_to;
        if (color.equals("w")) {
            color_to = "b";
        } else {
            color_to = "w";
        }
        field_previous = copyOf(field);
        changeColor(i, j, prepareForChanging(i, j, color), color_to);
        field[i][j] = color_to;
        i++;
        j++;
        out.println("Ход был совершён на ячейку " + i + " " + j);
    }

    // Создание поля, которое заполянется плюсиками,они отображают возможные ходы для человека.
    private void options(String color) {
        out.println("Вы можете сходить следующим образом: ");
        pluses_i.clear();
        pluses_j.clear();
        field_plus = copyOf(field);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (field_plus[i][j].equals(" ") && j != 7 && field_plus[i][j + 1].equals(color)) {
                    if (checkHorizontalRight(i, j, color)) {
                        out.println((i + 1) + " " + (j + 1));
                        field_plus[i][j] = "+";
                        pluses_i.add(i + 1);
                        pluses_j.add(j + 1);
                    }
                } else if (field_plus[i][j].equals(" ") && j != 0 && field_plus[i][j - 1].equals(color)) {
                    if (checkHorizontalLeft(i, j, color)) {
                        out.println((i + 1) + " " + (j + 1));
                        field_plus[i][j] = "+";
                        pluses_i.add(i + 1);
                        pluses_j.add(j + 1);
                    }
                }
                if (field_plus[i][j].equals(" ") && i != 7 && field_plus[i + 1][j].equals(color)) {
                    if (checkVerticalDown(i, j, color)) {
                        out.println((i + 1) + " " + (j + 1));
                        field_plus[i][j] = "+";
                        pluses_i.add(i + 1);
                        pluses_j.add(j + 1);
                    }
                } else if (field_plus[i][j].equals(" ") && i != 0 && field_plus[i - 1][j].equals(color)) {
                    if (checkVerticalUp(i, j, color)) {
                        out.println((i + 1) + " " + (j + 1));
                        field_plus[i][j] = "+";
                        pluses_i.add(i + 1);
                        pluses_j.add(j + 1);
                    }
                }
                if (field_plus[i][j].equals(" ") && i != 7 && j != 7 && field_plus[i + 1][j + 1].equals(color)) {
                    if (checkRightDiagonalDown(i, j, color)) {
                        out.println((i + 1) + " " + (j + 1));
                        field_plus[i][j] = "+";
                        pluses_i.add(i + 1);
                        pluses_j.add(j + 1);
                    }
                } else if (field_plus[i][j].equals(" ") && i != 0 && j != 0 && field_plus[i - 1][j - 1].equals(color)) {
                    if (checkRightDiagonalUp(i, j, color)) {
                        out.println((i + 1) + " " + (j + 1));
                        field_plus[i][j] = "+";
                        pluses_i.add(i + 1);
                        pluses_j.add(j + 1);
                    }
                }
                if (field_plus[i][j].equals(" ") && i != 7 && j != 0 && field_plus[i + 1][j - 1].equals(color)) {
                    if (checkLeftDiagonalDown(i, j, color)) {
                        out.println((i + 1) + " " + (j + 1));
                        field_plus[i][j] = "+";
                        pluses_i.add(i + 1);
                        pluses_j.add(j + 1);
                    }
                } else if (field_plus[i][j].equals(" ") && i != 0 && j != 7 && field_plus[i - 1][j + 1].equals(color)) {
                    if (checkLeftDiagonalUp(i, j, color)) {
                        out.println((i + 1) + " " + (j + 1));
                        field_plus[i][j] = "+";
                        pluses_i.add(i + 1);
                        pluses_j.add(j + 1);
                    }
                }

            }
        }
    }

    // Вывод поля, где плюсиками отображены возможные ходы, которые может совершить человек.
    private void visualization() {
        out.println("Возможные ходы помечены +");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j == 7) {
                    out.println("|" + field_plus[i][j] + "|");
                    if (field_plus[i][j].equals("+")) {
                        field_plus[i][j] = " ";
                    }
                } else {
                    out.print("|" + field_plus[i][j]);
                    if (field_plus[i][j].equals("+")) {
                        field_plus[i][j] = " ";
                    }
                }
            }
        }
    }


    // Подготовка к изменению цвета фишек после хода челоека
    // Заполянется массив индексов, до которых будут совершены изменения.
    private int[] prepareForChanging(int i, int j, String color_prepare) {
        int indexes[] = new int[]{-1, -1, -1, -1, -1, -1, -1, -1};
        if (field[i][j].equals(" ") && j != 7 && field[i][j + 1].equals(color_prepare)) {
            if (checkHorizontalRight(i, j, color_prepare)) {
                indexes[0] = end_i * 10 + end_j;
            }
        } else if (field[i][j].equals(" ") && j != 0 && field[i][j - 1].equals(color_prepare)) {
            if (checkHorizontalLeft(i, j, color_prepare)) {
                indexes[1] = end_i * 10 + end_j;
            }
        }
        if (field[i][j].equals(" ") && i != 7 && field[i + 1][j].equals(color_prepare)) {
            if (checkVerticalDown(i, j, color_prepare)) {
                indexes[2] = end_i * 10 + end_j;
            }
        } else if (field[i][j].equals(" ") && i != 0 && field[i - 1][j].equals(color_prepare)) {
            if (checkVerticalUp(i, j, color_prepare)) {
                indexes[3] = end_i * 10 + end_j;
            }
        }
        if (field[i][j].equals(" ") && i != 7 && j != 7 && field[i + 1][j + 1].equals(color_prepare)) {
            if (checkRightDiagonalDown(i, j, color_prepare)) {
                indexes[4] = end_i * 10 + end_j;
            }
        } else if (field[i][j].equals(" ") && i != 0 && j != 0 && field[i - 1][j - 1].equals(color_prepare)) {
            if (checkRightDiagonalUp(i, j, color_prepare)) {
                indexes[5] = end_i * 10 + end_j;
            }
        }
        if (field[i][j].equals(" ") && i != 7 && j != 0 && field[i + 1][j - 1].equals(color_prepare)) {
            if (checkLeftDiagonalDown(i, j, color_prepare)) {
                indexes[6] = end_i * 10 + end_j;
            }
        } else if (field[i][j].equals(" ") && i != 0 && j != 7 && field[i - 1][j + 1].equals(color_prepare)) {
            if (checkLeftDiagonalUp(i, j, color_prepare)) {
                indexes[7] = end_i * 10 + end_j;
            }
        }
        return indexes;
    }

    // Реализация возможности отмены хода.
    public boolean cancelMove(String color) {
        boolean result = false;
        out.println("Результат хода противника: ");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j == 7) {
                    out.println("|" + field[i][j] + "|");
                } else {
                    out.print("|" + field[i][j]);
                }
            }
        }
        out.println("Хотите ли вы отменить свой прошлый ход и сделать его заново (y- да, n-нет?)");
        do {
            Scanner input = new Scanner(in);
            String yes_no = input.nextLine();
            if (yes_no.equals("y")) {
                out.println("Ок, возвращаем Вас к прошлому ходу");
                field = copyOf(field_previous);
                personRun(color);
                result = true;
                break;
            } else if (yes_no.equals("n")) {
                break;
            } else {
                out.println("Не понял Вас, повторите ввод: ");
            }

        } while (true);
        return result;
    }

}
