package gcom.atendimentopublico.ordemservico;

import gcom.faturamento.debito.DebitoTipo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class FiscalizacaoSituacaoServicoACobrar implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private FiscalizacaoSituacaoServicoACobrarPK comp_id;

	/** persistent field */
	private short indicadorHidrometroCapacidade;

	/** persistent field */
	private short numeroVezesServicoCalculadoValor;

	private Short consumoCalculo;

	/** persistent field */
	private Date ultimaAlteracao;

	/** nullable persistent field */
	private DebitoTipo debitoTipo;
	
	/** persistent field */
	private BigDecimal percentualParticipacaoMultaFuncionario;
	
	/** persistent field */
	private short indicadorMultaInfracao;

	/** nullable persistent field */
	private FiscalizacaoSituacao fiscalizacaoSituacao;
	
	private BigDecimal valorMultaAutoInfracao;
	
	private Short numeroVezesServicoCalculadoValorReincidencia;
	
	public static final Short CONSUMO_CALCULO_ZERO = 0;
	
	public static final Short CONSUMO_CALCULO_UM = 1;

	public static final Short CONSUMO_CALCULO_DOIS = 2;

	public static final Short CONSUMO_CALCULO_TRES = 3;

	public static final Short CONSUMO_CALCULO_QUATRO = 4;
	
	public static final Short CONSUMO_CALCULO_CINCO = 5;
	
	public static final Short CONSUMO_CALCULO_SEIS = 6;
	
	public static final Short CONSUMO_CALCULO_SETE = 7;
	
	public static final Short CONSUMO_CALCULO_OITO = 8;
	
	public static final Short CONSUMO_CALCULO_NOVE = 9;
	
	public static final Short CONSUMO_CALCULO_DEZ = 10;
	
	public static final Short CONSUMO_CALCULO_ONZE = 11;

	public String toString() {
		return new ToStringBuilder(this).append("comp_id", getComp_id())
				.toString();
	}

	/**
	 * @return Retorna o campo comp_id.
	 */
	public FiscalizacaoSituacaoServicoACobrarPK getComp_id() {
		return comp_id;
	}

	/**
	 * @param comp_id
	 *            O comp_id a ser setado.
	 */
	public void setComp_id(FiscalizacaoSituacaoServicoACobrarPK comp_id) {
		this.comp_id = comp_id;
	}

	/**
	 * @return Retorna o campo debitoTipo.
	 */
	public DebitoTipo getDebitoTipo() {
		return debitoTipo;
	}

	/**
	 * @param debitoTipo
	 *            O debitoTipo a ser setado.
	 */
	public void setDebitoTipo(DebitoTipo debitoTipo) {
		this.debitoTipo = debitoTipo;
	}

	/**
	 * @return Retorna o campo fiscalizacaoSituacao.
	 */
	public FiscalizacaoSituacao getFiscalizacaoSituacao() {
		return fiscalizacaoSituacao;
	}

	/**
	 * @param fiscalizacaoSituacao
	 *            O fiscalizacaoSituacao a ser setado.
	 */
	public void setFiscalizacaoSituacao(
			FiscalizacaoSituacao fiscalizacaoSituacao) {
		this.fiscalizacaoSituacao = fiscalizacaoSituacao;
	}

	/**
	 * @return Retorna o campo indicadorHidrometroCapacidade.
	 */
	public short getIndicadorHidrometroCapacidade() {
		return indicadorHidrometroCapacidade;
	}

	/**
	 * @param indicadorHidrometroCapacidade
	 *            O indicadorHidrometroCapacidade a ser setado.
	 */
	public void setIndicadorHidrometroCapacidade(
			short indicadorHidrometroCapacidade) {
		this.indicadorHidrometroCapacidade = indicadorHidrometroCapacidade;
	}

	/**
	 * @return Retorna o campo numeroVezesServicoCalculadoValor.
	 */
	public short getNumeroVezesServicoCalculadoValor() {
		return numeroVezesServicoCalculadoValor;
	}

	/**
	 * @param numeroVezesServicoCalculadoValor
	 *            O numeroVezesServicoCalculadoValor a ser setado.
	 */
	public void setNumeroVezesServicoCalculadoValor(
			short numeroVezesServicoCalculadoValor) {
		this.numeroVezesServicoCalculadoValor = numeroVezesServicoCalculadoValor;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao
	 *            O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Short getConsumoCalculo() {
		return consumoCalculo;
	}

	public void setConsumoCalculo(Short consumoCalculo) {
		this.consumoCalculo = consumoCalculo;
	}

	public BigDecimal getValorMultaAutoInfracao() {
		return valorMultaAutoInfracao;
	}

	public void setValorMultaAutoInfracao(BigDecimal valorMultaAutoInfracao) {
		this.valorMultaAutoInfracao = valorMultaAutoInfracao;
	}

	public short getIndicadorMultaInfracao() {
		return indicadorMultaInfracao;
	}

	public void setIndicadorMultaInfracao(short indicadorMultaInfracao) {
		this.indicadorMultaInfracao = indicadorMultaInfracao;
	}

	public BigDecimal getPercentualParticipacaoMultaFuncionario() {
		return percentualParticipacaoMultaFuncionario;
	}

	public void setPercentualParticipacaoMultaFuncionario(
			BigDecimal percentualParticipacaoMultaFuncionario) {
		this.percentualParticipacaoMultaFuncionario = percentualParticipacaoMultaFuncionario;
	}

	public Short getNumeroVezesServicoCalculadoValorReincidencia() {
		return numeroVezesServicoCalculadoValorReincidencia;
	}

	public void setNumeroVezesServicoCalculadoValorReincidencia(
			Short numeroVezesServicoCalculadoValorReincidencia) {
		this.numeroVezesServicoCalculadoValorReincidencia = numeroVezesServicoCalculadoValorReincidencia;
	}

}
