package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.validator.ValidatorForm;

/**[UC1213] Emitir Relatorio de Ordem de Sercico de Fiscalizacao
 *
 * @author Paulo Diniz
 * @date 09/08/2011
 */
public class ExibirFiltrarRelatorioOrdensServicoFiscalizacaoActionForm extends ValidatorForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tipoRelatorioFiscalizacao;
	
	private String periodoInicial;
	
	private String periodoFinal;
	
	private String gerenciaRegional;
	
	private String unidadeNegocio;
	
	private String localidadeInicial;
	
	private String localidadeFinal;
	
	private String descricaoLocalidadeInicial;
	
	private String descricaoLocalidadeFinal;
	
	private String situacao;
	
	private String tipoRetorno;
	
	private String aceitacaoOS;

	public String getPeriodoInicial() {
		return periodoInicial;
	}

	public void setPeriodoInicial(String periodoInicial) {
		this.periodoInicial = periodoInicial;
	}

	public String getPeriodoFinal() {
		return periodoFinal;
	}

	public void setPeriodoFinal(String periodoFinal) {
		this.periodoFinal = periodoFinal;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(String localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public String getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(String localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getTipoRetorno() {
		return tipoRetorno;
	}

	public void setTipoRetorno(String tipoRetorno) {
		this.tipoRetorno = tipoRetorno;
	}

	public String getAceitacaoOS() {
		return aceitacaoOS;
	}

	public void setAceitacaoOS(String aceitacaoOS) {
		this.aceitacaoOS = aceitacaoOS;
	}

	public String getTipoRelatorioFiscalizacao() {
		return tipoRelatorioFiscalizacao;
	}

	public void setTipoRelatorioFiscalizacao(String tipoRelatorioFiscalizacao) {
		this.tipoRelatorioFiscalizacao = tipoRelatorioFiscalizacao;
	}

	public String getDescricaoLocalidadeInicial() {
		return descricaoLocalidadeInicial;
	}

	public void setDescricaoLocalidadeInicial(String descricaoLocalidadeInicial) {
		this.descricaoLocalidadeInicial = descricaoLocalidadeInicial;
	}

	public String getDescricaoLocalidadeFinal() {
		return descricaoLocalidadeFinal;
	}

	public void setDescricaoLocalidadeFinal(String descricaoLocalidadeFinal) {
		this.descricaoLocalidadeFinal = descricaoLocalidadeFinal;
	}

}
