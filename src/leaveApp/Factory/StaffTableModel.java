package leaveApp.Factory;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import leaveApp.DAO.Staff;

public class StaffTableModel extends AbstractTableModel{

    /**
	 * 
	 */
	private static final long serialVersionUID = -6775897967024882731L;
	private List<Staff> staffs;

    public StaffTableModel(List<Staff> staffs) {
        this.staffs = new ArrayList<>(staffs);
    }

    @Override
    public int getRowCount() {
        return staffs.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        String name = "??";
        switch (column) {
            case 0:
                name = "Staff ID";
                break;
            case 1:
                name = "Name";
                break;
            case 2:
                name = "Role";
                break;
            case 3:
                name = "Supervior";
                break;
        }
        return name;
    }

    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Staff staff = staffs.get(rowIndex);
        Object value = null;
        switch (columnIndex) {
            case 0:
                value = staff.getStaffID();
                break;
            case 1:
                value = staff.getName();
                break;
            case 2:
            	if(staff.getSupervisor()==null)
            		value = "Director";
            	else if (staff.getSupervisees().size()>0)
            		value = "Supervisor";
            	else 
            		value = "Employee";
                break;    
            case 3:
            	if(staff.getSupervisor()==null)
            		value="";
            	else
            		value = staff.getSupervisor().getName();
                break;                
        }
        if (value ==null)
        	return "";
        else
        	return value;
    }            
}
