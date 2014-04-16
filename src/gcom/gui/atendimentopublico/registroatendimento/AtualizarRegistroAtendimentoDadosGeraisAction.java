package gcom.gui.atendimentopublico.registroatendimento;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

/**
 * Esta classe tem por finalidade validar as informações da primeira aba do
 * processo de inserção de um registro de atendimento
 * 
 * @author Sávio Luiz
 * @date 10/08/2006
 */
public class AtualizarRegistroAtendimentoDadosGeraisAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("");

		AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm = (AtualizarRegistroAtendimentoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
		filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.ID, atualizarRegistroAtendimentoActionForm.getEspecificacao()));
		filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.INDICADOR_ENCERRAMENTO_AUTOMATICO, SolicitacaoTipoEspecificacao.INDICADOR_COM_ENCERRAMENTO_AUTOMATICO));
		
		Collection colecaoSolicitacaoTipoEspecificacao = fachada
				.pesquisar(filtroSolicitacaoTipoEspecificacao,
						SolicitacaoTipoEspecificacao.class.getName());
		
		// Caso a especificação seja de encerramento automático, verifica se a observação foi informada
		if (colecaoSolicitacaoTipoEspecificacao != null && !colecaoSolicitacaoTipoEspecificacao.isEmpty()) {
			if (atualizarRegistroAtendimentoActionForm.getObservacao() == null || atualizarRegistroAtendimentoActionForm.getObservacao().trim().equals("")) {
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Observação");
			}
		}
		
		if(atualizarRegistroAtendimentoActionForm.getObservacao() != null && 
			!atualizarRegistroAtendimentoActionForm.getObservacao().equals("") && 
			atualizarRegistroAtendimentoActionForm.getObservacao().length() > 400){
				
			String[] msg = new String[2];
			msg[0]="Observação";
			msg[1]="400";
				
			throw new ActionServletException("atencao.execedeu_limit_observacao",null,msg);
		}		

		fachada.validarInserirRegistroAtendimentoDadosGerais(
				atualizarRegistroAtendimentoActionForm.getDataAtendimento(),
				atualizarRegistroAtendimentoActionForm.getHora(),
				atualizarRegistroAtendimentoActionForm.getTempoEsperaInicial(),
				atualizarRegistroAtendimentoActionForm.getTempoEsperaFinal(),
				atualizarRegistroAtendimentoActionForm.getUnidade(),
				null);

		String idUnidade = atualizarRegistroAtendimentoActionForm.getUnidade();
		String descricaoUnidade = atualizarRegistroAtendimentoActionForm
				.getDescricaoUnidade();

		if (idUnidade != null && !idUnidade.equalsIgnoreCase("")
				&& (descricaoUnidade == null || descricaoUnidade.equals(""))) {

			UnidadeOrganizacional unidadeOrganizacionalSelecionada = fachada
					.verificarAutorizacaoUnidadeAberturaRA(new Integer(
							idUnidade), false);

			if (unidadeOrganizacionalSelecionada != null) {
				atualizarRegistroAtendimentoActionForm
						.setUnidade(unidadeOrganizacionalSelecionada.getId()
								.toString());
				atualizarRegistroAtendimentoActionForm
						.setDescricaoUnidade(unidadeOrganizacionalSelecionada
								.getDescricao());

				if (unidadeOrganizacionalSelecionada.getMeioSolicitacao() != null) {

					atualizarRegistroAtendimentoActionForm
							.setMeioSolicitacao(unidadeOrganizacionalSelecionada
									.getMeioSolicitacao().getId().toString());
				}

			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Unidade de Atendimento");
			}

		}

		return retorno;
	}

}
