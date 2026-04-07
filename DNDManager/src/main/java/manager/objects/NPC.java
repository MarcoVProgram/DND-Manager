package manager.objects;

import manager.enums.Alignment;
import manager.enums.Gender;
import manager.enums.Status;

public class NPC extends Character {

    private int challengeRating;
    private String dmName;

    public NPC(int id, String first_name, String full_name, int challengeRating, String specie, Gender gender,
               Alignment alignment, String dmName, Status currentStatus, String notes) {
        super(id, first_name, full_name, specie, gender, alignment, currentStatus, notes);

        this.challengeRating = challengeRating;
        this.dmName = dmName;
    }

    //Getter
    public int getChallengeRating() {
        return challengeRating;
    }

    public String getDmName() {
        return dmName;
    }

    //Setter
    public void setChallengeRating(int challengeRating) {
        this.challengeRating = challengeRating;
    }

    //Overrides
        @Override
    public String toString() {
        String info;

        boolean hasNotes = notes != null && !notes.isEmpty();

        //String Final
        info = String.format("NPC --> [ ID: %d\t|\tFirst Name: %s\t|\tFull Name: %s\t|\tCR: %d\t|\tSpecie: %s\t|\t" +
                        "Gender: %S\t|\tAlignment: %S\t|\tDM Name: %s\t|\tStatus: %S\t|\tHas Notes: %b ]",
                this.id, this.firstName, this.fullName, this.challengeRating, this.specie, this.gender.name(),
                this.alignment.name(), this.dmName, this.currentStatus.name(), hasNotes);


        return info;
    }
}
