public class RemoteObject {

    private int userID;
    private int id;
    private String title;
    private Boolean completed;

    public int getUserID(){ return userID; }

    public int getID(){ return id; }

    public String getTitle(){ return title; }

    public Boolean getComleted(){ return completed; }

    /*public RemoteObject (int userID, int id, String title, Boolean completed){
        this.userID = userID;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }*/

    public RemoteObject(String string){

        String[] a = string.split(",");
        String[] b = a[0].split(": ");
        String[] c = a[1].split(": ");
        String[] d = a[2].split("\"");
        String[] e = a[3].split(": ");
        String[] f = e[1].split("}");

        userID = Integer.parseInt(b[1]);
        id = Integer.parseInt(c[1]);
        title = d[3];
        completed = Boolean.parseBoolean(f[0]);

    }

}
