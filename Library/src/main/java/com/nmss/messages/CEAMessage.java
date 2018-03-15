/**
 * 
 */
package com.nmss.messages;

import dk.i1.diameter.AVP;
import dk.i1.diameter.AVP_Unsigned32;
import dk.i1.diameter.InvalidAVPLengthException;
import dk.i1.diameter.Message;

/**
 * @author Varun
 *
 *         Capability Exchange Answer
 */
public class CEAMessage extends Message {

	public decode_status decode(byte[] decodeBytes) {
		return this.decode(decodeBytes);
	}

	public boolean isSucces() {
		boolean result = false;
		AVP avp = this.find(268);
		if (avp == null) {
		}
		try {
			if ((new AVP_Unsigned32(avp)).queryValue() == 2001)
				result = true;
		} catch (InvalidAVPLengthException invalidavplengthexception) {
			invalidavplengthexception.printStackTrace();
		}
		return result;
	}
}
