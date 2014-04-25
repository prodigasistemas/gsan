package gcom.cadastro.geografico;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class MunicipioFeriado extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;
    
    /** persistent field */
    private gcom.cadastro.geografico.Municipio municipio;

    /** persistent field */
    private Date dataFeriado;

    /** nullable persistent field */
    private String descricaoFeriado;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** full constructor */
    public MunicipioFeriado(Date dataFeriado, String descricaoFeriado,
            Date ultimaAlteracao, gcom.cadastro.geografico.Municipio municipio) {
        this.dataFeriado = dataFeriado;
        this.descricaoFeriado = descricaoFeriado;
        this.ultimaAlteracao = ultimaAlteracao;
        this.municipio = municipio;
    }

    /** default constructor */
    public MunicipioFeriado() {
    }

    /** minimal constructor */
    public MunicipioFeriado(Date dataFeriado,
            gcom.cadastro.geografico.Municipio municipio) {
        this.dataFeriado = dataFeriado;
        this.municipio = municipio;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataFeriado() {
        return this.dataFeriado;
    }

    public void setDataFeriado(Date dataFeriado) {
        this.dataFeriado = dataFeriado;
    }

    public String getDescricaoFeriado() {
        return this.descricaoFeriado;
    }

    public void setDescricaoFeriado(String descricaoFeriado) {
        this.descricaoFeriado = descricaoFeriado;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.cadastro.geografico.Municipio getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(gcom.cadastro.geografico.Municipio municipio) {
        this.municipio = municipio;
    }
    
    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }
    
    
    public Filtro retornaFiltro(){
		FiltroMunicipioFeriado filtroMunicipioFeriado = new FiltroMunicipioFeriado();

		filtroMunicipioFeriado.adicionarParametro(new ParametroSimples(FiltroMunicipioFeriado.ID,this.getId()));
		filtroMunicipioFeriado.adicionarCaminhoParaCarregamentoEntidade("municipio");
		
		return filtroMunicipioFeriado;
	}

    public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

}
