import java.io.File;
import java.util.ArrayList;


/**
 * Данный класс предназначен для хранения переменных,их получения и изменения другими классами
 */
public class FileVariables {
    /**
     * Путь к корневой папке
     */
    private File rootFolder;

    /**
     * Список всех файлов в корневой папке и ниже
     */
    private ArrayList<File> files = new ArrayList<>();
    /**
     * Массив отсортированных по зависимостям файлов
     */
    private File[] sortFiles;

    /**
     * Массив, который помогает в сортиовке файлов (указывает,какой файл уже был обработан)
     */
    private int[] was;

    /**
     * Двумерный массив, который хранит зависимость между файлами в виде матрицы смежности
     */
    private int[][] graph;

    /**
     * @return возвращает путь к корневой папке, которую ввёл пользователь
     */
    public File getRootFolder() {
        return rootFolder;
    }

    /**
     * Получение данных о корневой папке
     *
     * @param rootFolder путь к корневой папке, которую ввёл пользователь
     */
    public void setRootFolder(File rootFolder) {
        this.rootFolder = rootFolder;
    }

    /**
     * Возвращает список файлов корневой папки и ниже
     *
     * @return список файлов
     */
    public ArrayList<File> getFiles() {
        return files;
    }

    /**
     * Добавление нового файла в список
     *
     * @param file
     */
    public void addToFiles(File file) {
        files.add(file);
    }

    /**
     * Получение отсортированного массива
     *
     * @return отсортированный массив
     */
    public File[] getSortFiles() {
        return sortFiles;
    }

    /**
     * Инициализация массива
     */
    public void setSortFiles() {
        this.sortFiles = new File[files.size()];
    }

    /**
     * Получение элемента массива по индексу
     *
     * @param i индекс
     * @return элемент массива
     */
    public File getSortFiles(int i) {
        return sortFiles[i];
    }

    /**
     * Заполнение ячейки массива
     *
     * @param i     индекс
     * @param value значение
     */
    public void setSortFiles(int i, File value) {
        sortFiles[i] = value;
    }

    /**
     * Получение элемента массива по индексу
     *
     * @param i индекс
     * @return элемент массива
     */
    public int getWas(int i) {
        return was[i];
    }

    /**
     * Инициализация массива
     */
    public void setWas() {
        this.was = new int[files.size()];
    }

    /**
     * Заполнение ячейки массива
     *
     * @param i     индекс
     * @param value значение
     */
    public void setWas(int i, int value) {
        was[i] = value;
    }

    /**
     * Получение элемента массива по индексам
     *
     * @param i индекс 1
     * @param j индекс 2
     * @return элемент двумерного массива
     */
    public int getGraph(int i, int j) {
        return graph[i][j];
    }

    /**
     * Инициализация массива
     */
    public void setGraph() {
        this.graph = new int[files.size()][files.size()];
    }


    /**
     * Зполнение ячейки массива
     *
     * @param i     индекс 1
     * @param j     индекс 2
     * @param value - значение
     */
    public void setGraph(int i, int j, int value) {
        graph[i][j] = value;
    }
}
