package manager.controlSQL;

import manager.tools.MyUtils;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDataManager {

    private static String DRIVER;
    private static String URL;
    private static String SCHEMA;
    private static String USUARIO;
    private static String CLAVE;

    public static Connection getConnection() {
        Connection conn = null;

        if (DRIVER == null || URL == null || SCHEMA == null || USUARIO == null || CLAVE == null) {
            readConnection();
        }

        try {

            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL + SCHEMA, USUARIO, CLAVE);

        } catch (ClassNotFoundException e) {
            System.err.println("Driver Error: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }

        return conn;
    }

    public static void readConnection() {
        final String path = ".\\src\\main\\java\\manager\\config\\";
        final String file = "config.dat";

        try (FileReader fr = new FileReader(path + file);
             BufferedReader br = new BufferedReader(fr)) {

            DRIVER = br.readLine();
            URL = br.readLine();
            SCHEMA = br.readLine();
            USUARIO = br.readLine();
            CLAVE = br.readLine();


        } catch (EOFException e) {
            MyUtils.print("End of File read");
        } catch (FileNotFoundException e) {
            MyUtils.print("The route provided couldn't be found, attempting creation of config file:");

            try {
                File f = new File(path + file);
                if(f.createNewFile()) {
                    MyUtils.print("The config file has been created: " + f);
                    MyUtils.print("END THE PROGRAM and insert the configuration data");
                    MyUtils.pause();
                }

            } catch (IOException ex) {
                MyUtils.print("Error at I/O; " + ex.getMessage());
            }

        } catch (IOException e) {
            MyUtils.print("Error at I/O");
        }

        if (DRIVER == null || URL == null || SCHEMA == null || USUARIO == null || CLAVE == null) {
            MyUtils.print("============================================================");
            MyUtils.print("ERROR WHILST READING THE CONFIG FILE - INCORRECT CONFIG FILE");
            MyUtils.print("============================================================");
        }
    }
}
