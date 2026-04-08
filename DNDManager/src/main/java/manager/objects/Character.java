package manager.objects;

import manager.enums.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class Character {

    protected int id;
    protected String firstName;
    protected String fullName;
    protected String specie;
    protected Gender gender;
    protected Alignment alignment;
    protected Status currentStatus;
    protected String notes;

    private HashSet<Tag> tags;

    public Character(int id, String firstName, String fullName, String specie, Gender gender, Alignment alignment, Status currentStatus, String notes) {
        this.id = id;
        this.firstName = firstName;
        this.fullName = fullName;
        this.specie = specie;
        this.gender = gender;
        this.alignment = alignment;
        this.currentStatus = currentStatus;
        this.notes = notes;

        tags = new HashSet<>();
    }

    //Getter
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public String getSpecie() {
        return specie;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public Status getCurrentStatus() {
        return currentStatus;
    }

    public String getNotes() {
        return notes;
    }

    public List<Tag> getTags() {
        return new LinkedList<>(tags);
    }

    public String showTags() {
        String tagsOutput = "";
        for  (Tag tag : tags) {
            tagsOutput = tagsOutput + tag.toString() + "\n";
        }
        return tagsOutput;
    }

    //Setter
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public void setCurrentStatus(Status currentStatus) {
        this.currentStatus = currentStatus;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    //Overrides
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Character character)) return false;
        return Objects.equals(fullName, character.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(fullName);
    }

    //Functionality
    public boolean addTag(Tag tag) {
        return tags.add(tag);
    }

    public boolean removeTag(Tag tag) {
        return tags.remove(tag);
    }

    public String listTags() {
        String info = "";

        if (tags.isEmpty()) return "No Tags exist";

        for  (Tag tag : tags) {
            info = tag.toString() + "\n";
        }

        return info;
    }
}