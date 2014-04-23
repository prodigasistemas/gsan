package gcom.arrecadacao.banco;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ContaBancaria implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String numeroConta;

    /** nullable persistent field */
    private Integer numeroContaContabil;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.arrecadacao.banco.Agencia agencia;

    /** full constructor */
    public ContaBancaria(String numeroConta, Integer numeroContaContabil, Date ultimaAlteracao, gcom.arrecadacao.banco.Agencia agencia) {
        this.numeroConta = numeroConta;
        this.numeroContaContabil = numeroContaContabil;
        this.ultimaAlteracao = ultimaAlteracao;
        this.agencia = agencia;
    }

    /** default constructor */
    public ContaBancaria() {
    }

    /** minimal constructor */
    public ContaBancaria(String numeroConta, gcom.arrecadacao.banco.Agencia agencia) {
        this.numeroConta = numeroConta;
        this.agencia = agencia;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroConta() {
        return this.numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Integer getNumeroContaContabil() {
        return this.numeroContaContabil;
    }

    public void setNumeroContaContabil(Integer numeroContaContabil) {
        this.numeroContaContabil = numeroContaContabil;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.arrecadacao.banco.Agencia getAgencia() {
        return this.agencia;
    }

    public void setAgencia(gcom.arrecadacao.banco.Agencia agencia) {
        this.agencia = agencia;
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
    
}
