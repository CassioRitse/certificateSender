package certificateSender.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import certificateSender.config.ConfigServerSMTP;
import certificateSender.execption.EmailSenderExecption;
import certificateSender.models.ModelComponentsEmail;
import certificateSender.models.ModelMessage;
import certificateSender.view.MainView;
import utils.CertificateReader;

public class ControllerEmailSender {
	private MainView mainView;
	private ConfigServerSMTP configSMTP;
	private File directoryCertificates;
	private List<ModelComponentsEmail> listMensagemToSend;

	public ControllerEmailSender(ConfigServerSMTP configSMTP) {
		this.mainView = new MainView();
		this.configSMTP = configSMTP;
		this.listMensagemToSend = new ArrayList<>();

		this.mainView.getBtn_selectDirectory().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSelectDirectory();
			}
		});

		this.mainView.getBtn_sendCertificates().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					btnSendCertificates();
				} catch (EmailSenderExecption e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		this.mainView.setVisible(true);
	}

	protected void btnSelectDirectory() {
		JFileChooser fc = new JFileChooser();

		// restringe a amostra a diretorios apenas
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int res = fc.showOpenDialog(null);

		if (res == JFileChooser.APPROVE_OPTION) {
			directoryCertificates = fc.getSelectedFile();
			mainView.getLbl_nameDirectory().setText(directoryCertificates.getName() + "...");
		} else
			JOptionPane.showMessageDialog(null, "Voce nao selecionou nenhum diretorio.");
	}

	protected void btnSendCertificates() throws EmailSenderExecption {
		if (!mainView.fieldValidation()) {
			return;
		}

		if (this.directoryCertificates == null) {
			JOptionPane.showMessageDialog(null, "Diretorio não selecionado");
			return;
		}

		// Leitor de certificados iniciado com no diretorio
		// Ira validar os arquivos e o proprio diretorio (retorna erro se encontrar)
		// adicionando todos os componentes de todos emails que serão enviados
		// Componentes (Endereço, Titulo, Mensagem, Corpo, Anexo...)
		for (File certificate : CertificateReader.getListCertificates(directoryCertificates)) {
			// Utiliza a classe ComponentsEmail para preenchar
			// as informações personalizadas de cada email que será enviado
			ModelComponentsEmail components = new ModelComponentsEmail(this.mainView.getTxtf_emailFrom().getText(),
					certificate.getName().replace(".pdf", ""), this.mainView.getTxtf_subject().getText(),
					this.mainView.getTxt_body().getText(), certificate);

			listMensagemToSend.add(components); // quardado a informação de cada email na lista
		}

		// Cria a seção de autenticação com senha e email
		Session session = Session.getInstance(this.configSMTP.getProps(), new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				String email = mainView.getTxtf_emailFrom().getText();
				char[] password = mainView.getPasswordf_password().getPassword();
				return new PasswordAuthentication(email, new String(password));
			}
		});

		// A criar um aviso de carregamento do envio de emails
		// A FAZER 
		//
		// TELA DE CARRAGAMENTO
		//
		// A FAZER

		try {
			for (ModelComponentsEmail components : listMensagemToSend) {
				// Cria a Mensagem com base na seção e os Components do Email
				ModelMessage messageEmail = new ModelMessage(session, components);
				Transport.send(messageEmail.getMessage()); // Envia o Email
			}
			
			JOptionPane.showMessageDialog(mainView, "Todos os emails, enviados com sucesso!", "Sucesso", 1);

		} catch (AddressException e) {
			throw new EmailSenderExecption("Existem (email) 'arquivos' com nomes formatado errado "
					+ "\npadronizar os nomes do arquivo de maneira correta " + "\nEx: exemploemail@gmail.com");
		} catch (AuthenticationFailedException e) {
			throw new EmailSenderExecption("Não foi possivel logar na sua conta Email");
		} catch (SendFailedException e) {
			throw new EmailSenderExecption("Não foi possivel enviar os seguintes Emails \n" + e.getInvalidAddresses());
		} catch (Exception e) {
			throw new EmailSenderExecption("ERRO desconhecido \n" + e.getMessage());
		}
		
		

	}

}
