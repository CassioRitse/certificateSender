package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import certificateSender.execption.EmailSenderExecption;

public class CertificateReader {

	private static List<File> listCertificates;

	public static List<File> getListCertificates(File directory) throws EmailSenderExecption {
		try {
			// Verifica se o diretorio é invalido
			System.out.println(directory.listFiles().length);
			if (!directory.isDirectory() || (directory.listFiles().length < 1)) {
				throw new Exception("Selecionado um diretorio vazio ou invalido");
			}

			listCertificates = new ArrayList<File>();
			// Arquivo por aquivo
			for (File certificate : directory.listFiles()) {
				// verificando seu tipo é invalido
				if (!certificate.isFile() || !(getFileExtension(certificate.getName()).equals("pdf"))) {
					throw new Exception("Existem arquivos invalidos no diretorio \nObs. apenas arquivos .pdf");
				}

				listCertificates.add(certificate);
			}

		} catch (Exception e) {
			throw new EmailSenderExecption(e.getMessage());
		}

		return listCertificates;
	}

	private static String getFileExtension(String filename) {
		if (filename.contains("."))
			return filename.substring(filename.lastIndexOf(".") + 1);
		else
			return "";
	}
}
