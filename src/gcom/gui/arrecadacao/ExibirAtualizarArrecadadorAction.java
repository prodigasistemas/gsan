package gcom.gui.arrecadacao;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0507] ATUALIZAR ARRECADADOR
 * 
 * @author Marcio Roberto
 * @date 13/02/2007
 */

public class ExibirAtualizarArrecadadorAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("atualizarArrecadador");

		AtualizarArrecadadorActionForm atualizarArrecadadorActionForm = (AtualizarArrecadadorActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		String idArrecadador = httpServletRequest
				.getParameter("idRegistroAtualizacao");

		if (idArrecadador == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				idArrecadador = (String) sessao
						.getAttribute("idRegistroAtualizacao");
			} else {
				idArrecadador = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}

		} else {
			sessao.setAttribute("i", true);
		}

		Arrecadador arrecadador = null;

		String idImovel = null;

		String inscricaoImovel = null;

		String idCliente = null;

		String objetoConsulta = httpServletRequest
				.getParameter("objetoConsulta");

		if (objetoConsulta != null && !objetoConsulta.equals("")) {

			// Validamos o cliente
			if (atualizarArrecadadorActionForm.getIdCliente() != null
					&& !atualizarArrecadadorActionForm.getIdCliente().trim()
							.equals("")) {
				FiltroCliente filtro = new FiltroCliente();

				filtro.adicionarParametro(new ParametroSimples(
						FiltroCliente.ID, atualizarArrecadadorActionForm
								.getIdCliente()));

				Collection colCliente = fachada.pesquisar(filtro, Cliente.class
						.getName());

				if (colCliente == null || !colCliente.iterator().hasNext()) {
					// O cliente não existe
					atualizarArrecadadorActionForm.setIdCliente("");
					atualizarArrecadadorActionForm
							.setNomeCliente("Cliente inexistente");
					httpServletRequest.setAttribute("existeCliente",
							"exception");

				} else {
					Cliente cliente = (Cliente) Util
							.retonarObjetoDeColecao(colCliente);
					atualizarArrecadadorActionForm.setIdCliente(cliente.getId()
							.toString());

					atualizarArrecadadorActionForm.setNomeCliente(cliente
							.getNome());
				}
			}

			if (atualizarArrecadadorActionForm.getIdImovel() != null
					&& !atualizarArrecadadorActionForm.getIdImovel().trim()
							.equals("")) {
				// Validamos o imovel
				FiltroImovel filtroImovel = new FiltroImovel();

				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, atualizarArrecadadorActionForm
								.getIdImovel()));

				Collection colImovel = fachada.pesquisar(filtroImovel,
						Imovel.class.getName());

				if (colImovel == null || colImovel.isEmpty()) {
					// O Imovel não existe
					atualizarArrecadadorActionForm.setIdImovel("");
					atualizarArrecadadorActionForm
							.setInscricaoImovel("Imóvel inexistente");

					httpServletRequest
							.setAttribute("existeImovel", "exception");

				} else {
					Imovel imovel = (Imovel) Util
							.retonarObjetoDeColecao(colImovel);

					inscricaoImovel = fachada.pesquisarInscricaoImovel(imovel
							.getId());

//					atualizarArrecadadorActionForm.setIdImovel(arrecadador
//							.getImovel().getId().toString());
                    
                    atualizarArrecadadorActionForm.setIdImovel(imovel
                            .getId().toString());

					atualizarArrecadadorActionForm
							.setInscricaoImovel(inscricaoImovel);
				}
			}
		} else {

			if (idArrecadador != null && !idArrecadador.trim().equals("")
					&& Integer.parseInt(idArrecadador) > 0) {

				FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
				// Adiciona entidade estrangeira para carregamento do objeto
				// "CLIENTE"
				// (ou seja, em ARRECADADOR existe um atributo do tipo Cliente,
				// então é preciso carregar o cliente)
				// o mesmo para Imovel.
				filtroArrecadador
						.adicionarCaminhoParaCarregamentoEntidade("cliente");
				filtroArrecadador
						.adicionarCaminhoParaCarregamentoEntidade("imovel");

				filtroArrecadador.adicionarParametro(new ParametroSimples(
						FiltroArrecadador.ID, idArrecadador));
				Collection colecaoArrecadador = fachada.pesquisar(
						filtroArrecadador, Arrecadador.class.getName());
				if (colecaoArrecadador != null && !colecaoArrecadador.isEmpty()) {
					arrecadador = (Arrecadador) Util
							.retonarObjetoDeColecao(colecaoArrecadador);
				}
			}

			if (idImovel == null || idImovel.trim().equals("")) {

				if (arrecadador.getImovel() != null
						&& !arrecadador.getImovel().getId().toString().trim()
								.equals("")) {

					idImovel = arrecadador.getImovel().getId().toString();

					// Filtro para descobrir id do Imovel
					FiltroImovel filtroImovel = new FiltroImovel();

					filtroImovel.adicionarParametro(new ParametroSimples(
							FiltroImovel.ID, idImovel));

					Collection colecaoImovel = fachada.pesquisar(filtroImovel,
							Imovel.class.getName());

					Imovel imovel = (Imovel) Util
							.retonarObjetoDeColecao(colecaoImovel);

					inscricaoImovel = fachada.pesquisarInscricaoImovel(imovel
							.getId());

					atualizarArrecadadorActionForm.setIdImovel(arrecadador
							.getImovel().getId().toString());

					atualizarArrecadadorActionForm
							.setInscricaoImovel(inscricaoImovel);
				}
			}

			if (idCliente == null || idCliente.trim().equals("")) {

				atualizarArrecadadorActionForm.setIdCliente(arrecadador
						.getCliente().getId().toString());

				atualizarArrecadadorActionForm.setNomeCliente(arrecadador
						.getCliente().getNome());

			}
			atualizarArrecadadorActionForm.setId(idArrecadador);

			atualizarArrecadadorActionForm.setIdAgente(arrecadador
					.getCodigoAgente().toString());

			atualizarArrecadadorActionForm.setInscricaoEstadual(arrecadador
					.getNumeroInscricaoEstadual());
			
			atualizarArrecadadorActionForm.setIndicadorUso(arrecadador
					.getIndicadorUso().toString());

			sessao.setAttribute("arrecadador", arrecadador);

			if(sessao.getAttribute("colecaoArrecadador") != null){
			sessao.setAttribute("caminhoRetornoVoltar",
					"/gsan/filtrarArrecadadorAction.do");
			}else{
				sessao.setAttribute("caminhoRetornoVoltar",
				"/gsan/exibirFiltrarArrecadadorAction.do");
			}

		}

		return retorno;
	}

}
