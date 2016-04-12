package leaveApp.Factory;

import java.util.ArrayList;
import java.util.List;

import leaveApp.DAO.Staff;

public final class StaffFactory {
	private static List<Staff> StaffList = new ArrayList<Staff>();

	public static List<Staff> getStaffList() {
		return StaffList;
	}

	public static void createDemonStaff() {
		Staff newStaff1 = new Staff("1","test1");
		Staff newStaff2 = new Staff("2","test2");
		newStaff2.setSupervisor(newStaff1);
		newStaff1.addSupervisee(newStaff2);
		Staff newStaff3 = new Staff("3","test3");
		newStaff3.setSupervisor(newStaff2);
		newStaff2.addSupervisee(newStaff3);
		Staff newStaff4 = new Staff("4","test4");
		newStaff4.setSupervisor(newStaff3);
		newStaff3.addSupervisee(newStaff4);
		StaffList.add(newStaff1);
		StaffList.add(newStaff2);
		StaffList.add(newStaff3);
		StaffList.add(newStaff4);
	}
	public static void addStaff(String staffid, String staffName, String supervisorid) throws Exception {
		if (staffid.isEmpty())
			throw new Exception("Staff Id cannot be empty");
		if (staffName.isEmpty())
			throw new Exception("Staff Name cannot be empty");
		if (getStaffbyId(staffid) != null)
			throw new Exception("Staff Id:" + staffid + " already exis");
		Staff newStaff = new Staff(staffid, staffName);
		if (!supervisorid.isEmpty()) {
			Staff supervisor = getStaffbyId(supervisorid);
			if (supervisor == null)
				throw new Exception("Supervisor is not found by Id:" + supervisorid);
			newStaff.setSupervisor(supervisor);
			supervisor.addSupervisee(newStaff);
		} else {
			if (StaffList.size() != 0)
				throw new Exception("Supervisor cannot be empty");
		}
		StaffList.add(newStaff);
	}

	public static void removeStaff(String staffid) throws Exception {
		for (Staff staff : StaffList) {
			if (staff.getStaffID().equals(staffid)) {
				if (staff.getSupervisor() == null)
					throw new Exception("Director cannot be deleted");
				if (staff.getSupervisees().size() != 0) {
					Staff supervisor = staff.getSupervisor();
					for (Staff s : staff.getSupervisees())
						s.setSupervisor(supervisor);
					supervisor.addSupervisees(staff.getSupervisees());
				}
				StaffList.remove(staff);
				return;
			}
		}
		throw new Exception("Staff is not found");
	}

	private static Staff getStaffbyId(String id) {
		for (Staff staff : StaffList) {
			if (staff.getStaffID().equals(id))
				return staff;
		}
		return null;
	}

	public static Staff getStaff(String id, String staffName) {
		for (Staff staff : StaffList) {
			if (staff.getStaffID().equals(id) && staff.getName().equals(staffName))
				return staff;
		}
		return null;
	}
	public static int getStaffCount() {
		return StaffList.size();
	}

}
