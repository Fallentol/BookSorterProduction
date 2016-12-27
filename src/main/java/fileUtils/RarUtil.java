package fileUtils;
import config.Configurator;

import java.io.File;
import java.io.IOException;

/**
 * Created by New on 12/10/2016.
 */
public class RarUtil implements UnArchive {

    public String unpack(String fileName, String targetDirectory) {
        if (fileName == null || "".equals(fileName)) return "FileName is nor correct";
        if (targetDirectory == null) {
            targetDirectory = Configurator.WORK_DIRECTORY;
        }
        File fileObject = new File(Configurator.WORK_DIRECTORY, fileName);
        if (!isNormalFile(fileObject)) return "File " + fileName + " with error";

        try {
            String exect = Configurator.UNRAR_PATH + " X \"" + Configurator.WORK_DIRECTORY+fileName + "\" " + targetDirectory;
            Runtime.getRuntime().exec(exect);
            Thread.sleep(4000);
            fileObject.delete();
        } catch (IOException e) {
            return "!! Runtime ERROR";
        } catch (InterruptedException e) {
            return "Sleep interupt";
        }
        return "Completed";
    }

    public static boolean isNormalFile(File f) {
        if (f.exists() && f.canRead()) return true;
        return false;
    }
}

