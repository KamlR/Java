import java.io.File;
import java.nio.file.Files;

import java.util.Scanner;

import static java.lang.System.*;

/**
 * Данный класс является основным для запуска программы
 * Здесь создаются объекты классов, а также вызываются соответствующие методы для работы программы
 *
 * @author Mavletova Karina
 */
public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        out.println("Hello! This program looks for dependencies between your files");
        out.println("Enter the path to the root folder from which the program will start working: ");
        String root_folder;
        File file_root_folder;
        FileVariables variables = new FileVariables();
        FilesWorker files = new FilesWorker(variables);
        ForUserView user = new ForUserView(variables);
        do {
            root_folder = input.nextLine();
            file_root_folder = new File(root_folder);
            if (!file_root_folder.isDirectory()) {
                out.println("There are problems in  finding this directory, input one more time");
            } else {
                user.getAllFiles(file_root_folder);
                break;
            }
        } while (true);
        files.buildGraph(file_root_folder);
        if (files.checkLoops()) {
            out.println("Your files have a cyclical dependency, we can't continue the program");
            return;
        }
        files.sortListOfFiles();
        user.printSortFiles();
    }
}