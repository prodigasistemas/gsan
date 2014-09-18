package gcom.cadastro.imovel;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Subcategoria extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    public static Integer RESIDENCIAL = new Integer(10);

    private Integer id;

    private int codigo;

    private String descricao;

    private Short indicadorUso;

    private Date ultimaAlteracao;
    
    private String codigoTarifaSocial;

    private short numeroFatorFiscalizacao;
    
    private Short indicadorTarifaConsumo;
    
    private Integer quantidadeEconomias;
    
    private String descricaoAbreviada;
    
    private Integer codigoGrupoSubcategoria;

    private gcom.cadastro.imovel.Categoria categoria;
    
    private String descricaoComId;
    
    private Short indicadorSazonalidade;

    public static final Subcategoria SUBCATEGORIA_ZERO;
    
    /**
     * Data: 01/03/2011
     * criação da constante da subcategoria R1, para ser usada na verificação 
     * da subcategoria do imovel, na geração do bonus social */
    public static final Integer SUBCATEGORIA_R1 = new Integer(1);

    /**
     * 
     * Mantis 494
     * 
     * Criação de constantes a serem utilizadas na geração 
     * da rota para recadastramento
     * 
     * @author Wellington Rocha*/
    public static final int RESIDENCIAL_R1 = 1;
    
    public static final int RESIDENCIAL_R2 = 2;
    
    public static final int RESIDENCIAL_R3 = 3;
    
    public static final int RESIDENCIAL_R4 = 4;
    
    public static final int COMERCIAL_C1 = 5;
    
    public static final int COMERCIAL_C2 = 6;
    
    public static final int COMERCIAL_C3 = 7;
    
    public static final int COMERCIAL_C4 = 8;
    
    public static final int INDUSTRIAL_I1 = 9;
    
    public static final int INDUSTRIAL_I2 = 10;
    
    public static final int INDUSTRIAL_I3 = 11;
    
    public static final int INDUSTRIAL_I4 = 12;
    
    public static final int PUBLICA_P1 = 13;
    
    public static final int PUBLICA_P2 = 14;
    
    public static final int PUBLICA_P3 = 15;
    
    public static final int PUBLICA_P4 = 16;
    
    static {
    	SUBCATEGORIA_ZERO = new Subcategoria();
    	SUBCATEGORIA_ZERO.setId(new Integer(0));
    }
    public Subcategoria(int codigo, String descricao, Short indicadorUso, Short indicadorSazonalidade,
    		String descricaoAbreviada, String codigoTarifaSocial, Integer codigoGrupoSubcategoria,
    		short numeroFatorFiscalizacao, Short indicadorTarifaConsumo,
            Date ultimaAlteracao, gcom.cadastro.imovel.Categoria categoria) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.indicadorSazonalidade = indicadorSazonalidade;
        this.descricaoAbreviada = descricaoAbreviada;
        this.codigoTarifaSocial = codigoTarifaSocial;
        this.codigoGrupoSubcategoria = codigoGrupoSubcategoria;
        this.numeroFatorFiscalizacao = numeroFatorFiscalizacao;
        this.indicadorTarifaConsumo = indicadorTarifaConsumo;
        this.ultimaAlteracao = ultimaAlteracao;
        this.categoria = categoria;
    }

    public Subcategoria() {
    }
    
    public Subcategoria(Integer id) {
    	this.id = id;
    }

    public Subcategoria(int codigo, String descricao,
            gcom.cadastro.imovel.Categoria categoria) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public gcom.cadastro.imovel.Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(gcom.cadastro.imovel.Categoria categoria) {
        this.categoria = categoria;
    }

    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if (!(other instanceof Subcategoria)) {
            return false;
        }
        Subcategoria castOther = (Subcategoria) other;

        return new EqualsBuilder().append(this.getId(), castOther.getId())
//                .append(this.getCategoria(), castOther.getCategoria())
                .isEquals();
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getId()).append(getCodigo())
                .append(getDescricao()).append(getIndicadorUso()).append(
                        getCategoria()).append(getUltimaAlteracao())
                .toHashCode();
    }
    
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
		
		filtroSubCategoria.adicionarCaminhoParaCarregamentoEntidade(FiltroSubCategoria.CATEGORIA);
		filtroSubCategoria.adicionarParametro(
				new ParametroSimples(FiltroSubCategoria.ID, this.getId()));
		return filtroSubCategoria; 
	}

	public String getCodigoTarifaSocial() {
		return codigoTarifaSocial;
	}

	public void setCodigoTarifaSocial(String codigoTarifaSocial) {
		this.codigoTarifaSocial = codigoTarifaSocial;
	}

	public short getNumeroFatorFiscalizacao() {
		return numeroFatorFiscalizacao;
	}

	public void setNumeroFatorFiscalizacao(short numeroFatorFiscalizacao) {
		this.numeroFatorFiscalizacao = numeroFatorFiscalizacao;
	}

	public Short getIndicadorTarifaConsumo() {
		return indicadorTarifaConsumo;
	}

	public void setIndicadorTarifaConsumo(Short indicadorTarifaConsumo) {
		this.indicadorTarifaConsumo = indicadorTarifaConsumo;
	}

	public Integer getQuantidadeEconomias() {
		return quantidadeEconomias;
	}

	public void setQuantidadeEconomias(Integer quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public Integer getCodigoGrupoSubcategoria() {
		return codigoGrupoSubcategoria;
	}

	public void setCodigoGrupoSubcategoria(Integer codigoGrupoSubcategoria) {
		this.codigoGrupoSubcategoria = codigoGrupoSubcategoria;
	}

	/**
	 * @author Pedro Alexandre
	 * @date 19/09/2007
	 */
	public String getDescricaoComId() {
		
		if(this.getId().compareTo(10) == -1){
			descricaoComId = "0" + getCodigo()+ " - " + getDescricao();
		}else{
			descricaoComId = getCodigo()+ " - " + getDescricao();
		}
		
		return descricaoComId;
	}
	
	@Override
	public void initializeLazy() {
		if (this.getCategoria() != null){
			this.getCategoria().initializeLazy();
		}
	}

	public Short getIndicadorSazonalidade() {
		return indicadorSazonalidade;
	}

	public void setIndicadorSazonalidade(Short indicadorSazonalidade) {
		this.indicadorSazonalidade = indicadorSazonalidade;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return this.getDescricao();
	}
}
