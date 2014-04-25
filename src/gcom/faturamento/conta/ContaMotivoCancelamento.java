package gcom.faturamento.conta;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ContaMotivoCancelamento extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricaoMotivoCancelamentoConta;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    
    public final static Integer TRASFERENCIA_DE_COBRANCA = new Integer(37);
    
    public final static Integer PRESCRICAO = new Integer(99);
    
    public final static Integer DEBITO_PRESCRITO = new Integer(64);
    
    
    /** full constructor */
    public ContaMotivoCancelamento(String descricaoMotivoCancelamentoConta, Short indicadorUso, Date ultimaAlteracao) {
        this.descricaoMotivoCancelamentoConta = descricaoMotivoCancelamentoConta;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public ContaMotivoCancelamento() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoMotivoCancelamentoConta() {
        return this.descricaoMotivoCancelamentoConta;
    }

    public void setDescricaoMotivoCancelamentoConta(String descricaoMotivoCancelamentoConta) {
        this.descricaoMotivoCancelamentoConta = descricaoMotivoCancelamentoConta;
    }

    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
    
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Filtro retornaFiltro() {
		Filtro filtro = new FiltroContaMotivoCancelamento();
		
		filtro.adicionarParametro(
				new ParametroSimples(FiltroContaMotivoCancelamento.CODIGO, this.id));
		return filtro; 
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricaoMotivoCancelamentoConta();
	}
}
