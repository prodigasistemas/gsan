package gcom.gui.relatorio.atendimentopublico;

import gcom.atendimentopublico.ordemservico.FiltroTipoServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaBoletimMedicao;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaBoletimMedicao;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroOperacao;
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

/** [UC1177] Gerar Relatório de Ordens de Serviço por Situação
 * Action responsável por carregar os dados para exibição
 * da tela relatorio_os_situacao_gerar.jsp
 * 
 * @author Diogo Peixoto
 * @since 03/06/2011
 */
public class ExibirGerarRelatorioOSSituacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioOSSituacao");
		GerarRelatorioOSSituacaoActionForm form = (GerarRelatorioOSSituacaoActionForm) actionForm;
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String voltouExcecaoCobrancaGrupo = httpServletRequest.getParameter("voltarGrupoCobranca");
		String limparForm = httpServletRequest.getParameter("limparForm");
		String pesquisarLocalidade = httpServletRequest.getParameter("pesquisarLocalidade"); 
		String pesquisarSetorComercial = httpServletRequest.getParameter("pesquisarSetorComercial");
		String pesquisarEloPolo = httpServletRequest.getParameter("pesquisarEloPolo");
		String pesquisarTipoServico = httpServletRequest.getParameter("pesquisarTipoServico");
		
		if(voltouExcecaoCobrancaGrupo == null){
			// Pesquisando a Localidade pelo código que o usuário digitou
			if(Util.verificarNaoVazio(pesquisarLocalidade)){
				String codigoLocalidade = form.getIdLocalidade();
				if (codigoLocalidade != null && !codigoLocalidade.trim().equals("")) {
					pesquisarLocalidade(httpServletRequest, form);
				}
				// Localidade
				if (Util.verificarIdNaoVazio(form.getIdLocalidade())) {
					httpServletRequest.setAttribute("localidadeEncontrada", true);
				}
			
			//Pesquisando o Setor Comercial pelo código que o usuário digitou	
			}else if(Util.verificarNaoVazio(pesquisarSetorComercial)){
				String codigoSetor = form.getIdSetorComercial();
				if (Util.verificarIdNaoVazio(codigoSetor)){
					pesquisarSetorComercial(httpServletRequest, form);
				}
				//Setor Comercial
				if (Util.verificarIdNaoVazio(form.getIdSetorComercial()) && Util.verificarNaoVazio(form.getDescricaoSetorComercial())) {
					httpServletRequest.setAttribute("setorComercialEncontrado", true);
				}
			}else if(Util.verificarNaoVazio(pesquisarEloPolo)){
				String codigoEloPolo = form.getIdEloPolo();
				if (Util.verificarIdNaoVazio(codigoEloPolo)){
					pesquisarEloPolo(form);
				}
				//Setor Comercial
				if (Util.verificarIdNaoVazio(form.getIdEloPolo()) && Util.verificarNaoVazio(form.getDescricaoEloPolo())) {
					httpServletRequest.setAttribute("eloPoloEncontrado", true);
				}
			}else if(Util.verificarNaoVazio(pesquisarTipoServico)){	
				//Pesquisando o Setor Comercial pelo código que o usuário digitou
				String codigoTipoServico = form.getIdTipoServico();
				if (Util.verificarIdNaoVazio(codigoTipoServico)){
					pesquisarTipoServico(httpServletRequest, form);
				}
				//Tipo Serviço
				if (Util.verificarIdNaoVazio(form.getIdTipoServico()) && Util.verificarNaoVazio(form.getDescricaoTipoServico())) {
					httpServletRequest.setAttribute("tipoServicoEncontrado", true);
				}
			}else if(Util.verificarNaoVazio(limparForm)){
				form.setOpcaoRelatorio("");
				form.setDataReferencia("");
				form.setIdEmpresa("");
				form.setSituacaoOS("");
				form.setIdGrupoCobranca("");
				form.setIdGerenciaRegional("");
				form.setIdUnidadeNegocio("");
				form.setIdLocalidade("");
				form.setDescricaoLocalidade("");
				form.setIdSetorComercial("");
				form.setDescricaoSetorComercial("");
				form.setIdQuadra("");
				form.setOpcaoOSCobranca("");
				form.setIdEloPolo("");
				form.setDescricaoEloPolo("");
				form.setIdTipoServico("");
				form.setDescricaoTipoServico("");
				
				sessao.removeAttribute("situacaoOSEncontrada");
				sessao.removeAttribute("gruposCobranca");
			}else{	
				String empresaSelecionada = httpServletRequest.getParameter("empresaSelecionada");
				/*Caso o submit do form tenha vindo através da seleção da empresa, não precisa
				 * carregar novamente todas as coleções, basta apenas carregar o CobrançaGrupo
				 * que depende da empresa selecionada. 
				 */
				if(empresaSelecionada != null && empresaSelecionada.equalsIgnoreCase("sim")){
					String idEmpresa = form.getIdEmpresa();
					sessao.removeAttribute("gruposCobranca");
					//Caso a empresa selecionada não seja a opção "Branco (value = -1)"
					if(!idEmpresa.equals("-1")){
						FiltroCobrancaGrupo filtro = new FiltroCobrancaGrupo();
						filtro.adicionarParametro(new ParametroSimples(FiltroCobrancaGrupo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
						filtro.adicionarParametro(new ParametroSimples(FiltroCobrancaGrupo.EMPRESA_ID, idEmpresa));
						filtro.setCampoOrderBy(FiltroCobrancaGrupo.ID);
						
						Collection<CobrancaGrupo> colecaoCobrancaGrupo = this.getFachada().pesquisar(filtro, CobrancaGrupo.class.getName());
						if (colecaoCobrancaGrupo == null || colecaoCobrancaGrupo.isEmpty()) {
							throw new ActionServletException("atencao.naocadastrado", "exibirGerarRelatorioOSSituacaoAction.do?voltarGrupoCobranca=sim", 
									new ActionServletException("atencao.naocadastrado"), "Cobrança Grupo");
						} else {
							sessao.setAttribute("gruposCobranca", true);
						}
						sessao.setAttribute("colecaoCobrancaGrupo", colecaoCobrancaGrupo);
					}
					sessao.removeAttribute("situacaoOSEncontrada");
					sessao.removeAttribute("boletimGerado");
				}else{
					String grupoCobrancaSelecionado = httpServletRequest.getParameter("grupoCobrancaSelecionado");
					if(grupoCobrancaSelecionado != null && grupoCobrancaSelecionado.equalsIgnoreCase("sim")){
						String idGrupoCobranca = form.getIdGrupoCobranca();
						/*
						 * Caso o submit do form tenha vindo através da seleção do grupo de cobrança
						 * verificar se o ano/mês referência possui uma data válida e verificar qual
						 * os tipos de situação de OS que serão carregados.
						 */
						if(!idGrupoCobranca.equals("-1")){
							
							if(Util.verificarNaoVazio(form.getDataReferencia())){
								//Verifica se o grupo de cobrança e o ano mês já foi gerado um boletim
								Integer anoMes = Util.formatarMesAnoComBarraParaAnoMes(form.getDataReferencia());
								FiltroCobrancaBoletimMedicao filtroMed = new FiltroCobrancaBoletimMedicao();
								filtroMed.adicionarParametro(new ParametroSimples(FiltroCobrancaBoletimMedicao.COBRANCA_GRUPO_ID, idGrupoCobranca));
								filtroMed.adicionarParametro(new ParametroSimples(FiltroCobrancaBoletimMedicao.ANO_MES_REFERENCIA, anoMes));
								Collection<CobrancaBoletimMedicao> medicoes = 
									Fachada.getInstancia().pesquisar(filtroMed, CobrancaBoletimMedicao.class.getName());
								
								//Caso o grupo de cobrança com ano/mês já tenha sido gerado boletim 
								if(!Util.isVazioOrNulo(medicoes)){
									sessao.setAttribute("boletimGerado", true);
								}
								sessao.setAttribute("situacaoOSEncontrada", true);
							}
						/*
						 * Caso o usuário tenha selecionado um grupo de cobrança que seja o vazio, não procurar
						 * por situações de OS e remover o atributo situacaoOSEncontrada.
						 */
						}else{
							sessao.removeAttribute("situacaoOSEncontrada");
							sessao.removeAttribute("boletimGerado");
						}
					}else{
						this.pesquisarEmpresa(sessao);
					}
				}
			}
		}
		verificaObjetosPesquisarPeloUsuario(httpServletRequest, form);
		
		//Gerencia Regional
		FiltroGerenciaRegional filtroGerencia = new FiltroGerenciaRegional();
		filtroGerencia.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroGerencia.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		Collection<GerenciaRegional> gerenciasRegionais = this.getFachada().pesquisar(filtroGerencia, GerenciaRegional.class.getName());
		httpServletRequest.setAttribute("colecaoGerenciaRegional",gerenciasRegionais);
		
		//Unidade de Negocio
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
		Collection<UnidadeNegocio> colecaoUnidadeNegocio = this.getFachada().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
		httpServletRequest.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
		
		//Por default a opção é TODAS
		form.setOpcaoOSCobranca("todas");
		
		return retorno;
	}
	
	private void pesquisarEmpresa(HttpSession sessao){
		
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		
		filtroEmpresa.setConsultaSemLimites(true);
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADOR_EMPRESA_CONTRATADA_COBRANCA, new Short("1")));
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroEmpresa.setCampoOrderBy(FiltroOperacao.DESCRICAO);		

		Collection<Empresa> colecaoEmpresa = this.getFachada().pesquisar(filtroEmpresa, Empresa.class.getName());

		if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null, "Empresa");
		} else {
			sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
		}
	}
	
	//Métodos para carregar os dados que o usuário apertou ENTER
	private void pesquisarLocalidade(HttpServletRequest request, GerarRelatorioOSSituacaoActionForm form) {
		Fachada fachada = Fachada.getInstancia();

		this.limparSetorComercial(form);
		this.limparQuara(form);
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidade()));
		Collection<Localidade> localidadePesquisada = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
		if (localidadePesquisada != null && !localidadePesquisada.isEmpty()) {
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(localidadePesquisada);
			
			form.setIdLocalidade(localidade.getId().toString());
			form.setDescricaoLocalidade(localidade.getDescricao());
		} else {
			form.setIdLocalidade("");
			form.setDescricaoLocalidade("Localidade Inexistente");
		}
	}
	
	private void pesquisarSetorComercial(HttpServletRequest request, GerarRelatorioOSSituacaoActionForm form){
		
		this.limparQuara(form);
		
		if (Util.verificarNaoVazio(form.getIdLocalidade())) {
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.limparListaParametros();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID_LOCALIDADE, new Integer(form.getIdLocalidade())));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,new Integer(form.getIdSetorComercial())));
			
			Collection<SetorComercial> setorComerciais;
			setorComerciais = this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			
			if (setorComerciais != null && !setorComerciais.isEmpty()) {
				SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(setorComerciais);
				form.setIdSetorComercial(Util.adicionarZerosEsquedaNumero(3, new Integer(setorComercial.getCodigo()).toString()));
				form.setDescricaoSetorComercial(setorComercial.getDescricao());
			}else {
				form.setIdSetorComercial("");
				form.setDescricaoSetorComercial("Setor Comercial Inexistente");
			}
		}
	}
	
	private void pesquisarTipoServico(HttpServletRequest request, GerarRelatorioOSSituacaoActionForm form){
		
		if (Util.verificarNaoVazio(form.getIdTipoServico())){
			FiltroTipoServico filtroTipoServico = new FiltroTipoServico();
			filtroTipoServico.adicionarParametro(new ParametroSimples(FiltroTipoServico.ID, form.getIdTipoServico()));
			
			Collection<ServicoTipo> tiposServicos;
			tiposServicos = this.getFachada().pesquisar(filtroTipoServico, ServicoTipo.class.getName());
			
			if (!Util.isVazioOrNulo(tiposServicos)) {
				ServicoTipo servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(tiposServicos);
				form.setIdTipoServico(servicoTipo.getId().toString());
				form.setDescricaoTipoServico(servicoTipo.getDescricao());
			}else {
				form.setIdTipoServico("");
				form.setDescricaoTipoServico("Serviço Tipo Inexistente");
			}
		}
	}
	
	private void pesquisarEloPolo(GerarRelatorioOSSituacaoActionForm form) {
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_ELO, form.getIdEloPolo()));
		filtroLocalidade.adicionarCaminhoParaCarregamentoEntidade("localidade");
		
		// Recupera Elo Pólo
		Collection<Localidade> colecaoEloPolo = this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
	
		if (colecaoEloPolo != null && !colecaoEloPolo.isEmpty()) {
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoEloPolo);
			localidade = localidade.getLocalidade();
			form.setIdEloPolo(localidade.getId().toString());
			form.setDescricaoEloPolo(localidade.getDescricao());
		} else {
			form.setIdEloPolo(null);
			form.setDescricaoEloPolo("Localidade inexistente");
		}
	}
	
	/*Método responsável por manter as cores (vermelho - caso não existe, preto - caso exista
	 * dos objetos procurados pelo usuário (Localidade, Elo Polo e Seter Comercial)
	 */
	private void verificaObjetosPesquisarPeloUsuario(HttpServletRequest request, GerarRelatorioOSSituacaoActionForm form){
		if(Util.verificarNaoVazio(form.getDescricaoLocalidade()) && Util.verificarIdNaoVazio(form.getIdLocalidade())){
			request.setAttribute("localidadeEncontrada", true);
		}
		if(Util.verificarNaoVazio(form.getDescricaoEloPolo()) && Util.verificarIdNaoVazio(form.getIdEloPolo())){
			request.setAttribute("eloPoloEncontrado", true);
		}
		if(Util.verificarNaoVazio(form.getDescricaoSetorComercial()) && Util.verificarIdNaoVazio(form.getIdSetorComercial())){
			request.setAttribute("setorComercialEncontrado", true);
		}
		if(Util.verificarNaoVazio(form.getDescricaoTipoServico()) && Util.verificarIdNaoVazio(form.getIdTipoServico())){
			request.setAttribute("tipoServicoEncontrado", true);
		}
	}
	
	private void limparSetorComercial(GerarRelatorioOSSituacaoActionForm form){
		form.setIdSetorComercial("");
		form.setDescricaoSetorComercial("");
	}
	
	private void limparQuara(GerarRelatorioOSSituacaoActionForm form){
		form.setIdQuadra("");
	}
}
