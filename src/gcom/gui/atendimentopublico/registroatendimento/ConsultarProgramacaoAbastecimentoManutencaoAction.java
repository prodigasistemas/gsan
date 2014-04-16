package gcom.gui.atendimentopublico.registroatendimento;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Collection;
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
 * @date 21/08/2006
 */
public class ConsultarProgramacaoAbastecimentoManutencaoAction extends
		GcomAction {
	/**
	 * Este caso de uso permite a programaçao de abastecimento e manutencao de
	 * uma determinada área de bairro
	 * 
	 * [UC0440] Consultar Programação de Abastecimento e Manutenção
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 21/08/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirResultadoConsultarProgramacaoAbastecimentoManutencaoAction");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		ConsultarProgramacaoAbastecimentoManutencaoActionForm consultarProgramacaoAbastecimentoManutencaoActionForm = (ConsultarProgramacaoAbastecimentoManutencaoActionForm) actionForm;

		String idMunicipio = consultarProgramacaoAbastecimentoManutencaoActionForm
				.getMunicipio();

		sessao.setAttribute("idMunicipio", idMunicipio);

		String idBairro = consultarProgramacaoAbastecimentoManutencaoActionForm
				.getBairro();

		sessao.setAttribute("idBairro", idBairro);

		String areaBairro = consultarProgramacaoAbastecimentoManutencaoActionForm
				.getAreaBairro();

		sessao.setAttribute("areaBairro", areaBairro);

		String mesAnoReferencia = consultarProgramacaoAbastecimentoManutencaoActionForm
				.getMesAnoReferencia();

		Collection colecaoProgramacaoAbastecimento = fachada
				.consultarProgramacaoAbastecimento(idMunicipio, idBairro,
						areaBairro, mesAnoReferencia);

		// Seleciona todas as programações de abastecimento de acordo da área de
		// bairro e o mês e o ano de referencia informado. Ordenando de forma
		// crescente por Data de Inicio de Abastecimento e Hora de Inicio de
		// abastecimento
		Collection colecaoProgramacaoManutencao = fachada
				.consultarProgramacaoManutencao(idMunicipio, idBairro,
						areaBairro, mesAnoReferencia);

		if ((colecaoProgramacaoAbastecimento == null || colecaoProgramacaoAbastecimento
				.isEmpty())
				&& ((colecaoProgramacaoManutencao == null || colecaoProgramacaoManutencao
						.isEmpty()))) {
			throw new ActionServletException(
					"atencao.abastecimento_manutencao_sem_registro");

		}

		// retorna ano e mes

		String ano = null;

		String mes = null;

		if (mesAnoReferencia == null || mesAnoReferencia.equals("")) {

			Date dataCorrente = new Date();
			String dataCorrenteTexto = Util.formatarData(dataCorrente);
			ano = dataCorrenteTexto.substring(6, 10);
			mes = dataCorrenteTexto.substring(3, 5);
			sessao.setAttribute("ano", ano);
			sessao.setAttribute("mes", mes);

		} else {

			boolean mesAnoValido = Util.validarMesAno(mesAnoReferencia);
			if (mesAnoValido == false) {
				throw new ActionServletException(
						"atencao.ano_mes_referencia.invalida");
			}

			mes = mesAnoReferencia.substring(0, 2);
			ano = mesAnoReferencia.substring(3, 7);
			sessao.setAttribute("ano", ano);
			sessao.setAttribute("mes", mes);
		}

		return retorno;
	}
}
