package gcom.relatorio.gerencial.micromedicao;

import java.io.Serializable;

public class RelatorioResumoZonaAbastecimentoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String idSetor;
	private String nomeSetor;
	private String codigoSetor;
	
	private String idLocalidade;
	private String nomeLocalidade;
	
	private String idDistrito;
	private String nomeDistrito;
	
	private String idUnidade;
	private String nomeUnidade;
	
	private String idGerencia;
	private String nomeGerencia;
	
	private Integer grupo;
	private Short rota;
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
	public Integer getFaturadosTotal() {
		return faturadosTotal;
	}
	public void setFaruradosTotal(Integer faruradosTotal) {
		this.faturadosTotal = faruradosTotal;
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
	public Integer getGrupo() {
		return grupo;
	}
	public void setGrupo(Integer grupo) {
		this.grupo = grupo;
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
	public Short getRota() {
		return rota;
	}
	public void setRota(Short rota) {
		this.rota = rota;
	}
	public void setFaturadosTotal(Integer faturadosTotal) {
		this.faturadosTotal = faturadosTotal;
	}
	public String getIdDistrito() {
		return idDistrito;
	}
	public void setIdDistrito(String idDistrito) {
		this.idDistrito = idDistrito;
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
	public String getIdSetor() {
		return idSetor;
	}
	public void setIdSetor(String idSetor) {
		this.idSetor = idSetor;
	}
	public String getIdUnidade() {
		return idUnidade;
	}
	public void setIdUnidade(String idUnidade) {
		this.idUnidade = idUnidade;
	}
	public String getNomeDistrito() {
		return nomeDistrito;
	}
	public void setNomeDistrito(String nomeDistrito) {
		this.nomeDistrito = nomeDistrito;
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
	public String getNomeSetor() {
		return nomeSetor;
	}
	public void setNomeSetor(String nomeSetor) {
		this.nomeSetor = nomeSetor;
	}
	public String getNomeUnidade() {
		return nomeUnidade;
	}
	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}
	public String getCodigoSetor() {
		return codigoSetor;
	}
	public void setCodigoSetor(String codigoSetor) {
		this.codigoSetor = codigoSetor;
	}
}
