package gcom.gui.micromedicao.leitura;

import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraFiscalizacao;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.seguranca.acesso.usuario.Usuario;
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
 * @author Rômulo Aurélio
 *
 */
public class InformarLeituraFiscalizacaoAction extends GcomAction {

	/**
	 * Este caso de uso permite informar ou corrigir leitura de fiscalização
	 * 
	 * [UC0100] Informar Leitura de Fiscalização
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 16/05/2007
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

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		HttpSession sessao = httpServletRequest.getSession(false);

		InformarLeituraFiscalizacaoActionForm form = (InformarLeituraFiscalizacaoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		Usuario usuarioLogado = (Usuario) sessao
				.getAttribute(Usuario.USUARIO_LOGADO);

		String matricula = (String) sessao.getAttribute("matricula");

		String dataLeituraFiscalizacao = form.getDataLeituraFiscalizacao();

		String matriculaLeituristaFiscalizacao = form
				.getMatriculaLeituristaFiscalizacao();

		LeituraFiscalizacao leituraFiscalizacao = null;

		if (sessao.getAttribute("leituraFiscalizacao") != null) {

			leituraFiscalizacao = (LeituraFiscalizacao) sessao
					.getAttribute("leituraFiscalizacao");

		} else {

			leituraFiscalizacao = new LeituraFiscalizacao();
		}

		/**
		 * [FS0003]-Verificar existencia da matrícula do funcionario
		 */
		FiltroFuncionario filtroFuncionario = new FiltroFuncionario();

		filtroFuncionario.adicionarParametro(new ParametroSimples(
				FiltroFuncionario.ID, matriculaLeituristaFiscalizacao));

		Collection colecaoFuncionario = fachada.pesquisar(filtroFuncionario,
				Funcionario.class.getName());

		if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {

			Funcionario funcionario = (Funcionario) colecaoFuncionario
					.iterator().next();

			funcionario.setId(new Integer(matriculaLeituristaFiscalizacao));

			leituraFiscalizacao.setFuncionario(funcionario);

		} else {

			throw new ActionServletException("atencao.funcionario.inexistente");
		}

		/**
		 * [FS0005]-Validar Quantidade de Dígitos da Leitura
		 */
		Short numeroDigitosLeitura = (Short) sessao
				.getAttribute("numeroDigitosLeitura");

		if (form.getLeituraFiscalizacao().length() > numeroDigitosLeitura) {
			throw new ActionServletException("atencao.quantidade_digitos_leitura_invalido");
		}

		/**
		 * [FS0004]-Validar Data de Leitura
		 */
		String mesAnoReferencia = form.getMesAnoReferencia();

		String mesReferencia = mesAnoReferencia.substring(0, 2);

		String anoReferencia = mesAnoReferencia.substring(3, 7);

		String anoMesReferencia = anoReferencia + mesReferencia;

		Integer anoMesReferenciaInt = new Integer(anoMesReferencia);

		String mes = dataLeituraFiscalizacao.substring(3, 5);

		String ano = dataLeituraFiscalizacao.substring(6, 10);

		String anoMes = ano + mes;

		Integer anoMesInteiro = new Integer(anoMes);

		if (!((Util.compararAnoMesReferencia(anoMesReferenciaInt,
				anoMesInteiro, "=")) || (Util.compararAnoMesReferencia(
				anoMesReferenciaInt + 1, anoMesInteiro, "=")))) {

			throw new ActionServletException(
					"atencao.data_leitura_incompativel_ciclo_faturamento");

		}

		leituraFiscalizacao.setdataLeituraEmpresa(Util
				.converteStringParaDate(dataLeituraFiscalizacao));

		LeituraAnormalidade leituraAnormalidade = new LeituraAnormalidade();

		if(!form
				.getAnormalidadeFiscalizacao().equalsIgnoreCase("-1")){
		leituraAnormalidade.setId(new Integer(form
				.getAnormalidadeFiscalizacao()));
		}else{
			leituraAnormalidade.setId(new Integer(0));
		}
		leituraFiscalizacao.setLeituraAnormalidade(leituraAnormalidade);

		MedicaoHistorico medicaoHistorico = (MedicaoHistorico) sessao
				.getAttribute("medicaoHistorico");

		leituraFiscalizacao.setMedicaoHistorico(medicaoHistorico);

		leituraFiscalizacao.setId(medicaoHistorico.getId());

		if (!form.getLeituraFiscalizacao().equalsIgnoreCase("")) {

			Integer numeroLeituraEmpresa = new Integer(form
					.getLeituraFiscalizacao());

			leituraFiscalizacao.setNumeroLeituraEmpresa(numeroLeituraEmpresa
					.intValue());

		}

		fachada.informarLeituraFiscalizacao(usuarioLogado, leituraFiscalizacao);

		montarPaginaSucesso(httpServletRequest,
				"Leitura de Fiscalização para o imóvel " + matricula
						+ " informada com sucesso.",
				"Informar outra Fiscalização",
				"exibirFiltrarInformarLeituraFiscalizacaoAction.do?menu=sim");

		return retorno;
	}

}
