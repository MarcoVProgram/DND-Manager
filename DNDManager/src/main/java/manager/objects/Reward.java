package manager.objects;

public class Reward extends Prize {

    private int idPC;
    private String link;

    public Reward(String name, int id, String type, int idPC, String description, String link) {
        super(name, id, type, description);
        this.idPC = idPC;
        this.link = link;
    }

    public int getIdPC() {
        return idPC;
    }

    public String getLink() {
        return link;
    }

    public void setIdPC(int idPC) {
        this.idPC = idPC;
    }

    public void setLink(String link) {
        this.link = link;
    }

        //Overrides
        @Override
    public String toString() {
        String info;

        boolean hasDesc = description != null && !description.isEmpty();

        //String Final
        info = String.format("Tag --> [ ID: %d\t|\tTag: %s\t|\tID Owner: %d\t|\tHas Description: %b\t|\tLink: <%b> ]",
                this.id, this.type, this.idPC, hasDesc, this.link);


        return info;
    }
}