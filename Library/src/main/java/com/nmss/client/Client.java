package com.nmss.client;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Client {
	private Socket clientSocket;
	private DataInputStream in;
	private DataOutputStream out;
	private String serverIP;
	private int serverPort;
	private String localIP;
	private int localPort;

	public Client(String serverIP, int serverPort, String localIP, int localPort) {
		this.serverIP = serverIP;
		this.serverPort = serverPort;
		this.localIP = localIP;
		this.localPort = localPort;
	}

	public void disConnect()
	{
		try {
			//in.close();
			//out.close();
			clientSocket.shutdownInput();
			clientSocket.shutdownOutput();
			clientSocket.close();
			
			System.out.println("disconnected from  server....");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean connect() {
		try {
			//clientSocket = new Socket(serverIP, serverPort, InetAddress.getByName(localIP), localPort);
			clientSocket = new Socket(serverIP, serverPort);
			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
			System.out.println("connection created with server....");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void sendRequest(byte data[]) {
		try {
			out.write(data);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public byte[] readFromServer()
	{
		byte length [] = new  byte[3];
		byte version [] = new  byte[1];
		byte data[] = new byte[20000];
		byte total_data[] = null;
		try
		{
			in.read(data,0,1);
			in.read(data,1,3);
			version[0]      = data[0];
			length[0]       = data[1];
			length[1]       = data[2];
			length[2]       = data[3];
			int total_length_to_receive     = convertToInt(convertToHex(length));
			total_data = new  byte[total_length_to_receive];
			int received_from_server = readComplete((total_length_to_receive-4),data,4,0);
			int ll = received_from_server+4;
			if(total_length_to_receive == ll)
			{
				System.arraycopy(data,0, total_data, 0,total_length_to_receive);
			}
			else
			{
				int bytes_left_to_read = total_length_to_receive-(received_from_server+4);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return total_data;
	}

	public int readComplete(int total_length_to_receive, byte data[], int startIndex,int count) throws Exception
	{
		int readed = in.read(data,startIndex,total_length_to_receive);
		count = count+readed;
		if(readed == total_length_to_receive)
		{
//			BillingClient.LW.writeIntoLog(2," REQUEST MDN ["+MSISDN+"] I have readed complete in one frame count ["+count+"]");
		}
		else
		{
			int bytes_left_to_read = total_length_to_receive - readed;
//			BillingClient.LW.writeIntoLog(2," REQUEST MDN ["+MSISDN+"] I have readed only ["+readed+"] GOING TO READ AGAIN.. LEFT ["+bytes_left_to_read+"]");
			return readComplete(bytes_left_to_read,data,(readed+4),count);
			//readComplete(data,(readed+1)bytes_left_to_read);
		}
		return count;
	}

	public String convertToHex(byte[] data) 
	{
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) 
		{
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do 
			{
				if ((0 <= halfbyte) && (halfbyte <= 9))
					buf.append((char) ('0' + halfbyte));
				else
					buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} 
			while(two_halfs++ < 1);
		}
		return buf.toString();
	}

	public int convertToInt(String data)
	{
		return Integer.parseInt(data, 16);
	}

	public void start() {
		connect();
	}
}
