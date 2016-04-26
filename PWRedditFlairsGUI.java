package panzerWaltz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class PWRedditFlairsGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNamed;
	private final String namedTxt = "C:\\Users\\ReBootYourMind\\workspace\\omat javajutut\\src\\panzerWaltz\\named";
	private JTextField txtNameless;
	private final String namelessTxt = "C:\\Users\\ReBootYourMind\\workspace\\omat javajutut\\src\\panzerWaltz\\nameless";
	private JLabel lblImagesFound;
	private final String foundText = "images found";
	private JLabel lblImagesNamed;
	private final String namedText = "images named";
	private PWRedditFlairs programLogic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PWRedditFlairsGUI frame = new PWRedditFlairsGUI();
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
	public PWRedditFlairsGUI() {
		setMinimumSize(new Dimension(550, 300));
		programLogic = new PWRedditFlairs(this);
		setTitle("PW Reddit Flairs");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 513, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		panel_1.setMinimumSize(new Dimension(100, 10));
		panel_1.setMaximumSize(new Dimension(100, 32767));
		panel_1.setLayout(new BorderLayout(0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		txtNamed = new JTextField();
		txtNamed.setText(namedTxt);
		txtNamed.setColumns(10);
		
		txtNameless = new JTextField();
		txtNameless.setText(namelessTxt);
		txtNameless.setColumns(10);
		
		lblImagesFound = new JLabel(foundText);
		
		lblImagesNamed = new JLabel(namedText);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtNamed, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
						.addComponent(txtNameless, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblImagesNamed, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblImagesFound, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtNamed, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(txtNameless, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblImagesFound)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblImagesNamed)
					.addContainerGap(149, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.add(panel, BorderLayout.WEST);
		contentPane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setMaximumSize(new Dimension(100, 32767));
		panel_1.add(panel_2, BorderLayout.NORTH);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{53, 59, 0};
		gbl_panel_2.rowHeights = new int[]{23, 23, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JButton btnFind = new JButton("Find");
		GridBagConstraints gbc_btnFind = new GridBagConstraints();
		gbc_btnFind.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnFind.insets = new Insets(0, 0, 5, 5);
		gbc_btnFind.gridx = 0;
		gbc_btnFind.gridy = 0;
		panel_2.add(btnFind, gbc_btnFind);
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				find();
			}
		});
		
		JButton btnName = new JButton("Name");
		GridBagConstraints gbc_btnName = new GridBagConstraints();
		gbc_btnName.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnName.insets = new Insets(0, 0, 5, 0);
		gbc_btnName.gridx = 0;
		gbc_btnName.gridy = 1;
		panel_2.add(btnName, gbc_btnName);
		btnName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nameFiles();
			}
		});
		btnName.setBackground(Color.RED);
		
		JButton btnForget = new JButton("Forget");
		GridBagConstraints gbc_btnForget = new GridBagConstraints();
		gbc_btnForget.anchor = GridBagConstraints.NORTH;
		gbc_btnForget.gridx = 0;
		gbc_btnForget.gridy = 2;
		panel_2.add(btnForget, gbc_btnForget);
		
		JButton btnOutputlist = new JButton("outputList");
		btnOutputlist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				outputList();
			}
		});
		panel_1.add(btnOutputlist, BorderLayout.WEST);
		panel_1.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnFind, btnName, btnForget}));
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{panel, txtNamed, txtNameless, btnFind, btnName, btnForget}));
		btnForget.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				forget();
			}
		});
	}
	
	/**
	 * etsii kuvat
	 * finds all files from the locations given by the user it the two boxes
	 */
	private void find(){
		int howMany = programLogic.findFiles(txtNamed.getText(), txtNameless.getText());
		if (howMany < 0) {
			JOptionPane.showMessageDialog(this, "Something went wrong with the finding.");
			return;
		}
		else if (howMany == 0) {
			JOptionPane.showMessageDialog(this, "Didn't find anything from the locations given.");
			return;
		}
		lblImagesFound.setText(howMany + " " + foundText);
	}
	
	/**
	 * unohtaa kuvat mitä etsittiin
	 * forgets all files
	 */
	private void forget() {
		boolean didItWork = programLogic.forget();
		if (!didItWork) {
			JOptionPane.showMessageDialog(this, "Forgetting didn't work. Please restart the program.");
			return;
		}
		lblImagesFound.setText("0 " + foundText);
		lblImagesNamed.setText("0 " + namedText);
	}
	/**
	 * outputs a list of the flairs into a file
	 */
	private void outputList(){
		String where = txtNamed.getText();
		if (!programLogic.outputList(where)){
			JOptionPane.showMessageDialog(this, "There was something wrong with the output");
		}
	}
	
	/**
	 * nimeää kuvat, jos ei onnistunut niin heittää ilmoituksen
	 * names the files, if it doesn't work shows a popup
	 */
	private void nameFiles() {
		int answer =JOptionPane.showConfirmDialog(this, "Are you sure to rename the files in the lower box?");
		if (answer == JOptionPane.YES_OPTION) {
			int howMany = programLogic.nameFiles();
			if (howMany < 0) {
				doesntWork("Namimg returned: " + howMany + "\nSomething went wrong");
				return;
			}
			lblImagesNamed.setText(howMany + " " + namedText);
		}
	}
	/**
	 * pistää popupin jos joku ei toimi
	 * shows a popup
	 * @param what message that will be displayed
	 */
	protected void doesntWork (String what) {
		JOptionPane.showMessageDialog(this, what);
	}
}
