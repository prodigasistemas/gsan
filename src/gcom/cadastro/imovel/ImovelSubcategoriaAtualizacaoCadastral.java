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

	private Imovel imovel;

	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Categoria categoria;

	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private Subcategoria subcategoria;

//	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String descricaoCategoria;
	
//	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String descricaoSubcategoria;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private short quantidadeEconomias;

    /** persistent field */
    private Date ultimaAlteracao;

    public ImovelSubcategoriaAtualizacaoCadastral(Integer id, Imovel imovel, Categoria categoria, Subcategoria subcategoria, String descricaoCategoria, String descricaoSubcategoria, short quantidadeEconomias, Date ultimaAlteracao) {
		this.id = id;
		this.imovel = imovel;
		this.categoria = categoria;
		this.subcategoria = subcategoria;
		this.descricaoCategoria = descricaoCategoria;
		this.descricaoSubcategoria = descricaoSubcategoria;
		this.quantidadeEconomias = quantidadeEconomias;
		this.ultimaAlteracao = ultimaAlteracao;
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

	public Filtro retornaFiltro() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarParametro(new ParametroSimples(FiltroTabela.ID, this.getImovel().getId()));
		return filtro;

	}

	public String[] retornaCamposChavePrimaria() {
		return null;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String toString() {
		return "ImovelSubcategoriaAtualizacaoCadastral [descricaoCategoria=" + descricaoCategoria + ", descricaoSubcategoria=" + descricaoSubcategoria
				+ ", quantidadeEconomias=" + quantidadeEconomias + "]";
	}
}
