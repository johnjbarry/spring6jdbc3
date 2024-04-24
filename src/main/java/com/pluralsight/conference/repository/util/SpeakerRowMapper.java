package com.pluralsight.conference.repository.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.pluralsight.conference.model.Speaker;
import org.springframework.jdbc.core.RowMapper;

public class SpeakerRowMapper implements RowMapper<Speaker>
{
    public Speaker mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        Speaker speaker = new Speaker();
        speaker.setId(rs.getInt("id"));
        speaker.setName(rs.getString("name"));
        speaker.setSkill(rs.getString("skill"));
        return speaker;
    }
}
