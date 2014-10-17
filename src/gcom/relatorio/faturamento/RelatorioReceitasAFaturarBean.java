package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;

public class RelatorioReceitasAFaturarBean implements RelatorioBean {
	
	private Integer idGrupo;
	private String dataLeituraAnterior;
	private String dataLeituraAtual;
	private Integer diferencaDias;
	private Integer diasNaoFaturados;
	private BigDecimal valorAgua;
	private BigDecimal valorAguaDiario;
	private BigDecimal valorAguaAFaturar;
	private BigDecimal valorEsgoto;
	private BigDecimal valorEsgotoDiario;
	private BigDecimal valorEsgotoAFaturar;
	private Integer imovel;
	private String nomeCliente;
	
	public RelatorioReceitasAFaturarBean() {
		super();
	}
	
	public RelatorioReceitasAFaturarBean(RelatorioReceitasAFaturarHelper helper) {
		super();
		this.idGrupo = helper.getIdGrupo();
		Format formatter = new SimpleDateFormat("dd/MM/yyyy");
		this.dataLeituraAnterior = formatter.format(helper.getDataLeituraAnterior());
		this.dataLeituraAtual = formatter.format(helper.getDataLeituraPrevista());
		this.diferencaDias = helper.getDiferencaDias();
		this.diasNaoFaturados = helper.getDiasNaoFaturados();
		this.valorAgua = helper.getValorAgua();
		this.valorAguaDiario = helper.getValorAguaDiario();
		this.valorAguaAFaturar = helper.getValorAguaAFaturar();
		this.valorEsgoto = helper.getValorEsgoto();
		this.valorEsgotoDiario = helper.getValorEsgotoDiario();
		this.valorEsgotoAFaturar = helper.getValorEsgotoAFaturar();
		this.imovel = helper.getImovel();
		this.nomeCliente = helper.getNomeCliente();
	}

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getDataLeituraAnterior() {
		return dataLeituraAnterior;
	}

	public void setDataLeituraAnterior(String dataLeituraAnterior) {
		this.dataLeituraAnterior = dataLeituraAnterior;
	}

	public String getDataLeituraAtual() {
		return dataLeituraAtual;
	}

	public void setDataLeituraAtual(String dataLeituraAtual) {
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

	public Integer getImovel() {
		return imovel;
	}

	public void setImovel(Integer imovel) {
		this.imovel = imovel;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

}
