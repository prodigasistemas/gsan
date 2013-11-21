package gcom.atendimentopublico;

import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cobranca.CobrancaSituacao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

public class EspecificacaoUnidadeCobranca extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;
	
    private EspecificacaoUnidadeCobrancaPK comp_id;

	private Date ultimaAlteracao;
	
	private UnidadeOrganizacional unidadeOrganizacional;
	
	private SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao;
	
	private CobrancaSituacao cobrancaSituacao;

	public EspecificacaoUnidadeCobranca() {
		super();
	}

	public EspecificacaoUnidadeCobranca(EspecificacaoUnidadeCobrancaPK comp_id, Date ultimaAlteracao, UnidadeOrganizacional unidadeOrganizacional, SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao, CobrancaSituacao cobrancaSituacao) {
		super();
		this.comp_id = comp_id;
		this.ultimaAlteracao = ultimaAlteracao;
		this.unidadeOrganizacional = unidadeOrganizacional;
		this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
		this.cobrancaSituacao = cobrancaSituacao;
	}

	public CobrancaSituacao getCobrancaSituacao() {
		return cobrancaSituacao;
	}

	public void setCobrancaSituacao(CobrancaSituacao cobrancaSituacao) {
		this.cobrancaSituacao = cobrancaSituacao;
	}

	public EspecificacaoUnidadeCobrancaPK getComp_id() {
		return comp_id;
	}

	public void setComp_id(EspecificacaoUnidadeCobrancaPK comp_id) {
		this.comp_id = comp_id;
	}

	public SolicitacaoTipoEspecificacao getSolicitacaoTipoEspecificacao() {
		return solicitacaoTipoEspecificacao;
	}

	public void setSolicitacaoTipoEspecificacao(
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao) {
		this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "comp_id";
		return retorno;
	}    
    
	public Filtro retornaFiltro(){
		FiltroEspecificacaoUnidadeCobranca filtroEspecificacaoUnidadeCobranca = new FiltroEspecificacaoUnidadeCobranca();
		
		filtroEspecificacaoUnidadeCobranca.adicionarCaminhoParaCarregamentoEntidade("comp_id");
		filtroEspecificacaoUnidadeCobranca.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacao");
		filtroEspecificacaoUnidadeCobranca.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoEspecificacao");

		
		filtroEspecificacaoUnidadeCobranca.adicionarParametro(
						new ParametroSimples(FiltroEspecificacaoUnidadeCobranca.COMP_ID_COBRANCA_SITUACAO_ID, cobrancaSituacao.getId()));
		
		filtroEspecificacaoUnidadeCobranca.adicionarParametro(
				new ParametroSimples(FiltroEspecificacaoUnidadeCobranca.COMP_ID_SOLICITACAO_TIPO_ESPECIFICACAO_ID, solicitacaoTipoEspecificacao.getId()));
		
		return filtroEspecificacaoUnidadeCobranca; 
	}

}
