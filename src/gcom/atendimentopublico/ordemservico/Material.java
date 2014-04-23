package gcom.atendimentopublico.ordemservico;

import gcom.util.filtro.Filtro;
import gcom.util.tabelaauxiliar.unidade.TabelaAuxiliarUnidade;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Material extends TabelaAuxiliarUnidade implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer codigo;
	private String descricao;
	private String descricaoAbreviada;
	private Short indicadorUso;
	private Date ultimaAlteracao;
	private MaterialUnidade materialUnidade;

	/** full constructor */
	public Material(Integer codigo, String descricao, String descricaoAbreviada, short indicadorUso, 
			Date ultimaAlteracao, MaterialUnidade materialUnidade) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.materialUnidade = materialUnidade;
	}

	/** default constructor */
	public Material() {

	}

	/** minimal constructor */
	public Material(Integer codigo, short indicadorUso, Date ultimaAlteracao, MaterialUnidade materialUnidade) {
		this.codigo = codigo;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.materialUnidade = materialUnidade;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
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

	public MaterialUnidade getMaterialUnidade() {
		return this.materialUnidade;
	}

	public void setMaterialUnidade(MaterialUnidade materialUnidade) {
		this.materialUnidade = materialUnidade;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public Filtro retornaFiltro() {
		FiltroMaterial filtroMaterial = new FiltroMaterial();
		filtroMaterial.adicionarCaminhoParaCarregamentoEntidade("materialUnidade");

		return filtroMaterial;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

}
