package gcom.gui.faturamento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.faturamento.QualidadeAgua;
import gcom.faturamento.QualidadeAguaPadrao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0596] Inserir Qualidade de Agua
 * 
 * @author Kássia Albuquerque, Rômulo Aurélio [Alteracao para Aba]
 * @date 24/07/2007, 16/09/2008
 */

public class InserirQualidadeAguaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirQualidadeAguaActionForm form = (InserirQualidadeAguaActionForm) actionForm;

		HttpSession sessao = this.getSessao(httpServletRequest);
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		QualidadeAgua qualidadeAgua = new QualidadeAgua();
		QualidadeAguaPadrao qualidadeAguaPadrao = new QualidadeAguaPadrao();

		Collection colecaoQualidadeAgua = (Collection) sessao
				.getAttribute("colecaoQualidadeAgua");

		// 1ª Aba
		this.montarDadosQualidadeAgua(form, colecaoQualidadeAgua,
				qualidadeAgua, qualidadeAguaPadrao, httpServletRequest, sessao);

		// Atualiza na base de dados da Qualidade de Agua
		this.getFachada().inserirQualidadeAgua(qualidadeAgua,
				colecaoQualidadeAgua, usuarioLogado, qualidadeAguaPadrao);

		if (colecaoQualidadeAgua != null && !colecaoQualidadeAgua.isEmpty()) {
			// Monta a página de sucesso
			montarPaginaSucesso(httpServletRequest,
					"Qualidade da água com referência  " + form.getReferencia()
							+ " inserida com sucesso.",
					"Inserir outra Qualidade da água",
					"exibirInserirQualidadeAguaAction.do?menu=sim", null, null);

		} else {
			// Monta a página de sucesso
			montarPaginaSucesso(httpServletRequest,
					"Qualidade da água com referência  "
							+ Util.formatarAnoMesParaMesAno(qualidadeAgua
									.getAnoMesReferencia())
							+ " inserida com sucesso.",
					"Inserir outra Qualidade da água",
					"exibirInserirQualidadeAguaAction.do?menu=sim",
					"exibirAtualizarQualidadeAguaAction.do?idRegistroAtualizacao="
							+ qualidadeAgua.getId()+"&manter=sim",
					"Atualizar Qualidade da água inserida");
		}

		return retorno;

	}

	private void montarDadosQualidadeAgua(InserirQualidadeAguaActionForm form,
			Collection colecaoQualidadeAgua, QualidadeAgua qualidadeAgua,
			QualidadeAguaPadrao qualidadeAguaPadrao,
			HttpServletRequest httpServletRequest, HttpSession sessao) {

		if ((form.getReferencia() != null && !form.getReferencia().equals(""))) {

			String anoMesReferencia = Util.formatarMesAnoParaAnoMesSemBarra(form.getReferencia());
			
			SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
			String anoMesFuturo = 
				""+Util.somaUmMesAnoMesReferencia(sistemaParametro.getAnoMesFaturamento());
			
			if (Util.compararAnoMesReferencia(anoMesReferencia,anoMesFuturo,">")){
				httpServletRequest.setAttribute("nomeCampo", "referencia");	
				throw new ActionServletException("atencao.mes_ano_menor");
			}
		}

		if (colecaoQualidadeAgua == null || colecaoQualidadeAgua.isEmpty()) {

			// Seta todos os campos que foram preenchidos no formulario
			form.setDadosQualidadeAgua(qualidadeAgua);

			// [FS0004 - VERIFICAR PREENCHIMENTO DOS CAMPOS]
			// [FS0008 - VERIFICAR PADRAO CORRESPONDENTE]
			boolean peloMenosUmParametroInformado = false;

			// Turbidez
			if (qualidadeAgua.getNumeroIndiceTurbidez() != null
					&& !qualidadeAgua.getNumeroIndiceTurbidez().equals("")) {

				peloMenosUmParametroInformado = true;

				if (form.getPadraoTurbidez() == null
						|| form.getPadraoTurbidez().equals("")) {
					throw new ActionServletException(
							"atencao.parametro_sem_correspondente");
				}
			}

			// Cloro Residual
			if (qualidadeAgua.getNumeroCloroResidual() != null
					&& !qualidadeAgua.getNumeroCloroResidual().equals("")) {

				peloMenosUmParametroInformado = true;
				if (form.getPadraoCloroResidual() == null
						|| form.getPadraoCloroResidual().equals("")) {
					throw new ActionServletException(
							"atencao.parametro_sem_correspondente");
				}
			}

			// Indice Mensal PH
			if (qualidadeAgua.getNumeroIndicePh() != null
					&& !qualidadeAgua.getNumeroIndicePh().equals("")) {

				peloMenosUmParametroInformado = true;

				if (form.getPadraoPH() == null || form.getPadraoPH().equals("")) {
					throw new ActionServletException(
							"atencao.parametro_sem_correspondente");
				}
			}

			// Indice Mensal Cor
			if (qualidadeAgua.getNumeroIndiceCor() != null
					&& !qualidadeAgua.getNumeroIndiceCor().equals("")) {

				peloMenosUmParametroInformado = true;
				if (form.getPadraoCor() == null
						|| form.getPadraoCor().equals("")) {
					throw new ActionServletException(
							"atencao.parametro_sem_correspondente");
				}
			}

			// Indice Mensal Fluor
			if (qualidadeAgua.getNumeroIndiceFluor() != null
					&& !qualidadeAgua.getNumeroIndiceFluor().equals("")) {

				peloMenosUmParametroInformado = true;
				if (form.getPadraoFluor() == null
						|| form.getPadraoFluor().equals("")) {
					throw new ActionServletException(
							"atencao.parametro_sem_correspondente");
				}
			}

			// Indice Mensal Ferro
			if (qualidadeAgua.getNumeroIndiceFerro() != null
					&& !qualidadeAgua.getNumeroIndiceFerro().equals("")) {

				peloMenosUmParametroInformado = true;
				if (form.getPadraoFerro() == null
						|| form.getPadraoFerro().equals("")) {
					throw new ActionServletException(
							"atencao.parametro_sem_correspondente");
				}
			}

			// Indice Mensal Coliformes Totais
			if (qualidadeAgua.getNumeroIndiceColiformesTotais() != null
					&& !qualidadeAgua.getNumeroIndiceColiformesTotais().equals(
							"")) {

				peloMenosUmParametroInformado = true;
				if (form.getPadraoColiformesTotais() == null
						|| form.getPadraoColiformesTotais().equals("")) {
					throw new ActionServletException(
							"atencao.parametro_sem_correspondente");
				}
			}

			// Indice Mensal Coliformes Fecais
			if (qualidadeAgua.getNumeroIndiceColiformesFecais() != null
					&& !qualidadeAgua.getNumeroIndiceColiformesFecais().equals(
							"")) {

				peloMenosUmParametroInformado = true;
				if (form.getPadraoColiformesFecais() == null
						|| form.getPadraoColiformesFecais().equals("")) {
					throw new ActionServletException(
							"atencao.parametro_sem_correspondente");
				}
			}

			// Indice Mensal Nitrato
			if (qualidadeAgua.getNumeroNitrato() != null
					&& !qualidadeAgua.getNumeroNitrato().equals("")) {

				peloMenosUmParametroInformado = true;
				if (form.getPadraoNitrato() == null
						|| form.getPadraoNitrato().equals("")) {
					throw new ActionServletException(
							"atencao.parametro_sem_correspondente");
				}
			}

			// Indice Mensal Coliformes Termotolerantes
			if (qualidadeAgua.getNumeroIndiceColiformesTermotolerantes() != null
					&& !qualidadeAgua
							.getNumeroIndiceColiformesTermotolerantes().equals(
									"")) {

				peloMenosUmParametroInformado = true;
				if (form.getPadraoColiformesTermotolerantes() == null
						|| form.getPadraoColiformesTermotolerantes().equals("")) {
					throw new ActionServletException(
							"atencao.parametro_sem_correspondente");
				}
			}
			
			// Indice Mensal Alcalinidade
			if(qualidadeAgua.getNumeroIndiceAlcalinidade() != null
					&& !qualidadeAgua.getNumeroIndiceAlcalinidade().equals("")){
				
				peloMenosUmParametroInformado = true;
				if (form.getPadraoAlcalinidade() == null
						|| form.getPadraoAlcalinidade().equals("")){
					throw new ActionServletException("atencao.parametro_sem_correpondente");
				}
				
			}

			if (qualidadeAgua.getQuantidadeCloroAnalisadas() != null
					&& !qualidadeAgua.getQuantidadeCloroAnalisadas().equals("")) {

				peloMenosUmParametroInformado = true;

			}

			if (qualidadeAgua.getQuantidadeCloroConforme() != null
					&& !qualidadeAgua.getQuantidadeCloroConforme().equals("")) {

				peloMenosUmParametroInformado = true;

			}

			if (qualidadeAgua.getQuantidadeCloroExigidas() != null
					&& !qualidadeAgua.getQuantidadeCloroExigidas().equals("")) {

				peloMenosUmParametroInformado = true;

			}

			if (qualidadeAgua.getQuantidadeColiformesFecaisAnalisadas() != null
					&& !qualidadeAgua.getQuantidadeColiformesFecaisAnalisadas()
							.equals("")) {

				peloMenosUmParametroInformado = true;

			}

			if (qualidadeAgua.getQuantidadeColiformesFecaisConforme() != null
					&& !qualidadeAgua.getQuantidadeColiformesFecaisConforme()
							.equals("")) {

				peloMenosUmParametroInformado = true;

			}

			if (qualidadeAgua.getQuantidadeColiformesFecaisExigidas() != null
					&& !qualidadeAgua.getQuantidadeColiformesFecaisExigidas()
							.equals("")) {

				peloMenosUmParametroInformado = true;

			}

			if (qualidadeAgua
					.getQuantidadeColiformesTermotolerantesAnalisadas() != null
					&& !qualidadeAgua
							.getQuantidadeColiformesTermotolerantesAnalisadas()
							.equals("")) {

				peloMenosUmParametroInformado = true;

			}

			if (qualidadeAgua.getQuantidadeColiformesTermotolerantesConforme() != null
					&& !qualidadeAgua
							.getQuantidadeColiformesTermotolerantesConforme()
							.equals("")) {

				peloMenosUmParametroInformado = true;

			}

			if (qualidadeAgua.getQuantidadeColiformesTermotolerantesExigidas() != null
					&& !qualidadeAgua
							.getQuantidadeColiformesTermotolerantesExigidas()
							.equals("")) {

				peloMenosUmParametroInformado = true;

			}

			if (qualidadeAgua.getQuantidadeColiformesTotaisAnalisadas() != null
					&& !qualidadeAgua.getQuantidadeColiformesTotaisAnalisadas()
							.equals("")) {

				peloMenosUmParametroInformado = true;

			}

			if (qualidadeAgua.getQuantidadeColiformesTotaisConforme() != null
					&& !qualidadeAgua.getQuantidadeColiformesTotaisConforme()
							.equals("")) {

				peloMenosUmParametroInformado = true;

			}

			if (qualidadeAgua.getQuantidadeColiformesTotaisExigidas() != null
					&& !qualidadeAgua.getQuantidadeColiformesTotaisExigidas()
							.equals("")) {

				peloMenosUmParametroInformado = true;

			}

			if (qualidadeAgua.getQuantidadeCorAnalisadas() != null
					&& !qualidadeAgua.getQuantidadeCorAnalisadas().equals("")) {

				peloMenosUmParametroInformado = true;

			}

			if (qualidadeAgua.getQuantidadeCorConforme() != null
					&& !qualidadeAgua.getQuantidadeCorConforme().equals("")) {

				peloMenosUmParametroInformado = true;

			}

			if (qualidadeAgua.getQuantidadeCorExigidas() != null
					&& !qualidadeAgua.getQuantidadeCorExigidas().equals("")) {

				peloMenosUmParametroInformado = true;

			}

			if (qualidadeAgua.getQuantidadeFluorAnalisadas() != null
					&& !qualidadeAgua.getQuantidadeFluorAnalisadas().equals("")) {

				peloMenosUmParametroInformado = true;

			}

			if (qualidadeAgua.getQuantidadeFluorConforme() != null
					&& !qualidadeAgua.getQuantidadeFluorConforme().equals("")) {

				peloMenosUmParametroInformado = true;

			}
			if (qualidadeAgua.getQuantidadeFluorExigidas() != null
					&& !qualidadeAgua.getQuantidadeFluorExigidas().equals("")) {

				peloMenosUmParametroInformado = true;

			}

			if (qualidadeAgua.getQuantidadeTurbidezAnalisadas() != null
					&& !qualidadeAgua.getQuantidadeTurbidezAnalisadas().equals(
							"")) {

				peloMenosUmParametroInformado = true;

			}

			if (qualidadeAgua.getQuantidadeTurbidezConforme() != null
					&& !qualidadeAgua.getQuantidadeTurbidezConforme()
							.equals("")) {

				peloMenosUmParametroInformado = true;

			}

			if (qualidadeAgua.getQuantidadeTurbidezExigidas() != null
					&& !qualidadeAgua.getQuantidadeTurbidezExigidas()
							.equals("")) {

				peloMenosUmParametroInformado = true;

			}
			
			if (qualidadeAgua
					.getQuantidadeAlcalinidadeAnalisadas() != null
					&& !qualidadeAgua
							.getQuantidadeAlcalinidadeAnalisadas()
							.equals("")) {

				peloMenosUmParametroInformado = true;

			}

			if (qualidadeAgua.getQuantidadeAlcalinidadeConforme() != null
					&& !qualidadeAgua
							.getQuantidadeAlcalinidadeConforme()
							.equals("")) {

				peloMenosUmParametroInformado = true;

			}

			if (qualidadeAgua.getQuantidadeAlcalinidadeExigidas() != null
					&& !qualidadeAgua
							.getQuantidadeAlcalinidadeExigidas()
							.equals("")) {

				peloMenosUmParametroInformado = true;

			}

			if (!peloMenosUmParametroInformado) {
				throw new ActionServletException(
						"atencao.nenhum_parametro_informado");

			} else {

				qualidadeAguaPadrao.setId((Integer) sessao
						.getAttribute("qualidadeAguaPadraoId"));

				if (form.getPadraoTurbidez() != null
						&& !form.getPadraoTurbidez().equals("")) {
					qualidadeAguaPadrao.setDescricaoPadraoTurbidez(form
							.getPadraoTurbidez());
				}
				if (form.getPadraoCloroResidual() != null
						&& !form.getPadraoCloroResidual().equals("")) {
					qualidadeAguaPadrao.setDescricaoPadraoCloro(form
							.getPadraoCloroResidual());
				}
				if (form.getPadraoPH() != null
						&& !form.getPadraoPH().equals("")) {
					qualidadeAguaPadrao
							.setDescricaoPadraoPh(form.getPadraoPH());
				}
				if (form.getPadraoCor() != null
						&& !form.getPadraoCor().equals("")) {
					qualidadeAguaPadrao.setDescricaoPadraoCor(form
							.getPadraoCor());
				}
				if (form.getPadraoFluor() != null
						&& !form.getPadraoFluor().equals("")) {
					qualidadeAguaPadrao.setDescricaoPadraoFluor(form
							.getPadraoFluor());
				}
				if (form.getPadraoFerro() != null
						&& !form.getPadraoFerro().equals("")) {
					qualidadeAguaPadrao.setDescricaoPadraoFerro(form
							.getPadraoFerro());
				}
				if (form.getPadraoColiformesTotais() != null
						&& !form.getPadraoColiformesTotais().equals("")) {
					qualidadeAguaPadrao.setDescricaoPadraoColiformesTotais(form
							.getPadraoColiformesTotais());
				}
				if (form.getPadraoColiformesFecais() != null
						&& !form.getPadraoColiformesFecais().equals("")) {
					qualidadeAguaPadrao.setDescricaoPadraoColiformesFecais(form
							.getPadraoColiformesFecais());
				}
				if (form.getPadraoNitrato() != null
						&& !form.getPadraoNitrato().equals("")) {
					qualidadeAguaPadrao.setDescricaoNitrato(form
							.getPadraoNitrato());
				}
				if (form.getPadraoColiformesTermotolerantes() != null
						&& !form.getPadraoColiformesTermotolerantes()
								.equals("")) {
					qualidadeAguaPadrao
							.setDescricaoPadraoColiformesTermotolerantes(form
									.getPadraoColiformesTermotolerantes());
				}
				
				if (form.getPadraoAlcalinidade() != null
						&& !form.getPadraoAlcalinidade().equals("")){
					qualidadeAguaPadrao
					.setDescricaoPadraoAlcalinidade(form
							.getPadraoAlcalinidade());
				}

			}
		}

	}

}
