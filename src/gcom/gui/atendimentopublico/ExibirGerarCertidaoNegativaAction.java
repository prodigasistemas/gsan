package gcom.gui.atendimentopublico;

import gcom.cadastro.cliente.Cliente;import gcom.cadastro.cliente.ClienteTipo;import gcom.cadastro.cliente.FiltroCliente;import gcom.cadastro.imovel.FiltroImovel;import gcom.cadastro.imovel.Imovel;import gcom.fachada.Fachada;import gcom.gui.GcomAction;import gcom.util.ControladorException;import gcom.util.FachadaException;import gcom.util.Util;import gcom.util.filtro.ParametroSimples;import java.util.Collection;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import org.apache.struts.action.ActionForm;import org.apache.struts.action.ActionForward;import org.apache.struts.action.ActionMapping;

/**
 * [UC0738] Gerar Relatório de Imóveis com Faturas em Atraso
 * 
 * @author Bruno Barros
 *
 * @date 22/01/2008
 */


public class ExibirGerarCertidaoNegativaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,			ActionForm actionForm, HttpServletRequest httpServletRequest,			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno		ActionForward retorno = actionMapping.findForward("exibirGerarCertidaoNegativa");
		GerarCertidaoNegativaActionForm form = 			(GerarCertidaoNegativaActionForm) actionForm;
		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
		// Pesquisar Imovel		if (objetoConsulta != null && !objetoConsulta.trim().equals("") ) {			if ( objetoConsulta.trim().equals( "1" ) ){				// Faz a consulta do Imovel				this.pesquisarImovel(form,objetoConsulta, httpServletRequest);			}		}		return retorno;	}
	
	/**	 * Pesquisa Localidade	 *	 * @author Bruno Barros	 * @date 22/01/2008	 */
	private void pesquisarImovel(GerarCertidaoNegativaActionForm form,		String objetoConsulta, HttpServletRequest httpServletRequest) {
		String idImovel = form.getIdImovel();
		FiltroImovel filtroImovel = new FiltroImovel();		filtroImovel.adicionarCaminhoParaCarregamentoEntidade( "setorComercial" );		filtroImovel.adicionarCaminhoParaCarregamentoEntidade( "quadra" );		filtroImovel.adicionarParametro(			new ParametroSimples(FiltroImovel.ID, idImovel));
		// Recupera o Imovel		Collection colecaoImovel = 			this.getFachada().pesquisar(filtroImovel, Imovel.class.getName());
		if (colecaoImovel != null && !colecaoImovel.isEmpty()) {
			httpServletRequest.setAttribute("idImovelNaoEncontrado", null );
			Imovel imovel = 				(Imovel) Util.retonarObjetoDeColecao(colecaoImovel);						form.setIdImovel(imovel.getId().toString());			form.setMatriculaImovel(imovel.getInscricaoFormatada());
			// Encontramos o cliente Usuario			Cliente cliente = Fachada.getInstancia().pesquisarClienteUsuarioImovel( imovel.getId() );
			// Carregamos as informações			FiltroCliente filtroCliente = new FiltroCliente();			filtroCliente.adicionarCaminhoParaCarregamentoEntidade( "clienteTipo" );			filtroCliente.adicionarParametro(				new ParametroSimples(FiltroCliente.ID, cliente.getId()));
			// Recupera o Cliente			Collection colecaoCliente = 				this.getFachada().pesquisar(filtroCliente, Cliente.class.getName());
			cliente = 				(Cliente) Util.retonarObjetoDeColecao(colecaoCliente);			
			if ( cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica().intValue() == ClienteTipo.INDICADOR_PESSOA_JURIDICA.intValue()  ){				form.setCpfCnpj( cliente.getCnpjFormatado() );			} else {				form.setCpfCnpj( cliente.getCpfFormatado() );			}
			form.setNomeClienteUsuario( cliente.getNome() );
			try {				form.setEnderecoImovel( Fachada.getInstancia().pesquisarEnderecoFormatado( imovel.getId() ) );			} catch (ControladorException e) {				throw new FachadaException( e.getMessage() );			}			
		} else {
			form.setIdImovel( null );			form.setNomeClienteUsuario( null );			form.setCpfCnpj( null );			form.setEnderecoImovel( null );			form.setMatriculaImovel("Imovel inexistente");			httpServletRequest.setAttribute("idImovelNaoEncontrado", "s" );		}	}	
}
