package gcom.financeiro;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class FinanciamentoTipo extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	//Constantes tipo de financiamento >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 
    public final static Integer SERVICO_NORMAL = new Integer(1);
    public final static Integer PARCELAMENTO_AGUA = new Integer(2);
    public final static Integer PARCELAMENTO_ESGOTO = new Integer(3);
    public final static Integer PARCELAMENTO_SERVICO = new Integer(4);
    public final static Integer ARRASTO_AGUA = new Integer(5);
    public final static Integer ARRASTO_ESGOTO = new Integer(6);
    public final static Integer ARRASTO_SERVICO = new Integer(7);
    public final static Integer JUROS_PARCELAMENTO = new Integer(8);
    public final static Integer ENTRADA_PARCELAMENTO = new Integer(9);
    public final static Integer DOACOES = new Integer(10);
    public final static Integer CANCELAMENTO_PARCELAMENTO = new Integer(11);
    
    public final static Short INDICADOR_INCLUSAO_SIM = new Short("1");
    public final static Short INDICADOR_INCLUSAO_NAO = new Short("2");
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>	
    
	
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private String descricaoAbreviada;

    /** nullable persistent field */
    private Short indicadorUso;
    
    private Short indicadorInclusao;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public FinanciamentoTipo(String descricao, String descricaoAbreviada, Short indicadorUso, Date ultimaAlteracao) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public FinanciamentoTipo() {
	}

	public FinanciamentoTipo(Integer id) {
		super();
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

	public Short getIndicadorInclusao() {
		return indicadorInclusao;
	}

	public void setIndicadorInclusao(Short indicadorInclusao) {
		this.indicadorInclusao = indicadorInclusao;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public Filtro retornaFiltro() {
		FiltroFinanciamentoTipo filtro = new FiltroFinanciamentoTipo();
		filtro.adicionarParametro(new ParametroSimples(FiltroFinanciamentoTipo.ID, this.getId()));
		return filtro;
	}

	@Override
	public void initializeLazy() {
		retornaCamposChavePrimaria();
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}
}
