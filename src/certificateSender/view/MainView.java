package certificateSender.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;

public class MainView extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField txtf_subject;
	private JTextField txtf_emailFrom;
	private JPasswordField passf_password;
	private JTextArea txt_body;
	private JButton btn_selectDirectory;
	private JButton btn_sendCertificates;
	private JLabel lbl_nameDirectory;

	/**
	 * Create the frame.
	 */
	public MainView() {
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("CertificateSender");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 422, 555);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("CertificateSender");
		lblNewLabel.setForeground(new Color(81, 180, 217));
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Gill Sans MT", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(10, 11, 376, 19);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(81, 180, 217));
		panel_1.setBorder(new LineBorder(SystemColor.inactiveCaptionBorder, 3, true));
		panel_1.setBounds(10, 108, 376, 387);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Subject:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setBounds(10, 11, 46, 14);
		panel_1.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Body:");
		lblNewLabel_4.setBounds(10, 36, 46, 14);
		panel_1.add(lblNewLabel_4);

		txtf_subject = new JTextField();
		txtf_subject.setBackground(new Color(232, 230, 232));
		txtf_subject.setBounds(66, 8, 300, 20);
		panel_1.add(txtf_subject);
		txtf_subject.setColumns(10);

		txt_body = new JTextArea();
		txt_body.setBackground(new Color(232, 230, 232));
		txt_body.setBorder(new LineBorder(SystemColor.controlHighlight, 1, true));
		txt_body.setLineWrap(true);
		txt_body.setBounds(10, 53, 356, 226);
		panel_1.add(txt_body);

		lbl_nameDirectory = new JLabel("Certificates to be sent.");
		lbl_nameDirectory.setBounds(10, 290, 172, 14);
		panel_1.add(lbl_nameDirectory);

		btn_selectDirectory = new JButton("Select  Directory");
		btn_selectDirectory.setBounds(187, 286, 179, 23);
		panel_1.add(btn_selectDirectory);

		btn_sendCertificates = new JButton("Send Certificates");
		btn_sendCertificates.setBounds(107, 351, 158, 23);
		panel_1.add(btn_sendCertificates);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(SystemColor.inactiveCaptionBorder, 3, true));
		panel_2.setBackground(new Color(81, 180, 217));
		panel_2.setBounds(10, 38, 376, 63);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("From:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(10, 14, 46, 14);
		panel_2.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Password/App key:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setBounds(10, 35, 110, 14);
		panel_2.add(lblNewLabel_2);

		txtf_emailFrom = new JTextField();
		txtf_emailFrom.setBackground(new Color(232, 230, 232));
		txtf_emailFrom.setColumns(10);
		txtf_emailFrom.setBounds(66, 11, 300, 20);
		panel_2.add(txtf_emailFrom);

		passf_password = new JPasswordField();
		passf_password.setBackground(new Color(232, 230, 232));
		passf_password.setBounds(131, 32, 235, 20);
		panel_2.add(passf_password);
	}

	// validação dos campos do formulario de envio de email
	public Boolean fieldValidation() {
		if (this.getTxtf_emailFrom().getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Voce não preencheu o seu email");
			return false;

		} else if (this.getPasswordf_password().getPassword().toString().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Voce não preencheu a senha");
			return false;

		} else if (this.getTxtf_subject().getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Voce não preencheu o titulo do email");
			return false;

		} else if (this.getTxt_body().getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "Voce não preencheu o corpo do email");
			return false;
		}

		return true;

	}

	public JButton getBtn_selectDirectory() {
		return btn_selectDirectory;
	}

	public JButton getBtn_sendCertificates() {
		return btn_sendCertificates;
	}

	public JTextField getTxtf_subject() {
		return txtf_subject;
	}

	public JTextField getTxtf_emailFrom() {
		return txtf_emailFrom;
	}

	public JPasswordField getPasswordf_password() {
		return passf_password;
	}

	public JTextArea getTxt_body() {
		return txt_body;
	}

	public JLabel getLbl_nameDirectory() {
		return lbl_nameDirectory;
	}

	public void setLbl_nameDirectory(JLabel lbl_nameDirectory) {
		this.lbl_nameDirectory = lbl_nameDirectory;
	}

}
