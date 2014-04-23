package gcom.gui.cadastro;

import gcom.cadastro.cliente.FiltroFoneTipo;
import gcom.cadastro.cliente.FoneTipo;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC1052] Informar Telefone
 *  
 * @author 	Daniel Alves 
 * @date  	05/08/2010
 */
public class ExibirInformarTelefoneAction extends GcomAction {
	
	
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
				.findForward("informarTelefone");
		
		HttpSession sessao = getSessao(httpServletRequest);
		
		InformarTelefoneActionForm informarTelefoneActionForm = 
			(InformarTelefoneActionForm) actionForm;
		
		String idCliente = (String) httpServletRequest.getParameter("idCliente");
		String idImovel = (String) httpServletRequest.getParameter("idImovel");
		String limparForm = (String) httpServletRequest.getParameter("limparForm");
		
		if(idCliente != null && !idCliente.equals("")){
			informarTelefoneActionForm.setIdCliente(idCliente);
		}
		
		if(idImovel != null && !idImovel.equals("")){
			informarTelefoneActionForm.setIdImovel(idImovel);
		}
		
		if(limparForm != null && limparForm.equals("S")){
			informarTelefoneActionForm.setTipoTelefone("");
			informarTelefoneActionForm.setTelefonePrincipal("");
			informarTelefoneActionForm.setMunicipio("");
			informarTelefoneActionForm.setMunicipioId("");
			informarTelefoneActionForm.setNomeContato("");
			informarTelefoneActionForm.setNumeroTelefone("");
			informarTelefoneActionForm.setRamal("");
			informarTelefoneActionForm.setDdd("");
		}
		
		
		// Colecao de Tipos Telefone
		Collection<FoneTipo> colecaoTiposTelefone = null;
		
		colecaoTiposTelefone = this.pesquisarColecaoTiposTelefone();
		
		if(colecaoTiposTelefone!= null && colecaoTiposTelefone.size() !=0){
			httpServletRequest.setAttribute("tiposTelefone", colecaoTiposTelefone);
		}
		
		
		//se pesquisar o municipio pelo popup, para selecionar o ddd.
		String tipoConsulta = (String)httpServletRequest.getParameter("tipoConsulta");
		if(tipoConsulta != null && !tipoConsulta.equals("") && tipoConsulta.equals("municipio")){
			
			informarTelefoneActionForm.setMunicipioId((String)httpServletRequest.getParameter("idCampoEnviarDados"));
			informarTelefoneActionForm.setMunicipio((String)httpServletRequest.getParameter("descricaoCampoEnviarDados"));
			
			if(informarTelefoneActionForm.getMunicipioId() != null && 
					!informarTelefoneActionForm.getMunicipioId().equals("")){
				
				Municipio municipio = pesquisarMunicipio(
						Integer.valueOf(informarTelefoneActionForm.getMunicipioId()));
				
				if(municipio != null){
					informarTelefoneActionForm.setDdd( municipio.getDdd().toString() );
				}
			}			
			
		}
		
		
		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");

		// Pesquisar com enter
		if (objetoConsulta != null && !objetoConsulta.trim().equals("")){
			// Faz a consulta de Municipio
			if(objetoConsulta.trim().equals("1")){
				this.pesquisarMunicipio(informarTelefoneActionForm);
				
			}else if(objetoConsulta.trim().equals("2")){
				this.pesquisarDDD(informarTelefoneActionForm);
			}
			
		}
		
		
		//Seta os request´s encontrados
		this.setaRequest(httpServletRequest,informarTelefoneActionForm);
		
		if(httpServletRequest.getParameter("nomeDigitado")!=null 
    			&& httpServletRequest.getParameter("cpfCnpj")!=null
    			&& httpServletRequest.getParameter("clienteImovel")!=null){
    		
			sessao.setAttribute("nomeDigitado", httpServletRequest.getParameter("nomeDigitado"));
			sessao.setAttribute("cpfCnpj", httpServletRequest.getParameter("cpfCnpj"));
			sessao.setAttribute("clienteImovel", httpServletRequest.getParameter("clienteImovel"));
    		
    	}
				
				
		return retorno;

	}



	/**
	 * Pesquisa Colecao Tipos de Telefone 
	 * @author Daniel Alves 
	 */

	private Collection pesquisarColecaoTiposTelefone() {

		FiltroFoneTipo filtroFoneTipo = new FiltroFoneTipo();
		
		Collection colecaoTiposTelefone = this.getFachada().pesquisar(filtroFoneTipo, FoneTipo.class.getName());

		if (colecaoTiposTelefone != null && !colecaoTiposTelefone.isEmpty()) {
			
			return (Collection) colecaoTiposTelefone;

		} else {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Tipo de Telefone");
		}

	}
	
	
	/**
	 * Pesquisa Municipio
	 * 
	 * @author Daniel Alves
	 * @date 03/08/2010
	 */
	private Municipio pesquisarMunicipio(int municipioId) {

		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, municipioId));		

		Collection colecaoMunicipio = this.getFachada().pesquisar(filtroMunicipio, Municipio.class.getName());

		if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {
			
			return (Municipio) Util.retonarObjetoDeColecao( colecaoMunicipio);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null,"Municipio");
		}

	}
	
	/**
	 * Pesquisa Municipio
	 *
	 * @author Daniel Alves
	 * @date 21/07/2010
	 */
	private void pesquisarMunicipio(InformarTelefoneActionForm form) {

		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(
				new ParametroSimples(FiltroMunicipio.ID, 
				form.getMunicipioId()));
		
		// Recupera Municipio
		Collection colecaoMunicipio = 
			this.getFachada().pesquisar(filtroMunicipio, Municipio.class.getName());
	
		if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {

			Municipio municipio = 
				(Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);
			
			form.setMunicipioId(municipio.getId().toString());
			form.setMunicipio(municipio.getNome());
			form.setDdd( municipio.getDdd().toString() );
			
		} else {
			form.setMunicipioId(null);
			form.setMunicipio("Municipio inexistente");
		}
	}	
	
	
	/**
	 * Pesquisa Municipio
	 *
	 * @author Daniel Alves
	 * @date 21/07/2010
	 */
	private void pesquisarDDD(InformarTelefoneActionForm form) {
		

		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(
				new ParametroSimples(FiltroMunicipio.DDD, 
				form.getDdd()));
		
		// Recupera Municipio
		Collection colecaoMunicipio = 
			this.getFachada().pesquisar(filtroMunicipio, Municipio.class.getName());
	
		if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {

			Municipio municipio = 
				(Municipio) Util.retonarObjetoDeColecao(colecaoMunicipio);
			
			form.setMunicipioId(municipio.getId().toString());
			form.setMunicipio(municipio.getNome());
			form.setDdd( municipio.getDdd().toString() );
			
		} else {
			form.setMunicipioId(null);
			form.setMunicipio("Municipio inexistente");
		}
	}
	
	/**
	 * Seta os request com os id encontrados 
	 *
	 * @author Rafael Pinto
	 * @date 23/11/2007
	 */
	private void setaRequest(HttpServletRequest httpServletRequest,
			InformarTelefoneActionForm form){
		
		//Municipio
		if(form.getMunicipioId() != null && 
			!form.getMunicipioId().equals("") && 
			form.getMunicipio() != null && 
			!form.getMunicipio().equals("")){
					
			httpServletRequest.setAttribute("municipioEncontrado","true");
		}else{
			httpServletRequest.setAttribute("municipioEncontrado","false");
		}
		
	}


}
