package gcom.gui.relatorio.faturamento.conta;

import gcom.api.GsanApi;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.faturamento.bean.ContaSegundaViaDTO;
import gcom.faturamento.bo.ContaSegundaViaBO;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.conta.FiltroConta;
import gcom.faturamento.conta.FiltroContaHistorico;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.seguranca.SegurancaParametro;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorio2ViaContaAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		SistemaParametro parametros = getFachada().pesquisarParametrosDoSistema();

		HttpSession sessao = request.getSession(false);

		Collection<Integer> idsConta = new ArrayList<Integer>();
		Integer idContaHistorico = null;

		if (sessao.getAttribute("idContaHistorico") != null && !sessao.getAttribute("idContaHistorico").equals("")) {
			idContaHistorico = new Integer("" + sessao.getAttribute("idContaHistorico"));
			idsConta.add(idContaHistorico);
		} else if (sessao.getAttribute("idConta") != null && !sessao.getAttribute("idConta").equals("")) {
			idsConta.add(new Integer("" + sessao.getAttribute("idConta")));
		} else if (request.getParameter("idConta") != null && !request.getParameter("idConta").equals("")) {
			idsConta.add(new Integer("" + request.getParameter("idConta")));
		} else if (sessao.getAttribute("idsContaEP") != null) {
			// Parcelamento
			idsConta = (Collection<Integer>) sessao.getAttribute("idsContaEP");
		} else if (sessao.getAttribute("colecaoImovel") != null) {
			// Manter Conjunto Conta
			Integer anoMes = 0;
			if (request.getParameter("mesAno") != null) {
				anoMes = Util.formatarMesAnoComBarraParaAnoMes(request.getParameter("mesAno"));
				sessao.setAttribute("anoMes", anoMes);
			} else {
				anoMes = new Integer("" + sessao.getAttribute("anoMes"));
				sessao.removeAttribute("anoMes");
			}

			Integer anoMesFim = anoMes;
			if (request.getParameter("mesAnoFim") != null) {
				anoMesFim = Util.formatarMesAnoComBarraParaAnoMes(request.getParameter("mesAnoFim"));
				sessao.setAttribute("anoMesFim", anoMesFim);
			} else if (sessao.getAttribute("anoMesFim") != null) {
				anoMesFim = new Integer("" + sessao.getAttribute("anoMesFim"));
				sessao.removeAttribute("anoMesFim");
			}

			Date dataVencimentoContaInicio = null;
			Date dataVencimentoContaFim = null;
			String indicadorContaPaga = null;
			Integer idGrupoFaturamento = null;

			String dataVencimentoContaInicioParam = request.getParameter("dataVencimentoContaInicial");
			if (dataVencimentoContaInicioParam != null && !dataVencimentoContaInicioParam.equals("")) {

				dataVencimentoContaInicio = Util.converteStringParaDate(request.getParameter("dataVencimentoContaInicial"));
				sessao.setAttribute("dataVencimentoContaInicial", dataVencimentoContaInicio);
			} else if (sessao.getAttribute("dataVencimentoContaInicial") != null) {
				dataVencimentoContaInicio = Util.converteStringParaDate("" + sessao.getAttribute("dataVencimentoContaInicial"));
				sessao.removeAttribute("dataVencimentoContaInicial");
			}

			String dataVencimentoContaFimParam = request.getParameter("dataVencimentoContaFinal");
			if (dataVencimentoContaFimParam != null && !dataVencimentoContaFimParam.equals("")) {

				dataVencimentoContaFim = Util.converteStringParaDate(request.getParameter("dataVencimentoContaFinal"));
				sessao.setAttribute("dataVencimentoContaFinal", dataVencimentoContaFim);
			} else if (sessao.getAttribute("dataVencimentoContaFinal") != null) {
				dataVencimentoContaFim = Util.converteStringParaDate("" + sessao.getAttribute("dataVencimentoContaFinal"));
				sessao.removeAttribute("dataVencimentoContaFinal");
			}

			if (request.getParameter("indicadorContaPaga") != null) {

				indicadorContaPaga = request.getParameter("indicadorContaPaga");
				sessao.setAttribute("indicadorContaPaga", indicadorContaPaga);
			} else if (sessao.getAttribute("indicadorContaPaga") != null) {
				indicadorContaPaga = (String) sessao.getAttribute("indicadorContaPaga");
				sessao.removeAttribute("indicadorContaPaga");
			}

			String idGrupoFaturamentoParam = request.getParameter("idGrupoFaturamento");
			if (idGrupoFaturamentoParam != null && !idGrupoFaturamentoParam.equals("")) {

				idGrupoFaturamento = new Integer((String) request.getParameter("idGrupoFaturamento"));
				sessao.setAttribute("idGrupoFaturamento", idGrupoFaturamento);
			} else if (sessao.getAttribute("idGrupoFaturamento") != null) {
				idGrupoFaturamento = (Integer) sessao.getAttribute("idGrupoFaturamento");
				sessao.removeAttribute("idGrupoFaturamento");
			}

			Collection colecaoImovel = (Collection) sessao.getAttribute("colecaoImovel");

			if (idGrupoFaturamento != null) {
				idsConta = getFachada().pesquisarConjuntoContaEmitir2Via(idGrupoFaturamento, anoMes, dataVencimentoContaInicio, dataVencimentoContaFim, anoMesFim);
			} else {

				idsConta = getFachada().pesquisarConjuntoContaEmitir2Via(colecaoImovel, anoMes, dataVencimentoContaInicio, dataVencimentoContaFim, anoMesFim, indicadorContaPaga);
			}

			if (parametros.getIndicadorBloqueioContasContratoParcelManterConta() != null && parametros.getIndicadorBloqueioContasContratoParcelManterConta().equals(ConstantesSistema.SIM)) {
				idsConta = getFachada().obterColecaoSemContasEmContratoParcelamentoIDs(idsConta);
			}

		} else {
			// a partir do Manter Conta
			String contaSelected = request.getParameter("conta");
			String[] arrayIdentificadores = contaSelected.split(",");

			for (int i = 0; i < arrayIdentificadores.length; i++) {
				String dadosConta = arrayIdentificadores[i];
				String[] idContaArray = dadosConta.split("-");
				Integer idConta = new Integer(idContaArray[0]);
				idsConta.add(idConta);

			}
		}

		if (idsConta != null && !isConjuntoContaRevisao(sessao)) {
			Collection colecaoContaDataRevisao = getFachada().pesquisarDataRevisaoConta(idsConta);

			if (sessao.getAttribute("idsContaEP") == null && colecaoContaDataRevisao != null && !colecaoContaDataRevisao.isEmpty()) {
				Iterator colecaoContaDataRevisaoIterator = colecaoContaDataRevisao.iterator();

				while (colecaoContaDataRevisaoIterator.hasNext()) {
					Conta conta = (Conta) colecaoContaDataRevisaoIterator.next();

					if (conta.getContaMotivoRevisao() != null && !conta.getContaMotivoRevisao().getId().equals(ContaMotivoRevisao.REVISAO_ENTRADA_DE_PARCELAMENTO)) {
						throw new ActionServletException("atencao.nao_permite_emitir_conta_em_revisao");
					}
				}
			}
		}

		Imovel imovel = null;
		String situacaoConta = "";

		Integer idConta = (Integer) idsConta.iterator().next();
		Conta conta = fitlrarConta(idConta);
		
		if (conta != null) {
			imovel = conta.getImovel();
			situacaoConta = conta.getDebitoCreditoSituacaoAtual().getDescricaoDebitoCreditoSituacao();
		} else {
			ContaHistorico contaHistorico = filtrarContaHistorico(idConta);
			imovel = contaHistorico.getImovel();
			situacaoConta = contaHistorico.getDebitoCreditoSituacaoAtual().getDescricaoDebitoCreditoSituacao();
		}

		boolean cobrarTaxaEmissao = isCobrarTaxaEmissao(request, sessao, imovel);
		verificarSituacaoEspecialCobranca(imovel);
		getFachada().verificarClienteSemDocumento(imovel.getId(), (Usuario) (request.getSession(false)).getAttribute("usuarioLogado"));

		try {
			ContaSegundaViaBO bo = new ContaSegundaViaBO(idContaHistorico, idsConta, cobrarTaxaEmissao, getContaSemCodigoBarras(request, sessao));
			ContaSegundaViaDTO dto = bo.criar(imovel, (Usuario) sessao.getAttribute("usuarioLogado"), situacaoConta);

			if (dto != null) {
				String url = getFachada().getSegurancaParametro(SegurancaParametro.NOME_PARAMETRO_SEGURANCA.URL_SEGUNDA_VIA.toString());

				GsanApi api = new GsanApi(url);
				api.invoke(dto);
				api.download(dto.getNomeArquivo(), response);
			} else {
				throw new ActionServletException("atencao.conta_segunda_via_sem_dados");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ActionServletException("atencao.erro_baixar_conta_segunda_via");
		}

		return null;
	}

	private boolean isConjuntoContaRevisao(HttpSession sessao) {
		boolean conjuntoContaRevisao = false;
		if (sessao.getAttribute("contaRevisao") != null) {
			conjuntoContaRevisao = true;
		}
		return conjuntoContaRevisao;
	}

	private boolean verificarIndicadorGerarDebito(boolean cobrarTaxaEmissao, Imovel imovel) {
		if (imovel.getImovelPerfil() != null && imovel.getImovelPerfil().getIndicadorGeraDebitoSegundaViaConta() != null && cobrarTaxaEmissao) {
			if (imovel.getImovelPerfil().getIndicadorGeraDebitoSegundaViaConta().compareTo(ConstantesSistema.NAO) == 0) {
				cobrarTaxaEmissao = false;
			}
		}
		return cobrarTaxaEmissao;
	}

	private Short getContaSemCodigoBarras(HttpServletRequest request, HttpSession sessao) {
		Short contaSemCodigoBarras = ConstantesSistema.NAO;
		if (request.getParameter("contaSemCodigoBarras") != null && request.getParameter("contaSemCodigoBarras").equals("1")) {
			contaSemCodigoBarras = ConstantesSistema.SIM;
			sessao.setAttribute("contaSemCodigoBarras", contaSemCodigoBarras);
		} else if (sessao.getAttribute("contaSemCodigoBarras") != null) {
			contaSemCodigoBarras = (Short) sessao.getAttribute("contaSemCodigoBarras");
		}
		return contaSemCodigoBarras;
	}

	private boolean isCobrarTaxaEmissao(HttpServletRequest request, HttpSession sessao, Imovel imovel) {
		boolean cobrarTaxaEmissao = true;
		
		if (request.getParameter("cobrarTaxaEmissaoConta") != null && request.getParameter("cobrarTaxaEmissaoConta").equals("N")) {
			cobrarTaxaEmissao = false;
			sessao.setAttribute("cobrarTaxaEmissaoConta", cobrarTaxaEmissao);
		} else if (sessao.getAttribute("cobrarTaxaEmissaoConta") != null) {
			cobrarTaxaEmissao = (Boolean) sessao.getAttribute("cobrarTaxaEmissaoConta");
		}
		
		return verificarIndicadorGerarDebito(cobrarTaxaEmissao, imovel);
	}

	@SuppressWarnings("rawtypes")
	private ContaHistorico filtrarContaHistorico(Integer idConta) {
		Filtro filtro = new FiltroContaHistorico();
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroContaHistorico.IMOVEL);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroContaHistorico.IMOVEL_PERFIL);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroContaHistorico.IMOVEL_COBRANCA_SITUACAO_TIPO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroContaHistorico.IMOVEL_LIGACAO_AGUA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroContaHistorico.HIDROMETRO_INSTALACAO_HISTORICO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL);
		filtro.adicionarParametro(new ParametroSimples(FiltroContaHistorico.ID, idConta));

		Collection colecao = getFachada().pesquisar(filtro, ContaHistorico.class.getName());

		return !colecao.isEmpty() ? (ContaHistorico) colecao.iterator().next() : null;
	}

	@SuppressWarnings("rawtypes")
	private Conta fitlrarConta(Integer id) {
		Filtro filtro = new FiltroConta();
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.IMOVEL);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.IMOVEL_PERFIL);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.IMOVEL_COBRANCA_SITUACAO_TIPO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.IMOVEL_LIGACAO_AGUA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.HIDROMETRO_INSTALACAO_HISTORICO);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroConta.DEBITO_CREDITO_SITUACAO_ATUAL);
		filtro.adicionarParametro(new ParametroSimples(FiltroConta.ID, id));

		Collection colecao = getFachada().pesquisar(filtro, Conta.class.getName());
		
		return !colecao.isEmpty() ? (Conta) colecao.iterator().next() : null;
	}

	private void verificarSituacaoEspecialCobranca(Imovel imovel) {
		if (imovel != null && imovel.getCobrancaSituacaoTipo() != null) {
			if (imovel.getCobrancaSituacaoTipo().getIndicadorEmitirDocumentoCobranca().equals(ConstantesSistema.NAO)) {
				throw new ActionServletException("atencao.segundavianaoemitido_imovel_situacaoespecial");
			}
		}
	}
}
