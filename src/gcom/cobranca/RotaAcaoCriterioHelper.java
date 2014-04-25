package gcom.cobranca;

import java.util.Date;

import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.Rota;
import gcom.util.filtro.Filtro;

@ControleAlteracao()
public class RotaAcaoCriterioHelper extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	public static final int TABELA_ID = 56;

	@ControleAlteracao(idTabelaColuna=251)
	private CobrancaAcao cobrancaAcao;
	
	@ControleAlteracao(idTabelaColuna=252)
	private CobrancaCriterio cobrancaCriterio;
	
	@ControleAlteracao(idTabelaColuna=23750)
	private CobrancaGrupo cobrancaGrupo;
	
	@ControleAlteracao(idTabelaColuna=23751)
	private GerenciaRegional gerenciaRegional;
	
	@ControleAlteracao(idTabelaColuna=23752)
	private UnidadeNegocio unidadeNegocio;
	
	@ControleAlteracao(idTabelaColuna=23753)
	private Localidade localidadeInicial;
	
	@ControleAlteracao(idTabelaColuna=23754)
	private Localidade localidadeFinal;
	
	@ControleAlteracao(idTabelaColuna=23755)
	private SetorComercial setorComercialInicial;
	
	@ControleAlteracao(idTabelaColuna=23756)
	private SetorComercial setorComercialFinal;
	
	@ControleAlteracao(idTabelaColuna=23757)
	private Rota rotaInicial;
	
	@ControleAlteracao(idTabelaColuna=23758)
	private Rota rotaFinal;
	
	public RotaAcaoCriterioHelper(){}
	
	public RotaAcaoCriterioHelper(CobrancaAcao cobrancaAcao, CobrancaCriterio cobrancaCriterio, 
								  CobrancaGrupo cobrancaGrupo, GerenciaRegional gerenciaRegional,UnidadeNegocio unidadeNegocio, 
								  Localidade localidadeInicial, Localidade localidadeFinal,
								  SetorComercial setorComercialInicial, 
								  SetorComercial setorComercialFinal, 
			                     Rota rotaInicial, Rota rotaFinal ) {
		super();
		// TODO Auto-generated constructor stub
		this.cobrancaAcao = cobrancaAcao;
		this.cobrancaCriterio = cobrancaCriterio;
		this.cobrancaGrupo = cobrancaGrupo;
		this.gerenciaRegional = gerenciaRegional;
		this.unidadeNegocio = unidadeNegocio;
		this.localidadeInicial = localidadeInicial;
		this.localidadeFinal = localidadeFinal;
		this.setorComercialInicial = setorComercialInicial;
		this.setorComercialFinal = setorComercialFinal;
		this.rotaInicial = rotaInicial;
		this.rotaFinal = rotaFinal;
	}
	
	@Override
	public Date getUltimaAlteracao() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String[] retornaCamposChavePrimaria() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Filtro retornaFiltro() {
		return null; 
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String[] labels = {"cobrancaAcao.descricaoCobrancaAcao","cobrancaCriterio.descricaoCobrancaCriterio"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String[] labels = {"Ação de Cobranca", "Criterio de Cobranca"};
		return labels;		
	}
	
	public CobrancaAcao getCobrancaAcao() {
		return cobrancaAcao;
	}

	public void setCobrancaAcao(CobrancaAcao cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}

	public CobrancaCriterio getCobrancaCriterio() {
		return cobrancaCriterio;
	}

	public void setCobrancaCriterio(CobrancaCriterio cobrancaCriterio) {
		this.cobrancaCriterio = cobrancaCriterio;
	}

	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public CobrancaGrupo getCobrancaGrupo() {
		return cobrancaGrupo;
	}

	public void setCobrancaGrupo(CobrancaGrupo cobrancaGrupo) {
		this.cobrancaGrupo = cobrancaGrupo;
	}

	public Localidade getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(Localidade localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public Localidade getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(Localidade localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public Rota getRotaFinal() {
		return rotaFinal;
	}

	public void setRotaFinal(Rota rotaFinal) {
		this.rotaFinal = rotaFinal;
	}

	public Rota getRotaInicial() {
		return rotaInicial;
	}

	public void setRotaInicial(Rota rotaInicial) {
		this.rotaInicial = rotaInicial;
	}

	public SetorComercial getSetorComercialFinal() {
		return setorComercialFinal;
	}

	public void setSetorComercialFinal(SetorComercial setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}

	public SetorComercial getSetorComercialInicial() {
		return setorComercialInicial;
	}

	public void setSetorComercialInicial(SetorComercial setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}

	public UnidadeNegocio getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}


}
