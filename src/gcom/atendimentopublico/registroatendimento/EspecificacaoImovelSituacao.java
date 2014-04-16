package gcom.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.ordemservico.FiltroOrdemServicoProgramacao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class EspecificacaoImovelSituacao extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String descricao;

    /** persistent field */
    private Date ultimaAlteracao;

    /** full constructor */
    public EspecificacaoImovelSituacao(String descricao, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public EspecificacaoImovelSituacao() {
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
	
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroEspecificacaoImovelSituacao filtro = new FiltroEspecificacaoImovelSituacao();
		
		filtro.adicionarParametro(
				new ParametroSimples(FiltroOrdemServicoProgramacao.ID, this.getId()));
		
		return filtro; 
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return this.getDescricao();
	}
}
