package certificateSender.models;

import java.io.File;

public class ModelComponentsEmail {
	private String fromEmail; // Remetente
	private String toEmail; // Destinatário
	private String subject; // Assunto do e-mail
	private String body;
	private File attachment;

	
	public ModelComponentsEmail(String fromEmail, String toEmail, String subject, String body, File attachment) {
		super();
		this.fromEmail = fromEmail;
		this.toEmail = toEmail;
		this.subject = subject;
		this.body = body;
		this.attachment = attachment;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public File getAttachment() {
		return attachment;
	}

	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

}
