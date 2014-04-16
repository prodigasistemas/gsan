package gcom.gui.arrecadacao;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Vinícius Medeiros
 * @date 27/03/2008
 */
public class InserirArrecadacaoFormaAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma Forma de Arrecadacao
	 * 
	 * [UC0757] Inserir Forma de Arrecadacao
	 * 
	 * 
	 * @author Vinícius Medeiros
	 * @date 08/04/2008
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

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirArrecadacaoFormaActionForm inserirArrecadacaoFormaActionForm = (InserirArrecadacaoFormaActionForm) actionForm;

		// Mudar isso quando houver um esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();


		String descricao = inserirArrecadacaoFormaActionForm.getDescricao();
		
		ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
		Collection colecaoPesquisa = null;

		// Verifica se a Descrição foi preenchida.
		if (!"".equals(inserirArrecadacaoFormaActionForm.getDescricao())&& !"".equals(
				inserirArrecadacaoFormaActionForm.getCodigoArrecadacaoForma())) {
			
			arrecadacaoForma.setDescricao(inserirArrecadacaoFormaActionForm.getDescricao());
			arrecadacaoForma.setCodigoArrecadacaoForma(inserirArrecadacaoFormaActionForm.getCodigoArrecadacaoForma());
		} else {
			throw new ActionServletException("atencao.required", null,
					"descricao");
		}
		
		// Seta a Ultima alteração
		arrecadacaoForma.setUltimaAlteracao(new Date());

		FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();

		filtroArrecadacaoForma.adicionarParametro(new ParametroSimples(
				FiltroArrecadacaoForma.DESCRICAO, arrecadacaoForma.getDescricao()));
	
		colecaoPesquisa = (Collection) fachada.pesquisar(filtroArrecadacaoForma,
				ArrecadacaoForma.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			// Caso já haja uma Forma de Arrecadação com a descrição passada
			throw new ActionServletException(
					"atencao.arrecadacao_forma_ja_cadastrada", null, arrecadacaoForma
							.getDescricao());
		} else {
			arrecadacaoForma.setDescricao(descricao);

			Integer idArrecadacaoForma = (Integer) fachada.inserir(arrecadacaoForma);
			
			montarPaginaSucesso(httpServletRequest,
					"Forma de Arrecadação de descrição " + descricao
							+ " inserido com sucesso.",
					"Inserir outra Forma de Arrecadação",
					"exibirInserirArrecadacaoFormaAction.do?menu=sim",
					"exibirAtualizarArrecadacaoFormaAction.do?idRegistroAtualizacao="
							+ idArrecadacaoForma,
					"Atualizar Forma de Arrecadação Inserido");

			sessao.removeAttribute("InserirArrecadacaoFormaActionForm");

			return retorno;
		}

	}
}
