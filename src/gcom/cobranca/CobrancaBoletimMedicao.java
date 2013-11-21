package gcom.cobranca;

import java.math.BigDecimal;
import java.util.Date;

import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.ContratoEmpresaServico;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

/** @author Hibernate CodeGenerator */
public class CobrancaBoletimMedicao extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private Integer anoMesReferencia;

    /** nullable persistent field */
    private Date dataGeracaoBoletim;

    /** nullable persistent field */
    private BigDecimal valorDescontos;

    /** nullable persistent field */
    private BigDecimal valorTaxaSucesso;

    /** nullable persistent field */
    private BigDecimal valorServicosExecutados;

    /** nullable persistent field */
    private BigDecimal valorTotal;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private CobrancaGrupo cobrancaGrupo;

    /** nullable persistent field */
    private ContratoEmpresaServico contratoEmpresaServico;

    
	public CobrancaBoletimMedicao() {
		super();
	}

	public CobrancaBoletimMedicao(Integer id, Integer anoMesReferencia, Date dataGeracaoBoletim, BigDecimal valorDescontos, BigDecimal valorTaxaSucesso, BigDecimal valorServicosExecutados, BigDecimal valorTotal, Date ultimaAlteracao, CobrancaGrupo cobrancaGrupo, ContratoEmpresaServico contratoEmpresaServico) {
		super();
		this.id = id;
		this.anoMesReferencia = anoMesReferencia;
		this.dataGeracaoBoletim = dataGeracaoBoletim;
		this.valorDescontos = valorDescontos;
		this.valorTaxaSucesso = valorTaxaSucesso;
		this.valorServicosExecutados = valorServicosExecutados;
		this.valorTotal = valorTotal;
		this.ultimaAlteracao = ultimaAlteracao;
		this.cobrancaGrupo = cobrancaGrupo;
		this.contratoEmpresaServico = contratoEmpresaServico;
	}

	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public CobrancaGrupo getCobrancaGrupo() {
		return cobrancaGrupo;
	}

	public void setCobrancaGrupo(CobrancaGrupo cobrancaGrupo) {
		this.cobrancaGrupo = cobrancaGrupo;
	}

	public ContratoEmpresaServico getContratoEmpresaServico() {
		return contratoEmpresaServico;
	}

	public void setContratoEmpresaServico(
			ContratoEmpresaServico contratoEmpresaServico) {
		this.contratoEmpresaServico = contratoEmpresaServico;
	}

	public Date getDataGeracaoBoletim() {
		return dataGeracaoBoletim;
	}

	public void setDataGeracaoBoletim(Date dataGeracaoBoletim) {
		this.dataGeracaoBoletim = dataGeracaoBoletim;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorDescontos() {
		return valorDescontos;
	}

	public void setValorDescontos(BigDecimal valorDescontos) {
		this.valorDescontos = valorDescontos;
	}

	public BigDecimal getValorServicosExecutados() {
		return valorServicosExecutados;
	}

	public void setValorServicosExecutados(BigDecimal valorServicosExecutados) {
		this.valorServicosExecutados = valorServicosExecutados;
	}

	public BigDecimal getValorTaxaSucesso() {
		return valorTaxaSucesso;
	}

	public void setValorTaxaSucesso(BigDecimal valorTaxaSucesso) {
		this.valorTaxaSucesso = valorTaxaSucesso;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
    
    public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

    @Override
	public Filtro retornaFiltro(){
    	FiltroCobrancaBoletimMedicao filtroCobrancaBoletimMedicao = new FiltroCobrancaBoletimMedicao();

    	filtroCobrancaBoletimMedicao.adicionarParametro(new ParametroSimples(FiltroCobrancaBoletimMedicao.ID,
				this.getId()));

		
		return filtroCobrancaBoletimMedicao;
	}

}
