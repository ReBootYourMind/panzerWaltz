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

public class PWRedditFlairsGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNamed;
	private JTextField txtNameless;
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
		programLogic = new PWRedditFlairs(this);
		setTitle("PW Reddit Flairs");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txtNamed = new JTextField();
		txtNamed.setText("Named");
		txtNamed.setColumns(10);
		
		txtNameless = new JTextField();
		txtNameless.setText("Nameless");
		txtNameless.setColumns(10);
		
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				find();
			}
		});
		
		JButton btnName = new JButton("Name");
		btnName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nameFiles();
			}
		});
		btnName.setBackground(Color.RED);
		
		lblImagesFound = new JLabel(foundText);
		
		lblImagesNamed = new JLabel(namedText);
		
		JButton btnForget = new JButton("Forget");
		btnForget.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				forget();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtNameless, Alignment.TRAILING)
								.addComponent(txtNamed, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnFind))
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(btnForget)
										.addComponent(btnName)))))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblImagesNamed, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblImagesFound, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)))
					.addContainerGap(79, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNamed, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnFind))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNameless, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblImagesFound)
						.addComponent(btnForget))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblImagesNamed)
					.addContainerGap(148, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
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
	private void doesntWork (String what) {
		JOptionPane.showMessageDialog(this, what);
	}
}
