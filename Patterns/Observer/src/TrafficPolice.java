import java.util.ArrayList;

public class TrafficPolice {
    protected ArrayList<String> posts;
    private String message;

    public void notifyAllMembers() {
        for (String post : posts) {
            System.out.println(post + " осведомлён о сообщении " + message);
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
