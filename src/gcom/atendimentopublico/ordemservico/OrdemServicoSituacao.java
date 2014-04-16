package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OrdemServicoSituacao implements Serializable {
	
	public static final int PENDENTE = 1;
	public final static int ENCERRADO = 2;
	public final static int EXECUCAO_EM_ANDAMENTO = 3;
	public final static int AGUARDANDO_LIBERACAO = 4;

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private String descricaoSituacao;

    /** persistent field */
    private String descricaoAbreviadaSituacao;

    /** persistent field */
    private short indicadorUso;

    /** persistent field */
    private Date dataUltimaAlteracao;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer osstId) {
        this.id = osstId;
    }

    public String getDescricaoSituacao() {
        return this.descricaoSituacao;
    }

    public void setDescricaoSituacao(String osstDssituacao) {
        this.descricaoSituacao = osstDssituacao;
    }

    public String getDescricaoAbreviadaSituacao() {
        return this.descricaoAbreviadaSituacao;
    }

    public void setDescricaoAbreviadaSituacao(String osstDsabreviadasituacao) {
        this.descricaoAbreviadaSituacao = osstDsabreviadasituacao;
    }

    public short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(short osstIcuso) {
        this.indicadorUso = osstIcuso;
    }

    public Date getDataUltimaAlteracao() {
        return this.dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(Date osstTmultimaalteracao) {
        this.dataUltimaAlteracao = osstTmultimaalteracao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("osstId", getId())
            .toString();
    }

}
