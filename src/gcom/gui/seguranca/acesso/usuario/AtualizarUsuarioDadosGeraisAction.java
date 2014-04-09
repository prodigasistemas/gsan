package gcom.gui.seguranca.acesso.usuario;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
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
import gcom.seguranca.acesso.usuario.UsuarioGrupo;
import gcom.seguranca.acesso.usuario.UsuarioTipo;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que exibe o menu
 * 
 * @author Administrador
 * @date 02/05/2006
 */
public class AtualizarUsuarioDadosGeraisAction extends GcomAction {

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

		AtualizarUsuarioDadosGeraisActionForm form = (AtualizarUsuarioDadosGeraisActionForm) actionForm;

		ActionForward retorno = actionMapping
				.findForward("gerenciadorProcesso");
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		// Usuario que vai ser cadastrado no sistema, usado só nessa
		// funcionalidade
		Usuario usuarioParaAtualizar = (Usuario) sessao
				.getAttribute("usuarioParaAtualizar");

		if (usuarioParaAtualizar == null) {
			usuarioParaAtualizar = new Usuario();
		}

		if (!"".equals(form.getDataInicial())) {
			Date data = Util.converteStringParaDate(form.getDataInicial());
			if (data == null) {
				throw new ActionServletException("atencao.data.inicio.invalida");
			}
			if (data.getTime() > new Date(System.currentTimeMillis()).getTime()) {
				throw new ActionServletException(
						"atencao.data_inicial.posterior.hoje", null, Util
								.formatarData(new Date()));
			}
		}
		if (!"".equals(form.getDataFinal())) {
			Date data = Util.converteStringParaDate(form.getDataFinal());
			if (data == null) {
				throw new ActionServletException("atencao.data.final.invalida");
			}
			Calendar dataFim = new GregorianCalendar();
			dataFim.setTime(data);
			dataFim.set(Calendar.HOUR, 23);
			dataFim.set(Calendar.MINUTE, 59);
			dataFim.set(Calendar.SECOND, 59);
			data = dataFim.getTime();
			
			if (data.getTime() < new Date(System.currentTimeMillis()).getTime()) {
				throw new ActionServletException(
						"atencao.data_final.posterior.hoje", null, Util
								.formatarData(new Date()));
			}
		}
		if (!"".equals(form.getDataInicial())
				&& !"".equals(form.getDataFinal())) {
			Date dataInicial = Util.converteStringParaDate(form
					.getDataInicial());
			Date dataFinal = Util.converteStringParaDate(form.getDataFinal());

			if (dataFinal.getTime() < dataInicial.getTime()) {
				throw new ActionServletException(
						"atencao.data.intervalo.invalido", null, Util
								.formatarData(new Date()));
			}
			usuarioParaAtualizar.setDataCadastroInicio(dataInicial);
			usuarioParaAtualizar.setDataCadastroFim(dataFinal);
		}

		if (!"".equals(form.getLogin())
				&& !form.getLogin().equalsIgnoreCase(
						usuarioParaAtualizar.getLogin())) {
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.LOGIN, form.getLogin()));
			if (usuarioParaAtualizar.getId() != null) {
				filtroUsuario
						.adicionarParametro(new ParametroSimplesDiferenteDe(
								FiltroUsuario.ID, usuarioParaAtualizar.getId()));
			}
			Collection coll = Fachada.getInstancia().pesquisar(filtroUsuario,
					Usuario.class.getSimpleName());
			if (coll != null && !coll.isEmpty()) {
				throw new ActionServletException(
						"atencao.usuario.login.ja.existe",null,
						((Usuario)Util.retonarObjetoDeColecao(coll)).getLogin());
			}
		}

		if (!"".equals(form.getEmail())
				&& !form.getEmail().equalsIgnoreCase(
						usuarioParaAtualizar.getDescricaoEmail())) {
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(
					FiltroUsuario.EMAIL, form.getEmail()));
			if (usuarioParaAtualizar.getId() != null) {
				filtroUsuario
						.adicionarParametro(new ParametroSimplesDiferenteDe(
								FiltroUsuario.ID, usuarioParaAtualizar.getId()));
			}
			Collection coll = Fachada.getInstancia().pesquisar(filtroUsuario,
					Usuario.class.getSimpleName());
			if (coll != null && !coll.isEmpty()) {
				throw new ActionServletException(
						"atencao.usuario.email.ja.existe");
			}
		}
		
//		SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
//		
//		String dataNascimento = form.getDataNascimento();
//		
//		Date dataNascimentoFormatada = null;
//		
//		try {
//			dataNascimentoFormatada = dataFormatada.parse(dataNascimento);
//		} catch (ParseException ex) {
//			throw new ActionServletException("erro.sistema");
//		}
		
//		Campo data de nascimento não é obrigatório para usuários indicados como rotina batch e/ou internet
		if(form.getIndicadorUsuarioBatch() != 1 && form.getIndicadorUsuarioInternet() != 1){
			Date dataNascimentoFormatada = Util.converteStringParaDate(form.getDataNascimento());
			usuario.setDataNascimento(dataNascimentoFormatada);
			if (!"".equals(form.getDataNascimento())) {
				Date data = Util.converteStringParaDate(form.getDataNascimento());
				if (data == null) {
					throw new ActionServletException("atencao.data.inicio.invalida");
				}
				if (data.getTime() > new Date(System.currentTimeMillis()).getTime()) {
					throw new ActionServletException(
							"atencao.data_nascimento_superior_atual",form.getDataNascimento() , Util
							.formatarData(new Date()));
				}
			}
		}
		
		
		//Campo CPF não é obrigatório para usuários indicados como rotina batch e/ou internet
		if(form.getIndicadorUsuarioBatch() != 1 && form.getIndicadorUsuarioInternet() != 1){
			String cpf =  form.getCpf();
			
			//O usuário é pessoa física
			if (cpf != null && !cpf.trim().equalsIgnoreCase("")) {
				
				boolean igual = true;
				Integer i = 0;
				
//				Integer tam = cpf.length();
//				
//				while (i < tam - 1) {
//				if ((cpf.charAt(i)) == (cpf.charAt(i + 1))) {
//				i = i + 1;
//				} else {
//				igual = false;
//				}
//				i = i + 1;
//				}
				
				Integer tam = cpf.length() - 1;
				
				while ( i < tam ) {
					if ( (cpf.charAt(i)) != (cpf.charAt(i + 1)) ){
						igual = false;
						break;
					} else {
						i++;
					}
				}
				
				if (igual) {
					throw new ActionServletException("atencao.cpf_invalido");
				}
				
				FiltroUsuario filtroUsuario = new FiltroUsuario();
				
				filtroUsuario.adicionarParametro(new ParametroSimples(
						FiltroUsuario.CPF, cpf));
				
				filtroUsuario.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroUsuario.ID,usuarioParaAtualizar.getId()));
				
				Collection colecaoUsuarioComCpf = fachada.pesquisar(filtroUsuario,
						Usuario.class.getName());
				
				if (!colecaoUsuarioComCpf.isEmpty()) {
					Usuario usuarioComCpf = (Usuario) colecaoUsuarioComCpf
					.iterator().next();
					
					throw new ActionServletException(
							"atencao.cpf.usuario.ja_cadastrado", null, ""
							+ usuarioComCpf.getId());
				}
			}
			
		}
		
		//Verifica a existência de usuario BATCH caso necessite
		if(form.getIndicadorUsuarioBatch() == 1){
			Usuario usuarioBatch = Fachada.getInstancia().pesquisarUsuarioRotinaBatch();
			if(usuarioBatch != null && usuarioBatch.getId().intValue() != usuarioParaAtualizar.getId().intValue() ){
				throw new ActionServletException("atencao.usuario.rotina.batch.ja.existe", null, usuarioBatch.getNomeUsuario());
			}
		}
		
		//Verifica a existência de usuario INTERNET caso necessite
		if(form.getIndicadorUsuarioInternet() == 1){
			Usuario usuarioInternet = Fachada.getInstancia().pesquisarUsuarioInternet();
			if(usuarioInternet != null && usuarioInternet.getId().intValue() != usuarioParaAtualizar.getId().intValue()){
				throw new ActionServletException("atencao.usuario.internet.ja.existe", null, usuarioInternet.getNomeUsuario());
			}
		}

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
						// matricula do funcionário é obrigatório
						if (form.getIdFuncionario() == null
								|| form.getIdFuncionario().equals("")) {
							throw new ActionServletException(
									"atencao.required", null,
									"Matrícula Funcionário");
						}
						FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
						filtroFuncionario
								.adicionarParametro(new ParametroSimples(
										FiltroFuncionario.ID, form
												.getIdFuncionario()));
						filtroFuncionario
								.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionario.UNIDADE_ORGANIZACIONAL);
						// filtroFuncionario.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionario.UNIDADE_EMPRESA_ID);
						Collection colecaoFuncionario = Fachada
								.getInstancia().pesquisar(
										filtroFuncionario,
										Funcionario.class.getSimpleName());
						if (colecaoFuncionario != null
								&& !colecaoFuncionario.isEmpty()) {
							Funcionario f = (Funcionario) colecaoFuncionario.iterator()
									.next();
							usuarioParaAtualizar.setFuncionario(f);
							form.setIdFuncionario(f.getId().toString());
							form.setNomeFuncionario(f.getNome());
							usuarioParaAtualizar
									.setNomeUsuario(f.getNome());
						} else {
							throw new ActionServletException(
									"atencao.required", null,
									"Matrícula Funcionário");
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
						
						//Data de nascimento é obrigatorio para usuário diferentes de rotina batch e/ou internet
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
							//CPF do funcionario é obrigatorio para usuário diferentes de rotina batch e/ou internet
							if(form.getIndicadorUsuarioBatch() != 1 && form.getIndicadorUsuarioInternet() != 1){
								if (form.getCpf() == null || form.getCpf().equals("")) {
									throw new ActionServletException(
											"atencao.required", null, "Número do CPF");
								}
							}
							
							//Data de nascimento é obrigatorio para usuário diferentes de rotina batch e/ou internet
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
				usuarioParaAtualizar.setUsuarioTipo(usuarioTipo);
			} else {
				usuarioParaAtualizar.setUsuarioTipo(null);
			}

		} else {
			usuarioParaAtualizar.setUsuarioTipo(null);
		}

		if (!"".equals(form.getEmpresa())) {
			if (!(usuarioParaAtualizar.getEmpresa() != null
					&& usuarioParaAtualizar.getEmpresa().getId() != null && usuarioParaAtualizar
					.getEmpresa().getId().toString().equals(form.getEmpresa()))) {

				FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
				filtroEmpresa.adicionarParametro(new ParametroSimples(
						FiltroEmpresa.ID, form.getEmpresa()));
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroEmpresa, Empresa.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
					usuarioParaAtualizar.setEmpresa((Empresa) coll.iterator()
							.next());
				} else {
					usuarioParaAtualizar.setEmpresa(null);
				}
			}
		} else {
			usuarioParaAtualizar.setEmpresa(null);
		}

		if (!"".equals(form.getIdFuncionario())) {
			if (!(usuarioParaAtualizar.getFuncionario() != null
					&& usuarioParaAtualizar.getFuncionario().getId() != null && usuarioParaAtualizar
					.getFuncionario().getId().toString().equals(
							form.getIdFuncionario()))) {

				FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
				filtroFuncionario.adicionarParametro(new ParametroSimples(
						FiltroFuncionario.ID, form.getIdFuncionario()));
				filtroFuncionario
						.adicionarCaminhoParaCarregamentoEntidade(FiltroFuncionario.UNIDADE_ORGANIZACIONAL);
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroFuncionario, Funcionario.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
					usuarioParaAtualizar.setFuncionario((Funcionario) coll
							.iterator().next());
				} else {
					usuarioParaAtualizar.setFuncionario(null);
				}
			}
		} else {
			usuarioParaAtualizar.setFuncionario(null);
		}

		// valida a unidade de lotação
		if (!"".equals(form.getIdLotacao())) {
			
//			if (!(usuarioParaAtualizar.getUnidadeOrganizacional() != null
//					&& usuarioParaAtualizar.getUnidadeOrganizacional().getId() != null
//					&& usuarioParaAtualizar.getUnidadeOrganizacional().getId()
//							.toString().equals(form.getIdLotacao()) && usuarioParaAtualizar
//					.getUnidadeOrganizacional().getDescricao().equals(
//							form.getNomeLotacao()))) {

			 if (usuarioParaAtualizar.getUnidadeOrganizacional() == null 
						|| (usuarioParaAtualizar.getUnidadeOrganizacional() != null
						&& usuarioParaAtualizar.getUnidadeOrganizacional().getId() != null 
						&& !form.getIdLotacao().equals(
								usuarioParaAtualizar.getUnidadeOrganizacional().getId().toString()))
								|| (usuarioParaAtualizar.getUsuarioTipo().getIndicadorFuncionario() == UsuarioTipo.INDICADOR_FUNCIONARIO &&
										usuarioParaAtualizar.getUnidadeOrganizacional() != null
										&& usuarioParaAtualizar.getUnidadeOrganizacional().getId() != null 
										&& !form.getIdLotacao().equals(
												usuarioParaAtualizar.getFuncionario().getUnidadeOrganizacional().getId().toString()))) {
			
				FiltroUnidadeOrganizacional filtroUnidadeEmpresa = new FiltroUnidadeOrganizacional();
				filtroUnidadeEmpresa.adicionarParametro(new ParametroSimples(
						FiltroUnidadeOrganizacional.ID, form.getIdLotacao()));
				filtroUnidadeEmpresa
						.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroUnidadeEmpresa,
						UnidadeOrganizacional.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
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
							
							//Alterado por Vivianne Sousa  13/02/2007
							//solicitado por Leonardo Vieira
							if (usuarioParaAtualizar.getUsuarioTipo().getIndicadorFuncionario() == UsuarioTipo.INDICADOR_FUNCIONARIO){
								Funcionario funcionario = usuarioParaAtualizar.getFuncionario();
								funcionario.setUnidadeOrganizacional(unidadeEmpresa);
								usuarioParaAtualizar.setFuncionario(funcionario);
							}

						}
					}
					short indicadorFuncionario = new Short(form
							.getIndicadorFuncionario());
					if (form.getUsuarioTipo() != null
							&& indicadorFuncionario != UsuarioTipo.INDICADOR_FUNCIONARIO) {
						usuarioParaAtualizar
								.setUnidadeOrganizacional(unidadeEmpresa);
					} else {
						usuarioParaAtualizar.setUnidadeOrganizacional(null);
					}
				} else {
					usuarioParaAtualizar.setUnidadeOrganizacional(null);
				}
			}
		} else {
			usuarioParaAtualizar.setUnidadeOrganizacional(null);
		}

		if (!"".equals(form.getNome())) {
			usuarioParaAtualizar.setNomeUsuario(form.getNome());
		}
		
		//Adiciona CPF apenas para usuários diferentes de batch e/ou internet
		if (form.getIndicadorUsuarioBatch() != 1 && form.getIndicadorUsuarioInternet() != 1 && !"".equals(form.getCpf())) {
			usuarioParaAtualizar.setCpf(form.getCpf().toUpperCase());
		}
		//Adiciona data de nascimento apenas para usuários diferentes de batch e/ou internet
		if (form.getIndicadorUsuarioBatch() != 1 && form.getIndicadorUsuarioInternet() != 1 && !"".equals(form.getDataNascimento())) 
			usuarioParaAtualizar.setDataNascimento(Util
					.converteStringParaDate(form.getDataNascimento()));
		
		if (!"".equals(form.getDataInicial()))
			usuarioParaAtualizar.setDataCadastroInicio(Util
					.converteStringParaDate(form.getDataInicial()));
		if (!"".equals(form.getDataFinal()))
			usuarioParaAtualizar.setDataCadastroFim(Util
					.converteStringParaDate(form.getDataFinal()));
		if (!"".equals(form.getLogin()))
			usuarioParaAtualizar.setLogin(form.getLogin());
		if (!"".equals(form.getEmail()))
			usuarioParaAtualizar.setDescricaoEmail(form.getEmail());
		
		if (!"".equals(form.getIndicadorUsuarioBatch()))
			usuarioParaAtualizar.setIndicadorUsuarioBatch(form.getIndicadorUsuarioBatch());
		if (!"".equals(form.getIndicadorUsuarioInternet()))
			usuarioParaAtualizar.setIndicadorUsuarioInternet(form.getIndicadorUsuarioInternet());

		sessao.setAttribute("usuarioParaAtualizar", usuarioParaAtualizar);

		sessao.setAttribute("usuario", usuario);

		return retorno;
	}
}
