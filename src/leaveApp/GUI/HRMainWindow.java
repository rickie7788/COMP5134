package leaveApp.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import leaveApp.Factory.StaffFactory;
import leaveApp.Factory.StaffTableModel;

public class HRMainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2662330916816722727L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtStaffId;
	private JTextField txtStaffName;
	private JTextField txtSupervisorId;

	/**
	 * Create the frame.
	 * Initialize the contents of the frame.
	 */
	public HRMainWindow() {

		setTitle("HR Main Window");
		setBounds(100, 100, 491, 300);
		contentPane = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAddStaff = new JButton("Add Staff");
		btnAddStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					StaffFactory.addStaff(txtStaffId.getText(), txtStaffName.getText(), txtSupervisorId.getText());
					table.setModel(new StaffTableModel(StaffFactory.getStaffList()));
					txtStaffId.setText("");
					txtStaffName.setText("");
					txtSupervisorId.setText("");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Add Staff error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAddStaff.setBounds(364, 39, 103, 23);
		contentPane.add(btnAddStaff);

		JButton btnDeleteStaff = new JButton("Delete Saff");
		btnDeleteStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int i = table.getSelectedRow();
					String id = table.getModel().getValueAt(i, 0).toString();
					StaffFactory.removeStaff(id);
					table.setModel(new StaffTableModel(StaffFactory.getStaffList()));

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Delete Staff error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDeleteStaff.setBounds(364, 67, 103, 23);
		contentPane.add(btnDeleteStaff);

		StaffTableModel model = new StaffTableModel(StaffFactory.getStaffList());
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 344, 212);
		contentPane.add(scrollPane);
		table = new JTable(model);
		scrollPane.setViewportView(table);

		JLabel lblStaffId = new JLabel("Staff ID:");
		lblStaffId.setBounds(10, 14, 50, 14);
		contentPane.add(lblStaffId);

		txtStaffId = new JTextField();
		txtStaffId.setBounds(60, 11, 50, 20);
		contentPane.add(txtStaffId);

		JLabel lblStaffName = new JLabel("Staff Name:");
		lblStaffName.setBounds(120, 14, 70, 14);
		contentPane.add(lblStaffName);

		txtStaffName = new JTextField();
		txtStaffName.setBounds(190, 11, 100, 20);
		contentPane.add(txtStaffName);

		JLabel lblSupervisorId = new JLabel("Supervisor ID:");
		lblSupervisorId.setBounds(300, 14, 80, 14);
		contentPane.add(lblSupervisorId);

		txtSupervisorId = new JTextField();
		txtSupervisorId.setBounds(385, 11, 80, 20);
		contentPane.add(txtSupervisorId);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginWindow window = new LoginWindow();
				window.setVisible(true);
				dispose();
			}
		});
		btnLogout.setBounds(364, 228, 103, 23);
		contentPane.add(btnLogout);
	}
}
