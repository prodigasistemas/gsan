package gcom.relatorio.gerencial.micromedicao;

import gcom.relatorio.RelatorioBean;

public class RelatorioResumoZonaAbastecimentoBean implements RelatorioBean {
	private String idSetorComercial;
	private String codigoSetorComercial;
	private String nomeSetorComercial;
	
	private String idLocalidade;	
	private String nomeLocalidade;
	
	private String idDistritoOperacional;
	private String nomeDistritoOperacional;
	
	private String idUnidadeNegocio;
	private String nomeUnidadeNegocio;
	
	private String idGerenciaRegional;
	private String nomeGerenciaRegional;
 	
	private String grupo;

	private String rota;

	private Integer comHidrometro;

	private Integer semHidrometro;

	private Integer situacaoLigadas;

	private Integer situacaoCortadas;

	private Integer situacaoTotal;

	private Integer situacaoFactiveis;

	private Integer situacaoPotenciais;

	private Integer economias;

	private Integer volumeReal;

	private Integer faturadosComHidro;

	private Integer faturadosSemHidro;

	private Integer faturadosTotal;

	public RelatorioResumoZonaAbastecimentoBean(
			RelatorioResumoZonaAbastecimentoHelper helper) {
		this.idDistritoOperacional= helper.getIdDistrito();
		this.nomeDistritoOperacional = helper.getNomeDistrito();
		this.idGerenciaRegional = helper.getIdGerencia();
		this.nomeGerenciaRegional = helper.getNomeGerencia();
		this.idLocalidade = helper.getIdLocalidade();
		this.nomeLocalidade = helper.getNomeLocalidade();
		this.codigoSetorComercial = helper.getCodigoSetor();
		this.idSetorComercial = helper.getIdSetor();
		this.nomeSetorComercial = helper.getNomeSetor();
		this.rota = helper.getRota().toString();
		this.comHidrometro = helper.getComHidrometro();
		this.semHidrometro = helper.getSemHidrometro();
		this.situacaoLigadas = helper.getSituacaoLigadas();
		this.situacaoCortadas = helper.getSituacaoCortadas();
		this.situacaoTotal = helper.getSituacaoTotal();
		this.situacaoFactiveis = helper.getSituacaoFactiveis();
		this.situacaoPotenciais = helper.getSituacaoPotenciais();
		this.economias = helper.getEconomias();
		this.volumeReal = helper.getVolumeReal();
		this.faturadosComHidro = helper.getFaturadosComHidro();
		this.faturadosSemHidro = helper.getFaturadosSemHidro();
		this.faturadosTotal = helper.getFaturadosTotal();
	}

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Integer getComHidrometro() {
		return comHidrometro;
	}

	public void setComHidrometro(Integer comHidrometro) {
		this.comHidrometro = comHidrometro;
	}

	public Integer getEconomias() {
		return economias;
	}

	public void setEconomias(Integer economias) {
		this.economias = economias;
	}

	public Integer getFaturadosComHidro() {
		return faturadosComHidro;
	}

	public void setFaturadosComHidro(Integer faturadosComHidro) {
		this.faturadosComHidro = faturadosComHidro;
	}

	public Integer getFaturadosSemHidro() {
		return faturadosSemHidro;
	}

	public void setFaturadosSemHidro(Integer faturadosSemHidro) {
		this.faturadosSemHidro = faturadosSemHidro;
	}

	public Integer getFaturadosTotal() {
		return faturadosTotal;
	}

	public void setFaturadosTotal(Integer faturadosTotal) {
		this.faturadosTotal = faturadosTotal;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getIdDistritoOperacional() {
		return idDistritoOperacional;
	}

	public void setIdDistritoOperacional(String idDistritoOperacional) {
		this.idDistritoOperacional = idDistritoOperacional;
	}

	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getNomeDistritoOperacional() {
		return nomeDistritoOperacional;
	}

	public void setNomeDistritoOperacional(String nomeDistritoOperacional) {
		this.nomeDistritoOperacional = nomeDistritoOperacional;
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

	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}

	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}

	public Integer getSemHidrometro() {
		return semHidrometro;
	}

	public void setSemHidrometro(Integer semHidrometro) {
		this.semHidrometro = semHidrometro;
	}

	public Integer getSituacaoCortadas() {
		return situacaoCortadas;
	}

	public void setSituacaoCortadas(Integer situacaoCortadas) {
		this.situacaoCortadas = situacaoCortadas;
	}

	public Integer getSituacaoFactiveis() {
		return situacaoFactiveis;
	}

	public void setSituacaoFactiveis(Integer situacaoFactiveis) {
		this.situacaoFactiveis = situacaoFactiveis;
	}

	public Integer getSituacaoLigadas() {
		return situacaoLigadas;
	}

	public void setSituacaoLigadas(Integer situacaoLigadas) {
		this.situacaoLigadas = situacaoLigadas;
	}

	public Integer getSituacaoPotenciais() {
		return situacaoPotenciais;
	}

	public void setSituacaoPotenciais(Integer situacaoPotenciais) {
		this.situacaoPotenciais = situacaoPotenciais;
	}

	public Integer getSituacaoTotal() {
		return situacaoTotal;
	}

	public void setSituacaoTotal(Integer situacaoTotal) {
		this.situacaoTotal = situacaoTotal;
	}

	public Integer getVolumeReal() {
		return volumeReal;
	}

	public void setVolumeReal(Integer volumeReal) {
		this.volumeReal = volumeReal;
	}

	

}
