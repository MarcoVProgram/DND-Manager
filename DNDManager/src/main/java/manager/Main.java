package manager;

import manager.controlSQL.SQLAccess;
import manager.enums.Alignment;
import manager.enums.Classes;
import manager.enums.Gender;
import manager.enums.Status;
import manager.objects.*;
import manager.objects.abstractObjects.Tagged;
import manager.tools.MyUtils;
import manager.tools.Patterns;

import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        MyUtils.print("Welcome to DnD Manager!");

        String[] menu = new String[9];
        menu[0] = "Search Information";
        menu[1] = "Update Information";
        menu[2] = "Add a new element";
        menu[3] = "Delete an element";
        menu[4] = "Manage elements";
        menu[8] = "Exit Program";

        int option;

        do {

            MyUtils.menuMaker("MAIN MENU",
                    menu);

            option = typeIndex();

            switch (option) {

                //Search Info
                case 1:
                    searchMenu();
                    break;

                //Update Info
                case 2:
                    updateMenu();
                    break;

                //Add Info
                case 3:
                    addMenu();
                    break;

                //Delete Info
                case 4:
                    deleteMenu();
                    break;

                //Manage Menu
                case 5:
                    manageMenu();
                    break;

                //Exit Program
                case 9:
                    MyUtils.print("\nGoodbye!");
                    break;

                //Error invalid option
                default:
                    MyUtils.print("\nThe option you are trying to select does not exist yet.");
                    break;
            }
        } while (option != 9);
    }

    //PRIVATE METHODS
    private static int typeIndex() {
        return MyUtils.insertValidInt("Insert one index option",
                    "The answer is not a valid index number");
    }

    //ATTRIBUTE METHODS
    private static int typeID() {
        return MyUtils.insertValidInt("Insert the Number ID of what you are trying to search",
                "The answer is not a valid ID");
    }

    private static String typeCharacterName() {
        return MyUtils.insertValidString("Insert the full name of the character (maximum 50 characters)",
                Patterns.CHARACTER_NAME,
                "The input does not match a valid character name, use letters, apostrophes and underscores or hyphens");
    }

    private static String typeName() {
        return MyUtils.insertValidString("Insert a name (maximum 20 characters)",
                Patterns.GENERIC_NAME_20,
                "The input does not match a valid name, only letters and spaces");
    }

    private static String typeSpecies() {
        return MyUtils.insertValidString("Insert the name of the specie (maximum 30 characters)",
                Patterns.GENERIC_NAME_30,
                "The input does not match a valid specie, only letters and spaces");
    }

    private static Gender typeGender() {
        return (Gender) MyUtils.selectEnum(Gender.class);
    }

    private static Alignment typeAlignment() {
        return (Alignment) MyUtils.selectEnum(Alignment.class);
    }

    private static Status typeStatus() {
        return (Status) MyUtils.selectEnum(Status.class);
    }

    private static String typeUserID() {
        return MyUtils.insertValidString("Insert a Discord ID",
                Patterns.USER_ID,
                "The answer is not a valid Discord ID, try letters, numbers, hyphens, dots and underscores, and less than 30 characters");
    }

    private static String typeNotes() {
        return MyUtils.insertValidString("Insert some notes (can be left empty)",
                Patterns.NOTE_FORMAT,
                "Try to avoid special characters, just spaces, letters and numbers");
    }

    private static Classes typeClasses() {
        return (Classes) MyUtils.selectEnum(Classes.class);
    }

    private static int typeLevel() {
        return MyUtils.insertValidInt("Insert the level",
                "The answer is not within range",
                1, 30);
    }

    private static String typeSubclasses() {
        return MyUtils.insertValidString("Insert the Subclass (maximum 30 characters)",
                Patterns.GENERIC_NAME_30,
                "The answer is not a valid subclass, try using letters and spaces");
    }

    private static String typePrizeName() {
        return MyUtils.insertValidString("Insert the name of the treasure or reward (maxmimum 30 characters)",
                Patterns.GENERIC_NAME_30,
                "The answer is not a valid name, only letters and spaces");
    }

    private static String typePrizeType() {
        return MyUtils.insertValidString("Insert the type of the treasure or reward (maximum 30 characters)",
                Patterns.GENERIC_NAME_30,
                "The answer is not a valid type, only letters and spaces");
    }

    private static String typeLink() {
        return MyUtils.insertValidString("Paste or insert the link to the reward (can be empty)",
                Patterns.LINK_FORMAT,
                "The link inserted is not a valid one, copy the discord link instead");
    }

    private static String typeDescription() {
        String desc;
        String path = ".\\userInteraction\\";
        String file = "input.txt";
        boolean proceed;

        do {
            desc = "";
            MyUtils.print("Insert in the file named " + file + " located in " + path +
                    "\nAll the information you'd wish to upload. Once it is done, press to continue");
            MyUtils.pause();
            MyUtils.print("Commencing reading...");

            try (FileReader fr = new FileReader(path + file);
                 BufferedReader br = new BufferedReader(fr);) {

                List<String> lines = br.lines().toList();

                for (String line : lines) {
                    desc += line;
                    if (!lines.getLast().equals(line)) {
                        desc += "\n";
                    }
                }

                if (desc.length() > 4100) {
                    MyUtils.print("The file " + file + " is too large.");
                    proceed = MyUtils.confirm("Do you want to skip with the field empty? You will be allowed to reupload otherwise");
                    if (proceed) {
                        desc = "";
                    }

                } else {
                    proceed = true;
                }

            } catch (FileNotFoundException e) {
                MyUtils.print("The file " + file + " does not exist. Leaving field empty.");
                desc = "";
                proceed = true;
            } catch (IOException e) {
                MyUtils.print("The file " + file + " could not be opened. Leaving field empty.");
                desc = "";
                proceed = true;
            }
        } while (!proceed);

        return desc;
    }

    private static void printDescription(String desc) {
        String path = ".\\userInteraction\\";
        String file = "output.txt";

        MyUtils.print("Printing description/lore on file " + file + " located in " + path);

        try (FileWriter fw = new FileWriter(path + file, false);
            BufferedWriter bw = new BufferedWriter(fw);) {

            String[] lines = desc.split("\n");
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }

        } catch (FileNotFoundException e) {
            MyUtils.print("The file " + file + " does not exist.");
        } catch (IOException e) {
            MyUtils.print("The file " + file + " could not be opened.");
        }
    }

    private static int typeChallengeRating() {
        return MyUtils.insertValidInt("Insert the Challenge Rating",
                "The number inserted is not in valid range",
                1,255);
    }

    private static boolean foundCheck() {
        return MyUtils.confirm("Has it been found by PCs?");
    }

    private static int selectPCByIdentification() {
        MyUtils.print("\nObtaining ID of a PC");

        String[] options = new String[2];
        options[0] = "Write PC ID";
        options[1] = "Write PC Full Name";

        int id;

        MyUtils.menuMaker("Select how do you want to identify the PC (Type ID -1 if empty)",
                options);

        int choice = MyUtils.insertValidInt("Choose one of the two options",
                "That is not a valid option",
                1,2);

        switch (choice) {
            case 1:
                id = typeID();
                break;
            case 2:
                String fullName = typeCharacterName();
                id = SQLAccess.getIDFromPCName(fullName);
                break;
            default:
                MyUtils.print("An unexpected error occurred, communicate it to a developer.");
                id = -1;
                break;
        }

        return id;
    }

    private static int selectNPCByIdentification() {
        MyUtils.print("\nObtaining ID of an NPC");

        String[] options = new String[2];
        options[0] = "Write NPC ID";
        options[1] = "Write NPC Full Name";

        int id;

        MyUtils.menuMaker("Select how do you want to identify the NPC",
                options);

        int choice = MyUtils.insertValidInt("Choose one of the two options",
                "That is not a valid option",
                1,2);

        switch (choice) {
            case 1:
                id = typeID();
                break;
            case 2:
                String fullName = typeCharacterName();
                id = SQLAccess.getIDFromNPCName(fullName);
                break;
            default:
                MyUtils.print("An unexpected error occurred, communicate it to a developer.");
                id = -1;
                break;
        }

        return id;
    }

    private static int selectTagByIdentification() {
        MyUtils.print("\nObtaining ID of a Tag");

        String[] options = new String[2];
        options[0] = "Write Tag ID";
        options[1] = "Write Tag Full Name";

        int id;

        MyUtils.menuMaker("Select how do you want to identify the Tag",
                options);

        int choice = MyUtils.insertValidInt("Choose one of the two options",
                "That is not a valid option",
                1,2);

        switch (choice) {
            case 1:
                id = typeID();
                break;
            case 2:
                String tagName = typeName();
                id = SQLAccess.getIDFromTagName(tagName);
                break;
            default:
                MyUtils.print("An unexpected error occurred, communicate it to a developer.");
                id = -1;
                break;
        }

        return id;
    }

    private static int selectRewardByIdentification() {
        MyUtils.print("\nObtaining ID of a Reward");

        String[] options = new String[2];
        options[0] = "Write Reward ID";
        options[1] = "Write Reward Full Name";

        int id;

        MyUtils.menuMaker("Select how do you want to identify the Reward",
                options);

        int choice = MyUtils.insertValidInt("Choose one of the two options",
                "That is not a valid option",
                1,2);

        switch (choice) {
            case 1:
                id = typeID();
                break;
            case 2:
                String rewardName = typePrizeName();
                id = SQLAccess.getIDFromRewardName(rewardName);
                break;
            default:
                MyUtils.print("An unexpected error occurred, communicate it to a developer.");
                id = -1;
                break;
        }

        return id;
    }

    private static int selectTreasureByIdentification() {
        MyUtils.print("\nObtaining ID of a Treasure");

        String[] options = new String[2];
        options[0] = "Write Treasure ID";
        options[1] = "Write Treasure Full Name";

        int id;

        MyUtils.menuMaker("Select how do you want to identify the Treasure",
                options);

        int choice = MyUtils.insertValidInt("Choose one of the two options",
                "That is not a valid option",
                1,2);

        switch (choice) {
            case 1:
                id = typeID();
                break;
            case 2:
                String treasureName = typePrizeName();
                id = SQLAccess.getIDFromTreasureName(treasureName);
                break;
            default:
                MyUtils.print("An unexpected error occurred, communicate it to a developer.");
                id = -1;
                break;
        }

        return id;
    }

    private static void modifyClassLevel(Player_Character pc) {
        MyUtils.print("\nUpdating Class Levels");
        boolean done = false;

        do {
            MyUtils.print("Select the class in which you wish to add levels in");
            Classes thisClass = MyUtils.selectEnum(Classes.class);

            boolean instance = pc.hasClassInstance(thisClass);

            if (instance) {
                MyUtils.print("\nThis PC already has levels in that class, this is the following list:");
                Set<ClassLevels> lvlSpread = pc.getLevelSpread();
                for (ClassLevels lvl : lvlSpread) {
                    MyUtils.print(lvl.toString());
                }

                done = MyUtils.confirm("\nDo you want to exit modifying this class?");

                if (!done) {
                    int levels;

                    boolean lvlUp = MyUtils.confirm("\nDo you want to level up?");
                    if (lvlUp) {
                        if (pc.getTotalLevel() < 30) {
                            pc.levelUp(thisClass);
                        } else {
                            MyUtils.print("\nThe character is already level 30, it cannot go higher");
                        }
                        continue;
                    }

                    else {
                        MyUtils.print("\nSetting Class Levels");
                        levels = typeLevel();

                        if (!(pc.getTotalLevel() + levels <= 30)) {
                            MyUtils.print("It cannot go higher than 30");
                            continue;
                        }
                    }

                    String subclass = typeSubclasses();

                    ClassLevels lvl = new ClassLevels(thisClass, levels, subclass);
                    SQLAccess.updateClassInstance(pc.getId(), lvl);
                }
            }
            else {
                int levels = typeLevel();

                if (!(pc.getTotalLevel() + levels <= 30)) {
                    MyUtils.print("It cannot go higher than 30");
                    continue;
                }

                String subclass = typeSubclasses();

                ClassLevels lvl = new ClassLevels(thisClass, levels, subclass);
                SQLAccess.sqlAddClassInstance(pc.getId(), lvl);
            }

            done = MyUtils.confirm("\nAre you done with all changes?");

        } while (!done);
    }

    private static void showTag(Tag tag) {
        MyUtils.print("\nTag Info:");

        if (tag != null) {
            MyUtils.print(tag.toString());

            if (tag.getLore() != null && MyUtils.confirm("Do you want to load the Tag lore in the output file?")) {
                printDescription(tag.getLore());
            }
        } else {
            MyUtils.print("No results to print");
        }
    }

    private static void showPC(Player_Character pc) {
        MyUtils.print("\nPC Info:");

        if (pc != null) {
            MyUtils.print(pc.toString());
            MyUtils.print(pc.getNotes());
            for  (Tag tag : pc.getTags()) {
                MyUtils.print(tag.toString());
            }
            for (Reward reward : pc.getAssets()) {
                MyUtils.print(reward.toString());
            }
            for (ClassLevels lvl : pc.getLevelSpread()) {
                MyUtils.print(lvl.toString());
            }
        } else {
            MyUtils.print("No results to print");
        }
    }

    private static void showNPC(NPC npc) {
        MyUtils.print("\nNPC Info:");

        if (npc != null) {
            MyUtils.print(npc.toString());
            MyUtils.print(npc.getNotes());
            for  (Tag tag : npc.getTags()) {
                MyUtils.print(tag.toString());
            }
            for (Treasure loot : npc.getLoot()) {
                MyUtils.print(loot.toString());
            }
        } else {
            MyUtils.print("No results to print");
        }
    }

    private static void showReward(Reward reward) {
        MyUtils.print("\nReward Info:");

        if (reward != null) {
            MyUtils.print(reward.toString());
            for   (Tag tag : reward.getTags()) {
                MyUtils.print(tag.toString());
            }

            if (reward.getDescription() != null && MyUtils.confirm("Do you want to load the Reward Description in the output file?")) {
                printDescription(reward.getDescription());
            }
        } else {
            MyUtils.print("No results to print");
        }
    }

    private static void showTreasure(Treasure treasure) {
        MyUtils.print("\nTreasure Info:");

        if (treasure != null) {
            MyUtils.print(treasure.toString());
            for   (Tag tag : treasure.getTags()) {
                MyUtils.print(tag.toString());
            }

            if (treasure.getDescription() != null && MyUtils.confirm("Do you want to load the Treasure Description in the output file?")) {
                printDescription(treasure.getDescription());
            }
        } else {
            MyUtils.print("No results to print");
        }
    }

    //SEARCH SUBMENUS
    public static void searchMenu() {

        String[] menuSearch = new String[9];
        menuSearch[0] = "Search in Tags";
        menuSearch[1] = "Search in PCs";
        menuSearch[2] = "Search in NPCs";
        menuSearch[3] = "Search in Rewards";
        menuSearch[4] = "Search in Treasures";
        menuSearch[8] = "Go back";

        int option;

        do {
            MyUtils.menuMaker("SEARCH MENU",
                    menuSearch);
            option = typeIndex();

            switch (option) {
                case 1:
                    searchTags();
                    break;
                case 2:
                    searchPCs();
                    break;
                case 3:
                    searchNPCs();
                    break;
                case 4:
                    searchRewards();
                    break;
                case 5:
                    searchTreasures();
                    break;
                case 9:
                    MyUtils.print("\nExiting Search!");
                    break;
                default:
                    MyUtils.print("\nThe option you are trying to select does not exist yet.");
                    break;
            }
        } while (option != 9);
    }

    public static void searchTags() {
        String[] searchTagMenu = new String[9];

        searchTagMenu[0] = "Search all Tags";
        searchTagMenu[1] = "Search Tag by Name";
        searchTagMenu[2] = "Search Tag by ID";
        searchTagMenu[3] = "Search Everything containing one Tag";
        searchTagMenu[8] = "Go back";

        int option;

        do {
            MyUtils.menuMaker("SEARCH MENU",
                    searchTagMenu);
            option = typeIndex();

            switch (option) {

                case 1:
                    MyUtils.print("\nSearching All Tags:\n");

                    List<Tag> tagList = SQLAccess.getAllTags();
                    if (tagList == null) {
                        MyUtils.print("No results to print");
                        continue;
                    }
                    for (Tag tag : tagList) {
                        MyUtils.print(tag.toString());
                    }

                    MyUtils.pause();
                    break;

                case 2:
                    MyUtils.print("\nSearching Tag By Name:\n");

                    String tagName = typeName();
                    Tag tagByName = SQLAccess.getTagByName(tagName);
                    showTag(tagByName);

                    MyUtils.pause();
                    break;

                case 3:
                    MyUtils.print("\nSearching Tag By ID:\n");

                    int id = typeID();
                    Tag tagByID = SQLAccess.getTagById(id);
                    showTag(tagByID);

                    MyUtils.pause();
                    break;

                case 4:
                    MyUtils.print("\nSearching Everything containing Tag:\n");

                    Tag tag = SQLAccess.getTagById(selectTagByIdentification());
                    List<Tagged> results = SQLAccess.getEverythingWithTag(tag);
                    if (results == null) {
                        MyUtils.print("No results to print");
                        continue;
                    }
                    for (Tagged tagged : results) {
                        MyUtils.print(tagged.toString());
                    }

                    MyUtils.pause();
                    break;

                case 9:
                    MyUtils.print("\nExiting Search!");
                    break;

                default:
                    MyUtils.print("\nThe option you are trying to select does not exist yet.");
                    break;
            }
        } while (option != 9);
    }

    public static void searchPCs() {

        String[] searchPCMenu = new String[9];

        searchPCMenu[0] = "Search all PCs";
        searchPCMenu[1] = "Find PC by Name";
        searchPCMenu[2] = "Find PC by ID";
        searchPCMenu[3] = "Search PCs inside Level Range";
        searchPCMenu[4] = "Search PCs that have one Tag";
        searchPCMenu[5] = "Search PCs from a Player";
        searchPCMenu[8] = "Go back";

        int option;

        do {
            MyUtils.menuMaker("SEARCH MENU",
                    searchPCMenu);
            option = typeIndex();

            switch (option) {
                case 1:
                    MyUtils.print("\nSearching All PCs:\n");

                    List<Player_Character> pcs = SQLAccess.getEverythingAboutPCs();
                    if (pcs == null) {
                        MyUtils.print("No results to print");
                        continue;
                    }
                    for  (Player_Character pc : pcs) {
                        MyUtils.print(pc.toString());
                    }

                    MyUtils.pause();
                    break;

                case 2:
                    MyUtils.print("\nSearching PC by name:\n");

                    String fullName = typeCharacterName();
                    Player_Character pc = SQLAccess.getPlayerByName(fullName);
                    showPC(pc);

                    MyUtils.pause();
                    break;

                case 3:
                    MyUtils.print("\nSearching PC by ID:\n");

                    int id = typeID();
                    Player_Character pcId = SQLAccess.getPlayerByID(id);
                    showPC(pcId);

                    MyUtils.pause();
                    break;

                case 4:
                    MyUtils.print("\nSearching PCs inside Level Range:\n");

                    int min = MyUtils.insertValidInt("Write the minimum level",
                            "You are not giving a number",
                            1, 30);
                    int max = MyUtils.insertValidInt("Write the maximum level",
                            "You are not giving a number",
                            1, 30);

                    if (min > max) {
                        MyUtils.print("The minimum level is greater than the maximum level");
                        continue;
                    }
                    List<Player_Character> playerCharacters = SQLAccess.getPCsInLevelRange(min, max);
                    if (playerCharacters == null) {
                        MyUtils.print("No results to print");
                        continue;
                    }
                    for (Player_Character playerCharacter : playerCharacters) {
                        MyUtils.print(playerCharacter.toString());
                    }

                    break;

                case 5:
                    MyUtils.print("\nSearching PCs that have one Tag:\n");

                    Tag tag = SQLAccess.getTagById(selectTagByIdentification());
                    List<Player_Character> pcList = SQLAccess.getPCsByTag(tag);
                    if (pcList == null) {
                        MyUtils.print("No results to print");
                        continue;
                    }
                    for (Player_Character playerCharacter  : pcList) {
                        MyUtils.print(playerCharacter.toString());
                    }

                    MyUtils.pause();
                    break;

                case 6:
                    MyUtils.print("\nSearching PCs from one User:\n");

                    String userName = typeUserID();
                    List<Player_Character> userPCs = SQLAccess.getPCsFromUser(userName);
                    if (userPCs == null) {
                        MyUtils.print("No results to print");
                        continue;
                    }
                    for (Player_Character playerCharacter  : userPCs) {
                        MyUtils.print(playerCharacter.toString());
                    }

                    MyUtils.pause();
                    break;

                case 9:
                    MyUtils.print("\nExiting Search!");
                    break;
                default:
                    MyUtils.print("\nThe option you are trying to select does not exist yet.");
                    break;
            }
        } while (option != 9);
    }

    public static void searchNPCs() {
        String[] searchNPCMenu = new String[9];

        searchNPCMenu[0] = "Search all NPCs";
        searchNPCMenu[1] = "Find NPC by Name";
        searchNPCMenu[2] = "Find NPC by ID";
        searchNPCMenu[3] = "Search NPCs that have one Tag";
        searchNPCMenu[4] = "Search NPCs from a DM";
        searchNPCMenu[8] = "Go back";

        int option;

        do {
            MyUtils.menuMaker("SEARCH MENU",
                    searchNPCMenu);
            option = typeIndex();

            switch (option) {
                case 1:
                    MyUtils.print("\nSearching All NPCs:\n");

                    List<NPC> npcs = SQLAccess.getEverythingAboutNPCs();
                    if (npcs == null) {
                        MyUtils.print("No results to print");
                        continue;
                    }
                    for  (NPC npc : npcs) {
                        MyUtils.print(npc.toString());
                    }

                    MyUtils.pause();
                    break;

                case 2:
                    MyUtils.print("\nSearching NPC by name:\n");

                    String fullName = typeCharacterName();
                    NPC npc = SQLAccess.getNPCByName(fullName);
                    showNPC(npc);

                    MyUtils.pause();
                    break;

                case 3:
                    MyUtils.print("\nSearching NPC by ID:\n");

                    int id = typeID();
                    NPC npcId = SQLAccess.getNPCByID(id);
                    showNPC(npcId);

                    MyUtils.pause();
                    break;

                case 4:
                    MyUtils.print("\nSearching NPCs that have one Tag:\n");

                    Tag tag = SQLAccess.getTagById(selectTagByIdentification());
                    List<NPC> npcList = SQLAccess.getNPCsByTag(tag);
                    if (npcList == null) {
                        MyUtils.print("No results to print");
                        continue;
                    }
                    for (NPC npcHere : npcList) {
                        MyUtils.print(npcHere.toString());
                    }

                    MyUtils.pause();
                    break;

                case 5:
                    MyUtils.print("\nSearching NPCs from one User:\n");

                    String userName = typeUserID();
                    List<NPC> userNPCs = SQLAccess.getNPCsFromDM(userName);
                    if (userNPCs == null) {
                        MyUtils.print("No results to print");
                        continue;
                    }
                    for (NPC npcIdsDM  : userNPCs) {
                        MyUtils.print(npcIdsDM.toString());
                    }

                    MyUtils.pause();
                    break;

                case 9:
                    MyUtils.print("\nExiting Search!");
                    break;
                default:
                    MyUtils.print("\nThe option you are trying to select does not exist yet.");
                    break;
            }
        } while (option != 9);
    }
    public static void searchRewards() {
        String[] searchRewardsMenu = new String[9];

        searchRewardsMenu[0] = "Search all Rewards";
        searchRewardsMenu[1] = "Find Rewards by Name";
        searchRewardsMenu[2] = "Find Rewards by ID";
        searchRewardsMenu[3] = "Search Rewards of Type";
        searchRewardsMenu[4] = "Search Rewards that have one Tag";
        searchRewardsMenu[8] = "Go back";

        int option;

        do {
            MyUtils.menuMaker("SEARCH MENU",
                    searchRewardsMenu);
            option = typeIndex();

            switch (option) {
                case 1:
                    MyUtils.print("\nSearching All Rewards:\n");

                    List<Reward> rewards = SQLAccess.getAllRewards();
                    if (rewards == null) {
                        MyUtils.print("No results to print");
                        continue;
                    }
                    for(Reward reward : rewards) {
                        MyUtils.print(reward.toString());
                    }

                    MyUtils.pause();
                    break;

                case 2:
                    MyUtils.print("\nFinding Reward by Name:\n");

                    String name = typePrizeName();
                    Reward reward = SQLAccess.getRewardByName(name);
                    showReward(reward);

                    MyUtils.pause();
                    break;

                case 3:
                    MyUtils.print("\nFinding Rewards by ID:\n");

                    int  id = typeID();
                    Reward rewardId = SQLAccess.getRewardByID(id);
                    showReward(rewardId);

                    MyUtils.pause();
                    break;

                case 4:
                    MyUtils.print("\nSearching Rewards of type:\n");

                    String type = typePrizeType();
                    List<Reward> rewardList = SQLAccess.getRewardsByType(type);
                    if (rewardList == null) {
                        MyUtils.print("No results to print");
                        continue;
                    }
                    for (Reward reward1 : rewardList ) {
                        MyUtils.print(reward1.toString());
                    }

                    MyUtils.pause();
                    break;

                case 5:
                    MyUtils.print("\nSearching Rewards that have one Tag:\n");

                    Tag tag = SQLAccess.getTagById(selectTagByIdentification());
                    List<Reward> rewardsTagged = SQLAccess.getRewardsByTag(tag);
                    if (rewardsTagged == null) {
                        MyUtils.print("No results to print");
                        continue;
                    }
                    for (Reward taggedReward : rewardsTagged) {
                        MyUtils.print(taggedReward.toString());
                    }

                    MyUtils.pause();
                    break;

                case 9:
                    MyUtils.print("\nExiting Search!");
                    break;
                default:
                    MyUtils.print("\nThe option you are trying to select does not exist yet.");
                    break;
            }
        } while (option != 9);
    }
    public static void searchTreasures() {
        String[] searchRewardsMenu = new String[9];

        searchRewardsMenu[0] = "Search all Treasures";
        searchRewardsMenu[1] = "Find Treasures by Name";
        searchRewardsMenu[2] = "Find Treasures by ID";
        searchRewardsMenu[3] = "Search Treasures of Type";
        searchRewardsMenu[4] = "Search Treasures that have one Tag";
        searchRewardsMenu[8] = "Go back";

        int option;

        do {
            MyUtils.menuMaker("SEARCH MENU",
                    searchRewardsMenu);
            option = typeIndex();

            switch (option) {
                case 1:
                    MyUtils.print("\nSearching All Treasures:\n");

                    List<Treasure> treasures = SQLAccess.getAllTreasures();
                    if (treasures == null) {
                        MyUtils.print("No results to print");
                        continue;
                    }
                    for(Treasure treasured : treasures) {
                        MyUtils.print(treasured.toString());
                    }

                    MyUtils.pause();
                    break;

                case 2:
                    MyUtils.print("\nFinding Reward by Name:\n");

                    String name = typePrizeName();
                    Treasure treasure = SQLAccess.getTreasureByName(name);
                    showTreasure(treasure);

                    MyUtils.pause();
                    break;

                case 3:
                    MyUtils.print("\nFinding Rewards by ID:\n");

                    int  id = typeID();
                    Treasure treasureId = SQLAccess.getTreasureById(id);
                    showTreasure(treasureId);

                    MyUtils.pause();
                    break;

                case 4:
                    MyUtils.print("\nSearching Rewards of type:\n");

                    String type = typePrizeType();
                    List<Treasure> trList = SQLAccess.getTreasureByType(type);
                    if (trList == null) {
                        MyUtils.print("No results to print");
                        continue;
                    }
                    for (Treasure treasureFound : trList ) {
                        MyUtils.print(treasureFound.toString());
                    }

                    MyUtils.pause();
                    break;

                case 5:
                    MyUtils.print("\nSearching Rewards that have one Tag:\n");

                    Tag tag = SQLAccess.getTagById(selectTagByIdentification());
                    List<Treasure> treasureTagged = SQLAccess.getTreasuresByTag(tag);
                    if (treasureTagged == null) {
                        MyUtils.print("No results to print");
                        continue;
                    }
                    for (Treasure t : treasureTagged) {
                        MyUtils.print(t.toString());
                    }

                    MyUtils.pause();
                    break;

                case 9:
                    MyUtils.print("\nExiting Search!");
                    break;
                default:
                    MyUtils.print("\nThe option you are trying to select does not exist yet.");
                    break;
            }
        } while (option != 9);
    }

    //UPDATE SUBMENUS

    //ADD SUBMENUS
    public static void addMenu() {
        String[] addMenuOptions = new String[9];
        addMenuOptions[0] = "Add Tag";
        addMenuOptions[1] = "Add PC";
        addMenuOptions[2] = "Add NPC";
        addMenuOptions[3] = "Add Rewards";
        addMenuOptions[4] = "Add Treasures";
        addMenuOptions[8] = "Go back";

        int option;

        do {
            MyUtils.menuMaker("ADD MENU",
                    addMenuOptions);
            option = typeIndex();

            switch (option) {
                case 1:
                    buildTag();
                    break;
                case 2:
                    buildPC();
                    break;
                case 3:
                    buildNPC();
                    break;
                case 4:
                    buildRewards();
                    break;
                case 5:
                    buildTreasures();
                    break;
                case 9:
                    MyUtils.print("\nExiting Addition!");
                    break;
                default:
                    MyUtils.print("\nThe option you are trying to select does not exist yet.");
                    break;
            }
        } while (option != 9);
    }

    public static void buildTag() {
        Tag newTag;

        MyUtils.print("\nBuilding a new Tag\n");
        String name = typeName();
        String lore = typeDescription();

        newTag = new Tag(0, name, lore);

        boolean addition = SQLAccess.sqlAddTag(newTag) > 0;
        if (addition) {
            MyUtils.print("\nAdded with success\n");
        }
        else {
            MyUtils.print("\nFailed to add the object\n");
        }
        MyUtils.pause();
    }

    public static void buildPC() {
        Player_Character newPC;

        MyUtils.print("\nBuilding a new PC\n");
        String fullName = typeCharacterName();
        String characterName = typeName();
        String specie = typeSpecies();
        Gender gen = typeGender();
        Alignment alignment = typeAlignment();
        String playerName = typeUserID();
        Status currentStatus = typeStatus();
        String notes = typeNotes();

        newPC = new Player_Character(0, characterName, fullName, specie, gen, alignment, playerName, currentStatus, notes);

        boolean addition = SQLAccess.sqlAddPC(newPC) > 0;
        if (addition) {
            MyUtils.print("\nAdded with success\n");

            if (MyUtils.confirm("Do you want to add Class Levels this PC?")) {
                MyUtils.print("\nWait a few seconds to allow data to load\n");
                MyUtils.pause();
                int id = SQLAccess.getIDFromPCName(characterName);
                newPC = new Player_Character(id, characterName, fullName, specie, gen, alignment, playerName, currentStatus, notes);
                modifyClassLevel(newPC);
            }
        }
        else {
            MyUtils.print("\nFailed to add the object\n");
        }
        MyUtils.pause();
    }

    public static void buildNPC() {
        NPC newNPC;

        MyUtils.print("\nBuilding a new NPC\n");
        String fullName = typeCharacterName();
        String npcName = typeName();
        int cr = typeChallengeRating();
        String specie = typeSpecies();
        Gender gen = typeGender();
        Alignment alignment = typeAlignment();
        String dmName = typeUserID();
        Status currentStatus = typeStatus();
        String notes = typeNotes();

        newNPC = new NPC(0,npcName, fullName, cr, specie, gen, alignment, dmName, currentStatus, notes);

        boolean addition = SQLAccess.sqlAddNPC(newNPC) > 0;
        if (addition) {
            MyUtils.print("\nAdded with success\n");
        }
        else {
            MyUtils.print("\nFailed to add the object\n");
        }
        MyUtils.pause();
    }

    public static void buildRewards() {
        Reward newReward;

        MyUtils.print("\nBuilding a new Reward\n");
        String name = typePrizeName();
        String type = typePrizeType();
        int idPC = selectPCByIdentification();
        String txtDescription = typeDescription();
        String link = typeLink();

        newReward = new Reward(name, 0, type, idPC, txtDescription, link);

        boolean addition = SQLAccess.sqlAddReward(newReward) > 0;
        if (addition) {
            MyUtils.print("\nAdded with success\n");
        }
        else {
            MyUtils.print("\nFailed to add the object\n");
        }
        MyUtils.pause();
    }

    public static void buildTreasures() {
        Treasure newTreasure;

        MyUtils.print("\nBuilding a new Treasure\n");
        String name = typePrizeName();
        String type = typePrizeType();
        int idPC = selectNPCByIdentification();
        String txtDescription = typeDescription();
        boolean found = foundCheck();

        newTreasure = new Treasure(name, 0, type, idPC, txtDescription, found);

        boolean addition = SQLAccess.sqlAddTreasure(newTreasure) > 0;
        if (addition) {
            MyUtils.print("\nAdded with success\n");
        }
        else {
            MyUtils.print("\nFailed to add the object\n");
        }
        MyUtils.pause();
    }

    //DELETE SUBMENUS
    public static void deleteMenu() {
        String[] deleteMenuOptions = new String[9];
        deleteMenuOptions[0] = "Delete Tag";
        deleteMenuOptions[1] = "Delete PC";
        deleteMenuOptions[2] = "Delete NPC";
        deleteMenuOptions[3] = "Delete Rewards";
        deleteMenuOptions[4] = "Delete Treasures";
        deleteMenuOptions[8] = "Go back";

        int option;
        int deleting;

        do {
            MyUtils.menuMaker("DELETE MENU",
                    deleteMenuOptions);
            option = typeIndex();

            switch (option) {
                case 1:
                    MyUtils.print("\nDeleting Tag\n");

                    int tagID = selectTagByIdentification();
                    deleting = SQLAccess.deleteTagByID(tagID);

                    if (deleting > 0) {
                        MyUtils.print("\nDeleted successfully\n");
                    } else {
                        MyUtils.print("\nFailed to delete the item\n");
                    }
                    MyUtils.pause();
                    break;

                case 2:
                    MyUtils.print("\nDeleting PC\n");

                    int pcID = selectPCByIdentification();
                    deleting = SQLAccess.deletePCByID(pcID);

                    if (deleting > 0) {
                        MyUtils.print("\nDeleted successfully\n");
                    } else {
                        MyUtils.print("\nFailed to delete the item\n");
                    }
                    MyUtils.pause();
                    break;

                case 3:
                    MyUtils.print("Deleting NPC\n");

                    int npcID = selectNPCByIdentification();
                    deleting = SQLAccess.deleteNPCByID(npcID);

                    if (deleting > 0) {
                        MyUtils.print("\nDeleted successfully\n");
                    } else {
                        MyUtils.print("\nFailed to delete the item\n");
                    }
                    MyUtils.pause();
                    break;

                case 4:
                    MyUtils.print("Deleting Rewards\n");

                    int rewardID = selectRewardByIdentification();
                    deleting = SQLAccess.deleteRewardByID(rewardID);

                    if (deleting > 0) {
                        MyUtils.print("\nDeleted successfully\n");
                    } else {
                        MyUtils.print("\nFailed to delete the item\n");
                    }
                    MyUtils.pause();
                    break;

                case 5:
                    MyUtils.print("Deleting Treasures\n");
                    int treasureID = selectTreasureByIdentification();
                    deleting = SQLAccess.deleteTreasureByID(treasureID);

                    if (deleting > 0) {
                        MyUtils.print("\nDeleted successfully\n");
                    } else {
                        MyUtils.print("\nFailed to delete the item\n");
                    }
                    MyUtils.pause();
                    break;

                case 9:
                    MyUtils.print("\nExiting Addition!");
                    break;
                default:
                    MyUtils.print("\nThe option you are trying to select does not exist yet.");
                    break;
            }
        } while (option != 9);
    }

    //UPDATES
    public static void updateMenu() {
        String[] updateMenuOpts = new String[9];
        updateMenuOpts[0] = "Update Tag";
        updateMenuOpts[1] = "Update PC";
        updateMenuOpts[2] = "Update NPC";
        updateMenuOpts[3] = "Update Rewards";
        updateMenuOpts[4] = "Update Treasures";
        updateMenuOpts[8] = "Go back";

        int option;

        do {
            MyUtils.menuMaker("UPDATE MENU",
                    updateMenuOpts);
            option = typeIndex();

            switch (option) {
                case 1:
                    MyUtils.print("\nUpdating Tag\n");

                    int tagID = selectTagByIdentification();
                    Tag tagUpdate = SQLAccess.getTagById(tagID);
                    if (tagUpdate == null) {
                        continue;
                    }

                    String lore = typeDescription();
                    tagUpdate.setLore(lore);

                    if (SQLAccess.updateTag(tagUpdate) > 0) {
                        MyUtils.print("\nUpdated successfully\n");
                    }
                    else {
                        MyUtils.print("\nFailed to update the item\n");
                    }

                    MyUtils.pause();
                    break;

                case 2:
                    MyUtils.print("\nUpdating PC (enter to skip editing old info)\n");

                    int pcID = selectPCByIdentification();
                    Player_Character pcUpdate = SQLAccess.getPlayerByID(pcID);
                    if (pcUpdate == null) {
                        continue;
                    }

                    String fullName = typeCharacterName();
                    String characterName = typeName();
                    String specie = typeSpecies();
                    Alignment alignment = typeAlignment();
                    Status currentStatus = typeStatus();
                    String notes = typeNotes();

                    if (!characterName.isEmpty()) {
                        pcUpdate.setFirstName(characterName);
                    }
                    if (!fullName.isEmpty()) {
                        pcUpdate.setFullName(fullName);
                    }
                    if (!specie.isEmpty()) {
                        pcUpdate.setSpecie(specie);
                    }
                    pcUpdate.setAlignment(alignment);
                    pcUpdate.setCurrentStatus(currentStatus);
                    if (notes.isEmpty()) {
                        pcUpdate.setNotes(notes);
                    }

                    if (MyUtils.confirm("Do you want to add Class Levels this PC?")) {
                        int id = SQLAccess.getIDFromPCName(characterName);
                            modifyClassLevel(pcUpdate);
                    }

                    if (SQLAccess.updatePC(pcUpdate) > 0) {
                        MyUtils.print("\nUpdated successfully\n");
                    }
                    else {
                        MyUtils.print("\nFailed to update the item\n");
                    }

                    MyUtils.pause();
                    break;

                case 3:
                    MyUtils.print("Updating NPC (enter to skip editing old info)\n");

                    int npcID = selectNPCByIdentification();
                    NPC npcUpdate = SQLAccess.getNPCByID(npcID);
                    if (npcUpdate == null) {
                        continue;
                    }

                    String fullName2 = typeCharacterName();
                    String characterName2 = typeName();
                    int cr = typeChallengeRating();
                    String specie2 = typeSpecies();
                    Alignment alignment2 = typeAlignment();
                    Status currentStatus2 = typeStatus();
                    String notes2 = typeNotes();

                    if (!characterName2.isEmpty()) {
                        npcUpdate.setFirstName(characterName2);
                    }
                    if (!fullName2.isEmpty()) {
                        npcUpdate.setFullName(fullName2);
                    }
                    if (!specie2.isEmpty()) {
                        npcUpdate.setSpecie(specie2);
                    }
                    npcUpdate.setAlignment(alignment2);
                    npcUpdate.setCurrentStatus(currentStatus2);
                    if (notes2.isEmpty()) {
                        npcUpdate.setNotes(notes2);
                    }
                    npcUpdate.setChallengeRating(cr);

                    if (SQLAccess.updateNPC(npcUpdate) > 0) {
                        MyUtils.print("\nUpdated successfully\n");
                    }
                    else {
                        MyUtils.print("\nFailed to update the item\n");
                    }

                    MyUtils.pause();
                    break;

                case 4:
                    MyUtils.print("Updating Rewards\n");

                    int rewardID = selectRewardByIdentification();
                    Reward rewardUpdate = SQLAccess.getRewardByID(rewardID);

                    rewardUpdate.setDescription(typeDescription());
                    rewardUpdate.setLink(typeLink());

                    if (SQLAccess.updateReward(rewardUpdate) > 0) {
                        MyUtils.print("\nUpdated successfully\n");
                    }
                    else {
                        MyUtils.print("\nFailed to update the item\n");
                    }

                    MyUtils.pause();
                    break;

                case 5:
                    MyUtils.print("Updating Treasures\n");
                    int treasureID = selectTreasureByIdentification();
                    Treasure treasureUpdate = SQLAccess.getTreasureById(treasureID);

                    treasureUpdate.setDescription(typeDescription());
                    treasureUpdate.setFound(foundCheck());

                    if (SQLAccess.updateTreasure(treasureUpdate) > 0) {
                        MyUtils.print("\nUpdated successfully\n");
                    }
                    else {
                        MyUtils.print("\nFailed to update the item\n");
                    }

                    MyUtils.pause();
                    break;

                case 9:
                    MyUtils.print("\nExiting Addition!");
                    break;
                default:
                    MyUtils.print("\nThe option you are trying to select does not exist yet.");
                    break;
            }
        } while (option != 9);
    }

    //MANAGE MENU
    public static void manageMenu() {
        String[] manageMenuOpts = new String[9];
        manageMenuOpts[0] = "Assign Tag to item";
        manageMenuOpts[1] = "Give Reward to PC";
        manageMenuOpts[2] = "Remove Reward to PC";
        manageMenuOpts[3] = "Give Treasure to NPC";
        manageMenuOpts[4] = "Remove Treasure to NPC";
        manageMenuOpts[8] = "Go back";

        int option;

        do {
            MyUtils.menuMaker("MANAGE MENU",
                    manageMenuOpts);
            option = typeIndex();

            switch (option) {
                case 1:
                    MyUtils.print("\nManaging Tag\n");

                    int tagID = selectTagByIdentification();
                    Tag tag = SQLAccess.getTagById(tagID);

                    if (tag == null) {
                        MyUtils.print("Not found");
                        continue;
                    }

                    option = MyUtils.insertValidInt("Choose an option to add the  (1 - PC, 2 - NPC, 3 - Reward, 4 - Treasure)",
                            "That's not a Number",
                                    1,4);
                    int id;

                    switch (option) {
                        case 1:
                            id = selectPCByIdentification();
                            SQLAccess.sqlApplyTag("PC",tag.getId(), id);
                            break;
                        case 2:
                            id = selectNPCByIdentification();
                            SQLAccess.sqlApplyTag("NPC",tag.getId(), id);
                            break;
                        case 3:
                            id = selectRewardByIdentification();
                            SQLAccess.sqlApplyTag("Reward",tag.getId(), id);
                            break;
                        case 4:
                            id = selectTreasureByIdentification();
                            SQLAccess.sqlApplyTag("Treasure",tag.getId(), id);
                            break;
                    }

                    MyUtils.pause();
                    break;

                case 2:
                    MyUtils.print("\nGiving PC Reward\n");

                    int idPCgive = selectPCByIdentification();
                    int idRewardGive = selectRewardByIdentification();

                    SQLAccess.giveRewardPC(idPCgive, idRewardGive);

                    MyUtils.pause();
                    break;

                case 3:
                    MyUtils.print("\nRemoving PC Reward\n");

                    int idPCtake = selectPCByIdentification();
                    int idRewardtake = selectRewardByIdentification();

                    SQLAccess.removeRewardPC(idPCtake, idRewardtake);

                    MyUtils.pause();
                    break;

                case 4:
                    MyUtils.print("\nGiving NPC loot\n");

                    int idNPCgive = selectPCByIdentification();
                    int idTrGive = selectRewardByIdentification();

                    SQLAccess.giveRewardPC(idNPCgive, idTrGive);

                    MyUtils.pause();
                    break;

                case 5:
                    int idNPCtake = selectPCByIdentification();
                    int idTrtake = selectRewardByIdentification();

                    SQLAccess.giveRewardPC(idNPCtake, idTrtake);

                    MyUtils.pause();
                    break;

                case 9:
                    MyUtils.print("\nExiting Addition!");
                    break;
                default:
                    MyUtils.print("\nThe option you are trying to select does not exist yet.");
                    break;
            }
        } while (option != 9);
    }
}