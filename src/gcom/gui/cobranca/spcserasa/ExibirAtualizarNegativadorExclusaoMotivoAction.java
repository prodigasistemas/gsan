package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.CobrancaDebitoSituacao;
import gcom.cobranca.FiltroCobrancaDebitoSituacao;
import gcom.cobranca.FiltroNegativadorExclusaoMotivo;
import gcom.cobranca.NegativadorExclusaoMotivo;
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
 * Action de Exibir Atualizar Negativador Exclusao Motivo
 * 
 * @author Yara Taciane
 * @created 04/01/2008
 */

public class ExibirAtualizarNegativadorExclusaoMotivoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Inicializando Variaveis
		ActionForward retorno = actionMapping.findForward("atualizarNegativadorExclusaoMotivo");
		AtualizarNegativadorExclusaoMotivoActionForm atualizarNegativadorExclusaoMotivoActionForm = (AtualizarNegativadorExclusaoMotivoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);

		// volta da msg de Negativador Exclusao Motivo já utilizado, não pode ser
		// alterado nem excluído.
		String confirmado = httpServletRequest.getParameter("confirmado");

		String idNegativadorExclusaoMotivo = null;
		if (httpServletRequest.getParameter("reload") == null
				|| httpServletRequest.getParameter("reload").equalsIgnoreCase(
						"") && (confirmado == null || confirmado.equals(""))) {
			// Recupera o id do Negativador Exclusao Motivo que vai ser atualizado

			if (httpServletRequest.getParameter("idRegistroInseridoAtualizar") != null) {
				idNegativadorExclusaoMotivo = httpServletRequest.getParameter("idRegistroInseridoAtualizar");
				// Definindo a volta do botão Voltar p Filtrar Negativador Exclusao Motivo			
				httpServletRequest.setAttribute("voltar", "filtrar");
				sessao.setAttribute("idRegistroAtualizacao",idNegativadorExclusaoMotivo);
			} else if (httpServletRequest.getParameter("idRegistroAtualizacao") == null) {
				idNegativadorExclusaoMotivo = (String) sessao.getAttribute("idRegistroAtualizacao");
				// Definindo a volta do botão Voltar p Filtrar Negativador Exclusao Motivo				
				httpServletRequest.setAttribute("voltar", "filtrar");
			} else if (httpServletRequest.getParameter("idRegistroAtualizacao") != null) {
				idNegativadorExclusaoMotivo = httpServletRequest.getParameter("idRegistroAtualizacao");
				// Definindo a volta do botão Voltar p Manter Negativador Exclusao Motivo		
				httpServletRequest.setAttribute("voltar", "manter");
				sessao.setAttribute("idRegistroAtualizacao",idNegativadorExclusaoMotivo);
			}
		} else {
			idNegativadorExclusaoMotivo = (String) sessao.getAttribute("idRegistroAtualizacao");
		}


		
		Collection colecaoCobrancaDebitoSituacao = (Collection) sessao.getAttribute("colecaoCobrancaDebitoSituacao");

		if (colecaoCobrancaDebitoSituacao == null) {

			FiltroCobrancaDebitoSituacao filtroCobrancaDebitoSituacao = new FiltroCobrancaDebitoSituacao();	
			
			filtroCobrancaDebitoSituacao.setConsultaSemLimites(true);

			colecaoCobrancaDebitoSituacao = fachada.pesquisar(filtroCobrancaDebitoSituacao,
					CobrancaDebitoSituacao.class.getName());

			if (colecaoCobrancaDebitoSituacao == null || colecaoCobrancaDebitoSituacao.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"COBRANCA DEBITO SITUACAO");
			} else {
				sessao.setAttribute("colecaoCobrancaDebitoSituacao", colecaoCobrancaDebitoSituacao);
			}
		}
		
			

		// Verifica se o usuário está selecionando o Negativador Exclusao Motivo da
		// página de manter
		// Caso contrário o usuário está teclando enter na página de atualizar
		if ((idNegativadorExclusaoMotivo != null && !idNegativadorExclusaoMotivo.equals(""))
				&& (httpServletRequest.getParameter("desfazer") == null)
				&& (httpServletRequest.getParameter("reload") == null || httpServletRequest
						.getParameter("reload").equalsIgnoreCase(""))) {
			exibirNegativadorExclusaoMotivo(idNegativadorExclusaoMotivo,
					atualizarNegativadorExclusaoMotivoActionForm, fachada, sessao,
					httpServletRequest);

		}

		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {

			// -------------- bt DESFAZER ---------------

			exibirNegativadorExclusaoMotivo(idNegativadorExclusaoMotivo,
					atualizarNegativadorExclusaoMotivoActionForm, fachada, sessao,
					httpServletRequest);

		}

		return retorno;

	}

	private void exibirNegativadorExclusaoMotivo(
			String idNegativadorExclusaoMotivo,
			AtualizarNegativadorExclusaoMotivoActionForm atualizarNegativadorExclusaoMotivoActionForm,
			Fachada fachada, HttpSession sessao,
			HttpServletRequest httpServletRequest) {

		// Cria a variável que vai armazenar o ParcelamentoPerfil para ser
		// atualizado
		NegativadorExclusaoMotivo negativadorExclusaoMotivo = null;

		// Cria o filtro de NegativadorExclusaoMotivo e seta o id do
		// NegativadorExclusaoMotivo para ser atualizado no filtro
		// e indica quais objetos devem ser retornados pela pesquisa
		FiltroNegativadorExclusaoMotivo filtroNegativadorExclusaoMotivo = new FiltroNegativadorExclusaoMotivo();
		filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(
				FiltroNegativadorExclusaoMotivo.ID, idNegativadorExclusaoMotivo));

		filtroNegativadorExclusaoMotivo
				.adicionarCaminhoParaCarregamentoEntidade("negativador.cliente");

		Collection<NegativadorExclusaoMotivo> collectionNegativadorExclusaoMotivo = fachada
				.pesquisar(filtroNegativadorExclusaoMotivo, NegativadorExclusaoMotivo.class
						.getName());

		// Caso a pesquisa tenha retornado o NegativadorExclusaoMotivo
		if (collectionNegativadorExclusaoMotivo != null
				&& !collectionNegativadorExclusaoMotivo.isEmpty()) {

			// Recupera da coleção o NegativadorExclusaoMotivo que vai ser atualizado
			negativadorExclusaoMotivo = (NegativadorExclusaoMotivo) Util
					.retonarObjetoDeColecao(collectionNegativadorExclusaoMotivo);
	
			// Seta no form os dados de NegativadorExclusaoMotivo
			if (negativadorExclusaoMotivo.getNegativador() != null
					&& !negativadorExclusaoMotivo.getNegativador().equals("")) {

				atualizarNegativadorExclusaoMotivoActionForm.setIdNegativador(""
						+ negativadorExclusaoMotivo.getNegativador());

				atualizarNegativadorExclusaoMotivoActionForm.setNegativadorCliente(""
						+ negativadorExclusaoMotivo.getNegativador().getCliente()
								.getNome());
			} else {
				atualizarNegativadorExclusaoMotivoActionForm
						.setNegativadorCliente("");
			}
			
			if (negativadorExclusaoMotivo.getCodigoExclusaoMotivo()!= 0) {

				atualizarNegativadorExclusaoMotivoActionForm.setCodigoMotivo(""
						+ negativadorExclusaoMotivo.getCodigoExclusaoMotivo());

			} else {
				atualizarNegativadorExclusaoMotivoActionForm
						.setCodigoMotivo("");
			}
			
			if (negativadorExclusaoMotivo.getDescricaoExclusaoMotivo() != null
					&& !negativadorExclusaoMotivo.getDescricaoExclusaoMotivo().equals("")) {

				atualizarNegativadorExclusaoMotivoActionForm.setDescricaoExclusaoMotivo(""
						+ negativadorExclusaoMotivo.getDescricaoExclusaoMotivo());

			} else {
				atualizarNegativadorExclusaoMotivoActionForm
						.setDescricaoExclusaoMotivo("");
			}
			
			
			if (negativadorExclusaoMotivo.getIndicadorUso() != 0) {

				atualizarNegativadorExclusaoMotivoActionForm.setIndicadorUso(""
						+ negativadorExclusaoMotivo.getIndicadorUso());

			} else {
				atualizarNegativadorExclusaoMotivoActionForm
						.setIndicadorUso("");
			}
			
			

			if (negativadorExclusaoMotivo.getCobrancaDebitoSituacao() != null
					&& !negativadorExclusaoMotivo.getCobrancaDebitoSituacao().equals("")) {

				atualizarNegativadorExclusaoMotivoActionForm.setIdCobrancaDebitoSituacao(""
						+ negativadorExclusaoMotivo.getCobrancaDebitoSituacao().getId());

			} else {
				atualizarNegativadorExclusaoMotivoActionForm
						.setIdCobrancaDebitoSituacao("-1");
			}
			
					
			atualizarNegativadorExclusaoMotivoActionForm.setTime(Long
					.toString(negativadorExclusaoMotivo.getUltimaAlteracao()
							.getTime()));

			sessao.setAttribute("negativadorExclusaoMotivo", negativadorExclusaoMotivo);

		}

	}

}
