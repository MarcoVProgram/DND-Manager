package manager.objects;

public class Tag {

    private int id;
    private String tag;
    private String lore;

    public Tag(int id, String tag, String lore) {
        this.id = id;
        this.tag = tag;
        this.lore = lore;
    }

    public int getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    //Overrides
        @Override
    public String toString() {
        String info;

        boolean hasLore = lore != null && !lore.isEmpty();

        //String Final
        info = String.format("[ Tag: %s\t|\tID: %d\t|\tHas Lore: %b ]",
                this.id, this.tag, hasLore);


        return info;
    }
}
