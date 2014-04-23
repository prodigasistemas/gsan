package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.action.ActionForm;

/**
 * Descrição da classe 
 *
 * @author Thiago Tenório
 * @date 07/08/2006
 */
public class PesquisarTipoServicoReferenciaActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	// Tipo Servico Referência
	private String descricao;

	private String descricaoAbreviada;

	private String indicadorExistenciaOsReferencia;

	private String tipoServico;
    
	private String idTipoServico;
	
	private String descricaoTipoServico;

	public String getDescricaoTipoServico() {
		return descricaoTipoServico;
	}

	public void setDescricaoTipoServico(String descricaoTipoServico) {
		this.descricaoTipoServico = descricaoTipoServico;
	}

	public String getIdTipoServico() {
		return idTipoServico;
	}

	public void setIdTipoServico(String idTipoServico) {
		this.idTipoServico = idTipoServico;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public String getIndicadorExistenciaOsReferencia() {
		return indicadorExistenciaOsReferencia;
	}

	public void setIndicadorExistenciaOsReferencia(
			String indicadorExistenciaOsReferencia) {
		this.indicadorExistenciaOsReferencia = indicadorExistenciaOsReferencia;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

}
