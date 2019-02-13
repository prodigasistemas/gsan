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
    
    public static final Integer SUBCATEGORIA_R1 = new Integer(1);
    
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

    public static final String DESCRICAO_R1 = "RESIDENCIAL - R1";
    public static final String DESCRICAO_R2 = "RESIDENCIAL - R2";
    public static final String DESCRICAO_R3 = "RESIDENCIAL - R3";
    public static final String DESCRICAO_R4 = "RESIDENCIAL - R4";

    public static final String DESCRICAO_C1 = "COMERCIAL - C1";
    public static final String DESCRICAO_C2 = "COMERCIAL - C2";
    public static final String DESCRICAO_C3 = "COMERCIAL - C3";
    public static final String DESCRICAO_C4 = "COMERCIAL - C4";
    
    public static final String DESCRICAO_I1 = "INDUSTRIAL - I1";
    public static final String DESCRICAO_I2 = "INDUSTRIAL - I2";
    public static final String DESCRICAO_I3 = "INDUSTRIAL - I3";
    public static final String DESCRICAO_I4 = "INDUSTRIAL - I4";
    
    public static final String DESCRICAO_P1 = "PUBLICO - P1";
    public static final String DESCRICAO_P2 = "PUBLICO - P2";
    public static final String DESCRICAO_P3 = "PUBLICO - P3";
    public static final String DESCRICAO_P4 = "PUBLICO - P4";
    
    public static final String DESCRICAO_SUB_R1 = "R1";
    public static final String DESCRICAO_SUB_R2 = "R2";
    public static final String DESCRICAO_SUB_R3 = "R3";
    public static final String DESCRICAO_SUB_R4 = "R4";

    public static final String DESCRICAO_SUB_C1 = "C1";
    public static final String DESCRICAO_SUB_C2 = "C2";
    public static final String DESCRICAO_SUB_C3 = "C3";
    public static final String DESCRICAO_SUB_C4 = "C4";
    
    public static final String DESCRICAO_SUB_I1 = "I1";
    public static final String DESCRICAO_SUB_I2 = "I2";
    public static final String DESCRICAO_SUB_I3 = "I3";
    public static final String DESCRICAO_SUB_I4 = "I4";
    
    public static final String DESCRICAO_SUB_P1 = "P1";
    public static final String DESCRICAO_SUB_P2 = "P2";
    public static final String DESCRICAO_SUB_P3 = "P3";
    public static final String DESCRICAO_SUB_P4 = "P4";

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
	
	public static Integer obterIdSubcategoria(String descricao) {
		if (descricao.equals(DESCRICAO_R1)) return RESIDENCIAL_R1;
		else if (descricao.equals(DESCRICAO_R2)) return RESIDENCIAL_R2;
		else if (descricao.equals(DESCRICAO_R3)) return RESIDENCIAL_R3;
		else if (descricao.equals(DESCRICAO_R4)) return RESIDENCIAL_R4;
		
		else if (descricao.equals(DESCRICAO_C1)) return COMERCIAL_C1;
		else if (descricao.equals(DESCRICAO_C2)) return COMERCIAL_C2;
		else if (descricao.equals(DESCRICAO_C3)) return COMERCIAL_C3;
		else if (descricao.equals(DESCRICAO_C4)) return COMERCIAL_C4;
		
		else if (descricao.equals(DESCRICAO_I1)) return INDUSTRIAL_I1;
		else if (descricao.equals(DESCRICAO_I2)) return INDUSTRIAL_I2;
		else if (descricao.equals(DESCRICAO_I3)) return INDUSTRIAL_I3;
		else if (descricao.equals(DESCRICAO_I4)) return INDUSTRIAL_I4;
		
		else if (descricao.equals(DESCRICAO_P1)) return PUBLICA_P1;
		else if (descricao.equals(DESCRICAO_P2)) return PUBLICA_P2;
		else if (descricao.equals(DESCRICAO_P3)) return PUBLICA_P3;
		else return PUBLICA_P4;
	}
	
	public static String getDescricaoSubcategoria(Integer id) {
        if (id.equals(RESIDENCIAL_R1)) return DESCRICAO_SUB_R1;
        else if (id.equals(RESIDENCIAL_R2)) return DESCRICAO_SUB_R2;
        else if (id.equals(RESIDENCIAL_R3)) return DESCRICAO_SUB_R3;
        else if (id.equals(RESIDENCIAL_R4)) return DESCRICAO_SUB_R4;
        
        else if (id.equals(COMERCIAL_C1)) return DESCRICAO_SUB_C1;
        else if (id.equals(COMERCIAL_C2)) return DESCRICAO_SUB_C2;
        else if (id.equals(COMERCIAL_C3)) return DESCRICAO_SUB_C3;
        else if (id.equals(COMERCIAL_C4)) return DESCRICAO_SUB_C4;
        
        else if (id.equals(INDUSTRIAL_I1)) return DESCRICAO_SUB_I1;
        else if (id.equals(INDUSTRIAL_I2)) return DESCRICAO_SUB_I2;
        else if (id.equals(INDUSTRIAL_I3)) return DESCRICAO_SUB_I3;
        else if (id.equals(INDUSTRIAL_I4)) return DESCRICAO_SUB_I4;
        
        else if (id.equals(PUBLICA_P1)) return DESCRICAO_SUB_P1;
        else if (id.equals(PUBLICA_P2)) return DESCRICAO_SUB_P2;
        else if (id.equals(PUBLICA_P3)) return DESCRICAO_SUB_P3;
        else return DESCRICAO_SUB_P4;
    }
	
	
	public boolean isR1() {
		return this.id.intValue() == RESIDENCIAL_R1;
	}
	
	public boolean isR2() {
		return this.id.intValue() == RESIDENCIAL_R2;
	}
	
	public boolean isR3() {
		return this.id.intValue() == RESIDENCIAL_R3;
	}
	
	public boolean isR4() {
		return this.id.intValue() == RESIDENCIAL_R4;
	}
	
	public boolean isC1() {
		return this.id.intValue() == COMERCIAL_C1;
	}
	
	public boolean isC2() {
		return this.id.intValue() == COMERCIAL_C2;
	}
	
	public boolean isC3() {
		return this.id.intValue() == COMERCIAL_C3;
	}
	
	public boolean isC4() {
		return this.id.intValue() == COMERCIAL_C4;
	}
	
	public boolean isI1() {
		return this.id.intValue() == INDUSTRIAL_I1;
	}
	
	public boolean isI2() {
		return this.id.intValue() == INDUSTRIAL_I2;
	}
	
	public boolean isI3() {
		return this.id.intValue() == INDUSTRIAL_I3;
	}
	
	public boolean isI4() {
		return this.id.intValue() == INDUSTRIAL_I4;
	}
	
	public boolean isP1() {
		return this.id.intValue() == PUBLICA_P1;
	}
	
	public boolean isP2() {
		return this.id.intValue() == PUBLICA_P2;
	}
	
	public boolean isP3() {
		return this.id.intValue() == PUBLICA_P3;
	}
	
	public boolean isP4() {
		return this.id.intValue() == PUBLICA_P4;
	}
}
