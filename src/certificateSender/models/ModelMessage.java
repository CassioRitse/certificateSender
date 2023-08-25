package certificateSender.models;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class ModelMessage {
	// Componentes do e-mail
	ModelComponentsEmail componentsEmail;
	// Mensagem de e-mail
	private Message message;
	// Parte de anexos do e-mail
	private MimeMultipart multipart;
	private MimeBodyPart attachmentsPart;
	private DataSource source; // Anexo
	// Corpo do e-mail
	private BodyPart textPart;

	public ModelMessage(Session session, ModelComponentsEmail emailInfos) throws AddressException, MessagingException {
		// Cria a mensagem de e-mail
		this.message = new MimeMessage(session);
		message.setFrom(new InternetAddress(emailInfos.getFromEmail()));// de quem é o email
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailInfos.getToEmail()));
		message.setSubject(emailInfos.getSubject()); // Titulo do email

		// Cria a parte de anexos do e-mail
		this.multipart = new MimeMultipart();

		// Adiciona o texto ao corpo do e-mail
		this.textPart = new MimeBodyPart();
		this.textPart.setText(emailInfos.getBody());
		multipart.addBodyPart(textPart);

		// adicionando anexo ao email
		this.attachmentsPart = new MimeBodyPart();
		this.source = new FileDataSource(emailInfos.getAttachment());
		this.attachmentsPart.setDataHandler(new DataHandler(this.source));
		this.attachmentsPart.setFileName(source.getName());

		// adicionando a parte de anexo no resto do documento
		multipart.addBodyPart(attachmentsPart);

		// Define a estrutura como conteúdo da mensagem
		message.setContent(multipart);
	}

	public Message getMessage() {
		return message;
	}

}
