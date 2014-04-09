package gcom.gui.atendimentopublico.registroatendimento;

import org.apache.struts.validator.ValidatorActionForm;

public class InserirTipoSolicitacaoEspecificacaoActionForm extends
		ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String descricao;

	private String idgrupoTipoSolicitacao;

	private String indicadorFaltaAgua;
	
	private String indicadorTarifaSocial;
	
	private String indicadorUsoSistema;
    
    private String idSolicitacaoTipoEspecificacao;
    
    private String indicadorUrgencia;

	
    public String getIdSolicitacaoTipoEspecificacao() {
        return idSolicitacaoTipoEspecificacao;
    }

    
    public void setIdSolicitacaoTipoEspecificacao(
            String idSolicitacaoTipoEspecificacao) {
        this.idSolicitacaoTipoEspecificacao = idSolicitacaoTipoEspecificacao;
    }

    public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIdgrupoTipoSolicitacao() {
		return idgrupoTipoSolicitacao;
	}

	public void setIdgrupoTipoSolicitacao(String idgrupoTipoSolicitacao) {
		this.idgrupoTipoSolicitacao = idgrupoTipoSolicitacao;
	}

	public String getIndicadorFaltaAgua() {
		return indicadorFaltaAgua;
	}

	public void setIndicadorFaltaAgua(String indicadorFaltaAgua) {
		this.indicadorFaltaAgua = indicadorFaltaAgua;
	}

	public String getIndicadorTarifaSocial() {
		return indicadorTarifaSocial;
	}

	public void setIndicadorTarifaSocial(String indicadorTarifaSocial) {
		this.indicadorTarifaSocial = indicadorTarifaSocial;
	}

	/**
	 * @return Retorna o campo indicadorUsoSistema.
	 */
	public String getIndicadorUsoSistema() {
		return indicadorUsoSistema;
	}

	/**
	 * @param indicadorUsoSistema O indicadorUsoSistema a ser setado.
	 */
	public void setIndicadorUsoSistema(String indicadorUsoSistema) {
		this.indicadorUsoSistema = indicadorUsoSistema;
	}


	public String getIndicadorUrgencia() {
		return indicadorUrgencia;
	}


	public void setIndicadorUrgencia(String indicadorUrgencia) {
		this.indicadorUrgencia = indicadorUrgencia;
	}
	
}
