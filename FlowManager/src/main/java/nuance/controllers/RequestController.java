package nuance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import nuance.base.pojo.Request;

@RestController
public class RequestController {

	@Autowired
	Gson gson;

	@RequestMapping(method = RequestMethod.GET, value = "/Request")
	public String requestRecieve() {
		/*Request transactionPojo = new VirginRequest();
		transactionPojo.setFlowName("LOAN");
		transactionPojo.setNextRetryTime("2017-21");

		return gson.toJson(transactionPojo);*/
		return "";
		
	}
}
