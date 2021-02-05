package gcom.api.ordemservico.response;

public class OrdemServicoResponse {

	private boolean encerrada;

	private String mensagem;

	public OrdemServicoResponse() {
		super();
	}

	public boolean isEncerrada() {
		return encerrada;
	}

	public void setEncerrada(boolean encerrada) {
		this.encerrada = encerrada;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}