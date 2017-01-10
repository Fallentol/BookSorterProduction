package fileUtils;


import config.Configurator;
import essence.Book;

import java.io.File;
import java.util.ArrayList;

public class FileController {

    private static Thread delOldFileIdThread;
    private static Thread unzipThread;

    public static ArrayList<String> getFileBooksByName(String name) {
        ArrayList<String> result = new ArrayList<String>();
        File fileObject = new File(Configurator.filePath);
        String[] fileArray = fileObject.list();// list вытягивает список папок и файлов (null если это не директория, а файл)
        for (String currentFile : fileArray) {
            if (currentFile.contains(name)) {
                result.add(currentFile);
                if (result.size() > 20) break;
            }
        }
        return result;
    }

    public static void unzipAllFiles() {
        if (unzipThread == null) {
            unzipThread = new UnzipThread();
            unzipThread.start();
        }
    }

    public static void deleteOldFileIdentity() {
        if (FileProcessor.folderPathIsCorrectly() && delOldFileIdThread == null) {
            delOldFileIdThread = new DeleteOldFileIdentityThread();
            delOldFileIdThread.start();
            while (delOldFileIdThread != null && delOldFileIdThread.isAlive()) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            delOldFileIdThread = null;
        }
    }

    public static void abortDeletingOldFileIdentity() {
        if (delOldFileIdThread != null) delOldFileIdThread.interrupt();
        if (unzipThread != null) unzipThread.interrupt();
        unzipThread = delOldFileIdThread = null;
    }


    public static void openFile(String fileName) {
        FileProcessor.openFile(fileName);
    }

    public static void reserveBook(Book book) {
        FileProcessor.addLineIntoReservedFile(book.toString());
    }


}