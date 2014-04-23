package gcom.gui.atendimentopublico.hidrometro;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;

import java.math.BigDecimal;
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
 * @author Rômulo Aurélio
 * @date 28/06/2006
 */
public class EfetuarRemanejamentoHidrometroAction extends GcomAction {

	/**
	 * [UC0365] Efetuar Remanejamento de Hidrômetro
	 * 
	 * Este caso de uso permite efetuar o remanejamento de hidrometro, sendo
	 * chamado pela funcionalidade que encerra a execução da ordem de serviço ou
	 * chamada diretamente do Menu.
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

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		EfetuarRemanejamentoHidrometroActionForm efetuarRemanejamentoHidrometroActionForm = (EfetuarRemanejamentoHidrometroActionForm) actionForm;

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		/*
		 * -------------[UC0107] Registrar Transação-----------------------
		 * 
		 */

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_REMANEJAMENTO_HIDROMETRO_EFETUAR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_REMANEJAMENTO_HIDROMETRO_EFETUAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		// ----------------[UC0107] -Fim- Registrar Transação-----------

		String localInstalacao = efetuarRemanejamentoHidrometroActionForm
				.getLocalInstalacao();
		String protecao = efetuarRemanejamentoHidrometroActionForm
				.getProtecao();
		String cavalete = efetuarRemanejamentoHidrometroActionForm
				.getTipoCavalete();
		String idServicoMotivoNaoCobranca = efetuarRemanejamentoHidrometroActionForm
				.getMotivoNaoCobranca();
		String valorPercentual = efetuarRemanejamentoHidrometroActionForm
				.getPercentualCobranca();
		String qtdParcelas = efetuarRemanejamentoHidrometroActionForm
				.getQuantidadeParcelas();

		OrdemServico ordemServico = (OrdemServico) sessao
				.getAttribute("ordemServico");
		
		Imovel imovel = null;
		if (ordemServico.getRegistroAtendimento() != null && 
			ordemServico.getRegistroAtendimento().getImovel() != null) {
			
			imovel = ordemServico.getRegistroAtendimento().getImovel();
			
		} else if (ordemServico.getImovel() != null) {
			imovel = ordemServico.getImovel();
		}
		
		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = (HidrometroInstalacaoHistorico) sessao
				.getAttribute("hidrometroInstalacaoHistorico");

		HidrometroLocalInstalacao hidrometroLocalInstalacao = new HidrometroLocalInstalacao();
		hidrometroLocalInstalacao.setId(new Integer(localInstalacao));

		HidrometroProtecao hidrometroProtecao = new HidrometroProtecao();
		hidrometroProtecao.setId(new Integer(protecao));

		hidrometroInstalacaoHistorico
				.setHidrometroLocalInstalacao(hidrometroLocalInstalacao);
		hidrometroInstalacaoHistorico.setHidrometroProtecao(hidrometroProtecao);
		hidrometroInstalacaoHistorico.setUltimaAlteracao(new Date());
		hidrometroInstalacaoHistorico.setIndicadorExistenciaCavalete(new Short(
				cavalete));

		// regitrando operacao
		hidrometroInstalacaoHistorico.setOperacaoEfetuada(operacaoEfetuada);
		hidrometroInstalacaoHistorico.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		registradorOperacao.registrarOperacao(hidrometroInstalacaoHistorico);

		// if (ordemServico != null
		// && efetuarRemanejamentoHidrometroActionForm.getVeioEncerrarOS()
		// .equalsIgnoreCase("true")
		// && efetuarRemanejamentoHidrometroActionForm.getIdTipoDebito() !=
		// null) {
		//
		// ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;
		//
		// ordemServico.setIndicadorComercialAtualizado(new Short("1"));
		//
		// if (efetuarRemanejamentoHidrometroActionForm.getValorDebito() !=
		// null) {
		// ordemServico
		// .setValorAtual(new BigDecimal(
		// efetuarRemanejamentoHidrometroActionForm
		// .getValorDebito().toString().replace(
		// ",", ".")));
		// }
		//
		// if (idServicoMotivoNaoCobranca != null) {
		// servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
		// servicoNaoCobrancaMotivo.setId(new Integer(
		// idServicoMotivoNaoCobranca));
		// }
		// ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);
		//
		// if (valorPercentual != null) {
		// ordemServico.setPercentualCobranca(new BigDecimal(
		// efetuarRemanejamentoHidrometroActionForm
		// .getPercentualCobranca()));
		// }
		//
		// if (idServicoMotivoNaoCobranca != null) {
		// servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
		// servicoNaoCobrancaMotivo.setId(new Integer(
		// idServicoMotivoNaoCobranca));
		// }
		// ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);
		//
		// if (valorPercentual != null) {
		// ordemServico.setPercentualCobranca(new BigDecimal(
		// efetuarRemanejamentoHidrometroActionForm
		// .getPercentualCobranca()));
		// }
		//
		// BigDecimal valorAtual = new BigDecimal(0);
		// if (efetuarRemanejamentoHidrometroActionForm.getValorDebito() !=
		// null) {
		// valorAtual = new BigDecimal(
		// efetuarRemanejamentoHidrometroActionForm
		// .getValorDebito().toString().replace(",", "."));
		// }
		//
		// if (ordemServico.getServicoTipo().getDebitoTipo() != null
		// && (ordemServico.getServicoNaoCobrancaMotivo() == null ||
		// ordemServico
		// .getServicoNaoCobrancaMotivo().getId() ==
		// ConstantesSistema.NUMERO_NAO_INFORMADO)) {
		// fachada.gerarDebitoOrdemServico(ordemServico.getId(),
		// ordemServico.getServicoTipo().getDebitoTipo().getId(),
		// valorAtual, new Integer(
		// efetuarRemanejamentoHidrometroActionForm
		// .getQuantidadeParcelas()).intValue());
		// }
		// }

		BigDecimal valorAtual = new BigDecimal(0);

		if (ordemServico != null
				&& efetuarRemanejamentoHidrometroActionForm.getIdTipoDebito() != null) {

			ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;

			ordemServico.setIndicadorComercialAtualizado(ConstantesSistema.SIM);

			if (efetuarRemanejamentoHidrometroActionForm.getValorDebito() != null) {
				String valorDebito = efetuarRemanejamentoHidrometroActionForm
						.getValorDebito().toString().replace(".", "");

				valorDebito = valorDebito.replace(",", ".");

				valorAtual = new BigDecimal(valorDebito);

				ordemServico.setValorAtual(valorAtual);
			}

			if (idServicoMotivoNaoCobranca != null
					&& !idServicoMotivoNaoCobranca
							.equals("-1")) {
				servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
				servicoNaoCobrancaMotivo.setId(new Integer(
						idServicoMotivoNaoCobranca));
			}
			ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);

			if (valorPercentual != null) {
				ordemServico.setPercentualCobranca(new BigDecimal(
						efetuarRemanejamentoHidrometroActionForm
								.getPercentualCobranca()));
			}

			ordemServico.setUltimaAlteracao(new Date());
		}
		
		
		IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();
		
		integracaoComercialHelper.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);
		integracaoComercialHelper.setImovel(imovel);
		integracaoComercialHelper.setOrdemServico(ordemServico);
		integracaoComercialHelper.setQtdParcelas(qtdParcelas);
		
		if(efetuarRemanejamentoHidrometroActionForm.getVeioEncerrarOS().equalsIgnoreCase("FALSE")){
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);
			
			fachada.efetuarRemanejamentoHidrometro(integracaoComercialHelper);
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

		/*fachada.efetuarRemanejamentoHidrometro(hidrometroInstalacaoHistorico,
				ordemServico, efetuarRemanejamentoHidrometroActionForm
						.getVeioEncerrarOS(), qtdParcelas);*/

		if(retorno.getName().equalsIgnoreCase("telaSucesso")){
		montarPaginaSucesso(httpServletRequest,
				"Remanejamento do Hidrômetro no imóvel " + imovel.getId()
						+ " efetuada com sucesso",
				"Efetuar outro Remanejamento de hidrômetro",
				"exibirEfetuarRemanejamentoHidrometroAction.do?menu=sim");
		}

		return retorno;
	}
}
