package manager.controlSQL;

import manager.enums.Alignment;
import manager.enums.Classes;
import manager.enums.Gender;
import manager.enums.Status;
import manager.objects.*;
import manager.tools.MyUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SQLAccess {

    //Obtain PC/NPC
    public static List<NPC> getEverythingAboutNPCs() {
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

                NPC newNPC = new NPC(id,first_name,full_name,cr,specie,gender,alignment,dm,status,notes);
                npcs.add(newNPC);

                Set<Tag> tagsNPC = new HashSet<>(getTagsFromPC(connection, id));
                newNPC.setTags(tagsNPC);
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return npcs;
    }

    public static List<Player_Character> getEverythingAboutPCs() {
        List<Player_Character> pcs = new LinkedList<>();

        String sql = "SELECT * FROM player_character";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            ResultSet resultCharacter = statement.executeQuery();

            while (resultCharacter.next()) {

                int  id = resultCharacter.getInt("id");
                String first_name = resultCharacter.getString("first_name");
                String full_name = resultCharacter.getString("full_name");
                String specie = resultCharacter.getString("specie");
                Gender gender = Gender.valueOf(resultCharacter.getString("gender"));
                Alignment alignment = Alignment.valueOf(resultCharacter.getString("alignment"));
                String player =  resultCharacter.getString("player_name");
                Status status = Status.valueOf(resultCharacter.getString("current_status"));
                String notes  = resultCharacter.getString("notes");

                Player_Character newPC = new Player_Character(id,first_name,full_name,specie,gender,alignment,player,status,notes);

                pcs.add(newPC);

                Set<Tag> tagsPC = new HashSet<>(getTagsFromPC(connection, id));
                newPC.setTags(tagsPC);

            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return pcs;
    }

    //Obtain Tags
    private static List<Tag> getTagsOfIdSearch(Connection connection, int id, String sql) {
        List<Tag> allTags = new LinkedList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, id);
            ResultSet resultTags = statement.executeQuery();

            while (resultTags.next()) {
                int idTag = resultTags.getInt("id");
                String tag = resultTags.getString("tag");
                String lore =  resultTags.getString("lore");

                allTags.add(new Tag(idTag,tag,lore));
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return allTags;
    }

    public static List<Tag> getAllTags() {
        List<Tag> allTags = new LinkedList<>();

        String sql = "SELECT * FROM tag";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            ResultSet resultTags = statement.executeQuery();

            while (resultTags.next()) {
                int idTag = resultTags.getInt("id");
                String tag = resultTags.getString("tag");
                String lore =  resultTags.getString("lore");

                allTags.add(new Tag(idTag,tag,lore));
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return allTags;
    }

     public static List<Tag> getTagsFromPC(Connection connection, int pcId) {
        List<Tag> allTags = new LinkedList<>();

        String sql = "SELECT t.* FROM tag t JOIN tag_character tc ON tc.idTag = t.id JOIN player_character pc ON pc.id = tc.idPC WHERE pc.id = ?";

         return getTagsOfIdSearch(connection, pcId, sql);
     }

    public static List<Tag> getTagsFromNPC(Connection connection, int npcId) {
        List<Tag> allTags = new LinkedList<>();

        String sql = "SELECT t.* FROM tag t JOIN tag_npc tn ON tn.idTag = t.id JOIN non_player_character npc ON npc.id = tn.idNPC WHERE npc.id = ?";

        return getTagsOfIdSearch(connection, npcId, sql);
    }

    public static List<Tag> getTagsFromReward(Connection connection, int rewardId) {
        List<Tag> allTags = new LinkedList<>();

        String sql = "SELECT t.* FROM tag t JOIN tag_reward tr ON tr.idTag = t.id JOIN reward r ON r.id = tr.idReward WHERE r.id = ?";

        return getTagsOfIdSearch(connection, rewardId, sql);
    }

    public static List<Tag> getTagsFromTreasure(Connection connection, int treasureId) {
        List<Tag> allTags = new LinkedList<>();

        String sql = "SELECT t.* FROM tag t JOIN tag_treasure tt ON tt.idTag = t.id JOIN treasure tr ON tr.id = tt.idReward WHERE tr.id = ?";

        return getTagsOfIdSearch(connection, treasureId, sql);
    }

    //Obtain Classes
    public static List<ClassLevels> listOfLevelsFromPC(Connection connection, int pcId) {
        List<ClassLevels> allLevels = new LinkedList<>();

        String sql = "SELECT * FROM class_instance WHERE idPC = ? ORDER BY levels";

        try (PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, pcId);
            ResultSet resultLevels = statement.executeQuery();

            while (resultLevels.next()) {
                Classes className = Classes.valueOf(resultLevels.getString("className"));
                int level =  resultLevels.getInt("levels");
                String subclass =  resultLevels.getString("subclass");

                allLevels.add(new ClassLevels(className,level,subclass));
            }


        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return allLevels;
    }

    //Obtain Treasure
    public static List<Treasure> getAllTreasures() {
        List<Treasure> allTreasures = new LinkedList<>();

        String sql = "SELECT * FROM treasure";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {


            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return allTreasures;
    }
}
