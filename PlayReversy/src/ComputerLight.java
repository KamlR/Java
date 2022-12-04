import static java.lang.System.*;

// Класс реализует лёгкий режим игры с компьютером.
public class ComputerLight extends Play {
    int[] indexes;

    // Основной метод, который запускает ход для компьютера.
    // Здесь перебираются ячейки поля и решается, на какую ячейку можно сделать ход.
    // Соответственно вызываются вспомогательные методы.
    public void computerRun() {
        double max = 0;
        boolean flag;
        int[] max_ij = new int[2];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                flag = false;
                double sum = 0;
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
                if (sum > max) {
                    max = sum;
                    max_ij[0] = i;
                    max_ij[1] = j;
                    this.indexes = indexes;
                }
            }
        }
        changeColor(max_ij[0], max_ij[1], indexes, "b");
        field[max_ij[0]][max_ij[1]] = "b";
        max_ij[0] += 1;
        max_ij[1] += 1;
        out.println("Компьютер поставил фишку на ячейку: " + max_ij[0] + " " + max_ij[1]);
    }
}