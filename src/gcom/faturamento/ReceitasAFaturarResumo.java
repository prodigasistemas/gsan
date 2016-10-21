package gcom.faturamento;

import gcom.cadastro.imovel.Categoria;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.relatorio.faturamento.RelatorioReceitasAFaturarHelper;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

@ControleAlteracao
public class ReceitasAFaturarResumo extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer anoMesReferencia;
	
	private Integer idGrupo;
	
	private Date dataLeituraAnterior;
	
	private Date dataLeituraAtual;
	
	private Integer diferencaDias;
	
	private Integer diasNaoFaturados;
	
	private BigDecimal valorAgua;
	
	private BigDecimal valorAguaDiario;
	
	private BigDecimal valorAguaAFaturar;
	
	private BigDecimal valorEsgoto;
	
	private BigDecimal valorEsgotoDiario;
	
	private BigDecimal valorEsgotoAFaturar;
	
	private Date ultimaAlteracao;
	
	private Categoria categoria;
	
	public ReceitasAFaturarResumo() {
		super();
	}
	
	public ReceitasAFaturarResumo(RelatorioReceitasAFaturarHelper helper) {
		super();
		this.idGrupo = helper.getIdGrupo();
		this.dataLeituraAnterior = helper.getDataLeituraAnterior();
		this.dataLeituraAtual = helper.getDataLeituraPrevista();
		this.diferencaDias = helper.getDiferencaDias();
		this.diasNaoFaturados = helper.getDiasNaoFaturados();
		this.valorAgua = helper.getValorAgua();
		this.valorAguaDiario = helper.getValorAguaDiario();
		this.valorAguaAFaturar = helper.getValorAguaAFaturar();
		this.valorEsgoto = helper.getValorEsgoto();
		this.valorEsgotoDiario = helper.getValorEsgotoDiario();
		this.valorEsgotoAFaturar = helper.getValorEsgotoAFaturar();
		this.categoria = helper.getCategoria();
	}

	public ReceitasAFaturarResumo(Integer idGrupo, Date dataLeituraAnterior, Date dataLeituraAtual, Integer diferencaDias,
			Integer diasNaoFaturados, BigDecimal valorAgua, BigDecimal valorAguaDiario, BigDecimal valorAguaAFaturar, BigDecimal valorEsgoto, 
			BigDecimal valorEsgotoDiario, BigDecimal valorEsgotoAFaturar, Categoria categoria) {
		super();
		this.idGrupo = idGrupo;
		this.dataLeituraAnterior = dataLeituraAnterior;
		this.dataLeituraAtual = dataLeituraAtual;
		this.diferencaDias = diferencaDias;
		this.diasNaoFaturados = diasNaoFaturados;
		this.valorAgua = valorAgua;
		this.valorAguaDiario = valorAguaDiario;
		this.valorAguaAFaturar = valorAguaAFaturar;
		this.valorEsgoto = valorEsgoto;
		this.valorEsgotoDiario = valorEsgotoDiario;
		this.valorEsgotoAFaturar = valorEsgotoAFaturar;
		this.categoria = categoria;
	}
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	public Date getDataLeituraAnterior() {
		return dataLeituraAnterior;
	}

	public void setDataLeituraAnterior(Date dataLeituraAnterior) {
		this.dataLeituraAnterior = dataLeituraAnterior;
	}

	public Date getDataLeituraAtual() {
		return dataLeituraAtual;
	}

	public void setDataLeituraAtual(Date dataLeituraAtual) {
		this.dataLeituraAtual = dataLeituraAtual;
	}

	public Integer getDiferencaDias() {
		return diferencaDias;
	}

	public void setDiferencaDias(Integer diferencaDias) {
		this.diferencaDias = diferencaDias;
	}

	public Integer getDiasNaoFaturados() {
		return diasNaoFaturados;
	}

	public void setDiasNaoFaturados(Integer diasNaoFaturados) {
		this.diasNaoFaturados = diasNaoFaturados;
	}

	public BigDecimal getValorAgua() {
		return valorAgua;
	}

	public void setValorAgua(BigDecimal valorAgua) {
		this.valorAgua = valorAgua;
	}

	public BigDecimal getValorAguaDiario() {
		return valorAguaDiario;
	}

	public void setValorAguaDiario(BigDecimal valorAguaDiario) {
		this.valorAguaDiario = valorAguaDiario;
	}

	public BigDecimal getValorAguaAFaturar() {
		return valorAguaAFaturar;
	}

	public void setValorAguaAFaturar(BigDecimal valorAguaAFaturar) {
		this.valorAguaAFaturar = valorAguaAFaturar;
	}

	public BigDecimal getValorEsgoto() {
		return valorEsgoto;
	}

	public void setValorEsgoto(BigDecimal valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}

	public BigDecimal getValorEsgotoDiario() {
		return valorEsgotoDiario;
	}

	public void setValorEsgotoDiario(BigDecimal valorEsgotoDiario) {
		this.valorEsgotoDiario = valorEsgotoDiario;
	}

	public BigDecimal getValorEsgotoAFaturar() {
		return valorEsgotoAFaturar;
	}

	public void setValorEsgotoAFaturar(BigDecimal valorEsgotoAFaturar) {
		this.valorEsgotoAFaturar = valorEsgotoAFaturar;
	}

	@Override
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public Filtro retornaFiltro() {
		FiltroReceitasAFaturarResumo filtro = new FiltroReceitasAFaturarResumo(FiltroReceitasAFaturarResumo.GRUPO_ID);
		filtro.adicionarParametro(new ParametroSimples(FiltroReceitasAFaturarResumo.ANO_MES_REFERENCIA, this.getAnoMesReferencia()));
		
		return filtro;
	}
	
	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
}
