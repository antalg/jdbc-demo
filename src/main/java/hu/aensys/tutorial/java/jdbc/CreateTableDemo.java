package hu.aensys.tutorial.java.jdbc;

import hu.aensys.tutorial.java.jdbc.database.CustomerDao;
import hu.aensys.tutorial.java.jdbc.database.DatabaseConnector;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class CreateTableDemo {

    public static void main(String[] args) throws SQLException {
        try (Connection connection = DatabaseConnector.connect()) {
            CustomerDao customerDao = new CustomerDao(connection);
            customerDao.createTable();
            log.info("Table created successfully.");
        }
    }

}
