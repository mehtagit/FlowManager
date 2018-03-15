package nuance.util;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nuance.base.pojo.Request;
import nuance.nmss.pojos.crbt.CrbtRequest;
import nuance.nmss.pojos.crbt.PackDetails;
import nuance.repository.RequestRepository;

@Component("validation")
public class Validation {

	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private ConcurrentHashMap<String, List<PackDetails>> mapOfFallBack;
	
	public boolean validateRequest(Request request) {
		CrbtRequest crbtRequest = (CrbtRequest) request;

		if (request.getTid() == null || "".equals(request.getTid())) {
			request.setTxnStatusDesc("Validation Fail::json parameter {\"msisdn\"} cannot be empty and null");
			return false;
		} else if (request.getMsisdn() == null || "".equals(request.getMsisdn())) {
			request.setTxnStatusDesc("Validation Fail::json parameter {\"tid\"} cannot be empty and null");
			return false;
		} else if (crbtRequest.getPackId() == null || "".equals(crbtRequest.getPackId())) {
			request.setTxnStatusDesc("Validation Fail::json parameter {\"packId\"} cannot be empty and null");
			return false;
		} else if (mapOfFallBack.get(crbtRequest.getPackId()) == null) {
			request.setTxnStatusDesc("Validation Fail::Pack Details Not Found");
			return false;
		}
		return true;
	}

	public boolean validateActivation(Request request) {
		CrbtRequest crbtRequest = (CrbtRequest) request;
		if (!validateRequest(crbtRequest)) {
			request.setTxnStatus("-1");
			return false;
		} else if (requestRepository.isAlreadySubscribed(crbtRequest)) {
			System.out.println("Subscription validation fail");
			request.setTxnStatus("-1");
			request.setTxnStatusDesc("Validation Fail::Already Subscribed");
			return false;
		}

		return true;
	}
	public boolean validateDeActivation(Request request) {
		CrbtRequest crbtRequest = (CrbtRequest) request;
		if (!validateRequest(crbtRequest)) {
			return false;
		} else if (!requestRepository.isAlreadySubscribed(crbtRequest)) {
			request.setTxnStatusDesc("Validation Fail::Not Subscribed");
			return false;
		}

		return true;
	}
	public boolean validateRenewal(Request request) {
		CrbtRequest crbtRequest = (CrbtRequest) request;
		if (!validateRequest(crbtRequest)) {
			return false;
		} else if (requestRepository.isAlreadyRenewalForDay(crbtRequest)) {
			request.setTxnStatusDesc("Validation Fail::No Need To Renew ");
			return false;
		}

		return true;
	}
}
