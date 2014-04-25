package gcom.cadastro.imovel.bean;

import java.math.BigDecimal;


/**
 * Débitos de um imóvel
 * @author Ivan Sérgio
 * @since 10/09/2007
 */
public class GerarCurvaAbcDebitosHelper {
	
	private BigDecimal faixaInicial;
	private BigDecimal faixaFinal;
	private Integer idFaixa;
	
	private Integer quantidadeLigacoes = 0;
	private BigDecimal percLigacoes = new BigDecimal(0.00).setScale(2);
	private Integer ligacoesAcumuladas = 0;
	private BigDecimal percLigacoesAcumulado = new BigDecimal(0.00).setScale(2);
	
	private BigDecimal valor = new BigDecimal(0.00).setScale(2);
	private BigDecimal percValor = new BigDecimal(0.00).setScale(2);
	private BigDecimal valorAcumulado = new BigDecimal(0.00).setScale(2);
	private BigDecimal percValorAcumulado = new BigDecimal(0.00).setScale(2);
	private BigDecimal valorMedio = new BigDecimal(0.00).setScale(2);
	
	//private Integer quantidadeDocumentos;
	private Integer idGerenciaRegional;
	private String nomeGerenciaRegional;
	private Integer idLocalidade;
	private String nomeLocalidade;
	private Integer idSetorComercial;
	private Integer codigoSetorComercial;
	private String nomeSetorComercial;
	private Integer idMunicipio;
	private String nomeMunicipio;
	
	public GerarCurvaAbcDebitosHelper(
			BigDecimal faixaInicial, 
			BigDecimal faixaFinal, 
			Integer idFaixa, 
			Integer quantidadeLigacoes, 
			BigDecimal valor, 
			Integer idGerenciaRegional, 
			String nomeGerenciaRegional, 
			Integer idLocalidade, 
			String nomeLocalidade,
			Integer idSetorComercial, 
			Integer codigoSetorComercial, 
			String nomeSetorComercial,
			Integer idMunicipio,
			String nomeMunicipio) {
		
		this.faixaInicial = faixaInicial;
		this.faixaFinal = faixaFinal;
		this.idFaixa = idFaixa;
		this.quantidadeLigacoes = quantidadeLigacoes;
		this.valor = valor;
		this.idGerenciaRegional = idGerenciaRegional;
		this.nomeGerenciaRegional = nomeGerenciaRegional;
		this.idLocalidade = idLocalidade;
		this.nomeMunicipio = nomeMunicipio;
		this.idSetorComercial = idSetorComercial;
		this.codigoSetorComercial = codigoSetorComercial;
		this.nomeSetorComercial = nomeSetorComercial;
		this.nomeLocalidade = nomeLocalidade;
		this.idMunicipio = idMunicipio;
	}
	
	public GerarCurvaAbcDebitosHelper(
			Integer idFaixa,
			BigDecimal faixaInicial, 
			BigDecimal faixaFinal) {
		
		this.idFaixa = idFaixa;
		this.faixaInicial = faixaInicial;
		this.faixaFinal = faixaFinal;
	}
	
	public GerarCurvaAbcDebitosHelper(){}
	
	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	public BigDecimal getFaixaFinal() {
		return faixaFinal;
	}
	public void setFaixaFinal(BigDecimal faixaFinal) {
		this.faixaFinal = faixaFinal;
	}
	public BigDecimal getFaixaInicial() {
		return faixaInicial;
	}
	public void setFaixaInicial(BigDecimal faixaInicial) {
		this.faixaInicial = faixaInicial;
	}
	public Integer getIdFaixa() {
		return idFaixa;
	}
	public void setIdFaixa(Integer idFaixa) {
		this.idFaixa = idFaixa;
	}
	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}
	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	public Integer getLigacoesAcumuladas() {
		return ligacoesAcumuladas;
	}
	public void setLigacoesAcumuladas(Integer ligacoesAcumuladas) {
		this.ligacoesAcumuladas = ligacoesAcumuladas;
	}
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
	public String getNomeSetorComercial() {
		return nomeSetorComercial;
	}
	public void setNomeSetorComercial(String nomeSetorComercial) {
		this.nomeSetorComercial = nomeSetorComercial;
	}
	public BigDecimal getPercLigacoes() {
		return percLigacoes;
	}
	public void setPercLigacoes(BigDecimal percLigacoes) {
		this.percLigacoes = percLigacoes;
	}
	public BigDecimal getPercLigacoesAcumulado() {
		return percLigacoesAcumulado;
	}
	public void setPercLigacoesAcumulado(BigDecimal percLigacoesAcumulado) {
		this.percLigacoesAcumulado = percLigacoesAcumulado;
	}
	public BigDecimal getPercValor() {
		return percValor;
	}
	public void setPercValor(BigDecimal percValor) {
		this.percValor = percValor;
	}
	public BigDecimal getPercValorAcumulado() {
		return percValorAcumulado;
	}
	public void setPercValorAcumulado(BigDecimal percValorAcumulado) {
		this.percValorAcumulado = percValorAcumulado;
	}
	public Integer getQuantidadeLigacoes() {
		return quantidadeLigacoes;
	}
	public void setQuantidadeLigacoes(Integer quantidadeLigacoes) {
		this.quantidadeLigacoes = quantidadeLigacoes;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public BigDecimal getValorAcumulado() {
		return valorAcumulado;
	}
	public void setValorAcumulado(BigDecimal valorAcumulado) {
		this.valorAcumulado = valorAcumulado;
	}
	public BigDecimal getValorMedio() {
		return valorMedio;
	}
	public void setValorMedio(BigDecimal valorMedio) {
		this.valorMedio = valorMedio;
	}
	public Integer getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public String getNomeMunicipio() {
		return nomeMunicipio;
	}
	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	/**
	 * Compara duas properiedades inteiras, comparando
	 * seus valores para descobrirmos se sao iguais
	 * @param pro1 Primeira propriedade
	 * @param pro2 Segunda propriedade
	 * @return Se iguais, retorna true
	 */
	private boolean propriedadesIguais( Integer pro1, Integer pro2 ){
		if ( pro2 != null ){
			if ( !pro2.equals( pro1 ) ){
				return false;
			}
		} else if ( pro1 != null ){
			return false;
		}
	  
	  // Se chegou ate aqui quer dizer que as propriedades sao iguais
	  return true;
	}	
	
    /**
     * Compara dois objetos levando em consideracao apenas as propriedades
     * que identificam o agrupamento
     * 
     * @param obj Objeto a ser comparado com a instancia deste objeto 
     */
	public boolean equals( Object obj ){
		if ( !( obj instanceof GerarCurvaAbcDebitosHelper ) ){
			return false;			
		} else {
			GerarCurvaAbcDebitosHelper resumoTemp = (GerarCurvaAbcDebitosHelper) obj;
		  
		    // Verificamos se todas as propriedades que identificam o objeto sao iguais
			return 
			  propriedadesIguais( this.idFaixa, resumoTemp.idFaixa );// &&
			  //propriedadesIguais( this.idGerenciaRegional, resumoTemp.idGerenciaRegional ) &&
			  //propriedadesIguais( this.idLocalidade, resumoTemp.idLocalidade ) &&
			  //propriedadesIguais( this.idSetorComercial, resumoTemp.idSetorComercial );
		}	
	}
	
}
