package essence;


public class UserProfile {

    public int prof_id;
    public String profPath;

    public UserProfile(){}

    public UserProfile(int prof_id, String profPath) {
        this.prof_id = prof_id;
        this.profPath = profPath;
    }

    public int getProfId() {
        return prof_id;
    }

    public int setProfId(int prof_id) {
        this.prof_id = prof_id;
        return prof_id;
    }

    public String getProfPath() {
        return this.profPath;
    }

    public String setProfPath(String profPath) {
        this.profPath = profPath;
        return profPath;
    }

}


