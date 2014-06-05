package gcom.micromedicao.hidrometro;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class HidrometroCapacidade extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private String descricao;

	/** nullable persistent field */
	private String descricaoAbreviada;

	/** nullable persistent field */
	private Short leituraMinimo;

	/** nullable persistent field */
	private Short leituraMaximo;

	/** nullable persistent field */
	private Short indicadorUso;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** nullable persistent field */
	private Short numeroOrdem;

	/** nullable persistent field */
	private String codigoHidrometroCapacidade;

	public final static Integer CAPACIDADE_ID_A = 10;

	public final static String CAPACIDADE_A = "A";

	public final static Integer CAPACIDADE_ID_B = 11;

	public final static String CAPACIDADE_B = "B";

	public final static Integer CAPACIDADE_ID_C = 12;

	public final static String CAPACIDADE_C = "C";

	public final static Integer CAPACIDADE_ID_D = 13;

	public final static String CAPACIDADE_D = "D";

	/** full constructor */
	public HidrometroCapacidade(String descricao, String descricaoAbreviada,
			Short leituraMinimo, Short leituraMaximo, Short indicadorUso,
			Date ultimaAlteracao) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.leituraMinimo = leituraMinimo;
		this.leituraMaximo = leituraMaximo;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/** default constructor */
	public HidrometroCapacidade() {
	}

	public HidrometroCapacidade(Integer id) {
		this.id = id;
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

	public Short getLeituraMinimo() {
		return this.leituraMinimo;
	}

	public void setLeituraMinimo(Short leituraMinimo) {
		this.leituraMinimo = leituraMinimo;
	}

	public Short getLeituraMaximo() {
		return this.leituraMaximo;
	}

	public void setLeituraMaximo(Short leituraMaximo) {
		this.leituraMaximo = leituraMaximo;
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

	public String getNumeroDigitosLeituraMinimoMaximo() {
		return (getLeituraMinimo().toString() + " - " + getLeituraMaximo()
				.toString());
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public Short getNumeroOrdem() {
		return numeroOrdem;
	}

	public void setNumeroOrdem(Short numeroOrdem) {
		this.numeroOrdem = numeroOrdem;
	}

	public String getCodigoHidrometroCapacidade() {
		return codigoHidrometroCapacidade;
	}

	public void setCodigoHidrometroCapacidade(String codigoHidrometroCapacidade) {
		this.codigoHidrometroCapacidade = codigoHidrometroCapacidade;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Filtro retornaFiltro() {
		FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();

		filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
				FiltroHidrometroCapacidade.ID, this.getId()));
		//filtroHidrometroCapacidade.adicionarCaminhoParaCarregamentoEntidade("descricao");
		
		return filtroHidrometroCapacidade;
	}

}
