package gcom.gui.relatorio.faturamento;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.faturamento.GerarRelatorioAnormalidadePorAmostragemActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.RelatorioAnormalidadePorAmostragem;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Este caso de uso gera relatorio de anormalidade Informadas - Amostragem
 * 
 * [UC1051] ? Gerar Relatório de Amostragem das Anormalidades Informadas
 * 
 * @author Hugo Leonardo
 * @date 06/08/2010
 * 
 */
public class GerarRelatorioAnormalidadePorAmostragemAction extends
		ExibidorProcessamentoTarefaRelatorio {

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
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno
		ActionForward retorno = null;

		GerarRelatorioAnormalidadePorAmostragemActionForm form = (GerarRelatorioAnormalidadePorAmostragemActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Valida os parâmetro passados como consulta
		boolean peloMenosUmParametroInformado = false;

		// Grupo
		Integer idGrupo = null;
		if (form.getGrupo() != null
				&& !form.getGrupo().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;
			idGrupo = new Integer(form.getGrupo());
		}
		
		// Rota
		Short cdRota = null;
		if (form.getRota() != null && !form.getRota().trim().equals("")) {
			
			peloMenosUmParametroInformado = true;
			cdRota = new Short(form.getRota());
		}

		// Gerência Regional
		Integer idGerenciaRegional = null;
		if (form.getRegional() != null
				&& !form.getRegional()
						.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
			idGerenciaRegional = new Integer(
					form.getRegional());
		}

		// Unidade de Negócio
		Integer idUnidadeNegocio = null;
		if (form.getUnidadeNegocio() != null
				&& !form.getUnidadeNegocio().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;
			idUnidadeNegocio = new Integer(form.getUnidadeNegocio());
		}

		// Localidade Inicial
		Localidade localidadeInicial = null;
		SetorComercial setorComercialInicial = null;

		String idLocalidadeInicial = form
				.getIdLocalidadeInicial();
		String codigoSetorComercialInicial = form
				.getCodigoSetorComercialInicial();

		if (idLocalidadeInicial != null && !idLocalidadeInicial.equals("")) {
			peloMenosUmParametroInformado = true;

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeInicial));

			Collection colecaoLocalidades = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoLocalidades != null && !colecaoLocalidades.isEmpty()) {
				localidadeInicial = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidades);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Localidade Inicial");
			}
			
			// Setor Comercial Inicial
			if (codigoSetorComercialInicial != null && !codigoSetorComercialInicial.trim().equals("")) {
				
				FiltroSetorComercial filtro = new FiltroSetorComercial();
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercialInicial));
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidadeInicial.getId()));
				Collection pesquisa = (Collection) fachada.pesquisar(filtro, SetorComercial.class.getName());
				
				if (pesquisa != null && !pesquisa.isEmpty()) {
					setorComercialInicial = (SetorComercial) Util.retonarObjetoDeColecao(pesquisa);
				} else {
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Setor Comercial");
				}
			}
			
		}

		// Localidade Final
		Localidade localidadeFinal = null;
		SetorComercial setorComercialFinal = null;

		String idLocalidadeFinal = form
				.getIdLocalidadeFinal();
		String codigoSetorComercialFinal = form
				.getCodigoSetorComercialFinal();

		if (idLocalidadeFinal != null && !idLocalidadeFinal.equals("")) {
			peloMenosUmParametroInformado = true;

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeFinal));

			Collection colecaoLocalidades = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoLocalidades != null && !colecaoLocalidades.isEmpty()) {
				localidadeFinal = (Localidade) Util
						.retonarObjetoDeColecao(colecaoLocalidades);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Localidade Final");
			}
			
			// Setor Comercial Final
			if (codigoSetorComercialFinal != null && !codigoSetorComercialFinal.trim().equals("")) {
				
				FiltroSetorComercial filtro = new FiltroSetorComercial();
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercialFinal));
				filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidadeFinal.getId()));
				Collection pesquisa = (Collection) fachada.pesquisar(filtro, SetorComercial.class.getName());
				
				if (pesquisa != null && !pesquisa.isEmpty()) {
					setorComercialFinal = (SetorComercial) Util.retonarObjetoDeColecao(pesquisa);
				} else {
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Setor Comercial");
				}
			}
			
			pesquisarQuadra(form,fachada,httpServletRequest);
			validacaoFinal(form);	
		}
		
		Collection<Integer> colecaoIdsEmpresa = null;
		if(form.getColecaoIdsEmpresa() != null){
			colecaoIdsEmpresa = new ArrayList<Integer>();
			
			for (String id : form.getColecaoIdsEmpresa()) {
				if (!id.equals("-1")) {
					colecaoIdsEmpresa.add(new Integer(id));
				}
			}
			
			if (colecaoIdsEmpresa.size() > 0) {
				peloMenosUmParametroInformado = true;
			}	
		}

		// Anormalidade de Leitura Informada
		Collection<Integer> colecaoIdsAnormalidadeLeituraInformada = null;

		if (form.getColecaoIdsLeituraAnormalidadeInformada() != null) {
			colecaoIdsAnormalidadeLeituraInformada = new ArrayList<Integer>();
			
			for (String id : form.getColecaoIdsLeituraAnormalidadeInformada()) {
				if (!id.equals("-1")) {
					colecaoIdsAnormalidadeLeituraInformada.add(new Integer(id));
				}
			}
			
			if (colecaoIdsAnormalidadeLeituraInformada.size() > 0) {
				peloMenosUmParametroInformado = true;
			}
		}

		// Perfil do Imóvel
		Integer idImovelPerfil = null;

		if (form.getIdImovelPerfil() != null
				&& !form.getIdImovelPerfil().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;

			idImovelPerfil = new Integer( form.getIdImovelPerfil());
		}
		
		//Categoria
		Integer idCategoria = null;
		if (form.getIdCategoria() != null
				&& !form.getIdCategoria().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;

			idCategoria = new Integer( form.getIdCategoria());
		}

		// Referência
		Integer referencia = null;

		if (form.getReferencia() != null
				&& !form.getReferencia().equals("")) {
			
			peloMenosUmParametroInformado = true;

			referencia = Util.formatarMesAnoComBarraParaAnoMes(form.getReferencia());

			SistemaParametro sistemaParametro = fachada
					.pesquisarParametrosDoSistema();

			if (referencia > sistemaParametro.getAnoMesFaturamento()) {
				throw new ActionServletException(
						"atencao.ano_mes_referencia_anterior_que_ano_mes_faturamento_corrente",
						null, Util.somaMesMesAnoComBarra((Util.formatarAnoMesParaMesAno(sistemaParametro
								.getAnoMesFaturamento())),1));
			}

		}

		Integer mediaConsumoInicial = null;
		Integer mediaConsumoFinal = null;

		if (form.getIntervaloMediaConsumoInicial() != null
				&& !form.getIntervaloMediaConsumoInicial().trim().equals("")) {
			peloMenosUmParametroInformado = true;

			mediaConsumoInicial = new Integer(
					form.getIntervaloMediaConsumoInicial());

			mediaConsumoFinal = new Integer(
					form.getIntervaloMediaConsumoFinal());

			if (mediaConsumoInicial > mediaConsumoFinal) {
				throw new ActionServletException(
						"atencao.media.consumo.final.maior.media.consumo.inical");
			}

		}

		Integer numeroOcorrenciasConsecutivas = null;

		if (form.getNumOcorrenciasConsecutivas() != null
				&& !form.getNumOcorrenciasConsecutivas().trim().equals("")) {
			numeroOcorrenciasConsecutivas = new Integer(
					form.getNumOcorrenciasConsecutivas());

			if (numeroOcorrenciasConsecutivas > 12) {
				throw new ActionServletException(
						"atencao.quantidade_ocorrencia_maior");
			}
		}
		
		Integer amostragem = null;

		if (form.getAmostragem() != null && !form.getAmostragem().trim().equals("")) {
			
			amostragem = new Integer( form.getAmostragem());

			if (amostragem <= 0 || amostragem > 100 ) {
				throw new ActionServletException(
						"atencao.amostragem_intevalo_incorreto");
			}
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// seta os parametros que serão mostrados no relatório

		// Fim da parte que vai mandar os parametros para o relatório
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		RelatorioAnormalidadePorAmostragem relatorioAnormalidadePorAmostragem = new RelatorioAnormalidadePorAmostragem(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));

		relatorioAnormalidadePorAmostragem.addParametro("idGrupo", idGrupo);
		
		relatorioAnormalidadePorAmostragem.addParametro("cdRota", cdRota);

		relatorioAnormalidadePorAmostragem.addParametro("idGerenciaRegional",
				idGerenciaRegional);

		relatorioAnormalidadePorAmostragem.addParametro("idUnidadeNegocio",
				idUnidadeNegocio);

		if (localidadeInicial != null) {
			relatorioAnormalidadePorAmostragem.addParametro("idLocalidadeInicial",
					localidadeInicial.getId());
		}

		if (localidadeFinal != null) {
			relatorioAnormalidadePorAmostragem.addParametro("idLocalidadeFinal",
					localidadeFinal.getId());
		}
		
		if (setorComercialInicial != null) {
			relatorioAnormalidadePorAmostragem.addParametro("idSetorComercialInicial", setorComercialInicial.getCodigo());
		}
		
		if (setorComercialFinal != null) {
			relatorioAnormalidadePorAmostragem.addParametro("idSetorComercialFinal", setorComercialFinal.getCodigo());
		}
		
		if (form.getQuadraInicialNM() != null &&
				!form.getQuadraInicialNM().equals("")){
			relatorioAnormalidadePorAmostragem.addParametro("numeroQuadraInicial",
					new Integer(form.getQuadraInicialNM()));
		}
		
		if (form.getQuadraFinalID() != null &&
				!form.getQuadraFinalNM().equals("")){
			relatorioAnormalidadePorAmostragem.addParametro("numeroQuadraFinal", 
					new Integer(form.getQuadraFinalNM()));
		}

		if (colecaoIdsEmpresa != null) {
			relatorioAnormalidadePorAmostragem.addParametro("colecaoIdsEmpresa", colecaoIdsEmpresa);
		}

		if (colecaoIdsAnormalidadeLeituraInformada != null) {
			relatorioAnormalidadePorAmostragem.addParametro("colecaoIdsAnormalidadeLeituraInformada", colecaoIdsAnormalidadeLeituraInformada);
		}

		relatorioAnormalidadePorAmostragem.addParametro("numeroOcorrencias",
				numeroOcorrenciasConsecutivas);

		relatorioAnormalidadePorAmostragem.addParametro("ocorrenciasIguais",
				form.getIndicadorOcorrenciasIguais());

		relatorioAnormalidadePorAmostragem.addParametro("idImovelPerfil",
				idImovelPerfil);
		relatorioAnormalidadePorAmostragem.addParametro("referencia", referencia);

		relatorioAnormalidadePorAmostragem.addParametro("mediaConsumoInicial",
				mediaConsumoInicial);
		relatorioAnormalidadePorAmostragem.addParametro("mediaConsumoFinal",
				mediaConsumoFinal);
		
		relatorioAnormalidadePorAmostragem.addParametro("tipoMedicao",
				new Integer(form.getTipoMedicao()));

		relatorioAnormalidadePorAmostragem.addParametro("idCategoria",idCategoria);
		
		relatorioAnormalidadePorAmostragem.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));
		
		relatorioAnormalidadePorAmostragem.addParametro("amostragem", amostragem);

		
		retorno = processarExibicaoRelatorio(relatorioAnormalidadePorAmostragem,
				tipoRelatorio, httpServletRequest, httpServletResponse,
				actionMapping);

		return retorno;
	}
	
	private void pesquisarQuadra( GerarRelatorioAnormalidadePorAmostragemActionForm form,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		Collection colecaoPesquisa = null;
		String setorComercialCD = null;
		String setorComercialID = null;
		String quadraNM = null;
		FiltroQuadra filtroQuadra = new FiltroQuadra();

		if(form.getQuadraInicialNM() != null &&
				!form.getQuadraInicialNM().equals("")){
			
			setorComercialCD = (String) form.getCodigoSetorComercialInicial();
			setorComercialID = (String) form.getIdSetorComercialInicial();

			if (setorComercialCD != null
					&& !setorComercialCD.trim().equalsIgnoreCase("")
					&& setorComercialID != null
					&& !setorComercialID.trim().equalsIgnoreCase("")) {

				quadraNM = (String) form.getQuadraInicialNM();

				// Adiciona o id do setor comercial que está no formulário para compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));

				// Adiciona o número da quadra que esta no formulário para compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, quadraNM));

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna quadra
				colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Quadra nao encontrada
					// Limpa os campos quadraOrigemNM e quadraOrigemID do formulário
					form.setQuadraInicialNM("");
					form.setQuadraInicialID("");
					// Mensagem de tela
					form.setQuadraMensagemInicial("QUADRA INEXISTENTE.");
					httpServletRequest.setAttribute("corQuadraOrigem",	"exception");
					httpServletRequest.setAttribute("nomeCampo", "quadraInicialNM");
					
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Quadra Inicial");
					
				} else {
					Quadra objetoQuadra = (Quadra) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					form.setQuadraInicialNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
					form.setQuadraInicialID(String.valueOf(objetoQuadra.getId()));
					httpServletRequest.setAttribute("corQuadraOrigem", "valor");
					
					if(form.getQuadraFinalNM() == null || form.getQuadraFinalNM().equals("")){
						form.setQuadraFinalNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
						form.setQuadraFinalID(String.valueOf(objetoQuadra.getId()));
						httpServletRequest.setAttribute("corQuadraDestino", "valor");
					}
					
				}
			} else {
				// Limpa o campo quadraOrigemNM do formulário
				form.setQuadraInicialNM("");
				form.setQuadraMensagemInicial("Informe o setor comercial inicial.");
				httpServletRequest.setAttribute("corQuadraOrigem", "exception");
				httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercialInicial");
			}
		}
		
		if(form.getQuadraFinalNM() != null &&
				!form.getQuadraFinalNM().equals("")){
			//Recebe os valores dos campos setorComercialOrigemCD e
			// setorComercialOrigemID do formulário.
			setorComercialCD = (String) form.getCodigoSetorComercialFinal();
			setorComercialID = (String) form.getIdSetorComercialFinal();

			// Os campos setorComercialOrigemCD e setorComercialID serão
			// obrigatórios
			if (setorComercialCD != null
					&& !setorComercialCD.trim().equalsIgnoreCase("")
					&& setorComercialID != null
					&& !setorComercialID.trim().equalsIgnoreCase("")) {

				quadraNM = (String) form.getQuadraFinalNM();

				// Adiciona o id do setor comercial que está no formulário para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.ID_SETORCOMERCIAL, setorComercialID));

				// Adiciona o número da quadra que esta no formulário para
				// compor a pesquisa.
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, quadraNM));

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna quadra
				colecaoPesquisa = fachada.pesquisar(filtroQuadra, Quadra.class
						.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Quadra nao encontrada
					// Limpa os campos quadraOrigemNM e quadraOrigemID do
					// formulário
					form.setQuadraFinalNM("");
					form.setQuadraFinalID("");
					// Mensagem de tela
					form.setQuadraMensagemFinal("QUADRA INEXISTENTE.");
					httpServletRequest.setAttribute("corQuadraDestino",	"exception");
					httpServletRequest.setAttribute("nomeCampo", "quadraFinalNM");
					
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Quadra Final");
					
				} else {
					Quadra objetoQuadra = (Quadra) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					form.setQuadraFinalNM(String.valueOf(objetoQuadra.getNumeroQuadra()));
					form.setQuadraFinalID(String.valueOf(objetoQuadra.getId()));
					httpServletRequest.setAttribute("corQuadraDestino", "valor");
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formulário
				form.setQuadraFinalNM("");
				// Mensagem de tela
				form.setQuadraMensagemFinal("Informe o setor comercial final.");
				httpServletRequest.setAttribute("corQuadraDestino", "exception");
				httpServletRequest.setAttribute("nomeCampo","codigoSetorComercialFinal");
			}
		}
	}
	
	private void validacaoFinal(GerarRelatorioAnormalidadePorAmostragemActionForm form) {

		// validar localidade inicial sendo maior que localidade final
		if (form.getIdLocalidadeInicial() != null
				&& form.getIdLocalidadeFinal() != null) {
			
			if (!form.getIdLocalidadeInicial().equals("") && !form.getIdLocalidadeFinal().equals("")) {
				
				int origem = Integer.parseInt(form.getIdLocalidadeInicial());
				int destino = Integer.parseInt(form.getIdLocalidadeFinal());
				
				if (origem > destino) {
					throw new ActionServletException(
					"atencao.localidade.final.maior.localidade.inicial",null, "");
				}
			}
		}

		// validar setor comercial sendo maior que localidade final
		if (form.getCodigoSetorComercialInicial() != null
				&& form.getCodigoSetorComercialFinal() != null) {
			if (!form.getCodigoSetorComercialInicial().equals("")
					&& !form.getCodigoSetorComercialFinal().equals("")) {
				
				int origem = Integer.parseInt(form.getCodigoSetorComercialInicial());
				int destino = Integer.parseInt(form.getCodigoSetorComercialFinal());
				
				if (origem > destino) {
					throw new ActionServletException(
							"atencao.setor.comercial.final.maior.setor.comercial.inicial", null, "");
				}
			}
		}

		// validar quadra sendo maior que localidade final
		if (form.getQuadraInicialNM() != null && form.getQuadraFinalNM() != null) {
			
			if (!form.getQuadraInicialNM().equals("")
					&& !form.getQuadraFinalNM().equals("")) {
				
				int origem = Integer.parseInt(form.getQuadraInicialNM());
				int destino = Integer.parseInt(form.getQuadraFinalNM());
				
				if (origem > destino)
					throw new ActionServletException(
							"atencao.quadra.final.maior.quadra.inical", null,"");
			}
		}
	}
	
}
