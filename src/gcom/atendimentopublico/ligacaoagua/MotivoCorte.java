package gcom.atendimentopublico.ligacaoagua;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class MotivoCorte extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** identifier field */
    private String descricao;

    /** identifier field */
    private Short indicadorUso;

    /** identifier field */
    private Date ultimaAlteracao;
    
    public final static Integer ATUALIZACAO_CADASTRAL = 9;

    /** full constructor */
    public MotivoCorte(Integer id, String descricao, Short indicadorUso, Date ultimaAlteracao) {
        this.id = id;
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public MotivoCorte() {
    }

    public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
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
	 
	public Filtro retornaFiltro() {
		FiltroMotivoCorte filtro = new FiltroMotivoCorte();
		filtro.adicionarParametro(new ParametroSimples(
				FiltroMotivoCorte.ID, this.getId()));
		return filtro;
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}
	 
}
