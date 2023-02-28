package org.example.repositroy;

import org.example.model.Actor.Actor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorDaoJdbc implements ActorDao {

    static final String DB_TYPE = "jdbc:postgresql";
    static final String HOST = "localhost";
    static final int PORT = 5432;
    static final String DB_NAME = "dvdrental";

    static final String DB_URL = DB_TYPE + "://" + HOST + ":" + PORT + "/" + DB_NAME;
    static final String USER = "postgres";
    static final String PASS = "784512";

    @Override
    public List<Actor> getAllActors() {
        final String SQL = "SELECT  actor_id, first_name, last_name FROM actor;";

        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement st = con.prepareStatement(SQL);

            //st.execute();
            //st.executeUpdate();
            ResultSet rs = st.executeQuery();

            List<Actor> actors = new ArrayList<>();

            while (rs.next()) {
                Actor actor = new Actor();
                actor.setId(rs.getInt(1));
                actor.setFirstName(rs.getString(2));
                actor.setLastName(rs.getString(3));

                actors.add(actor);
            }

            return actors;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Actor getActorByName(String firstName, String lastName) {
        final String SQL = "SLECET actor_id, first_name, last_name FROM actor WHERE first_name = ? NAD last_name =?;";

        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement st = con.prepareStatement(SQL);
                st.setString(1,firstName);
                st.setString(2,lastName);

            ResultSet rs = st.executeQuery();

            if (!rs.next()) {
                return null;
            }

            Actor actor = new Actor();
            actor.setId(rs.getInt(1));
            actor.setFirstName(rs.getString(2));
            actor.setLastName(rs.getString(3));


            return actor;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void addActor(Actor actor) {
        final String SQL = "INSERT INTO actor(first_name, last_name) VALUES(?,?);";

        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1,actor.getFirstName());
            st.setString(2,actor.getLastName());
            st.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateActor(int id, Actor actor) {
        final String SQL = "UPDATE actor SET first_name = ?, last_name = ? WHERE actor_id = ?;";

        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, actor.getFirstName());
            st.setString(2, actor.getLastName());
            st.setInt(3, id);
            st.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteActorById(int id) {
        final String SQL = "DELETE FROM actor WHERE actor_id = ?;";

        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement st = con.prepareStatement(SQL);
            st.setInt(1, id);
            st.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
