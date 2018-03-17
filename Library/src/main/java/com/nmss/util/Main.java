package com.nmss.util;

import com.nmss.client.Client;
import com.nmss.messages.CEAMessage;
import com.nmss.messages.CERMessage;

import dk.i1.diameter.AVP;
import dk.i1.diameter.AVP_Unsigned32;

public class Main 
{
	public static void main(String aa[]) throws Exception
	{
		if (aa[0].equals("client"))
		{
			Client client = new Client("103.206.248.235",8888,"103.206.248.235",4444);
			if (!client.connect())
				return;
			
			CERMessage cer = new CERMessage(1, 2, "103.206.248.235", "B", "5", "D", 3);
			System.out.println("CERMessage is created.....");
			client.sendRequest(cer.encode());
			System.out.println("CERMessage is send.....");
			byte received [] = client.readFromServer();
			System.out.println("CEA is received.......");
			CERMessage answer = new CERMessage();
			answer.decode(received);
			AVP avp = answer.find(268);
			int resultcode = new AVP_Unsigned32(avp).queryValue();
			System.out.println("CER Answer ["+resultcode+"]");
			client.disConnect();
		}
	}
}
