package gcom.arrecadacao.debitoautomatico;

import gcom.arrecadacao.banco.Agencia;
import gcom.cadastro.imovel.Imovel;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class DebitoAutomatico implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private Integer id;
    private String identificacaoClienteBanco;
    private Date dataOpcaoDebitoContaCorrente;
    private Date dataInclusaoNovoDebitoAutomatico;
    private Date dataExclusao;
    private Date ultimaAlteracao;
    private Usuario usuarioExclusao;

    private Agencia agencia;
    private Imovel imovel;

    public DebitoAutomatico(String identificacaoClienteBanco, Date dataOpcaoDebitoContaCorrente, Date dataInclusaoNovoDebitoAutomatico, Date dataExclusao, Date ultimaAlteracao, Agencia agencia, Imovel imovel) {
        this.identificacaoClienteBanco = identificacaoClienteBanco;
        this.dataOpcaoDebitoContaCorrente = dataOpcaoDebitoContaCorrente;
        this.dataInclusaoNovoDebitoAutomatico = dataInclusaoNovoDebitoAutomatico;
        this.dataExclusao = dataExclusao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.agencia = agencia;
        this.imovel = imovel;
    }

    public DebitoAutomatico() {
    }

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
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

	public Usuario getUsuarioExclusao() {
		return usuarioExclusao;
	}

	public void setUsuarioExclusao(Usuario usuarioExclusao) {
		this.usuarioExclusao = usuarioExclusao;
	}
    
}
