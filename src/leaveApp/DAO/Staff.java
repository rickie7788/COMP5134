package leaveApp.DAO;

import java.util.ArrayList;
import java.util.List;

public class Staff {
	private String staffid;
	private String name;
	private Staff supervisor;
	private List<Staff> supervisees = new ArrayList<Staff>();

	public Staff(String staffid, String name) {
		this.staffid = staffid;
		this.name = name;
		this.supervisees = new  ArrayList<Staff>();
	}

	public List<Staff> getSupervisees() {
		return supervisees;
	}

	public void addSupervisee(Staff supervisee) {
		this.supervisees.add(supervisee);
	}

	public void addSupervisees(List<Staff> supervisees) {
		this.supervisees.addAll(supervisees);
	}
	
	public String getStaffID() {
		return staffid;
	}
	
	public String getName() {
		return name;
	}
	
	public Staff getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Staff supervisor) {
		this.supervisor = supervisor;
	}

	public void processLeave(LeaveInfo leave)
	{
		leave.setNextSupervior(this.supervisor);
		if(this.supervisor==null)
		{
			leave.setStatus("approved");
		}
		
	}
	
	public void handleLeave(LeaveInfo leave, boolean confirm)
	{
		if(confirm)
		{
			this.processLeave(leave);
		}
		else
		{
			leave.setStatus("declinced");
			leave.setNextSupervior(null);
		}
		
	
	}

}
