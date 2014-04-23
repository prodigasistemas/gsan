package gcom.gui.micromedicao;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.micromedicao.consumo.FiltroConsumoHistorico;
import gcom.micromedicao.medicao.FiltroMedicaoHistoricoSql;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.relatorio.micromedicao.FiltrarAnaliseExcecoesLeiturasHelper;
import gcom.seguranca.acesso.FiltroGrupo;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltrarExcecoesLeiturasConsumosAction extends GcomAction {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
        // Limpa os dados da sessao
		sessao.removeAttribute("totalRegistros");

//		String nomeCaminhoMapping = (String) sessao
//				.getAttribute("nomeCaminhoMapping");

//		ActionForward retorno = actionMapping.findForward(nomeCaminhoMapping);
		ActionForward retorno = null;

		LeituraConsumoActionForm leituraConsumoActionForm = (LeituraConsumoActionForm) actionForm;

		// obtem instancia da fachada
		Fachada fachada = Fachada.getInstancia();

//		sessao.removeAttribute("leituraConsumoActionForm");

		// ----------- Parte 1 - Pesquisar por Imovel subCategoria - 1.1 > 1.10
		String localidade =  leituraConsumoActionForm
				.getLocalidadeFiltro();
		String setorComercial =  leituraConsumoActionForm
				.getSetorComercialFiltro();
		//String idSetorComercial = leituraConsumoActionForm.getSetorComercialID();
		String nmQuadraInicial =  leituraConsumoActionForm
				.getQuadraInicialFiltro();
		String nmQuadraFinal =  leituraConsumoActionForm
				.getQuadraFinalFiltro();
		// Rota
		String nmRota =  leituraConsumoActionForm.getRotaFiltro();
		
		String matricula =  leituraConsumoActionForm.getImovelFiltro();
		String matriculaCondominio =  leituraConsumoActionForm
				.getImovelCondominioFiltro();
		String grupoFaturamento =  leituraConsumoActionForm
				.getIdGrupoFaturamentoFiltro();
		String idEmpresa =  leituraConsumoActionForm
				.getIdEmpresaFiltro();
		String indicadorImovelCondominio = null;
		if (leituraConsumoActionForm.getIndicadorImovelCondominioFiltro() != null
				&& leituraConsumoActionForm
						.getIndicadorImovelCondominioFiltro().equalsIgnoreCase(
								"S")) {
			indicadorImovelCondominio = "1";
		} else if (leituraConsumoActionForm
				.getIndicadorImovelCondominioFiltro() != null
				&& leituraConsumoActionForm
						.getIndicadorImovelCondominioFiltro().equalsIgnoreCase(
								"N")) {
			indicadorImovelCondominio = "2";
		} else {
			// if(leituraConsumoActionForm.getIndicadorImovelCondominio() !=
			// null && leituraConsumoActionForm
			// .getIndicadorImovelCondominio().equalsIgnoreCase("T")){
			indicadorImovelCondominio = "3";
		}
		sessao.setAttribute("indicadorImovelCondominio",indicadorImovelCondominio);
		String[] perfilImovel =  leituraConsumoActionForm
				.getPerfilImovelFiltro();
		String categoria =  leituraConsumoActionForm
				.getCategoriaImovelFiltro();
		String qtdEconomias =  leituraConsumoActionForm
				.getQuantidadeEconomiaFiltro();
		// ----------- Parte 2 - Pesquisar Por Consumo Historico e Medicao
		// Historico
		String tipoMedicao =  leituraConsumoActionForm
				.getTipoMedicaoFiltro();
		String tipoLigacao =  leituraConsumoActionForm
				.getTipoLigacaoFiltro();
		String tipoAnormalidade =  leituraConsumoActionForm
				.getTipoAnormalidadeFiltro();
		String[] anormalidadeLeituraInformada =  leituraConsumoActionForm
				.getAnormalidadeLeituraInformadaFiltro();
		String[] anormalidadeLeituraFaturada =  leituraConsumoActionForm
				.getAnormalidadeLeituraFaturadaFiltro();
		String[] anormalidadeConsumo =  leituraConsumoActionForm
				.getAnormalidadeConsumoFiltro();
		String consumoFaturadoInicial =  leituraConsumoActionForm
				.getConsumoFaturadoInicialFiltro();
		String consumoFaturadoFinal =  leituraConsumoActionForm
				.getConsumoFaturadoFinalFiltro();
		String consumoMedidoInicial =  leituraConsumoActionForm
				.getConsumoMedidoInicialFiltro();
		String consumoMedidoFinal =  leituraConsumoActionForm
				.getConsumoMedidoFinalFiltro();
		String consumoMedioInicial =  leituraConsumoActionForm
				.getConsumoMedioInicialFiltro();
		String consumoMedioFinal =  leituraConsumoActionForm
				.getConsumoMedioFinalFiltro();
		String tipoApresentacao =  leituraConsumoActionForm.getTipoApresentacao();
		
		String indicadorDebitoAutomatico =  leituraConsumoActionForm.getIndicadorDebitoAutomatico();
		
		String indicadorAnalisado =  leituraConsumoActionForm.getIndicadorAnalisado();
		
		String loginUsuarioAlteracao =  leituraConsumoActionForm.getLoginUsuarioAlteracao();
		
		String leituraSituacaoAtual = leituraConsumoActionForm.getLeituraSituacaoAtualFiltro();

		String valorAguaEsgotoInicialFiltro = leituraConsumoActionForm.getValorAguaEsgotoInicialFiltro();
		
		String valorAguaEsgotoFinalFiltro = leituraConsumoActionForm.getValorAguaEsgotoFinalFiltro();
		
		String mesAno = leituraConsumoActionForm.getMesAno();
		sessao.setAttribute("mesAnoPesquisa", mesAno);
		
		sessao.setAttribute("tipoApresentacao", tipoApresentacao);
		
		sessao.setAttribute("valorAguaEsgotoInicial", valorAguaEsgotoInicialFiltro);
		
		sessao.setAttribute("valorAguaEsgotoFinal", valorAguaEsgotoFinalFiltro);
		
		if (tipoApresentacao.trim().equals("1")) {
			retorno = actionMapping.findForward("efetuarAnaliseExcecoesLeiturasConsumosResumido");
		} else if (tipoApresentacao.trim().equals("2")) {
			retorno = actionMapping.findForward("gerarRelatorioAnaliseConsumo");
		} else {
			retorno = actionMapping.findForward("gerarRelatorioAvisoAnormalidade");
		}

		FiltroConsumoHistorico filtroConsumoHistorico = new FiltroConsumoHistorico();
		FiltroMedicaoHistoricoSql filtroMedicaoHistoricoSql = new FiltroMedicaoHistoricoSql();
		FiltrarAnaliseExcecoesLeiturasHelper filtrarAnaliseExcecoesLeiturasHelper = new FiltrarAnaliseExcecoesLeiturasHelper();

		// --------------Verifica existencia
		// ---Imovel e Imovel Condominio
		// =====================================================================

		if (matricula != null && !matricula.trim().equalsIgnoreCase("")) {

			String inscricao = fachada.pesquisarInscricaoImovel(new Integer(
					matricula));
			if (inscricao == null) {
				boolean testeExclusao = fachada
						.confirmarImovelExcluido(new Integer(matricula));
				if (testeExclusao) {
					throw new ActionServletException("atencao.imovel.excluido");
				}
				throw new ActionServletException("atencao.naocadastrado","imóvel");
			}
		}
		// ======================================================================
		if (matriculaCondominio != null
				&& !matriculaCondominio.trim().equalsIgnoreCase("")) {
			String condominio = fachada.pesquisarInscricaoImovel(new Integer(
					matriculaCondominio));

			if (condominio == null || condominio.trim().equals("")) {
				throw new ActionServletException("atencao.naocadastrado","imóvel condominio");
			}
		}

		// ---- Localidade
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		if (localidade != null && !localidade.trim().equalsIgnoreCase("")) {
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidade));
		}
		Collection<Localidade> localidades = fachada.pesquisar(
				filtroLocalidade, Localidade.class.getName());
		if (localidades.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado","localidade");
		}
		// ---- Setor Comercial
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		if (setorComercial != null
				&& !setorComercial.trim().equalsIgnoreCase("")) {
			
			// Verificamos se a localidade foi informada
			if ( localidade == null || localidade.equals("") ){
				throw new ActionServletException("atencao.localidade_nao_informada", "Localidade");
			}			
			
			filtroSetorComercial
			.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.LOCALIDADE,
					localidade));
			
			filtroSetorComercial
					.adicionarParametro(new ParametroSimples(
							FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
							setorComercial));
		}
		Collection<SetorComercial> setorComerciais = fachada.pesquisar(
				filtroSetorComercial, SetorComercial.class.getName());		
		if (setorComerciais.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado","setor comercial");			
		}
		Integer idSetorComercial = setorComerciais.iterator().next().getId();
		// ---- Quadra Inicial
		FiltroQuadra filtroQuadraInicial = new FiltroQuadra();
		if (nmQuadraInicial != null
				&& !nmQuadraInicial.trim().equalsIgnoreCase("")) {
			Integer quadraIni = new Integer(nmQuadraInicial);
			filtroQuadraInicial.adicionarParametro(new ParametroSimples(
					FiltroQuadra.NUMERO_QUADRA, quadraIni));
			if (setorComercial != null
					&& !setorComercial.trim().equalsIgnoreCase("")) {
				// Adiciona o id do setor comercial que está no formulário
				// para
				// compor a pesquisa.
				filtroQuadraInicial.adicionarParametro(new ParametroSimples(
						FiltroQuadra.CODIGO_SETORCOMERCIAL, setorComercial));
			}
			
			filtroQuadraInicial.adicionarParametro(new ParametroSimples(
					FiltroQuadra.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			Collection<Quadra> quadraIniciais = fachada.pesquisar(
					filtroQuadraInicial, Quadra.class.getName());
			if (quadraIniciais.isEmpty()) {
				throw new ActionServletException("atencao.naocadastrado","Quadra Inicial");
			}
		}
		
		// ---- Quadra Inicial
		FiltroQuadra filtroQuadraFinal = new FiltroQuadra();
		if (nmQuadraFinal != null && !nmQuadraFinal.trim().equalsIgnoreCase("")) {
			Integer quadraFin = new Integer(nmQuadraFinal);
			
			filtroQuadraFinal.adicionarParametro(new ParametroSimples(
					FiltroQuadra.NUMERO_QUADRA, quadraFin));
			
			if (setorComercial != null
					&& !setorComercial.trim().equalsIgnoreCase("")) {
				// Adiciona o id do setor comercial que está no formulário
				// para
				// compor a pesquisa.
				filtroQuadraFinal.adicionarParametro(new ParametroSimples(
						FiltroQuadra.CODIGO_SETORCOMERCIAL, setorComercial));
			}

			filtroQuadraFinal.adicionarParametro(new ParametroSimples(
					FiltroQuadra.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			Collection<Quadra> quadraFinais = fachada.pesquisar(
					filtroQuadraFinal, Quadra.class.getName());
			if (quadraFinais.isEmpty()) {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Quadra Final");
			}
		}
		
		// ---- Rota
		FiltroRota filtroRota = new FiltroRota();
		if (nmRota != null
				&& !nmRota.trim().equalsIgnoreCase("")) {
			
			// Verificamos se a localidade foi informada
			if ( localidade == null || localidade.equals("") ){
				throw new ActionServletException("atencao.localidade_nao_informada", null, "Localidade");
			}
			
			// Verificamos se o setor foi informada
			if ( setorComercial == null || setorComercial.equals("") ){
				throw new ActionServletException("atencao.codigo_setor_comercial_nao_informado", null, "Localidade");
			}

			// Validamos se a rota pertence aquela localidade/setorcomercial
			Integer rota = new Integer(nmRota);
			filtroRota.adicionarParametro(new ParametroSimples(
					FiltroRota.CODIGO_ROTA, rota) );
			filtroRota.adicionarParametro(new ParametroSimples(
					FiltroRota.LOCALIDADE, localidade));
			filtroRota.adicionarParametro(new ParametroSimples(
					FiltroRota.SETOR_COMERCIAL, idSetorComercial ));			
			
			filtroRota.adicionarParametro(new ParametroSimples(
					FiltroRota.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			Collection<Rota> rotas = fachada.pesquisar(
					filtroRota, Rota.class.getName());
			if (rotas.isEmpty()) {
				throw new ActionServletException("atencao.naocadastrado", null, "Rota");
			}
		}		
		
		// ----- Fim verifica Existencia

		boolean peloMenosUmParametro = false;
		// -------- Parte 1

		if (matricula != null && !matricula.trim().equalsIgnoreCase("")) {
			peloMenosUmParametro = true;

			filtroMedicaoHistoricoSql
					.adicionarParametro(new ParametroSimples(
							FiltroMedicaoHistoricoSql.IMOVEL_ID, new Integer(
									matricula)));
			filtrarAnaliseExcecoesLeiturasHelper.setIdImovel(matricula);
		}

		if (matriculaCondominio != null
				&& !matriculaCondominio.trim().equalsIgnoreCase("")) {
			peloMenosUmParametro = true;

			filtroMedicaoHistoricoSql.adicionarParametro(new ParametroSimples(
					FiltroMedicaoHistoricoSql.IMOVEL_CONDOMINIO_ID,
					new Integer(matriculaCondominio)));
			filtrarAnaliseExcecoesLeiturasHelper.setIdImovelCondominio(matriculaCondominio);
		}

		String anoMesFaturamento = null;
		int anoMesFaturamentoGrupo = 0;
		if (grupoFaturamento != null
				&& !grupoFaturamento.trim().equalsIgnoreCase(
						"" + +ConstantesSistema.NUMERO_NAO_INFORMADO) 
				&& !grupoFaturamento.trim().equalsIgnoreCase("")) {
			peloMenosUmParametro = true;

			filtroMedicaoHistoricoSql.adicionarParametro(new ParametroSimples(
					FiltroMedicaoHistoricoSql.GRUPO_FATURAMENTO, new Integer(
							grupoFaturamento)));
			filtrarAnaliseExcecoesLeiturasHelper.setIdFaturamentoGrupo(grupoFaturamento);
			
			FiltroGrupo filtroGrupo = new FiltroGrupo();
			filtroGrupo.adicionarParametro(new ParametroSimples(FiltroGrupo.ID, grupoFaturamento));
			Collection colecaoGrupo = fachada.pesquisar(filtroGrupo, FaturamentoGrupo.class.getName());
			FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo)colecaoGrupo.iterator().next();
			sessao.setAttribute("faturamentoGrupo", faturamentoGrupo);
			anoMesFaturamento = Util.formatarAnoMesParaMesAno(faturamentoGrupo.getAnoMesReferencia());
			
			anoMesFaturamentoGrupo = faturamentoGrupo.getAnoMesReferencia().intValue();
		}
		
		//se anomes diferente de ano mes do faturamento - bloqueia dados na tela(tela só para consulta)
		if(anoMesFaturamento != null &&  mesAno.equalsIgnoreCase(anoMesFaturamento)){
			sessao.removeAttribute("habilitaCampos");
		}else{
			sessao.setAttribute("habilitaCampos", true);
		}
		
		// caso o ano/mês de referência informado seja igual a FTGR_AMREFERENCIA ? 1, 
		// habilitar Botão de alterar Dados para Faturamento 
		if(anoMesFaturamento != null && anoMesFaturamentoGrupo > 0){
			int anoMesFaturamentoGrupoAnterior =  Util.subtrairMesDoAnoMes(anoMesFaturamentoGrupo, 1);
			
			if(Util.formatarMesAnoParaAnoMesSemBarra(mesAno).equalsIgnoreCase(""+anoMesFaturamentoGrupoAnterior)){
				sessao.setAttribute("habilitaBotaoAlterarDadosFaturamento", true);
			}else{
				sessao.removeAttribute("habilitaBotaoAlterarDadosFaturamento");
			}
		}
		
		
		if (idEmpresa != null
				&& !idEmpresa.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametro = true;

			filtroMedicaoHistoricoSql.adicionarParametro(new ParametroSimples(
					FiltroMedicaoHistoricoSql.IMOVEL_EMPRESA, idEmpresa));
			filtrarAnaliseExcecoesLeiturasHelper.setIdEmpresa(idEmpresa);
		}

		if (localidade != null && !localidade.trim().equalsIgnoreCase("")) {
			peloMenosUmParametro = true;

			filtroMedicaoHistoricoSql.adicionarParametro(new ParametroSimples(
					FiltroMedicaoHistoricoSql.LOCALIDADE_IMOVEL, new Integer(
							localidade)));
			filtrarAnaliseExcecoesLeiturasHelper.setIdLocalidade(localidade);
		}

		if (setorComercial != null
				&& !setorComercial.trim().equalsIgnoreCase("")) {
			peloMenosUmParametro = true;

			filtroMedicaoHistoricoSql.adicionarParametro(new ParametroSimples(
					FiltroMedicaoHistoricoSql.SETOR_COMERCIAL_IMOVEL_CODIGO,
					new Integer(setorComercial)));
			filtrarAnaliseExcecoesLeiturasHelper.setCodigoSetorComercial(setorComercial);
		}

		if (nmQuadraInicial != null
				&& !nmQuadraInicial.trim().equalsIgnoreCase("")) {
			peloMenosUmParametro = true;

			if (nmQuadraFinal == null
					|| nmQuadraFinal.trim().equalsIgnoreCase("")) {
				nmQuadraFinal = nmQuadraInicial;
			}
			filtroMedicaoHistoricoSql.adicionarParametro(new Intervalo(
					FiltroMedicaoHistoricoSql.QUADRA_IMOVEL_NUMERO,
					new Integer(nmQuadraInicial), new Integer(nmQuadraFinal)));
			filtrarAnaliseExcecoesLeiturasHelper.setNumeroQuadraInicial(nmQuadraInicial);
			filtrarAnaliseExcecoesLeiturasHelper.setNumeroQuadraFinal(nmQuadraFinal);
		}
		
		if (nmRota != null
				&& !nmRota.trim().equalsIgnoreCase("")) {
			peloMenosUmParametro = true;

			filtroMedicaoHistoricoSql.adicionarParametro(new ParametroSimples(
					FiltroMedicaoHistoricoSql.ROTA_QUADRA, 
					new Integer(nmRota) ) );
			filtrarAnaliseExcecoesLeiturasHelper.setCodigoRota(nmRota);
		}		

		if (indicadorImovelCondominio != null
				&& !indicadorImovelCondominio.trim().equalsIgnoreCase("")) {
			if (indicadorImovelCondominio.trim().equalsIgnoreCase("1")) {
				peloMenosUmParametro = true;

				filtroMedicaoHistoricoSql
						.adicionarParametro(new ParametroSimples(
								FiltroMedicaoHistoricoSql.INDICADOR_IMOVEL_CONDOMINIO,
								new Short(indicadorImovelCondominio)));
				filtrarAnaliseExcecoesLeiturasHelper.setIndicadorImovelCondominio(indicadorImovelCondominio);
			} else if (indicadorImovelCondominio.trim().equalsIgnoreCase("2")) {
				peloMenosUmParametro = true;

				filtroMedicaoHistoricoSql
						.adicionarParametro(new ParametroSimples(
								FiltroMedicaoHistoricoSql.INDICADOR_IMOVEL_CONDOMINIO,
								new Short(indicadorImovelCondominio)));
				filtrarAnaliseExcecoesLeiturasHelper.setIndicadorImovelCondominio(indicadorImovelCondominio);
			}
		}
		
		if(indicadorDebitoAutomatico != null && !indicadorDebitoAutomatico.trim().equals("")){
			if(indicadorDebitoAutomatico.trim().equals("S")){
				filtroMedicaoHistoricoSql.adicionarParametro(new ParametroSimples(
						FiltroMedicaoHistoricoSql.INDICADOR_DEBITO_AUTOMATICO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				filtrarAnaliseExcecoesLeiturasHelper.setIndicadorDebitoAutomatico("" + ConstantesSistema.INDICADOR_USO_ATIVO);
			}else if(indicadorDebitoAutomatico.trim().equals("N")){
				filtroMedicaoHistoricoSql.adicionarParametro(new ParametroSimples(
						FiltroMedicaoHistoricoSql.INDICADOR_DEBITO_AUTOMATICO,
						ConstantesSistema.INDICADOR_USO_DESATIVO));
				filtrarAnaliseExcecoesLeiturasHelper.setIndicadorDebitoAutomatico("" + ConstantesSistema.INDICADOR_USO_DESATIVO);
			}
		}
		
		if(indicadorAnalisado != null && !indicadorAnalisado.trim().equals("")){
			peloMenosUmParametro = true;
			
			filtroMedicaoHistoricoSql.adicionarParametro(new ParametroSimples(
						FiltroMedicaoHistoricoSql.MH_INDICADOR_ANALISADO, indicadorAnalisado));

			String[] icAnalisado = new String[2];
			if (indicadorAnalisado.equals("" + ConstantesSistema.INDICADOR_USO_ATIVO)) {
				icAnalisado[0] = "" + MedicaoHistorico.INDICADOR_ANALISADO_SIM;
				icAnalisado[1] = "" + MedicaoHistorico.INDICADOR_ANALISADO_ATUALIZADO;
			} else {
				icAnalisado[0] = "" + MedicaoHistorico.INDICADOR_ANALISADO_NAO;
			}
			
			filtrarAnaliseExcecoesLeiturasHelper.setIndicadorAnalisado(icAnalisado);
			
		}
		
		if (loginUsuarioAlteracao != null
				&& !loginUsuarioAlteracao.trim().equals("")) {
			peloMenosUmParametro = true;
			
			Integer idUsuario = null;
			
			String idUsuarioAlteracao = leituraConsumoActionForm.getIdUsuarioAlteracao();
			
			if (idUsuarioAlteracao != null && !idUsuarioAlteracao.trim().equals("")) {
				idUsuario = new Integer(idUsuarioAlteracao);
			} else {
				FiltroUsuario filtroUsuario = new FiltroUsuario();
				filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, loginUsuarioAlteracao));
			
				Collection colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
				Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
			
				idUsuario = usuario.getId();
			}

			filtroMedicaoHistoricoSql.adicionarParametro(new ParametroSimples(
					FiltroMedicaoHistoricoSql.MH_USUARIO_ALTERACAO, idUsuario));
			filtrarAnaliseExcecoesLeiturasHelper.setIdUsuarioAlteracao(idUsuario.toString());
			
		}

		if (perfilImovel != null) {
			
			String valorPerfil = "";
			
			for (int i = 0; i < perfilImovel.length; i++) {
				if (perfilImovel[i] != null && !perfilImovel[i].trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					valorPerfil = valorPerfil + perfilImovel[i] + ", "; 
				}
			}
			
			if (valorPerfil != null && !valorPerfil.trim().equals("")) {
				peloMenosUmParametro = true;
				
				valorPerfil = Util.removerUltimosCaracteres(valorPerfil, 2);

				filtroMedicaoHistoricoSql.adicionarParametro(new ParametroSimples(
						FiltroMedicaoHistoricoSql.PERFIL_IMOVEL, valorPerfil));
				filtrarAnaliseExcecoesLeiturasHelper.setIdsImovelPerfil(perfilImovel);
			}
			
		}

		if (categoria != null
				&& !categoria.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametro = true;

			filtroMedicaoHistoricoSql.adicionarParametro(new ParametroSimples(
					FiltroMedicaoHistoricoSql.IMOVEL_CATEGORIA, new Integer(
							categoria)));
			filtrarAnaliseExcecoesLeiturasHelper.setIdCategoria(categoria);
		}

		// ---- fim parte 1

		// ---- parte 2

		// ----Por Medicao Historico

		if (tipoMedicao != null
				&& !tipoMedicao.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametro = true;

			filtroMedicaoHistoricoSql.adicionarParametro(new ParametroSimples(
					FiltroMedicaoHistoricoSql.MH_MEDICAO_TIPO_ID, tipoMedicao));
			filtrarAnaliseExcecoesLeiturasHelper.setIdMedicaoTipo(tipoMedicao);
		}

		if (tipoAnormalidade != null
				&& !tipoAnormalidade.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			if (tipoAnormalidade.trim().equalsIgnoreCase(
					ConstantesSistema.ANORMALIDADE_LEITURA_INFORMADA)) {
				peloMenosUmParametro = true;
				filtroMedicaoHistoricoSql
						.adicionarParametro(new ParametroNaoNulo(
								FiltroMedicaoHistoricoSql.MH_ANORMALIDADE_INFORMADA));
				filtrarAnaliseExcecoesLeiturasHelper.setIdTipoAnormalidade(FiltrarAnaliseExcecoesLeiturasHelper.ANORMALIDADE_LEITURA_INFORMADA);
			} else if (tipoAnormalidade.trim().equalsIgnoreCase(
					ConstantesSistema.ANORMALIDADE_LEITURA_FATURADA)) {
				peloMenosUmParametro = true;
				filtroMedicaoHistoricoSql
						.adicionarParametro(new ParametroNaoNulo(
								FiltroMedicaoHistoricoSql.MH_ANORMALIDADE_FATURADA));
				filtrarAnaliseExcecoesLeiturasHelper.setIdTipoAnormalidade(FiltrarAnaliseExcecoesLeiturasHelper.ANORMALIDADE_LEITURA_FATURADA);
			} else if (tipoAnormalidade.trim().equalsIgnoreCase(
					ConstantesSistema.ANORMALIDADE_CONSUMO)) {
				peloMenosUmParametro = true;
				filtroConsumoHistorico.adicionarParametro(new ParametroNaoNulo(
						FiltroConsumoHistorico.ANORMALIDADE_CONSUMO));
				filtrarAnaliseExcecoesLeiturasHelper.setIdTipoAnormalidade(FiltrarAnaliseExcecoesLeiturasHelper.ANORMALIDADE_CONSUMO);
			}
		}

		if (anormalidadeLeituraInformada != null) {
			
			String valor = "";
			
			for (int i = 0; i < anormalidadeLeituraInformada.length; i++) {
				if (anormalidadeLeituraInformada[i] != null && !anormalidadeLeituraInformada[i].trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					valor = valor + anormalidadeLeituraInformada[i] + ", "; 
				}
			}
			
			if (valor != null && !valor.trim().equals("")) {
				peloMenosUmParametro = true;
				
				valor = Util.removerUltimosCaracteres(valor, 2);

				filtroMedicaoHistoricoSql.adicionarParametro(new ParametroSimples(
						FiltroMedicaoHistoricoSql.MH_ANORMALIDADE_INFORMADA, valor));
				filtrarAnaliseExcecoesLeiturasHelper.setIdsAnormalidadeLeituraInformada(anormalidadeLeituraInformada);
			}
			
		}
		
		if (anormalidadeLeituraFaturada != null) {
			
			String valor = "";
			
			for (int i = 0; i < anormalidadeLeituraFaturada.length; i++) {
				if (anormalidadeLeituraFaturada[i] != null && !anormalidadeLeituraFaturada[i].trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					valor = valor + anormalidadeLeituraFaturada[i] + ", "; 
				}
			}
			
			if (valor != null && !valor.trim().equals("")) {
				peloMenosUmParametro = true;
				
				valor = Util.removerUltimosCaracteres(valor, 2);

				filtroMedicaoHistoricoSql.adicionarParametro(new ParametroSimples(
						FiltroMedicaoHistoricoSql.MH_ANORMALIDADE_FATURADA, valor));
				filtrarAnaliseExcecoesLeiturasHelper.setIdsAnormalidadeLeituraFaturada(anormalidadeLeituraFaturada);
			}
			
		}

		// ----- Por Consumo Historico
		if (tipoLigacao != null
				&& !tipoLigacao.trim().equalsIgnoreCase(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametro = true;

			filtroMedicaoHistoricoSql.adicionarParametro(new ParametroSimples(
					FiltroMedicaoHistoricoSql.CONSUMO_HISTORICO_LIGACAO_TIPO,
					new Integer(tipoLigacao)));
			filtrarAnaliseExcecoesLeiturasHelper.setIdLigacaoTipo(tipoLigacao);
		}
		
		if (anormalidadeConsumo != null) {
			
			String valor = "";
			
			for (int i = 0; i < anormalidadeConsumo.length; i++) {
				if (anormalidadeConsumo[i] != null && !anormalidadeConsumo[i].trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					valor = valor + anormalidadeConsumo[i] + ", "; 
				}
			}
			
			if (valor != null && !valor.trim().equals("")) {
				peloMenosUmParametro = true;
				
				valor = Util.removerUltimosCaracteres(valor, 2);

				filtroMedicaoHistoricoSql.adicionarParametro(new ParametroSimples(
						FiltroMedicaoHistoricoSql.CONSUMO_HISTORICO_ANORMALIDADE_CONSUMO, valor));
				filtrarAnaliseExcecoesLeiturasHelper.setIdsAnormalidadeConsumo(anormalidadeConsumo);
			}
			
		}

		if ( ( consumoFaturadoInicial != null
				&& !consumoFaturadoInicial.trim().equalsIgnoreCase("") ) ||
			( consumoFaturadoFinal != null
				&& !consumoFaturadoFinal.trim().equalsIgnoreCase("") ) ) {
			peloMenosUmParametro = true;

			if (consumoFaturadoInicial == null || consumoFaturadoInicial.trim().equalsIgnoreCase("")) {
				consumoFaturadoFinal = "0";
			} else if (consumoFaturadoFinal == null || consumoFaturadoFinal.trim().equalsIgnoreCase("")) {
				consumoFaturadoFinal = "999999";
			}
			
			filtroMedicaoHistoricoSql.adicionarParametro(new MaiorQue(
					FiltroMedicaoHistoricoSql.CONSUMO_HISTORICO_FATURADO_MES,
					consumoFaturadoInicial));
			filtroMedicaoHistoricoSql.adicionarParametro(new MenorQue(
					FiltroMedicaoHistoricoSql.CONSUMO_HISTORICO_FATURADO_MES,
					consumoFaturadoFinal));
			
			filtrarAnaliseExcecoesLeiturasHelper.setConsumoFaturadoInicial(consumoFaturadoInicial);
			filtrarAnaliseExcecoesLeiturasHelper.setConsumoFaturadoFinal(consumoFaturadoFinal);
		} 

		if ( ( consumoMedidoInicial != null
				&& !consumoMedidoInicial.trim().equalsIgnoreCase("") ) ||
			( consumoMedidoFinal != null
						&& !consumoMedidoFinal.trim().equalsIgnoreCase("") )) {
			peloMenosUmParametro = true;
			
			if (consumoMedidoInicial == null || consumoMedidoInicial.trim().equalsIgnoreCase("")) {
				consumoMedidoInicial = "0";
			} else if (consumoMedidoFinal == null || consumoMedidoFinal.trim().equalsIgnoreCase("")) {
				consumoMedidoFinal = "999999";
			}

			filtroMedicaoHistoricoSql.adicionarParametro(new MaiorQue(
					FiltroMedicaoHistoricoSql.MH_CONSUMO_MEDIDO_MES,
					consumoMedidoInicial));
			filtroMedicaoHistoricoSql.adicionarParametro(new MenorQue(
					FiltroMedicaoHistoricoSql.MH_CONSUMO_MEDIDO_MES,
					consumoMedidoFinal));
			filtrarAnaliseExcecoesLeiturasHelper.setConsumoMedidoInicial(consumoMedidoInicial);
			filtrarAnaliseExcecoesLeiturasHelper.setConsumoMedidoFinal(consumoMedidoFinal);
		}

		if (qtdEconomias != null && !qtdEconomias.trim().equalsIgnoreCase("")) {
			peloMenosUmParametro = true;

			filtroMedicaoHistoricoSql.adicionarParametro(new ParametroSimples(
					FiltroMedicaoHistoricoSql.MH_IMOVEL_QTD_ECONOMIAS,
					qtdEconomias));
			filtrarAnaliseExcecoesLeiturasHelper.setQuantidadeEconomias(qtdEconomias);
		}

		if ( ( consumoMedioInicial != null
				&& !consumoMedioInicial.trim().equalsIgnoreCase("") ) ||
			( consumoMedioFinal != null
					&& !consumoMedioFinal.trim().equalsIgnoreCase("") ) ) {
			peloMenosUmParametro = true;
			
			if (consumoMedioInicial == null || consumoMedioInicial.trim().equalsIgnoreCase("")) {
				consumoMedioInicial = "0";
			} else if (consumoMedioFinal == null || consumoMedioFinal.trim().equalsIgnoreCase("")) {
				consumoMedioFinal = "999999";
			}

			filtroMedicaoHistoricoSql.adicionarParametro(new MaiorQue(
					FiltroMedicaoHistoricoSql.CONSUMO_HISTORICO_CONSUMO_MEDIO,
					consumoMedioInicial));
			filtroMedicaoHistoricoSql.adicionarParametro(new MenorQue(
					FiltroMedicaoHistoricoSql.CONSUMO_HISTORICO_CONSUMO_MEDIO,
					consumoMedioFinal));
			
			filtrarAnaliseExcecoesLeiturasHelper.setConsumoMedioInicial(consumoMedioInicial);
			filtrarAnaliseExcecoesLeiturasHelper.setConsumoMedioFinal(consumoMedioFinal);
		}
		
		if (leituraSituacaoAtual != null && 
			!leituraSituacaoAtual.trim().equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametro = true;

			filtroMedicaoHistoricoSql.adicionarParametro(
				new ParametroSimples(FiltroMedicaoHistoricoSql.MH_LEITURA_SITUACAO_ATUAL,
					new Integer(leituraSituacaoAtual)));
			filtrarAnaliseExcecoesLeiturasHelper.setIdLeituraSituacaoAtual(leituraSituacaoAtual);
		}
		
		

		filtroMedicaoHistoricoSql.adicionarParametro(new ParametroSimples(
				FiltroMedicaoHistoricoSql.IMOVEL_INDICADOR_EXCLUSAO,
				ConstantesSistema.INDICADOR_IMOVEL_ATIVO));

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametro) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		sessao.setAttribute("leituraConsumoActionForm",
				leituraConsumoActionForm);
		sessao.setAttribute("filtroMedicaoHistoricoSql",
				filtroMedicaoHistoricoSql);
		sessao.setAttribute("filtrarAnaliseExcecoesLeiturasHelper",
				filtrarAnaliseExcecoesLeiturasHelper);


		return retorno;
	}	
}
