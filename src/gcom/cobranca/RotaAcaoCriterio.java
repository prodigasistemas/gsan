package gcom.cobranca;

import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.Rota;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;


/** @author Hibernate CodeGenerator */
public class RotaAcaoCriterio extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private RotaAcaoCriterioPK comp_id;

    /** nullable persistent field */
    private Rota rota;

    /** nullable persistent field */
    private CobrancaAcao cobrancaAcao;

    /** nullable persistent field */
    private CobrancaCriterio cobrancaCriterio;
    
    /** nullable persistent field */
    private Date ultimaAlteracao;

    public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/** default constructor */
    public RotaAcaoCriterio() {

    }

	public RotaAcaoCriterio(RotaAcaoCriterioPK comp_id, Rota rota, CobrancaAcao cobrancaAcao, CobrancaCriterio cobrancaCriterio) {
		super();
		// TODO Auto-generated constructor stub
		this.comp_id = comp_id;
		this.rota = rota;
		this.cobrancaAcao = cobrancaAcao;
		this.cobrancaCriterio = cobrancaCriterio;
	}

	/**
	 * @return Retorna o campo cobrancaAcao.
	 */
	public CobrancaAcao getCobrancaAcao() {
		return cobrancaAcao;
	}


	/**
	 * @param cobrancaAcao O cobrancaAcao a ser setado.
	 */
	public void setCobrancaAcao(CobrancaAcao cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}


	/**
	 * @return Retorna o campo cobrancaCriterio.
	 */
	public CobrancaCriterio getCobrancaCriterio() {
		return cobrancaCriterio;
	}


	/**
	 * @param cobrancaCriterio O cobrancaCriterio a ser setado.
	 */
	public void setCobrancaCriterio(CobrancaCriterio cobrancaCriterio) {
		this.cobrancaCriterio = cobrancaCriterio;
	}


	/**
	 * @return Retorna o campo comp_id.
	 */
	public RotaAcaoCriterioPK getComp_id() {
		return comp_id;
	}


	/**
	 * @param comp_id O comp_id a ser setado.
	 */
	public void setComp_id(RotaAcaoCriterioPK comp_id) {
		this.comp_id = comp_id;
	}


	/**
	 * @return Retorna o campo rota.
	 */
	public Rota getRota() {
		return rota;
	}


	/**
	 * @param rota O rota a ser setado.
	 */
	public void setRota(Rota rota) {
		this.rota = rota;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];

		retorno[0] = "comp_id";
		
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroRotaAcaoCriterio filtroRotaAcaoCriterio = new FiltroRotaAcaoCriterio();
		
		filtroRotaAcaoCriterio. adicionarCaminhoParaCarregamentoEntidade("rota");
		filtroRotaAcaoCriterio. adicionarCaminhoParaCarregamentoEntidade("cobrancaAcao");
		filtroRotaAcaoCriterio. adicionarCaminhoParaCarregamentoEntidade("cobrancaCriterio");
		filtroRotaAcaoCriterio. adicionarCaminhoParaCarregamentoEntidade("comp_id");

		filtroRotaAcaoCriterio. adicionarParametro(
				new ParametroSimples(FiltroRotaAcaoCriterio.ROTA_ID, rota.getId()));
		filtroRotaAcaoCriterio. adicionarParametro(
				new ParametroSimples(FiltroRotaAcaoCriterio.COBRANCA_ACAO_ID, cobrancaAcao.getId()));
		return filtroRotaAcaoCriterio; 
	}

}
