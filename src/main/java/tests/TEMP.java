package tests;

import java.util.ArrayList;
import java.util.Arrays;

public class TEMP {
//    public static void main(String[] args) {
//        SQLUtils s =new SQLUtils();
//        s.createSYSTableUserData();
//        s.createDBTableBooks();
//        s.createDBTableLinks();
//        s.createDBTableTags();
//    }

    String[] stringBaseName = {"BookSorterPro"};
    ArrayList<String> arrayBaseList = new ArrayList<String>(Arrays.asList(stringBaseName));

    String[] stringUserList = {"admin"};
    ArrayList<String> arrayUserList = new ArrayList<String>(Arrays.asList(stringUserList));

    public ArrayList<String> getBaseList() {
        return arrayBaseList;
    }

    public String setNewBaseInBaseList(String baseName) {
        String result = "Done";
        arrayBaseList.add(baseName);
        return result;
    }

    public ArrayList<String> getUserList() {
        return arrayUserList;
    }

    public String setNewUserInUserList(String userName) {
        String result = "Done";
        arrayUserList.add(userName);
        return result;
    }
}
