package gcom.micromedicao;

import java.math.BigDecimal;
import java.util.Date;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

/**
 * ContratoEmpresaAditivo 
 *
 * @author Mariana Victor
 * @date 22/11/2010
 */
public class ContratoEmpresaAditivo extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private ContratoEmpresaServico contratoEmpresaServico;
	
	private Date dataInicioContrato;
	
	private Date dataFimContrato;
	
	private BigDecimal valorAditivoContrato;
	
	private BigDecimal percentualTaxaSucesso;
	
	private Date ultimaAlteracao;
	
	
	public ContratoEmpresaServico getContratoEmpresaServico() {
		return contratoEmpresaServico;
	}

	public void setContratoEmpresaServico(
			ContratoEmpresaServico contratoEmpresaServico) {
		this.contratoEmpresaServico = contratoEmpresaServico;
	}

	public Date getDataFimContrato() {
		return dataFimContrato;
	}

	public void setDataFimContrato(Date dataFimContrato) {
		this.dataFimContrato = dataFimContrato;
	}

	public Date getDataInicioContrato() {
		return dataInicioContrato;
	}

	public void setDataInicioContrato(Date dataInicioContrato) {
		this.dataInicioContrato = dataInicioContrato;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getPercentualTaxaSucesso() {
		return percentualTaxaSucesso;
	}

	public void setPercentualTaxaSucesso(BigDecimal percentualTaxaSucesso) {
		this.percentualTaxaSucesso = percentualTaxaSucesso;
	}

	public BigDecimal getValorAditivoContrato() {
		return valorAditivoContrato;
	}

	public void setValorAditivoContrato(BigDecimal valorAditivoContrato) {
		this.valorAditivoContrato = valorAditivoContrato;
	}

	@Override
	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public ContratoEmpresaAditivo() {
		super();
	}

	public ContratoEmpresaAditivo(ContratoEmpresaServico contratoEmpresaServico,
			Date dataInicioContrato, Date dataFimContrato, BigDecimal valorAditivoContrato,
			BigDecimal percentualTaxaSucesso, Date ultimaAlteracao) {
		super();
		this.contratoEmpresaServico = contratoEmpresaServico;
		this.dataInicioContrato = dataInicioContrato;
		this.dataFimContrato = dataFimContrato;
		this.valorAditivoContrato = valorAditivoContrato;
		this.percentualTaxaSucesso = percentualTaxaSucesso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	@Override
	public void initializeLazy() {
		if (this.getContratoEmpresaServico() != null) contratoEmpresaServico.initializeLazy();
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroContratoEmpresaAditivo filtroContratoEmpresaAditivo = new FiltroContratoEmpresaAditivo();

		filtroContratoEmpresaAditivo.adicionarParametro(new ParametroSimples(FiltroContratoEmpresaAditivo.ID,
				this.getId()));
		
		return filtroContratoEmpresaAditivo;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
}
