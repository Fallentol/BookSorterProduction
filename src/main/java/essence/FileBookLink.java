package essence;

public class FileBookLink {

    public FileBookLink(String fileName, int baseIdint) {
        this.fileName = fileName;
        this.baseId = String.format("%06d", baseIdint);
    }

    public FileBookLink() {
    }

    private String fileName; // название файла
    private String baseId; // айдишник записи в БД

    public void setFileName(String file) {
        this.fileName = file;
    }

    public void setBaseId(int baseInt) {
        this.baseId = String.format("%06d", baseInt);
    }

    public String getFileName() {
        return fileName;
    }

    public String getBaseId() {
        return baseId;
    }
}
