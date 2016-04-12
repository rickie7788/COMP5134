package leaveApp.GUI;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import leaveApp.DAO.Staff;
import leaveApp.Factory.LeaveInfoFactory;
import leaveApp.Factory.LeaveInfoTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;


public class ApplyLeaveWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8710118398517771403L;
	private Staff currentStaff;
	private JPanel contentPane;
	JFormattedTextField txtFromDate;
	JFormattedTextField txtToDate;
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private JTable table;
	/**
	 * Create the frame.
	 * @param currentStaff 
	 */
	public ApplyLeaveWindow(Staff staff) {
		currentStaff= staff;
		setTitle("Apply Leave");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 406, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFromDate = new JLabel("From Date:");
		lblFromDate.setBounds(30, 26, 86, 14);
		contentPane.add(lblFromDate);
		
		JLabel lblYyyymmdd = new JLabel("(yyyy-mm-dd)");
		lblYyyymmdd.setBounds(30, 44, 86, 14);
		contentPane.add(lblYyyymmdd);

		txtFromDate = new JFormattedTextField(df);
		txtFromDate.setBounds(114, 23, 86, 20);
		contentPane.add(txtFromDate);
		
		JLabel lblToDate = new JLabel("To Date:");
		lblToDate.setBounds(206, 26, 86, 14);
		contentPane.add(lblToDate);
		
		JLabel lblyyyymmdd = new JLabel("(yyyy-mm-dd)");
		lblyyyymmdd.setBounds(206, 41, 86, 14);
		contentPane.add(lblyyyymmdd);
		
		txtToDate = new JFormattedTextField(df);
		txtToDate.setBounds(290, 23, 86, 20);
		contentPane.add(txtToDate);

		JLabel lblApplyBy = new JLabel("Apply by:");
		lblApplyBy.setBounds(30, 69, 86, 14);
		contentPane.add(lblApplyBy);
		
		JLabel lblStaffname = new JLabel(currentStaff.getName());
		lblStaffname.setBounds(114, 69, 86, 14);
		contentPane.add(lblStaffname);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					LeaveInfoFactory.addLeave(df.parse(txtFromDate.getText()),df.parse(txtToDate.getText()),currentStaff);
					table.setModel(new LeaveInfoTableModel(LeaveInfoFactory.getLeaveList(currentStaff)));
					txtFromDate.setText("");
					txtToDate.setText("");
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, "Incorrect Date Format", "Format error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnSubmit.setBounds(287, 51, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LeaveApplicationWindow frame = new LeaveApplicationWindow(currentStaff);
				frame.setVisible(true);
				dispose();
			}
		});
		btnClose.setBounds(287, 85, 89, 23);
		contentPane.add(btnClose);
		
		LeaveInfoTableModel model = new LeaveInfoTableModel(LeaveInfoFactory.getLeaveList(currentStaff));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 115, 390, 195);
		contentPane.add(scrollPane);
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
	

	}
}
