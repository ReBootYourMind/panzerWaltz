package panzerWaltz;

import java.awt.BorderLayout;
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

	private JPanel contentPane;
	private JTextField txtNamed;
	private JTextField txtNameless;
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
		
		JLabel lblImagesFound = new JLabel("images found");
		
		JLabel lblImagesNamed = new JLabel("images named");
		
		JButton btnUnohda = new JButton("Unohda");
		btnUnohda.addActionListener(new ActionListener() {
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
										.addComponent(btnUnohda)
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
						.addComponent(btnUnohda))
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
		//TODO update the findings to the labels
		doesntWork("update of the findings "+ howMany +"\n" + txtNamed.getText()+"\n"+ txtNameless.getText() );
	}
	
	/**
	 * unohtaa kuvat mit‰ etsittiin
	 * forgets all files
	 */
	private void forget() {
		boolean didItWork = programLogic.forget();
		if (!didItWork) {
			//TODO if failed
			//updte statistics to 0
			doesntWork("forget " + didItWork);
		}
	}
	
	/**
	 * nime‰‰ kuvat, jos ei onnistunut niin heitt‰‰ ilmoituksen
	 * names the files, if it doesn't work shows a popup
	 */
	private void nameFiles() {
		int howMany = programLogic.nameFiles();
		if (howMany < 0) {
			doesntWork("name " + howMany);
		}
	}
	/**
	 * pist‰‰ popupin jos joku ei toimi
	 * shows a popup
	 * @param what message that will be displayed
	 */
	private void doesntWork (String what) {
		JOptionPane.showMessageDialog(this, what);
	}
}
