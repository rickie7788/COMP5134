package leaveApp.GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import leaveApp.DAO.LeaveInfo;
import leaveApp.DAO.Staff;
import leaveApp.Factory.LeaveInfoFactory;
import leaveApp.Factory.LeaveInfoTableModel;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LeaveApprovalWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3425514826471481178L;
	private JPanel contentPane;
	private JTable table;
	private Staff currentStaff;

	/**
	 * Create the frame.
	 */
	public LeaveApprovalWindow(Staff staff) {
		currentStaff= staff;
		setTitle("Apply Leave");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
			
		LeaveInfoTableModel model = new LeaveInfoTableModel(LeaveInfoFactory.getHandleLeaveList(currentStaff));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 11, 424, 195);
		contentPane.add(scrollPane);
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		JButton btnEndorse = new JButton("Endorse");
		btnEndorse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = table.getSelectedRow();
				LeaveInfo leave = ((LeaveInfoTableModel) table.getModel()).getValue(i);
				currentStaff.handleLeave(leave, true);
				table.setModel(new LeaveInfoTableModel(LeaveInfoFactory.getHandleLeaveList(currentStaff)));
			}
		});
		btnEndorse.setBounds(20, 228, 89, 23);
		contentPane.add(btnEndorse);
		
		JButton btnDecline = new JButton("Decline");
		btnDecline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int i = table.getSelectedRow();
					LeaveInfo leave = ((LeaveInfoTableModel) table.getModel()).getValue(i);
					currentStaff.handleLeave(leave, false);
					table.setModel(new LeaveInfoTableModel(LeaveInfoFactory.getHandleLeaveList(currentStaff)));
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDecline.setBounds(119, 228, 89, 23);
		contentPane.add(btnDecline);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LeaveApplicationWindow frame = new LeaveApplicationWindow(currentStaff);
				frame.setVisible(true);
				dispose();
			}
		});
		btnClose.setBounds(218, 228, 89, 23);
		contentPane.add(btnClose);
		
	}

}
