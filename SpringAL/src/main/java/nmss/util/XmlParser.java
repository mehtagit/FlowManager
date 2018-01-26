package nmss.util;

import java.io.StringReader;

import javax.xml.parsers.*;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import org.w3c.dom.Node;

import org.w3c.dom.NodeList;

import org.xml.sax.InputSource;

import java.util.HashMap;

@Component("xmlParser")
public class XmlParser

{

	public String Parser(String responseToParse, String tagName) {

		NodeList nodeList = null;

		String tagValue = null;

		DocumentBuilder db = null;

		Document DOMdoc = null;

		int numSec = 0;

		try {

			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();

			DOMdoc = db.parse(new InputSource(new StringReader(responseToParse)));

			nodeList = DOMdoc.getElementsByTagName(tagName);

			numSec = nodeList.getLength();

			// System.out.println( numSec );

			for (int i = 0; i < numSec; i++) {

				Node node = nodeList.item(i);

				tagValue = node.getFirstChild().getNodeValue();

				// System.out.println( tagValue );

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return tagValue;

	}

	public HashMap<String, String> soapParserMultipleValues(String responseToParse, String[] tagName) {

		NodeList nodeList = null;

		HashMap<String, String> hm = new HashMap<String, String>();

		DocumentBuilder db = null;

		Document DOMdoc = null;

		int numSec = 0;

		try {

			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();

			DOMdoc = db.parse(new InputSource(new StringReader(responseToParse)));

			// System.out.println( "Lenght of tag array is["+tagName.length+"]"
			// );

			for (int i = 0; i < tagName.length; i++) {

				// System.out.println( "Tag name to get valueis ["+tagName[ i
				// ]+"]" );

				nodeList = DOMdoc.getElementsByTagName(tagName[i]);

				numSec = nodeList.getLength();

				// System.out.println( "length of numSec is + numSec );

				for (int j = 0; j < numSec; j++) {

					Node node = nodeList.item(j);

					// System.out.println(node.getFirstChild().getNodeValue() );

					try{hm.put(tagName[i], node.getFirstChild().getNodeValue());
					}catch(Exception ee){
						hm.put(tagName[i], null);
					}
					
					// System.out.println( tagValue );

				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return hm;

	}

}
