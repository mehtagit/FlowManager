package nuance.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import nuance.nmss.pojos.crbt.PackDetails;

@Component("packDetailsMapper")
public class PackDetailsMapper implements RowMapper<PackDetails> {

	public PackDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		PackDetails packDetails = new PackDetails();
		packDetails.setId(rs.getString("packid"));
		packDetails.setAmount(rs.getFloat("amount"));
		packDetails.setDays(rs.getInt("days"));
		packDetails.setGraceDays(rs.getInt("grace_days"));
		packDetails.setSongAmount(rs.getFloat("song_amount"));
		packDetails.setSongDays(rs.getInt("song_days"));
		return packDetails;
	}
}
