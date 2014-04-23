package gcom.micromedicao;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ConsumoMinimoArea extends ObjetoTransacao {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferencia;

    /** persistent field */
    private BigDecimal numeroAreaFinal;

    /** persistent field */
    private Integer numeroConsumo;
    
    /** persistent field */
    private short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;
    
    /** persistent field */
    private Categoria categoria;
    
    /** persistent field */
    private Subcategoria subCategoria;

    /** full constructor */
    public ConsumoMinimoArea(Integer id, int anoMesReferencia, BigDecimal numeroAreaFinal, Integer numeroConsumo, short indicadorUso, Date ultimaAlteracao, Categoria categoria, Subcategoria subCategoria) {
        this.id = id;
        this.anoMesReferencia = anoMesReferencia;
        this.numeroAreaFinal = numeroAreaFinal;
        this.numeroConsumo = numeroConsumo;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.categoria = categoria;
        this.subCategoria = subCategoria;
    }

    /** default constructor */
    public ConsumoMinimoArea() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    public int getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(int anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public BigDecimal getNumeroAreaFinal() {
		return numeroAreaFinal;
	}

	public void setNumeroAreaFinal(BigDecimal numeroAreaFinal) {
		this.numeroAreaFinal = numeroAreaFinal;
	}

	public Integer getNumeroConsumo() {
		return numeroConsumo;
	}

	public void setNumeroConsumo(Integer numeroConsumo) {
		this.numeroConsumo = numeroConsumo;
	}

	public Subcategoria getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(Subcategoria subCategoria) {
		this.subCategoria = subCategoria;
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
		FiltroConsumoMinimoArea filtroConsumoMinimoArea = new FiltroConsumoMinimoArea();
		filtroConsumoMinimoArea.adicionarParametro(new ParametroSimples(FiltroConsumoMinimoArea.ID,this.getId()));
		return filtroConsumoMinimoArea;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

}
