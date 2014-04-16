package gcom.relatorio.cobranca;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author Diogo Peixoto
 * @date 19/04/2011
 */

public class CurvaAbcDebitosHelper implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private BigDecimal faixaInicial;
	private BigDecimal faixaFinal;
	private Integer idFaixa;
	private Integer quantidadeLigacoes;
	private BigDecimal total;
	private Integer qtdeDocumentos;
	private Integer idGerencia;
	private String nomeGerencia;
	private Integer idLocalidade;
	private String nomeLocalidade;
	private Integer idSetor;
	private Integer codigoSetor;
	private String nomeSetor;
	private Integer idMunicipio;
	private String nomeMunicipio;
	
	@Override
	public boolean equals(Object obj) {
		boolean retorno = false;
		CurvaAbcDebitosHelper curvaAbc = (CurvaAbcDebitosHelper) obj;
		
		if(curvaAbc.getFaixaFinal().equals(this.faixaFinal) && curvaAbc.getFaixaInicial().equals(this.faixaInicial)
				&& curvaAbc.getIdFaixa().equals(this.idFaixa) && curvaAbc.getQuantidadeLigacoes().equals(this.quantidadeLigacoes)
				&& curvaAbc.getTotal().equals(this.total) && curvaAbc.getQtdeDocumentos().equals(this.qtdeDocumentos)
				&& curvaAbc.getIdGerencia().equals(this.idGerencia) && curvaAbc.getNomeGerencia().equals(this.nomeGerencia)
				&& curvaAbc.getIdLocalidade().equals(this.idLocalidade) && curvaAbc.getNomeLocalidade().equals(this.nomeLocalidade)
				&& curvaAbc.getIdSetor().equals(this.idSetor) && curvaAbc.getCodigoSetor().equals(this.codigoSetor)
				&& curvaAbc.getNomeSetor().equals(this.nomeSetor) && curvaAbc.getIdMunicipio().equals(this.idMunicipio)
				&& curvaAbc.getNomeMunicipio().equals(this.nomeMunicipio)){
			retorno = true;
		}
		return retorno;
	}
	
	public Integer getCodigoSetor() {
		return codigoSetor;
	}
	public void setCodigoSetor(Integer codigoSetor) {
		this.codigoSetor = codigoSetor;
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
	public Integer getIdGerencia() {
		return idGerencia;
	}
	public void setIdGerencia(Integer idGerencia) {
		this.idGerencia = idGerencia;
	}
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public Integer getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public Integer getIdSetor() {
		return idSetor;
	}
	public void setIdSetor(Integer idSetor) {
		this.idSetor = idSetor;
	}
	public String getNomeGerencia() {
		return nomeGerencia;
	}
	public void setNomeGerencia(String nomeGerencia) {
		this.nomeGerencia = nomeGerencia;
	}
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
	public String getNomeMunicipio() {
		return nomeMunicipio;
	}
	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}
	public String getNomeSetor() {
		return nomeSetor;
	}
	public void setNomeSetor(String nomeSetor) {
		this.nomeSetor = nomeSetor;
	}
	public Integer getQtdeDocumentos() {
		return qtdeDocumentos;
	}
	public void setQtdeDocumentos(Integer qtdeDocumentos) {
		this.qtdeDocumentos = qtdeDocumentos;
	}
	public Integer getQuantidadeLigacoes() {
		return quantidadeLigacoes;
	}
	public void setQuantidadeLigacoes(Integer quantidadeLigacoes) {
		this.quantidadeLigacoes = quantidadeLigacoes;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
}
