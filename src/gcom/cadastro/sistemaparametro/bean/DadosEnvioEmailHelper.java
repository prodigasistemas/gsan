package gcom.cadastro.sistemaparametro.bean;

/**
 *
 * @author Tiago Moreno
 * @date 08/03/2006
 */
public class DadosEnvioEmailHelper {
	
	private String ipServidorSmtp;
	private String emailResponsavel;
	
	public String getEmailResponsavel() {
		return emailResponsavel;
	}
	public void setEmailResponsavel(String emailResponsavel) {
		this.emailResponsavel = emailResponsavel;
	}
	public String getIpServidorSmtp() {
		return ipServidorSmtp;
	}
	public void setIpServidorSmtp(String ipServidorSmtp) {
		this.ipServidorSmtp = ipServidorSmtp;
	}
	
	public DadosEnvioEmailHelper(String ipServidorSmtp, String emailResponsavel) {

		this.ipServidorSmtp = ipServidorSmtp;
		this.emailResponsavel = emailResponsavel;
	}
	
}
