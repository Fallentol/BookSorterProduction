package fileUtils;

import config.Configurator;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipUtil implements UnArchive {

    public String unpack(String fileName, String targetDirectory) {
        if (fileName == null || "".equals(fileName)) return "FileName is nor correct";
        if (targetDirectory == null) {
            targetDirectory = Configurator.WORK_DIRECTORY;
        }
        File fileObject = new File(Configurator.WORK_DIRECTORY, fileName);
        if (!isNormalFile(fileObject)) return "File " + fileName + " with error";
        try {
            ZipFile zip = new ZipFile(Configurator.WORK_DIRECTORY + fileName);
            Enumeration entries = zip.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                System.out.println(entry.getName());

                if (entry.isDirectory()) {
                    new File(fileObject.getParent(), entry.getName()).mkdirs();
                } else {
                    write(zip.getInputStream(entry),
                            new BufferedOutputStream(new FileOutputStream(
                                    new File(fileObject.getParent(), entry.getName()))));
                }
            }
            zip.close();
            fileObject.delete();
        } catch (IOException e) {
            e.printStackTrace();
            return "!! Something wrong in file " + fileName;
        }
        return "Completed";
    }

    private static boolean isNormalFile(File f) {
        if (f.exists() && f.canRead()) return true;
        return false;
    }


    private static void write(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) >= 0)
            out.write(buffer, 0, len);
        out.close();
        in.close();
    }

}

