package gcom.relatorio.faturamento;

import gcom.cadastro.imovel.Categoria;
import gcom.faturamento.ReceitasAFaturarResumo;

import java.math.BigDecimal;
import java.util.Date;

public class RelatorioReceitasAFaturarHelper {

	private Integer idGrupo;
	private Integer imovel;
	private String nomeCliente;
	private Date dataLeituraAnterior;
	private Date dataLeituraPrevista;
	private Integer diferencaDias;
	private Integer diasNaoFaturados;
	private BigDecimal valorAgua;
	private BigDecimal valorAguaDiario;
	private BigDecimal valorAguaAFaturar;
	private BigDecimal valorEsgoto;
	private BigDecimal valorEsgotoDiario;
	private BigDecimal valorEsgotoAFaturar;
	private Categoria categoria;

	public RelatorioReceitasAFaturarHelper() {
		super();
	}
	
	public RelatorioReceitasAFaturarHelper(ReceitasAFaturarResumo receitasAFaturarResumo) {
		this.idGrupo = receitasAFaturarResumo.getIdGrupo();
		this.dataLeituraAnterior = receitasAFaturarResumo.getDataLeituraAnterior();
		this.dataLeituraPrevista = receitasAFaturarResumo.getDataLeituraAtual();
		this.diferencaDias = receitasAFaturarResumo.getDiferencaDias();
		this.diasNaoFaturados = receitasAFaturarResumo.getDiasNaoFaturados();
		this.valorAgua = receitasAFaturarResumo.getValorAgua();
		this.valorAguaDiario = receitasAFaturarResumo.getValorAguaDiario();
		this.valorAguaAFaturar = receitasAFaturarResumo.getValorAguaAFaturar();
		this.valorEsgoto = receitasAFaturarResumo.getValorEsgoto();
		this.valorEsgotoDiario = receitasAFaturarResumo.getValorEsgotoDiario();
		this.valorEsgotoAFaturar = receitasAFaturarResumo.getValorEsgotoAFaturar();
		this.categoria = receitasAFaturarResumo.getCategoria();
	}

	public boolean gerar() {
		if (dataLeituraAnterior != null
				&& dataLeituraPrevista != null
				&& valorAgua != null
				&& valorEsgoto != null) {
			
			return true;
		} else {
			return false;
		}
	}
	
	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
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

	public Date getDataLeituraAnterior() {
		return dataLeituraAnterior;
	}

	public void setDataLeituraAnterior(Date dataLeituraAnterior) {
		this.dataLeituraAnterior = dataLeituraAnterior;
	}

	public Date getDataLeituraPrevista() {
		return dataLeituraPrevista;
	}

	public void setDataLeituraPrevista(Date dataLeituraPrevista) {
		this.dataLeituraPrevista = dataLeituraPrevista;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
