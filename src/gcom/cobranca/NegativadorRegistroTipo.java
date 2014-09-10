package gcom.cobranca;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativadorRegistroTipo extends ObjetoTransacao implements Serializable  {
	
	public static final String TIPO_HEADER = "H";
	public static final String TIPO_TRAILLER = "T";
	public static final String TIPO_DETALHE_CONSUMIDOR = "D";
	public static final String TIPO_DETALHE_SPC = "D";
	public static final String TIPO_DETALHE = "D";

	public static final Integer ID_SPC_HEADER = new Integer(1); 
	public static final Integer ID_SPC_TRAILLER = new Integer(2);
	public static final Integer ID_SPC_DETALHE_CONSUMIDOR = new Integer(3);
	public static final Integer ID_SPC_DETALHE_SPC = new Integer(4);
	public static final Integer ID_SERASA_HEADER = new Integer(5);
	public static final Integer ID_SERASA_TRAILLER = new Integer(6);
	public static final Integer ID_SERASA_DETALHE = new Integer(7);

	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricaoRegistroTipo;

    /** nullable persistent field */
    private String codigoRegistro;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.cobranca.Negativador negativador;

    /** persistent field */
    private Set negativadorMovimentoReg;

    /** full constructor */
    public NegativadorRegistroTipo(Integer id, String descricaoRegistroTipo, String codigoRegistro, Date ultimaAlteracao, gcom.cobranca.Negativador negativador, Set negativadorMovimentoReg) {
        this.id = id;
        this.descricaoRegistroTipo = descricaoRegistroTipo;
        this.codigoRegistro = codigoRegistro;
        this.ultimaAlteracao = ultimaAlteracao;
        this.negativador = negativador;
        this.negativadorMovimentoReg = negativadorMovimentoReg;
    }

    /** default constructor */
    public NegativadorRegistroTipo() {
    }

    /** minimal constructor */
    public NegativadorRegistroTipo(Integer id, gcom.cobranca.Negativador negativador, Set negativadorMovimentoReg) {
        this.id = id;
        this.negativador = negativador;
        this.negativadorMovimentoReg = negativadorMovimentoReg;
    }

    public NegativadorRegistroTipo(Integer id) {
		this.id = id;
	}

	public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoRegistroTipo() {
        return this.descricaoRegistroTipo;
    }

    public void setDescricaoRegistroTipo(String descricaoRegistroTipo) {
        this.descricaoRegistroTipo = descricaoRegistroTipo;
    }

    public String getCodigoRegistro() {
        return this.codigoRegistro;
    }

    public void setCodigoRegistro(String codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.cobranca.Negativador getNegativador() {
        return this.negativador;
    }

    public void setNegativador(gcom.cobranca.Negativador negativador) {
        this.negativador = negativador;
    }

    public Set getNegativadorMovimentoReg() {
        return this.negativadorMovimentoReg;
    }

    public void setNegativadorMovimentoReg(Set negativadorMovimentoReg) {
        this.negativadorMovimentoReg = negativadorMovimentoReg;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public Filtro retornaFiltro() {
		FiltroNegativadorRegistroTipo filtroNegativadorRegistroTipo = new FiltroNegativadorRegistroTipo();
		filtroNegativadorRegistroTipo.adicionarParametro(new ParametroSimples(FiltroNegativadorRegistroTipo.ID,this.getId()));		
		return filtroNegativadorRegistroTipo;
	}


	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}
}
