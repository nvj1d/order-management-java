import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JTextField user;
	private JPasswordField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(450, 230, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("username:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setBounds(117, 105, 94, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("password:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(115, 139, 85, 27);
		panel.add(lblNewLabel_1);
		
		user = new JTextField();
		user.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		user.setBounds(221, 104, 86, 20);
		panel.add(user);
		user.setColumns(10);
		
		JButton btnNewButton = new JButton("login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestd1","root","");
					Statement stm = con.createStatement();
					String sql = "select * from login where user ='"+user.getText()+"' and pass ='"+pass.getText().toString()+"'";
					ResultSet rs = stm.executeQuery(sql);
					if(rs.next()){
						JOptionPane.showMessageDialog(null,"Login Successfully ...");
						new Produits().frame.setVisible(true);
						frame.dispose();
					}else{
						JOptionPane.showMessageDialog(null,"Incorrrect Username or Password!");
					}
					con.close();
				}catch(Exception ex){
					System.out.print(ex);
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnNewButton.setBounds(144, 188, 70, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setFont(new Font("MV Boli", Font.PLAIN, 36));
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setBounds(174, 44, 99, 43);
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("clear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user.setText("");
				pass.setText("");
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnNewButton_1.setBounds(219, 188, 70, 23);
		panel.add(btnNewButton_1);
		
		pass = new JPasswordField();
		pass.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		pass.setBounds(221, 142, 86, 20);
		panel.add(pass);
	}
}
