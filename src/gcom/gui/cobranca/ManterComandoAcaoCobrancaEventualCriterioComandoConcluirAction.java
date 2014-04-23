package gcom.gui.cobranca;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaAcaoAtividadeComandoFiscalizacaoSituacao;
import gcom.cobranca.CobrancaAtividade;
import gcom.cobranca.FiltroCobrancaAcaoAtividadeComandoFiscalizacaoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.util.ConstantesSistema;
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
 * [UC0244] Manter Comando de Ação de Conbrança
 * 
 * @author Rafael Santos
 * @since 24/04/2006
 */
public class ManterComandoAcaoCobrancaEventualCriterioComandoConcluirAction
		extends GcomAction {

	/**
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Mudar isso quando implementar a parte de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		ManterComandoAcaoCobrancaDetalhesActionForm manterComandoAcaoCobrancaDetalhesActionForm = null;
		if (sessao.getAttribute("manterComandoAcaoCobrancaDetalhesActionForm") != null) {
			manterComandoAcaoCobrancaDetalhesActionForm = (ManterComandoAcaoCobrancaDetalhesActionForm) sessao
					.getAttribute("manterComandoAcaoCobrancaDetalhesActionForm");
		}

		CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando = null;

		if (sessao.getAttribute("cobrancaAcaoAtividadeComando") != null) {
			cobrancaAcaoAtividadeComando = (CobrancaAcaoAtividadeComando) sessao
					.getAttribute("cobrancaAcaoAtividadeComando");
		}

		String idComando = httpServletRequest.getParameter("idComando");

		String idLocalidade = manterComandoAcaoCobrancaDetalhesActionForm
				.getLocalidadeOrigemID();
		String codigoSetorComercial = manterComandoAcaoCobrancaDetalhesActionForm
				.getSetorComercialOrigemCD();

		String idLocalidadeFinal = manterComandoAcaoCobrancaDetalhesActionForm
				.getLocalidadeDestinoID();
		String codigoSetorComercialFinal = manterComandoAcaoCobrancaDetalhesActionForm
				.getSetorComercialDestinoCD();

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		if (idLocalidade != null
				&& !idLocalidade.toString().trim().equalsIgnoreCase("")) {
			filtroLocalidade.limparListaParametros();
			// coloca parametro no filtro
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, new Integer(idLocalidade)));
			// pesquisa
			Collection localidades = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());
			if (localidades == null || localidades.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.localidade_inicial_inexistente");
			}
		}

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		if (codigoSetorComercial != null
				&& !codigoSetorComercial.toString().trim().equalsIgnoreCase("")) {
			if (idLocalidade != null
					&& !idLocalidade.toString().trim().equalsIgnoreCase("")) {
				filtroSetorComercial.limparListaParametros();
				// coloca parametro no filtro
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, new Integer(
								idLocalidade)));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						new Integer(codigoSetorComercial)));
				// pesquisa
				Collection setorComerciais = fachada.pesquisar(
						filtroSetorComercial, SetorComercial.class.getName());
				if (setorComerciais == null || setorComerciais.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.setor_inicial_inexistente");
				}
			}

		}

		filtroLocalidade = new FiltroLocalidade();
		if (idLocalidade != null
				&& !idLocalidade.toString().trim().equalsIgnoreCase("")) {
			filtroLocalidade.limparListaParametros();
			// coloca parametro no filtro
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, new Integer(idLocalidadeFinal)));
			// pesquisa
			Collection localidades = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());
			if (localidades == null || localidades.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.localidade_final_inexistente");
			}
		}

		filtroSetorComercial = new FiltroSetorComercial();
		if (codigoSetorComercial != null
				&& !codigoSetorComercial.toString().trim().equalsIgnoreCase("")) {
			if (idLocalidade != null
					&& !idLocalidade.toString().trim().equalsIgnoreCase("")) {
				filtroSetorComercial.limparListaParametros();
				// coloca parametro no filtro
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, new Integer(
								idLocalidadeFinal)));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						new Integer(codigoSetorComercialFinal)));
				// pesquisa
				Collection setorComerciais = fachada.pesquisar(
						filtroSetorComercial, SetorComercial.class.getName());
				if (setorComerciais == null || setorComerciais.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.setor_final_inexistente");
				}
			}
		}

		String rotaInicial = manterComandoAcaoCobrancaDetalhesActionForm
				.getRotaInicial();

		if (codigoSetorComercial != null
				&& !codigoSetorComercial.toString().trim().equalsIgnoreCase("")) {
			if (idLocalidade != null
					&& !idLocalidade.toString().trim().equalsIgnoreCase("")) {
				if (rotaInicial != null
						&& !rotaInicial.toString().trim().equalsIgnoreCase("")) {
					FiltroRota filtroRota = new FiltroRota();
					filtroRota.adicionarParametro(new ParametroSimples(
							FiltroRota.CODIGO_ROTA, rotaInicial));
					filtroRota.adicionarParametro(new ParametroSimples(
							FiltroRota.LOCALIDADE_ID, idLocalidade));
					filtroRota.adicionarParametro(new ParametroSimples(
							FiltroRota.SETOR_COMERCIAL_CODIGO,
							codigoSetorComercial));

					Collection rotas = fachada.pesquisar(filtroRota, Rota.class
							.getName());

					if (rotas != null && !rotas.isEmpty()) {
					} else {
						throw new ActionServletException(
								"atencao.pesquisa.rota_inicial_inexistente");
					}
				}
			}
		}

		String rotaFinal = manterComandoAcaoCobrancaDetalhesActionForm
				.getRotaFinal();

		if (codigoSetorComercial != null
				&& !codigoSetorComercial.toString().trim().equalsIgnoreCase("")) {
			if (idLocalidade != null
					&& !idLocalidade.toString().trim().equalsIgnoreCase("")) {
				if (rotaFinal != null
						&& !rotaFinal.toString().trim().equalsIgnoreCase("")) {

					FiltroRota filtroRota = new FiltroRota();
					filtroRota.limparListaParametros();
					filtroRota.adicionarParametro(new ParametroSimples(
							FiltroRota.LOCALIDADE_ID, idLocalidade));
					filtroRota.adicionarParametro(new ParametroSimples(
							FiltroRota.SETOR_COMERCIAL_CODIGO,
							codigoSetorComercial));
					filtroRota.adicionarParametro(new ParametroSimples(
							FiltroRota.CODIGO_ROTA, rotaFinal));
					Collection rotas = null;
					rotas = fachada.pesquisar(filtroRota, Rota.class.getName());

					if (rotas != null && !rotas.isEmpty()) {
					} else {
						throw new ActionServletException(
								"atencao.pesquisa.rota_final_inexistente");
					}
				}
			}
		}
		
		// Verifica se houve alteração nas Fiscalização Situação do comando
		FiltroCobrancaAcaoAtividadeComandoFiscalizacaoSituacao filtroCobrancaAcaoFisc
			= new FiltroCobrancaAcaoAtividadeComandoFiscalizacaoSituacao();
	
		filtroCobrancaAcaoFisc.adicionarParametro(
				new ParametroSimples(
					FiltroCobrancaAcaoAtividadeComandoFiscalizacaoSituacao.COBRANCA_ACAO_ATIVIDADE_COMANDO_ID,
					cobrancaAcaoAtividadeComando.getId()));
		
		Collection<CobrancaAcaoAtividadeComandoFiscalizacaoSituacao> colecaoCobrancaAcaoFisc =
			fachada.pesquisar(filtroCobrancaAcaoFisc, 
					CobrancaAcaoAtividadeComandoFiscalizacaoSituacao.class.getName());
		
		
		String[] fiscalizacaoSituacoes = null;
		if(!Util.isVazioOrNulo(colecaoCobrancaAcaoFisc)){
		
			fiscalizacaoSituacoes = manterComandoAcaoCobrancaDetalhesActionForm.getSituacaoFiscalizacao();

			boolean alterouFiscalizacaoSituacao = false;
			lacoAtividades : for (CobrancaAcaoAtividadeComandoFiscalizacaoSituacao helper : colecaoCobrancaAcaoFisc) {
				boolean encontrou = true;
				for (int i = 0;i < fiscalizacaoSituacoes.length;i++) {
					
					if(!fiscalizacaoSituacoes[i].equals(
							helper.getFiscalizacaoSituacao().getId().toString())){
						encontrou = false;
					}
					
					if(!encontrou){
						alterouFiscalizacaoSituacao = true;
						break lacoAtividades;
					}					
				}	
			}
			// Se houve alteração remove os registros em CobrancaAcao Atividade Comando Fiscalizacao Situacao,
			// e inseri novamente os novos registros.
			if(alterouFiscalizacaoSituacao){
				fachada.removerCobrancaAcaoAtividadeComandoFiscalizacaoSituacao(cobrancaAcaoAtividadeComando.getId());				
			}
			
		}

		fachada.concluirManterComandoAcaoCobranca(
				manterComandoAcaoCobrancaDetalhesActionForm.getPeriodoInicialConta(),
				manterComandoAcaoCobrancaDetalhesActionForm.getPeriodoFinalConta(),
				manterComandoAcaoCobrancaDetalhesActionForm.getPeriodoVencimentoContaInicial(),
				manterComandoAcaoCobrancaDetalhesActionForm.getPeriodoVencimentoContaFinal(),
				manterComandoAcaoCobrancaDetalhesActionForm.getCobrancaAcao(),
				manterComandoAcaoCobrancaDetalhesActionForm.getCobrancaAtividade(),
				manterComandoAcaoCobrancaDetalhesActionForm.getCobrancaGrupo(),
				manterComandoAcaoCobrancaDetalhesActionForm.getGerenciaRegional(),
				manterComandoAcaoCobrancaDetalhesActionForm.getLocalidadeOrigemID(),
				manterComandoAcaoCobrancaDetalhesActionForm.getLocalidadeDestinoID(),
				manterComandoAcaoCobrancaDetalhesActionForm.getSetorComercialOrigemCD(),
				manterComandoAcaoCobrancaDetalhesActionForm.getSetorComercialDestinoCD(),
				manterComandoAcaoCobrancaDetalhesActionForm.getIdCliente(),
				manterComandoAcaoCobrancaDetalhesActionForm.getClienteRelacaoTipo(),
				manterComandoAcaoCobrancaDetalhesActionForm.getIndicador(),
				rotaInicial, 
				rotaFinal,
				manterComandoAcaoCobrancaDetalhesActionForm.getSetorComercialOrigemID(),
				manterComandoAcaoCobrancaDetalhesActionForm.getSetorComercialDestinoID(),
				cobrancaAcaoAtividadeComando.getId().toString(),
				cobrancaAcaoAtividadeComando.getRealizacao(),
				cobrancaAcaoAtividadeComando.getComando(),
				cobrancaAcaoAtividadeComando.getUltimaAlteracao(),
				cobrancaAcaoAtividadeComando.getUsuario(),
				cobrancaAcaoAtividadeComando.getEmpresa(),
				cobrancaAcaoAtividadeComando.getQuantidadeDocumentos(),
				cobrancaAcaoAtividadeComando.getValorDocumentos(),
				cobrancaAcaoAtividadeComando.getQuantidadeItensCobrados(),
				idComando, 
				manterComandoAcaoCobrancaDetalhesActionForm.getUnidadeNegocio(),
				manterComandoAcaoCobrancaDetalhesActionForm.getTitulo(),
				manterComandoAcaoCobrancaDetalhesActionForm.getDescricaoSolicitacao(),
				manterComandoAcaoCobrancaDetalhesActionForm.getPrazoExecucao(),
				manterComandoAcaoCobrancaDetalhesActionForm.getQuantidadeMaximaDocumentos(),
				manterComandoAcaoCobrancaDetalhesActionForm.getValorLimiteObrigatoria(),
				manterComandoAcaoCobrancaDetalhesActionForm.getIndicadorImoveisDebito(),
				manterComandoAcaoCobrancaDetalhesActionForm.getIndicadorGerarBoletimCadastro(),
				manterComandoAcaoCobrancaDetalhesActionForm.getCodigoClienteSuperior(),
				manterComandoAcaoCobrancaDetalhesActionForm.getRotaInicial(),
				manterComandoAcaoCobrancaDetalhesActionForm.getRotaFinal(),
				manterComandoAcaoCobrancaDetalhesActionForm.getConsumoMedioInicial(),
				manterComandoAcaoCobrancaDetalhesActionForm.getConsumoMedioFinal(),
				manterComandoAcaoCobrancaDetalhesActionForm.getTipoConsumo(),
				manterComandoAcaoCobrancaDetalhesActionForm.getPeriodoInicialFiscalizacao(),
				manterComandoAcaoCobrancaDetalhesActionForm.getPeriodoFinalFiscalizacao(),
				manterComandoAcaoCobrancaDetalhesActionForm.getSituacaoFiscalizacao(),
				manterComandoAcaoCobrancaDetalhesActionForm.getNumeroQuadraInicial(),
				manterComandoAcaoCobrancaDetalhesActionForm.getNumeroQuadraFinal());

		// pesquisar cobranca acao
		CobrancaAcao cobrancaAcao = fachada
				.consultarCobrancaAcao(manterComandoAcaoCobrancaDetalhesActionForm
						.getCobrancaAcao());

		// pesquisar cobranca atividade
		CobrancaAtividade cobrancaAtividade = fachada
				.consultarCobrancaAtividade(manterComandoAcaoCobrancaDetalhesActionForm
						.getCobrancaAtividade());

		montarPaginaSucesso(httpServletRequest, "A Ação "
				+ cobrancaAcao.getDescricaoCobrancaAcao()
				+ " para a atividade "
				+ cobrancaAtividade.getDescricaoCobrancaAtividade()
				+ " comandada com sucesso",
				"Manter outro Comando de Ação de Cobrança",
				"exibirManterComandoAcaoCobrancaAction.do?menu=sim");

		return retorno;
	}

}
