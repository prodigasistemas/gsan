package gcom.gui.util.tabelaauxiliar.indicador;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.tabelaauxiliar.indicador.TabelaAuxiliarIndicador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rômulo Aurélio
 *
 */
public class AtualizarTabelaAuxiliarIndicadorAction extends GcomAction {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		//Pega o action form
		TabelaAuxiliarIndicadorActionForm form = (TabelaAuxiliarIndicadorActionForm) actionForm;

		//Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		//Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		//Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		//Recupera o ponto de coleta da sessão
		TabelaAuxiliarIndicador tabelaAuxiliarIndicador = (TabelaAuxiliarIndicador) sessao
				.getAttribute("tabelaAuxiliarIndicador");
		
		String tela = (String) sessao.getAttribute("tela");
		
		String indicadorNegocio = null ;
		
		if (tela.equalsIgnoreCase("quadraPerfil")) {
			
			indicadorNegocio = form.getIndicadorBaixaRenda();
			
		}
		
		

		//Atualiza a tabela auxiliar com os dados inseridos pelo usuário
		//A data de última alteração não é alterada, pois será usada na
		//verificação de atualização

		tabelaAuxiliarIndicador.setDescricao(form.getDescricao());
		
		if(form.getIndicadorBaixaRenda()!=null)
		tabelaAuxiliarIndicador.setIndicadorBaixaRenda(new Short(indicadorNegocio));

		if (form.getIndicadorUso() != null) {
			tabelaAuxiliarIndicador.setIndicadorUso(new Short(form
					.getIndicadorUso()));
		}
		
		//Atualiza os dados
		fachada.atualizarTabelaAuxiliar(tabelaAuxiliarIndicador);

		//Monta a página de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {

			montarPaginaSucesso(
					httpServletRequest,
					((String) sessao.getAttribute("titulo")) + " "
							+ tabelaAuxiliarIndicador.getId().toString()
							+ " atualizado(a) com sucesso.",
					"Realizar outra manutenção de "
							+ ((String) sessao.getAttribute("titulo")),
					((String) sessao
							.getAttribute("funcionalidadeTabelaAuxiliarIndicadorFiltrar")));

		}

		//Remove os objetos da sessão
		sessao.removeAttribute("funcionalidadeTabelaAuxiliarIndicadorManter");
		sessao.removeAttribute("titulo");
		sessao.removeAttribute("tabelaAuxiliarIndicador");
		sessao.removeAttribute("tamMaxCampoDescricao");


		//devolve o mapeamento de retorno
		return retorno;
	}

}
