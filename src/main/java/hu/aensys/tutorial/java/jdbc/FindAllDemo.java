package hu.aensys.tutorial.java.jdbc;

import hu.aensys.tutorial.java.jdbc.database.CustomerDao;
import hu.aensys.tutorial.java.jdbc.database.DatabaseConnector;
import hu.aensys.tutorial.java.jdbc.model.Customer;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Slf4j
public class FindAllDemo {

    public static void main(String[] args) throws SQLException {
        try (Connection connection = DatabaseConnector.connect()) {
            CustomerDao customerDao = new CustomerDao(connection);
            List<Customer> customers = customerDao.findAll();

            customers.forEach(customer -> log.info(customer.toString()));
        }
    }

}
