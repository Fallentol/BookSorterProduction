package fileUtils;

import config.Configurator;

import java.io.File;

public class FileProcessor {

    private final static String filePath = "E:\\LIBRARY\\Техн литра\\"; // папка для тестов

    public static void main(String[] args) {
        deleteOldFileIdentity();


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

    private static String oldIdentityDelete(String oldName) {
        return oldName.replaceAll("^\\[(\\d+)?\\]", "");
    }

}
