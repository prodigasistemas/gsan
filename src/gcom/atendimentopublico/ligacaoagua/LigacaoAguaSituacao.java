package gcom.atendimentopublico.ligacaoagua;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Hibernate CodeGenerator
 */

public class LigacaoAguaSituacao extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	/**
	 * identifier field
	 */
	private Integer id;

	/** nullable persistent field */
	private String descricaoAbreviado;

	/**
	 * nullable persistent field
	 */
	private String descricao;

	/**
	 * nullable persistent field
	 */
	private Short indicadorUso;

	/**
	 * nullable persistent field
	 */
	private Date ultimaAlteracao;

	private Short indicadorFaturamentoSituacao;

	private Integer consumoMinimoFaturamento;

	private Short indicadorExistenciaRede;

	private Short indicadorExistenciaLigacao;

	private Short indicadorAbastecimento;

	private Short indicadorAguaAtiva;

	private Short indicadorAguaDesligada;

	private Short indicadorAguaCadastrada;

	private Short indicadorAnalizeAgua;

	private Short indicadorConsumoReal;

	private Integer numeroDiasCorte;
	
	/**
	 * @since 19/09/2007
	 */
	private String descricaoComId;

	// --CONSTANTES

	public final static Integer POTENCIAL = new Integer(1);

	public final static Integer FACTIVEL = new Integer(2);

	public final static Integer LIGADO = new Integer(3);

	public final static Integer EM_FISCALIZACAO = new Integer(4);

	public final static Integer CORTADO = new Integer(5);

	public final static Integer SUPRIMIDO = new Integer(6);

	public final static Integer SUPR_PARC = new Integer(7);

	public final static Integer SUPR_PARC_PEDIDO = new Integer(8);

	public final static Integer EM_CANCELAMENTO = new Integer(9);

	public final static Short FATURAMENTO_ATIVO = new Short("1");

	public final static Integer LIGADO_A_REVELIA = new Integer("4");

	public final static Integer LIGADO_EM_ANALISE = new Integer("4");

	public final static Short INDICADOR_EXISTENCIA_REDE_SIM = new Short("1");

	public final static Short INDICADOR_EXISTENCIA_REDE_NAO = new Short("2");

	public final static Short INDICADOR_EXISTENCIA_LIGACAO_SIM = new Short("1");

	public final static Short INDICADOR_EXISTENCIA_LIGACAO_NAO = new Short("2");

	/**
	 * full constructor
	 * 
	 * @param descricao
	 *            Descrição do parâmetro
	 * @param indicadorUso
	 *            Descrição do parâmetro
	 * @param ultimaAlteracao
	 *            Descrição do parâmetro
	 */
	public LigacaoAguaSituacao(
			String descricao, Short indicadorUso, Date ultimaAlteracao, Short indicadorFaturamentoSituacao,
			Integer consumoMinimoFaturamento) {
		this.descricao = descricao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorFaturamentoSituacao = indicadorFaturamentoSituacao;
		this.consumoMinimoFaturamento = consumoMinimoFaturamento;
	}

	/**
	 * full constructor
	 * 
	 * @param descricao
	 *            Descrição do parâmetro
	 * @param indicadorUso
	 *            Descrição do parâmetro
	 * @param ultimaAlteracao
	 *            Descrição do parâmetro
	 */
	public LigacaoAguaSituacao(
			String descricao, String descricaoAbreviado, Short indicadorUso, Date ultimaAlteracao,
			Short indicadorFaturamentoSituacao, Integer consumoMinimoFaturamento) {
		this.descricao = descricao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.descricaoAbreviado = descricaoAbreviado;
		this.indicadorFaturamentoSituacao = indicadorFaturamentoSituacao;
		this.consumoMinimoFaturamento = consumoMinimoFaturamento;
	}

	/**
	 * default constructor
	 */
	public LigacaoAguaSituacao() {
	}
	
	public LigacaoAguaSituacao(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo descricaoAbreviado.
	 */
	public String getDescricaoAbreviado() {
		return descricaoAbreviado;
	}

	public Integer getConsumoMinimoFaturamento() {
		return consumoMinimoFaturamento;
	}

	public void setConsumoMinimoFaturamento(Integer consumoMinimoFaturamento) {
		this.consumoMinimoFaturamento = consumoMinimoFaturamento;
	}

	public Short getIndicadorFaturamentoSituacao() {
		return indicadorFaturamentoSituacao;
	}

	public void setIndicadorFaturamentoSituacao(Short indicadorFaturamentoSituacao) {
		this.indicadorFaturamentoSituacao = indicadorFaturamentoSituacao;
	}

	/**
	 * @param descricaoAbreviado
	 *            O descricaoAbreviado a ser setado.
	 */
	public void setDescricaoAbreviado(String descricaoAbreviado) {
		this.descricaoAbreviado = descricaoAbreviado;
	}

	/**
	 * Retorna o valor de id
	 * 
	 * @return O valor de id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Seta o valor de id
	 * 
	 * @param id
	 *            O novo valor de id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Retorna o valor de descricao
	 * 
	 * @return O valor de descricao
	 */
	public String getDescricao() {
		return this.descricao;
	}

	/**
	 * Seta o valor de descricao
	 * 
	 * @param descricao
	 *            O novo valor de descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Retorna o valor de indicadorUso
	 * 
	 * @return O valor de indicadorUso
	 */
	public Short getIndicadorUso() {
		return this.indicadorUso;
	}

	/**
	 * Seta o valor de indicadorUso
	 * 
	 * @param indicadorUso
	 *            O novo valor de indicadorUso
	 */
	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	/**
	 * Retorna o valor de ultimaAlteracao
	 * 
	 * @return O valor de ultimaAlteracao
	 */
	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	/**
	 * Seta o valor de ultimaAlteracao
	 * 
	 * @param ultimaAlteracao
	 *            O novo valor de ultimaAlteracao
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @return Descrição do retorno
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroLigacaoAguaSituacao filtro = new FiltroLigacaoAguaSituacao();

		filtro.adicionarParametro(new ParametroSimples(	FiltroLigacaoAguaSituacao.ID,
														this.getId()));
		return filtro;
	}

	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * @author Pedro Alexandre
	 * @date 19/09/2007
	 * 
	 * @return
	 */
	public String getDescricaoComId() {

		if (this.getId().compareTo(10) == -1) {
			descricaoComId = "0" + getId() + " - " + getDescricao();
		} else {
			descricaoComId = getId() + " - " + getDescricao();
		}

		return descricaoComId;
	}

	public String getDescricaoParaRegistroTransacao() {
		return this.descricao;
	}

	@Override
	public void initializeLazy() {
		getDescricaoParaRegistroTransacao();
	}

	/**
	 * @return Retorna o campo indicadorExistenciaLigacao.
	 */
	public Short getIndicadorExistenciaLigacao() {
		return indicadorExistenciaLigacao;
	}

	/**
	 * @param indicadorExistenciaLigacao
	 *            O indicadorExistenciaLigacao a ser setado.
	 */
	public void setIndicadorExistenciaLigacao(Short indicadorExistenciaLigacao) {
		this.indicadorExistenciaLigacao = indicadorExistenciaLigacao;
	}

	/**
	 * @return Retorna o campo indicadorExistenciaRede.
	 */
	public Short getIndicadorExistenciaRede() {
		return indicadorExistenciaRede;
	}

	/**
	 * @param indicadorExistenciaRede
	 *            O indicadorExistenciaRede a ser setado.
	 */
	public void setIndicadorExistenciaRede(Short indicadorExistenciaRede) {
		this.indicadorExistenciaRede = indicadorExistenciaRede;
	}

	public Short getIndicadorAbastecimento() {
		return indicadorAbastecimento;
	}

	public void setIndicadorAbastecimento(Short indicadorAbastecimento) {
		this.indicadorAbastecimento = indicadorAbastecimento;
	}

	public Short getIndicadorAguaAtiva() {
		return indicadorAguaAtiva;
	}

	public void setIndicadorAguaAtiva(Short indicadorAguaAtiva) {
		this.indicadorAguaAtiva = indicadorAguaAtiva;
	}

	public Short getIndicadorAguaCadastrada() {
		return indicadorAguaCadastrada;
	}

	public void setIndicadorAguaCadastrada(Short indicadorAguaCadastrada) {
		this.indicadorAguaCadastrada = indicadorAguaCadastrada;
	}

	public Short getIndicadorAguaDesligada() {
		return indicadorAguaDesligada;
	}

	public void setIndicadorAguaDesligada(Short indicadorAguaDesligada) {
		this.indicadorAguaDesligada = indicadorAguaDesligada;
	}

	public Short getIndicadorAnalizeAgua() {
		return indicadorAnalizeAgua;
	}

	public void setIndicadorAnalizeAgua(Short indicadorAnalizeAgua) {
		this.indicadorAnalizeAgua = indicadorAnalizeAgua;
	}


	public Short getIndicadorConsumoReal() {
		return indicadorConsumoReal;
	}

	public void setIndicadorConsumoReal(Short indicadorConsumoReal) {
		this.indicadorConsumoReal = indicadorConsumoReal;
	}

	public Integer getNumeroDiasCorte() {
		return numeroDiasCorte;
	}

	public void setNumeroDiasCorte(Integer numeroDiasCorte) {
		this.numeroDiasCorte = numeroDiasCorte;
	}
}
