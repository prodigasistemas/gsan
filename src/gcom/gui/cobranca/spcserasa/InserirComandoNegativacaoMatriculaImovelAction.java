package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.bean.DadosNegativacaoPorImovelHelper;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade validar as informações da segunda aba do
 * processo de inserção de um Comando de Negativação
 * 
 * @author Ana Maria
 * @date 06/11/2007
 */
public class InserirComandoNegativacaoMatriculaImovelAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws ControladorException {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		InserirComandoNegativacaoActionForm inserirComandoNegativacaoActionForm = (InserirComandoNegativacaoActionForm) actionForm;
		
        //Pesquisa Usuario 
		String usuario = inserirComandoNegativacaoActionForm.getUsuario();
		
        if(usuario != null && !usuario.equals("")){
        	
        	FiltroUsuario filtroUsuario = new FiltroUsuario();  
            
        	filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, usuario));
            
            Collection colecaoUsuario = fachada.pesquisar(
                    filtroUsuario,Usuario.class.getName());
            
            if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
            	httpServletRequest.setAttribute("corUsuario", "valor");
            	
            	inserirComandoNegativacaoActionForm.setUsuario(""
						+ ((Usuario) ((List) colecaoUsuario).get(0)).getId());
            	inserirComandoNegativacaoActionForm.setNomeUsuario(""
						+ ((Usuario) ((List) colecaoUsuario).get(0)).getNomeUsuario());
			} else {
				throw new ActionServletException(
							"atencao.cliente.inexistente");
			}
        }
        
		Collection<DadosNegativacaoPorImovelHelper> colecaoDadosNegativacaoPorImovelHelper = (Collection)sessao.getAttribute("colecaoDadosNegativacaoPorImovelHelper");

		if(colecaoDadosNegativacaoPorImovelHelper == null || colecaoDadosNegativacaoPorImovelHelper.isEmpty()){
			throw new ActionServletException(
					"atencao.informe_imovel_negativacao");
		}
		
		//Verifica o bloqueio de negativação para o cliente
		Iterator iColecaoDadosNegativacao = colecaoDadosNegativacaoPorImovelHelper.iterator();
		
		while (iColecaoDadosNegativacao.hasNext()){
			
			DadosNegativacaoPorImovelHelper dadosNegativacao = (DadosNegativacaoPorImovelHelper)iColecaoDadosNegativacao.next();
			
			Collection colecaoCliente = null;
			if (dadosNegativacao.getIdCliente() != null && !dadosNegativacao.getIdCliente().equals("")){
				
				FiltroCliente filtroCliente = new FiltroCliente();
				filtroCliente.adicionarParametro(new ParametroSimples(
						FiltroCliente.ID, dadosNegativacao.getIdCliente()));
				
				colecaoCliente = fachada.pesquisarCliente(filtroCliente);
				
				Iterator icliente = colecaoCliente.iterator();
		        Cliente cliente = (Cliente) icliente.next();
				if (cliente.getIndicadorPermiteNegativacao().equals(ConstantesSistema.NAO)){
		        	  throw new ActionServletException("atencao.cliente_bloqueado_negativacao", cliente.getNome());
		          }
				
			} 
		}
		
		Integer idNegativador = new Integer(inserirComandoNegativacaoActionForm.getIdNegativador());
		String identificacaoCI = inserirComandoNegativacaoActionForm.getIdentificacaoCI();
		Integer idUsuario = new Integer(inserirComandoNegativacaoActionForm.getUsuario());
	
		fachada.gerarMovimentoInclusaoNegativacao(null, 
				ConstantesSistema.TIPO_COMANDO_POR_MATRICULA_IMOVEIS, 
				identificacaoCI, 
				idNegativador, 
				idUsuario, 
				colecaoDadosNegativacaoPorImovelHelper, 
				new Date(),
				inserirComandoNegativacaoActionForm.getIndicadorBaixaRenda(),
				inserirComandoNegativacaoActionForm.getIndicadorContaNomeCliente(),
				inserirComandoNegativacaoActionForm.getIndicadorImovelCategoriaPublico());		

		//Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Foram enviados "+colecaoDadosNegativacaoPorImovelHelper.size()
				+ " imóveis para negativação.", "Efetuar outra Negativação",
				"");
		
		return retorno;
	}
}
