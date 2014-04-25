package gcom.relatorio.faturamento;

import java.io.Serializable;


/**
 * [UC1129] Gerar Relatório Devolução dos Pagamentos em Duplicidade
 * 
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * do Relatório Devolução dos Pagamentos em Duplicidade.
 *
 * @author Hugo Leonardo
 * @date 10/03/2011
 */
public class FiltrarRelatorioDevolucaoPagamentosDuplicidadeHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String idGerencia;
	private String idUnidadeNegocio;
	private String idPerfilImovel;
	private String idCategoriaImovel;
	private String idLocalidade;
	private String nomeLocalidade;
	private String anoMesReferenciaInicial;
	private String anoMesReferenciaFinal;
	private String nomeGerencia;
	private String nomeUnidadeNegocio;
	
	public String getIdCategoriaImovel() {
		return idCategoriaImovel;
	}
	
	public void setIdCategoriaImovel(String idCategoriaImovel) {
		this.idCategoriaImovel = idCategoriaImovel;
	}

	public String getIdGerencia() {
		return idGerencia;
	}

	public void setIdGerencia(String idGerencia) {
		this.idGerencia = idGerencia;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdPerfilImovel() {
		return idPerfilImovel;
	}

	public void setIdPerfilImovel(String idPerfilImovel) {
		this.idPerfilImovel = idPerfilImovel;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getAnoMesReferenciaFinal() {
		return anoMesReferenciaFinal;
	}

	public void setAnoMesReferenciaFinal(String anoMesReferenciaFinal) {
		this.anoMesReferenciaFinal = anoMesReferenciaFinal;
	}

	public String getAnoMesReferenciaInicial() {
		return anoMesReferenciaInicial;
	}

	public void setAnoMesReferenciaInicial(String anoMesReferenciaInicial) {
		this.anoMesReferenciaInicial = anoMesReferenciaInicial;
	}

	public String getNomeGerencia() {
		return nomeGerencia;
	}

	public void setNomeGerencia(String nomeGerencia) {
		this.nomeGerencia = nomeGerencia;
	}

	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}

	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}

}
