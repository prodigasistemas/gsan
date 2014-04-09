package gcom.relatorio.faturamento;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *[UC0958] Gerar Relatório Juras, Multas e Débitos Cancelados
 *
 * @author Marlon Patrick
 * @since 22/10/2009
 */
public class FiltrarRelatorioJurosMultasDebitosCanceladosHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer mesAnoFaturamento;

	private Date dataCancelamentoInicial;
	private Date dataCancelamentoFinal;

	private Integer unidadeNegocio;
	
	private Integer localidade;

	private Collection<Integer> colecaoTiposDebito;
	private Collection<Integer> colecaoCategorias;
	private Collection<Integer> colecaoPerfilImovel;
	private Collection<Integer> colecaoEsferaPoder;
		
	private Integer usuarioCancelamento;

	public Integer getMesAnoFaturamento() {
		return mesAnoFaturamento;
	}

	public void setMesAnoFaturamento(Integer mesAnoFaturamento) {
		this.mesAnoFaturamento = mesAnoFaturamento;
	}

	public Date getDataCancelamentoInicial() {
		return dataCancelamentoInicial;
	}

	public void setDataCancelamentoInicial(Date dataCancelamentoInicial) {
		this.dataCancelamentoInicial = dataCancelamentoInicial;
	}

	public Date getDataCancelamentoFinal() {
		return dataCancelamentoFinal;
	}

	public void setDataCancelamentoFinal(Date dataCancelamentoFinal) {
		this.dataCancelamentoFinal = dataCancelamentoFinal;
	}

	public Integer getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(Integer unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public Integer getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Integer localidade) {
		this.localidade = localidade;
	}

	public Collection<Integer> getColecaoTiposDebito() {
		return colecaoTiposDebito;
	}

	public void setColecaoTiposDebito(Collection<Integer> colecaoTiposDebito) {
		this.colecaoTiposDebito = colecaoTiposDebito;
	}

	public Collection<Integer> getColecaoCategorias() {
		return colecaoCategorias;
	}

	public void setColecaoCategorias(Collection<Integer> categorias) {
		this.colecaoCategorias = categorias;
	}

	public Collection<Integer> getColecaoPerfilImovel() {
		return colecaoPerfilImovel;
	}

	public void setColecaoPerfilImovel(Collection<Integer> colecaoPerfilImovel) {
		this.colecaoPerfilImovel = colecaoPerfilImovel;
	}

	public Collection<Integer> getColecaoEsferaPoder() {
		return colecaoEsferaPoder;
	}

	public void setColecaoEsferaPoder(Collection<Integer> colecaoEsferaPoder) {
		this.colecaoEsferaPoder = colecaoEsferaPoder;
	}

	public Integer getUsuarioCancelamento() {
		return usuarioCancelamento;
	}

	public void setUsuarioCancelamento(Integer usuarioCancelamento) {
		this.usuarioCancelamento = usuarioCancelamento;
	}
	
}
