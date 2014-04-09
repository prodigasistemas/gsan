package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarClienteTipoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarClienteTipoActionForm atualizarClienteTipoActionForm = (AtualizarClienteTipoActionForm) actionForm;

		ClienteTipo clienteTipo = (ClienteTipo) sessao.getAttribute("clienteTipoAtualizar");


		clienteTipo.setDescricao(atualizarClienteTipoActionForm.getDescricao());

		clienteTipo.setIndicadorPessoaFisicaJuridica(new Short(atualizarClienteTipoActionForm
				.getTipoPessoa()));

	
		// AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = null;

		if (atualizarClienteTipoActionForm.getEsferaPoder() != null) {

			Integer idEsferaPoder = new Integer(atualizarClienteTipoActionForm
					.getEsferaPoder());

			if (idEsferaPoder.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				clienteTipo.setEsferaPoder(null);
			} else {
				FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();
				filtroEsferaPoder.adicionarParametro(new ParametroSimples(
						FiltroEsferaPoder.ID, atualizarClienteTipoActionForm
								.getEsferaPoder().toString()));
				Collection colecaoEsferaPoder = (Collection) fachada.pesquisar(
						filtroEsferaPoder, EsferaPoder.class.getName());

				// setando
				clienteTipo.setEsferaPoder((EsferaPoder) colecaoEsferaPoder.iterator().next());
			}
		}

		fachada.atualizarClienteTipo(clienteTipo);

		montarPaginaSucesso(httpServletRequest, "Tipo de Cliente de código "
				+ clienteTipo.getId().toString() + " atualizado com sucesso.",
				"Realizar outra Manutenção de Tipo de Cliente",
				"exibirFiltrarClienteTipoAction.do?menu=sim");
		return retorno;
	}
}
