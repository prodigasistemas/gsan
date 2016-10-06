package gcom.faturamento.debito;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class DebitoCreditoSituacao extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String descricaoDebitoCreditoSituacao;
	private String descricaoAbreviada;
	private Date ultimaAlteracao;

	public final static Integer NORMAL = new Integer(0);
	public final static Integer RETIFICADA = new Integer(1);
	public final static Integer INCLUIDA = new Integer(2);
	public final static Integer CANCELADA = new Integer(3);
	public final static Integer CANCELADA_POR_RETIFICACAO = new Integer(4);
	public final static Integer PARCELADA = new Integer(5);
	public final static Integer ARRASTADA = new Integer(6);
	public final static Integer ENTRADA_DE_PARCELAMENTO = new Integer(7);
	public final static Integer DEBITO_PRESCRITO = new Integer(8);
	public final static Integer PRE_FATURADA = new Integer(9);
	public final static Integer CARTAO_CREDITO = new Integer(10);
	
	public final static Integer ERRO_PROCESSAMENTO = new Integer(11);
	public final static Integer DEBITO_PRESCRITO_CONTAS_INCLUIDAS = new Integer(12);
	public final static Integer PAGA = new Integer(13);

	public DebitoCreditoSituacao(String descricaoDebitoCreditoSituacao, String descricaoAbreviada, Date ultimaAlteracao) {
		this.descricaoDebitoCreditoSituacao = descricaoDebitoCreditoSituacao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public DebitoCreditoSituacao() {
	}

	public DebitoCreditoSituacao(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoDebitoCreditoSituacao() {
		return this.descricaoDebitoCreditoSituacao;
	}

	public void setDescricaoDebitoCreditoSituacao(String descricaoDebitoCreditoSituacao) {
		this.descricaoDebitoCreditoSituacao = descricaoDebitoCreditoSituacao;
	}

	public String getDescricaoAbreviada() {
		return this.descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
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
		FiltroDebitoCreditoSituacao filtro = new FiltroDebitoCreditoSituacao();
		filtro.adicionarParametro(new ParametroSimples(FiltroDebitoCreditoSituacao.ID, this.getId()));
		return filtro;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricaoDebitoCreditoSituacao();
	}
	
	@Override
	public void initializeLazy() {
		retornaCamposChavePrimaria();
		getDescricaoDebitoCreditoSituacao();
	}
}
