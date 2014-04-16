package gcom.gui.gerencial.cobranca;


import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.gerencial.bean.InformarDadosGeracaoResumoAcaoConsultaHelper;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Ana Maria
 * @date 26/10/2006
 * 
 */
public class ExibirDadosGeracaoConsultaAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirDadosGeracaoConsulta");
		
		//obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
		
		InformarDadosGeracaoResumoAcaoConsultaActionForm form = (InformarDadosGeracaoResumoAcaoConsultaActionForm) actionForm;
		
		String[] imovelPerfil = form.getPerfilImovel();
		String[] ligacaoAguaSituacao = form.getSituacaoLigacaoAgua();
		String[] ligacaoEsgotoSituacao = form.getSituacaoLigacaoEsgoto();
		String[] categoria = form.getCategoria();
		String[] esferaPoder = form.getEsferaPoder();
		String[] empresa = form.getEmpresa();
		String[] grupoCobranca = form.getGrupoCobranca();
		String[] gerenciaRegional = form.getGerencialRegional();
		String[] unidadeNegocio = form.getUnidadeNegocio();
		
		InformarDadosGeracaoResumoAcaoConsultaHelper helper = new InformarDadosGeracaoResumoAcaoConsultaHelper();
		helper.setAnoMesReferencia(Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoFaturamento()));
		sessao.setAttribute("informarDadosGeracaoResumoAcaoConsultaHelper", helper);
		
		Integer idLocalidade = null;
		Integer idSetor = null;
		Integer numeroQuadra = null;
		Integer eloPolo = null;
		
		if(form.getLocalidade() != null && !form.getLocalidade().trim().equals("")){
			idLocalidade = new Integer(form.getLocalidade());
		}
		
		if(form.getIdSetorComercial() != null && !form.getIdSetorComercial().trim().equals("")){
			idSetor = new Integer(form.getIdSetorComercial());
		}
		
		if(form.getQuadra() != null && !form.getQuadra().trim().equals("")){
			numeroQuadra = new Integer(form.getQuadra());
		}
		
		if(form.getEloPolo() != null && !form.getEloPolo().trim().equals("")){
			eloPolo = new Integer(form.getEloPolo());
		}
		
		//colecao com parametros escolhidos ------------------
		//na tela n tem filtro por unidade
		sessao.removeAttribute("colecaoGerenciaRegionalResultado");
		sessao.removeAttribute("colecaoUnidadeNegocioResultado");
		sessao.removeAttribute("colecaoCobrancaGrupoResultado");	
		sessao.removeAttribute("colecaoEmpresaResultado");	
		sessao.removeAttribute("colecaoEsferaPoderResultado");	
		sessao.removeAttribute("colecaoCategoriaResultado");	
		sessao.removeAttribute("colecaoImovelPerfilResultado");
		sessao.removeAttribute("colecaoLigacaoAguaSituacaoResultado");
		sessao.removeAttribute("colecaoLigacaoEsgotoSituacaoResultado");
		
		Collection colecaoGerenciaRegional = new ArrayList();
		if(gerenciaRegional != null && gerenciaRegional.length > 0){
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			for (int i = 0; i < gerenciaRegional.length; i++) {
				if(!gerenciaRegional[i].trim().equals("")){
					filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID, gerenciaRegional[i]));
					Collection itemGerencia = fachada.pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
					colecaoGerenciaRegional.add(Util.retonarObjetoDeColecao(itemGerencia));
					filtroGerenciaRegional.limparListaParametros();
				}
			}
		}
		sessao.setAttribute("colecaoGerenciaRegionalResultado", colecaoGerenciaRegional);	
		
		Collection colecaoUnidadeNegocio = new ArrayList();
		if(unidadeNegocio != null && unidadeNegocio.length > 0){
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			for (int i = 0; i < unidadeNegocio.length; i++) {
				if(!unidadeNegocio[i].trim().equals("")){
					filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID, unidadeNegocio[i]));
					Collection itemUN = fachada.pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
					colecaoUnidadeNegocio.add(Util.retonarObjetoDeColecao(itemUN));
					filtroUnidadeNegocio.limparListaParametros();
				}
			}
		}
		sessao.setAttribute("colecaoUnidadeNegocioResultado", colecaoUnidadeNegocio);	

		Collection colecaoGrupoCobranca = new ArrayList();
		if(grupoCobranca != null && grupoCobranca.length > 0){
			FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
			for (int i = 0; i < grupoCobranca.length; i++) {
				if(!grupoCobranca[i].trim().equals("")){
					filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(FiltroCobrancaGrupo.ID, grupoCobranca[i]));
					Collection itemGC = fachada.pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName());
					colecaoGrupoCobranca.add(Util.retonarObjetoDeColecao(itemGC));
					filtroCobrancaGrupo.limparListaParametros();
				}
			}
		}
		sessao.setAttribute("colecaoCobrancaGrupoResultado", colecaoGrupoCobranca);	
		
		Collection colecaoEmpresa = new ArrayList();
		if(empresa != null && empresa.length > 0){
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			for (int i = 0; i < empresa.length; i++) {
				if(!empresa[i].trim().equals("")){
					filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, empresa[i]));
					Collection itemEmp = fachada.pesquisar(filtroEmpresa, Empresa.class.getName());
					colecaoEmpresa.add(Util.retonarObjetoDeColecao(itemEmp));
					filtroEmpresa.limparListaParametros();
				}
			}
		}
		sessao.setAttribute("colecaoEmpresaResultado", colecaoEmpresa);	
		
		Collection colecaoEsferaPoder = new ArrayList();
		if(esferaPoder != null && esferaPoder.length > 0){
			FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();
			for (int i = 0; i < esferaPoder.length; i++) {
				if(!esferaPoder[i].trim().equals("")){
					filtroEsferaPoder.adicionarParametro(new ParametroSimples(FiltroEsferaPoder.ID, esferaPoder[i]));
					Collection itemEP = fachada.pesquisar(filtroEsferaPoder, EsferaPoder.class.getName());
					colecaoEsferaPoder.add(Util.retonarObjetoDeColecao(itemEP));
					filtroEsferaPoder.limparListaParametros();
				}
			}
		}
		sessao.setAttribute("colecaoEsferaPoderResultado", colecaoEsferaPoder);	
		
		Collection colecaoCategoria = new ArrayList();
		if(categoria != null && categoria.length > 0){
			FiltroCategoria filtroCategoria = new FiltroCategoria();
			for (int i = 0; i < categoria.length; i++) {
				if(!categoria[i].trim().equals("")){
					
					filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.CODIGO, categoria[i]));
					Collection itemCatg = fachada.pesquisar(filtroCategoria, Categoria.class.getName());
					colecaoCategoria.add(Util.retonarObjetoDeColecao(itemCatg));
					filtroCategoria.limparListaParametros();
				}
			}
		}
		sessao.setAttribute("colecaoCategoriaResultado", colecaoCategoria);	
		
		Collection colecaoImovelPerfil = new ArrayList();
		if(imovelPerfil != null && imovelPerfil.length > 0){
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
			for (int i = 0; i < imovelPerfil.length; i++) {
				if(!imovelPerfil[i].trim().equals("")){
					filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID, imovelPerfil[i]));
					Collection itemPerfil =  fachada.pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
					colecaoImovelPerfil.add(Util.retonarObjetoDeColecao(itemPerfil));
					filtroImovelPerfil.limparListaParametros();
				}
			}
		}
		sessao.setAttribute("colecaoImovelPerfilResultado", colecaoImovelPerfil);
		
		Collection colecaoLigacaoAguaSituacao = new ArrayList();
		if(ligacaoAguaSituacao != null && ligacaoAguaSituacao.length > 0){
			FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
			for (int i = 0; i < ligacaoAguaSituacao.length; i++) {
				if(!ligacaoAguaSituacao[i].trim().equals("")){
					filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.ID, 
						ligacaoAguaSituacao[i]));
					Collection itemLigacaoAgua =  fachada.pesquisar(filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
					colecaoLigacaoAguaSituacao.add(Util.retonarObjetoDeColecao(itemLigacaoAgua));
					filtroLigacaoAguaSituacao.limparListaParametros();
					
				}
			}
		}
		sessao.setAttribute("colecaoLigacaoAguaSituacaoResultado", colecaoLigacaoAguaSituacao);
		
		Collection colecaoLigacaoEsgotoSituacao = new ArrayList();
		if(ligacaoEsgotoSituacao != null && ligacaoEsgotoSituacao.length > 0){
			FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
			for (int i = 0; i < ligacaoEsgotoSituacao.length; i++) {
				if(!ligacaoEsgotoSituacao[i].trim().equals("")){
					filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgotoSituacao.ID, 
						ligacaoEsgotoSituacao[i]));
					Collection itemLigacaoEsgoto =  fachada.pesquisar(filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
					colecaoLigacaoEsgotoSituacao.add(Util.retonarObjetoDeColecao(itemLigacaoEsgoto));
					filtroLigacaoEsgotoSituacao.limparListaParametros();
					
				}
			}
		}
		sessao.setAttribute("colecaoLigacaoEsgotoSituacaoResultado", colecaoLigacaoEsgotoSituacao);
		
		//----------------------------------------------------

		InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsultaHelper = 
			informarDadosGeracaoResumoAcaoConsultaHelper = fachada
			.informarDadosGeracaoResumoAcaoConsulta(form.getMesAnoFaturamento(),
					grupoCobranca, gerenciaRegional,unidadeNegocio, eloPolo,
					idLocalidade, idSetor, numeroQuadra, imovelPerfil,
					ligacaoAguaSituacao, ligacaoEsgotoSituacao, categoria,
					esferaPoder, empresa, form.getTipoImpressao());
		
		//Pesquisa os dados do resumo das ações de cobrança
		Collection colecaoResumoAcaoCobranca = fachada.consultarResumoCobrancaAcao(informarDadosGeracaoResumoAcaoConsultaHelper);
		
		sessao.setAttribute("colecaoResumoAcaoCobranca", colecaoResumoAcaoCobranca);

		Collection colecaoAgrupamento = fachada.criarColecaoAgrupamentoResumosCobrancaAcao(informarDadosGeracaoResumoAcaoConsultaHelper);
		
		sessao.setAttribute("colecaoAgrupamento", colecaoAgrupamento);
		sessao.setAttribute("mesAnoReferencia", Util.formatarAnoMesParaMesAno(informarDadosGeracaoResumoAcaoConsultaHelper.getAnoMesReferencia()));
		
		StatusWizard statusWizard = (StatusWizard)sessao.getAttribute("statusWizard");
		
		adicionarTextoParametrosParaHintStatusWizard(statusWizard, informarDadosGeracaoResumoAcaoConsultaHelper);
		
		sessao.setAttribute("statusWizard", statusWizard);
		
		sessao.setAttribute("informarDadosGeracaoResumoAcaoConsultaHelper", informarDadosGeracaoResumoAcaoConsultaHelper);
				
		sessao.removeAttribute("colecaoImoveisPerfil");
		sessao.removeAttribute("colecaoLigacaoAgua");
		sessao.removeAttribute("colecaoImoveisPerfil");
		sessao.removeAttribute("colecaoLigacaoEsgoto");
		sessao.removeAttribute("colecaoCategoria");
		sessao.removeAttribute("colecaoEsferaPoder");
		sessao.removeAttribute("colecaoGrupoCobranca");
		sessao.removeAttribute("colecaoEmpresa");
		
 		return retorno;
	}
	
	private void adicionarTextoParametrosParaHintStatusWizard(StatusWizard statusWizard, 
			InformarDadosGeracaoResumoAcaoConsultaHelper helper){
		StringBuffer texto = new StringBuffer();
		
		texto.append("<B>Parâmetros:</B>");
		texto.append("<BR>Mês/Ano de Referência: <I>" + Util.
				formatarAnoMesParaMesAno(helper.getAnoMesReferencia()) + "</I>");

		String grupos = "";
		if (helper.getColecaoCobrancaGrupo() != null){
			for (Iterator iter = helper.getColecaoCobrancaGrupo().iterator();iter.hasNext();) {
				CobrancaGrupo grupo = (CobrancaGrupo) iter.next();
				grupos += grupo.getDescricao() + " / ";
			}
			if (!grupos.equals("")){
				grupos = grupos.substring(0, grupos.length() - 3);
			}
		} else {
			grupos = "TODOS";
		}
		texto.append("<BR>Grupo de Cobrança:<I>" + grupos + "</I>");
		
		String gerencias = "";
		if (helper.getColecaoGerenciaRegional() != null){
			for (Iterator iter = helper.getColecaoGerenciaRegional().iterator();iter.hasNext();) {
				GerenciaRegional GR = (GerenciaRegional) iter.next();
				gerencias += GR.getNome() + " / ";
			}
			if (!gerencias.equals("")){
				gerencias = gerencias.substring(0, gerencias.length() - 3);
			}
		} else {
			gerencias = "TODAS";
		}
		texto.append("<BR>Gerência Regional: <I>" + gerencias + "</I>");
		
		String unidadesNegocio = "";
		if (helper.getColecaoUnidadeNegocio() != null){
			for (Iterator iter = helper.getColecaoUnidadeNegocio().iterator();iter.hasNext();) {
				UnidadeNegocio unidadeNeg = (UnidadeNegocio) iter.next();
				unidadesNegocio += unidadeNeg.getNome() + " / ";
			}
			if (!unidadesNegocio.equals("")){
				unidadesNegocio = unidadesNegocio.substring(0, unidadesNegocio.length() - 3);
			}
		} else {
			unidadesNegocio = "TODAS";
		}
		texto.append("<BR>Unidade de Negócio: <I>" + unidadesNegocio + "</I>");
		
		if (helper.getEloPolo() != null){
			texto.append("<BR>Elo Polo: <I>" + helper.getEloPolo().getDescricao() + "</I>");
		}
		if (helper.getLocalidade() != null){
			texto.append("<BR>Localidade: <I>" + helper.getLocalidade().getDescricaoParaRegistroTransacao() + "</I>");
		}
		if (helper.getSetorComercial() != null){
			texto.append("<BR>Setor Comercial: <I>" + helper.getSetorComercial().getCodigo() + "</I>");
		}
		if (helper.getQuadra() != null){
			texto.append("<BR>Quadra: <I>" + helper.getQuadra().getNumeroQuadra() + "</I>");
		}
						
		String perfisImovel = "";
		if (helper.getColecaoImovelPerfil() != null){
			for (Iterator iter = helper.getColecaoImovelPerfil().iterator();iter.hasNext();) {
				ImovelPerfil imovelPerfil = (ImovelPerfil) iter.next();
				perfisImovel += imovelPerfil.getDescricao() + " / ";
			}
			if (!perfisImovel.equals("")){
				perfisImovel = perfisImovel.substring(0, perfisImovel.length() - 3);
			}
		} else {
			perfisImovel = "TODOS";
		}
		texto.append("<BR>Perfil do Imóvel: <I>" + perfisImovel + "</I>");
		
		String situacoesLigacaoAgua = "";
		if (helper.getColecaoLigacaoAguaSituacao() != null){
			for (Iterator iter = helper.getColecaoLigacaoAguaSituacao().iterator();iter.hasNext();) {
				LigacaoAguaSituacao ligacaoAguaSituacao = (LigacaoAguaSituacao) iter.next();
				situacoesLigacaoAgua += ligacaoAguaSituacao.getDescricao() + " / ";
			}
			if (!situacoesLigacaoAgua.equals("")){
				situacoesLigacaoAgua = situacoesLigacaoAgua.substring(0, situacoesLigacaoAgua.length() - 3);
			}
		} else {
			situacoesLigacaoAgua = "TODAS";
		}
		texto.append("<BR>Situação de Ligação de Água: <I>" + situacoesLigacaoAgua + "</I>");

		String situacoesLigacaoEsgoto = "";
		if (helper.getColecaoLigacaoEsgotoSituacao() != null){
			for (Iterator iter = helper.getColecaoLigacaoEsgotoSituacao().iterator();iter.hasNext();) {
				LigacaoEsgotoSituacao ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) iter.next();
				situacoesLigacaoEsgoto += ligacaoEsgotoSituacao.getDescricao() + " / ";
			}
			if (!situacoesLigacaoEsgoto.equals("")){
				situacoesLigacaoEsgoto = situacoesLigacaoEsgoto.substring(0, situacoesLigacaoEsgoto.length() - 3);
			}
		} else {
			situacoesLigacaoEsgoto = "TODAS";
		}
		texto.append("<BR>Situação de Ligação de Esgoto: <I>" + situacoesLigacaoEsgoto + "</I>");
		
		String categorias = "";
		if (helper.getColecaoCategoria() != null){
			for (Iterator iter = helper.getColecaoCategoria().iterator();iter.hasNext();) {
				Categoria categoria = (Categoria) iter.next();
				categorias += categoria.getDescricao() + " / ";
			}
			if (!categorias.equals("")){
				categorias = categorias.substring(0, categorias.length() - 3);
			}
		} else {
			categorias = "TODAS";
		}
		texto.append("<BR>Categoria: <I>" + categorias + "</I>");
		
		String esferasPoder = "";
		if (helper.getColecaoEsferaPoder() != null){
			for (Iterator iter = helper.getColecaoEsferaPoder().iterator();iter.hasNext();) {
				EsferaPoder esferaPoder = (EsferaPoder) iter.next();
				esferasPoder += esferaPoder.getDescricao() + " / ";
			}
			if (!esferasPoder.equals("")){
				esferasPoder = esferasPoder.substring(0, esferasPoder.length() - 3);
			}
		} else {
			esferasPoder = "TODAS";
		}
		texto.append("<BR>Esfera de Poder: <I>" + esferasPoder + "</I>");
		
		String empresas = "";
		if (helper.getColecaoEmpresa() != null){
			for (Iterator iter = helper.getColecaoEmpresa().iterator();iter.hasNext();) {
				Empresa empresa = (Empresa) iter.next();
				empresas += empresa.getDescricao() + " / ";
			}
			if (!empresas.equals("")){
				empresas = empresas.substring(0, empresas.length() - 3);
			}
		} else {
			empresas = "TODAS";
		}
		texto.append("<BR>Empresa: <I>" + empresas + "</I>");
		
		statusWizard.adicionarItemHint("",texto.toString());
	}
}
