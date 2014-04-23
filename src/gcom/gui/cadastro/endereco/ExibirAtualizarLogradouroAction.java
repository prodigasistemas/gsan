package gcom.gui.cadastro.endereco;

import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.FiltroCep;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.FiltroLogradouroBairro;
import gcom.cadastro.endereco.FiltroLogradouroCep;
import gcom.cadastro.endereco.FiltroLogradouroTipo;
import gcom.cadastro.endereco.FiltroLogradouroTitulo;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.endereco.LogradouroTitulo;
import gcom.cadastro.endereco.bean.AtualizarLogradouroBairroHelper;
import gcom.cadastro.endereco.bean.AtualizarLogradouroCepHelper;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action para a pré-exibição da página de Atualizar Logradouro
 * 
 * @author Sávio Luiz
* @date   30/06/2006 
 */
public class ExibirAtualizarLogradouroAction extends GcomAction {
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
				.findForward("atualizarLogradouro");

		LogradouroActionForm logradouroActionForm = (LogradouroActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		logradouroActionForm.setColecaoBairro("");
		logradouroActionForm.setColecaoCep("");
		
		String idLogradouro = httpServletRequest.getParameter("idLogradouro");
		
		if (sessao.getAttribute("colecaoBairrosSelecionadosUsuario") != null){
	         
	         Collection colecaoBairros = (List) sessao
	            .getAttribute("colecaoBairrosSelecionadosUsuario");
	         
	         if (!colecaoBairros.isEmpty()){
	        	 logradouroActionForm.setColecaoBairro("CARREGADO");
	         }
	         else{
	        	 logradouroActionForm.setColecaoBairro("");
	         }
		}
	        
	    if (sessao.getAttribute("colecaoCepSelecionadosUsuario") != null){
	         
	         Collection colecaoCep = (List) sessao
	            .getAttribute("colecaoCepSelecionadosUsuario");
	         
	         if (!colecaoCep.isEmpty()){
	        	 logradouroActionForm.setColecaoCep("CARREGADO");
	         }
	         else{
	        	 logradouroActionForm.setColecaoCep("");
	         }
	    }

	    
		String codigoLogradouro = httpServletRequest
				.getParameter("idRegistroAtualizacao");
		
		if (codigoLogradouro == null){
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") != null){
				codigoLogradouro = httpServletRequest.getAttribute("idRegistroAtualizacao").toString();
			}
		}
		
		if (httpServletRequest.getParameter("manter") != null){
			sessao.setAttribute("manter", "manter");
		}		

		// -------Parte que trata do código quando o usuário tecla enter
		// caso seja o id do municipio
		String idDigitadoEnterMunicipio = (String) logradouroActionForm
				.getIdMunicipio();
		String codigoDigitadoEnterBairro = (String) logradouroActionForm
				.getCodigoBairro();
		
		/*
         * Removendo toda a coleção de bairro da sessão
         */
        String removerColecaoBairro = httpServletRequest.getParameter("removerColecaoBairro");
        
        if (removerColecaoBairro != null && !removerColecaoBairro.equals("")){
        	
        	sessao.removeAttribute("colecaoBairrosSelecionadosUsuario");
        	logradouroActionForm.setColecaoBairro("");
        }
        
        /*
         * Removendo toda a coleção de cep da sessão
         */
        String removerColecaoCep = httpServletRequest.getParameter("removerColecaoCep");
        
        if (removerColecaoCep != null && !removerColecaoCep.equals("")){
        	
        	sessao.removeAttribute("colecaoCepSelecionadosUsuario");
        	logradouroActionForm.setColecaoCep("");
        }
		
		//Verifica se o código foi digitado
        if (idDigitadoEnterMunicipio != null
                && !idDigitadoEnterMunicipio.trim().equals("")
                && !Util.validarValorNaoNumerico(idDigitadoEnterMunicipio)) {
            FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

            filtroMunicipio.adicionarParametro(new ParametroSimples(
                    FiltroMunicipio.ID, idDigitadoEnterMunicipio));
            filtroMunicipio.adicionarParametro(new ParametroSimples(
                    FiltroMunicipio.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            Collection municipioEncontrado = fachada.pesquisar(filtroMunicipio,
                    Municipio.class.getName());

            if (municipioEncontrado != null && !municipioEncontrado.isEmpty()) {
                //O municipio foi encontrado
                logradouroActionForm
                        .setIdMunicipio(((Municipio) ((List) municipioEncontrado)
                                .get(0)).getId().toString());
                logradouroActionForm
                        .setNomeMunicipio(((Municipio) ((List) municipioEncontrado)
                                .get(0)).getNome());
                
                httpServletRequest.setAttribute("nomeCampo",
                "codigoBairro");

                httpServletRequest.setAttribute("idMunicipioNaoEncontrado",
                        "true");
                
                Municipio municipio = ((Municipio) ((List) municipioEncontrado).get(0));
                
                if (!fachada.verificarMunicipioComCepPorLogradouro(municipio)){
                	
                	httpServletRequest.setAttribute("cepUnico", "OK");
                	
                	Cep cep = fachada.obterCepUnicoMunicipio(municipio);
                	
                	if (cep != null){
                		Collection colecaoCepSelecionadosUsuario = new ArrayList();
                    	colecaoCepSelecionadosUsuario.add(cep);
                    	
                    	sessao.setAttribute("colecaoCepSelecionadosUsuario", colecaoCepSelecionadosUsuario);
                	}
                }

            } else {
            	
                logradouroActionForm.setIdMunicipio("");
                httpServletRequest.setAttribute("nomeCampo",
                "idMunicipio");
                httpServletRequest.setAttribute("idMunicipioNaoEncontrado",
                        "exception");
                logradouroActionForm.setNomeMunicipio("Município inexistente");

            }

        }

        //Verifica se o código foi digitado
        if (codigoDigitadoEnterBairro != null
                && !codigoDigitadoEnterBairro.trim().equals("")
                && !Util.validarValorNaoNumerico(codigoDigitadoEnterBairro)) {
            FiltroBairro filtroBairro = new FiltroBairro();

            filtroBairro.adicionarCaminhoParaCarregamentoEntidade("municipio");
            
            filtroBairro.adicionarParametro(new ParametroSimples(
                    FiltroBairro.CODIGO, codigoDigitadoEnterBairro));
            filtroBairro.adicionarParametro(new ParametroSimples(
                    FiltroBairro.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));
            // verifica se o bairro pesquisado é de um municipio existente
            if (idDigitadoEnterMunicipio != null
                    && !idDigitadoEnterMunicipio.trim().equals("")
                    && Integer.parseInt(idDigitadoEnterMunicipio) > 0) {

                filtroBairro.adicionarParametro(new ParametroSimples(
                        FiltroBairro.MUNICIPIO_ID, idDigitadoEnterMunicipio));
            }

            Collection bairroEncontrado = fachada.pesquisar(filtroBairro,
                    Bairro.class.getName());

            if (bairroEncontrado != null && !bairroEncontrado.isEmpty()) {
                
            	//O bairro foi encontrado
                Bairro objetoBairroEncontrado = (Bairro) Util.retonarObjetoDeColecao(bairroEncontrado);
            	
            	logradouroActionForm.setCodigoBairro(String.valueOf(objetoBairroEncontrado.getCodigo()));
                logradouroActionForm.setNomeBairro(objetoBairroEncontrado.getNome());
                
                httpServletRequest.setAttribute("nomeCampo", "botaoAdicionarBairro");

                httpServletRequest.setAttribute("idBairroNaoEncontrado", "true");
                
                /*
                 * Adicionado o novo BAIRRO na coleção
                 */
                String adicionarBairroColecao = httpServletRequest.getParameter("adicionarBairroColecao");
                
                if (adicionarBairroColecao != null && !adicionarBairroColecao.equals("")){
                	
                	logradouroActionForm.setCodigoBairro("");
                    logradouroActionForm.setNomeBairro("");
                    
                	List colecaoBairrosSelecionadosUsuario = new ArrayList();                	
                    if (sessao.getAttribute("colecaoBairrosSelecionadosUsuario") != null){
                    	
                    	colecaoBairrosSelecionadosUsuario = (List) sessao
                        .getAttribute("colecaoBairrosSelecionadosUsuario");
                    	
                    	if (!colecaoBairrosSelecionadosUsuario.contains((Bairro) ((List) bairroEncontrado).get(0))){
                    		colecaoBairrosSelecionadosUsuario.add((Bairro) ((List) bairroEncontrado).get(0));
                    		logradouroActionForm.setColecaoBairro("CARREGADO");
                    	}
                    	else{
                    		throw new ActionServletException(
                                    "atencao.objeto_ja_selecionado", null, "Bairro");
                    	}
                    }
                    else{
                    	colecaoBairrosSelecionadosUsuario.add((Bairro) ((List) bairroEncontrado).get(0));
                    	sessao.setAttribute("colecaoBairrosSelecionadosUsuario", colecaoBairrosSelecionadosUsuario);
                    	logradouroActionForm.setColecaoBairro("CARREGADO");
                    }
                }
                
            } else {
                logradouroActionForm.setCodigoBairro("");
                httpServletRequest.setAttribute("nomeCampo",
                "codigoBairro");
                httpServletRequest.setAttribute("idBairroNaoEncontrado",
                        "exception");
                logradouroActionForm.setNomeBairro("Bairro inexistente");

            }

        }
        //fim da parte da pesquisa do enter
        
        
        // ------Inicio da parte que verifica se vem da página de
		// manter_bairro.jsp

		if (codigoLogradouro != null && !codigoLogradouro.equals("")) {
			
			sessao.removeAttribute("colecaoBairrosSelecionadosUsuario");
			sessao.removeAttribute("colecaoCepSelecionadosUsuario");
			
			logradouroActionForm.setCodigoBairro("");
			logradouroActionForm.setCodigoCEP("");
			logradouroActionForm.setDescricaoCEP("");
			logradouroActionForm.setNomeBairro("");

			FiltroLogradouro filtroLogradouro = new FiltroLogradouro();

			filtroLogradouro.adicionarParametro(new ParametroSimples(
					FiltroLogradouro.ID, codigoLogradouro));

			filtroLogradouro
					.adicionarCaminhoParaCarregamentoEntidade("logradouroTipo");
			filtroLogradouro
					.adicionarCaminhoParaCarregamentoEntidade("logradouroTitulo");
			filtroLogradouro
					.adicionarCaminhoParaCarregamentoEntidade("municipio");

			Collection logradouros = fachada.pesquisar(filtroLogradouro,
					Logradouro.class.getName());

			if (logradouros != null && !logradouros.isEmpty()) {
				
				Logradouro logradouro = ((Logradouro) ((List) logradouros)
						.get(0));

				logradouroActionForm.setNome(formatarResultado(logradouro
						.getNome()));
				
				logradouroActionForm.setNomePopular(formatarResultado(logradouro
						.getNomePopular()));

				logradouroActionForm.setIdTipo(new Integer(formatarResultado(""
						+ logradouro.getLogradouroTipo().getId())));
				FiltroLogradouroTipo filtroLogradouroTipo = new FiltroLogradouroTipo(
						FiltroLogradouroTipo.DESCRICAO);

				filtroLogradouroTipo.setConsultaSemLimites(true);
				// cria a coleção de tipos para serem mandadas para o
				// logradouro_atualizar.jsp
				filtroLogradouroTipo.adicionarParametro(new ParametroSimples(
						FiltroLogradouroTipo.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection logradouroTipos = fachada.pesquisar(
						filtroLogradouroTipo, LogradouroTipo.class.getName());

				if (logradouroTipos == null || logradouroTipos.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhumresultado", null,
							"logradouro tipo");
				} else {
					sessao.setAttribute("logradouroTipos", logradouroTipos);
				}

				logradouroActionForm
						.setIdMunicipio(formatarResultado(logradouro
								.getMunicipio().getId().toString()));

				logradouroActionForm
						.setNomeMunicipio(formatarResultado(logradouro
								.getMunicipio().getNome()));

				logradouroActionForm.setIndicadorUso(formatarResultado(""
						+ logradouro.getIndicadorUso()));

				if (logradouro.getLogradouroTitulo() != null
						&& !logradouro.getLogradouroTitulo().equals("")) {

					logradouroActionForm
							.setIdTitulo(new Integer(
									formatarResultado(""
											+ (logradouro.getLogradouroTitulo()
													.getId()))));
				}else{
					logradouroActionForm.setIdTitulo(null);
				}

				// cria a coleção de titulos para serem mandadas para o
				// logradouro_atualizar.jsp
				FiltroLogradouroTitulo filtroLogradouroTitulo = new FiltroLogradouroTitulo(
						FiltroLogradouroTitulo.DESCRICAO);

				filtroLogradouroTitulo.setConsultaSemLimites(true);
				filtroLogradouroTitulo.adicionarParametro(new ParametroSimples(
						FiltroLogradouroTipo.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection logradouroTitulos = fachada.pesquisar(
						filtroLogradouroTitulo, LogradouroTitulo.class
								.getName());

				if (logradouroTitulos == null || logradouroTitulos.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhumresultado", null,
							"logradouro título");
				} else {
					sessao.setAttribute("logradouroTitulos", logradouroTitulos);
				}

				/*
				 * Carrega uma coleção com os bairros que estão relacionados com o logradouro selecionado
				 */
				FiltroLogradouroBairro filtroLogradouroBairro = new FiltroLogradouroBairro();

				filtroLogradouroBairro
						.adicionarParametro(new ParametroSimples(
								FiltroLogradouroBairro.ID_LOGRADOURO,
								logradouro.getId()));
				
				
				filtroLogradouroBairro
						.adicionarCaminhoParaCarregamentoEntidade("bairro");

				Collection logradouroBairros = fachada.pesquisar(
						filtroLogradouroBairro, LogradouroBairro.class
								.getName());
				
				/*
				 * Carregando uma coleção com os bairros que foram selecinados a partir da relação 
				 * existente entre o próprio bairro e o logradouro escolhido 
				 */
				if (logradouroBairros != null && !logradouroBairros.isEmpty()) {
					
					LogradouroBairro logradouroBairro = null;
					Collection colecaoBairrosSelecionadosUsuario = new ArrayList();
					Iterator iteratorLogradouroBairros = logradouroBairros.iterator();
					
					while (iteratorLogradouroBairros.hasNext()){
						logradouroBairro = (LogradouroBairro) iteratorLogradouroBairros.next();
						colecaoBairrosSelecionadosUsuario.add(logradouroBairro.getBairro());
					}
					
					logradouroActionForm.setColecaoBairro("CARREGADO");
					sessao.setAttribute("colecaoBairrosSelecionadosUsuario", colecaoBairrosSelecionadosUsuario);
				}
				
				
				/*
				 * Carrega uma coleção com os CEPs que estão relacionados com o logradouro selecionado
				 */
				FiltroLogradouroCep filtroLogradouroCep = new FiltroLogradouroCep();

				filtroLogradouroCep
						.adicionarParametro(new ParametroSimples(
								FiltroLogradouroCep.ID_LOGRADOURO,
								logradouro.getId()));
				
				filtroLogradouroCep
				.adicionarParametro(new ParametroSimples(
						FiltroLogradouroCep.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				
				filtroLogradouroCep
						.adicionarCaminhoParaCarregamentoEntidade("cep");

				
				Collection logradouroCeps = fachada.pesquisar(
						filtroLogradouroCep, LogradouroCep.class
								.getName());
				
				/*
				 * Carregando uma coleção com os CEPs que foram selecinados a partir da relação 
				 * existente entre o próprio CEO e o logradouro escolhido 
				 */
				if (logradouroCeps != null && !logradouroCeps.isEmpty()) {
					
					LogradouroCep logradouroCep = null;
					Collection colecaoCepSelecionadosUsuario = new ArrayList();
					Iterator iteratorlogradouroCeps = logradouroCeps.iterator();
					
					while (iteratorlogradouroCeps.hasNext()){
						logradouroCep = (LogradouroCep) iteratorlogradouroCeps.next();
						colecaoCepSelecionadosUsuario.add(logradouroCep.getCep());
					}
					
					sessao.setAttribute("colecaoCepSelecionadosUsuario", colecaoCepSelecionadosUsuario);
					logradouroActionForm.setColecaoCep("CARREGADO");
				}

				sessao.setAttribute("logradouro", logradouro);

			}
		}
		
		
		/*
         * Removendo o bairro selecionado da sessão
         */
        String idBairro = httpServletRequest.getParameter("idBairro");
        
        if (Util.verificarNaoVazio(idBairro) &&
        	sessao.getAttribute("colecaoBairrosSelecionadosUsuario") != null && !"true".equals(sessao.getAttribute("idBairroFiltro"))){
        	
        	Collection colecaoBairrosSelecionadosUsuario = (Collection) sessao
            .getAttribute("colecaoBairrosSelecionadosUsuario");

            Iterator colecaoBairrosSelecionadosUsuarioIterator;

            Bairro bairroInserir;

            colecaoBairrosSelecionadosUsuarioIterator = colecaoBairrosSelecionadosUsuario
            .iterator();

            while (colecaoBairrosSelecionadosUsuarioIterator.hasNext()) {

            	bairroInserir = (Bairro) colecaoBairrosSelecionadosUsuarioIterator
                .next();

                if (bairroInserir.getId().equals(new Integer(idBairro))) {

                	//Alteracao feita por Tiago Moreno - verifica se pode ser excluido o Logradouro Bairro
                	FiltroLogradouroBairro filtroLogradouroBairro = new FiltroLogradouroBairro();
                	filtroLogradouroBairro.adicionarParametro(new ParametroSimples(FiltroLogradouroBairro.ID_BAIRRO, idBairro));
                	filtroLogradouroBairro.adicionarParametro(new ParametroSimples(FiltroLogradouroBairro.ID_LOGRADOURO, idLogradouro));
                	Collection colecaoLogradouroBairro = fachada.pesquisar(filtroLogradouroBairro, LogradouroBairro.class.getName());

                	if (colecaoLogradouroBairro != null && !colecaoLogradouroBairro.isEmpty()){
                		
                		LogradouroBairro logradouroBairro = (LogradouroBairro) colecaoLogradouroBairro.iterator().next();
                		
                		int id = logradouroBairro.getId();
                		httpServletRequest.removeAttribute("idBairro");
                		fachada.verificaObjetoRemocao(id, LogradouroBairro.class.getName(), null, null);
                	}
                	//Fim da Alteracao - Tiago Moreno - 08/08/2006
                	
                	//Remove atualizacao do bairro: Raphael Rossiter
                	//==================================================================================================
                	Collection colecaoAtualizarLogradouroBairroHelper = (Collection)
            		sessao.getAttribute("colecaoAtualizarLogradouroBairroHelper");
                	
                	if (colecaoAtualizarLogradouroBairroHelper != null &&
                		!colecaoAtualizarLogradouroBairroHelper.isEmpty()){
                	
                		Iterator iterator = colecaoAtualizarLogradouroBairroHelper.iterator();
                    	AtualizarLogradouroBairroHelper atualizarLogradouroBairroHelper = null;
                    	
                    	while (iterator.hasNext()){
                    		
                    		atualizarLogradouroBairroHelper = (AtualizarLogradouroBairroHelper) iterator.next();
                    		
                    		if (atualizarLogradouroBairroHelper.getBairro().getId()
                    			.equals(bairroInserir.getId())){
                    			
                    			colecaoAtualizarLogradouroBairroHelper.remove(atualizarLogradouroBairroHelper);
                    			break;
                    		}
                    	}
                	}
                	//==================================================================================================
                	
                	colecaoBairrosSelecionadosUsuario.remove(bairroInserir);
                	break;
                }
            }
            
            if (colecaoBairrosSelecionadosUsuario.isEmpty()){
            	logradouroActionForm.setColecaoBairro("");
            }
        }
        
        
        /*
         * Removendo o CEP selecionado da sessão
         */
        String idCep = httpServletRequest.getParameter("idCep");
        
        if (idCep != null && !idCep.equals("") &&
        	sessao.getAttribute("colecaoCepSelecionadosUsuario") != null){
        	
        	Collection colecaoCepSelecionadosUsuario = (Collection) sessao
            .getAttribute("colecaoCepSelecionadosUsuario");

            Iterator colecaoCepSelecionadosUsuarioIterator;

            Cep cepInserir;

            colecaoCepSelecionadosUsuarioIterator = colecaoCepSelecionadosUsuario
            .iterator();

            while (colecaoCepSelecionadosUsuarioIterator.hasNext()) {

            	cepInserir = (Cep) colecaoCepSelecionadosUsuarioIterator
                .next();

                if (cepInserir.getCepId().equals(new Integer(idCep))) {
                	//Alteracao feita por Tiago Moreno - verifica se pode ser excluido o Logradouro Bairro
                	FiltroLogradouroCep filtroLogradouroCep = new FiltroLogradouroCep();
                	filtroLogradouroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.ID_CEP, idCep));
                	filtroLogradouroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.ID_LOGRADOURO, idLogradouro));
                	Collection colecaoLogradouroCep = fachada.pesquisar(filtroLogradouroCep, LogradouroCep.class.getName());

                	if (colecaoLogradouroCep != null && !colecaoLogradouroCep.isEmpty()){
                		
                		LogradouroCep logradouroCep = (LogradouroCep) colecaoLogradouroCep.iterator().next();
                		
                		int id = logradouroCep.getId();
                		
                		fachada.verificaObjetoRemocao(id, LogradouroCep.class.getName(), null, null);
                	}
                	//Fim da Alteracao - Tiago Moreno - 08/08/2006
                	
                	//Remove atualizacao do cep: Raphael Rossiter
                	//==================================================================================================
                	Collection colecaoAtualizarLogradouroCepHelper = (Collection)
            		sessao.getAttribute("colecaoAtualizarLogradouroCepHelper");
                	
                	if (colecaoAtualizarLogradouroCepHelper != null &&
                		!colecaoAtualizarLogradouroCepHelper.isEmpty()){
                	
                		Iterator iterator = colecaoAtualizarLogradouroCepHelper.iterator();
                    	AtualizarLogradouroCepHelper atualizarLogradouroCepHelper = null;
                    	
                    	while (iterator.hasNext()){
                    		
                    		atualizarLogradouroCepHelper = (AtualizarLogradouroCepHelper) iterator.next();
                    		
                    		if (atualizarLogradouroCepHelper.getCep().getCepId()
                    			.equals(cepInserir.getCepId())){
                    			
                    			colecaoAtualizarLogradouroCepHelper.remove(atualizarLogradouroCepHelper);
                    			break;
                    		}
                    	}
                	}
                	//==================================================================================================
	
                	colecaoCepSelecionadosUsuario.remove(cepInserir);
                	break;
                }
            }
            
            if (colecaoCepSelecionadosUsuario.isEmpty()){
            	logradouroActionForm.setColecaoCep("");
            }
        }
        
        
        /*
        * Adicionando um cep na coleção a partir do nome do município e do código do cep 
        */
        String codigoDigitadoEnterCep = (String) logradouroActionForm.getCodigoCEP();
        
        if (codigoDigitadoEnterCep != null && 
        	!codigoDigitadoEnterCep.trim().equals("") && 
        	!Util.validarValorNaoNumerico(codigoDigitadoEnterCep)) {
        	
            FiltroCep filtroCep = new FiltroCep();

            filtroCep.adicionarParametro(new ParametroSimples(
            FiltroCep.CODIGO, codigoDigitadoEnterCep));
            
            filtroCep.adicionarParametro(new ParametroSimples(
            FiltroCep.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
            
            if (logradouroActionForm.getNomeMunicipio() != null
                && !logradouroActionForm.getNomeMunicipio().trim().equals("")) {

            	filtroCep.adicionarParametro(new ComparacaoTexto(
                FiltroCep.MUNICIPIO, logradouroActionForm.getNomeMunicipio()));
            	
            }

            Collection cepEncontrado = fachada.pesquisar(filtroCep,
            Cep.class.getName());

            if (cepEncontrado != null && !cepEncontrado.isEmpty()) {
                
            	Cep objetoCepEncontrado = (Cep) Util.retonarObjetoDeColecao(cepEncontrado);
            	
            	logradouroActionForm.setCodigoCEP(String.valueOf(objetoCepEncontrado.getCodigo()));
                logradouroActionForm.setDescricaoCEP(objetoCepEncontrado.getDescricaoLogradouroFormatada());
                
                httpServletRequest.setAttribute("nomeCampo", "botaoAdicionarCep");

                httpServletRequest.setAttribute("idCEPNaoEncontrado", "true");
                
                /*
                 * Adicionado o novo CEP na coleção
                 */
                String adicionarCepColecao = httpServletRequest.getParameter("adicionarCepColecao");
                
                if (adicionarCepColecao != null && !adicionarCepColecao.equals("")){
                	
                	logradouroActionForm.setCodigoCEP("");
                    logradouroActionForm.setDescricaoCEP("");
                    
                	List colecaoCepSelecionadosUsuario = new ArrayList();
	                if (sessao.getAttribute("colecaoCepSelecionadosUsuario") != null){
	                	
	                	colecaoCepSelecionadosUsuario = (List) sessao
	                    .getAttribute("colecaoCepSelecionadosUsuario");
	                	
	                	if (!colecaoCepSelecionadosUsuario.contains((Cep) ((List) cepEncontrado).get(0))){
	                		colecaoCepSelecionadosUsuario.add((Cep) ((List) cepEncontrado).get(0));
	                		logradouroActionForm.setColecaoCep("CARREGADO");
	                	}
	                	else{
                    		throw new ActionServletException(
                                    "atencao.objeto_ja_selecionado", null, "Cep");
                    	}
	                }
	                else{
	                	colecaoCepSelecionadosUsuario.add((Cep) ((List) cepEncontrado).get(0));
	                	sessao.setAttribute("colecaoCepSelecionadosUsuario", colecaoCepSelecionadosUsuario);
	                	logradouroActionForm.setColecaoCep("CARREGADO");
	                }
                }

            } else {
                logradouroActionForm.setCodigoCEP("");
                httpServletRequest.setAttribute("nomeCampo",
                "codigoCEP");
                httpServletRequest.setAttribute("idCEPNaoEncontrado",
                        "exception");
                logradouroActionForm.setDescricaoCEP("CEP inexistente");

            }

        }
        
        
        
        /*
         * Alteração de Bairro (Logradouro_Bairro)
         */
        String idBairroAtual = httpServletRequest.getParameter("idBairroAtual");
        
        if (idBairroAtual != null && !idBairroAtual.equalsIgnoreCase("")){
        	
        	String idBairroNovo = httpServletRequest.getParameter("idBairroNovo");
        	Logradouro logradouro = (Logradouro) sessao.getAttribute("logradouro");
        	Collection colecaoAtualizarLogradouroBairroHelper = null;
        	List colecaoBairrosSelecionadosUsuario = null;
        	Bairro bairroNovo = null;
        	Bairro bairroAtual = null;
        	AtualizarLogradouroBairroHelper atualizarLogradouroBairroHelper = null;
        	LogradouroBairro logradouroBairro = null;
        	boolean bairroJaAtualizado = false;
        	
        	//Carregando o novo bairro
        	FiltroBairro filtroBairro = new FiltroBairro();
    		
    		filtroBairro.adicionarParametro(new ParametroSimples(
            FiltroBairro.ID, idBairroNovo));
    		
    		Collection colecaoBairro = fachada.pesquisar(filtroBairro, 
    		Bairro.class.getName());
    		
    		bairroNovo = (Bairro) Util.retonarObjetoDeColecao(colecaoBairro);
        	
    		
        	if (sessao.getAttribute("colecaoAtualizarLogradouroBairroHelper") != null){
        		
        		colecaoBairrosSelecionadosUsuario = (List)
        		sessao.getAttribute("colecaoBairrosSelecionadosUsuario");
        		colecaoAtualizarLogradouroBairroHelper = (Collection)
        		sessao.getAttribute("colecaoAtualizarLogradouroBairroHelper");
        		
        		Iterator iteratorBairros = colecaoBairrosSelecionadosUsuario.iterator();
        		Iterator iteratorAtualizacaoBairros = colecaoAtualizarLogradouroBairroHelper.iterator();
        		
        		/*
        		 * Utilizado para verificar se será necessário adicionar o novo bairro. Ficará na dependência da
        		 * condicional que será realizada dentro do loop abaixo.
        		 * 
        		 * Colocado em 22/02/2007 por Raphael Rossiter
        		 */
        		boolean adicionarBairro = true;
        		
        		while (iteratorBairros.hasNext()){
        			
        			bairroAtual = (Bairro) iteratorBairros.next();
        			
        			if (bairroAtual.getId().equals(new Integer(idBairroAtual))){
        				
        				if (colecaoBairrosSelecionadosUsuario.contains(bairroNovo)){
        					
        					//Parte antiga
        					/*throw new ActionServletException(
                                    "atencao.objeto_ja_selecionado", null, "Bairro");*/
        					
        					/*
        					 * Caso o cep atual seja inicial de município e o novo cep já tenho sido adicionado
        					 * na coleção colecaoCepSelecionadosUsuario, removeremos apenas o cep inicial de
        					 * município e todos os imóveis que estiverem associados ao mesmo, serão automaticamente
        					 * associados ao cep de logradouro
        					 * 
        					 * Colocado em 22/02/2007 por Raphael Rossiter
        					 */
        					/*if (fachada.verificarBairroTipoBairroNaoInformado(bairroAtual)){
        						adicionarBairro = false;
        					}
        					else{
        						
        						throw new ActionServletException(
                                        "atencao.objeto_ja_selecionado", null, "Bairro");
        					}*/
        					
        					adicionarBairro = false;
        		
        				}
        				
        				bairroJaAtualizado = false;
        				while (iteratorAtualizacaoBairros.hasNext()){
        				
        					atualizarLogradouroBairroHelper = (AtualizarLogradouroBairroHelper)
        					iteratorAtualizacaoBairros.next();
        					
        					//Bairro já atualizado
        					if (atualizarLogradouroBairroHelper.getBairro().getId()
        						.equals(bairroAtual.getId())){
        						
        						atualizarLogradouroBairroHelper.setBairro(bairroNovo);
        						bairroJaAtualizado = true;
        						break;
        					}
        				}
        				
        				//Caso o bairro ainda não tenha sido atualizado
        				if (!bairroJaAtualizado){
        					
        					FiltroLogradouroBairro filtroLogradouroBairro = new FiltroLogradouroBairro();
        	        		
        	        		filtroLogradouroBairro.adicionarParametro(new ParametroSimples(
        	                FiltroLogradouroBairro.ID_LOGRADOURO, logradouro.getId()));
        	        		
        	        		filtroLogradouroBairro.adicionarParametro(new ParametroSimples(
        	        		FiltroLogradouroBairro.ID_BAIRRO, idBairroAtual));
        	        		
        	        		Collection colecaoLogradouroBairro = fachada.pesquisar(filtroLogradouroBairro, 
        	        		LogradouroBairro.class.getName());
        	        		
        	        		logradouroBairro = (LogradouroBairro)
        	        		Util.retonarObjetoDeColecao(colecaoLogradouroBairro);
        	        		
        	        		atualizarLogradouroBairroHelper = new AtualizarLogradouroBairroHelper();
        	        		
        	        		atualizarLogradouroBairroHelper.setLogradouroBairro(logradouroBairro);
        	        		atualizarLogradouroBairroHelper.setBairro(bairroNovo);
        	        		
        	        		colecaoAtualizarLogradouroBairroHelper.add(atualizarLogradouroBairroHelper);
        				}
        				
        				colecaoBairrosSelecionadosUsuario.remove(bairroAtual);
        				
        				/*
                		 * Utilizado para verificar se será necessário adicionar o novo bairro.
                		 * 
                		 * Colocado em 22/02/2007 por Raphael Rossiter
                		 */
        				if (adicionarBairro){
        					colecaoBairrosSelecionadosUsuario.add(bairroNovo);
        				}
        				
        				break;
        			}
        		}
        		
        	}
        	else{
        		
        		//Gerando coleção de apoio
        		colecaoAtualizarLogradouroBairroHelper = new ArrayList();
        		
        		FiltroLogradouroBairro filtroLogradouroBairro = new FiltroLogradouroBairro();
        		
        		filtroLogradouroBairro.adicionarParametro(new ParametroSimples(
                FiltroLogradouroBairro.ID_LOGRADOURO, logradouro.getId()));
        		
        		filtroLogradouroBairro.adicionarParametro(new ParametroSimples(
        		FiltroLogradouroBairro.ID_BAIRRO, idBairroAtual));
        		
        		Collection colecaoLogradouroBairro = fachada.pesquisar(filtroLogradouroBairro, 
        		LogradouroBairro.class.getName());
        		
        		//Verifica se o usuário está alterando um bairro que ele acabou de informar
        		if (colecaoLogradouroBairro != null &&
        			!colecaoLogradouroBairro.isEmpty()){
        		
        			logradouroBairro = (LogradouroBairro)
            		Util.retonarObjetoDeColecao(colecaoLogradouroBairro);
            		
            		atualizarLogradouroBairroHelper = new AtualizarLogradouroBairroHelper();
            		
            		atualizarLogradouroBairroHelper.setLogradouroBairro(logradouroBairro);
            		atualizarLogradouroBairroHelper.setBairro(bairroNovo);
            		
            		colecaoAtualizarLogradouroBairroHelper.add(atualizarLogradouroBairroHelper);
            		
            		sessao.setAttribute("colecaoAtualizarLogradouroBairroHelper", 
            		colecaoAtualizarLogradouroBairroHelper);
        		}
        		
        		//Atualização da tabela de exibição
        		colecaoBairrosSelecionadosUsuario = (List)
        		sessao.getAttribute("colecaoBairrosSelecionadosUsuario");
        		
        		Iterator iterator = colecaoBairrosSelecionadosUsuario.iterator();
        		bairroAtual = null;
        		
        		/*
        		 * Utilizado para verificar se será necessário adicionar o novo bairro. Ficará na dependência da
        		 * condicional que será realizada dentro do loop abaixo.
        		 * 
        		 * Colocado em 22/02/2007 por Raphael Rossiter
        		 */
        		boolean adicionarBairro = true;
        		
        		while (iterator.hasNext()){
        			
        			bairroAtual = (Bairro) iterator.next();
        			
        			if (bairroAtual.getId().equals(new Integer(idBairroAtual))){
        				
        				if (colecaoBairrosSelecionadosUsuario.contains(bairroNovo)){
        					
        					//Parte antiga
        					/*throw new ActionServletException(
                                    "atencao.objeto_ja_selecionado", null, "Bairro");*/
        					
        					/*
        					 * Caso o cep atual seja inicial de município e o novo cep já tenho sido adicionado
        					 * na coleção colecaoCepSelecionadosUsuario, removeremos apenas o cep inicial de
        					 * município e todos os imóveis que estiverem associados ao mesmo, serão automaticamente
        					 * associados ao cep de logradouro
        					 * 
        					 * Colocado em 22/02/2007 por Raphael Rossiter
        					 */
        					/*if (fachada.verificarBairroTipoBairroNaoInformado(bairroAtual)){
        						adicionarBairro = false;
        					}
        					else{
        						
        						throw new ActionServletException(
                                        "atencao.objeto_ja_selecionado", null, "Bairro");
        					}*/
        					
        					adicionarBairro = false;
        					
        				}
        				
        				colecaoBairrosSelecionadosUsuario.remove(bairroAtual);
        				
        				/*
                		 * Utilizado para verificar se será necessário adicionar o novo bairro.
                		 * 
                		 * Colocado em 22/02/2007 por Raphael Rossiter
                		 */
        				if (adicionarBairro){
        					colecaoBairrosSelecionadosUsuario.add(bairroNovo);
        				}
        				
        				break;
        			}
        		}
        	}
        	
        	//Organizar a coleção
            Collections.sort((List) colecaoBairrosSelecionadosUsuario, new Comparator() {
                public int compare(Object a, Object b) {
                    String nomeBairro1 = ((Bairro) a).getNome();
                    String nomeBairro2 = ((Bairro) b).getNome();

                    return nomeBairro1.compareTo(nomeBairro2);

                }
            });
        }
        
        
        
        /*
         * Alteração de Cep (Logradouro_Cep)
         */
        String cdCepAtual = httpServletRequest.getParameter("cdCepAtual");
        
        if (cdCepAtual != null && !cdCepAtual.equalsIgnoreCase("")){
        	
        	String cdCepNovo = httpServletRequest.getParameter("cdCepNovo");
        	Logradouro logradouro = (Logradouro) sessao.getAttribute("logradouro");
        	Collection colecaoAtualizarLogradouroCepHelper = null;
        	List colecaoCepSelecionadosUsuario = null;
        	Cep cepNovo = null;
        	Cep cepAtual = null;
        	AtualizarLogradouroCepHelper atualizarLogradouroCepHelper = null;
        	LogradouroCep logradouroCep = null;
        	boolean cepJaAtualizado = false;
        	
        	//Carregando o novo cep
        	FiltroCep filtroCep = new FiltroCep();
    		
        	filtroCep.adicionarParametro(new ParametroSimples(
            FiltroCep.CODIGO, cdCepNovo));
    		
    		Collection colecaoCep = fachada.pesquisar(filtroCep, 
    		Cep.class.getName());
    		
    		cepNovo = (Cep) Util.retonarObjetoDeColecao(colecaoCep);
        	
    		
        	if (sessao.getAttribute("colecaoAtualizarLogradouroCepHelper") != null){
        		
        		colecaoCepSelecionadosUsuario = (List)
        		sessao.getAttribute("colecaoCepSelecionadosUsuario");
        		colecaoAtualizarLogradouroCepHelper = (Collection)
        		sessao.getAttribute("colecaoAtualizarLogradouroCepHelper");
        		
        		Iterator iteratorCeps = colecaoCepSelecionadosUsuario.iterator();
        		Iterator iteratorAtualizacaoCeps = colecaoAtualizarLogradouroCepHelper.iterator();
        		
        		/*
        		 * Utilizado para verificar se será necessário adicionar o novo cep. Ficará na dependência da
        		 * condicional que será realizada dentro do loop abaixo.
        		 * 
        		 * Colocado em 22/02/2007 por Raphael Rossiter
        		 */
        		boolean adicionarCep = true;
        		
        		while (iteratorCeps.hasNext()){
        			
        			cepAtual = (Cep) iteratorCeps.next();
        			
        			if (cepAtual.getCodigo().equals(new Integer(cdCepAtual))){
        				
        				if (colecaoCepSelecionadosUsuario.contains(cepNovo)){
        					
        					//Parte antiga
        					/*throw new ActionServletException(
                                    "atencao.objeto_ja_selecionado", null, "Cep");*/
        					
        					/*
        					 * Caso o cep atual seja inicial de município e o novo cep já tenho sido adicionado
        					 * na coleção colecaoCepSelecionadosUsuario, removeremos apenas o cep inicial de
        					 * município e todos os imóveis que estiverem associados ao mesmo, serão automaticamente
        					 * associados ao cep de logradouro
        					 * 
        					 * Colocado em 22/02/2007 por Raphael Rossiter
        					 */
        					/*if (fachada.verificarCepInicialMunicipio(cepAtual)){
        						adicionarCep = false;
        					}
        					else{
        						
        						throw new ActionServletException(
                                        "atencao.objeto_ja_selecionado", null, "Cep");
        					}*/
        					
        					adicionarCep = false;
        					
        				}
        				
        				cepJaAtualizado = false;
        				while (iteratorAtualizacaoCeps.hasNext()){
        				
        					atualizarLogradouroCepHelper = (AtualizarLogradouroCepHelper)
        					iteratorAtualizacaoCeps.next();
        					
        					//Cep já atualizado
        					if (atualizarLogradouroCepHelper.getCep().getCodigo()
        						.equals(cepAtual.getCodigo())){
        						
        						atualizarLogradouroCepHelper.setCep(cepNovo);
        						cepJaAtualizado = true;
        						break;
        					}
        				}
        				
        				//Caso o cep ainda não tenha sido atualizado
        				if (!cepJaAtualizado){
        					
        					FiltroLogradouroCep filtroLogradouroCep = new FiltroLogradouroCep();
        	        		
        					filtroLogradouroCep.adicionarParametro(new ParametroSimples(
        					FiltroLogradouroCep.ID_LOGRADOURO, logradouro.getId()));
        	        		
        					filtroLogradouroCep.adicionarParametro(new ParametroSimples(
        	        		FiltroLogradouroCep.CODIGO_CEP, cdCepAtual));
        	        		
        	        		Collection colecaoLogradouroCep = fachada.pesquisar(filtroLogradouroCep, 
        	        		LogradouroCep.class.getName());
        	        		
        	        		logradouroCep = (LogradouroCep)
        	        		Util.retonarObjetoDeColecao(colecaoLogradouroCep);
        	        		
        	        		atualizarLogradouroCepHelper = new AtualizarLogradouroCepHelper();
        	        		
        	        		atualizarLogradouroCepHelper.setLogradouroCep(logradouroCep);
        	        		atualizarLogradouroCepHelper.setCep(cepNovo);
        	        		
        	        		colecaoAtualizarLogradouroCepHelper.add(atualizarLogradouroCepHelper);
        				}
        				
        				colecaoCepSelecionadosUsuario.remove(cepAtual);
        				
        				/*
                		 * Utilizado para verificar se será necessário adicionar o novo cep.
                		 * 
                		 * Colocado em 22/02/2007 por Raphael Rossiter
                		 */
        				if (adicionarCep){
        					colecaoCepSelecionadosUsuario.add(cepNovo);
        				}
        				
        				break;
        			}
        		}
        		
        	}
        	else{
        		
        		//Gerando coleção de apoio
        		colecaoAtualizarLogradouroCepHelper = new ArrayList();
        		
        		FiltroLogradouroCep filtroLogradouroCep = new FiltroLogradouroCep();
        		
				filtroLogradouroCep.adicionarParametro(new ParametroSimples(
				FiltroLogradouroCep.ID_LOGRADOURO, logradouro.getId()));
        		
				filtroLogradouroCep.adicionarParametro(new ParametroSimples(
        		FiltroLogradouroCep.CODIGO_CEP, cdCepAtual));
        		
        		Collection colecaoLogradouroCep = fachada.pesquisar(filtroLogradouroCep, 
        		LogradouroCep.class.getName());
        		
        		//Verifica se o usuário está alterando um cep que ele acabou de informar
        		if (colecaoLogradouroCep != null &&
        			!colecaoLogradouroCep.isEmpty()){
        		
        			logradouroCep = (LogradouroCep)
	        		Util.retonarObjetoDeColecao(colecaoLogradouroCep);
	        		
	        		atualizarLogradouroCepHelper = new AtualizarLogradouroCepHelper();
	        		
	        		atualizarLogradouroCepHelper.setLogradouroCep(logradouroCep);
	        		atualizarLogradouroCepHelper.setCep(cepNovo);
            		
	        		colecaoAtualizarLogradouroCepHelper.add(atualizarLogradouroCepHelper);
            		
            		sessao.setAttribute("colecaoAtualizarLogradouroCepHelper", 
            		colecaoAtualizarLogradouroCepHelper);
        		}
        		
        		//Atualização da tabela de exibição
        		colecaoCepSelecionadosUsuario = (List)
        		sessao.getAttribute("colecaoCepSelecionadosUsuario");
        		
        		Iterator iterator = colecaoCepSelecionadosUsuario.iterator();
        		cepAtual = null;
        		
        		/*
        		 * Utilizado para verificar se será necessário adicionar o novo cep. Ficará na dependência da
        		 * condicional que será realizada dentro do loop abaixo.
        		 * 
        		 * Colocado em 22/02/2007 por Raphael Rossiter
        		 */
        		boolean adicionarCep = true;
        		
        		while (iterator.hasNext()){
        			
        			cepAtual = (Cep) iterator.next();
        			
        			if (cepAtual.getCodigo().equals(new Integer(cdCepAtual))){
        				
        				if (colecaoCepSelecionadosUsuario.contains(cepNovo)){
        					
        					//Parte antiga
        					/*throw new ActionServletException(
                                    "atencao.objeto_ja_selecionado", null, "Cep");*/
        					
        					/*
        					 * Caso o cep atual seja inicial de município e o novo cep já tenho sido adicionado
        					 * na coleção colecaoCepSelecionadosUsuario, removeremos apenas o cep inicial de
        					 * município e todos os imóveis que estiverem associados ao mesmo, serão automaticamente
        					 * associados ao cep de logradouro
        					 * 
        					 * Colocado em 22/02/2007 por Raphael Rossiter
        					 */
        					/*if (fachada.verificarCepInicialMunicipio(cepAtual)){
        						adicionarCep = false;
        					}
        					else{
        						
        						throw new ActionServletException(
                                        "atencao.objeto_ja_selecionado", null, "Cep");
        					}*/
        					
        					adicionarCep = false;
        				}
        				
        				colecaoCepSelecionadosUsuario.remove(cepAtual);
        				
        				/*
                		 * Utilizado para verificar se será necessário adicionar o novo cep.
                		 * 
                		 * Colocado em 22/02/2007 por Raphael Rossiter
                		 */
        				if (adicionarCep){
        					colecaoCepSelecionadosUsuario.add(cepNovo);
        				}
        				
        				break;
        			}
        		}
        	}
        	
        	//Organizar a coleção
            Collections.sort((List) colecaoCepSelecionadosUsuario, new Comparator() {
                public int compare(Object a, Object b) {
                    String logradouroCep1 = ((Cep) a).getDescricaoLogradouroFormatada();
                    String logradouroCep2 = ((Cep) b).getDescricaoLogradouroFormatada();

                    return logradouroCep1.compareTo(logradouroCep2);

                }
            });
        }
        
        
        
        // Permissão especial
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        if (!fachada.verificarPermissaoAtualizarLogradouroBairro(usuarioLogado)) {
        	httpServletRequest.setAttribute("bloquearLinkBairro", "OK");
        }
        
        
		return retorno;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param parametro
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	private String formatarResultado(String parametro) {
		if (parametro != null && !parametro.trim().equals("")) {
			if (parametro.equals("null")) {
				return "";
			} else {
				return parametro.trim();
			}
		} else {
			return "";
		}
	}

}
