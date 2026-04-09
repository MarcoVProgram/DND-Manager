package manager.objects.abstractObjects;

import java.util.Objects;

public abstract class Prize extends Tagged {

    protected String name;
    protected int id;
    protected String type;
    protected String description;

    public Prize(String name, int id, String type, String description) {
        super();
        this.name = name;
        this.id = id;
        this.type = type;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Prize prize)) return false;
        return Objects.equals(name, prize.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
