package manager.objects;

import manager.enums.Alignment;
import manager.enums.Classes;
import manager.enums.Gender;
import manager.enums.Status;
import manager.objects.abstractObjects.Character;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

public class Player_Character extends Character {

    private String playerName;

    private LinkedHashSet<ClassLevels> levelSpread;
    private List<Reward> assets;

    public Player_Character(int id, String first_name, String full_name, String specie, Gender gender, Alignment alignment, String playerName,
                            Status currentStatus, String notes) {
        super(id, first_name, full_name, specie, gender, alignment, currentStatus, notes);

        this.playerName = playerName;
        assets = new LinkedList<>();
        levelSpread = new LinkedHashSet<>();
    }

    public Player_Character(int id, String first_name, String full_name, String specie, Gender gender, Alignment alignment, String playerName,
                            Status currentStatus, String notes, LinkedHashSet<ClassLevels> levelSpread) {
        super(id, first_name, full_name, specie, gender, alignment, currentStatus, notes);

        this.playerName = playerName;
        assets = new LinkedList<>();
        this.levelSpread = levelSpread;
    }

    //Getter
    public LinkedHashSet<ClassLevels> getLevelSpread() {
        return new LinkedHashSet<>(levelSpread);
    }

    private ClassLevels getClassInstance(Classes className) {
        for (ClassLevels classLevels : this.levelSpread) {
            if (className.equals(classLevels.getClassName())) {
                return classLevels;
            }
        }
        return null;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<Reward> getAssets() {
        return new LinkedList<>(assets);
    }

    //Setter
    public void setClasses(ClassLevels classInstance) {
        this.levelSpread.add(classInstance);
    }

    public void setSubclass(Classes className, String subclass) {
        for  (ClassLevels classLevels : this.levelSpread) {
            if (className.equals(classLevels.getClassName())) {
                classLevels.setSubclass(subclass);
                break;
            }
        }
    }

    public void setClassLevels(Classes className, int classLevel) {
        for (ClassLevels classLevels : this.levelSpread) {
            if (className.equals(classLevels.getClassName())) {
                classLevels.setLevel(classLevel);
            }
        }
    }

    public void removeClasses(Classes className) {
        ClassLevels classToRemove = getClassInstance(className);
        if (classToRemove != null) {
            this.levelSpread.remove(classToRemove);
        }
    }

    //Overrides
        @Override
    public String toString() {
        String info;

        boolean hasNotes = notes != null && !notes.isEmpty();

        //String Final
        info = String.format("NPC --> [ ID: %d\t|\tFirst Name: %s\t|\tFull Name: %s\t|\tSpecie: %s\t|\tGender: %S" +
                        "\t|\tAlignment: %S\t|\tPlayer: %s\t|\tStatus: %S\t|\tHas Notes: %b ]",
                this.id, this.firstName, this.fullName, this.specie, this.gender.name(), this.alignment.name(),
                this.playerName, this.currentStatus.name(), hasNotes);

        if (levelSpread != null || !levelSpread.isEmpty()) {
            info += "\n";
            for (ClassLevels classLevels : this.levelSpread) {
                info += classLevels.toString();
            }
        }


        return info;
    }

    public boolean giveAsset(Reward reward) {
        return assets.add(reward);
    }

    public boolean revokeAsset(Tag tag) {
        return assets.remove(tag);
    }

    public void levelUp(Classes className) {
        ClassLevels classLevelUp = getClassInstance(className);

        if (classLevelUp != null) {
            classLevelUp.setLevel(classLevelUp.getLevel() + 1);
        }

        else {
            classLevelUp = new ClassLevels(className, 1, "");
        }
    }
}
