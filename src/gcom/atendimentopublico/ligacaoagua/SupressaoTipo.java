package gcom.atendimentopublico.ligacaoagua;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class SupressaoTipo extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    /** nullable persistent field */
    private short indicadorTotal;
    
    /** nullable persistent field */
    private short indicadorParcial;
    
    public final static Integer SUPRESSAO_TIPO_ID = 4;

    /** full constructor */
    public SupressaoTipo(String descricao, Short indicadorUso,
            Date ultimaAlteracao) {
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public SupressaoTipo() {
    }
    
    

    public short getIndicadorParcial() {
		return indicadorParcial;
	}

	public void setIndicadorParcial(short indicadorParcial) {
		this.indicadorParcial = indicadorParcial;
	}

	public short getIndicadorTotal() {
		return indicadorTotal;
	}

	public void setIndicadorTotal(short indicadorTotal) {
		this.indicadorTotal = indicadorTotal;
	}

	public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }
    
	 public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
 
	@Override
	public Filtro retornaFiltro() {
		FiltroSupressaoTipo  filtro = new FiltroSupressaoTipo();

		filtro.adicionarParametro(new ParametroSimples(
				FiltroSupressaoTipo.ID, this.getId()));
		return filtro;
	}
	 
	public String getDescricaoParaRegistroTransacao(){
		return this.descricao;
	}
	
	@Override
	public void initializeLazy() {
		getDescricaoParaRegistroTransacao();
	}
}
