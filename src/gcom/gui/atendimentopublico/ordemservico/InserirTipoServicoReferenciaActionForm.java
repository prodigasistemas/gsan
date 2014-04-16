package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 04/08/2006
 */
public class InserirTipoServicoReferenciaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String descricao;

	private String abreviatura;

	private String indicadorExistenciaOsReferencia;

	private String situacaoOSAntes;

	private String situacaoOSApos;

	private String tipoServico;

	private String nomeTipoServico;
	
	private String indicadorDiagnostico;
	
	private String indicadorFiscalizacao;
	
	public void reset(){
		this.descricao = null;
		this.abreviatura  = null;
		this.indicadorExistenciaOsReferencia = null;
		this.situacaoOSAntes = null;
		this.situacaoOSApos = null;
		this.tipoServico = null;
		this.nomeTipoServico = null;		
		this.indicadorDiagnostico = null;		
		this.indicadorFiscalizacao = null;
	}
	public String getIndicadorDiagnostico() {
		return indicadorDiagnostico;
	}

	public void setIndicadorDiagnostico(String indicadorDiagnostico) {
		this.indicadorDiagnostico = indicadorDiagnostico;
	}

	public String getIndicadorFiscalizacao() {
		return indicadorFiscalizacao;
	}

	public void setIndicadorFiscalizacao(String indicadorFiscalizacao) {
		this.indicadorFiscalizacao = indicadorFiscalizacao;
	}

	/**
	 * @return Retorna o campo nomeTipoServico.
	 */
	public String getNomeTipoServico() {
		return nomeTipoServico;
	}

	/**
	 * @param nomeTipoServico O nomeTipoServico a ser setado.
	 */
	public void setNomeTipoServico(String nomeTipoServico) {
		this.nomeTipoServico = nomeTipoServico;
	}

	/**
	 * @return Retorna o campo abreviatura.
	 */
	public String getAbreviatura() {
		return abreviatura;
	}

	/**
	 * @param abreviatura
	 *            O abreviatura a ser setado.
	 */
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	/**
	 * @return Retorna o campo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            O descricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return Retorna o campo indicadorExistenciaOsReferencia.
	 */
	public String getIndicadorExistenciaOsReferencia() {
		return indicadorExistenciaOsReferencia;
	}

	/**
	 * @param indicadorExistenciaOsReferencia
	 *            O indicadorExistenciaOsReferencia a ser setado.
	 */
	public void setIndicadorExistenciaOsReferencia(
			String indicadorExistenciaOsReferencia) {
		this.indicadorExistenciaOsReferencia = indicadorExistenciaOsReferencia;
	}

	/**
	 * @return Retorna o campo situacaoOSAntes.
	 */
	public String getSituacaoOSAntes() {
		return situacaoOSAntes;
	}

	/**
	 * @param situacaoOSAntes
	 *            O situacaoOSAntes a ser setado.
	 */
	public void setSituacaoOSAntes(String situacaoOSAntes) {
		this.situacaoOSAntes = situacaoOSAntes;
	}

	/**
	 * @return Retorna o campo situacaoOSApos.
	 */
	public String getSituacaoOSApos() {
		return situacaoOSApos;
	}

	/**
	 * @param situacaoOSApos
	 *            O situacaoOSApos a ser setado.
	 */
	public void setSituacaoOSApos(String situacaoOSApos) {
		this.situacaoOSApos = situacaoOSApos;
	}

	/**
	 * @return Retorna o campo tipoServicao.
	 */
	public String getTipoServico() {
		return tipoServico;
	}

	/**
	 * @param tipoServicao
	 *            O tipoServicao a ser setado.
	 */
	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

}
