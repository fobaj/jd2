package com.noirix.repository.impl;

import com.noirix.domain.Cars;
import com.noirix.exception.EntityNotFoundException;
import com.noirix.repository.CarsColumns;
import com.noirix.repository.CarsRepository;
import com.noirix.util.DatabasePropertiesReader;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.noirix.util.DatabasePropertiesReader.DATABASE_DRIVER_NAME;
import static com.noirix.util.DatabasePropertiesReader.DATABASE_LOGIN;
import static com.noirix.util.DatabasePropertiesReader.DATABASE_PASSWORD;
import static com.noirix.util.DatabasePropertiesReader.DATABASE_URL;

@Repository
public class CarsRepositoryImpl implements CarsRepository {

    private static final Logger log = Logger.getLogger(CarsRepositoryImpl.class);

    public static final DatabasePropertiesReader reader = DatabasePropertiesReader.getInstance();

    @Override
    public List<Cars> search(String query) {
        return null;
    }

    @Override
    public Cars save(Cars cars) {
        final String findByIdQuery = "insert into m_cars (model, creation_year, user_id, price, color) " +
                "values (?,?,?,?,?)";

        Connection connection;
        PreparedStatement statement;

        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
        } catch (ClassNotFoundException e) {
            log.error("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL), reader.getProperty(DATABASE_LOGIN), reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(findByIdQuery);
            PreparedStatement lastInsertId = connection.prepareStatement("SELECT currval('m_cars_id_seq') as last_insert_id;");

            statement.setString(1, cars.getModel());
            statement.setInt(2, cars.getCreation_year());
            statement.setInt(3, cars.getUser_id());
            statement.setDouble(4, cars.getPrice());
            statement.setString(5, cars.getColor());

            statement.executeUpdate();

            Long insertedId;
            ResultSet lastIdResultSet = lastInsertId.executeQuery();
            if (lastIdResultSet.next()) {
                insertedId = lastIdResultSet.getLong("last_insert_id");
            } else {
                throw new RuntimeException("We cannot read sequence last value during Cars creation!");
            }

            return findById(insertedId);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public List<Cars> findAll() {
        final String findAllQuery = "select * from m_cars order by id";

        List<Cars> result = new ArrayList<>();

        Connection connection;
        Statement statement;
        ResultSet rs;

        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL), reader.getProperty(DATABASE_LOGIN), reader.getProperty(DATABASE_PASSWORD));
            statement = connection.createStatement();
            rs = statement.executeQuery(findAllQuery);

            while (rs.next()) {
                result.add(parseResultSet(rs));
            }

            return result;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    private Cars parseResultSet(ResultSet rs) throws SQLException {
        Cars cars = new Cars();
        cars.setId(rs.getLong(CarsColumns.ID));
        cars.setModel(rs.getString(CarsColumns.MODEL));
        cars.setCreation_year(rs.getInt(CarsColumns.CREATION_YEAR));
        cars.setUser_id(rs.getInt(CarsColumns.USER_ID));
        cars.setPrice(rs.getDouble(CarsColumns.PRICE));
        cars.setColor(rs.getString(CarsColumns.COLOR));
        return cars;
    }

    @Override
    public Cars findById(Long key) {
        final String findByIdQuery = "select * from m_cars where id = ?";

        Connection connection;
        PreparedStatement statement;
        ResultSet rs;

        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL), reader.getProperty(DATABASE_LOGIN), reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(findByIdQuery);
            statement.setLong(1, key);

            rs = statement.executeQuery();

            if (rs.next()) {
                return parseResultSet(rs);
            } else {
                throw new EntityNotFoundException("User with ID:" + key + "not found");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Optional<Cars> findOne(Long key) {
        return Optional.of(findById(key));
    }

    @Override
    public Cars update(Cars cars) {
        final String findByIdQuery = "update m_cars " +
                "set " +
                "model = ?,  " +
                "creation_year = ?,  " +
                "user_id = ?,  " +
                "price = ?,  " +
                "color = ?,  " +
                "where id = ?";

        Connection connection;
        PreparedStatement statement;

        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL), reader.getProperty(DATABASE_LOGIN), reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(findByIdQuery);
            statement.setString(1, cars.getModel());
            statement.setInt(2, cars.getCreation_year());
            statement.setInt(3, cars.getUser_id());
            statement.setDouble(4, cars.getPrice());
            statement.setString(5, cars.getColor());
            statement.setLong(8, cars.getId());

            statement.executeUpdate();
            return findById(cars.getId());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Long delete(Cars cars) {
        final String findByIdQuery = "delete from m_cars where id = ?";

        Connection connection;
        PreparedStatement statement;

        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL), reader.getProperty(DATABASE_LOGIN), reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(findByIdQuery);
            statement.setLong(1, cars.getId());

            int deletedRows = statement.executeUpdate();
            return (long)deletedRows;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }
}
