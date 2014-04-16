package gcom.gui.arrecadacao;
import java.util.Collection;
import java.util.Date;

import gcom.arrecadacao.Arrecadador;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * [UC005] Manter Municipio [SB0001]Atualizar Municipio
 *
 * @author Kássia Albuquerque
 * @date 08/01/2007
 */


public class AtualizarArrecadadorAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		AtualizarArrecadadorActionForm atualizarArrecadadorActionForm = (AtualizarArrecadadorActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Arrecadador arrecadador = (Arrecadador) sessao.getAttribute("arrecadador");
		
		Fachada fachada = Fachada.getInstancia();
		
		// Validamos o cliente
		FiltroCliente filtro = new FiltroCliente();
				
		arrecadador.setId(new Integer(atualizarArrecadadorActionForm.getId()));
		arrecadador.setNumeroInscricaoEstadual(atualizarArrecadadorActionForm.getInscricaoEstadual());
		arrecadador.setIndicadorUso(new Short (atualizarArrecadadorActionForm.getIndicadorUso()));
		
		filtro.adicionarParametro( new ParametroSimples(
				FiltroCliente.ID, atualizarArrecadadorActionForm.getIdCliente() ) );
		
		Collection colCliente = fachada.pesquisar( filtro, Cliente.class.getName() );
		
		if ( colCliente == null || !colCliente.iterator().hasNext() ){
			// O cliente não existe
			throw new ActionServletException("atencao.cliente.inexistente",
					null, "Cliente");
		}
		
		if(atualizarArrecadadorActionForm.getIdImovel() != null
				&& !atualizarArrecadadorActionForm.getIdImovel().equals("")){
			// Validamos o imovel
			FiltroImovel filtroImovel = new FiltroImovel();
					
			filtroImovel.adicionarParametro( new ParametroSimples(
					FiltroImovel.ID, atualizarArrecadadorActionForm.getIdImovel() ) );
			
			Collection colImovel = fachada.pesquisar( filtroImovel, Imovel.class.getName() );
			
			if ( colImovel == null || colImovel.isEmpty() ){
				// O Imovel não existe
				throw new ActionServletException("atencao.imovel.inexistente",
						null, "Imovel");
			}
		}
		
		String id = atualizarArrecadadorActionForm.getId();		
        String inscricaoEstadual = atualizarArrecadadorActionForm
        .getInscricaoEstadual();
        String indicadorUso = atualizarArrecadadorActionForm.getIndicadorUso();
		
		String[] idArrecadador = new String[1];
		
		idArrecadador[0] = arrecadador.getId().toString();
		
		//Atualiza a entidade com os valores do formulário
	//	atualizarArrecadadorActionForm.setFormValues(arrecadador);
		
		// Seta Objeto Cliente para Arrecadador
		FiltroCliente filtroCliente = new FiltroCliente();
		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, atualizarArrecadadorActionForm.getIdCliente()));
		Collection colecaoCliente = fachada.pesquisar(filtroCliente, Cliente.class.getName());		
		Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
		arrecadador.setCliente(cliente);
		if(atualizarArrecadadorActionForm.getIdImovel() != null && !atualizarArrecadadorActionForm.getIdImovel().equals("")){
			// Seta Objeto Imovel para Arrecadador
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, atualizarArrecadadorActionForm.getIdImovel()));
			Collection colecaoImovel = fachada.pesquisar(filtroImovel,Imovel.class.getName());
			Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
			arrecadador.setImovel(imovel);
		}
		
        arrecadador.setId(new Integer(id));
        
        if(!atualizarArrecadadorActionForm.getInscricaoEstadual().trim().equals("") && atualizarArrecadadorActionForm.getInscricaoEstadual() != null){
        	arrecadador.setNumeroInscricaoEstadual(inscricaoEstadual);
        	
        } else {
        	inscricaoEstadual = null;
        	arrecadador.setNumeroInscricaoEstadual(inscricaoEstadual);
        }
       
        arrecadador.setUltimaAlteracao( new Date() );	
        arrecadador.setIndicadorUso(new Short(indicadorUso));
        
		//atualiza na base de dados de Arrecadador
		fachada.atualizarArrecadador(arrecadador,usuarioLogado);
		
		//Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Arrecadador de código "+ atualizarArrecadadorActionForm.getId()+" atualizado com sucesso.", "Realizar outra Manutenção de Arrecadador",
				"exibirFiltrarArrecadadorAction.do?menu=sim");
		
		return retorno;
	}
	
}	      
    



