package beans.dtos;


public class EnrollRequestDTO {
	private long buyerId;
	private long trainingId;
	private long coachId;
	private String year;
	private String month;
	private String day;
	private String hour;
	private String minutes;
	
	
	
	public EnrollRequestDTO() {}



	public long getBuyerId() {
		return buyerId;
	}



	public void setBuyerId(long buyerId) {
		this.buyerId = buyerId;
	}



	public long getTrainingId() {
		return trainingId;
	}



	public void setTrainingId(long trainingId) {
		this.trainingId = trainingId;
	}



	public long getCoachId() {
		return coachId;
	}



	public void setCoachId(long coachId) {
		this.coachId = coachId;
	}



	public String getYear() {
		return year;
	}



	public void setYear(String year) {
		this.year = year;
	}



	public String getMonth() {
		return month;
	}



	public void setMonth(String month) {
		this.month = month;
	}



	public String getDay() {
		return day;
	}



	public void setDay(String day) {
		this.day = day;
	}



	public String getHour() {
		return hour;
	}



	public void setHour(String hour) {
		this.hour = hour;
	}



	public String getMinutes() {
		return minutes;
	}



	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}

	
	
	
}
