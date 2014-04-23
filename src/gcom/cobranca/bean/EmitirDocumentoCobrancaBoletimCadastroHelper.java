package gcom.cobranca.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/** @author Hibernate CodeGenerator */
public class EmitirDocumentoCobrancaBoletimCadastroHelper implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idDocumentoCobranca;
	
	private Integer idLocalidade;

	private Integer codigoSetorComercial;

	private int numeroQuadra;

	private short lote;
	
	private short subLote;

	private Integer idImovel;

    private Integer idCobrancaGrupo;
	
    private Integer idLigacaoAguaSituacao;
    
    private Integer idLigacaoEsgotoSituacao;
    
	private Short numeroMorador;
    
    private BigDecimal areaConstruida;
    
    private Integer idLogradouro;
    
    private Integer codigoCep;
    
    private Integer codigoBairro;
    
    private Integer referencia;
    
    private String numeroImovel;
    
    private String complemento;
    
    private Integer volumeReservatorioInferior;
    
    private Integer volumeReservatorioSuperior;
    
    private Integer volumePiscina;
    
    private Short jardim;
    
    private Integer idPavimentoRua;
    
    private Integer idPavimentoCalcada;
    
    private Short numeroPontosUtilizacao;
    
    private Integer idImovelPerfil;
    
    private Integer idDespejo;
    
    private Integer idPoco;
    
    private Integer idFonteAbastecimento;
    
	private BigDecimal numeroIptu;
	
	private Long numeroCelpe;
	
	private Short codigoRota;
	private Integer numeroSequencialRota;
    
	/**
	 * @return Retorna o campo idFonteAbastecimento.
	 */
	public Integer getIdFonteAbastecimento() {
		return idFonteAbastecimento;
	}

	/**
	 * @param idFonteAbastecimento O idFonteAbastecimento a ser setado.
	 */
	public void setIdFonteAbastecimento(Integer idFonteAbastecimento) {
		this.idFonteAbastecimento = idFonteAbastecimento;
	}

	/**
	 * @return Retorna o campo idPoco.
	 */
	public Integer getIdPoco() {
		return idPoco;
	}

	/**
	 * @param idPoco O idPoco a ser setado.
	 */
	public void setIdPoco(Integer idPoco) {
		this.idPoco = idPoco;
	}

	/**
	 * @return Retorna o campo idDespejo.
	 */
	public Integer getIdDespejo() {
		return idDespejo;
	}

	/**
	 * @param idDespejo O idDespejo a ser setado.
	 */
	public void setIdDespejo(Integer idDespejo) {
		this.idDespejo = idDespejo;
	}

	/**
	 * @return Retorna o campo idImovelPerfil.
	 */
	public Integer getIdImovelPerfil() {
		return idImovelPerfil;
	}

	/**
	 * @param idImovelPerfil O idImovelPerfil a ser setado.
	 */
	public void setIdImovelPerfil(Integer idImovelPerfil) {
		this.idImovelPerfil = idImovelPerfil;
	}

	/**
	 * @return Retorna o campo idPavimentoCalcada.
	 */
	public Integer getIdPavimentoCalcada() {
		return idPavimentoCalcada;
	}

	/**
	 * @param idPavimentoCalcada O idPavimentoCalcada a ser setado.
	 */
	public void setIdPavimentoCalcada(Integer idPavimentoCalcada) {
		this.idPavimentoCalcada = idPavimentoCalcada;
	}

	/**
	 * @return Retorna o campo idPavimentoRua.
	 */
	public Integer getIdPavimentoRua() {
		return idPavimentoRua;
	}

	/**
	 * @param idPavimentoRua O idPavimentoRua a ser setado.
	 */
	public void setIdPavimentoRua(Integer idPavimentoRua) {
		this.idPavimentoRua = idPavimentoRua;
	}

	/**
	 * @return Retorna o campo jardim.
	 */
	public Short getJardim() {
		return jardim;
	}

	/**
	 * @param jardim O jardim a ser setado.
	 */
	public void setJardim(Short jardim) {
		this.jardim = jardim;
	}

	/**
	 * @return Retorna o campo numeroPontosUtilizacao.
	 */
	public Short getNumeroPontosUtilizacao() {
		return numeroPontosUtilizacao;
	}

	/**
	 * @param numeroPontosUtilizacao O numeroPontosUtilizacao a ser setado.
	 */
	public void setNumeroPontosUtilizacao(Short numeroPontosUtilizacao) {
		this.numeroPontosUtilizacao = numeroPontosUtilizacao;
	}

	/**
	 * @return Retorna o campo volumePiscina.
	 */
	public Integer getVolumePiscina() {
		return volumePiscina;
	}

	/**
	 * @param volumePiscina O volumePiscina a ser setado.
	 */
	public void setVolumePiscina(Integer volumePiscina) {
		this.volumePiscina = volumePiscina;
	}

	/**
	 * @return Retorna o campo volumeReservatorioInferior.
	 */
	public Integer getVolumeReservatorioInferior() {
		return volumeReservatorioInferior;
	}

	/**
	 * @param volumeReservatorioInferior O volumeReservatorioInferior a ser setado.
	 */
	public void setVolumeReservatorioInferior(Integer volumeReservatorioInferior) {
		this.volumeReservatorioInferior = volumeReservatorioInferior;
	}

	/**
	 * @return Retorna o campo volumeReservatorioSuperior.
	 */
	public Integer getVolumeReservatorioSuperior() {
		return volumeReservatorioSuperior;
	}

	/**
	 * @param volumeReservatorioSuperior O volumeReservatorioSuperior a ser setado.
	 */
	public void setVolumeReservatorioSuperior(Integer volumeReservatorioSuperior) {
		this.volumeReservatorioSuperior = volumeReservatorioSuperior;
	}

	/**
	 * @return Retorna o campo areaConstruida.
	 */
	public BigDecimal getAreaConstruida() {
		return areaConstruida;
	}

	/**
	 * @param areaConstruida O areaConstruida a ser setado.
	 */
	public void setAreaConstruida(BigDecimal areaConstruida) {
		this.areaConstruida = areaConstruida;
	}

	/**
	 * @return Retorna o campo codigoBairro.
	 */
	public Integer getCodigoBairro() {
		return codigoBairro;
	}

	/**
	 * @param codigoBairro O codigoBairro a ser setado.
	 */
	public void setCodigoBairro(Integer codigoBairro) {
		this.codigoBairro = codigoBairro;
	}

	/**
	 * @return Retorna o campo codigoCep.
	 */
	public Integer getCodigoCep() {
		return codigoCep;
	}

	/**
	 * @param codigoCep O codigoCep a ser setado.
	 */
	public void setCodigoCep(Integer codigoCep) {
		this.codigoCep = codigoCep;
	}

	/**
	 * @return Retorna o campo complemento.
	 */
	public String getComplemento() {
		return complemento;
	}

	/**
	 * @param complemento O complemento a ser setado.
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/**
	 * @return Retorna o campo idLogradouro.
	 */
	public Integer getIdLogradouro() {
		return idLogradouro;
	}

	/**
	 * @param idLogradouro O idLogradouro a ser setado.
	 */
	public void setIdLogradouro(Integer idLogradouro) {
		this.idLogradouro = idLogradouro;
	}

	/**
	 * @return Retorna o campo numeroImovel.
	 */
	public String getNumeroImovel() {
		return numeroImovel;
	}

	/**
	 * @param numeroImovel O numeroImovel a ser setado.
	 */
	public void setNumeroImovel(String numeroImovel) {
		this.numeroImovel = numeroImovel;
	}

	/**
	 * @return Retorna o campo numeroMorador.
	 */
	public Short getNumeroMorador() {
		return numeroMorador;
	}

	/**
	 * @param numeroMorador O numeroMorador a ser setado.
	 */
	public void setNumeroMorador(Short numeroMorador) {
		this.numeroMorador = numeroMorador;
	}

	/**
	 * @return Retorna o campo referencia.
	 */
	public Integer getReferencia() {
		return referencia;
	}

	/**
	 * @param referencia O referencia a ser setado.
	 */
	public void setReferencia(Integer referencia) {
		this.referencia = referencia;
	}

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Integer getIdCobrancaGrupo() {
		return idCobrancaGrupo;
	}

	public void setIdCobrancaGrupo(Integer idCobrancaGrupo) {
		this.idCobrancaGrupo = idCobrancaGrupo;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public short getLote() {
		return lote;
	}

	public void setLote(short lote) {
		this.lote = lote;
	}

	public int getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(int numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public short getSubLote() {
		return subLote;
	}

	public void setSubLote(short subLote) {
		this.subLote = subLote;
	}
	
	public Integer getIdDocumentoCobranca() {
		return idDocumentoCobranca;
	}

	public void setIdDocumentoCobranca(Integer idDocumentoCobranca) {
		this.idDocumentoCobranca = idDocumentoCobranca;
	}

	public Integer getIdLigacaoAguaSituacao() {
		return idLigacaoAguaSituacao;
	}

	public void setIdLigacaoAguaSituacao(Integer idLigacaoAguaSituacao) {
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
	}

	public Integer getIdLigacaoEsgotoSituacao() {
		return idLigacaoEsgotoSituacao;
	}

	public void setIdLigacaoEsgotoSituacao(Integer idLigacaoEsgotoSituacao) {
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
	}
	
	public EmitirDocumentoCobrancaBoletimCadastroHelper() {
	}
	
	public EmitirDocumentoCobrancaBoletimCadastroHelper(
			Integer idDocumentoCobranca, Integer idLocalidade,
			Integer codigoSetorComercial, int numeroQuadra, short lote,
			short subLote, Integer idImovel, Integer idCobrancaGrupo,
			Integer idLigacaoAguaSituacao, Integer idLigacaoEsgotoSituacao,
			Short numeroMorador, BigDecimal areaConstruida,
			Integer idLogradouro, Integer codigoCep, Integer codigoBairro,
			Integer referencia, String numeroImovel, String complemento,
			Integer volumeReservatorioInferior,
			Integer volumeReservatorioSuperior, Integer volumePiscina,
			Short jardim, Integer idPavimentoRua, Integer idPavimentoCalcada,
			Short numeroPontosUtilizacao, Integer idImovelPerfil,
			Integer idDespejo, Integer idPoco, Integer idFonteAbastecimento,
			BigDecimal numeroIptu, Long numeroCelpe,Short codigoRota,
			Integer numeroSequencialRota) {
		
		
		
		this.idDocumentoCobranca = idDocumentoCobranca;
		this.idLocalidade = idLocalidade;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.lote = lote;
		this.subLote = subLote;
		this.idImovel = idImovel;
		this.idCobrancaGrupo = idCobrancaGrupo;
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
		this.numeroMorador = numeroMorador;
		this.areaConstruida = areaConstruida;
		this.idLogradouro = idLogradouro;
		this.codigoCep = codigoCep;
		this.codigoBairro = codigoBairro;
		this.referencia = referencia;
		this.numeroImovel = numeroImovel;
		this.complemento = complemento;
		this.volumeReservatorioInferior = volumeReservatorioInferior;
		this.volumeReservatorioSuperior = volumeReservatorioSuperior;
		this.volumePiscina = volumePiscina;
		this.jardim = jardim;
		this.idPavimentoRua = idPavimentoRua;
		this.idPavimentoCalcada = idPavimentoCalcada;
		this.numeroPontosUtilizacao = numeroPontosUtilizacao;
		this.idImovelPerfil = idImovelPerfil;
		this.idDespejo = idDespejo;
		this.idPoco = idPoco;
		this.idFonteAbastecimento = idFonteAbastecimento;
		this.numeroIptu = numeroIptu;
		this.numeroCelpe = numeroCelpe;
	}

	/**
	 * @return Retorna o campo numeroCelpe.
	 */
	public Long getNumeroCelpe() {
		return numeroCelpe;
	}

	/**
	 * @param numeroCelpe O numeroCelpe a ser setado.
	 */
	public void setNumeroCelpe(Long numeroCelpe) {
		this.numeroCelpe = numeroCelpe;
	}

	/**
	 * @return Retorna o campo numeroIptu.
	 */
	public BigDecimal getNumeroIptu() {
		return numeroIptu;
	}

	/**
	 * @param numeroIptu O numeroIptu a ser setado.
	 */
	public void setNumeroIptu(BigDecimal numeroIptu) {
		this.numeroIptu = numeroIptu;
	}

	public Short getCodigoRota() {
		return codigoRota;
	}

	public Integer getNumeroSequencialRota() {
		return numeroSequencialRota;
	}

	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
	}

	public void setNumeroSequencialRota(Integer numeroSequencialRota) {
		this.numeroSequencialRota = numeroSequencialRota;
	}
	
	
	
	
}
