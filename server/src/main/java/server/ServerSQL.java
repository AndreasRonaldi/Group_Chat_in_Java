package server;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;

import com.google.common.hash.Hashing;

public class ServerSQL extends Thread {

    static Statement statement;
    static Connection conn;

    @Override
    public void run() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/" + Server.DB_NAME,
                    Server.DB_USERNAME,
                    Server.DB_PASSWORD);

            statement = conn.createStatement();
            System.out.println("> Database connected");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR: Connection to Database");
        }
    }

    public void stopSQL() throws SQLException {
        conn.close();
    }

    public static void listOfUser() {
        view("select * from user");
    }

    public static Boolean findUser(String username, String password) {
        Boolean isUserFound = false;
        try {
            String hashPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
            String query = "select * from user where username = ? and password = ?";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, hashPassword);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                System.out.println("# Found user: " + resultSet.getString(2) + " " + resultSet.getString(3));
                isUserFound = true;
            }

        } catch (SQLException ex) {
            System.out.println("ERROR: Checking user [" + username + ", " + password + "]");
        }

        return isUserFound;
    }

    public static Boolean insertUser(String username, String password) {
        Boolean success = false;
        try {
            String hashPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();

            String query = "insert into user (username, password) values (?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, hashPassword);

            int res = pstmt.executeUpdate();
            // System.out.println("> INFO: res insert user: " + res);
            if (res > 0)
                return true;
        } catch (SQLException e) {
            System.out.println("ERROR: Inserting New User [" + username + ", " + password + "]");
        }
        return success;
    }

    public static Boolean isValidGroup(Integer groupId) {
        try {
            String query = "select * from room where idRoom = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "" + groupId);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next())
                return true;
        } catch (SQLException e) {
            System.out.println("ERROR: Finding Group Id = " + groupId);
        }
        return false;
    }

    public static String findAllGroup() {
        ArrayList<String> res = new ArrayList<>();
        try {
            String query = "select idRoom, room.name, user.username from room join user on ownerId = idUser";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String temp = resultSet.getString(1) + "=" + resultSet.getString(2) + ":" + resultSet.getString(3);
                res.add(temp);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: Viewing All Group");
        }

        return res.toString();
    }

    public static Boolean createGroup(String roomName, String username) {
        try {
            String query = "insert into room (name, ownerId) values (? , (select idUser from user where username = ?))";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, roomName);
            pstmt.setString(2, username);

            int res = pstmt.executeUpdate();

            if (res > 0)
                return true;
        } catch (SQLException e) {
            System.out.println("ERROR: Create New Group [" + roomName + ", " + username + "]");
        }
        return false;
    }

    public static String findGroupOwner(Integer roomId) {
        String res = null;
        try {
            String query = "select user.username from room join user on ownerId = idUser where idRoom = ?";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "" + roomId);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                res = resultSet.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("ERROR: Finding Group with id = " + roomId);
        }
        return res;
    }

    public static Boolean removeGroup(Integer roomId) {
        try {
            String query = "delete from room where idRoom = ?";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "" + roomId);

            int res = pstmt.executeUpdate();
            if (res > 0)
                return true;
        } catch (SQLException e) {
            System.out.println("ERROR: Delete Room id = " + roomId);
        }
        return false;
    }

    // for viewing data
    public static void view(String query) {
        try {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                System.out.println("# " + result.getString(2) + " " + result.getString(3));
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: Viewing Query [" + query + "]");
        }
    }
}
