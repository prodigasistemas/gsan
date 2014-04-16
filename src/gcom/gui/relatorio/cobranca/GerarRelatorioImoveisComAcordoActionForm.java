package gcom.gui.relatorio.cobranca;

import org.apache.struts.action.ActionForm;

/**
 * [UC0891]-Gerar Relatório de Imóveis com Acordo
 *
 * @author Rômulo Aurélio
 * @date 23/03/2009
 */
public class GerarRelatorioImoveisComAcordoActionForm  extends
		ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String periodoInicialAcordo;
	private String periodoFinalAcordo;
	private String idGerenciaRegional;
	private String nomeGerenciaRegional;
	private String idLocalidadeInicial;
	private String nomeLocalidadeInicial;
	private String idLocalidadeFinal;
	private String nomeLocalidadeFinal;
	private String rotaInicial;
	private String rotaFinal;
	private String setorComercialInicial;
	private String setorComercialFinal;
	private String sequencialRotaInicial;
	private String sequencialRotaFinal;
	private String nomeSetorComercialInicial;
	private String nomeSetorComercialFinal;
	private String idUnidadeNegocio;
	private String tipoPesquisa;
	private String quantidade;
	
	
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
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
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}
	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}
	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
	}
	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}
	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}
	public String getPeriodoFinalAcordo() {
		return periodoFinalAcordo;
	}
	public void setPeriodoFinalAcordo(String periodoFinalAcordo) {
		this.periodoFinalAcordo = periodoFinalAcordo;
	}
	public String getPeriodoInicialAcordo() {
		return periodoInicialAcordo;
	}
	public void setPeriodoInicialAcordo(String periodoInicialAcordo) {
		this.periodoInicialAcordo = periodoInicialAcordo;
	}
	public String getRotaFinal() {
		return rotaFinal;
	}
	public void setRotaFinal(String rotaFinal) {
		this.rotaFinal = rotaFinal;
	}
	public String getRotaInicial() {
		return rotaInicial;
	}
	public void setRotaInicial(String rotaInicial) {
		this.rotaInicial = rotaInicial;
	}
	public String getSequencialRotaFinal() {
		return sequencialRotaFinal;
	}
	public void setSequencialRotaFinal(String sequencialRotaFinal) {
		this.sequencialRotaFinal = sequencialRotaFinal;
	}
	public String getSequencialRotaInicial() {
		return sequencialRotaInicial;
	}
	public void setSequencialRotaInicial(String sequencialRotaInicial) {
		this.sequencialRotaInicial = sequencialRotaInicial;
	}
	public String getSetorComercialFinal() {
		return setorComercialFinal;
	}
	public void setSetorComercialFinal(String setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}
	public String getSetorComercialInicial() {
		return setorComercialInicial;
	}
	public void setSetorComercialInicial(String setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}
	public String getNomeSetorComercialFinal() {
		return nomeSetorComercialFinal;
	}
	public void setNomeSetorComercialFinal(String nomeSetorComercialFinal) {
		this.nomeSetorComercialFinal = nomeSetorComercialFinal;
	}
	public String getNomeSetorComercialInicial() {
		return nomeSetorComercialInicial;
	}
	public void setNomeSetorComercialInicial(String nomeSetorComercialInicial) {
		this.nomeSetorComercialInicial = nomeSetorComercialInicial;
	}
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	public String getTipoPesquisa() {
		return tipoPesquisa;
	}
	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

}
