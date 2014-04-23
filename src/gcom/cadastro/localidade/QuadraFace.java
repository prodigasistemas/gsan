package gcom.cadastro.localidade;

import gcom.interceptor.ObjetoTransacao;
import gcom.operacional.Bacia;
import gcom.operacional.DistritoOperacional;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;

public class QuadraFace extends ObjetoTransacao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public final static Short COM_REDE = new Short("2");
	public final static String SIM = "SIM";

	public final static Short SEM_REDE = new Short("1");
	public final static String NAO = "NAO";

	public final static Short REDE_PARCIAL = new Short("3");
	public final static String PARCIAL = "PARCIAL";
	
	
	/** identifier field */
	private Integer id;

	/** persistent field */
	private Integer numeroQuadraFace;

	/** nullable persistent field */
	private Short indicadorRedeAgua;

	/** nullable persistent field */
	private Short indicadorRedeEsgoto;
	
	/** nullable persistent field */
	private Short indicadorUso;

	/** nullable persistent field */
	private Date ultimaAlteracao;
	
	/** persistent field */
	private DistritoOperacional distritoOperacional;

	/** persistent field */
	private Bacia bacia;
	
	/** persistent field */
	private Quadra quadra;
	
	/** persistent field */
	private GrauDificuldadeExecucao grauDificuldadeExecucao;
	
	/** persistent field */
	private GrauRiscoSegurancaFisica grauRiscoSegurancaFisica;
	
	/** persistent field */
	private CondicaoAbastecimentoAgua condicaoAbastecimentoAgua;
	
	
	public QuadraFace(Integer id, Integer numeroQuadraFace, Short indicadorRedeAgua, Short indicadorRedeEsgoto, Short indicadorUso, Date ultimaAlteracao, DistritoOperacional distritoOperacional, Bacia bacia) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.numeroQuadraFace = numeroQuadraFace;
		this.indicadorRedeAgua = indicadorRedeAgua;
		this.indicadorRedeEsgoto = indicadorRedeEsgoto;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.distritoOperacional = distritoOperacional;
		this.bacia = bacia;
	}

	/** default constructor */
	public QuadraFace() {}

	public Bacia getBacia() {
		return bacia;
	}

	public void setBacia(Bacia bacia) {
		this.bacia = bacia;
	}

	public DistritoOperacional getDistritoOperacional() {
		return distritoOperacional;
	}

	public void setDistritoOperacional(DistritoOperacional distritoOperacional) {
		this.distritoOperacional = distritoOperacional;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorRedeAgua() {
		return indicadorRedeAgua;
	}

	public void setIndicadorRedeAgua(Short indicadorRedeAgua) {
		this.indicadorRedeAgua = indicadorRedeAgua;
	}

	public Short getIndicadorRedeEsgoto() {
		return indicadorRedeEsgoto;
	}

	public void setIndicadorRedeEsgoto(Short indicadorRedeEsgoto) {
		this.indicadorRedeEsgoto = indicadorRedeEsgoto;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Integer getNumeroQuadraFace() {
		return numeroQuadraFace;
	}

	public void setNumeroQuadraFace(Integer numeroQuadraFace) {
		this.numeroQuadraFace = numeroQuadraFace;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public String getDescricaoRedeAgua(){
		
		String retorno = "";
		
		if (this.getIndicadorRedeAgua() != null && this.getIndicadorRedeAgua().equals(QuadraFace.COM_REDE)){
			
			retorno = QuadraFace.SIM;
		}
		else if (this.getIndicadorRedeAgua() != null && this.getIndicadorRedeAgua().equals(QuadraFace.SEM_REDE)){
			
			retorno = QuadraFace.NAO;
		}
		else if (this.getIndicadorRedeAgua() != null && this.getIndicadorRedeAgua().equals(QuadraFace.REDE_PARCIAL)){
			
			retorno = QuadraFace.PARCIAL;
		}
		
		return retorno;
	}
	
	public String getDescricaoRedeEsgoto(){
		
		String retorno = "";
		
		if (this.getIndicadorRedeEsgoto() != null && this.getIndicadorRedeEsgoto().equals(QuadraFace.COM_REDE)){
			
			retorno = QuadraFace.SIM;
		}
		else if (this.getIndicadorRedeEsgoto() != null && this.getIndicadorRedeEsgoto().equals(QuadraFace.SEM_REDE)){
			
			retorno = QuadraFace.NAO;
		}
		else if (this.getIndicadorRedeEsgoto() != null && this.getIndicadorRedeEsgoto().equals(QuadraFace.REDE_PARCIAL)){
			
			retorno = QuadraFace.PARCIAL;
		}
		
		return retorno;
	}

	public Quadra getQuadra() {
		return quadra;
	}

	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}

	public CondicaoAbastecimentoAgua getCondicaoAbastecimentoAgua() {
		return condicaoAbastecimentoAgua;
	}

	public void setCondicaoAbastecimentoAgua(
			CondicaoAbastecimentoAgua condicaoAbastecimentoAgua) {
		this.condicaoAbastecimentoAgua = condicaoAbastecimentoAgua;
	}

	public GrauDificuldadeExecucao getGrauDificuldadeExecucao() {
		return grauDificuldadeExecucao;
	}

	public void setGrauDificuldadeExecucao(
			GrauDificuldadeExecucao grauDificuldadeExecucao) {
		this.grauDificuldadeExecucao = grauDificuldadeExecucao;
	}

	public GrauRiscoSegurancaFisica getGrauRiscoSegurancaFisica() {
		return grauRiscoSegurancaFisica;
	}

	public void setGrauRiscoSegurancaFisica(
			GrauRiscoSegurancaFisica grauRiscoSegurancaFisica) {
		this.grauRiscoSegurancaFisica = grauRiscoSegurancaFisica;
	}

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof QuadraFace)) {
			return false;
		}
		QuadraFace castOther = (QuadraFace) other;

		return (this.getNumeroQuadraFace().equals(castOther.getNumeroQuadraFace()));
	}
	
	public Filtro retornaFiltro() {
		
		FiltroQuadraFace filtroQuadraFace = new FiltroQuadraFace();

		filtroQuadraFace.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional");
		filtroQuadraFace.adicionarCaminhoParaCarregamentoEntidade("bacia");
		filtroQuadraFace.adicionarCaminhoParaCarregamentoEntidade("quadra");

		filtroQuadraFace.adicionarParametro(new ParametroSimples(
		FiltroQuadraFace.ID, this.getId()));
		
		return filtroQuadraFace;
	}
	
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
}
