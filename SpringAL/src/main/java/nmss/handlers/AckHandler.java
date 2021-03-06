package nmss.handlers;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nmss.base.Request;
import nmss.pojos.crbt.CrbtRequest;
import nmss.util.JdbcCrbtRequestDAOImpl;
import nmss.util.XmlParser;

@Component("ackHandler")
public class AckHandler extends Thread
{
	
	@Resource(name = "ackQueue")
	@Autowired
	private LinkedBlockingQueue<String> ackQueue = null;
	@Autowired
	protected Logger lFile = null;

	private String[] tags = {"TID","MSISDN","PACKID"};
	
	@Autowired
	private XmlParser xmlParser;
	
	@Autowired
	private JdbcCrbtRequestDAOImpl jdbcRequestDAOImpl;

	public void run()
	{
		while(true)
		{
			try
			{
				String ack  = ackQueue.take();
				lFile.info("ack received ["+ack+"]");
				//00049#1212121#RESPONSE_ACK#9818751483#171218135605000#|
				String ack_buff [] = ack.split("#");
				//HashMap<String ,String > TV = xmlParser.soapParserMultipleValues(ack, tags);
				Request request = new CrbtRequest();
				//request.setMsisdn(TV.get(tags[1]));
				//request.setTid(TV.get(tags[0]));
				request.setMsisdn(ack_buff[3]);
				request.setTid(ack_buff[4]);
				jdbcRequestDAOImpl.delete(request);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
}
