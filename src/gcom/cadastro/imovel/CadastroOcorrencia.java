package gcom.cadastro.imovel;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.FiltroTabelaAuxiliar;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

@ControleAlteracao()

public class CadastroOcorrencia extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    private Integer id;
    private String descricao;
    private Short indicadorUso;
    private Date ultimaAlteracao;
    private Short indicadorValidacao;
    private String descricaoComId;
    private Short indicadorVisita;

    public CadastroOcorrencia(String descricao, Short indicadorUso,
            Date ultimaAlteracao) {
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public CadastroOcorrencia(Integer id) {
		this.id = id;
	}

	/** default constructor */
    public CadastroOcorrencia() {
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

    public Short getIndicadorValidacao() {
		return indicadorValidacao;
	}

	public void setIndicadorValidacao(Short indicadorValidacao) {
		this.indicadorValidacao = indicadorValidacao;
	}

	public Short getIndicadorVisita() {
		return indicadorVisita;
	}

	public void setIndicadorVisita(Short indicadorVisita) {
		this.indicadorVisita = indicadorVisita;
	}

	public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
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
	
	public Filtro retornaFiltro(){
		FiltroTabelaAuxiliar filtroTabelaAuxiliar = new FiltroTabelaAuxiliar();
		filtroTabelaAuxiliar.adicionarParametro(new ParametroSimples(FiltroTabelaAuxiliar.ID,this.getId()));
		
		return filtroTabelaAuxiliar; 
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = { "descricao"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Descricao"};
		return labels;		
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}
	
	@Override
	public void initializeLazy() {
		retornaCamposChavePrimaria();
	}
	
	public boolean ehOcorrenciaImpeditiva() {
		return indicadorValidacao.equals(ConstantesSistema.SIM);
	}

}
