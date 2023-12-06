package POSUI;

import javax.swing.JPanel;


import POSPD.Register;
import POSPD.Store;
import POSPD.TaxCategory;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.SwingConstants;

public class TaxCategoryListPanel extends JPanel
{
	JButton  btnNewButton_1;
	JButton btnNewButton;
	
	/**
	 * Create the panel.
	 */
	public TaxCategoryListPanel(JFrame currentFrame, Store store)
	{
		DefaultListModel<TaxCategory> listModel = new DefaultListModel<TaxCategory>();
		for (TaxCategory taxCat : store.getTaxCategories().values()) {
			listModel.addElement(taxCat);
		}

		JList<TaxCategory> list = new JList<TaxCategory>(listModel);
		list.setBounds(104, 65, 242, 126);
		setLayout(null);

		JLabel lblTaxCategoriesList = new JLabel("Tax Categories List");
		lblTaxCategoriesList.setBounds(-189, 25, 776, 16);
		lblTaxCategoriesList.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTaxCategoriesList);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				store.removeTaxCategory(list.getSelectedValue());
				
				 listModel.removeElement(list.getSelectedValue());
				 btnNewButton.setEnabled(false);
			}
		});
		btnNewButton.setBounds(307, 234, 85, 21);
		add(btnNewButton);
		btnNewButton.setEnabled(false);
		
		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryEditPanel(currentFrame, store, list.getSelectedValue(), false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(187, 234, 85, 21);
		add(btnNewButton_1);
		btnNewButton_1.setEnabled(false);

		
		btnNewButton_1.setEnabled(false);
		btnNewButton.setEnabled(false);
		
		
		
		add(list);
		list.addListSelectionListener(new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent e) 
			{
				System.out.println(list.getSelectedValue().isUsed());
				btnNewButton_1.setEnabled(false);
				btnNewButton.setEnabled(false);
				if(list.getSelectedValue() != null)
				{
				 btnNewButton_1.setEnabled(true);
				 
					if(list.getSelectedValue().isUsed())
						btnNewButton.setEnabled(false);
					else
						btnNewButton.setEnabled(true);
				}
				else
				{
					btnNewButton_1.setEnabled(false);
					btnNewButton.setEnabled(false);
				}
			}
		});
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(104, 65, 72, 75);
		add(scrollPane);
		
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(74, 232, 97, 25);
		btnAdd.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryEditPanel(currentFrame, store, new TaxCategory(), true));
				currentFrame.getContentPane().revalidate();
			}
		});
		add(btnAdd);
		
		
	}
}
