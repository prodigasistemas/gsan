package gcom.gui.cadastro;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroFoneTipo;
import gcom.cadastro.cliente.FoneTipo;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InformarTelefoneAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("informarTelefoneAction");

		HttpSession sessao = getSessao(httpServletRequest);
		
		// Obtém a instância da fachada
		sessao.removeAttribute("ConsultarImovelActionForm");

		InformarTelefoneActionForm informarTelefoneActionForm = 
			(InformarTelefoneActionForm) actionForm;
				
        String ddd = informarTelefoneActionForm.getDdd();
        
        if(this.isDddMunicipioValido(ddd)){
        	
        	ClienteFone clienteFone = new ClienteFone();
        	
        	Cliente cliente = (Cliente)this.pesquisarCliente(informarTelefoneActionForm.getIdCliente());
        	
        	clienteFone.setCliente(cliente);
        	clienteFone.setContato(informarTelefoneActionForm.getNomeContato());
        	clienteFone.setDdd(informarTelefoneActionForm.getDdd());

        	FoneTipo foneTipo = new FoneTipo();
        	foneTipo = (FoneTipo) this.pesquisarFoneTipo(informarTelefoneActionForm.getTipoTelefone());

        	clienteFone.setFoneTipo(foneTipo);
        	clienteFone.setIndicadorTelefonePadrao(Short.valueOf(informarTelefoneActionForm.getTelefonePrincipal()) );
        	clienteFone.setRamal(informarTelefoneActionForm.getRamal());
        	clienteFone.setTelefone(informarTelefoneActionForm.getNumeroTelefone());
        	clienteFone.setUltimaAlteracao(new Date());
        	
        	//fachada.inserir(clienteFone);
        	
        	//verifica se o id do imovel estiver sido informado
        	String idImovel = (String) informarTelefoneActionForm.getIdImovel();
        	if(idImovel != null & !idImovel.equals("")){
        		sessao.setAttribute("idImovel", idImovel);
    		}


        	sessao.setAttribute("clienteFoneAdicionar", clienteFone);
        	
        	
        }else{
        	throw new ActionServletException("atencao.ddd_municipio.inexistente");
        }        
	    
		return retorno;
	}
	
	
	private boolean isDddMunicipioValido(String ddd){
		
		boolean isValido = false;
		
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
		filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.DDD, ddd));
		
		Collection colecao = this.getFachada().pesquisar(filtroMunicipio, Municipio.class.getName());
		
		if(colecao != null && colecao.size() > 0){
			isValido = true;
		}
		
		return isValido;
	}
	
	private Cliente pesquisarCliente(String idCliente){
		
		FiltroCliente filtroCliente = new FiltroCliente();
		
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idCliente));
		
		Collection colecaoCliente = this.getFachada().pesquisar(filtroCliente, Cliente.class.getName());
		
		return (Cliente)Util.retonarObjetoDeColecao(colecaoCliente);

	}
	
	private FoneTipo pesquisarFoneTipo(String idFoneTipo){
		
		FiltroFoneTipo filtroFoneTipo = new FiltroFoneTipo();
		
		filtroFoneTipo.adicionarParametro(new ParametroSimples(FiltroFoneTipo.ID, idFoneTipo) );
		
		Collection colecaoFoneTipo = (Collection)this.getFachada().pesquisar(filtroFoneTipo, FoneTipo.class.getName());
		
		return (FoneTipo) Util.retonarObjetoDeColecao(colecaoFoneTipo); 
		
	}
	
}
