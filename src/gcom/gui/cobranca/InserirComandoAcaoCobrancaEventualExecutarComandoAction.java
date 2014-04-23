package gcom.gui.cobranca;

import gcom.cobranca.CobrancaAtividade;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0243] Inserir Comando de Ação de Conbrança - Tipo de Comando Cronograma
 * Execuar o Comando, qdo o usuário clica no botão para executar estando na tela de comandar_acao_cobranca_eventual_inserir_processo2.jsp
 * @author Rafael Santos
 * @since 24/01/2006
 */
public class InserirComandoAcaoCobrancaEventualExecutarComandoAction  extends GcomAction{
	
	
	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();
		
		InserirComandoAcaoCobrancaEventualCriterioRotaActionForm inserirComandoAcaoCobrancaEventualCriterioRotaActionForm = (InserirComandoAcaoCobrancaEventualCriterioRotaActionForm) actionForm;
		String idComando = httpServletRequest.getParameter("idComando");
		
		Collection colecaoCobrancaAcaoAtividadeComando = 
			fachada.executarComandoAcaoCobranca(inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getPeriodoInicialConta(),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getPeriodoFinalConta(),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getPeriodoVencimentoContaInicial(),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getPeriodoVencimentoContaFinal(),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getCobrancaAcao(),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getCobrancaAtividade(),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getCobrancaGrupo(),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getGerenciaRegional(),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getLocalidadeOrigemID(),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getLocalidadeDestinoID(),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getSetorComercialOrigemCD(),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getSetorComercialDestinoCD(),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getIdCliente(),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getClienteRelacaoTipo(),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getIndicador(),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getRotaInicial(),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getRotaFinal(),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getSetorComercialOrigemID(),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getSetorComercialDestinoID(),
				idComando,
				this.getUsuarioLogado(httpServletRequest),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getTitulo(),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getDescricaoSolicitacao(),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getPrazoExecucao(),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getQuantidadeMaximaDocumentos(),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getIndicadorImoveisDebito(),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getIndicadorGerarBoletimCadastro(),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getCodigoClienteSuperior(),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getRotaInicial(),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getRotaFinal(),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getNumeroQuadraInicial(),
				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getNumeroQuadraFinal());
		

		//pesquisar cobranca atividade
		CobrancaAtividade cobrancaAtividade = fachada.consultarCobrancaAtividade(inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getCobrancaAtividade());
		
		//pesquisar cobranca acao
//		CobrancaAcao cobrancaAcao =  fachada.consultarCobrancaAcao(
//				inserirComandoAcaoCobrancaEventualCriterioRotaActionForm.getCobrancaAcao());

		montarPaginaSucesso(httpServletRequest,
				" "+colecaoCobrancaAcaoAtividadeComando.size()+" Ação(ões) de cobrança para a atividade " 
		           + cobrancaAtividade.getDescricaoCobrancaAtividade() + " executada(s) com sucesso.",
	                "Inserir outro Comando de Ação de Cobrança",
	                "exibirInserirComandoAcaoCobrancaAction.do?limparForm=OK&menu=sim");
        return retorno;
    }

}

