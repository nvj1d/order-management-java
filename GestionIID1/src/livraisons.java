import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Label;

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

public class livraisons {

	JFrame frame;
	private JTextField lNum;
	private JTextField lDate;
	private JTable lTable;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					livraisons window = new livraisons();
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
	public livraisons() {
		initialize();
		selectlivraison();
	}
	public void selectlivraison(){
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestd1","root","");
			Statement St1 = con.createStatement();			
			ResultSet rs1 = St1.executeQuery("select * from livraisons");
			lTable.setModel(DbUtils.resultSetToTableModel(rs1));
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
		panel_1_1.setBounds(160, 79, 904, 260);
		panel_1.add(panel_1_1);
		
		JLabel lblNewLabel_5 = new JLabel("num\u00E9ro : ");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(143, 61, 74, 24);
		panel_1_1.add(lblNewLabel_5);
		
		lNum = new JTextField();
		lNum.setColumns(10);
		lNum.setBounds(255, 63, 199, 20);
		panel_1_1.add(lNum);
		
		JLabel lblNewLabel_6 = new JLabel("Date:");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_6.setBounds(143, 122, 107, 24);
		panel_1_1.add(lblNewLabel_6);
		
		lDate = new JTextField();
		lDate.setColumns(10);
		lDate.setBounds(255, 124, 199, 20);
		panel_1_1.add(lDate);
		
		JButton ajt5 = new JButton("ajouter");
		ajt5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestd1","root","");
					PreparedStatement add = con.prepareStatement("insert into livraisons values(?,?)");
					add.setInt(1, Integer.valueOf(lNum.getText()));
					add.setString(2, lDate.getText());
					int row = add.executeUpdate();
					JOptionPane.showMessageDialog(null,"la livraison ajouté avec succès!");
					con.close();
				}catch(Exception  ex) {
					JOptionPane.showMessageDialog(null,"Erreur la livraison n\'est pas ajoutée!");
				}
				selectlivraison();
			}
		});
		ajt5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ajt5.setBounds(174, 175, 97, 33);
		panel_1_1.add(ajt5);
		
		JButton modif5 = new JButton("modifier");
		modif5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lNum.getText().isEmpty() || lDate.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"il y a des champs vide!");

				}else {
					try {
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestd1","root","");
						String upd= "UPDATE livraisons SET date = '"+lDate.getText()+"' where num ="+lNum.getText()+"";
						Statement add = con.createStatement();
						add.executeUpdate(upd);
						JOptionPane.showMessageDialog(null,"la livraison mis a jour avec succès!");

					}catch(Exception ex) {
						ex.printStackTrace();
					}
					selectlivraison();
					
				}
			}
		});
		modif5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		modif5.setBounds(276, 175, 107, 33);
		panel_1_1.add(modif5);
		
		JButton sp5 = new JButton("supprimer");
		sp5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lNum.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"entrer le num de laivraision a supprimer");
				}else{
					try{
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestd1","root","");
						String Id = lNum.getText();
						String query = "delete from livraisons where num = "+Id;
						Statement add = con.createStatement();
						add.executeUpdate(query);
						JOptionPane.showMessageDialog(null,"la laivraision a été supprimé");

					}catch(Exception ex){
						ex.printStackTrace();
					}
					selectlivraison();
				}
			}
		});
		sp5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		sp5.setBounds(388, 175, 127, 33);
		panel_1_1.add(sp5);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(153, 180, 209));
		panel_2.setLayout(null);
		panel_2.setBounds(10, 23, 140, 287);
		panel_1.add(panel_2);
		
		/*
		JLabel lblProduits = new JLabel("Produits");
		lblProduits.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblProduits.setBounds(23, 30, 65, 20);
		panel_2.add(lblProduits);
		*/
		
		JLabel lblNewLabel_1 = new JLabel("Clients");
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
		
		JLabel lblNewLabel_2 = new JLabel("Commandes");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Commandes().frame.setVisible(true);
				frame.dispose();
			}
		});
		lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(23, 152, 95, 20);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Factures");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Factures().frame.setVisible(true);
				frame.dispose();
			}
		});
		lblNewLabel_3.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(23, 210, 79, 20);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1_1 = new JLabel("produits");
		lblNewLabel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Produits().frame.setVisible(true);
				frame.dispose();
			}
		});
		lblNewLabel_1_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(23, 39, 95, 20);
		panel_2.add(lblNewLabel_1_1);
		
		
		lTable = new JTable();
		lTable.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lTable.setBackground(SystemColor.inactiveCaptionBorder);
		lTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)lTable.getModel();
				int MyIndex = lTable.getSelectedRow();
				lNum.setText(model.getValueAt(MyIndex, 0).toString());
				lDate.setText(model.getValueAt(MyIndex, 1).toString());
			}
		});
		lTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"num", "date"
			}
		));
		lTable.setBounds(160, 436, 904, 214);
		panel_1.add(lTable);
		
		JLabel lblNewLabel_9 = new JLabel("Liste des livraisons:");
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_9.setBounds(366, 369, 341, 41);
		panel_1.add(lblNewLabel_9);
		
		JLabel lblNewLabel_4 = new JLabel("Gestion Des Livraisons:");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_4.setBounds(346, 23, 307, 45);
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
		table.setBounds(160, 419, 904, 16);
		panel_1.add(table);
	}
}
