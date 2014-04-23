package gcom.gui.arrecadacao;

import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.FiltroDevolucao;
import gcom.arrecadacao.FiltroDevolucaoHistorico;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.DocumentoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarDevolucaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarDevolucaoActionForm filtrarDevolucaoActionForm = (FiltrarDevolucaoActionForm) actionForm;

		Collection colecaoClientesDevolucoes = null;
		Collection colecaoClientesDevolucoesHistorico = null;
		// Collection colecaoClientesContasDevolucoes = null;
		// Collection colecaoClientesGuiasPagamentosDevolucoes = null;
		// Collection colecaoClientesDebitosACobrarDevolucoes = null;
		Collection colecaoImoveisDevolucoes = null;
		Collection colecaoImoveisDevolucoesHistorico = null;
		// Collection colecaoImoveisContasDevolucoes = null;
		// Collection colecaoImoveisGuiasPagamentosDevolucoes = null;
		// Collection colecaoImoveisDebitosACobrarDevolucoes = null;
		Collection colecaoAvisosBancariosDevolucoes = null;
		Collection colecaoAvisosBancariosDevolucoesHistorico = null;
		// Collection colecaoAvisosBancariosContasDevolucoes = null;
		// Collection colecaoAvisosBancariosGuiasPagamentosDevolucoes = null;
		// Collection colecaoAvisosBancariosDebitosACobrarDevolucoes = null;
		Collection colecaoLocalidadeDevolucoes = null;
		Collection colecaoLocalidadeDevolucoesHistorico = null;

		// Recupera os parâmetros do form
		String idImovel = filtrarDevolucaoActionForm.getIdImovel();
		String idCliente = filtrarDevolucaoActionForm.getIdCliente();
		String idTipoRelacao = filtrarDevolucaoActionForm.getClienteRelacaoTipo();
		String idLocalidadeInicial = filtrarDevolucaoActionForm.getIdLocalidadeInicial();
		String idLocalidadeFinal = filtrarDevolucaoActionForm.getIdLocalidadeFinal();
		String idAvisoBancario = filtrarDevolucaoActionForm.getIdAvisoBancario();
		String periodoArrecadacaoInicial = filtrarDevolucaoActionForm.getPeriodoArrecadacaoInicio();
		String periodoArrecadacaoFinal = filtrarDevolucaoActionForm.getPeriodoArrecadacaoFim();
		String dataDevolucaoInicial = filtrarDevolucaoActionForm.getDataDevolucaoInicio();
		String dataDevolucaoFinal = filtrarDevolucaoActionForm.getDataDevolucaoFim();
		String[] idsDevolucoesSituacoes = filtrarDevolucaoActionForm.getDevolucaoSituacao();
		String[] idsCreditosTipos = filtrarDevolucaoActionForm.getCreditoTipo();
		String[] idsDocumentosTipos = filtrarDevolucaoActionForm.getDocumentoTipo();
		String atualizar = httpServletRequest.getParameter("atualizar");
		
		String indicadorOpcaoDevolucao = "" + filtrarDevolucaoActionForm
				.getIndicadorOpcaoDevolucao();
		
		String tela = (String) sessao.getAttribute("tela");
		
		Boolean telaConsultaDevolucaoAtual = false;
		Boolean telaConsultaDevolucaoHistorico = false;
		if ((tela == null || tela.equals("")
				&&(indicadorOpcaoDevolucao != null &&
				!indicadorOpcaoDevolucao.equals("")))) {
			//filtrar vai para tela de Consultar Devoluções
			if (indicadorOpcaoDevolucao.equals("1")){
				//consulta a tabela devolucao
				telaConsultaDevolucaoAtual = true;
			}else if (indicadorOpcaoDevolucao.equals("2")){
				//consulta a tabela devolucao_historico
				telaConsultaDevolucaoHistorico = true;
			}else{
				//consulta as tabelas devolucao e devolucao_historico
				telaConsultaDevolucaoAtual = true;
				telaConsultaDevolucaoHistorico = true;
			}
		}else{
			//filtrar vai para tela de Manter Devoluções
			telaConsultaDevolucaoAtual = true;
		}
		
		
		// Alterado para verificar se o mes ano da data inicial ou final é maior que o mes ano do faturamento corrente. 
		// Fernanda Paiva 30/08/2006
		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();

		Collection<SistemaParametro> colecaoSistemaParametro = fachada.pesquisar(filtroSistemaParametro,
						SistemaParametro.class.getName());
		
		SistemaParametro sistemaParametro = colecaoSistemaParametro.iterator().next();

		Integer mesAnoFaturamento = sistemaParametro.getAnoMesFaturamento();
		
		Integer mesAnoDataInicial = null;
		Integer mesAnoDataFinal = null;
		if(filtrarDevolucaoActionForm.getDataDevolucaoInicio() != null && !filtrarDevolucaoActionForm.getDataDevolucaoInicio().equalsIgnoreCase(""))
		{
			mesAnoDataInicial = new Integer(filtrarDevolucaoActionForm.getDataDevolucaoInicio().substring(6,10)+filtrarDevolucaoActionForm.getDataDevolucaoInicio().substring(3,5));
			if(Util.compararAnoMesReferencia(mesAnoDataInicial,mesAnoFaturamento,">"))
			{
				throw new ActionServletException(
				"atencao.mes.ano.inicial.maior.mes.ano.faturamento.corrente",
					null,Util.formatarAnoMesParaMesAno(mesAnoFaturamento.toString())); 
			}
		}
		if(filtrarDevolucaoActionForm.getDataDevolucaoFim() != null && !filtrarDevolucaoActionForm.getDataDevolucaoFim().equalsIgnoreCase(""))
		{
			mesAnoDataFinal = new Integer(filtrarDevolucaoActionForm.getDataDevolucaoFim().substring(6,10)+filtrarDevolucaoActionForm.getDataDevolucaoFim().substring(3,5));
			if(Util.compararAnoMesReferencia(mesAnoDataFinal,mesAnoFaturamento,">"))
			{
				throw new ActionServletException(
				"atencao.mes.ano.final.maior.mes.ano.faturamento.corrente",
					null,Util.formatarAnoMesParaMesAno(mesAnoFaturamento.toString())); 
			}
		}
		// [FS0003] - Verificar se um dos campos obrigatórios foi informado
		// Caso o usuário não tenha informado nenhum desses campos faz um
		// crítica solicitando que ele digite algum deles
		FiltroDevolucao filtroDevolucao = new FiltroDevolucao();
		FiltroDevolucaoHistorico filtroDevolucaoHistorico = new FiltroDevolucaoHistorico();
		int flag = 0;
		
		if(sessao.getAttribute("telaManter") == null)
		{
			if ((idImovel == null || idImovel.trim().equals(""))
					&& (idCliente == null || idCliente.trim().equals(""))
					&& (idAvisoBancario == null || idAvisoBancario.trim()
							.equals(""))
					&& ((idLocalidadeInicial == null || idLocalidadeInicial.trim()
							.equals("")) || (idLocalidadeFinal == null || idLocalidadeFinal
							.trim().equals("")))) {
				throw new ActionServletException(
						"atencao.parametros.obrigatorios.nao.informados");
			}
	
			// cria o filtro para Tabela Auxiliar
			
			boolean peloMenosUmParametroInformado = false;
	
			// Período Arrecadação
	
			if (periodoArrecadacaoInicial != null
					&& !periodoArrecadacaoInicial.equals("")) {
				peloMenosUmParametroInformado = true;
	
				if (periodoArrecadacaoFinal == null
						|| periodoArrecadacaoFinal.equals("")) {
					periodoArrecadacaoFinal = periodoArrecadacaoInicial;
				}
	
				String periodoArrecadacaoInicialFormatado = Util
						.formatarMesAnoParaAnoMesSemBarra(periodoArrecadacaoInicial);
				String periodoArrecadacaoFinalFormatado = Util
						.formatarMesAnoParaAnoMesSemBarra(periodoArrecadacaoFinal);
	
				if (telaConsultaDevolucaoAtual){
					filtroDevolucao.adicionarParametro(new Intervalo(
							FiltroDevolucao.ANO_MES_REFERENCIA_ARRECADACAO,
							periodoArrecadacaoInicialFormatado,
							periodoArrecadacaoFinalFormatado));
				}
				
				if (telaConsultaDevolucaoHistorico){
					filtroDevolucaoHistorico.adicionarParametro(new Intervalo(
							FiltroDevolucao.ANO_MES_REFERENCIA_ARRECADACAO,
							periodoArrecadacaoInicialFormatado,
							periodoArrecadacaoFinalFormatado));
				}
			}
	
			// Período Data Devolução
	
			if (dataDevolucaoInicial != null && !dataDevolucaoInicial.equals("")) {
				peloMenosUmParametroInformado = true;
	
				if (dataDevolucaoFinal == null || dataDevolucaoFinal.equals("")) {
					dataDevolucaoFinal = dataDevolucaoInicial;
				}
	
				Date dataDevolucaoInicialFormatada = Util
						.converteStringParaDate(dataDevolucaoInicial);
				String data1 = Util.recuperaDataInvertida(dataDevolucaoInicialFormatada);
				
				if (data1 != null && !data1.equals("") && data1.trim().length() == 8) {

					data1 = data1.substring(0, 4) + "-" + data1.substring(4, 6) + "-"
							+ data1.substring(6, 8);

				}
				Date dataDevolucaoFinalFormatada = Util
						.converteStringParaDate(dataDevolucaoFinal);
				String data2 = Util.recuperaDataInvertida(dataDevolucaoFinalFormatada);

				if (data2 != null && !data2.equals("") && data2.trim().length() == 8) {

					data2 = data2.substring(0, 4) + "-" + data2.substring(4, 6) + "-"
							+ data2.substring(6, 8);

				}
				if (telaConsultaDevolucaoAtual){
					filtroDevolucao
							.adicionarParametro(new Intervalo(
									FiltroDevolucao.DATA_DEVOLUCAO,
									data1,
									data2));
				}
				
				if (telaConsultaDevolucaoHistorico){
					filtroDevolucaoHistorico
					.adicionarParametro(new Intervalo(
							FiltroDevolucao.DATA_DEVOLUCAO,
							data1,
							data2));
				}
				
				/*filtroDevolucao
						.adicionarParametro(new Intervalo(
								FiltroDevolucao.DATA_DEVOLUCAO,
								dataDevolucaoInicial,
								dataDevolucaoFinal));*/
				
			}
	
			// Situação da Devolução
	
			int i = 0;
	
			if (idsDevolucoesSituacoes != null) {
	
				while (i < idsDevolucoesSituacoes.length) {
	
					if (!idsDevolucoesSituacoes[i].equals("")) {
						peloMenosUmParametroInformado = true;
						if (i + 1 < idsDevolucoesSituacoes.length) {
							if (telaConsultaDevolucaoAtual){
								filtroDevolucao
										.adicionarParametro(new ParametroSimples(
												FiltroDevolucao.DEVOLUCAO_SITUACAO_ATUAL_ID,
												idsDevolucoesSituacoes[i],
												ConectorOr.CONECTOR_OR,
												(idsDevolucoesSituacoes.length)));
							}
							
							if (telaConsultaDevolucaoHistorico){
								filtroDevolucaoHistorico
								.adicionarParametro(new ParametroSimples(
										FiltroDevolucao.DEVOLUCAO_SITUACAO_ATUAL_ID,
										idsDevolucoesSituacoes[i],
										ConectorOr.CONECTOR_OR,
										(idsDevolucoesSituacoes.length)));
							}
						} else {
							if (telaConsultaDevolucaoAtual){
								filtroDevolucao
										.adicionarParametro(new ParametroSimples(
												FiltroDevolucao.DEVOLUCAO_SITUACAO_ATUAL_ID,
												idsDevolucoesSituacoes[i]));
							}
							
							if (telaConsultaDevolucaoHistorico){
								filtroDevolucaoHistorico
								.adicionarParametro(new ParametroSimples(
										FiltroDevolucao.DEVOLUCAO_SITUACAO_ATUAL_ID,
										idsDevolucoesSituacoes[i]));
							}
						}
					}
	
					i++;
				}
	
			}
	
			// Tipo de Crédito
	
			i = 0;
	
			if (idsCreditosTipos != null) {
	
				while (i < idsCreditosTipos.length) {
	
					if (!idsCreditosTipos[i].equals("")) {
						peloMenosUmParametroInformado = true;
	
						if (i + 1 < idsCreditosTipos.length) {
	
							if (telaConsultaDevolucaoAtual){
								filtroDevolucao
										.adicionarParametro(new ParametroSimples(
												FiltroDevolucao.GUIA_DEVOLUCAO_CREDITO_TIPO_ID,
												idsCreditosTipos[i],
												ConectorOr.CONECTOR_OR,
												(idsCreditosTipos.length)));
							}
							if (telaConsultaDevolucaoHistorico){
								filtroDevolucaoHistorico
								.adicionarParametro(new ParametroSimples(
										FiltroDevolucao.GUIA_DEVOLUCAO_CREDITO_TIPO_ID,
										idsCreditosTipos[i],
										ConectorOr.CONECTOR_OR,
										(idsCreditosTipos.length)));
							}
	
						} else {
							if (telaConsultaDevolucaoAtual){
								filtroDevolucao
										.adicionarParametro(new ParametroSimples(
												FiltroDevolucao.GUIA_DEVOLUCAO_CREDITO_TIPO_ID,
												idsCreditosTipos[i]));
							}	
							
							if (telaConsultaDevolucaoHistorico){
								filtroDevolucaoHistorico
								.adicionarParametro(new ParametroSimples(
										FiltroDevolucao.GUIA_DEVOLUCAO_CREDITO_TIPO_ID,
										idsCreditosTipos[i]));
							}
						}
					}
	
					i++;
				}
	
			}
	
			// Cliente
			if (idCliente != null && !idCliente.trim().equalsIgnoreCase("")) {
	
				FiltroCliente filtroCliente = new FiltroCliente();
				filtroCliente.adicionarParametro(new ParametroSimples(
						FiltroCliente.ID, idCliente));
				filtroCliente
						.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
				filtroCliente.adicionarCaminhoParaCarregamentoEntidade("profissao");
				filtroCliente
						.adicionarCaminhoParaCarregamentoEntidade("ramoAtividade");
	
				Collection colecaoClientes = fachada.pesquisar(filtroCliente,
						Cliente.class.getName());
	
				if (colecaoClientes != null && !colecaoClientes.isEmpty()) {
					Cliente cliente = (Cliente) ((List) colecaoClientes).get(0);
					sessao.setAttribute("cliente", cliente);
				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Código de Cliente");
				}
	
				peloMenosUmParametroInformado = true;
	
				if (telaConsultaDevolucaoAtual){
					filtroDevolucao.adicionarParametro(new ParametroSimples(
							FiltroDevolucao.CLIENTE_ID, idCliente));
				}
				
				if (telaConsultaDevolucaoHistorico){
					filtroDevolucaoHistorico.adicionarParametro(new ParametroSimples(
							FiltroDevolucao.CLIENTE_ID, idCliente));
				}
				
				i = 0;
	
				if (idsDocumentosTipos != null) {
	
					while (i < idsDocumentosTipos.length) {
	
						if (!idsDocumentosTipos[i].equals("")) {
	
							if (i + 1 < idsDocumentosTipos.length) {
	
								if (telaConsultaDevolucaoAtual){
									filtroDevolucao
											.adicionarParametro(new ParametroSimples(
													FiltroDevolucao.GUIA_DEVOLUCAO_DOCUMENTO_TIPO_ID,
													idsDocumentosTipos[i],
													ConectorOr.CONECTOR_OR,
													(idsDocumentosTipos.length)));
								}
								
								if (telaConsultaDevolucaoHistorico){
									filtroDevolucaoHistorico
									.adicionarParametro(new ParametroSimples(
											FiltroDevolucao.GUIA_DEVOLUCAO_DOCUMENTO_TIPO_ID,
											idsDocumentosTipos[i],
											ConectorOr.CONECTOR_OR,
											(idsDocumentosTipos.length)));
								}
	
							} else {
								if (telaConsultaDevolucaoAtual){
									filtroDevolucao
											.adicionarParametro(new ParametroSimples(
													FiltroDevolucao.GUIA_DEVOLUCAO_DOCUMENTO_TIPO_ID,
													idsDocumentosTipos[i]));
								}
								
								if (telaConsultaDevolucaoHistorico){
									filtroDevolucaoHistorico
									.adicionarParametro(new ParametroSimples(
											FiltroDevolucao.GUIA_DEVOLUCAO_DOCUMENTO_TIPO_ID,
											idsDocumentosTipos[i]));
								}
							}
	
							// Cliente com Documento Tipo = Conta
							if (idsDocumentosTipos[i].equals(DocumentoTipo.CONTA)) {
								if (telaConsultaDevolucaoAtual){	
								filtroDevolucao
											.adicionarParametro(new ParametroSimples(
													FiltroDevolucao.DEVOLUCAO_GUIA_DEVOLUCAO_CONTA_CLIENTE_CONTAS_CLIENTE_ID,
													idCliente));
								}
								
								if (telaConsultaDevolucaoHistorico){
									filtroDevolucaoHistorico
									.adicionarParametro(new ParametroSimples(
											FiltroDevolucao.DEVOLUCAO_GUIA_DEVOLUCAO_CONTA_CLIENTE_CONTAS_CLIENTE_ID,
											idCliente));
								}
								
								if (idTipoRelacao != null
										&& !idTipoRelacao.trim().equalsIgnoreCase(
												"")) {
									if (telaConsultaDevolucaoAtual){
										filtroDevolucao
												.adicionarParametro(new ParametroSimples(
														FiltroDevolucao.DEVOLUCAO_GUIA_DEVOLUCAO_CONTA_CLIENTE_CONTAS_CLIENTE_RELACAO_TIPO_ID,
														idTipoRelacao));
									}
									
									if (telaConsultaDevolucaoHistorico){
										filtroDevolucaoHistorico
										.adicionarParametro(new ParametroSimples(
												FiltroDevolucao.DEVOLUCAO_GUIA_DEVOLUCAO_CONTA_CLIENTE_CONTAS_CLIENTE_RELACAO_TIPO_ID,
												idTipoRelacao));
									}
	
								}
	
							}
	
							// Cliente com Documento Tipo = Guia de Pagamento
							else if (idsDocumentosTipos[i]
									.equals(DocumentoTipo.GUIA_PAGAMENTO)) {
								if (telaConsultaDevolucaoAtual){
									filtroDevolucao
											.adicionarParametro(new ParametroSimples(
													FiltroDevolucao.DEVOLUCAO_GUIA_DEVOLUCAO_GUIA_PAGAMENTO_CLIENTE_GUIA_PAGAMENTO_CLIENTE_ID,
													idCliente));
								}
								if (telaConsultaDevolucaoHistorico){
									filtroDevolucaoHistorico
									.adicionarParametro(new ParametroSimples(
											FiltroDevolucao.DEVOLUCAO_GUIA_DEVOLUCAO_GUIA_PAGAMENTO_CLIENTE_GUIA_PAGAMENTO_CLIENTE_ID,
											idCliente));
								}
	
								if (idTipoRelacao != null
										&& !idTipoRelacao.trim().equalsIgnoreCase(
												"")) {
									if (telaConsultaDevolucaoAtual){
										filtroDevolucao
												.adicionarParametro(new ParametroSimples(
														FiltroDevolucao.DEVOLUCAO_GUIA_DEVOLUCAO_GUIA_PAGAMENTO_CLIENTE_GUIA_PAGAMENTO_CLIENTE_RELACAO_TIPO_ID,
														idTipoRelacao));
									}
									if (telaConsultaDevolucaoHistorico){
										filtroDevolucaoHistorico
										.adicionarParametro(new ParametroSimples(
												FiltroDevolucao.DEVOLUCAO_GUIA_DEVOLUCAO_GUIA_PAGAMENTO_CLIENTE_GUIA_PAGAMENTO_CLIENTE_RELACAO_TIPO_ID,
												idTipoRelacao));
									}
	
								} else {
									if (telaConsultaDevolucaoAtual){
										filtroDevolucao
												.adicionarParametro(new ParametroSimples(
														FiltroDevolucao.DEVOLUCAO_GUIA_DEVOLUCAO_GUIA_PAGAMENTO_CLIENTE_GUIA_PAGAMENTO_GUIA_PAGAMENTO_ID,
														idTipoRelacao));
									}
									if (telaConsultaDevolucaoHistorico){
										filtroDevolucaoHistorico
										.adicionarParametro(new ParametroSimples(
												FiltroDevolucao.DEVOLUCAO_GUIA_DEVOLUCAO_GUIA_PAGAMENTO_CLIENTE_GUIA_PAGAMENTO_GUIA_PAGAMENTO_ID,
												idTipoRelacao));
									}
	
								}
	
							}
	
							// Cliente com Documento Tipo = Débito a Cobrar
							else if (idsDocumentosTipos[i]
									.equals(DocumentoTipo.DEBITO_A_COBRAR)) {
								
								if (telaConsultaDevolucaoAtual){
									filtroDevolucao
										.adicionarParametro(new ParametroSimples(
												FiltroDevolucao.DEVOLUCAO_IMOVEL_CLIENTE_IMOVEIS_CLIENTE_ID,
												idCliente));
								}
								
								if (telaConsultaDevolucaoHistorico){
									filtroDevolucaoHistorico
										.adicionarParametro(new ParametroSimples(
											FiltroDevolucao.DEVOLUCAO_IMOVEL_CLIENTE_IMOVEIS_CLIENTE_ID,
											idCliente));
								}
								if (idTipoRelacao != null
										&& !idTipoRelacao.trim().equalsIgnoreCase(
												"")) {
									if (telaConsultaDevolucaoAtual){
										filtroDevolucao
												.adicionarParametro(new ParametroSimples(
														FiltroDevolucao.DEVOLUCAO_IMOVEL_CLIENTE_IMOVEIS_CLIENTE_RELACAO_TIPO_ID,
														idTipoRelacao));
									}
									if (telaConsultaDevolucaoHistorico){
										filtroDevolucaoHistorico
										.adicionarParametro(new ParametroSimples(
												FiltroDevolucao.DEVOLUCAO_IMOVEL_CLIENTE_IMOVEIS_CLIENTE_RELACAO_TIPO_ID,
												idTipoRelacao));
									}
	
								}
	
							}
	
						}
	
						i++;
					}
				}
	
				if (telaConsultaDevolucaoAtual){
					filtroDevolucao
							.adicionarCaminhoParaCarregamentoEntidade("avisoBancario.arrecadador");
					filtroDevolucao
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.documentoTipo");
					filtroDevolucao
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.conta");
					filtroDevolucao
							.adicionarCaminhoParaCarregamentoEntidade("devolucaoSituacaoAtual");
					filtroDevolucao
							.adicionarCaminhoParaCarregamentoEntidade("devolucaoSituacaoAnterior");
					filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade("imovel");
					filtroDevolucao
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.conta");
					filtroDevolucao
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.guiaPagamento.debitoTipo");
					filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade("cliente");
					filtroDevolucao
							.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
					filtroDevolucao
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.debitoACobrarGeral.debitoACobrar.debitoTipo");
					filtroDevolucao
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.debitoACobrarGeral.debitoACobrar.imovel");
		
					colecaoClientesDevolucoes = fachada
							.pesquisarDevolucao(filtroDevolucao);
					if (colecaoClientesDevolucoes != null
							&& !colecaoClientesDevolucoes.isEmpty()) {
						sessao.setAttribute("colecaoClientesDevolucoes",
								colecaoClientesDevolucoes);
					}
				}
				
				if (telaConsultaDevolucaoHistorico){
					filtroDevolucaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("avisoBancario.arrecadador");
					filtroDevolucaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.documentoTipo");
					filtroDevolucaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.conta");
					filtroDevolucaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("devolucaoSituacaoAtual");
					filtroDevolucaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("devolucaoSituacaoAnterior");
					filtroDevolucaoHistorico.adicionarCaminhoParaCarregamentoEntidade("imovel");
					filtroDevolucaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.conta");
					filtroDevolucaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.guiaPagamento.debitoTipo");
					filtroDevolucaoHistorico.adicionarCaminhoParaCarregamentoEntidade("cliente");
					filtroDevolucaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
					filtroDevolucaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.debitoACobrarGeral.debitoACobrar.debitoTipo");
					filtroDevolucaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.debitoACobrarGeral.debitoACobrar.imovel");
	
									
					colecaoClientesDevolucoesHistorico = fachada
							.pesquisarDevolucaoHistorico(filtroDevolucaoHistorico);
					if (colecaoClientesDevolucoesHistorico != null
							&& !colecaoClientesDevolucoesHistorico.isEmpty()) {
						sessao.setAttribute("colecaoClientesDevolucoesHistorico",
								colecaoClientesDevolucoesHistorico);
					}
				}
			}
	
			// Imóvel
			if (idImovel != null && !idImovel.trim().equalsIgnoreCase("")) {
				peloMenosUmParametroInformado = true;
				
				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, idImovel));
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("setorComercial.municipio.unidadeFederacao");
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
	
				Collection colecaoImoveis = fachada.pesquisar(filtroImovel,
						Imovel.class.getName());
	
				if (colecaoImoveis != null && !colecaoImoveis.isEmpty()) {
					Imovel imovel = (Imovel) ((List) colecaoImoveis).get(0);
					sessao.setAttribute("imovel", imovel);
				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Matrícula");
				}
	
				if (telaConsultaDevolucaoAtual){
					filtroDevolucao.adicionarParametro(new ParametroSimples(
							FiltroDevolucao.IMOVEL_ID, idImovel));
				}
				if (telaConsultaDevolucaoHistorico){
					filtroDevolucaoHistorico.adicionarParametro(new ParametroSimples(
							FiltroDevolucao.IMOVEL_ID, idImovel));
				}
				i = 0;
	
				if (idsDocumentosTipos != null) {
	
					while (i < idsDocumentosTipos.length) {
	
						if (!idsDocumentosTipos[i].equals("")) {
	
							if (i + 1 < idsDocumentosTipos.length) {
								if (telaConsultaDevolucaoAtual){
									filtroDevolucao
											.adicionarParametro(new ParametroSimples(
													FiltroDevolucao.GUIA_DEVOLUCAO_DOCUMENTO_TIPO_ID,
													idsDocumentosTipos[i],
													ConectorOr.CONECTOR_OR,
													idsDocumentosTipos.length));
								}
								
								if (telaConsultaDevolucaoHistorico){
									filtroDevolucaoHistorico
									.adicionarParametro(new ParametroSimples(
											FiltroDevolucao.GUIA_DEVOLUCAO_DOCUMENTO_TIPO_ID,
											idsDocumentosTipos[i],
											ConectorOr.CONECTOR_OR,
											idsDocumentosTipos.length));
								}
	
							} else {
								if (telaConsultaDevolucaoAtual){
									filtroDevolucao
											.adicionarParametro(new ParametroSimples(
													FiltroDevolucao.GUIA_DEVOLUCAO_DOCUMENTO_TIPO_ID,
													idsDocumentosTipos[i]));
								}
								if (telaConsultaDevolucaoHistorico){
									filtroDevolucaoHistorico
									.adicionarParametro(new ParametroSimples(
											FiltroDevolucao.GUIA_DEVOLUCAO_DOCUMENTO_TIPO_ID,
											idsDocumentosTipos[i]));
								}
							}
	
						}
	
						i++;
					}
	
				}
				if (telaConsultaDevolucaoAtual){
					filtroDevolucao
							.adicionarCaminhoParaCarregamentoEntidade("avisoBancario.arrecadador");
					filtroDevolucao
							.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
					filtroDevolucao
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.documentoTipo");
					filtroDevolucao
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.conta");
					filtroDevolucao
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.guiaPagamento.debitoTipo");
					filtroDevolucao
							.adicionarCaminhoParaCarregamentoEntidade("devolucaoSituacaoAtual");
					filtroDevolucao
							.adicionarCaminhoParaCarregamentoEntidade("devolucaoSituacaoAnterior");
					filtroDevolucao
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.debitoACobrarGeral.debitoACobrar.debitoTipo");
					filtroDevolucao
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.debitoACobrarGeral.debitoACobrar.imovel");
					filtroDevolucao.adicionarCaminhoParaCarregamentoEntidade("cliente");
					
					colecaoImoveisDevolucoes = fachada.pesquisarDevolucao(filtroDevolucao);
					
					if (colecaoImoveisDevolucoes != null
							&& !colecaoImoveisDevolucoes.isEmpty()) {
						sessao.setAttribute("colecaoImoveisDevolucoes",
								colecaoImoveisDevolucoes);
					}
				}
				
				if (telaConsultaDevolucaoHistorico){
					filtroDevolucaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("avisoBancario.arrecadador");
					filtroDevolucaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
					filtroDevolucaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.documentoTipo");
					filtroDevolucaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.conta");
					filtroDevolucaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.guiaPagamento.debitoTipo");
					filtroDevolucaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("devolucaoSituacaoAtual");
					filtroDevolucaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("devolucaoSituacaoAnterior");
					filtroDevolucaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.debitoACobrarGeral.debitoACobrar.debitoTipo");
					filtroDevolucaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.debitoACobrarGeral.debitoACobrar.imovel");
					filtroDevolucaoHistorico.adicionarCaminhoParaCarregamentoEntidade("cliente");
					
					colecaoImoveisDevolucoesHistorico = fachada.pesquisarDevolucaoHistorico(filtroDevolucaoHistorico);
					
					if (colecaoImoveisDevolucoesHistorico != null
							&& !colecaoImoveisDevolucoesHistorico.isEmpty()) {
						sessao.setAttribute("colecaoImoveisDevolucoesHistorico",
								colecaoImoveisDevolucoesHistorico);
					}
				}
			}
	
			// Aviso Bancário
			if (idAvisoBancario != null
					&& !idAvisoBancario.trim().equalsIgnoreCase("")) {
				peloMenosUmParametroInformado = true;
	
				FiltroAvisoBancario filtroAvisoBancario = new FiltroAvisoBancario();
				filtroAvisoBancario.adicionarParametro(new ParametroSimples(
						FiltroAvisoBancario.ID, idAvisoBancario));
	
				filtroAvisoBancario
						.adicionarCaminhoParaCarregamentoEntidade("arrecadador.cliente");
	
				Collection colecaoAvisoBancarios = fachada.pesquisar(
						filtroAvisoBancario, AvisoBancario.class.getName());
	
				if (colecaoAvisoBancarios != null
						&& !colecaoAvisoBancarios.isEmpty()) {
					AvisoBancario avisoBancario = (AvisoBancario) ((List) colecaoAvisoBancarios)
							.get(0);
					sessao.setAttribute("avisoBancario", avisoBancario);
				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "aviso bancario");
				}
	
				if (telaConsultaDevolucaoAtual){
					filtroDevolucao.adicionarParametro(new ParametroSimples(
							FiltroDevolucao.AVISO_BANCARIO_ID, idAvisoBancario));
				}
				if (telaConsultaDevolucaoHistorico){
					filtroDevolucaoHistorico.adicionarParametro(new ParametroSimples(
							FiltroDevolucao.AVISO_BANCARIO_ID, idAvisoBancario));
				}
				i = 0;
	
				if (idsDocumentosTipos != null) {
	
					while (i < idsDocumentosTipos.length) {
	
						if (!idsDocumentosTipos[i].equals("")) {
	
							if (i + 1 < idsDocumentosTipos.length) {
								if (telaConsultaDevolucaoAtual){
									filtroDevolucao
											.adicionarParametro(new ParametroSimples(
													FiltroDevolucao.GUIA_DEVOLUCAO_DOCUMENTO_TIPO_ID,
													idsDocumentosTipos[i],
													ConectorOr.CONECTOR_OR,
													(idsDocumentosTipos.length)));
								}
								if (telaConsultaDevolucaoHistorico){
									filtroDevolucaoHistorico
									.adicionarParametro(new ParametroSimples(
											FiltroDevolucao.GUIA_DEVOLUCAO_DOCUMENTO_TIPO_ID,
											idsDocumentosTipos[i],
											ConectorOr.CONECTOR_OR,
											(idsDocumentosTipos.length)));
								}
	
							} else {
								if (telaConsultaDevolucaoAtual){
									filtroDevolucao
											.adicionarParametro(new ParametroSimples(
													FiltroDevolucao.GUIA_DEVOLUCAO_DOCUMENTO_TIPO_ID,
													idsDocumentosTipos[i]));
								}
								if (telaConsultaDevolucaoHistorico){
									filtroDevolucaoHistorico
									.adicionarParametro(new ParametroSimples(
											FiltroDevolucao.GUIA_DEVOLUCAO_DOCUMENTO_TIPO_ID,
											idsDocumentosTipos[i]));
								}
							}
	
						}
						i++;
	
					}
				}
				if (telaConsultaDevolucaoAtual){
					filtroDevolucao
							.adicionarCaminhoParaCarregamentoEntidade("avisoBancario.arrecadador");
					filtroDevolucao
							.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
					filtroDevolucao
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.documentoTipo");
					filtroDevolucao
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.guiaPagamento.debitoTipo");
					filtroDevolucao
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.conta");
					filtroDevolucao
							.adicionarCaminhoParaCarregamentoEntidade("devolucaoSituacaoAtual");
					filtroDevolucao
							.adicionarCaminhoParaCarregamentoEntidade("devolucaoSituacaoAnterior");
					filtroDevolucao
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.debitoACobrarGeral.debitoACobrar.debitoTipo");
					filtroDevolucao
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.debitoACobrarGeral.debitoACobrar.imovel");
		
					colecaoAvisosBancariosDevolucoes = fachada
							.pesquisarDevolucao(filtroDevolucao);
					if (colecaoAvisosBancariosDevolucoes != null
							&& !colecaoAvisosBancariosDevolucoes.isEmpty()) {
						sessao.setAttribute("colecaoAvisosBancariosDevolucoes",
								colecaoAvisosBancariosDevolucoes);
					}
				}
				
				if (telaConsultaDevolucaoHistorico){
					filtroDevolucaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("avisoBancario.arrecadador");
					filtroDevolucaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
					filtroDevolucaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.documentoTipo");
					filtroDevolucaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.guiaPagamento.debitoTipo");
					filtroDevolucaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.conta");
					filtroDevolucaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("devolucaoSituacaoAtual");
					filtroDevolucaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("devolucaoSituacaoAnterior");
					filtroDevolucaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.debitoACobrarGeral.debitoACobrar.debitoTipo");
					filtroDevolucaoHistorico
							.adicionarCaminhoParaCarregamentoEntidade("guiaDevolucao.debitoACobrarGeral.debitoACobrar.imovel");
		
					colecaoAvisosBancariosDevolucoesHistorico = fachada
							.pesquisarDevolucaoHistorico(filtroDevolucaoHistorico);
					if (colecaoAvisosBancariosDevolucoesHistorico != null
							&& !colecaoAvisosBancariosDevolucoesHistorico.isEmpty()) {
						sessao.setAttribute("colecaoAvisosBancariosDevolucoesHistorico",
								colecaoAvisosBancariosDevolucoesHistorico);
					}
				}
			}
	
			// Localidade
	
			if (idLocalidadeInicial != null
					&& !idLocalidadeInicial.trim().equals("")) {
				if (idLocalidadeFinal == null
						|| idLocalidadeFinal.trim().equals("")) {
					throw new ActionServletException("atencao.required", null,
							"Localidade Final");
				}
				if (new Integer(idLocalidadeInicial).compareTo(new Integer(
						idLocalidadeFinal)) > 0) {
					throw new ActionServletException(
							"atencao.localidade.inicial.maior.localidade.final");
				}
	
				// Cria um filtro para verificar se a localidade inicial digitada
				// pelo o usuário existe
				FiltroLocalidade filtroLocalidadeInicial = new FiltroLocalidade();
				filtroLocalidadeInicial.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, idLocalidadeInicial));
	
				Collection colecaoLocalidadeInicial = fachada.pesquisar(
						filtroLocalidadeInicial, Localidade.class.getName());
	
				if (colecaoLocalidadeInicial == null
						|| colecaoLocalidadeInicial.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Localidade Inicial");
				}
	
				// Cria um filtro para verificar se a localidade final digitada pelo
				// o usuário existe
				FiltroLocalidade filtroLocalidadeFinal = new FiltroLocalidade();
				filtroLocalidadeFinal.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, idLocalidadeFinal));
	
				Collection colecaoLocalidadeFinal = fachada.pesquisar(
						filtroLocalidadeFinal, Localidade.class.getName());
	
				if (colecaoLocalidadeFinal == null
						|| colecaoLocalidadeFinal.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Localidade Final");
				}
	
				peloMenosUmParametroInformado = true;
	
				// Passa uma flag para ser verificado no
				// ExibirConsultarDevolucaoAction e caso esteja presente setar o
				// retorno para um relatório requisitado pelo caso de uso.
				httpServletRequest.setAttribute("localidadePresente", true);
	
				if (telaConsultaDevolucaoAtual){
					filtroDevolucao.adicionarParametro(new Intervalo(
							FiltroDevolucao.LOCALIDADE_ID, idLocalidadeInicial,
							idLocalidadeFinal));
					
					filtroDevolucao.limparCamposOrderBy();
					filtroDevolucao.setCampoOrderBy(FiltroDevolucao.LOCALIDADE_ID);
					filtroDevolucao.setCampoOrderBy(FiltroDevolucao.IMOVEL_ID);
					
					httpServletRequest.setAttribute("filtroDevolucao", filtroDevolucao);
					
					colecaoLocalidadeDevolucoes = fachada
							.pesquisarDevolucao(filtroDevolucao);
				}
				
				if (telaConsultaDevolucaoHistorico){
					filtroDevolucaoHistorico.adicionarParametro(new Intervalo(
							FiltroDevolucaoHistorico.LOCALIDADE_ID, idLocalidadeInicial,
							idLocalidadeFinal));
					
					filtroDevolucaoHistorico.limparCamposOrderBy();
					filtroDevolucaoHistorico.setCampoOrderBy(FiltroDevolucaoHistorico.LOCALIDADE_ID);
					filtroDevolucaoHistorico.setCampoOrderBy(FiltroDevolucaoHistorico.IMOVEL_ID);
					
					httpServletRequest.setAttribute("filtroDevolucaoHistorico", filtroDevolucaoHistorico);
					
					colecaoLocalidadeDevolucoesHistorico = fachada
							.pesquisarDevolucaoHistorico(filtroDevolucaoHistorico);
				}
			}
	
			// Erro caso o usuário mandou filtrar sem nenhum parâmetro
			if (!peloMenosUmParametroInformado) {
				throw new ActionServletException(
						"atencao.filtro.nenhum_parametro_informado");
			}else{
				if (telaConsultaDevolucaoAtual){
					httpServletRequest.setAttribute("filtroDevolucao",
							filtroDevolucao);
				}
				if (telaConsultaDevolucaoHistorico){
					httpServletRequest.setAttribute("filtroDevolucaoHistorico",
							filtroDevolucaoHistorico);
				}
			}
	
			if ((colecaoClientesDevolucoes == null || colecaoClientesDevolucoes.isEmpty())
					&& (colecaoImoveisDevolucoes == null || colecaoImoveisDevolucoes.isEmpty())
					&& (colecaoAvisosBancariosDevolucoes == null || colecaoAvisosBancariosDevolucoes.isEmpty())
					&& (colecaoLocalidadeDevolucoes == null || colecaoLocalidadeDevolucoes.isEmpty())
					&&(colecaoClientesDevolucoesHistorico == null || colecaoClientesDevolucoesHistorico.isEmpty())
					&& (colecaoImoveisDevolucoesHistorico == null || colecaoImoveisDevolucoesHistorico.isEmpty())
					&& (colecaoAvisosBancariosDevolucoesHistorico == null || colecaoAvisosBancariosDevolucoesHistorico.isEmpty())
					&& (colecaoLocalidadeDevolucoesHistorico == null || colecaoLocalidadeDevolucoesHistorico.isEmpty())) {
	
				// Nenhuma devolução cadastrada
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			}
			else{
				Collection colecaoDevolucao = (Collection) fachada.pesquisar(filtroDevolucao, Devolucao.class.getName());
				if (colecaoDevolucao != null && !colecaoDevolucao.isEmpty())
				{
			        if (atualizar != null && colecaoDevolucao.size() == 1)
			        {
			        	Devolucao devolucao = (Devolucao) colecaoDevolucao.iterator().next();
			        	httpServletRequest.setAttribute("idRegistroAtualizacao",
								devolucao.getId());
			        	
			        	retorno = actionMapping
			            	.findForward("exibirAtualizarDevolucao");
			        	
			        	flag = 1;
			        }
				}
			}
			
			if (filtrarDevolucaoActionForm.getIdLocalidadeInicial() != null
					&& !filtrarDevolucaoActionForm.getIdLocalidadeInicial().equals(
							"")) {
				sessao.setAttribute("colecaoLocalidadeDevolucoes",
						colecaoLocalidadeDevolucoes);
				sessao.setAttribute("colecaoLocalidadeDevolucoesHistorico",
						colecaoLocalidadeDevolucoesHistorico);
			}
	
			// Devolve o mapeamento de retorno
		}else{
			filtroDevolucao = (FiltroDevolucao)sessao.getAttribute("filtroDevolucao");
			sessao.setAttribute("filtroDevolucao",filtroDevolucao);
			sessao.removeAttribute("telaManter");
		}
//		String tela = (String) sessao.getAttribute("tela");
		
		if (tela != null && !tela.equals("")) {
			if (tela.equals("manterDevolucao") && flag == 0) {
				retorno = actionMapping.findForward("manterDevolucao");
			}
		} else if (flag == 0){
			retorno = actionMapping.findForward("consultarDevolucao");
			if (filtrarDevolucaoActionForm.getIdLocalidadeInicial() != null
					&& !filtrarDevolucaoActionForm.getIdLocalidadeInicial()
							.equals("")) {
				retorno = actionMapping.findForward("gerarRelatorioDevolucao");
				sessao.setAttribute("filtroDevolucao", filtroDevolucao);
				sessao.setAttribute("filtroDevolucaoHistorico", filtroDevolucaoHistorico);
				sessao.setAttribute("colecaoLocalidadeDevolucoes",
						colecaoLocalidadeDevolucoes);
				sessao.setAttribute("colecaoLocalidadeDevolucoesHistorico",
						colecaoLocalidadeDevolucoesHistorico);
			}
		}
		//sessao.removeAttribute("tela");
		sessao.setAttribute("filtrar_manter", "filtrar_manter");
		return retorno;
	}
}
