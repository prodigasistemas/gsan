package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.EspecificacaoImovSitCriterio;
import gcom.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao;
import gcom.atendimentopublico.registroatendimento.FiltroEspecificacaoImovelSituacao;
import gcom.atendimentopublico.registroatendimento.FiltroEspecificacaoImovelSituacaoCriterio;
import gcom.fachada.Fachada;
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
 * [UC0404] - Manter Especificação da Situação do Imóvel
 * [SB0001] - Atualizar Especificação da situação
 *
 * @author Rafael Pinto
 * 
 * @date 09/11/2006
 */

public class ExibirAtualizarEspecificacaoSituacaoImovelAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("atualizarEspecificacaoSituacaoImovel");

		AtualizarEspecificacaoSituacaoImovelActionForm form = 
			(AtualizarEspecificacaoSituacaoImovelActionForm) actionForm;
		
		String tipoOperacao = httpServletRequest.getParameter("tipoOperacao");
		
		if (form.getDeleteSituacaoCriterioImovel() != null && !form.getDeleteSituacaoCriterioImovel().equals("")) {
			
			this.removeSituacaoCriterioImovel(form);
		
		}else if (tipoOperacao == null || tipoOperacao.equals("")){
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			Fachada fachada = Fachada.getInstancia();
			
			EspecificacaoImovelSituacao especificacaoImovelSituacao = null;
			String idEspecificacao = form.getIdEspecificacao();
			
			if (idEspecificacao == null || idEspecificacao.equals("")){
				
				if (httpServletRequest.getAttribute("idRegistroAtualizar") == null){
					idEspecificacao = (String) sessao.getAttribute("idRegistroAtualizar");
				}else{
					idEspecificacao = (String) httpServletRequest.getAttribute("idRegistroAtualizar").toString();
				}
			}
			
			if (idEspecificacao != null && !idEspecificacao.equals("")) {

				FiltroEspecificacaoImovelSituacao filtro = new FiltroEspecificacaoImovelSituacao();
				filtro.adicionarParametro(new ParametroSimples(FiltroEspecificacaoImovelSituacao.ID, idEspecificacao));

				Collection colecao = 
					fachada.pesquisar(filtro, EspecificacaoImovelSituacao.class.getName());

				if (colecao != null && !colecao.isEmpty()) {
					especificacaoImovelSituacao = (EspecificacaoImovelSituacao) Util.retonarObjetoDeColecao(colecao);
					
				}
			}
			
			FiltroEspecificacaoImovelSituacaoCriterio filtro = 
				new FiltroEspecificacaoImovelSituacaoCriterio();
			
			filtro.adicionarParametro(
				new ParametroSimples(
					FiltroEspecificacaoImovelSituacaoCriterio.ESPECIFICAO_SITUACAO_IMOVEL_ID, idEspecificacao));
			
			filtro.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
			filtro.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");

			Collection<EspecificacaoImovSitCriterio> colecaoEspecificacaoImovelSituacaoCriterio = 
				fachada.pesquisar(filtro,EspecificacaoImovSitCriterio.class.getName());
				
			form.setIdEspecificacao(""+especificacaoImovelSituacao.getId());
			form.setDescricaoEspecificacao(especificacaoImovelSituacao.getDescricao());
			form.setEspecificacaoImovelSituacaoCriterio(colecaoEspecificacaoImovelSituacaoCriterio);
			form.setTamanhoColecao(""+colecaoEspecificacaoImovelSituacaoCriterio.size());
			
			sessao.setAttribute("especificacaoImovelSituacao",especificacaoImovelSituacao);
			
		}
		
		resetPopup(form);
		
		return retorno;
	}
	
	/**
	 * Remove Situacao Criterio Imovel da Coleção 
	 *
	 * @author Rafael Pinto
	 * @date 03/08/2006
	 *
	 * @param AtualizarEspecificacaoSituacaoImovelActionForm
	 */
	private void removeSituacaoCriterioImovel(AtualizarEspecificacaoSituacaoImovelActionForm form) {
		
		Collection newSituacaoCriterioImovelCollection = new ArrayList();
		
		int index = 0;
		
		for (Iterator iter = form.getEspecificacaoImovelSituacaoCriterio().iterator(); iter.hasNext();) {
			
			index++;
			
			EspecificacaoImovSitCriterio element = (EspecificacaoImovSitCriterio) iter.next();
			
			if (index != new Integer(form.getDeleteSituacaoCriterioImovel()).intValue()) {
				newSituacaoCriterioImovelCollection.add(element);
			}else{
				if(element.getId() != null){
					form.getEspecificacaoImovelSituacaoCriterioRemovidas().add(element);	
				}
			}
		}
		
		form.setEspecificacaoImovelSituacaoCriterio(newSituacaoCriterioImovelCollection);
		form.setTamanhoColecao(newSituacaoCriterioImovelCollection.size()+"");
		form.setDeleteSituacaoCriterioImovel(null);
		
		resetPopup(form);
	}
	
	/**
	 * Reseta informações vindas do popup 
	 *
	 * @author Rafael Pinto
	 * @date 03/08/2006
	 *
	 * @param inserirEspecificacaoSituacaoImovelActionForm
	 */
	private void resetPopup(AtualizarEspecificacaoSituacaoImovelActionForm form) {

		form.setIndicadorHidrometroLigacaoAgua(null);
		form.setIndicadorHidrometroPoco(null);
		form.setSituacaoLigacaoAgua(null);
		form.setSituacaoLigacaoEsgoto(null);
		
	}
	
}
