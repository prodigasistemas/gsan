package gcom.gui.cobranca;

import java.util.Collection;
import java.util.Iterator;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.FiltroCobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pre- processamento para inserir o criterio da cobrança
 * 
 * @author Sávio Luiz
 * @date 17/04/2006
 */
public class ExibirInserirCriterioCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("inserirCriterioCobranca");

		HttpSession sessao = httpServletRequest.getSession(false);

		CriterioCobrancaActionForm criterioCobrancaActionForm = (CriterioCobrancaActionForm) actionForm;
		if (httpServletRequest.getParameter("limpaSessao") != null
				&& !httpServletRequest.getParameter("limpaSessao").equals("")) {
			criterioCobrancaActionForm.setDescricaoCriterio("");
			criterioCobrancaActionForm.setDataInicioVigencia("");
			criterioCobrancaActionForm.setNumeroAnoContaAntiga("");
			criterioCobrancaActionForm
					.setOpcaoAcaoImovelDebitoContasAntigas("");
			criterioCobrancaActionForm.setOpcaoAcaoImovelDebitoMesConta("");
			criterioCobrancaActionForm.setOpcaoAcaoImovelSit("");
			criterioCobrancaActionForm.setOpcaoAcaoImovelSitEspecial("");
			criterioCobrancaActionForm.setOpcaoAcaoInquilinoDebitoMesConta("");
			criterioCobrancaActionForm.setOpcaoContasRevisao("");
			criterioCobrancaActionForm.setPercentualQuantidadeMinimoPagoParceladoCancelado("");
			criterioCobrancaActionForm.setPercentualValorMinimoPagoParceladoCancelado("");
			criterioCobrancaActionForm.setValorLimitePrioridade("");

			sessao.removeAttribute("colecaoCobrancaCriterioLinha");

		}
		
		Fachada fachada = Fachada.getInstancia();
		
		// consultar as situacoes de cobranca
        FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();
        
        filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(FiltroCobrancaSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
        filtroCobrancaSituacao.setCampoOrderBy(FiltroCobrancaSituacao.DESCRICAO);
        
        Collection colecaoCobrancaSituacao = fachada.pesquisar(filtroCobrancaSituacao, CobrancaSituacao.class.getName());
        sessao.setAttribute("colecaoCobrancaSituacao", colecaoCobrancaSituacao);
        
        // Verificando se ha algum item selecionado, caso nao, selecionar tudo
        if (criterioCobrancaActionForm.getIdsCobrancaSituacao() == null || 
        		criterioCobrancaActionForm.getIdsCobrancaSituacao().length == 0){
	        Iterator iterCobSit = colecaoCobrancaSituacao.iterator();
	        String[] idsCobSit = new String[colecaoCobrancaSituacao.size()];
	        int i = 0;
	        while (iterCobSit.hasNext()) {
				CobrancaSituacao cobSit = (CobrancaSituacao) iterCobSit.next();
				idsCobSit[i++] = cobSit.getId() + "";			
			}
	        criterioCobrancaActionForm.setIdsCobrancaSituacao(idsCobSit);
        }
        
        // consultar as situacoes de ligacao de agua
        FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
        
        filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
        filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);
        
        Collection colecaoLigacaoAguaSituacao = fachada.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
        sessao.setAttribute("colecaoSituacaoLigacaoAgua", colecaoLigacaoAguaSituacao);

        // Verificando se ha algum item selecionado, caso nao, selecionar tudo        
        if (criterioCobrancaActionForm.getIdsSituacaoLigacaoAgua() == null || 
        		criterioCobrancaActionForm.getIdsSituacaoLigacaoAgua().length == 0){
	        Iterator iterLigAguaSit = colecaoLigacaoAguaSituacao.iterator();
	        String[] idsLigAguaSit = new String[colecaoLigacaoAguaSituacao.size()];
	        int i = 0;
	        while (iterLigAguaSit.hasNext()) {
				LigacaoAguaSituacao ligAguaSit = (LigacaoAguaSituacao) iterLigAguaSit.next();
				idsLigAguaSit[i++] = ligAguaSit.getId() + "";			
			}
	        criterioCobrancaActionForm.setIdsSituacaoLigacaoAgua(idsLigAguaSit);
        }

        // consultar as situacoes de ligacao de agua
        FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
        
        filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
        filtroLigacaoEsgotoSituacao.setCampoOrderBy(FiltroLigacaoEsgotoSituacao.DESCRICAO);
        
        Collection colecaoLigacaoEsgotoSituacao = fachada.pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
        sessao.setAttribute("colecaoSituacaoLigacaoEsgoto", colecaoLigacaoEsgotoSituacao);
        
        // Verificando se ha algum item selecionado, caso nao, selecionar tudo        
        if (criterioCobrancaActionForm.getIdsSituacaoLigacaoEsgoto() == null || 
        		criterioCobrancaActionForm.getIdsSituacaoLigacaoEsgoto().length == 0){
        
	        Iterator iterLigEsgotoSit = colecaoLigacaoEsgotoSituacao.iterator();
	        String[] idsLigEsgotoSit = new String[colecaoLigacaoEsgotoSituacao.size()];
	        int i = 0;
	        while (iterLigEsgotoSit.hasNext()) {
				LigacaoEsgotoSituacao ligEsgotoSit = (LigacaoEsgotoSituacao) iterLigEsgotoSit.next();
				idsLigEsgotoSit[i++] = ligEsgotoSit.getId() + "";			
			}
	        criterioCobrancaActionForm.setIdsSituacaoLigacaoEsgoto(idsLigEsgotoSit);
        }


		return retorno;
	}
}
