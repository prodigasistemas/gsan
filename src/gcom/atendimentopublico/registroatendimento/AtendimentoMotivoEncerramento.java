package gcom.atendimentopublico.registroatendimento;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class AtendimentoMotivoEncerramento extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String descricao;

	private String descricaoAbreviada;

	private short indicadorUso;

	private Date ultimaAlteracao;

	private short indicadorExecucao;

	private short indicadorDuplicidade;

	private Integer qtdeDiasAditivoPrazo;

	private Short indicadorExibirFormOrdemSeletiva;

	public final static Short INDICADOR_DUPLICIDADE_ATIVO = new Short("1");

	public final static Short INDICADOR_DUPLICIDADE_INATIVO = new Short("2");

	public final static short INDICADOR_EXECUCAO_SIM = 1;

	public final static short INDICADOR_EXECUCAO_NAO = 2;

	public final static short CANCELADO_POR_DERCURSO_DE_PRAZO = 32;

	public final static short SUSPENSA_PAG_PARC_CANC_DO_DEBITO = 68;

	public final static Integer CONCLUSAO_SERVICO = 2;

	public final static Integer DEBITO_PAGO = 3;

	public final static Integer TARIFA_SOCIAL_ALTERADA = 53;
	public final static Integer TARIFA_SOCIAL_EXCLUIDA = 54;
	public final static Integer TARIFA_SOCIAL_IMPLANTADA = 15;
	public final static Integer ATUALIZAR_EXCLUIR_RECADASTRAR_TARIFA_SOCIAL = 55;

	public final static short DESISTENCIA_DO_USUARIO = 4;

	public AtendimentoMotivoEncerramento(String descricao, String descricaoAbreviada, short indicadorUso, Date ultimaAlteracao, short indicadorExecucao, short indicadorDuplicidade) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorExecucao = indicadorExecucao;
		this.indicadorDuplicidade = indicadorDuplicidade;
	}

	public AtendimentoMotivoEncerramento() {
	}
	
	public AtendimentoMotivoEncerramento(Integer id) {
		this.id = id;
	} 

	public AtendimentoMotivoEncerramento(short indicadorUso, Date ultimaAlteracao, short indicadorExecucao) {
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.indicadorExecucao = indicadorExecucao;
	}

	public short getIndicadorDuplicidade() {
		return indicadorDuplicidade;
	}

	public void setIndicadorDuplicidade(short indicadorDuplicidade) {
		this.indicadorDuplicidade = indicadorDuplicidade;
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

	public String getDescricaoAbreviada() {
		return this.descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public short getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public short getIndicadorExecucao() {
		return this.indicadorExecucao;
	}

	public void setIndicadorExecucao(short indicadorExecucao) {
		this.indicadorExecucao = indicadorExecucao;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Filtro retornaFiltro() {
		FiltroAtendimentoMotivoEncerramento filtro = new FiltroAtendimentoMotivoEncerramento();

		filtro.adicionarParametro(new ParametroSimples(FiltroAtendimentoMotivoEncerramento.ID, this.getId()));
		return filtro;
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}

	public Integer getQtdeDiasAditivoPrazo() {
		return qtdeDiasAditivoPrazo;
	}

	public void setQtdeDiasAditivoPrazo(Integer qtdeDiasAditivoPrazo) {
		this.qtdeDiasAditivoPrazo = qtdeDiasAditivoPrazo;
	}

	public Short getIndicadorExibirFormOrdemSeletiva() {
		return indicadorExibirFormOrdemSeletiva;
	}

	public void setIndicadorExibirFormOrdemSeletiva(Short indicadorExibirFormOrdemSeletiva) {
		this.indicadorExibirFormOrdemSeletiva = indicadorExibirFormOrdemSeletiva;
	}
}
