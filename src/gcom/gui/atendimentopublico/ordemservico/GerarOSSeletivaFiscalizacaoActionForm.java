package gcom.gui.atendimentopublico.ordemservico;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada 
 * @author Vivianne Sousa
 * @date 20/05/2011
 */
public class GerarOSSeletivaFiscalizacaoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String idLocalidadeInicial;
	private String descricaoLocalidadeInicial;
	private String idLocalidadeFinal;
	private String descricaoLocalidadeFinal;
	private String idServicoTipo;
	private String descricaoServicoTipo;
	private String grupoCobranca;
	private String gerenciaRegional;
	private String unidadeNegocio;
	private String qtdeDiasEncerramentoOS;
	private String qtdeOSEncerradasComConclusao;
	private String qtdeOSEncerradasSemConclusao;
	private String percentualOSgeradas;
	private String idOrdemServico;
	private String nomeOrdemServico;
	
	public String getDescricaoLocalidadeFinal() {
		return descricaoLocalidadeFinal;
	}
	public void setDescricaoLocalidadeFinal(String descricaoLocalidadeFinal) {
		this.descricaoLocalidadeFinal = descricaoLocalidadeFinal;
	}
	public String getDescricaoLocalidadeInicial() {
		return descricaoLocalidadeInicial;
	}
	public void setDescricaoLocalidadeInicial(String descricaoLocalidadeInicial) {
		this.descricaoLocalidadeInicial = descricaoLocalidadeInicial;
	}
	public String getDescricaoServicoTipo() {
		return descricaoServicoTipo;
	}
	public void setDescricaoServicoTipo(String descricaoServicoTipo) {
		this.descricaoServicoTipo = descricaoServicoTipo;
	}
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public String getGrupoCobranca() {
		return grupoCobranca;
	}
	public void setGrupoCobranca(String grupoCobranca) {
		this.grupoCobranca = grupoCobranca;
	}
	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}
	public void setIdLocalidadeFinal(String idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}
	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}
	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}
	public String getIdServicoTipo() {
		return idServicoTipo;
	}
	public void setIdServicoTipo(String idServicoTipo) {
		this.idServicoTipo = idServicoTipo;
	}
	public String getPercentualOSgeradas() {
		return percentualOSgeradas;
	}
	public void setPercentualOSgeradas(String percentualOSgeradas) {
		this.percentualOSgeradas = percentualOSgeradas;
	}
	public String getQtdeDiasEncerramentoOS() {
		return qtdeDiasEncerramentoOS;
	}
	public void setQtdeDiasEncerramentoOS(String qtdeDiasEncerramentoOS) {
		this.qtdeDiasEncerramentoOS = qtdeDiasEncerramentoOS;
	}
	public String getQtdeOSEncerradasComConclusao() {
		return qtdeOSEncerradasComConclusao;
	}
	public void setQtdeOSEncerradasComConclusao(String qtdeOSEncerradasComConclusao) {
		this.qtdeOSEncerradasComConclusao = qtdeOSEncerradasComConclusao;
	}
	public String getQtdeOSEncerradasSemConclusao() {
		return qtdeOSEncerradasSemConclusao;
	}
	public void setQtdeOSEncerradasSemConclusao(String qtdeOSEncerradasSemConclusao) {
		this.qtdeOSEncerradasSemConclusao = qtdeOSEncerradasSemConclusao;
	}
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	public String getIdOrdemServico() {
		return idOrdemServico;
	}
	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	public String getNomeOrdemServico() {
		return nomeOrdemServico;
	}
	public void setNomeOrdemServico(String nomeOrdemServico) {
		this.nomeOrdemServico = nomeOrdemServico;
	}
	
	
}
