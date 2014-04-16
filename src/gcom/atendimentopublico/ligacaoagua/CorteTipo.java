package gcom.atendimentopublico.ligacaoagua;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class CorteTipo extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private Short indicadorUso;
    
    /** nullable persistent field */
    private short indicadorCorteAdministrativo;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    /** nullable persistent field */
    private Short indicadorExibirFormulario;
    
    public final static Integer RAMAL_ID = 4;

    /** full constructor */
    public CorteTipo(String descricao, Short indicadorUso, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public CorteTipo() {
    }
    
    

    public short getIndicadorCorteAdministrativo() {
		return indicadorCorteAdministrativo;
	}

	public void setIndicadorCorteAdministrativo(short indicadorCorteAdministrativo) {
		this.indicadorCorteAdministrativo = indicadorCorteAdministrativo;
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

    public Short getIndicadorExibirFormulario() {
		return indicadorExibirFormulario;
	}

	public void setIndicadorExibirFormulario(Short indicadorExibirFormulario) {
		this.indicadorExibirFormulario = indicadorExibirFormulario;
	}

	public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro() {
		FiltroCorteTipo filtro = new FiltroCorteTipo();
		filtro.adicionarParametro(new ParametroSimples(
				FiltroCorteTipo.ID, this.getId()));
		return filtro;
	}
	 

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}
}
