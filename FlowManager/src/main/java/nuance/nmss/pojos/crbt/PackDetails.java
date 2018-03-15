package nuance.nmss.pojos.crbt;

public class PackDetails {

	private String id;
	private float amount;
	private int days;
	private int graceDays;
	private int songDays;
	private float songAmount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getGraceDays() {
		return graceDays;
	}

	public void setGraceDays(int graceDays) {
		this.graceDays = graceDays;
	}

	public int getSongDays() {
		return songDays;
	}

	public void setSongDays(int songDays) {
		this.songDays = songDays;
	}

	public float getSongAmount() {
		return songAmount;
	}

	public void setSongAmount(float songAmount) {
		this.songAmount = songAmount;
	}

	@Override
	public String toString() {
		return "PackDetails [id=" + id + ", amount=" + amount + ", days=" + days + ", graceDays=" + graceDays
				+ ", songDays=" + songDays + ", songAmount=" + songAmount + "]";
	}

}
