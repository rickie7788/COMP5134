package leaveApp.GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import leaveApp.DAO.Staff;
import leaveApp.Factory.StaffFactory;

public class LoginWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4259561259707565929L;
	private JPanel contentPane;
	private JTextField txtStaffId;
	private JTextField txtStaffName;
	private JButton btnCreateDemoStaff;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * Initialize the contents of the frame.
	 */
	public LoginWindow() {

		setTitle("Login Window");
		setBounds(100, 100, 331, 239);
		contentPane = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblStaffId = new JLabel("Staff ID:");
		lblStaffId.setBounds(76, 62, 46, 14);
		contentPane.add(lblStaffId);

		txtStaffId = new JTextField();
		txtStaffId.setBounds(156, 59, 86, 20);
		contentPane.add(txtStaffId);

		JLabel lblStaffName = new JLabel("Staff Name:");
		lblStaffName.setBounds(76, 87, 86, 14);
		contentPane.add(lblStaffName);

		txtStaffName = new JTextField();
		txtStaffName.setBounds(156, 84, 86, 20);
		contentPane.add(txtStaffName);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtStaffId.getText().equals("admin") && txtStaffName.getText().equals("admin"))
				{
					HRMainWindow frame = new HRMainWindow();
					frame.setVisible(true);
					dispose();
				}
				else
				{
					Staff loginstaff = StaffFactory.getStaff(txtStaffId.getText(),txtStaffName.getText());
					if (loginstaff!=null)
					{
						LeaveApplicationWindow frame = new LeaveApplicationWindow(loginstaff);
						frame.setVisible(true);
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Incorrect Staff ID and/or Staff Name", "Login fail", JOptionPane.ERROR_MESSAGE);
					}

				}
				
			}
		});
		btnLogin.setBounds(153, 115, 89, 23);
		contentPane.add(btnLogin);
		
		btnCreateDemoStaff = new JButton("Create Demo Staff");
		btnCreateDemoStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StaffFactory.createDemonStaff();
				btnCreateDemoStaff.setEnabled(false);
			}
		});
		if(StaffFactory.getStaffCount()>0)
			btnCreateDemoStaff.setEnabled(false);
		btnCreateDemoStaff.setBounds(10, 167, 152, 23);
		contentPane.add(btnCreateDemoStaff);
	}

}
