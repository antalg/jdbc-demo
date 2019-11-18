package hu.aensys.tutorial.java.jdbc;

import hu.aensys.tutorial.java.jdbc.database.DatabaseConnector;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class ConnectionDemo {

    public static void main(String[] args) {
        try (Connection connection = DatabaseConnector.connect()) {
            log.info("Connected to database successfully!");
        } catch (SQLException e) {
            log.error("Connecting to database failed", e);
        }
    }

}
