package gcom.arrecadacao.debitoautomatico;

import gcom.arrecadacao.banco.Agencia;
import gcom.cadastro.imovel.Imovel;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DebitoAutomatico implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String identificacaoClienteBanco;

    /** nullable persistent field */
    private Date dataOpcaoDebitoContaCorrente;

    /** nullable persistent field */
    private Date dataInclusaoNovoDebitoAutomatico;

    /** nullable persistent field */
    private Date dataExclusao;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Agencia agencia;

    /** persistent field */
    private Imovel imovel;

    /** full constructor */
    public DebitoAutomatico(String identificacaoClienteBanco, Date dataOpcaoDebitoContaCorrente, Date dataInclusaoNovoDebitoAutomatico, Date dataExclusao, Date ultimaAlteracao, Agencia agencia, Imovel imovel) {
        this.identificacaoClienteBanco = identificacaoClienteBanco;
        this.dataOpcaoDebitoContaCorrente = dataOpcaoDebitoContaCorrente;
        this.dataInclusaoNovoDebitoAutomatico = dataInclusaoNovoDebitoAutomatico;
        this.dataExclusao = dataExclusao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.agencia = agencia;
        this.imovel = imovel;
    }

    /** default constructor */
    public DebitoAutomatico() {
    }

    /** minimal constructor */
    public DebitoAutomatico(Agencia agencia, Imovel imovel) {
        this.agencia = agencia;
        this.imovel = imovel;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentificacaoClienteBanco() {
        return this.identificacaoClienteBanco;
    }

    public void setIdentificacaoClienteBanco(String identificacaoClienteBanco) {
        this.identificacaoClienteBanco = identificacaoClienteBanco;
    }

    public Date getDataOpcaoDebitoContaCorrente() {
        return this.dataOpcaoDebitoContaCorrente;
    }

    public void setDataOpcaoDebitoContaCorrente(Date dataOpcaoDebitoContaCorrente) {
        this.dataOpcaoDebitoContaCorrente = dataOpcaoDebitoContaCorrente;
    }

    public Date getDataInclusaoNovoDebitoAutomatico() {
        return this.dataInclusaoNovoDebitoAutomatico;
    }

    public void setDataInclusaoNovoDebitoAutomatico(Date dataInclusaoNovoDebitoAutomatico) {
        this.dataInclusaoNovoDebitoAutomatico = dataInclusaoNovoDebitoAutomatico;
    }

    public Date getDataExclusao() {
        return this.dataExclusao;
    }

    public void setDataExclusao(Date dataExclusao) {
        this.dataExclusao = dataExclusao;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Agencia getAgencia() {
        return this.agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public Imovel getImovel() {
        return this.imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
