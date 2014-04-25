package gcom.faturamento.debito;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class DebitoCreditoSituacao extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricaoDebitoCreditoSituacao;

	/** nullable persistent field */
	private String descricaoAbreviada;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	// Constantes
	public final static Integer NORMAL = new Integer(0);

	public final static Integer RETIFICADA = new Integer(1);

	public final static Integer INCLUIDA = new Integer(2);

	public final static Integer CANCELADA = new Integer(3);

	public final static Integer CANCELADA_POR_RETIFICACAO = new Integer(4);

	public final static Integer PARCELADA = new Integer(5);

	public final static Integer ARRASTADA = new Integer(6);

	public final static Integer ENTRADA_DE_PARCELAMENTO = new Integer(7);

	public final static Integer PAGA = new Integer(8);
	
	public final static Integer PRE_FATURADA = new Integer(9);
	
	public final static Integer CARTAO_CREDITO = new Integer(10);
	
	/* INICIO CONSTANTES NOVAS */
	public final static Integer DEBITO_PRESCRITO = new Integer(8);
	
	/**TODO: COSANPA
	 * Inlusão de nova constante para poder gerar a prescrição de débitos de todas as contas
	 * sem que ocorra problema com a restrição da chave xak1_conta*/
	public final static Integer DEBITO_PRESCRITO_CONTAS_INCLUIDAS = new Integer(12);
	
	/*
	 * TODO Cosanpa
	 * Mantis 225: Inclusão de constante para novo tipo de Situação.
	 */
	public final static Integer ERRO_PROCESSAMENTO = new Integer(11);
	/* FIM CONSTANTES NOVAS */

	/** full constructor */
	public DebitoCreditoSituacao(String descricaoDebitoCreditoSituacao,
			String descricaoAbreviada, Date ultimaAlteracao) {
		this.descricaoDebitoCreditoSituacao = descricaoDebitoCreditoSituacao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/** default constructor */
	public DebitoCreditoSituacao() {
	}

	// Construido por Sávio Luiz para setar o id no objeto
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

	public void setDescricaoDebitoCreditoSituacao(
			String descricaoDebitoCreditoSituacao) {
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

		filtro.adicionarParametro(new ParametroSimples(FiltroDebitoCreditoSituacao.ID,
				this.getId()));
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
