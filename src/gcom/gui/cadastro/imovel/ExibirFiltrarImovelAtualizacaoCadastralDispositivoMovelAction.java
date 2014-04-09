package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.ImovelAtualizacaoCadastral;
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

public class ExibirFiltrarImovelAtualizacaoCadastralDispositivoMovelAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping
                .findForward("exibirFiltrarImovelAtualizacaoCadastralDispositivoMovel");

        //Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        
        GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm form = (GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm) actionForm;

		String idLocalidade = null;
		String codigoSetorComercial = null;
		String numeroQuadraInicial = null;
		String numeroQuadraFinal = null;
		
    	idLocalidade = (String) form.getLocalidade();
    	codigoSetorComercial = (String) form.getSetorComercialCD();
    	numeroQuadraInicial = (String) form.getQuadraInicial();
    	numeroQuadraFinal = (String) form.getQuadraFinal();

		String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
		
		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")){
			switch (Integer.parseInt(objetoConsulta)) {
			// Localidade
			case 1:
		
				this.pesquisarLocalidade(idLocalidade,form,fachada,httpServletRequest);
		
				break;
			// Setor Comercial
			case 2:
		
				this.pesquisarSetorComercial( idLocalidade,codigoSetorComercial,form,fachada,httpServletRequest);
				break;
				
			// Quadra Inicial
			case 3:				
				this.pesquisarQuadraInicial(numeroQuadraInicial,codigoSetorComercial,idLocalidade,form,fachada,httpServletRequest);		
				break;

			// Quadra Final
			case 4:		
				if(new Integer(numeroQuadraInicial) > new Integer(numeroQuadraFinal)){
					form.setQuadraFinal("");
					form.setIdQuadraFinal(null);					
		            httpServletRequest.setAttribute( "codigoQuadraFinalNaoEncontrada", "true");
					httpServletRequest.setAttribute("msgQuadra", "QUADRA FINAL MENOR QUE INICIAL");
				}else{
				    this.pesquisarQuadraFinal(numeroQuadraFinal,codigoSetorComercial,idLocalidade,form,fachada,httpServletRequest);		
				}
				break;
				
			//Rota
			case 5:
				this.pesquisarRota(form, fachada, httpServletRequest, form.getSetorComercialID());
				break;
				
			//Imóvel
			case 6:
				this.pesquisarImovel(httpServletRequest, fachada, form);			
				
			default:
				break;
			}
		}
		
		if (form.getIdRota() != null && !form.getIdRota().trim().equals("")) {
			FiltroRota filtroRota = new FiltroRota();
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.ID_ROTA, form.getIdRota()));
			
			Collection colecaoRota = fachada.pesquisar(filtroRota, Rota.class.getName());
			
			if (colecaoRota != null && !colecaoRota.isEmpty()) {
				Rota rota = (Rota) Util.retonarObjetoDeColecao(colecaoRota);
				form.setCodigoRota(rota.getCodigo().toString());
			}
		}
		
		
		Collection colecaoImovel = (Collection)sessao.getAttribute("colecaoImovel");

		//Adicionar Imóvel
		String adicionarImovel = (String) httpServletRequest.getParameter("adicionarImovel");
		if (adicionarImovel != null && !adicionarImovel.trim().equalsIgnoreCase("")){
       	 	//Usuário logado
       	 	Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
       	 	ImovelAtualizacaoCadastral imovel = fachada.pesquisarImovelAtualizacaoCadastralInscricao(new Integer(form.getIdImovel().trim()), 
											usuario.getEmpresa().getId());
			if(imovel != null && !imovel.equals("")){	
				colecaoImovel = (Collection)sessao.getAttribute("colecaoImovel");
				if(colecaoImovel == null || colecaoImovel.isEmpty()){
					colecaoImovel = new ArrayList();								
			    }else{
					if(colecaoImovel.contains(imovel)){
						throw new ActionServletException(
								"atencao.imovel_ja_incluido", null, form.getIdImovel());	
					}
			    }
				colecaoImovel.add(imovel);	
				sessao.setAttribute("colecaoImovel",colecaoImovel);
				httpServletRequest.setAttribute("existeColecaoImovel","Sim");
				form.setIdImovel("");
				form.setInscricaoImovel("");
		    }else{
				form.setIdImovel("");
				form.setInscricaoImovel("Imóvel Indisponível");
				httpServletRequest.setAttribute("existeImovel","exception");	
		    }
		}
		
		//Remover Imóvel
		if(httpServletRequest.getParameter("idImovelRemover") != null){
			Integer idImovelRemover = new Integer(httpServletRequest.getParameter("idImovelRemover"));
			colecaoImovel = (Collection)sessao.getAttribute("colecaoImovel");			
			if(colecaoImovel != null && !colecaoImovel.isEmpty()){
				ImovelAtualizacaoCadastral imovel = new ImovelAtualizacaoCadastral();
				imovel.setIdImovel(idImovelRemover);
				colecaoImovel.remove(imovel);	
				if(colecaoImovel != null && !colecaoImovel.isEmpty()){
				  sessao.setAttribute("colecaoImovel", colecaoImovel);
				  httpServletRequest.setAttribute("existeColecaoImovel","Sim");
				}
			}
		}
		
		if(colecaoImovel != null && !colecaoImovel.isEmpty()){
			httpServletRequest.setAttribute("existeColecaoImovel","Sim");
		}
		
        return retorno;
    }

	/**
	 * Pesquisar Imóvel
	 * @param httpServletRequest
	 * @param fachada	  
	 * @param filtrarImovelAtualizacaoCadastralDispositivoMovelActionForm
	 */
	private void pesquisarImovel(HttpServletRequest httpServletRequest, Fachada fachada, GerarArquivoTextoAtualizacaoCadastralDispositivoMovelActionForm form) {

		String imovelEncontrado = fachada.pesquisarInscricaoImovel(new Integer(form.getIdImovel()));
		if (imovelEncontrado != null && !imovelEncontrado.equalsIgnoreCase("")) {
			
			// O imovel foi encontrado
			form.setIdImovel(form.getIdImovel());
			form.setInscricaoImovel(imovelEncontrado);
		} else {
			form.setIdImovel("");
			form.setInscricaoImovel(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
			httpServletRequest.setAttribute("existeImovel","exception");
		}
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
			form.setLocalidade("");
			form.setNomeLocalidade("Localidade inexistente");
			
			httpServletRequest.setAttribute("localidadeNaoEncontrada","exception");
			httpServletRequest.setAttribute("nomeCampo", "localidade");
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
		if (idLocalidadeFiltroFiltro != null
				&& !idLocalidadeFiltroFiltro.toString().trim().equalsIgnoreCase("")) {
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
			form.setSetorComercialCD( "");
			form.setNomeSetorComercial("Setor comercial inexistente");
			
			httpServletRequest.setAttribute("setorComercialNaoEncontrada", "exception");
			httpServletRequest.setAttribute("nomeCampo","setorComercialCD");

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
			httpServletRequest.setAttribute("nomeCampo", "quadraFinal");
		} else {
			form.setQuadraInicial("");
			form.setIdQuadraInicial(null);
			
            httpServletRequest.setAttribute( "codigoQuadraInicialNaoEncontrada", "true");
			httpServletRequest.setAttribute("msgQuadra", "QUADRA INICIAL INEXISTENTE");
			
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
			form.setQuadraFinal("");
			form.setIdQuadraFinal(null);
			
            httpServletRequest.setAttribute( "codigoQuadraFinalNaoEncontrada", "true");
			httpServletRequest.setAttribute("msgQuadra", "QUADRA FINAL INEXISTENTE");
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
            //Limpa o campo idRota do formulário
    		form.setCodigoRota("");
    		form.setIdRota("");
            httpServletRequest.setAttribute( "codigoRotaNaoEncontrada", "true");
			httpServletRequest.setAttribute("msgRota", "ROTA INEXISTENTE");
            
        } else {
            objetoRota = (Rota) Util
                    .retonarObjetoDeColecao(colecaorotas);
            form.setCodigoRota(objetoRota.getCodigo().toString());
            form.setIdRota(String.valueOf(objetoRota.getId()));
        }
    }

}
