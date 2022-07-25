package gcom.api.pagamentocredito;

public class PagamentoCreditoResponse {
	
	private String mensagem;
	
	private boolean erroRequisicao = false;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
		this.erroRequisicao = true;
	}
	
	public boolean isMensagemPreenchida() {
		return erroRequisicao;
	}
}
