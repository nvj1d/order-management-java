import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;

public class Commandes {

	JFrame frame;
	private JTextField coNum;
	private JTextField coDate;
	private JTable coTable;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Commandes window = new Commandes();
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
	public Commandes() {
		initialize();
		selectcommande();
	}
	
	public void selectcommande(){
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestd1","root","");
			Statement St1 = con.createStatement();			
			ResultSet rs1 = St1.executeQuery("select * from commandes");
			coTable.setModel(DbUtils.resultSetToTableModel(rs1));
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 30, 1089, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(Color.BLACK);
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(0, 0, 1073, 661);
		frame.getContentPane().add(panel_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(SystemColor.activeCaption);
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(160, 79, 904, 279);
		panel_1.add(panel_1_1);
		
		JLabel lblNewLabel_5 = new JLabel("num\u00E9ro : ");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(115, 71, 74, 24);
		panel_1_1.add(lblNewLabel_5);
		
		coNum = new JTextField();
		coNum.setColumns(10);
		coNum.setBounds(227, 73, 199, 20);
		panel_1_1.add(coNum);
		
		JLabel lblNewLabel_6 = new JLabel("Date:");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_6.setBounds(115, 132, 107, 24);
		panel_1_1.add(lblNewLabel_6);
		
		coDate = new JTextField();
		coDate.setColumns(10);
		coDate.setBounds(227, 134, 199, 20);
		panel_1_1.add(coDate);
		
		JButton ajt3 = new JButton("ajouter");
		ajt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestd1","root","");
					PreparedStatement add = con.prepareStatement("insert into commandes values(?,?)");
					add.setInt(1, Integer.valueOf(coNum.getText()));
					add.setString(2, coDate.getText());
					int row = add.executeUpdate();
					JOptionPane.showMessageDialog(null,"la commande ajouté avec succès!");
					con.close();
				}catch(Exception  ex) {
					JOptionPane.showMessageDialog(null,"Erreur la commande n\'est pas ajoutée!");
				}
				selectcommande();
			}
		});
		ajt3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ajt3.setBounds(242, 182, 97, 33);
		panel_1_1.add(ajt3);
		
		JButton mdf3 = new JButton("modifier");
		mdf3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(coNum.getText().isEmpty() || coDate.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"il y a des champs vide!");

				}else {
					try {
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestd1","root","");
						String upd= "UPDATE commandes SET date = '"+coDate.getText()+"' where num ="+coNum.getText()+"";
						Statement add = con.createStatement();
						add.executeUpdate(upd);
						JOptionPane.showMessageDialog(null,"la commande mis a jour avec succès!");

					}catch(Exception ex) {
						ex.printStackTrace();
					}
					selectcommande();
					
				}
			}
		});
		mdf3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		mdf3.setBounds(344, 182, 107, 33);
		panel_1_1.add(mdf3);
		
		JButton spr3 = new JButton("supprimer");
		spr3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(coNum.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"entrer le num de commande a supprimer");
				}else{
					try{
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestd1","root","");
						String Id = coNum.getText();
						String query = "delete from commandes where num = "+Id;
						Statement add = con.createStatement();
						add.executeUpdate(query);
						JOptionPane.showMessageDialog(null,"la commande a été supprimé");

					}catch(Exception ex){
						ex.printStackTrace();
					}
					selectcommande();
				}
			}
		});
		spr3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		spr3.setBounds(456, 182, 127, 33);
		panel_1_1.add(spr3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.activeCaption);
		panel_2.setLayout(null);
		panel_2.setBounds(10, 23, 140, 287);
		panel_1.add(panel_2);
		
		JLabel lblProduits = new JLabel("Produits");
		lblProduits.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Produits().frame.setVisible(true);
				frame.dispose();
			}
		});
		lblProduits.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblProduits.setBounds(23, 30, 65, 20);
		panel_2.add(lblProduits);
		
		JLabel lblNewLabel_1= new JLabel("Clients");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Clients().frame.setVisible(true);
				frame.dispose();
			}
		});
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(23, 90, 95, 20);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("factures");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Factures().frame.setVisible(true);
				frame.dispose();
			}
		});
		lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(23, 152, 65, 20);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("livraisons");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new livraisons().frame.setVisible(true);
				frame.dispose();
			}
		});
		lblNewLabel_3.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(23, 210, 79, 20);
		panel_2.add(lblNewLabel_3);
		
		coTable = new JTable();
		coTable.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		coTable.setBackground(SystemColor.inactiveCaptionBorder);
		coTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)coTable.getModel();
				int MyIndex = coTable.getSelectedRow();
				coNum.setText(model.getValueAt(MyIndex, 0).toString());
				coDate.setText(model.getValueAt(MyIndex, 1).toString());
				
			}
		});
		coTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"num", "date"
			}
		));
		coTable.setBounds(160, 446, 904, 204);
		panel_1.add(coTable);
		
		JLabel lblNewLabel_9 = new JLabel("Liste des Commandes:");
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_9.setBounds(366, 369, 341, 41);
		panel_1.add(lblNewLabel_9);
		
		JLabel lblNewLabel_4 = new JLabel("Gestion Des Commandes:");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_4.setBounds(366, 23, 329, 45);
		panel_1.add(lblNewLabel_4);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"num", "date"},
			},
			new String[] {
				"New column", "New column"
			}
		));
		table.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		table.setBounds(160, 430, 904, 16);
		panel_1.add(table);
	}

}
