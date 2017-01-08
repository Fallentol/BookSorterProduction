package fileUtils;

import config.Configurator;

import java.io.File;

public class DeleteOldFileIdentityThread extends Thread {
    public void run(){
        try {
            if (!isInterrupted()) {
                String localPathFile = Configurator.filePath;
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
                            //e.printStackTrace();
                            throw new InterruptedException();
                        }
                    }
                }
                System.out.println("-------STOP-----------");
            } else {
                throw new InterruptedException();
            }
        } catch (InterruptedException e) {
            System.out.println("THREAD IS ABORTED");
        }

    }

    private static String oldIdentityDelete(String oldName) {
        return oldName.replaceAll("^\\[(\\d+)?\\]", "");
    }
}
