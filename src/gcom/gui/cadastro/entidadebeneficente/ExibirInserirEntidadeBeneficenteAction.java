package gcom.gui.cadastro.entidadebeneficente;

import gcom.cadastro.cliente.Cliente;import gcom.cadastro.cliente.FiltroCliente;import gcom.cadastro.empresa.Empresa;import gcom.cadastro.empresa.FiltroEmpresa;import gcom.fachada.Fachada;import gcom.faturamento.debito.DebitoTipo;import gcom.faturamento.debito.FiltroDebitoTipo;import gcom.gui.ActionServletException;import gcom.gui.GcomAction;import gcom.util.ConstantesSistema;import gcom.util.Util;import gcom.util.filtro.ParametroSimples;import java.util.Collection;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import org.apache.struts.action.ActionForm;import org.apache.struts.action.ActionForward;import org.apache.struts.action.ActionMapping;

/**
 * Carrega os dados necessários e redireciona para a página que invocará o [UC0915] Inserir Entidade Beneficente.
 * Pré-valida algumas informações ao usuário utilizar a tecla enter para selecionar o cliente e o tipo de débito.
 *  
 * @author Samuel Valerio
 * @date 11/06/2009
 * @since 4.1.6.4
 *
 */
public class ExibirInserirEntidadeBeneficenteAction extends GcomAction {
	
	@Override
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		 ActionForward retorno = actionMapping.findForward("inserirEntidadeBeneficente");
		 
		 Fachada fachada = Fachada.getInstancia();
		 
		 EntidadeBeneficenteActionForm form = (EntidadeBeneficenteActionForm) actionForm;
		 
		 httpServletRequest.setAttribute("nomeCampo", "entidadeBeneficente.cliente.id");
		 
		 Cliente cliente = form.getEntidadeBeneficente().getCliente();
		 
		 
		 if (cliente.getId() != null) {
			 FiltroCliente filtroCliente = new FiltroCliente();
			 
			 filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, cliente.getId()));
			 filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
			 
			 Collection colecaoCliente = fachada.pesquisar(filtroCliente, Cliente.class.getName());
		
			// [FS0001] - Verificar existência do cliente
			if (colecaoCliente == null || colecaoCliente.isEmpty()) {
				if (!new Integer(0).equals(cliente.getId()))
					cliente.setNome("CLIENTE INEXISTENTE");
				cliente.setId(null);
				httpServletRequest.setAttribute("existeCliente","exception");	
				httpServletRequest.setAttribute("nomeCampo", "entidadeBeneficente.cliente.id");
			}else{
				
				Cliente clienteEncontrado = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
							
				// [FS0002] - Verificar se cliente é pessoa jurídica
				fachada.validarSeClienteEhPessoaJuridica(clienteEncontrado); 
				
				form.getEntidadeBeneficente().setCliente(clienteEncontrado);
				httpServletRequest.setAttribute("nomeCampo","entidadeBeneficente.debitoTipo.id");
			}
				 
			 
		 }
		 
		DebitoTipo debitoTipo = form.getEntidadeBeneficente().getDebitoTipo();
		 
		if (debitoTipo.getId() != null) {
			
			FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
			filtroDebitoTipo.adicionarParametro(new ParametroSimples(
					FiltroDebitoTipo.ID, debitoTipo.getId()));
		
			Collection colecaoDebitoTipo = fachada.pesquisar(filtroDebitoTipo,
					DebitoTipo.class.getName());
			
			// [FS0003] - Verificar existência do tipo de débito
			if (colecaoDebitoTipo == null || colecaoDebitoTipo.isEmpty()) {
				if (!new Integer(0).equals(debitoTipo.getId()))
					debitoTipo.setDescricao("TIPO DE DÉBITO INEXISTENTE");
				debitoTipo.setId(null);
				httpServletRequest.setAttribute("existeDebitoTipo","exception");	
				httpServletRequest.setAttribute("nomeCampo", "entidadeBeneficente.debitoTipo.id");
			} else {
				DebitoTipo debitoTipoEncontrado = (DebitoTipo) Util.retonarObjetoDeColecao(colecaoDebitoTipo);
				
				// [FS0004] - Verificar se tipo de débito não é gerado automaticamente
				fachada.validarSeDebitoTipoNaoEhGeradoAutomaticamente(debitoTipoEncontrado);
				
				form.getEntidadeBeneficente().setDebitoTipo(debitoTipoEncontrado);
				httpServletRequest.setAttribute("nomeCampo", "entidadeBeneficente.empresa.id");
			}
		
		} 
		 
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();

		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
		 
		Collection<Empresa> colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
					Empresa.class.getName());
		 
		if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()) { 
			throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"Empresa");
		}

		httpServletRequest.setAttribute("colecaoEmpresa", colecaoEmpresa);
		 
		return retorno;
	}
	
}
