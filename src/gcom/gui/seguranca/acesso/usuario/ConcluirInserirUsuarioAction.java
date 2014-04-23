package gcom.gui.seguranca.acesso.usuario;

import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.FiltroUsuarioGrupo;
import gcom.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.UsuarioGrupo;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
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
 * Action conclui o inserir Usuario
 * 
 * @author Sávio Luiz
 * @date 09/05/2006
 */
public class ConcluirInserirUsuarioAction extends GcomAction {

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
	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		HttpSession sessao = httpServletRequest.getSession(false);

		InserirUsuarioDadosGeraisActionForm form = (InserirUsuarioDadosGeraisActionForm) actionForm;
		
		String idSolicitacaoAcesso = (String) sessao.getAttribute("idSolicitacaoAcesso");

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		// Usuario que vai ser cadastrado no sistema, usado só nessa
		// funcionalidade
		Usuario usuarioCadastrar = (Usuario) sessao
				.getAttribute("usuarioCadastrar");

		// valida os campos obrigatórios do usuario tipo
		if (!"".equals(form.getUsuarioTipo())) {

			FiltroUsuarioTipo filtroUsuarioTipo = new FiltroUsuarioTipo();
			filtroUsuarioTipo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioTipo.ID, form.getUsuarioTipo()));
			Collection coll = Fachada.getInstancia().pesquisar(
					filtroUsuarioTipo, UsuarioTipo.class.getSimpleName());
			if (coll != null && !coll.isEmpty()) {
				UsuarioTipo usuarioTipo = (UsuarioTipo) Util
						.retonarObjetoDeColecao(coll);

				// caso não seja usuario tipo adiministrador então valida os
				// campos
				if (!usuarioTipo.getId().equals(
						UsuarioTipo.USUARIO_TIPO_ADMINISTRADOR)) {

					// valida os campos obrigatorios
					if (usuarioTipo.getIndicadorFuncionario() == UsuarioTipo.INDICADOR_FUNCIONARIO) {
						if (form.getEmpresa() == null
								|| form.getEmpresa().equals("")) {
							throw new ActionServletException(
									"atencao.required", null, "Empresa");
						}

						// matricula do funcionário é obrigatório
						if (form.getIdFuncionario() == null
								|| form.getIdFuncionario().equals("")) {
							throw new ActionServletException(
									"atencao.required", null,
									"Matrícula Funcionário");
						}
						if (form.getIdFuncionario() != null
								&& (form.getNomeFuncionario() == null || form
										.getNomeFuncionario().equals(""))) {
							FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
							filtroFuncionario
									.adicionarParametro(new ParametroSimples(
											FiltroFuncionario.ID, form
													.getIdFuncionario()));
							filtroFuncionario
									.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionario.UNIDADE_ORGANIZACIONAL);
							Collection colecaoFuncionario = Fachada
									.getInstancia().pesquisar(
											filtroFuncionario,
											Funcionario.class
													.getSimpleName());
							if (colecaoFuncionario != null
									&& !colecaoFuncionario.isEmpty()) {
								Funcionario f = (Funcionario) colecaoFuncionario
										.iterator().next();
								//Verifica se o funcionario ja esta cadastrado como usuário
								//pela matrícula
								FiltroUsuario filtroUsuario = new FiltroUsuario();
								filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.FUNCIONARIO_ID, f.getId()));
								Collection colecaoFuncUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
								if(!colecaoFuncUsuario.isEmpty()){
									throw new ActionServletException(
											"atencao.usuario.funcionario.ja.existe", null,
											f.getNome());
								}
									
								usuarioCadastrar.setFuncionario(f);
								form.setIdFuncionario(f.getId().toString());
								form.setNomeFuncionario(f.getNome());
								usuarioCadastrar
										.setNomeUsuario(f.getNome());
							} else {
								throw new ActionServletException(
										"atencao.required", null,
										"Matrícula Funcionário");
							}
						}
						// nome do funcionario é obrigatorio
						if (form.getNome() == null || form.getNome().equals("")) {
							throw new ActionServletException(
									"atencao.required", null, "Nome Usuário");
						}
						
						
						//CPF do funcionario é obrigatorio para usuário diferentes de rotina batch e/ou internet
						if(form.getIndicadorUsuarioBatch() != 1 && form.getIndicadorUsuarioInternet() != 1){
							if (form.getCpf() == null || form.getCpf().equals("")) {
								throw new ActionServletException(
										"atencao.required", null, "Número do CPF");
							}
						}
						
						//Data de Nascimento do funcionario é obrigatoria para usuários diferentes de rotina batch e/ou internet 
						if(form.getIndicadorUsuarioBatch() != 1 && form.getIndicadorUsuarioInternet() != 1){
							if (form.getDataNascimento() == null
									|| form.getDataNascimento().equals("")) {
								throw new ActionServletException(
										"atencao.required", null,
								"Data de Nascimento");
							}
						}
						
						//Indicador indicadorUsuarioBatch é obrigatorio
						if (form.getIndicadorUsuarioBatch() == null
								|| form.getIndicadorUsuarioBatch().equals("")) {
							throw new ActionServletException(
									"atencao.required", null,
									"Indicador para rotina batch");
						}
						//Indicador indicadorUsuarioInternet é obrigatorio
						if (form.getIndicadorUsuarioInternet() == null
								|| form.getIndicadorUsuarioInternet().equals("")) {
							throw new ActionServletException(
									"atencao.required", null,
									"Indicador para internet");
						}

					} else {
						if (usuarioTipo.getIndicadorFuncionario() != UsuarioTipo.INDICADOR_FUNCIONARIO) {
							// data inicio e data fim é obrigatorio
							if (form.getEmpresa() == null
									|| form.getEmpresa().equals("")) {
								throw new ActionServletException(
										"atencao.required", null, "Empresa");
							}
							// data inicio e data fim é obrigatorio
							if (form.getNome() == null
									|| form.getNome().equals("")) {
								throw new ActionServletException(
										"atencao.required", null,
										"Nome Usuário");
							}
							//CPF do funcionario é obrigatorio para usuários diferentes de rotina batch e/ou internet
							if(form.getIndicadorUsuarioBatch() != 1 && form.getIndicadorUsuarioInternet() != 1){
								if (form.getCpf() == null || form.getCpf().equals("")) {
									throw new ActionServletException(
											"atencao.required", null, "Número do CPF");
								}
							}
							
							//Data de Nascimento do funcionario é obrigatoria para usuários diferentes de rotina batch e/ou internet 
							if(form.getIndicadorUsuarioBatch() != 1 && form.getIndicadorUsuarioInternet() != 1){
								if (form.getDataNascimento() == null
										|| form.getDataNascimento().equals("")) {
									throw new ActionServletException(
											"atencao.required", null,
									"Data de Nascimento");
								}
							}
							
							// data inicio e data fim é obrigatorio
							if (form.getIdLotacao() == null
									|| form.getIdLotacao().equals("")) {
								throw new ActionServletException(
										"atencao.required", null,
										"Unidade Lotação");
							}
							// data inicio e data fim é obrigatorio
							if (form.getDataInicial() == null
									|| form.getDataInicial().equals("")
									|| form.getDataFinal() == null
									|| form.getDataFinal().equals("")) {
								throw new ActionServletException(
										"atencao.required", null,
										"Período de Cadastramento");
							}
							
							//Indicador indicadorUsuarioBatch é obrigatorio
							if (form.getIndicadorUsuarioBatch() == null
									|| form.getIndicadorUsuarioBatch().equals("")) {
								throw new ActionServletException(
										"atencao.required", null,
										"Indicador para rotina batch");
							}
							//Indicador indicadorUsuarioInternet é obrigatorio
							if (form.getIndicadorUsuarioInternet() == null
									|| form.getIndicadorUsuarioInternet().equals("")) {
								throw new ActionServletException(
										"atencao.required", null,
										"Indicador para internet");
							}
						}
					}
				}
				usuarioCadastrar.setUsuarioTipo(usuarioTipo);
			} else {
				usuarioCadastrar.setUsuarioTipo(null);
			}

		} else {
			throw new ActionServletException("atencao.required", null,
					"Usuário Tipo");
		}

		if (form.getAbrangencia() != null && !form.getAbrangencia().equals("")) {
			if (form.getAbrangencia().equals(
					UsuarioAbrangencia.GERENCIA_REGIONAL)) {
				if (form.getGerenciaRegional() == null
						|| form.getGerenciaRegional().equals("")) {
					throw new ActionServletException("atencao.required", null,
							"Gerência Regional");
				}
			}
			if (form.getAbrangencia().equals(
					UsuarioAbrangencia.UNIDADE_NEGOCIO_INT)) {
				if (form.getUnidadeNegocio() == null
						|| form.getUnidadeNegocio().equals("")) {
					throw new ActionServletException("atencao.required", null,
							"Unidade Negócio");
				}
			}
			if (form.getAbrangencia().equals(UsuarioAbrangencia.ELO_POLO)) {
				if (form.getIdElo() == null || form.getIdElo().equals("")) {
					throw new ActionServletException("atencao.required", null,
							"Elo Pólo");
				}
			}
			if (form.getAbrangencia().equals(UsuarioAbrangencia.LOCALIDADE)) {
				if (form.getIdLocalidade() == null
						|| form.getIdLocalidade().equals("")) {
					throw new ActionServletException("atencao.required", null,
							"Localidade");
				}
			}
		} else {
			throw new ActionServletException("atencao.required", null,
					"Abrangência do Acesso");
		}
		// valida a unidade de lotação
		if (!"".equals(form.getIdLotacao())) {
			if (!(usuarioCadastrar.getUnidadeOrganizacional() != null
					&& usuarioCadastrar.getUnidadeOrganizacional().getId() != null
					&& usuarioCadastrar.getUnidadeOrganizacional().getId()
							.toString().equals(form.getIdLotacao()) && usuarioCadastrar
					.getUnidadeOrganizacional().getDescricao().equals(
							form.getNomeLotacao()))) {

				FiltroUnidadeOrganizacional filtroUnidadeEmpresa = new FiltroUnidadeOrganizacional();
				filtroUnidadeEmpresa.adicionarParametro(new ParametroSimples(
						FiltroUnidadeOrganizacional.ID, form.getIdLotacao()));
				filtroUnidadeEmpresa
						.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroUnidadeEmpresa,
						UnidadeOrganizacional.class.getSimpleName());
				
				
				if ((coll != null && !coll.isEmpty())) {
					UnidadeOrganizacional unidadeEmpresa = (UnidadeOrganizacional) coll
							.iterator().next();

					// caso o usuário que esteja efetuando a inserção não
					// seja
					// do grupo de administradores
					FiltroUsuarioGrupo filtroUsuarioGrupo = new FiltroUsuarioGrupo();

					filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
							FiltroUsuarioGrupo.USUARIO_ID, usuario.getId()));
					filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
							FiltroUsuarioGrupo.GRUPO_ID, Grupo.ADMINISTRADOR));
					Collection colecaoUsuarioGrupo = Fachada.getInstancia()
							.pesquisar(filtroUsuarioGrupo,
									UsuarioGrupo.class.getName());
					if (colecaoUsuarioGrupo == null
							|| colecaoUsuarioGrupo.isEmpty()) {
						// se a unidade de lotacao do usuario que estiver
						// efetuando seja diferente da unidade de
						// lotação informada
						if (usuario.getUnidadeOrganizacional() != null
								&& usuario.getUnidadeOrganizacional().getId() != null
								&& !usuario
										.getUnidadeOrganizacional()
										.getId()
										.equals(
												new Integer(form.getIdLotacao()))) {
							// recupera a unidade do usuário
							FiltroUnidadeOrganizacional filtroUnidadeEmpresaUsuario = new FiltroUnidadeOrganizacional();
							filtroUnidadeEmpresaUsuario
									.adicionarParametro(new ParametroSimples(
											FiltroUnidadeOrganizacional.ID,
											usuario.getUnidadeOrganizacional()
													.getId()));
							filtroUnidadeEmpresaUsuario
									.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
							Collection colecaoUnidadeEmpresa = Fachada
									.getInstancia().pesquisar(
											filtroUnidadeEmpresaUsuario,
											UnidadeOrganizacional.class
													.getName());
							UnidadeOrganizacional unidadeEmpresaUsuario = null;
							if (colecaoUnidadeEmpresa != null
									&& !colecaoUnidadeEmpresa.isEmpty()) {
								unidadeEmpresaUsuario = (UnidadeOrganizacional) Util
										.retonarObjetoDeColecao(colecaoUnidadeEmpresa);
							}
							// se o nivel da unidade de lotação do usuário
							// que
							// estiver efetuando a inserção seja maior ou
							// igual
							// ao nivel de unidade de lotação informada
							if (unidadeEmpresaUsuario != null) {
								if (unidadeEmpresaUsuario.getUnidadeTipo()
										.getNivel() != null
										&& unidadeEmpresa.getUnidadeTipo()
												.getNivel() != null) {
									if (unidadeEmpresaUsuario.getUnidadeTipo()
											.getNivel().intValue() >= unidadeEmpresa
											.getUnidadeTipo().getNivel()
											.intValue()) {
										throw new ActionServletException(
												"atencao.usuario.sem.permissao",
												usuario.getLogin(),
												unidadeEmpresa.getDescricao());
									}
								} else {
									throw new ActionServletException(
											"atencao.usuario.sem.permissao",
											usuario.getLogin(),
											unidadeEmpresa.getDescricao());
								}

								// ou a unidade superior da unidade de
								// lotação
								// informada seja diferente da unidade de
								// lotação do usuário

								// enquanto o nivel superior da unidade de
								// lotação não esteja no mesmo nivel da
								// unidade
								// de lotação do usuário
								boolean mesmoNivel = true;
								int idNivelUsuario = unidadeEmpresaUsuario
										.getUnidadeTipo().getNivel().intValue();
								UnidadeOrganizacional unidadeEmpresaSuperior = null;
								while (mesmoNivel) {
									Integer idUnidadeEmpresaSuperior = null;
									if (unidadeEmpresaSuperior == null) {
										if (unidadeEmpresa.getUnidadeSuperior() != null
												&& !unidadeEmpresa
														.getUnidadeSuperior()
														.equals("")) {
											idUnidadeEmpresaSuperior = unidadeEmpresa
													.getUnidadeSuperior()
													.getId();
										}
									} else {
										if (unidadeEmpresaSuperior
												.getUnidadeSuperior() != null
												&& !unidadeEmpresaSuperior
														.getUnidadeSuperior()
														.equals("")) {
											idUnidadeEmpresaSuperior = unidadeEmpresaSuperior
													.getUnidadeSuperior()
													.getId();
										}
									}
									if (idUnidadeEmpresaSuperior == null) {
										throw new ActionServletException(
												"atencao.usuario.sem.permissao",
												usuario.getLogin(),
												unidadeEmpresa.getDescricao());
									}
									// recupera a unidade do usuário
									FiltroUnidadeOrganizacional filtroUnidadeEmpresaSuperior = new FiltroUnidadeOrganizacional();
									filtroUnidadeEmpresaSuperior
											.adicionarParametro(new ParametroSimples(
													FiltroUnidadeOrganizacional.ID,
													idUnidadeEmpresaSuperior));
									filtroUnidadeEmpresaSuperior
											.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
									Collection colecaoUnidadeEmpresaSuperior = Fachada
											.getInstancia()
											.pesquisar(
													filtroUnidadeEmpresaSuperior,
													UnidadeOrganizacional.class
															.getName());
									if (colecaoUnidadeEmpresaSuperior != null
											&& !colecaoUnidadeEmpresaSuperior
													.isEmpty()) {
										unidadeEmpresaSuperior = (UnidadeOrganizacional) Util
												.retonarObjetoDeColecao(colecaoUnidadeEmpresaSuperior);
									}
									if (unidadeEmpresaSuperior != null) {
										if (unidadeEmpresaSuperior
												.getUnidadeTipo().getNivel() == null
												|| unidadeEmpresaSuperior
														.getUnidadeTipo()
														.getNivel().equals("")) {
											throw new ActionServletException(
													"atencao.usuario.sem.permissao",
													usuario.getLogin(),
													unidadeEmpresa.getDescricao());

										}
										// caso seja o mesmo nivel
										if (unidadeEmpresaSuperior
												.getUnidadeTipo().getNivel()
												.intValue() == idNivelUsuario) {
											mesmoNivel = false;
											// caso o id da unidade empresa
											// informado for diferente do id
											// da
											// unidade empresa do usuário no
											// mesmo nivel
											if (!unidadeEmpresaSuperior.getId()
													.equals(
															unidadeEmpresaUsuario
																	.getId())) {
												throw new ActionServletException(
														"atencao.usuario.sem.permissao",
														usuario.getLogin(),
														unidadeEmpresa.getDescricao());
											}

										}
									}
								}

							}

						}
					}
					usuarioCadastrar.setUnidadeOrganizacional(unidadeEmpresa);
				} else {
					usuarioCadastrar.setUnidadeOrganizacional(null);
				}
			}
		}

		
		Integer[] idGrupos = null;

		//Não adiciona os grupos em caso de seleção do indicador batch e/ou internet
		if(usuarioCadastrar.getIndicadorUsuarioBatch() == 2 && usuarioCadastrar.getIndicadorUsuarioInternet() == 2 ){
			String[] grupo = form.getGrupo();

			if (grupo != null) {
				idGrupos = new Integer[grupo.length];
				for (int i = 0; i < idGrupos.length; i++) {
					idGrupos[i] = new Integer(grupo[i]);
				}
			}
		}
		
		
		
		
		usuarioCadastrar.setCpf(form.getCpf());
		
		UsuarioAbrangencia usuarioAbragencia = new UsuarioAbrangencia();
		usuarioAbragencia.setId(new Integer(form.getAbrangencia()));
		
		usuarioCadastrar.setUsuarioAbrangencia(usuarioAbragencia);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		
		Fachada.getInstancia().inserirUsuario(usuarioCadastrar, idGrupos, usuarioLogado, idSolicitacaoAcesso);
		
		if(usuario.getId() != null && usuario.getId().equals(usuarioCadastrar.getId())){
			sessao.setAttribute("usuarioLogado",usuarioCadastrar);
		}

		montarPaginaSucesso(httpServletRequest, "Usuário de Login "
				+ usuarioCadastrar.getLogin() + " inserido com sucesso!",
				"Inserir outro Usuário",
				"exibirInserirUsuarioAction.do?menu=sim");

		// limpa a sessão
		sessao.removeAttribute("usuarioCadastrar");
		sessao.removeAttribute("grupo");
		sessao.removeAttribute("collEmpresa");
		sessao.removeAttribute("collUsuarioTipo");
		sessao.removeAttribute("collUsuarioAbrangencia");
		sessao.removeAttribute("collGrupo");
		sessao.removeAttribute("collGerenciaRegional");
		sessao.removeAttribute("collUnidadeNegocio");

		return retorno;
	}
}
