package gcom.cadastro.imovel;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.transacao.FiltroTabela;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class ImovelSubcategoriaAtualizacaoCadastral extends ObjetoTransacao implements IImovelSubcategoria {
	
	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL = 1502;

    private Integer id;    

    private Integer idImovel;
    
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idCategoria;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String descricaoCategoria;
    
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Integer idSubcategoria;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String descricaoSubcategoria;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private short quantidadeEconomias;

	private ImovelSubcategoriaPK comp_id;
	
    /** persistent field */
    private Date ultimaAlteracao;

    public ImovelSubcategoriaAtualizacaoCadastral(Integer id, Integer idImovel, Integer idCategoria, String descricaoCategoria, Integer idSubcategoria, String descricaoSubcategoria, short quantidadeEconomias, Date ultimaAlteracao) {
		this.id = id;
		this.idImovel = idImovel;
		this.idCategoria = idCategoria;
		this.descricaoCategoria = descricaoCategoria;
		this.idSubcategoria = idSubcategoria;
		this.descricaoSubcategoria = descricaoSubcategoria;
		this.quantidadeEconomias = quantidadeEconomias;
		this.ultimaAlteracao = ultimaAlteracao;
		
		this.comp_id = new ImovelSubcategoriaPK(new Imovel(idImovel), new Subcategoria(idSubcategoria));
	}

	/** default constructor */
    public ImovelSubcategoriaAtualizacaoCadastral() {
    }

    public short getQuantidadeEconomias() {
		return quantidadeEconomias;
	}

	public void setQuantidadeEconomias(short quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String getDescricaoSubcategoria() {
        return this.descricaoSubcategoria;
    }

    public void setDescricaoSubcategoria(String descricaoSubcategoria) {
        this.descricaoSubcategoria = descricaoSubcategoria;
    }

    public Integer getIdCategoria() {
        return this.idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescricaoCategoria() {
        return this.descricaoCategoria;
    }

    public void setDescricaoCategoria(String descricaoCategoria) {
        this.descricaoCategoria = descricaoCategoria;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getIdSubcategoria() {
		return idSubcategoria;
	}

	public void setIdSubcategoria(Integer idSubcategoria) {
		this.idSubcategoria = idSubcategoria;
	}
	
	@Override
	public Filtro retornaFiltro() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarParametro(new ParametroSimples(FiltroTabela.ID, this.getIdImovel()));
		return filtro;

	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		// TODO Auto-generated method stub
		return null;
	}

	public ImovelSubcategoriaPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(ImovelSubcategoriaPK compId) {
		this.comp_id = compId;
	}

}
