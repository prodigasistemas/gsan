package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoAtivCronRota;
import gcom.faturamento.FaturamentoAtivCronRotaPK;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.faturamento.FiltroFaturamentoAtivCronRota;
import gcom.gui.GcomAction;
import gcom.micromedicao.Rota;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAtualizarComandoAtividadeFaturamentoDataVencimentoAction
		extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarComandoAtividadeFaturamentoDataVencimento");

		// Carrega a instancia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Carrega o objeto sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		// Instância do formulário que está sendo utilizado
		InserirComandoAtividadeFaturamentoActionForm inserirComandoAtividadeFaturamentoActionForm = (InserirComandoAtividadeFaturamentoActionForm) actionForm;

		Collection colecaoFaturamentoAtividadeCronogramaRota = new Vector();
		Collection colecaoRotasSelecionadasUsuario = new Vector();
		Collection colecaoFaturamentoAtividadeCronogramaRotaSelecionadasUsuario = new ArrayList();
		Collection colecaoFaturamentoAtividadeCronogramaRotaUniao = new ArrayList();

		// Faturamento Atividade Cronograma selecionado
		FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = (FaturamentoAtividadeCronograma) sessao
				.getAttribute("faturamentoAtividadeCronograma");

		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		// Calendar dataVencimentoGrupoBase = null;
		String dataVencimentoGrupoBaseComparar = null;
		String dataVencimentoGrupoJSP = null;

		Date dataVencimentoGrupoJSPObjeto = null;

		if (sessao.getAttribute("exibirCampoVencimentoGrupo") != null) {
			/*
			 * dataVencimentoGrupoBase = (Calendar) sessao
			 * .getAttribute("exibirCampoVencimentoGrupo");
			 */
			dataVencimentoGrupoBaseComparar = (String) sessao
					.getAttribute("exibirCampoVencimentoGrupo");

			dataVencimentoGrupoJSP = inserirComandoAtividadeFaturamentoActionForm
					.getVencimentoGrupo();

			try {
				dataVencimentoGrupoJSPObjeto = formatoData
						.parse(dataVencimentoGrupoJSP);
			} catch (ParseException ex) {
				dataVencimentoGrupoJSPObjeto = null;
			}

			httpServletRequest.setAttribute("dataVencimentoGrupoFormulario",
					dataVencimentoGrupoJSP);
		}

		// Gera uma coleção de rotas a partir da coleção
		// colecaoFaturamentoAtividadeCronogramaRota
		// [SB0004] Determinar Data de Vencimento para Rota
		// Rotas selecionadas no processo 01
		if (sessao.getAttribute("colecaoFaturamentoAtividadeCronogramaRota") != null) {
			colecaoFaturamentoAtividadeCronogramaRota = (Collection) sessao
					.getAttribute("colecaoFaturamentoAtividadeCronogramaRota");

			// Verifica se a data de vencimento do grupo foi alterada
			if (sessao.getAttribute("exibirCampoVencimentoGrupo") != null) {

				Iterator colecaoFaturamentoAtividadeCronogramaRotaIterator = colecaoFaturamentoAtividadeCronogramaRota
						.iterator();

				if (!dataVencimentoGrupoJSP
						.equalsIgnoreCase(dataVencimentoGrupoBaseComparar)) {

					// A data de vencimento da rota será a data de vencimento do
					// grupo
					while (colecaoFaturamentoAtividadeCronogramaRotaIterator
							.hasNext()) {
						FaturamentoAtivCronRota faturamentoAtividadeCronogramaRota = (FaturamentoAtivCronRota) colecaoFaturamentoAtividadeCronogramaRotaIterator
								.next();

						faturamentoAtividadeCronogramaRota
								.setDataContaVencimento(dataVencimentoGrupoJSPObjeto);
					}
				} else {
					FiltroFaturamentoAtivCronRota filtroFaturamentoAtivCronRota = new FiltroFaturamentoAtivCronRota();

					filtroFaturamentoAtivCronRota
							.setCampoOrderBy(
									FiltroFaturamentoAtivCronRota.FATURAMENTO_GRUPO_ID,
									FiltroFaturamentoAtivCronRota.GERENCIA_REGIONAL_NOME_ABREVIADO,
									FiltroFaturamentoAtivCronRota.LOCALIDADE_DESCRICAO,
									FiltroFaturamentoAtivCronRota.SETOR_COMERCIAL_CODIGO,
									FiltroFaturamentoAtivCronRota.COMP_ID_ROTA_ID);

					filtroFaturamentoAtivCronRota.adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoAtivCronRota.UNIDADE_NEGOCIO);
					
					Collection colecaoDataRota = null;
					FaturamentoAtivCronRota faturamentoAtivCronRota = new FaturamentoAtivCronRota();

					// A data de vencimento para a rota será a da rota grupo
					while (colecaoFaturamentoAtividadeCronogramaRotaIterator
							.hasNext()) {

						FaturamentoAtivCronRota faturamentoAtividadeCronogramaRota = (FaturamentoAtivCronRota) colecaoFaturamentoAtividadeCronogramaRotaIterator
								.next();

						filtroFaturamentoAtivCronRota
								.adicionarParametro(new ParametroSimples(
										FiltroFaturamentoAtivCronRota.COMP_ID_FATURAMENTO_ATIVIDADE_CRONOGRAMA_ID,
										faturamentoAtividadeCronogramaRota
												.getFaturamentoAtividadeCronograma()
												.getId()));

						filtroFaturamentoAtivCronRota
								.adicionarParametro(new ParametroSimples(
										FiltroFaturamentoAtivCronRota.COMP_ID_ROTA_ID,
										faturamentoAtividadeCronogramaRota
												.getRota().getId()));

						colecaoDataRota = fachada.pesquisar(
								filtroFaturamentoAtivCronRota,
								FaturamentoAtivCronRota.class.getName());

						faturamentoAtivCronRota = (FaturamentoAtivCronRota) Util
								.retonarObjetoDeColecao(colecaoDataRota);

						filtroFaturamentoAtivCronRota.limparListaParametros();

						if (faturamentoAtivCronRota.getDataContaVencimento() != null) {
							faturamentoAtividadeCronogramaRota
									.setDataContaVencimento(faturamentoAtivCronRota
											.getDataContaVencimento());
						} else {
							faturamentoAtividadeCronogramaRota
									.setDataContaVencimento(dataVencimentoGrupoJSPObjeto);
						}
					}
				}
			}
		}

		// Coleção de rotas selecionada pelo usuário
		if (sessao.getAttribute("colecaoRotasSelecionadasUsuario") != null) {
			colecaoRotasSelecionadasUsuario = (Collection) sessao
					.getAttribute("colecaoRotasSelecionadasUsuario");
			
			if (!colecaoRotasSelecionadasUsuario.isEmpty()) {
				Iterator colecaoRotasSelecionadasUsuarioIterator = colecaoRotasSelecionadasUsuario
						.iterator();
				
				Rota rota;
				FaturamentoAtivCronRotaPK faturamentoAtividadeCronogramaRotaPK;
				FaturamentoAtivCronRota faturamentoAtividadeCronogramaRota;

				while (colecaoRotasSelecionadasUsuarioIterator.hasNext()) {
					rota = (Rota) colecaoRotasSelecionadasUsuarioIterator.next();

					faturamentoAtividadeCronogramaRotaPK = new FaturamentoAtivCronRotaPK();

					faturamentoAtividadeCronogramaRotaPK
							.setFaturamentoAtividadeCronogramaId(faturamentoAtividadeCronograma
									.getId());
					faturamentoAtividadeCronogramaRotaPK
							.setRotaId(rota.getId());

					faturamentoAtividadeCronogramaRota = new FaturamentoAtivCronRota();

					faturamentoAtividadeCronogramaRota
							.setComp_id(faturamentoAtividadeCronogramaRotaPK);
					faturamentoAtividadeCronogramaRota
							.setFaturamentoAtividadeCronograma(faturamentoAtividadeCronograma);
					faturamentoAtividadeCronogramaRota.setRota(rota);

					if (sessao.getAttribute("exibirCampoVencimentoGrupo") != null) {
						faturamentoAtividadeCronogramaRota
								.setDataContaVencimento(dataVencimentoGrupoJSPObjeto);
					}

					colecaoFaturamentoAtividadeCronogramaRotaSelecionadasUsuario
							.add(faturamentoAtividadeCronogramaRota);
				}
			}
		}

		// União das coleções geradas nos processos 1 e 2 (Verifica existência
		// de registros repetidos).
		FaturamentoAtivCronRota faturamentoAtividadeCronogramaRota;
		Iterator colecaoRegistroAtual = colecaoFaturamentoAtividadeCronogramaRota
				.iterator();
		Iterator colecaoRegistroSelecaoUsuario = colecaoFaturamentoAtividadeCronogramaRotaSelecionadasUsuario
				.iterator();

		while (colecaoRegistroAtual.hasNext()) {
			faturamentoAtividadeCronogramaRota = (FaturamentoAtivCronRota) colecaoRegistroAtual
					.next();
			if (!colecaoFaturamentoAtividadeCronogramaRotaUniao
					.contains(faturamentoAtividadeCronogramaRota)) {
				colecaoFaturamentoAtividadeCronogramaRotaUniao
						.add(faturamentoAtividadeCronogramaRota);
			}
		}

		while (colecaoRegistroSelecaoUsuario.hasNext()) {
			faturamentoAtividadeCronogramaRota = (FaturamentoAtivCronRota) colecaoRegistroSelecaoUsuario
					.next();
			if (!colecaoFaturamentoAtividadeCronogramaRotaUniao
					.contains(faturamentoAtividadeCronogramaRota)) {
				colecaoFaturamentoAtividadeCronogramaRotaUniao
						.add(faturamentoAtividadeCronogramaRota);
			}
		}

		Collections.sort((List) colecaoFaturamentoAtividadeCronogramaRotaUniao,
				new Comparator() {
					public int compare(Object a, Object b) {
						String posicao1 = 
								((FaturamentoAtivCronRota) a)
									.getRota().getFaturamentoGrupo().getId().toString()
								+ ((FaturamentoAtivCronRota) a)
										.getRota().getSetorComercial()
										.getLocalidade().getGerenciaRegional()
										.getNomeAbreviado()
								+ ((FaturamentoAtivCronRota) a)
										.getRota().getSetorComercial()
										.getLocalidade().getDescricao()
								+ ((FaturamentoAtivCronRota) a)
										.getRota().getSetorComercial().getCodigo()
								+ ((FaturamentoAtivCronRota) a)
										.getRota().getId();
						String posicao2 = 
								((FaturamentoAtivCronRota) b)
									.getRota().getFaturamentoGrupo().getId().toString()
								+ ((FaturamentoAtivCronRota) b)
									.getRota().getSetorComercial()
									.getLocalidade().getGerenciaRegional()
									.getNomeAbreviado()
								+ ((FaturamentoAtivCronRota) b)
									.getRota().getSetorComercial()
									.getLocalidade().getDescricao()
								+ ((FaturamentoAtivCronRota) b)
									.getRota().getSetorComercial().getCodigo()
								+ ((FaturamentoAtivCronRota) b)
									.getRota().getId();

						return posicao1.compareTo(posicao2);
					}
				});

		sessao.setAttribute("colecaoFaturamentoAtividadeCronogramaRotaUniao",
				colecaoFaturamentoAtividadeCronogramaRotaUniao);

		return retorno;
	}
}
