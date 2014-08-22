package gcom.atendimentopublico.ligacaoagua;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class LigacaoAguaSituacao extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String descricaoAbreviado;
	private String descricao;
	private Short indicadorUso;
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
	
	private String descricaoComId;

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

	public LigacaoAguaSituacao(
			String descricao, Short indicadorUso, Date ultimaAlteracao, Short indicadorFaturamentoSituacao,
			Integer consumoMinimoFaturamento) {
		this.descricao = descricao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorFaturamentoSituacao = indicadorFaturamentoSituacao;
		this.consumoMinimoFaturamento = consumoMinimoFaturamento;
	}

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

	public LigacaoAguaSituacao() {
	}
	
	public LigacaoAguaSituacao(Integer id) {
		this.id = id;
	}

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

	public void setDescricaoAbreviado(String descricaoAbreviado) {
		this.descricaoAbreviado = descricaoAbreviado;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

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

	public Short getIndicadorExistenciaLigacao() {
		return indicadorExistenciaLigacao;
	}

	public void setIndicadorExistenciaLigacao(Short indicadorExistenciaLigacao) {
		this.indicadorExistenciaLigacao = indicadorExistenciaLigacao;
	}

	public Short getIndicadorExistenciaRede() {
		return indicadorExistenciaRede;
	}

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
