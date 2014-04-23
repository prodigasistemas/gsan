package gcom.gui.atendimentopublico.ordemservico;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.bean.GerarBoletimOrdensServicoConcluidasHelper;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0765] Gerar Boletim Ordens Servico Concluidas
 * 
 * @author Ivan Sérgio
 * @date 22/04/2008
 * 
 */
public class ExibirResumoBoletimOrdensServicoConcluidasAction extends GcomAction {
	/**
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return forward
	 */
	public ActionForward execute(
			ActionMapping actionMapping,
			ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirResumoBoletimOrdensServicoConcluidasAction");
		
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		// Colecao contendo os Ids das Os
		Collection<Integer> colecaoIdOrdemServico = new ArrayList();
		
		GerarBoletimOrdensServicoConcluidasActionForm form = 
			(GerarBoletimOrdensServicoConcluidasActionForm) actionForm;
		
		if (httpServletRequest.getParameter("encerrarBoletim") == null) {
			//*********************************************************************
			// Realiza a Pesquisa para o resumo das OS Concluidas
			//*********************************************************************
			Integer idEmpresa = Util.converterStringParaInteger(form.getIdFirma());
			Integer idLocalidade = Util.converterStringParaInteger(form.getIdLocalidade());
			String anoMesReferenciaBoletim = Util.formatarMesAnoParaAnoMesSemBarra(
					form.getAnoMesReferenciaEncerramento());
			
			// Valida a Localidade informada
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidade));
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			Localidade localidade = (Localidade) fachada.pesquisar(filtroLocalidade, 
					Localidade.class.getName()).iterator().next();
			
			if (localidade != null) {
				form.setNomeLocalidade(localidade.getDescricao());
			}else {
				throw new ActionServletException("pesquisa.localidade.inexistente");
			}
			
			Collection colecaoDados =  fachada.pesquisarResumoOrdensServicoConcluidas(
					idEmpresa, idLocalidade, anoMesReferenciaBoletim);
			
			if (colecaoDados != null && !colecaoDados.isEmpty()) {
				// Totais Acumulados
				Integer totalNaoFiscalizadas = 0;
				Integer totalAprovadas = 0;
				Integer totalNaoFiscalizadasAprovadas = 0;
				Integer totalReprovadas = 0;
				Integer totalEncerradasMesesAnterioresAprovadasMes = 0;	
				Integer totalBoletim = 0;
				Integer totalHidrometrosInstaladosMuro = 0;
				Integer totalHidrometrosInstaladosCalcada = 0;
				Integer totalHidrometrosInstaladosJardim = 0;
				Integer totalHidrometrosSubstituidosSemTrocaCaixa = 0;
				Integer totalHidrometrosSubstituidosComTrocaCaixaMuro = 0;
				Integer totalHidrometrosSubstituidosComTrocaCaixaCalcada = 0;
				Integer totalTrocaRegistro = 0;
				
				// Variavel de controle para o caso do item 3
				boolean adicionaOSLista = true;
				
				Iterator iColecaoDados = colecaoDados.iterator();
				
				while (iColecaoDados.hasNext()) {
					GerarBoletimOrdensServicoConcluidasHelper helper = 
						(GerarBoletimOrdensServicoConcluidasHelper) iColecaoDados.next();
					
					//***************************************************************
					// Quantidade de OS Encerradas no Mes
					//***************************************************************
					// Total de Nao Fiscalizadas
					if (helper.getAnoMesReferenciaBoletim().equals(anoMesReferenciaBoletim) &&
							helper.getCodigoFiscalizacao() == 0) {
						totalNaoFiscalizadas++;
					}
					// Total de Aprovadas
					if (helper.getAnoMesReferenciaBoletim().equals(anoMesReferenciaBoletim) &&
							helper.getCodigoFiscalizacao() == 1) {
						totalAprovadas++;
					}
					// Total Nao Fiscalizadas e Aprovadas
					totalNaoFiscalizadasAprovadas = totalNaoFiscalizadas + totalAprovadas;
					// Total Reprovadas
					if (helper.getAnoMesReferenciaBoletim().equals(anoMesReferenciaBoletim) &&
							helper.getCodigoFiscalizacao() == 2) {
						totalReprovadas++;
					}
					// Total Encerradas em meses Anteriores e Aprovadas no mes
					if ( (Util.converterStringParaInteger(helper.getAnoMesReferenciaBoletim())) <
							(Util.converterStringParaInteger(anoMesReferenciaBoletim)) ) {
						
						if (helper.getCodigoFiscalizacao() == 1) {
							// Verifica qual a atual Data de Fiscalizacao
							Date dataFiscalizacao = null;
							Integer anoMesFiscalizacao = null;
							
							if (helper.getDataFiscalizacao1() != null) {
								dataFiscalizacao = helper.getDataFiscalizacao1();
							}else if (helper.getDataFiscalizacao2() != null) {
								dataFiscalizacao = helper.getDataFiscalizacao2();
							}else if (helper.getDataFiscalizacao3() != null) {
								dataFiscalizacao = helper.getDataFiscalizacao3();
							}
							
							anoMesFiscalizacao = Util.recuperaAnoMesDaData(dataFiscalizacao);
							
							if (Util.converterStringParaInteger(anoMesReferenciaBoletim).equals(anoMesFiscalizacao)) {
								totalEncerradasMesesAnterioresAprovadasMes++;
							}
						}else {
							adicionaOSLista = false;
						}
					}
							
						
					//***************************************************************
					
					if ( (helper.getCodigoFiscalizacao() == 0) || (helper.getCodigoFiscalizacao() == 1) ) {
						//***************************************************************
						// Total Hidrometro Instalacao
						//***************************************************************
						// Hidrometro Instalados No Muro
						if (helper.getIdTipoServico().equals(ServicoTipo.TIPO_EFETUAR_INSTALACAO_HIDROMETRO) &&
								helper.getIdLocalInstalacaoHidrometro().equals(8)) {
							totalHidrometrosInstaladosMuro++;
						}
						// Hidrometro Instalados Na Calcada
						if (helper.getIdTipoServico().equals(ServicoTipo.TIPO_EFETUAR_INSTALACAO_HIDROMETRO) &&
								helper.getIdLocalInstalacaoHidrometro().equals(3)) {
							totalHidrometrosInstaladosCalcada++;
						}
						// Hidrometro Instalados No Jardim
						if (helper.getIdTipoServico().equals(ServicoTipo.TIPO_EFETUAR_INSTALACAO_HIDROMETRO) &&
								helper.getIdLocalInstalacaoHidrometro().equals(1)) {
							totalHidrometrosInstaladosJardim++;
						}
						//***************************************************************
						
						//***************************************************************
						// Total Hidrometro Substituicao
						//***************************************************************
						// Hidrometro Substituidos Sem troca de Caixa
						if (helper.getIdTipoServico().equals(ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO) &&
								helper.getIndicadorTrocaProtecao() == 2) {
							totalHidrometrosSubstituidosSemTrocaCaixa++;
						}
						// Hidrometro Substituidos Com troca de Caixa no Muro
						if (helper.getIdTipoServico().equals(ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO) &&
								helper.getIndicadorTrocaProtecao() == 1 &&
								helper.getIdLocalInstalacaoHidrometro().equals(8)) {
							totalHidrometrosSubstituidosComTrocaCaixaMuro++;
						}
						// Hidrometro Substituidos Com troca de Caixa na Calcada
						if (helper.getIdTipoServico().equals(ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO) &&
								helper.getIndicadorTrocaProtecao() == 1 &&
								helper.getIdLocalInstalacaoHidrometro().equals(3)) {
							totalHidrometrosSubstituidosComTrocaCaixaCalcada++;
						}
						//***************************************************************
					}
					
					//***************************************************************
					// Total das Ordens
					//***************************************************************
					if (helper.getIndicadorTrocaRegistro() == 1) {
						totalTrocaRegistro++;
					}
					//***************************************************************
					
					// Adiciona os Ids das OS para o caso do Codigo de Fiscalizacao = 0 ou 1
					if (helper.getCodigoFiscalizacao() == 0 || helper.getCodigoFiscalizacao() == 1) {
						if (adicionaOSLista) {
							colecaoIdOrdemServico.add(helper.getIdOrdemServico());
						}
					}
					
					adicionaOSLista = true;
				}
				
				// Total do Boletim
				totalBoletim = totalNaoFiscalizadas + totalAprovadas + totalEncerradasMesesAnterioresAprovadasMes;
				
				// Preenche o Form
				form.setTotalNaoFiscalizadas(totalNaoFiscalizadas);
				form.setTotalAprovadas(totalAprovadas);
				form.setTotalNaoFiscalizadasAprovadas(totalNaoFiscalizadasAprovadas);
				form.setTotalReprovadas(totalReprovadas);
				form.setTotalEncerradasMesesAnterioresAprovadasMes(totalEncerradasMesesAnterioresAprovadasMes);
				form.setTotalBoletim(totalBoletim);
				form.setTotalHidrometrosInstaladosMuro(totalHidrometrosInstaladosMuro);
				form.setTotalHidrometrosInstaladosCalcada(totalHidrometrosInstaladosCalcada);
				form.setTotalHidrometrosInstaladosJardim(totalHidrometrosInstaladosJardim);
				form.setTotalHidrometrosSubstituidosSemTrocaCaixa(totalHidrometrosSubstituidosSemTrocaCaixa);
				form.setTotalHidrometrosSubstituidosComTrocaCaixaMuro(totalHidrometrosSubstituidosComTrocaCaixaMuro);
				form.setTotalHidrometrosSubstituidosComTrocaCaixaCalcada(totalHidrometrosSubstituidosComTrocaCaixaCalcada);
				form.setTotalTrocaRegistro(totalTrocaRegistro);
				
				// Coloca a Colecao das Ordens de Servico na Session
				sessao.setAttribute("colecaoIdOrdemServico", colecaoIdOrdemServico);
				
			}else {
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			}
		}else {
			//*********************************************************************
			// Realiza o Encerramento das OS
			//*********************************************************************
			colecaoIdOrdemServico = (Collection<Integer>) sessao.getAttribute("colecaoIdOrdemServico");
			
			if (colecaoIdOrdemServico != null && !colecaoIdOrdemServico.isEmpty()) {
				fachada.encerrarBoletimOrdemServicoConcluida(colecaoIdOrdemServico);
			}else {
				throw new ActionServletException("atencao.nenhuma_os_selecionada_para_encerramento");
			}
			
			retorno = actionMapping.findForward("telaSucesso");
			montarPaginaSucesso(httpServletRequest, "Boletim de Ordens de Serviço Encerradas com Sucesso.",
					"Resumo das Ordens de Serviços Concluidas", 
					"exibirFiltrarBoletimOrdensServicoConcluidasAction.do?menu=sim");
		}

		return retorno;
	}
}
