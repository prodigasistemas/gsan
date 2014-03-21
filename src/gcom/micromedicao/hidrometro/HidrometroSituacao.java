package gcom.micromedicao.hidrometro;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class HidrometroSituacao extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

    private Integer id;

    private String descricao;

    private Short indicadorUso;

    private Date ultimaAlteracao;
    
    private Short extraviado;

    public final static Integer INSTALADO = new Integer("1");
    
    public final static Integer EM_MANUTENCAO = new Integer("2");

    public final static Integer DISPONIVEL = new Integer("3");
    
    public final static Integer ROUBADO = new Integer("4");
    
    public final static Integer EXTRAVIADO = new Integer("1");
    
    public final static Integer NAO_EXTRAVIADO = new Integer("2");

    public HidrometroSituacao(String descricao, Short indicadorUso,
            Date ultimaAlteracao) {
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public HidrometroSituacao() {
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

	public Short getExtraviado() {
		return extraviado;
	}

	public void setExtraviado(Short extraviado) {
		this.extraviado = extraviado;
	}

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

    public Filtro retornaFiltro(){
		FiltroHidrometroSituacao filtro = new FiltroHidrometroSituacao();
		
		filtro.adicionarParametro(
				new ParametroSimples(FiltroHidrometroSituacao.ID, this.getId()));
		return filtro; 
	}

    public String getDescricaoParaRegistroTransacao() {
    	return this.getDescricao();
    }
}
