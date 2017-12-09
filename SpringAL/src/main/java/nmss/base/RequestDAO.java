package nmss.base;

import java.util.List;

import nmss.pojos.crbt.CrbtRequest;

//CRUD operations
public interface RequestDAO {
	// Create
	public void save(Request request);

	// Read
	public Request getById(int tid);

	// Update Status
	public int updateState(Request request);

	// Update Status
	public int updateStatus(Request request);
	
	public int updateNextRetryTime(Request request, int minutes);

	// Delete
	public int deleteByTid(String tid);

	// Delete
	public int delete(Request request);

	// Get All
	public List<Request> getAll();
	
}
