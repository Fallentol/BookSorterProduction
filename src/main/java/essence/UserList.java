package essence;

public class UserList {

    public int user_id;
    public String userName;
    public String userPass;

    public UserList(){

    }

    public UserList(int user_id, String userName, String userPass) {
        this.user_id = user_id;
        this.userName = userName;
        this.userPass = userPass;
    }

    public int getUserId() {
        return user_id;
    }

    public int setProfId(int user_id) {
        this.user_id = user_id;
        return user_id;
    }

    public String getUserName() {
        return this.userName;
    }

    public String setUserName(String userName) {
        this.userName = userName;
        return userName;
    }

    public String getUserPass() {
        return this.userPass;
    }

    public String setUserPass(String userPass) {
        this.userPass = userPass;
        return userPass;
    }
}
