package gcom.atendimentopublico.registroatendimento;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class RaEncerramentoComandoEspecificacoes implements Serializable {

    /**
	 * @since 28/01/2008
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private Date ultimaAlteracao;
    
    /** persistent field */
    private RaEncerramentoComando raEncerramentoComando;

    /** persistent field */
    private SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao;

    /** full constructor */
    public RaEncerramentoComandoEspecificacoes(
			Integer id,
			Date ultimaAlteracao,
			RaEncerramentoComando raEncerramentoComando,
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.raEncerramentoComando = raEncerramentoComando;
        this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
    }

    /** default constructor */
    public RaEncerramentoComandoEspecificacoes() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
	 * @return Retorna o campo raEncerramentoComando.
	 */
	public RaEncerramentoComando getRaEncerramentoComando() {
		return raEncerramentoComando;
	}

	/**
	 * @param raEncerramentoComando O raEncerramentoComando a ser setado.
	 */
	public void setRaEncerramentoComando(RaEncerramentoComando raEncerramentoComando) {
		this.raEncerramentoComando = raEncerramentoComando;
	}

	/**
	 * @return Retorna o campo solicitacaoTipoEspecificacao.
	 */
	public SolicitacaoTipoEspecificacao getSolicitacaoTipoEspecificacao() {
		return solicitacaoTipoEspecificacao;
	}

	/**
	 * @param solicitacaoTipoEspecificacao O solicitacaoTipoEspecificacao a ser setado.
	 */
	public void setSolicitacaoTipoEspecificacao(
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao) {
		this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
