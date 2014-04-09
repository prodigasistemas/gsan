package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.TabelaAuxiliar;

import java.util.Date;

public class ImovelTipoPropriedade extends TabelaAuxiliar{
	
	private static final long serialVersionUID = 1L;

    private Integer id;
    private String descricao;
    private Short indicadorUso;
    private Date ultimaAlteracao;
    
    private String descricaoComId;

    
    public ImovelTipoPropriedade(Integer id, 
    	String descricao, 
    	Short indicadorUso, 
    	Date ultimaAlteracao) {
    	
		this.id = id;
		this.descricao = descricao;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public ImovelTipoPropriedade() {}
	
	public Filtro retornaFiltro() {
		FiltroImovelTipoPropriedade filtroImovelTipoPropriedade = new FiltroImovelTipoPropriedade();
		filtroImovelTipoPropriedade.adicionarParametro(new ParametroSimples(FiltroImovelTipoPropriedade.ID,
				this.getId()));
		return filtroImovelTipoPropriedade;		
	}
		
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
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
    
	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * @author Rafael Corrêa
	 * @date 09/05/2008
	 *
	 * @return
	 */
	public String getDescricaoComId() {
		
		if(this.getId().compareTo(10) == -1){
			descricaoComId = "0" + getId()+ " - " + getDescricao();
		}else{
			descricaoComId = getId()+ " - " + getDescricao();
		}
		
		return descricaoComId;
	}
	public String getDescricaoParaRegistroTransacao(){
		return this.getDescricao(); 
	}
}
