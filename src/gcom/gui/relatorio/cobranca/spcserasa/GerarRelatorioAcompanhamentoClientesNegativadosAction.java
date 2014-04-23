package gcom.gui.relatorio.cobranca.spcserasa;

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
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorRetornoMotivo;
import gcom.cobranca.bean.DadosConsultaNegativacaoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.cobranca.spcserasa.InformarDadosConsultaNegativacaoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cobranca.spcserasa.RelatorioAcompanhamentoClientesNegativados;
import gcom.relatorio.cobranca.spcserasa.RelatorioAcompanhamentoClientesNegativadosSintetico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.FiltroNegativador;
import gcom.spcserasa.FiltroNegativadorRetornoMotivo;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição do relatório de Acompanhamanto de Clientes Negativados
 * 
 * @author Yara Taciane
 * @created 18 de março de 2008
 */
public class GerarRelatorioAcompanhamentoClientesNegativadosAction extends
		ExibidorProcessamentoTarefaRelatorio {

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

		// cria a variável de retorno
		ActionForward retorno = actionMapping.findForward("exibirInformarDadosConsultaNegativacao");
		
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		InformarDadosConsultaNegativacaoActionForm form = (InformarDadosConsultaNegativacaoActionForm) actionForm;

		// Inicio da parte que vai mandar os parametros para o relatório
		DadosConsultaNegativacaoHelper parametros = new DadosConsultaNegativacaoHelper();

		//********************************************************
		// RM3755
		// Autor: Ivan Sergio
		// Data: 12/01/2011
		//********************************************************		
		//String idNegativador = (String) form.getIdNegativador();        
        //if (idNegativador == null || idNegativador.equalsIgnoreCase("")){
        //    idNegativador = (String)sessao.getAttribute("idNegativador");
        //}
        //sessao.setAttribute("idNegativador",idNegativador);
		//--------------------------------------------------------------------------------------------------------
		// Negativador
		//--------------------------------------------------------------------------------------------------------
		String[] arrayNegativador = form.getArrayNegativador();	
		
		if (arrayNegativador == null){
			arrayNegativador = (String[])sessao.getAttribute("arrayNegativador");
	    }
	    sessao.setAttribute("arrayNegativador", arrayNegativador);
		
		
	    Negativador negativadorColecao = new Negativador();
	    negativadorColecao.setId(-1);

		Collection colecaoNegativador = new ArrayList();
		int i = 0;		
		Collection colecaoIdNegativador = new ArrayList();
		
		if (arrayNegativador != null && arrayNegativador.length>0) {
			//negativadorColecao.setDescricao("OPÇÕES SELECIONADAS");
			colecaoNegativador.add(negativadorColecao);
			FiltroNegativador filtroNegativador = new FiltroNegativador();
			filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");
			
			for (i = 0; i < arrayNegativador.length; i++) {
				if (!arrayNegativador[i].equals("")
						&& !arrayNegativador[i].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					colecaoIdNegativador.add(arrayNegativador[i]);
				}
			}

			filtroNegativador.adicionarParametro(new ParametroSimplesIn(
					FiltroNegativador.ID, colecaoIdNegativador));
			
			filtroNegativador.setCampoOrderBy(FiltroNegativador.CLIENTE);

			Collection colecaoNegativadorPesquisa = fachada.pesquisar(
					filtroNegativador, Negativador.class.getName());

			if (colecaoNegativadorPesquisa != null
					&& !colecaoNegativadorPesquisa.isEmpty()) {
				colecaoNegativador.addAll(colecaoNegativadorPesquisa);
			}
		} else {
			//negativadorColecao.setDescricao("TODOS");
			colecaoNegativador.add(negativadorColecao);
		}
		//********************************************************
        
		Date periodoEnvioNegativacaoInicio = null;
		if (form.getPeriodoEnvioNegativacaoInicio() != null && !form.getPeriodoEnvioNegativacaoInicio().equals("")){
			Date periodoInicio = Util.converteStringParaDate(form.getPeriodoEnvioNegativacaoInicio());			
			periodoEnvioNegativacaoInicio = Util.getSQLDate(periodoInicio);
		}
		if (periodoEnvioNegativacaoInicio == null){
			periodoEnvioNegativacaoInicio = (Date)sessao.getAttribute("periodoEnvioNegativacaoInicio");
	    }
	    sessao.setAttribute("periodoEnvioNegativacaoInicio",periodoEnvioNegativacaoInicio);
		
				
		Date periodoEnvioNegativacaoFim = null;
		if (form.getPeriodoEnvioNegativacaoFim() != null && !form.getPeriodoEnvioNegativacaoFim().equals("")){
		 Date periodoFim = Util.converteStringParaDate(form.getPeriodoEnvioNegativacaoFim());
		 periodoEnvioNegativacaoFim = Util.getSQLDate(periodoFim);	
		}
		if (periodoEnvioNegativacaoFim == null){
			periodoEnvioNegativacaoFim = (Date)sessao.getAttribute("periodoEnvioNegativacaoFim");
	    }
	    sessao.setAttribute("periodoEnvioNegativacaoFim",periodoEnvioNegativacaoFim);
		
		
		String tituloComando = null;
		if (form.getTituloComando() != null && !form.getTituloComando().equals("")){
			tituloComando = form.getTituloComando();
		}
		if (tituloComando == null || tituloComando.equalsIgnoreCase("") ){
			tituloComando = (String)sessao.getAttribute("tituloComando");
	    }
	    sessao.setAttribute("tituloComando",tituloComando);
		
		
		
		Integer idEloPolo = null;
		if(form.getIdEloPolo()!= null && !form.getIdEloPolo().equals("") && !form.getIdEloPolo().equals("-1")){
			idEloPolo = new Integer(form.getIdEloPolo());
		}
		if (idEloPolo == null){
			idEloPolo = (Integer)sessao.getAttribute("idEloPolo");
	    }
	    sessao.setAttribute("idEloPolo",idEloPolo);
		
		
		
		Integer idLocalidade = null;
		if(form.getIdLocalidade()!= null && !form.getIdLocalidade().equals("")&& !form.getIdLocalidade().equals("-1")){
			idLocalidade = new Integer(form.getIdLocalidade());
		}
		if (idLocalidade == null){
			idLocalidade = (Integer)sessao.getAttribute("idLocalidade");
	    }
	    sessao.setAttribute("idLocalidade",idLocalidade);
		
		
		Integer idSetorComercial = null;
		if(form.getIdSetorComercial()!= null &&!form.getIdSetorComercial().equals("") && !form.getIdSetorComercial().equals("-1")){
			idSetorComercial = new Integer(form.getIdSetorComercial());
		}
		if (idSetorComercial == null){
			idSetorComercial = (Integer)sessao.getAttribute("idSetorComercial");
	    }
	    sessao.setAttribute("idSetorComercial",idSetorComercial);
		
		
		Integer idQuadra = null;
		if(form.getIdQuadra()!= null && !form.getIdQuadra().equals("") && !form.getIdQuadra().equals("-1")){
			idQuadra = new Integer(form.getIdQuadra());
		}
		if (idQuadra == null){
			idQuadra = (Integer)sessao.getAttribute("idQuadra");
	    }
	    sessao.setAttribute("idQuadra",idQuadra);
		
		//--------------------------------------------------------------------------------------------------------
		//Grupo Cobrança
		//--------------------------------------------------------------------------------------------------------
		String[] arrayCobrancaGrupo = form.getArrayCobrancaGrupo();	
		
		if (arrayCobrancaGrupo == null){
			arrayCobrancaGrupo = (String[])sessao.getAttribute("arrayCobrancaGrupo");
	    }
	    sessao.setAttribute("arrayCobrancaGrupo",arrayCobrancaGrupo);
		
		
		CobrancaGrupo cobrancaGrupoColecao = new CobrancaGrupo();
		cobrancaGrupoColecao.setId(-1);

		Collection colecaoCobrancaGrupo = new ArrayList();
		
		if (arrayCobrancaGrupo != null && arrayCobrancaGrupo.length>0) {
			cobrancaGrupoColecao.setDescricao("OPÇÕES SELECIONADAS");
			colecaoCobrancaGrupo.add(cobrancaGrupoColecao);
			FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();

			for (i = 0; i < arrayCobrancaGrupo.length; i++) {
				if (!arrayCobrancaGrupo[i].equals("")
						&& !arrayCobrancaGrupo[i].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (i + 1 < arrayCobrancaGrupo.length) {
						filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(
										FiltroCobrancaGrupo.ID, arrayCobrancaGrupo[i],
										ConectorOr.CONECTOR_OR,
										arrayCobrancaGrupo.length));

					} else {
						filtroCobrancaGrupo
								.adicionarParametro(new ParametroSimples(
										FiltroCobrancaGrupo.ID, arrayCobrancaGrupo[i]));
					}
				}
			}

			filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO);

			Collection colecaoCobrancaGrupoPesquisa = fachada.pesquisar(
					filtroCobrancaGrupo, CobrancaGrupo.class.getName());

			if (colecaoCobrancaGrupoPesquisa != null
					&& !colecaoCobrancaGrupoPesquisa.isEmpty()) {
				colecaoCobrancaGrupo.addAll(colecaoCobrancaGrupoPesquisa);
			}
		} else {
			cobrancaGrupoColecao.setDescricao("TODOS");
			colecaoCobrancaGrupo.add(cobrancaGrupoColecao);
		}
		
		//--------------------------------------------------------------------------------------------------------
		//Gerência Regional
		//--------------------------------------------------------------------------------------------------------
		String[] arrayGerenciaRegional = form.getArrayGerenciaRegional();		
		if (arrayGerenciaRegional == null){
			arrayGerenciaRegional = (String[])sessao.getAttribute("arrayGerenciaRegional");
	    }
	    sessao.setAttribute("arrayGerenciaRegional",arrayGerenciaRegional);
		
		
		GerenciaRegional gerenciaRegionalColecao = new GerenciaRegional();
		gerenciaRegionalColecao.setId(-1);

		Collection colecaoGerenciaRegional = new ArrayList();
		int j = 0;		
		
		if (arrayGerenciaRegional!= null && arrayGerenciaRegional.length>0) {
			gerenciaRegionalColecao.setNome("OPÇÕES SELECIONADAS");
			colecaoGerenciaRegional.add(gerenciaRegionalColecao);
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

			for (j = 0; j < arrayGerenciaRegional.length; j++) {
				if (!arrayGerenciaRegional[j].equals("")
						&& !arrayGerenciaRegional[j].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (j + 1 < arrayGerenciaRegional.length) {
						filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
										FiltroGerenciaRegional.ID, arrayGerenciaRegional[j],
										ConectorOr.CONECTOR_OR,
										arrayGerenciaRegional.length));

					} else {
						filtroGerenciaRegional
								.adicionarParametro(new ParametroSimples(
										FiltroGerenciaRegional.ID, arrayGerenciaRegional[j]));
					}
				}
			}

			filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);

			Collection colecaoGerenciaRegionalPesquisa = fachada.pesquisar(
					filtroGerenciaRegional, GerenciaRegional.class.getName());

			if (colecaoGerenciaRegionalPesquisa != null
					&& !colecaoGerenciaRegionalPesquisa.isEmpty()) {
				
				colecaoGerenciaRegional.addAll(colecaoGerenciaRegionalPesquisa);				
			}
		} else {
			gerenciaRegionalColecao.setNome("TODOS");
			colecaoGerenciaRegional.add(gerenciaRegionalColecao);
		}
		//--------------------------------------------------------------------------------------------------------
		//Unidade de Negócio
		//--------------------------------------------------------------------------------------------------------
		String[] arrayUnidadeNegocio = form.getArrayUnidadeNegocio();	
		if (arrayUnidadeNegocio == null){
			arrayUnidadeNegocio = (String[])sessao.getAttribute("arrayUnidadeNegocio");
	    }
	    sessao.setAttribute("arrayUnidadeNegocio",arrayUnidadeNegocio);
		
		UnidadeNegocio unidadeNegocioColecao = new UnidadeNegocio();
		unidadeNegocioColecao.setId(-1);

		Collection colecaoUnidadeNegocio = new ArrayList();
		int l = 0;		
		
		if (arrayUnidadeNegocio != null && arrayUnidadeNegocio.length>0) {
			unidadeNegocioColecao.setNome("OPÇÕES SELECIONADAS");
			colecaoUnidadeNegocio.add(unidadeNegocioColecao);
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();

			for (l = 0; l < arrayUnidadeNegocio.length; l++) {
				if (!arrayUnidadeNegocio[l].equals("")
						&& !arrayUnidadeNegocio[l].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (l + 1 < arrayUnidadeNegocio.length) {
						filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
										FiltroUnidadeNegocio.ID, arrayUnidadeNegocio[l],
										ConectorOr.CONECTOR_OR,
										arrayUnidadeNegocio.length));

					} else {
						filtroUnidadeNegocio
								.adicionarParametro(new ParametroSimples(
										FiltroUnidadeNegocio.ID, arrayUnidadeNegocio[l]));
					}
				}
			}

			filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);

			Collection colecaoUnidadeNegocioPesquisa = fachada.pesquisar(
					filtroUnidadeNegocio, UnidadeNegocio.class.getName());

			if (colecaoUnidadeNegocioPesquisa != null
					&& !colecaoUnidadeNegocioPesquisa.isEmpty()) {
				colecaoUnidadeNegocio.addAll(colecaoUnidadeNegocioPesquisa);
			}
		} else {
			unidadeNegocioColecao.setNome("TODOS");
			colecaoUnidadeNegocio.add(unidadeNegocioColecao);
		}
		
		//--------------------------------------------------------------------------------------------------------
		//Perfil Imóvel
		//--------------------------------------------------------------------------------------------------------
		String[] arrayImovelPerfil = form.getArrayImovelPerfil();	
		if (arrayImovelPerfil == null){
			arrayImovelPerfil = (String[])sessao.getAttribute("arrayImovelPerfil");
	    }
	    sessao.setAttribute("arrayImovelPerfil",arrayImovelPerfil);
	    
		ImovelPerfil imovelPerfilColecao = new ImovelPerfil();
		imovelPerfilColecao.setId(-1);

		Collection colecaoImovelPerfil = new ArrayList();
		int m = 0;		
		
		if (arrayImovelPerfil != null && arrayImovelPerfil.length>0) {
			imovelPerfilColecao.setDescricao("OPÇÕES SELECIONADAS");
			colecaoImovelPerfil.add(imovelPerfilColecao);
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();

			for (m = 0; m < arrayImovelPerfil.length; m++) {
				if (!arrayImovelPerfil[m].equals("")
						&& !arrayImovelPerfil[m].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (m + 1 < arrayImovelPerfil.length) {
						filtroImovelPerfil.adicionarParametro(new ParametroSimples(
										FiltroImovelPerfil.ID, arrayImovelPerfil[m],
										ConectorOr.CONECTOR_OR,
										arrayImovelPerfil.length));

					} else {
						filtroImovelPerfil
								.adicionarParametro(new ParametroSimples(
										FiltroImovelPerfil.ID, arrayImovelPerfil[m]));
					}
				}
			}

			filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);

			Collection colecaoImovelPerfilPesquisa = fachada.pesquisar(
					filtroImovelPerfil, ImovelPerfil.class.getName());

			if (colecaoImovelPerfilPesquisa != null
					&& !colecaoImovelPerfilPesquisa.isEmpty()) {
				colecaoImovelPerfil.addAll(colecaoImovelPerfilPesquisa);
			}
		} else {
			imovelPerfilColecao.setDescricao("TODOS");
			colecaoImovelPerfil.add(imovelPerfilColecao);
		}
		
		//--------------------------------------------------------------------------------------------------------
		//Categoria
		//--------------------------------------------------------------------------------------------------------
		String[] arrayCategoria = form.getArrayCategoria();	
		if (arrayCategoria == null){
			arrayCategoria = (String[])sessao.getAttribute("arrayCategoria");
	    }
	    sessao.setAttribute("arrayCategoria",arrayCategoria);
		
		Categoria categoriaColecao = new Categoria();
		categoriaColecao.setId(-1);

		Collection colecaoCategoria = new ArrayList();
		int n = 0;		
		
		if (arrayCategoria != null && arrayCategoria.length>0) {
			categoriaColecao.setDescricao("OPÇÕES SELECIONADAS");
			colecaoCategoria.add(categoriaColecao);
			FiltroCategoria filtroCategoria = new FiltroCategoria();

			for (n = 0; n < arrayCategoria.length; n++) {
				if (!arrayCategoria[n].equals("")
						&& !arrayCategoria[n].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (n + 1 < arrayCategoria.length) {
						filtroCategoria.adicionarParametro(new ParametroSimples(
										FiltroCategoria.CODIGO, arrayCategoria[n],
										ConectorOr.CONECTOR_OR,
										arrayCategoria.length));

					} else {
						filtroCategoria
								.adicionarParametro(new ParametroSimples(
										FiltroCategoria.CODIGO, arrayCategoria[n]));
					}
				}
			}

			filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);

			Collection colecaoCategoriaPesquisa = fachada.pesquisar(
					filtroCategoria, Categoria.class.getName());

			if (colecaoCategoriaPesquisa != null
					&& !colecaoCategoriaPesquisa.isEmpty()) {
				colecaoCategoria.addAll(colecaoCategoriaPesquisa);
			}
		} else {
			categoriaColecao.setDescricao("TODOS");
			colecaoCategoria.add(categoriaColecao);
		}
		
		
		//--------------------------------------------------------------------------------------------------------
		//TipoCliente
		//--------------------------------------------------------------------------------------------------------
		String[] arrayTipoCliente = form.getArrayTipoCliente();		
		if (arrayTipoCliente == null){
			arrayTipoCliente = (String[])sessao.getAttribute("arrayTipoCliente");
	    }
	    sessao.setAttribute("arrayTipoCliente",arrayTipoCliente);
		
		
		ClienteTipo tipoClienteColecao = new ClienteTipo();
		tipoClienteColecao.setId(-1);

		Collection colecaoTipoCliente = new ArrayList();
		int o = 0;		
		
		if (arrayTipoCliente != null && arrayTipoCliente.length>0) {
			tipoClienteColecao.setDescricao("OPÇÕES SELECIONADAS");
			colecaoTipoCliente.add(tipoClienteColecao);
			FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();

			for (o = 0; o < arrayTipoCliente.length; o++) {
				if (!arrayTipoCliente[o].equals("")
						&& !arrayTipoCliente[o].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (o + 1 < arrayTipoCliente.length) {
						filtroClienteTipo.adicionarParametro(new ParametroSimples(
										FiltroClienteTipo.ID, arrayTipoCliente[o],
										ConectorOr.CONECTOR_OR,
										arrayTipoCliente.length));

					} else {
						filtroClienteTipo
								.adicionarParametro(new ParametroSimples(
										FiltroClienteTipo.ID, arrayTipoCliente[o]));
					}
				}
			}

			filtroClienteTipo.setCampoOrderBy(FiltroClienteTipo.DESCRICAO);

			Collection colecaoTipoClientePesquisa = fachada.pesquisar(
					filtroClienteTipo, ClienteTipo.class.getName());

			if (colecaoTipoClientePesquisa != null
					&& !colecaoTipoClientePesquisa.isEmpty()) {
				colecaoTipoCliente.addAll(colecaoTipoClientePesquisa);
			}
		} else {
			tipoClienteColecao.setDescricao("TODOS");
			colecaoTipoCliente.add(tipoClienteColecao);
		}
	
		//--------------------------------------------------------------------------------------------------------
		//Esfera Poder
		//--------------------------------------------------------------------------------------------------------
		String[] arrayEsferaPoder = form.getArrayEsferaPoder();		
		if (arrayEsferaPoder == null){
			arrayEsferaPoder = (String[])sessao.getAttribute("arrayEsferaPoder");
	    }
	    sessao.setAttribute("arrayEsferaPoder",arrayEsferaPoder);
		
		EsferaPoder esferaPoderColecao = new EsferaPoder();
		esferaPoderColecao.setId(-1);

		Collection colecaoEsferaPoder = new ArrayList();
		int p = 0;		
		
		if (arrayEsferaPoder != null && arrayEsferaPoder.length>0) {
			esferaPoderColecao.setDescricao("OPÇÕES SELECIONADAS");
			colecaoEsferaPoder.add(esferaPoderColecao);
			FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();

			for (p = 0; p < arrayEsferaPoder.length; p++) {
				if (!arrayEsferaPoder[p].equals("")
						&& !arrayEsferaPoder[p].equals(""
								+ ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (p + 1 < arrayEsferaPoder.length) {
						filtroEsferaPoder.adicionarParametro(new ParametroSimples(
										FiltroEsferaPoder.ID, arrayEsferaPoder[p],
										ConectorOr.CONECTOR_OR,
										arrayEsferaPoder.length));

					} else {
						filtroEsferaPoder
								.adicionarParametro(new ParametroSimples(
										FiltroEsferaPoder.ID, arrayEsferaPoder[p]));
					}
				}
			}

			filtroEsferaPoder.setCampoOrderBy(FiltroEsferaPoder.DESCRICAO);

			Collection colecaoEsferaPoderPesquisa = fachada.pesquisar(
					filtroEsferaPoder, EsferaPoder.class.getName());

			if (colecaoEsferaPoderPesquisa != null
					&& !colecaoEsferaPoderPesquisa.isEmpty()) {
				colecaoEsferaPoder.addAll(colecaoEsferaPoderPesquisa);
			}
		} else {
			esferaPoderColecao.setDescricao("TODOS");
			colecaoEsferaPoder.add(esferaPoderColecao);
		}
		
		//********************************************************
		// RM3755
		// Autor: Ivan Sergio
		// Data: 12/01/2011
		//********************************************************
		//--------------------------------------------------------------------------------------------------------
		// Ligacao Agua Situacao
		//--------------------------------------------------------------------------------------------------------
		String[] arrayLigacaoAguaSituacao = form.getArrayLigacaoAguaSituacao();		
		LigacaoAguaSituacao ligacaoAguaSituacaoColecao = new LigacaoAguaSituacao();
		ligacaoAguaSituacaoColecao.setId(-1);

		Collection colecaoLigacaoAguaSituacao = new ArrayList();
		int x = 0;		
		
		if (arrayLigacaoAguaSituacao != null) {
			ligacaoAguaSituacaoColecao.setDescricao("OPÇÕES SELECIONADAS");
			colecaoLigacaoAguaSituacao.add(ligacaoAguaSituacaoColecao);
			FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
			
			for (x = 0; x < arrayLigacaoAguaSituacao.length; x++) {
				if (!arrayLigacaoAguaSituacao[x].equals("")
						&& !arrayLigacaoAguaSituacao[x].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (x + 1 < arrayLigacaoAguaSituacao.length) {
						filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
										FiltroLigacaoAguaSituacao.ID, arrayLigacaoAguaSituacao[x],
										ConectorOr.CONECTOR_OR,
										arrayEsferaPoder.length));

					} else {
						filtroLigacaoAguaSituacao
								.adicionarParametro(new ParametroSimples(
										FiltroLigacaoAguaSituacao.ID, arrayLigacaoAguaSituacao[x]));
					}
				}
			}
			
			filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);
			
			Collection colecaoLigacaoAguaSituacaoPesquisa = fachada.pesquisar(
					filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());

			if (colecaoLigacaoAguaSituacaoPesquisa != null
					&& !colecaoLigacaoAguaSituacaoPesquisa.isEmpty()) {
				colecaoLigacaoAguaSituacao.addAll(colecaoLigacaoAguaSituacaoPesquisa);
			}
		} else {
			ligacaoAguaSituacaoColecao.setDescricao("TODOS");
			colecaoLigacaoAguaSituacao.add(ligacaoAguaSituacaoColecao);
		}
		
		//--------------------------------------------------------------------------------------------------------
		// Ligacao Esgoto Situacao
		//--------------------------------------------------------------------------------------------------------
		String[] arrayLigacaoEsgotoSituacao = form.getArrayLigacaoEsgotoSituacao();		
		LigacaoEsgotoSituacao ligacaoEsgotoSituacaoColecao = new LigacaoEsgotoSituacao();
		ligacaoEsgotoSituacaoColecao.setId(-1);

		Collection colecaoLigacaoEsgotoSituacao = new ArrayList();
		
		if (arrayLigacaoEsgotoSituacao != null) {
			ligacaoEsgotoSituacaoColecao.setDescricao("OPÇÕES SELECIONADAS");
			colecaoLigacaoEsgotoSituacao.add(ligacaoEsgotoSituacaoColecao);
			FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
			
			for (x = 0; x < arrayLigacaoEsgotoSituacao.length; x++) {
				if (!arrayLigacaoEsgotoSituacao[x].equals("")
						&& !arrayLigacaoEsgotoSituacao[x].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

					if (x + 1 < arrayLigacaoEsgotoSituacao.length) {
						filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
										FiltroLigacaoEsgotoSituacao.ID, arrayLigacaoEsgotoSituacao[x],
										ConectorOr.CONECTOR_OR,
										arrayEsferaPoder.length));

					} else {
						filtroLigacaoEsgotoSituacao
								.adicionarParametro(new ParametroSimples(
										FiltroLigacaoEsgotoSituacao.ID, arrayLigacaoEsgotoSituacao[x]));
					}
				}
			}
			
			filtroLigacaoEsgotoSituacao.setCampoOrderBy(FiltroLigacaoEsgotoSituacao.DESCRICAO);
			
			Collection colecaoLigacaoEsgotoSituacaoPesquisa = fachada.pesquisar(
					filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName());

			if (colecaoLigacaoEsgotoSituacaoPesquisa != null
					&& !colecaoLigacaoEsgotoSituacaoPesquisa.isEmpty()) {
				colecaoLigacaoEsgotoSituacao.addAll(colecaoLigacaoEsgotoSituacaoPesquisa);
			}
		} else {
			ligacaoEsgotoSituacaoColecao.setDescricao("TODOS");
			colecaoLigacaoEsgotoSituacao.add(ligacaoEsgotoSituacaoColecao);
		}
				
		//************************************************************************************************
		
		
		Collection colecaoMotivoRejeicao = null;
		Short indicadorApenasNegativacoesRejeitadas = null;
		if(form.getIndicadorRelAcompanhamentoClientesNegativados()!= null && 
				form.getIndicadorRelAcompanhamentoClientesNegativados().equals("sim")){
			//--------------------------------------------------------------------------------------------------------
			//Motivo de Rejeição
			//--------------------------------------------------------------------------------------------------------
			
			String[] arrayMotivoRejeicao = form.getArrayMotivoRejeicao();		
			NegativadorRetornoMotivo negativadorRetornoMotivoColecao = new NegativadorRetornoMotivo();
			negativadorRetornoMotivoColecao.setId(-1);
			
			if (arrayMotivoRejeicao == null){
				arrayMotivoRejeicao = (String[])sessao.getAttribute("arrayMotivoRejeicao");
		    }
		    sessao.setAttribute("arrayMotivoRejeicao",arrayMotivoRejeicao);
			
		    colecaoMotivoRejeicao = new ArrayList();
			int t = 0;				
			
			
			if (arrayMotivoRejeicao != null && arrayMotivoRejeicao.length>0) {
				negativadorRetornoMotivoColecao.setDescricaoRetornocodigo("OPÇÕES SELECIONADAS");
				colecaoMotivoRejeicao.add(negativadorRetornoMotivoColecao);
				FiltroNegativadorRetornoMotivo filtroNegativadorRetornoMotivo = new FiltroNegativadorRetornoMotivo();
				filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorRetornoMotivo.INDICADOR_REGISTRO_ACEITO, new Short("2") ));
				filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimplesIn(FiltroNegativadorRetornoMotivo.NEGATIVADOR_RETORNO_MOTIVO_NEGATIVADOR, colecaoNegativador));
		
				for (t = 0; t < arrayMotivoRejeicao.length; t++) {
					if (!arrayMotivoRejeicao[t].equals("")
						&& !arrayMotivoRejeicao[t].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
	
						if (t + 1 < arrayMotivoRejeicao.length) {
							filtroNegativadorRetornoMotivo.adicionarParametro(new ParametroSimples(
											FiltroEsferaPoder.ID, arrayMotivoRejeicao[t],
											ConectorOr.CONECTOR_OR,	arrayMotivoRejeicao.length));
	
						} else {
							filtroNegativadorRetornoMotivo.adicionarParametro(
									new ParametroSimples(FiltroEsferaPoder.ID, arrayMotivoRejeicao[t]));
						}
					}
				}
				
				filtroNegativadorRetornoMotivo.setCampoOrderBy(FiltroNegativadorRetornoMotivo.DESCRICAO_RETORNO_CODIGO);
	
				Collection colecaoNegativadorRetornoMotivoPesquisa = fachada.pesquisar(
						filtroNegativadorRetornoMotivo, NegativadorRetornoMotivo.class.getName());
	
				if (colecaoNegativadorRetornoMotivoPesquisa != null
						&& !colecaoNegativadorRetornoMotivoPesquisa.isEmpty()) {
					colecaoMotivoRejeicao.addAll(colecaoNegativadorRetornoMotivoPesquisa);
				}
			} else {
				negativadorRetornoMotivoColecao.setDescricaoRetornocodigo("TODOS");
				colecaoMotivoRejeicao.add(negativadorRetornoMotivoColecao);
			}
			
			
			if(form.getIndicadorApenasNegativacoesRejeitadas() != null &&
					!form.getIndicadorApenasNegativacoesRejeitadas().equals("")){
				indicadorApenasNegativacoesRejeitadas = new Short(form.getIndicadorApenasNegativacoesRejeitadas());
			}
		
		}
		
		//*****************************************************
		// RM4036
		// Por: Ivan Sergio
		// Data: 04/02/2011
		//*****************************************************
		if (form.getTipoRelatorioNegativacao() != null &&
				form.getTipoRelatorioNegativacao().equals("" + ConstantesSistema.NAO)) {
			// SINTETICO
			TarefaRelatorio relatorio = new RelatorioAcompanhamentoClientesNegativadosSintetico(
					(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			
			// seta os parametros que serão mostrados no relatório
			parametros= validarGeracaoRelatorio(colecaoNegativador, periodoEnvioNegativacaoInicio, periodoEnvioNegativacaoFim,
													tituloComando, idEloPolo, idLocalidade,
													idSetorComercial,
													idQuadra,
													colecaoCobrancaGrupo,
													colecaoGerenciaRegional,
													colecaoUnidadeNegocio, 
													colecaoImovelPerfil,
													colecaoCategoria,
													colecaoTipoCliente,
													colecaoEsferaPoder,
													colecaoMotivoRejeicao,
													indicadorApenasNegativacoesRejeitadas,
													colecaoLigacaoAguaSituacao,
													colecaoLigacaoEsgotoSituacao);
			// Fim da parte que vai mandar os parametros para o relatório
			relatorio.addParametro("parametros",parametros);
				
			// chama o metódo de gerar relatório passando o código da analise
			// como parâmetro
			String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
			try {
				retorno = processarExibicaoRelatorio(relatorio,
						tipoRelatorio, httpServletRequest, httpServletResponse,
						actionMapping);
			} catch (SistemaException ex) {
				// manda o erro para a página no request atual
				reportarErros(httpServletRequest, "erro.sistema");
				// seta o mapeamento de retorno para a tela de erro de popup
				retorno = actionMapping.findForward("telaErroPopup");
			} catch (RelatorioVazioException ex1) {
				// manda o erro para a página no request atual
				reportarErros(httpServletRequest, "erro.relatorio.vazio");
				// seta o mapeamento de retorno para a tela de atenção de popup
				retorno = actionMapping.findForward("telaAtencaoPopup");
			}
		}else {
			// ANALITICO
			// cria uma instância da classe do relatório
			RelatorioAcompanhamentoClientesNegativados relatorio = new RelatorioAcompanhamentoClientesNegativados(
					(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			
			// seta os parametros que serão mostrados no relatório
			parametros= validarGeracaoRelatorio(colecaoNegativador, periodoEnvioNegativacaoInicio, periodoEnvioNegativacaoFim,
													tituloComando, idEloPolo, idLocalidade,
													idSetorComercial,
													idQuadra,
													colecaoCobrancaGrupo,
													colecaoGerenciaRegional,
													colecaoUnidadeNegocio, 
													colecaoImovelPerfil,
													colecaoCategoria,
													colecaoTipoCliente,
													colecaoEsferaPoder,
													colecaoMotivoRejeicao,
													indicadorApenasNegativacoesRejeitadas,
													colecaoLigacaoAguaSituacao,
													colecaoLigacaoEsgotoSituacao);
			// Fim da parte que vai mandar os parametros para o relatório

			relatorio.addParametro("parametros",parametros);
				
			// chama o metódo de gerar relatório passando o código da analise
			// como parâmetro
			String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
			try {
				retorno = processarExibicaoRelatorio(relatorio,
						tipoRelatorio, httpServletRequest, httpServletResponse,
						actionMapping);
			} catch (SistemaException ex) {
				// manda o erro para a página no request atual
				reportarErros(httpServletRequest, "erro.sistema");
				// seta o mapeamento de retorno para a tela de erro de popup
				retorno = actionMapping.findForward("telaErroPopup");
			} catch (RelatorioVazioException ex1) {
				// manda o erro para a página no request atual
				reportarErros(httpServletRequest, "erro.relatorio.vazio");
				// seta o mapeamento de retorno para a tela de atenção de popup
				retorno = actionMapping.findForward("telaAtencaoPopup");
			}
		}
		
		// devolve o mapeamento contido na variável retorno
		return retorno;
	}
	
	
	private DadosConsultaNegativacaoHelper validarGeracaoRelatorio(
										 Collection collNegativador,
										 Date periodoEnvioNegativacaoInicio,
										 Date periodoEnvioNegativacaoFim,
										 String tituloComando,
										 Integer idEloPolo,
										 Integer idLocalidade,
										 Integer idSetorComercial,
										 Integer idQuadra,
										 Collection collCobrancaGrupo,
										 Collection collGerenciaRegional,
										 Collection collUnidadeNegocio, 
										 Collection collImovelPerfil,
										 Collection collCategoria,
										 Collection collTipoCliente,
										 Collection collEsferaPoder,
										 Collection collMotivoRejeicao,
										 Short indicadorApenasNegativacoesRejeitadas,
										 Collection collLigacaoAguaSituacao,
										 Collection collLigacaoEsgotoSituacao) {

				
		Fachada fachada = Fachada.getInstancia();

		// Inicio da parte que vai mandar os parametros para o relatório
		DadosConsultaNegativacaoHelper helper = new DadosConsultaNegativacaoHelper();
		
		//********************************************************
		// RM3755
		// Autor: Ivan Sergio
		// Data: 12/01/2011
		//********************************************************
//		if (idNegativador != null && !idNegativador.equals("")) {
//				FiltroNegativador filtroNegativador = new FiltroNegativador();
//
//				filtroNegativador.adicionarParametro(new ParametroSimples(
//						FiltroNegativador.ID, idNegativador));
//				
//				filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");			
//
//				Collection collNegativador = fachada.pesquisar(filtroNegativador,
//						Negativador.class.getName());
//
//				if (Util.isVazioOrNulo(collNegativador)) {					
//					throw new ActionServletException(
//							"atencao.pesquisa_inexistente", null, "Negativador");
//				}
//		}
		//********************************************************
			
		if (idEloPolo != null && !idEloPolo.equals("")) {			
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID_ELO, idEloPolo));
				
				Collection collLocalidade = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());

				if (Util.isVazioOrNulo(collLocalidade)) {					
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null, "EloPolo");
				}
		}
		
		if (idLocalidade != null && !idLocalidade.equals("")) {		
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidade));
			
			Collection collLocalidade = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());

			if (Util.isVazioOrNulo(collLocalidade)) {			
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Localidade");
			}
	}
		

		if (idSetorComercial != null && !idSetorComercial.equals("")) {
			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE,idLocalidade));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID, idSetorComercial));
			
			Collection collSetorComercial = Fachada.getInstancia().pesquisar(filtroSetorComercial, SetorComercial.class.getName());

			if (Util.isVazioOrNulo(collSetorComercial)) {		
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Setor Comercial");
			}
	}
		
	
		
		if (idQuadra != null && !idQuadra.equals("")) {
			
			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE,idLocalidade));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL,idSetorComercial));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID,idQuadra));
			
			Collection collQuadra = Fachada.getInstancia().pesquisar(filtroQuadra, Quadra.class.getName());

			if (Util.isVazioOrNulo(collQuadra)) {				
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Quadra");
			}
	  } 
		
    		
		//helper.setIdNegativador(new Integer(idNegativador));
		helper.setColecaoNegativador(collNegativador);
		helper.setPeriodoEnvioNegativacaoInicio(periodoEnvioNegativacaoInicio);
		helper.setPeriodoEnvioNegativacaoFim(periodoEnvioNegativacaoFim);
		helper.setTituloComando(tituloComando);
		helper.setIdEloPolo(idEloPolo);
		helper.setIdLocalidade(idLocalidade);
		helper.setIdSetorComercial(idSetorComercial);
		helper.setIdQuadra(idQuadra);
		helper.setColecaoCobrancaGrupo(collCobrancaGrupo);
		helper.setColecaoGerenciaRegional(collGerenciaRegional);
		helper.setColecaoUnidadeNegocio(collUnidadeNegocio);
		helper.setColecaoImovelPerfil(collImovelPerfil);
		helper.setColecaoCategoria(collCategoria);
		helper.setColecaoClienteTipo(collTipoCliente);
		helper.setColecaoEsferaPoder(collEsferaPoder);
		helper.setColecaoMotivoRejeicao(collMotivoRejeicao);
		helper.setIndicadorApenasNegativacoesRejeitadas(indicadorApenasNegativacoesRejeitadas);
		helper.setColecaoLigacaoAguaSituacao(collLigacaoAguaSituacao);
		helper.setColecaoLigacaoEsgotoSituacao(collLigacaoEsgotoSituacao);

		//Verifica se a pesquisa retorno algum resultado
		int qtdeResultados = fachada.pesquisarRelatorioAcompanhamentoClientesNegativadorCount(helper);
		
		if (qtdeResultados == 0) {
			// Caso a pesquisa não retorne nenhum resultado comunica ao usuário;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}else{
			return helper;
		}

	}
	
	
}
