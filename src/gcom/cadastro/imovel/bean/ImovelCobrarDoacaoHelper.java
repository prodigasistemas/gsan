package gcom.cadastro.imovel.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Hibernate CodeGenerator
 * @created 1 de Junho de 2004
 */
public class ImovelCobrarDoacaoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** * Constantes ** */
	public static final short INDICADOR_HISTORICO = 2;

	public static final short NUMERO_PRESTACAO_DEBITO = 1;

	public static final short NUMERO_PRESTACAO_COBRADA = 0;

	public static final BigDecimal TAXA_JURO_FINANCIAMENTO = new BigDecimal(0);

	public static final int DEBITO_CREDITO_SITUACAO_ATUAL = 0;

	public static final int COBRANCA_FORMA = 1;

	/** * Campos internos ** */
	private Integer idImovel;

	private Integer idDebitoTipo;

	private Integer anoMesReferenciaContabil;

	private BigDecimal valorDebito;

	private Integer idLocalidade;

	private Integer idQuadra;

	private Integer codigoSetorComercial;

	private Integer numeroQuadra;

	private Short numeroLote;

	private Short numeroSubLote;

	private Integer financiamentoTipo;

	private Integer lancamentoItemContabil;

	private Integer anoMesReferenciaInicial;
	
	private Integer anoMesReferenciaFinal;
	
	private Integer idImovelDoacao;

	public ImovelCobrarDoacaoHelper(Integer idImovel, Integer idDebitoTipo,
			Integer anoMesReferenciaContabil, BigDecimal valorDebito,
			Integer idLocalidade, Integer idQuadra,
			Integer codigoSetorComercial, Integer numeroQuadra,
			Short numeroLote, Short numeroSubLote, Integer financiamentoTipo,
			Integer lancamentoItemContabil) {

		this.idImovel = idImovel;
		this.idDebitoTipo = idDebitoTipo;
		this.anoMesReferenciaContabil = anoMesReferenciaContabil;
		this.valorDebito = valorDebito;
		this.idLocalidade = idLocalidade;
		this.idQuadra = idQuadra;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.numeroLote = numeroLote;
		this.numeroSubLote = numeroSubLote;
		this.financiamentoTipo = financiamentoTipo;
		this.lancamentoItemContabil = lancamentoItemContabil;
	}

	public ImovelCobrarDoacaoHelper(Integer idImovel, Integer idDebitoTipo,
			BigDecimal valorDebito, Integer idLocalidade, Integer idQuadra,
			Integer codigoSetorComercial, Integer numeroQuadra,
			Short numeroLote, Short numeroSubLote, Integer financiamentoTipo,
			Integer lancamentoItemContabil) {

		this.idImovel = idImovel;
		this.idDebitoTipo = idDebitoTipo;
		this.valorDebito = valorDebito;
		this.idLocalidade = idLocalidade;
		this.idQuadra = idQuadra;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.numeroLote = numeroLote;
		this.numeroSubLote = numeroSubLote;
		this.financiamentoTipo = financiamentoTipo;
		this.lancamentoItemContabil = lancamentoItemContabil;
	}
	

	
	public ImovelCobrarDoacaoHelper(Integer idImovel, Integer idDebitoTipo,
		    BigDecimal valorDebito,
			Integer idLocalidade, Integer idQuadra,
			Integer codigoSetorComercial, Integer numeroQuadra,
			Short numeroLote, Short numeroSubLote, Integer financiamentoTipo,
			Integer lancamentoItemContabil,Integer anoMesReferenciaFinal,Integer idImovelDoacao) {

		this.idImovel = idImovel;
		this.idDebitoTipo = idDebitoTipo;
		this.valorDebito = valorDebito;
		this.idLocalidade = idLocalidade;
		this.idQuadra = idQuadra;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.numeroLote = numeroLote;
		this.numeroSubLote = numeroSubLote;
		this.financiamentoTipo = financiamentoTipo;
		this.lancamentoItemContabil = lancamentoItemContabil;
		this.anoMesReferenciaFinal = anoMesReferenciaFinal;
		this.idImovelDoacao = idImovelDoacao;
	}
	
	public ImovelCobrarDoacaoHelper(Integer idImovel, Integer idDebitoTipo,
		    BigDecimal valorDebito,
			Integer idLocalidade, Integer idQuadra,
			Integer codigoSetorComercial, Integer numeroQuadra,
			Short numeroLote, Short numeroSubLote, Integer financiamentoTipo,
			Integer lancamentoItemContabil,Integer anoMesReferenciaFinal,Integer idImovelDoacao,Integer anoMesReferenciaInicial) {

		this.idImovel = idImovel;
		this.idDebitoTipo = idDebitoTipo;
		this.valorDebito = valorDebito;
		this.idLocalidade = idLocalidade;
		this.idQuadra = idQuadra;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.numeroLote = numeroLote;
		this.numeroSubLote = numeroSubLote;
		this.financiamentoTipo = financiamentoTipo;
		this.lancamentoItemContabil = lancamentoItemContabil;
		this.anoMesReferenciaFinal = anoMesReferenciaFinal;
		this.idImovelDoacao = idImovelDoacao;
		this.anoMesReferenciaInicial = anoMesReferenciaInicial;
	}

	public Integer getAnoMesReferenciaContabil() {
		return anoMesReferenciaContabil;
	}

	public void setAnoMesReferenciaContabil(Integer anoMesReferenciaContabil) {
		this.anoMesReferenciaContabil = anoMesReferenciaContabil;
	}

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Integer getFinanciamentoTipo() {
		return financiamentoTipo;
	}

	public void setFinanciamentoTipo(Integer financiamentoTipo) {
		this.financiamentoTipo = financiamentoTipo;
	}

	public Integer getIdDebitoTipo() {
		return idDebitoTipo;
	}

	public void setIdDebitoTipo(Integer idDebitoTipo) {
		this.idDebitoTipo = idDebitoTipo;
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

	public Integer getIdQuadra() {
		return idQuadra;
	}

	public void setIdQuadra(Integer idQuadra) {
		this.idQuadra = idQuadra;
	}

	public Integer getLancamentoItemContabil() {
		return lancamentoItemContabil;
	}

	public void setLancamentoItemContabil(Integer lancamentoItemContabil) {
		this.lancamentoItemContabil = lancamentoItemContabil;
	}

	public Short getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(Short numeroLote) {
		this.numeroLote = numeroLote;
	}

	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Short getNumeroSubLote() {
		return numeroSubLote;
	}

	public void setNumeroSubLote(Short numeroSubLote) {
		this.numeroSubLote = numeroSubLote;
	}

	public BigDecimal getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}

	public Integer getAnoMesReferenciaFinal() {
		return anoMesReferenciaFinal;
	}

	public void setAnoMesReferenciaFinal(Integer anoMesReferenciaFinal) {
		this.anoMesReferenciaFinal = anoMesReferenciaFinal;
	}

	public Integer getIdImovelDoacao() {
		return idImovelDoacao;
	}

	public void setIdImovelDoacao(Integer idImovelDoacao) {
		this.idImovelDoacao = idImovelDoacao;
	}

	public Integer getAnoMesReferenciaInicial() {
		return anoMesReferenciaInicial;
	}

	public void setAnoMesReferenciaInicial(Integer anoMesReferenciaInicial) {
		this.anoMesReferenciaInicial = anoMesReferenciaInicial;
	}
}
