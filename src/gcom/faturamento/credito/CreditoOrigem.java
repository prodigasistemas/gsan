package gcom.faturamento.credito;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class CreditoOrigem extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
	// constantes de origem de crédito >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public final static Integer CONTAS_PAGAS_EM_DUPLICIDADE_EXCESSO = new Integer(
			1);

	public final static Integer DEVOLUCAO_TARIFA_AGUA = new Integer(2);

	public final static Integer DEVOLUCAO_TARIFA_ESGOTO = new Integer(3);

	public final static Integer SERVICOS_INDIRETOS_PAGOS_INDEVIDAMENTE = new Integer(
			4);

	public final static Integer DEVOLUCAO_JUROS_PARCELAMENTO = new Integer(5);

	public final static Integer DESCONTOS_CONCEDIDOS_NO_PARCELAMENTO = new Integer(
			6);

	public final static Integer VALORES_COBRADOS_INDEVIDAMENTE = new Integer(7);

	public final static Integer DESCONTOS_CONDICIONAIS = new Integer(7);

	public final static Integer DESCONTOS_INCONDICIONAIS = new Integer(8);

	public final static Integer AJUSTES_PARA_ZERAR_CONTA = new Integer(9);
	
	/**TODO: COSANPA
	 * Mantis 615 - Contabilizar créditos com crédito origem correspondente 
	 * a Contas Pagas em Excesso
	 * 
	 * @author Wellington Rocha
	 * @date */
	public final static Integer CONTAS_PAGAS_EM_EXCESSO = new Integer (10);

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricaoCreditoOrigem;

	/** nullable persistent field */
	private String descricaoAbreviada;

	/** nullable persistent field */
	private Short indicadorUso;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** full constructor */
	public CreditoOrigem(String descricaoCreditoOrigem,
			String descricaoAbreviada, Short indicadorUso, Date ultimaAlteracao) {
		this.descricaoCreditoOrigem = descricaoCreditoOrigem;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/** default constructor */
	public CreditoOrigem() {
	}

	// Construido por Sávio Luiz para setar o id no objeto
	public CreditoOrigem(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricaoCreditoOrigem() {
		return this.descricaoCreditoOrigem;
	}

	public void setDescricaoCreditoOrigem(String descricaoCreditoOrigem) {
		this.descricaoCreditoOrigem = descricaoCreditoOrigem;
	}

	public String getDescricaoAbreviada() {
		return this.descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
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
		FiltroCreditoOrigem filtro = new FiltroCreditoOrigem();
		
		filtro.adicionarParametro(
				new ParametroSimples(FiltroCreditoOrigem.ID, this.getId()));		
		return filtro;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricaoCreditoOrigem();
	}
	
	@Override
	public void initializeLazy() {
		retornaCamposChavePrimaria();
		getId();
	}
}
