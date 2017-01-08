package fileUtils;


import config.Configurator;
import essence.Book;

import java.io.File;
import java.util.ArrayList;

public class FileController {

    public static ArrayList<String> getFileBooksByName(String name) {
        ArrayList<String> result = new ArrayList<String>();
        File fileObject = new File(Configurator.filePath);
        String[] fileArray = fileObject.list();// list вытягивает список папок и файлов (null если это не директория, а файл)
        for (String currentFile : fileArray) {
            if (currentFile.contains(name)) {
                result.add(currentFile);
                if(result.size()>20) break;
            }
        }
        return result;
    }

    public static void saveFileWithIdenty (Book book) {
        // метод открывает файл по его пути, инсертит файл в формате [00021]filename.pdf
    }

    public static void reserveBook (Book book) {
        FileProcessor.addLineIntoReservedFile(book.toString());
    }




}