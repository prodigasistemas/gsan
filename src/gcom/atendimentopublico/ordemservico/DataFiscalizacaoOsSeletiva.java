package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DataFiscalizacaoOsSeletiva implements Serializable {
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private Date dataFiscalizacaoOrdemServico2;

    /** nullable persistent field */
    private Date dataFiscalizacaoOrdemServico3;

    /** nullable persistent field */
    //private OrdemServico ordemServico;

    /** full constructor */
    public DataFiscalizacaoOsSeletiva(Integer id, Date dataFiscalizacaoOrdemServico2, Date dataFiscalizacaoOrdemServico3) {
        this.id = id;
        this.dataFiscalizacaoOrdemServico2 = dataFiscalizacaoOrdemServico2;
        this.dataFiscalizacaoOrdemServico3 = dataFiscalizacaoOrdemServico3;
    }

    /** default constructor */
    public DataFiscalizacaoOsSeletiva() {
    }

    /** minimal constructor */
    public DataFiscalizacaoOsSeletiva(Integer id, Date dataFiscalizacaoOrdemServico2) {
        this.id = id;
        this.dataFiscalizacaoOrdemServico2 = dataFiscalizacaoOrdemServico2;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataFiscalizacaoOrdemServico2() {
        return this.dataFiscalizacaoOrdemServico2;
    }

    public void setDataFiscalizacaoOrdemServico2(Date dataFiscalizacaoOrdemServico2) {
        this.dataFiscalizacaoOrdemServico2 = dataFiscalizacaoOrdemServico2;
    }

    public Date getDataFiscalizacaoOrdemServico3() {
        return this.dataFiscalizacaoOrdemServico3;
    }

    public void setDataFiscalizacaoOrdemServico3(Date dataFiscalizacaoOrdemServico3) {
        this.dataFiscalizacaoOrdemServico3 = dataFiscalizacaoOrdemServico3;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
