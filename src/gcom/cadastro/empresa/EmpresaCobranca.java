package gcom.cadastro.empresa;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

public class EmpresaCobranca extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Empresa empresa;

	private BigDecimal percentualContratoCobranca;

	private Date dataInicioContrato;

	private Date ultimaAlteracao;

	private Date dataFinalContrato;

	public EmpresaCobranca() {

	}
	
	public EmpresaCobranca(Integer id, Empresa empresa, BigDecimal percentualContratoCobranca, Date dataInicioContrato, Date ultimaAlteracao, Date dataFinalContrato) {
		super();

		this.id = id;
		this.empresa = empresa;
		this.percentualContratoCobranca = percentualContratoCobranca;
		this.dataInicioContrato = dataInicioContrato;
		this.ultimaAlteracao = ultimaAlteracao;
		this.dataFinalContrato = dataFinalContrato;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public BigDecimal getPercentualContratoCobranca() {
		return percentualContratoCobranca;
	}

	public void setPercentualContratoCobranca(BigDecimal percentualContratoCobranca) {
		this.percentualContratoCobranca = percentualContratoCobranca;
	}

	public Date getDataInicioContrato() {
		return dataInicioContrato;
	}

	public void setDataInicioContrato(Date dataInicioContrato) {
		this.dataInicioContrato = dataInicioContrato;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Date getDataFinalContrato() {
		return dataFinalContrato;
	}

	public void setDataFinalContrato(Date dataFinalContrato) {
		this.dataFinalContrato = dataFinalContrato;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroEmpresaCobranca filtro = new FiltroEmpresaCobranca();
		filtro.adicionarParametro(new ParametroSimples(FiltroEmpresaCobranca.ID, this.getId()));
		
		return filtro;
	}
	
	public boolean isPercentualInformado() {
		return percentualContratoCobranca != null ? true : false;
	}
}
