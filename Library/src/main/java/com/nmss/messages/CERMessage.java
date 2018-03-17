/**
 * 
 */
package com.nmss.messages;

import java.net.InetAddress;
import java.util.Random;

import dk.i1.diameter.AVP_Address;
import dk.i1.diameter.AVP_UTF8String;
import dk.i1.diameter.AVP_Unsigned32;
import dk.i1.diameter.Message;
import dk.i1.diameter.ProtocolConstants;
import dk.i1.diameter.Utils;
import dk.i1.diameter.node.Capability;

/**
 * @author Varun
 *
 *         Capability Exchange Request
 */
public class CERMessage extends Message {

	private Capability capability;
	public CERMessage() {
		// TODO Auto-generated constructor stub
	}

	public CERMessage(int vendor_id, int app, String IN_ORIGIN_HOST, String IN_ORIGIN_REALM, String IN_VENDOR_ID,
			String IN_PRODUCT_NAME, int IN_AUTH_APP) {
		capability = new Capability();
		capability.addVendorAcctApp(vendor_id, app);
		capability.addAuthApp(ProtocolConstants.DIAMETER_APPLICATION_CREDIT_CONTROL);
		super.hdr.setRequest(true);
		super.hdr.command_code = 257;
		super.hdr.application_id = 0;
		super.hdr.hop_by_hop_identifier = (new Random()).nextInt();

		int i = (int) (System.currentTimeMillis() / 1000L);
		int state_id = i;

		super.hdr.end_to_end_identifier = i << 20 | (new Random()).nextInt() & 0xfffff;
		/**
		 * Client Data
		 */
		add(new AVP_UTF8String(264, IN_ORIGIN_HOST));// origin host
		add(new AVP_UTF8String(296, IN_ORIGIN_REALM));// origin relam
		InetAddress inetaddress = null;
		try {
			inetaddress = InetAddress.getByName(IN_ORIGIN_HOST);
		} catch (Exception e) {
			System.out.println(e);
		}
		add(new AVP_Address(257, inetaddress));
		add(new AVP_Unsigned32(266, Integer.parseInt(IN_VENDOR_ID))); // Vendor_id
		add(new AVP_UTF8String(269, IN_PRODUCT_NAME)); // product id
		add(new AVP_Unsigned32(258, IN_AUTH_APP)); // Auth-Application-Id
		Utils.setMandatory_RFC3588(this);
	}

}
