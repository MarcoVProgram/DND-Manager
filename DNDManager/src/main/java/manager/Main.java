package manager;

import manager.tools.MyUtils;
import manager.tools.Patterns;

public class Main {
    public static void main(String[] args) {

        MyUtils.print("Welcome to DnD Manager!");

        String[] menu = new String[9];
        menu[0] = "Search Information";
        menu[1] = "Update Information";
        menu[2] = "Add a new element";
        menu[3] = "Delete an element";
        menu[5] = "Manage PC or NPC";
        menu[6] = "Assign Rewards";
        menu[7] = "Export all Database into File";
        menu[8] = "Exit Program";

        int option;

        do {

            MyUtils.menuMaker("MAIN MENU",
                    menu);

            option = MyUtils.insertValidInt("Insert one index option",
                    Patterns.ONLY_NUMBERS);

            switch (option) {

                //Search Info
                case '1':
                    searchMenu();
                    break;

                //Update Info
                case '2':
                    break;

                //Add Info
                case '3':
                    break;

                //Delete Info
                case '4':
                    break;

                //Exit Program
                case '9':
                    MyUtils.print("\nGoodbye!");
                    break;

                //Error invalid option
                default:
                    MyUtils.print("\nThe option you are trying to select does not exist yet.");
                    break;
            }
        } while (option != 9);
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
            option = MyUtils.insertValidInt("Insert one index option",
                    Patterns.ONLY_NUMBERS);

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
        searchTagMenu[4] = "Search Unused Tags";
        searchTagMenu[8] = "Go back";

        int option;

        do {
            MyUtils.menuMaker("SEARCH MENU",
                    searchTagMenu);
            option = MyUtils.insertValidInt("Insert one index option",
                    Patterns.ONLY_NUMBERS);

            switch (option) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
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

        String[] searchPCMenu = new String[10];

        searchPCMenu[0] = "Search all PCs";
        searchPCMenu[1] = "Find PC by Name";
        searchPCMenu[2] = "Find PC by ID";
        searchPCMenu[3] = "Find PC with most Rewards";
        searchPCMenu[4] = "Search PCs inside Level Range";
        searchPCMenu[5] = "Search PCs that have one Specific Reward";
        searchPCMenu[6] = "Search PCs that have one Type of Reward";
        searchPCMenu[7] = "Search PCs that have one Tag";
        searchPCMenu[8] = "Search PCs from a Player";
        searchPCMenu[9] = "Go back";

        int option;

        do {
            MyUtils.menuMaker("SEARCH MENU",
                    searchPCMenu);
            option = MyUtils.insertValidInt("Insert one index option",
                    Patterns.ONLY_NUMBERS);

            switch (option) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 10:
                    MyUtils.print("\nExiting Search!");
                    break;
                default:
                    MyUtils.print("\nThe option you are trying to select does not exist yet.");
                    break;
            }
        } while (option != 10);
    }

    public static void searchNPCs() {
        String[] searchNPCMenu = new String[11];

        searchNPCMenu[0] = "Search all NPCs";
        searchNPCMenu[1] = "Find NPC by Name";
        searchNPCMenu[2] = "Find NPC by ID";
        searchNPCMenu[3] = "Search NPCs with Unclaimed Treasures";
        searchNPCMenu[4] = "Search NPCs that have one Tag";
        searchNPCMenu[5] = "Search NPCs in a CR range";
        searchNPCMenu[8] = "Search NPCs by alignment";
        searchNPCMenu[9] = "Search NPCs by status";
        searchNPCMenu[10] = "Go back";

        int option;

        do {
            MyUtils.menuMaker("SEARCH MENU",
                    searchNPCMenu);
            option = MyUtils.insertValidInt("Insert one index option",
                    Patterns.ONLY_NUMBERS);

            switch (option) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 11:
                    MyUtils.print("\nExiting Search!");
                    break;
                default:
                    MyUtils.print("\nThe option you are trying to select does not exist yet.");
                    break;
            }
        } while (option != 11);
    }
    public static void searchRewards() {
        String[] searchRewardsMenu = new String[9];

        searchRewardsMenu[0] = "Search all Rewards";
        searchRewardsMenu[1] = "Find Rewards by Name";
        searchRewardsMenu[2] = "Find Rewards by ID";
        searchRewardsMenu[3] = "Search Rewards of Type";
        searchRewardsMenu[4] = "Search Rewards that have one Tag";
        searchRewardsMenu[5] = "Search Rewards that are not assigned";
        searchRewardsMenu[6] = "Search Rewards that were once Treasure";
        searchRewardsMenu[8] = "Go back";

        int option;

        do {
            MyUtils.menuMaker("SEARCH MENU",
                    searchRewardsMenu);
            option = MyUtils.insertValidInt("Insert one index option",
                    Patterns.ONLY_NUMBERS);

            switch (option) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
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
        searchRewardsMenu[5] = "Search Treasures that were found";
        searchRewardsMenu[6] = "Search Treasures chgn8sygfzuxcbzygwith no NPC assigned";
        searchRewardsMenu[8] = "Go back";

        int option;

        do {
            MyUtils.menuMaker("SEARCH MENU",
                    searchRewardsMenu);
            option = MyUtils.insertValidInt("Insert one index option",
                    Patterns.ONLY_NUMBERS);

            switch (option) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
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
}