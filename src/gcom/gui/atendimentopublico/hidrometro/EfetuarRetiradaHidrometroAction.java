package gcom.gui.atendimentopublico.hidrometro;

import gcom.atendimentopublico.bean.IntegracaoComercialHelper;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.HidrometroSituacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
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
 * Description of the Class
 * 
 * @author Thiago Tenório
 */
public class EfetuarRetiradaHidrometroAction extends GcomAction {

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

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		/*
		 * [UC0107] Registrar Transação
		 * 
		 */
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_RETIRADA_HIDROMETRO_EFETUAR,
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_RETIRADA_HIDROMETRO_EFETUAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		// [UC0107] -Fim- Registrar Transação

		EfetuarRetiradaHidrometroActionForm efetuarRetiradaHidrometroActionForm = 
			(EfetuarRetiradaHidrometroActionForm) actionForm;

		String idHidrometroLocalArmazenagem = null;
		String hidrometroExtraviado = (String) sessao.getAttribute("hidrometroExtravido");
		sessao.removeAttribute("hidrometroExtravido");
		
        // caso o hidrometro esteja extraviado, nao pega o local de armazenagem
		if(hidrometroExtraviado == null || !hidrometroExtraviado.equals("sim")){
		idHidrometroLocalArmazenagem = efetuarRetiradaHidrometroActionForm
				.getHidrometroLocalArmazenagem();
		}

		OrdemServico ordemServico = (OrdemServico) sessao
				.getAttribute("ordemServico");
		
		
		Imovel imovel = null;
		if (ordemServico.getRegistroAtendimento() != null &&
				ordemServico.getRegistroAtendimento().getImovel() != null ) {

			imovel = ordemServico.getRegistroAtendimento().getImovel();
		} else {
			imovel = ordemServico.getImovel();
		}
		

		HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico = (HidrometroInstalacaoHistorico) sessao
				.getAttribute("hidrometroInstalacaoHistorico");
		
		hidrometroInstalacaoHistorico.setUsuarioRetirada(usuario);

		Hidrometro hidrometro = hidrometroInstalacaoHistorico.getHidrometro();

		hidrometro = fachada.pesquisarHidrometroPeloId(hidrometro.getId());

		HidrometroLocalArmazenagem hidrometroLocalArmazenagem = null;
		if(idHidrometroLocalArmazenagem != null){
		hidrometroLocalArmazenagem = new HidrometroLocalArmazenagem();
		hidrometroLocalArmazenagem.setId(new Integer(
				idHidrometroLocalArmazenagem));
		}

		HidrometroSituacao hidrometroSituacao = new HidrometroSituacao();
		hidrometroSituacao.setId(new Integer(efetuarRetiradaHidrometroActionForm.getIdHidrometroSituacao()));
		hidrometro.setHidrometroSituacao(hidrometroSituacao);

		hidrometro.setHidrometroLocalArmazenagem(hidrometroLocalArmazenagem);
		hidrometro.setUltimaAlteracao(new Date());

		if(efetuarRetiradaHidrometroActionForm
				.getNumeroLeitura() != null && !efetuarRetiradaHidrometroActionForm
				.getNumeroLeitura().equals("")){
			hidrometroInstalacaoHistorico.setNumeroLeituraRetirada(new Integer(efetuarRetiradaHidrometroActionForm
					.getNumeroLeitura()));
		}
		hidrometroInstalacaoHistorico.setUltimaAlteracao(new Date());
		hidrometroInstalacaoHistorico.setHidrometro(hidrometro);
		if (efetuarRetiradaHidrometroActionForm.getDataRetirada() != null
				&& !efetuarRetiradaHidrometroActionForm.getDataRetirada()
						.equals("")) {
			hidrometroInstalacaoHistorico.setDataRetirada(Util
					.converteStringParaDate(efetuarRetiradaHidrometroActionForm
							.getDataRetirada()));
		}

		// regitrando operacao
		hidrometroInstalacaoHistorico.setOperacaoEfetuada(operacaoEfetuada);
		hidrometroInstalacaoHistorico.adicionarUsuario(usuario,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
		
		registradorOperacao.registrarOperacao(hidrometroInstalacaoHistorico);

		ServicoNaoCobrancaMotivo servicoNaoCobrancaMotivo = null;

		ordemServico.setIndicadorComercialAtualizado(new Short("1"));

		if (efetuarRetiradaHidrometroActionForm.getMotivoNaoCobranca() != null) {
			servicoNaoCobrancaMotivo = new ServicoNaoCobrancaMotivo();
			servicoNaoCobrancaMotivo.setId(new Integer(
					efetuarRetiradaHidrometroActionForm.getMotivoNaoCobranca()));
		}
		ordemServico.setServicoNaoCobrancaMotivo(servicoNaoCobrancaMotivo);

		if (efetuarRetiradaHidrometroActionForm.getPercentualCobranca() != null
				&& !efetuarRetiradaHidrometroActionForm.getPercentualCobranca().equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			ordemServico.setPercentualCobranca(new BigDecimal(
							efetuarRetiradaHidrometroActionForm
									.getPercentualCobranca()));
		}

		BigDecimal valorAtual = new BigDecimal(0);
		if (efetuarRetiradaHidrometroActionForm.getValorDebito() != null) {
			String valorDebito = efetuarRetiradaHidrometroActionForm
					.getValorDebito().toString().replace(".", "");

			valorDebito = valorDebito.replace(",", ".");

			valorAtual = new BigDecimal(valorDebito);

			ordemServico.setValorAtual(valorAtual);
		}

		String qtdParcelas = efetuarRetiradaHidrometroActionForm
				.getQuantidadeParcelas();

		IntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();

		integracaoComercialHelper
				.setHidrometroInstalacaoHistorico(hidrometroInstalacaoHistorico);
		integracaoComercialHelper.setImovel(imovel);
		integracaoComercialHelper.setOrdemServico(ordemServico);
		integracaoComercialHelper.setQtdParcelas(qtdParcelas);

		if (efetuarRetiradaHidrometroActionForm.getVeioEncerrarOS()
				.equalsIgnoreCase("FALSE")) {
			integracaoComercialHelper.setVeioEncerrarOS(Boolean.FALSE);

			fachada.efetuarRetiradaHidrometro(integracaoComercialHelper);
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

		// fachada.efetuarRetiradaHidrometro(hidrometroInstalacaoHistorico);
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(httpServletRequest,
					"Retirada do Hidrômetro para "
							+ efetuarRetiradaHidrometroActionForm.getMedicaoTipo() + " no imóvel "
							+ imovel.getId() + " efetuada com sucesso",
					"Efetuar outra Retirada de hidrômetro",
					"exibirEfetuarRetiradaHidrometroAction.do?menu=sim");
		}

		return retorno;
	}
	

}
