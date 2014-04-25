package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroLocalidadeEspecificacaoUnidade;
import gcom.atendimentopublico.registroatendimento.LocalidadeEspecificacaoUnidade;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1091] Informar Associação de Localidade com Especificação e Unidade
 * 
 * @author Hugo Leonardo
 *
 * @date 26/11/2010
 */
public class ExibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeAction");
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		// Form
		ExibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeActionForm form = 
			(ExibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeActionForm) actionForm;
		
		ArrayList<LocalidadeEspecificacaoUnidade> colecaoLocalidadeEspecificacaoUnidade = new ArrayList();
		
		if (httpServletRequest.getParameter("menu") != null
				&& httpServletRequest.getParameter("menu").equals("sim")) {
			
		}
		
		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");

		// Pega os codigos que o usuario digitou para a pesquisa direta da lotação
		if(form.getIdLocalidade() != null && !form.getIdLocalidade().trim().equals("") 
				&& objetoConsulta != null && objetoConsulta.equals("1")){
			
			sessao.removeAttribute("colecaoLocalidadeEspecificacaoUnidade");
			this.pesquisarLocalidade(httpServletRequest, form, sessao);
			
			this.pesquisarLocalidadeEspecificacaoUnidade(form, sessao);
		}
		
		// Remover Itens do Contrato da Colecao
        if ( httpServletRequest.getParameter("acao") != null && 
        	httpServletRequest.getParameter("acao").equals("limpar") ) {
        	
        	sessao.removeAttribute("colecaoLocalidadeEspecificacaoUnidade");
        }
        
        // Desfazer
        if ( httpServletRequest.getParameter("acao") != null && 
        	httpServletRequest.getParameter("acao").equals("desfazer") ) {
        	
        	sessao.removeAttribute("colecaoLocalidadeEspecificacaoUnidade");
			this.pesquisarLocalidade(httpServletRequest, form, sessao);
			
			this.pesquisarLocalidadeEspecificacaoUnidade(form, sessao);
        }
		
        // Remover Itens do Contrato da Colecao
        if ( httpServletRequest.getParameter("acao") != null && 
        	httpServletRequest.getParameter("acao").equals("remover") ) {
        	
        	Integer indice = new Integer(httpServletRequest.getParameter("id")).intValue();

        	if(sessao.getAttribute("colecaoLocalidadeEspecificacaoUnidade") != null){
        		colecaoLocalidadeEspecificacaoUnidade = 
        			(ArrayList<LocalidadeEspecificacaoUnidade>) sessao.getAttribute("colecaoLocalidadeEspecificacaoUnidade");
        		
        		if (colecaoLocalidadeEspecificacaoUnidade != null && 
	        			!colecaoLocalidadeEspecificacaoUnidade.isEmpty() && 
	        			colecaoLocalidadeEspecificacaoUnidade.size() > 1) {
	        		
	        		if (colecaoLocalidadeEspecificacaoUnidade.size() >= indice) {
	            		
	        			colecaoLocalidadeEspecificacaoUnidade.remove(indice-1);
	            	}
	        		
	        		if(colecaoLocalidadeEspecificacaoUnidade.isEmpty()){
	        			sessao.removeAttribute("colecaoLocalidadeEspecificacaoUnidade");
	        		}else{
	        			
	        			// o sistema classifica a lista de LocalidadeEspecificacaoUnidade
	            		Collections.sort((List) colecaoLocalidadeEspecificacaoUnidade,
	            				new Comparator() {
	            					public int compare(Object a, Object b) {
	            						String codigo1 = ((LocalidadeEspecificacaoUnidade) a)
	            								.getComp_id().getSolicitacaoTipoEspecificacao().getDescricao();
	            						String codigo2 = ((LocalidadeEspecificacaoUnidade) b)
	            							.getComp_id().getUnidadeOrganizacional().getDescricao();
	            						if (codigo1 == null || codigo1.equals("")) {
	            							return -1;
	            						} else {
	            							return codigo1.compareTo(codigo2);
	            						}
	            					}
	            				});
	        			
	        			sessao.setAttribute("colecaoLocalidadeEspecificacaoUnidade", colecaoLocalidadeEspecificacaoUnidade);
	        		}
				}
        	}
        }
        
        form.setIdTipoEspecificacao("");
		form.setIdTipoSolicitacao("");
		form.setIdUnidadeAtendimento("");
		form.setNomeUnidadeAtendimento("");
		
		return retorno;
	}
	
	/**
	 * Pesquisar Localidade
	 *
	 * @author Hugo Leonardo
	 * @date 26/11/2010
	 */
	private void pesquisarLocalidade(HttpServletRequest httpServletRequest, 
			ExibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeActionForm form, HttpSession sessao) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, form.getIdLocalidade()));

		Collection<Localidade> localidadePesquisada = Fachada.getInstancia().pesquisar(
				filtroLocalidade, Localidade.class.getName());

		if (localidadePesquisada != null && !localidadePesquisada.isEmpty()) {
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(localidadePesquisada);
			form.setIdLocalidade("" + localidade.getId());
			form.setNomeLocalidade( localidade.getDescricao());
			
			sessao.setAttribute("localidadePesquisada", localidade);

		} else {
			form.setIdLocalidade("");
			form.setNomeLocalidade("LOTAÇÃO INEXISTENTE");
			httpServletRequest.setAttribute("localidadeInexistente",true);
			httpServletRequest.setAttribute("nomeCampo", "idLocalidade");
		}
	}
	
	/**
	 * Pesquisar Collection LocalidadeEspecificacaoUnidade
	 *
	 * @author Hugo Leonardo
	 * @date 30/11/2010
	 */
	private void pesquisarLocalidadeEspecificacaoUnidade(
			ExibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeActionForm form, HttpSession sessao) {

		Collection<LocalidadeEspecificacaoUnidade> colecaoLocalidadeEspecificacaoUnidade = new ArrayList();
		
		FiltroLocalidadeEspecificacaoUnidade filtroLocalidadeEspecificacaoUnidade = new FiltroLocalidadeEspecificacaoUnidade();
		filtroLocalidadeEspecificacaoUnidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidadeEspecificacaoUnidade.LOCALIDADE_ID, form.getIdLocalidade()));
		
		filtroLocalidadeEspecificacaoUnidade.adicionarCaminhoParaCarregamentoEntidade(
				FiltroLocalidadeEspecificacaoUnidade.SOLICITACAO_TIPO_ESPECIFICACAO);
		filtroLocalidadeEspecificacaoUnidade.adicionarCaminhoParaCarregamentoEntidade(
				FiltroLocalidadeEspecificacaoUnidade.UNIDADE_ORGANIZACIONAL);
		
		filtroLocalidadeEspecificacaoUnidade.setCampoOrderBy(
				FiltroLocalidadeEspecificacaoUnidade.SOLICITACAO_TIPO_ESPECIFICACAO_ID);
		filtroLocalidadeEspecificacaoUnidade.setCampoOrderBy(
				FiltroLocalidadeEspecificacaoUnidade.UNIDADE_ORGANIZACIONAL_ID);
		
		colecaoLocalidadeEspecificacaoUnidade = this.getFachada().pesquisar(filtroLocalidadeEspecificacaoUnidade, 
				LocalidadeEspecificacaoUnidade.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoLocalidadeEspecificacaoUnidade)){
			sessao.setAttribute("colecaoLocalidadeEspecificacaoUnidade", colecaoLocalidadeEspecificacaoUnidade);
		}
	}
	
}
