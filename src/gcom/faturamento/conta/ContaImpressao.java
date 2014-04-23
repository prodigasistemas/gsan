package gcom.faturamento.conta;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.empresa.Empresa;
import gcom.faturamento.FaturamentoGrupo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ContaImpressao implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private int referenciaConta;

    /** persistent field */
    private short indicadorImpressao;

    /** persistent field */
    private Date ultimaAlteracao;
    
    /** persistent field */
    private Integer sequencialImpressao;
    
    private BigDecimal valorConta;
    
    private Short indicadorFichaCompensacao;

    /** nullable persistent field */
    private gcom.faturamento.conta.ContaGeral contaGeral;

    /** persistent field */
    private FaturamentoGrupo faturamentoGrupo;
    
    private Cliente clienteResponsavel;
    
    private ContaTipo contaTipo;
    
    private Empresa empresa;

    /** full constructor */
    public ContaImpressao(Integer id, int referenciaConta, short indicadorImpressao, Date ultimaAlteracao, Integer sequencialImpressao, ContaGeral contaGeral, FaturamentoGrupo faturamentoGrupo) {
        this.id = id;
        this.referenciaConta = referenciaConta;
        this.indicadorImpressao = indicadorImpressao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.sequencialImpressao = sequencialImpressao;
        this.contaGeral = contaGeral;
        this.faturamentoGrupo = faturamentoGrupo;
    }

    /** default constructor */
    public ContaImpressao() {
    }

    /** minimal constructor */
    public ContaImpressao(Integer id, int referenciaConta, short indicadorImpressao, Date ultimaAlteracao, Integer sequencialImpressao, FaturamentoGrupo faturamentoGrupo) {
        this.id = id;
        this.referenciaConta = referenciaConta;
        this.indicadorImpressao = indicadorImpressao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.sequencialImpressao = sequencialImpressao;
        this.faturamentoGrupo = faturamentoGrupo;
    }

    
   
    public ContaTipo getContaTipo() {
		return contaTipo;
	}

	public void setContaTipo(ContaTipo contaTipo) {
		this.contaTipo = contaTipo;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public ContaGeral getContaGeral() {
        return this.contaGeral;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public short getIndicadorImpressao() {
		return indicadorImpressao;
	}

	public void setIndicadorImpressao(short indicadorImpressao) {
		this.indicadorImpressao = indicadorImpressao;
	}

	public int getReferenciaConta() {
		return referenciaConta;
	}

	public void setReferenciaConta(int referenciaConta) {
		this.referenciaConta = referenciaConta;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public void setContaGeral(ContaGeral contaGeral) {
        this.contaGeral = contaGeral;
    }

    public FaturamentoGrupo getFaturamentoGrupo() {
        return this.faturamentoGrupo;
    }

    public void setFaturamentoGrupo(FaturamentoGrupo faturamentoGrupo) {
        this.faturamentoGrupo = faturamentoGrupo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if (!(other instanceof ContaImpressao)) {
            return false;
        }
        ContaImpressao castOther = (ContaImpressao) other;

        return (this.getId().equals(castOther.getId()));
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
        .append(getId())
        .toHashCode();
    }

	public Cliente getClienteResponsavel() {
		return clienteResponsavel;
	}

	public void setClienteResponsavel(Cliente clienteResponsavel) {
		this.clienteResponsavel = clienteResponsavel;
	}

	/**
	 * @return Retorna o campo sequencialImpressao.
	 */
	public Integer getSequencialImpressao() {
		return sequencialImpressao;
	}

	/**
	 * @param sequencialImpressao O sequencialImpressao a ser setado.
	 */
	public void setSequencialImpressao(Integer sequencialImpressao) {
		this.sequencialImpressao = sequencialImpressao;
	}

    public BigDecimal getValorConta() {
        return valorConta;
    }

    public void setValorConta(BigDecimal valorConta) {
        this.valorConta = valorConta;
    }

    public Short getIndicadorFichaCompensacao() {
        return indicadorFichaCompensacao;
    }

    public void setIndicadorFichaCompensacao(Short indicadorFichaCompensacao) {
        this.indicadorFichaCompensacao = indicadorFichaCompensacao;
    }

    
    
}
