package certificateSender.execption;

public class EmailSenderExecption extends Exception {
	private static final long serialVersionUID = 1L;

	public EmailSenderExecption() {
		super("Nenhuma mensagem foi especificada");
	}
	
	public EmailSenderExecption(String msg) {
		super(msg);
	}
}
