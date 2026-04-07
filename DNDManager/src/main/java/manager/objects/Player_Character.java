package manager.objects;

import manager.enums.Alignment;
import manager.enums.Gender;
import manager.enums.Status;

public class Player_Character extends Character {

    private String pcClass;
    private String subclass;
    private int levels;
    private boolean isMulticlass;
    private String playerName;

    public Player_Character(int id, String first_name, String full_name, String pcClass, String subclass, int levels,
                            boolean isMulticlass, String specie, Gender gender, Alignment alignment, String playerName,
                            Status currentStatus, String notes) {
        super(id, first_name, full_name, specie, gender, alignment, currentStatus, notes);

        this.pcClass = pcClass;
        this.subclass = subclass;
        this.levels = levels;
        this.isMulticlass = isMulticlass;
        this.playerName = playerName;
    }

    //Getter
    public String getPcClass() {
        return pcClass;
    }

    public String getSubclass() {
        return subclass;
    }

    public int getLevels() {
        return levels;
    }

    public boolean isMulticlass() {
        return isMulticlass;
    }

    public String getPlayerName() {
        return playerName;
    }

    //Setter
    public void setPcClass(String pcClass) {
        this.pcClass = pcClass;
    }

    public void setSubclass(String subclass) {
        this.subclass = subclass;
    }

    public void setLevels(int levels) {
        this.levels = levels;
    }

    public void setMulticlass(boolean multiclass) {
        isMulticlass = multiclass;
    }

    //Overrides
        @Override
    public String toString() {
        String info;

        boolean hasNotes = notes != null && !notes.isEmpty();

        //String Final
        info = String.format("NPC --> [ ID: %d\t|\tFirst Name: %s\t|\tFull Name: %s\t|\tClass: %s\t|\tSubclass: %s\t|\t" +
                        "Levels: %d\t|\tIs Multiclass: %b\t|\tSpecie: %s\t|\tGender: %S\t|\tAlignment: %S\t|\tPlayer: %s\t|\t" +
                        "Status: %S\t|\tHas Notes: %b ]",
                this.id, this.firstName, this.fullName, this.pcClass, this.subclass, this.levels, this.isMulticlass, this.specie,
                this.gender.name(), this.alignment.name(), this.playerName, this.currentStatus.name(), hasNotes);


        return info;
    }
}
