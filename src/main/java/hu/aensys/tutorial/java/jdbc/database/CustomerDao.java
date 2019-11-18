package hu.aensys.tutorial.java.jdbc.database;

import hu.aensys.tutorial.java.jdbc.model.Address;
import hu.aensys.tutorial.java.jdbc.model.Customer;
import lombok.AllArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class CustomerDao {

    private static final String CREATE_TABLE_COMMAND = """
        create table if not exists customer (
            id bigint primary key,
            full_name varchar(255),
            birth_date date,
            address_country varchar(100),
            address_city varchar(100),
            address_street varchar(100),
            address_number varchar(100)
        );""";

    private static final String INSERT_COMMAND = "insert into customer values (?, ?, ?, ?, ?, ?, ?);";
    private static final String FIND_ALL_COMMAND = "select * from customer;";
    private static final String DELETE_ALL_COMMAND = "delete from customer;";

    private final Connection connection;

    public void createTable() throws SQLException {
        try (Statement tableCreatorStatement = connection.createStatement()) {
            tableCreatorStatement.execute(CREATE_TABLE_COMMAND);
        }
    }

    public void save(Customer customer) throws SQLException {
        try (PreparedStatement insertStatement = connection.prepareStatement(INSERT_COMMAND)) {
            insertStatement.setLong(1, customer.getId());
            insertStatement.setString(2, customer.getFullName());
            insertStatement.setDate(3, customer.getBirthDate());

            Address address = customer.getAddress();
            insertStatement.setString(4, address.getCountry());
            insertStatement.setString(5, address.getCity());
            insertStatement.setString(6, address.getStreet());
            insertStatement.setString(7, address.getNumber());

            insertStatement.executeUpdate();
        }
    }

    public List<Customer> findAll() throws SQLException {
        try (
                Statement selectStatement = connection.createStatement();
                ResultSet resultSet = selectStatement.executeQuery(FIND_ALL_COMMAND);
        ) {
            List<Customer> customers = new ArrayList<>();

            while (resultSet.next()) {
                customers.add(readCustomer(resultSet));
            }

            return customers;
        }
    }

    private Customer readCustomer(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setId(resultSet.getLong("id"));
        customer.setFullName(resultSet.getString("full_name"));
        customer.setBirthDate(resultSet.getDate("birth_date"));

        Address address = customer.getAddress();
        address.setCountry(resultSet.getString("address_country"));
        address.setCity(resultSet.getString("address_city"));
        address.setStreet(resultSet.getString("address_street"));
        address.setNumber(resultSet.getString("address_number"));

        return customer;
    }

    public int deleteAll() throws SQLException {
        try (Statement deleteStatement = connection.createStatement()) {
            return deleteStatement.executeUpdate(DELETE_ALL_COMMAND);
        }
    }

}
