package gcom.gui.micromedicao;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.bean.ImovelMicromedicao;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroMotivoInterferenciaTipo;
import gcom.faturamento.FiltroMovimentoContaPrefaturada;
import gcom.faturamento.MotivoInterferenciaTipo;
import gcom.faturamento.MovimentoContaPrefaturada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroReleituraMobile;
import gcom.micromedicao.ReleituraMobile;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.micromedicao.bean.CalculoConsumoHelper;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraTipo;
import gcom.micromedicao.medicao.FiltroMedicaoHistoricoSql;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirDadosAnaliseExcecoesLeituraResumidoAction extends GcomAction {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("dadosAnaliseMedicaoConsumoResumo");

		LeituraConsumoActionForm leituraConsumoActionForm = (LeituraConsumoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = this.getUsuarioLogado(httpServletRequest);
		
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo)sessao.getAttribute("faturamentoGrupo");
		String mesAnoPesquisa = (String) sessao.getAttribute("mesAnoPesquisa");
		
		if(possuiAnormalidade(httpServletRequest, leituraConsumoActionForm)){
			
			String codigoAnormalidadeLeituraDigitadoEnter = leituraConsumoActionForm.getIdAnormalidade();

			FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
			filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidade.ID, codigoAnormalidadeLeituraDigitadoEnter));

			Collection anormalidadeLeituraEncontrada = fachada.pesquisar(filtroLeituraAnormalidade, LeituraAnormalidade.class.getName());

			if (anormalidadeLeituraEncontrada != null && !anormalidadeLeituraEncontrada.isEmpty()) {

				leituraConsumoActionForm.setIdAnormalidade("" + ((LeituraAnormalidade) ((List) anormalidadeLeituraEncontrada).get(0)).getId());
				leituraConsumoActionForm.setDescricaoAnormalidade(((LeituraAnormalidade) ((List) anormalidadeLeituraEncontrada).get(0)).getDescricao());
				leituraConsumoActionForm.setIndicadorLeitura(""	+ ((LeituraAnormalidade) ((List) anormalidadeLeituraEncontrada).get(0)).getIndicadorLeitura());

			} else {
				leituraConsumoActionForm.setIdAnormalidade("");
				httpServletRequest.setAttribute("idAnormalidadeNaoEncontrada", "true");
				leituraConsumoActionForm.setDescricaoAnormalidade("Anormalidade de leitura inexistente");
				httpServletRequest.setAttribute("nomeCampo","idAnormalidade");
				leituraConsumoActionForm.setIndicadorLeitura("");
			}	
		} else {
			leituraConsumoActionForm = limparLeituraConsumo(leituraConsumoActionForm);
			
			String codigoImovel = httpServletRequest.getParameter("idRegistroAtualizacao");
			
			if(httpServletRequest.getParameter("consultaImovelLista") != null){
				
				Collection colecaoTeste = (Collection)sessao.getAttribute("colecaoIdsImovelTotal");
				Iterator iteratorTeste = colecaoTeste.iterator();
				int contem = 0;
				
				while(iteratorTeste.hasNext()){
					ImovelMicromedicao imovelMicromedicaoTeste = (ImovelMicromedicao)iteratorTeste.next();
					Integer codigoImovelTeste = imovelMicromedicaoTeste.getImovel().getId();
					if(codigoImovel.equals(codigoImovelTeste.toString())){
						contem = 1;
						break;
					}
				}
				
				if(contem != 1){
					throw new ActionServletException("atencao.pesquisa.nenhumresultado");
				}
			}
			
			String idMedicaoTipo = httpServletRequest.getParameter("medicaoTipo");
	
			FiltroMedicaoHistoricoSql filtroMedicaoHistoricoSql = new FiltroMedicaoHistoricoSql();
			
			if (sessao.getAttribute("filtroMedicaoHistoricoSql") != null) {
				filtroMedicaoHistoricoSql = (FiltroMedicaoHistoricoSql) sessao.getAttribute("filtroMedicaoHistoricoSql");
			}
			
			Integer totalRegistros = obterTotalRegistros(fachada, sessao, faturamentoGrupo, filtroMedicaoHistoricoSql);
	
			int numeroPaginasPesquisa = 0;

			if (sessao.getAttribute("numeroPaginasPesquisa") != null && !sessao.getAttribute("numeroPaginasPesquisa").equals("")) {
				numeroPaginasPesquisa = (Integer) sessao.getAttribute("numeroPaginasPesquisa");
			}
	
			int index = 0;
			if (sessao.getAttribute("index") != null) {
				index = (Integer) sessao.getAttribute("index");
			}

			Collection colecaoIdsImovel = obterColecaoIdsImovel(fachada, sessao, faturamentoGrupo, filtroMedicaoHistoricoSql, totalRegistros);
	
			Imovel imovelAtual = ((ImovelMicromedicao) ((List) colecaoIdsImovel).get(index)).getImovel();
			
			String observacao = (String)httpServletRequest.getParameter("observacao");
			adicionarObjetosSelecionadosColecoes(httpServletRequest, sessao, imovelAtual, observacao);
			
			sessao.setAttribute("analisado", "");
			sessao.setAttribute("gerarAviso", "");
			sessao.setAttribute("gerarOS", "");
			sessao.setAttribute("gerarRelatorio", "");
			sessao.setAttribute("observacao", "");

			// Atualiza os dados de medição histórico colocando como analisado
			if (sessao.getAttribute("habilitaCampos") == null) {
				
				String analisado = httpServletRequest.getParameter("analisado");

				if (analisado != null && !analisado.trim().equals("")) {

					MedicaoHistorico medicaoHistorico = ((ImovelMicromedicao) ((List) colecaoIdsImovel).get(index)).getMedicaoHistorico();

					if (medicaoHistorico.getIndicadorAnalisado().equals(MedicaoHistorico.INDICADOR_ANALISADO_NAO)) {

						fachada.atualizarIndicadorAnalisadoMedicaoHistorico(medicaoHistorico.getId(), usuarioLogado);
						
						medicaoHistorico.setUsuarioAlteracao(usuarioLogado);
						medicaoHistorico.setIndicadorAnalisado(MedicaoHistorico.INDICADOR_ANALISADO_SIM);

					}
				}
			}
			
			if (codigoImovel != null && !codigoImovel.equals("")) {
				int i = 0;
				Iterator iterator = colecaoIdsImovel.iterator();

				while (iterator.hasNext()) {
					ImovelMicromedicao imovelMicromedicao = (ImovelMicromedicao) iterator.next();
					
					if (!imovelMicromedicao.getImovel().getId().equals(new Integer(codigoImovel))) {
						i = i + 1;
					} else {
						break;
					}
				}
				index = i;
				sessao.setAttribute("index", index);
			} else {
				
				if (httpServletRequest.getParameter("imovelAnterior") != null) {
					index = index - 1;
					limparFormPaginacao(leituraConsumoActionForm);
					
				} else if (httpServletRequest.getParameter("proximoImovel") != null) {
					index = index + 1;
					limparFormPaginacao(leituraConsumoActionForm);
				}
				
				if (index == colecaoIdsImovel.size() || index == -1) {
					if (colecaoIdsImovel != null && !colecaoIdsImovel.isEmpty()) {
	
						codigoImovel = ((ImovelMicromedicao) ((List) colecaoIdsImovel).get(index)).getImovel().getId().toString();
						
						if(((ImovelMicromedicao) ((List) colecaoIdsImovel).get(index)).getMedicaoHistorico().getMedicaoTipo() != null){
							idMedicaoTipo = ((ImovelMicromedicao) ((List) colecaoIdsImovel).get(index)).getMedicaoHistorico().getMedicaoTipo().getId().toString();
						}
						sessao.setAttribute("index", index);
					}
				} else {
	
					codigoImovel = ((ImovelMicromedicao) ((List) colecaoIdsImovel).get(index)).getImovel().getId().toString();
					
					if(((ImovelMicromedicao) ((List) colecaoIdsImovel).get(index)).getMedicaoHistorico().getMedicaoTipo() != null){
						
						if(((ImovelMicromedicao) ((List) colecaoIdsImovel).get(index)).getMedicaoHistorico().getMedicaoTipo().getId() != null){
							idMedicaoTipo = ((ImovelMicromedicao) ((List) colecaoIdsImovel).get(index)).getMedicaoHistorico().getMedicaoTipo().getId().toString();	
						}
					}
					sessao.setAttribute("index", index);
				}
			}
			
			imovelAtual = ((ImovelMicromedicao) ((List) colecaoIdsImovel).get(index)).getImovel();

			ImovelPerfil imovelPerfil = fachada.recuperaPerfilImovel(new Integer(codigoImovel));
			
			MovimentoContaPrefaturada movimentoContaPrefaturada = obterMovimentoContaPreFaturada(fachada, mesAnoPesquisa, codigoImovel, idMedicaoTipo);
			
			Imovel imovelFiltro = null;
			
			if (codigoImovel != null && !codigoImovel.equals("") ) {
				
				imovelFiltro = obterImovel(fachada, codigoImovel);
				
				if(imovelFiltro != null){
					
					if (imovelFiltro.getRotaAlternativa() != null){
						leituraConsumoActionForm.setRota(imovelFiltro.getRotaAlternativa().getCodigo() + "");
					}
					else{
						leituraConsumoActionForm.setRota(imovelFiltro.getQuadra().getRota().getCodigo() + "");
					}
					
					if (imovelFiltro.getNumeroSequencialRota() != null) {
						leituraConsumoActionForm.setSeqRota(imovelFiltro.getNumeroSequencialRota().toString());
					}
				}
			}
			
			boolean existeContaPreFaturada = false;
			Integer idDebitoCreditoSituacaoAtualDaConta = fachada.pesquisarDebitoCreditoSituacaoAtualDaConta(new Integer(codigoImovel), Util.formatarMesAnoComBarraParaAnoMes(mesAnoPesquisa));
			
			if(idDebitoCreditoSituacaoAtualDaConta != null){
				existeContaPreFaturada = true;
			}
			
			boolean desabilitaAtualizarImovel = true;
			
			if(isTipoLeituraImpressaoSimultanea(imovelAtual, imovelPerfil)){
				//Caso o tipo de leitura da rota do imóvel seja LEIT.E ENTR.SIMULTAN (tabela ROTA), então desabilita os campos de atualização da tela. 
				desabilitaAtualizarImovel = true;
			}
			
			if(existeContaPreFaturada){
				//Caso a conta esteja como pré-faturada (DCST_IDATUAL=9) habilitar os campos para alteração da conta
				desabilitaAtualizarImovel = false;
			
			}
			
			if(movimentoContaPrefaturada != null){
				//Caso o imóvel não esteja em MOVIMENTO_CONTA_PREFATURADA para o ano/mês de referencia ou MCPF_ICEMISSAOCONTA esteja como 2, habilitar campos para alteração
				if(movimentoContaPrefaturada.getIndicadorEmissaoConta().compareTo(ConstantesSistema.NAO) == 0){
					desabilitaAtualizarImovel = false;
				}
			}else{
				desabilitaAtualizarImovel = false;
			}
			
			
			if(isImovelCondominio(imovelFiltro)){
				//Caso o imóvel seja condomínio, não permitir alterar
				desabilitaAtualizarImovel = true;
			}
			
			//Caso o imovel possua conta com situação diferente de pre-faturada para referencia informada, não sera possivel atualizar a medição 
			boolean imovelPossuiContaDiferentePreFaturada = fachada.pesquisarContaDoImovelDiferentePreFaturada(new Integer(codigoImovel), Util.formatarMesAnoComBarraParaAnoMes(mesAnoPesquisa));
			
			if ( imovelPossuiContaDiferentePreFaturada ) {
				desabilitaAtualizarImovel = true;
			}
			
			httpServletRequest.setAttribute("desabilitaAtualizarImovel",desabilitaAtualizarImovel);
			verificarAbrangenciaUsuario(httpServletRequest, usuarioLogado, imovelAtual);
			
			Collection colecaoFaturamentoSituacaoEspecial = fachada.pesquisarSituacaoEspecialFaturamentoVigente(imovelAtual.getId(), Util.formatarMesAnoComBarraParaAnoMes(mesAnoPesquisa));
			httpServletRequest.setAttribute("colecaoFaturamentoSituacaoEspecial", colecaoFaturamentoSituacaoEspecial);
			
			Collection colecaoImoveisGerarAviso = (Collection) sessao.getAttribute("colecaoImoveisGerarAviso");
			if (colecaoImoveisGerarAviso != null && colecaoImoveisGerarAviso.contains(imovelAtual.getId())) {
				sessao.setAttribute("gerarAviso", "1");
			}
			
			Collection colecaoImoveisGerarOS = (Collection) sessao.getAttribute("colecaoImoveisGerarOS");
			HashMap<Integer, String> colecaoObservacaoOS = (HashMap<Integer, String>) sessao.getAttribute("colecaoObservacaoOS");
			if (colecaoImoveisGerarOS != null && colecaoImoveisGerarOS.contains(imovelAtual)) {
				sessao.setAttribute("gerarOS", "1");
				String observacao2 = colecaoObservacaoOS.get(imovelAtual.getId());
				sessao.setAttribute("observacao", observacao2);
				leituraConsumoActionForm.setObservacao(observacao2);
			}
			
			Collection colecaoImoveisGerarRelatorio = (Collection) sessao.getAttribute("colecaoImoveisGerarRelatorio");
			if (colecaoImoveisGerarRelatorio != null && colecaoImoveisGerarRelatorio.contains(imovelAtual)) {
				sessao.setAttribute("gerarRelatorio", "1");
			}
			
			sessao.setAttribute("indiceImovel", ""+(index+1));
			
			if (codigoImovel != null && !codigoImovel.equals("") ) {
				
				boolean ligacaoAgua = false;
				if(idMedicaoTipo != null && idMedicaoTipo.trim().equals("" + MedicaoTipo.LIGACAO_AGUA)){
					ligacaoAgua = true;
				}
				
				sessao.setAttribute("ligacaoAgua", ligacaoAgua);
				sessao.setAttribute("tipoMedicao", idMedicaoTipo);
				
				// Cria as istancias dos objetos q receberam os dados q iram compor a tela
				ImovelMicromedicao imovelMicromedicaoDadosResumo = new ImovelMicromedicao();
				ImovelMicromedicao imovelMicromedicaoMedicaoResumo = new ImovelMicromedicao();
				
				imovelMicromedicaoDadosResumo = fachada.pesquiarImovelExcecoesApresentaDadosResumido(new Integer(codigoImovel), ligacaoAgua);
				imovelMicromedicaoMedicaoResumo = fachada.carregarDadosMedicaoResumido(new Integer(codigoImovel), ligacaoAgua, Util.formatarMesAnoParaAnoMesSemBarra((String)sessao.getAttribute("mesAnoPesquisa")));
				
				String mesAnoDigitado = Util.formatarMesAnoParaAnoMesSemBarra((String)sessao.getAttribute("mesAnoPesquisa"));
				
				if(imovelMicromedicaoDadosResumo.getImovel() != null &&
						imovelMicromedicaoDadosResumo.getImovel().getHidrometroInstalacaoHistorico() != null){
					leituraConsumoActionForm.setInstalacaoHidrometro(Util.formatarData(imovelMicromedicaoDadosResumo.getImovel().getHidrometroInstalacaoHistorico().getDataInstalacao()));
				}else{
					leituraConsumoActionForm.setInstalacaoHidrometro("");
				}
				
				imovelMicromedicaoDadosResumo.getImovel().setId(new Integer(codigoImovel));
				sessao.setAttribute("imovelMicromedicaoDadosResumo",imovelMicromedicaoDadosResumo);
				sessao.setAttribute("idImovel", codigoImovel);
				sessao.setAttribute("imovelMicromedicaoCarregaMedicaoResumo", imovelMicromedicaoMedicaoResumo);

				if(imovelPossuiPoco(imovelMicromedicaoDadosResumo)){
					sessao.setAttribute("poco",true);
				}else{
					sessao.removeAttribute("poco");
				}
				
				Imovel imovel = new Imovel(new Integer(codigoImovel));
				
				sessao.setAttribute("categoria", fachada.obterDescricoesCategoriaImovel(imovel));
				sessao.setAttribute("inscricaoFormatada", fachada.pesquisarInscricaoImovel(new Integer(codigoImovel)));
				
				Collection colecaoMedicaoHistorico = fachada.carregarDadosMedicaoResumo(new Integer(codigoImovel), ligacaoAgua);
	
				Collection imoveisMicromedicaoCarregamento = null;
				Collection colecaoImovelMicromedicao = new ArrayList();
				
				MedicaoHistorico medicaoHistoricoAnoMesAtual = new MedicaoHistorico();
				ImovelMicromedicao imovelMicromedicaoConsumo = new ImovelMicromedicao();

				MedicaoHistorico medicaoHistorico = ((ImovelMicromedicao) ((List) colecaoIdsImovel).get(index)).getMedicaoHistorico();

				if (medicaoHistorico.getIndicadorAnalisado() != null){
					leituraConsumoActionForm.setAnalisado(medicaoHistorico.getIndicadorAnalisado().toString());
					sessao.setAttribute("analisado", medicaoHistorico.getIndicadorAnalisado().toString());
				} 
				
				if (medicaoHistorico.getUsuarioAlteracao() != null) {
					leituraConsumoActionForm.setLoginUsuario(medicaoHistorico.getUsuarioAlteracao().getLogin());
					leituraConsumoActionForm.setNomeUsuario(medicaoHistorico.getUsuarioAlteracao().getNomeUsuario());
				}else{
					leituraConsumoActionForm.setLoginUsuario("");
					leituraConsumoActionForm.setNomeUsuario("");
				}				
				
				if (medicaoHistorico.getLeituraCampo() != null) {
					leituraConsumoActionForm.setLeituraCampo(medicaoHistorico.getLeituraCampo().toString());
				}else{
					leituraConsumoActionForm.setLeituraCampo("");
				}
				
				if (medicaoHistorico.getLeiturista().getCliente() != null) {
					leituraConsumoActionForm.setNomeLeiturista(medicaoHistorico.getLeiturista().getCliente().getNome());
				} else if (medicaoHistorico.getLeiturista().getFuncionario() != null) {
					leituraConsumoActionForm.setNomeLeiturista(medicaoHistorico.getLeiturista().getFuncionario().getNome());
				}
				
				if (imovelMicromedicaoMedicaoResumo.getMedicaoHistorico().getMotivoInterferenciaTipo() != null){
					leituraConsumoActionForm.setMotivoInterferenciaTipo(
							imovelMicromedicaoMedicaoResumo.getMedicaoHistorico().getMotivoInterferenciaTipo().getId());
				} 
				
				// Carrega o combo de calculo de consumo
				Collection<CalculoConsumoHelper> colecaoCalculoConsumo = carregarComboCalculoConsumo(medicaoHistorico, imovel, Util.formatarMesAnoComBarraParaAnoMes(mesAnoPesquisa));
				sessao.setAttribute("colecaoCalculoConsumo", colecaoCalculoConsumo);
				leituraConsumoActionForm.setCalculoConsumo("" + ConstantesSistema.NUMERO_NAO_INFORMADO);
	
				if (colecaoMedicaoHistorico != null && !colecaoMedicaoHistorico.isEmpty()) {
					Iterator iteratorMedicaoHistorico = colecaoMedicaoHistorico
							.iterator();
	
					while (iteratorMedicaoHistorico.hasNext()) {
						MedicaoHistorico medicaoHistoricoConsumo = (MedicaoHistorico) iteratorMedicaoHistorico.next();
						if (medicaoHistoricoConsumo.getAnoMesReferencia() != 0) {
							
							
							imoveisMicromedicaoCarregamento = fachada.carregarDadosConsumo(new Integer(codigoImovel),
											medicaoHistoricoConsumo.getAnoMesReferencia(), ligacaoAgua);
	
							if (imoveisMicromedicaoCarregamento != null) {
								ImovelMicromedicao imovelMicromedicao = (ImovelMicromedicao)imoveisMicromedicaoCarregamento.iterator().next();
								if(imovelMicromedicao.getMedicaoHistorico() != null && imovelMicromedicao.getMedicaoHistorico().getNumeroConsumoMes() != null){
									medicaoHistoricoConsumo.setNumeroConsumoMes(imovelMicromedicao.getMedicaoHistorico().getNumeroConsumoMes());
								}
								
								if(Util.formatarMesAnoComBarraParaAnoMes(mesAnoPesquisa) == medicaoHistoricoConsumo.getAnoMesReferencia()){
									
									if (imovelMicromedicao.getConsumoHistorico().getNumeroConsumoFaturadoMes() != null) {
										leituraConsumoActionForm.setConsumo(imovelMicromedicao.getConsumoHistorico().getNumeroConsumoFaturadoMes().toString());
									} else {
										leituraConsumoActionForm.setConsumo("");
									}
									
									if (imovelMicromedicao.getConsumoHistorico().getConsumoMedio() != null) {
										leituraConsumoActionForm.setConsumoMedioImovel(imovelMicromedicao.getConsumoHistorico().getConsumoMedio().toString());
									} else {
										leituraConsumoActionForm.setConsumoMedioImovel("");
									}
									
									if(imovelMicromedicao.getConsumoHistorico().getConsumoRateio() != null){
										leituraConsumoActionForm.setRateio(imovelMicromedicao.getConsumoHistorico().getConsumoRateio().toString());
									}
								}
								
								imovelMicromedicao.setMedicaoHistorico(medicaoHistoricoConsumo);
								
								colecaoImovelMicromedicao.add(imovelMicromedicao);
							}
							if(mesAnoDigitado.equals(medicaoHistoricoConsumo.getAnoMesReferencia()+"")){
								medicaoHistoricoAnoMesAtual = medicaoHistoricoConsumo;
								sessao.setAttribute("medicaoHistoricoAnoMesAtual", medicaoHistoricoAnoMesAtual);
								
								if (imoveisMicromedicaoCarregamento != null){
									imovelMicromedicaoConsumo = (ImovelMicromedicao)imoveisMicromedicaoCarregamento.iterator().next();

									if(imovelMicromedicaoConsumo.getQtdDias() != null){
										leituraConsumoActionForm.setDiasConsumo(imovelMicromedicaoConsumo.getQtdDias());
									}
									
									if(imovelMicromedicaoMedicaoResumo.getMedicaoHistorico().getDataLeituraAnteriorFaturamento() != null){
										leituraConsumoActionForm.setDataLeituraAnteriorFaturamento(Util.formatarData(imovelMicromedicaoMedicaoResumo.getMedicaoHistorico().getDataLeituraAnteriorFaturamento()));
									}else{
										leituraConsumoActionForm.setDataLeituraAnteriorFaturamento("");
									}
									if(imovelMicromedicaoMedicaoResumo.getMedicaoHistorico().getDataLeituraAtualInformada() != null){
										leituraConsumoActionForm.setDataLeituraAtualInformada(Util.formatarData(imovelMicromedicaoMedicaoResumo.getMedicaoHistorico().getDataLeituraAtualInformada()));
									}else{
										leituraConsumoActionForm.setDataLeituraAtualInformada("");
									}
									if(imovelMicromedicaoMedicaoResumo.getMedicaoHistorico().getConsumoMedioHidrometro() != null){
										leituraConsumoActionForm.setConsumoMedioHidrometro(imovelMicromedicaoMedicaoResumo.getMedicaoHistorico().getConsumoMedioHidrometro()+"");
									}else{
										leituraConsumoActionForm.setConsumoMedioHidrometro("");
									}
									
									if(medicaoHistoricoAnoMesAtual.getDataLeituraAtualFaturamento() != null){
										leituraConsumoActionForm.setDataLeituraAtualFaturamento(Util.formatarData(medicaoHistoricoAnoMesAtual.getDataLeituraAtualFaturamento()));
									}else{
										leituraConsumoActionForm.setDataLeituraAtualFaturamento("");
									}
									
									leituraConsumoActionForm
											.setLeituraAnteriorFaturamento(imovelMicromedicaoMedicaoResumo
													.getMedicaoHistorico()
													.getLeituraAnteriorFaturamento()
													+ "");
									if(medicaoHistoricoAnoMesAtual.getLeituraAtualInformada() != null){
										leituraConsumoActionForm.setLeituraAtualInformada(medicaoHistoricoAnoMesAtual.getLeituraAtualInformada() + "");
									}else{
										leituraConsumoActionForm.setLeituraAtualInformada("");
									}
									
									leituraConsumoActionForm.setLeituraAtualFaturada(medicaoHistoricoAnoMesAtual.getLeituraAtualFaturamento() + "");

									if(medicaoHistoricoAnoMesAtual.getLeituraSituacaoAtual() != null){
										leituraConsumoActionForm.setLeituraSituacaoAtual(medicaoHistoricoAnoMesAtual.getLeituraSituacaoAtual().getDescricao());
									}
									
									if(medicaoHistoricoAnoMesAtual.getFuncionario() != null){
										leituraConsumoActionForm.setIdFuncionario(medicaoHistoricoAnoMesAtual.getFuncionario().getId().toString());
									}
									
									if(imovelMicromedicaoMedicaoResumo.getMedicaoHistorico().getNumeroConsumoInformado() != null){
										leituraConsumoActionForm.setConsumoInformado(imovelMicromedicaoMedicaoResumo.getMedicaoHistorico().getNumeroConsumoInformado().toString());
									} else {
										leituraConsumoActionForm.setConsumoInformado("");
										
									}
									
									if (medicaoHistoricoConsumo.getNumeroConsumoMes() != null) {
										leituraConsumoActionForm.setMedido(medicaoHistoricoConsumo.getNumeroConsumoMes()+"");
									} else {
										leituraConsumoActionForm.setMedido("");
									}
									
									if(imovelMicromedicaoMedicaoResumo.getMedicaoHistorico() != null
										&& imovelMicromedicaoMedicaoResumo.getMedicaoHistorico().getLeituraAnormalidadeFaturamento() != null
										&& imovelMicromedicaoMedicaoResumo.getMedicaoHistorico().getLeituraAnormalidadeFaturamento().getId() != null){
										
										LeituraAnormalidade leituraAnormalidade = obterAnormalidadeLeituraEncontrada(fachada, imovelMicromedicaoMedicaoResumo);

										if (leituraAnormalidade != null) {
											imovelMicromedicaoMedicaoResumo.getMedicaoHistorico().setLeituraAnormalidadeFaturamento(leituraAnormalidade);
											
											leituraConsumoActionForm.setIdAnormalidade(""+ leituraAnormalidade.getId());
											leituraConsumoActionForm.setDescricaoAnormalidade(leituraAnormalidade.getDescricao());
											leituraConsumoActionForm.setIndicadorLeitura("" + leituraAnormalidade.getIndicadorLeitura());
										}
									}

									leituraConsumoActionForm.setConfirmacao(httpServletRequest.getParameter("confirmacao"));
									
									sessao.setAttribute("imovelMicromedicaoConsumo", imovelMicromedicaoConsumo);
									//% Var.Consumo
									if (imovelMicromedicaoConsumo.getConsumoHistorico().getNumeroConsumoFaturadoMes() != null
											&& imovelMicromedicaoConsumo.getConsumoHistorico().getNumeroConsumoFaturadoMes() != 0
											&& imovelMicromedicaoConsumo.getConsumoHistorico().getConsumoMedio() != null
											&& imovelMicromedicaoConsumo.getConsumoHistorico().getConsumoMedio() != 0) {
										int operacaoSubMult = (imovelMicromedicaoConsumo.getConsumoHistorico().getNumeroConsumoFaturadoMes() - imovelMicromedicaoConsumo.getConsumoHistorico().getConsumoMedio()) * 100;
										BigDecimal percentual = new BigDecimal(operacaoSubMult)
												.divide(new BigDecimal(imovelMicromedicaoConsumo.getConsumoHistorico().getConsumoMedio()), 2,
														BigDecimal.ROUND_HALF_UP);
										String valorPercentual = "" + percentual;
										leituraConsumoActionForm.setVarConsumo(valorPercentual.replace(".", ",") + "%");
									}
								}
								
								if(imovelMicromedicaoConsumo.getConsumoHistorico() != null
										&& imovelMicromedicaoConsumo.getConsumoHistorico().getConsumoAnormalidade() != null){
									leituraConsumoActionForm.setConsumoAnormalidadeAbreviada(imovelMicromedicaoConsumo.getConsumoHistorico().getConsumoAnormalidade().getDescricaoAbreviada());
									leituraConsumoActionForm.setAnormalidadeConsumo(imovelMicromedicaoConsumo.getConsumoHistorico().getConsumoAnormalidade().getDescricao());
								}else if(imovelMicromedicaoConsumo.getConsumoHistoricoEsgoto()!= null
										&& imovelMicromedicaoConsumo.getConsumoHistoricoEsgoto().getConsumoAnormalidade() != null){
									leituraConsumoActionForm.setAnormalidadeConsumo(imovelMicromedicaoConsumo.getConsumoHistoricoEsgoto().getConsumoAnormalidade().getDescricao());
								}
								iteratorMedicaoHistorico.remove();
							}
						}
					}
					
//					 Organizar a coleção de Conta
					if (colecaoImovelMicromedicao != null && !colecaoImovelMicromedicao.isEmpty()) {
						
						Collections.sort((List) colecaoImovelMicromedicao, new Comparator() {
									public int compare(Object a, Object b) {
										
										int retorno = 0;
										Integer anoMesReferencia1 = ((ImovelMicromedicao) a).getMedicaoHistorico().getAnoMesReferencia();
										Integer anoMesReferencia2 = ((ImovelMicromedicao) b).getMedicaoHistorico().getAnoMesReferencia();

										if(anoMesReferencia1.compareTo(anoMesReferencia2) == 1){
											retorno = -1;
										}else if(anoMesReferencia1.compareTo(anoMesReferencia2) == -1){
											retorno = 1;
										}
										return retorno;
									}
							});
					}
					
									
					sessao.setAttribute("medicoesHistoricos", colecaoMedicaoHistorico);
					sessao.setAttribute("imoveisMicromedicao", colecaoImovelMicromedicao);
				} 
				
				if ( httpServletRequest.getParameter("solicitarReleitura") != null ){
					fachada.solicitarReleitura( codigoImovel,  usuarioLogado );
					httpServletRequest.setAttribute("mensagemReleitura", "Releitura do imóvel foi solicitada com sucesso.");
				}
				
				if ( fachada.releituraJaRealizada( codigoImovel ) ){
					httpServletRequest.setAttribute( "releituraJaRealizada", Boolean.TRUE );
				}
				
				if (verificarRotaFinalizada(imovelFiltro.getQuadra().getRota().getId(), mesAnoPesquisa)){
					httpServletRequest.setAttribute( "rotaFinalizada", Boolean.TRUE );
					httpServletRequest.setAttribute( "solicitarReleitura", Boolean.TRUE );
				}
				
				if (verificarReleitura(Util.formatarMesAnoComBarraParaAnoMes(mesAnoPesquisa), imovelFiltro.getId())){
					httpServletRequest.setAttribute( "solicitarReleitura", Boolean.TRUE );
				}
			}
			
			sessao.setAttribute("leituraConsumoActionForm", leituraConsumoActionForm);
			if (index == 0 && numeroPaginasPesquisa == 0) {
				sessao.setAttribute("desabilitaBotaoAnterior", 1);
			}else{
				sessao.removeAttribute("desabilitaBotaoAnterior");
			}
			
			if (index >= (colecaoIdsImovel.size() - 1)) {
				sessao.setAttribute("desabilitaBotaoProximo", 1);
			}else{
				sessao.removeAttribute("desabilitaBotaoProximo");
			}
			
			Integer anoMes = Util.formatarMesAnoComBarraParaAnoMes(mesAnoPesquisa);
			
			if (imovelAtual.getQuadra().getRota().getLeituraTipo().getId().intValue() != LeituraTipo.LEITURA_E_ENTRADA_SIMULTANEA.intValue()) {
				if (!isFaturamentoGrupoAberto(Util.formatarMesAnoComBarraParaAnoMes(mesAnoPesquisa), faturamentoGrupo)) {
					verificarAbrangenciaUsuario(httpServletRequest, usuarioLogado, imovelAtual);
					desabilitaAtualizarImovel = true;
				}
			}
			else if(isTipoLeituraImpressaoSimultanea(imovelAtual, imovelPerfil)) {
				if (isFaturamentoGrupoAberto(Util.formatarMesAnoComBarraParaAnoMes(mesAnoPesquisa), faturamentoGrupo)
						&& imovelPossuiMedicaoHistorico(idMedicaoTipo, imovelAtual, anoMes) 
						&& imovelPossuiContaPreFaturada(imovelAtual, anoMes)
						&& imovelPossuiContaGerada(imovelAtual, anoMes)){
					desabilitaAtualizarImovel = false;
				} else {
					httpServletRequest.setAttribute("habilitaCampos", false);
					desabilitaAtualizarImovel = true;
				}
			}
			httpServletRequest.setAttribute("desabilitaAtualizarImovel",desabilitaAtualizarImovel);
			
		}
		
		FiltroMotivoInterferenciaTipo filtroMotivoInterferenciaTipo = new FiltroMotivoInterferenciaTipo();
		filtroMotivoInterferenciaTipo.adicionarParametro(new ParametroSimples(
				FiltroMotivoInterferenciaTipo.INDICADOR_USO, ConstantesSistema.SIM));
		
		Collection colecaoMotivoInterferenciaTipo = fachada.pesquisar(filtroMotivoInterferenciaTipo, MotivoInterferenciaTipo.class.getName());
		
		if (colecaoMotivoInterferenciaTipo!= null && !colecaoMotivoInterferenciaTipo.isEmpty()){
			sessao.setAttribute("colecaoMotivoInterferenciaTipo", colecaoMotivoInterferenciaTipo);
		}
		
		return retorno;
	}

	private boolean imovelPossuiContaGerada(Imovel imovelAtual, Integer anoMes) {
		MovimentoContaPrefaturada mcpf = this.getFachada().obterMovimentoImovel(imovelAtual.getId(), anoMes);
		
		if ((mcpf != null && mcpf.getId() != null
				&& mcpf.getIndicadorEmissaoConta().shortValue() == ConstantesSistema.NAO.shortValue()
				&& mcpf.getIndicadorGeracaoConta().shortValue() == ConstantesSistema.NAO.shortValue())
				|| mcpf == null) {
			return true;
		} else {
			return false;
		}
	}

	private boolean imovelPossuiContaPreFaturada(Imovel imovelAtual, Integer anoMes) {
		Conta conta = this.getFachada().obterContaImovel(imovelAtual.getId(), anoMes);
		
		if(conta != null && conta.getId() != null && conta.getDebitoCreditoSituacaoAtual().getId().equals(DebitoCreditoSituacao.PRE_FATURADA)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean imovelPossuiMedicaoHistorico(String idMedicaoTipo, Imovel imovelAtual, Integer anoMes) {
		MedicaoHistorico medicaoHistorico = this.getFachada().pesquisarMedicaoHistorico(imovelAtual.getId(), anoMes, new Integer(idMedicaoTipo));
		
		if (medicaoHistorico != null && medicaoHistorico.getId() != null) {
			return true;
		} else {
			return false;
		}
	}

	private boolean imovelPossuiPoco(
			ImovelMicromedicao imovelMicromedicaoDadosResumo) {
		return imovelMicromedicaoDadosResumo.getImovel() != null 
				&& imovelMicromedicaoDadosResumo.getImovel().getPocoTipo() != null 
				&& imovelMicromedicaoDadosResumo.getImovel().getPocoTipo().getId() != null
				&& imovelMicromedicaoDadosResumo.getImovel().getPocoTipo().getId() != 0;
	}

	private boolean isImovelCondominio(Imovel imovelFiltro) {
		return imovelFiltro != null && ( imovelFiltro.getImovelCondominio() != null || imovelFiltro.getIndicadorImovelCondominio().compareTo(ConstantesSistema.SIM) == 0 );
	}

	private boolean isTipoLeituraImpressaoSimultanea(Imovel imovelAtual,
			ImovelPerfil imovelPerfil) {
		return imovelAtual.getQuadra().getRota().getLeituraTipo().getId().intValue() == LeituraTipo.LEITURA_E_ENTRADA_SIMULTANEA.intValue() && 
				imovelPerfil.getIndicadorGerarDadosLeitura().intValue() == ConstantesSistema.INDICADOR_USO_ATIVO.intValue();
	}

	@SuppressWarnings("rawtypes")
	private Imovel obterImovel(Fachada fachada, String codigoImovel) {
		Imovel imovel = null;
		
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra.rota");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("rotaAlternativa");
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, new Integer(codigoImovel)));
		Collection imoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
		
		if(!imoveis.isEmpty()){
			imovel = (Imovel) imoveis.iterator().next();
		}
		return imovel;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private MovimentoContaPrefaturada obterMovimentoContaPreFaturada(Fachada fachada, String mesAnoPesquisa, String codigoImovel, String idMedicaoTipo) {
		MovimentoContaPrefaturada mcpf = null;
		
		FiltroMovimentoContaPrefaturada filtroMovimentoContaPrefaturada = new FiltroMovimentoContaPrefaturada();
		filtroMovimentoContaPrefaturada.adicionarParametro(new ParametroSimples(FiltroMovimentoContaPrefaturada.MATRICULA, codigoImovel));
		filtroMovimentoContaPrefaturada.adicionarParametro(new ParametroSimples(FiltroMovimentoContaPrefaturada.ID_MEDICAO_TIPO, idMedicaoTipo));
		filtroMovimentoContaPrefaturada.adicionarParametro(new ParametroSimples(FiltroMovimentoContaPrefaturada.ANO_MES_REFERENCIA_PRE_FATURAMENTO, Util.formatarMesAnoComBarraParaAnoMes(mesAnoPesquisa)));
		
		Collection colecaoMovimentoContaPF = fachada.pesquisar(filtroMovimentoContaPrefaturada, MovimentoContaPrefaturada.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoMovimentoContaPF)){
			mcpf = (MovimentoContaPrefaturada) colecaoMovimentoContaPF.iterator().next();
		}
		return mcpf;
	}

	private void limparFormPaginacao(
			LeituraConsumoActionForm leituraConsumoActionForm) {
		leituraConsumoActionForm.setAnalisado("");
		leituraConsumoActionForm.setGerarAviso("");
		leituraConsumoActionForm.setGerarOS("");
		leituraConsumoActionForm.setGerarRelatorio("");
		leituraConsumoActionForm.setMotivoInterferenciaTipo(-1);
	}

	@SuppressWarnings("rawtypes")
	private Collection obterColecaoIdsImovel(Fachada fachada, HttpSession sessao, FaturamentoGrupo faturamentoGrupo,
			FiltroMedicaoHistoricoSql filtroMedicaoHistoricoSql, Integer totalRegistros) {
		
		Collection colecaoIdsImovel;
		if(sessao.getAttribute("colecaoIdsImovelTotal") != null){
			 colecaoIdsImovel = (Collection)sessao.getAttribute("colecaoIdsImovelTotal");
		}else{
			colecaoIdsImovel =  fachada.filtrarExcecoesLeiturasConsumos(faturamentoGrupo,
					filtroMedicaoHistoricoSql, totalRegistros, true, (String)sessao.getAttribute("mesAnoPesquisa"),
							(String)sessao.getAttribute("valorAguaEsgotoInicial"), (String)sessao.getAttribute("valorAguaEsgotoFinal"));
			sessao.setAttribute("colecaoIdsImovelTotal", colecaoIdsImovel);
		}
		return colecaoIdsImovel;
	}

	private Integer obterTotalRegistros(Fachada fachada, HttpSession sessao, FaturamentoGrupo faturamentoGrupo, FiltroMedicaoHistoricoSql filtroMedicaoHistoricoSql) {
		Integer totalRegistros = null;
		if (sessao.getAttribute("totalRegistros") != null && !sessao.getAttribute("totalRegistros").equals("")) {
			totalRegistros = (Integer) sessao.getAttribute("totalRegistros");
		} else {
			totalRegistros = fachada.filtrarExcecoesLeiturasConsumosCount(faturamentoGrupo, filtroMedicaoHistoricoSql,
					(String) sessao.getAttribute("mesAnoPesquisa"),
					(String)sessao.getAttribute("valorAguaEsgotoInicial"), 
					(String)sessao.getAttribute("valorAguaEsgotoFinal"));
			
			sessao.setAttribute("totalRegistros", totalRegistros);
		}
		
		if (totalRegistros == 0) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		return totalRegistros;
	}

	private LeituraConsumoActionForm limparLeituraConsumo(LeituraConsumoActionForm leituraConsumoActionForm) {
		
		leituraConsumoActionForm.setDataLeituraAnteriorFaturamento("");
		leituraConsumoActionForm.setDataLeituraAtualFaturamento("");
		leituraConsumoActionForm.setDataLeituraAtualInformada("");
		leituraConsumoActionForm.setConsumo("");
		leituraConsumoActionForm.setLeituraAnterior("");
		leituraConsumoActionForm.setLeituraAnteriorFaturamento("");
		leituraConsumoActionForm.setLeituraAtualFaturada("");
		leituraConsumoActionForm.setLeituraAtualInformada("");
		leituraConsumoActionForm.setIdAnormalidade("");
		leituraConsumoActionForm.setDescricaoAnormalidade("");
		leituraConsumoActionForm.setIndicadorLeitura("");
		leituraConsumoActionForm.setConsumoMedioImovel("");
		leituraConsumoActionForm.setDiasConsumo("");
		leituraConsumoActionForm.setVarConsumo("");
		leituraConsumoActionForm.setLeituraSituacaoAtual("");
		leituraConsumoActionForm.setIdFuncionario("");
		leituraConsumoActionForm.setConsumoInformado("");
		leituraConsumoActionForm.setConsumoTipo("");
		leituraConsumoActionForm.setConsumoAnormalidadeAbreviada("");
		leituraConsumoActionForm.setMedido("");
		leituraConsumoActionForm.setMotivoInterferenciaTipo(-1);
		leituraConsumoActionForm.setObservacao("");
		
		return leituraConsumoActionForm;
	}

	private boolean possuiAnormalidade(HttpServletRequest httpServletRequest,
			LeituraConsumoActionForm leituraConsumoActionForm) {
		return leituraConsumoActionForm.getIdAnormalidade() != null && !leituraConsumoActionForm.getIdAnormalidade().trim().equals("") 
				&& httpServletRequest.getParameter("pesquisarAnormalidade") != null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private LeituraAnormalidade obterAnormalidadeLeituraEncontrada(Fachada fachada, ImovelMicromedicao imovelMicromedicaoMedicaoResumo) {
		LeituraAnormalidade leituraAnormalidade = null;
		
		FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
		filtroLeituraAnormalidade.adicionarParametro(new ParametroSimples(FiltroLeituraAnormalidade.ID,
						imovelMicromedicaoMedicaoResumo.getMedicaoHistorico().getLeituraAnormalidadeFaturamento().getId()));

		Collection anormalidadeLeituraEncontrada = fachada.pesquisar(filtroLeituraAnormalidade, LeituraAnormalidade.class.getName());
		
		if (anormalidadeLeituraEncontrada != null && !anormalidadeLeituraEncontrada.isEmpty()) {

			leituraAnormalidade = (LeituraAnormalidade) Util.retonarObjetoDeColecao(anormalidadeLeituraEncontrada);
		}
		return leituraAnormalidade;
	}

	private boolean isFaturamentoGrupoAberto(Integer anoMesConsulta, FaturamentoGrupo grupoFaturamento) {
		if (anoMesConsulta >= grupoFaturamento.getAnoMesReferencia())
			return true;
		else
			return false;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void verificarAbrangenciaUsuario(HttpServletRequest httpServletRequest, Usuario usuarioLogado, Imovel imovelAtual) {
		
		Fachada fachada = Fachada.getInstancia();
		
		if (usuarioLogado.getUsuarioAbrangencia().getId().equals(UsuarioAbrangencia.LOCALIDADE)) {
			
			if (!usuarioLogado.getLocalidade().getId().equals(imovelAtual.getLocalidade().getId())) {
				httpServletRequest.setAttribute("habilitaCampos", true);
			}
			
		} else if (usuarioLogado.getUsuarioAbrangencia().getId().equals(UsuarioAbrangencia.GERENCIA_REGIONAL)) {
			
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, imovelAtual.getLocalidade().getId()));
			
			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			
			if (!usuarioLogado.getGerenciaRegional().getId().equals(localidade.getGerenciaRegional().getId())) {
				httpServletRequest.setAttribute("habilitaCampos", true);
			}
			
		} else if (usuarioLogado.getUsuarioAbrangencia().getId().equals(UsuarioAbrangencia.UNIDADE_NEGOCIO)) {
			
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, imovelAtual.getLocalidade().getId()));
			
			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			
			if (!usuarioLogado.getUnidadeNegocio().getId().equals(localidade.getUnidadeNegocio().getId())) {
				httpServletRequest.setAttribute("habilitaCampos", true);
			}
			
		} else if (usuarioLogado.getUsuarioAbrangencia().getId().equals(UsuarioAbrangencia.ELO_POLO)) {
			
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, imovelAtual.getLocalidade().getId()));
			
			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
			
			if (!usuarioLogado.getLocalidadeElo().getId().equals(localidade.getLocalidade().getId())) {
				httpServletRequest.setAttribute("habilitaCampos", true);
			}
			
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void adicionarObjetosSelecionadosColecoes(HttpServletRequest httpServletRequest, HttpSession sessao, Imovel imovel, String observacao) {
		String gerarAviso = httpServletRequest.getParameter("gerarAviso");
		String gerarOS = httpServletRequest.getParameter("gerarOS");
		String gerarRelatorio = httpServletRequest.getParameter("gerarRelatorio");
		Collection colecaoImoveisGerarAviso = (Collection) sessao.getAttribute("colecaoImoveisGerarAviso");
		Collection colecaoImoveisGerarOS = (Collection) sessao.getAttribute("colecaoImoveisGerarOS");
		Collection colecaoImoveisGerarRelatorio = (Collection) sessao.getAttribute("colecaoImoveisGerarRelatorio");
		HashMap<Integer, String> colecaoObservacaoOS = (HashMap<Integer, String>) sessao.getAttribute("colecaoObservacaoOS");
		
		if (gerarAviso != null && !gerarAviso.trim().equals("")) {
			
			if (colecaoImoveisGerarAviso == null) {
				colecaoImoveisGerarAviso = new ArrayList();
			}
			
			if (!colecaoImoveisGerarAviso.contains(imovel.getId())) {
				colecaoImoveisGerarAviso.add(imovel.getId());
			} else {
				sessao.setAttribute("gerarAviso", "1");
			}

			sessao.setAttribute("colecaoImoveisGerarAviso", colecaoImoveisGerarAviso);
		} 
		// Caso não esteja selecionado, verifica se ele estava na coleção e remove-o
		else {
			if (colecaoImoveisGerarAviso != null && !colecaoImoveisGerarAviso.isEmpty()) {
				if (colecaoImoveisGerarAviso.contains(imovel.getId())) {
					colecaoImoveisGerarAviso.remove(imovel.getId());
					sessao.setAttribute("colecaoImoveisGerarAviso", colecaoImoveisGerarAviso);
				}
			}
		}

		// Verifica se o usuário selecionou este imóvel para geração de OS
		if (gerarOS != null && !gerarOS.trim().equals("")) {
			
			if (colecaoImoveisGerarOS == null) {
				colecaoImoveisGerarOS = new ArrayList();
				colecaoObservacaoOS = new HashMap<Integer, String>();
			}
			
			if (!colecaoImoveisGerarOS.contains(imovel)) {
				colecaoImoveisGerarOS.add(imovel);
				colecaoObservacaoOS.put(imovel.getId(),observacao);
			}
			sessao.setAttribute("observacao", observacao);
			sessao.setAttribute("colecaoImoveisGerarOS", colecaoImoveisGerarOS);
			sessao.setAttribute("colecaoObservacaoOS", colecaoObservacaoOS);
		} 
		// Caso não esteja selecionado, verifica se ele estava na coleção e remove-o
		else {
			if (colecaoImoveisGerarOS != null && !colecaoImoveisGerarOS.isEmpty()) {
				if (colecaoImoveisGerarOS.contains(imovel)) {
					colecaoImoveisGerarOS.remove(imovel);
					colecaoObservacaoOS.remove(imovel.getId());
					sessao.setAttribute("colecaoImoveisGerarOS", colecaoImoveisGerarOS);
					sessao.setAttribute("colecaoObservacaoOS", colecaoObservacaoOS);
				}
			}
		}
		
		// Verifica se o usuário selecionou este imóvel para geração do relatório
		if (gerarRelatorio != null && !gerarRelatorio.trim().equals("")) {
			
			if (colecaoImoveisGerarRelatorio == null) {
				colecaoImoveisGerarRelatorio = new ArrayList();
			}
			
			if (!colecaoImoveisGerarRelatorio.contains(imovel.getId())) {
				colecaoImoveisGerarRelatorio.add(imovel.getId());
			} 

			sessao.setAttribute("colecaoImoveisGerarRelatorio", colecaoImoveisGerarRelatorio);
		} 
		// Caso não esteja selecionado, verifica se ele estava na coleção e remove-o
		else {
			if (colecaoImoveisGerarRelatorio != null && !colecaoImoveisGerarRelatorio.isEmpty()) {
				if (colecaoImoveisGerarRelatorio.contains(imovel.getId())) {
					colecaoImoveisGerarRelatorio.remove(imovel.getId());
					sessao.setAttribute("colecaoImoveisGerarRelatorio", colecaoImoveisGerarRelatorio);
				}
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Collection<CalculoConsumoHelper> carregarComboCalculoConsumo(MedicaoHistorico medicaoHistorico, Imovel imovel, Integer anoMesReferencia) {

		Fachada fachada = Fachada.getInstancia();
		
		Collection<CalculoConsumoHelper> retorno = new ArrayList<CalculoConsumoHelper>();
		
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.CONSUMO_TARIFA);
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, imovel.getId()));
		
		Collection colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
		
		imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);
		
		// Consumo Médio
		CalculoConsumoHelper calculoConsumoMedio = new CalculoConsumoHelper();
		
		calculoConsumoMedio.setDescricao("MEDIA");
		calculoConsumoMedio.setConsumo(medicaoHistorico.getConsumoMedioHidrometro());
		
		retorno.add(calculoConsumoMedio);
		
		// Consumo Mínimo
		CalculoConsumoHelper calculoConsumoMinimo = new CalculoConsumoHelper();
		
		Integer consumoMinimo = fachada.obterConsumoMinimoLigacao(imovel, null);
		
		calculoConsumoMinimo.setDescricao("MINIMO");
		calculoConsumoMinimo.setConsumo(consumoMinimo);
		
		retorno.add(calculoConsumoMinimo);
		
		// Consumo Não Medido
		CalculoConsumoHelper calculoConsumoNaoMedido = new CalculoConsumoHelper();
		
		Integer consumoNaoMedido = fachada.obterConsumoNaoMedidoSemTarifa(imovel.getId(), anoMesReferencia);
		
		calculoConsumoNaoMedido.setDescricao("N MED.");
		calculoConsumoNaoMedido.setConsumo(consumoNaoMedido);
		
		retorno.add(calculoConsumoNaoMedido);
		
		return retorno;
	}
	
	private boolean verificarRotaFinalizada(Integer idRota, String mesAnoPesquisa) {
		
		Fachada fachada = Fachada.getInstancia();
	
		Integer anoMesFaturamento = Util.formatarMesAnoComBarraParaAnoMes(mesAnoPesquisa);
        
		Object[] dadosArquivoTextoRoteiroEmpresa = 
			fachada.pesquisarArquivoTextoRoteiroEmpresa( idRota, anoMesFaturamento);
		
		if (dadosArquivoTextoRoteiroEmpresa != null){
			Integer idSituacaoTransmissaoLeitura = (Integer)dadosArquivoTextoRoteiroEmpresa[1];
			if( !idSituacaoTransmissaoLeitura.equals( SituacaoTransmissaoLeitura.DISPONIVEL ) &&
				 !idSituacaoTransmissaoLeitura.equals( SituacaoTransmissaoLeitura.LIBERADO ) &&
				 !idSituacaoTransmissaoLeitura.equals( SituacaoTransmissaoLeitura.EM_CAMPO ) && 
				 !idSituacaoTransmissaoLeitura.equals( SituacaoTransmissaoLeitura.FINALIZADO_NAO_TRANSMITIDO ) ){
				return true;
			} 
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	private boolean verificarReleitura(Integer anoMesFaturamento, Integer idImovel) {
		
		Fachada fachada = Fachada.getInstancia();
			
		FiltroReleituraMobile filtroReleituraMobile = new FiltroReleituraMobile();
		filtroReleituraMobile.adicionarParametro(new ParametroSimples(FiltroReleituraMobile.ANO_MES_FATURAMENTO, anoMesFaturamento));
		filtroReleituraMobile.adicionarParametro(new ParametroSimples(FiltroReleituraMobile.ID_IMOVEL, idImovel));
		
        
		Collection<ReleituraMobile> colecaoReleituraMobile = fachada.pesquisar( filtroReleituraMobile, ReleituraMobile.class.getName());
		
		if (colecaoReleituraMobile != null && !colecaoReleituraMobile.isEmpty()){
			return true;
		}
		return false;
	}
}
