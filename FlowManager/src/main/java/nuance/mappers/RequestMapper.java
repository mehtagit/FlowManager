package nuance.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import nuance.base.pojo.Request;
import nuance.nmss.pojos.crbt.CrbtRequest;

@Component("requestMapper")
public class RequestMapper implements RowMapper<Request> {

	@Autowired
	private Gson gson;

	protected final Logger logger = LogManager.getLogger(RequestMapper.class);

	public Request mapRow(ResultSet rs, int rowNum) throws SQLException {
		Request request = null;
		try {

			if (rs.getBoolean("RECONCILE")) {
				String JSON = rs.getString("TRANSACTION_OBJECT");
				/*** Need to be think about **/
				request = gson.fromJson(JSON, CrbtRequest.class);
				CrbtRequest tempreq = (CrbtRequest)request;
				tempreq.setMsg(rs.getString("MSG"));
				request = tempreq;
			} else {
				String JSON = rs.getString("REQUEST_OBJECT");

				/*** Need to be think about **/
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
			request = new CrbtRequest();
			request.setMsisdn(rs.getString("MSISDN"));
			request.setTid(rs.getString("TID"));
			request.setTxnName(rs.getString("STATE"));
			request.setStatus(-1);
			// requestRepository.deleteByTid(request.getTid());
			logger.error("Wrong Json Recieved " + request);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return request;
	}
}
