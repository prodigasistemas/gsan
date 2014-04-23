package gcom.gui.atendimentopublico.ligacaoagua;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ligacaoagua.CorteTipo;
import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.bean.DadosEfetuacaoCorteLigacaoAguaHelper;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o processamento da página de efetuar corte de ligação de
 * água
 * 
 * @author Thiago Tenório
 * @date 12/07/2006
 */

public class EfetuarCorteAdministrativoLigacaoAguaAction extends GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		HttpSession sessao = getSessao(httpServletRequest);
		EfetuarCorteAdministrativoLigacaoAguaActionForm corteAdministrativoLigacaoAguaActionForm = (EfetuarCorteAdministrativoLigacaoAguaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		String tipoCorteId = corteAdministrativoLigacaoAguaActionForm
				.getTipoCorte();

		OrdemServico ordemServico = (OrdemServico) sessao
				.getAttribute("ordemServico");
		
		//Comentado por Raphael Rossiter em 28/02/2007
		//Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();
		Imovel imovel = ordemServico.getImovel();

		// String idServicoMotivoNaoCobranca =
		// corteAdministrativoLigacaoAguaActionForm
		// .getMotivoNaoCobranca();
		// String valorPercentual = corteAdministrativoLigacaoAguaActionForm
		// .getPercentualCobranca();

		/*---------------------  Início Dados do Corte da Ligação de Água  ------------------------*/
		// validar Data Corte
		Date dataCorteAdministrativo = null;
		if (corteAdministrativoLigacaoAguaActionForm.getDataCorte() != null
				&& corteAdministrativoLigacaoAguaActionForm.getDataCorte() != "") {

			dataCorteAdministrativo = Util
					.converteStringParaDate(corteAdministrativoLigacaoAguaActionForm
							.getDataCorte());
		} else {
			throw new ActionServletException("atencao.required", null,
					" Data do Corte");
		}
		// Validar Tipo do Corte
		CorteTipo corteTipo = new CorteTipo();
		corteTipo.setId(new Integer(tipoCorteId));

		LigacaoAgua ligacaoAgua = imovel.getLigacaoAgua();

		ligacaoAgua.setDataCorteAdministrativo(dataCorteAdministrativo);
		ligacaoAgua.setCorteTipo(corteTipo);
		ligacaoAgua.setUltimaAlteracao(new Date());

		ordemServico.setIndicadorComercialAtualizado(new Short("1"));
		ordemServico.setUltimaAlteracao(new Date());
		ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;
		if (corteAdministrativoLigacaoAguaActionForm.getMotivoNaoCobranca() != null
				&& !corteAdministrativoLigacaoAguaActionForm
						.getMotivoNaoCobranca().equals("-1")) {
			servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
			servicoNaoCobrancaMotivo.setId(new Integer(
					corteAdministrativoLigacaoAguaActionForm
							.getMotivoNaoCobranca()));
		}
		ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);

		BigDecimal valorAtual = new BigDecimal(0);
		if (corteAdministrativoLigacaoAguaActionForm.getValorDebito() != null) {
			String valorDebito = corteAdministrativoLigacaoAguaActionForm
					.getValorDebito().toString().replace(".", "");

			valorDebito = valorDebito.replace(",", ".");

			valorAtual = new BigDecimal(valorDebito);

			ordemServico.setValorAtual(valorAtual);
		}

		if (corteAdministrativoLigacaoAguaActionForm.getPercentualCobranca() != null
				&& !corteAdministrativoLigacaoAguaActionForm
						.getPercentualCobranca().equals("")) {
			ordemServico.setPercentualCobranca(new BigDecimal(
					corteAdministrativoLigacaoAguaActionForm
							.getPercentualCobranca()));
		}
//		ordemServico
//				.setUltimaAlteracao(corteAdministrativoLigacaoAguaActionForm
//						.getDataConcorrencia());

		// Preenche Helper
		DadosEfetuacaoCorteLigacaoAguaHelper dadosHelper = new DadosEfetuacaoCorteLigacaoAguaHelper();
		dadosHelper.setLigacaoAgua(ligacaoAgua);
		dadosHelper.setOrdemServico(ordemServico);
		if (corteAdministrativoLigacaoAguaActionForm.getVeioEncerrarOS()
				.equalsIgnoreCase("true")) {
			dadosHelper.setVeioEncerrarOS(true);
		} else {
			dadosHelper.setVeioEncerrarOS(false);
		}
		if (corteAdministrativoLigacaoAguaActionForm.getQuantidadeParcelas() != null
				&& !corteAdministrativoLigacaoAguaActionForm
						.getQuantidadeParcelas().equals("")) {
			dadosHelper.setQtdeParcelas(new Integer(
					corteAdministrativoLigacaoAguaActionForm
							.getQuantidadeParcelas()).intValue());
		} else {
			dadosHelper.setQtdeParcelas(0);
		}
		
		IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();
		
		integracaoComercialHelper.setLigacaoAgua(ligacaoAgua);
		integracaoComercialHelper.setImovel(imovel);
		integracaoComercialHelper.setOrdemServico(ordemServico);
		integracaoComercialHelper.setDadosEfetuacaoCorteLigacaoAguaHelper(dadosHelper);
		if(corteAdministrativoLigacaoAguaActionForm.getVeioEncerrarOS().equalsIgnoreCase("FALSE")){
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);
			
			fachada.efetuarCorteAdministrativoLigacaoAgua(dadosHelper, usuario);
		}else{
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.TRUE);
			sessao.setAttribute("integracaoComercialHelper", integracaoComercialHelper);
			
			if(sessao.getAttribute("semMenu") == null){
				retorno = actionMapping.findForward("encerrarOrdemServicoAction");
			}else{
				retorno = actionMapping.findForward("encerrarOrdemServicoPopupAction");
			}
			sessao.removeAttribute("caminhoRetornoIntegracaoComercial");
		}
		
		
		if(retorno.getName().equalsIgnoreCase("telaSucesso")){

		// Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest,
				"Corte Administrativo da Ligação de Água do imóvel "
						+ imovel.getId() + " efetuada com Sucesso ",
				"Efetuar outro Corte Administrativo da Ligação de Água",
				"exibirEfetuarCorteAdministrativoLigacaoAguaAction.do?reset=true");
		}

		return retorno;
	}
}
