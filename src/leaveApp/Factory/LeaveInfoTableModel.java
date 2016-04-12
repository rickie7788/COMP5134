package leaveApp.Factory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import leaveApp.DAO.LeaveInfo;;

public class LeaveInfoTableModel extends AbstractTableModel{

	private static final long serialVersionUID = -6775897967024882731L;
	private List<LeaveInfo> leaveInfos;

    public LeaveInfoTableModel(List<LeaveInfo> leaveInfos) {
        this.leaveInfos = new ArrayList<>(leaveInfos);
    }

    @Override
    public int getRowCount() {
        return leaveInfos.size();
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
                name = "From Date";
                break;
            case 1:
                name = "To Date";
                break;
            case 2:
                name = "Apply by";
                break;
            case 3:
                name = "status";
                break;
        }
        return name;
    }

    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	LeaveInfo leave = leaveInfos.get(rowIndex);
        Object value = null;
        switch (columnIndex) {
            case 0:
                value = df.format(leave.getFromDate());
                break;
            case 1:
                value = df.format(leave.getToDate());
                break;
            case 2:
            	value = leave.getApplyStaff().getName();
                break;
            case 3:
            	value = leave.getStatus();
                break;             
        }
        if (value ==null)
        	return "";
        else
        	return value;
    }            
    
    public LeaveInfo getValue(int rowIndex) {
    	return leaveInfos.get(rowIndex);
       
    }            
}
