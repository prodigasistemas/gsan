package gcom.gui.relatorio.atendimentopublico;

import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.util.Util;

import java.io.Serializable;

/**
 * [UC1177] Gerar Relatório de Ordens de Serviço por Situação
 * 
 * Filtro responsável por auxiliar na geração do relatório de 
 * OS por Situação RelatorioOSSituacao
 * 
 * @author Diogo Peixoto
 * @since 09/06/2011
 * 
 */
public class FiltrarRelatorioOSSituacaoHelper implements Serializable{

	private static final long serialVersionUID = 1L;

	//Campos obrigatórios
	private String opcaoRelatorio;
	private Integer dataReferencia;
	private Integer idEmpresa;
	private String situacaoOS;
	private Integer idGrupoCobranca;
	
	//Campos não obrigatórios
	private GerenciaRegional gerenciaRegional;
	private UnidadeNegocio unidadeNegocio;
	private Localidade localidade;
	private SetorComercial setorComercial;
	private Quadra quadra;
	private String opcaoOSCobranca;
	private Localidade eloPolo;
	private ServicoTipo servicoTipo;
	
	public FiltrarRelatorioOSSituacaoHelper(){
		
	}

	public FiltrarRelatorioOSSituacaoHelper(String opcaoRel, Integer data, Integer idEmpresa, String situacaoOS, Integer idGrupoCobranca,
			GerenciaRegional gerencia, UnidadeNegocio unidade, Localidade localidade, SetorComercial setor, Quadra quadra, String opcaoCobranca, 
			Localidade eloPolo, ServicoTipo servicoTipo){
		
		this.opcaoRelatorio = opcaoRel;
		this.dataReferencia = data;
		this.idEmpresa = idEmpresa;
		this.situacaoOS = situacaoOS;
		this.idGrupoCobranca = idGrupoCobranca;
		this.gerenciaRegional = gerencia;
		this.unidadeNegocio = unidade;
		this.localidade = localidade;
		this.setorComercial = setor;
		this.quadra = quadra;
		this.opcaoOSCobranca = opcaoCobranca;
		this.eloPolo = eloPolo;
		this.servicoTipo = servicoTipo;
	}
	
	public String getOpcaoRelatorio() {
		return opcaoRelatorio;
	}

	public void setOpcaoRelatorio(String opcaoRelatorio) {
		this.opcaoRelatorio = opcaoRelatorio;
	}

	public Integer getDataReferencia() {
		return dataReferencia;
	}

	public void setDataReferencia(String strDataReferencia) throws NumberFormatException {
		this.dataReferencia = Util.formatarMesAnoComBarraParaAnoMes(strDataReferencia);
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getSituacaoOS() {
		return situacaoOS;
	}

	public void setSituacaoOS(String situacaoOS) {
		this.situacaoOS = situacaoOS;
	}

	public Integer getIdGrupoCobranca() {
		return idGrupoCobranca;
	}

	public void setIdGrupoCobranca(Integer idGrupoCobranca) {
		this.idGrupoCobranca = idGrupoCobranca;
	}

	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public String getOpcaoOSCobranca() {
		return opcaoOSCobranca;
	}

	public void setOpcaoOSCobranca(String opcaoOSCobranca) {
		this.opcaoOSCobranca = opcaoOSCobranca;
	}

	public SetorComercial getSetorComercial() {
		return setorComercial;
	}

	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	public Quadra getQuadra() {
		return quadra;
	}

	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}

	public Localidade getEloPolo() {
		return eloPolo;
	}

	public void setEloPolo(Localidade eloPolo) {
		this.eloPolo = eloPolo;
	}

	public ServicoTipo getServicoTipo() {
		return servicoTipo;
	}

	public void setServicoTipo(ServicoTipo servicoTipo) {
		this.servicoTipo = servicoTipo;
	}
}
