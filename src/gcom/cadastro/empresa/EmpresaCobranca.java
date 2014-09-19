package gcom.cadastro.empresa;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

public class EmpresaCobranca extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private Empresa empresa;

	/** nullable persistent field */
	private BigDecimal percentualContratoCobranca;

	/** nullable persistent field */
	private Date dataInicioContrato;

	/** nullable persistent field */
	@SuppressWarnings("unused")
	private Date ultimaAlteracao;

	/** nullable persistent field */
	private Date dataFinalContrato;

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroEmpresaCobranca filtroEmpresaCobranca = new FiltroEmpresaCobranca();

		filtroEmpresaCobranca.adicionarParametro(new ParametroSimples(
				FiltroEmpresaCobranca.ID, this.getId()));
		return filtroEmpresaCobranca;
	}

	public Date getDataFinalContrato() {
		return dataFinalContrato;
	}

	public void setDataFinalContrato(Date dataFinalContrato) {
		this.dataFinalContrato = dataFinalContrato;
	}

	public Date getDataInicioContrato() {
		return dataInicioContrato;
	}

	public void setDataInicioContrato(Date dataInicioContrato) {
		this.dataInicioContrato = dataInicioContrato;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getPercentualContratoCobranca() {
		return percentualContratoCobranca;
	}

	public void setPercentualContratoCobranca(
			BigDecimal percentualContratoCobranca) {
		this.percentualContratoCobranca = percentualContratoCobranca;
	}

	public EmpresaCobranca(Integer id, Empresa empresa,
			BigDecimal percentualContratoCobranca, Date dataInicioContrato,
			Date ultimaAlteracao, Date dataFinalContrato) {
		super();
		
		this.id = id;
		this.empresa = empresa;
		this.percentualContratoCobranca = percentualContratoCobranca;
		this.dataInicioContrato = dataInicioContrato;
		this.ultimaAlteracao = ultimaAlteracao;
		this.dataFinalContrato = dataFinalContrato;
	}

	public EmpresaCobranca() {

	}
}
