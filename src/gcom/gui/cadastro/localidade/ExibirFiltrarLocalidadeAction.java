package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarLocalidadeAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("filtrarLocalidade");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		if (sessao.getAttribute("consulta") != null) {
			sessao.removeAttribute("consulta");
		}
		
		FiltrarLocalidadeActionForm filtrarLocalidadeActionForm = (FiltrarLocalidadeActionForm) actionForm;

		Collection colecaoPesquisa;

		if (sessao.getAttribute("colecaoGerenciaRegional") == null) {

			// Carregamento inicial do formulário.
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

			filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME_ABREVIADO);
			
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna Gerencia_Regional
			colecaoPesquisa = fachada.pesquisar(filtroGerenciaRegional,
					GerenciaRegional.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Nenhum registro na tabela gerencia_regional foi encontrada
				throw new ActionServletException(
						"atencao.pesquisa.nenhum_registro_tabela", null,
						"Gerencia_Regional");
			} else {

				GerenciaRegional gerenciaRegional = null;
				Iterator iterator = colecaoPesquisa.iterator();

				while (iterator.hasNext()) {
					gerenciaRegional = (GerenciaRegional) iterator.next();

					String descGerenciaRegional = gerenciaRegional
							.getNomeAbreviado()
							+ "-" + gerenciaRegional.getNome();
					gerenciaRegional.setNome(descGerenciaRegional);

				}

				sessao.setAttribute("colecaoGerenciaRegional", colecaoPesquisa);
			}

		}
	
		if (sessao.getAttribute("colecaoUnidadeNegocio") == null) {

			// Carregamento inicial do formulário.
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			
			filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME_ABREVIADO);
			
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna Gerencia_Regional
			Collection colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio,
					UnidadeNegocio.class.getName());

			if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()) {
				// Nenhum registro na tabela gerencia_regional foi encontrada
				throw new ActionServletException(
						"atencao.pesquisa.nenhum_registro_tabela", null,
						"Unidade de Negócio");
			} else {

				UnidadeNegocio unidadeNegocio = null;
				Iterator iterator = colecaoUnidadeNegocio.iterator();

				while (iterator.hasNext()) {
					unidadeNegocio = (UnidadeNegocio) iterator.next();

					String descUnidadeNegocio = unidadeNegocio
							.getNomeAbreviado()
							+ "-" + unidadeNegocio.getNome();
					unidadeNegocio.setNome(descUnidadeNegocio);

				}

				sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
			}

		}
		
		
        String objetoConsulta = (String) httpServletRequest
        .getParameter("objetoConsulta");
		
        if (objetoConsulta != null
                && !objetoConsulta.trim().equalsIgnoreCase("")) {

            switch (Integer.parseInt(objetoConsulta)) {
            	case 1:
	        	
	        	
		        	String idGerenciaRegional = filtrarLocalidadeActionForm.getGerenciaID();
		        	
		        	if(idGerenciaRegional != null && !idGerenciaRegional.equals(ConstantesSistema.NUMERO_NAO_INFORMADO+"")){
		        	
						// Carregamento inicial do formulário.
						FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
						
						filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID_GERENCIA,idGerenciaRegional));
			
						filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
								FiltroUnidadeNegocio.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));
			
						// Retorna Gerencia_Regional
						Collection colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio,
								UnidadeNegocio.class.getName());
			
						if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()) {
							// Nenhum registro na tabela gerencia_regional foi encontrada
							throw new ActionServletException(
									"atencao.pesquisa.nenhum_registro_tabela", null,
									"Unidade de Negócio");
						} else {
			
							UnidadeNegocio unidadeNegocio = null;
							Iterator iterator = colecaoUnidadeNegocio.iterator();
			
							while (iterator.hasNext()) {
								unidadeNegocio = (UnidadeNegocio) iterator.next();
			
								String descUnidadeNegocio = unidadeNegocio
										.getNomeAbreviado()
										+ "-" + unidadeNegocio.getNome();
								unidadeNegocio.setNome(descUnidadeNegocio);
			
							}
			
							sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
						}
		        	}else{
						// Carregamento inicial do formulário.
						FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
						
						filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
								FiltroUnidadeNegocio.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));
			
						// Retorna Gerencia_Regional
						Collection colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio,
								UnidadeNegocio.class.getName());
			
						if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()) {
							// Nenhum registro na tabela gerencia_regional foi encontrada
							throw new ActionServletException(
									"atencao.pesquisa.nenhum_registro_tabela", null,
									"Unidade de Negócio");
						} else {
			
							UnidadeNegocio unidadeNegocio = null;
							Iterator iterator = colecaoUnidadeNegocio.iterator();
			
							while (iterator.hasNext()) {
								unidadeNegocio = (UnidadeNegocio) iterator.next();
			
								String descUnidadeNegocio = unidadeNegocio
										.getNomeAbreviado()
										+ "-" + unidadeNegocio.getNome();
								unidadeNegocio.setNome(descUnidadeNegocio);
			
							}
			
							sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
						}        		
		        	}
                break;

            	case 2:
            		
    	        	String idUnidadeNegocio = filtrarLocalidadeActionForm.getIdUnidadeNegocio();
    	        	
    	        	if(idUnidadeNegocio != null && !idUnidadeNegocio.equals(ConstantesSistema.NUMERO_NAO_INFORMADO+"")){
    	        	
						// Carregamento inicial do formulário.
						FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
						
						filtroUnidadeNegocio.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
						
						filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID,idUnidadeNegocio));
						
						filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
								FiltroUnidadeNegocio.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));
			
						// Retorna Gerencia_Regional
						Collection colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio,
								UnidadeNegocio.class.getName());
    	        		
    	        		UnidadeNegocio unidadeNegocio = (UnidadeNegocio) colecaoUnidadeNegocio.iterator().next();
						
    	    			// Carregamento inicial do formulário.
    	    			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
    	    			
    	    			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID,
    	    			unidadeNegocio.getGerenciaRegional().getId()));

    	    			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
    	    					FiltroGerenciaRegional.INDICADOR_USO,
    	    					ConstantesSistema.INDICADOR_USO_ATIVO));

    	    			// Retorna Gerencia_Regional
    	    			colecaoPesquisa = fachada.pesquisar(filtroGerenciaRegional,
    	    					GerenciaRegional.class.getName());

    	    			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
    	    				// Nenhum registro na tabela gerencia_regional foi encontrada
    	    				throw new ActionServletException(
    	    						"atencao.pesquisa.nenhum_registro_tabela", null,
    	    						"Gerencia_Regional");
    	    			} else {

    	    				GerenciaRegional gerenciaRegional = null;
    	    				Iterator iterator = colecaoPesquisa.iterator();

    	    				while (iterator.hasNext()) {
    	    					gerenciaRegional = (GerenciaRegional) iterator.next();
    	    					filtrarLocalidadeActionForm.setGerenciaID(gerenciaRegional.getId().toString());
    	    					String descGerenciaRegional = gerenciaRegional
    	    							.getNomeAbreviado()
    	    							+ "-" + gerenciaRegional.getNome();
    	    					gerenciaRegional.setNome(descGerenciaRegional);

    	    				}

    	    				sessao.setAttribute("colecaoGerenciaRegional", colecaoPesquisa);
    	    			}
    	        	}else{
    	    			// Carregamento inicial do formulário.
    	    			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

    	    			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
    	    					FiltroGerenciaRegional.INDICADOR_USO,
    	    					ConstantesSistema.INDICADOR_USO_ATIVO));

    	    			// Retorna Gerencia_Regional
    	    			colecaoPesquisa = fachada.pesquisar(filtroGerenciaRegional,
    	    					GerenciaRegional.class.getName());

    	    			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
    	    				// Nenhum registro na tabela gerencia_regional foi encontrada
    	    				throw new ActionServletException(
    	    						"atencao.pesquisa.nenhum_registro_tabela", null,
    	    						"Gerencia_Regional");
    	    			} else {

    	    				GerenciaRegional gerenciaRegional = null;
    	    				Iterator iterator = colecaoPesquisa.iterator();

    	    				while (iterator.hasNext()) {
    	    					gerenciaRegional = (GerenciaRegional) iterator.next();
    	    					
    	    					String descGerenciaRegional = gerenciaRegional
    	    							.getNomeAbreviado()
    	    							+ "-" + gerenciaRegional.getNome();
    	    					gerenciaRegional.setNome(descGerenciaRegional);

    	    				}

    	    				sessao.setAttribute("colecaoGerenciaRegional", colecaoPesquisa);
    	    			}
    	        	}
                
                default:

                    break;
                }
        }
		
				
		if (httpServletRequest.getParameter("desfazer") != null
		
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {

			// -------------- bt LIMPAR ---------------
			// Limpando o formulario
			filtrarLocalidadeActionForm.setLocalidadeID("");
			filtrarLocalidadeActionForm.setLocalidadeNome("");
			filtrarLocalidadeActionForm.setIndicadorUso("3");
			filtrarLocalidadeActionForm.setOrdernarPor("1");
			filtrarLocalidadeActionForm
					.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL
							.toString());
			filtrarLocalidadeActionForm.setIdUnidadeNegocio(""
					+ ConstantesSistema.NUMERO_NAO_INFORMADO);
			filtrarLocalidadeActionForm.setGerenciaID(""
					+ ConstantesSistema.NUMERO_NAO_INFORMADO);
			
			sessao.setAttribute("indicadorAtualizar", "1");
		}

		if (filtrarLocalidadeActionForm.getIndicadorUso() == null
				|| filtrarLocalidadeActionForm.getIndicadorUso()
						.equalsIgnoreCase("")) {
			filtrarLocalidadeActionForm.setIndicadorUso("3");
		}
		
		if (filtrarLocalidadeActionForm.getOrdernarPor() == null
				|| filtrarLocalidadeActionForm.getOrdernarPor()
						.equalsIgnoreCase("")) {
			filtrarLocalidadeActionForm.setOrdernarPor("1");
		}

		if (filtrarLocalidadeActionForm.getTipoPesquisa() == null
				|| filtrarLocalidadeActionForm.getTipoPesquisa()
						.equalsIgnoreCase("")) {
			filtrarLocalidadeActionForm
					.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL
							.toString());
		}

		// código para checar ou naum o Atualizar
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarLocalidadeActionForm.setIndicadorUso("3");
			filtrarLocalidadeActionForm.setOrdernarPor("1");
			filtrarLocalidadeActionForm
					.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL
							.toString());
		}
		// se voltou da tela de Atualizar Localidade
		if (sessao.getAttribute("voltar") != null
				&& sessao.getAttribute("voltar").equals("filtrar")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			if(sessao.getAttribute("tipoPesquisa") != null){
				filtrarLocalidadeActionForm.setTipoPesquisa(sessao.getAttribute("tipoPesquisa").toString());
			}
		}
		sessao.removeAttribute("voltar");
		sessao.removeAttribute("idRegistroAtualizacao");
		sessao.removeAttribute("tipoPesquisa");
		
		// devolve o mapeamento de retorno
		return retorno;
	}

}
