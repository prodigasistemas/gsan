package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OSAtividadeExecucaoAcompanhamentoServico implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private Date dataExecucaoInicio;

    /** persistent field */
    private Date dataExecucaoFim;

    /** persistent field */
    private Date dataUltimaAlteracao;

    /** persistent field */
    private OSAtividadeProgramacaoAcompanhamentoServico osAtividadeProgramacaoAcompanhamentoServico;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer oeasId) {
        this.id = oeasId;
    }

    public Date getDataExecucaoInicio() {
        return this.dataExecucaoInicio;
    }

    public void setDataExecucaoInicio(Date oeasTmexecucaoinicio) {
        this.dataExecucaoInicio = oeasTmexecucaoinicio;
    }

    public Date getDataExecucaoFim() {
        return this.dataExecucaoFim;
    }

    public void setDataExecucaoFim(Date oeasTmexecucaofim) {
        this.dataExecucaoFim = oeasTmexecucaofim;
    }

    public Date getDataUltimaAlteracao() {
        return this.dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(Date oeasTmultimaalteracao) {
        this.dataUltimaAlteracao = oeasTmultimaalteracao;
    }

    
    

    public OSAtividadeProgramacaoAcompanhamentoServico getOsAtividadeProgramacaoAcompanhamentoServico() {
		return osAtividadeProgramacaoAcompanhamentoServico;
	}

	public void setOsAtividadeProgramacaoAcompanhamentoServico(
			OSAtividadeProgramacaoAcompanhamentoServico osAtividadeProgramacaoAcompanhamentoServico) {
		this.osAtividadeProgramacaoAcompanhamentoServico = osAtividadeProgramacaoAcompanhamentoServico;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("oeasId", getId())
            .toString();
    }

}
