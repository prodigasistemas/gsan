package gcom.cobranca;

import java.io.Serializable;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class NegativCritNegRetMot implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private gcom.cobranca.NegativacaoCriterio negativacaoCriterio;

    /** nullable persistent field */
    private gcom.cobranca.NegativadorRetornoMotivo negativadorRetornoMotivo;
    

	public NegativCritNegRetMot(Integer id, Date ultimaAlteracao, NegativacaoCriterio negativacaoCriterio, NegativadorRetornoMotivo negativadorRetornoMotivo) {
		super();
		
		this.id = id;
		this.ultimaAlteracao = ultimaAlteracao;
		this.negativacaoCriterio = negativacaoCriterio;
		this.negativadorRetornoMotivo = negativadorRetornoMotivo;
	}

	public NegativCritNegRetMot() {
		super();
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public gcom.cobranca.NegativacaoCriterio getNegativacaoCriterio() {
		return negativacaoCriterio;
	}

	public void setNegativacaoCriterio(
			gcom.cobranca.NegativacaoCriterio negativacaoCriterio) {
		this.negativacaoCriterio = negativacaoCriterio;
	}

	public gcom.cobranca.NegativadorRetornoMotivo getNegativadorRetornoMotivo() {
		return negativadorRetornoMotivo;
	}

	public void setNegativadorRetornoMotivo(
			gcom.cobranca.NegativadorRetornoMotivo negativadorRetornoMotivo) {
		this.negativadorRetornoMotivo = negativadorRetornoMotivo;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

   

}
