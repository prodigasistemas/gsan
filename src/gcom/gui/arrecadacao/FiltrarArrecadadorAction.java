package gcom.gui.arrecadacao;

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
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
 * [UC0536]FILTRAR ARRECADADOR
 * 
 * @author Marcio Roberto
 * @date 01/02/2007
 */

public class FiltrarArrecadadorAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirManterArrecadador");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarArrecadadorActionForm filtrarArrecadadorActionForm = (FiltrarArrecadadorActionForm) actionForm;

		// Recupera todos os campos da página para ser colocada no filtro posteriormente

		String idAgente = filtrarArrecadadorActionForm.getIdAgente();
		String idCliente = filtrarArrecadadorActionForm.getIdCliente();
		String idImovel = filtrarArrecadadorActionForm.getIdImovel();
		String inscricaoEstadual = filtrarArrecadadorActionForm.getInscricaoEstadual();
		String indicadorUso = filtrarArrecadadorActionForm.getIndicadorUso();

		// Indicador Atualizar
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {
			sessao.removeAttribute("indicadorAtualizar");
		}

		boolean peloMenosUmParametroInformado = false;
		FiltroArrecadador filtroArrecadador = new FiltroArrecadador();

		// Código do Arrecadador
		if (idAgente != null && !idAgente.trim().equals("")) {
			// [FS0003] - Verificando a existência do Agente
			boolean achou = fachada.verificarExistenciaAgente(new Integer(idAgente));
			if (achou) {
				peloMenosUmParametroInformado = true;
				filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.CODIGO_AGENTE, idAgente));
			}
		}

		// Cliente
		if (idCliente != null && !idCliente.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.CLIENTE_ID, idCliente));
		}

		// Imovel
		if (idImovel != null && !idImovel.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.IMOVEL_ID, idImovel));
		}

		// Inscricao Estadual
		if (inscricaoEstadual != null && !inscricaoEstadual.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.INSCRICAO_ESTATAL, inscricaoEstadual));
		}
		
		//Indicador de Uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroArrecadador.adicionarParametro(new ParametroSimples(FiltroArrecadador.INDICADOR_USO, indicadorUso));
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}

		filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("imovel");

		Collection<Arrecadador> colecaoArrecadador = fachada.pesquisar(
				filtroArrecadador, Arrecadador.class.getName());

		if (colecaoArrecadador == null || colecaoArrecadador.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Arrecadador");
		} else {
			httpServletRequest.setAttribute("colecaoArrecadador",colecaoArrecadador);
			Arrecadador arrecadador = new Arrecadador();
			arrecadador = (Arrecadador) Util.retonarObjetoDeColecao(colecaoArrecadador);
			String idRegistroAtualizacao = arrecadador.getId().toString();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}

		sessao.setAttribute("filtroArrecadador", filtroArrecadador);

		httpServletRequest.setAttribute("filtroArrecadador", filtroArrecadador);

		return retorno;

	}
}
