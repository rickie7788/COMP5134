package leaveApp.DAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LeaveInfo {
	private Date fromDate;
	private Date toDate;
	private Staff applyStaff;
	private String status;
	private Staff nextSupervior;
	
	public LeaveInfo(Date fromDate, Date toDate, Staff applyStaff) {
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.applyStaff = applyStaff;
		this.status = "Processing";
	}
	
	public Date getFromDate() {
		return fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public Staff getApplyStaff() {
		return applyStaff;
	}
	

	public Staff getNextSupervior() {
		return nextSupervior;
	}
	
	public void setNextSupervior(Staff nextSupervior) {
		this.nextSupervior = nextSupervior;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String toString() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return "Leave Application From: " + df.format(fromDate) + " to: "+ df.format(toDate) +" have been "+ this.status; 
	}
}
