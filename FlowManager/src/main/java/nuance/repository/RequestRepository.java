package nuance.repository;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import nuance.base.AppConfig;
import nuance.base.pojo.Request;
import nuance.mappers.PackDetailsMapper;
import nuance.mappers.RequestMapper;
import nuance.nmss.pojos.crbt.CrbtRequest;
import nuance.nmss.pojos.crbt.PackDetails;
import nuance.nmss.util.CrbtConfig;
import nuance.util.ApplicationContextProvider;
import nuance.util.SqlUtility;
import nuance.util.Utility;

@Repository
public class RequestRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private CrbtConfig crbtConfig;

	protected final Logger logger = LogManager.getLogger(RequestRepository.class);

	@Autowired
	private SqlUtility sqlUtility;

	@Autowired
	private Utility utility;

	@Autowired
	private Gson gson;

	private int update(String query) {
		int j;
		try {
			j = this.jdbcTemplate.update(query);
		} catch (org.springframework.jdbc.CannotGetJdbcConnectionException e) {
			j = -3;
			logger.error("Exception [" + e.getMessage() + "], Query :" + query);
		} catch (Exception e) {
			e.printStackTrace();
			j = -2;
		}
		logger.info("Query [" + query + "] , status:" + j);
		return j;
	}

	public int updateState(Request request) {
		String TRANS_OBJECT = gson.toJson(request);
		String query = "update " + appConfig.getFulfilmentRequestTable() + " set STATE='" + request.getTxnName()
				+ "', TRANSACTION_OBJECT='" + TRANS_OBJECT + "', MODIFIED_ON=" + sqlUtility.currentDate()
				+ " where TID='" + request.getTid() + "'";
		return update(query);
	}

	public int deleteByTid(String tid) {
		String query = "delete from " + appConfig.getFulfilmentRequestTable() + " where TID='" + tid + "'";
		return update(query);
	}

	@Transactional(readOnly = true)
	public List<Request> findAll() {
		List<Request> reqDataList = null;
		String query = "select * from " + appConfig.getFulfilmentRequestTable() + " where "
				+ appConfig.getFulfilmentRequestSelectLogic();
		try {
			reqDataList = this.jdbcTemplate.query(query,
					ApplicationContextProvider.getBean("requestMapper", RequestMapper.class));
		} catch (org.springframework.jdbc.CannotGetJdbcConnectionException e) {
			logger.error("Exception [" + e.getMessage() + "] for Query: " + query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Query [" + query + "]");
		return reqDataList;
	}

	public int updateNextRetryTime(Request request, int minutes) {
		String TRANS_OBJECT = gson.toJson(request);
		String query = "update " + appConfig.getFulfilmentRequestTable() + " set STATE='" + request.getTxnName()
				+ "', TRANSACTION_OBJECT='" + TRANS_OBJECT + "', MODIFIED_ON=" + sqlUtility.currentDate()
				+ " ,  NEXT_RETRY_TIME=" + sqlUtility.toDate(utility.getDateTime(minutes)) + " where TID='"
				+ request.getTid() + "'";

		return update(query);
	}

	public int delete(Request request) {
		String query = "delete from " + appConfig.getFulfilmentRequestTable() + " where TID='" + request.getTid()
				+ "' OR MSISDN='" + request.getMsisdn() + "'";
		return update(query);
	}

	public int updateStatus(Request request) {
		String query = "update " + appConfig.getFulfilmentRequestTable() + " set STATUS=0, MODIFIED_ON="
				+ sqlUtility.currentDate() + " where TID='" + request.getTid() + "'";
		return update(query);
	}

	/**
	 * For CRBT functions
	 * 
	 */
	public int deleteFromSubsciption(Request request) {
		CrbtRequest crbtRequest = (CrbtRequest) request;
		String query = "delete from SUBSCRIBER_BASE  WHERE MSISDN=" + request.getMsisdn() + " and packId='"
				+ crbtRequest.getPackId() + "'";
		return update(query);
	}

	public int updateGrace(Request request) {
		CrbtRequest crbtRequest = (CrbtRequest) request;
		String query = "update SUBSCRIBER_BASE set LAST_TRNX_DATE=" + sqlUtility.currentDate()
				+ " , STATUS='GRACE' WHERE MSISDN=" + request.getMsisdn() + " and packId='" + crbtRequest.getPackId()
				+ "'";
		return update(query);
	}

	public int updateRenewal(Request request) {
		CrbtRequest crbtRequest = (CrbtRequest) request;

		String query = "update SUBSCRIBER_BASE set RENEW_DATE=DATE_ADD(RENEW_DATE , INTERVAL " + crbtRequest.getDays()
				+ " DAY) , STATUS='ACT' WHERE MSISDN=" + request.getMsisdn() + " and packId='" + crbtRequest.getPackId()
				+ "'";
		return update(query);
	}

	public int saveAsSubscribe(Request request) {
		CrbtRequest crbtRequest = (CrbtRequest) request;
		String query = "insert into SUBSCRIBER_BASE (MSISDN,PACKID,ACT_DATE,RENEW_DATE,SIM,LAST_AMT,LAST_TRNX_DATE,STATUS) values ("
				+ request.getMsisdn() + ",'" + crbtRequest.getPackId() + "'," + sqlUtility.currentDate() + ","
				+ sqlUtility.toDate(utility.getDateByDays(crbtRequest.getDays())) + ",'" + crbtRequest.getSimType()
				+ "'," + crbtRequest.getDebitAmount() + "," + sqlUtility.currentDate() + ",'ACT')";
		return update(query);
	}

	public boolean isAlreadyRenewalForDay(Request request) {
		CrbtRequest crbtRequest = (CrbtRequest) request;
		int i = 0;
		try {
			if (sqlUtility.getDbType().equals("mysql")) {
				i = this.jdbcTemplate
						.queryForObject(
								"select datediff(NOW(),RENEW_DATE) from SUBSCRIBER_BASE WHERE MSISDN="
										+ request.getMsisdn() + " and packId='" + crbtRequest.getPackId() + "'",
								Integer.class);
			} else {
				i = this.jdbcTemplate.queryForObject("select SYSDATE - RENEW_DATE from SUBSCRIBER_BASE WHERE MSISDN="
						+ request.getMsisdn() + " and packId='" + crbtRequest.getPackId() + "'", Integer.class);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		crbtRequest.setDaysGraced(i);

		return i < 1 ? true : false;
	}

	public boolean isAlreadySubscribed(Request request) {
		CrbtRequest crbtRequest = (CrbtRequest) request;
		String query = "select count(*) from SUBSCRIBER_BASE WHERE MSISDN=" + request.getMsisdn() + " and packId='"
				+ crbtRequest.getPackId() + "'";
		int i = 0;
		try {
			i = this.jdbcTemplate.queryForObject(query, Integer.class);
		} catch (Exception e) {

		}
		logger.info("Query [" + query + "] , status:" + i);
		return i > 0 ? true : false;
	}

	public List<PackDetails> getAllPacks() {
		List<PackDetails> reqDataList = null;
		String query = "select * from PACK_DETAILS order by packid,amount desc";
		try {
			reqDataList = this.jdbcTemplate.query(query,
					ApplicationContextProvider.getBean("packDetailsMapper", PackDetailsMapper.class));
		} catch (org.springframework.jdbc.CannotGetJdbcConnectionException e) {
			logger.error("Exception [" + e.getMessage() + "] for Query: " + query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Query [" + query + "]");
		return reqDataList;
	}

	public int updateComplete(Request request) {
		String query = "update " + appConfig.getFulfilmentRequestTable() + " set STATUS=9, MODIFIED_ON="
				+ sqlUtility.currentDate() + ",  NEXT_RETRY_TIME=" + sqlUtility.toDate(utility.getDateTime(1))
				+ "where TID='" + request.getTid() + "'";
		return update(query);
	}

	public List<Request> getPendingAcks() {
		List<Request> reqDataList = null;
		String query = "select * from " + appConfig.getFulfilmentRequestTable() + " where "
				+ crbtConfig.getRetryAckRequestLogic();
		try {
			reqDataList = this.jdbcTemplate.query(query,
					ApplicationContextProvider.getBean("requestMapper", RequestMapper.class));
		} catch (org.springframework.jdbc.CannotGetJdbcConnectionException e) {
			logger.error("Exception [" + e.getMessage() + "] for Query: " + query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Query [" + query + "]");
		return reqDataList;
	}
}
