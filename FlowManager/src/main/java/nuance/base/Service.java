package nuance.base;

import nuance.base.pojo.Request;

public abstract class Service{
	
	public abstract Transaction getNextState(Request request);
}
