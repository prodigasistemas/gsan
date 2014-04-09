package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.LocalidadeEspecificacaoUnidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Iterator;
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
 * @date 30/07/2010
 */

public class InserirAssociacaoLocalidadeComEspecificacaoUnidadeAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ExibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeActionForm form = 
			(ExibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeActionForm) actionForm;
		
		List colecaoLocalidadeEspecificacaoUnidade = (List) sessao.getAttribute("colecaoLocalidadeEspecificacaoUnidade");
		
		LocalidadeEspecificacaoUnidade localidadeEspecificacaoUnidade = null;
		Integer idLocalidade = new Integer(form.getIdLocalidade());
		Iterator iterator = null;

		if(colecaoLocalidadeEspecificacaoUnidade != null && (colecaoLocalidadeEspecificacaoUnidade.size() > 0)){

			// remover as LocalidadeComEspecificacaoUnidade da localidades 
			fachada.removerLocalidadeComEspecificacaoUnidade(idLocalidade);
			
			iterator = colecaoLocalidadeEspecificacaoUnidade.iterator();
			
			while (iterator.hasNext()) {
				localidadeEspecificacaoUnidade = (LocalidadeEspecificacaoUnidade) iterator.next();
				
				fachada.inserir(localidadeEspecificacaoUnidade);
			}
		}else{
			throw new ActionServletException("atencao.especificacao_unidade_nao_informada");
		}
		
		// Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Associação de Localidade com especificação e Unidade: "
				+ " efetuada com sucesso.",
				"Realizar outra Manutenção de Associação de Localidade com especificação e Unidade",
				"exibirInserirAssociacaoLocalidadeComEspecificacaoUnidadeAction.do?menu=sim");
		
		sessao.removeAttribute("colecaoLocalidadeEspecificacaoUnidade");
		sessao.removeAttribute("colecaoTipoSolicitacao");
		sessao.removeAttribute("colecaoSolicitacaoTipoEspecificacao");
		sessao.removeAttribute("localidadePesquisada");
		
		return retorno;
	}

}
