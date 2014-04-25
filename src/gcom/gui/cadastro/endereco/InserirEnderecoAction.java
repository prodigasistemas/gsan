package gcom.gui.cadastro.endereco;

import gcom.arrecadacao.banco.Agencia;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.endereco.EnderecoTipo;
import gcom.cadastro.endereco.FiltroCep;
import gcom.cadastro.endereco.FiltroEnderecoReferencia;
import gcom.cadastro.endereco.FiltroEnderecoTipo;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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
import org.apache.struts.validator.DynaValidatorForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author rodrigo
 */
public class InserirEnderecoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("inserirEndereco");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
      //  Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		String tipoRetorno = (String) sessao.getAttribute("tipoPesquisaRetorno");
		String tipoOperacao = (String) sessao.getAttribute("operacao");

		if (tipoRetorno != null && !tipoRetorno.trim().equalsIgnoreCase("")) {

			InserirEnderecoActionForm inserirEnderecoActionForm = (InserirEnderecoActionForm) actionForm;

			String enderecoTipoJSP = inserirEnderecoActionForm.getTipo();
			String cepJSP = inserirEnderecoActionForm.getCepSelecionado();
			String logradouroJSP = inserirEnderecoActionForm.getLogradouro();
			String bairroJSP = inserirEnderecoActionForm.getBairro();
			String enderecoReferenciaJSP = inserirEnderecoActionForm
					.getEnderecoReferencia();
			String numeroJSP = inserirEnderecoActionForm.getNumero();
			String complementoJSP = inserirEnderecoActionForm.getComplemento();
			String tipoAcao = inserirEnderecoActionForm.getTipoAcao();
			String idPerimetroInicial = inserirEnderecoActionForm.getIdPerimetroInicial();
			String idPerimetroFinal = inserirEnderecoActionForm.getIdPerimetroFinal();

			Imovel imovel = new Imovel();
			ClienteEndereco clienteEndereco = new ClienteEndereco();
			Localidade localidade = new Localidade();
			GerenciaRegional gerenciaRegional = new GerenciaRegional();
			Agencia agencia = new Agencia();

			LogradouroCep logradouroCep = new LogradouroCep();
			LogradouroBairro logradouroBairro = new LogradouroBairro();
			
			Quadra quadra = new Quadra();
			
			if(sessao.getAttribute("quadraCaracteristicas") != null){
				quadra = (Quadra) sessao.getAttribute("quadraCaracteristicas");
				imovel.setQuadra(quadra);
			}

			// == Cep ================================================
			if (cepJSP != null && !cepJSP.trim().equalsIgnoreCase("")) {
				Collection colecaoCep = null;
				FiltroCep filtroCep = new FiltroCep();

				filtroCep.adicionarParametro(new ParametroSimples(
						FiltroCep.CEPID, cepJSP));
				filtroCep.adicionarParametro(new ParametroSimples(
						FiltroCep.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				colecaoCep = fachada.pesquisar(filtroCep, Cep.class.getName());

				if (colecaoCep == null || colecaoCep.isEmpty()) {

					throw new ActionServletException(
							"atencao.pesquisa.cep_invalido");

				} else {
					Cep cep = (Cep) Util.retonarObjetoDeColecao(colecaoCep);

					// Adiciona o cep ao objeto final
					logradouroCep.setCep(cep);
				}
			}
			// =======================================================

			// == Logradouro =========================================
			if (logradouroJSP != null
					&& !logradouroJSP.trim().equalsIgnoreCase("")) {

				Collection colecaoLogradouro = null;
				FiltroLogradouro filtroLogradouro = new FiltroLogradouro();

				// Objetos que serão retornados pelo hibernate.
				filtroLogradouro
						.adicionarCaminhoParaCarregamentoEntidade("logradouroTipo");
				filtroLogradouro
						.adicionarCaminhoParaCarregamentoEntidade("logradouroTitulo");

				filtroLogradouro.adicionarParametro(new ParametroSimples(
						FiltroLogradouro.ID, logradouroJSP));

				filtroLogradouro.adicionarParametro(new ParametroSimples(
						FiltroLogradouro.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				colecaoLogradouro = fachada.pesquisar(filtroLogradouro,
						Logradouro.class.getName());

				if (colecaoLogradouro == null || colecaoLogradouro.isEmpty()) {
					// Nenhum logradouro foi encontrado
					throw new ActionServletException(
							"atencao.pesquisa.logradouro_inexistente");
				} else {
					Logradouro logradouro = (Logradouro) Util
							.retonarObjetoDeColecao(colecaoLogradouro);

					// Adiciona o logradouro ao objeto final
					logradouroCep.setLogradouro(logradouro);
				}

				// ======================================================

				// == Bairro ============================================
				if (bairroJSP != null
						&& !bairroJSP.equalsIgnoreCase("")
						&& !bairroJSP
								.trim()
								.equalsIgnoreCase(
										String
												.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

					Collection colecaoBairro = null;
					FiltroBairro filtroBairro = new FiltroBairro();

					// Objetos que serão retornados pelo hibernate.
					filtroBairro
							.adicionarCaminhoParaCarregamentoEntidade("municipio.unidadeFederacao");

					filtroBairro.adicionarParametro(new ParametroSimples(
							FiltroBairro.ID, bairroJSP));

					filtroBairro.adicionarParametro(new ParametroSimples(
							FiltroBairro.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

					colecaoBairro = fachada.pesquisar(filtroBairro,
							Bairro.class.getName());

					if (colecaoBairro == null || colecaoBairro.isEmpty()) {
						// Nenhum bairro foi encontrado
						throw new ActionServletException(
								"atencao.pesquisa.bairro_inexistente");
					} else {
						Bairro bairro = (Bairro) Util
								.retonarObjetoDeColecao(colecaoBairro);

						// Adiciona o bairro ao objeto final
						logradouroBairro.setBairro(bairro);
					}
				}
				// ======================================================
			}
			// ========================================================
			
			if (idPerimetroInicial != null && !idPerimetroInicial.trim().equals("")) {
				Logradouro perimetroInicial = pesquisarLogradouroDigitado(idPerimetroInicial);
				
				if (perimetroInicial != null) {
					imovel.setPerimetroInicial(perimetroInicial);
					clienteEndereco.setPerimetroInicial(perimetroInicial);
				} else {
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Perímetro Inicial");
				}
			}
			
			if (idPerimetroFinal != null && !idPerimetroFinal.trim().equals("")) {
				Logradouro perimetroFinal = pesquisarLogradouroDigitado(idPerimetroFinal);
				
				if (perimetroFinal != null) {
					imovel.setPerimetroFinal(perimetroFinal);
					clienteEndereco.setPerimetroFinal(perimetroFinal);
				} else {
					throw new ActionServletException("atencao.pesquisa_inexistente", null, "Perímetro Final");
				}
			}

			// == Endereco Referencia ==========================
			if (enderecoReferenciaJSP != null
					&& !enderecoReferenciaJSP
							.trim()
							.equalsIgnoreCase(
									String
											.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

				Collection colecaoEnderecoReferencia = null;

				FiltroEnderecoReferencia filtroEnderecoReferencia = new FiltroEnderecoReferencia();

				filtroEnderecoReferencia
						.adicionarParametro(new ParametroSimples(
								FiltroEnderecoReferencia.ID,
								enderecoReferenciaJSP));

				filtroEnderecoReferencia
						.adicionarParametro(new ParametroSimples(
								FiltroEnderecoReferencia.INDICADORUSO,
								ConstantesSistema.INDICADOR_USO_ATIVO));

				colecaoEnderecoReferencia = fachada.pesquisar(
						filtroEnderecoReferencia, EnderecoReferencia.class
								.getName());

				if (colecaoEnderecoReferencia == null
						|| colecaoEnderecoReferencia.isEmpty()) {
					// Nenhum EnderecoReferencia foi encontrado
					throw new ActionServletException(
							"atencao.pesquisa.endereco_referencia_inexistente");
				} else {
					EnderecoReferencia enderecoReferencia = (EnderecoReferencia) Util
							.retonarObjetoDeColecao(colecaoEnderecoReferencia);

					// Adiciona o EnderecoReferencia ao objeto final
					clienteEndereco.setEnderecoReferencia(enderecoReferencia);
					imovel.setEnderecoReferencia(enderecoReferencia);
					localidade.setEnderecoReferencia(enderecoReferencia);
					gerenciaRegional.setEnderecoReferencia(enderecoReferencia);
					agencia.setEnderecoReferencia(enderecoReferencia);
				}
			}
			// ========================================================

			// == Endereco Tipo =======================================
			if (tipoRetorno.trim().equalsIgnoreCase("cliente")) {
				if (enderecoTipoJSP != null
						&& !enderecoTipoJSP
								.trim()
								.equalsIgnoreCase(
										String
												.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

					Collection colecaoEnderecoTipo = null;

					FiltroEnderecoTipo filtroEnderecoTipo = new FiltroEnderecoTipo();

					filtroEnderecoTipo.adicionarParametro(new ParametroSimples(
							FiltroEnderecoTipo.ID, enderecoTipoJSP));

					filtroEnderecoTipo.adicionarParametro(new ParametroSimples(
							FiltroEnderecoTipo.INDICADORUSO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

					colecaoEnderecoTipo = fachada.pesquisar(filtroEnderecoTipo,
							EnderecoTipo.class.getName());

					if (colecaoEnderecoTipo == null
							|| colecaoEnderecoTipo.isEmpty()) {
						// Nenhum EnderecoTipo foi encontrado
						throw new ActionServletException(
								"atencao.pesquisa.endereco_tipo_inexistente");
					} else {
						EnderecoTipo enderecoTipo = (EnderecoTipo) Util
								.retonarObjetoDeColecao(colecaoEnderecoTipo);

						// Adiciona o EnderecoTipo ao objeto final
						clienteEndereco.setEnderecoTipo(enderecoTipo);
					}
				}
			}
			// ======================================================

			// == Número ============================================
			if (numeroJSP != null && !numeroJSP.trim().equalsIgnoreCase("")) {

				// Adiciona o numero ao objeto final
				clienteEndereco.setNumero(numeroJSP);
				imovel.setNumeroImovel(numeroJSP);
				localidade.setNumeroImovel(numeroJSP);
				gerenciaRegional.setNumeroImovel(numeroJSP);
				agencia.setNumeroImovel(numeroJSP);
			}
			// ======================================================

			// == Complemento ======================================
			if (complementoJSP != null
					&& !complementoJSP.trim().equalsIgnoreCase("")) {

				// Adiciona o complemento ao objeto final
				clienteEndereco.setComplemento(complementoJSP);
				imovel.setComplementoEndereco(complementoJSP);
				localidade.setComplementoEndereco(complementoJSP);
				gerenciaRegional.setComplementoEndereco(complementoJSP);
				agencia.setComplementoEndereco(complementoJSP);
			}
			// ======================================================

			// Adiciona o indicador para nao receber correspondência ao objeto
			// final
			clienteEndereco
					.setIndicadorEnderecoCorrespondencia(ConstantesSistema.INDICADOR_NAO_ENDERECO_CORRESPONDENCIA);

			// Adiciona a data da última alteração ao objeto final
			imovel.setUltimaAlteracao(new Date());
			localidade.setUltimaAlteracao(new Date());
			clienteEndereco.setUltimaAlteracao(new Date());
			gerenciaRegional.setUltimaAlteracao(new Date());
			agencia.setUltimaAlteracao(new Date());

			// Carregando os objetos finais com o LogradouroCep e
			// LogradouroBairro
			logradouroCep
					.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

			Integer idLogradouroCep = fachada
					.inserirAssociacaoLogradouroCep(logradouroCep);
			logradouroCep.setId(idLogradouroCep);

			logradouroBairro = fachada.pesquisarAssociacaoLogradouroBairro(
					logradouroBairro.getBairro().getId(), logradouroCep
							.getLogradouro().getId());

			imovel.setLogradouroCep(logradouroCep);
			imovel.setLogradouroBairro(logradouroBairro);

			localidade.setLogradouroCep(logradouroCep);
			localidade.setLogradouroBairro(logradouroBairro);
			
			gerenciaRegional.setLogradouroCep(logradouroCep);
			gerenciaRegional.setLogradouroBairro(logradouroBairro);
			
			agencia.setLogradouroCep(logradouroCep);
			agencia.setLogradouroBairro(logradouroBairro);

			clienteEndereco.setLogradouroCep(logradouroCep);
			clienteEndereco.setLogradouroBairro(logradouroBairro);
			// ======================================================

			Collection enderecos = null;
			
			/*
			 * Especialmente para o retorno do caso de uso de RA, foi criado um retorno diferente
			 */
			if (tipoRetorno.trim().equalsIgnoreCase("registroAtendimento")){
				
				//Aba Nº 02 - Endereço do Imóvel
				if (tipoOperacao.equalsIgnoreCase("1")){
					
					if (sessao.getAttribute("colecaoEnderecos") == null){
						enderecos = new ArrayList();
						enderecos.add(imovel);
						sessao.setAttribute("colecaoEnderecos", enderecos);
					}
					else{
						
						enderecos = (Collection) sessao
						.getAttribute("colecaoEnderecos");
						
						if (enderecos.isEmpty() || (tipoAcao != null && !tipoAcao.equals(""))) {
							enderecos.clear();
							enderecos.add(imovel);
						}
					}
					
					httpServletRequest.setAttribute("fecharPopup", "ok");
				}
				//Aba Nº 03 - Endereço do Solicitante
				else if (tipoOperacao.equalsIgnoreCase("2")){
					
					if (sessao.getAttribute("colecaoEnderecosAbaSolicitante") == null){
						enderecos = new ArrayList();
						enderecos.add(clienteEndereco);
						sessao.setAttribute("colecaoEnderecosAbaSolicitante", enderecos);
					}
					else{
						
						enderecos = (Collection) sessao
						.getAttribute("colecaoEnderecosAbaSolicitante");
						
						if (enderecos.isEmpty() || (tipoAcao != null && !tipoAcao.equals(""))) {
							enderecos.clear();
							enderecos.add(clienteEndereco);
						}
					}
					
					httpServletRequest.setAttribute("fecharPopup", "ok");
				}
				//Aba Nº 02 - Endereço do Imóvel (Atualizar)
				else if (tipoOperacao.equalsIgnoreCase("3")){
					
					if (sessao.getAttribute("colecaoEnderecos") == null){
						enderecos = new ArrayList();
						enderecos.add(imovel);
						sessao.setAttribute("colecaoEnderecos", enderecos);
					}
					else{
						
						enderecos = (Collection) sessao
						.getAttribute("colecaoEnderecos");
						
						if (enderecos.isEmpty() || (tipoAcao != null && !tipoAcao.equals(""))) {
							enderecos.clear();
							enderecos.add(imovel);
						}
					}
					
					httpServletRequest.setAttribute("fecharPopup", "ok");
				}
				//POPUP - Endereço do Solicitante
				else if (tipoOperacao.equalsIgnoreCase("4")){
					
					if (sessao.getAttribute("colecaoEnderecosSolicitante") == null){
						enderecos = new ArrayList();
						enderecos.add(clienteEndereco);
						sessao.setAttribute("colecaoEnderecosSolicitante", enderecos);
					}
					else{
						
						enderecos = (Collection) sessao
						.getAttribute("colecaoEnderecosSolicitante");
						
						if (enderecos.isEmpty() || (tipoAcao != null && !tipoAcao.equals(""))) {
							enderecos.clear();
							enderecos.add(clienteEndereco);
						}
					}
				}
				//POPUP - Endereço do Solicitante da reiteração da RA
				else if (tipoOperacao.equalsIgnoreCase("5")){
					
					if (sessao.getAttribute("colecaoEnderecos") == null){
						enderecos = new ArrayList();
						enderecos.add(clienteEndereco);
						sessao.setAttribute("colecaoEnderecos", enderecos);
					}
					else{
						
						enderecos = (Collection) sessao
						.getAttribute("colecaoEnderecos");
						
						if (enderecos.isEmpty() || (tipoAcao != null && !tipoAcao.equals(""))) {
							enderecos.clear();
							enderecos.add(clienteEndereco);
						}
					}
					httpServletRequest.setAttribute("fecharPopup", "ok");
				}
				
				httpServletRequest.setAttribute("flagRedirect", "registroAtendimento");
				
			}
			
			//Continuação da forma padrão do retorno de um endereço
			else{
				
				if (sessao.getAttribute("colecaoEnderecos") == null) {
					enderecos = new ArrayList();

					if (tipoRetorno.trim().equalsIgnoreCase("cliente")) {
						enderecos.add(clienteEndereco);
						httpServletRequest.setAttribute("flagRedirect", "cliente");
					} else if (tipoRetorno.trim().equalsIgnoreCase("imovel")) {
						enderecos.add(imovel);
						httpServletRequest.setAttribute("flagRedirect", "imovel");
						httpServletRequest.setAttribute("fecharPopup", "ok");
					} else if (tipoRetorno.trim().equalsIgnoreCase("localidade")) {
						enderecos.add(localidade);
						httpServletRequest.setAttribute("flagRedirect",
								"localidade");
						httpServletRequest.setAttribute("fecharPopup", "ok");
					} else if (tipoRetorno.trim().equalsIgnoreCase("gerenciaRegional")) {
						enderecos.add(gerenciaRegional);
						httpServletRequest.setAttribute("flagRedirect",
								"gerenciaRegional");
						httpServletRequest.setAttribute("fecharPopup", "ok");
					} else if (tipoRetorno.trim().equalsIgnoreCase("agencia")) {
						enderecos.add(agencia);
						httpServletRequest.setAttribute("flagRedirect",
								"agencia");
						httpServletRequest.setAttribute("fecharPopup", "ok");
					} else if (tipoRetorno.trim().equalsIgnoreCase("sistemaParametro")) {
						enderecos.add(imovel);
						httpServletRequest.setAttribute("flagRedirect","sistemaParametro");
					}

					sessao.setAttribute("colecaoEnderecos", enderecos);

				} else {
					enderecos = (Collection) sessao
							.getAttribute("colecaoEnderecos");

					if (tipoRetorno.trim().equalsIgnoreCase("cliente")) {

						if (tipoAcao != null && !tipoAcao.equals("")) {

							Iterator iteratorEnderecos = enderecos.iterator();
							ClienteEndereco clienteEnderecoNaColecao = null;

							while (iteratorEnderecos.hasNext()) {
								clienteEnderecoNaColecao = (ClienteEndereco) iteratorEnderecos
										.next();
								
								// Verificando se o endereço já existe na coleção
								// Roberta Costa - 31/07/2006 
								if ( clienteEnderecoNaColecao.getEnderecoTipo().getId()
										.equals(clienteEndereco.getEnderecoTipo().getId())
									&& clienteEnderecoNaColecao.getLogradouroCep()
										.equals(clienteEndereco.getLogradouroCep())
									&& clienteEnderecoNaColecao.getLogradouroBairro()
										.equals(clienteEndereco.getLogradouroBairro())
									&& clienteEnderecoNaColecao.getEnderecoReferencia().getId()
										.equals(clienteEndereco.getEnderecoReferencia().getId())
									&& clienteEnderecoNaColecao.getNumero()
										.equals(clienteEndereco.getNumero()) 
									&& ( clienteEnderecoNaColecao.getComplemento() == null
											&& clienteEndereco.getComplemento() == null
												|| (clienteEnderecoNaColecao.getComplemento() != null
														&& clienteEndereco.getComplemento() != null
															&& clienteEnderecoNaColecao.getComplemento()
																.equals(clienteEndereco.getComplemento())))
									&& ( (clienteEnderecoNaColecao.getPerimetroInicial() == null && clienteEndereco.getPerimetroInicial() == null)
										  || (clienteEnderecoNaColecao.getPerimetroInicial() != null && clienteEndereco.getPerimetroInicial() != null 
												 && clienteEnderecoNaColecao.getPerimetroInicial().getId().equals(clienteEndereco.getPerimetroInicial().getId())))
									&& ( (clienteEnderecoNaColecao.getPerimetroFinal() == null && clienteEndereco.getPerimetroFinal() == null)
										  || (clienteEnderecoNaColecao.getPerimetroFinal() != null && clienteEndereco.getPerimetroFinal() != null 
												 && clienteEnderecoNaColecao.getPerimetroFinal().getId().equals(clienteEndereco.getPerimetroFinal().getId())))
									){		 
									
									throw new ActionServletException(
										"atencao.endereco_ja_informado");
								}

								if (obterTimestampIdObjeto(clienteEnderecoNaColecao) == Long
										.parseLong(inserirEnderecoActionForm
												.getObjetoClienteEnderecoSelecionado())) {

									if (clienteEnderecoNaColecao.getId() != null){
										clienteEndereco.setId(clienteEnderecoNaColecao.getId());
									}
									
									clienteEndereco
											.setUltimaAlteracao(clienteEnderecoNaColecao
													.getUltimaAlteracao());

									iteratorEnderecos.remove();
								}

								if (obterTimestampIdObjeto(clienteEnderecoNaColecao) == Long
										.parseLong(inserirEnderecoActionForm
												.getEnderecoCorrespondencia())) {

									DynaValidatorForm formProcessoCliente = (DynaValidatorForm) sessao
											.getAttribute("ClienteActionForm");
									formProcessoCliente
											.set(
													"enderecoCorrespondenciaSelecao",
													Long
															.parseLong(inserirEnderecoActionForm
																	.getEnderecoCorrespondencia()));

									clienteEndereco
											.setIndicadorEnderecoCorrespondencia(ConstantesSistema.INDICADOR_ENDERECO_CORRESPONDENCIA);

								}
							}

							httpServletRequest.setAttribute("fecharPopup", "ok");
						}else{
							Iterator iteratorEnderecos = enderecos.iterator();
							ClienteEndereco clienteEnderecoNaColecao = null;

							while (iteratorEnderecos.hasNext()) {
								clienteEnderecoNaColecao = (ClienteEndereco) iteratorEnderecos
										.next();
								// Verificando se o endereço já existe na coleção
								// Roberta Costa - 31/07/2006 
								if ( clienteEnderecoNaColecao.getEnderecoTipo().getId()
										.equals(clienteEndereco.getEnderecoTipo().getId())
									&& clienteEnderecoNaColecao.getLogradouroCep()
										.equals(clienteEndereco.getLogradouroCep())
									&& clienteEnderecoNaColecao.getLogradouroBairro()
										.equals(clienteEndereco.getLogradouroBairro())
									&& clienteEnderecoNaColecao.getEnderecoReferencia().getId()
										.equals(clienteEndereco.getEnderecoReferencia().getId())
									&& clienteEnderecoNaColecao.getNumero()
										.equals(clienteEndereco.getNumero()) 
									&& ( clienteEnderecoNaColecao.getComplemento() == null
											&& clienteEndereco.getComplemento() == null
												|| (clienteEnderecoNaColecao.getComplemento() != null
														&& clienteEndereco.getComplemento() != null
															&& clienteEnderecoNaColecao.getComplemento()
																.equals(clienteEndereco.getComplemento())))
									&& ( (clienteEnderecoNaColecao.getPerimetroInicial() == null && clienteEndereco.getPerimetroInicial() == null)
										  || (clienteEnderecoNaColecao.getPerimetroInicial() != null && clienteEndereco.getPerimetroInicial() != null 
												 && clienteEnderecoNaColecao.getPerimetroInicial().getId().equals(clienteEndereco.getPerimetroInicial().getId())))
									&& ( (clienteEnderecoNaColecao.getPerimetroFinal() == null && clienteEndereco.getPerimetroFinal() == null)
										  || (clienteEnderecoNaColecao.getPerimetroFinal() != null && clienteEndereco.getPerimetroFinal() != null 
												 && clienteEnderecoNaColecao.getPerimetroFinal().getId().equals(clienteEndereco.getPerimetroFinal().getId())))
									){		 
									
									throw new ActionServletException(
										"atencao.endereco_ja_informado");
								}
							}
						}

						enderecos.add(clienteEndereco);

						httpServletRequest.setAttribute("flagRedirect", "cliente");

					} else if (tipoRetorno.trim().equalsIgnoreCase("imovel")) {

						if (enderecos.isEmpty()
								|| (tipoAcao != null && !tipoAcao.equals(""))) {
							enderecos.clear();
							enderecos.add(imovel);
						}

						httpServletRequest.setAttribute("flagRedirect", "imovel");
						httpServletRequest.setAttribute("fecharPopup", "ok");

					} else if (tipoRetorno.trim().equalsIgnoreCase("localidade")) {

						if (enderecos.isEmpty()
								|| (tipoAcao != null && !tipoAcao.equals(""))) {
							enderecos.clear();
							enderecos.add(localidade);
						}

						httpServletRequest.setAttribute("flagRedirect",
								"localidade");
						httpServletRequest.setAttribute("fecharPopup", "ok");
						
					} else if (tipoRetorno.trim().equalsIgnoreCase("gerenciaRegional")) {

						if (enderecos.isEmpty()
								|| (tipoAcao != null && !tipoAcao.equals(""))) {
							enderecos.clear();
							enderecos.add(gerenciaRegional);
						}

						httpServletRequest.setAttribute("flagRedirect",
								"gerenciaRegional");
						httpServletRequest.setAttribute("fecharPopup", "ok");
						
					} else if (tipoRetorno.trim().equalsIgnoreCase("agencia")) {

						if (enderecos.isEmpty()
								|| (tipoAcao != null && !tipoAcao.equals(""))) {
							enderecos.clear();
							enderecos.add(agencia);
						}

						httpServletRequest.setAttribute("flagRedirect",
								"agencia");
						httpServletRequest.setAttribute("fecharPopup", "ok");

					} else if (tipoRetorno.trim().equalsIgnoreCase("sistemaParametro")) {

						if (enderecos.isEmpty() || (tipoAcao != null && !tipoAcao.equals("")) ) {

							enderecos.clear();
							enderecos.add(imovel);
						}

						httpServletRequest.setAttribute("flagRedirect","sistemaParametro");
						
					}  else if (tipoRetorno.trim().equalsIgnoreCase("agencia")) {

						if (enderecos.isEmpty()
								|| (tipoAcao != null && !tipoAcao.equals(""))) {
							enderecos.clear();
							enderecos.add(agencia);
						}

						httpServletRequest.setAttribute("flagRedirect",
								"agencia");
						httpServletRequest.setAttribute("fecharPopup", "ok");

					}
				}
			}

			

			httpServletRequest.setAttribute("flagOperacao", tipoOperacao);
			httpServletRequest.setAttribute("flagReload", "true");

			// Limpar o formulário
			inserirEnderecoActionForm.setBairro("");
			inserirEnderecoActionForm.setCep("");
			inserirEnderecoActionForm.setCepSelecionado("");
			inserirEnderecoActionForm.setCepUnico("");
			inserirEnderecoActionForm.setComplemento("");
			inserirEnderecoActionForm.setEnderecoReferencia(EnderecoReferencia.NUMERO.toString());
			inserirEnderecoActionForm.setLogradouro("");
			inserirEnderecoActionForm.setLogradouroDescricao("");
			inserirEnderecoActionForm.setNumero("");
			inserirEnderecoActionForm.setTipo(String
					.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO));
			
			inserirEnderecoActionForm.setEnderecoCorrespondencia("");
			inserirEnderecoActionForm.setObjetoClienteEnderecoSelecionado("");
			
			inserirEnderecoActionForm.setTipoAcao("");
			inserirEnderecoActionForm.setIdPerimetroInicial("");
			inserirEnderecoActionForm.setDescricaoPerimetroInicial("");
			inserirEnderecoActionForm.setIdPerimetroFinal("");
			inserirEnderecoActionForm.setDescricaoPerimetroFinal("");
			inserirEnderecoActionForm.setTipoPesquisaTela("");
			
			String fecharPopup = (String) httpServletRequest.getAttribute("fecharPopup");
			
			if (fecharPopup != null && !fecharPopup.trim().equals("")) {
				inserirEnderecoActionForm.setMostrarPerimetro(false);
			}

			sessao.removeAttribute("objetoCep");
			sessao.removeAttribute("colecaoCepSelecionadosUsuario");
			sessao.removeAttribute("logradouroBairros");

		}
		// devolve o mapeamento de retorno
		return retorno;
	}
	
	private Logradouro pesquisarLogradouroDigitado(String idLogradouro) {
		
		Logradouro logradouro = null;
		
		FiltroLogradouro filtroLogradouro = new FiltroLogradouro();
		filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouro.LOGRADOUROTIPO);
		filtroLogradouro.adicionarCaminhoParaCarregamentoEntidade(FiltroLogradouro.LOGRADOUROTITULO);
		filtroLogradouro.adicionarParametro(new ParametroSimples(FiltroLogradouro.ID, idLogradouro));
		
		Collection logradouros = Fachada.getInstancia().pesquisar(filtroLogradouro, Logradouro.class.getName());
		
		if (logradouros != null && !logradouros.isEmpty()) {
			logradouro = (Logradouro) Util.retonarObjetoDeColecao(logradouros);
		}
		
		return logradouro;
	}

}
