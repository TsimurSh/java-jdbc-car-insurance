package demo.repository;

import demo.config.DBConnector;
import demo.model.User;
import demo.model.exception.NoSuchUserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements DBConnector {
    private static final String QUERY = "SELECT * FROM users WHERE login =?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM users";

    public User findByLogin(String login) {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement ps = connection.prepareStatement(QUERY)
        ) {
            ps.setString(1, login);
            System.out.println("👨🏻‍🏭 Prepared Statement = " + ps);

            ResultSet rs = ps.executeQuery();

            User user = null;
            if (rs.next()) {
                Long userId = rs.getLong("id");
                String password = rs.getString("password");
                String nick = rs.getString("nick");
                user = new User(userId, nick, login, password);
            }

            rs.close();
            System.out.println("🔎Found user: " + login);
            return user;
        } catch (SQLException e) {
            throw new NoSuchUserException("Error searching login in DB for user login =" + login, e);
        }
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_QUERY)
        ) {
            System.out.println("👨🏻‍🏭 Prepared Statement = " + ps);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                long id = rs.getLong("id");
                String nick = rs.getString("nick");
                String login = rs.getString("login");
                String password = rs.getString("password");

                user.setId(id);
                user.setNick(nick);
                user.setLogin(login);
                user.setPassword(password);

                System.out.println("Found user: " + user);

                users.add(user);
            }
            rs.close();

            System.out.println("🔎Found users: " + users.size());
            return users;
        } catch (SQLException e) {
            throw new NoSuchUserException("Error searching all users in DB", e);
        }
    }

}
