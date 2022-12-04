import java.lang.reflect.Array;
import java.util.Arrays;

import static java.lang.System.out;

// Реализация сложной игры с компьютером.
public class ComputerHard extends Play {
    int[] indexes;

    // Запуск хода компьютера.
    // Выбор лучшего хода благодаря анализу ходов противника.
    public void computerRun() {
        boolean flag;
        double max = -100;
        int[] max_ij = new int[2];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                double sum = 0;
                flag = false;
                int[] indexes = new int[]{-1, -1, -1, -1, -1, -1, -1, -1};
                if (field[i][j].equals(" ") && j != 7 && field[i][j + 1].equals("w")) {
                    double result = goHorizontalRight(i, j);
                    sum += result;
                    if (result != 0) {
                        flag = true;
                        indexes[0] = end_i * 10 + end_j;
                    }
                } else if (field[i][j].equals(" ") && j != 0 && field[i][j - 1].equals("w")) {
                    double result = goHorizontalLeft(i, j);
                    sum += result;
                    if (result != 0) {
                        flag = true;
                        indexes[1] = end_i * 10 + end_j;
                    }
                }
                if (field[i][j].equals(" ") && i != 7 && field[i + 1][j].equals("w")) {
                    double result = goVerticalDown(i, j);
                    sum += result;
                    if (result != 0) {
                        flag = true;
                        indexes[2] = end_i * 10 + end_j;
                    }

                } else if (field[i][j].equals(" ") && i != 0 && field[i - 1][j].equals("w")) {
                    double result = goVerticalUp(i, j);
                    sum += result;
                    if (result != 0) {
                        flag = true;
                        indexes[3] = end_i * 10 + end_j;
                    }
                }
                if (field[i][j].equals(" ") && i != 7 && j != 7 && field[i + 1][j + 1].equals("w")) {
                    double result = goRightDiagonalDown(i, j);
                    sum += result;
                    if (result != 0) {
                        flag = true;
                        indexes[4] = end_i * 10 + end_j;
                    }

                } else if (field[i][j].equals(" ") && i != 0 && j != 0 && field[i - 1][j - 1].equals("w")) {
                    double result = goRightDiagonalUp(i, j);
                    sum += result;
                    if (result != 0) {
                        flag = true;
                        indexes[5] = end_i * 10 + end_j;
                    }
                }
                if (field[i][j].equals(" ") && i != 7 && j != 0 && field[i + 1][j - 1].equals("w")) {
                    double result = goLeftDiagonalDown(i, j);
                    sum += result;
                    if (result != 0) {
                        flag = true;
                        indexes[6] = end_i * 10 + end_j;
                    }
                } else if (field[i][j].equals(" ") && i != 0 && j != 7 && field[i - 1][j + 1].equals("w")) {
                    double result = goLeftDiagonalUp(i, j);
                    sum += result;
                    if (result != 0) {
                        flag = true;
                        indexes[7] = end_i * 10 + end_j;
                    }
                }
                if (flag) {
                    sum += getSS(i, j);
                }
                if (sum > 0) {
                    String[][] field_temp = copyOf(field);
                    changeColor(i, j, indexes, "b");
                    double counter = findMaxSum();
                    if (sum - counter > max) {
                        max = sum - counter;
                        this.indexes = indexes;
                        max_ij[0] = i;
                        max_ij[1] = j;
                    }
                    field = copyOf(field_temp);
                }
            }
        }
        changeColor(max_ij[0], max_ij[1], indexes, "b");
        field[max_ij[0]][max_ij[1]] = "b";
        out.println("Компьютер поставил фишку на ячейку: " + max_ij[0] + " " + max_ij[1]);
    }

}
