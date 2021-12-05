package com.myjob.dinstesttask.dao;

import com.myjob.dinstesttask.DinstesttaskApplication;
import com.myjob.dinstesttask.models.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class DataDAO implements DataInt {

    private static final Logger logger = LogManager.getLogger(DinstesttaskApplication.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DataDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Data> read(String tableName) {
        logger.info("DB" + tableName + " read...");
        return jdbcTemplate.query("SELECT * FROM " + tableName, new BeanPropertyRowMapper<>(Data.class));
    }

    @Override
    public void wright(String tableName, Data data) {
        logger.info("DB" + tableName + " wright...");
        jdbcTemplate.update("INSERT INTO " + tableName + " VALUES(?, ?, ?)", data.getId(), data.getName(), data.getTimeStamp());
    }
}
