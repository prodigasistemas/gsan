package gcom.gui.atendimentopublico.registroatendimento;

import gcom.cadastro.cliente.FiltroFoneTipo;
import gcom.cadastro.cliente.FoneTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os
 * parâmetros para realização da atualização de um R.A (Aba nº 01 - Dados
 * gerais)
 * 
 * @author Sávio Luiz
 * @date 31/08/2006
 */
public class ExibirAdicionarSolicitanteFoneAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("adicionarSolicitanteFone");

		HttpSession sessao = httpServletRequest.getSession(false);

		AdicionarSolicitanteRegistroAtendimentoActionForm adicionarSolicitanteRegistroAtendimentoActionForm = (AdicionarSolicitanteRegistroAtendimentoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		//URL de retorno
		sessao.removeAttribute("caminhoRetornoTelaAdicionarFone");
		sessao.removeAttribute("caminhoRetornoTelaAdicionarFonePopUp");
		String caminhoRetornoTelaAdicionarFone = httpServletRequest.getParameter("telaRetorno");
		String caminhoRetornoTelaAdicionarFonePopUp = httpServletRequest.getParameter("telaRetornoPopUp");
		String caminhoRetornoTelaAdicionarFoneReiterar = httpServletRequest.getParameter("telaRetornoReiterar");
		
		if (caminhoRetornoTelaAdicionarFone != null && !caminhoRetornoTelaAdicionarFone.equalsIgnoreCase("")){
			//Colocado por Raphael Rossiter em 30/09/2006
			//Facilitador para o controle da Aba nº 03 do processo de Inserir RA
			caminhoRetornoTelaAdicionarFone = caminhoRetornoTelaAdicionarFone + "&retornoFone=OK";
			sessao.setAttribute("caminhoRetornoTelaAdicionarFone", caminhoRetornoTelaAdicionarFone);
		}
		else if (caminhoRetornoTelaAdicionarFonePopUp != null && !caminhoRetornoTelaAdicionarFonePopUp.equalsIgnoreCase("")){
			
			caminhoRetornoTelaAdicionarFonePopUp = caminhoRetornoTelaAdicionarFonePopUp + "?retornoFone=OK";
			sessao.setAttribute("caminhoRetornoTelaAdicionarFonePopUp", caminhoRetornoTelaAdicionarFonePopUp);
			
		}else if (caminhoRetornoTelaAdicionarFoneReiterar != null && !caminhoRetornoTelaAdicionarFoneReiterar.equalsIgnoreCase("")){
			caminhoRetornoTelaAdicionarFoneReiterar = caminhoRetornoTelaAdicionarFoneReiterar + "?retornoFone=OK";
			sessao.setAttribute("caminhoRetornoTelaAdicionarFoneReiterar", caminhoRetornoTelaAdicionarFoneReiterar);
		}
		

		/*
		 * Tipo de Solicitação - Carregando a coleção que irá ficar disponível
		 * para escolha do usuário
		 * 
		 * [FS0003] - Verificar existência de dados
		 */
		Collection colecaoFoneTipo = (Collection) sessao
				.getAttribute("colecaoFoneTipo");

		if (colecaoFoneTipo == null) {

			FiltroFoneTipo filtroFoneTipo = new FiltroFoneTipo();

			colecaoFoneTipo = fachada.pesquisar(filtroFoneTipo,
					FoneTipo.class.getName());

			if (colecaoFoneTipo == null || colecaoFoneTipo.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"Tipo Telefone");
			} else {
				sessao.setAttribute("colecaoFoneTipo", colecaoFoneTipo);
			}
		}

		// limpa os campos no form
		adicionarSolicitanteRegistroAtendimentoActionForm.setIdTipoTelefone("");
		adicionarSolicitanteRegistroAtendimentoActionForm.setDddTelefone("");
		adicionarSolicitanteRegistroAtendimentoActionForm.setNumeroTelefone("");
		adicionarSolicitanteRegistroAtendimentoActionForm.setRamal("");
		
		httpServletRequest.setAttribute("nomeCampo", "idTipoTelefone");

		return retorno;

	}

}
