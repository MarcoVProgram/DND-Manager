package manager.tools;

public class Patterns {
    public final static String CHARACTER_NAME = "^[a-z A-Z'_-]{1,50}$";
    public final static String GENERIC_NAME_20 = "^[a-z A-Z)(,']{1,20}$";
    public final static String GENERIC_NAME_30 = "^[a-z A-Z)(,']{1,30}$";
    public final static String LINK_FORMAT = "^[a-z:_/.0-9]{0,200}$";
    public final static String NOTE_FORMAT = "^[a-zA-Z 0-9,.]{0,200}$";
    public final static String USER_ID = "^[a-z0-9._]{1,30}$";
}
