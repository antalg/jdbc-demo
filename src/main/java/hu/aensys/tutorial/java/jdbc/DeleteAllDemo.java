package hu.aensys.tutorial.java.jdbc;

import hu.aensys.tutorial.java.jdbc.database.CustomerDao;
import hu.aensys.tutorial.java.jdbc.database.DatabaseConnector;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class DeleteAllDemo {

    public static void main(String[] args) throws SQLException {
        try (Connection connection = DatabaseConnector.connect()) {
            CustomerDao customerDao = new CustomerDao(connection);
            int rowsDeleted = customerDao.deleteAll();
            log.info("{} rows deleted.", rowsDeleted);
        }
    }

}
