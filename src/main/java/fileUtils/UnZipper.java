package fileUtils;

import java.io.File;

public class UnZipper {


    public String unRARFile(final String fileName, String targetDirectory) {
        return null;
    }


    private boolean isNormalFile(File f) {
        if (f.exists() && f.canRead()) return true;
        return false;
    }



}
