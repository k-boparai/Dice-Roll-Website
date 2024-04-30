package ca.sheridancollege.bopkaran.database;

import ca.sheridancollege.bopkaran.beans.Dice;
import ca.sheridancollege.bopkaran.beans.Roll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseAccess {

    @Autowired
    NamedParameterJdbcTemplate jdbc;

    public void insertDice (Dice dice) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "INSERT INTO dice (diceNumber, diceFaces, diceTotal) VALUES " +
                "(:diceNumber, :diceFaces, :diceTotal)";
        parameters.addValue("diceNumber", dice.diceNumber);
        parameters.addValue("diceFaces", dice.diceFaces);
        parameters.addValue("diceTotal", dice.diceTotal);
        jdbc.update(query, parameters);
    }

    public void insertRoll (int rollNumber, int rollValue) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "INSERT INTO roll (rollNumber, rollValue) VALUES " +
                "(:rollNumber, :rollValue)";
        parameters.addValue("rollNumber", rollNumber);
        parameters.addValue("rollValue", rollValue);
        jdbc.update(query, parameters);
    }

    public List<Dice> getDiceList () {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * from dice";
        return jdbc.query(query, parameters, new BeanPropertyRowMapper<Dice>(Dice.class));
    }

    public List<Roll> getRollList () {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * from roll";
        return jdbc.query(query, parameters, new BeanPropertyRowMapper<Roll>(Roll.class));
    }

    public void diceDelete () {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "DELETE FROM dice";
        jdbc.update(query, parameters);
    }

    public void rollDelete () {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "DELETE from roll";
        jdbc.update(query, parameters);
    }

}
