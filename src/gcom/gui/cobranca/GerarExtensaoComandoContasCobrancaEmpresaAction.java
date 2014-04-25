package gcom.gui.cobranca;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cobranca.ComandoEmpresaCobrancaConta;
import gcom.cobranca.ComandoEmpresaCobrancaContaExtensao;
import gcom.cobranca.GerarExtensaoComandoContasCobrancaEmpresaHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0879] Gerar Arquivo Texto das Contas em Cobrança por Empresa
 * 
 * @author Rômulo Aurélio
 * @since 02/01/2009
 */

public class GerarExtensaoComandoContasCobrancaEmpresaAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		GerarExtensaoComandoContasCobrancaEmpresaActionForm form = (GerarExtensaoComandoContasCobrancaEmpresaActionForm) actionForm;
		// Obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		String[] idsRegistros = form.getIdRegistros();

		String idEmpresa = form.getIdEmpresa();

		Collection colecaoGerarExtensaoComandoContasCobrancaEmpresaHelper = (Collection) sessao
				.getAttribute("colecaoGerarExtensaoComandoContasCobrancaEmpresaHelper");

		Collection colecaoComandoEmpresaCobrancaContaExtensao = new ArrayList();

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		ComandoEmpresaCobrancaContaExtensao comandoEmpresaCobrancaContaExtensao = null;

		int count = 0;

		if (idEmpresa != null && !idEmpresa.trim().equals("")) {

			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.ID, idEmpresa));

			Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
					Empresa.class.getName());

			if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()) {
				throw new ActionServletException("atencao.empresa.inexistente ");

			} else {

				if (idsRegistros != null && idsRegistros.length != 0) {

					for (int i = 0; i < idsRegistros.length; i++) {

						comandoEmpresaCobrancaContaExtensao = new ComandoEmpresaCobrancaContaExtensao();

						ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta = new ComandoEmpresaCobrancaConta();

						Integer id = Integer.parseInt(idsRegistros[i]);

						// -----------------------------------------------
						Iterator it = colecaoGerarExtensaoComandoContasCobrancaEmpresaHelper
								.iterator();

						String dataInicial;
						String dataFinal;

						while (it.hasNext()) {
							GerarExtensaoComandoContasCobrancaEmpresaHelper helper = (GerarExtensaoComandoContasCobrancaEmpresaHelper) it
									.next();

							if (helper
									.getGerarArquivoTextoContasCobrancaEmpresaHelper()
									.getIdComandoEmpresaCobrancaConta()
									.toString().equals(id.toString())) {

								dataInicial = (String) httpServletRequest
										.getParameter("a"
												+ helper
														.getGerarArquivoTextoContasCobrancaEmpresaHelper()
														.getIdComandoEmpresaCobrancaConta()
														.toString());
								if (dataInicial == null
										|| dataInicial.equals("")) {
									throw new ActionServletException(
											"atencao.Informe_entidade", null,
											"Referência Inicial da extensão do comando");
								}

								Integer anoMesInicialReferencia = Util
										.formatarMesAnoComBarraParaAnoMes(dataInicial);
								
								if(Util.validarAnoMesSemBarra(anoMesInicialReferencia.toString())){
									throw new ActionServletException("atencao.invalid",null,"Referência");
								}

								dataFinal = (String) httpServletRequest
										.getParameter("b"
												+ helper
														.getGerarArquivoTextoContasCobrancaEmpresaHelper()
														.getIdComandoEmpresaCobrancaConta()
														.toString());

								if (dataFinal == null || dataFinal.equals("")) {

									throw new ActionServletException(
											"atencao.Informe_entidade", null,
											"Referência Final da extensão do comando");
								}

								Integer anoMesFinalReferencia = Util
										.formatarMesAnoComBarraParaAnoMes(dataFinal);

								if(Util.validarAnoMesSemBarra(anoMesInicialReferencia.toString())){
									throw new ActionServletException("atencao.invalid",null,"Referência");
								}
								
								
								if (anoMesInicialReferencia != null
										&& anoMesFinalReferencia != null
										&& Util.compararAnoMesReferencia(
												anoMesInicialReferencia,
												anoMesFinalReferencia, ">")) {
									throw new ActionServletException(
											"atencao.referencia_final_anterior_referencia_inicial");
								}
								comandoEmpresaCobrancaContaExtensao
										.setReferenciaContaInicial(anoMesInicialReferencia);

								comandoEmpresaCobrancaContaExtensao
										.setReferenciaContaFinal(anoMesFinalReferencia);

								// -----------------------------------------

								comandoEmpresaCobrancaConta.setId(id);

								comandoEmpresaCobrancaContaExtensao
										.setComandoEmpresaCobrancaConta(comandoEmpresaCobrancaConta);

								comandoEmpresaCobrancaConta
										.setEmpresa((Empresa) Util
												.retonarObjetoDeColecao(colecaoEmpresa));

								comandoEmpresaCobrancaContaExtensao
										.setUsuario(usuarioLogado);

								
								colecaoComandoEmpresaCobrancaContaExtensao
										.add(comandoEmpresaCobrancaContaExtensao);

								fachada
										.inserirExtensaoComandoContasCobrancaEmpresa(
												comandoEmpresaCobrancaContaExtensao,
												colecaoGerarExtensaoComandoContasCobrancaEmpresaHelper);
								count = count + 1;

							}
						}

					}

				}

			}
		} else {
			throw new ActionServletException("atencao.Informe_entidade", null,
					"Empresa");
		}

		// montando página de sucesso
		montarPaginaSucesso(httpServletRequest, "Foram geradas " + count
				+ " extensões de comandos de contas em cobrança com sucesso.",
				"Gerar outra Extensão de Comando de Contas por Empresa",
				"exibirGerarExtensaoComandoContasCobrancaEmpresaAction.do?menu=sim");

		return retorno;
	}

}
