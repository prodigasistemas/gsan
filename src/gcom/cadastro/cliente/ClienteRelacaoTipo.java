package gcom.cadastro.cliente;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ClienteRelacaoTipo extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    private Integer id;
    private String descricao;
    private Short indicadorUso;
    private Date ultimaAlteracao;
    private String descricaoComId;

    public final static Short PROPRIETARIO = new Short("1");
    public final static Short USUARIO = new Short("2");
    public final static Short RESPONSAVEL = new Short("3");
    
    public ClienteRelacaoTipo(String descricao, Short indicadorUso, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public ClienteRelacaoTipo(Integer id) {
        this.id = id;
    }

    public ClienteRelacaoTipo() {
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
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
	public Filtro retornaFiltro() {
		
		FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();
		filtroClienteRelacaoTipo.adicionarParametro(new ParametroSimples(
				FiltroClienteRelacaoTipo.CLIENTE_RELACAO_TIPO_ID, this.getId()));
		

		return filtroClienteRelacaoTipo;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
	
	public String getDescricaoComId() {
		
		if(this.getId().compareTo(10) == -1){
			descricaoComId = "0" + getId()+ " - " + getDescricao();
		}else{
			descricaoComId = getId()+ " - " + getDescricao();
		}
		
		return descricaoComId;
	}
	
	@Override
	public void initializeLazy() {
		retornaCamposChavePrimaria();
	}
}
