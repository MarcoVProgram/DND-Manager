package manager.objects;

import manager.enums.Classes;

public class ClassLevels {

    private Classes className;
    private int level;
    private String subclass;

    public ClassLevels(Classes className, int level, String subclass) {
        this.className = className;
        this.level = level;
        this.subclass = subclass;
    }

    public Classes getClassName() {
        return className;
    }

    public int getLevel() {
        return level;
    }

    public String getSubclass() {
        return subclass;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setSubclass(String subclass) {
        this.subclass = subclass;
    }

    //Overrides
        @Override
    public String toString() {
        String info;

        String subclassCheck = "No Subclass";

        if (this.subclass != null || !this.subclass.isEmpty()) {
            subclassCheck = subclass;
        }

        //String Final
        info = String.format("< %s (%d) [%s] >",
                this.className.name(), this.level, this.subclass);


        return info;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
