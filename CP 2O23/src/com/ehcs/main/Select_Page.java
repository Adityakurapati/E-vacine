package com.ehcs.main;

import java.awt.EventQueue; 
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ehcs.e_medico.services.MedicineLists;
import com.ehcs.vacine.services.DashBoard;
import com.ehcs.vacine.services.Login;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Select_Page extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String adhar = null;
					Select_Page frame = new Select_Page(adhar);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Select_Page(String adhar ) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("MEDICINE");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				MedicineLists ml = new MedicineLists();
				ml.setVisible(true);
				setVisible(false);	
			}
		});
		btnNewButton.setBounds(324, 413,  154, 47);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 23));
		contentPane.add(btnNewButton);
		
		JButton btnVaccine = new JButton("VACCINE ");
		
		btnVaccine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
//				String adhar = null;
				DashBoard r = new DashBoard(adhar);
				Login n = new Login();
				r.setVisible(true);
				n.setVisible(false);	
			}
		});
		btnVaccine.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btnVaccine.setBounds(60, 413, 154, 47);
		contentPane.add(btnVaccine);
	}

}