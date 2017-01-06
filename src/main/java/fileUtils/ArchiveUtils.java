package fileUtils;

import config.Configurator;

import java.io.File;
import java.util.*;

/**
 * Created by New on 12/27/2016.
 */
public class ArchiveUtils {

    public static void main(String[] args) {

        ZipUtil zu = new ZipUtil();
        RarUtil ru = new RarUtil();
        Map<String, UnArchive> unArchivesMap = new HashMap<String, UnArchive>();
        unArchivesMap.put("zip", zu);
        unArchivesMap.put("rar", ru);

        File fileObject = new File(Configurator.filePath);
        String[] fileArray = fileObject.list();// list вытягивает список папок и файлов (null если это не директория, а файл)
        Set<String> as = new TreeSet<String>(new ArrayList<String>(Arrays.asList(fileArray)));
        int count = 0;
        ArrayList<String> listAfterSort = new ArrayList<String>();
        for (String currentFile : fileArray) {
            if ((currentFile.contains("rar") || currentFile.contains("zip")) && currentFile.contains("150")) {
                listAfterSort.add(currentFile);
                count++;
            }
        }

        for (String currentFile : listAfterSort) {
            System.out.println(count + " ---- " + currentFile);
            if (currentFile.contains("rar")) {
                System.out.println(count + "th is " + unArchivesMap.get("rar").unpack(currentFile, null));
            }
            if (currentFile.contains("zip")) {
                System.out.println(count + "th is " + unArchivesMap.get("zip").unpack(currentFile, null));
            }
            count--;
        }
    }

}
