package nuance.parser;

import java.io.StringReader;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import nuance.handlers.HandleRequest;

@Component("xmlParser")
public class XmlParser {

	protected final Logger logger = LogManager.getLogger(HandleRequest.class);

	private String[] tagsToParse = { "REASON", "RESULT", "TID", "MSISDN", "PACKID", "MSG_TYPE" };

	public HashMap<String, String> soapParserMultipleValues(String responseToParse) {
		NodeList nodeList = null;
		HashMap<String, String> hm = new HashMap<String, String>();
		DocumentBuilder db = null;
		Document DOMdoc = null;
		int numSec = 0;
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			DOMdoc = db.parse(new InputSource(new StringReader(responseToParse)));
			for (int i = 0; i < tagsToParse.length; i++) {
				nodeList = DOMdoc.getElementsByTagName(tagsToParse[i]);
				numSec = nodeList.getLength();
				for (int j = 0; j < numSec; j++) {
					Node node = nodeList.item(j);
					try {
						hm.put(tagsToParse[i], node.getFirstChild().getNodeValue());
					} catch (Exception e) {
						hm.put(tagsToParse[i], null);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return hm;
	}
}
