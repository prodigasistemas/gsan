package gcom.gui.cadastro.tarifasocial;

import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Prepara a página para a exibição de Inserir Tarifa Social
 * 
 * @author rodrigo
 */
public class ExibirInserirTarifaSocialImovelAction extends GcomAction {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("inserirTarifaSocialImovel");

		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		sessao.removeAttribute("colecaoTarifaSocialDadoEconomia");
		sessao.removeAttribute("colecaoDadosTarifaSocial");
		sessao.removeAttribute("colecaoImovelEconomiaAtualizados");
		sessao.setAttribute("inserirTarifaSocial", true);

		// Pega uma instancia do actionform
		DynaValidatorForm inserirTarifaSocialActionForm = (DynaValidatorForm) actionForm;

		Fachada fachada = Fachada.getInstancia();		
		
		String idImovel = null;

		String idRegistroAtendimento = (String)inserirTarifaSocialActionForm.get("registroAtendimento");

		// pesquisa o imovel pelo registro de atendimento
		if (idRegistroAtendimento != null
				&& !idRegistroAtendimento.trim().equals("")) {
			
			
			RegistroAtendimento registroAtendimento = fachada.verificarRegistroAtendimentoTarifaSocial(idRegistroAtendimento);
  		    if (registroAtendimento != null) {

				// Registro de Atendimento não está associado a um imóvel
				if (registroAtendimento.getImovel() == null) {
					// FS0001 - Validar Registro de Atendimento
					throw new ActionServletException(
							"atencao.registro_atendimento.nao.associado.imovel");
				}

				// Registro de Atendimento está encerrado
				if (registroAtendimento.getAtendimentoMotivoEncerramento() != null) {
					// FS0001 - Validar Registro de Atendimento
					throw new ActionServletException(
							"atencao.registro_atendimento.esta.encerrado");
				}

				// Tipo de Solicitação do registro de atendimento não permite a inclusão na tarifa social
				if (registroAtendimento.getSolicitacaoTipoEspecificacao().getSolicitacaoTipo()
						.getIndicadorTarifaSocial() == 2) {
					// FS0001 - Validar Registro de Atendimento
					throw new ActionServletException(
							"atencao.registro_atendimento.nao.permite.inclusao.tarifa_social");
				}

				// caso tenha o imovel
				idImovel = registroAtendimento.getImovel().getId().toString();
				
				inserirTarifaSocialActionForm.set("registroAtendimento",registroAtendimento.getId()
						.toString());
				inserirTarifaSocialActionForm.set("nomeRegistroAtendimento",registroAtendimento
						.getSolicitacaoTipoEspecificacao()
						.getDescricao());
				
				inserirTarifaSocialActionForm.set("idImovel", idImovel);
				
				sessao.setAttribute("ra", registroAtendimento);
				
				httpServletRequest.setAttribute("corRegistroAtendimento","valor");
				httpServletRequest.setAttribute("nomeCampo", "registroAtendimento");

			} else {
				
				inserirTarifaSocialActionForm.set("idImovel","");
				
				sessao.removeAttribute("clienteImovel");
				sessao.removeAttribute("quantEconomias");				
				
				// FS0001-Validar Registro de Atendimento
				inserirTarifaSocialActionForm.set("registroAtendimento", "");
				inserirTarifaSocialActionForm.set("nomeRegistroAtendimento","RA inexistente");
				httpServletRequest.setAttribute("corRegistroAtendimento",
						"exception");
				httpServletRequest.setAttribute("nomeCampo",
						"registroAtendimento");
			}
		}

		// Verifica se foi feita uma pesquisa de imovel que retornou para este
		// exibir
		if (idImovel != null  && !idImovel.equals("")) {

			// [FS0002] - Verificar Existência de RA
			Collection clientesImoveis = fachada.pesquisarClienteImovelPeloImovelParaEndereco(
					new Integer(idImovel));

			if (!clientesImoveis.isEmpty()) {
				ClienteImovel clienteImovel = (ClienteImovel) ((List) clientesImoveis)
						.get(0);
				
				Imovel imovel = clienteImovel.getImovel();

				if (imovel.getImovelPerfil().getId().intValue() == ImovelPerfil.TARIFA_SOCIAL.intValue()){
					// FS0002 - Verificar imóvel na tarifa social
					throw new ActionServletException(
							"atencao.imovel.associado.registro_atendimento.ja.tarifa_social",null,imovel.getId().toString());
				}
				
				// Obter a quantidade de economias do imóvel escolhido
				int quantEconomias = Fachada.getInstancia()
						.obterQuantidadeEconomias(imovel);

				// Seta no request
				sessao.setAttribute("clienteImovel", clienteImovel);
				sessao.setAttribute("quantEconomias", String
						.valueOf(quantEconomias));
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Matrícula do imóvel " + idImovel);
			}
		}

		return retorno;
	}

}
