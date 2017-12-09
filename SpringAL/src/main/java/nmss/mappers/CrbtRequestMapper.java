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
import nmss.util.JdbcCrbtRequestDAOImpl;

@Component("requestMapper")
public class CrbtRequestMapper implements RowMapper<Request> {

	@Autowired
	private Gson gson;

	@Autowired
	private Logger lFile = null;

	@Autowired
	private JdbcCrbtRequestDAOImpl jdbcRequestDAOImpl;

	public Request mapRow(ResultSet rs, int rowNum) throws SQLException {
		Request request = null;
		try {

			if (rs.getBoolean("RECONCILE")) {
				String JSON = rs.getString("TRANSACTION_OBJECT");
				request = gson.fromJson(JSON, CrbtRequest.class);
				CrbtRequest tempreq = (CrbtRequest)request;
				tempreq.setMsg(rs.getString("MSG"));
				request = tempreq;
			} else {
				String JSON = rs.getString("REQUEST_OBJECT");
				request = gson.fromJson(JSON, CrbtRequest.class);
				CrbtRequest tempreq = (CrbtRequest)request;
				tempreq.setMsg(rs.getString("MSG"));
				request = tempreq;
				request.setTxnName(rs.getString("STATE"));
				request.setFlowName(rs.getString("BUSINESS_FLOW"));
				
			}

			/*
			 * if MSISDN and TID Is not present in JSON Request than it will use
			 * column value of TID and MSISDN
			 */
			if (request.getMsisdn() == null || "".equals(request.getMsisdn()))
				request.setMsisdn(rs.getString("MSISDN"));
			if (request.getTid() == null || "".equals(request.getTid()))
				request.setTid(rs.getString("TID"));
			
		} catch (com.google.gson.JsonSyntaxException e) {
			Request tempReq = new CrbtRequest();
			tempReq.setMsisdn(rs.getString("MSISDN"));
			tempReq.setTid(rs.getString("TID"));
			tempReq.setTxnName(rs.getString("STATE"));
			jdbcRequestDAOImpl.deleteByTid(tempReq.getTid());
			lFile.info("Wrong Json Recieved " + tempReq);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return request;
	}
}
