package fileUtils;

public class UnzipThread extends Thread {
    public void run() {
        try {
            if (!isInterrupted()) {

            } else {
                throw new InterruptedException();
            }
        } catch (InterruptedException e) {
            System.out.println("THREAD IS ABORTED");
        }
    }
}
