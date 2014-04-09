package gcom.gerencial.bean;

import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class CobrancaAcaoHelper {

	private Integer id;

	private String descricao;

	private Collection colecaoCobrancaAcaoSituacao;

	private Date realizacaoEmitir;

	private Date realizacaoEncerrar;

	private int somatorioQuantidadesDocumentos;

	private BigDecimal somatorioValoresDocumentos;
	
	private String indicadorDefinitivo;
	
	private boolean geraOrdemServico = false;
	
	private Integer numeroDiasRemuneracaoTerceiro = null;

	public CobrancaAcaoHelper(Integer id, String descricao, Integer idServicoTipo, 
		Date realizacaoEmitir, Date realizacaoEncerrar, int somatorioQuantidadesDocumentos, BigDecimal somatorioValoresDocumentos, Integer numeroDiasRemuneracaoTerceiro) {
		this(id, descricao, idServicoTipo, realizacaoEmitir, realizacaoEncerrar, 
				somatorioQuantidadesDocumentos, somatorioValoresDocumentos);
		this.numeroDiasRemuneracaoTerceiro = numeroDiasRemuneracaoTerceiro;
	}

	/**
	 * @return Retorna o campo colecaoCobrancaAcaoSituacao.
	 */
	public Collection getColecaoCobrancaAcaoSituacao() {
		return colecaoCobrancaAcaoSituacao;
	}

	/**
	 * @param colecaoCobrancaAcaoSituacao
	 *            O colecaoCobrancaAcaoSituacao a ser setado.
	 */
	public void setColecaoCobrancaAcaoSituacao(
			Collection colecaoCobrancaAcaoSituacao) {
		this.colecaoCobrancaAcaoSituacao = colecaoCobrancaAcaoSituacao;
	}

	/**
	 * @return Retorna o campo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            O descricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public CobrancaAcaoHelper(Integer id, String descricao,
			Integer idServicoTipo,
			Date realizacaoEmitir, Date realizacaoEncerrar,
			int somatorioQuantidadesDocumentos,
			BigDecimal somatorioValoresDocumentos) {
		this.id = id;
		this.descricao = descricao;
		this.geraOrdemServico = idServicoTipo != null;
		this.realizacaoEmitir = realizacaoEmitir;
		this.realizacaoEncerrar = realizacaoEncerrar;
		this.somatorioQuantidadesDocumentos = somatorioQuantidadesDocumentos;
		this.somatorioValoresDocumentos = somatorioValoresDocumentos;
	}

	/**
	 * @return Retorna o campo realizacaoEmitir.
	 */
	public String getRealizacaoEmitir() {
		String dataEmitir = Util.formatarData(realizacaoEmitir);
		return dataEmitir;
	}

	/**
	 * @param realizacaoEmitir
	 *            O realizacaoEmitir a ser setado.
	 */
	public void setRealizacaoEmitir(Date realizacaoEmitir) {
		this.realizacaoEmitir = realizacaoEmitir;
	}

	/**
	 * @return Retorna o campo realizacaoEncerrar.
	 */
	public String getRealizacaoEncerrar() {
		String dataEncerrar = Util.formatarData(realizacaoEncerrar);
		return dataEncerrar;
	}

	/**
	 * @param realizacaoEncerrar
	 *            O realizacaoEncerrar a ser setado.
	 */
	public void setRealizacaoEncerrar(Date realizacaoEncerrar) {
		this.realizacaoEncerrar = realizacaoEncerrar;
	}

	public int getSomatorioQuantidadesDocumentos() {
		return somatorioQuantidadesDocumentos;
	}

	public void setSomatorioQuantidadesDocumentos(
			int somatorioQuantidadesDocumentos) {
		this.somatorioQuantidadesDocumentos = somatorioQuantidadesDocumentos;
	}

	public BigDecimal getSomatorioValoresDocumentos() {
		return somatorioValoresDocumentos;
	}

	public void setSomatorioValoresDocumentos(
			BigDecimal somatorioValoresDocumentos) {
		this.somatorioValoresDocumentos = somatorioValoresDocumentos;
	}

	public String getIndicadorDefinitivo() {
		return indicadorDefinitivo;
	}

	public void setIndicadorDefinitivo(String indicadorDefinitivo) {
		this.indicadorDefinitivo = indicadorDefinitivo;
	}

	public boolean isGeraOrdemServico() {
		return geraOrdemServico;
	}

	public void setGeraOrdemServico(boolean geraOrdemServico) {
		this.geraOrdemServico = geraOrdemServico;
	}

	/**
	 * @return Retorna o campo numeroDiasRemuneracaoTerceiro.
	 */
	public Integer getNumeroDiasRemuneracaoTerceiro() {
		return numeroDiasRemuneracaoTerceiro;
	}

	/**
	 * @param numeroDiasRemuneracaoTerceiro O numeroDiasRemuneracaoTerceiro a ser setado.
	 */
	public void setNumeroDiasRemuneracaoTerceiro(
			Integer numeroDiasRemuneracaoTerceiro) {
		this.numeroDiasRemuneracaoTerceiro = numeroDiasRemuneracaoTerceiro;
	}

}
