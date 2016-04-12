package leaveApp.Factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import leaveApp.DAO.LeaveInfo;
import leaveApp.DAO.Staff;

public final class LeaveInfoFactory {
	private static List<LeaveInfo> leaveList = new ArrayList<LeaveInfo>();
	
	public static void addLeave(Date fromDate, Date toDate, Staff applyStaff)  throws Exception {
		if(fromDate.after(toDate))
			throw new Exception("From Date cannot earlier than To Date");
		if(fromDate.before(new Date()))
			throw new Exception("Date cannot equal to or earlier than Today");
		LeaveInfo newLeave = new LeaveInfo(fromDate, toDate, applyStaff);
		applyStaff.processLeave(newLeave);
		leaveList.add(newLeave);
	}

	public static List<LeaveInfo> getLeaveList(Staff applyStaff) {
		List<LeaveInfo> l = new ArrayList<LeaveInfo>();
		for (LeaveInfo leaves : leaveList) {
			if(leaves.getApplyStaff()==applyStaff)
				l.add(leaves);
		}
		return l;
	}
	public static String getResultMessage(Staff applyStaff) {
		String result="";
		for (LeaveInfo leaves : leaveList) {
			if(leaves.getApplyStaff()==applyStaff && leaves.getStatus() != "Processing")
				result =result+leaves.toString()+"\n";
		}
		return result;
	}
	public static List<LeaveInfo> getHandleLeaveList(Staff nextSupervior) {
		List<LeaveInfo> l = new ArrayList<LeaveInfo>();
		for (LeaveInfo leaves : leaveList) {
			if(leaves.getNextSupervior()==nextSupervior)
				l.add(leaves);
		}
		return l;
	}
}
