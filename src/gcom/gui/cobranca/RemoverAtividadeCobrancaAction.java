package gcom.gui.cobranca;

import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.FiltroCobrancaAtividade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade remover uma ou várias atividades de cobrança que tenham 
 * sido selecionadas anteriormente pelo usuário
 * 
 * @author Raphael Rossiter
 * @date 13/09/2007
 */
public class RemoverAtividadeCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		ManterAtividadeCobrancaActionForm manterAtividadeCobrancaActionForm = 
		(ManterAtividadeCobrancaActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection colecaoCobrancaAtividade = (Collection) sessao.getAttribute("colecaoCobrancaAtividade");
		
		String[] selecaoCobrancaAtividade = manterAtividadeCobrancaActionForm
        .getCobrancaAtividadeSelectID();
		
		if (selecaoCobrancaAtividade == null || selecaoCobrancaAtividade.length < 1) {
            throw new ActionServletException("atencao.registros.nao_selecionados");
        } 
		else{
        	
			Collection<CobrancaAtividade> colecaoCobrancaAtividadeRemocao = 
			this.carregarColecaoCobrancaAtividade(colecaoCobrancaAtividade, selecaoCobrancaAtividade);
			
			Iterator colecaoIter = colecaoCobrancaAtividadeRemocao.iterator();
			while (colecaoIter.hasNext()){
				CobrancaAtividade CobAt = (CobrancaAtividade) colecaoIter.next();	
				
				// Verificar se a cobrança atividade já foi atualizado por outro usuário
				// durante
				// esta atualização
				FiltroCobrancaAtividade filtroCobrancaAtividadeNoBanco = new FiltroCobrancaAtividade();

				filtroCobrancaAtividadeNoBanco.adicionarParametro(new ParametroSimples(
						FiltroCobrancaAtividade.ID, CobAt.getId()));

				filtroCobrancaAtividadeNoBanco
						.adicionarCaminhoParaCarregamentoEntidade("processo");

				Collection colecaoCobrancaAtividadeNaBase =  Fachada.getInstancia().pesquisar(
						filtroCobrancaAtividadeNoBanco,
						CobrancaAtividade.class.getName());
				if (colecaoCobrancaAtividadeNaBase != null
						&& !colecaoCobrancaAtividadeNaBase.isEmpty()) {
					CobrancaAtividade cobrancaAtividadeNaBase = (CobrancaAtividade) Util
							.retonarObjetoDeColecao(colecaoCobrancaAtividadeNaBase);
					if (cobrancaAtividadeNaBase.getUltimaAlteracao().after(
							CobAt.getUltimaAlteracao())) {
						throw new ActionServletException(
								"atencao.atualizacao.timestamp");

					}
				} else {
					throw new ActionServletException(
							"atencao.atualizacao.timestamp");
				}
				
				
				Fachada.getInstancia().remover(CobAt);
			}
			
			
		}
		
		sessao.removeAttribute("colecaoCobrancaAtividade");
		
		
		
		montarPaginaSucesso(httpServletRequest,
				selecaoCobrancaAtividade.length +
				" Atividade(s) de Cobrança removida(s) com sucesso.",
				"Realizar outra manutenção de Atividade de Cobrança",
				"exibirManterAtividadeCobrancaAction.do?menu=sim");
        
		

        
        return retorno;
	}
	
	
	private Collection<CobrancaAtividade> carregarColecaoCobrancaAtividade(Collection colecaoCobrancaAtividade,
			String[] selecaoCobrancaAtividade){
		
		Collection<CobrancaAtividade> colecaoRetorno = new ArrayList();
		
		if (colecaoCobrancaAtividade.size() != selecaoCobrancaAtividade.length){
		
			Integer idCobrancaAtividade = null;
			
			Iterator itColecaoCobrancaAtividade = null;
			CobrancaAtividade cobrancaAtividade = null;
			
			for (int x = 0; x < selecaoCobrancaAtividade.length; x++){
				
				idCobrancaAtividade = Integer.parseInt(selecaoCobrancaAtividade[x]);
			
				itColecaoCobrancaAtividade = colecaoCobrancaAtividade.iterator();
				
				while (itColecaoCobrancaAtividade.hasNext()){
					
					cobrancaAtividade = (CobrancaAtividade) itColecaoCobrancaAtividade.next();
					
					if (cobrancaAtividade.getId().equals(idCobrancaAtividade)){
						colecaoRetorno.add(cobrancaAtividade);
						break;
					}
				}
			}
			
		}
		else{
			colecaoRetorno.addAll(colecaoCobrancaAtividade);
		}
		
		
		return colecaoRetorno;
	}
}
