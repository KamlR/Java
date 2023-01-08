import java.io.*;

import static java.lang.System.out;

/**
 * Данный класс извлекает данные для обработки из файлов и визуализирует их
 */
public class ForUserView {
    /**
     * Объект класса, который хранит необходимые переменные для работы программы
     */
    FileVariables variables;

    public ForUserView(FileVariables variables) {
        this.variables = variables;
    }


    /**
     * Заполнение списка files всеми файлами, которые хранятся в указанной директории
     *
     * @param directory - корневая папка
     */
    public void getAllFiles(File directory) {
        File[] directoryFiles = directory.listFiles();
        if (directoryFiles != null) {
            for (File file_or_dir : directoryFiles) {
                if (file_or_dir.isDirectory()) {
                    getAllFiles(file_or_dir);
                } else {
                    variables.addToFiles(file_or_dir);
                }
            }
        }
    }

    /**
     * Печать списка с отсортированными файлами в консоль
     */
    public void printSortFiles() {
        out.println("File dependency:");
        for (int i = 0; i < variables.getSortFiles().length; i++) {
            out.println(variables.getSortFiles(i).toString());
        }
        createFileForAll();
    }


    /**
     * Вывод содержимого всех файлов в один после сортровки файлов
     */
    private void createFileForAll() {
        File all_files = new File(variables.getRootFolder().toString() + "\\all_files.txt");
        try (FileWriter writer = new FileWriter(all_files, true)) {
            for (File file : variables.getSortFiles()) {
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        writer.write(line + '\n');
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
