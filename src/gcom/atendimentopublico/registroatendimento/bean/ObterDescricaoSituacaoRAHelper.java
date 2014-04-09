package gcom.atendimentopublico.registroatendimento.bean;

/**
 * Esta classe tem por finalidade facilitar a forma como será retornado o resultado do 
 * [UC0420] Obter Descrição da Situação do RA
 * 
 * @author Ana Maria
 * @date 04/08/2006
 */
public class ObterDescricaoSituacaoRAHelper {
	
	private Short codigoSituacao;

	private String descricaoSituacao;
	
	private String descricaoAbreviadaSituacao;
	
	public ObterDescricaoSituacaoRAHelper(){}

	public String getDescricaoAbreviadaSituacao() {
		return descricaoAbreviadaSituacao;
	}

	public void setDescricaoAbreviadaSituacao(String descricaoAbreviadaSituacao) {
		this.descricaoAbreviadaSituacao = descricaoAbreviadaSituacao;
	}

	public String getDescricaoSituacao() {
		return descricaoSituacao;
	}

	public void setDescricaoSituacao(String descricaoSituacao) {
		this.descricaoSituacao = descricaoSituacao;
	}

	public Short getCodigoSituacao() {
		return codigoSituacao;
	}

	public void setCodigoSituacao(Short codigoSituacao) {
		this.codigoSituacao = codigoSituacao;
	}
	
}
