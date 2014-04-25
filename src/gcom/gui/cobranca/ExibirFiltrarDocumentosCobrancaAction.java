package gcom.gui.cobranca;

import java.util.Collection;
import java.util.List;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoSituacao;
import gcom.cobranca.CobrancaDebitoSituacao;
import gcom.cobranca.DocumentoEmissaoForma;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaAcaoSituacao;
import gcom.cobranca.FiltroCobrancaDebitoSituacao;
import gcom.cobranca.FiltroDocumentoEmissaoForma;
import gcom.cobranca.FiltroMotivoNaoEntregaDocumento;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.MotivoNaoEntregaDocumento;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Consultar Pagamento - Exibir
 * 
 * @author TIAGO MORENO - 31/01/2006
 */
public class ExibirFiltrarDocumentosCobrancaAction extends GcomAction {

	private Collection colecaoPesquisa = null;

	private String localidadeID = null;

	private String setorComercialCD = null;

	private String setorComercialID = null;

	private String quadraNM = null;

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("filtrarDocumentosCobranca");

		// Instacia a fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		if(httpServletRequest.getParameter("ehPopup") != null){
			sessao.setAttribute("ehPopup","true");	
		}
		

		FiltrarDocumentosCobrancaActionForm filtrarDocumentosCobrancaActionForm = (FiltrarDocumentosCobrancaActionForm) actionForm;

		// Pesquisar Gerências Regionais
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional(FiltroGerenciaRegional.NOME_ABREVIADO);
		
		filtroGerenciaRegional.setConsultaSemLimites(true);
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME_ABREVIADO);
		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
		FiltroGerenciaRegional.INDICADOR_USO,
		ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<GerenciaRegional> colecaoGerenciasRegionais = 
			fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());

		httpServletRequest.setAttribute("colecaoGerenciasRegionais",
		colecaoGerenciasRegionais);
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME_ABREVIADO);
		Collection colecaoUnidadeNegocio = (Collection) fachada.pesquisar(
				filtroUnidadeNegocio, UnidadeNegocio.class.getName());
		if (colecaoUnidadeNegocio != null
				&& !colecaoUnidadeNegocio.isEmpty()) {
			// carregar gerencia regional
			sessao.setAttribute("colecaoUnidadeNegocio",
					colecaoUnidadeNegocio);
		} else {
			throw new ActionServletException("atencao.pesquisa_inexistente",
					null, "Tabela Unidade Negócio");
		}
		
		
		String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");

		String inscricaoTipo = (String) httpServletRequest.getParameter("inscricaoTipo");

		if (objetoConsulta != null && 
			!objetoConsulta.trim().equalsIgnoreCase("") && 
			inscricaoTipo != null && 
			!inscricaoTipo.trim().equalsIgnoreCase("")) {

			switch (Integer.parseInt(objetoConsulta)) {
			
			// Localidade
			case 1:

				pesquisarLocalidade(inscricaoTipo,
						filtrarDocumentosCobrancaActionForm, fachada,
						httpServletRequest);

				break;
			
			// Setor Comercial
			case 2:

				pesquisarLocalidade(inscricaoTipo,
						filtrarDocumentosCobrancaActionForm, fachada,
						httpServletRequest);

				pesquisarSetorComercial(inscricaoTipo,
						filtrarDocumentosCobrancaActionForm, fachada,
						httpServletRequest);

				break;
			
			// Quadra
			case 3:

				pesquisarLocalidade(inscricaoTipo,
						filtrarDocumentosCobrancaActionForm, fachada,
						httpServletRequest);

				pesquisarSetorComercial(inscricaoTipo,
						filtrarDocumentosCobrancaActionForm, fachada,
						httpServletRequest);

				pesquisarQuadra(inscricaoTipo,
						filtrarDocumentosCobrancaActionForm, fachada,
						httpServletRequest);

				break;
			default:
				break;
			}
		} else {
			sessao.removeAttribute("filtrarDocumentosCobrancaActionForm");
		}

		if (filtrarDocumentosCobrancaActionForm != null) {

			String idImovel = filtrarDocumentosCobrancaActionForm.getIdImovel();

			if (idImovel != null && !idImovel.equals("")) {

				// Pesquisa o imovel para mandar para a pagina informacoes sobre
				// o endereco
				FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("imovel");
				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("imovel.localidade");
				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial.municipio.unidadeFederacao");
				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro.municipio.unidadeFederacao");
				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTipo");
				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.logradouro.logradouroTitulo");
				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("imovel.enderecoReferencia");
				
				filtroClienteImovel.adicionarParametro(new ParametroSimples(
						FiltroClienteImovel.IMOVEL_ID, idImovel));

				Collection clientesImoveis = Fachada.getInstancia().pesquisar(
						filtroClienteImovel, ClienteImovel.class.getName());

				if (!clientesImoveis.isEmpty()) {
					
					ClienteImovel clienteImovel = (ClienteImovel) ((List) clientesImoveis)
							.get(0);

					int quantEconomias = Fachada
							.getInstancia()
							.obterQuantidadeEconomias(clienteImovel.getImovel());

					sessao.setAttribute("quantEconomias", String
							.valueOf(quantEconomias));
					try {
						filtrarDocumentosCobrancaActionForm
									.setEndereco(fachada.pesquisarEnderecoFormatado(clienteImovel.getImovel().getId()));
					} catch (ControladorException e) {
						e.printStackTrace();
					}
					filtrarDocumentosCobrancaActionForm
							.setInscricaoImovel(fachada.pesquisarInscricaoImovel(clienteImovel.getImovel().getId()));
					httpServletRequest.setAttribute("nomeCampo", "selecionar");
					httpServletRequest.setAttribute("existe", "sim");
				} else {
					
					filtrarDocumentosCobrancaActionForm.setEndereco("");
					filtrarDocumentosCobrancaActionForm.setInscricaoImovel("MATRÍCULA INEXISTENTE");

					httpServletRequest.setAttribute("corImovel", "exception");
					httpServletRequest.setAttribute("nomeCampo", "idImovel");

				}
			}
		}

		validacaoFinal(filtrarDocumentosCobrancaActionForm);


		FiltroDocumentoEmissaoForma filtroDocumentoEmissaoForma = new FiltroDocumentoEmissaoForma();
		filtroDocumentoEmissaoForma.setCampoOrderBy(FiltroDocumentoEmissaoForma.EMISSAO_FORMA);
		Collection colecaoDocumentoEmissaoForma = fachada.pesquisar(
				filtroDocumentoEmissaoForma, DocumentoEmissaoForma.class.getName());
		
		sessao.setAttribute("colecaoDocumentoEmissaoForma",colecaoDocumentoEmissaoForma);


		FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
		filtroCobrancaAcao.setCampoOrderBy(FiltroCobrancaAcao.DESCRICAO);
		Collection colecaoCobrancaAcao = fachada.pesquisar(filtroCobrancaAcao,
				CobrancaAcao.class.getName());
		sessao.setAttribute("colecaoCobrancaAcao", colecaoCobrancaAcao);

		// ---------
		FiltroMotivoNaoEntregaDocumento filtroMotivoNaoEntregaDocumento = new FiltroMotivoNaoEntregaDocumento();
		filtroMotivoNaoEntregaDocumento.setCampoOrderBy(FiltroMotivoNaoEntregaDocumento.MOTIVO);
		Collection colecaoMotivo = fachada.pesquisar(
				filtroMotivoNaoEntregaDocumento,
				MotivoNaoEntregaDocumento.class.getName());
		sessao.setAttribute("colecaoMotivo", colecaoMotivo);

		// ---------
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
		Collection colecaoImovelPerfil = fachada.pesquisar(filtroImovelPerfil,
				ImovelPerfil.class.getName());
		sessao.setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);
		
		//Adicionado por: Anderson Italo
		
		
		//Pesquisar CobrancaAcaoSituacao
		FiltroCobrancaAcaoSituacao filtroCobrancaAcaoSituacao = new FiltroCobrancaAcaoSituacao();
		
		filtroCobrancaAcaoSituacao.setConsultaSemLimites(true);
		filtroCobrancaAcaoSituacao.setCampoOrderBy(FiltroCobrancaAcaoSituacao.DESCRICAO);
		filtroCobrancaAcaoSituacao.adicionarParametro(new ParametroSimples(
		FiltroCobrancaAcaoSituacao.INDICADOR_USO,
		ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<CobrancaAcaoSituacao> colecaoCobrancaAcaoSituacao = 
			fachada.pesquisar(filtroCobrancaAcaoSituacao, CobrancaAcaoSituacao.class.getName());

		httpServletRequest.setAttribute("colecaoCobrancaAcaoSituacao",
				colecaoCobrancaAcaoSituacao);
		//[FS002] - Verificar existência de dados
		if (colecaoCobrancaAcaoSituacao == null || colecaoCobrancaAcaoSituacao.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"CobrancaAcaoSituacao");
		}
		
		//Pesquisar CobrancaDebitoSituacao
		FiltroCobrancaDebitoSituacao filtroCobrancaDebitoSituacao = new FiltroCobrancaDebitoSituacao();
		
		filtroCobrancaDebitoSituacao.setConsultaSemLimites(true);
		filtroCobrancaDebitoSituacao.setCampoOrderBy(FiltroCobrancaDebitoSituacao.DESCRICAO);
		filtroCobrancaDebitoSituacao.adicionarParametro(new ParametroSimples(
		FiltroCobrancaDebitoSituacao.INDICADOR_USO,
		ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<CobrancaDebitoSituacao> colecaoCobrancaDebitoSituacao = 
			fachada.pesquisar(filtroCobrancaDebitoSituacao, CobrancaDebitoSituacao.class.getName());

		httpServletRequest.setAttribute("colecaoCobrancaDebitoSituacao",
				colecaoCobrancaDebitoSituacao);
		//[FS002] - Verificar existência de dados
		if (colecaoCobrancaDebitoSituacao == null || colecaoCobrancaDebitoSituacao.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"CobrancaDebitoSituacao");
		}
		
		//Pesquisar Categoria
		FiltroCategoria filtroCategoria = new FiltroCategoria();
		
		filtroCategoria.setConsultaSemLimites(true);
		filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
		filtroCategoria.adicionarParametro(new ParametroSimples(
		FiltroCategoria.INDICADOR_USO,
		ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<Categoria> colecaoCategorias = 
			fachada.pesquisar(filtroCategoria, Categoria.class.getName());

		httpServletRequest.setAttribute("colecaoCategorias",
		colecaoCategorias);
		
		//[FS002] - Verificar existência de dados
		if (colecaoCategorias == null || colecaoCategorias.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Categoria");
		}
		
		//Pesquisar Empresa
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		
		filtroEmpresa.setConsultaSemLimites(true);
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO_ABREVIADA);
		filtroEmpresa.adicionarParametro(new ParametroSimples(
		FiltroEmpresa.INDICADORUSO,
		ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<Empresa> colecaoEmpresas = 
			fachada.pesquisar(filtroEmpresa, Empresa.class.getName());

		httpServletRequest.setAttribute("colecaoEmpresas",
				colecaoEmpresas);
		//[FS002] - Verificar existência de dados
		if (colecaoEmpresas == null || colecaoEmpresas.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Empresa");
		}
		//fim alteracao
		
		
		if (httpServletRequest.getParameter("tipoConsulta") != null && 
			!httpServletRequest.getParameter("tipoConsulta").equals("")) {
			
			if (httpServletRequest.getParameter("tipoConsulta").equals("localidadeDestino")) {
					
				filtrarDocumentosCobrancaActionForm.setLocalidadeDestinoID(
						httpServletRequest.getParameter("idCampoEnviarDados"));
						
				filtrarDocumentosCobrancaActionForm.setNomeLocalidadeDestino(
						httpServletRequest.getParameter("descricaoCampoEnviarDados"));
					
				httpServletRequest.setAttribute("corLocalidadeDestino","valor");

				
			}else if (httpServletRequest.getParameter("tipoConsulta").equals("localidadeOrigem")) {
				
				filtrarDocumentosCobrancaActionForm.setLocalidadeOrigemID(
						httpServletRequest.getParameter("idCampoEnviarDados"));
						
				filtrarDocumentosCobrancaActionForm.setNomeLocalidadeOrigem(
						httpServletRequest.getParameter("descricaoCampoEnviarDados"));

				httpServletRequest.setAttribute("corLocalidadeOrigem","valor");
				
			}else if (httpServletRequest.getParameter("tipoConsulta").equals("setorComercialOrigem")) {

				filtrarDocumentosCobrancaActionForm.setSetorComercialOrigemCD(
						httpServletRequest.getParameter("idCampoEnviarDados"));
						
				filtrarDocumentosCobrancaActionForm.setNomeSetorComercialOrigem(
						httpServletRequest.getParameter("descricaoCampoEnviarDados"));
				
				httpServletRequest.setAttribute("corSetorComercialOrigem","valor");
				
			}else if (httpServletRequest.getParameter("tipoConsulta").equals("setorComercialDestino")) {
				
				filtrarDocumentosCobrancaActionForm.setSetorComercialDestinoCD(
						httpServletRequest.getParameter("idCampoEnviarDados"));
						
				filtrarDocumentosCobrancaActionForm.setNomeSetorComercialDestino(
						httpServletRequest.getParameter("descricaoCampoEnviarDados"));

				httpServletRequest.setAttribute("corSetorComercialDestino","valor");
			}
		}
	
		if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaDocumentoCobranca") != null) {
			sessao.setAttribute("caminhoRetornoTelaPesquisaDocumentoCobranca",
				httpServletRequest.getParameter("caminhoRetornoTelaPesquisaDocumentoCobranca"));
		}
		
		return retorno;

	}

	private void validacaoFinal(FiltrarDocumentosCobrancaActionForm form) {

		// validar localidade inicial sendo maior que localidade final
		if (form.getLocalidadeOrigemID() != null
				&& form.getLocalidadeDestinoID() != null) {
			if (!form.getLocalidadeOrigemID().equals("")
					&& !form.getLocalidadeDestinoID().equals("")) {
				int origem = Integer.parseInt(form.getLocalidadeOrigemID());
				int destino = Integer.parseInt(form.getLocalidadeDestinoID());
				if (origem > destino) {
					throw new ActionServletException(
							"atencao.localidade.final.maior.localidade.inicial",
							null, "");
				}

			}
		}
		// validar localidade inicial sendo maior que localidade final

		// validar setor comercial sendo maior que localidade final
		if (form.getSetorComercialOrigemCD() != null
				&& form.getSetorComercialDestinoCD() != null) {
			if (!form.getSetorComercialOrigemCD().equals("")
					&& !form.getSetorComercialDestinoCD().equals("")) {
				int origem = Integer.parseInt(form.getSetorComercialOrigemCD());
				int destino = Integer.parseInt(form
						.getSetorComercialDestinoCD());
				if (origem > destino) {
					throw new ActionServletException(
							"atencao.setor.comercial.final.maior.setor.comercial.inicial",
							null, "");
				}

			}
		}
		// validar setor comercial sendo maior que localidade final

		// validar quadra sendo maior que localidade final

		if (form.getQuadraOrigemNM() != null
				&& form.getQuadraDestinoNM() != null) {
			if (!form.getQuadraOrigemNM().equals("")
					&& !form.getQuadraDestinoNM().equals("")) {
				int origem = Integer.parseInt(form.getQuadraOrigemNM());
				int destino = Integer.parseInt(form.getQuadraDestinoNM());
				if (origem > destino)
					throw new ActionServletException(
							"atencao.quadra.final.maior.quadra.inical", null,
							"");
			}
		}
		// validar quadra sendo maior que localidade final

		// validar lote sendo maior que localidade final

		if (form.getLoteOrigem() != null && form.getLoteDestino() != null) {
			if (!form.getLoteOrigem().equals("")
					&& !form.getLoteDestino().equals("")) {
				try {
					int origem = Integer.parseInt(form.getLoteOrigem());
					int destino = Integer.parseInt(form.getLoteDestino());
					if (origem > destino) {
						throw new ActionServletException(
								"atencao.lote.final.maior.lote.inical", null,
								"");
					}
				} catch (NumberFormatException e) {
					throw new ActionServletException("atencao.nao.numerico",
							null, "Lote(s)");
				}
			}
		}
		// validar lote sendo maior que localidade final

		// validar sublote sendo maior que localidade final
		if (form.getSubloteOrigem() != null && form.getSubloteDestino() != null) {
			if (!form.getSubloteOrigem().equals("")
					&& !form.getSubloteDestino().equals("")) {
				try {
					int origem = Integer.parseInt(form.getSubloteOrigem());
					int destino = Integer.parseInt(form.getSubloteDestino());
					if (origem > destino) {
						throw new ActionServletException(
								"atencao.sublote.final.maior.sublote.inicial",
								null, "");
					}
				} catch (NumberFormatException e) {
					throw new ActionServletException("atencao.nao.numerico",
							null, "SubLote(s)");
				}
			}
		}
		// validar Sublote sendo maior que localidade final

	}

	private void pesquisarLocalidade(
			String inscricaoTipo,
			FiltrarDocumentosCobrancaActionForm filtrarDocumentosCobrancaActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			filtrarDocumentosCobrancaActionForm.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formulário.
			localidadeID = (String) filtrarDocumentosCobrancaActionForm
					.getLocalidadeOrigemID();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
				// formulário
				filtrarDocumentosCobrancaActionForm.setLocalidadeOrigemID("");
				filtrarDocumentosCobrancaActionForm
						.setNomeLocalidadeOrigem("LOCALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("corLocalidadeOrigem",
						"exception");
				httpServletRequest.setAttribute("nomeCampo",
						"localidadeOrigemID");
			} else {
				Localidade objetoLocalidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoPesquisa);
				filtrarDocumentosCobrancaActionForm
						.setLocalidadeOrigemID(String.valueOf(objetoLocalidade
								.getId()));
				filtrarDocumentosCobrancaActionForm
						.setNomeLocalidadeOrigem(objetoLocalidade
								.getDescricao());
				filtrarDocumentosCobrancaActionForm
						.setLocalidadeDestinoID(String.valueOf(objetoLocalidade
								.getId()));
				filtrarDocumentosCobrancaActionForm
						.setNomeLocalidadeDestino(objetoLocalidade
								.getDescricao());
				httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");
				httpServletRequest.setAttribute("nomeCampo",
						"setorComercialOrigemCD");
			}
		} else {
			// Recebe o valor do campo localidadeDestinoID do formulário.
			localidadeID = (String) filtrarDocumentosCobrancaActionForm
					.getLocalidadeDestinoID();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			filtrarDocumentosCobrancaActionForm.setInscricaoTipo("destino");

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Localidade nao encontrada
				// Limpa os campos localidadeDestinoID e nomeLocalidadeDestino
				// do formulário
				filtrarDocumentosCobrancaActionForm.setLocalidadeDestinoID("");
				filtrarDocumentosCobrancaActionForm
						.setNomeLocalidadeDestino("Localidade inexistente.");
				httpServletRequest.setAttribute("corLocalidadeDestino",
						"exception");
				httpServletRequest.setAttribute("nomeCampo",
						"localidadeDestinoID");
			} else {
				Localidade objetoLocalidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoPesquisa);
				filtrarDocumentosCobrancaActionForm
						.setLocalidadeDestinoID(String.valueOf(objetoLocalidade
								.getId()));
				filtrarDocumentosCobrancaActionForm
						.setNomeLocalidadeDestino(objetoLocalidade
								.getDescricao());
				httpServletRequest
						.setAttribute("corLocalidadeDestino", "valor");
				httpServletRequest.setAttribute("nomeCampo",
						"setorComercialDestinoCD");
			}
		}

	}

	private void pesquisarSetorComercial(
			String inscricaoTipo,
			FiltrarDocumentosCobrancaActionForm filtrarDocumentosCobrancaActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			filtrarDocumentosCobrancaActionForm.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formulário.
			localidadeID = (String) filtrarDocumentosCobrancaActionForm
					.getLocalidadeOrigemID();

			// O campo localidadeOrigemID será obrigatório
			if (localidadeID != null
					&& !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) filtrarDocumentosCobrancaActionForm
						.getSetorComercialOrigemCD();

				// Adiciona o id da localidade que está no formulário para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

				// Adiciona o código do setor comercial que esta no formulário
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
						SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialOrigemCD,
					// nomeSetorComercialOrigem e setorComercialOrigemID do
					// formulário
					filtrarDocumentosCobrancaActionForm
							.setSetorComercialOrigemCD("");
					filtrarDocumentosCobrancaActionForm
							.setSetorComercialOrigemID("");
					filtrarDocumentosCobrancaActionForm
							.setNomeSetorComercialOrigem("Setor comercial inexistente.");
					httpServletRequest.setAttribute("corSetorComercialOrigem",
							"exception");
					httpServletRequest.setAttribute("nomeCampo",
							"setorComercialOrigemCD");

				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					// setorComercialOrigem
					filtrarDocumentosCobrancaActionForm
							.setSetorComercialOrigemCD(String
									.valueOf(objetoSetorComercial.getCodigo()));
					filtrarDocumentosCobrancaActionForm
							.setSetorComercialOrigemID(String
									.valueOf(objetoSetorComercial.getId()));
					filtrarDocumentosCobrancaActionForm
							.setNomeSetorComercialOrigem(objetoSetorComercial
									.getDescricao());
					// setorComercialOrigem

					// setorComercialDestino
					filtrarDocumentosCobrancaActionForm
							.setSetorComercialDestinoCD(String
									.valueOf(objetoSetorComercial.getCodigo()));
					filtrarDocumentosCobrancaActionForm
							.setSetorComercialDestinoID(String
									.valueOf(objetoSetorComercial.getId()));
					filtrarDocumentosCobrancaActionForm
							.setNomeSetorComercialDestino(objetoSetorComercial
									.getDescricao());
					// setorComercialDestino
					httpServletRequest.setAttribute("corSetorComercialOrigem",
							"valor");
					httpServletRequest.setAttribute("nomeCampo",
							"quadraOrigemNM");
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formulário
				filtrarDocumentosCobrancaActionForm
						.setSetorComercialOrigemCD("");
				filtrarDocumentosCobrancaActionForm
						.setNomeSetorComercialOrigem("Informe a localidade da inscrição de origem.");
				httpServletRequest.setAttribute("corSetorComercialOrigem",
						"exception");
				httpServletRequest.setAttribute("nomeCampo",
						"localidadeOrigemID");
			}
		} else {

			filtrarDocumentosCobrancaActionForm.setInscricaoTipo("destino");

			// Recebe o valor do campo localidadeDestinoID do formulário.
			localidadeID = (String) filtrarDocumentosCobrancaActionForm
					.getLocalidadeDestinoID();

			// O campo localidadeOrigem será obrigatório
			if (localidadeID != null
					&& !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) filtrarDocumentosCobrancaActionForm
						.getSetorComercialDestinoCD();

				// Adiciona o id da localidade que está no formulário para
				// compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, localidadeID));

				// Adiciona o código do setor comercial que esta no formulário
				// para compor a pesquisa.
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						setorComercialCD));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna setorComercial
				colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
						SetorComercial.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialDestinoCD,
					// nomeSetorComercialDestino e setorComercialDestinoID do
					// formulário
					filtrarDocumentosCobrancaActionForm
							.setSetorComercialDestinoCD("");
					filtrarDocumentosCobrancaActionForm
							.setSetorComercialDestinoID("");
					filtrarDocumentosCobrancaActionForm
							.setNomeSetorComercialDestino("Setor comercial inexistente.");
					httpServletRequest.setAttribute("corSetorComercialDestino",
							"exception");
					httpServletRequest.setAttribute("nomeCampo",
							"setorComercialDestinoCD");
				} else {
					SetorComercial objetoSetorComercial = (SetorComercial) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					filtrarDocumentosCobrancaActionForm
							.setSetorComercialDestinoCD(String
									.valueOf(objetoSetorComercial.getCodigo()));
					filtrarDocumentosCobrancaActionForm
							.setSetorComercialDestinoID(String
									.valueOf(objetoSetorComercial.getId()));
					filtrarDocumentosCobrancaActionForm
							.setNomeSetorComercialDestino(objetoSetorComercial
									.getDescricao());
					httpServletRequest.setAttribute("corSetorComercialDestino",
							"valor");
					httpServletRequest.setAttribute("nomeCampo",
							"quadraDestinoNM");
				}
			} else {
				// Limpa o campo setorComercialDestinoCD do formulário
				filtrarDocumentosCobrancaActionForm
						.setSetorComercialDestinoCD("");
				filtrarDocumentosCobrancaActionForm
						.setNomeSetorComercialDestino("Informe a localidade da inscrição de destino.");
				httpServletRequest.setAttribute("corSetorComercialDestino",
						"exception");
				httpServletRequest.setAttribute("nomeCampo",
						"localidadeDestinoID");
			}
		}

	}

	private void pesquisarQuadra(
			String inscricaoTipo,
			FiltrarDocumentosCobrancaActionForm filtrarDocumentosCobrancaActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		FiltroQuadra filtroQuadra = new FiltroQuadra();

		// Objetos que serão retornados pelo hibernate.
		//filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bairro");

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			filtrarDocumentosCobrancaActionForm.setInscricaoTipo("origem");
			// Recebe os valores dos campos setorComercialOrigemCD e
			// setorComercialOrigemID do formulário.
			setorComercialCD = (String) filtrarDocumentosCobrancaActionForm
					.getSetorComercialOrigemCD();

			setorComercialID = (String) filtrarDocumentosCobrancaActionForm
					.getSetorComercialOrigemID();

			// Os campos setorComercialOrigemCD e setorComercialID serão
			// obrigatórios
			if (setorComercialCD != null
					&& !setorComercialCD.trim().equalsIgnoreCase("")
					&& setorComercialID != null
					&& !setorComercialID.trim().equalsIgnoreCase("")) {

				quadraNM = (String) filtrarDocumentosCobrancaActionForm
						.getQuadraOrigemNM();

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
					filtrarDocumentosCobrancaActionForm.setQuadraOrigemNM("");
					filtrarDocumentosCobrancaActionForm.setQuadraOrigemID("");
					// Mensagem de tela
					filtrarDocumentosCobrancaActionForm
							.setQuadraMensagemOrigem("QUADRA INEXISTENTE.");
					httpServletRequest.setAttribute("corQuadraOrigem",
							"exception");
					httpServletRequest.setAttribute("nomeCampo",
							"quadraOrigemNM");
				} else {
					Quadra objetoQuadra = (Quadra) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					filtrarDocumentosCobrancaActionForm
							.setQuadraOrigemNM(String.valueOf(objetoQuadra
									.getNumeroQuadra()));
					filtrarDocumentosCobrancaActionForm
							.setQuadraOrigemID(String.valueOf(objetoQuadra
									.getId()));
					filtrarDocumentosCobrancaActionForm
							.setQuadraDestinoNM(String.valueOf(objetoQuadra
									.getNumeroQuadra()));
					filtrarDocumentosCobrancaActionForm
							.setQuadraDestinoID(String.valueOf(objetoQuadra
									.getId()));
					httpServletRequest.setAttribute("corQuadraOrigem", "valor");
					httpServletRequest.setAttribute("nomeCampo", "loteOrigem");
				}
			} else {
				// Limpa o campo quadraOrigemNM do formulário
				filtrarDocumentosCobrancaActionForm.setQuadraOrigemNM("");
				filtrarDocumentosCobrancaActionForm
						.setQuadraMensagemOrigem("Informe o setor comercial da inscrição de origem.");
				httpServletRequest.setAttribute("corQuadraOrigem", "exception");
				httpServletRequest.setAttribute("nomeCampo",
						"setorComercialOrigemCD");
			}
		} else {

			filtrarDocumentosCobrancaActionForm.setInscricaoTipo("destino");

			// Recebe os valores dos campos setorComercialOrigemCD e
			// setorComercialOrigemID do formulário.
			setorComercialCD = (String) filtrarDocumentosCobrancaActionForm
					.getSetorComercialDestinoCD();
			setorComercialID = (String) filtrarDocumentosCobrancaActionForm
					.getSetorComercialDestinoID();

			// Os campos setorComercialOrigemCD e setorComercialID serão
			// obrigatórios
			if (setorComercialCD != null
					&& !setorComercialCD.trim().equalsIgnoreCase("")
					&& setorComercialID != null
					&& !setorComercialID.trim().equalsIgnoreCase("")) {

				quadraNM = (String) filtrarDocumentosCobrancaActionForm
						.getQuadraDestinoNM();

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
					filtrarDocumentosCobrancaActionForm.setQuadraDestinoNM("");
					filtrarDocumentosCobrancaActionForm.setQuadraDestinoID("");
					// Mensagem de tela
					filtrarDocumentosCobrancaActionForm
							.setQuadraMensagemDestino("Quadra inexistente.");
					httpServletRequest.setAttribute("corQuadraDestino",
							"exception");
					httpServletRequest.setAttribute("nomeCampo",
							"quadraDestinoNM");
				} else {
					Quadra objetoQuadra = (Quadra) Util
							.retonarObjetoDeColecao(colecaoPesquisa);
					filtrarDocumentosCobrancaActionForm
							.setQuadraDestinoNM(String.valueOf(objetoQuadra
									.getNumeroQuadra()));
					filtrarDocumentosCobrancaActionForm
							.setQuadraDestinoID(String.valueOf(objetoQuadra
									.getId()));
					httpServletRequest
							.setAttribute("corQuadraDestino", "valor");
					httpServletRequest.setAttribute("nomeCampo", "loteDestino");
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formulário
				filtrarDocumentosCobrancaActionForm.setQuadraDestinoNM("");
				// Mensagem de tela
				filtrarDocumentosCobrancaActionForm
						.setQuadraMensagemDestino("Informe o setor comercial da inscrição.");
				httpServletRequest
						.setAttribute("corQuadraDestino", "exception");
				httpServletRequest.setAttribute("nomeCampo",
						"setorComercialDestinoCD");
			}
		}

	}

}
