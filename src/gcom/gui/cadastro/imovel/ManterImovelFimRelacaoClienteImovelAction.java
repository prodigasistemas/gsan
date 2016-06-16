package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteImovelFimRelacaoMotivo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Rafael Santos
 * @since 17/04/2006
 */
public class ManterImovelFimRelacaoClienteImovelAction extends GcomAction {
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

		// Prepara o retorno da Ação
		ActionForward retorno = actionMapping
				.findForward("atualizarImovelCliente");
		// Cria a sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();

		FimRelacaoClienteImovelActionForm fimRelacaoClienteImovelActionForm = (FimRelacaoClienteImovelActionForm) actionForm;

		Collection colecaoClientesImoveisFimRelacao = (Collection) sessao
				.getAttribute("colecaoClientesImoveisFimRelacao");
		
		Collection colecaoClientesImoveisRemovidos = (Collection)
			sessao.getAttribute("colecaoClientesImoveisRemovidos");
		
        Collection imovelClientesNovos = (Collection) sessao
        .getAttribute("imovelClientesNovos");		
		
		// verifica se tem algum cliente imovel que precisa ser
		// atualizado com a data de termino da
		// relação e o motivo.
		if (colecaoClientesImoveisFimRelacao != null
				&& !colecaoClientesImoveisFimRelacao.isEmpty()) {
			Iterator clienteImovelIterator = colecaoClientesImoveisFimRelacao
					.iterator();
			String idMotivoFimRelacao = fimRelacaoClienteImovelActionForm
					.getIdMotivo();

			ClienteImovelFimRelacaoMotivo clienteImovelFimRelacao = new ClienteImovelFimRelacaoMotivo();
			// seta o id do motivo do fim da relação
			clienteImovelFimRelacao.setId(new Integer(idMotivoFimRelacao));

			String dataFimRelacaoForm = fimRelacaoClienteImovelActionForm
					.getDataTerminoRelacao();

			SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");

			Date dataFimRelacao = null;
			try {
				dataFimRelacao = dataFormato.parse(dataFimRelacaoForm);
			} catch (ParseException ex) {
				dataFimRelacao = null;
			}

			Date dataCorrente = null;
			Calendar a = Calendar.getInstance();
			a.set(Calendar.SECOND, 0);
			a.set(Calendar.MILLISECOND, 0);
			a.set(Calendar.HOUR, 0);
			a.set(Calendar.MINUTE, 0);
			dataCorrente = a.getTime();

			if (dataFimRelacao.after(dataCorrente)) {
				throw new ActionServletException(
						"atencao.data_fim_relacao_cliente_imovel");
			}

			while (clienteImovelIterator.hasNext()) {
				ClienteImovel clienteImovel = (ClienteImovel) clienteImovelIterator
						.next();
				if (dataFimRelacao.before(clienteImovel
						.getDataInicioRelacao())) {
					throw new ActionServletException(
							"atencao.data_fim_relacao_cliente_imovel_menor_inicial");

				}

			}

			// caso a data não seja menor que a atual então
			// seta a data final no cliente imovel
			clienteImovelIterator = colecaoClientesImoveisFimRelacao
					.iterator();

			while (clienteImovelIterator.hasNext()) {
				ClienteImovel clienteImovel = (ClienteImovel) clienteImovelIterator
						.next();
				clienteImovel
						.setClienteImovelFimRelacaoMotivo(clienteImovelFimRelacao);
				clienteImovel.setDataFimRelacao(dataFimRelacao);

                 if (clienteImovel.isClienteUsuario()) {
                     if(sessao.getAttribute(
                             "idClienteImovelUsuario") != null){
                    	 sessao.removeAttribute("idClienteImovelUsuario");	 
                     }
                	 
                 }
                 
                 if (clienteImovel.isClienteResponsavel()) {
                     if(sessao.getAttribute("idClienteImovelResponsavel") != null){ 
                    	 sessao.removeAttribute("idClienteImovelResponsavel");	 
                     }
                 }
                
             	
                if(!(colecaoClientesImoveisRemovidos.contains(clienteImovel))){
                	fachada.verificaRestricaoDaTabelaClienteImovel(clienteImovel);
                	colecaoClientesImoveisRemovidos.add(clienteImovel);	
             	}
                 
             	imovelClientesNovos.remove(clienteImovel);
			}
		}
		
		sessao.setAttribute("imovelClientesNovos", imovelClientesNovos);
		sessao.setAttribute("colecaoClientesImoveisFimRelacao", null);
		sessao.setAttribute("colecaoClientesImoveisRemovidos", colecaoClientesImoveisRemovidos);
		httpServletRequest.setAttribute("reloadPage", "OK");
		
		return retorno;
	}
	
}
