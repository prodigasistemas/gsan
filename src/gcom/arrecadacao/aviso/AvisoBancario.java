package gcom.arrecadacao.aviso;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.ArrecadadorMovimento;
import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.banco.ContaBancaria;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class AvisoBancario extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	public static final int AVISO_BANCARIO_ATUALIZAR = 3; //Operacao.OPERACAO_AVISO_BANCARIO_ATUALIZAR
	public static final int AVISO_BANCARIO_INSERIR = 4; //Operacao.OPERACAO_AVISO_BANCARIO_INSERIR

	public static final int ATUALIZAR_AVISO_BANCARIO = 1714; //Operacao.OPERACAO_ATUALIZAR_AVISO_BANCARIO
	public static final int AVISO_BANCARIO_REMOVER = 501; //Operacao.OPERACAO_REMOVER_AVISO_BANCARIO
	
	/** identifier field */
	private Integer id;

	/** persistent field */
	@ControleAlteracao(funcionalidade={ATUALIZAR_AVISO_BANCARIO, AVISO_BANCARIO_INSERIR, AVISO_BANCARIO_REMOVER})
	private Date dataLancamento;

	/** persistent field */
	private BigDecimal valorContabilizado;

	/** persistent field */
	private int anoMesReferenciaArrecadacao;

	/** persistent field */
	private Short indicadorCreditoDebito;

	/** persistent field */
	@ControleAlteracao(funcionalidade={ATUALIZAR_AVISO_BANCARIO, AVISO_BANCARIO_INSERIR, AVISO_BANCARIO_REMOVER})
	private int numeroDocumento;

	/** nullable persistent field */
	//private BigDecimal valorDevolucao;

	/** nullable persistent field */
	//private BigDecimal valorArrecadacao;

	/** nullable persistent field */
	private Short numeroSequencial;

	/** nullable persistent field */
	private BigDecimal valorRealizado;

	/** nullable persistent field */
	private Date dataPrevista;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATUALIZAR_AVISO_BANCARIO, AVISO_BANCARIO_INSERIR, AVISO_BANCARIO_REMOVER})
	private Date dataRealizada;

	/** nullable persistent field */
	//private BigDecimal valorPrevisto;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private ContaBancaria contaBancaria;

	/** persistent field */
	@ControleAlteracao(value=FiltroAvisoBancario.ARRECADADOR, funcionalidade={AVISO_BANCARIO_INSERIR, AVISO_BANCARIO_REMOVER})
	private Arrecadador arrecadador;

	/** persistent field */
	private ArrecadadorMovimento arrecadadorMovimento;
	
	/** nullable persistent field */
	private BigDecimal valorArrecadacaoCalculado;

	/** nullable persistent field */
	private BigDecimal valorDevolucaoCalculado;
	
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATUALIZAR_AVISO_BANCARIO, AVISO_BANCARIO_INSERIR, AVISO_BANCARIO_REMOVER})
	private BigDecimal valorArrecadacaoInformado;
	
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATUALIZAR_AVISO_BANCARIO, AVISO_BANCARIO_INSERIR, AVISO_BANCARIO_REMOVER})
	private BigDecimal valorDevolucaoInformado;
	
	/** persistent field */
	@ControleAlteracao(value=FiltroAvisoBancario.ARRECADACAO_FORMA, funcionalidade={ATUALIZAR_AVISO_BANCARIO, AVISO_BANCARIO_INSERIR, AVISO_BANCARIO_REMOVER})
	private ArrecadacaoForma arrecadacaoForma;
	
	/**
	 * Description of the Field
	 */
	public final static Short INDICADOR_CREDITO = new Short("1");
	public final static String CREDITO = "CRÉDITO";
	public final static String SIGLA_CREDITO = "C";

	/**
	 * Description of the Field
	 */
	public final static Short INDICADOR_DEBITO = new Short("2");
	public final static String DEBITO = "DÉBITO";
	public final static String SIGLA_DEBITO = "D";
	

	/** full constructor */
	public AvisoBancario(Date dataLancamento, BigDecimal valorContabilizado,
			int anoMesReferenciaArrecadacao, Short indicadorCreditoDebito,
			int numeroDocumento, BigDecimal valorDevolucao,
			BigDecimal valorArrecadacao, Short numeroSequencial,
			BigDecimal valorRealizado, Date dataPrevista, Date dataRealizada,
			BigDecimal valorPrevisto, Date ultimaAlteracao,
			ContaBancaria contaBancaria, Arrecadador arrecadador,
			ArrecadadorMovimento arrecadadorMovimento) {
		this.dataLancamento = dataLancamento;
		this.valorContabilizado = valorContabilizado;
		this.anoMesReferenciaArrecadacao = anoMesReferenciaArrecadacao;
		this.indicadorCreditoDebito = indicadorCreditoDebito;
		this.numeroDocumento = numeroDocumento;
		/*this.valorDevolucao = valorDevolucao;
		this.valorArrecadacao = valorArrecadacao;*/
		this.numeroSequencial = numeroSequencial;
		this.valorRealizado = valorRealizado;
		this.dataPrevista = dataPrevista;
		this.dataRealizada = dataRealizada;
		//this.valorPrevisto = valorPrevisto;
		this.ultimaAlteracao = ultimaAlteracao;
		this.contaBancaria = contaBancaria;
		this.arrecadador = arrecadador;
		this.arrecadadorMovimento = arrecadadorMovimento;
	}

	/** default constructor */
	public AvisoBancario() {
	}

	/** minimal constructor */
	public AvisoBancario(Date dataLancamento, BigDecimal valorContabilizado,
			int anoMesReferenciaArrecadacao, Short indicadorCreditoDebito,
			int numeroDocumento, ContaBancaria contaBancaria,
			Arrecadador arrecadador, ArrecadadorMovimento arrecadadorMovimento) {
		this.dataLancamento = dataLancamento;
		this.valorContabilizado = valorContabilizado;
		this.anoMesReferenciaArrecadacao = anoMesReferenciaArrecadacao;
		this.indicadorCreditoDebito = indicadorCreditoDebito;
		this.numeroDocumento = numeroDocumento;
		this.contaBancaria = contaBancaria;
		this.arrecadador = arrecadador;
		this.arrecadadorMovimento = arrecadadorMovimento;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataLancamento() {
		return this.dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public BigDecimal getValorContabilizado() {
		return this.valorContabilizado;
	}

	public void setValorContabilizado(BigDecimal valorContabilizado) {
		this.valorContabilizado = valorContabilizado;
	}

	public int getAnoMesReferenciaArrecadacao() {
		return this.anoMesReferenciaArrecadacao;
	}

	public void setAnoMesReferenciaArrecadacao(int anoMesReferenciaArrecadacao) {
		this.anoMesReferenciaArrecadacao = anoMesReferenciaArrecadacao;
	}

	public Short getIndicadorCreditoDebito() {
		return this.indicadorCreditoDebito;
	}

	public void setIndicadorCreditoDebito(Short indicadorCreditoDebito) {
		this.indicadorCreditoDebito = indicadorCreditoDebito;
	}

	public int getNumeroDocumento() {
		return this.numeroDocumento;
	}

	public void setNumeroDocumento(int numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/*public BigDecimal getValorDevolucao() {
		return this.valorDevolucao;
	}

	public void setValorDevolucao(BigDecimal valorDevolucao) {
		this.valorDevolucao = valorDevolucao;
	}

	public BigDecimal getValorArrecadacao() {
		return this.valorArrecadacao;
	}

	public void setValorArrecadacao(BigDecimal valorArrecadacao) {
		this.valorArrecadacao = valorArrecadacao;
	}*/

	public Short getNumeroSequencial() {
		return this.numeroSequencial;
	}

	public void setNumeroSequencial(Short numeroSequencial) {
		this.numeroSequencial = numeroSequencial;
	}

	public BigDecimal getValorRealizado() {
		return this.valorRealizado;
	}

	public void setValorRealizado(BigDecimal valorRealizado) {
		this.valorRealizado = valorRealizado;
	}

	public Date getDataPrevista() {
		return this.dataPrevista;
	}

	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public Date getDataRealizada() {
		return this.dataRealizada;
	}

	public void setDataRealizada(Date dataRealizada) {
		this.dataRealizada = dataRealizada;
	}

	/*public BigDecimal getValorPrevisto() {
		return this.valorPrevisto;
	}

	public void setValorPrevisto(BigDecimal valorPrevisto) {
		this.valorPrevisto = valorPrevisto;
	}*/

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public ContaBancaria getContaBancaria() {
		return this.contaBancaria;
	}

	public void setContaBancaria(ContaBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	public Arrecadador getArrecadador() {
		return this.arrecadador;
	}

	public void setArrecadador(Arrecadador arrecadador) {
		this.arrecadador = arrecadador;
	}

	public ArrecadadorMovimento getArrecadadorMovimento() {
		return this.arrecadadorMovimento;
	}

	public void setArrecadadorMovimento(
			ArrecadadorMovimento arrecadadorMovimento) {
		this.arrecadadorMovimento = arrecadadorMovimento;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public Filtro retornaFiltro() {
		FiltroAvisoBancario filtroAvisoBancario  = new FiltroAvisoBancario();
        filtroAvisoBancario.adicionarParametro(new ParametroSimples(FiltroAvisoBancario.ID, this.getId()));
        filtroAvisoBancario.adicionarCaminhoParaCarregamentoEntidade(FiltroAvisoBancario.ARRECADADOR);
        filtroAvisoBancario.adicionarCaminhoParaCarregamentoEntidade(FiltroAvisoBancario.CONTA_BANCARIA);
		return filtroAvisoBancario ;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] ids = {"id"};
		return ids;
	}

	/**
	 * @return Retorna o campo valorArrecadacaoCalculado.
	 */
	public BigDecimal getValorArrecadacaoCalculado() {
		return valorArrecadacaoCalculado;
	}

	/**
	 * @param valorArrecadacaoCalculado O valorArrecadacaoCalculado a ser setado.
	 */
	public void setValorArrecadacaoCalculado(BigDecimal valorArrecadacaoCalculado) {
		this.valorArrecadacaoCalculado = valorArrecadacaoCalculado;
	}

	/**
	 * @return Retorna o campo valorArrecadacaoInformado.
	 */
	public BigDecimal getValorArrecadacaoInformado() {
		return valorArrecadacaoInformado;
	}

	/**
	 * @param valorArrecadacaoInformado O valorArrecadacaoInformado a ser setado.
	 */
	public void setValorArrecadacaoInformado(BigDecimal valorArrecadacaoInformado) {
		this.valorArrecadacaoInformado = valorArrecadacaoInformado;
	}

	/**
	 * @return Retorna o campo valorDevolucaoCalculado.
	 */
	public BigDecimal getValorDevolucaoCalculado() {
		return valorDevolucaoCalculado;
	}

	/**
	 * @param valorDevolucaoCalculado O valorDevolucaoCalculado a ser setado.
	 */
	public void setValorDevolucaoCalculado(BigDecimal valorDevolucaoCalculado) {
		this.valorDevolucaoCalculado = valorDevolucaoCalculado;
	}

	/**
	 * @return Retorna o campo valorDevolucaoInformado.
	 */
	public BigDecimal getValorDevolucaoInformado() {
		return valorDevolucaoInformado;
	}

	/**
	 * @param valorDevolucaoInformado O valorDevolucaoInformado a ser setado.
	 */
	public void setValorDevolucaoInformado(BigDecimal valorDevolucaoInformado) {
		this.valorDevolucaoInformado = valorDevolucaoInformado;
	}

	public ArrecadacaoForma getArrecadacaoForma() {
		return arrecadacaoForma;
	}

	public void setArrecadacaoForma(ArrecadacaoForma arrecadacaoForma) {
		this.arrecadacaoForma = arrecadacaoForma;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getArrecadador().getCodigoAgente() + " - " + Util.formatarData(getDataLancamento());
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = { "numeroDocumento",
							"valorArrecadacaoInformado"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Num. Documento",
						   "Valor da Arrecadacao"};
		return labels;		
	}
	
}
