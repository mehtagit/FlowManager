package nmss.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import nmss.base.Request;
import nmss.pojos.crbt.CrbtRequest;
import nmss.pojos.crbt.PackDetails;
import nmss.util.JdbcCrbtRequestDAOImpl;

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
