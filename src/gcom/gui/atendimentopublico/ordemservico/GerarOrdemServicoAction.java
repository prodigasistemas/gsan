package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <<Descrição da Classe>>
 * 
 * @author lms
 * @date 22/08/2006
 */
public class GerarOrdemServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		GerarOrdemServicoActionForm form = (GerarOrdemServicoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		OrdemServico ordemServico = (OrdemServico) sessao
				.getAttribute("ordemServico");

		// Inicio Alterado por Sàvio Luiz

		if (form.getValorServicoOriginal() == null
				|| form.getValorServicoOriginal().equals("")) {
			Integer idRA = Integer.valueOf(form.getIdRegistroAtendimento());
			RegistroAtendimento ra = fachada.validarRegistroAtendimento(idRA);
			// Serviço Tipo
			ServicoTipo servicoTipo = null;

			Integer idServicoTipo = Util.converterStringParaInteger(form
					.getIdServicoTipo());
			String descricaoServicoTipo = null;
			String valorServicoOriginal = null;
			Integer idServicoTipoPrioridadeOriginal = null;
			String descricaoServicoTipoPrioridadeOriginal = null;

			if (Util.validarNumeroMaiorQueZERO(idServicoTipo)) {
				servicoTipo = fachada.pesquisarSevicoTipo(idServicoTipo);

				fachada.validarServicoTipo(ra.getId(), idServicoTipo);

				if (servicoTipo != null) {
					descricaoServicoTipo = servicoTipo.getDescricao();
					if (servicoTipo.getValor() != null) {
						String valorFormatado = servicoTipo.getValor()
								.toString().replace('.', ',');
						valorServicoOriginal = valorFormatado;
					}
					if (servicoTipo.getServicoTipoPrioridade() != null) {
						idServicoTipoPrioridadeOriginal = servicoTipo
								.getServicoTipoPrioridade().getId();
						descricaoServicoTipoPrioridadeOriginal = servicoTipo
								.getServicoTipoPrioridade().getDescricao();
					}
					httpServletRequest.setAttribute("idServicoTipoEncontrada",
							"true");
				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Tipo de Serviço");
				}
				form.getOrdemServico().setServicoTipo(servicoTipo);
			}

			form.setDescricaoServicoTipo(descricaoServicoTipo);
			form.setValorServicoOriginal(valorServicoOriginal);
			form.setIdPrioridadeServicoOriginal(idServicoTipoPrioridadeOriginal
					+ "");
			form
					.setDescricaoPrioridadeServicoOriginal(descricaoServicoTipoPrioridadeOriginal);
		}

		// Fim Alterado por Sàvio Luiz

		if (ordemServico != null) {
			ordemServico = form.setFormValues(form.getOrdemServico());
		} else {
			ordemServico = form.setFormValues(form.getOrdemServico());
		}
		
		if(ordemServico.getServicoTipo()!=null 
				&& ordemServico.getRegistroAtendimento() != null){
			fachada.validarServicoTipo(ordemServico.getRegistroAtendimento().getId(),ordemServico.getServicoTipo().getId());
		}
		
		String veioAcompanhamento = (String) sessao
				.getAttribute("veioAcompanhamento");
		
		String veioAcompanhamentoRoteiro = (String) sessao
				.getAttribute("veioAcompanhamentoRoteiro");
		
		if (veioAcompanhamentoRoteiro != null && !veioAcompanhamentoRoteiro.equals("")){
			if (ordemServico.getServicoTipo() != null &&
					ordemServico.getServicoTipo().getIndicadorProgramacaoAutomatica() != ConstantesSistema.SIM.shortValue()){
				throw new ActionServletException("atencao.servico_tipo.nao_compativel");
			}
		}
		
		// gera a ordem de serviço
		Integer idOrdemServico = fachada.gerarOrdemServico(ordemServico,
				usuario, null);

		sessao.removeAttribute("ordemServico");
		
		if (veioAcompanhamentoRoteiro != null && !veioAcompanhamentoRoteiro.equals("")){
			retorno = actionMapping
					.findForward("incluirOrdemServicoAcompanhamentoArqRoteiro");

			httpServletRequest.setAttribute("objetoConsulta", "");
			httpServletRequest.setAttribute("idOrdemServico", "" + idOrdemServico);
			httpServletRequest.setAttribute("descOrdemServico", "" + ordemServico.getServicoTipo().getDescricao());
			
			httpServletRequest.setAttribute("numeroRa", ordemServico.getRegistroAtendimento().getId());

		} else if (veioAcompanhamento != null) {

			retorno = actionMapping
					.findForward("incluirOrdemServicoAcompanharRoteiroProgramacao");

			httpServletRequest.setAttribute("objetoConsulta", "2");
			httpServletRequest.setAttribute("idOrdemServico", ""
					+ idOrdemServico);
		}else{

			String caminhoRetornoGerarOs = (String) sessao
					.getAttribute("caminhoRetornoGerarOs");

			if (caminhoRetornoGerarOs != null
					&& caminhoRetornoGerarOs
							.equals("exibirConsultarRegistroAtendimentoAction.do")) {

				String idRa = ordemServico.getRegistroAtendimento().getId()
						.toString();
				caminhoRetornoGerarOs = caminhoRetornoGerarOs + "?numeroRA="
						+ idRa;
			}

			String msg = "Geração da Ordem de Serviço " + ordemServico.getId()
					+ " para o registro de Atendimento número "
					+ ordemServico.getRegistroAtendimento().getId()
					+ " efetuada com sucesso.";

			if (caminhoRetornoGerarOs == null) {
				montarPaginaSucessoUmRelatorio(httpServletRequest, msg,"",
	                    "","exibirGerarOrdemServicoAction.do","Voltar",
						"Imprimir OS",
						"gerarRelatorioOrdemServicoAction.do?idsOS=" + idOrdemServico);

			} else {	
				montarPaginaSucessoUmRelatorio(httpServletRequest, msg,"",
	                    "",caminhoRetornoGerarOs,"Voltar",
						"Imprimir OS",
						"gerarRelatorioOrdemServicoAction.do?idsOS=" + idOrdemServico);
				
			}
		}
		return retorno;
	}

}
