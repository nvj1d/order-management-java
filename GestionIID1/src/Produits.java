import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;

public class Produits {

	JFrame frame;
	private JTextField pNum;
	private JTextField pNom;
	private JTextField pPrix;
	private JTable pTable;
	private JTextField Pquantite;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Produits window = new Produits();
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
	public Produits() {
		initialize();
		selectproduit();
	}
	
	public void selectproduit(){
		try{
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestd1","root","");
			Statement St1 = con.createStatement();			
			ResultSet rs1 = St1.executeQuery("select * from produits");
			pTable.setModel(DbUtils.resultSetToTableModel(rs1));
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.BLACK);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setForeground(Color.BLACK);
		panel.setBounds(0, 0, 1073, 661);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(160, 79, 904, 269);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("num\u00E9ro : ");
		lblNewLabel_5.setBounds(94, 55, 134, 21);
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel_1.add(lblNewLabel_5);
		
		pNum = new JTextField();
		pNum.setBounds(226, 58, 182, 20);
		panel_1.add(pNum);
		pNum.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("quatit\u00E9: ");
		lblNewLabel_7.setBounds(442, 55, 89, 21);
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel_1.add(lblNewLabel_7);
		
		JLabel lblNewLabel_6 = new JLabel("Nom: ");
		lblNewLabel_6.setBounds(94, 113, 112, 21);
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel_1.add(lblNewLabel_6);
		
		pNom = new JTextField();
		pNom.setBounds(226, 114, 182, 20);
		panel_1.add(pNom);
		pNom.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("prix: ");
		lblNewLabel_8.setBounds(442, 113, 60, 21);
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel_1.add(lblNewLabel_8);
		
		pPrix = new JTextField();
		pPrix.setBounds(541, 115, 182, 20);
		panel_1.add(pPrix);
		pPrix.setColumns(10);
		
		JButton btnNewButton = new JButton("ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestd1","root","");
					//Statement stm = con.createStatement();
					PreparedStatement add = con.prepareStatement("insert into produits values(?,?,?,?)");
					add.setInt(1, Integer.valueOf(pNum.getText()));
					add.setString(2, pNom.getText());
					add.setInt(3, Integer.valueOf(Pquantite.getText()));
					add.setInt(4, Integer.valueOf(pPrix.getText()));
					int row = add.executeUpdate();
					JOptionPane.showMessageDialog(null,"le produit ajouté avec succès!");
					con.close();
					selectproduit();

				}catch(Exception  ex) {
					JOptionPane.showMessageDialog(null,"Erreur le produit n\'est pas ajouté!");
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton.setBounds(204, 176, 89, 23);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("modifier");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pNum.getText().isEmpty() || pNom.getText().isEmpty() || Pquantite.getText().isEmpty() || pPrix.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"il y a des champs vide!");

				}else {
					try {
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestd1","root","");
						String upd= "UPDATE produits SET nom = '"+pNom.getText()+"',quantite="+Pquantite.getText()+",prix="+pPrix.getText()+" where num ="+pNum.getText()+"";
						Statement add = con.createStatement();
						add.executeUpdate(upd);
						selectproduit();
						JOptionPane.showMessageDialog(null,"le produit mis a jour avec succès!");

					}catch(Exception ex) {
						ex.printStackTrace();
					}
					
				}
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton_1.setBounds(303, 176, 112, 23);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("supprimer");
		
		btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pNum.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"entrer le num de produit a supprimer");
				}else{
					try{
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestd1","root","");
						String Id = pNum.getText();
						String query = "delete from produits where num = "+Id;
						Statement add = con.createStatement();
						add.executeUpdate(query);
						
						JOptionPane.showMessageDialog(null,"le produit a été supprimé");

					}catch(Exception ex){
						ex.printStackTrace();
					}
					selectproduit();
				}
			}
		});
		btnNewButton_2.setBounds(425, 176, 125, 23);
		panel_1.add(btnNewButton_2);
		
		Pquantite = new JTextField();
		Pquantite.setColumns(10);
		Pquantite.setBounds(541, 57, 182, 20);
		panel_1.add(Pquantite);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.activeCaption);
		panel_2.setBounds(10, 23, 140, 287);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("clients");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Clients().frame.setVisible(true);
				frame.dispose();
			}
		});
		lblNewLabel.setBounds(23, 30, 53, 20);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Commandes");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Commandes().frame.setVisible(true);
				frame.dispose();
			}
		});
		lblNewLabel_1.setBounds(23, 90, 95, 20);
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("factures");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Factures().frame.setVisible(true);
				frame.dispose();
			}
		});
		lblNewLabel_2.setBounds(23, 152, 65, 20);
		lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("livraisons");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new livraisons().frame.setVisible(true);
				frame.dispose();
			}
		});
		lblNewLabel_3.setBounds(23, 210, 79, 20);
		lblNewLabel_3.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		panel_2.add(lblNewLabel_3);
		
		pTable = new JTable();
		pTable.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		pTable.setBackground(SystemColor.inactiveCaptionBorder);
		pTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)pTable.getModel();
				int MyIndex = pTable.getSelectedRow(); 
				pNum.setText(model.getValueAt(MyIndex, 0).toString());
				pNom.setText(model.getValueAt(MyIndex, 1).toString());
				Pquantite.setText(model.getValueAt(MyIndex, 2).toString());
				pPrix.setText(model.getValueAt(MyIndex, 3).toString());
			}
		});
		
		pTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"num", "nom", "quantite", "prix"
			}
		));
		pTable.setBounds(160, 421, 904, 229);
		panel.add(pTable);
		
		JLabel lblNewLabel_9 = new JLabel("Liste des Produits:");
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_9.setBounds(427, 359, 244, 41);
		panel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_4 = new JLabel("Gestion Des Produits:");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_4.setBounds(378, 23, 307, 45);
		panel.add(lblNewLabel_4);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"num", "nom", "quantite", "prix"},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		table.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		table.setBounds(160, 405, 904, 16);
		panel.add(table);
		frame.setBounds(100, 30, 1089, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
