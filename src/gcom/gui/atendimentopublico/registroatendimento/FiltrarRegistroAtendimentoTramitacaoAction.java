package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.Tramite;
import gcom.atendimentopublico.registroatendimento.bean.FiltrarRegistroAtendimentoHelper;
import gcom.atendimentopublico.registroatendimento.bean.ObterDescricaoSituacaoRAHelper;
import gcom.atendimentopublico.registroatendimento.bean.RAFiltroHelper;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.integracao.ProcessarRequisicaoGisAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesAplicacao;
import gcom.util.ConstantesSistema;
import gcom.util.FachadaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0503]Tramitar Conjunto de Registro de Atendimento
 * 
 * @author Ana Maria
 * @date 08/01/2007
 */
public class FiltrarRegistroAtendimentoTramitacaoAction extends GcomAction {
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

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltrarRegistroAtendimentoTramitacaoActionForm form = (FiltrarRegistroAtendimentoTramitacaoActionForm) actionForm;

		ActionForward retorno = actionMapping.findForward("tramitacaoRegistroAtendimento");
		
		boolean parametroInformado = false;
		
		String retornaDoPopup = (String)httpServletRequest.getParameter("retornaDoPopup");
		
		String importarMovimentoACQUAGIS = 
			(String) httpServletRequest.getParameter("importarMovimentoACQUAGIS");
		
		Collection<RegistroAtendimento> colecaoRegistroAtendimento = new ArrayList();
		
		if(retornaDoPopup == null){
			RegistroAtendimento ra = new RegistroAtendimento();
			FiltrarRegistroAtendimentoHelper filtroRA = new FiltrarRegistroAtendimentoHelper();
			HashMap<String, Collection> dadosMovimentoACQUAGIS = null;
			
			if (importarMovimentoACQUAGIS != null &&
					importarMovimentoACQUAGIS.equalsIgnoreCase("sim")) {
				
				dadosMovimentoACQUAGIS = ProcessarRequisicaoGisAction.listaRAIntegracaoGIS;
				Set colecaoRA = null;
				if (dadosMovimentoACQUAGIS != null && !dadosMovimentoACQUAGIS.isEmpty()) {
//					Set<String> chaves = dadosMovimentoACQUAGIS.keySet();
					colecaoRA = new HashSet();
//					
//					for (Iterator<String> iterator = chaves.iterator(); iterator.hasNext();) {  
//					    // Nesse caso o Login do Usuario
//						String chave = iterator.next();
//					    if(chave != null) {
//					    	// Percorre a Lista de RAs
//					    	Iterator lista = dadosMovimentoACQUAGIS.get(chave).iterator();
//					    	while (lista.hasNext()) {
//					    		Integer idRA = new Integer(lista.next().toString());
//					    		colecaoRA.add(idRA);
//					    	}
//					    }
//					}
					
					Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
					if (dadosMovimentoACQUAGIS.get(usuario.getLogin()) != null) {
						Iterator lista = dadosMovimentoACQUAGIS.get(usuario.getLogin()).iterator();
				    	while (lista.hasNext()) {
				    		Integer idRA = new Integer(lista.next().toString());
				    		colecaoRA.add(idRA);
				    	}
					}else {
						//Nenhum resultado
						throw new ActionServletException(
								"atencao.pesquisa.nenhumresultado");
					}
					
					if (colecaoRA != null && !colecaoRA.isEmpty()) {
						/*
						FiltroRegistroAtendimento filtro = new FiltroRegistroAtendimento();
						filtro.adicionarCaminhoParaCarregamentoEntidade(
								FiltroRegistroAtendimento.SOLICITACAO_TIPO_ESPECIFICACAO);
						filtro.adicionarCaminhoParaCarregamentoEntidade("localidade");
						filtro.adicionarParametro(new ParametroSimplesIn(
								FiltroRegistroAtendimento.ID, colecaoRA));
						colecaoRegistroAtendimento = 
							fachada.pesquisar(filtro, RegistroAtendimento.class.getName());
						*/
						filtroRA.setRegistroAtendimento(new RegistroAtendimento());
						filtroRA.setColecaoRAPorUnidades((Set) colecaoRA);
						filtroRA.setNumeroPagina(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO));
						colecaoRegistroAtendimento = fachada.filtrarRegistroAtendimento(filtroRA);
						
						sessao.setAttribute("importarMovimentoACQUAGIS", "sim");
						sessao.setAttribute("caminhoRetornoOS", "filtrarRegistroAtendimentoTramitacaoAction.do?importarMovimentoACQUAGIS=sim");
						
						this.tramitarRAMovimentoACQUAGIS(sessao, colecaoRegistroAtendimento);
					}
				}else {
					//Nenhum resultado
					throw new ActionServletException(
							"atencao.pesquisa.nenhumresultado");
				}
			}else {
				// Numero RA
				if (form.getNumeroRa() != null
						&& !form.getNumeroRa().equals("")) {
					ra.setId(new Integer(form.getNumeroRa()));
					parametroInformado = true;
				}

				// Tipo Solicitação
				Collection<Integer> colecaoSolicitacaoTipoSolicitacao = new ArrayList();
				if (form.getTipoSolicitacao() != null
						&& form.getTipoSolicitacao().length > 0) {
					String[] tipoSolicitacao = form.getTipoSolicitacao();
					for (int i = 0; i < tipoSolicitacao.length; i++) {
						if (new Integer(tipoSolicitacao[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
							colecaoSolicitacaoTipoSolicitacao.add(new Integer(
									tipoSolicitacao[i]));
							// passar a coleção de especificação por parâmetro
							parametroInformado = true;
						}
					}
				}

				// Tipo Especificação
				Collection<Integer> colecaoSolicitacaoTipoEspecificacao = new ArrayList();
				if (colecaoSolicitacaoTipoSolicitacao.size() < 2
						&& form.getEspecificacao() != null
						&& form.getEspecificacao().length > 0) {
					String[] tipoSolicitacaoEspecificacao = form
							.getEspecificacao();
					for (int i = 0; i < tipoSolicitacaoEspecificacao.length; i++) {
						if (new Integer(tipoSolicitacaoEspecificacao[i])
								.intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
							colecaoSolicitacaoTipoEspecificacao
									.add(new Integer(
											tipoSolicitacaoEspecificacao[i]));
							// passar a coleção de especificação por parâmetro
							parametroInformado = true;
						}
					}
				}
				
				// Perfil Imovel
				Collection<Integer> colecaoPerfilImovel = null;
				if (form.getColecaoPerfilImovel() != null) {
					colecaoPerfilImovel = new ArrayList<Integer>();
					
					for (String id : form.getColecaoPerfilImovel()) {
						if (!id.equals("-1")) {
							parametroInformado = true;
							colecaoPerfilImovel.add(new Integer(id));
						}
					}
					if (colecaoPerfilImovel.size() == 0) colecaoPerfilImovel = null;
				}				
				
				// Data de Atendimento
				Date dataAtendimentoInicial = null;
				Date dataAtendimentoFinal = null;
				if (form.getPeriodoAtendimentoInicial() != null
						&& !form.getPeriodoAtendimentoInicial().equals("")) {
					dataAtendimentoInicial = Util.converteStringParaDate(form
							.getPeriodoAtendimentoInicial());
					dataAtendimentoFinal = null;
					if (form.getPeriodoAtendimentoFinal() != null
							&& !form.getPeriodoAtendimentoFinal().equals("")) {
						dataAtendimentoFinal = Util.converteStringParaDate(form
								.getPeriodoAtendimentoFinal());
						dataAtendimentoFinal = Util
								.adaptarDataFinalComparacaoBetween(dataAtendimentoFinal);
					} else {
						dataAtendimentoFinal = new Date();
					}
					// Verificar data final menor que data inicial
					int qtdeDias = Util.obterQuantidadeDiasEntreDuasDatas(
							dataAtendimentoInicial, dataAtendimentoFinal);
					if (qtdeDias < 0) {
						throw new ActionServletException(
								"atencao.filtrar_data_final_maior_que_inicial");
					}
					// passar as datas de atendimento por parâmetro
					parametroInformado = true;
				}

				// Unidade de Atual
				UnidadeOrganizacional unidadeAtual = null;
				if (form.getUnidadeAtualId() != null
						&& !form.getUnidadeAtualId().equals("")) {
					unidadeAtual = new UnidadeOrganizacional();
					unidadeAtual.setId(new Integer(form.getUnidadeAtualId()));
					// passar coleção de unidades por parâmetro
					parametroInformado = true;
				}
				// Unidade de Superior
				UnidadeOrganizacional unidadeSuperior = null;
				if (form.getUnidadeSuperiorId() != null
						&& !form.getUnidadeSuperiorId().equals("")) {
					unidadeSuperior = new UnidadeOrganizacional();
					unidadeSuperior.setId(new Integer(form
							.getUnidadeSuperiorId()));
					// passar coleção de unidades por parâmetro
					parametroInformado = true;
				}
				// Município
				String municipioId = null;
				if (form.getMunicipioId() != null
						&& !form.getMunicipioId().equals("")) {
					municipioId = form.getMunicipioId();
					parametroInformado = true;
				}
				// Bairro
				String bairroId = null;
				if (form.getBairroId() != null
						&& !form.getBairroId().equals("")) {
					bairroId = form.getBairroId();
					parametroInformado = true;
				}
				// Bairro Área
				if (new Integer(form.getAreaBairroId()).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					BairroArea bairroArea = new BairroArea();
					bairroArea.setId(new Integer(form.getAreaBairroId()));
					ra.setBairroArea(bairroArea);
					parametroInformado = true;
				}
				// Logradouro
				String logradouroId = null;
				if (form.getLogradouroId() != null
						&& !form.getLogradouroId().equals("")) {
					logradouroId = form.getLogradouroId();
					parametroInformado = true;
				}

				ra.setCodigoSituacao(new Short("1"));

				// Filtra Registro Atendimento
				if (parametroInformado) {
					//Collection<RegistroAtendimento> colecaoRegistroAtendimento = new ArrayList();

					filtroRA.setRegistroAtendimento(ra);
					filtroRA.setUnidadeAtual(unidadeAtual);
					filtroRA.setUnidadeSuperior(unidadeSuperior);
					filtroRA.setDataAtendimentoInicial(dataAtendimentoInicial);
					filtroRA.setDataAtendimentoFinal(dataAtendimentoFinal);
					filtroRA
							.setColecaoTipoSolicitacaoEspecificacao(colecaoSolicitacaoTipoEspecificacao);
					filtroRA
							.setColecaoTipoSolicitacao(colecaoSolicitacaoTipoSolicitacao);
					filtroRA.setMunicipioId(municipioId);
					filtroRA.setBairroId(bairroId);
					filtroRA.setLogradouroId(logradouroId);
					
					filtroRA.setColecaoPerfilImovel(colecaoPerfilImovel);

					filtroRA.setNumeroPagina(new Integer(
							ConstantesSistema.NUMERO_NAO_INFORMADO));
					// Collection colecaoRAHelperCompleta =
					// fachada.filtrarRegistroAtendimento(filtroRA);
					// Integer totalRegistros = colecaoRAHelperCompleta.size();

					// retorno = this.controlarPaginacao(httpServletRequest,
					// retorno, totalRegistros);

					// String totalRegistrosRA = ""+ (Integer)
					// sessao.getAttribute("totalRegistros");

					// sessao.setAttribute("totalRegistrosRA",
					// totalRegistrosRA);

					// filtroRA.setNumeroPagina(((Integer)
					// httpServletRequest.getAttribute("numeroPaginasPesquisa")));
					colecaoRegistroAtendimento = fachada
							.filtrarRegistroAtendimento(filtroRA);
				} else {
					throw new ActionServletException(
							"atencao.filtrar_informar_um_filtro");
				}
			}
			
			if (colecaoRegistroAtendimento != null) {
				// Carrega Coleção
				Collection<RAFiltroHelper> colecaoRAHelper = loadColecaoRAHelper(colecaoRegistroAtendimento, sessao);

				sessao.removeAttribute("numeroOS");
				// sessao.setAttribute("colecaoCompleta",
				// colecaoRAHelperCompleta);
				
				
				Collection<Tramite> tramites = (Collection)sessao.getAttribute("tramites");
				
				//************************************************************
				// CRC3430
				// Por: Ivan Sergio
				// tramites não estava sendo verificado se estava null
				//************************************************************
				if (tramites != null) {
					Collection<RAFiltroHelper> colecaoRAHelperDepoisTramite = new ArrayList<RAFiltroHelper>();
					for(RAFiltroHelper raFiltroHelper: colecaoRAHelper) {
						for(Tramite tramite: tramites){
							if (raFiltroHelper.getRegistroAtendimento().getId().equals(tramite.getRegistroAtendimento().getId())) {
								colecaoRAHelperDepoisTramite.add(raFiltroHelper);
							}
							
						} 
						
					}
					
					if (colecaoRAHelperDepoisTramite.size() == 0) {
						// Nenhum resultado
						throw new ActionServletException(
								"atencao.pesquisa.nenhumresultado");
						
					}
	
					sessao.setAttribute("colecaoRAHelper", colecaoRAHelperDepoisTramite);
					
					//sessao.setAttribute("colecaoRAHelper", colecaoRAHelper);
				}else {
					sessao.setAttribute("colecaoRAHelper", colecaoRAHelper);
				}

			} else {
				// Nenhum resultado
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
		}
		return retorno;
	}
	
	/**
	 * Carrega coleção de registro atendimento, situação abreviada e unidade atual no 
	 * objeto facilitador 
	 *
	 * @param colecaoRegistroAtendimento
	 * @return
	 */
	private Collection loadColecaoRAHelper(Collection<RegistroAtendimento> colecaoRegistroAtendimento, HttpSession sessao) {
		Fachada fachada = Fachada.getInstancia();
		Collection colecaoRAHelper = new ArrayList();
		UnidadeOrganizacional unidadeAtual = null;
		UnidadeOrganizacional unidadeDestino = null;
		ObterDescricaoSituacaoRAHelper situacao = null;
		RAFiltroHelper helper = null;
		
		//************************************************************
		// Autor: Ivan Sergio
		// Data: 29/09/2009
		// Esta modificacao esta sendo utilizada para auxiliar no
		// Processar Movimento pela Integracao com o ACQUAGIS.
		//************************************************************
		if(sessao.getAttribute("importarMovimentoACQUAGIS") != null &&
				sessao.getAttribute("importarMovimentoACQUAGIS").equals("sim")) {
			
			FiltroUnidadeOrganizacional filtroUnidade = new FiltroUnidadeOrganizacional();
			filtroUnidade.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID, 9543));
			unidadeDestino = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(
					fachada.pesquisar(filtroUnidade, UnidadeOrganizacional.class.getName()));
		}
		//************************************************************
		
		for (Iterator iter = colecaoRegistroAtendimento.iterator(); iter.hasNext();) {
			RegistroAtendimento registroAtendimento = (RegistroAtendimento) iter.next();
			
			situacao = fachada.obterDescricaoSituacaoRA(registroAtendimento.getId());
			unidadeAtual = fachada.obterUnidadeAtualRA(registroAtendimento.getId());
			helper = new RAFiltroHelper();
			helper.setRegistroAtendimento(registroAtendimento);
			helper.setUnidadeAtual(unidadeAtual);
			helper.setSituacao(situacao.getDescricaoAbreviadaSituacao());
			
			if(registroAtendimento.getImovel()!= null){
				helper.setPerfilImovel(registroAtendimento.getImovel().getImovelPerfil());				
			}			
			
			//************************************************************
			// Autor: Ivan Sergio
			// Data: 29/09/2009
			// Esta modificacao esta sendo utilizada para auxiliar no
			// Processar Movimento pela Integracao com o ACQUAGIS.
			//************************************************************
			if (unidadeDestino != null)
				helper.setUnidadeDestino(unidadeDestino);
			//************************************************************
			
			colecaoRAHelper.add(helper);
		}
		return colecaoRAHelper;
	}	
	
	/***
	 * @author Ivan Sergio
	 * @date 28/09/2009
	 * Metodo utilizado para auxiliar no Processar Movimento pela Integracao com o ACQUAGIS
	 * 
	 * @param sessao
	 * @param idsRA
	 */
	private void tramitarRAMovimentoACQUAGIS(HttpSession sessao, Collection idsRA) {
		Fachada fachada = Fachada.getInstancia();
		//Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");	
		
		//String[] ids = (String[]) sessao.getAttribute("ids");
		String[] ids = new String[idsRA.size()];
		Iterator iColecaoRa = idsRA.iterator();
		int x = 0;
		while (iColecaoRa.hasNext()) {
			RegistroAtendimento ra = (RegistroAtendimento) iColecaoRa.next();
			ids[x] = ra.getId() + ";" + ra.getUnidadeAtual().getId();
			x++;
			System.out.println(ra.getId().toString());
		}
		
        //Data e Hora da tramitação
		//String dataHoraTramitacao = form.getDataTramitacao() + " " + form.getHoraTramitacao()+ ":00";
		String dataHoraTramitacao = Util.formatarDataComHora(new Date());
		Date dataHoraTramitacaoObjetoDate = Util.converteStringParaDateHora(dataHoraTramitacao);
		
		//Unidade Destino
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, 9543));
		UnidadeOrganizacional unidadeDestino =(UnidadeOrganizacional) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroUnidadeOrganizacional,UnidadeOrganizacional.class.getName()));
		
		//[FS0006] Valida Data
		//[FS0007] Valida Hora
		//[FS0008] Valida Unidade Destino		
		fachada.validarConjuntoTramitacaoIntegracao(ids, dataHoraTramitacaoObjetoDate, 9543,usuario);
		
		Collection<Tramite> tramites = null;
		//Recupera a coleção de tramite da sessão, caso exista, ou cria uma nova
		if(sessao.getAttribute("tramites") != null && !sessao.getAttribute("tramites").equals("")){
			tramites = (Collection)sessao.getAttribute("tramites");
		}else{
			tramites  = new ArrayList();
		}
		
		boolean achou = false;
		
		if (ids != null && ids.length != 0) {
			for (int i = 0; i < ids.length; i++) {
				if (ids[i] != null) {
					achou = false;
					String[] idsTramitacao = ids[i].split(";");
					// Verifica a existência da coleção na sessão.
					if (sessao.getAttribute("tramites") != null
							&& !sessao.getAttribute("tramites").equals("")) {
						Collection tramitesSessao = (Collection) sessao
								.getAttribute("tramites");
						if (tramitesSessao != null && !tramitesSessao.isEmpty()) {
							Iterator iteratorTramite = tramitesSessao
									.iterator();
							while (iteratorTramite.hasNext()) {
								Tramite tramiteColecao = (Tramite) iteratorTramite
										.next();
								// Caso exita na colecão da sessão o registro de
								// atendimento selecionado atualiza o tramite
								// existente.
								if (tramiteColecao
										.getRegistroAtendimento()
										.getId()
										.equals(
												Integer
														.parseInt(idsTramitacao[0]))) {
									// Unidade Destino
									tramiteColecao
											.setUnidadeOrganizacionalDestino(unidadeDestino);
									// Atualiza a unidade Destino do RA

									// atualizarUnidadeDestinoColecao(Integer.parseInt(idsTramitacao[0]),
									// sessao, unidadeDestino);

									// Usuário Responsável
									tramiteColecao
											.setUsuarioResponsavel(usuario);

									// Data da tramitação
									tramiteColecao
											.setDataTramite(dataHoraTramitacaoObjetoDate);
									// Parecer da tramitação
									tramiteColecao
											.setParecerTramite("TRAMITE AUTOMATICO. RESULTANTE DA GERACAO DE MOVIMENTO PARA A EXECUTORA.");

									achou = true;
									break;
								}
							}
						}
					}

					if (!achou) {
						// Cria o tramite e joga na sessão
						Tramite tramite = new Tramite();
						// Unidade Destino
						tramite.setUnidadeOrganizacionalDestino(unidadeDestino);
						// Atualiza a unidade Destino do RA
						// atualizarUnidadeDestinoColecao(Integer.parseInt(idsTramitacao[0]),
						// sessao, unidadeDestino);

						// Registro de Atendimento
						FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
						filtroRegistroAtendimento
								.adicionarCaminhoParaCarregamentoEntidade(FiltroRegistroAtendimento.SOLICITACAO_TIPO);

						filtroRegistroAtendimento
								.adicionarParametro(new ParametroSimples(
										FiltroRegistroAtendimento.ID,
										idsTramitacao[0]));

						Collection colecaoRA = fachada.pesquisar(
								filtroRegistroAtendimento,
								RegistroAtendimento.class.getName());

						RegistroAtendimento ra = (RegistroAtendimento) Util
								.retonarObjetoDeColecao(colecaoRA);
						tramite.setRegistroAtendimento(ra);
						// Unidade Atual
						FiltroUnidadeOrganizacional filtroUnidadeOrganizacionalAtual = new FiltroUnidadeOrganizacional();
						filtroUnidadeOrganizacionalAtual
								.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
						filtroUnidadeOrganizacionalAtual
								.adicionarParametro(new ParametroSimples(
										FiltroUnidadeOrganizacional.ID, Integer
												.parseInt(idsTramitacao[1])));
						UnidadeOrganizacional unidadeAtual = (UnidadeOrganizacional) Util
								.retonarObjetoDeColecao(fachada.pesquisar(
										filtroUnidadeOrganizacionalAtual,
										UnidadeOrganizacional.class.getName()));

						tramite.setUnidadeOrganizacionalOrigem(unidadeAtual);
						// Usuário Responsável
						tramite.setUsuarioResponsavel(usuario);
						// Usuário Logado
						tramite.setUsuarioRegistro(usuario);
						// Data da tramitação
						tramite.setDataTramite(dataHoraTramitacaoObjetoDate);
						//Parecer da tramitação
						tramite
								.setParecerTramite("TRAMITE AUTOMATICO. GERADO PELA GERACAO DE MOVIMENTO PARA A EXECUTORA.");

						boolean erroValidacaoTramitacao = false;
						try { 
						fachada.validarTramitacao(tramite, usuario);
						} catch (FachadaException e) {
							erroValidacaoTramitacao = true;
							System.out.println(tramite.getRegistroAtendimento().getId() +" "+ ConstantesAplicacao.get(e.getMessage(), e.getParametroMensagem().toArray(new String[e.getParametroMensagem().size()])));
						}
						if (!erroValidacaoTramitacao) {
							tramites.add(tramite);
						}
					}
				}
			}
		}	
		
		sessao.setAttribute("tramites", tramites);
		
	}
	

}
