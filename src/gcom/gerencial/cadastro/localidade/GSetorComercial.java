package gcom.gerencial.cadastro.localidade;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GSetorComercial implements Serializable {
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
    private gcom.gerencial.cadastro.localidade.GLocalidade gerLocalidade;

    /** persistent field */
    private Set unResumoColetaEsgotos;

    /** persistent field */
    private Set unResumoConsumoAguas;

    /** persistent field */
    private Set unResumoFaturamentos;

    /** persistent field */
    private Set UnResumoArrecadacao;

    /** persistent field */
    private Set unResumoLigacaoEconomias;

    /** persistent field */
    private Set gerRotas;

    /** persistent field */
    private Set gerQuadras;
    
    /** persistent field */
    private Set unResumoIndicadorDesempenhoMicromedicaos;
    

    /** full constructor */
    public GSetorComercial(int codigo, String descricao, Short indicadorUso, Date ultimaAlteracao, gcom.gerencial.cadastro.localidade.GLocalidade gLocalidade, Set unResumoColetaEsgotos, Set unResumoConsumoAguas, Set unResumoFaturamentos, Set UnResumoArrecadacao, Set unResumoLigacaoEconomias, Set gRotas, Set gQuadras) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerLocalidade = gLocalidade;
        this.unResumoColetaEsgotos = unResumoColetaEsgotos;
        this.unResumoConsumoAguas = unResumoConsumoAguas;
        this.unResumoFaturamentos = unResumoFaturamentos;
        this.UnResumoArrecadacao = UnResumoArrecadacao;
        this.unResumoLigacaoEconomias = unResumoLigacaoEconomias;
        this.gerRotas = gRotas;
        this.gerQuadras = gQuadras;
    }

    /** default constructor */
    public GSetorComercial() {
    }

    /** minimal constructor */
    public GSetorComercial(int codigo, String descricao, gcom.gerencial.cadastro.localidade.GLocalidade gLocalidade, Set unResumoColetaEsgotos, Set unResumoConsumoAguas, Set unResumoFaturamentos, Set UnResumoArrecadacao, Set unResumoLigacaoEconomias, Set gRotas, Set gQuadras) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.gerLocalidade = gLocalidade;
        this.unResumoColetaEsgotos = unResumoColetaEsgotos;
        this.unResumoConsumoAguas = unResumoConsumoAguas;
        this.unResumoFaturamentos = unResumoFaturamentos;
        this.UnResumoArrecadacao = UnResumoArrecadacao;
        this.unResumoLigacaoEconomias = unResumoLigacaoEconomias;
        this.gerRotas = gRotas;
        this.gerQuadras = gQuadras;
    }

  
    public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public gcom.gerencial.cadastro.localidade.GLocalidade getGerLocalidade() {
		return gerLocalidade;
	}

	public void setGerLocalidade(gcom.gerencial.cadastro.localidade.GLocalidade gerLocalidade) {
		this.gerLocalidade = gerLocalidade;
	}

	public Set getGerQuadras() {
		return gerQuadras;
	}

	public void setGerQuadras(Set gerQuadras) {
		this.gerQuadras = gerQuadras;
	}

	public Set getGerRotas() {
		return gerRotas;
	}

	public void setGerRotas(Set gerRotas) {
		this.gerRotas = gerRotas;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	

	public Set getUnResumoArrecadacao() {
		return UnResumoArrecadacao;
	}

	public void setUnResumoArrecadacao(Set unResumoArrecadacao) {
		UnResumoArrecadacao = unResumoArrecadacao;
	}

	public Set getUnResumoColetaEsgotos() {
		return unResumoColetaEsgotos;
	}

	public void setUnResumoColetaEsgotos(Set unResumoColetaEsgotos) {
		this.unResumoColetaEsgotos = unResumoColetaEsgotos;
	}

	public Set getUnResumoConsumoAguas() {
		return unResumoConsumoAguas;
	}

	public void setUnResumoConsumoAguas(Set unResumoConsumoAguas) {
		this.unResumoConsumoAguas = unResumoConsumoAguas;
	}

	public Set getUnResumoFaturamentos() {
		return unResumoFaturamentos;
	}

	public void setUnResumoFaturamentos(Set unResumoFaturamentos) {
		this.unResumoFaturamentos = unResumoFaturamentos;
	}

	public Set getUnResumoLigacaoEconomias() {
		return unResumoLigacaoEconomias;
	}

	public void setUnResumoLigacaoEconomias(Set unResumoLigacaoEconomias) {
		this.unResumoLigacaoEconomias = unResumoLigacaoEconomias;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Set getUnResumoIndicadorDesempenhoMicromedicaos() {
		return unResumoIndicadorDesempenhoMicromedicaos;
	}

	public void setUnResumoIndicadorDesempenhoMicromedicaos(
			Set unResumoIndicadorDesempenhoMicromedicaos) {
		this.unResumoIndicadorDesempenhoMicromedicaos = unResumoIndicadorDesempenhoMicromedicaos;
	}

	

}
