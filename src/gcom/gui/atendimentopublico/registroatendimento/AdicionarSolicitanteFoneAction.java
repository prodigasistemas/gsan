package gcom.gui.atendimentopublico.registroatendimento;

import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.FoneTipo;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * adiciona o telefona ao solicitante
 * 
 * @author Sávio Luiz
 * @date 31/08/2006
 */
public class AdicionarSolicitanteFoneAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirAdicionarSolicitante");

		HttpSession sessao = httpServletRequest.getSession(false);

		AdicionarSolicitanteRegistroAtendimentoActionForm adicionarSolicitanteRegistroAtendimentoActionForm = (AdicionarSolicitanteRegistroAtendimentoActionForm) actionForm;

		Collection colecaoFonesSolicitante = null;
		if (sessao.getAttribute("caminhoRetornoTelaAdicionarFone") != null){
			colecaoFonesSolicitante = (Collection) sessao
			.getAttribute("colecaoFonesAbaSolicitante");
		}
		else{
			colecaoFonesSolicitante = (Collection) sessao
			.getAttribute("colecaoFonesSolicitante");
		}
		
		
		boolean primeiro = false;
		if (colecaoFonesSolicitante == null
				|| colecaoFonesSolicitante.isEmpty()) {
			colecaoFonesSolicitante = new ArrayList();
			primeiro = true;
		}

		ClienteFone clienteFone = new ClienteFone();
		// seta o id e a descrição do fone tipo
		FoneTipo foneTipo = new FoneTipo();
		Object[] parmsFoneTipo = adicionarSolicitanteRegistroAtendimentoActionForm
				.getIdTipoTelefone().split(";");
		foneTipo.setId(new Integer((String) parmsFoneTipo[0]));
		foneTipo.setDescricao((String) parmsFoneTipo[1]);
		clienteFone.setFoneTipo(foneTipo);

		// seta o DDD
		clienteFone.setDdd(adicionarSolicitanteRegistroAtendimentoActionForm
				.getDddTelefone());

		// seta o número do telefone
		clienteFone
				.setTelefone(adicionarSolicitanteRegistroAtendimentoActionForm
						.getNumeroTelefone());
		// seta o ramal do telefone
		clienteFone.setRamal(adicionarSolicitanteRegistroAtendimentoActionForm
				.getRamal());
		
		if (primeiro) {
			clienteFone.setIndicadorTelefonePadrao(new Short("1"));
		}
		else{
			clienteFone.setIndicadorTelefonePadrao(new Short("2"));
		}
		
		//UltimaAlteracao para facilitar a identificacao do objeto
		clienteFone.setUltimaAlteracao(new Date());
		
		colecaoFonesSolicitante.add(clienteFone);
		
		
		//URL de retorno
		if (sessao.getAttribute("caminhoRetornoTelaAdicionarFone") != null){
			retorno = actionMapping.findForward("exibirAdicionarSolicitanteProcessoRA");
			httpServletRequest.setAttribute("telaRetorno", sessao.getAttribute("caminhoRetornoTelaAdicionarFone"));
			sessao.setAttribute("colecaoFonesAbaSolicitante", colecaoFonesSolicitante);
		
		}
		else if (sessao.getAttribute("caminhoRetornoTelaAdicionarFonePopUp") != null){
			retorno = actionMapping.findForward("exibirAdicionarSolicitanteProcessoRA");
			httpServletRequest.setAttribute("telaRetornoPopUp", sessao.getAttribute("caminhoRetornoTelaAdicionarFonePopUp"));
			sessao.setAttribute("colecaoFonesSolicitante", colecaoFonesSolicitante);
		}
		else if (sessao.getAttribute("caminhoRetornoTelaAdicionarFoneReiterar") != null){
			retorno = actionMapping.findForward("exibirAdicionarSolicitanteProcessoRA");
			httpServletRequest.setAttribute("telaRetorno", sessao.getAttribute("caminhoRetornoTelaAdicionarFoneReiterar"));
			sessao.setAttribute("colecaoFonesSolicitante", colecaoFonesSolicitante);
		}
		else{
			sessao.setAttribute("colecaoFonesSolicitante", colecaoFonesSolicitante);
		}

		
		return retorno;

	}

}
