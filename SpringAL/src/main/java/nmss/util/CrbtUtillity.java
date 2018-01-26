package nmss.util;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import nmss.pojos.crbt.CrbtRequest;

@Component("crbtUtill")
public class CrbtUtillity extends Utility {

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private MessageSource resources;
	@Autowired
	private XmlParser xmlParser;
	private String[] tags = {"TID","MSISDN","PACKID","MSG_TYPE"};
	
	public String getResponseBack(CrbtRequest request) {
		String DATA = null;
		String result = null;
		String messageType = "";
		HashMap<String ,String > TV = xmlParser.soapParserMultipleValues(request.getMsg(), tags);
		messageType = TV.get("MSG_TYPE");
		messageType = messageType.replaceAll("REQ", "RES");
		
		try {
			// activation request
			if ("0".equals(request.getTxnStatus()))
				result = "success";
			else
				result = "fail";
			if (request.getFlowName().equals("activation")) {

				DATA = "00000#5667799#CRBT#"+request.getMsisdn()+"#<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><RES><MSG_TYPE>"+messageType+"</MSG_TYPE><MSISDN>"
						+ request.getMsisdn() + "</MSISDN><PACKID>" + request.getPackId()
						+ "</PACKID><ACTION>activation</ACTION><TRANS_ID>" + request.getTid() + "</TRANS_ID><RESULT>"
						+ result + "</RESULT><REASON>" + request.getTxnStatusDesc() + "</REASON><SERVICE_AMT>"
						+ request.getDebitAmount() + "</SERVICE_AMT><SERVICE_DAYS>" + request.getDays()
						+ "</SERVICE_DAYS><SONG_AMT></SONG_AMT><SONG_DAYS></SONG_DAYS><SIM>" + request.getSimType()
						+ "</SIM><IMSE>imse</IMSE><HLRID>hlrid</HLRID><CIRCLE>circle</CIRCLE><INID>indi</INID><SCLASS>serviceclass</SCLASS><FREESONG></FREESONG><SERVICESMS>sms of service</SERVICESMS><SONGSMS>sms of song</SONGSMS></RES>#";
			}
			// deactivation request
			else if (request.getFlowName().equals("deactivation")) {
				DATA = "00000#5667799#CRBT#"+request.getMsisdn()+"#<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><RES><MSG_TYPE>"+messageType+"</MSG_TYPE><MSISDN>"
						+ request.getMsisdn() + "</MSISDN><PACKID>" + request.getPackId()
						+ "</PACKID><ACTION>deactivation</ACTION><TRANS_ID>" + request.getTid() + "</TRANS_ID><RESULT>"
						+ result + "</RESULT><REASON>" + request.getTxnStatusDesc() + "</REASON><SERVICE_AMT>"
						+ request.getDebitAmount() + "</SERVICE_AMT><SERVICE_DAYS>" + request.getDays()
						+ "</SERVICE_DAYS><SONG_AMT></SONG_AMT><SONG_DAYS></SONG_DAYS><SIM>" + request.getSimType()
						+ "</SIM><IMSE>imse</IMSE><HLRID>hlrid</HLRID><CIRCLE>circle</CIRCLE><INID>indi</INID><SCLASS>serviceclass</SCLASS><FREESONG></FREESONG><SERVICESMS>sms of service</SERVICESMS><SONGSMS>sms of song</SONGSMS></RES>#";
			}
			else if (request.getFlowName().equals("songdownload")) {
				DATA = "00000#5667799#CRBT#"+request.getMsisdn()+"#<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><RES><MSG_TYPE>"+messageType+"</MSG_TYPE><MSISDN>"
						+ request.getMsisdn() + "</MSISDN><PACKID>" + request.getPackId()
						+ "</PACKID><ACTION>songdownload</ACTION><TRANS_ID>" + request.getTid() + "</TRANS_ID><RESULT>"
						+ result + "</RESULT><REASON>" + request.getTxnStatusDesc() + "</REASON><SERVICE_AMT></SERVICE_AMT><SERVICE_DAYS></SERVICE_DAYS><SONG_AMT>"
						+request.getDebitAmount()+"</SONG_AMT><SONG_DAYS>"+request.getDays()+"</SONG_DAYS><SIM>" + request.getSimType()
						+ "</SIM><IMSE>imse</IMSE><HLRID>hlrid</HLRID><CIRCLE>circle</CIRCLE><INID>indi</INID><SCLASS>serviceclass</SCLASS><FREESONG></FREESONG><SERVICESMS>sms of service</SERVICESMS><SONGSMS>sms of song</SONGSMS></RES>#";
			}
			// song and activation
			else if (request.getFlowName().equals("activationWithSong")) {
				DATA = "00000#5667799#CRBT#"+request.getMsisdn()+"#<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><RES><MSG_TYPE>"+messageType+"</MSG_TYPE><MSISDN>"
						+ request.getMsisdn() + "</MSISDN><PACKID>" + request.getPackId()
						+ "</PACKID><ACTION>activation</ACTION><TRANS_ID>" + request.getTid() + "</TRANS_ID><RESULT>"
						+ result + "</RESULT><REASON>" + request.getTxnStatusDesc()
						+ "</REASON><SERVICE_AMT></SERVICE_AMT><SERVICE_DAYS></SERVICE_DAYS><SONG_AMT>" + request.getDebitAmount()
						+ "</SONG_AMT><SONG_DAYS>"+request.getDays()+"</SONG_DAYS><SIM>" + request.getSimType()
						+ "</SIM><IMSE>imse</IMSE><HLRID>hlrid</HLRID><CIRCLE>circle</CIRCLE><INID>indi</INID><SCLASS>serviceclass</SCLASS><FREESONG></FREESONG><SERVICESMS>sms of service</SERVICESMS><SONGSMS>sms of song</SONGSMS></RES>#";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DATA;
	}

	public int getNextRetryTime(String state, int rertyCount) {
		return Integer.parseInt(resources.getMessage(state + "." + rertyCount, null, "-1", null));

	}
}
