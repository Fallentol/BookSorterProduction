package fileUtils;

import config.Configurator;
import essence.Book;

import java.awt.*;
import java.io.*;

public class FileProcessor {

    private final static String filePath = "E:\\LIBRARY\\Техн литра\\"; // папка для тестов


    public static void addLineIntoReservedFile(String line) {
        try {
            File file = new File(Configurator.reservedFilePath);
            if (!file.exists() || !file.isFile()) {
                file.createNewFile();
            }
            PrintWriter fileReserve = new PrintWriter(new FileOutputStream(Configurator.reservedFilePath, true));
            fileReserve.println(line);
            fileReserve.flush();
            fileReserve.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public static void addLineIntoReservedFile(Book book) {
        try {
            File file = new File(Configurator.reservedFilePath);
            if (!file.exists() || !file.isFile()) {
                file.createNewFile();
            }
            ObjectOutputStream fileReserve = new ObjectOutputStream(new FileOutputStream(Configurator.reservedFilePath, true));
            fileReserve.writeObject(book);
            fileReserve.flush();
            fileReserve.reset();
            fileReserve.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public static void readBookFromFile() {
        try {
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(Configurator.reservedFilePath));
            Book book1 = (Book) file.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String oldIdentityDelete(String oldName) {
        return oldName.replaceAll("^\\[(\\d+)?\\]", "");
    }

    public static boolean folderPathIsCorrectly() {
        if (Configurator.filePath == null || "".equals(Configurator.filePath)) {
            return false;
        }
        File file = new File(Configurator.filePath);
        if (!file.exists() || !file.isDirectory()) {
            return false;
        }
        return true;
    }

    public static void openFile(String filename) {
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(Configurator.filePath + filename);
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                System.out.println("ОШИБКА ОТКРЫТИЯ");
            }
        } else {
            System.out.println("НЕ ПОДДЕРЖИВАЕТСЯ");
        }
    }

}
