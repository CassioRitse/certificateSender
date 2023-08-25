package certificateSender;

import certificateSender.config.ConfigServerSMTP;
import certificateSender.controller.ControllerEmailSender;
import certificateSender.execption.EmailSenderExecption;

public class Main {
	public static void main(String[] args) throws EmailSenderExecption {
		new ControllerEmailSender(new ConfigServerSMTP());
	}
}
