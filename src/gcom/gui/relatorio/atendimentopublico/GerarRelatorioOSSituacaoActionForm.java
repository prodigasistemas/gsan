package gcom.gui.relatorio.atendimentopublico;

import org.apache.struts.action.ActionForm;

/** [UC1177] Gerar Relatório de Ordens de Serviço por Situação
 * Formulário responsável por armazenar os dados da tela
 * relatorio_os_situacao_gerar.jsp.
 * 
 * @author Diogo Peixoto
 * @since 03/06/2011
 */
public class GerarRelatorioOSSituacaoActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	//Campos obrigatórios
	private String opcaoRelatorio;
	private String dataReferencia;
	private String idEmpresa;
	private String situacaoOS;
	private String idGrupoCobranca;
	
	//Campos não obrigatórios
	private String idGerenciaRegional;
	private String idUnidadeNegocio;
	private String idLocalidade;
	private String descricaoLocalidade;
	private String idSetorComercial;
	private String descricaoSetorComercial;
	private String idQuadra;
	private String opcaoOSCobranca;
	private String idEloPolo;
	private String descricaoEloPolo;
	private String idTipoServico;
	private String descricaoTipoServico;
	
	public GerarRelatorioOSSituacaoActionForm(){
		
	}

	public String getOpcaoRelatorio() {
		return opcaoRelatorio;
	}

	public void setOpcaoRelatorio(String opcaoRelatorio) {
		this.opcaoRelatorio = opcaoRelatorio;
	}

	public String getDataReferencia() {
		return dataReferencia;
	}

	public void setDataReferencia(String dataReferencia) {
		this.dataReferencia = dataReferencia;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getSituacaoOS() {
		return situacaoOS;
	}

	public void setSituacaoOS(String situacaoOS) {
		this.situacaoOS = situacaoOS;
	}

	public String getIdGrupoCobranca() {
		return idGrupoCobranca;
	}

	public void setIdGrupoCobranca(String idGrupoCobranca) {
		this.idGrupoCobranca = idGrupoCobranca;
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}

	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
	}

	public String getIdQuadra() {
		return idQuadra;
	}

	public void setIdQuadra(String idQuadra) {
		this.idQuadra = idQuadra;
	}

	public String getOpcaoOSCobranca() {
		return opcaoOSCobranca;
	}

	public void setOpcaoOSCobranca(String opcaoOSCobranca) {
		this.opcaoOSCobranca = opcaoOSCobranca;
	}

	public String getIdEloPolo() {
		return idEloPolo;
	}

	public void setIdEloPolo(String idEloPolo) {
		this.idEloPolo = idEloPolo;
	}

	public String getDescricaoEloPolo() {
		return descricaoEloPolo;
	}

	public void setDescricaoEloPolo(String descricaoEloPolo) {
		this.descricaoEloPolo = descricaoEloPolo;
	}

	public String getIdTipoServico() {
		return idTipoServico;
	}

	public void setIdTipoServico(String idTipoServico) {
		this.idTipoServico = idTipoServico;
	}

	public String getDescricaoTipoServico() {
		return descricaoTipoServico;
	}

	public void setDescricaoTipoServico(String descricaoTipoServico) {
		this.descricaoTipoServico = descricaoTipoServico;
	}
}
