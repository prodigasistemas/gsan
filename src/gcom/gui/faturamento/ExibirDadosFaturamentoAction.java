package gcom.gui.faturamento;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoAtivCronRota;
import gcom.faturamento.FaturamentoAtividade;
import gcom.faturamento.FiltroFaturamentoAtivCronRota;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraSituacao;
import gcom.micromedicao.leitura.LeituraTipo;
import gcom.micromedicao.medicao.FiltroMedicaoHistorico;
import gcom.micromedicao.medicao.FiltroMedicaoTipo;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirDadosFaturamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
			throws ControladorException {

		ActionForward retorno = actionMapping
				.findForward("atualizarFaturamentoDados");

		// obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		sessao.removeAttribute("medicoesTipo");
		sessao.removeAttribute("imovel");
		sessao.removeAttribute("medicaoHistorico");
		sessao.removeAttribute("alterarFaturamentoDadosActionForm");
		
		AlterarFaturamentoDadosActionForm alterarFaturamentoDadosActionForm = (AlterarFaturamentoDadosActionForm) actionForm;
		
		if (httpServletRequest.getParameter("telaMedicaoConsumoDadosAnt") != null
				&& !httpServletRequest.getParameter(
						"telaMedicaoConsumoDadosAnt").equals("")) {
			sessao.setAttribute("telaMedicaoConsumoDadosAnt",
					httpServletRequest
							.getParameter("telaMedicaoConsumoDadosAnt"));
			alterarFaturamentoDadosActionForm.setIdAnormalidade("");
		}

		if (httpServletRequest.getParameter("desfazer") != null
				&& !httpServletRequest.getParameter("desfazer").equals("")) {
			alterarFaturamentoDadosActionForm.setIdAnormalidade("");
		}
		
		// se a requisição vier da Tela de Apresentar Dados Para Análise da Medição e Consumo
		// carregar o imóvel
		if(httpServletRequest.getParameter("idRegistroAtualizacao") != null
				&& !httpServletRequest.getParameter("idRegistroAtualizacao").equals("") 
				&& httpServletRequest.getParameter("medicaoTipo") != null
				&& !httpServletRequest.getParameter("medicaoTipo").equals("")){
			
			String idImovel = httpServletRequest.getParameter("idRegistroAtualizacao");
			
			String medicaoTipo = httpServletRequest.getParameter("medicaoTipo");
			
			alterarFaturamentoDadosActionForm.setIdImovel(idImovel);
			
			alterarFaturamentoDadosActionForm.setTipoMedicao(medicaoTipo);
		}
		
		// se a requisição vier da Tela de Apresentar Dados Para Análise da Medição e Consumo
		// carregar o imóvel
		if(httpServletRequest.getParameter("idRegistroAtualizacao") != null
				&& !httpServletRequest.getParameter("idRegistroAtualizacao").equals("") 
				&& httpServletRequest.getParameter("medicaoTipo") != null
				&& !httpServletRequest.getParameter("medicaoTipo").equals("")){
			
			String idImovel = httpServletRequest.getParameter("idRegistroAtualizacao");
			
			String medicaoTipo = httpServletRequest.getParameter("medicaoTipo");
			
			alterarFaturamentoDadosActionForm.setIdImovel(idImovel);
			
			alterarFaturamentoDadosActionForm.setTipoMedicao(medicaoTipo);
		}

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();
		
		
		// Deve ser alterado por Leo
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		// Parte que pega as coleções da sessão

		// Medição Tipo
		FiltroMedicaoTipo filtroMedicaoTipoSessao = new FiltroMedicaoTipo();
		filtroMedicaoTipoSessao.adicionarParametro(new ParametroSimples(
				FiltroMedicaoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection medicoesTipo = fachada.pesquisar(filtroMedicaoTipoSessao,
				MedicaoTipo.class.getName());
		if (medicoesTipo.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"tipo de medição");
		} else {
			sessao.setAttribute("medicoesTipo", medicoesTipo);
		}

		// Pega os codigos que o usuario digitou para a pesquisa direta de

		// Imóvel
		
//		Parte que verifica  se a acao de atualizar deve ser bloqueada
		if(((httpServletRequest.getParameter("idImovel") != null &&
				!httpServletRequest.getParameter("idImovel").toString().equals("")) ||
				alterarFaturamentoDadosActionForm.getIdImovel() != null) ){
			
			String idImovel = null; 
				
			if(httpServletRequest.getParameter("idImovel") != null &&
					!httpServletRequest.getParameter("idImovel").toString().equals("")){
				
				idImovel = httpServletRequest.getParameter("idImovel").toString();
				
			}else if(httpServletRequest.getParameter("idRegistroAtualizacao") != null
					&& !httpServletRequest.getParameter("idRegistroAtualizacao").equals("")){
				
				idImovel = httpServletRequest.getParameter("idRegistroAtualizacao");
			}

			if(idImovel == null || idImovel.equals("")){
				idImovel = alterarFaturamentoDadosActionForm.getIdImovel();
			}

			Integer leituraImovel = null;
			FiltroImovel filtroImovel1 = null;
			Collection ColecaoimovelEncontrado1 = null;
			Imovel imovel1 = null;
			Integer gerarDadosLeitura = null;
			
			filtroImovel1 = new FiltroImovel();
			
			filtroImovel1.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
					idImovel));
			filtroImovel1.adicionarCaminhoParaCarregamentoEntidade("quadra");
			filtroImovel1.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
			filtroImovel1.adicionarCaminhoParaCarregamentoEntidade("rotaAlternativa.leituraTipo");

			ColecaoimovelEncontrado1 = fachada.pesquisar(filtroImovel1,Imovel.class.getName());
			
			if(ColecaoimovelEncontrado1 != null && !ColecaoimovelEncontrado1.isEmpty()){
				imovel1 = (Imovel) Util.retonarObjetoDeColecao(ColecaoimovelEncontrado1);
			
			if(imovel1 != null && imovel1.getImovelPerfil() != null &&
					imovel1.getImovelPerfil().getIndicadorGerarDadosLeitura() != null){
				gerarDadosLeitura = new Integer(imovel1.getImovelPerfil().getIndicadorGerarDadosLeitura());
			}
			if(imovel1 != null && imovel1.getQuadra() != null){
				Integer idQuadra = imovel1.getQuadra().getId();
				
				FiltroQuadra filtroQuadra = new FiltroQuadra();
				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID,idQuadra));
				filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("rota");
				
				Collection colecaoQuadraEncontrada = fachada.pesquisar(filtroQuadra,Quadra.class.getName());
				Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadraEncontrada);
				
				// Alterado por Rômulo Aurélio
				// Analista: Nelson Carvalho
				// Motivo: Quando o imovel tinha rota alternativa as informações que eram referentes a essa rota eram 
				// retidas da rota da quadra
				// Data: 05/11/2010
				// RM: 57
				if(imovel1.getRotaAlternativa() != null ){
					leituraImovel = imovel1.getRotaAlternativa().getLeituraTipo().getId();
				}else  if(quadra != null && quadra.getRota().getLeituraTipo() != null){
					leituraImovel = quadra.getRota().getLeituraTipo().getId();
				}
				
			}
			
			// Alterado por Ulysses Dias
			// Analista: Wellington Monteiro
			// Motivo: Desbloquear Anormalidade de Leitura Informada para todos os tipos de imovel.
			// Data: 10/03/2011
			// [UC0091] Alterar Dados para Faturamento
			
			if(gerarDadosLeitura == ImovelPerfil.SIM.intValue() && 
					leituraImovel == LeituraTipo.LEITURA_E_ENTRADA_SIMULTANEA.intValue() 
					){
				
				sessao.setAttribute("bloquearAtualizar","nao");
				
				if(httpServletRequest.getParameter("tipoMedicao") != null && 
						!httpServletRequest.getParameter("tipoMedicao").toString().equals("")){
					alterarFaturamentoDadosActionForm.setTipoMedicao(httpServletRequest.getParameter("tipoMedicao"));	
				}
				
				boolean jaGeradoArquivoImpressaoParaRotaDoImovel = false;
				
				if(imovel1.getRotaAlternativa() == null){
					jaGeradoArquivoImpressaoParaRotaDoImovel = 
						fachada.verificarExistenciaArquivoDeImpressao(
								new Integer(alterarFaturamentoDadosActionForm.getIdImovel()),
								new Integer(alterarFaturamentoDadosActionForm.getTipoMedicao()));
				} else{
					jaGeradoArquivoImpressaoParaRotaDoImovel = 
						fachada.verificarExistenciaArquivoDeImpressaoRotaAlternativa(
								new Integer(alterarFaturamentoDadosActionForm.getIdImovel()),
								new Integer(alterarFaturamentoDadosActionForm.getTipoMedicao()));
				}
				if(!jaGeradoArquivoImpressaoParaRotaDoImovel){
					sessao.setAttribute("bloquearAtualizarleitura","nao");
				}else{
					sessao.setAttribute("bloquearAtualizarleitura","sim");
				}
				
			}
			else{
				sessao.setAttribute("bloquearAtualizar","nao");
				}
			}
			
//			Fim Parte que verifica  se a acao de atualizar deve ser bloqueada
		}
		else{
			sessao.setAttribute("bloquearAtualizar","nao");
		}
		
		

		// Parte que cuida da pesquisa efetuada pelo enter
		if (httpServletRequest.getParameter("pesquisaImovel") != null) {
			String codigoImovelDigitadoEnter = alterarFaturamentoDadosActionForm
					.getIdImovel();
			
			httpServletRequest.setAttribute("nomeCampo", "tipoMedicao");
			
			if (codigoImovelDigitadoEnter != null
					&& !codigoImovelDigitadoEnter.trim().equalsIgnoreCase("")) {
				
				// Seta os campos para vazio
				alterarFaturamentoDadosActionForm.setIdImovelSelecionado("");
				alterarFaturamentoDadosActionForm.setTipoMedicaoSelecionada("");
				alterarFaturamentoDadosActionForm.setIdLocalidade("");
				alterarFaturamentoDadosActionForm.setNomeLocalidade("");
				alterarFaturamentoDadosActionForm.setIdSetorComercial("");
				alterarFaturamentoDadosActionForm.setNomeSetorComercial("");
				alterarFaturamentoDadosActionForm.setLeituraAnterior("");
				alterarFaturamentoDadosActionForm.setDataLeituraAnterior("");
				alterarFaturamentoDadosActionForm.setIdLeiturista("");
				alterarFaturamentoDadosActionForm.setNomeLeiturista("");
				alterarFaturamentoDadosActionForm.setLeituraAtual("");
				alterarFaturamentoDadosActionForm.setDataLeituraAtual("");
				alterarFaturamentoDadosActionForm.setIdSituacaoLeituraAtual("");
				alterarFaturamentoDadosActionForm
						.setNomeSituacaoLeituraAtual("");
				alterarFaturamentoDadosActionForm.setIdAnormalidade("");
				alterarFaturamentoDadosActionForm.setNomeAnormalidade("");
				alterarFaturamentoDadosActionForm.setConsumoMedido("");
				alterarFaturamentoDadosActionForm.setConsumoInformado("");
				alterarFaturamentoDadosActionForm.setNomeUsuario("");
				alterarFaturamentoDadosActionForm.setLigacaoAguaSituacao("");
				alterarFaturamentoDadosActionForm.setLigacaoEsgotoSituacao("");

				// filtro para pesquisar o imovel digitado --- pesquisa por
				// enter
				FiltroImovel filtroImovelPesquisa = new FiltroImovel();
				filtroImovelPesquisa
						.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
				filtroImovelPesquisa
						.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroImovelPesquisa
						.adicionarCaminhoParaCarregamentoEntidade("quadra");
				filtroImovelPesquisa.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, codigoImovelDigitadoEnter));

				Collection colecaoImovel = fachada.pesquisar(
						filtroImovelPesquisa, Imovel.class.getName());
				if (!colecaoImovel.isEmpty()) {
					Imovel imovelPesquisa = (Imovel) Util
							.retonarObjetoDeColecao(colecaoImovel);

					alterarFaturamentoDadosActionForm
							.setInscricaoImovel(imovelPesquisa
									.getInscricaoFormatada());
					
					String enderecoCompleto = fachada
							.pesquisarEnderecoFormatado(new Integer(
									codigoImovelDigitadoEnter));
					httpServletRequest.setAttribute("enderecoCompleto",
							enderecoCompleto);

				} else {
					sessao.setAttribute("bloquearAtualizar","nao");
					alterarFaturamentoDadosActionForm
							.setInscricaoImovel("IMÓVEL INEXISTENTE");
				}
			}
		} 
		
		else if(httpServletRequest.getParameter("pesquisaImovel") == null){
			Imovel imovel = new Imovel();

			MedicaoHistorico medicaoHistorico = new MedicaoHistorico();

			String codigoImovelDigitadoEnter = null;

			String idTipoMedicao = null;

			if (httpServletRequest.getParameter("idImovel") != null
					&& !httpServletRequest.getParameter("idImovel").equals("")
					&& httpServletRequest.getParameter("idTipoMedicao") != null
					&& !httpServletRequest.getParameter("idTipoMedicao")
							.equals("")) {
				codigoImovelDigitadoEnter = httpServletRequest
						.getParameter("idImovel");
				sessao
						.setAttribute("idImovelSessao",
								codigoImovelDigitadoEnter);
				idTipoMedicao = httpServletRequest
						.getParameter("idTipoMedicao");
				alterarFaturamentoDadosActionForm.setTipoMedicao(idTipoMedicao);
				sessao.setAttribute("idTipoMedicaoSessao", idTipoMedicao);
				sessao.setAttribute("bloqueaCampos", "S");
			} else {
				codigoImovelDigitadoEnter = alterarFaturamentoDadosActionForm
						.getIdImovel();
				idTipoMedicao = alterarFaturamentoDadosActionForm
						.getTipoMedicao();
			}

			String codigoAnormalidadeLeituraDigitadoEnter = alterarFaturamentoDadosActionForm
					.getIdAnormalidade();
			alterarFaturamentoDadosActionForm
					.setIndicadorConfirmacao(ConstantesSistema.NAO_CONFIRMADA);

			// Seta os campos para vazio
			alterarFaturamentoDadosActionForm.setIdImovelSelecionado("");
			alterarFaturamentoDadosActionForm.setTipoMedicaoSelecionada("");
			alterarFaturamentoDadosActionForm.setIdLocalidade("");
			alterarFaturamentoDadosActionForm.setNomeLocalidade("");
			alterarFaturamentoDadosActionForm.setIdSetorComercial("");
			alterarFaturamentoDadosActionForm.setNomeSetorComercial("");
			alterarFaturamentoDadosActionForm.setLeituraAnterior("");
			alterarFaturamentoDadosActionForm.setDataLeituraAnterior("");
			alterarFaturamentoDadosActionForm.setIdLeiturista("");
			alterarFaturamentoDadosActionForm.setNomeLeiturista("");
			alterarFaturamentoDadosActionForm.setLeituraAtual("");
			alterarFaturamentoDadosActionForm.setDataLeituraAtual("");
			alterarFaturamentoDadosActionForm.setIdSituacaoLeituraAtual("");
			alterarFaturamentoDadosActionForm.setNomeSituacaoLeituraAtual("");
			alterarFaturamentoDadosActionForm.setIdAnormalidade("");
			alterarFaturamentoDadosActionForm.setNomeAnormalidade("");
			alterarFaturamentoDadosActionForm.setConsumoMedido("");
			alterarFaturamentoDadosActionForm.setConsumoInformado("");
			alterarFaturamentoDadosActionForm.setNomeUsuario("");
			alterarFaturamentoDadosActionForm.setLigacaoAguaSituacao("");
			alterarFaturamentoDadosActionForm.setLigacaoEsgotoSituacao("");

			if (sessao.getAttribute("nomeCampo") != null
					&& !sessao.getAttribute("nomeCampo").toString().trim()
							.equals("")) {
				httpServletRequest.setAttribute("nomeCampo", sessao
						.getAttribute("nomeCampo").toString());
			}

			if (codigoImovelDigitadoEnter != null
					&& !codigoImovelDigitadoEnter.trim().equals("")) {

				// Pesquisa o imovel na base
				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("setorComercial.municipio.unidadeFederacao");

				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("quadra.rota.faturamentoGrupo");
				filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("rotaAlternativa.faturamentoGrupo");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico.hidrometro");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua.hidrometroInstalacaoHistorico.hidrometro");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidade");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_AGUA_SITUACAO);
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.LIGACAO_ESGOTO_SITUACAO);

				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, codigoImovelDigitadoEnter));

				Collection imovelEncontrado = fachada.pesquisar(filtroImovel,
						Imovel.class.getName());

				String enderecoCompleto = fachada
						.pesquisarEnderecoFormatado(new Integer(
								codigoImovelDigitadoEnter));
				httpServletRequest.setAttribute("enderecoCompleto",
						enderecoCompleto);

				if (imovelEncontrado != null && !imovelEncontrado.isEmpty()) {
					// O imovel foi encontrado

					imovel = ((Imovel) ((List) imovelEncontrado).get(0));

					Cliente clienteUsuario = fachada.pesquisarClienteUsuarioImovel(imovel.getId());
					
					alterarFaturamentoDadosActionForm.setNomeUsuario(clienteUsuario.getNome());
					alterarFaturamentoDadosActionForm.setLigacaoAguaSituacao(imovel.getLigacaoAguaSituacao().getDescricao());
					alterarFaturamentoDadosActionForm.setLigacaoEsgotoSituacao(imovel.getLigacaoEsgotoSituacao().getDescricao());
					
					if(idTipoMedicao == null){
						httpServletRequest.setAttribute("nomeCampo", "idImovel");

						// Alterado por Rômulo Aurélio
						// Analista: Nelson Carvalho
						// Motivo: Quando o imovel tinha rota alternativa as informações que eram referentes a essa rota eram 
						// retidas da rota da quadra
						// Data: 05/11/2010
						// RM: 57
						
						Integer anoMesReferenciaGrupo = (imovel.getRotaAlternativa() != null ? imovel.getRotaAlternativa().getFaturamentoGrupo()
								.getAnoMesReferencia() : imovel.getQuadra().getRota().getFaturamentoGrupo().getAnoMesReferencia());

						throw new ActionServletException(
								"atencao.imovel.ano.mes.faturamento.inexistente",
								null,
								Util.formatarAnoMesParaMesAno(anoMesReferenciaGrupo));
					}
					
					FiltroMedicaoTipo filtroMedicaoTipo = new FiltroMedicaoTipo();
					filtroMedicaoTipo.adicionarParametro(new ParametroSimples(
							FiltroMedicaoTipo.ID, idTipoMedicao));

					Collection tipoMedicaoEncontrada = fachada.pesquisar(
							filtroMedicaoTipo, MedicaoTipo.class.getName());

					FiltroFaturamentoAtivCronRota filtroFaturamentoAtividadeCronogramaRota = new FiltroFaturamentoAtivCronRota();
					
					// Alterado por Rômulo Aurélio
					// Analista: Nelson Carvalho
					// Motivo: Quando o imovel tinha rota alternativa as informações que eram referentes a essa rota eram 
					// retidas da rota da quadra
					// Data: 05/11/2010
					// RM: 57
					filtroFaturamentoAtividadeCronogramaRota
							.adicionarParametro(new ParametroSimples(
									FiltroFaturamentoAtivCronRota.COMP_ID_ROTA_ID,
									(imovel.getRotaAlternativa() != null ? imovel.getRotaAlternativa().getId()
											: imovel.getQuadra().getRota().getId())));
					
					 
					
					
					filtroFaturamentoAtividadeCronogramaRota
							.adicionarParametro(new ParametroSimples(
									FiltroFaturamentoAtivCronRota.COMP_ID_FATURAMENTO_ATIVIDADE_CRONOGRAMA_FATURAMENTO_ATIVIDADE_ID,
									FaturamentoAtividade.FATURAR_GRUPO));
					
					// Alterado por Rômulo Aurélio
					// Analista: Nelson Carvalho
					// Motivo: Quando o imovel tinha rota alternativa as informações que eram referentes a essa rota eram 
					// retidas da rota da quadra
					// Data: 05/11/2010
					// RM: 57
					filtroFaturamentoAtividadeCronogramaRota
							.adicionarParametro(new ParametroSimples(
									FiltroFaturamentoAtivCronRota.COMP_ID_FATURAMENTO_ATIVIDADE_CRONOGRAMA_FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_ANO_MES_REFERENCIA,
									(imovel.getRotaAlternativa() != null ? imovel.getRotaAlternativa().getFaturamentoGrupo().getAnoMesReferencia()
											: imovel.getQuadra().getRota().getFaturamentoGrupo().getAnoMesReferencia())));
					
					
					
					filtroFaturamentoAtividadeCronogramaRota
						.adicionarParametro(new ParametroNaoNulo(
							FiltroFaturamentoAtivCronRota.COMP_ID_FATURAMENTO_ATIVIDADE_CRONOGRAMA_DATA_REALIZACAO));

					Collection faturamentoAtividadeCronogramaRota = fachada
							.pesquisar(
									filtroFaturamentoAtividadeCronogramaRota,
									FaturamentoAtivCronRota.class.getName());

					// Alterado por Rômulo Aurélio
					// Analista: Nelson Carvalho
					// Motivo: Quando o imovel tinha rota alternativa as informações que eram referentes a essa rota eram 
					// retidas da rota da quadra
					// Data: 05/11/2010
					// RM: 57
					if (!faturamentoAtividadeCronogramaRota.isEmpty()) {
						httpServletRequest
								.setAttribute("nomeCampo", "idImovel");
						throw new ActionServletException(
								"atencao.imovel.faturamento.existente", null,
								""
										+ (imovel.getRotaAlternativa() != null ? imovel.getRotaAlternativa().getFaturamentoGrupo().getId()
												: imovel.getQuadra().getRota().getFaturamentoGrupo().getId()));

					} else {

						sessao.setAttribute("imovel", imovel);

						Collection medicaoHistoricoEncontrada = null;

						alterarFaturamentoDadosActionForm.setIdImovel(""
								+ imovel.getId());
						alterarFaturamentoDadosActionForm
								.setIdImovelSelecionado("" + imovel.getId());
						if (!tipoMedicaoEncontrada.isEmpty()) {
							alterarFaturamentoDadosActionForm
									.setTipoMedicaoSelecionada(((MedicaoTipo) ((List) tipoMedicaoEncontrada)
											.get(0)).getDescricao());
						}

						FiltroMedicaoHistorico filtroMedicaoHistorico = new FiltroMedicaoHistorico();

						filtroMedicaoHistorico
								.adicionarCaminhoParaCarregamentoEntidade("funcionario");
						filtroMedicaoHistorico
								.adicionarCaminhoParaCarregamentoEntidade("leituraSituacaoAtual");
						filtroMedicaoHistorico
								.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidadeInformada");
						// Alterado por Rômulo Aurélio
						// Analista: Nelson Carvalho
						// Motivo: Quando o imovel tinha rota alternativa as informações que eram referentes a essa rota eram 
						// retidas da rota da quadra
						// Data: 05/11/2010
						// RM: 57
						filtroMedicaoHistorico
								.adicionarParametro(new ParametroSimples(
										FiltroMedicaoHistorico.ANO_MES_REFERENCIA_FATURAMENTO,
										(((Imovel) ((List) imovelEncontrado).get(0)).getRotaAlternativa() != null ? 
												((Imovel) ((List) imovelEncontrado).get(0)).getRotaAlternativa().getFaturamentoGrupo().getAnoMesReferencia()
												: ((Imovel) ((List) imovelEncontrado).get(0)).getQuadra().getRota().getFaturamentoGrupo().getAnoMesReferencia())
												));

						if (idTipoMedicao.trim().equalsIgnoreCase(
								MedicaoTipo.LIGACAO_AGUA.toString())) {
							if (imovel.getLigacaoAgua() == null
									|| imovel.getLigacaoAgua().getId().equals(0)) {
								throw new ActionServletException(
										"atencao.imovel.agua.sem_indicacao");

							} else {
								filtroMedicaoHistorico
										.adicionarParametro(new ParametroSimples(
												FiltroMedicaoHistorico.LIGACAO_AGUA_ID,
												codigoImovelDigitadoEnter));
							}

						} else {

							if (imovel.getHidrometroInstalacaoHistorico() == null
									|| imovel.getHidrometroInstalacaoHistorico().getId().equals(0)) {
								throw new ActionServletException(
										"atencao.imovel.poco.sem_indicacao");

							} else {

								filtroMedicaoHistorico
										.adicionarParametro(new ParametroSimples(
												FiltroMedicaoHistorico.IMOVEL_ID,
												codigoImovelDigitadoEnter));

								httpServletRequest.setAttribute("nomeCampo",
										"idImovel");

							}

						}

						medicaoHistoricoEncontrada = fachada.pesquisar(
								filtroMedicaoHistorico, MedicaoHistorico.class
										.getName());
						
						if ( medicaoHistoricoEncontrada == null || medicaoHistoricoEncontrada.size() == 0  ){
							MedicaoHistorico medicaoHistoricoGeradaSessao = (MedicaoHistorico)sessao.getAttribute( "medicaoHistoricoGerada" ); 
							if (medicaoHistoricoGeradaSessao != null){
								String idImovel = null;
								if(idTipoMedicao.trim().equalsIgnoreCase(
										MedicaoTipo.LIGACAO_AGUA.toString())){
									idImovel = medicaoHistoricoGeradaSessao.getLigacaoAgua().getId().toString(); 
								}else{
									idImovel = medicaoHistoricoGeradaSessao.getImovel().getId().toString(); 
								}
								if(idImovel.equals(imovel.getId().toString())){
									medicaoHistoricoEncontrada.add( (MedicaoHistorico) sessao.getAttribute("medicaoHistoricoGerada") );	
								}						
							}
						}

						if (medicaoHistoricoEncontrada != null
								&& !medicaoHistoricoEncontrada.isEmpty()) {

							medicaoHistorico = ((MedicaoHistorico) ((List) medicaoHistoricoEncontrada)
									.get(0));

							if (medicaoHistorico.getLeituraSituacaoAtual()
									.getId().equals(LeituraSituacao.CONFIRMADA)) {
								alterarFaturamentoDadosActionForm
										.setIndicadorConfirmacao(ConstantesSistema.CONFIRMADA);
							}

							SimpleDateFormat dataFormatada = new SimpleDateFormat(
									"dd/MM/yyyy");

							String dataLeituraAnterior = "";
							if (medicaoHistorico
									.getDataLeituraAnteriorFaturamento() != null
									&& !medicaoHistorico
											.getDataLeituraAnteriorFaturamento()
											.equals("")) {
								dataLeituraAnterior = dataFormatada
										.format(medicaoHistorico
												.getDataLeituraAnteriorFaturamento());
							}

							String dataLeituraAtual = "";
							if (medicaoHistorico.getDataLeituraAtualInformada() != null
									&& !medicaoHistorico
											.getDataLeituraAtualInformada()
											.equals("")) {
								dataLeituraAtual = dataFormatada
										.format(medicaoHistorico
												.getDataLeituraAtualInformada());
							}

							alterarFaturamentoDadosActionForm
									.setIdLocalidade(""
											+ imovel.getLocalidade().getId());
							alterarFaturamentoDadosActionForm
									.setNomeLocalidade(imovel.getLocalidade()
											.getDescricao());
							alterarFaturamentoDadosActionForm
									.setIdSetorComercial(""
											+ imovel.getSetorComercial()
													.getCodigo());
							alterarFaturamentoDadosActionForm
									.setNomeSetorComercial(imovel
											.getSetorComercial().getDescricao());
							alterarFaturamentoDadosActionForm
									.setLeituraAnterior(""
											+ medicaoHistorico
													.getLeituraAnteriorFaturamento());
							alterarFaturamentoDadosActionForm
									.setDataLeituraAnterior(dataLeituraAnterior);
							alterarFaturamentoDadosActionForm
									.setIdLeiturista(medicaoHistorico
											.getFuncionario() == null ? "" : ""
											+ medicaoHistorico.getFuncionario()
													.getId());
							alterarFaturamentoDadosActionForm
									.setNomeLeiturista(medicaoHistorico
											.getFuncionario() == null ? ""
											: medicaoHistorico.getFuncionario()
													.getNome());
							alterarFaturamentoDadosActionForm
									.setLeituraAtual(medicaoHistorico
											.getLeituraAtualInformada() == null ? ""
											: ""
													+ medicaoHistorico
															.getLeituraAtualInformada());
							alterarFaturamentoDadosActionForm
									.setDataLeituraAtual(dataLeituraAtual);
							alterarFaturamentoDadosActionForm
									.setIdSituacaoLeituraAtual(""
											+ medicaoHistorico
													.getLeituraSituacaoAtual()
													.getId());
							alterarFaturamentoDadosActionForm
									.setNomeSituacaoLeituraAtual(""
											+ medicaoHistorico
													.getLeituraSituacaoAtual()
													.getDescricao());
							alterarFaturamentoDadosActionForm
									.setIdAnormalidade(medicaoHistorico
											.getLeituraAnormalidadeInformada() == null ? ""
											: ""
													+ medicaoHistorico
															.getLeituraAnormalidadeInformada()
															.getId());
							alterarFaturamentoDadosActionForm
									.setNomeAnormalidade(medicaoHistorico
											.getLeituraAnormalidadeInformada() == null ? ""
											: medicaoHistorico
													.getLeituraAnormalidadeInformada()
													.getDescricao());
							alterarFaturamentoDadosActionForm
									.setConsumoMedido(medicaoHistorico
											.getNumeroConsumoMes() == null ? ""
											: ""
													+ medicaoHistorico
															.getNumeroConsumoMes());
							alterarFaturamentoDadosActionForm
									.setConsumoInformado(medicaoHistorico
											.getNumeroConsumoInformado() == null ? ""
											: ""
													+ medicaoHistorico
															.getNumeroConsumoInformado());
							
							alterarFaturamentoDadosActionForm.setInscricaoImovel(fachada.pesquisarInscricaoImovel(new Integer(codigoImovelDigitadoEnter)));

						}
						// else if ((imovel.getLigacaoAgua() != null ? (imovel
						// .getLigacaoAgua().getId().toString().equals(
						// codigoImovelDigitadoEnter) && imovel
						// .getLigacaoAgua()
						// .getHidrometroInstalacaoHistorico() == null)
						// : false)
						// || (imovel.getHidrometroInstalacaoHistorico() ==
						// null)) {
						// alterarFaturamentoDadosActionForm
						// .setIdAnormalidade(imovel
						// .getLeituraAnormalidade() == null ? ""
						// : ""
						// + imovel
						// .getLeituraAnormalidade()
						// .getId());
						// alterarFaturamentoDadosActionForm
						// .setNomeAnormalidade(imovel
						// .getLeituraAnormalidade() == null ? ""
						// : imovel.getLeituraAnormalidade()
						// .getDescricao());
						// httpServletRequest.setAttribute(
						// "hidrometroInexistente", "true");
						//
						// } else {
						//
						// httpServletRequest.setAttribute("nomeCampo",
						// "idImovel");
						//
						// throw new ActionServletException(
						// "atencao.imovel.ano.mes.faturamento.inexistente",
						// null, Util.formatarAnoMesParaMesAno(imovel
						// .getQuadra().getRota()
						// .getFaturamentoGrupo()
						// .getAnoMesReferencia()));
						//
						// }

						else {

							if (idTipoMedicao.equals(MedicaoTipo.LIGACAO_AGUA
									.toString())) {
								//TODO
								/*if (imovel.getHidrometroInstalacaoHistorico() == null) {
									alterarFaturamentoDadosActionForm
											.setIdAnormalidade(imovel
													.getLeituraAnormalidade() == null ? ""
													: ""
															+ imovel
																	.getLeituraAnormalidade()
																	.getId());
									alterarFaturamentoDadosActionForm
											.setNomeAnormalidade(imovel
													.getLeituraAnormalidade() == null ? ""
													: imovel
															.getLeituraAnormalidade()
															.getDescricao());
									httpServletRequest.setAttribute(
											"hidrometroInexistente", "true");
									sessao.removeAttribute("bloquearAtualizarleitura");
								} else*/ if (imovel.getLigacaoAgua() != null
										&& imovel
												.getLigacaoAgua()
												.getId()
												.toString()
												.equals(
														codigoImovelDigitadoEnter)
										&& imovel
												.getLigacaoAgua()
												.getHidrometroInstalacaoHistorico() == null) {
									alterarFaturamentoDadosActionForm
											.setIdAnormalidade(imovel
													.getLeituraAnormalidade() == null ? ""
													: ""
															+ imovel
																	.getLeituraAnormalidade()
																	.getId());
									alterarFaturamentoDadosActionForm
											.setNomeAnormalidade(imovel
													.getLeituraAnormalidade() == null ? ""
													: imovel
															.getLeituraAnormalidade()
															.getDescricao());
									httpServletRequest.setAttribute(
											"hidrometroInexistente", "true");
									sessao.removeAttribute("bloquearAtualizarleitura");
								} else {
									sessao.removeAttribute( "medicaoHistoricoGerada" );
									MedicaoTipo medicaoTipo= ((MedicaoTipo) ((List) tipoMedicaoEncontrada)
											.get(0));
									// Alterado por Rômulo Aurélio
									// Analista: Nelson Carvalho
									// Motivo: Quando o imovel tinha rota alternativa as informações que eram referentes a essa rota eram 
									// retidas da rota da quadra
									// Data: 05/11/2010
									// RM: 57
									sistemaParametro.setAnoMesFaturamento(
											(imovel.getRotaAlternativa() != null ? imovel.getRotaAlternativa().getFaturamentoGrupo().getAnoMesReferencia()
													: imovel.getQuadra().getRota().getFaturamentoGrupo().getAnoMesReferencia()));
									
									MedicaoHistorico medicaoHistoricoGerada = fachada.gerarHistoricoMedicao(
											medicaoTipo,
											imovel,
											(imovel.getRotaAlternativa() != null ? imovel.getRotaAlternativa().getFaturamentoGrupo()
													: imovel.getQuadra().getRota().getFaturamentoGrupo()),
													sistemaParametro);	
									
									sessao.setAttribute( "medicaoHistoricoGerada", medicaoHistoricoGerada );																		
									
									retorno = actionMapping
									.findForward("rechecarFaturamentoDados");									
								}
							} else {
								if (imovel.getHidrometroInstalacaoHistorico() == null) {
									alterarFaturamentoDadosActionForm
											.setIdAnormalidade(imovel
													.getLeituraAnormalidade() == null ? ""
													: ""
															+ imovel
																	.getLeituraAnormalidade()
																	.getId());
									alterarFaturamentoDadosActionForm
											.setNomeAnormalidade(imovel
													.getLeituraAnormalidade() == null ? ""
													: imovel
															.getLeituraAnormalidade()
															.getDescricao());
									httpServletRequest.setAttribute(
											"hidrometroInexistente", "true");
									sessao.removeAttribute("bloquearAtualizarleitura");
								} else {
									sessao.removeAttribute( "medicaoHistoricoGerada" );
									MedicaoTipo medicaoTipo= ((MedicaoTipo) ((List) tipoMedicaoEncontrada)
											.get(0));
									// Alterado por Rômulo Aurélio
									// Analista: Nelson Carvalho
									// Motivo: Quando o imovel tinha rota alternativa as informações que eram referentes a essa rota eram 
									// retidas da rota da quadra
									// Data: 05/11/2010
									// RM: 57
									sistemaParametro.setAnoMesFaturamento((imovel.getRotaAlternativa() != null ? imovel.getRotaAlternativa().getFaturamentoGrupo().getAnoMesReferencia()
											: imovel.getQuadra().getRota().getFaturamentoGrupo().getAnoMesReferencia()));
									
									MedicaoHistorico medicaoHistoricoGerada = fachada.gerarHistoricoMedicao(
											medicaoTipo,
											imovel,
											(imovel.getRotaAlternativa() != null ? imovel.getRotaAlternativa().getFaturamentoGrupo()
													: imovel.getQuadra().getRota().getFaturamentoGrupo())
											, sistemaParametro	);
									
									sessao.setAttribute( "medicaoHistoricoGerada", medicaoHistoricoGerada );
									
									retorno = actionMapping
									.findForward("rechecarFaturamentoDados");
								}
							}

						}
					}

					// Verifica se o usuário alterou a Anormalidade de Leitura

					if (codigoAnormalidadeLeituraDigitadoEnter != null
							&& !codigoAnormalidadeLeituraDigitadoEnter
									.equals("")) {
						FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
						filtroLeituraAnormalidade
								.adicionarParametro(new ParametroSimples(
										FiltroLeituraAnormalidade.ID,
										codigoAnormalidadeLeituraDigitadoEnter));

						Collection anormalidadeLeituraEncontrada = fachada
								.pesquisar(filtroLeituraAnormalidade,
										LeituraAnormalidade.class.getName());

						if (anormalidadeLeituraEncontrada != null
								&& !anormalidadeLeituraEncontrada.isEmpty()) {

							alterarFaturamentoDadosActionForm
									.setIdAnormalidade(""
											+ ((LeituraAnormalidade) ((List) anormalidadeLeituraEncontrada)
													.get(0)).getId());
							alterarFaturamentoDadosActionForm
									.setNomeAnormalidade(((LeituraAnormalidade) ((List) anormalidadeLeituraEncontrada)
											.get(0)).getDescricao());

						} else {
							alterarFaturamentoDadosActionForm
									.setIdAnormalidade("");
							httpServletRequest.setAttribute(
									"idAnormalidadeNaoEncontrada", "true");
							alterarFaturamentoDadosActionForm
									.setNomeAnormalidade("Anormalidade de leitura inexistente");
							httpServletRequest.setAttribute("nomeCampo",
									"idAnormalidade");
						}

						/**
						 * TODO : COSANPA Alterando o cálculo da média
						 */
						MedicaoTipo medicao = new MedicaoTipo();
						medicao.setId(new Integer(MedicaoTipo.LIGACAO_AGUA));
							
						boolean houveIntslacaoHidrometro = false;
						try {
							houveIntslacaoHidrometro = fachada.verificarInstalacaoSubstituicaoHidrometro(imovel.getId(), medicao);
						} catch (ControladorException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// Alterado por Rômulo Aurélio
						// Analista: Nelson Carvalho
						// Motivo: Quando o imovel tinha rota alternativa as informações que eram referentes a essa rota eram 
						// retidas da rota da quadra
						// Data: 05/11/2010
						// RM: 57
						sistemaParametro.setAnoMesFaturamento(
								(imovel.getRotaAlternativa() != null ? imovel.getRotaAlternativa().getFaturamentoGrupo().getAnoMesReferencia()
										: imovel.getQuadra().getRota().getFaturamentoGrupo().getAnoMesReferencia()));
						
						int qtdeEconomias = fachada
								.obterQuantidadeEconomias(imovel);
						int[] consumoMedioImovel = fachada
						.obterVolumeMedioAguaEsgoto(imovel.getId(),
								sistemaParametro.getAnoMesFaturamento(),Util.converterStringParaInteger(idTipoMedicao),
								houveIntslacaoHidrometro);
						if (consumoMedioImovel != null
								&& consumoMedioImovel.length > 0) {
							int consumoMedio = consumoMedioImovel[0];
							int consumoMedio5 = consumoMedio * 5;
							int qtdeEconomias30 = qtdeEconomias * 30;
							httpServletRequest.setAttribute("consumoMedio5",
									consumoMedio5);
							httpServletRequest.setAttribute("qtdeEconomias30",
									qtdeEconomias30);
						}

					}

				} else {

					httpServletRequest.setAttribute("nomeCampo", "idImovel");

					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Matrícula do imóvel");

				}
			}
            
            // Verificamos se a leitura atual informada existe
            if ( medicaoHistorico.getDataLeituraAtualInformada() == null  ){
                httpServletRequest.setAttribute(
                        "dataLeituraAtualNaoInformada", "true");
            }
            
			sessao.setAttribute("alterarFaturamentoDadosActionForm",
					alterarFaturamentoDadosActionForm);
			sessao.setAttribute("medicaoHistorico", medicaoHistorico);
			

			if (sessao.getAttribute("telaMedicaoConsumoDadosAnt") != null
					&& !sessao.getAttribute("telaMedicaoConsumoDadosAnt")
							.equals("")) {
				httpServletRequest.setAttribute("voltar", "S");
			}
			httpServletRequest.setAttribute("nomeCampo", "tipoMedicao");
			
		}
		

		return retorno;
	}

}
