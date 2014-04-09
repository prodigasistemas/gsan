package gcom.gui.relatorio.cadastro.imovel;

import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioDadosTarifaSocialAction extends ExibidorProcessamentoTarefaRelatorio {

	/**
	 * <<Descrição do método>>
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

/*

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

			Fachada fachada = Fachada.getInstancia();

			Collection imoveisRelatoriosHelper = null;
			ImovelOutrosCriteriosActionForm imovelOutrosCriteriosActionForm = (ImovelOutrosCriteriosActionForm) actionForm;
			imoveisRelatoriosHelper = (Collection) httpServletRequest
					.getAttribute("collectionImoveis");

			// Inicia parte q vai mandar os parâmetros para o relatório
			Imovel imovelParametrosInicial = new Imovel();
			Imovel imovelParametrosFinal = new Imovel();
			ClienteImovel clienteImovelParametros = new ClienteImovel();
			LigacaoAgua ligacaoAguaParametrosInicial = new LigacaoAgua();
			LigacaoAgua ligacaoAguaParametrosFinal = new LigacaoAgua();
			LigacaoEsgoto ligacaoEsgotoParametrosInicial = new LigacaoEsgoto();
			LigacaoEsgoto ligacaoEsgotoParametrosFinal = new LigacaoEsgoto();
			ConsumoHistorico consumoHistoricoParametrosInicial = new ConsumoHistorico();
			ConsumoHistorico consumoHistoricoParametrosFinal = new ConsumoHistorico();
			MedicaoHistorico medicaoHistoricoParametrosInicial = new MedicaoHistorico();
			MedicaoHistorico medicaoHistoricoParametrosFinal = new MedicaoHistorico();
			TarifaSocialDado tarifaSocialDadoInicial = new TarifaSocialDado();
			TarifaSocialDado tarifaSocialDadoFinal = new TarifaSocialDado();
			TarifaSocialDadoEconomia tarifaSocialDadoEconomiaInicial = new TarifaSocialDadoEconomia();
			TarifaSocialDadoEconomia tarifaSocialDadoEconomiaFinal = new TarifaSocialDadoEconomia();

			// Gerência Regional

			String idGerenciaRegional = (String) imovelOutrosCriteriosActionForm
					.getIdGerenciaRegional();

			GerenciaRegional gerenciaRegional = null;

			if (idGerenciaRegional != null && !idGerenciaRegional.equals("")) {
				FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

				filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
						FiltroGerenciaRegional.ID, idGerenciaRegional));
				filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
						FiltroGerenciaRegional.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection gerenciasRegionais = fachada.pesquisar(
						filtroGerenciaRegional, GerenciaRegional.class
								.getName());

				if (gerenciasRegionais != null && !gerenciasRegionais.isEmpty()) {
					// A Gerência Regional Foi Encontrada
					Iterator gerenciaRegionalIterator = gerenciasRegionais
							.iterator();

					gerenciaRegional = (GerenciaRegional) gerenciaRegionalIterator
							.next();

				} else {
					gerenciaRegional = new GerenciaRegional();
				}
			}

			// Quadra Inicial

			String numeroQuadraOrigem = (String) imovelOutrosCriteriosActionForm
					.getQuadraOrigemNM();

			Quadra quadraOrigem = null;

			if (numeroQuadraOrigem != null && !numeroQuadraOrigem.equals("")) {
				FiltroQuadra filtroQuadra = new FiltroQuadra();

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, numeroQuadraOrigem));
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoQuadras = fachada.pesquisar(filtroQuadra,
						Quadra.class.getName());

				if (colecaoQuadras != null && !colecaoQuadras.isEmpty()) {
					// A quadra foi encontrada
					Iterator quadraIterator = colecaoQuadras.iterator();

					quadraOrigem = (Quadra) quadraIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Quadra");
				}

			} else {
				quadraOrigem = new Quadra();
			}

			// Quadra Final

			String numeroQuadraDestino = (String) imovelOutrosCriteriosActionForm
					.getQuadraDestinoNM();

			Quadra quadraDestino = null;

			if (numeroQuadraDestino != null && !numeroQuadraDestino.equals("")) {
				FiltroQuadra filtroQuadra = new FiltroQuadra();

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, numeroQuadraDestino));
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoQuadras = fachada.pesquisar(filtroQuadra,
						Quadra.class.getName());

				if (colecaoQuadras != null && !colecaoQuadras.isEmpty()) {
					// A quadra foi encontrada
					Iterator quadraIterator = colecaoQuadras.iterator();

					quadraDestino = (Quadra) quadraIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Quadra");
				}

			} else {
				quadraDestino = new Quadra();
			}

			// Lote Inicial

			String loteOrigem = null;
			String loteOrigemPesquisado = (String) imovelOutrosCriteriosActionForm
					.getLoteOrigem();

			if (loteOrigemPesquisado != null
					&& !loteOrigemPesquisado.equals("")) {
				loteOrigem = loteOrigemPesquisado;
			}

			// Lote Final

			String loteDestino = null;
			String loteDestinoPesquisado = (String) imovelOutrosCriteriosActionForm
					.getLoteDestino();

			if (loteDestinoPesquisado != null
					&& !loteDestinoPesquisado.equals("")) {
				loteDestino = loteDestinoPesquisado;
			}

			// Cep

			Cep cep = new Cep();
			String numeroCep = null;
			String cepPesquisado = (String) imovelOutrosCriteriosActionForm
					.getCEP();

			if (cepPesquisado != null && !cepPesquisado.equals("")) {
				numeroCep = cepPesquisado;
				cep.setCodigo(new Integer(numeroCep));
			}

			// Localidade Inicial

			String idLocalidadeOrigem = (String) imovelOutrosCriteriosActionForm
					.getLocalidadeOrigemID();

			Localidade localidadeOrigem = null;

			if (idLocalidadeOrigem != null && !idLocalidadeOrigem.equals("")) {
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, idLocalidadeOrigem));
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoLocalidades = fachada.pesquisar(
						filtroLocalidade, Localidade.class.getName());

				if (colecaoLocalidades != null && !colecaoLocalidades.isEmpty()) {
					// A localidade foi encontrada
					Iterator localidadeIterator = colecaoLocalidades.iterator();

					localidadeOrigem = (Localidade) localidadeIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Localidade");
				}

			} else {
				localidadeOrigem = new Localidade();
			}

			// Localidade Final

			String idLocalidadeDestino = (String) imovelOutrosCriteriosActionForm
					.getLocalidadeDestinoID();

			Localidade localidadeDestino = null;

			if (idLocalidadeDestino != null && !idLocalidadeDestino.equals("")) {
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, idLocalidadeDestino));
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoLocalidades = fachada.pesquisar(
						filtroLocalidade, Localidade.class.getName());

				if (colecaoLocalidades != null && !colecaoLocalidades.isEmpty()) {
					// A localidade foi encontrada
					Iterator localidadeIterator = colecaoLocalidades.iterator();

					localidadeDestino = (Localidade) localidadeIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Localidade");
				}

			} else {
				localidadeDestino = new Localidade();
			}

			// Setor Comercial Inicial

			String idSetorComercialOrigem = (String) imovelOutrosCriteriosActionForm
					.getSetorComercialOrigemID();

			SetorComercial setorComercialOrigem = null;

			if (idSetorComercialOrigem != null
					&& !idSetorComercialOrigem.equals("")) {
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID, idSetorComercialOrigem));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoSetoresComerciais = fachada.pesquisar(
						filtroSetorComercial, SetorComercial.class.getName());

				if (colecaoSetoresComerciais != null
						&& !colecaoSetoresComerciais.isEmpty()) {
					// O setor comercial foi encontrado
					Iterator setorComercialIterator = colecaoSetoresComerciais
							.iterator();

					setorComercialOrigem = (SetorComercial) setorComercialIterator
							.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Setor Comercial");
				}

			} else {
				setorComercialOrigem = new SetorComercial();
			}

			// Setor Comercial Final

			String idSetorComercialDestino = (String) imovelOutrosCriteriosActionForm
					.getSetorComercialDestinoID();

			SetorComercial setorComercialDestino = null;

			if (idSetorComercialDestino != null
					&& !idSetorComercialDestino.equals("")) {
				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID, idSetorComercialDestino));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoSetoresComerciais = fachada.pesquisar(
						filtroSetorComercial, SetorComercial.class.getName());

				if (colecaoSetoresComerciais != null
						&& !colecaoSetoresComerciais.isEmpty()) {
					// O setor comercial foi encontrado
					Iterator setorComercialIterator = colecaoSetoresComerciais
							.iterator();

					setorComercialDestino = (SetorComercial) setorComercialIterator
							.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Setor Comercial");
				}

			} else {
				setorComercialDestino = new SetorComercial();
			}

			// Cliente

			String idCliente = (String) imovelOutrosCriteriosActionForm
					.getIdCliente();

			Cliente cliente = null;

			if (idCliente != null && !idCliente.equals("")) {
				FiltroCliente filtroCliente = new FiltroCliente();

				filtroCliente.adicionarParametro(new ParametroSimples(
						FiltroCliente.ID, idCliente));
				filtroCliente.adicionarParametro(new ParametroSimples(
						FiltroCliente.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoClientes = fachada.pesquisar(filtroCliente,
						Cliente.class.getName());

				if (colecaoClientes != null && !colecaoClientes.isEmpty()) {
					// O cliente foi encontrado
					Iterator clienteIterator = colecaoClientes.iterator();

					cliente = (Cliente) clienteIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Cliente");
				}

			} else {
				cliente = new Cliente();
			}

			// Município

			String idMunicipio = (String) imovelOutrosCriteriosActionForm
					.getIdMunicipio();

			Municipio municipio = null;

			if (idMunicipio != null && !idMunicipio.equals("")) {
				FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

				filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.ID, idMunicipio));
				filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection municipios = fachada.pesquisar(filtroMunicipio,
						Municipio.class.getName());

				if (municipios != null && !municipios.isEmpty()) {
					// O municipio foi encontrado
					Iterator municipioIterator = municipios.iterator();

					municipio = (Municipio) municipioIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Município");
				}

			} else {
				municipio = new Municipio();
			}

			// Bairro

			String idBairro = (String) imovelOutrosCriteriosActionForm
					.getIdBairro();

			Bairro bairro = null;

			if (idBairro != null && !idBairro.equals("")) {
				FiltroBairro filtroBairro = new FiltroBairro();

				filtroBairro.adicionarParametro(new ParametroSimples(
						FiltroBairro.CODIGO, idBairro));

				Collection bairros = fachada.pesquisar(filtroBairro,
						Bairro.class.getName());

				if (bairros != null && !bairros.isEmpty()) {
					// O bairro foi encontrado
					Iterator bairroIterator = bairros.iterator();

					bairro = (Bairro) bairroIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Bairro");
				}

			} else {
				bairro = new Bairro();
			}

			// Logradouro

			String idLogradouro = (String) imovelOutrosCriteriosActionForm
					.getIdLogradouro();

			Logradouro logradouro = null;

			if (idLogradouro != null && !idLogradouro.equals("")) {
				FiltroLogradouro filtroLogradouro = new FiltroLogradouro();

				filtroLogradouro.adicionarParametro(new ParametroSimples(
						FiltroLogradouro.ID, idLogradouro));
				filtroLogradouro.adicionarParametro(new ParametroSimples(
						FiltroLogradouro.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection logradouros = fachada.pesquisar(filtroLogradouro,
						Logradouro.class.getName());

				if (logradouros != null && !logradouros.isEmpty()) {
					// O logradouro foi encontrado
					Iterator logradouroIterator = logradouros.iterator();

					logradouro = (Logradouro) logradouroIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Logradouro");
				}

			} else {
				logradouro = new Logradouro();
			}

			// Tipo da Relação

			String idRelacaoTipo = null;
			ClienteRelacaoTipo clienteRelacaoTipo = null;

			if (idRelacaoTipo != null && !idRelacaoTipo.equals("")) {
				FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();

				filtroClienteRelacaoTipo
						.adicionarParametro(new ParametroSimples(
								FiltroClienteRelacaoTipo.CLIENTE_RELACAO_TIPO_ID,
								idRelacaoTipo));
				filtroClienteRelacaoTipo
						.adicionarParametro(new ParametroSimples(
								FiltroClienteRelacaoTipo.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoClientesRelacoesTipo = fachada.pesquisar(
						filtroClienteRelacaoTipo, ClienteRelacaoTipo.class
								.getName());

				if (colecaoClientesRelacoesTipo != null
						&& !colecaoClientesRelacoesTipo.isEmpty()) {
					// O Tipo da Relação do Cliente foi encontrada
					Iterator clienteRelacaoTipoIterator = colecaoClientesRelacoesTipo
							.iterator();

					clienteRelacaoTipo = (ClienteRelacaoTipo) clienteRelacaoTipoIterator
							.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Logradouro");
				}

			} else {
				clienteRelacaoTipo = new ClienteRelacaoTipo();
			}

			// Tipo de Cliente

			String idClienteTipo = (String) imovelOutrosCriteriosActionForm
					.getIdClienteTipo();

			ClienteTipo clienteTipo = null;

			if (idClienteTipo != null && !idClienteTipo.equals("")) {
				FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();

				filtroClienteTipo.adicionarParametro(new ParametroSimples(
						FiltroClienteTipo.ID, idClienteTipo));
				filtroClienteTipo.adicionarParametro(new ParametroSimples(
						FiltroClienteTipo.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoClientesTipos = fachada.pesquisar(
						filtroClienteTipo, ClienteTipo.class.getName());

				if (colecaoClientesTipos != null
						&& !colecaoClientesTipos.isEmpty()) {
					// O Tipo do cliente foi encontrado
					Iterator clienteTipoIterator = colecaoClientesTipos
							.iterator();

					clienteTipo = (ClienteTipo) clienteTipoIterator.next();
					cliente.setClienteTipo(clienteTipo);

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Tipo de Cliente");
				}

			} else {
				clienteTipo = new ClienteTipo();
				cliente.setClienteTipo(clienteTipo);
			}

			// Imóvel Condomínio

			String idImovelCondominio = (String) imovelOutrosCriteriosActionForm
					.getIdImovelCondominio();

			Imovel imovelCondominio = null;

			if (idImovelCondominio != null && !idImovelCondominio.equals("")) {
				FiltroImovel filtroImovel = new FiltroImovel();

				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, idImovelCondominio));

				Collection colecaoImoveis = fachada.pesquisar(filtroImovel,
						Imovel.class.getName());

				if (colecaoImoveis != null && !colecaoImoveis.isEmpty()) {
					// O Imóvel condomínio foi encontrado
					Iterator imovelIterator = colecaoImoveis.iterator();

					imovelCondominio = (Imovel) imovelIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Imóvel Condomínio");
				}

			} else {
				imovelCondominio = new Imovel();
			}

			// Imóvel Principal

			String idImovelPrincipal = (String) imovelOutrosCriteriosActionForm
					.getIdImovelPrincipal();

			Imovel imovelPrincipal = null;

			if (idImovelPrincipal != null && !idImovelPrincipal.equals("")) {
				FiltroImovel filtroImovel = new FiltroImovel();

				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, idImovelPrincipal));

				Collection colecaoImoveisPrincipais = fachada.pesquisar(
						filtroImovel, Imovel.class.getName());

				if (colecaoImoveisPrincipais != null
						&& !colecaoImoveisPrincipais.isEmpty()) {
					// O Imóvel principal foi encontrado
					Iterator imovelPrincipalIterator = colecaoImoveisPrincipais
							.iterator();

					imovelPrincipal = (Imovel) imovelPrincipalIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Imóvel Principal");
				}

			} else {
				imovelPrincipal = new Imovel();
			}

			// Nome Conta

//			String idNomeConta = (String) imovelOutrosCriteriosActionForm
//					.getIdNomeConta();

//			NomeConta nomeConta = null;

//			if (idNomeConta != null && !idNomeConta.equals("")) {
//				FiltroNomeConta filtroNomeConta = new FiltroNomeConta();
//
//				filtroNomeConta.adicionarParametro(new ParametroSimples(
//						FiltroNomeConta.CODIGO, idNomeConta));
//				filtroNomeConta.adicionarParametro(new ParametroSimples(
//						FiltroNomeConta.INDICADOR_USO,
//						ConstantesSistema.INDICADOR_USO_ATIVO));
//
//				Collection colecaoNomesContas = fachada.pesquisar(
//						filtroNomeConta, Imovel.class.getName());
//
//				if (colecaoNomesContas != null && !colecaoNomesContas.isEmpty()) {
//					// O Nome da conta foi encontrado
//					Iterator nomeContaIterator = colecaoNomesContas.iterator();
//
//					nomeConta = (NomeConta) nomeContaIterator.next();
//
//				} else {
//					throw new ActionServletException(
//							"atencao.pesquisa_inexistente", null,
//							"Nome da Conta");
//				}
//
//			} else {
//				nomeConta = new NomeConta();
//			}

			// Situação Ligação Água

			String idSituacaoLigacaoAgua = (String) imovelOutrosCriteriosActionForm
					.getSituacaoAgua();

			LigacaoAguaSituacao ligacaoAguaSituacao = null;

			if (idSituacaoLigacaoAgua != null
					&& !idSituacaoLigacaoAgua.equals("")) {
				FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();

				filtroLigacaoAguaSituacao
						.adicionarParametro(new ParametroSimples(
								FiltroLigacaoAguaSituacao.ID,
								idSituacaoLigacaoAgua));
				filtroLigacaoAguaSituacao
						.adicionarParametro(new ParametroSimples(
								FiltroLigacaoAguaSituacao.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoSituacoesLigacoesAgua = fachada.pesquisar(
						filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class
								.getName());

				if (colecaoSituacoesLigacoesAgua != null
						&& !colecaoSituacoesLigacoesAgua.isEmpty()) {
					// A Situação da Ligação de Água foi encontrada
					Iterator situacaoLigacaoAguaIterator = colecaoSituacoesLigacoesAgua
							.iterator();

					ligacaoAguaSituacao = (LigacaoAguaSituacao) situacaoLigacaoAguaIterator
							.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Situação da Ligação de Água");
				}

			} else {
				ligacaoAguaSituacao = new LigacaoAguaSituacao();
			}

			// Situação Ligação Esgoto

			String idSituacaoLigacaoEsgoto = (String) imovelOutrosCriteriosActionForm
					.getSituacaoLigacaoEsgoto();

			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = null;

			if (idSituacaoLigacaoEsgoto != null
					&& !idSituacaoLigacaoEsgoto.equals("")) {
				FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();

				filtroLigacaoEsgotoSituacao
						.adicionarParametro(new ParametroSimples(
								FiltroLigacaoEsgotoSituacao.ID,
								idSituacaoLigacaoEsgoto));
				filtroLigacaoEsgotoSituacao
						.adicionarParametro(new ParametroSimples(
								FiltroLigacaoEsgotoSituacao.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoSituacoesLigacoesEsgoto = fachada.pesquisar(
						filtroLigacaoEsgotoSituacao,
						LigacaoEsgotoSituacao.class.getName());

				if (colecaoSituacoesLigacoesEsgoto != null
						&& !colecaoSituacoesLigacoesEsgoto.isEmpty()) {
					// A Situação da Ligação de Esgoto foi encontrada
					Iterator situacaoLigacaoEsgotoIterator = colecaoSituacoesLigacoesEsgoto
							.iterator();

					ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) situacaoLigacaoEsgotoIterator
							.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Situação da Ligação de Esgoto");
				}

			} else {
				ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
			}

			// Intervalo Consumo Mínimo Fixado de Água

			String consumoFixadoAguaInicial = null;
			String consumoFixadoAguaInicialPesquisado = imovelOutrosCriteriosActionForm
					.getConsumoMinimoInicial();

			if (consumoFixadoAguaInicialPesquisado != null
					&& !consumoFixadoAguaInicialPesquisado.equals("")) {
				consumoFixadoAguaInicial = consumoFixadoAguaInicialPesquisado;
			}

			String consumoFixadoAguaFinal = null;
			String consumoFixadoAguaFinalPesquisado = imovelOutrosCriteriosActionForm
					.getConsumoMinimoFinal();

			if (consumoFixadoAguaFinalPesquisado != null
					&& !consumoFixadoAguaFinalPesquisado.equals("")) {
				consumoFixadoAguaFinal = consumoFixadoAguaFinalPesquisado;
			}

			// Intervalo Consumo Mínimo Fixado de Esgoto

			String consumoFixadoEsgotoInicial = null;
			String consumoFixadoEsgotoInicialPesquisado = imovelOutrosCriteriosActionForm
					.getConsumoMinimoFixadoEsgotoInicial();

			if (consumoFixadoEsgotoInicialPesquisado != null
					&& !consumoFixadoEsgotoInicialPesquisado.equals("")) {
				consumoFixadoEsgotoInicial = consumoFixadoEsgotoInicialPesquisado;
			}

			String consumoFixadoEsgotoFinal = null;
			String consumoFixadoEsgotoFinalPesquisado = imovelOutrosCriteriosActionForm
					.getConsumoMinimoFixadoEsgotoFinal();

			if (consumoFixadoEsgotoFinalPesquisado != null
					&& !consumoFixadoEsgotoFinalPesquisado.equals("")) {
				consumoFixadoEsgotoFinal = consumoFixadoEsgotoFinalPesquisado;
			}

			// Intervalo Percentual Esgoto

			String percentualEsgotoInicial = null;
			String percentualEsgotoInicialPesquisado = imovelOutrosCriteriosActionForm
					.getIntervaloPercentualEsgotoInicial();

			if (percentualEsgotoInicialPesquisado != null
					&& !percentualEsgotoInicialPesquisado.equals("")) {
				percentualEsgotoInicial = percentualEsgotoInicialPesquisado;
			}

			String percentualEsgotoFinal = null;
			String percentualEsgotoFinalPesquisado = imovelOutrosCriteriosActionForm
					.getIntervaloPercentualEsgotoFinal();

			if (percentualEsgotoFinalPesquisado != null
					&& !percentualEsgotoFinalPesquisado.equals("")) {
				percentualEsgotoFinal = percentualEsgotoFinalPesquisado;
			}

			// Indicador Medição

			Short indicadorMedicao = null;
			String indicadorMedicaoPesquisado = imovelOutrosCriteriosActionForm
					.getIndicadorMedicao();

			if (indicadorMedicaoPesquisado != null
					&& !indicadorMedicaoPesquisado.equals("")) {
				indicadorMedicao = new Short(indicadorMedicaoPesquisado);
			}

			// Medição Tipo

			String idMedicaoTipo = (String) imovelOutrosCriteriosActionForm
					.getTipoMedicao();

			MedicaoTipo medicaoTipo = null;

			if (idMedicaoTipo != null && !idMedicaoTipo.equals("")) {
				FiltroMedicaoTipo filtroMedicaoTipo = new FiltroMedicaoTipo();

				filtroMedicaoTipo.adicionarParametro(new ParametroSimples(
						FiltroMedicaoTipo.ID, idMedicaoTipo));
				filtroMedicaoTipo.adicionarParametro(new ParametroSimples(
						FiltroMedicaoTipo.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoMedicoesTipos = fachada.pesquisar(
						filtroMedicaoTipo, MedicaoTipo.class.getName());

				if (colecaoMedicoesTipos != null
						&& !colecaoMedicoesTipos.isEmpty()) {
					// O Tipo de Medição foi encontrado
					Iterator medicaoTipoIterator = colecaoMedicoesTipos
							.iterator();

					medicaoTipo = (MedicaoTipo) medicaoTipoIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Tipo de Medição");
				}

			} else {
				medicaoTipo = new MedicaoTipo();
			}

			// Intervalo Média Mínima Imóvel

			String mediaMinimaImovelInicial = null;
			String mediaMinimaImovelInicialPesquisado = imovelOutrosCriteriosActionForm
					.getIntervaloMediaMinimaImovelInicio();

			if (mediaMinimaImovelInicialPesquisado != null
					&& !mediaMinimaImovelInicialPesquisado.equals("")) {
				mediaMinimaImovelInicial = mediaMinimaImovelInicialPesquisado;
			}

			String mediaMinimaImovelFinal = null;
			String mediaMinimaImovelFinalPesquisado = imovelOutrosCriteriosActionForm
					.getIntervaloMediaMinimaImovelFinal();

			if (mediaMinimaImovelFinalPesquisado != null
					&& !mediaMinimaImovelFinalPesquisado.equals("")) {
				mediaMinimaImovelFinal = mediaMinimaImovelFinalPesquisado;
			}

			// Intervalo Média Mínima Hidrômetro

			String mediaMinimahidrometroInicial = null;
			String mediaMinimahidrometroInicialPesquisado = imovelOutrosCriteriosActionForm
					.getIntervaloMediaMinimaHidrometroInicio();

			if (mediaMinimahidrometroInicialPesquisado != null
					&& !mediaMinimahidrometroInicialPesquisado.equals("")) {
				mediaMinimahidrometroInicial = mediaMinimahidrometroInicialPesquisado;
			}

			String mediaMinimahidrometroFinal = null;
			String mediaMinimahidrometroFinalPesquisado = imovelOutrosCriteriosActionForm
					.getIntervaloMediaMinimaHidrometroFinal();

			if (mediaMinimahidrometroFinalPesquisado != null
					&& !mediaMinimahidrometroFinalPesquisado.equals("")) {
				mediaMinimahidrometroFinal = mediaMinimahidrometroFinalPesquisado;
			}

			// Perfil do Imóvel

			String idImovelPerfil = (String) imovelOutrosCriteriosActionForm
					.getPerfilImovel();

			ImovelPerfil imovelPerfil = null;

			if (idImovelPerfil != null && !idImovelPerfil.equals("")) {
				FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();

				filtroImovelPerfil.adicionarParametro(new ParametroSimples(
						FiltroImovelPerfil.ID, idMedicaoTipo));
				filtroImovelPerfil.adicionarParametro(new ParametroSimples(
						FiltroImovelPerfil.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoImoveisPerfis = fachada.pesquisar(
						filtroImovelPerfil, ImovelPerfil.class.getName());

				if (colecaoImoveisPerfis != null
						&& !colecaoImoveisPerfis.isEmpty()) {
					// O Perfil do Imóvel foi encontrado
					Iterator imovelPerfilIterator = colecaoImoveisPerfis
							.iterator();

					imovelPerfil = (ImovelPerfil) imovelPerfilIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Perfil do Imóvel");
				}

			} else {
				imovelPerfil = new ImovelPerfil();
			}

			// Categoria

			String idCategoria = (String) imovelOutrosCriteriosActionForm
					.getCategoriaImovel();

			Categoria categoria = null;

			if (idCategoria != null && !idCategoria.equals("")) {
				FiltroCategoria filtroCategoria = new FiltroCategoria();

				filtroCategoria.adicionarParametro(new ParametroSimples(
						FiltroCategoria.CODIGO, idCategoria));
				filtroCategoria.adicionarParametro(new ParametroSimples(
						FiltroCategoria.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoCategorias = fachada.pesquisar(
						filtroCategoria, Categoria.class.getName());

				if (colecaoCategorias != null && !colecaoCategorias.isEmpty()) {
					// A categoria foi encontrado
					Iterator categoriaIterator = colecaoCategorias.iterator();

					categoria = (Categoria) categoriaIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "Categoria");
				}

			} else {
				categoria = new Categoria();
			}

			// SubCategoria

			String idSubCategoria = (String) imovelOutrosCriteriosActionForm
					.getSubcategoria();

			Subcategoria subcategoria = null;

			if (idSubCategoria != null && !idSubCategoria.equals("")) {
				FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();

				filtroSubCategoria.adicionarParametro(new ParametroSimples(
						FiltroSubCategoria.ID, idSubCategoria));
				filtroSubCategoria.adicionarParametro(new ParametroSimples(
						FiltroSubCategoria.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoSubCategorias = fachada.pesquisar(
						filtroSubCategoria, Subcategoria.class.getName());

				if (colecaoSubCategorias != null
						&& !colecaoSubCategorias.isEmpty()) {
					// A subcategoria foi encontrado
					Iterator subCategoriaIterator = colecaoSubCategorias
							.iterator();

					subcategoria = (Subcategoria) subCategoriaIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Subcategoria");
				}

			} else {
				subcategoria = new Subcategoria();
			}

			// Intervalo Qtde de economias

			Integer qtdeEconomiasInicial = null;
			String qtdeEconomiasInicialPesquisado = imovelOutrosCriteriosActionForm
					.getQuantidadeEconomiasInicial();

			if (qtdeEconomiasInicialPesquisado != null
					&& !qtdeEconomiasInicialPesquisado.equals("")) {
				qtdeEconomiasInicial = new Integer(
						qtdeEconomiasInicialPesquisado);
			}

			Integer qtdeEconomiasFinal = null;
			String qtdeEconomiasFinalPesquisado = imovelOutrosCriteriosActionForm
					.getQuantidadeEconomiasFinal();

			if (qtdeEconomiasFinalPesquisado != null
					&& !qtdeEconomiasFinalPesquisado.equals("")) {
				qtdeEconomiasFinal = new Integer(qtdeEconomiasFinalPesquisado);
			}

			// Intervalo Número de Pontos

			Short numeroPontosInicial = null;
			String numeroPontosInicialPesquisado = imovelOutrosCriteriosActionForm
					.getNumeroPontosInicial();

			if (numeroPontosInicialPesquisado != null
					&& !numeroPontosInicialPesquisado.equals("")) {
				numeroPontosInicial = new Short(numeroPontosInicialPesquisado);
			}

			Short numeroPontosFinal = null;
			String numeroPontosFinalPesquisado = imovelOutrosCriteriosActionForm
					.getNumeroPontosFinal();

			if (numeroPontosFinalPesquisado != null
					&& !numeroPontosFinalPesquisado.equals("")) {
				numeroPontosFinal = new Short(numeroPontosFinalPesquisado);
			}

			// Intervalo Número de Moradores

			Short numeroMoradoresInicial = null;
			String numeroMoradoresInicialPesquisado = imovelOutrosCriteriosActionForm
					.getNumeroMoradoresInicial();

			if (numeroMoradoresInicialPesquisado != null
					&& !numeroMoradoresInicialPesquisado.equals("")) {
				numeroMoradoresInicial = new Short(
						numeroMoradoresInicialPesquisado);
			}

			Short numeroMoradoresFinal = null;
			String numeroMoradoresFinalPesquisado = imovelOutrosCriteriosActionForm
					.getNumeroMoradoresFinal();

			if (numeroMoradoresFinalPesquisado != null
					&& !numeroMoradoresFinalPesquisado.equals("")) {
				numeroMoradoresFinal = new Short(numeroMoradoresFinalPesquisado);
			}

			// Intervalo Área Construída

			BigDecimal areaConstruidaInicial = null;
			String areaConstruidaInicialPesquisado = imovelOutrosCriteriosActionForm
					.getAreaConstruidaInicial();

			if (areaConstruidaInicialPesquisado != null
					&& !areaConstruidaInicialPesquisado.equals("")) {
				areaConstruidaInicial = new BigDecimal(
						areaConstruidaInicialPesquisado);
			}

			BigDecimal areaConstruidaFinal = null;
			String areaConstruidaFinalPesquisado = imovelOutrosCriteriosActionForm
					.getAreaConstruidaFinal();

			if (areaConstruidaFinalPesquisado != null
					&& !areaConstruidaFinalPesquisado.equals("")) {
				areaConstruidaFinal = new BigDecimal(areaConstruidaFinalPesquisado);
			}

			// Tipo de Poço

			String idPocoTipo = (String) imovelOutrosCriteriosActionForm
					.getTipoPoco();

			PocoTipo pocoTipo = null;

			if (idPocoTipo != null && !idPocoTipo.equals("")) {
				FiltroPocoTipo filtroPocoTipo = new FiltroPocoTipo();

				filtroPocoTipo.adicionarParametro(new ParametroSimples(
						FiltroPocoTipo.ID, idPocoTipo));
				filtroPocoTipo.adicionarParametro(new ParametroSimples(
						FiltroPocoTipo.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoPocosTipos = fachada.pesquisar(
						filtroPocoTipo, PocoTipo.class.getName());

				if (colecaoPocosTipos != null && !colecaoPocosTipos.isEmpty()) {
					// O Tipo do Poço foi encontrado
					Iterator pocoTipoIterator = colecaoPocosTipos.iterator();

					pocoTipo = (PocoTipo) pocoTipoIterator.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Tipo de Poço");
				}

			} else {
				pocoTipo = new PocoTipo();
			}

			// Tipo Situação Especial Faturamento

			String idTipoSituacaoFaturamento = (String) imovelOutrosCriteriosActionForm
					.getTipoSituacaoEspecialFaturamento();

			FaturamentoSituacaoTipo faturamentoSituacaoTipo = null;

			if (idTipoSituacaoFaturamento != null
					&& !idTipoSituacaoFaturamento.equals("")) {
				FiltroFaturamentoSituacaoTipo filtroFaturamentoSituacaoTipo = new FiltroFaturamentoSituacaoTipo();

				filtroFaturamentoSituacaoTipo
						.adicionarParametro(new ParametroSimples(
								FiltroFaturamentoSituacaoTipo.ID,
								idTipoSituacaoFaturamento));
				filtroFaturamentoSituacaoTipo
						.adicionarParametro(new ParametroSimples(
								FiltroFaturamentoSituacaoTipo.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoFaturamentosSituacoesTipo = fachada
						.pesquisar(filtroFaturamentoSituacaoTipo,
								FaturamentoSituacaoTipo.class.getName());

				if (colecaoFaturamentosSituacoesTipo != null
						&& !colecaoFaturamentosSituacoesTipo.isEmpty()) {
					// O Tipo do Faturamento Situacao Especial foi
					// encontrado
					Iterator faturamentoSituacaoTipoIterator = colecaoFaturamentosSituacoesTipo
							.iterator();

					faturamentoSituacaoTipo = (FaturamentoSituacaoTipo) faturamentoSituacaoTipoIterator
							.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Tipo do Faturamento Situacao Especial");
				}

			} else {
				faturamentoSituacaoTipo = new FaturamentoSituacaoTipo();
			}

			// Tipo Situação Especial Cobrança

			String idTipoSituacaoCobranca = (String) imovelOutrosCriteriosActionForm
					.getTipoSituacaoEspecialCobranca();

			CobrancaSituacaoTipo cobrancaSituacaoTipo = null;

			if (idTipoSituacaoCobranca != null
					&& !idTipoSituacaoCobranca.equals("")) {
				FiltroCobrancaSituacaoTipo filtroCobrancaSituacaoTipo = new FiltroCobrancaSituacaoTipo();

				filtroCobrancaSituacaoTipo
						.adicionarParametro(new ParametroSimples(
								FiltroCobrancaSituacaoTipo.ID,
								idTipoSituacaoCobranca));

				Collection colecaoCobrancasSituacoesTipo = fachada.pesquisar(
						filtroCobrancaSituacaoTipo, CobrancaSituacaoTipo.class
								.getName());

				if (colecaoCobrancasSituacoesTipo != null
						&& !colecaoCobrancasSituacoesTipo.isEmpty()) {
					// O Tipo da Cobrança Situacao Especial foi encontrado
					Iterator cobrancaSituacaoTipoIterator = colecaoCobrancasSituacoesTipo
							.iterator();

					cobrancaSituacaoTipo = (CobrancaSituacaoTipo) cobrancaSituacaoTipoIterator
							.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Tipo da Cobrança Situacao Especial");
				}

			} else {
				cobrancaSituacaoTipo = new CobrancaSituacaoTipo();
			}

			// Situação Cobrança

			String idSituacaoCobranca = (String) imovelOutrosCriteriosActionForm
					.getSituacaoCobranca();

			CobrancaSituacao cobrancaSituacao = null;

			if (idSituacaoCobranca != null && !idSituacaoCobranca.equals("")) {
				FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();

				filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(
						FiltroCobrancaSituacao.ID, idSituacaoCobranca));

				Collection colecaoCobrancasSituacoes = fachada.pesquisar(
						filtroCobrancaSituacao, CobrancaSituacao.class
								.getName());

				if (colecaoCobrancasSituacoes != null
						&& !colecaoCobrancasSituacoes.isEmpty()) {
					// A Cobrança Situacao foi encontrada
					Iterator cobrancaSituacaoIterator = colecaoCobrancasSituacoes
							.iterator();

					cobrancaSituacao = (CobrancaSituacao) cobrancaSituacaoIterator
							.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Cobrança Situacao");
				}

			} else {
				cobrancaSituacaoTipo = new CobrancaSituacaoTipo();
			}

			// Dia Vencimento Alternativo

			Short diaVencimentoAlternativo = null;
			String diaVencimentoAlternativoPesquisado = imovelOutrosCriteriosActionForm
					.getDiaVencimentoAlternativo();

			if (diaVencimentoAlternativoPesquisado != null
					&& !diaVencimentoAlternativoPesquisado.equals("")) {
				diaVencimentoAlternativo = new Short(
						diaVencimentoAlternativoPesquisado);
			}

			// Ocorrência de Cadastro

			String idCadastroOcorrencia = (String) imovelOutrosCriteriosActionForm
					.getOcorrenciaCadastro();

			CadastroOcorrencia cadastroOcorrencia = null;

			if (idCadastroOcorrencia != null
					&& !idCadastroOcorrencia.equals("")) {
				FiltroCadastroOcorrencia filtroCadastroOcorrencia = new FiltroCadastroOcorrencia();

				filtroCadastroOcorrencia
						.adicionarParametro(new ParametroSimples(
								FiltroCadastroOcorrencia.ID, idSituacaoCobranca));

				Collection colecaoCadastrosOcorrencias = fachada.pesquisar(
						filtroCadastroOcorrencia, CadastroOcorrencia.class
								.getName());

				if (colecaoCadastrosOcorrencias != null
						&& !colecaoCadastrosOcorrencias.isEmpty()) {
					// A Ocorrência de Cadastro foi encontrada
					Iterator cadastroOcorrenciaIterator = colecaoCadastrosOcorrencias
							.iterator();

					cadastroOcorrencia = (CadastroOcorrencia) cadastroOcorrenciaIterator
							.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Ocorrência de Cadastro");
				}

			} else {
				cadastroOcorrencia = new CadastroOcorrencia();
			}

			// Tarifa de Consumo

			String idTarifaConsumo = (String) imovelOutrosCriteriosActionForm
					.getTarifaConsumo();

			ConsumoTarifa consumoTarifa = null;

			if (idTarifaConsumo != null && !idTarifaConsumo.equals("")) {
				FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();

				filtroConsumoTarifa.adicionarParametro(new ParametroSimples(
						FiltroConsumoTarifa.ID, idTarifaConsumo));

				Collection colecaoConsumosTarifas = fachada.pesquisar(
						filtroConsumoTarifa, ConsumoTarifa.class.getName());

				if (colecaoConsumosTarifas != null
						&& !colecaoConsumosTarifas.isEmpty()) {
					// A Tarifa de Consumo foi encontrada
					Iterator consumoTarifaIterator = colecaoConsumosTarifas
							.iterator();

					consumoTarifa = (ConsumoTarifa) consumoTarifaIterator
							.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Tarifa de Consumo");
				}

			} else {
				consumoTarifa = new ConsumoTarifa();
			}

			// Anormalidade do Elo

			String idEloAnormalidade = (String) imovelOutrosCriteriosActionForm
					.getAnormalidadeElo();

			EloAnormalidade eloAnormalidade = null;

			if (idEloAnormalidade != null && !idEloAnormalidade.equals("")) {
				FiltroEloAnormalidade filtroEloAnormalidade = new FiltroEloAnormalidade();

				filtroEloAnormalidade.adicionarParametro(new ParametroSimples(
						FiltroEloAnormalidade.ID, idEloAnormalidade));

				Collection colecaoElosAnormalidades = fachada.pesquisar(
						filtroEloAnormalidade, EloAnormalidade.class.getName());

				if (colecaoElosAnormalidades != null
						&& !colecaoElosAnormalidades.isEmpty()) {
					// A Anormalidade do Elo foi encontrada
					Iterator eloAnormalidadeIterator = colecaoElosAnormalidades
							.iterator();

					eloAnormalidade = (EloAnormalidade) eloAnormalidadeIterator
							.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Anormalidade do Elo");
				}

			} else {
				eloAnormalidade = new EloAnormalidade();
			}
			
			// Indicador de Situação do Imóvel na Tarifa Social
			
			Short indicadorSituacaoImovelTarifaSocial = null;
			String indicadorSituacaoImovelTarifaSocialPesquisado = imovelOutrosCriteriosActionForm.getIndicadorImovelTarifaSocial();

			if (indicadorSituacaoImovelTarifaSocialPesquisado != null
					&& !indicadorSituacaoImovelTarifaSocialPesquisado.equals("")) {
				indicadorSituacaoImovelTarifaSocial = new Short(indicadorSituacaoImovelTarifaSocialPesquisado);
			}
			
			// Período de Implantação

            Date dataImplantacaoInicial = null;

            String dataImplantacaoInicialPesquisada = imovelOutrosCriteriosActionForm.getDataInicioImplantacao();

            if (dataImplantacaoInicialPesquisada != null
                    && !dataImplantacaoInicialPesquisada.equals("")) {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                try {
                	dataImplantacaoInicial = format.parse(dataImplantacaoInicialPesquisada);
                } catch (ParseException ex) {
                    throw new ActionServletException("erro.sistema");
                }

            }
			
            Date dataImplantacaoFinal = null;

            String dataImplantacaoFinalPesquisada = imovelOutrosCriteriosActionForm.getDataFimImplantacao();

            if (dataImplantacaoFinalPesquisada != null
                    && !dataImplantacaoFinalPesquisada.equals("")) {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                try {
                	dataImplantacaoFinal = format.parse(dataImplantacaoFinalPesquisada);
                } catch (ParseException ex) {
                    throw new ActionServletException("erro.sistema");
                }

            }
            
			// Período de Exclusão

            Date dataExclusaoInicial = null;

            String dataExclusaoInicialPesquisada = imovelOutrosCriteriosActionForm.getDataInicioExclusao();

            if (dataExclusaoInicialPesquisada != null
                    && !dataExclusaoInicialPesquisada.equals("")) {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                try {
                	dataExclusaoInicial = format.parse(dataExclusaoInicialPesquisada);
                } catch (ParseException ex) {
                    throw new ActionServletException("erro.sistema");
                }

            }
			
            Date dataExclusaoFinal = null;

            String dataExclusaoFinalPesquisada = imovelOutrosCriteriosActionForm.getDataFimExclusao();

            if (dataExclusaoFinalPesquisada != null
                    && !dataExclusaoFinalPesquisada.equals("")) {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                try {
                	dataExclusaoFinal = format.parse(dataExclusaoFinalPesquisada);
                } catch (ParseException ex) {
                    throw new ActionServletException("erro.sistema");
                }

            }
            
			// Motivo de Exclusão da Tarifa Social

			String idExclusaoMotivo = (String) imovelOutrosCriteriosActionForm
					.getTarifaSocialExclusaoMotivoId();

			TarifaSocialExclusaoMotivo tarifaSocialExclusaoMotivo = null;

			if (idExclusaoMotivo != null && !idExclusaoMotivo.equals("")) {
				FiltroTarifaSocialExclusaoMotivo filtroTarifaSocialExclusaoMotivo = new FiltroTarifaSocialExclusaoMotivo();

				filtroTarifaSocialExclusaoMotivo.adicionarParametro(new ParametroSimples(
						FiltroTarifaSocialExclusaoMotivo.ID, idExclusaoMotivo));

				Collection colecaoTarifasSociaisExclusoesMotivos = fachada.pesquisar(
						filtroTarifaSocialExclusaoMotivo, TarifaSocialExclusaoMotivo.class.getName());

				if (colecaoTarifasSociaisExclusoesMotivos != null
						&& !colecaoTarifasSociaisExclusoesMotivos.isEmpty()) {
					// O Motivo de Exclusão da Tarifa Social foi encontrado
					Iterator tarifaSocialExclusaoMotivoIterator = colecaoTarifasSociaisExclusoesMotivos
							.iterator();

					tarifaSocialExclusaoMotivo = (TarifaSocialExclusaoMotivo) tarifaSocialExclusaoMotivoIterator
							.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Motivo de Exclusão da Tarifa Social");
				}

			} else {
				tarifaSocialExclusaoMotivo = new TarifaSocialExclusaoMotivo();
			}
            
			// Perído de Validade do Cartão do Programa Social

            Date dataValidadeCartaoInicial = null;

            String dataValidadeCartaoInicialPesquisada = imovelOutrosCriteriosActionForm.getDataInicioValidadeCartao();

            if (dataValidadeCartaoInicialPesquisada != null
                    && !dataValidadeCartaoInicialPesquisada.equals("")) {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                try {
                	dataValidadeCartaoInicial = format.parse(dataValidadeCartaoInicialPesquisada);
                } catch (ParseException ex) {
                    throw new ActionServletException("erro.sistema");
                }

            }
			
            Date dataValidadeCartaoFinal = null;

            String dataValidadeCartaoFinalPesquisada = imovelOutrosCriteriosActionForm.getDataFimValidadeCartao();

            if (dataValidadeCartaoFinalPesquisada != null
                    && !dataValidadeCartaoFinalPesquisada.equals("")) {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                try {
                	dataValidadeCartaoFinal = format.parse(dataValidadeCartaoFinalPesquisada);
                } catch (ParseException ex) {
                    throw new ActionServletException("erro.sistema");
                }

            }
            
			// Intervalo de Número de Meses de Adesão

			Short numeroMesesAdesaoInicial = null;
			String numeroMesesAdesaoInicialPesquisado = imovelOutrosCriteriosActionForm.getMesInicioAdesao();

			if (numeroMesesAdesaoInicialPesquisado != null
					&& !numeroMesesAdesaoInicialPesquisado.equals("")) {
				numeroMesesAdesaoInicial = new Short(
						numeroMesesAdesaoInicialPesquisado);
			}

			Short numeroMesesAdesaoFinal = null;
			String numeroMesesAdesaoFinalPesquisado = imovelOutrosCriteriosActionForm.getMesFimAdesao();

			if (numeroMesesAdesaoFinalPesquisado != null
					&& !numeroMesesAdesaoFinalPesquisado.equals("")) {
				numeroMesesAdesaoFinal = new Short(numeroMesesAdesaoFinalPesquisado);
			}
			
			// Tipo do Cartão da Tarifa Social

			String idCartaoTipo = (String) imovelOutrosCriteriosActionForm
					.getTarifaSocialCartaoTipoId();

			TarifaSocialCartaoTipo tarifaSocialCartaoTipo = null;

			if (idCartaoTipo != null && !idCartaoTipo.equals("")) {
				FiltroTarifaSocialCartaoTipo filtroTarifaSocialCartaoTipo = new FiltroTarifaSocialCartaoTipo();

				filtroTarifaSocialCartaoTipo.adicionarParametro(new ParametroSimples(
						FiltroTarifaSocialCartaoTipo.ID, idCartaoTipo));

				Collection colecaoTarifasSociaisCartoesTipos = fachada.pesquisar(
						filtroTarifaSocialCartaoTipo, TarifaSocialCartaoTipo.class.getName());

				if (colecaoTarifasSociaisCartoesTipos != null
						&& !colecaoTarifasSociaisCartoesTipos.isEmpty()) {
					// O Motivo de Exclusão da Tarifa Social foi encontrado
					Iterator tarifaSocialCartaoTipoIterator = colecaoTarifasSociaisCartoesTipos
							.iterator();

					tarifaSocialCartaoTipo = (TarifaSocialCartaoTipo) tarifaSocialCartaoTipoIterator
							.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Motivo de Exclusão da Tarifa Social");
				}

			} else {
				tarifaSocialCartaoTipo = new TarifaSocialCartaoTipo();
			}
            
			// Tipo da Renda

			String idRendaTipo = (String) imovelOutrosCriteriosActionForm.getTarifaSocialRendaTipoId();

			RendaTipo rendaTipo = null;

			if (idRendaTipo != null && !idRendaTipo.equals("")) {
				FiltroRendaTipo filtroRendaTipo = new FiltroRendaTipo();

				filtroRendaTipo.adicionarParametro(new ParametroSimples(
						FiltroRendaTipo.ID, idRendaTipo));

				Collection colecaoRendasTipos = fachada.pesquisar(
						filtroRendaTipo, RendaTipo.class.getName());

				if (colecaoRendasTipos != null
						&& !colecaoRendasTipos.isEmpty()) {
					// O Motivo de Exclusão da Tarifa Social foi encontrado
					Iterator rendaTipoIterator = colecaoRendasTipos
							.iterator();

					rendaTipo = (RendaTipo) rendaTipoIterator
							.next();

				} else {
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Motivo de Exclusão da Tarifa Social");
				}

			} else {
				rendaTipo = new RendaTipo();
			}
			
			// Intervalo de Renda Familiar

			BigDecimal valorRendaFamiliarInicial = null;
			String valorRendaFamiliarInicialPesquisada = imovelOutrosCriteriosActionForm.getRendaInicial();

			if (valorRendaFamiliarInicialPesquisada != null
					&& !valorRendaFamiliarInicialPesquisada.equals("")) {
				valorRendaFamiliarInicial = new BigDecimal(
						valorRendaFamiliarInicialPesquisada);
			}

			BigDecimal valorRendaFamiliarFinal = null;
			String valorRendaFamiliarFinalPesquisada = imovelOutrosCriteriosActionForm.getRendaFinal();

			if (valorRendaFamiliarFinalPesquisada != null
					&& !valorRendaFamiliarFinalPesquisada.equals("")) {
				valorRendaFamiliarFinal = new BigDecimal(valorRendaFamiliarFinalPesquisada);
			}
			
			// Intervalo de Consumo Celpe

			String consumoCelpeInicial = null;
			String consumoCelpeInicialPesquisado = imovelOutrosCriteriosActionForm.getCelpeInicial();

			if (consumoCelpeInicialPesquisado != null
					&& !consumoCelpeInicialPesquisado.equals("")) {
				consumoCelpeInicial = consumoCelpeInicialPesquisado;
			}

			String consumoCelpeFinal = null;
			String consumoCelpeFinalPesquisado = imovelOutrosCriteriosActionForm.getCelpeFinal();

			if (consumoCelpeFinalPesquisado != null
					&& !consumoCelpeFinalPesquisado.equals("")) {
				consumoCelpeFinal = consumoCelpeFinalPesquisado;
			}
			
			// Perído de Validade do Cartão do Programa Social

            Date dataRecadastramentoInicial = null;

            String dataRecadastramentoInicialPesquisada = imovelOutrosCriteriosActionForm.getDataInicioRecadastramento();

            if (dataRecadastramentoInicialPesquisada != null
                    && !dataRecadastramentoInicialPesquisada.equals("")) {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                try {
                	dataRecadastramentoInicial = format.parse(dataRecadastramentoInicialPesquisada);
                } catch (ParseException ex) {
                    throw new ActionServletException("erro.sistema");
                }

            }
			
            Date dataRecadastramentoFinal = null;

            String dataRecadastramentoFinalPesquisada = imovelOutrosCriteriosActionForm.getDataFimRecadastramento();

            if (dataRecadastramentoFinalPesquisada != null
                    && !dataRecadastramentoFinalPesquisada.equals("")) {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                try {
                	dataRecadastramentoFinal = format.parse(dataRecadastramentoFinalPesquisada);
                } catch (ParseException ex) {
                    throw new ActionServletException("erro.sistema");
                }

            }
			
			// Intervalo de Renda Familiar

			Short numeroRecadastramentosInicial = null;
			String numeroRecadastramentosInicialPesquisado = imovelOutrosCriteriosActionForm.getRecadastramentoNumeroInicial();
			
			if (numeroRecadastramentosInicialPesquisado != null
					&& !numeroRecadastramentosInicialPesquisado.equals("")) {
				numeroRecadastramentosInicial = new Short(
						numeroRecadastramentosInicialPesquisado);
			}

			Short numeroRecadastramentosFinal = null;
			String numeroRecadastramentosFinalPesquisado = imovelOutrosCriteriosActionForm.getRecadastramentoNumeroFinal();

			if (numeroRecadastramentosFinalPesquisado != null
					&& !numeroRecadastramentosFinalPesquisado.equals("")) {
				numeroRecadastramentosFinal = new Short(numeroRecadastramentosFinalPesquisado);
			}
			
			// Limpa a sessão
			sessao.removeAttribute("inserirImovelActionForm");

			// seta os parametros que serão mostrados no relatório

			clienteImovelParametros.setCliente(cliente);
			clienteImovelParametros.setClienteRelacaoTipo(clienteRelacaoTipo);
			ligacaoAguaParametrosInicial
					.setNumeroConsumoMinimoAgua(consumoFixadoAguaInicial == null ? null
							: new Integer(consumoFixadoAguaInicial));
			ligacaoAguaParametrosFinal
					.setNumeroConsumoMinimoAgua(consumoFixadoAguaFinal == null ? null
							: new Integer(consumoFixadoAguaFinal));
			ligacaoEsgotoParametrosInicial
					.setConsumoMinimo(consumoFixadoEsgotoInicial == null ? null
							: new Integer(consumoFixadoEsgotoInicial));
			ligacaoEsgotoParametrosInicial
					.setPercentual(percentualEsgotoInicial == null ? null
							: new BigDecimal(percentualEsgotoInicial));
			ligacaoEsgotoParametrosFinal
					.setConsumoMinimo(consumoFixadoEsgotoFinal == null ? null
							: new Integer(consumoFixadoEsgotoFinal));
			ligacaoEsgotoParametrosFinal
					.setPercentual(percentualEsgotoFinal == null ? null
							: new BigDecimal(percentualEsgotoFinal));
			consumoHistoricoParametrosInicial
					.setConsumoMedio(mediaMinimaImovelInicial == null ? null
							: new Integer(mediaMinimaImovelInicial));
			consumoHistoricoParametrosFinal
					.setConsumoMedio(mediaMinimaImovelFinal == null ? null
							: new Integer(mediaMinimaImovelFinal));
			medicaoHistoricoParametrosInicial
					.setConsumoMedioHidrometro(mediaMinimahidrometroInicial == null ? null
							: new Integer(mediaMinimahidrometroInicial));
			medicaoHistoricoParametrosInicial.setMedicaoTipo(medicaoTipo);
			medicaoHistoricoParametrosFinal
					.setConsumoMedioHidrometro(mediaMinimahidrometroFinal == null ? null
							: new Integer(mediaMinimahidrometroFinal));
			imovelParametrosInicial.setLocalidade(localidadeOrigem);
			imovelParametrosInicial.setSetorComercial(setorComercialOrigem);
			imovelParametrosInicial.setQuadra(quadraOrigem);
			imovelParametrosInicial.setLote(loteOrigem == null ? 0 : new Short(
					loteOrigem));
			
			LogradouroCep logradouroCep = fachada.pesquisarAssociacaoLogradouroCep(cep.getCepId(),
			logradouro.getId());
					
			imovelParametrosInicial.setLogradouroCep(logradouroCep);
			imovelParametrosInicial.setImovelCondominio(imovelCondominio);
			imovelParametrosInicial.setImovelPrincipal(imovelPrincipal);
			imovelParametrosInicial.setLigacaoAguaSituacao(ligacaoAguaSituacao);
			imovelParametrosInicial
					.setLigacaoEsgotoSituacao(ligacaoEsgotoSituacao);
			imovelParametrosInicial.setConsumoTarifa(consumoTarifa);
			imovelParametrosInicial
					.setFaturamentoSituacaoTipo(faturamentoSituacaoTipo);
			imovelParametrosInicial
					.setCobrancaSituacaoTipo(cobrancaSituacaoTipo);
			imovelParametrosInicial.setDiaVencimento(diaVencimentoAlternativo);
			imovelParametrosInicial.setEloAnormalidade(eloAnormalidade);
			imovelParametrosInicial.setCadastroOcorrencia(cadastroOcorrencia);
			imovelParametrosInicial.setImovelPerfil(imovelPerfil);
			imovelParametrosInicial.setPocoTipo(pocoTipo);
//			imovelParametrosInicial.setNomeConta(nomeConta);
			imovelParametrosInicial
					.setNumeroPontosUtilizacao(numeroPontosInicial == null ? 0
							: numeroPontosInicial);
			imovelParametrosInicial
					.setNumeroMorador(numeroMoradoresInicial == null ? 0
							: numeroMoradoresInicial);
			imovelParametrosInicial
					.setAreaConstruida(areaConstruidaInicial == null ? new BigDecimal(0)
							: areaConstruidaInicial);
			imovelParametrosInicial
					.setQuantidadeEconomias(qtdeEconomiasInicial.shortValue());
			imovelParametrosInicial
					.setLigacaoEsgoto(ligacaoEsgotoParametrosInicial);
			imovelParametrosInicial
					.setLigacaoAgua(ligacaoAguaParametrosInicial);
			imovelParametrosFinal.setLocalidade(localidadeDestino);
			imovelParametrosFinal.setSetorComercial(setorComercialDestino);
			imovelParametrosFinal.setQuadra(quadraDestino);
			imovelParametrosFinal.setLote(loteDestino == null ? 0 : new Short(
					loteDestino));
			imovelParametrosFinal
					.setNumeroPontosUtilizacao(numeroPontosFinal == null ? 0
							: numeroPontosFinal);
			imovelParametrosFinal
					.setNumeroMorador(numeroMoradoresFinal == null ? 0
							: numeroMoradoresFinal);
			imovelParametrosFinal
					.setAreaConstruida(areaConstruidaFinal == null ? new BigDecimal(0)
							: areaConstruidaFinal);
			imovelParametrosFinal.setQuantidadeEconomias(qtdeEconomiasFinal.shortValue());
			imovelParametrosFinal.setLigacaoAgua(ligacaoAguaParametrosFinal);
			imovelParametrosFinal
					.setLigacaoEsgoto(ligacaoEsgotoParametrosFinal);
			tarifaSocialDadoInicial.setDataImplantacao(dataImplantacaoInicial);
			tarifaSocialDadoInicial.setDataExclusao(dataExclusaoInicial);
			tarifaSocialDadoInicial.setTarifaSocialExclusaoMotivo(tarifaSocialExclusaoMotivo);
			tarifaSocialDadoInicial.setQuantidadeRecadastramento(numeroRecadastramentosInicial);
			tarifaSocialDadoInicial.setDataRecadastramento(dataRecadastramentoInicial);
			tarifaSocialDadoFinal.setDataImplantacao(dataImplantacaoFinal);
			tarifaSocialDadoFinal.setDataExclusao(dataExclusaoFinal);
			tarifaSocialDadoFinal.setQuantidadeRecadastramento(numeroRecadastramentosFinal);
			tarifaSocialDadoFinal.setDataRecadastramento(dataRecadastramentoFinal);
			tarifaSocialDadoEconomiaInicial.setDataValidadeCartao(dataValidadeCartaoInicial);
			tarifaSocialDadoEconomiaInicial.setNumeroMesesAdesao(numeroMesesAdesaoInicial == null? 0: numeroMesesAdesaoInicial);
			tarifaSocialDadoEconomiaInicial.setTarifaSocialCartaoTipo(tarifaSocialCartaoTipo);
			tarifaSocialDadoEconomiaInicial.setRendaTipo(rendaTipo);
			tarifaSocialDadoEconomiaInicial.setValorRendaFamiliar(valorRendaFamiliarInicial);
			tarifaSocialDadoEconomiaInicial.setConsumoCelpe(consumoCelpeInicial == null? null: new Integer(consumoCelpeInicial));
			tarifaSocialDadoEconomiaInicial.setTarifaSocialDado(tarifaSocialDadoInicial);
			tarifaSocialDadoEconomiaFinal.setDataValidadeCartao(dataValidadeCartaoFinal);
			tarifaSocialDadoEconomiaFinal.setNumeroMesesAdesao(numeroMesesAdesaoFinal == null? 0: numeroMesesAdesaoFinal);
			tarifaSocialDadoEconomiaFinal.setValorRendaFamiliar(valorRendaFamiliarFinal);
			tarifaSocialDadoEconomiaFinal.setConsumoCelpe(consumoCelpeFinal == null? null: new Integer(consumoCelpeFinal));
			tarifaSocialDadoEconomiaFinal.setTarifaSocialDado(tarifaSocialDadoFinal);
			
			
			// cria uma instância da classe do relatório
			RelatorioDadosTarifaSocial relatorioDadosTarifaSocial = new RelatorioDadosTarifaSocial((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			
			relatorioDadosTarifaSocial.addParametro(
					"imoveisRelatoriosHelper", imoveisRelatoriosHelper);
			relatorioDadosTarifaSocial.addParametro(
					"imovelParametrosInicial", imovelParametrosInicial);
			relatorioDadosTarifaSocial.addParametro("imovelParametrosFinal",
					imovelParametrosFinal);
			relatorioDadosTarifaSocial.addParametro(
					"clienteImovelParametros", clienteImovelParametros);
			relatorioDadosTarifaSocial.addParametro("municipio", municipio);
			relatorioDadosTarifaSocial.addParametro("bairro", bairro);
			relatorioDadosTarifaSocial.addParametro(
					"medicaoHistoricoParametrosInicial",
					medicaoHistoricoParametrosInicial);
			relatorioDadosTarifaSocial.addParametro(
					"medicaoHistoricoParametrosFinal",
					medicaoHistoricoParametrosFinal);
			relatorioDadosTarifaSocial.addParametro(
					"consumoHistoricoParametrosInicial",
					consumoHistoricoParametrosInicial);
			relatorioDadosTarifaSocial.addParametro(
					"consumoHistoricoParametrosFinal",
					consumoHistoricoParametrosFinal);
			relatorioDadosTarifaSocial.addParametro("gerenciaRegional",
					gerenciaRegional);
			relatorioDadosTarifaSocial.addParametro("categoria", categoria);
			relatorioDadosTarifaSocial.addParametro("subcategoria",
					subcategoria);
			relatorioDadosTarifaSocial.addParametro("cobrancaSituacao",
					cobrancaSituacao);
			relatorioDadosTarifaSocial.addParametro("indicadorMedicao",
					indicadorMedicao);
			relatorioDadosTarifaSocial.addParametro("indicadorSituacaoImovelTarifaSocial",
					indicadorSituacaoImovelTarifaSocial);
			relatorioDadosTarifaSocial.addParametro("tarifaSocialDadoEconomiaInicial",
					tarifaSocialDadoEconomiaInicial);
			relatorioDadosTarifaSocial.addParametro("tarifaSocialDadoEconomiaFinal",
					tarifaSocialDadoEconomiaFinal);
			
			String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorioDadosTarifaSocial.addParametro("tipoFormatoRelatorio", Integer
					.parseInt(tipoRelatorio));

			try {
				retorno = processarExibicaoRelatorio(relatorioDadosTarifaSocial,
						tipoRelatorio, httpServletRequest, httpServletResponse,
						actionMapping);

			} catch (RelatorioVazioException ex) {
				// manda o erro para a página no request atual
				reportarErros(httpServletRequest, "atencao.relatorio.vazio");

				// seta o mapeamento de retorno para a tela de atenção de popup
				retorno = actionMapping.findForward("telaAtencaoPopup");

			}
*/
			return retorno;
		}
	}



