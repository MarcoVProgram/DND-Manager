package manager.tools;

import manager.exceptions.IncorrectInputException;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtils {

    public static void print(String text) {

        System.out.println(text);
    }

    public static void printHere(String text) {

        System.out.print(text);
    }

    public static void pause() {

        Scanner in = new Scanner(System.in);
        print("=========================================");
        print("       Press enter to continue...");
        print("=========================================");
        in.nextLine();
    }

    public static void menuMaker(String title, String[] options) {

        String menu = "\n"+ title + "\n";

        for (int i = 0; i < options.length; i++) {
            if (options[i] != null) {
                menu += i + 1 + ". " + options[i] + "\n";
            }
        }

        print(menu);
    }


    public static String inputRequest(String pattern) throws IncorrectInputException {

        Scanner in = new Scanner(System.in);
        String input;
        Pattern p = Pattern.compile(pattern);
        Matcher matcher;

        input = in.nextLine();
        matcher = p.matcher(input);

        if (!matcher.matches()) {
            throw new IncorrectInputException(input);
        }

        return input;
    }

    public static LocalDate requestBirthday() {

        final String yearPattern = "[0-9]{4}";//4-digit number
        final String monthPattern = "[0-9]{1,2}";//1 or 2 digit number
        final String dayPattern = "[0-9]{1,2}";//1 or 2 digit number

        String yearDate, monthDate, dayDate;
        boolean isDayNotValid;

        //Insertion year
        do {

            try {

                printHere("\nSelect Year of Birth: ");
                yearDate = inputRequest(yearPattern);

                if (Integer.parseInt(yearDate) < 1900) {
                    print("Year out of range, it must be at least higher than 1900, try again:");
                }

            } catch (IncorrectInputException e) {

                print(e.getMessage());
                yearDate = "0";
                print("You must provide at least a 4 digit number that is higher than 1900");
            }
        } while (Integer.parseInt(yearDate) < 1900);

        //Insertion month
        do {

            try {

                printHere("\nSelect Month of Birth: ");
                monthDate = inputRequest(monthPattern);

                if (Integer.parseInt(monthDate) < 1 || Integer.parseInt(monthDate) > 12) {
                    print("The month range is invalid, you must choose a month between 1 and 12, try again:");
                }

            } catch (IncorrectInputException e) {

                print(e.getMessage());
                monthDate = "0";
                print("You must insert a number between 1 and 12 that corresponds to a month");
            }
        } while (Integer.parseInt(monthDate) < 1 || Integer.parseInt(monthDate) > 12);

        //Insert Day
        do {

            try {

                printHere("\nSelect Day of Birth: ");
                dayDate = inputRequest(dayPattern);

                //Day checker
                switch (Integer.parseInt(monthDate)) {

                    //Case months with 31 days
                    case 1, 3, 5, 7, 8, 10, 12:
                        isDayNotValid = Integer.parseInt(dayDate) < 1 || Integer.parseInt(dayDate) > 31;
                        break;

                    //Case months with 30 days
                    case 4, 6, 9, 11:

                        isDayNotValid = Integer.parseInt(dayDate) < 1 || Integer.parseInt(dayDate) > 30;
                        break;

                    //Case months with 28 or 29 days, counting leap years
                    case 2:

                        isDayNotValid = Integer.parseInt(dayDate) < 1 || Integer.parseInt(dayDate) > 28;

                        if (Year.isLeap(Long.parseLong(yearDate))) {
                            isDayNotValid = Integer.parseInt(dayDate) < 1 || Integer.parseInt(dayDate) > 29;
                        }
                        break;

                    default:
                        throw new IncorrectInputException(dayDate);
                }

                if (isDayNotValid) {
                    print("The range of the day is not valid, you must choose a number that fits within the days of your selected month" +
                            " (Month chosen: " + Integer.parseInt(monthDate) + "):");
                }

            } catch (IncorrectInputException e) {

                print(e.getMessage());
                dayDate = "0";
                isDayNotValid = true;
                print("You must insert a 1 or 2 digit number that corresponds to a day of the month (Month chosen: " + Integer.parseInt(monthDate) + ")");
            }
        } while (isDayNotValid);

        LocalDate dt = LocalDate.of(Integer.parseInt(yearDate), Integer.parseInt(monthDate), Integer.parseInt(dayDate));

        return dt;
    }

    public static LocalDate requestDate() {

        final String yearPattern = "[0-9]{4}";//4-digit number
        final String monthPattern = "[0-9]{1,2}";//1 or 2 digit number
        final String dayPattern = "[0-9]{1,2}";//1 or 2 digit number

        String yearDate, monthDate, dayDate;
        boolean isDayNotValid;

        //Insert Year
        do {

            try {

                printHere("\nSelect Year: ");
                yearDate = inputRequest(yearPattern);

            } catch (IncorrectInputException e) {

                print(e.getMessage());
                yearDate = "0";
                print("The number provided must be of 4 digits and above 1000");
            }
        } while (Integer.parseInt(yearDate) < 1000);

        //Insert Month
        do {

            try {

                printHere("\nSelect Month of Year: ");
                monthDate = inputRequest(monthPattern);

                if (Integer.parseInt(monthDate) < 1 || Integer.parseInt(monthDate) > 12) {
                    print("The month range is invalid, you must choose a month between 1 and 12, try again:");
                }

            } catch (IncorrectInputException e) {

                print(e.getMessage());
                monthDate = "0";
                print("You must insert a number between 1 and 12 that corresponds to a month");
            }
        } while (Integer.parseInt(monthDate) < 1 || Integer.parseInt(monthDate) > 12);

        //Insert Day
        do {

            try {

                printHere("\nSelect Day of Month: ");
                dayDate = inputRequest(dayPattern);

                //Day checker
                switch (Integer.parseInt(monthDate)) {

                    //Case months with 31 days
                    case 1, 3, 5, 7, 8, 10, 12:
                        isDayNotValid = Integer.parseInt(dayDate) < 1 || Integer.parseInt(dayDate) > 31;
                        break;

                    //Case months with 30 days
                    case 4, 6, 9, 11:

                        isDayNotValid = Integer.parseInt(dayDate) < 1 || Integer.parseInt(dayDate) > 30;
                        break;

                    //Case months with 28 or 29 days, counting leap years
                    case 2:

                        isDayNotValid = Integer.parseInt(dayDate) < 1 || Integer.parseInt(dayDate) > 28;

                        if (Year.isLeap(Long.parseLong(yearDate))) {
                            isDayNotValid = Integer.parseInt(dayDate) < 1 || Integer.parseInt(dayDate) > 29;
                        }
                        break;

                    default:
                        throw new IncorrectInputException(dayDate);
                }

                if (isDayNotValid) {
                    print("The range of the day is not valid, you must choose a number that fits within the days of your selected month" +
                            " (Month chosen: " + Integer.parseInt(monthDate) + "):");
                }

            } catch (IncorrectInputException e) {

                print(e.getMessage());
                dayDate = "0";
                isDayNotValid = true;
                print("You must insert a 1 or 2 digit number that corresponds to a day of the month (Month chosen: " + Integer.parseInt(monthDate) + ")");
            }
        } while (isDayNotValid);

        LocalDate dt = LocalDate.of(Integer.parseInt(yearDate), Integer.parseInt(monthDate), Integer.parseInt(dayDate));

        return dt;
    }

    public static String formatDate(String pattern, Object date) {
        String formattedResult = "N/A";

        if (date != null && date instanceof TemporalAccessor) {
            formattedResult = DateTimeFormatter.ofPattern(pattern).format((TemporalAccessor) date);
        }

        return formattedResult;
    }

    public static String insertValidString(String query, String pattern, String errorMsg) {
        String response;
        boolean valid = false;

        do {
            try {

                MyUtils.printHere(query + ": ");
                response = MyUtils.inputRequest(pattern);
                valid = true;

            }  catch (IncorrectInputException e) {

                MyUtils.print(errorMsg);
                response = "";

            }
        } while (!valid);

        return response;
    }

    public static double insertValidDouble(String query, String errorMsg) {
        double response;
        boolean valid = false;
        Scanner in = new Scanner(System.in);

        do {
            try {

                MyUtils.printHere(query + ": ");
                in = new Scanner(System.in);
                response = in.nextDouble();
                valid = true;

            }  catch (InputMismatchException e) {

                MyUtils.print(errorMsg);
                response = 0.0;
                in.nextLine();

            }
        } while (!valid);

        return response;
    }

    public static double insertValidDouble(String query, String errorMsg, double decimalDigits) {
        double response;
        boolean valid = false;
        Scanner in = new Scanner(System.in);

        do {
            try {

                MyUtils.printHere(query + ": ");
                in = new Scanner(System.in);
                response = in.nextDouble();
                response = Math.floor(response * Math.pow(10,decimalDigits))/Math.pow(10,decimalDigits);
                valid = true;

            }  catch (InputMismatchException e) {

                MyUtils.print(errorMsg);
                response = 0.0;
                in.nextLine();

            }
        } while (!valid);

        return response;
    }

    public static int insertValidInt(String query, String errorMsg) {
        int response;
        boolean valid = false;
        Scanner in = new Scanner(System.in);

        do {
            try {

                MyUtils.printHere(query + ": ");
                in = new Scanner(System.in);
                response = in.nextInt();
                valid = true;

            }  catch (InputMismatchException e) {

                MyUtils.print(errorMsg);
                response = 0;
                in.nextLine();

            }
        } while (!valid);

        return response;
    }

    public static int insertValidInt(String query, String errorMsg, int minRange, int maxRange) {
        int response;
        boolean valid = false;
        Scanner in = new Scanner(System.in);

        do {
            try {

                MyUtils.printHere(query + " (It must be within this range: " + minRange + " - " + maxRange + "): ");
                in = new Scanner(System.in);
                response = in.nextInt();
                valid = response >= minRange && response <= maxRange;

            }  catch (InputMismatchException e) {

                MyUtils.print(errorMsg);
                response = 0;
                in.nextLine();

            }
        } while (!valid);

        return response;
    }

    public static int insertInt(String query, int inputDefault) {
        int response;
        Scanner in = new Scanner(System.in);

        try {

            MyUtils.printHere(query + " (Default Value: " + inputDefault + "): ");
            in = new Scanner(System.in);
            response = in.nextInt();

        }  catch (InputMismatchException e) {

            response = inputDefault;
            in.nextLine();

        }

        return response;
    }

    public static <T extends Enum<T>> T selectEnum(Class<T> enumClass) {
        T[] valuesEnum = enumClass.getEnumConstants();
        T selection = valuesEnum[0];

        MyUtils.print("\n" + enumClass.getSimpleName() + ":");
        for (T e : valuesEnum) {

            MyUtils.print(e.ordinal() + ". " + e.name());
        }

        int index = insertInt("Insert the index of an option", 0);

        for (T e : valuesEnum) {
            if (e.ordinal() == index) {
                selection = e;
            }
        }

        return selection;
    }

    public static boolean confirm(String question) {
        Scanner scanner  = new Scanner(System.in);

        MyUtils.printHere(question + " (Type 'Y' if so, or anything else to abort): ");

        if  (scanner.nextLine().equalsIgnoreCase("Y")) {
            return true;
        }

        return false;
    }
}
