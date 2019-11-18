package hu.aensys.tutorial.java.jdbc;

import hu.aensys.tutorial.java.jdbc.database.CustomerDao;
import hu.aensys.tutorial.java.jdbc.database.DatabaseConnector;
import hu.aensys.tutorial.java.jdbc.model.Address;
import hu.aensys.tutorial.java.jdbc.model.Customer;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Slf4j
public class InsertDemo {

    public static void main(String[] args) throws SQLException {
        try (Connection connection = DatabaseConnector.connect()) {
            CustomerDao customerDao = new CustomerDao(connection);

            List<Customer> customers = LongStream.rangeClosed(1, 10)
                    .mapToObj(InsertDemo::generateCustomer)
                    .collect(Collectors.toList());

            for (Customer customer : customers) {
                customerDao.save(customer);
            }

            log.info("Customers saved successfully.");
        }
    }

    private static Customer generateCustomer(Long id) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFullName("Test Customer " + id);
        customer.setBirthDate(new Date(System.currentTimeMillis()));

        Address address = customer.getAddress();
        address.setCountry("Hungary");
        address.setCity("Szeged");
        address.setStreet("Kalvaria sugarut");
        address.setNumber("24");

        return customer;
    }

}
