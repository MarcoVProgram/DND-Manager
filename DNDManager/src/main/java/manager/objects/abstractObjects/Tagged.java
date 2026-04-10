package manager.objects.abstractObjects;

import manager.objects.Tag;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public abstract class Tagged {

    protected Set<Tag> tags;

    public Tagged() {
        this.tags = new HashSet<>();
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

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
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