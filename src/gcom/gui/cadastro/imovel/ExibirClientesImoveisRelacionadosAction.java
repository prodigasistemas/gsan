package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirClientesImoveisRelacionadosAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarClientesImoveisRelacionados");
		
		ImovelOutrosCriteriosActionForm form = (ImovelOutrosCriteriosActionForm) actionForm; 
		
		//Inicio Pesquisar imove condominio
		if ((form.getIdImovelCondominio() != null) && !(form.getIdImovelCondominio().equals(""))) {
			
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,form.getIdImovelCondominio()));
			filtroImovel
			.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
			filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
			
			filtroImovel
			.adicionarCaminhoParaCarregamentoEntidade("quadra");			
			filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
			filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
			filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
			filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			/*filtroImovel.adicionarCaminhoParaCarregamentoEntidade("lote");
			filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("subLote");*/
			filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("localidade");			
			
			Collection coll = Fachada.getInstancia().pesquisar(filtroImovel,Imovel.class.getName());
			if (coll != null && !coll.isEmpty()) {
				Imovel imovel = (Imovel) coll.iterator().next();
				form.setIdImovelCondominio(imovel.getId().toString());
				form.setInscricaoImovelCondominio(imovel.getInscricaoFormatada());
				httpServletRequest.setAttribute("corTipoImovelCondominio", "valor");
			}
			else{
				httpServletRequest.setAttribute("corTipoImovelCondominio", "exception");
				form.setInscricaoImovelCondominio("IMOVEL INEXISTENTE");
				form.setIdImovelCondominio("");
			}
		}else{
			httpServletRequest.setAttribute("corTipoImovelCondominio", "exception");
		}
		
		//Inicio Pesquisar imove Principal
		if ((form.getIdImovelPrincipal() != null) && !(form.getIdImovelPrincipal().equals(""))) {
			
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,form.getIdImovelPrincipal()));
			filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
			filtroImovel
			.adicionarCaminhoParaCarregamentoEntidade("quadra");			
			
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			/*filtroImovel.adicionarCaminhoParaCarregamentoEntidade("lote");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("subLote");*/
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("localidade");
			
			
			Collection coll = Fachada.getInstancia().pesquisar(filtroImovel,Imovel.class.getName());
			if (coll != null && !coll.isEmpty()) {
				Imovel imovel = (Imovel) coll.iterator().next();
				form.setIdImovelPrincipal(imovel.getId().toString());
				form.setInscricaoImovelPrincipal(imovel.getInscricaoFormatada());
				httpServletRequest.setAttribute("corTipoImovelPrincipal", "valor");
			}
			else{
				httpServletRequest.setAttribute("corTipoImovelPrincipal", "exception");
				form.setInscricaoImovelPrincipal("IMOVEL INEXISTENTE");
				form.setIdImovelPrincipal("");
			}
		}else{
			httpServletRequest.setAttribute("corTipoImovelPrincipal", "exception");
		}
		
		
		//Inicio Pesquisar nomeCliente		
		if ((form.getIdCliente() != null) && !(form.getIdCliente().equals(""))) {
			
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,form.getIdCliente()));
			
			
			Collection coll = Fachada.getInstancia().pesquisarCliente(filtroCliente);
			if (coll != null && !coll.isEmpty()) {
				Cliente cliente = cliente = (Cliente) coll.iterator().next();
				form.setIdCliente(cliente.getId().toString());
				form.setNomeCliente(cliente.getNome());
				httpServletRequest.setAttribute("corTipoCliente", "valor");
			}else{
				form.setIdCliente("");
				form.setNomeCliente("CLIENTE INEXISTENTE");
				httpServletRequest.setAttribute("corTipoCliente", "exception");
			}
		}else{
			httpServletRequest.setAttribute("corTipoCliente", "exception");
		}
			
		//Fim Pesquisar nomeCliente
		
		Fachada fachada = Fachada.getInstancia();

		//Nome Conta
		/*FiltroNomeConta filtroNomeConta = new FiltroNomeConta();
		Collection<NomeConta> collectionsNomeConta = fachada.pesquisar(filtroNomeConta, NomeConta.class.getName() );
		
		if(collectionsNomeConta == null || collectionsNomeConta.isEmpty()){
			throw new ActionServletException("atencao.naocadastrado", null,
			"Nome Conta");
		}*/
		
		//Cliente Relacao Tipo
		FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();
		Collection<ClienteRelacaoTipo> collectionsClienteRelacaoTipo = fachada.pesquisar(filtroClienteRelacaoTipo, ClienteRelacaoTipo.class.getName());

		if(collectionsClienteRelacaoTipo == null || collectionsClienteRelacaoTipo.isEmpty()){
			throw new ActionServletException("atencao.naocadastrado", null,
			"Tipo da Relação");			
		}
		
		
		//Cliente Tipo
		FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();
		Collection<ClienteTipo> collectionsClienteTipo = fachada.pesquisar(filtroClienteTipo, ClienteTipo.class.getName() );
		
		if(collectionsClienteTipo == null || collectionsClienteTipo.isEmpty()){
			throw new ActionServletException("atencao.naocadastrado", null,
			"Tipo de Cliente");			
		}
		
		if(form.getIndicadorCpfCnpjInformado()==null ||
				(form.getIndicadorCpfCnpjInformado()!=null && form.getIndicadorCpfCnpjInformado().equals(""))){
			form.setIndicadorCpfCnpjInformado(ConstantesSistema.TODOS.toString());
			httpServletRequest.setAttribute("indicadorCpfCnpjInformado", ConstantesSistema.TODOS.toString());
		}else{
			httpServletRequest.setAttribute("indicadorCpfCnpjInformado", form.getIndicadorCpfCnpjInformado());
		}
		
		
		//httpServletRequest.setAttribute("collectionsNomeConta", collectionsNomeConta);
		httpServletRequest.setAttribute("collectionsClienteRelacaoTipo", collectionsClienteRelacaoTipo);
		httpServletRequest.setAttribute("collectionsClienteTipo", collectionsClienteTipo);
		
		return retorno;
	}
	
	
	/*private String pesquisarLocalidade(String inscricaoTipo,
			ImovelOutrosCriteriosActionForm imovelOutrosCriteriosActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {
		
	colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
			Localidade.class.getName());

	if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
		// Localidade nao encontrada
		// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
		// formulário
		imovelOutrosCriteriosActionForm.setLocalidadeOrigemID("");
		imovelOutrosCriteriosActionForm.setNomeLocalidadeOrigem("");
		httpServletRequest.setAttribute("corLocalidadeOrigem",
				"exception");
	} else {
		Localidade objetoLocalidade = (Localidade) Util
				.retonarObjetoDeColecao(colecaoPesquisa);
		imovelOutrosCriteriosActionForm.setLocalidadeOrigemID(String
				.valueOf(objetoLocalidade.getId()));
		imovelOutrosCriteriosActionForm
				.setNomeLocalidadeOrigem(objetoLocalidade
						.getDescricao());
						httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");
	}	
  }*/

}
