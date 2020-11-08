package com.noirix.repository.impl;

import com.noirix.domain.Cars;
import com.noirix.repository.CarsColumns;
import com.noirix.repository.CarsRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@Primary
public class CarsRepositoryJdbcTemplateImpl implements CarsRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CarsRepositoryJdbcTemplateImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Cars> search(String query) {
        return jdbcTemplate.query("select * from m_cars where name like ?", new Object[]{query}, this::getCarsRowMapper);
    }

    @Override
    public Cars save(Cars entity) {
        final String createQuery = "insert into m_cars (model, creation_year, user_id, price, color) " +
                "values (:model, :creation_year, :user_id, :price, :color);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("model", entity.getModel());
        params.addValue("creation_year", entity.getCreation_year());
        params.addValue("user_id", entity.getUser_id());
        params.addValue("price", entity.getPrice());
        params.addValue("color", entity.getColor());

        namedParameterJdbcTemplate.update(createQuery, params, keyHolder, new String[]{"id"});

        long createdCarsId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdCarsId);
    }

    @Override
    public List<Cars> findAll() {
        return jdbcTemplate.query("select * from m_cars", this::getCarsRowMapper);
    }

    private Cars getCarsRowMapper(ResultSet rs, int i) throws SQLException {
        Cars car = new Cars();
        car.setId(rs.getLong(CarsColumns.ID));
        car.setModel(rs.getString(CarsColumns.MODEL));
        car.setCreation_year(rs.getInt(CarsColumns.CREATION_YEAR));
        car.setUser_id(rs.getInt(CarsColumns.USER_ID));
        car.setPrice(rs.getDouble(CarsColumns.PRICE));
        car.setColor(rs.getString(CarsColumns.COLOR));
        return car;
    }

    @Override
    public Cars findById(Long key) {
//        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
//        mapSqlParameterSource.addValue("carId", key);
//
//        return namedParameterJdbcTemplate.queryForObject("select * from m_cars where id = :carId", mapSqlParameterSource, this::getCarsRowMapper);

          return jdbcTemplate.queryForObject("select * from m_cars where id = ?", new Object[]{key}, this::getCarsRowMapper);
    }

    @Override
    public Optional<Cars> findOne(Long key) {
        return Optional.empty();
    }

    @Override
    public Cars update(Cars object) {
        return null;
    }

    @Override
    public Long delete(Cars object) {
        return null;
    }
}
