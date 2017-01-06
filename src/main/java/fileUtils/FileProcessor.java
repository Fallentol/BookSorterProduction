package fileUtils;

import config.Configurator;
import essence.Book;

import java.io.*;

public class FileProcessor {

    private final static String filePath = "E:\\LIBRARY\\Техн литра\\"; // папка для тестов

    public static void main(String[] args) {
        //deleteOldFileIdentity();
        /*addLineIntoReservedFile("First String");
        addLineIntoReservedFile("Second String");
        addLineIntoReservedFile("Third String");*/
       /* Book book = new Book();
        book.id = 214926341;
        book.name = "Name";
        book.author = "Push";
        book.language = "ru";
        book.type = "book";
        book.format = "pdf";
        book.path = " E:/Tech/Name.pdf";
        book.description = "";
        book.year = 1956;
        book.size = 15;
        addLineIntoReservedFile(book);*/

        /*readBookFromFile();*/
        // поиск файла по названию
        /*for(String str: FileController.getFileBooksByName("Чугунное")) {
            System.out.println("Str="+str);
        }*/
    }


    public static void deleteOldFileIdentity() {
        String localPathFile = Configurator.filePath;
        //String localPathFile = filePath;
        File fileObject = new File(localPathFile);
        int counter = 1;
        for (String oldName : fileObject.list()) {
            String newName = oldIdentityDelete(oldName);
            if (!oldName.equals(newName)) {
                System.out.println("OLD=" + oldName);
                System.out.println("NEW=" + newName);
                File oldFile = new File(localPathFile + oldName);
                File newFile = new File(localPathFile + newName);
                if (oldFile.renameTo(newFile)) {
                    System.out.println("Удачно");
                } else {
                    System.out.println("Неудачно");
                }
                oldFile.delete();
                System.out.println("//////////// " + counter + " //////////////");
                counter++;
                oldFile = null;
                newFile = null;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("-------STOP-----------");
    }

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
            System.out.println(file.available());
            Book book1 = (Book) file.readObject();
            //Book book2 = (Book) file.readObject();
            System.out.println("I have read:");
            System.out.println("A Date object: " + book1);
            //System.out.println("A Date object: " + book2);
            System.out.println("Two Group of randoms");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String oldIdentityDelete(String oldName) {
        return oldName.replaceAll("^\\[(\\d+)?\\]", "");
    }

}
