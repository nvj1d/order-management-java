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

public class Factures {

	JFrame frame;
	private JTextField fNum;
	private JTextField fDate;
	private JTable fTable;
	private JTextField fMontant;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Factures window = new Factures();
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
	public Factures() {
		initialize();
		selectfacture();
	}
	public void selectfacture(){
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestd1","root","");
			Statement St1 = con.createStatement();			
			ResultSet rs1 = St1.executeQuery("select * from factures");
			fTable.setModel(DbUtils.resultSetToTableModel(rs1));
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
		lblNewLabel_5.setBounds(128, 31, 74, 24);
		panel_1_1.add(lblNewLabel_5);
		
		fNum = new JTextField();
		fNum.setColumns(10);
		fNum.setBounds(240, 33, 199, 20);
		panel_1_1.add(fNum);
		
		JLabel lblNewLabel_6 = new JLabel("Date:");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_6.setBounds(128, 92, 107, 24);
		panel_1_1.add(lblNewLabel_6);
		
		fDate = new JTextField();
		fDate.setColumns(10);
		fDate.setBounds(240, 94, 199, 20);
		panel_1_1.add(fDate);
		
		JButton ajout4 = new JButton("ajouter");
		ajout4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestd1","root","");
					PreparedStatement add = con.prepareStatement("insert into factures values(?,?,?)");
					add.setInt(1, Integer.valueOf(fNum.getText()));
					add.setString(2, fDate.getText());
					add.setString(3, fMontant.getText());
					int row = add.executeUpdate();
					JOptionPane.showMessageDialog(null,"la facture ajouté avec succès!");
					con.close();
				}catch(Exception  ex) {
					JOptionPane.showMessageDialog(null,"Erreur la facture n\'est pas ajoutée!");
				}
				selectfacture();
			}
		});
		ajout4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ajout4.setBounds(173, 200, 97, 33);
		panel_1_1.add(ajout4);
		
		JButton modif4 = new JButton("modifier");
		modif4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fNum.getText().isEmpty() || fDate.getText().isEmpty() || fMontant.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"il y a des champs vide!");

				}else {
					try {
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestd1","root","");
						String upd= "UPDATE factures SET date = '"+fDate.getText()+"',montant= '"+fMontant.getText()+"' where num ="+fNum.getText()+"";
						Statement add = con.createStatement();
						add.executeUpdate(upd);
						JOptionPane.showMessageDialog(null,"la commande mis a jour avec succès!");

					}catch(Exception ex) {
						ex.printStackTrace();
					}
					selectfacture();
					
				}
			}
		});
		modif4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		modif4.setBounds(275, 200, 107, 33);
		panel_1_1.add(modif4);
		
		JButton suppr4 = new JButton("supprimer");
		suppr4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fNum.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"entrer le num de facture a supprimer");
				}else{
					try{
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestd1","root","");
						String Id = fNum.getText();
						String query = "delete from factures where num = "+Id;
						Statement add = con.createStatement();
						add.executeUpdate(query);
						JOptionPane.showMessageDialog(null,"la facture a été supprimé");

					}catch(Exception ex){
						ex.printStackTrace();
					}
					selectfacture();
			}
				}
		});
		suppr4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		suppr4.setBounds(387, 200, 127, 33);
		panel_1_1.add(suppr4);
		
		fMontant = new JTextField();
		fMontant.setColumns(10);
		fMontant.setBounds(240, 147, 199, 20);
		panel_1_1.add(fMontant);
		
		JLabel lblNewLabel_5_1 = new JLabel("montant: ");
		lblNewLabel_5_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_5_1.setBounds(128, 145, 74, 24);
		panel_1_1.add(lblNewLabel_5_1);
		
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
		
		fTable = new JTable();
		fTable.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		fTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)fTable.getModel();
				int MyIndex = fTable.getSelectedRow();
				fNum.setText(model.getValueAt(MyIndex, 0).toString());
				fDate.setText(model.getValueAt(MyIndex, 1).toString());
				fMontant.setText(model.getValueAt(MyIndex, 2).toString());
			}
		});
		fTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"num", "date", "montant"
			}
		));
		fTable.setBounds(160, 441, 904, 209);
		panel_1.add(fTable);
		
		JLabel lblNewLabel_9 = new JLabel("Liste des Factures:");
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_9.setBounds(366, 369, 341, 41);
		panel_1.add(lblNewLabel_9);
		
		JLabel lblNewLabel_4 = new JLabel("Gestion Des Factures:");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_4.setBounds(341, 23, 307, 45);
		panel_1.add(lblNewLabel_4);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"num", "date", "montant"},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		table.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		table.setBounds(160, 421, 904, 16);
		panel_1.add(table);
	}

}
