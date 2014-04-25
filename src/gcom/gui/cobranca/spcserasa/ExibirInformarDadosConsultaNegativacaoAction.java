package gcom.gui.cobranca.spcserasa;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.cliente.FiltroEsferaPoder;
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
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.cobranca.FiltroNegativadorExclusaoMotivo;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorExclusaoMotivo;
import gcom.cobranca.NegativadorRetornoMotivo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativador;
import gcom.spcserasa.FiltroNegativadorRetornoMotivo;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Description of the Class
 * 
 * @author Thiago Vieira
 */
public class ExibirInformarDadosConsultaNegativacaoAction extends GcomAction {

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

//    	 localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("exibirInformarDadosConsultaNegativacao");
		HttpSession sessao = httpServletRequest.getSession(false);
		InformarDadosConsultaNegativacaoActionForm form = (InformarDadosConsultaNegativacaoActionForm) actionForm;
		
		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();
			
		String gerarRelatorio = (String) sessao.getAttribute("gerarRelatorio");
		
		if(httpServletRequest.getParameter("gerarRelatorio")!= null && httpServletRequest.getParameter("gerarRelatorio").equals("nao")){			
			gerarRelatorio = "exibirResumoNegativacaoParametros";		
			form.setIndicadorRelExclusao(null);
			form.setIndicadorRelAcompanhamentoClientesNegativados(null);
		}
		if(httpServletRequest.getParameter("gerarRelatorio")!= null && httpServletRequest.getParameter("gerarRelatorio").equals("relatorioAcompanhamentoClientesNegativados")){			
			gerarRelatorio = "relatorioAcompanhamentoClientesNegativados";		
			form.setIndicadorRelExclusao(null);
			form.setIndicadorRelAcompanhamentoClientesNegativados("sim");
			form.setIndicadorApenasNegativacoesRejeitadas(ConstantesSistema.NAO.toString());
		}
		if((httpServletRequest.getParameter("gerarRelatorio")!= null && httpServletRequest.getParameter("gerarRelatorio").equals("relatorioNegativacoesExcluidas")) 
			|| (gerarRelatorio!=null && gerarRelatorio.equals("relatorioNegativacoesExcluidas"))){			
			gerarRelatorio = "relatorioNegativacoesExcluidas";		
			form.setIndicadorRelExclusao("sim");
			form.setIndicadorRelAcompanhamentoClientesNegativados(null);
		}
		
	
		sessao.setAttribute("gerarRelatorio", gerarRelatorio);
		sessao.setAttribute("indicadorRelExclusao", form.getIndicadorRelExclusao());
		
		if (sessao.getAttribute("collNegativador") == null){
			FiltroNegativador filtroNegativador = new FiltroNegativador();
			filtroNegativador.adicionarParametro(new ParametroSimples(FiltroNegativador.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtroNegativador.setCampoOrderBy("cliente.nome");
			Collection collNegativador = Fachada.getInstancia().pesquisar(filtroNegativador, Negativador.class.getName());
			sessao.setAttribute("collNegativador", collNegativador);
		}
		
		//*********************************************************
		// RM3755
		// Autor: Ivan Sergio
		// Data: 11/01/2011
		//*********************************************************
		if (form.getArrayNegativador() != null && form.getArrayNegativador().length > 0) {
			
			Collection colecaoNegativador = new ArrayList();
			for (int i = 0; i < form.getArrayNegativador().length; i++) {
				colecaoNegativador.add(form.getArrayNegativador()[i]);
			}
			
			FiltroNegativadorExclusaoMotivo filtroNegativadorExclusaoMotivo = new FiltroNegativadorExclusaoMotivo();
			filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorExclusaoMotivo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));		
			filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimplesIn(FiltroNegativadorExclusaoMotivo.NEGATIVADOR_ID, colecaoNegativador));
			Collection collNegativadorExclusaoMotivo = Fachada.getInstancia().pesquisar(filtroNegativadorExclusaoMotivo, NegativadorExclusaoMotivo.class.getName());
			sessao.setAttribute("collNegativadorExclusaoMotivo", collNegativadorExclusaoMotivo);
		}
		//*********************************************************
		
		if (sessao.getAttribute("collCobrancaGrupo") == null){
			FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
			filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(FiltroCobrancaGrupo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroCobrancaGrupo.setCampoOrderBy("descricao");
			Collection collCobrancaGrupo = Fachada.getInstancia().pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName());
			sessao.setAttribute("collCobrancaGrupo", collCobrancaGrupo);
		}
		
		if (sessao.getAttribute("collGerenciaRegional") == null){
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroGerenciaRegional.setCampoOrderBy("nome");
			Collection collGerenciaRegional = Fachada.getInstancia().pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
			sessao.setAttribute("collGerenciaRegional", collGerenciaRegional);
		}
		
		if (sessao.getAttribute("collUnidadeNegocio") == null){
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroUnidadeNegocio.setCampoOrderBy("nome");
			Collection collUnidadeNegocio = Fachada.getInstancia().pesquisar(filtroUnidadeNegocio , UnidadeNegocio.class.getName());
			sessao.setAttribute("collUnidadeNegocio", collUnidadeNegocio);
		}
		
		if (form.getIdEloPolo() != null && !form.getIdEloPolo().trim().equals("")){
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdEloPolo()));
			
			Collection collEloPolo = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (collEloPolo != null && !collEloPolo.isEmpty()) {
				if (((Localidade) ((List) collEloPolo).get(0))
						.getIndicadorUso().equals(ConstantesSistema.INDICADOR_USO_DESATIVO)) {
					throw new ActionServletException("atencao.localidade_inativa",
							null, "" + ((Localidade) ((List) collEloPolo).get(0)).getId());
				}

				form.setIdEloPolo(((Localidade) ((List) collEloPolo)
								.get(0)).getId().toString());
				form.setDescricaoEloPolo(((Localidade) ((List) collEloPolo)
								.get(0)).getDescricao());
			} else {
				httpServletRequest.setAttribute("corEloPolo","exception");
               	form.setDescricaoEloPolo(ConstantesSistema.CODIGO_ELO_POLO_INEXISTENTE);
               	form.setIdEloPolo("");
			}
		}
		
		if (form.getIdLocalidade() != null && !form.getIdLocalidade().trim().equals("")){
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidade()));
			
			Collection collLocalidade = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if (collLocalidade != null && !collLocalidade.isEmpty()) {
				if (((Localidade) ((List) collLocalidade).get(0))
						.getIndicadorUso().equals(ConstantesSistema.INDICADOR_USO_DESATIVO)) {
					throw new ActionServletException("atencao.localidade_inativa",
							null, "" + ((Localidade) ((List) collLocalidade).get(0)).getId());
				}

				form.setIdLocalidade(((Localidade) ((List) collLocalidade)
								.get(0)).getId().toString());
				form.setDescricaoLocalidade(((Localidade) ((List) collLocalidade)
								.get(0)).getDescricao());
			} else {
				httpServletRequest.setAttribute("corLocalidade","exception");
               	form.setDescricaoLocalidade(ConstantesSistema.CODIGO_LOCALIDADE_INEXISTENTE);
               	form.setIdLocalidade("");
			}
		}
		
		if (form.getIdSetorComercial() != null && !form.getIdSetorComercial().trim().equals("")){
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getIdSetorComercial()));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getIdLocalidade()));
			
			Collection collSetorComercial = Fachada.getInstancia().pesquisar(filtroSetorComercial, SetorComercial.class.getName());
			
			if (collSetorComercial != null && !collSetorComercial.isEmpty()) {
				if (((SetorComercial) ((List) collSetorComercial).get(0))
						.getIndicadorUso().equals(ConstantesSistema.INDICADOR_USO_DESATIVO)) {
					throw new ActionServletException("atencao.setor_comercial_inativo",
							null, "" + ((SetorComercial) ((List) collSetorComercial).get(0)).getId());
				}

				Integer codigoSetorComercial = ((SetorComercial) ((List) collSetorComercial).get(0)).getCodigo();				
				form.setIdSetorComercial(codigoSetorComercial.toString());
				form.setDescricaoSetorComercial(((SetorComercial) ((List) collSetorComercial)
								.get(0)).getDescricao());
			} else {
				httpServletRequest.setAttribute("corSetorComercial","exception");
               	form.setDescricaoSetorComercial(ConstantesSistema.CODIGO_SETOR_COMERCIAL_INEXISTENTE);
               	form.setIdSetorComercial("");
			}
		} 
		
		/*
		if (form.getIdQuadra() != null && !form.getIdQuadra().trim().equals("")){
			FiltroQuadra filtroQuadra= new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID, form.getIdQuadra()));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, form.getIdSetorComercial()));
			
			Collection collQuadra = Fachada.getInstancia().pesquisar(filtroQuadra, Quadra.class.getName());
			
			if (collQuadra != null && !collQuadra.isEmpty()) {
				if (((Quadra) ((List) collQuadra).get(0))
						.getIndicadorUso().equals(ConstantesSistema.INDICADOR_USO_DESATIVO)) {
					throw new ActionServletException("atencao.quadra_inativa",
							null, "" + ((Quadra) ((List) collQuadra).get(0)).getId());
				}

				form.setIdQuadra(((Quadra) ((List) collQuadra)
								.get(0)).getId().toString());
				form.setDescricaoQuadra(((Quadra) ((List) collQuadra)
								.get(0)).getDescricao());
			} else {
				httpServletRequest.setAttribute("corQuadra","exception");
               	form.setDescricaoQuadra(ConstantesSistema.CODIGO_QUADRA_INEXISTENTE);
               	form.setIdQuadra("");
			}
		}
		*/
		
		if (form.getIdQuadra() != null && !form.getIdQuadra().trim().equals("")&& 
				(form.getIdSetorComercial() != null && !form.getIdSetorComercial()
				.toString().trim().equalsIgnoreCase(""))
				&& (form.getIdLocalidade() != null && !form.getIdLocalidade()
						.toString().trim().equalsIgnoreCase(""))){
			
			this.pesquisarQuadra(form.getIdQuadra(),form.getIdSetorComercial(),form.getIdLocalidade(),
					form,fachada,httpServletRequest);
			
		}
		
		if (sessao.getAttribute("collImovelPerfil") == null){
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
			filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroImovelPerfil.setCampoOrderBy("descricao");
			Collection collImovelPerfil = Fachada.getInstancia().pesquisar(filtroImovelPerfil , ImovelPerfil.class.getName());
			sessao.setAttribute("collImovelPerfil", collImovelPerfil);
		}
		
		if (sessao.getAttribute("collCategoria") == null){
			FiltroCategoria filtroCategoria= new FiltroCategoria();
			filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroCategoria.setCampoOrderBy("descricao");
			Collection collCategoria = Fachada.getInstancia().pesquisar(filtroCategoria , Categoria.class.getName());
			sessao.setAttribute("collCategoria", collCategoria);
		}
		
		if (sessao.getAttribute("collClienteTipo") == null){
			FiltroClienteTipo filtroClienteTipo= new FiltroClienteTipo();
			filtroClienteTipo.adicionarParametro(new ParametroSimples(FiltroClienteTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroClienteTipo.setCampoOrderBy("descricao");
			Collection collClienteTipo = Fachada.getInstancia().pesquisar(filtroClienteTipo , ClienteTipo.class.getName());
			sessao.setAttribute("collClienteTipo", collClienteTipo);
		}
		
		if (sessao.getAttribute("collEsferaPoder") == null){
			FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();
			filtroEsferaPoder.adicionarParametro(new ParametroSimples(FiltroEsferaPoder.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroEsferaPoder.setCampoOrderBy("descricao");
			Collection collEsferaPoder = Fachada.getInstancia().pesquisar(filtroEsferaPoder , EsferaPoder.class.getName());
			sessao.setAttribute("collEsferaPoder", collEsferaPoder);
		}
		
		//*********************************************************
		// RM3755
		// Autor: Ivan Sergio
		// Data: 11/01/2011
		//*********************************************************
		if (sessao.getAttribute("collLigacaoAguaSituacao") == null){
			FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
			filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
					FiltroLigacaoAguaSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroLigacaoAguaSituacao.setCampoOrderBy("descricao");
			Collection collLigacaoAguaSituacao = Fachada.getInstancia().pesquisar(
					filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());
			sessao.setAttribute("collLigacaoAguaSituacao", collLigacaoAguaSituacao);
		}
		
		if (sessao.getAttribute("collLigacaoEsgotoSituacao") == null){
			FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
			filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
					FiltroLigacaoEsgotoSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroLigacaoEsgotoSituacao.setCampoOrderBy("descricao");
			Collection collLigacaoEsgotoSituacao = Fachada.getInstancia().pesquisar(
					filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());
			sessao.setAttribute("collLigacaoEsgotoSituacao", collLigacaoEsgotoSituacao);
		}
		
		if (form.getArrayNegativador() != null && form.getArrayNegativador().length > 0
			&& form.getIndicadorRelAcompanhamentoClientesNegativados() != null 
			&& form.getIndicadorRelAcompanhamentoClientesNegativados().equals("sim")){
			
			Collection colecaoNegativador = new ArrayList();
			for (int i = 0; i < form.getArrayNegativador().length; i++) {
				colecaoNegativador.add(form.getArrayNegativador()[i]);
			}
			
			FiltroNegativadorRetornoMotivo filtroNegativadorRetornoMotivo = new FiltroNegativadorRetornoMotivo();
			filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorRetornoMotivo.INDICADOR_REGISTRO_ACEITO, new Short("2") ));
			filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimplesIn(FiltroNegativadorRetornoMotivo.NEGATIVADOR_RETORNO_MOTIVO_NEGATIVADOR, colecaoNegativador));
			filtroNegativadorRetornoMotivo.setCampoOrderBy("descricaoRetornocodigo");
			
			Collection collMotivoRejeicao = Fachada.getInstancia().pesquisar(filtroNegativadorRetornoMotivo , NegativadorRetornoMotivo.class.getName());
			sessao.setAttribute("collMotivoRejeicao", collMotivoRejeicao);
		}
		//*********************************************************
		
		return retorno;
        
    }
    

	/**
	 * Pesquisar Quadra
	 * 
	 * @param filtroQuadra
	 * @param numeroQuadra
	 * @param codigoSetorComercial
	 * @param quadras
	 * @param form
	 * @param fachada
	 * @param httpServletRequest
	 */
	public void pesquisarQuadra (String numeroQuadra, String codigoSetorComercial, 
			String idLocalidadeFiltroFiltro,
			InformarDadosConsultaNegativacaoActionForm form,
			Fachada fachada, HttpServletRequest httpServletRequest) {
			
		FiltroQuadra filtroQuadra = new FiltroQuadra();		
		
		if (idLocalidadeFiltroFiltro != null
				&& !idLocalidadeFiltroFiltro.toString().trim()
						.equalsIgnoreCase("")) {
			// coloca parametro no filtro
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID_LOCALIDADE, new Integer(
							idLocalidadeFiltroFiltro)));
		}

		if (codigoSetorComercial != null
				&& !codigoSetorComercial.toString().trim()
						.equalsIgnoreCase("")) {
			// coloca parametro no filtro
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID_LOCALIDADE, new Integer(
							idLocalidadeFiltroFiltro)));
		}

		if (codigoSetorComercial != null
				&& !codigoSetorComercial.toString().trim()
						.equalsIgnoreCase("")) {
			// coloca parametro no filtro
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(
							codigoSetorComercial)));
		}
		filtroQuadra.adicionarParametro(new ParametroSimples(
				FiltroQuadra.NUMERO_QUADRA, new Integer(numeroQuadra)));

		//filtroQuadra.adicionarParametro(new ParametroSimples(
			//	FiltroQuadra.INDICADORUSO,
				//ConstantesSistema.INDICADOR_USO_ATIVO));
		//filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bairro");
		// pesquisa
		Collection quadras = fachada.pesquisar(filtroQuadra, Quadra.class
				.getName());
		if (quadras != null && !quadras.isEmpty()) {
			// O cliente foi encontrado
			
			form.setIdQuadra(""
					+ ((Quadra) ((List) quadras).get(0)).getNumeroQuadra());
			httpServletRequest
					.setAttribute("idQuadraNaoEncontrada", "true");
			httpServletRequest.setAttribute("nomeCampo", "loteFiltro");

		} else {
            httpServletRequest.setAttribute(
                    "codigoQuadraNaoEncontrada", "true");

			httpServletRequest.setAttribute("idQuadraNaoEncontrada",
					"exception");
			form.setIdQuadra("");
			httpServletRequest.setAttribute
					("msgQuadra", "QUADRA INEXISTENTE");
			httpServletRequest.setAttribute("nomeCampo", "idQuadraFiltro");
		}
	}
}
