package gcom.relatorio.cadastro.imovel;

import java.io.Serializable;


/**
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * do relatorio de imoveis por Consumo Medio 
 *
 * @author Bruno Barros
 * @date 17/12/2007
 */
public class RelatorioImoveisConsumoMedioHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String inscricaoImovel;
	
	private Integer unidadeNegocio;
	private String nomeUnidadeNegocio;
	
	private Integer gerenciaRegional;
	private String nomeGerenciaRegional;
	
	private Integer localidade;
	private String descricaoLocalidade;
	
	private Integer setorComercial;
	private String descricaoSetorComercial;
	
	private Short codigoRota;
	private Integer sequencialRota;
	
	private String nomeCliente;
	private String endereco;
	private String matriculaImovel;

	private String situacaoLigacaoAgua;
	private String situacaoLigacaoEsgoto;
	
	private Integer consumoMedioAgua;
	private Integer consumoMedioEsgoto;

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}
	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Integer getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(Integer gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public Integer getLocalidade() {
		return localidade;
	}
	public void setLocalidade(Integer localidade) {
		this.localidade = localidade;
	}
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}
	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}
	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}
	public Integer getSequencialRota() {
		return sequencialRota;
	}
	public void setSequencialRota(Integer sequencialRota) {
		this.sequencialRota = sequencialRota;
	}
	public Integer getSetorComercial() {
		return setorComercial;
	}
	public void setSetorComercial(Integer setorComercial) {
		this.setorComercial = setorComercial;
	}
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}
	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}
	public Integer getUnidadeNegocio() {
		return unidadeNegocio;
	}
	public void setUnidadeNegocio(Integer unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	
	public Integer getConsumoMedioAgua() {
	
		return consumoMedioAgua;
	}

	
	public void setConsumoMedioAgua(Integer consumoMedioAgua) {
	
		this.consumoMedioAgua = consumoMedioAgua;
	}

	
	public Integer getConsumoMedioEsgoto() {
	
		return consumoMedioEsgoto;
	}

	
	public void setConsumoMedioEsgoto(Integer consumoMedioEsgoto) {
	
		this.consumoMedioEsgoto = consumoMedioEsgoto;
	}

	
	public String getSituacaoLigacaoEsgoto() {
	
		return situacaoLigacaoEsgoto;
	}

	
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
	
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}

	
	public Short getCodigoRota() {
	
		return codigoRota;
	}

	
	public void setCodigoRota(Short codigoRota) {
	
		this.codigoRota = codigoRota;
	}
	
	
	
}
