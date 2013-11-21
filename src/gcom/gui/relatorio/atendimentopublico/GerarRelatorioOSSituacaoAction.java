package gcom.gui.relatorio.atendimentopublico;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
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
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.micromedicao.ContratoEmpresaServico;
import gcom.micromedicao.FiltroContratoEmpresaServico;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.RelatorioOSSituacao;
import gcom.relatorio.atendimentopublico.RelatorioOSSituacaoHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**[UC1177] Gerar Relatório de Ordens de Serviço por Situação
 * 
 * Action responsável por gerar o relatório de ordens
 * de serviço por situação. Action será chamada através
 * da tela relatorio_os_situacao_gerar.jsp
 * 
 * @author Diogo Peixoto
 * @since 09/06/2011
 */
public class GerarRelatorioOSSituacaoAction extends ExibidorProcessamentoTarefaRelatorio{

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		GerarRelatorioOSSituacaoActionForm form = (GerarRelatorioOSSituacaoActionForm) actionForm;
		String nomeRelatorio = null;
		
		if(form.getOpcaoRelatorio().equalsIgnoreCase("1")){
			nomeRelatorio = ConstantesRelatorios.RELATORIO_OS_SITUACAO_ANALITICO;
		}else if(form.getOpcaoRelatorio().equalsIgnoreCase("2")){
			nomeRelatorio = ConstantesRelatorios.RELATORIO_OS_SITUACAO_SINTETICO;
		}
		RelatorioOSSituacao relatorioOSSituacao = new RelatorioOSSituacao(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"), nomeRelatorio);

		FiltrarRelatorioOSSituacaoHelper filtro = new FiltrarRelatorioOSSituacaoHelper();

		//Verifica se o elo polo foi digitado pelo usuário
		String idEloPolo = form.getIdEloPolo();
		Localidade eloPolo = null;
		if(Util.verificarIdNaoVazio(idEloPolo)){
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_ELO, idEloPolo));
			Collection<Localidade> localidadeEloPolo = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());
			if (!Util.isVazioOrNulo(localidadeEloPolo)){
				eloPolo = (Localidade) localidadeEloPolo.iterator().next();
				filtro.setEloPolo(eloPolo);
			}else{
				throw new ActionServletException("atencao.localidade.nao.elo");
			}
		}

		//Verifica se a localidade foi digitada pelo usuário
		String idLocalidade = form.getIdLocalidade();
		Localidade localidadeEncontrada = null;
		if(Util.verificarIdNaoVazio(idLocalidade)){
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidade));
			if(eloPolo != null){
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_ELO, idEloPolo));
			}
			Collection<Localidade> localidade = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());
			if (!Util.isVazioOrNulo(localidade)){
				localidadeEncontrada = (Localidade) localidade.iterator().next();
				filtro.setLocalidade(localidadeEncontrada);
			}else{
				if(eloPolo != null){
					throw new ActionServletException("atencao.localidade_inexistente_elo", eloPolo.getDescricao());
				}else{
					throw new ActionServletException("atencao.pesquisa.localidade_inexistente");
				}
			}
		}

		//Verifica se o setor comercial foi digitado pelo usuário
		String idSetorComercial = form.getIdSetorComercial();
		SetorComercial setorComercial = null;
		if(Util.verificarIdNaoVazio(idSetorComercial)){
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, idSetorComercial));
			//Verifica se o usuário digitou a localidade, pois o setor comercial é atrelado à localidade
			if(localidadeEncontrada != null){
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, idLocalidade));
			}
			Collection<SetorComercial> setorComercials = Fachada.getInstancia().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			if (!Util.isVazioOrNulo(setorComercials)){
				setorComercial = (SetorComercial) setorComercials.iterator().next();
				filtro.setSetorComercial(setorComercial);
			}else{
				if(localidadeEncontrada != null){
					throw new ActionServletException("atencao.setor_comercial_nao_existe");
				}else{
					throw new ActionServletException("atencao.processo.setorComercialNaoCadastrada");
				}
			}
		}

		//Verifica se o usuário digitou a quadra.
		String idQuadra = form.getIdQuadra();
		if(Util.verificarIdNaoVazio(idQuadra)){
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID, idQuadra));
			//Verifica se o usuário digitou o setor comercial ou a localidade, pois a quadra está associada a ambos.
			if(setorComercial != null){
				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, idSetorComercial));
			}
			if(localidadeEncontrada != null){
				filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, idLocalidade));
			}				
			Collection<Quadra> quadras = Fachada.getInstancia().pesquisar(filtroQuadra, Quadra.class.getName());
			if (!Util.isVazioOrNulo(quadras)){
				Quadra quadra = (Quadra) quadras.iterator().next();
				filtro.setQuadra(quadra);
			}else{
				if(setorComercial != null){
					throw new ActionServletException("atencao.quadra.inexistente");
				}else if(localidadeEncontrada != null){
					throw new ActionServletException("atencao.quadra.inexistente");
				}else{
					throw new ActionServletException("atencao.quadra.inexistente");
				}
			}
		}

		//Verifica se o tipo de serviço foi digitado pelo usuário
		String idServicoTipo = form.getIdTipoServico();
		ServicoTipo servicoTipo = null;
		if(Util.verificarIdNaoVazio(idServicoTipo)){
			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, idServicoTipo));
			
			Collection<ServicoTipo> servicosTipo = Fachada.getInstancia().pesquisar(filtroServicoTipo, ServicoTipo.class.getName());
			if (!Util.isVazioOrNulo(servicosTipo)){
				servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(servicosTipo);
				filtro.setServicoTipo(servicoTipo);
			}else{
				throw new ActionServletException("atencao.tipo_servico_inexistente");
			}
		}
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID, form.getIdGerenciaRegional()));
		Collection<GerenciaRegional> gerenciasRegional = Fachada.getInstancia().pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
		GerenciaRegional gerenciaRegional = (GerenciaRegional) Util.retonarObjetoDeColecao(gerenciasRegional);
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, form.getIdUnidadeNegocio()));
		Collection<UnidadeNegocio> unidadeNegocios = Fachada.getInstancia().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
		UnidadeNegocio unidadeNegocio = (UnidadeNegocio) Util.retonarObjetoDeColecao(unidadeNegocios);
		
		try{
			filtro.setOpcaoOSCobranca(form.getOpcaoRelatorio());
			filtro.setDataReferencia(form.getDataReferencia());
			filtro.setIdEmpresa(Integer.valueOf(form.getIdEmpresa()));
			filtro.setSituacaoOS(form.getSituacaoOS());
			filtro.setIdGrupoCobranca(Integer.valueOf(form.getIdGrupoCobranca()));
			filtro.setUnidadeNegocio(unidadeNegocio);
			filtro.setOpcaoOSCobranca(form.getOpcaoOSCobranca());
			filtro.setGerenciaRegional(gerenciaRegional);
			filtro.setOpcaoRelatorio(form.getOpcaoRelatorio());
		}catch (NumberFormatException e) {
			throw new ActionServletException("atencao.data_inicio_contrato_invalida");
		}	

		RelatorioOSSituacaoHelper helper = Fachada.getInstancia().filtrarRelatorioOSSituacao(filtro);
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
				
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, form.getIdEmpresa()));
		Collection<Empresa> empresas = Fachada.getInstancia().pesquisar(filtroEmpresa, Empresa.class.getName());
		Empresa empresa = (Empresa)Util.retonarObjetoDeColecao(empresas);
		
		FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
		filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(FiltroCobrancaGrupo.ID, form.getIdGrupoCobranca()));
		filtroCobrancaGrupo.adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaGrupo.CONTRATO_EMPRESA_SERVICO);
		Collection<CobrancaGrupo> cobrancasGrupo = Fachada.getInstancia().pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName()); 
		CobrancaGrupo cobrancaGrupo = (CobrancaGrupo) Util.retonarObjetoDeColecao(cobrancasGrupo);
		String numeroContrato = null;
		String strCobrancaGrupo = null;
		if(cobrancaGrupo != null){
			FiltroContratoEmpresaServico filtroContratoEmpresa = new FiltroContratoEmpresaServico();
			filtroContratoEmpresa.adicionarParametro(new ParametroSimples(FiltroContratoEmpresaServico.ID, 
					cobrancaGrupo.getContratoEmpresaServico().getId()));
			Collection<ContratoEmpresaServico> contratosEmpresa = Fachada.getInstancia().pesquisar(filtroContratoEmpresa, ContratoEmpresaServico.class.getName());
			numeroContrato = (String) ((ContratoEmpresaServico)(Util.retonarObjetoDeColecao(contratosEmpresa))).getDescricaoContrato();
			strCobrancaGrupo = cobrancaGrupo.getDescricao();
		}
		
		
		httpServletRequest.setAttribute( "telaSucessoRelatorio", "sim" );
		relatorioOSSituacao.addParametro("dataReferencia", form.getDataReferencia());
		relatorioOSSituacao.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		relatorioOSSituacao.addParametro("dadosRelatorio", helper);
		relatorioOSSituacao.addParametro("situacaoOS", form.getSituacaoOS());
		relatorioOSSituacao.addParametro("descricaoEmpresa", empresa.getDescricao());
		relatorioOSSituacao.addParametro("numeroContrato", numeroContrato);
		relatorioOSSituacao.addParametro("cobrancaGrupo", strCobrancaGrupo);
		
		if(form.getOpcaoRelatorio().equalsIgnoreCase("1")){
			relatorioOSSituacao.addParametro("tipoRelatorio", "Analítico");
		}else if(form.getOpcaoRelatorio().equalsIgnoreCase("2")){
			relatorioOSSituacao.addParametro("tipoRelatorio", "Sintético");
		}
		
		try {
			retorno = processarExibicaoRelatorio(relatorioOSSituacao, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);
		} catch (RelatorioVazioException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");
			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}
		return retorno;
	}
}