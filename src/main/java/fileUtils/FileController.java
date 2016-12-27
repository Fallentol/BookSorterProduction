package fileUtils;


public class FileController {
    public static void main(String[] args) {
        //File fl = new File("\\","cucaracha2.txt");
        //File fl = new File("C:\\Users\\New\\IdeaProjects\\JavaLib", "cucaracha2.txt");
        //File fli = new File("cucaracha2.txt");
        //fl.renameTo(fli);
        //System.out.println("Path=" + fl.getPath() + " read=" + fl.getTotalSpace());

        /*try {
            FileWriter butnik = new FileWriter("C:\\batnik.bat");
            butnik.write("chcp 65001 \n");
        } catch (IOException ioEx) {
            System.out.println(ioEx);
        }


        String bookDirectoryPath = "E:\\LIBRARY\\Техническая литература";
        File fileObject = new File(bookDirectoryPath);
        System.out.println("fileObject is " + (fileObject.isDirectory() ? "Directory" : "File"));
        String[] fileArray = fileObject.list();// list вытягивает список папок и файлов (null если это не директория, а файл)
        int count = 1;
        for (String currentFile : fileArray) {

            *//*if(contRegEx(currentFile)) {
                System.out.println(count + " - " + currentFile);
                count++;
            }*//*

        }
*/


        /*String salt = " \"C:\\Program Files (x86)\\Foxit Reader\\FoxitReader.exe\" ";
        String find = "TEST21"; */

        /*String patName;
        for (Integer counter = 0; counter < smas.length; counter++) {
            File newF = new File(bookDirectoryPath + "\\" + smas[counter]);
            String kr = "fdsfgs";
            if (smas[counter].contains(find)) {
                System.out.println("Bool Number " + counter + " Name: " + smas[counter] + " is " + (newF.isDirectory() ? "Directory" : "File") + " Size is " + newF.length());
                patName = salt + "\"" + bookDirectoryPath + "\\" + smas[counter] + "\"";
                butnik.write("START " + patName + "\n");
                //new ProcessBuilder("cmd", "/c","start", "C:/OakOwlProject_1.0_BAT.bat").start();
                //Runtime.getRuntime().exec("cmd " + bookDirectoryPath + " " + patName);
            }
        }*/
        /*
        butnik.close();
        //new ProcessBuilder("cmd", "start", patName).start();
        //Runtime.getRuntime().exec("cmd /c c:/batnik.bat");*/

        /*File flo = new File("E:\\LIBRARY\\Техническая литература", "TEST21.txt");
        System.out.println(flo.isFile());
        FileInputStream fis = new FileInputStream(flo);
        for (int k = 0; k < 3; k++) {
            System.out.println((char) fis.read());
            System.out.println(fis.available());
        }

        //fis.reset();
        System.out.println("reset()");
        for (int k = 0; k < 3; k++)
            System.out.println((char) fis.read());*/

    }



}