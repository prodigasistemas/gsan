package gcom.gerencial.arrecadacao.pagamento;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GEpocaPagamento implements Serializable {	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String epocaPagemento;

    /** persistent field */
    private Date ultimaalteracao;

    /** persistent field */
    private Set unResumoArrecadacao;
    
    private Set unResumoArrecadacaoPorAno;

    /** full constructor */
    public GEpocaPagamento(String epocaPagemento, Date ultimaalteracao, Set unResumoArrecadacao) {
        this.epocaPagemento = epocaPagemento;
        this.ultimaalteracao = ultimaalteracao;
        this.unResumoArrecadacao = unResumoArrecadacao;
    }
    public Set getUnResumoArrecadacaoPorAno() {
		return unResumoArrecadacaoPorAno;
	}

	public void setUnResumoArrecadacaoPorAno(Set unResumoArrecadacaoPorAno) {
		this.unResumoArrecadacaoPorAno = unResumoArrecadacaoPorAno;
	}

	/** default constructor */
    public GEpocaPagamento() {
    }

    /** minimal constructor */
    public GEpocaPagamento(Date ultimaalteracao, Set unResumoArrecadacao) {
        this.ultimaalteracao = ultimaalteracao;
        this.unResumoArrecadacao = unResumoArrecadacao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEpocaPagemento() {
        return this.epocaPagemento;
    }

    public void setEpocaPagemento(String epocaPagemento) {
        this.epocaPagemento = epocaPagemento;
    }

    public Date getUltimaalteracao() {
        return this.ultimaalteracao;
    }

    public void setUltimaalteracao(Date ultimaalteracao) {
        this.ultimaalteracao = ultimaalteracao;
    }

   

    public Set getUnResumoArrecadacao() {
		return unResumoArrecadacao;
	}

	public void setUnResumoArrecadacao(Set unResumoArrecadacao) {
		this.unResumoArrecadacao = unResumoArrecadacao;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
