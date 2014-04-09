package gcom.gerencial.micromedicao.hidrometro;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GHidrometroCapacidade implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricaoHidrometroCapacidade;

    /** nullable persistent field */
    private String descricaoAbreviadaHidrometroCapacidade;

    /** nullable persistent field */
    private Short numeroDigitosLeituraMinimo;

    /** nullable persistent field */
    private Short numeroDigitosLeituraMaximo;

    /** nullable persistent field */
    private Short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Short numeroOrdem;

    /** persistent field */
    private Set resumoHidrometros;

    /** full constructor */
    public GHidrometroCapacidade(Integer id, String descricaoHidrometroCapacidade, String descricaoAbreviadaHidrometroCapacidade, Short numeroDigitosLeituraMinimo, Short numeroDigitosLeituraMaximo, Short indicadorUso, Date ultimaAlteracao, Short numeroOrdem, Set resumoHidrometros) {
        this.id = id;
        this.descricaoHidrometroCapacidade = descricaoHidrometroCapacidade;
        this.descricaoAbreviadaHidrometroCapacidade = descricaoAbreviadaHidrometroCapacidade;
        this.numeroDigitosLeituraMinimo = numeroDigitosLeituraMinimo;
        this.numeroDigitosLeituraMaximo = numeroDigitosLeituraMaximo;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.numeroOrdem = numeroOrdem;
        this.resumoHidrometros = resumoHidrometros;
    }

    /** default constructor */
    public GHidrometroCapacidade() {
    }

    /** minimal constructor */
    public GHidrometroCapacidade(Integer id, Date ultimaAlteracao, Set resumoHidrometros) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.resumoHidrometros = resumoHidrometros;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoHidrometroCapacidade() {
        return this.descricaoHidrometroCapacidade;
    }

    public void setDescricaoHidrometroCapacidade(String descricaoHidrometroCapacidade) {
        this.descricaoHidrometroCapacidade = descricaoHidrometroCapacidade;
    }

    public String getDescricaoAbreviadaHidrometroCapacidade() {
        return this.descricaoAbreviadaHidrometroCapacidade;
    }

    public void setDescricaoAbreviadaHidrometroCapacidade(String descricaoAbreviadaHidrometroCapacidade) {
        this.descricaoAbreviadaHidrometroCapacidade = descricaoAbreviadaHidrometroCapacidade;
    }

    public Short getNumeroDigitosLeituraMinimo() {
        return this.numeroDigitosLeituraMinimo;
    }

    public void setNumeroDigitosLeituraMinimo(Short numeroDigitosLeituraMinimo) {
        this.numeroDigitosLeituraMinimo = numeroDigitosLeituraMinimo;
    }

    public Short getNumeroDigitosLeituraMaximo() {
        return this.numeroDigitosLeituraMaximo;
    }

    public void setNumeroDigitosLeituraMaximo(Short numeroDigitosLeituraMaximo) {
        this.numeroDigitosLeituraMaximo = numeroDigitosLeituraMaximo;
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

    public Short getNumeroOrdem() {
        return this.numeroOrdem;
    }

    public void setNumeroOrdem(Short numeroOrdem) {
        this.numeroOrdem = numeroOrdem;
    }

    public Set getResumoHidrometros() {
        return this.resumoHidrometros;
    }

    public void setResumoHidrometros(Set resumoHidrometros) {
        this.resumoHidrometros = resumoHidrometros;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
