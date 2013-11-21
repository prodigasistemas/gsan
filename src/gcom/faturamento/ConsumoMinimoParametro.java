package gcom.faturamento;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.FiltroConsumoMinimoArea;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ConsumoMinimoParametro extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferencia;

    /** persistent field */
    private BigDecimal numeroParametroFinal;

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

	public ConsumoMinimoParametro() {
		super();
	}

	public ConsumoMinimoParametro(Integer id, int anoMesReferencia, BigDecimal numeroParametroFinal, Integer numeroConsumo, short indicadorUso, Date ultimaAlteracao, Categoria categoria, Subcategoria subCategoria) {
		super();
		this.id = id;
		this.anoMesReferencia = anoMesReferencia;
		this.numeroParametroFinal = numeroParametroFinal;
		this.numeroConsumo = numeroConsumo;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.categoria = categoria;
		this.subCategoria = subCategoria;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Integer getNumeroConsumo() {
		return numeroConsumo;
	}

	public void setNumeroConsumo(Integer numeroConsumo) {
		this.numeroConsumo = numeroConsumo;
	}

	public BigDecimal getNumeroParametroFinal() {
		return numeroParametroFinal;
	}

	public void setNumeroParametroFinal(BigDecimal numeroParametroFinal) {
		this.numeroParametroFinal = numeroParametroFinal;
	}

	public Subcategoria getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(Subcategoria subCategoria) {
		this.subCategoria = subCategoria;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    
    public Filtro retornaFiltro() { //TODO
		FiltroConsumoMinimoArea filtroConsumoMinimoArea = new FiltroConsumoMinimoArea();
		filtroConsumoMinimoArea.adicionarParametro(new ParametroSimples(FiltroConsumoMinimoArea.ID,this.getId()));
		return filtroConsumoMinimoArea;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

}
