package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarImovelAtualizacaoCadastralDispositivoMovelAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		ActionForward retorno = actionMapping
				.findForward("gerarArquivoTextoAtualizacaoCadastralDispositivoMovel");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
        GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm form = (GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm) actionForm;
        
        Collection<Imovel> colecaoImovelFiltrado = new ArrayList();
        
        //Caso informe a inscrição 
        if(form.getLocalidade() != null && !form.getLocalidade().equals("")){
        	 //Usuário logado
        	 Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
        	//Localidade
        	Integer localidade = new Integer(form.getLocalidade());
			this.pesquisarLocalidade(localidade.toString(),form,fachada,httpServletRequest);
			//Setor comercial
        	String codigoSetorComercial = form.getSetorComercialCD();
        	if(codigoSetorComercial != null && !codigoSetorComercial.equals("")){
	        	if(form.getSetorComercialID() == null || form.getSetorComercialID().equals("")){        		
	        		this.pesquisarSetorComercial(localidade.toString(),codigoSetorComercial,form,fachada,httpServletRequest);       		
	        	}        	
	        	//Integer idsetor = new Integer(form.getSetorComercialID());
	        	//Quadra
	        	if(form.getQuadraInicial() != null && !form.getQuadraInicial().equals("")){
	            	String numeroQuadraInicial = form.getQuadraInicial();
	            	String numeroQuadraFinal = form.getQuadraFinal();
	          		this.pesquisarQuadraInicial(numeroQuadraInicial,codigoSetorComercial,localidade.toString(),form,fachada,httpServletRequest);
	    			if(new Integer(numeroQuadraInicial) > new Integer(numeroQuadraFinal)){				
	    				throw new ActionServletException(
	    							"atencao.quadra_final_menor", null, form.getIdImovel());	
	
	    			}else{
	    			    this.pesquisarQuadraFinal(numeroQuadraFinal,codigoSetorComercial,localidade.toString(),form,fachada,httpServletRequest);		
	    			}
	    			
	    			colecaoImovelFiltrado = fachada.obterImoveisAtualizacaoCadastral(localidade, codigoSetorComercial, new Integer(numeroQuadraInicial), 
	    											new Integer(numeroQuadraFinal), usuario.getEmpresa().getId(), null);
	    			form.setDescricaoArquivo("Loc"+localidade+"Set"+codigoSetorComercial+"QuadInic"+numeroQuadraInicial+"QuadFin"+numeroQuadraFinal);
	    			form.setCodigoRota("");
	    			form.setIdRota(null);
	        	}else if(form.getCodigoRota() != null && !form.getCodigoRota().equals("")){//Rota
	            	this.pesquisarRota(form, fachada, httpServletRequest, form.getSetorComercialID());
	            	Integer idRota = new Integer(form.getIdRota());
	    			colecaoImovelFiltrado = fachada.obterImoveisAtualizacaoCadastral(localidade, codigoSetorComercial, null, 
							null, usuario.getEmpresa().getId(), idRota);	   
	            	form.setDescricaoArquivo("Loc"+localidade+"Set"+codigoSetorComercial+"Rota"+form.getCodigoRota());
	            	form.setIdQuadraInicial(null);
	            	form.setQuadraInicial("");
	            	form.setIdQuadraFinal(null);
	            	form.setQuadraFinal("");            	
	            }else{
	        		colecaoImovelFiltrado = fachada.obterImoveisAtualizacaoCadastral(localidade, codigoSetorComercial, null, 
							null, usuario.getEmpresa().getId(), null);
	        		form.setDescricaoArquivo("Localidade"+localidade);	
	            }
        	}else{
        		colecaoImovelFiltrado = fachada.obterImoveisAtualizacaoCadastral(localidade, null, null, 
						null, usuario.getEmpresa().getId(), null);
        		form.setDescricaoArquivo("Localidade"+localidade);
        	}
			sessao.removeAttribute("informarDescricao");
			
			if (colecaoImovelFiltrado == null || colecaoImovelFiltrado.isEmpty() ) {
	        	//Nenhum Imovel cadastrado
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
        	}
			
        }else{//Caso informe os imóveis
        	colecaoImovelFiltrado = (Collection)sessao.getAttribute("colecaoImovel");  
        	form.setDescricaoArquivo("");
        	sessao.setAttribute("informarDescricao","Sim");
        	
        }
			
        sessao.setAttribute("colecaoImovelFiltrado",colecaoImovelFiltrado);

		return retorno;
	}
	
	/**
	 * Pesquisar Localidade
	 * @param filtroLocalidade
	 * @param idLocalidade
	 * @param localidades
	 * @param filtrarImovelAtualizacaoCadastralDispositivoMovelActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	public void pesquisarLocalidade(
			String idLocalidade,
			GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm form,
			Fachada fachada, HttpServletRequest httpServletRequest) {
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

		// coloca parametro no filtro
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, new Integer(idLocalidade)));

		// pesquisa
		Collection localidades = fachada.pesquisar(filtroLocalidade,
				Localidade.class.getName());
		if (localidades != null && !localidades.isEmpty()) {
			form.setLocalidade(((Localidade) ((List) localidades).get(0)).getId().toString());
			form.setNomeLocalidade(((Localidade) ((List) localidades).get(0)).getDescricao());
			
			httpServletRequest.setAttribute("localidadeNaoEncontrada","true");
			httpServletRequest.setAttribute("nomeCampo","setorComercialCD");
		} else {
			throw new ActionServletException(
					"atencao.localidade.inexistente", null, form.getIdImovel());	
		}
	}
	
	/**
	 * Pesquisar Setor Comercial
	 * @param filtroSetorComercial
	 * @param idLocalidadeFiltroFiltro
	 * @param codigoSetorComercial
	 * @param setorComerciais
	 * @param filtrarImovelAtualizacaoCadastralDispositivoMovelActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	public void pesquisarSetorComercial( 
			String idLocalidadeFiltroFiltro,
			String codigoSetorComercial,  
			GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm form,
			Fachada fachada, HttpServletRequest httpServletRequest) {
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		if (idLocalidadeFiltroFiltro != null && !idLocalidadeFiltroFiltro.toString().trim().equalsIgnoreCase("")) {
			// coloca parametro no filtro
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
					FiltroSetorComercial.ID_LOCALIDADE, new Integer(
							idLocalidadeFiltroFiltro)));
		}
			
		filtroSetorComercial.adicionarCaminhoParaCarregamentoEntidade("municipio");
		
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(
						codigoSetorComercial)));
		
		// pesquisa
		Collection setorComerciais = fachada.pesquisar(
				filtroSetorComercial, SetorComercial.class.getName());
		if (setorComerciais != null && !setorComerciais.isEmpty()) {
			form.setSetorComercialID(""
					+ ((SetorComercial) ((List) setorComerciais).get(0))
							.getId());
			form.setSetorComercialCD(""
					+ ((SetorComercial) ((List) setorComerciais).get(0))
							.getCodigo());
			form.setNomeSetorComercial(
					((SetorComercial) ((List) setorComerciais).get(0))
							.getDescricao());
			
			httpServletRequest.setAttribute("setorComercialNaoEncontrada", "true");
			httpServletRequest.setAttribute("nomeCampo", "quadraInicial");
		} else {
			throw new ActionServletException(
					"atencao.setor_comercial.inexistente", null, form.getIdImovel());	

		}	
	}
	
	
	/**
	 * Pesquisar Quadra Inicial
	 * 
	 * @param filtroQuadra
	 * @param numeroQuadra
	 * @param codigoSetorComercial
	 * @param quadras
	 * @param GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	public void pesquisarQuadraInicial(String numeroQuadra,
			String codigoSetorComercial, 
			String idLocalidadeFiltroFiltro,
			GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm form,
			Fachada fachada, HttpServletRequest httpServletRequest) {
		
		FiltroQuadra filtroQuadra = new FiltroQuadra();		
		if (idLocalidadeFiltroFiltro != null && 
				!idLocalidadeFiltroFiltro.toString().trim().equalsIgnoreCase("")) {
			// coloca parametro no filtro
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID_LOCALIDADE, new Integer(idLocalidadeFiltroFiltro)));
		}

		if (codigoSetorComercial != null
				&& !codigoSetorComercial.toString().trim().equalsIgnoreCase("")) {
			// coloca parametro no filtro
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(codigoSetorComercial)));
		}
		filtroQuadra.adicionarParametro(new ParametroSimples(
				FiltroQuadra.NUMERO_QUADRA, new Integer(numeroQuadra)));

		// pesquisa
		Collection quadras = fachada.pesquisar(filtroQuadra, Quadra.class
				.getName());
		
		if (quadras != null && !quadras.isEmpty()) {
			// O cliente foi encontrado
			form.setQuadraInicial(""
					+ ((Quadra) ((List) quadras).get(0)).getNumeroQuadra());
			form.setIdQuadraInicial(""
					+ ((Quadra) ((List) quadras).get(0)).getId());

		} else {
			throw new ActionServletException(
					"atencao.quadra.inexistente", null, form.getIdImovel());	
			
		}			
	}
	
	/**
	 * Pesquisar Quadra Final
	 * 
	 * @param filtroQuadra
	 * @param numeroQuadra
	 * @param codigoSetorComercial
	 * @param quadras
	 * @param GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	public void pesquisarQuadraFinal(String numeroQuadra,
			String codigoSetorComercial, 
			String idLocalidadeFiltroFiltro,
			GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm form,
			Fachada fachada, HttpServletRequest httpServletRequest) {
		
		FiltroQuadra filtroQuadra = new FiltroQuadra();		
		if (idLocalidadeFiltroFiltro != null && 
				!idLocalidadeFiltroFiltro.toString().trim().equalsIgnoreCase("")) {
			// coloca parametro no filtro
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.ID_LOCALIDADE, new Integer(idLocalidadeFiltroFiltro)));
		}

		if (codigoSetorComercial != null
				&& !codigoSetorComercial.toString().trim().equalsIgnoreCase("")) {
			// coloca parametro no filtro
			filtroQuadra.adicionarParametro(new ParametroSimples(
					FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(codigoSetorComercial)));
		}
		filtroQuadra.adicionarParametro(new ParametroSimples(
				FiltroQuadra.NUMERO_QUADRA, new Integer(numeroQuadra)));

		// pesquisa
		Collection quadras = fachada.pesquisar(filtroQuadra, Quadra.class
				.getName());
		
		if (quadras != null && !quadras.isEmpty()) {
			// O cliente foi encontrado
			form.setQuadraFinal(""
					+ ((Quadra) ((List) quadras).get(0)).getNumeroQuadra());
			form.setIdQuadraFinal(""
					+ ((Quadra) ((List) quadras).get(0)).getId());					

		} else {
			throw new ActionServletException(
					"atencao.quadra.inexistente", null, form.getIdImovel());	
		}			
	}
	
    /**
     * 
     * @param form
     * @param fachada
     * @param httpServletRequest
     */
    private void pesquisarRota(GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm form,
            Fachada fachada, HttpServletRequest httpServletRequest, String setorComercialId) {
    	
    	Rota objetoRota = null;
        	
    	//Recebe o valor do campo rotaID do formulário.
        String rotaCodigo = form.getCodigoRota();

        FiltroRota filtroRota = new FiltroRota();

        filtroRota.adicionarParametro(new ParametroSimples(
        FiltroRota.SETOR_COMERCIAL_ID, new Integer(setorComercialId)));
        
        filtroRota.adicionarParametro(new ParametroSimples(
        FiltroRota.CODIGO_ROTA, rotaCodigo));

        filtroRota.adicionarParametro(new ParametroSimples(
        FiltroRota.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

        //Retorna Rota
        Collection colecaorotas = fachada.pesquisar(filtroRota, Rota.class
                .getName());

        if (colecaorotas == null || colecaorotas.isEmpty()) {
            //Rota nao encontrada
			throw new ActionServletException(
					"atencao.pesquisa.rota_inexistente", null, form.getIdImovel());	
            
        } else {
            objetoRota = (Rota) Util
                    .retonarObjetoDeColecao(colecaorotas);
            form.setCodigoRota(objetoRota.getCodigo().toString());
            form.setIdRota(String.valueOf(objetoRota.getId()));
            httpServletRequest.setAttribute("corRotaMensagem", "valor");                
            httpServletRequest.setAttribute("nomeCampo", "botaoSelecionar");
        }

    }

}
