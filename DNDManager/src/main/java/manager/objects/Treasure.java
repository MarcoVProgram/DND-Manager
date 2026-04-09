package manager.objects;

import manager.objects.abstractObjects.Prize;

public class Treasure extends Prize {

    private int idNPC;
    private boolean found;

    public Treasure(String name, int id, String type, int idNPC, String description, boolean found) {
        super(name, id, type, description);
        this.idNPC = idNPC;
        this.found = found;
    }

    public int getIdNPC() {
        return idNPC;
    }

    public boolean hasBeenFound() {
        return found;
    }

    public void setIdNPC(int idNPC) {
        this.idNPC = idNPC;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    //Overrides
        @Override
    public String toString() {
        String info;

        boolean hasDesc = description != null && !description.isEmpty();

        //String Final
        info = String.format("[ Tag: %s\t|\tID: %d\t|\tID Owner: %d\t|\tHas Description: %b\t|\tHas been found: %b ]",
                this.id, this.type, this.idNPC, hasDesc, this.found);


        return info;
    }
}