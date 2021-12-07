import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;

public class Clients {

	JFrame frame;
	private JTextField cNum;
	private JTextField cNom;
	private JTextField cPhone;
	private JTable cTable;
	private JTextField cPrenom;
	private JTextField cAddresse;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clients window = new Clients();
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
	public Clients() {
		initialize();
		selectclient();
	}
	public void selectclient(){
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestd1","root","");
			Statement St1 = con.createStatement();			
			ResultSet rs1 = St1.executeQuery("select * from clients");
			cTable.setModel(DbUtils.resultSetToTableModel(rs1));
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
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1073, 661);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(Color.BLACK);
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(0, 0, 1073, 661);
		panel.add(panel_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(SystemColor.activeCaption);
		panel_1_1.setBounds(160, 79, 904, 279);
		panel_1.add(panel_1_1);
		panel_1_1.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("num\u00E9ro : ");
		lblNewLabel_5.setBounds(94, 52, 74, 24);
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel_1_1.add(lblNewLabel_5);
		
		cNum = new JTextField();
		cNum.setBounds(206, 54, 199, 20);
		cNum.setColumns(10);
		panel_1_1.add(cNum);
		
		JLabel lblNewLabel_7 = new JLabel("Addresse: ");
		lblNewLabel_7.setBounds(426, 52, 97, 24);
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel_1_1.add(lblNewLabel_7);
		
		JLabel lblNewLabel_6 = new JLabel("Nom: ");
		lblNewLabel_6.setBounds(94, 113, 107, 24);
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel_1_1.add(lblNewLabel_6);
		
		cNom = new JTextField();
		cNom.setBounds(206, 115, 199, 20);
		cNom.setColumns(10);
		panel_1_1.add(cNom);
		
		JLabel lblNewLabel_8 = new JLabel("Telephone:");
		lblNewLabel_8.setBounds(426, 113, 92, 24);
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel_1_1.add(lblNewLabel_8);
		
		cPhone = new JTextField();
		cPhone.setBounds(528, 117, 187, 20);
		cPhone.setColumns(10);
		panel_1_1.add(cPhone);
		
		JLabel lblNewLabel_6_1 = new JLabel("Prenom: ");
		lblNewLabel_6_1.setBounds(94, 172, 107, 24);
		lblNewLabel_6_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel_1_1.add(lblNewLabel_6_1);
		
		cPrenom = new JTextField();
		cPrenom.setBounds(206, 176, 199, 20);
		cPrenom.setColumns(10);
		panel_1_1.add(cPrenom);
		
		JButton ajout2 = new JButton("ajouter");
		ajout2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestd1","root","");
					PreparedStatement add = con.prepareStatement("insert into clients values(?,?,?,?,?)");
					add.setInt(1, Integer.valueOf(cNum.getText()));
					add.setString(2, cNom.getText());
					add.setString(3, cPrenom.getText());
					add.setString(4, cAddresse.getText());
					add.setString(5, cPhone.getText());
					int row = add.executeUpdate();
					JOptionPane.showMessageDialog(null,"le client ajouté avec succès!");
					con.close();
				}catch(Exception  ex) {
					JOptionPane.showMessageDialog(null,"Erreur le produit n\'est pas ajouté!");
				}
				selectclient();
			}
		});
		ajout2.setBounds(242, 223, 97, 33);
		ajout2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel_1_1.add(ajout2);
		
		JButton modif2 = new JButton("modifier");
		modif2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cNum.getText().isEmpty() || cNom.getText().isEmpty() || cPrenom.getText().isEmpty() || cAddresse.getText().isEmpty() || cPhone.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"il y a des champs vide!");

				}else {
					try {
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestd1","root","");
						String upd= "UPDATE clients SET nom = '"+cNom.getText()+"',prenom = '"+cPrenom.getText()+"',addresse='"+cAddresse.getText()+"',telephone='"+cPhone.getText()+"' where num ="+cNum.getText()+"";
						Statement add = con.createStatement();
						add.executeUpdate(upd);
						JOptionPane.showMessageDialog(null,"le client mis a jour avec succès!");

					}catch(Exception ex) {
						ex.printStackTrace();
					}
					selectclient();
					
				}
			}
		});
		modif2.setBounds(344, 223, 107, 33);
		modif2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel_1_1.add(modif2);
		
		JButton supp2 = new JButton("supprimer");
		supp2.setBounds(456, 223, 127, 33);
		supp2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cNum.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"entrer le num de client a supprimer");
				}else{
					try{
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestd1","root","");
						String Id = cNum.getText();
						String query = "delete from clients where num = "+Id;
						Statement add = con.createStatement();
						add.executeUpdate(query);
						
						JOptionPane.showMessageDialog(null,"le client a été supprimé");

					}catch(Exception ex){
						ex.printStackTrace();
					}
					selectclient();
				}
			}
		});
		supp2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel_1_1.add(supp2);
		
		cAddresse = new JTextField();
		cAddresse.setColumns(10);
		cAddresse.setBounds(528, 56, 187, 20);
		panel_1_1.add(cAddresse);
		
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
		
		JLabel lblNewLabel_1 = new JLabel("Commandes");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Commandes().frame.setVisible(true);
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
		
		cTable = new JTable();
		cTable.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		cTable.setBackground(SystemColor.inactiveCaptionBorder);
		cTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)cTable.getModel();
				int MyIndex = cTable.getSelectedRow();
				cNum.setText(model.getValueAt(MyIndex, 0).toString());
				cNom.setText(model.getValueAt(MyIndex, 1).toString());
				cPrenom.setText(model.getValueAt(MyIndex, 2).toString());
				cAddresse.setText(model.getValueAt(MyIndex, 3).toString());
				cPhone.setText(model.getValueAt(MyIndex, 4).toString());
			}
		});
		cTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"num", "nom", "penom", "adresse", "telephone"
			}
		));
		cTable.setBounds(160, 438, 904, 204);
		panel_1.add(cTable);
		
		JLabel lblNewLabel_9 = new JLabel("Liste des Clients:");
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_9.setBounds(427, 369, 244, 41);
		panel_1.add(lblNewLabel_9);
		
		JLabel lblNewLabel_4 = new JLabel("Gestion Des Clients:");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_4.setBounds(404, 23, 307, 45);
		panel_1.add(lblNewLabel_4);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"num", "nom", "prenom", "addresse", "telephone"},
			},
			new String[] {
				"num", "New column", "New column", "New column", "New column"
			}
		));
		table.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		table.setBounds(160, 421, 904, 16);
		panel_1.add(table);
	}
}
