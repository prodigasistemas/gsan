package gcom.cadastro.imovel;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Categoria extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String descricao;

    /** persistent field */
    private String descricaoAbreviada;

    /** nullable persistent field */
    private Integer consumoMinimo;

    /** nullable persistent field */
    private Integer consumoEstouro;
    
    /** identifier field */
    private Integer consumoMaximoEconomiasValidacao;

    /** nullable persistent field */
    private BigDecimal vezesMediaEstouro;

    /** nullable persistent field */
    private Integer mediaBaixoConsumo;

    /** nullable persistent field */
    private BigDecimal porcentagemMediaBaixoConsumo;

    /** nullable persistent field */
    private Integer consumoAlto;

    /** nullable persistent field */
    private BigDecimal vezesMediaAltoConsumo;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    /** nullable persistent field */
    private Integer numeroConsumoMaximoEc;

    private CategoriaTipo categoriaTipo;
    
    private Short indicadorCobrancaAcrescimos;
    
    private String descricaoComId;
    
    private Short fatorEconomias;
    
    private short indicadorPermissaoEspecial;
    
    //  constantes
 	public final static int RESIDENCIAL_INT = 1; 
 	public final static int COMERCIAL_INT = 2; 
 	public final static int INDUSTRIAL_INT = 3; 
 	public final static int PUBLICO_INT = 4; 
     
     
   public final static Integer RESIDENCIAL = new Integer(1);
   public final static Integer COMERCIAL = new Integer(2);
   public final static Integer INDUSTRIAL = new Integer(3);
   public final static Integer PUBLICO = new Integer(4);
   
   public final static String RESIDENCIAL_DESCRICAO_ABREVIADA = "RES";
   public final static String COMERCIAL_DESCRICAO_ABREVIADA = "COM";
   public final static String INDUSTRIAL_DESCRICAO_ABREVIADA = "IND";
   public final static String PUBLICO_DESCRICAO_ABREVIADA = "PUB";
     
     //QUANTIDADE DE ECONOMIAS - UTILIZADO NO CASO DE USO [UC0108] - Obter
     // Quantidade de Economias por Categoria
     private Integer quantidadeEconomiasCategoria;
    
     public Categoria(Integer id) {
		this.id = id;
	}

	/** full constructor */
     public Categoria(String descricao, String descricaoAbreviada, Integer consumoMinimo, Integer consumoEstouro, BigDecimal vezesMediaEstouro, Integer mediaBaixoConsumo, BigDecimal porcentagemMediaBaixoConsumo, Integer consumoAlto, BigDecimal vezesMediaAltoConsumo, Short indicadorUso, Date ultimaAlteracao) {
         this.descricao = descricao;
         this.descricaoAbreviada = descricaoAbreviada;
         this.consumoMinimo = consumoMinimo;
         this.consumoEstouro = consumoEstouro;
         this.vezesMediaEstouro = vezesMediaEstouro;
         this.mediaBaixoConsumo = mediaBaixoConsumo;
         this.porcentagemMediaBaixoConsumo = porcentagemMediaBaixoConsumo;
         this.consumoAlto = consumoAlto;
         this.vezesMediaAltoConsumo = vezesMediaAltoConsumo;
         this.indicadorUso = indicadorUso;
         this.ultimaAlteracao = ultimaAlteracao;
         
     }

     /** full constructor */
     public Categoria(String descricao, String descricaoAbreviada, Integer consumoMinimo, Integer consumoEstouro, BigDecimal vezesMediaEstouro, Integer mediaBaixoConsumo, BigDecimal porcentagemMediaBaixoConsumo, Integer consumoAlto, BigDecimal vezesMediaAltoConsumo, Short indicadorUso, Date ultimaAlteracao, CategoriaTipo categoriaTipo) {
         this.descricao = descricao;
         this.descricaoAbreviada = descricaoAbreviada;
         this.consumoMinimo = consumoMinimo;
         this.consumoEstouro = consumoEstouro;
         this.vezesMediaEstouro = vezesMediaEstouro;
         this.mediaBaixoConsumo = mediaBaixoConsumo;
         this.porcentagemMediaBaixoConsumo = porcentagemMediaBaixoConsumo;
         this.consumoAlto = consumoAlto;
         this.vezesMediaAltoConsumo = vezesMediaAltoConsumo;
         this.indicadorUso = indicadorUso;
         this.ultimaAlteracao = ultimaAlteracao;
         this.categoriaTipo = categoriaTipo;
     }

     /** full constructor */
     public Categoria(String descricao, String descricaoAbreviada, Integer consumoMinimo, Integer consumoEstouro, BigDecimal vezesMediaEstouro, Integer mediaBaixoConsumo, BigDecimal porcentagemMediaBaixoConsumo, Integer consumoAlto, BigDecimal vezesMediaAltoConsumo, Short indicadorUso, Date ultimaAlteracao, Integer numeroConsumoMaximoEc) {
         this.descricao = descricao;
         this.descricaoAbreviada = descricaoAbreviada;
         this.consumoMinimo = consumoMinimo;
         this.consumoEstouro = consumoEstouro;
         this.vezesMediaEstouro = vezesMediaEstouro;
         this.mediaBaixoConsumo = mediaBaixoConsumo;
         this.porcentagemMediaBaixoConsumo = porcentagemMediaBaixoConsumo;
         this.consumoAlto = consumoAlto;
         this.vezesMediaAltoConsumo = vezesMediaAltoConsumo;
         this.indicadorUso = indicadorUso;
         this.ultimaAlteracao = ultimaAlteracao;
         this.numeroConsumoMaximoEc = numeroConsumoMaximoEc;
     }
     
    /** default constructor */
    public Categoria() {
    }

    /**
	 * @return Retorna o campo categoriaTipo.
	 */
	public CategoriaTipo getCategoriaTipo() {
		return categoriaTipo;
	}

	/**
	 * @param categoriaTipo O categoriaTipo a ser setado.
	 */
	public void setCategoriaTipo(CategoriaTipo categoriaTipo) {
		this.categoriaTipo = categoriaTipo;
	}

	/** minimal constructor */
    public Categoria(String descricao, String descricaoAbreviada) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
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

    public String getDescricaoAbreviada() {
        return this.descricaoAbreviada;
    }

    public void setDescricaoAbreviada(String descricaoAbreviada) {
        this.descricaoAbreviada = descricaoAbreviada;
    }

    public Integer getConsumoMinimo() {
        return this.consumoMinimo;
    }

    public void setConsumoMinimo(Integer consumoMinimo) {
        this.consumoMinimo = consumoMinimo;
    }

    public Integer getConsumoEstouro() {
        return this.consumoEstouro;
    }

    public void setConsumoEstouro(Integer consumoEstouro) {
        this.consumoEstouro = consumoEstouro;
    }

    public BigDecimal getVezesMediaEstouro() {
        return this.vezesMediaEstouro;
    }

    public void setVezesMediaEstouro(BigDecimal vezesMediaEstouro) {
        this.vezesMediaEstouro = vezesMediaEstouro;
    }

    public Integer getMediaBaixoConsumo() {
        return this.mediaBaixoConsumo;
    }

    public void setMediaBaixoConsumo(Integer mediaBaixoConsumo) {
        this.mediaBaixoConsumo = mediaBaixoConsumo;
    }

    public BigDecimal getPorcentagemMediaBaixoConsumo() {
        return this.porcentagemMediaBaixoConsumo;
    }

    public void setPorcentagemMediaBaixoConsumo(BigDecimal porcentagemMediaBaixoConsumo) {
        this.porcentagemMediaBaixoConsumo = porcentagemMediaBaixoConsumo;
    }

    public Integer getConsumoAlto() {
        return this.consumoAlto;
    }

    public void setConsumoAlto(Integer consumoAlto) {
        this.consumoAlto = consumoAlto;
    }

    public BigDecimal getVezesMediaAltoConsumo() {
        return this.vezesMediaAltoConsumo;
    }

    public void setVezesMediaAltoConsumo(BigDecimal vezesMediaAltoConsumo) {
        this.vezesMediaAltoConsumo = vezesMediaAltoConsumo;
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
    /**
      *  Descrição do método>>
      * 
      * @param other
      *            Descrição do parâmetro
      * @return Descrição do retorno
      */
     public boolean equals(Object other) {
         if ((this == other)) {
             return true;
         }
         if (!(other instanceof Categoria)) {
             return false;
         }
         Categoria castOther = (Categoria) other;
 
         return new EqualsBuilder().append(this.getId(), castOther.getId())
                 .isEquals();
     }

    
    /**
      * Description of the Method
      * 
      * @return Description of the Return Value
      */
     public int hashCode() {
         return new HashCodeBuilder().append(getId()).append(getConsumoAlto())
                 .append(getConsumoEstouro()).append(getConsumoMinimo()).append(
                         getDescricao()).append(getDescricaoAbreviada()).append(
                         getIndicadorUso()).append(getMediaBaixoConsumo())
                 .append(getPorcentagemMediaBaixoConsumo()).append(
                         getVezesMediaAltoConsumo())
                 .append(getUltimaAlteracao()).append(getVezesMediaEstouro())
                 .toHashCode();
     }
 
     /**
      * Retorna o valor de quantidadeEconomiasCategoria
      * 
      * @return O valor de quantidadeEconomiasCategoria
      */
     public Integer getQuantidadeEconomiasCategoria() {
         return quantidadeEconomiasCategoria;
     }
 
     /**
      * Seta o valor de quantidadeEconomiasCategoria
      * 
      * @param quantidadeEconomiasCategoria
      *            O novo valor de quantidadeEconomiasCategoria
      */
     public void setQuantidadeEconomiasCategoria(
             Integer quantidadeEconomiasCategoria) {
         this.quantidadeEconomiasCategoria = quantidadeEconomiasCategoria;
     }
     
     public String[] retornaCamposChavePrimaria(){
 		String[] retorno = new String[1];
 		retorno[0] = "id";
 		return retorno;
 	}
     
 	public Filtro retornaFiltro(){
 		FiltroCategoria filtroCategoria = new FiltroCategoria();
 		
 		filtroCategoria.adicionarParametro(
 				new ParametroSimples(FiltroCategoria.CODIGO, this.getId()));
 		return filtroCategoria; 
 	}

	public Integer getNumeroConsumoMaximoEc() {
		return numeroConsumoMaximoEc;
	}

	public void setNumeroConsumoMaximoEc(Integer numeroConsumoMaximoEc) {
		this.numeroConsumoMaximoEc = numeroConsumoMaximoEc;
	}

	public Short getIndicadorCobrancaAcrescimos() {
		return indicadorCobrancaAcrescimos;
	}

	public void setIndicadorCobrancaAcrescimos(Short indicadorCobrancaAcrescimos) {
		this.indicadorCobrancaAcrescimos = indicadorCobrancaAcrescimos;
	}

	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * @author Pedro Alexandre
	 * @date 19/09/2007
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
 	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return this.getDescricao();
	}
	
	@Override
	public void initializeLazy() {
		getDescricao();
	}

	public Integer getConsumoMaximoEconomiasValidacao() {
		return consumoMaximoEconomiasValidacao;
	}

	public void setConsumoMaximoEconomiasValidacao(
			Integer consumoMaximoEconomiasValidacao) {
		this.consumoMaximoEconomiasValidacao = consumoMaximoEconomiasValidacao;
	}

	public Short getFatorEconomias() {
		return fatorEconomias;
	}

	public void setFatorEconomias(Short fatorEconomias) {
		this.fatorEconomias = fatorEconomias;
	}

	public short getIndicadorPermissaoEspecial() {
		return indicadorPermissaoEspecial;
	}

	public void setIndicadorPermissaoEspecial(short indicadorPermissaoEspecial) {
		this.indicadorPermissaoEspecial = indicadorPermissaoEspecial;
	}
	
}
