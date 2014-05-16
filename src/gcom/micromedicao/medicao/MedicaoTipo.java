package gcom.micromedicao.medicao;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class MedicaoTipo extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;

	private Integer id;
    private String descricao;
    private Short indicadorUso;
    private Date ultimaAlteracao;

    public final static Integer LIGACAO_AGUA = new Integer(1);
    public final static Integer POCO = new Integer(2);

    public MedicaoTipo(String descricao, Short indicadorUso, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public MedicaoTipo() {
    }

    public MedicaoTipo(Integer id) {
    	this.id = id;
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
		FiltroMedicaoTipo filtro = new FiltroMedicaoTipo();
		filtro.adicionarParametro(new ParametroSimples(
				FiltroMedicaoTipo.ID, this.getId()));
		return filtro;	
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}
		 
}
