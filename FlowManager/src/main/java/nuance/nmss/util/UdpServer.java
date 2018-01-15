package nuance.nmss.util;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("udpServer")
public class UdpServer extends Thread {

	private DatagramSocket socket;

	@Resource(name = "ackQueue")
	@Autowired
	private LinkedBlockingQueue<String> ackQueue = null;

	@Autowired
	private CrbtConfig crbtConfig;

	@PostConstruct
	public void init() {
		try {
			socket = new DatagramSocket(crbtConfig.getAckUdpServerPort());
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void run() {
		byte[] reciveData = null;
		DatagramPacket recivePacket = null;
		while (true) {
			try {
				reciveData = new byte[1024];
				recivePacket = new DatagramPacket(reciveData, reciveData.length);
				socket.receive(recivePacket);
				String data = new String(recivePacket.getData());
				ackQueue.put(data.trim());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
