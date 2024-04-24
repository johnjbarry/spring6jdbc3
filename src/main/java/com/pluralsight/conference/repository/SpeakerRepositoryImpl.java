package com.pluralsight.conference.repository;

import com.pluralsight.conference.model.Speaker;
import com.pluralsight.conference.repository.util.SpeakerRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("speakerRepository")
public class SpeakerRepositoryImpl implements SpeakerRepository {

    private final JdbcTemplate jdbcTemplate;

    public SpeakerRepositoryImpl(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Speaker> findAll() {

        return jdbcTemplate.query("SELECT * FROM speaker", new SpeakerRowMapper());
    }

    @Override
    public Speaker create(Speaker speaker)
    {
        // int update = jdbcTemplate.update("INSERT INTO speaker (name) VALUES (?)", speaker.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement("INSERT INTO speaker (name, skill) VALUES (?, ?)", new String[]{"id"});
            ps.setString(1, speaker.getName());
            ps.setString(2, speaker.getSkill());
            return ps;
        }, keyHolder);

        Number id =  keyHolder.getKey();

        if (id != null)
            return getSpeaker(id.intValue());

        System.out.println("Error writing to the database");
        return null;
    }

    @Override
    public Speaker getSpeaker(int id)
    {
        return jdbcTemplate.queryForObject("SELECT * FROM speaker WHERE id = ?", new SpeakerRowMapper(), id);
    }

    @Override
    public Speaker update(Speaker speaker)
    {
        jdbcTemplate.update("UPDATE speaker SET name = ? WHERE id = ?", speaker.getName(), speaker.getId());
        return speaker;
    }

    @Override
    public void update(List<Object[]> pairs)
    {
        jdbcTemplate.batchUpdate("UPDATE speaker SET skill = ? WHERE id = ?", pairs);
    }

    @Override
    public void delete(int id)
    {
//        jdbcTemplate.update("DELETE FROM speaker WHERE id = ?", id);
        NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        namedTemplate.update("DELETE FROM speaker WHERE id = :id", paramMap);
    }
}
