package gcom.gui.atendimentopublico.ligacaoagua;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0463] Atualizar Consumo Mínimo da Ligação de Água
 * 
 * Apresentação da atualização de consumo mínimo de ligação de água
 * 
 * @author Leonardo Regis
 * @date 30/08/2006
 */
public class AtualizarConsumoMinimoLigacaoAguaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		AtualizarConsumoMinimoLigacaoAguaActionForm atualizarConsumoMinimoLigacaoAguaActionForm = (AtualizarConsumoMinimoLigacaoAguaActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		// Imovel
		Imovel imovel = new Imovel();
		imovel.setId(new Integer(atualizarConsumoMinimoLigacaoAguaActionForm
				.getMatriculaImovel()));
		ConsumoTarifa consumoTarifa = new ConsumoTarifa();
		consumoTarifa.setId(new Integer(
				atualizarConsumoMinimoLigacaoAguaActionForm
						.getConsumoTarifaId()));
		imovel.setConsumoTarifa(consumoTarifa);
		// Ligação Água
		LigacaoAgua ligacaoAgua = new LigacaoAgua();
		ligacaoAgua.setId(imovel.getId());
		
		if (atualizarConsumoMinimoLigacaoAguaActionForm
				.getConsumoMinimoFixado() != null && !atualizarConsumoMinimoLigacaoAguaActionForm
				.getConsumoMinimoFixado().trim().equals("")) {
			ligacaoAgua.setNumeroConsumoMinimoAgua(new Integer(
				atualizarConsumoMinimoLigacaoAguaActionForm
						.getConsumoMinimoFixado()));
		}
		
		ligacaoAgua
				.setUltimaAlteracao(atualizarConsumoMinimoLigacaoAguaActionForm
						.getDataConcorrencia());
		imovel.setLigacaoAgua(ligacaoAgua);
		// [FS0004] Validar Consumo Mínimo
		if (atualizarConsumoMinimoLigacaoAguaActionForm
				.getConsumoMinimoFixado() != null && !atualizarConsumoMinimoLigacaoAguaActionForm
				.getConsumoMinimoFixado().trim().equals("")) {
			fachada.validarConsumoMinimoLigacaoAgua(imovel);
		}

		IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();

		integracaoComercialHelper.setLigacaoAgua(ligacaoAgua);
		integracaoComercialHelper.setUsuarioLogado(usuario);
		
		if (atualizarConsumoMinimoLigacaoAguaActionForm.getVeioEncerrarOS()
				.equalsIgnoreCase("FALSE")) {
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);
			
			// Efetuando Atualização volume mínimo da Ligação de Água
			fachada.atualizarConsumoMinimoLigacaoAgua(integracaoComercialHelper);

		} else {
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.TRUE);
			sessao.setAttribute("integracaoComercialHelper",
					integracaoComercialHelper);

			if (sessao.getAttribute("semMenu") == null) {
				retorno = actionMapping
						.findForward("encerrarOrdemServicoAction");
			} else {
				retorno = actionMapping
						.findForward("encerrarOrdemServicoPopupAction");
			}
			sessao.removeAttribute("caminhoRetornoIntegracaoComercial");
		}
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {

			// Monta a página de sucesso
			montarPaginaSucesso(
					httpServletRequest,
					"Atualização do Consumo Mínimo da Ligação de Água "
							+ ligacaoAgua.getId() + " efetuada com Sucesso",
					"Atualizar o Consumo Mínimo de outra Ligação de Água",
					"exibirAtualizarConsumoMinimoLigacaoAguaAction.do?menu=sim",
					"exibirAtualizarConsumoMinimoLigacaoAguaAction.do?idOrdemServico="
							+ atualizarConsumoMinimoLigacaoAguaActionForm
									.getIdOrdemServico(),
					"Atualizar o Consumo Mínimo da Ligação de Água alterada");
		}

		return retorno;
	}
}
