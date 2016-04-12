package leaveApp.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import leaveApp.DAO.Staff;
import leaveApp.Factory.LeaveInfoFactory;

import javax.swing.JTextPane;

public class LeaveApplicationWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6825751663925416259L;
	private JPanel contentPane;
	private JLabel lblCurrentUser;
	private JButton btnApplyLeave;
	private JButton btnApproveLeave;
	private JTextPane textPane;
	private Staff currentStaff;

	/**
	 * Create the frame.
	 */
	public LeaveApplicationWindow() {
		setTitle("Leave Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 358, 289);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginWindow window = new LoginWindow();
				window.setVisible(true);
				dispose();
			}
		});
		btnLogout.setBounds(188, 45, 120, 23);
		contentPane.add(btnLogout);
		
		btnApplyLeave = new JButton("Apply Leave");
		btnApplyLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ApplyLeaveWindow frame = new ApplyLeaveWindow(currentStaff);
				frame.setVisible(true);
				dispose();
			}
		});
		btnApplyLeave.setBounds(58, 45, 120, 23);
		contentPane.add(btnApplyLeave);
		
		btnApproveLeave = new JButton("Approve Leave");
		btnApproveLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LeaveApprovalWindow frame = new LeaveApprovalWindow(currentStaff);
				frame.setVisible(true);
				dispose();
			}
		});
		btnApproveLeave.setBounds(58, 79, 120, 23);
		contentPane.add(btnApproveLeave);
		
		lblCurrentUser = new JLabel("Login staff: testing user");
		lblCurrentUser.setBounds(18, 11, 200, 23);
		contentPane.add(lblCurrentUser);
		
		textPane = new JTextPane();
		textPane.setBounds(10, 113, 322, 127);
		contentPane.add(textPane);
	}

	public LeaveApplicationWindow(Staff loginstaff) {
		this();
		currentStaff = loginstaff;
		lblCurrentUser.setText("Login staff: " + currentStaff.getName());
		if(currentStaff.getSupervisor()==null)
			btnApplyLeave.setEnabled(false);
    	if (currentStaff.getSupervisees().size()==0 || LeaveInfoFactory.getHandleLeaveList(currentStaff).size()==0)
    		btnApproveLeave.setEnabled(false);
    	else
    		textPane.setText("No of Leave need to Handle:"+ LeaveInfoFactory.getHandleLeaveList(currentStaff).size()+"\n");
    	textPane.setText(textPane.getText()+ LeaveInfoFactory.getResultMessage(currentStaff));
	}
}
