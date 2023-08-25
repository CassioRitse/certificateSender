package certificateSender.config;

import java.util.Properties;

public final class ConfigServerSMTP {
    // Configurações do servidor SMTP do Gmail
    private final String host = "smtp.gmail.com";
    private final int port = 587;
    private final Properties props;

    public ConfigServerSMTP() {
        // Configurações adicionais
        this.props = new Properties();
        this.props.put("mail.smtp.auth", "true");
        this.props.put("mail.smtp.starttls.enable", "true");
        this.props.put("mail.smtp.host", host);
        this.props.put("mail.smtp.port", port);
    }

    public Properties getProps(){
        return this.props;
    }
}
