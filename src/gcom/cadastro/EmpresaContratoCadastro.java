package gcom.cadastro;

import gcom.arrecadacao.ContratoMotivoCancelamento;
import gcom.cadastro.empresa.Empresa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class EmpresaContratoCadastro implements Serializable {
	
	private static final long serialVersionUID = 1L;
    
	/** identifier field */
    private Integer id;

    /** persistent field */
    private Date dataInicioContrato;

    /** persistent field */
    private Date dataFinalContrato;

    /** nullable persistent field */
    private Date dataCancelamentoContrato;

    /** persistent field */
    private BigDecimal valorVisita;

    /** persistent field */
    private Date ultimaAlteracao;

    private Empresa empresa;
    
    private ContratoMotivoCancelamento contratoMotivoCancelamento;
    
    /** persistent field */
    private Set empresaContratoCadastroAtributos;

    /** full constructor */
    public EmpresaContratoCadastro(Integer id, Date dataInicioContrato, Date dataFinalContrato, Date dataCancelamentoContrato, BigDecimal valorVisita, Date ultimaAlteracao, Set empresaContratoCadastroAtributos) {
        this.id = id;
        this.dataInicioContrato = dataInicioContrato;
        this.dataFinalContrato = dataFinalContrato;
        this.dataCancelamentoContrato = dataCancelamentoContrato;
        this.valorVisita = valorVisita;
        this.ultimaAlteracao = ultimaAlteracao;
        this.empresaContratoCadastroAtributos = empresaContratoCadastroAtributos;
    }

    /** default constructor */
    public EmpresaContratoCadastro() {
    }

    /** minimal constructor */
    public EmpresaContratoCadastro(Integer id, Date dataInicioContrato, Date dataFinalContrato, BigDecimal valorVisita, Date ultimaAlteracao, Set empresaContratoCadastroAtributos) {
        this.id = id;
        this.dataInicioContrato = dataInicioContrato;
        this.dataFinalContrato = dataFinalContrato;
        this.valorVisita = valorVisita;
        this.ultimaAlteracao = ultimaAlteracao;
        this.empresaContratoCadastroAtributos = empresaContratoCadastroAtributos;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataInicioContrato() {
        return this.dataInicioContrato;
    }

    public void setDataInicioContrato(Date dataInicioContrato) {
        this.dataInicioContrato = dataInicioContrato;
    }

    public Date getDataFinalContrato() {
        return this.dataFinalContrato;
    }

    public void setDataFinalContrato(Date dataFinalContrato) {
        this.dataFinalContrato = dataFinalContrato;
    }

    public Date getDataCancelamentoContrato() {
        return this.dataCancelamentoContrato;
    }

    public void setDataCancelamentoContrato(Date dataCancelamentoContrato) {
        this.dataCancelamentoContrato = dataCancelamentoContrato;
    }

    public BigDecimal getValorVisita() {
        return this.valorVisita;
    }

    public void setValorVisita(BigDecimal valorVisita) {
        this.valorVisita = valorVisita;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Set getEmpresaContratoCadastroAtributos() {
        return this.empresaContratoCadastroAtributos;
    }

    public void setEmpresaContratoCadastroAtributos(Set empresaContratoCadastroAtributos) {
        this.empresaContratoCadastroAtributos = empresaContratoCadastroAtributos;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public ContratoMotivoCancelamento getContratoMotivoCancelamento() {
		return contratoMotivoCancelamento;
	}

	public void setContratoMotivoCancelamento(
			ContratoMotivoCancelamento contratoMotivoCancelamento) {
		this.contratoMotivoCancelamento = contratoMotivoCancelamento;
	}

    
}
