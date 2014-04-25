package gcom.micromedicao;

import gcom.cadastro.empresa.Empresa;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class ContratoEmpresaServico extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_CONTRATO_EMPRESA_SERVICO_INFORMAR = 1661; //Operacao.OPERACAO_INFORMAR_ITEM_SERVICO_CONTRATO

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	@ControleAlteracao(value=FiltroContratoEmpresaServico.EMPRESA, funcionalidade={ATRIBUTOS_CONTRATO_EMPRESA_SERVICO_INFORMAR})
	private Empresa empresa;
	
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CONTRATO_EMPRESA_SERVICO_INFORMAR})
	private String descricaoContrato;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CONTRATO_EMPRESA_SERVICO_INFORMAR})
	private Date dataInicioContrato;
	
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CONTRATO_EMPRESA_SERVICO_INFORMAR})
	private Date dataFimContrato;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CONTRATO_EMPRESA_SERVICO_INFORMAR})
	private Date ultimaAlteracao;
	
	//@ControleAlteracao(value=FiltroContratoEmpresaServico.ITEM_SERVICO_CONTRATOS, funcionalidade={ATRIBUTOS_CONTRATO_EMPRESA_SERVICO_INFORMAR})
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CONTRATO_EMPRESA_SERVICO_INFORMAR})
	private Set itemServicoContratos;

	private BigDecimal valorGlobalContrato;
	
	private String observacao;
	
	private BigDecimal percentualTaxaSucesso;
	
	
	/** full constructor */
	public ContratoEmpresaServico(String descricaoContrato, Empresa empresa, Date dataInicioContrato,
			 Date dataFimContrato, Date ultimaAlteracao) {
		
		this.empresa = empresa;
		this.descricaoContrato = descricaoContrato;
		this.dataInicioContrato = dataInicioContrato;
		this.dataFimContrato = dataFimContrato;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/** default constructor */
	public ContratoEmpresaServico() {
		
	}
	
	public String getDescricaoContrato() {
		return descricaoContrato;
	}

	public void setDescricaoContrato(String descricaoContrato) {
		this.descricaoContrato = descricaoContrato;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public Set getItemServicoContratos() {
		return itemServicoContratos;
	}

	public void setItemServicoContratos(Set itemServicoContratos) {
		this.itemServicoContratos = itemServicoContratos;
	}

	public BigDecimal getValorGlobalContrato() {
		return valorGlobalContrato;
	}

	public void setValorGlobalContrato(BigDecimal valorGlobalContrato) {
		this.valorGlobalContrato = valorGlobalContrato;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public void initializeLazy() {
		
		initilizarCollectionLazy(this.getItemServicoContratos());
		if (getEmpresa() != null) empresa.initializeLazy();
	}

	public Filtro retornaFiltro() {
		FiltroContratoEmpresaServico filtroContratoEmpresaServico = new FiltroContratoEmpresaServico();

		filtroContratoEmpresaServico.adicionarParametro(new ParametroSimples(FiltroContratoEmpresaServico.ID,
				this.getId()));
		
		return filtroContratoEmpresaServico;
	}
	
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoEmpresaServico.EMPRESA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoEmpresaServico.ITEM_SERVICO_CONTRATOS);
		
		return filtro;
	}
	
	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
	
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public BigDecimal getPercentualTaxaSucesso() {
		return percentualTaxaSucesso;
	}

	public void setPercentualTaxaSucesso(BigDecimal percentualTaxaSucesso) {
		this.percentualTaxaSucesso = percentualTaxaSucesso;
	}	
	
}
