package gcom.gerencial.cadastro.imovel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GCategoria implements Serializable {
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

    /** persistent field */
    private Set unResumoColetaEsgotos;

    /** persistent field */
    private Set unResumoConsumoAguas;

    /** persistent field */
    private Set unResumoFaturamentos;

    /** persistent field */
    private Set unResumoArrecadacao;

    /** persistent field */
    private Set unResumoLigacaoEconomias;

    /** persistent field */
    private Set gerSubcategorias;

    /** persistent field */
    private Set rgResumoLigacaoEconomias;
    
    /** persistent field */
    private Set UnResumoIndicadorDesempenhoMicromedicaos;    /** CONSTANTES */
    public final static int RESIDENCIAL = 1;  	public final static int COMERCIAL = 2;  	public final static int INDUSTRIAL = 3;  	public final static int PUBLICO = 4;  	/** FIM CONSTANTES */ 	 	 	private Integer quantidadeEconomiasCategoria; 	
    /** full constructor */
    public GCategoria(String descricao, String descricaoAbreviada, Integer consumoMinimo, Integer consumoEstouro, BigDecimal vezesMediaEstouro, Integer mediaBaixoConsumo, BigDecimal porcentagemMediaBaixoConsumo, Integer consumoAlto, BigDecimal vezesMediaAltoConsumo, Short indicadorUso, Date ultimaAlteracao, Set unResumoColetaEsgotos, Set unResumoConsumoAguas, Set unResumoFaturamentos, Set unResumoArrecadacao, Set unResumoLigacaoEconomias, Set gSubcategorias, Set rgResumoLigacaoEconomias) {
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
        this.unResumoColetaEsgotos = unResumoColetaEsgotos;
        this.unResumoConsumoAguas = unResumoConsumoAguas;
        this.unResumoFaturamentos = unResumoFaturamentos;
        this.unResumoArrecadacao = unResumoArrecadacao;
        this.unResumoLigacaoEconomias = unResumoLigacaoEconomias;
        this.gerSubcategorias = gSubcategorias;
        this.rgResumoLigacaoEconomias = rgResumoLigacaoEconomias;
    }

    /** default constructor */
    public GCategoria() {
    }

    /** minimal constructor */
    public GCategoria(String descricao, String descricaoAbreviada, Set unResumoColetaEsgotos, Set unResumoConsumoAguas, Set unResumoFaturamentos, Set unResumoArrecadacao, Set unResumoLigacaoEconomias, Set gSubcategorias, Set rgResumoLigacaoEconomias) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.unResumoColetaEsgotos = unResumoColetaEsgotos;
        this.unResumoConsumoAguas = unResumoConsumoAguas;
        this.unResumoFaturamentos = unResumoFaturamentos;
        this.unResumoArrecadacao = unResumoArrecadacao;
        this.unResumoLigacaoEconomias = unResumoLigacaoEconomias;
        this.gerSubcategorias = gSubcategorias;
        this.rgResumoLigacaoEconomias = rgResumoLigacaoEconomias;
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

    public Set getUnResumoColetaEsgotos() {
        return this.unResumoColetaEsgotos;
    }

    public void setUnResumoColetaEsgotos(Set unResumoColetaEsgotos) {
        this.unResumoColetaEsgotos = unResumoColetaEsgotos;
    }

    public Set getUnResumoConsumoAguas() {
        return this.unResumoConsumoAguas;
    }

    public void setUnResumoConsumoAguas(Set unResumoConsumoAguas) {
        this.unResumoConsumoAguas = unResumoConsumoAguas;
    }

    public Set getUnResumoFaturamentos() {
        return this.unResumoFaturamentos;
    }

    public void setUnResumoFaturamentos(Set unResumoFaturamentos) {
        this.unResumoFaturamentos = unResumoFaturamentos;
    }

   

    public Set getUnResumoArrecadacao() {
		return unResumoArrecadacao;
	}

	public void setUnResumoArrecadacao(Set unResumoArrecadacao) {
		this.unResumoArrecadacao = unResumoArrecadacao;
	}

	public Set getUnResumoLigacaoEconomias() {
        return this.unResumoLigacaoEconomias;
    }

    public void setUnResumoLigacaoEconomias(Set unResumoLigacaoEconomias) {
        this.unResumoLigacaoEconomias = unResumoLigacaoEconomias;
    }

 

    public Set getGerSubcategorias() {
		return gerSubcategorias;
	}

	public void setGerSubcategorias(Set gerSubcategorias) {
		this.gerSubcategorias = gerSubcategorias;
	}

	public Set getRgResumoLigacaoEconomias() {
        return this.rgResumoLigacaoEconomias;
    }

    public void setRgResumoLigacaoEconomias(Set rgResumoLigacaoEconomias) {
        this.rgResumoLigacaoEconomias = rgResumoLigacaoEconomias;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }	public Integer getQuantidadeEconomiasCategoria() {		return quantidadeEconomiasCategoria;	}	public void setQuantidadeEconomiasCategoria(Integer quantidadeEconomiasCategoria) {		this.quantidadeEconomiasCategoria = quantidadeEconomiasCategoria;	}

	public Set getUnResumoIndicadorDesempenhoMicromedicaos() {
		return UnResumoIndicadorDesempenhoMicromedicaos;
	}

	public void setUnResumoIndicadorDesempenhoMicromedicaos(
			Set unResumoIndicadorDesempenhoMicromedicaos) {
		UnResumoIndicadorDesempenhoMicromedicaos = unResumoIndicadorDesempenhoMicromedicaos;
	}

}
