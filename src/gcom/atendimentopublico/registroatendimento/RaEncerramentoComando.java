package gcom.atendimentopublico.registroatendimento;

import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class RaEncerramentoComando implements Serializable {

    /**
	 * @since 28/01/2008
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private Date dataAtendimentoInicial;

    /** persistent field */
    private Date dataAtendimentoFinal;

    /** persistent field */
    private Date tempoComando;

    /** nullable persistent field */
    private Date tempoRealizacao;

    /** nullable persistent field */
    private Integer quantidadeRasEncerradas;

    /** nullable persistent field */
    private Integer quantidadeOsEncerradas;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Usuario usuario;

    /** persistent field */
    private gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento atendimentoMotivoEncerramento;

    /** persistent field */
    private UnidadeOrganizacional unidadeOrganizacionalAtual;

    /** persistent field */
    private UnidadeOrganizacional unidadeOrganizacionalSuperior;

    /** persistent field */
    private UnidadeOrganizacional unidadeOrganizacionalAtendimento;

    /** full constructor */
    public RaEncerramentoComando(
			Integer id,
			Date dataAtendimentoInicial,
			Date dataAtendimentoFinal,
			Date tempoComando,
			Date tempoRealizacao,
			Integer quantidadeRasEncerradas,
			Integer quantidadeOsEncerradas,
			Date ultimaAlteracao,
			Usuario usuario,
			gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento atendimentoMotivoEncerramento,
			UnidadeOrganizacional unidadeOrganizacionalAtual,
			UnidadeOrganizacional unidadeOrganizacionalSuperior,
			UnidadeOrganizacional unidadeOrganizacionalAtendimento) {
        this.id = id;
        this.dataAtendimentoInicial = dataAtendimentoInicial;
        this.dataAtendimentoFinal = dataAtendimentoFinal;
        this.tempoComando = tempoComando;
        this.tempoRealizacao = tempoRealizacao;
        this.quantidadeRasEncerradas = quantidadeRasEncerradas;
        this.quantidadeOsEncerradas = quantidadeOsEncerradas;
        this.ultimaAlteracao = ultimaAlteracao;
        this.usuario = usuario;
        this.atendimentoMotivoEncerramento = atendimentoMotivoEncerramento;
        this.unidadeOrganizacionalAtual = unidadeOrganizacionalAtual;
        this.unidadeOrganizacionalSuperior = unidadeOrganizacionalSuperior;
        this.unidadeOrganizacionalAtendimento = unidadeOrganizacionalAtendimento;
    }

    /** default constructor */
    public RaEncerramentoComando() {
    }

    /** minimal constructor */
    public RaEncerramentoComando(
			Integer id,
			Date dataAtendimentoInicial,
			Date dataAtendimentoFinal,
			Date tempoComando,
			Date ultimaAlteracao,
			Usuario usuario,
			gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento atendimentoMotivoEncerramento,
			UnidadeOrganizacional unidadeOrganizacionalAtual,
			UnidadeOrganizacional unidadeOrganizacionalSuperior,
			UnidadeOrganizacional unidadeOrganizacionalAtendimento) {
        this.id = id;
        this.dataAtendimentoInicial = dataAtendimentoInicial;
        this.dataAtendimentoFinal = dataAtendimentoFinal;
        this.tempoComando = tempoComando;
        this.ultimaAlteracao = ultimaAlteracao;
        this.usuario = usuario;
        this.atendimentoMotivoEncerramento = atendimentoMotivoEncerramento;
        this.unidadeOrganizacionalAtual = unidadeOrganizacionalAtual;
        this.unidadeOrganizacionalSuperior = unidadeOrganizacionalSuperior;
        this.unidadeOrganizacionalAtendimento = unidadeOrganizacionalAtendimento;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataAtendimentoInicial() {
        return this.dataAtendimentoInicial;
    }

    public void setDataAtendimentoInicial(Date dataAtendimentoInicial) {
        this.dataAtendimentoInicial = dataAtendimentoInicial;
    }

    public Date getDataAtendimentoFinal() {
        return this.dataAtendimentoFinal;
    }

    public void setDataAtendimentoFinal(Date dataAtendimentoFinal) {
        this.dataAtendimentoFinal = dataAtendimentoFinal;
    }

    public Date getTempoComando() {
        return this.tempoComando;
    }

    public void setTempoComando(Date tempoComando) {
        this.tempoComando = tempoComando;
    }

    public Date getTempoRealizacao() {
        return this.tempoRealizacao;
    }

    public void setTempoRealizacao(Date tempoRealizacao) {
        this.tempoRealizacao = tempoRealizacao;
    }

    public Integer getQuantidadeRasEncerradas() {
        return this.quantidadeRasEncerradas;
    }

    public void setQuantidadeRasEncerradas(Integer quantidadeRasEncerradas) {
        this.quantidadeRasEncerradas = quantidadeRasEncerradas;
    }

    public Integer getQuantidadeOsEncerradas() {
        return this.quantidadeOsEncerradas;
    }

    public void setQuantidadeOsEncerradas(Integer quantidadeOsEncerradas) {
        this.quantidadeOsEncerradas = quantidadeOsEncerradas;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento getAtendimentoMotivoEncerramento() {
        return this.atendimentoMotivoEncerramento;
    }

    public void setAtendimentoMotivoEncerramento(gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento atendimentoMotivoEncerramento) {
        this.atendimentoMotivoEncerramento = atendimentoMotivoEncerramento;
    }

    public UnidadeOrganizacional getUnidadeOrganizacionalAtual() {
        return this.unidadeOrganizacionalAtual;
    }

    public void setUnidadeOrganizacionalAtual(UnidadeOrganizacional unidadeOrganizacionalAtual) {
        this.unidadeOrganizacionalAtual = unidadeOrganizacionalAtual;
    }

    public UnidadeOrganizacional getUnidadeOrganizacionalSuperior() {
        return this.unidadeOrganizacionalSuperior;
    }

    public void setUnidadeOrganizacionalSuperior(UnidadeOrganizacional unidadeOrganizacionalSuperior) {
        this.unidadeOrganizacionalSuperior = unidadeOrganizacionalSuperior;
    }

    public UnidadeOrganizacional getUnidadeOrganizacionalAtendimento() {
        return this.unidadeOrganizacionalAtendimento;
    }

    public void setUnidadeOrganizacionalAtendimento(UnidadeOrganizacional unidadeOrganizacionalAtendimento) {
        this.unidadeOrganizacionalAtendimento = unidadeOrganizacionalAtendimento;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
