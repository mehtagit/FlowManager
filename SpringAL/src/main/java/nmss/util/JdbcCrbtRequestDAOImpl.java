package nmss.util;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import nmss.Main.Start;
import nmss.base.Request;
import nmss.base.RequestDAO;
import nmss.mappers.CrbtRequestMapper;
import nmss.mappers.PackDetailsMapper;
import nmss.pojos.crbt.CrbtRequest;
import nmss.pojos.crbt.PackDetails;

@Component("jdbcCrbtRequestDAOImpl")
public class JdbcCrbtRequestDAOImpl implements RequestDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	protected AppConfig appConfig;

	@Autowired
	protected Utility utility;

	@Autowired
	private Logger lFile = null;

	@Autowired
	private Gson gson;

	@Autowired
	SqlUtility sqlUtility;

	@Autowired
	@Resource(name = "dataSource")
	private org.apache.commons.dbcp.BasicDataSource dataSource;

	@PostConstruct
	public void init() {
		try {
			this.jdbcTemplate = new JdbcTemplate(dataSource);
			lFile.info("DB DataSource URL::" + dataSource.getUrl() + ", USER::" + dataSource.getUsername() + ", PASS::"
					+ dataSource.getPassword() + ", PoolMin::" + dataSource.getInitialSize() + ", PoolMax::"
					+ dataSource.getMaxActive());
		} catch (Exception e) {
			lFile.info("Exception while initialization of JDBC Template" + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public void save(Request request) {
		// TODO Auto-generated method stub

	}

	@Override
	public Request getById(int tid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateState(Request request) {
		String TRANS_OBJECT = gson.toJson(request);
		int j;
		String query = "update " + appConfig.fulfilmentRequestTable + " set STATE='" + request.getTxnName()
				+ "', TRANSACTION_OBJECT='" + TRANS_OBJECT + "', MODIFIED_ON=" + sqlUtility.currentDate()
				+ " where TID='" + request.getTid() + "'";
		try {
			j = this.jdbcTemplate.update(query);
		} catch (org.springframework.jdbc.CannotGetJdbcConnectionException e) {
			j = -3;
			lFile.error("Exception [" + e.getMessage() + "] TID:" + request.getTid());
		} catch (Exception e) {
			e.printStackTrace();
			j = -2;
		}
		lFile.info("Query [" + query + "] , status:" + j);
		return j;
	}

	@Override
	public int deleteByTid(String tid) {
		int j;
		String query = "delete from " + appConfig.fulfilmentRequestTable + " where TID='" + tid + "'";
		return update(query);
	}

	@Override
	public List<Request> getAll() {
		List<Request> reqDataList = null;
		String query = "select * from " + appConfig.fulfilmentRequestTable + " where " + appConfig.selectRequestLogic;
		try {
			reqDataList = this.jdbcTemplate.query(query, Start.ctx.getBean("requestMapper", CrbtRequestMapper.class));
		} catch (org.springframework.jdbc.CannotGetJdbcConnectionException e) {
			lFile.error("Exception [" + e.getMessage() + "] for Query: " + query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		lFile.info("Query [" + query + "]");
		return reqDataList;
	}

	public List<Request> getPendingAcks() {
		List<Request> reqDataList = null;
		String query = "select * from " + appConfig.fulfilmentRequestTable + " where " + appConfig.RetryAckRequestLogic;
		try {
			reqDataList = this.jdbcTemplate.query(query, Start.ctx.getBean("requestMapper", CrbtRequestMapper.class));
		} catch (org.springframework.jdbc.CannotGetJdbcConnectionException e) {
			lFile.error("Exception [" + e.getMessage() + "] for Query: " + query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		lFile.info("Query [" + query + "]");
		return reqDataList;
	}

	@Override
	public int updateNextRetryTime(Request request, int minutes) {
		String TRANS_OBJECT = gson.toJson(request);
		String query = "update " + appConfig.fulfilmentRequestTable + " set TRANSACTION_OBJECT='" + TRANS_OBJECT
				+ "', MODIFIED_ON=" + sqlUtility.currentDate() + " ,  NEXT_RETRY_TIME="
				+ sqlUtility.toDate(utility.getDateTime(minutes)) + ", RECONCILE=1 where TID='" + request.getTid()
				+ "'";
		return update(query);
	}

	@Override
	public int delete(Request request) {
		String query = "delete from " + appConfig.fulfilmentRequestTable + " where TID='" + request.getTid()
				+ "' OR MSISDN='" + request.getMsisdn() + "'";

		return update(query);
	}

	@Override
	public int updateStatus(Request request) {
		String query = "update " + appConfig.fulfilmentRequestTable + " set STATUS=0, MODIFIED_ON="
				+ sqlUtility.currentDate() + " where TID='" + request.getTid() + "'";
		return update(query);
	}

	public int updateComplete(Request request) {
		String query = "update " + appConfig.fulfilmentRequestTable + " set STATUS=9, MODIFIED_ON="
				+ sqlUtility.currentDate() + ",  NEXT_RETRY_TIME="
				+ sqlUtility.toDate(utility.getDateTime(1)) +"where TID='" + request.getTid() + "'";
		return update(query);
	}

	public List<PackDetails> getAllPacks() {
		List<PackDetails> reqDataList = null;
		String query = "select * from PACK_DETAILS order by packid,amount desc";
		try {
			reqDataList = this.jdbcTemplate.query(query,
					Start.ctx.getBean("packDetailsMapper", PackDetailsMapper.class));
		} catch (org.springframework.jdbc.CannotGetJdbcConnectionException e) {
			lFile.error("Exception [" + e.getMessage() + "] for Query: " + query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		lFile.info("Query [" + query + "]");
		return reqDataList;
	}

	public boolean isAlreadySubscribed(Request request) {
		CrbtRequest crbtRequest = (CrbtRequest) request;
		String query = "select count(*) from SUBSCRIBER_BASE WHERE MSISDN=" + request.getMsisdn() + " and packId='"
				+ crbtRequest.getPackId() + "'";
		int i = 0;
		try {
			i = this.jdbcTemplate.queryForInt(query);
		} catch (Exception e) {

		}
		lFile.info("Query [" + query + "] , status:" + i);
		return i > 0 ? true : false;
	}

	public boolean isAlreadyRenewalForDay(Request request) {
		CrbtRequest crbtRequest = (CrbtRequest) request;
		int i = 0;
		try {
			if (sqlUtility.getDbType().equals("mysql")) {
				i = this.jdbcTemplate.queryForInt("select datediff(NOW(),RENEW_DATE) from SUBSCRIBER_BASE WHERE MSISDN="
						+ request.getMsisdn() + " and packId='" + crbtRequest.getPackId() + "'");
			} else {
				i = this.jdbcTemplate.queryForInt("select SYSDATE - RENEW_DATE from SUBSCRIBER_BASE WHERE MSISDN="
						+ request.getMsisdn() + " and packId='" + crbtRequest.getPackId() + "'");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		crbtRequest.setDaysGraced(i);

		return i < 1 ? true : false;
	}

	public int saveAsSubscribe(Request request) {
		CrbtRequest crbtRequest = (CrbtRequest) request;
		String query = "insert into SUBSCRIBER_BASE (MSISDN,PACKID,ACT_DATE,RENEW_DATE,SIM,LAST_AMT,LAST_TRNX_DATE,STATUS) values ("
				+ request.getMsisdn() + ",'" + crbtRequest.getPackId() + "'," + sqlUtility.currentDate() + ","
				+ sqlUtility.toDate(utility.getDateByDays(crbtRequest.getDays())) + ",'" + crbtRequest.getSimType()
				+ "'," + crbtRequest.getDebitAmount() + "," + sqlUtility.currentDate() + ",'ACT')";
		return update(query);
	}

	public int updateRenewal(Request request) {
		CrbtRequest crbtRequest = (CrbtRequest) request;

		String query = "update SUBSCRIBER_BASE set RENEW_DATE=DATE_ADD(RENEW_DATE , INTERVAL " + crbtRequest.getDays()
				+ " DAY) , STATUS='ACT' WHERE MSISDN=" + request.getMsisdn() + " and packId='" + crbtRequest.getPackId()
				+ "'";
		return update(query);
	}

	public int updateSystemRenewal(Request request) {
		CrbtRequest crbtRequest = (CrbtRequest) request;

		String query = "update SUBSCRIBER_BASE set RENEW_DATE=DATE_ADD(RENEW_DATE , INTERVAL " + crbtRequest.getDays()
				+ " DAY) , STATUS='ACT' WHERE MSISDN=" + request.getMsisdn() + " and packId='" + crbtRequest.getPackId()
				+ "'";
		return update(query);
	}
	
	public int updateGrace(Request request) {
		CrbtRequest crbtRequest = (CrbtRequest) request;
		String query = "update SUBSCRIBER_BASE set LAST_TRNX_DATE=" + sqlUtility.currentDate()
				+ " , STATUS='GRACE' WHERE MSISDN=" + request.getMsisdn() + " and packId='" + crbtRequest.getPackId()
				+ "'";
		return update(query);
	}
	
	public int updateSystemGrace(Request request) {
		CrbtRequest crbtRequest = (CrbtRequest) request;
		String query = "update SUBSCRIBER_BASE set LAST_TRNX_DATE=" + sqlUtility.currentDate()
				+ " , STATUS='GRACE' WHERE MSISDN=" + request.getMsisdn() + " and packId='" + crbtRequest.getPackId()
				+ "'";
		return update(query);
	}

	public int deleteFromSubsciption(Request request) {
		CrbtRequest crbtRequest = (CrbtRequest) request;
		String query = "delete from SUBSCRIBER_BASE  WHERE MSISDN=" + request.getMsisdn() + " and packId='"
				+ crbtRequest.getPackId() + "'";
		return update(query);
	}

	private int update(String query) {
		int j;
		try {
			j = this.jdbcTemplate.update(query);
		} catch (org.springframework.jdbc.CannotGetJdbcConnectionException e) {
			j = -3;
			lFile.error("Exception [" + e.getMessage() + "] query:" + query);
		} catch (Exception e) {
			e.printStackTrace();
			j = -2;
		}
		lFile.info("Query [" + query + "] , status:" + j);
		return j;
	}
}
