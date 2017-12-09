package nmss.base;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class Service{

	@Autowired
	protected Logger lFile = null;
	
	public abstract Transaction getNextState(Request request);
}
