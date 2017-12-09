package nmss.handlers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nmss.base.Request;
import nmss.pojos.crbt.CrbtRequest;
import nmss.util.AppConfig;
import nmss.util.CrbtUtillity;
import nmss.util.JdbcCrbtRequestDAOImpl;

@Component("responseRerty")
public class RetryResponse extends Thread
{

	@Autowired
	private JdbcCrbtRequestDAOImpl jdbcRequestDAOImpl;
	
	@Autowired
	private CrbtUtillity crbtUtill;

	@Autowired
	private AppConfig config;
	@Autowired
	private Logger lFile = null;


	public void run()
	{
		lFile.info("retry of reponse thread is up");
		while (true)
		{
			try 
			{
				List<Request> requestList = jdbcRequestDAOImpl.getPendingAcks();
				for (Request request : requestList) 
				{
					sleep(1000);
					String response = crbtUtill.getResponseBack((CrbtRequest) request);
					crbtUtill.UDP_SEND(config.responseIP, config.responsePORT, response, true);
				}
				sleep(1000*60);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
}
