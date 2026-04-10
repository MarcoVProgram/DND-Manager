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
        menu[6] = "Manage ";
        menu[7] = "Export all Database into File";
        menu[8] = "Exit Program";

        char option;

        do {

            MyUtils.menuMaker("MAIN MENU",
                    menu,
                    "Please, Insert one option: ");

            option = MyUtils.insertValidString("Insert one index option",
                    Patterns.ONLY_NUMBERS,
                    "The input introduced is not a number").charAt(0);

            switch (option) {

                //Search Info
                case '1':
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
        } while (option != '9');
    }
}