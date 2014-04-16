package gcom.gerencial.cadastro.imovel;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GSubcategoria implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private int codigo;

    /** persistent field */
    private String descricao;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private GCategoria gerCategoria;

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
    private Set rgResumoLigacaoEconomias;
    
    /** persistent field */
    private int codigoGrupoSubcategoria;
    
    /** persistent field */
    private Set unResumoIndicadorDesempenhoMicromedicaos;

    /** full constructor */
    public GSubcategoria(int codigo, String descricao, Short indicadorUso, Date ultimaAlteracao, GCategoria gCategoria, Set unResumoColetaEsgotos, Set unResumoConsumoAguas, Set unResumoFaturamentos, Set unResumoArrecadacao, Set unResumoLigacaoEconomias, Set rgResumoLigacaoEconomias) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerCategoria = gCategoria;
        this.unResumoColetaEsgotos = unResumoColetaEsgotos;
        this.unResumoConsumoAguas = unResumoConsumoAguas;
        this.unResumoFaturamentos = unResumoFaturamentos;
        this.unResumoArrecadacao = unResumoArrecadacao;
        this.unResumoLigacaoEconomias = unResumoLigacaoEconomias;
        this.rgResumoLigacaoEconomias = rgResumoLigacaoEconomias;
    }

    /** default constructor */
    public GSubcategoria() {
    }

    /** minimal constructor */
    public GSubcategoria(int codigo, String descricao, GCategoria gCategoria, Set unResumoColetaEsgotos, Set unResumoConsumoAguas, Set unResumoFaturamentos, Set unResumoArrecadacao, Set unResumoLigacaoEconomias, Set rgResumoLigacaoEconomias) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.gerCategoria = gCategoria;
        this.unResumoColetaEsgotos = unResumoColetaEsgotos;
        this.unResumoConsumoAguas = unResumoConsumoAguas;
        this.unResumoFaturamentos = unResumoFaturamentos;
        this.unResumoArrecadacao = unResumoArrecadacao;
        this.unResumoLigacaoEconomias = unResumoLigacaoEconomias;
        this.rgResumoLigacaoEconomias = rgResumoLigacaoEconomias;
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

 

    public GCategoria getGerCategoria() {
		return gerCategoria;
	}

	public void setGerCategoria(GCategoria gerCategoria) {
		this.gerCategoria = gerCategoria;
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
    }

	public int getCodigoGrupoSubcategoria() {
		return codigoGrupoSubcategoria;
	}

	public void setCodigoGrupoSubcategoria(int codigoGrupoSubcategoria) {
		this.codigoGrupoSubcategoria = codigoGrupoSubcategoria;
	}

	public Set getUnResumoIndicadorDesempenhoMicromedicaos() {
		return unResumoIndicadorDesempenhoMicromedicaos;
	}

	public void setUnResumoIndicadorDesempenhoMicromedicaos(
			Set unResumoIndicadorDesempenhoMicromedicaos) {
		this.unResumoIndicadorDesempenhoMicromedicaos = unResumoIndicadorDesempenhoMicromedicaos;
	}

}
