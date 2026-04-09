package manager.controlSQL;

import manager.enums.Alignment;
import manager.enums.Gender;
import manager.enums.Status;
import manager.objects.NPC;
import manager.objects.Player_Character;
import manager.objects.Tag;
import manager.tools.MyUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SQLAccess {

    public static List<NPC> getNPCs() {
        List<NPC> npcs = new LinkedList<>();

        String sql = "SELECT * FROM non_player_character";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int  id = resultSet.getInt("id");
                String first_name = resultSet.getString("first_name");
                String full_name = resultSet.getString("full_name");
                int cr =  resultSet.getInt("challenge_rating");
                String specie = resultSet.getString("specie");
                Gender gender = Gender.valueOf(resultSet.getString("gender"));
                Alignment alignment = Alignment.valueOf(resultSet.getString("alignment"));
                String dm =  resultSet.getString("dm_name");
                Status status = Status.valueOf(resultSet.getString("current_status"));
                String notes  = resultSet.getString("notes");

                npcs.add(new NPC(id,first_name,full_name,cr,specie,gender,alignment,dm,status,notes));
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return npcs;
    }

    public static List<Player_Character> getPCs() {
        List<Player_Character> pcs = new LinkedList<>();

        String sql = "SELECT * FROM player_character";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int  id = resultSet.getInt("id");
                String first_name = resultSet.getString("first_name");
                String full_name = resultSet.getString("full_name");
                int cr =  resultSet.getInt("challenge_rating");
                String specie = resultSet.getString("specie");
                Gender gender = Gender.valueOf(resultSet.getString("gender"));
                Alignment alignment = Alignment.valueOf(resultSet.getString("alignment"));
                String dm =  resultSet.getString("dm_name");
                Status status = Status.valueOf(resultSet.getString("current_status"));
                String notes  = resultSet.getString("notes");

                pcs.add(new Player_Character(id,first_name,full_name,specie,gender,alignment,dm,status,notes));
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return pcs;
    }

    public static List<Tag> getAllTags() {
        List<Tag> allTags = new LinkedList<>();

        String sql = "SELECT * FROM tag";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {


            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return allTags;
    }
}
