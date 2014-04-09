package gcom.relatorio.financeiro;

import gcom.financeiro.ResumoReceita;
import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

/**
 * classe responsável por criar o relatório de imoveis por situação da ligação de agua
 * 
 * @author Rafael Pinto
 * @created 03/12/2007
 */
public class RelatorioResumoReceitaAnaliticoBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	private String anoMesReferencia;
    
    private String dataRealizada;

    /** nullable persistent field */
    private String valorReceita;
    
    private String ultimaAlteracao;
    
    private String bancoId;
    
    private String arrecadador;
    
    private String contaContabil;
    
    private String gerenciaRegional;
    
    private String localidade;
    
    private String categoria;
    
    private String contaBancaria;
    
    private String unidadeNegocio;
    
    private String bancoNome;
    
    public RelatorioResumoReceitaAnaliticoBean(ResumoReceita resumo){
    	this.anoMesReferencia = resumo.getAnoMesReferencia()+"";
    	this.arrecadador = resumo.getArrecadador().getNumeroInscricaoEstadual();
    	this.bancoId = resumo.getBanco().getId() + "";
    	this.bancoNome = resumo.getBanco().getDescricao();
    	//this.categoria = resumo.getCategoria().getId() + "";
    	this.contaContabil = resumo.getContaContabil().getNumeroConta();
    	this.contaBancaria = resumo.getContaBancaria().getNumeroConta();
    	this.dataRealizada = Util.formatarData(resumo.getDataRealizada());
    	//this.gerenciaRegional = resumo.getGerenciaRegional().getId() + "";
    	//this.localidade = resumo.getLocalidade().getId() + "";
    	//this.ultimaAlteracao = Util.formatarData(resumo.getUltimaAlteracao());
    	//this.unidadeNegocio = resumo.getUnidadeNegocio().getId() + "";
    	this.valorReceita = resumo.getValorReceita()+"";
    }

	public String getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(String anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public String getArrecadador() {
		return arrecadador;
	}

	public void setArrecadador(String arrecadador) {
		this.arrecadador = arrecadador;
	}

	public String getBancoId() {
		return bancoId;
	}

	public void setBancoId(String bancoId) {
		this.bancoId = bancoId;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(String contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	public String getContaContabil() {
		return contaContabil;
	}

	public void setContaContabil(String contaContabil) {
		this.contaContabil = contaContabil;
	}

	public String getDataRealizada() {
		return dataRealizada;
	}

	public void setDataRealizada(String dataRealizada) {
		this.dataRealizada = dataRealizada;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getValorReceita() {
		return valorReceita;
	}

	public void setValorReceita(String valorReceita) {
		this.valorReceita = valorReceita;
	}

	public String getBancoNome() {
		return bancoNome;
	}

	public void setBancoNome(String bancoNome) {
		this.bancoNome = bancoNome;
	}
        

	
}
