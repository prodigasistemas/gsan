package gcom.gui.arrecadacao;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Marcio Roberto
 * @date 25/01/2007
 */
public class InserirArrecadadorAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de um novo Arrecadador
	 * 
	 * [UC0381] Inserir Arrecadador
	 * 
	 * 
	 * @author Marcio Roberto
	 * @date 25/01/2007
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirArrecadadorActionForm inserirArrecadadorActionForm = (InserirArrecadadorActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		sessao.setAttribute("caminhoRetornoVoltar",
				"/gsan/exibirInserirArrecadadorAction.do");

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// Validamos o cliente
		FiltroCliente filtro = new FiltroCliente();
				
		filtro.adicionarParametro( new ParametroSimples(
				FiltroCliente.ID, inserirArrecadadorActionForm.getIdCliente() ) );
		
		Collection colCliente = fachada.pesquisar( filtro, Cliente.class.getName() );
		
		if ( colCliente == null || !colCliente.iterator().hasNext() ){
			// O cliente não existe
			throw new ActionServletException("atencao.cliente.inexistente",
					null, "Cliente");
		}
	
		String inscricaoEstadual = inserirArrecadadorActionForm.getInscricaoEstadual();
        
		
			//Inscricao Estadual
		if(!inserirArrecadadorActionForm.getInscricaoEstadual().trim().equals("") && inserirArrecadadorActionForm.getInscricaoEstadual() != null){
        	inserirArrecadadorActionForm.setInscricaoEstadual(inscricaoEstadual);
        	
        } else {
        	inscricaoEstadual = null;
        	inserirArrecadadorActionForm.setInscricaoEstadual(inscricaoEstadual);
        }
		
		if(inserirArrecadadorActionForm.getIdImovel() != null &&
				!inserirArrecadadorActionForm.getIdImovel().equals("")){
			// Validamos o imovel
			FiltroImovel filtroImovel = new FiltroImovel();
					
			filtroImovel.adicionarParametro( new ParametroSimples(
					FiltroImovel.ID, inserirArrecadadorActionForm.getIdImovel() ) );
			
			Collection colImovel = fachada.pesquisar( filtroImovel, Imovel.class.getName() );
			
			if ( colImovel == null || colImovel.isEmpty() ){
				// O Imovel não existe
				throw new ActionServletException("atencao.imovel.inexistente",
						null, "Imovel");
			}
           
		}
      
		
		Integer idArrecadador = fachada.inserirArrecadador(
				inserirArrecadadorActionForm.getIdAgente(), 
				inserirArrecadadorActionForm.getIdCliente(), 
				inserirArrecadadorActionForm.getInscricaoEstadual(), 
				inserirArrecadadorActionForm.getIdImovel(), usuario);
		
		String idRegistroAtualizacao = idArrecadador.toString();
		sessao.setAttribute("idRegistroAtualizacao",idRegistroAtualizacao);

		// Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Arrecadador de código " + idArrecadador
				+ " inserido com sucesso.", "Inserir outro Arrecadador",
				"exibirInserirArrecadadorAction.do?menu=sim",
				"exibirAtualizarArrecadadorAction.do?idRegistroInseridoAtualizar="
						+ idArrecadador, "Atualizar Arrecadador Inserido");

		sessao.removeAttribute("InserirArrecadadorActionForm");

		return retorno;
	}

}
