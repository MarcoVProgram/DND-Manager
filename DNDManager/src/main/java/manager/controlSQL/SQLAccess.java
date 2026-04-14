package manager.controlSQL;

import manager.enums.Alignment;
import manager.enums.Classes;
import manager.enums.Gender;
import manager.enums.Status;
import manager.objects.*;
import manager.objects.abstractObjects.Tagged;
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

    //Add to Database
    public static int sqlAddPC(Player_Character pc) {
        int result = 0;

        String sql = "INSERT INTO player_character (first_name, full_name, specie, gender, alignment, player_name, current_status, notes)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, pc.getFirstName());
            statement.setString(2, pc.getFullName());
            statement.setString(3, pc.getSpecie());
            statement.setString(4, pc.getGender().name());
            statement.setString(5, pc.getAlignment().name());
            statement.setString(6, pc.getPlayerName());
            statement.setString(7, pc.getCurrentStatus().name());
            statement.setString(8, pc.getNotes());

            result = statement.executeUpdate();


        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return result;
    }

    public static int sqlAddNPC(NPC npc) {
        int result = 0;

        String sql = "INSERT INTO non_player_character (first_name, full_name, challenge_rating, specie, gender, alignment, dm_name, current_status, notes)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, npc.getFirstName());
            statement.setString(2, npc.getFullName());
            statement.setInt(3, npc.getChallengeRating());
            statement.setString(4, npc.getSpecie());
            statement.setString(5, npc.getGender().name());
            statement.setString(6, npc.getAlignment().name());
            statement.setString(7, npc.getDmName());
            statement.setString(8, npc.getCurrentStatus().name());
            statement.setString(9, npc.getNotes());

            result = statement.executeUpdate();


        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return result;
    }

    public static int sqlAddTag(Tag tag) {
        int result = 0;

        String sql = "INSERT INTO tag (tag, lore)" +
                "VALUES (?, ?)";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, tag.getTag());
            statement.setString(2, tag.getLore());

            result = statement.executeUpdate();


        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return result;
    }

    public static int sqlAddReward(Reward reward) {
        int result = 0;

        String sql = "INSERT INTO reward (nameReward, typeReward, idPC, textDescription, linkRewards)" +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, reward.getName());
            statement.setString(2, reward.getType());
            statement.setInt(3, reward.getIdPC());
            statement.setString(4, reward.getDescription());
            statement.setString(5, reward.getLink());

            result = statement.executeUpdate();


        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return result;
    }

    public static int sqlAddTreasure(Treasure treasure) {
        int result = 0;

        String sql = "INSERT INTO treasure (nameTreasure, typeTreasure, idNPC, textDescription, hasBeenFound)" +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, treasure.getName());
            statement.setString(2, treasure.getType());
            statement.setInt(3, treasure.getIdNPC());
            statement.setString(4, treasure.getDescription());
            statement.setBoolean(5, treasure.hasBeenFound());

            result = statement.executeUpdate();


        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return result;
    }

    public static int sqlAddClassInstance(int idPC, ClassLevels instance) {
        int result = 0;

        String sql = "INSERT INTO class_instance (className, idPC, levels, subclass) VALUES (?, ?, ?, ?)";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, instance.getClassName().name());
            statement.setInt(2, idPC);
            statement.setInt(3, instance.getLevel());
            statement.setString(4, instance.getSubclass());

            result = statement.executeUpdate();


        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return result;
    }

    //Apply Tags
    public static int sqlApplyTag(String choice, int idTag, int idTagged) {
        int result = 0;
        String sql;

        switch (choice) {
            case "PC":
                sql = "INSERT INTO tag_character VALUES (?, ?)";
                break;
            case "NPC":
                sql = "INSERT INTO tag_npc VALUES (?, ?)";
                break;
            case "Reward":
                sql = "INSERT INTO tag_reward VALUES (?, ?)";
                break;
            case "Treasure":
                sql = "INSERT INTO tag_treasure VALUES (?, ?)";
                break;
            default:
                sql = "";
                break;
        }

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, idTag);
            statement.setInt(2, idTagged);

            result = statement.executeUpdate();


        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return result;
    }

    //Updates
    public static int updateClassInstance(int idPC, ClassLevels instance) {
        int result = 0;

        String sql = "UPDATE class_instance SET levels = ?, subclass = ? WHERE idPC = ? AND className = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, instance.getLevel());
            statement.setString(2, instance.getSubclass());

            statement.setInt(3, idPC);
            statement.setString(4, instance.getClassName().name());


            result = statement.executeUpdate();


        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return result;
    }

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

                getNPCAttributes(newNPC, connection, id);
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

                getPCAttributes(newPC, connection, id);
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return pcs;
    }

    public static List<Player_Character> getPCsFromUser(String user) {
        List<Player_Character> pcs = new LinkedList<>();

        String sql = "SELECT * FROM player_character WHERE player_name = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, user);

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

                getPCAttributes(newPC, connection, id);
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return pcs;
    }

    public static List<NPC> getNPCsFromDM(String dm_name) {
        List<NPC> npcs = new LinkedList<>();

        String sql = "SELECT * FROM non_player_character WHERE dm_name = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, dm_name);

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

                getNPCAttributes(newNPC, connection, id);
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return npcs;
    }

    public static Player_Character getPlayerByName(String name) {
        Player_Character newPC = null;

        String sql = "SELECT * FROM player_character WHERE full_name = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, name);

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

                newPC = new Player_Character(id,first_name,full_name,specie,gender,alignment,player,status,notes);

                getPCAttributes(newPC, connection, id);
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return newPC;
    }

    public static NPC getNPCByName(String name) {
        NPC newNPC = null;

        String sql = "SELECT * FROM non_player_character WHERE full_name = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, name);

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

                newNPC = new NPC(id,first_name,full_name,cr,specie,gender,alignment,dm,status,notes);

                getNPCAttributes(newNPC, connection, id);
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return newNPC;
    }

    public static Player_Character getPlayerByID(int idGiven) {
        Player_Character newPC = null;

        String sql = "SELECT * FROM player_character WHERE id = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, idGiven);

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

                newPC = new Player_Character(id,first_name,full_name,specie,gender,alignment,player,status,notes);

                getPCAttributes(newPC, connection, id);
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return newPC;
    }

    public static NPC getNPCByID(int idGiven) {
        NPC newNPC = null;

        String sql = "SELECT * FROM non_player_character WHERE full_name = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, idGiven);

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

                newNPC = new NPC(id,first_name,full_name,cr,specie,gender,alignment,dm,status,notes);

                getNPCAttributes(newNPC, connection, id);
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return newNPC;
    }

    public static List<Player_Character> getPCsInLevelRange(int min, int max) {
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

                getPCAttributes(newPC, connection, id);

                if (min <= newPC.getTotalLevel() && newPC.getTotalLevel() <= max) {
                    pcs.add(newPC);
                }
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return pcs;
    }

    private static void getNPCAttributes(NPC newNPC, Connection connection, int id) {
        Set<Tag> tagsNPC = new HashSet<>(getTagsFromPC(connection, id));
        newNPC.setTags(tagsNPC);

        List<Treasure> hoard = getTreasureFromNPC(newNPC);
        for (Treasure loot : hoard) {
            newNPC.bestowLoot(loot);
        }
    }

    private static void getPCAttributes(Player_Character newPC, Connection connection, int id) {
        Set<Tag> tagsPC = new HashSet<>(getTagsFromPC(connection, id));
        newPC.setTags(tagsPC);

        List<ClassLevels> lvls = listOfLevelsFromPC(connection, id);
        for (ClassLevels lvl : lvls) {
            newPC.setClasses(lvl);
        }

        List<Reward> assets = getRewardsFromPC(newPC);
        for (Reward asset : assets) {
            newPC.giveAsset(asset);
        }
    }


    //Get IDs
    public static int getIDFromPCName(String fullName) {
        int id = -1;

        String sql = "SELECT p.id FROM player_character p WHERE p.full_name = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, fullName);

            ResultSet resultId = statement.executeQuery();

            while (resultId.next()) {

                id = resultId.getInt("id");

            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return id;
    }

    public static int getIDFromNPCName(String fullName) {
        int id = -1;

        String sql = "SELECT p.id FROM non_player_character p WHERE p.full_name = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, fullName);

            ResultSet resultId = statement.executeQuery();

            while (resultId.next()) {

                id = resultId.getInt("id");

            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return id;
    }

    public static int getIDFromTagName(String tagName) {
        int id = -1;

        String sql = "SELECT t.id FROM tag t WHERE t.tag = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, tagName);

            ResultSet resultId = statement.executeQuery();

            while (resultId.next()) {

                id = resultId.getInt("id");

            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return id;
    }

    public static int getIDFromRewardName(String nameReward) {
        int id = -1;

        String sql = "SELECT r.id FROM reward r WHERE r.nameReward = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, nameReward);

            ResultSet resultId = statement.executeQuery();

            while (resultId.next()) {

                id = resultId.getInt("id");

            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return id;
    }

    public static int getIDFromTreasureName(String nameTreasure) {
        int id = -1;

        String sql = "SELECT t.id FROM treasure t WHERE t.nameTreasure = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, nameTreasure);

            ResultSet resultId = statement.executeQuery();

            while (resultId.next()) {

                id = resultId.getInt("id");

            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return id;
    }

    //Get by Tag
    public static List<Player_Character> getPCsByTag(Tag tag) {
        List<Player_Character> pcs = new LinkedList<>();

        String sql = "SELECT v.* FROM tag t JOIN tag_character ti ON t.id = ti.idTag JOIN player_character v ON v.id = ti.idPC WHERE t.id = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, tag.getId());

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

                getPCAttributes(newPC, connection, id);
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return pcs;
    }

    public static List<NPC> getNPCsByTag(Tag tag) {
        List<NPC> npcs = new LinkedList<>();

        String sql = "SELECT v.* FROM tag t JOIN tag_npc ti ON t.id = ti.idTag JOIN non_player_character v ON v.id = ti.idNPC WHERE t.id = ?";;

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, tag.getId());

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

                getNPCAttributes(newNPC, connection, id);
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return npcs;
    }

    public static List<Treasure> getTreasuresByTag(Tag tag) {
        List<Treasure> allTreasures = new LinkedList<>();

        String sql = "SELECT v.* FROM tag t JOIN tag_treasure ti ON t.id = ti.idTag JOIN treasure v ON v.id = ti.idTreasure WHERE t.id = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, tag.getId());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String name = resultSet.getString("nameTreasure");
                int idTreasure = resultSet.getInt("id");
                String type =  resultSet.getString("typeTreasure");
                int idNPC = resultSet.getInt("idNPC");
                String desc = resultSet.getNString("textDescription");
                boolean found = resultSet.getBoolean("hasBeenFound");

                Treasure treasure = new Treasure(name, idTreasure, type, idNPC, desc, found);
                allTreasures.add(treasure);

                Set<Tag> tags = new HashSet<>(getTagsFromReward(connection, idTreasure));
                treasure.setTags(tags);
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return allTreasures;
    }

    public static List<Reward> getRewardsByTag(Tag tag) {
        List<Reward> allRewards = new LinkedList<>();

        String sql = "SELECT v.* FROM tag t JOIN tag_reward ti ON t.id = ti.idTag JOIN reward v ON v.id = ti.idReward WHERE t.id = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, tag.getId());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String name = resultSet.getString("nameReward");
                int idReward = resultSet.getInt("id");
                String type =  resultSet.getString("typeReward");
                int idPC = resultSet.getInt("idPC");
                String desc = resultSet.getNString("textDescription");
                String link = resultSet.getString("linkRewards");

                Reward reward = new Reward(name, idReward, type, idPC, desc, link);
                allRewards.add(reward);

                Set<Tag> tags = new HashSet<>(getTagsFromReward(connection, idReward));
                reward.setTags(tags);
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return allRewards;
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

    public static Tag getTagByName(String name) {
        Tag tagResult = null;

        String sql = "SELECT * FROM tag WHERE tag = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, name);

            ResultSet resultTags = statement.executeQuery();

            while (resultTags.next()) {
                int idTag = resultTags.getInt("id");
                String tag = resultTags.getString("tag");
                String lore =  resultTags.getString("lore");

                tagResult = new Tag(idTag,tag,lore);
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return tagResult;
    }

    public static Tag getTagById(int id) {
        Tag tagResult = null;

        String sql = "SELECT * FROM tag WHERE id = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, id);

            ResultSet resultTags = statement.executeQuery();

            while (resultTags.next()) {
                int idTag = resultTags.getInt("id");
                String tag = resultTags.getString("tag");
                String lore =  resultTags.getString("lore");

                tagResult = new Tag(idTag,tag,lore);
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return tagResult;
    }

    public static List<Tagged> getEverythingWithTag(Tag tag) {
        List<Tagged> taggeds = new LinkedList<>();

        taggeds.addAll(getPCsByTag(tag));
        taggeds.addAll(getNPCsByTag(tag));
        taggeds.addAll(getRewardsByTag(tag));
        taggeds.addAll(getTreasuresByTag(tag));

        return taggeds;
    }

    public static List<Tag> getTagsFromPC(Connection connection, int pcId) {
        List<Tag> allTags = new LinkedList<>();

        String sql = "SELECT DISTINCT t.* FROM tag t JOIN tag_character tc ON tc.idTag = t.id JOIN player_character pc ON pc.id = tc.idPC WHERE pc.id = ?";

        return getTagsOfIdSearch(connection, pcId, sql);
    }
    public static List<Tag> getTagsFromNPC(Connection connection, int npcId) {
        List<Tag> allTags = new LinkedList<>();

        String sql = "SELECT DISTINCT t.* FROM tag t JOIN tag_npc tn ON tn.idTag = t.id JOIN non_player_character npc ON npc.id = tn.idNPC WHERE npc.id = ?";

        return getTagsOfIdSearch(connection, npcId, sql);
    }

    public static List<Tag> getTagsFromReward(Connection connection, int rewardId) {
        List<Tag> allTags = new LinkedList<>();

        String sql = "SELECT DISTINCT t.* FROM tag t JOIN tag_reward tr ON tr.idTag = t.id JOIN reward r ON r.id = tr.idReward WHERE r.id = ?";

        return getTagsOfIdSearch(connection, rewardId, sql);
    }

    public static List<Tag> getTagsFromTreasure(Connection connection, int treasureId) {
        List<Tag> allTags = new LinkedList<>();

        String sql = "SELECT DISTINCT t.* FROM tag t JOIN tag_treasure tt ON tt.idTag = t.id JOIN treasure tr ON tr.id = tt.idReward WHERE tr.id = ?";

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

    //Obtain Treasure/Reward
    public static List<Treasure> getAllTreasures() {
        List<Treasure> allTreasures = new LinkedList<>();

        String sql = "SELECT * FROM treasure";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String name = resultSet.getString("nameTreasure");
                int idTreasure = resultSet.getInt("id");
                String type =  resultSet.getString("typeTreasure");
                int idNPC = resultSet.getInt("idNPC");
                String desc = resultSet.getNString("textDescription");
                boolean found = resultSet.getBoolean("hasBeenFound");

                Treasure treasure = new Treasure(name, idTreasure, type, idNPC, desc, found);
                allTreasures.add(treasure);

                Set<Tag> tagsPC = new HashSet<>(getTagsFromReward(connection, idTreasure));
                treasure.setTags(tagsPC);
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return allTreasures;
    }

    public static List<Reward> getAllRewards() {
        List<Reward> allRewards = new LinkedList<>();

        String sql = "SELECT * FROM reward";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String name = resultSet.getString("nameReward");
                int idReward = resultSet.getInt("id");
                String type =  resultSet.getString("typeReward");
                int idPC = resultSet.getInt("idPC");
                String desc = resultSet.getNString("textDescription");
                String link = resultSet.getString("linkRewards");

                Reward reward = new Reward(name, idReward, type, idPC, desc, link);
                allRewards.add(reward);

                Set<Tag> tagsPC = new HashSet<>(getTagsFromReward(connection, idReward));
                reward.setTags(tagsPC);
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return allRewards;
    }

    public static List<Reward> getRewardsFromPC(Player_Character pc) {
        List<Reward> allRewards = new LinkedList<>();

        String sql = "SELECT * FROM reward r WHERE r.idPC = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, pc.getId());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String name = resultSet.getString("nameReward");
                int idReward = resultSet.getInt("id");
                String type =  resultSet.getString("typeReward");
                int idPC = resultSet.getInt("idPC");
                String desc = resultSet.getNString("textDescription");
                String link = resultSet.getString("linkRewards");

                Reward reward = new Reward(name, idReward, type, idPC, desc, link);
                allRewards.add(reward);

                Set<Tag> tags = new HashSet<>(getTagsFromReward(connection, idReward));
                reward.setTags(tags);
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return allRewards;
    }

    public static List<Treasure> getTreasureFromNPC(NPC npc) {
        List<Treasure> allTreasures = new LinkedList<>();

        String sql = "SELECT * FROM treasure t WHERE t.idNPC = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, npc.getId());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String name = resultSet.getString("nameTreasure");
                int idTreasure = resultSet.getInt("id");
                String type =  resultSet.getString("typeTreasure");
                int idNPC = resultSet.getInt("idNPC");
                String desc = resultSet.getNString("textDescription");
                boolean found = resultSet.getBoolean("hasBeenFound");

                Treasure treasure = new Treasure(name, idTreasure, type, idNPC, desc, found);
                allTreasures.add(treasure);

                Set<Tag> tags = new HashSet<>(getTagsFromReward(connection, idTreasure));
                treasure.setTags(tags);
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return allTreasures;
    }

    public static List<Reward> getRewardsByType(String typeInput) {
        List<Reward> allRewards = new LinkedList<>();

        String sql = "SELECT * FROM reward r WHERE r.typeReward = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, typeInput);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String name = resultSet.getString("nameReward");
                int idReward = resultSet.getInt("id");
                String type =  resultSet.getString("typeReward");
                int idPC = resultSet.getInt("idPC");
                String desc = resultSet.getNString("textDescription");
                String link = resultSet.getString("linkRewards");

                Reward reward = new Reward(name, idReward, type, idPC, desc, link);
                allRewards.add(reward);

                Set<Tag> tags = new HashSet<>(getTagsFromReward(connection, idReward));
                reward.setTags(tags);
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return allRewards;
    }

    public static Reward getRewardByID(int idInput) {
        Reward newReward = null;

        String sql = "SELECT * FROM reward r WHERE r.id = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, idInput);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String name = resultSet.getString("nameReward");
                int idReward = resultSet.getInt("id");
                String type =  resultSet.getString("typeReward");
                int idPC = resultSet.getInt("idPC");
                String desc = resultSet.getNString("textDescription");
                String link = resultSet.getString("linkRewards");

                newReward = new Reward(name, idReward, type, idPC, desc, link);

                Set<Tag> tags = new HashSet<>(getTagsFromReward(connection, idReward));
                newReward.setTags(tags);
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return newReward;
    }

    public static Reward getRewardByName(String nameInput) {
        Reward newReward = null;

        String sql = "SELECT * FROM reward r WHERE r.nameReward = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, nameInput);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String name = resultSet.getString("nameReward");
                int idReward = resultSet.getInt("id");
                String type =  resultSet.getString("typeReward");
                int idPC = resultSet.getInt("idPC");
                String desc = resultSet.getNString("textDescription");
                String link = resultSet.getString("linkRewards");

                newReward = new Reward(name, idReward, type, idPC, desc, link);

                Set<Tag> tags = new HashSet<>(getTagsFromReward(connection, idReward));
                newReward.setTags(tags);
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return newReward;
    }

    public static List<Treasure> getTreasureByType(String typeInput) {
        List<Treasure> allTreasures = new LinkedList<>();

        String sql = "SELECT * FROM treasure t WHERE t.typeTreasure = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, typeInput);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String name = resultSet.getString("nameTreasure");
                int idTreasure = resultSet.getInt("id");
                String type =  resultSet.getString("typeTreasure");
                int idNPC = resultSet.getInt("idNPC");
                String desc = resultSet.getNString("textDescription");
                boolean found = resultSet.getBoolean("hasBeenFound");

                Treasure treasure = new Treasure(name, idTreasure, type, idNPC, desc, found);
                allTreasures.add(treasure);

                Set<Tag> tags = new HashSet<>(getTagsFromReward(connection, idTreasure));
                treasure.setTags(tags);
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return allTreasures;
    }

    public static Treasure getTreasureById(int id) {
        Treasure newTreasure = null;

        String sql = "SELECT * FROM treasure t WHERE t.id = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String name = resultSet.getString("nameTreasure");
                int idTreasure = resultSet.getInt("id");
                String type =  resultSet.getString("typeTreasure");
                int idNPC = resultSet.getInt("idNPC");
                String desc = resultSet.getNString("textDescription");
                boolean found = resultSet.getBoolean("hasBeenFound");

                newTreasure = new Treasure(name, idTreasure, type, idNPC, desc, found);

                Set<Tag> tags = new HashSet<>(getTagsFromReward(connection, idTreasure));
                newTreasure.setTags(tags);
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return newTreasure;
    }

    public static Treasure getTreasureByName(String nameTreasure) {
        Treasure newTreasure = null;

        String sql = "SELECT * FROM treasure t WHERE t.nameTreasure = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, nameTreasure);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String name = resultSet.getString("nameTreasure");
                int idTreasure = resultSet.getInt("id");
                String type =  resultSet.getString("typeTreasure");
                int idNPC = resultSet.getInt("idNPC");
                String desc = resultSet.getNString("textDescription");
                boolean found = resultSet.getBoolean("hasBeenFound");

                newTreasure = new Treasure(name, idTreasure, type, idNPC, desc, found);

                Set<Tag> tags = new HashSet<>(getTagsFromReward(connection, idTreasure));
                newTreasure.setTags(tags);
            }

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return newTreasure;
    }

    //DELETE
    public static int deleteTagByID(int id) {
        int result = 0;
        String sql = "DELETE FROM tag t WHERE t.id = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, id);

            result = statement.executeUpdate();

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return result;
    }

    public static int deletePCByID(int id) {
        int result = 0;
        String sql = "DELETE FROM player_character t WHERE t.id = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, id);

            result = statement.executeUpdate();

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return result;
    }

    public static int deleteNPCByID(int id) {
        int result = 0;
        String sql = "DELETE FROM non_player_character t WHERE t.id = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, id);

            result = statement.executeUpdate();

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return result;
    }

    public static int deleteRewardByID(int id) {
        int result = 0;
        String sql = "DELETE FROM reward t WHERE t.id = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, id);

            result = statement.executeUpdate();

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return result;
    }

    public static int deleteTreasureByID(int id) {
        int result = 0;
        String sql = "DELETE FROM treasure t WHERE t.id = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, id);

            result = statement.executeUpdate();

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return result;
    }

    //UPDATES
    public static int updateTag(Tag tag) {
        int result = 0;

        String sql = "UPDATE tag t SET t.lore = ? WHERE t.id = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, tag.getLore());
            statement.setInt(2, tag.getId());

            result = statement.executeUpdate();

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return result;
    }

    public static int updatePC(Player_Character player_character) {
        int result = 0;

        String sql = "UPDATE player_character t SET t.first_name = ?, t.full_name = ?, t.specie = ?, t.alignment = ?, t.current_status = ?, t.notes = ? WHERE t.id = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(7, player_character.getId());
            statement.setString(1, player_character.getFirstName());
            statement.setString(2, player_character.getFullName());
            statement.setString(3, player_character.getSpecie());
            statement.setString(4,player_character.getAlignment().name());
            statement.setString(5,player_character.getCurrentStatus().name());
            statement.setString(6,player_character.getNotes());

            result = statement.executeUpdate();

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return result;
    }

    public static int updateNPC(NPC npc) {
        int result = 0;

        String sql = "UPDATE non_player_character t SET t.first_name = ?, t.full_name = ?, t.challenge_rating, t.specie = ?, t.alignment = ?, t.current_status = ?, t.notes = ? WHERE t.id = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(7, npc.getId());
            statement.setString(1, npc.getFirstName());
            statement.setString(2, npc.getFullName());
            statement.setString(3, npc.getSpecie());
            statement.setString(4,npc.getAlignment().name());
            statement.setString(5,npc.getCurrentStatus().name());
            statement.setString(6,npc.getNotes());

            result = statement.executeUpdate();

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return result;
    }

    public static int updateReward(Reward reward) {
        int result = 0;

        String sql = "UPDATE reward t SET t.textDescription = ?, t.linkRewards = ? WHERE t.id = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, reward.getDescription());
            statement.setString(2, reward.getLink());
            statement.setInt(3, reward.getId());

            result = statement.executeUpdate();

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return result;
    }

    public static int updateTreasure(Treasure treasure) {
        int result = 0;

        String sql = "UPDATE treasure t SET t.textDescription = ?, t.hasBeenFound = ? WHERE t.id = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, treasure.getDescription());
            statement.setBoolean(2, treasure.hasBeenFound());
            statement.setInt(3, treasure.getId());

            result = statement.executeUpdate();

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return result;
    }

    //MANAGE
    public static int giveRewardPC(int pcID, int rewardID) {
        int result = 0;

        String sql = "UPDATE reward SET idPC = ? WHERE id = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(2, rewardID);
            statement.setInt(1, pcID);

            result = statement.executeUpdate();

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return result;
    }

    public static int removeRewardPC(int idPC, int rewardID) {
        int result = 0;

        String sql = "UPDATE reward SET idPC = null WHERE id = ? AND idPC = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, rewardID);
            statement.setInt(2, idPC);

            result = statement.executeUpdate();

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return result;
    }

    public static int giveLootNPC(int npcID, int rewardID) {
        int result = 0;

        String sql = "UPDATE treasure SET idNPC = ? WHERE id = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(2, rewardID);
            statement.setInt(1, npcID);

            result = statement.executeUpdate();

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return result;
    }

    public static int removeLootNPC(int npcID, int rewardID) {
        int result = 0;

        String sql = "UPDATE treasure SET idNPC = null WHERE id = ? AND idNPC = ?";

        try (Connection connection = SQLDataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, rewardID);
            statement.setInt(2, npcID);

            result = statement.executeUpdate();

        } catch (SQLException e) {
            MyUtils.print("Error whilst connecting to the database: " + e.getMessage());
        }

        return result;
    }
}
