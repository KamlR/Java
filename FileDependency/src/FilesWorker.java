import java.io.*;

/**
 * Данный класс обрабытывает данные для того, чтобы предоставить их пользователю
 * В этом классе никакие данные не читаются и никуда не выводятся, только их обработка
 */
public class FilesWorker {
    /** Объект класса, который хранит необходимые переменные для работы программы*/
    FileVariables variables;

    /** Отслеживает позицию, на которую встаёт файл в отсортированном списке */
    private int position;

    public FilesWorker(FileVariables variables) {
        this.variables = variables;
    }

    /**
     * Моя программа хранит данные о зависимостях файлов в графе
     * В данном методе граф заполняется на основе данных файлов
     * @param root - путь до корневой папки
     */
    public void buildGraph(File root) {
        variables.setRootFolder(root);
        variables.setGraph();
        variables.setWas();
        variables.setSortFiles();
        position = variables.getFiles().size() - 1;
        int count = 0;
        for (File file : variables.getFiles()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] require = line.split(" ");
                    if (require[0].equals("require")) {
                        openNewFile(require[1], count);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            count++;
        }
    }

    /**
     * Метод находит индекс файла, от которого зависит текущий из списка files
     * Заполяется зависимость, которая хранится в двумерном массиве graph
     * @param file_path путь к файлу, от которого зависит текущий
     * @param index_child номер файла в списке files для которого сейчас проверяется зависимость
     */
    private void openNewFile(String file_path, int index_child) {
        File current_file = new File(file_path);
        if (current_file.isDirectory()) {
            return;
        }
        int index_parent = 0;
        int count = 0;
        for (File file : variables.getFiles()) {
            if (file.getAbsolutePath().endsWith(file_path)) {
                index_parent = count;
                break;
            }
            count++;
        }
        variables.setGraph(index_parent, index_child, 1);
    }

    /**
     * Проверка на существование циклической зависимости
     * @return если цикл есть - true, если нет - false
     */
    public boolean checkLoops() {
        for (int i = 0; i < variables.getFiles().size(); i++) {
            for (int j = 0; j < variables.getFiles().size(); j++) {
                if (variables.getGraph(i, j) == 1 && variables.getGraph(j, i) == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Сортировка файлов, опираясь на их зависимость
     * Данные хранятся в массиве sortFiles
     * Здесь перебираются все файлы из списка files, для каждого вызывается метод-помощник
     */
    public void sortListOfFiles() {
        for (int i = 0; i < variables.getFiles().size(); i++) {
            if (variables.getWas(i) == 1) {
                continue;
            }
            helpWithSort(i);
        }
    }

    /**
     * Метод-помощник, кототрый создаёт рекурсию в поиске детей у файлов
     * @param index - индекс текущего файла, у которого идёт поиск зависящих от него файлов
     */
    private void helpWithSort(int index) {
        variables.setWas(index, 1);
        for (int i = 0; i < variables.getFiles().size(); i++) {
            if (variables.getGraph(index, i) == 1 && variables.getWas(i) != 1) {
                helpWithSort(i);
            }
        }
        variables.setSortFiles(position, variables.getFiles().get(index));
        position--;
    }
}
