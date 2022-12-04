import static java.lang.System.*;

// Основной класс для всех типов игр.
public class Play {
    protected static String[][] field = new String[8][8];
    protected int end_i;
    protected int end_j;
    private String first_player = "";
    private String second_player = "";

    // Конструктор заполняет поле для игры.
    public Play(String first_player, String second_player) {
        this.first_player = first_player;
        this.second_player = second_player;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                field[i][j] = " ";
            }
        }
        field[3][3] = "w";
        field[3][4] = "b";
        field[4][3] = "b";
        field[4][4] = "w";
    }

    public Play() {
    }

    // Вычисление ss.
    protected double getSS(int i, int j) {
        double ss;
        if (i == 0 && j == 0 || i == 0 && j == 7 || i == 7 && j == 0 || i == 7 && j == 7) {
            ss = 0.8;
        } else if (i == 0 || j == 0 || i == 7 || j == 7) {
            ss = 0.4;
        } else {
            ss = 0;
        }
        return ss;
    }

    // Вычисление si.
    protected int getSI(int i, int j) {
        int s_i;
        if (i == 0 || j == 0 || i == 7 || j == 7) {
            s_i = 2;
        } else {
            s_i = 1;
        }
        return s_i;
    }

    // Изменение цвета фишек после хода игрока.
    protected void changeColor(int i, int j, int[] indexes, String to) {
        for (int iter = 0; iter < indexes.length; iter++) {
            int from_i = i;
            int from_j = j;
            if (indexes[iter] != -1) {
                int to_i = indexes[iter] / 10;
                int to_j = indexes[iter] % 10;
                while (from_i != to_i || from_j != to_j) {
                    if (iter == 0) {
                        from_j++;
                    } else if (iter == 1) {
                        from_j--;
                    } else if (iter == 2) {
                        from_i++;
                    } else if (iter == 3) {
                        from_i--;
                    } else if (iter == 4) {
                        from_i++;
                        from_j++;
                    } else if (iter == 5) {
                        from_i--;
                        from_j--;
                    } else if (iter == 6) {
                        from_i++;
                        from_j--;
                    } else {
                        from_i--;
                        from_j++;
                    }
                    field[from_i][from_j] = to;
                }
            }
        }
    }

    // Проверка на возможность совершения хода.
    // Если ход совершить больше нельзя, то игра должна закончиться.
    public boolean canWePlay(String color) {
        if (!helpForCanWePlay(color)) {
            return false;
        }
        int count_all = 0;
        int count_w = 0;
        int count_b = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (field[i][j].equals("w") || field[i][j].equals("b")) {
                    count_all++;
                }
                if (field[i][j].equals("w")) {
                    count_w++;
                }
                if (field[i][j].equals("b")) {
                    count_b++;
                }
            }
        }
        if (count_all == 64 || count_w == 64 || count_b == 64) {
            return false;
        }
        return true;
    }

    // Помощник в анализе возможности хода.
    // В прошлом методе проверяется заполненность чёрыными и белыми фишками.
    // В данном методе возможность поставить куда-то фишки.
    private boolean helpForCanWePlay(String color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (field[i][j].equals(" ") && j != 7 && field[i][j + 1].equals(color)) {
                    if (checkHorizontalRight(i, j, color)) {
                        return true;
                    }
                } else if (field[i][j].equals(" ") && j != 0 && field[i][j - 1].equals(color)) {
                    if (checkHorizontalLeft(i, j, color)) {
                        return true;
                    }
                }
                if (field[i][j].equals(" ") && i != 7 && field[i + 1][j].equals(color)) {
                    if (checkVerticalDown(i, j, color)) {
                        return true;
                    }

                } else if (field[i][j].equals(" ") && i != 0 && field[i - 1][j].equals(color)) {
                    if (checkVerticalUp(i, j, color)) {
                        return true;
                    }
                }
                if (field[i][j].equals(" ") && i != 7 && j != 7 && field[i + 1][j + 1].equals(color)) {
                    if (checkRightDiagonalDown(i, j, color)) {
                        return true;
                    }

                } else if (field[i][j].equals(" ") && i != 0 && j != 0 && field[i - 1][j - 1].equals(color)) {
                    if (checkRightDiagonalUp(i, j, color)) {
                        return true;
                    }
                }
                if (field[i][j].equals(" ") && i != 7 && j != 0 && field[i + 1][j - 1].equals(color)) {
                    if (checkLeftDiagonalDown(i, j, color)) {
                        return true;
                    }
                } else if (field[i][j].equals(" ") && i != 0 && j != 7 && field[i - 1][j + 1].equals(color)) {
                    if (checkLeftDiagonalUp(i, j, color)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Проверка возможности заменить фишки противника по горизонатали вправо.
    protected boolean checkHorizontalRight(int i, int j, String color) {
        if (color.equals("w")) {
            color = "b";
        } else {
            color = "w";
        }
        j++;
        while (j != 8 && !field[i][j].equals(color) && !field[i][j].equals(" ")) {
            j++;
        }
        if (j != 8 && field[i][j].equals(color)) {
            end_i = i;
            end_j = j;
            return true;
        }
        return false;
    }

    // Проверка возможности заменить фишки противника по горизонатали влево.
    protected boolean checkHorizontalLeft(int i, int j, String color) {
        if (color.equals("w")) {
            color = "b";
        } else {
            color = "w";
        }
        j--;
        while (j != -1 && !field[i][j].equals(color) && !field[i][j].equals(" ")) {
            j--;
        }
        if (j != -1 && field[i][j].equals(color)) {
            end_i = i;
            end_j = j;
            return true;
        }
        return false;
    }

    // Проверка возможности заменить фишки противника по вертикали вниз.
    protected boolean checkVerticalDown(int i, int j, String color) {
        if (color.equals("w")) {
            color = "b";
        } else {
            color = "w";
        }
        i++;
        while (i != 8 && !field[i][j].equals(color) && !field[i][j].equals(" ")) {
            i++;
        }
        if (i != 8 && field[i][j].equals(color)) {
            end_i = i;
            end_j = j;
            return true;
        }
        return false;
    }

    // Проверка возможности заменить фишки противника по вертикали вверх.
    protected boolean checkVerticalUp(int i, int j, String color) {
        if (color.equals("w")) {
            color = "b";
        } else {
            color = "w";
        }
        i--;
        while (i != -1 && !field[i][j].equals(color) && !field[i][j].equals(" ")) {
            i--;
        }
        if (i != -1 && field[i][j].equals(color)) {
            end_i = i;
            end_j = j;
            return true;
        }
        return false;
    }


    // Проверка возможности заменить фишки противника по диагонали (из левого верхнего - в правый нижний).
    protected boolean checkRightDiagonalDown(int i, int j, String color) {
        if (color.equals("w")) {
            color = "b";
        } else {
            color = "w";
        }
        i++;
        j++;
        while (i != 8 && j != 8 && !field[i][j].equals(color) && !field[i][j].equals(" ")) {
            i++;
            j++;
        }
        if (i != 8 && j != 8 && field[i][j].equals(color)) {
            end_i = i;
            end_j = j;
            return true;
        }
        return false;
    }

    // Проверка возможности заменить фишки противника по диагонали (из правого нижнего - в левый верхний).
    protected boolean checkRightDiagonalUp(int i, int j, String color) {
        if (color.equals("w")) {
            color = "b";
        } else {
            color = "w";
        }
        i--;
        j--;
        while (i != -1 && j != -1 && !field[i][j].equals(color) && !field[i][j].equals(" ")) {
            i--;
            j--;
        }
        if (i != -1 && j != -1 && field[i][j].equals(color)) {
            end_i = i;
            end_j = j;
            return true;
        }
        return false;
    }

    // Проверка возможности заменить фишки противника по диагонали (из правого верхнего - в левый нижний).
    protected boolean checkLeftDiagonalDown(int i, int j, String color) {
        if (color.equals("w")) {
            color = "b";
        } else {
            color = "w";
        }
        i++;
        j--;
        while (i != 8 && j != -1 && !field[i][j].equals(color) && !field[i][j].equals(" ")) {
            i++;
            j--;
        }
        if (i != 8 && j != -1 && field[i][j].equals(color)) {
            end_i = i;
            end_j = j;
            return true;
        }
        return false;
    }

    // Проверка возможности заменить фишки противника по диагонали (из правого нижнего - в левый верхний).
    protected boolean checkLeftDiagonalUp(int i, int j, String color) {
        if (color.equals("w")) {
            color = "b";
        } else {
            color = "w";
        }
        i--;
        j++;
        while (i != -1 && j != 8 && !field[i][j].equals(color) && !field[i][j].equals(" ")) {
            i--;
            j++;
        }
        if (i != -1 && j != 8 && field[i][j].equals(color)) {
            end_i = i;
            end_j = j;
            return true;
        }
        return false;
    }

    // Подсчёт кол-ва очков для каждого игрока.
    public int[] getPoints(int[] max_points) {
        int count_w = 0;
        int count_b = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (field[i][j].equals("w")) {
                    count_w++;
                }
                if (field[i][j].equals("b")) {
                    count_b++;
                }
            }
        }
        if (first_player.equals("computer") && count_b > max_points[0]) {
            max_points[0] = count_b;
        } else if (first_player.equals("first_player") && count_b > max_points[2]) {
            max_points[2] = count_b;
        }
        if (second_player.equals("you") && count_w > max_points[1]) {
            max_points[1] = count_w;
        } else if (second_player.equals("second_player") && count_w > max_points[3]) {
            max_points[3] = count_w;
        }
        out.println(first_player + " заработал: " + count_b);
        out.println(second_player + " заработал: " + count_w);
        return max_points;
    }

    /*
    Далее с началом go идут аналоги методам с началом check, но для режима игры с компьютером.
    Немного другая логика, так как нет зависимости от цвета, компьютер всегда играет чёрными.
     */
    protected double goHorizontalRight(int i, int j) {
        double sum = 0;
        j++;
        while (j != 8 && !field[i][j].equals("b") && !field[i][j].equals(" ")) {
            sum += getSI(i, j);
            j++;
        }
        if (j != 8 && field[i][j].equals("b")) {
            end_i = i;
            end_j = j;
            return sum;
        }
        return 0;
    }

    protected double goHorizontalLeft(int i, int j) {
        double sum = 0;
        j--;
        while (j != -1 && !field[i][j].equals("b") && !field[i][j].equals(" ")) {
            sum += getSI(i, j);
            j--;
        }
        if (j != -1 && field[i][j].equals("b")) {
            end_i = i;
            end_j = j;
            return sum;
        }
        return 0;
    }

    protected double goVerticalDown(int i, int j) {
        double sum = 0;
        i++;
        while (i != 8 && !field[i][j].equals("b") && !field[i][j].equals(" ")) {
            sum += getSI(i, j);
            i++;
        }
        if (i != 8 && field[i][j].equals("b")) {
            end_i = i;
            end_j = j;
            return sum;
        }
        return 0;
    }

    protected double goVerticalUp(int i, int j) {
        double sum = 0;
        i--;
        while (i != -1 && !field[i][j].equals("b") && !field[i][j].equals(" ")) {
            sum += getSI(i, j);
            i--;
        }
        if (i != -1 && field[i][j].equals("b")) {
            end_i = i;
            end_j = j;
            return sum;
        }
        return 0;
    }

    protected double goRightDiagonalDown(int i, int j) {
        double sum = 0;
        i++;
        j++;
        while (i != 8 && j != 8 && !field[i][j].equals("b") && !field[i][j].equals(" ")) {
            sum += getSI(i, j);
            i++;
            j++;
        }
        if (i != 8 && j != 8 && field[i][j].equals("b")) {
            end_i = i;
            end_j = j;
            return sum;
        }
        return 0;
    }

    protected double goRightDiagonalUp(int i, int j) {
        double sum = 0;
        i--;
        j--;
        while (i != -1 && j != -1 && !field[i][j].equals("b") && !field[i][j].equals(" ")) {
            sum += getSI(i, j);
            i--;
            j--;
        }
        if (i != -1 && j != -1 && field[i][j].equals("b")) {
            end_i = i;
            end_j = j;
            return sum;
        }
        return 0;
    }

    protected double goLeftDiagonalDown(int i, int j) {
        double sum = 0;
        i++;
        j--;
        while (i != 8 && j != -1 && !field[i][j].equals("b") && !field[i][j].equals(" ")) {
            sum += getSI(i, j);
            i++;
            j--;
        }
        if (i != 8 && j != -1 && field[i][j].equals("b")) {
            end_i = i;
            end_j = j;
            return sum;
        }
        return 0;
    }

    protected double goLeftDiagonalUp(int i, int j) {
        double sum = 0;
        i--;
        j++;
        while (i != -1 && j != 8 && !field[i][j].equals("b") && !field[i][j].equals(" ")) {
            sum += getSI(i, j);
            i--;
            j++;
        }
        if (i != -1 && j != 8 && field[i][j].equals("b")) {
            end_i = i;
            end_j = j;
            return sum;
        }
        return 0;
    }

    // Поиск максимальной суммы для сложной игры с компьютером.
    // Помогает принтяь решение относительно следующего хода.
    protected double findMaxSum() {
        double max = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boolean flag = false;
                double sum = 0;
                if (field[i][j].equals(" ") && j != 7 && field[i][j + 1].equals("w")) {
                    double result = goHorizontalRight(i, j);
                    sum += result;
                    if (result != 0) {
                        flag = true;
                    }
                } else if (field[i][j].equals(" ") && j != 0 && field[i][j - 1].equals("w")) {
                    double result = goHorizontalLeft(i, j);
                    sum += result;
                    if (result != 0) {
                        flag = true;
                    }
                }
                if (field[i][j].equals(" ") && i != 7 && field[i + 1][j].equals("w")) {
                    double result = goVerticalDown(i, j);
                    sum += result;
                    if (result != 0) {
                        flag = true;
                    }

                } else if (field[i][j].equals(" ") && i != 0 && field[i - 1][j].equals("w")) {
                    double result = goVerticalUp(i, j);
                    sum += result;
                    if (result != 0) {
                        flag = true;
                    }
                }
                if (field[i][j].equals(" ") && i != 7 && j != 7 && field[i + 1][j + 1].equals("w")) {
                    double result = goRightDiagonalDown(i, j);
                    sum += result;
                    if (result != 0) {
                        flag = true;
                    }

                } else if (field[i][j].equals(" ") && i != 0 && j != 0 && field[i - 1][j - 1].equals("w")) {
                    double result = goRightDiagonalUp(i, j);
                    sum += result;
                    if (result != 0) {
                        flag = true;
                    }
                }
                if (field[i][j].equals(" ") && i != 7 && j != 0 && field[i + 1][j - 1].equals("w")) {
                    double result = goLeftDiagonalDown(i, j);
                    sum += result;
                    if (result != 0) {
                        flag = true;
                    }
                } else if (field[i][j].equals(" ") && i != 0 && j != 7 && field[i - 1][j + 1].equals("w")) {
                    double result = goLeftDiagonalUp(i, j);
                    sum += result;
                    if (result != 0) {
                        flag = true;
                    }
                }
                if (flag) {
                    sum += getSS(i, j);
                }
                if (sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    }

    // Совершаем копирование массива
    protected String[][] copyOf(String[][] array) {
        String[][] new_array = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                new_array[i][j] = array[i][j];
            }
        }
        return new_array;
    }

    // Вывод поля после завершения игры.
    public void finalBoard() {
        out.println("В итоге получилась такая доска:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j == 7) {
                    out.println("|" + field[i][j] + "|");
                } else {
                    out.print("|" + field[i][j]);
                }
            }
        }
    }
}