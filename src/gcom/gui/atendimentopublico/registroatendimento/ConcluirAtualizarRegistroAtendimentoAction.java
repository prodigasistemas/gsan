package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosIdentificacaoLocalOcorrenciaHelper;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroBairroArea;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DivisaoEsgoto;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
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

/**
 * Esta classe tem por finalidade validar as informações das três abas do
 * processo de atualização de um registro de atendimento e chamar o método que
 * irá concluir a mesma
 * 
 * @author Sávio Luiz
 * @date 10/08/2006
 */
public class ConcluirAtualizarRegistroAtendimentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		AtualizarRegistroAtendimentoActionForm form = (AtualizarRegistroAtendimentoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);

		// recupara o id da especificação para verificar se
		// será gerado a ordem de serviço ou não dependendo da mudança
		// da especificação
		Integer idEspecificacaoBase = (Integer) sessao
				.getAttribute("idEspecificacaoBase");

		/*
		 * Validação Aba 01
		 * ======================================================================================================
		 */
		fachada.validarInserirRegistroAtendimentoDadosGerais(form
				.getDataAtendimento(), form.getHora(), form
				.getTempoEsperaInicial(), form.getTempoEsperaFinal(), form
				.getUnidade(), null);

		/*
		 * ======================================================================================================
		 * ======================================================================================================
		 */

		/*
		 * Validação Aba 02
		 * ======================================================================================================
		 */

		// [FS0040] - Validar Preenchimento dos campos
		String idImovel = form.getIdImovel();
		String pontoReferencia = form.getPontoReferencia();
		String idMunicipio = form.getIdMunicipio();
		String descricaoMunicipio = form.getDescricaoMunicipio();
		String cdBairro = form.getCdBairro();
		String descricaoBairro = form.getDescricaoBairro();
		String idAreaBairro = form.getIdBairroArea();
		String idlocalidade = form.getIdLocalidade();
		String descricaoLocalidade = form.getDescricaoLocalidade();
		String cdSetorComercial = form.getCdSetorComercial();
		String descricaoSetorComercial = form.getDescricaoSetorComercial();
		String numeroQuadra = form.getNnQuadra();
		String idDivisaoEsgoto = form.getIdDivisaoEsgoto();
		String idUnidade = form.getIdUnidadeAtual();
		String descricaoUnidade = form.getDescricaoUnidadeAtual();
		String idLocalOcorrencia = form.getIdLocalOcorrencia();
		String idPavimentoRua = form.getIdPavimentoRua();
		String idPavimentoCalcada = form.getIdPavimentoCalcada();
		String descricaoLocalOcorrencia = form.getDescricaoLocalOcorrencia();
		String imovelObrigatorio = form.getImovelObrigatorio();
		String pavimentoRuaObrigatorio = form.getPavimentoRuaObrigatorio();
		String pavimentoCalcadaObrigatorio = form
				.getPavimentoCalcadaObrigatorio();
		String solicitacaoTipoRelativoFaltaAgua = (String) sessao
				.getAttribute("solicitacaoTipoRelativoFaltaAgua");
		String solicitacaoTipoRelativoAreaEsgoto = (String) sessao
				.getAttribute("solicitacaoTipoRelativoAreaEsgoto");

		String desabilitarMunicipioBairro = (String) sessao
				.getAttribute("desabilitarMunicipioBairro");
		String indRuaLocalOcorrencia = form.getIndRuaLocalOcorrencia();
		String indCalcadaLocalOcorrencia = form.getIndCalcadaLocalOcorrencia();
		
		String idEspecificacao = form.getEspecificacao();
		String numeroRA = form.getNumeroRA();
		
		Collection colecaoEnderecos = (Collection) sessao.getAttribute("colecaoEnderecos");
		
		Collection colecaoPagamento = null;
		
		if (sessao.getAttribute("colecaoPagamentosDuplicidade") != null){
			colecaoPagamento = (Collection) sessao.getAttribute("colecaoPagamentosDuplicidade");
		}
		
		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = 
			new FiltroSolicitacaoTipoEspecificacao();
		
		filtroSolicitacaoTipoEspecificacao.adicionarParametro(
			new ParametroSimples(
				FiltroSolicitacaoTipoEspecificacao.ID, form.getEspecificacao()));
		
		Collection colecaoSolicitacaoTipoEspecificacao = 
			this.getFachada()
				.pesquisar(filtroSolicitacaoTipoEspecificacao,
						SolicitacaoTipoEspecificacao.class.getName());

		SolicitacaoTipoEspecificacao especificacao = 
			(SolicitacaoTipoEspecificacao) Util.retonarObjetoDeColecao(colecaoSolicitacaoTipoEspecificacao);

		fachada.validarCamposObrigatoriosRA_2ABA(idImovel, pontoReferencia,
				idMunicipio, descricaoMunicipio, cdBairro, descricaoBairro,
				idAreaBairro, idlocalidade, descricaoLocalidade,
				cdSetorComercial, descricaoSetorComercial, numeroQuadra,
				idDivisaoEsgoto, idUnidade, descricaoUnidade,
				idLocalOcorrencia, idPavimentoRua, idPavimentoCalcada,
				descricaoLocalOcorrencia, imovelObrigatorio,
				pavimentoRuaObrigatorio, pavimentoCalcadaObrigatorio,
				solicitacaoTipoRelativoFaltaAgua,
				solicitacaoTipoRelativoAreaEsgoto, desabilitarMunicipioBairro,
				indRuaLocalOcorrencia, indCalcadaLocalOcorrencia, new Integer(idEspecificacao), 
				new Integer(numeroRA), colecaoEnderecos,especificacao,colecaoPagamento, usuarioLogado);

		// -----------------------------------------------------------------------

		// valida os campos de enter(caso tenha mudado algum valor
		// validar)
		validarCamposEnter(form, fachada, httpServletRequest, actionMapping,
				sessao);

		/*
		 * ======================================================================================================
		 * ======================================================================================================
		 */

		/*
		 * Validação Aba 03
		 * ======================================================================================================
		 */

		// recupera a coleção de RA solicitante
		Collection colecaoRASolicitante = (Collection) sessao
				.getAttribute("colecaoRASolicitante");

		Collection colecaoRASolicitanteRemovida = (Collection) sessao
				.getAttribute("colecaoRASolicitanteRemovidas");

		String idSolicitantePrincipal = form.getIdSolicitantePrincipal();
		if (idSolicitantePrincipal != null
				&& !idSolicitantePrincipal.equals("")) {
			// responsável pera troca do solicitante principal
			// caso tenha sido trocado então sai da coleção
			boolean trocaPrincipal = false;

			if (colecaoRASolicitante != null && !colecaoRASolicitante.isEmpty()) {
				Iterator iteratorRASolicitante = colecaoRASolicitante
						.iterator();
				while (iteratorRASolicitante.hasNext()) {
					RegistroAtendimentoSolicitante registroAtendimentoSolicitante = (RegistroAtendimentoSolicitante) iteratorRASolicitante
							.next();
					// caso a colecao só tenha um solicitante então o
					// solicitante será o principal
					if (colecaoRASolicitante.size() == 1) {
						registroAtendimentoSolicitante
								.setIndicadorSolicitantePrincipal(new Short("1"));
					} else {
						// senão se o id socilitante seja igual ao o id
						// do solicitante que foi escolhido como
						// principal
						if (registroAtendimentoSolicitante.getUltimaAlteracao()
								.getTime() == Long
								.parseLong(idSolicitantePrincipal)) {
							// se for diferente de 1, ou seja se o
							// solicitante não era o principal
							if (registroAtendimentoSolicitante
									.getIndicadorSolicitantePrincipal() != 1) {
								// seta o valor 1 ao indicador principal do
								// solicitante
								registroAtendimentoSolicitante
										.setIndicadorSolicitantePrincipal(new Short(
												"1"));
								// verifica se o indicador principal do
								// solicitante que era 1 anteriormente já
								// foi mudado para 2(nesse caso o boolean
								// trocaPrincipal está com o valor true).
								if (trocaPrincipal) {
									break;
								}
								trocaPrincipal = true;
							} else {
								break;
							}

						} else {
							// parte que muda o indicador principal do
							// solicitante(que não é mais principal)
							// para 2
							if (registroAtendimentoSolicitante
									.getIndicadorSolicitantePrincipal() == 1) {
								registroAtendimentoSolicitante
										.setIndicadorSolicitantePrincipal(new Short(
												"2"));
								if (trocaPrincipal) {
									break;
								}
								trocaPrincipal = true;
							}
						}
					}
				}
			} else {
				// [FS0021] - Verificar registro atendimento sem
				// solicitante
				throw new ActionServletException(
						"atencao.informar_registro_atendimento_solicitante");
			}
		}

		/*
		 * ======================================================================================================
		 * ======================================================================================================
		 */
		
		/*
         * Validação Aba 04 - Anexos
         * ======================================================================================================
         */
		Collection colecaoRegistroAtendimentoAnexo = null;
		
		if (sessao.getAttribute("colecaoRegistroAtendimentoAnexo") != null){
			
			colecaoRegistroAtendimentoAnexo = (Collection) 
			sessao.getAttribute("colecaoRegistroAtendimentoAnexo");	
		}
		
		boolean indicadorEncerramentoAutomatico = false;
		
		if (especificacao.getIndicadorEncerramentoAutomatico() == 
			SolicitacaoTipoEspecificacao.INDICADOR_COM_ENCERRAMENTO_AUTOMATICO.shortValue()) {
			
			indicadorEncerramentoAutomatico = true;
			
			if (form.getObservacao() == null || form.getObservacao().trim().equals("")) {
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Observação");
			}
		}

		//Comentado por Raphael Rossiter
		/*OrdemServico os = null;

		if (sessao.getAttribute("ordemServico") != null) {
			os = (OrdemServico) sessao.getAttribute("ordemServico");
		}*/

		Collection colecaoEnderecoLocalOcorrencia = null;

		if (sessao.getAttribute("colecaoEnderecos") != null) {
			colecaoEnderecoLocalOcorrencia = (Collection) sessao
					.getAttribute("colecaoEnderecos");
		}
		
		Collection colecaoRegistroAtendimentoConta = null;

		if (sessao.getAttribute("colecaoRAContasAtualizar") != null) {
			colecaoRegistroAtendimentoConta = (Collection) sessao
					.getAttribute("colecaoRAContasAtualizar");
		}
		
		Collection colecaoRegistroAtendimentoContaRemover = null;

		if (sessao.getAttribute("colecaoRAContasRemover") != null) {
			colecaoRegistroAtendimentoContaRemover = (Collection) sessao
					.getAttribute("colecaoRAContasRemover");
		}

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		Date ultimaAlteracao = (Date) sessao.getAttribute("ultimaAlteracao");
		
		
		// Colocado por Raphael Rossiter em 01/03/2007
		//ServicoTipo
		Integer idServicoTipo = null;
		if (sessao.getAttribute("servicoTipo") != null){
			idServicoTipo = (Integer) sessao.getAttribute("servicoTipo");
		}
		
		BigDecimal valorNnCoordenadaNorte = null;		
		if (form.getNnCoordenadaNorte() != null){
			String valorNnCoordenadaNorteStr = form.getNnCoordenadaNorte().replace(',','.');
			if (valorNnCoordenadaNorteStr != null && !valorNnCoordenadaNorteStr.trim().equals("")){
				valorNnCoordenadaNorte = new BigDecimal(valorNnCoordenadaNorteStr);
			}
		}
		
		BigDecimal valorNnCoordenadaLeste = null;
		if (form.getNnCoordenadaLeste() != null){
			String valorNnCoordenadaLesteStr = form.getNnCoordenadaLeste().replace(',','.');
			if (valorNnCoordenadaLesteStr != null  && !valorNnCoordenadaLesteStr.trim().equals("")){
				valorNnCoordenadaLeste = new BigDecimal(valorNnCoordenadaLesteStr);
			}			
		}
		
		BigDecimal nnDiametro = null;
		
		if(form.getNnDiametro()!=null && !form.getNnDiametro().equals("")){
			nnDiametro = new BigDecimal(form.getNnDiametro());
		}

		// [SB0028] Inclui Registro de Atendimento
		Integer[] idsGerados = fachada.atualizarRegistroAtendimento(
					new Integer(form.getNumeroRA()),
					Short.parseShort(form.getTipo()), 
					form.getDataAtendimento(),
					form.getHora(), 
					form.getTempoEsperaInicial(), 
					form.getTempoEsperaFinal(), 
					Util.converterStringParaInteger(form.getMeioSolicitacao()),
					Util.converterStringParaInteger(form.getEspecificacao()), 
					form.getDataPrevista(), 
					form.getObservacao(), 
					Util.converterStringParaInteger(form.getIdImovel()), 
					form.getDescricaoLocalOcorrencia(), 
					Util.converterStringParaInteger(form.getTipoSolicitacao()),
					colecaoEnderecoLocalOcorrencia, 
					form.getPontoReferencia(), 
					Util.converterStringParaInteger(form.getIdBairroArea()),
					Util.converterStringParaInteger(form.getIdLocalidade()),
					Util.converterStringParaInteger(form.getIdSetorComercial()),
					Util.converterStringParaInteger(form.getIdQuadra()), 
					Util.converterStringParaInteger(form.getIdDivisaoEsgoto()),
					Util.converterStringParaInteger(form.getIdLocalOcorrencia()),
					Util.converterStringParaInteger(form.getIdPavimentoRua()), 
					Util.converterStringParaInteger(form.getIdPavimentoCalcada()), 
					Util.converterStringParaInteger(form.getUnidade()),
					usuario, 
					Util.converterStringParaInteger(form.getIndMatricula()),
				    ultimaAlteracao, 
				    colecaoRASolicitante,
				    colecaoRASolicitanteRemovida, 
				    idServicoTipo,
				    (Integer)sessao.getAttribute("idEspecificacaoBase"),
				    Util.converterStringParaInteger(form.getIdUnidadeAtual()),
				    valorNnCoordenadaNorte,
				    valorNnCoordenadaLeste, colecaoRegistroAtendimentoAnexo,
				    colecaoRegistroAtendimentoConta, 
				    colecaoRegistroAtendimentoContaRemover,
				    colecaoPagamento, nnDiametro);

		
		// Caso a especificação seja de encerramento automático, encerra o ra
		if (indicadorEncerramentoAutomatico) {
			
			RegistroAtendimentoUnidade registroAtendimentoUnidade = this.montarRegistroAtendimentoParaEncerramento(new Integer(form.getNumeroRA()), usuario);
			
			fachada.encerrarRegistroAtendimento(registroAtendimentoUnidade.getRegistroAtendimento(),
					registroAtendimentoUnidade, usuario, null, null, null, null, false,null,false);
		}

		
		// Colocado por Raphael Rossiter em 01/03/2007
		//Montando a pagina de sucesso
		if (!fachada.gerarOrdemServicoAutomatica(Util.converterStringParaInteger(form.getEspecificacao())) 
			&& fachada.gerarOrdemServicoOpcional(Util.converterStringParaInteger(form.getEspecificacao()))
			&& !idEspecificacaoBase.equals(new Integer(form.getEspecificacao()))){
			
			montarPaginaSucesso(httpServletRequest,
					"Registro de Atendimento de código " + form.getNumeroRA()
							+ " atualizado com sucesso.",
					"Atualizar outro Registro de Atendimento",
					"exibirFiltrarRegistroAtendimentoAction.do?menu=sim",
					"exibirGerarOrdemServicoAction.do?menu=sim&forward=exibirGerarOrdemServico&veioRA=OK&idRegistroAtendimento=" + form.getNumeroRA(),
					"Gerar OS",
					"Voltar", "exibirConsultarRegistroAtendimentoAction.do?numeroRA="
					+ form.getNumeroRA());
		}
		else{
			
			if (fachada.gerarOrdemServicoAutomatica(Util.converterStringParaInteger(form.getEspecificacao()))
				&& !idEspecificacaoBase.equals(new Integer(form.getEspecificacao()))){
				
				montarPaginaSucessoUmRelatorio(httpServletRequest, "Registro de Atendimento de código "
	                    + form.getNumeroRA()+ " atualizado com sucesso.", "Atualizar outro Registro de Atendimento",
	                    "exibirFiltrarRegistroAtendimentoAction.do?menu=sim",
						"exibirConsultarRegistroAtendimentoAction.do?numeroRA="
								+ form.getNumeroRA(),
								null,null,
						null,"Imprimir OS" ,"gerarRelatorioOrdemServicoAction.do?idsOS=" + idsGerados[1]);
				
			}
			else{
				
				montarPaginaSucesso(httpServletRequest,
						"Registro de Atendimento de código " + form.getNumeroRA()
								+ " atualizado com sucesso.",
						"Atualizar outro Registro de Atendimento",
						"exibirFiltrarRegistroAtendimentoAction.do?menu=sim",
						"exibirConsultarRegistroAtendimentoAction.do?numeroRA="
								+ form.getNumeroRA(),
						"Voltar");
			}
			
		}
		
		// Comentado por Raphael Rossiter em 01/03/2007
		// Montando a página de sucesso
		/*if ((!fachada.gerarOrdemServicoOpcional(Util.converterStringParaInteger(form.getEspecificacao())))
			&& fachada.gerarOrdemServicoOpcional(Util.converterStringParaInteger(form.getEspecificacao()))
				&& !(idEspecificacaoBase.equals(new Integer(form
						.getEspecificacao())))) {

			montarPaginaSucesso(httpServletRequest,
					"Registro de Atendimento de código " + form.getNumeroRA()
							+ " atualizado com sucesso.",
					"Atualizar outro Registro de Atendimento",
					"exibirFiltrarRegistroAtendimentoAction.do?menu=sim",
					"exibirGerarOrdemServicoAction.do?menu=sim&forward=exibirGerarOrdemServico&veioRA=OK&idRegistroAtendimento=" + form.getNumeroRA(),
					"Gerar OS",
					"Voltar", "exibirConsultarRegistroAtendimentoAction.do?numeroRA="
					+ form.getNumeroRA());
		} else {

			montarPaginaSucesso(httpServletRequest,
					"Registro de Atendimento de código " + form.getNumeroRA()
							+ " atualizado com sucesso.",
					"Atualizar outro Registro de Atendimento",
					"exibirFiltrarRegistroAtendimentoAction.do?menu=sim",
					"exibirConsultarRegistroAtendimentoAction.do?numeroRA="
							+ form.getNumeroRA(),
					"Voltar");
		}*/

		// remove as coleções da sessão
		sessao.removeAttribute("AtualizarRegistroAtendimentoActionForm");
		sessao.removeAttribute("colecaoMeioSolicitacao");
		sessao.removeAttribute("colecaoSolicitacaoTipo");
		sessao.removeAttribute("colecaoSolicitacaoTipoEspecificacao");
		sessao.removeAttribute("colecaoEnderecos");
		sessao.removeAttribute("idEspecificacaoBase");
		sessao.removeAttribute("ultimaAlteracao");
		sessao.removeAttribute("ordemServico");
		sessao.removeAttribute("colecaoDivisaoEsgoto");
		sessao.removeAttribute("colecaoLocalOcorrencia");
		sessao.removeAttribute("colecaoPavimentoRua");
		sessao.removeAttribute("colecaoPavimentoCalcada");
		sessao.removeAttribute("solicitacaoTipoRelativoFaltaAgua");
		sessao.removeAttribute("solicitacaoTipoRelativoAreaEsgoto");
		sessao.removeAttribute("colecaoBairroArea");
		sessao.removeAttribute("habilitarAlteracaoEndereco");
		sessao.removeAttribute("desabilitarMunicipioBairro");
		sessao.removeAttribute("desabilitarDescricaoLocalOcorrencia");
		sessao.removeAttribute("colecaoRASolicitanteRemovidas");
		sessao.removeAttribute("colecaoRASolicitante");
		sessao.removeAttribute("osAutomatica");
		sessao.removeAttribute("colecaoRAContasAtualizar");
		sessao.removeAttribute("colecaoRAContasRemover");

		return retorno;
	}

	private void validarCamposEnter(
			AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest,
			ActionMapping actionMapping, HttpSession sessao) {

		/*
		 * [SB0004] Obté½m e Habilita/Desabilita Dados da Identificação do
		 * Local da Ocorrência e Dados do Solicitante
		 * 
		 * [FS0019] erificar endereço do imóvel [FS0020] - Verificar
		 * existência de registro de atendimento para o imóvel com a mesma
		 * especificação
		 * 
		 * [SB0020] Verifica Situação do Imóvel e Especificação
		 * 
		 */

		// [SB0002] Habilita/Desabilita Município, Bairro, área do
		// Bairro e
		// Divisão de Esgoto
		ObterDadosIdentificacaoLocalOcorrenciaHelper habilitaGeograficoDivisaoEsgoto = fachada
				.habilitarGeograficoDivisaoEsgoto(new Integer(
						atualizarRegistroAtendimentoActionForm
								.getTipoSolicitacao()));

		String idImovel = atualizarRegistroAtendimentoActionForm.getIdImovel();
		String inscricaoImovel = atualizarRegistroAtendimentoActionForm
				.getInscricaoImovel();
		// caso seja a pesquisa do enter do imóvel ou o indicador de
		// validação de matrícula do imóvel seja 1
		if (idImovel != null && !idImovel.equalsIgnoreCase("")
				&& (inscricaoImovel == null || inscricaoImovel.equals(""))) {
			
			
			ObterDadosIdentificacaoLocalOcorrenciaHelper dadosIdentificacaoLocalOcorrencia = fachada
			.obterDadosIdentificacaoLocalOcorrenciaAtualizar(new Integer(
					atualizarRegistroAtendimentoActionForm.getIdImovel()),
					new Integer(atualizarRegistroAtendimentoActionForm
							.getEspecificacao()), new Integer(
							atualizarRegistroAtendimentoActionForm
									.getTipoSolicitacao()), new Integer(
							atualizarRegistroAtendimentoActionForm
									.getNumeroRA()), true);

			
			if (dadosIdentificacaoLocalOcorrencia.getImovel() != null) {

				// [FS0020] - Verificar existência de registro de
				// atendimento
				// para o imóvel com a mesma especificação
				fachada.verificarExistenciaRAImovelMesmaEspecificacao(
						dadosIdentificacaoLocalOcorrencia.getImovel().getId(),
						new Integer(atualizarRegistroAtendimentoActionForm
								.getEspecificacao()));

				// [SB0020] Verifica Situação do imóvel e
				// Especificação
				fachada.verificarSituacaoImovelEspecificacao(
						dadosIdentificacaoLocalOcorrencia.getImovel(),
						new Integer(atualizarRegistroAtendimentoActionForm
								.getEspecificacao()));

				atualizarRegistroAtendimentoActionForm
						.setIdImovel(dadosIdentificacaoLocalOcorrencia
								.getImovel().getId().toString());

				atualizarRegistroAtendimentoActionForm
						.setInscricaoImovel(dadosIdentificacaoLocalOcorrencia
								.getImovel().getInscricaoFormatada());

				if (!dadosIdentificacaoLocalOcorrencia.isInformarEndereco()) {
					Collection colecaoEnderecos = new ArrayList();
					colecaoEnderecos.add(dadosIdentificacaoLocalOcorrencia
							.getImovel());
					sessao.setAttribute("colecaoEnderecos", colecaoEnderecos);
				} else if (dadosIdentificacaoLocalOcorrencia
						.getEnderecoDescritivo() != null) {
					atualizarRegistroAtendimentoActionForm
							.setDescricaoLocalOcorrencia(dadosIdentificacaoLocalOcorrencia
									.getEnderecoDescritivo());
					sessao.removeAttribute("colecaoEnderecos");
				} else {
					sessao.removeAttribute("colecaoEnderecos");
				}

				this
						.carregarMunicipioBairroParaImovel(
								habilitaGeograficoDivisaoEsgoto,
								dadosIdentificacaoLocalOcorrencia,
								atualizarRegistroAtendimentoActionForm, sessao,
								fachada);

				atualizarRegistroAtendimentoActionForm
						.setIdLocalidade(dadosIdentificacaoLocalOcorrencia
								.getImovel().getLocalidade().getId().toString());

				atualizarRegistroAtendimentoActionForm
						.setDescricaoLocalidade(dadosIdentificacaoLocalOcorrencia
								.getImovel().getLocalidade().getDescricao());

				atualizarRegistroAtendimentoActionForm
						.setIdSetorComercial(dadosIdentificacaoLocalOcorrencia
								.getImovel().getSetorComercial().getId()
								.toString());

				atualizarRegistroAtendimentoActionForm
						.setCdSetorComercial(String
								.valueOf(dadosIdentificacaoLocalOcorrencia
										.getImovel().getSetorComercial()
										.getCodigo()));

				atualizarRegistroAtendimentoActionForm
						.setDescricaoSetorComercial(dadosIdentificacaoLocalOcorrencia
								.getImovel().getSetorComercial().getDescricao());

				atualizarRegistroAtendimentoActionForm.setIdQuadra(String
						.valueOf(dadosIdentificacaoLocalOcorrencia.getImovel()
								.getQuadra().getId()));

				atualizarRegistroAtendimentoActionForm.setNnQuadra(String
						.valueOf(dadosIdentificacaoLocalOcorrencia.getImovel()
								.getQuadra().getNumeroQuadra()));

			}
		}

		String idMunicipio = atualizarRegistroAtendimentoActionForm
				.getIdMunicipio();
		String descricaoMunicipio = atualizarRegistroAtendimentoActionForm
				.getDescricaoMunicipio();

		if (idMunicipio != null
				&& !idMunicipio.equalsIgnoreCase("")
				&& (descricaoMunicipio == null || descricaoMunicipio.equals(""))) {

			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, atualizarRegistroAtendimentoActionForm
							.getIdMunicipio()));

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoMunicipio = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (colecaoMunicipio == null || colecaoMunicipio.isEmpty()) {

				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Município");

			}
			Municipio municipio = (Municipio) Util
					.retonarObjetoDeColecao(colecaoMunicipio);

			atualizarRegistroAtendimentoActionForm.setIdMunicipio(municipio
					.getId().toString());
			atualizarRegistroAtendimentoActionForm
					.setDescricaoMunicipio(municipio.getNome());

			httpServletRequest.setAttribute("nomeCampo", "cdBairro");
		}

		String codigoBairro = atualizarRegistroAtendimentoActionForm
				.getCdBairro();
		String descricaoBairro = atualizarRegistroAtendimentoActionForm
				.getDescricaoBairro();

		if (codigoBairro != null && !codigoBairro.equalsIgnoreCase("")) {

			if ((descricaoBairro == null || descricaoBairro.equals(""))) {

				FiltroBairro filtroBairro = new FiltroBairro();

				filtroBairro.adicionarParametro(new ParametroSimples(
						FiltroBairro.CODIGO,
						atualizarRegistroAtendimentoActionForm.getCdBairro()));

				filtroBairro
						.adicionarParametro(new ParametroSimples(
								FiltroBairro.MUNICIPIO_ID,
								atualizarRegistroAtendimentoActionForm
										.getIdMunicipio()));

				filtroBairro.adicionarParametro(new ParametroSimples(
						FiltroBairro.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoBairro = fachada.pesquisar(filtroBairro,
						Bairro.class.getName());

				if (colecaoBairro == null || colecaoBairro.isEmpty()) {

					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Bairro");

				}
				Bairro bairro = (Bairro) Util
						.retonarObjetoDeColecao(colecaoBairro);

				atualizarRegistroAtendimentoActionForm.setCdBairro(String
						.valueOf(bairro.getCodigo()));
				atualizarRegistroAtendimentoActionForm.setCdBairro(String
						.valueOf(bairro.getId()));
				atualizarRegistroAtendimentoActionForm
						.setDescricaoBairro(bairro.getNome());
				this.pesquisarBairroArea(new Integer(
						atualizarRegistroAtendimentoActionForm
								.getIdBairro()), fachada, sessao);
			}

		}

		String idLocalidade = atualizarRegistroAtendimentoActionForm
				.getIdLocalidade();
		String descricaoLocalidade = atualizarRegistroAtendimentoActionForm
				.getDescricaoBairro();

		if (idLocalidade != null
				&& !idLocalidade.equalsIgnoreCase("")
				&& (descricaoLocalidade == null || descricaoLocalidade
						.equals(""))) {
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, atualizarRegistroAtendimentoActionForm
							.getIdLocalidade()));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoLocalidade == null || colecaoLocalidade.isEmpty()) {

				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Localidade");

			}
			Localidade localidade = (Localidade) Util
					.retonarObjetoDeColecao(colecaoLocalidade);

			atualizarRegistroAtendimentoActionForm
					.setIdLocalidade(localidade.getId().toString());
			atualizarRegistroAtendimentoActionForm
					.setDescricaoLocalidade(localidade.getDescricao());

			httpServletRequest
					.setAttribute("nomeCampo", "cdSetorComercial");
		}

		String cdSetorComercial = atualizarRegistroAtendimentoActionForm
				.getCdSetorComercial();
		String descricaoSetorComercial = atualizarRegistroAtendimentoActionForm
				.getDescricaoSetorComercial();

		if (cdSetorComercial != null
				&& !cdSetorComercial.equalsIgnoreCase("")
				&& (descricaoSetorComercial == null || descricaoSetorComercial
						.equals(""))) {

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID_LOCALIDADE,
					atualizarRegistroAtendimentoActionForm.getIdLocalidade()));

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
					atualizarRegistroAtendimentoActionForm
							.getCdSetorComercial()));

			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoSetorComercial = fachada.pesquisar(
					filtroSetorComercial, SetorComercial.class.getName());

			if (colecaoSetorComercial == null
					|| colecaoSetorComercial.isEmpty()) {

				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Setor Comercial");

			}
			SetorComercial setorComercial = (SetorComercial) Util
					.retonarObjetoDeColecao(colecaoSetorComercial);

			atualizarRegistroAtendimentoActionForm
					.setIdSetorComercial(setorComercial.getId().toString());
			atualizarRegistroAtendimentoActionForm
					.setCdSetorComercial(String.valueOf(setorComercial
							.getCodigo()));
			atualizarRegistroAtendimentoActionForm
					.setDescricaoSetorComercial(setorComercial
							.getDescricao());

			httpServletRequest.setAttribute("nomeCampo", "nnQuadra");
		}

		String nnQuadra = atualizarRegistroAtendimentoActionForm.getNnQuadra();

		if (nnQuadra != null && !nnQuadra.equalsIgnoreCase("")) {

			FiltroQuadra filtroQuadra = new FiltroQuadra();

			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID_SETORCOMERCIAL,
					atualizarRegistroAtendimentoActionForm
							.getIdSetorComercial()));

			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.NUMERO_QUADRA,
					atualizarRegistroAtendimentoActionForm.getNnQuadra()));

			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoQuadra = fachada.pesquisar(filtroQuadra,
					Quadra.class.getName());

			if (colecaoQuadra == null || colecaoQuadra.isEmpty()) {

				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Quadra");

			}
			Quadra quadra = (Quadra) Util
					.retonarObjetoDeColecao(colecaoQuadra);

			atualizarRegistroAtendimentoActionForm.setIdQuadra(quadra
					.getId().toString());
			atualizarRegistroAtendimentoActionForm.setNnQuadra(String
					.valueOf(quadra.getNumeroQuadra()));

			// [SB0006] Obtém Divisão de Esgoto
			DivisaoEsgoto divisaoEsgoto = fachada.obterDivisaoEsgoto(quadra
					.getId(), habilitaGeograficoDivisaoEsgoto
					.isSolicitacaoTipoRelativoAreaEsgoto());

			if (divisaoEsgoto != null) {
				atualizarRegistroAtendimentoActionForm
						.setIdDivisaoEsgoto(divisaoEsgoto.getId()
								.toString());

				/*
				 * [FS0013] Verificar compatibilidade entre divisão de
				 * esgoto e localidade/setor/quadra [SB0007] Define
				 * Unidade Destino da Divisão de Esgoto
				 */
				this
						.verificarCompatibilidadeDefinirUnidadeDestinoDivisaoEsgoto(
								fachada,
								atualizarRegistroAtendimentoActionForm,
								habilitaGeograficoDivisaoEsgoto
										.isSolicitacaoTipoRelativoAreaEsgoto());

			}
		}
	}

	public void verificarCompatibilidadeDefinirUnidadeDestinoDivisaoEsgoto(
			Fachada fachada,
			AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm,
			boolean solicitacaoTipoRelativoAreaEsgoto) {

		fachada
				.verificarCompatibilidadeDivisaoEsgotoLocalidadeSetorQuadra(
						Util
								.converterStringParaInteger(atualizarRegistroAtendimentoActionForm
										.getIdLocalidade()),
						Util
								.converterStringParaInteger(atualizarRegistroAtendimentoActionForm
										.getIdSetorComercial()),
						Util
								.converterStringParaInteger(atualizarRegistroAtendimentoActionForm
										.getIdQuadra()),
						Util
								.converterStringParaInteger(atualizarRegistroAtendimentoActionForm
										.getIdDivisaoEsgoto()));

	}

	public void pesquisarBairroArea(Integer idBairro, Fachada fachada,
			HttpSession sessao) {

		FiltroBairroArea filtroBairroArea = new FiltroBairroArea();

		filtroBairroArea.adicionarParametro(new ParametroSimples(
				FiltroBairroArea.ID_BAIRRO, idBairro));

		Collection colecaoBairroArea = fachada.pesquisar(filtroBairroArea,
				BairroArea.class.getName());

		if (colecaoBairroArea == null || colecaoBairroArea.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"BAIRRO_AREA");
		}
		sessao.setAttribute("colecaoBairroArea", colecaoBairroArea);
	}

	public void carregarMunicipioBairroParaImovel(
			ObterDadosIdentificacaoLocalOcorrenciaHelper habilitaGeograficoDivisaoEsgoto,
			ObterDadosIdentificacaoLocalOcorrenciaHelper obterDadosIdentificacaoLocalOcorrenciaHelper,
			AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm,
			HttpSession sessao, Fachada fachada) {

		if (habilitaGeograficoDivisaoEsgoto != null
				&& habilitaGeograficoDivisaoEsgoto
						.isSolicitacaoTipoRelativoFaltaAgua()
				&& obterDadosIdentificacaoLocalOcorrenciaHelper
						.getEnderecoDescritivo() == null) {

			atualizarRegistroAtendimentoActionForm
					.setIdMunicipio(obterDadosIdentificacaoLocalOcorrenciaHelper
							.getImovel().getLogradouroBairro().getBairro()
							.getMunicipio().getId().toString());

			atualizarRegistroAtendimentoActionForm
					.setDescricaoMunicipio(obterDadosIdentificacaoLocalOcorrenciaHelper
							.getImovel().getLogradouroBairro().getBairro()
							.getMunicipio().getNome());

			atualizarRegistroAtendimentoActionForm
					.setIdBairro(obterDadosIdentificacaoLocalOcorrenciaHelper
							.getImovel().getLogradouroBairro().getBairro()
							.getId().toString());

			atualizarRegistroAtendimentoActionForm.setCdBairro(String
					.valueOf(obterDadosIdentificacaoLocalOcorrenciaHelper
							.getImovel().getLogradouroBairro().getBairro()
							.getCodigo()));

			atualizarRegistroAtendimentoActionForm
					.setDescricaoBairro(obterDadosIdentificacaoLocalOcorrenciaHelper
							.getImovel().getLogradouroBairro().getBairro()
							.getNome());

			this.pesquisarBairroArea(
					obterDadosIdentificacaoLocalOcorrenciaHelper.getImovel()
							.getLogradouroBairro().getBairro().getId(),
					fachada, sessao);

			sessao.setAttribute("desabilitarMunicipioBairro", "OK");

		} else {

			atualizarRegistroAtendimentoActionForm.setIdMunicipio("");

			atualizarRegistroAtendimentoActionForm.setDescricaoMunicipio("");

			atualizarRegistroAtendimentoActionForm.setIdBairro("");

			atualizarRegistroAtendimentoActionForm.setCdBairro("");

			atualizarRegistroAtendimentoActionForm.setDescricaoBairro("");

			sessao.removeAttribute("colecaoBairroArea");
		}
	}
	
	private RegistroAtendimentoUnidade montarRegistroAtendimentoParaEncerramento(Integer idRegistroAtendimento, Usuario usuarioLogado) {
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
		filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID, idRegistroAtendimento));
		
		Collection colecaoRegistroAtendimento = fachada
		.pesquisar(filtroRegistroAtendimento,
				RegistroAtendimento.class.getName());
		
		RegistroAtendimento registroAtendimento = (RegistroAtendimento) Util.retonarObjetoDeColecao(colecaoRegistroAtendimento);
		
		FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
		filtroAtendimentoMotivoEncerramento.adicionarParametro(new ParametroSimples(FiltroAtendimentoMotivoEncerramento.ID, AtendimentoMotivoEncerramento.CONCLUSAO_SERVICO));
		
		Collection colecaoAtendimentoMotivoEncerramento = fachada
		.pesquisar(filtroAtendimentoMotivoEncerramento,
				AtendimentoMotivoEncerramento.class.getName());
		
		AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = (AtendimentoMotivoEncerramento) Util.retonarObjetoDeColecao(colecaoAtendimentoMotivoEncerramento);
		
		registroAtendimento.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
		
		RegistroAtendimentoUnidade registroAtendimentoUnidade = new RegistroAtendimentoUnidade();
		registroAtendimentoUnidade.setRegistroAtendimento(registroAtendimento);
		registroAtendimentoUnidade.setUnidadeOrganizacional(usuarioLogado.getUnidadeOrganizacional());
		registroAtendimentoUnidade.setUsuario(usuarioLogado);
		registroAtendimentoUnidade.setUltimaAlteracao(new Date());
		registroAtendimentoUnidade.getRegistroAtendimento().setDataEncerramento(new Date());
		
		AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
		atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);
		
		registroAtendimentoUnidade.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);
		
		return registroAtendimentoUnidade;
	}
}
