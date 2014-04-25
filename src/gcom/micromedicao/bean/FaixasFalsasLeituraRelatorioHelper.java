package gcom.micromedicao.bean;

import java.util.Date;



/**
 * @author Raphael Rossiter
 * @date 03/03/2007
 */
public class FaixasFalsasLeituraRelatorioHelper {
	
	private Integer idGrupoFaturamento;
	private String descricaoGrupoFaturamento;
	private Integer idEmpresa;
	private String nomeEmpresa;
	private Integer idLocalidade;
	private String nomeLocalidade;
	private Integer idSetorComercial;
	private Integer codigoSetorComercial;
	private String nomeSetorComercial;
	private Integer faixaFalsaInferior;
	private Integer faixaFalsaSuperior;
	private Integer leituraAtual;
	private Integer numeroQuadra;
	private Short lote;
	private Short sublote;
	private Integer idImovel;
	private Integer idLeiturista;
	private Integer faixaCorretaInferior;
	private Integer faixaCorretaSuperior;
	private Date dataLeitura;
	private String descricaoLeituraAnormalidade;
	private String descricaoSituacaoLeitura;
	
	public FaixasFalsasLeituraRelatorioHelper(){}

	/**
	 * @return Retorna o campo codigoSetorComercial.
	 */
	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	/**
	 * @param codigoSetorComercial O codigoSetorComercial a ser setado.
	 */
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	/**
	 * @return Retorna o campo dataLeitura.
	 */
	public Date getDataLeitura() {
		return dataLeitura;
	}

	/**
	 * @param dataLeitura O dataLeitura a ser setado.
	 */
	public void setDataLeitura(Date dataLeitura) {
		this.dataLeitura = dataLeitura;
	}

	/**
	 * @return Retorna o campo descricaoGrupoFaturamento.
	 */
	public String getDescricaoGrupoFaturamento() {
		return descricaoGrupoFaturamento;
	}

	/**
	 * @param descricaoGrupoFaturamento O descricaoGrupoFaturamento a ser setado.
	 */
	public void setDescricaoGrupoFaturamento(String descricaoGrupoFaturamento) {
		this.descricaoGrupoFaturamento = descricaoGrupoFaturamento;
	}

	/**
	 * @return Retorna o campo descricaoLeituraAnormalidade.
	 */
	public String getDescricaoLeituraAnormalidade() {
		return descricaoLeituraAnormalidade;
	}

	/**
	 * @param descricaoLeituraAnormalidade O descricaoLeituraAnormalidade a ser setado.
	 */
	public void setDescricaoLeituraAnormalidade(String descricaoLeituraAnormalidade) {
		this.descricaoLeituraAnormalidade = descricaoLeituraAnormalidade;
	}

	/**
	 * @return Retorna o campo descricaoSituacaoLeitura.
	 */
	public String getDescricaoSituacaoLeitura() {
		return descricaoSituacaoLeitura;
	}

	/**
	 * @param descricaoSituacaoLeitura O descricaoSituacaoLeitura a ser setado.
	 */
	public void setDescricaoSituacaoLeitura(String descricaoSituacaoLeitura) {
		this.descricaoSituacaoLeitura = descricaoSituacaoLeitura;
	}

	/**
	 * @return Retorna o campo faixaCorretaInferior.
	 */
	public Integer getFaixaCorretaInferior() {
		return faixaCorretaInferior;
	}

	/**
	 * @param faixaCorretaInferior O faixaCorretaInferior a ser setado.
	 */
	public void setFaixaCorretaInferior(Integer faixaCorretaInferior) {
		this.faixaCorretaInferior = faixaCorretaInferior;
	}

	/**
	 * @return Retorna o campo faixaCorretaSuperior.
	 */
	public Integer getFaixaCorretaSuperior() {
		return faixaCorretaSuperior;
	}

	/**
	 * @param faixaCorretaSuperior O faixaCorretaSuperior a ser setado.
	 */
	public void setFaixaCorretaSuperior(Integer faixaCorretaSuperior) {
		this.faixaCorretaSuperior = faixaCorretaSuperior;
	}

	/**
	 * @return Retorna o campo faixaFalsaInferior.
	 */
	public Integer getFaixaFalsaInferior() {
		return faixaFalsaInferior;
	}

	/**
	 * @param faixaFalsaInferior O faixaFalsaInferior a ser setado.
	 */
	public void setFaixaFalsaInferior(Integer faixaFalsaInferior) {
		this.faixaFalsaInferior = faixaFalsaInferior;
	}

	/**
	 * @return Retorna o campo faixaFalsaSuperior.
	 */
	public Integer getFaixaFalsaSuperior() {
		return faixaFalsaSuperior;
	}

	/**
	 * @param faixaFalsaSuperior O faixaFalsaSuperior a ser setado.
	 */
	public void setFaixaFalsaSuperior(Integer faixaFalsaSuperior) {
		this.faixaFalsaSuperior = faixaFalsaSuperior;
	}

	/**
	 * @return Retorna o campo idEmpresa.
	 */
	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * @param idEmpresa O idEmpresa a ser setado.
	 */
	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	/**
	 * @return Retorna o campo idGrupoFaturamento.
	 */
	public Integer getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}

	/**
	 * @param idGrupoFaturamento O idGrupoFaturamento a ser setado.
	 */
	public void setIdGrupoFaturamento(Integer idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}

	/**
	 * @return Retorna o campo idImovel.
	 */
	public Integer getIdImovel() {
		return idImovel;
	}

	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	/**
	 * @return Retorna o campo idLeiturista.
	 */
	public Integer getIdLeiturista() {
		return idLeiturista;
	}

	/**
	 * @param idLeiturista O idLeiturista a ser setado.
	 */
	public void setIdLeiturista(Integer idLeiturista) {
		this.idLeiturista = idLeiturista;
	}

	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * @return Retorna o campo idSetorComercial.
	 */
	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}

	/**
	 * @param idSetorComercial O idSetorComercial a ser setado.
	 */
	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	/**
	 * @return Retorna o campo leituraAtual.
	 */
	public Integer getLeituraAtual() {
		return leituraAtual;
	}

	/**
	 * @param leituraAtual O leituraAtual a ser setado.
	 */
	public void setLeituraAtual(Integer leituraAtual) {
		this.leituraAtual = leituraAtual;
	}

	/**
	 * @return Retorna o campo lote.
	 */
	public Short getLote() {
		return lote;
	}

	/**
	 * @param lote O lote a ser setado.
	 */
	public void setLote(Short lote) {
		this.lote = lote;
	}

	/**
	 * @return Retorna o campo nomeEmpresa.
	 */
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	/**
	 * @param nomeEmpresa O nomeEmpresa a ser setado.
	 */
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	/**
	 * @return Retorna o campo nomeLocalidade.
	 */
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	/**
	 * @param nomeLocalidade O nomeLocalidade a ser setado.
	 */
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	/**
	 * @return Retorna o campo nomeSetorComercial.
	 */
	public String getNomeSetorComercial() {
		return nomeSetorComercial;
	}

	/**
	 * @param nomeSetorComercial O nomeSetorComercial a ser setado.
	 */
	public void setNomeSetorComercial(String nomeSetorComercial) {
		this.nomeSetorComercial = nomeSetorComercial;
	}

	/**
	 * @return Retorna o campo numeroQuadra.
	 */
	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	/**
	 * @param numeroQuadra O numeroQuadra a ser setado.
	 */
	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	/**
	 * @return Retorna o campo sublote.
	 */
	public Short getSublote() {
		return sublote;
	}

	/**
	 * @param sublote O sublote a ser setado.
	 */
	public void setSublote(Short sublote) {
		this.sublote = sublote;
	}

}
