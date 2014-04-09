package gcom.gui.seguranca.acesso.usuario;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroGrupo;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.usuario.FiltroUsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.FiltroUsuarioGrupo;
import gcom.seguranca.acesso.usuario.FiltroUsuarioSituacao;
import gcom.seguranca.acesso.usuario.FiltroUsuarioTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.UsuarioGrupo;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAtualizarUsuarioDadosGeraisAction extends GcomAction {

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

		// FachadaBatch.getInstancia().gerarResumoSituacaoEspecialFaturamento();

		ActionForward retorno = actionMapping.findForward("atualizarDadosGerais");

		AtualizarUsuarioDadosGeraisActionForm form = (AtualizarUsuarioDadosGeraisActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		// Usuario que vai ser cadastrado no sistema, usado só nessa funcionalidade
		Usuario usuarioParaAtualizar = (Usuario) sessao.getAttribute("usuarioParaAtualizar");

		if (usuarioParaAtualizar == null) {
			usuarioParaAtualizar = new Usuario();
		}

		if (usuarioParaAtualizar != null) {

			if ("".equals(form.getUsuarioTipo())) {

				// se o form nao tiver o usuario tipo
				if (usuarioParaAtualizar.getUsuarioTipo() != null && usuarioParaAtualizar.getUsuarioTipo().getId() != null) {
					form.setUsuarioTipo(usuarioParaAtualizar.getUsuarioTipo().getId().toString());
					form.setIndicadorFuncionario(""	+ usuarioParaAtualizar.getUsuarioTipo().getIndicadorFuncionario());
					form.setParmsUsuarioTipo(form.getUsuarioTipo() + ";" + form.getIndicadorFuncionario());
				}

			} else {

				UsuarioTipo usuarioTipo = new UsuarioTipo();
				usuarioTipo.setId(new Integer(form.getUsuarioTipo()));
				usuarioTipo.setIndicadorFuncionario(new Short(form.getIndicadorFuncionario()));
				usuarioParaAtualizar.setUsuarioTipo(usuarioTipo);

				/*
				 * // indica se o tipo de usuario eh funcionario if
				 * (UsuarioTipo.USUARIO_TIPO_FUNCIONARIO.equals(usuarioParaAtualizar
				 * .getUsuarioTipo().getId())) {
				 * form.setUsuarioTipoFuncionario("true"); form.setNome("");
				 * form.setIdLotacao(""); form.setNomeLotacao(""); } else {
				 * form.setUsuarioTipoFuncionario("false");
				 * form.setIdFuncionario(""); form.setNomeFuncionario(""); }
				 */
			}

			if (usuarioParaAtualizar.getEmpresa() != null && usuarioParaAtualizar.getEmpresa().getId() != null) {
				form.setEmpresa(usuarioParaAtualizar.getEmpresa().getId().toString());
			}

			if ("".equals(form.getIdFuncionario())) {
				
				if (usuarioParaAtualizar.getFuncionario() != null) {
					
					if (usuarioParaAtualizar.getFuncionario().getId() != null)
						form.setIdFuncionario(usuarioParaAtualizar.getFuncionario().getId().toString());
					
					if (usuarioParaAtualizar.getFuncionario().getNome() != null)
						form.setNomeFuncionario(usuarioParaAtualizar.getFuncionario().getNome());
					
					if (usuarioParaAtualizar.getFuncionario().getNumeroCpf() != null){
						form.setCpf(usuarioParaAtualizar.getFuncionario().getNumeroCpf());
					}else{
						form.setCpf("");
					}
					
					if (usuarioParaAtualizar.getFuncionario().getDataNascimento() != null){
						form.setDataNascimento(Util.formatarData(usuarioParaAtualizar.getFuncionario().getDataNascimento()));
					}else{
						form.setDataNascimento("");
					}

					
				}
			} else if (form.getIdFuncionario() != null	
					&& (usuarioParaAtualizar.getFuncionario() == null
						|| (usuarioParaAtualizar.getFuncionario() != null && usuarioParaAtualizar.getFuncionario().getId() != null 
							&& !form.getIdFuncionario().equals(usuarioParaAtualizar.getFuncionario().getId().toString())))) {

				FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
				filtroFuncionario.adicionarParametro(new ParametroSimples(FiltroFuncionario.ID, form.getIdFuncionario()));
				filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionario.UNIDADE_ORGANIZACIONAL);
				Collection coll = Fachada.getInstancia().pesquisar(filtroFuncionario, Funcionario.class.getSimpleName());
				
				if (coll != null && !coll.isEmpty()) {
					Funcionario f = (Funcionario) coll.iterator().next();
					usuarioParaAtualizar.setFuncionario(f);
					form.setIdFuncionario(f.getId().toString());
					form.setNomeFuncionario(f.getNome());
					form.setLogin(f.getId().toString());
					form.setFuncionarioNaoEncontrada("false");
					

					if (f.getNumeroCpf() != null){
						form.setCpf(f.getNumeroCpf());
					}else{
						form.setCpf("");
					}
					
					if (f.getDataNascimento() != null){
						form.setDataNascimento(Util.formatarData(f.getDataNascimento()));
					}else{
						form.setDataNascimento("");
					}
					
				} else {
					usuarioParaAtualizar.setFuncionario(null);
					form.setIdFuncionario("");
					form.setNomeFuncionario("Funcionario inexistente.");
					form.setNome("");
					form.setFuncionarioNaoEncontrada("true");
					form.setLogin("");
					

					if (usuarioParaAtualizar.getCpf() != null){
						form.setCpf(usuarioParaAtualizar.getCpf());
					}else{
						form.setCpf("");
					}
					
					if (usuarioParaAtualizar.getDataNascimento() != null){
						form.setDataNascimento(Util.formatarData(usuarioParaAtualizar.getDataNascimento()));
					}else{
						form.setDataNascimento("");
					}
					
				}
			}

			
//			Alterado por Vivianne Sousa  13/02/2007
			//solicitado por Leonardo Vieira
			// caso for funcionario pegar os dados a partir do funcionario
//			if (usuarioParaAtualizar != null && usuarioParaAtualizar.getUsuarioTipo() != null
//					&& usuarioParaAtualizar.getUsuarioTipo().getId() != null
//					&& usuarioParaAtualizar.getUsuarioTipo().getIndicadorFuncionario() == UsuarioTipo.INDICADOR_FUNCIONARIO) {

//				if (usuarioParaAtualizar.getFuncionario() != null) {
//
//					if (usuarioParaAtualizar.getFuncionario().getNome() != null)
//						form.setNome(usuarioParaAtualizar.getFuncionario().getNome());
//
//					if (usuarioParaAtualizar.getFuncionario().getUnidadeOrganizacional() != null
//							&& (form.getIdLotacao() == null || form.getIdLotacao().equals(""))) {
//						if (usuarioParaAtualizar.getFuncionario().getUnidadeOrganizacional().getId() != null )
//							form.setIdLotacao(usuarioParaAtualizar.getFuncionario().getUnidadeOrganizacional().getId().toString());
//						if (usuarioParaAtualizar.getFuncionario().getUnidadeOrganizacional().getDescricao() != null)
//							form.setNomeLotacao(usuarioParaAtualizar.getFuncionario().getUnidadeOrganizacional().getDescricao());
//					}
//				}

//			} else {

				if ("".equals(form.getIdLotacao())) {
					// caso for funcionario pegar os dados a partir do funcionario
					if (usuarioParaAtualizar != null && usuarioParaAtualizar.getUsuarioTipo() != null
							&& usuarioParaAtualizar.getUsuarioTipo().getId() != null
							&& usuarioParaAtualizar.getUsuarioTipo().getIndicadorFuncionario() == UsuarioTipo.INDICADOR_FUNCIONARIO) {

						if (usuarioParaAtualizar.getFuncionario() != null) {

							if (usuarioParaAtualizar.getFuncionario().getNome() != null)
								form.setNome(usuarioParaAtualizar.getFuncionario().getNome());

							if (usuarioParaAtualizar.getFuncionario().getUnidadeOrganizacional() != null && (form.getIdLotacao() == null || form.getIdLotacao().equals(""))) {
								if (usuarioParaAtualizar.getFuncionario().getUnidadeOrganizacional().getId() != null )
									form.setIdLotacao(usuarioParaAtualizar.getFuncionario().getUnidadeOrganizacional().getId().toString());
								if (usuarioParaAtualizar.getFuncionario().getUnidadeOrganizacional().getDescricao() != null)
									form.setNomeLotacao(usuarioParaAtualizar.getFuncionario().getUnidadeOrganizacional().getDescricao());
							}
						}

					} else if (usuarioParaAtualizar.getUnidadeOrganizacional() != null) {
						if (usuarioParaAtualizar.getUnidadeOrganizacional().getId() != null)
							form.setIdLotacao(usuarioParaAtualizar.getUnidadeOrganizacional().getId().toString());
						if (usuarioParaAtualizar.getUnidadeOrganizacional().getDescricao() != null)
							form.setNomeLotacao(usuarioParaAtualizar.getUnidadeOrganizacional().getDescricao());
					}
				} else if (form.getIdLotacao() != null	&& !form.getIdLotacao().equals("")
						&& ((usuarioParaAtualizar.getUnidadeOrganizacional() == null) 
						|| (usuarioParaAtualizar.getUnidadeOrganizacional() != null
						&& usuarioParaAtualizar.getUnidadeOrganizacional().getId() != null 
						/*&& !form.getIdLotacao().equals(usuarioParaAtualizar.getUnidadeOrganizacional().getId().toString())*/))) {

					FiltroUnidadeOrganizacional filtroUnidadeEmpresa = new FiltroUnidadeOrganizacional();
					filtroUnidadeEmpresa.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, form.getIdLotacao()));
					filtroUnidadeEmpresa.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
					Collection coll = Fachada.getInstancia().pesquisar(filtroUnidadeEmpresa,UnidadeOrganizacional.class.getSimpleName());
					
					if (coll != null && !coll.isEmpty()) {
						UnidadeOrganizacional unidadeEmpresa = (UnidadeOrganizacional) coll.iterator().next();

						// caso o usuário que esteja efetuando a inserção não
						// seja do grupo de administradores
						FiltroUsuarioGrupo filtroUsuarioGrupo = new FiltroUsuarioGrupo();

						filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(FiltroUsuarioGrupo.USUARIO_ID, usuario.getId()));
						filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(FiltroUsuarioGrupo.GRUPO_ID,Grupo.ADMINISTRADOR));
						Collection colecaoUsuarioGrupo = Fachada.getInstancia().pesquisar(filtroUsuarioGrupo,UsuarioGrupo.class.getName());
						if (colecaoUsuarioGrupo == null || colecaoUsuarioGrupo.isEmpty()) {
							// se a unidade de lotacao do usuario que estiver
							// efetuando seja diferente da unidade de lotação informada
							if (usuario.getUnidadeOrganizacional() != null
									&& usuario.getUnidadeOrganizacional().getId() != null
									&& !usuario.getUnidadeOrganizacional().getId().equals(new Integer(form.getIdLotacao()))) {
								// recupera a unidade do usuário
								FiltroUnidadeOrganizacional filtroUnidadeEmpresaUsuario = new FiltroUnidadeOrganizacional();
								filtroUnidadeEmpresaUsuario.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID,	usuario.getUnidadeOrganizacional().getId()));
								filtroUnidadeEmpresaUsuario.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
								Collection colecaoUnidadeEmpresa = Fachada.getInstancia().pesquisar(filtroUnidadeEmpresaUsuario, UnidadeOrganizacional.class.getName());
								UnidadeOrganizacional unidadeEmpresaUsuario = null;
								if (colecaoUnidadeEmpresa != null && !colecaoUnidadeEmpresa.isEmpty()) {
									unidadeEmpresaUsuario = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeEmpresa);
								}
								// se o nivel da unidade de lotação do usuário que
								// estiver efetuando a inserção seja maior ou igual
								// ao nivel de unidade de lotação informada
								if (unidadeEmpresaUsuario != null) {
									if (unidadeEmpresaUsuario.getUnidadeTipo().getNivel() != null && unidadeEmpresa.getUnidadeTipo().getNivel() != null) {
										if (unidadeEmpresaUsuario.getUnidadeTipo().getNivel().intValue() >= unidadeEmpresa.getUnidadeTipo().getNivel().intValue()) {
											throw new ActionServletException("atencao.usuario.sem.permissao", usuario.getLogin(),unidadeEmpresa.getDescricao());
										}
									} else {
										throw new ActionServletException("atencao.usuario.sem.permissao", usuario.getLogin(),unidadeEmpresa.getDescricao());
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
									int idNivelUsuario = unidadeEmpresaUsuario.getUnidadeTipo().getNivel().intValue();
									UnidadeOrganizacional unidadeEmpresaSuperior = null;
									while (mesmoNivel) {
										Integer idUnidadeEmpresaSuperior = null;
										if (unidadeEmpresaSuperior == null) {
											if (unidadeEmpresa.getUnidadeSuperior() != null
													&& !unidadeEmpresa.getUnidadeSuperior().equals("")) {
												idUnidadeEmpresaSuperior = unidadeEmpresa.getUnidadeSuperior().getId();
											}
										} else {
											if (unidadeEmpresaSuperior.getUnidadeSuperior() != null
													&& !unidadeEmpresaSuperior.getUnidadeSuperior().equals("")) {
												idUnidadeEmpresaSuperior = unidadeEmpresaSuperior.getUnidadeSuperior().getId();
											}
										}
										if (idUnidadeEmpresaSuperior == null) {
											throw new ActionServletException("atencao.usuario.sem.permissao", usuario.getLogin(),unidadeEmpresa.getDescricao());
										}
										// recupera a unidade do usuário
										FiltroUnidadeOrganizacional filtroUnidadeEmpresaSuperior = new FiltroUnidadeOrganizacional();
										filtroUnidadeEmpresaSuperior.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID,	idUnidadeEmpresaSuperior));
										filtroUnidadeEmpresaSuperior.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
										Collection colecaoUnidadeEmpresaSuperior = Fachada.getInstancia().pesquisar(
														filtroUnidadeEmpresaSuperior, UnidadeOrganizacional.class.getName());
										if (colecaoUnidadeEmpresaSuperior != null
												&& !colecaoUnidadeEmpresaSuperior.isEmpty()) {
											unidadeEmpresaSuperior = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeEmpresaSuperior);
										}
										if (unidadeEmpresaSuperior != null) {
											if (unidadeEmpresaSuperior.getUnidadeTipo().getNivel() == null
													|| unidadeEmpresaSuperior.getUnidadeTipo().getNivel().equals("")) {
												throw new ActionServletException("atencao.usuario.sem.permissao",
														usuario.getLogin(),unidadeEmpresa.getDescricao());

											}
											// caso seja o mesmo nivel
											if (unidadeEmpresaSuperior.getUnidadeTipo().getNivel().intValue() == idNivelUsuario) {
												mesmoNivel = false;
												// caso o id da unidade empresa informado for diferente do id
												// da unidade empresa do usuário no mesmo nivel
												if (!unidadeEmpresaSuperior.getId().equals(unidadeEmpresaUsuario.getId())) {
													throw new ActionServletException("atencao.usuario.sem.permissao",
															usuario.getLogin(), unidadeEmpresa.getDescricao());
												}

											}
										}
									}

								}

							}
						}

						usuarioParaAtualizar.setUnidadeOrganizacional(unidadeEmpresa);
						form.setIdLotacao(unidadeEmpresa.getId().toString());
						form.setNomeLotacao(unidadeEmpresa.getDescricao());
						form.setLotacaoNaoEncontrada("false");
						
						if (usuarioParaAtualizar.getUsuarioTipo().getIndicadorFuncionario() == UsuarioTipo.INDICADOR_FUNCIONARIO){
							Funcionario funcionario = usuarioParaAtualizar.getFuncionario();
							funcionario.setUnidadeOrganizacional(unidadeEmpresa);
							usuarioParaAtualizar.setFuncionario(funcionario);
						}
						
					} else {
						usuarioParaAtualizar.setUnidadeOrganizacional(null);
						form.setIdLotacao("");
						form.setNomeLotacao("Lotação inexistente.");
						form.setLotacaoNaoEncontrada("true");
						
					}
				} else if (usuarioParaAtualizar.getUsuarioTipo().getIndicadorFuncionario() != UsuarioTipo.INDICADOR_FUNCIONARIO){
					if (usuarioParaAtualizar.getUnidadeOrganizacional() != null
							&& !usuarioParaAtualizar.getUnidadeOrganizacional().equals("")) {
						form.setNomeLotacao(usuarioParaAtualizar.getUnidadeOrganizacional().getDescricao());
					}
				}
			 
				if (usuarioParaAtualizar.getNomeUsuario() != null)
					form.setNome(usuarioParaAtualizar.getNomeUsuario());
				if (form.getDataInicial() == null || form.getDataInicial().equals("")){
					
					if (usuarioParaAtualizar.getDataCadastroInicio() != null)
						form.setDataInicial(Util.formatarData(usuarioParaAtualizar.getDataCadastroInicio()));
				}
				if (form.getDataFinal() == null || form.getDataFinal().equals("")){
					
					if (usuarioParaAtualizar.getDataCadastroFim() != null)
						form.setDataFinal(Util.formatarData(usuarioParaAtualizar.getDataCadastroFim()));
				}	
			}
		
		
			if(form.getIdFuncionario() == null || form.getIdFuncionario().equals("")){
				
				if (usuarioParaAtualizar.getCpf() != null)
					form.setCpf(usuarioParaAtualizar.getCpf());
					
				if (usuarioParaAtualizar.getDataNascimento() != null)
					form.setDataNascimento(Util.formatarData(usuarioParaAtualizar.getDataNascimento()));
			}
	
			if (usuarioParaAtualizar.getLogin() != null)
				form.setLogin(usuarioParaAtualizar.getLogin());
			if (form.getEmail() == null || form.getEmail().equals("")){
				
				if (usuarioParaAtualizar.getDescricaoEmail() != null)
					form.setEmail(usuarioParaAtualizar.getDescricaoEmail());
			}
			
			
			form.setEmail(form.getEmail().toLowerCase());
			
			if(usuarioParaAtualizar.getIndicadorUsuarioBatch() != null){
				form.setIndicadorUsuarioBatch(usuarioParaAtualizar.getIndicadorUsuarioBatch());
			}
			if(usuarioParaAtualizar.getIndicadorUsuarioInternet() != null){
				form.setIndicadorUsuarioInternet(usuarioParaAtualizar.getIndicadorUsuarioInternet());
			}
//		}

		if (sessao.getAttribute("collEmpresa") == null || !sessao.getAttribute("collEmpresa").equals("")) {

			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();

			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.INDICADORUSO,	ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection coll = Fachada.getInstancia().pesquisar(filtroEmpresa, Empresa.class.getSimpleName());

			if (coll == null || coll.isEmpty()) {
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null," Empresa está ");
			}

			sessao.setAttribute("collEmpresa", coll);
		}

		if (sessao.getAttribute("collUsuarioTipo") == null || !sessao.getAttribute("collUsuarioTipo").equals("")) {

			FiltroUsuarioTipo filtroUsuarioTipo = new FiltroUsuarioTipo();

			// caso o usuário não seja administrador então
			if (!usuario.getUsuarioTipo().getId().equals(UsuarioTipo.USUARIO_TIPO_ADMINISTRADOR)) {
				// não seta na coleção de usuário tipo o tipo administrador
		
				filtroUsuarioTipo.adicionarParametro(new ParametroSimplesDiferenteDe(
								FiltroUsuarioTipo.ID, UsuarioTipo.USUARIO_TIPO_ADMINISTRADOR));
			}

			filtroUsuarioTipo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection coll = Fachada.getInstancia().pesquisar(filtroUsuarioTipo, UsuarioTipo.class.getSimpleName());
			if (coll == null || coll.isEmpty()) {
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, " Tipo do Usúario está ");
			}

			sessao.setAttribute("collUsuarioTipo", coll);
		}

		// carregando 2º aba
		String[] grupo = (String[]) sessao.getAttribute("grupo");
		if (usuarioParaAtualizar != null) {

			 if (grupo != null) {
			 form.setGrupo(grupo);
			 }

			if (usuarioParaAtualizar.getUsuarioAbrangencia() != null && usuarioParaAtualizar.getUsuarioAbrangencia().getId() != null)
				form.setAbrangencia(usuarioParaAtualizar.getUsuarioAbrangencia().getId().toString());
			if (usuarioParaAtualizar.getGerenciaRegional() != null && usuarioParaAtualizar.getGerenciaRegional().getId() != null)
				form.setGerenciaRegional(usuarioParaAtualizar.getGerenciaRegional().getId().toString());

			if (usuarioParaAtualizar.getUnidadeNegocio() != null && usuarioParaAtualizar.getUnidadeNegocio().getId() != null)
				form.setUnidadeNegocio(usuarioParaAtualizar.getUnidadeNegocio().getId().toString());

			if ("".equals(form.getIdElo())) {
				if (usuarioParaAtualizar.getLocalidadeElo() != null) {
					if (usuarioParaAtualizar.getLocalidadeElo().getId() != null)
						form.setIdElo(usuarioParaAtualizar.getLocalidadeElo().getId().toString());
					if (usuarioParaAtualizar.getLocalidadeElo().getDescricao() != null)
						form.setNomeElo(usuarioParaAtualizar.getLocalidadeElo().getDescricao());
				}
			}

			if ("".equals(form.getIdLocalidade())) {
				if (usuarioParaAtualizar.getLocalidade() != null) {
					if (usuarioParaAtualizar.getLocalidade().getId() != null)
						form.setIdLocalidade(usuarioParaAtualizar.getLocalidade().getId().toString());
					if (usuarioParaAtualizar.getLocalidade().getDescricao() != null)
						form.setNomeLocalidade(usuarioParaAtualizar.getLocalidade().getDescricao());
				}
			}
			
			if (usuarioParaAtualizar.getUsuarioSituacao() != null && usuarioParaAtualizar.getUsuarioSituacao().getId() != null)
				form.setSituacao(usuarioParaAtualizar.getUsuarioSituacao().getId().toString());
		}

		Collection colecaoUsuarioAbrangencia = null;
		if (sessao.getAttribute("collUsuarioAbrangencia") == null || sessao.getAttribute("collUsuarioAbrangencia").equals("")) {

			// caso o usuário que esteja efetuando a inserção não
			// seja do grupo de administradores
			FiltroUsuarioGrupo filtroUsuarioGrupo = new FiltroUsuarioGrupo();

			filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(FiltroUsuarioGrupo.USUARIO_ID, usuario.getId()));
			filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(FiltroUsuarioGrupo.GRUPO_ID, Grupo.ADMINISTRADOR));
			Collection colecaoUsuarioGrupo = Fachada.getInstancia().pesquisar(filtroUsuarioGrupo, UsuarioGrupo.class.getName());
			if (colecaoUsuarioGrupo != null && !colecaoUsuarioGrupo.isEmpty()) {

				colecaoUsuarioAbrangencia = Fachada.getInstancia().pesquisar(
						new FiltroUsuarioAbrangencia(), UsuarioAbrangencia.class.getSimpleName());
			} else {
				Integer idUsuarioAbrangencia = usuario.getUsuarioAbrangencia().getId();
				FiltroUsuarioAbrangencia filtroUsuarioAbrangencia = new FiltroUsuarioAbrangencia();
				filtroUsuarioAbrangencia.adicionarParametro(new ParametroSimples(FiltroUsuarioAbrangencia.ID, idUsuarioAbrangencia));
				colecaoUsuarioAbrangencia = Fachada.getInstancia().pesquisar(filtroUsuarioAbrangencia, UsuarioAbrangencia.class.getName());
				// caso exista mais de uma abrangência na pesquisa passando o id da abrangência como superior
				boolean mesmoNivel = true;
				// cria um boolean para verificar se existe o ususário abrangencia
				// que está sendo atualizado na coleção de usuarios abrangencia
				// permitido para o usuário logado do usuário
				boolean achouUsuarioAbrangencia = false;
				Collection usuarioAbrangenciaParaPesquisa = new ArrayList();
				Iterator iteratorUsuarioAbrangencia = colecaoUsuarioAbrangencia.iterator();
				while (iteratorUsuarioAbrangencia.hasNext()) {
					UsuarioAbrangencia usuarioAbrangencia = (UsuarioAbrangencia) iteratorUsuarioAbrangencia.next();
					if (usuarioParaAtualizar.getUsuarioAbrangencia() != null && usuarioAbrangencia.getId().equals(usuarioParaAtualizar.getUsuarioAbrangencia().getId())) {
						achouUsuarioAbrangencia = true;
					}
				}
				usuarioAbrangenciaParaPesquisa.addAll(colecaoUsuarioAbrangencia);
				// enquanto exista mais usuário abrangência(s)
				while (mesmoNivel) {
					Collection colecaoUsuarioAbrangenciaPorSuperior = Fachada.getInstancia()
							.pesquisarUsuarioAbrangenciaPorSuperior(usuarioAbrangenciaParaPesquisa,	idUsuarioAbrangencia);
					if (colecaoUsuarioAbrangenciaPorSuperior != null && !colecaoUsuarioAbrangenciaPorSuperior.isEmpty()) {
						Iterator iteratorUsuarioAbrangenciaSuperior = colecaoUsuarioAbrangenciaPorSuperior.iterator();
						// verifica se existe na coleção de usuarios
						// abrangências o usuario abrangência do usuario atualizar
						while (iteratorUsuarioAbrangenciaSuperior.hasNext()) {
							UsuarioAbrangencia usuarioAbrangencia = (UsuarioAbrangencia) iteratorUsuarioAbrangenciaSuperior.next();
							if (usuarioParaAtualizar.getUsuarioAbrangencia() != null
									&& usuarioAbrangencia.getId().equals(usuarioParaAtualizar.getUsuarioAbrangencia().getId())) {
								achouUsuarioAbrangencia = true;
							}
						}

						colecaoUsuarioAbrangencia.addAll(colecaoUsuarioAbrangenciaPorSuperior);
						usuarioAbrangenciaParaPesquisa = new ArrayList();
						usuarioAbrangenciaParaPesquisa.addAll(colecaoUsuarioAbrangenciaPorSuperior);
					} else {
						mesmoNivel = false;
					}
				}
				// se não achou o usuário abrangencia do usuário que está
				// atualizando na coleção de usuários abrangencias então inseri
				// essa abrangencia na coleção e não permite alteração
				if (!achouUsuarioAbrangencia) {
					filtroUsuarioAbrangencia = new FiltroUsuarioAbrangencia();
					filtroUsuarioAbrangencia.adicionarParametro(new ParametroSimples(FiltroUsuarioAbrangencia.ID,
									usuarioParaAtualizar.getUsuarioAbrangencia().getId()));
					Collection colecaoUsuarioAbrangenciaAdicionar = Fachada.getInstancia().pesquisar(filtroUsuarioAbrangencia,UsuarioAbrangencia.class.getName());
					colecaoUsuarioAbrangencia.addAll(colecaoUsuarioAbrangenciaAdicionar);
					sessao.setAttribute("desabilitaUsuarioAbrangencia", "1");

				}
			}
			// caso exista mais de uma abrangência na pesquisa passando o id da
			// abrangência como superior

			sessao.setAttribute("collUsuarioAbrangencia", colecaoUsuarioAbrangencia);

			Collection colecaoGrupo = null;
			// caso o usuário que esteja efetuando a inserção não
			// seja
			// do grupo de administradores
			if (colecaoUsuarioGrupo != null && !colecaoUsuarioGrupo.isEmpty()) {

				FiltroGrupo filtroGrupo = new FiltroGrupo();
				filtroGrupo.setCampoOrderBy(FiltroGrupo.DESCRICAO);
				filtroGrupo.adicionarParametro(new ParametroSimples(FiltroGrupo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

				colecaoGrupo = Fachada.getInstancia().pesquisar(filtroGrupo, Grupo.class.getSimpleName());
			} else {
				Collection colecaoGrupoUsuario = (Collection)sessao.getAttribute("colecaoGruposUsuario");
				colecaoGrupo = new ArrayList();
				Collection colecaoGrupoParaSelecao = Fachada.getInstancia().pesquisarGruposUsuarioAcesso(colecaoGrupoUsuario);
				colecaoGrupo.addAll(colecaoGrupoParaSelecao);

				boolean primeiraVez = true;
				String grupoNaoDesabilitado = "";
				for (int i = 0; i < grupo.length; i++) {
					boolean achouGrupo = true;
					Iterator iteratorGrupo = colecaoGrupoParaSelecao.iterator();
					while (iteratorGrupo.hasNext()) {
						Grupo grupoParaAtualizar = (Grupo) iteratorGrupo.next();
						if (new Integer(grupo[i]).equals(grupoParaAtualizar.getId())) {
							achouGrupo = false;
						}
					}
					if (achouGrupo) {
						if (primeiraVez) {
							grupoNaoDesabilitado = grupoNaoDesabilitado	+ grupo[i];
							primeiraVez = false;
						} else {
							grupoNaoDesabilitado = grupoNaoDesabilitado + "," + grupo[i];
						}
						FiltroGrupo filtroGrupo = new FiltroGrupo();
						filtroGrupo.setCampoOrderBy(FiltroGrupo.DESCRICAO);
						filtroGrupo.adicionarParametro(new ParametroSimples(FiltroGrupo.ID, grupo[i]));
						Collection colecaoGrupoSelecionado = Fachada.getInstancia().pesquisar(filtroGrupo, Grupo.class.getName());
						Grupo grupoSelecionado = (Grupo) Util.retonarObjetoDeColecao(colecaoGrupoSelecionado);
						colecaoGrupo.add(grupoSelecionado);
					}

				}
				form.setGrupoNaoDesabilitados(grupoNaoDesabilitado);
			}

			sessao.setAttribute("collGrupo", colecaoGrupo);

		}

		if (sessao.getAttribute("collGerenciaRegional") == null || sessao.getAttribute("collGerenciaRegional").equals("")) {

			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoGerenciaRegional = Fachada.getInstancia()
					.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getSimpleName());
			sessao.setAttribute("collGerenciaRegional",	colecaoGerenciaRegional);
		}

		if (sessao.getAttribute("collUnidadeNegocio") == null
				|| sessao.getAttribute("collUnidadeNegocio").equals("")) {

			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoUnidadeNegocio = Fachada.getInstancia()
					.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getSimpleName());
			sessao.setAttribute("collUnidadeNegocio", colecaoUnidadeNegocio);
		}
		
		if (sessao.getAttribute("collUsuarioSituacao") == null
				|| sessao.getAttribute("collUsuarioSituacao").equals("")) {

			FiltroUsuarioSituacao filtroUsuarioSituacao = new FiltroUsuarioSituacao(FiltroUsuarioSituacao.DESCRICAO);
			filtroUsuarioSituacao.adicionarParametro(new ParametroSimples(
					FiltroUsuarioSituacao.INDICADOR_DE_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoUsuarioSituacao = Fachada.getInstancia()
					.pesquisar(filtroUsuarioSituacao, UsuarioSituacao.class.getSimpleName());
			sessao.setAttribute("collUsuarioSituacao", colecaoUsuarioSituacao);
		}

		sessao.setAttribute("usuarioParaAtualizar", usuarioParaAtualizar);

		sessao.setAttribute("usuario", usuario);
		
		//DATA CORRENTE
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar dataCorrente = new GregorianCalendar();
        
        httpServletRequest.setAttribute("dataAtual", formatoData
        .format(dataCorrente.getTime()));
        
        //IDADE MÍNIMA
        httpServletRequest.setAttribute("idadeMinimaFuncionario", ConstantesSistema.IDADE_MINIMA_FUNCIONARIO);
        httpServletRequest.setAttribute("idadeMinimaUsuario", ConstantesSistema.IDADE_MINIMA_USUARIO);

		return retorno;
	}
}
