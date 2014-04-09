package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.FiltroClienteEndereco;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroUsuarioPemissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecial;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Description of the Class
 * 
 * @author Rodrigo
 */
public class InserirClienteEnderecoAction extends GcomAction {

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

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping
				.findForward("gerenciadorProcesso");

		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		Collection colecaoEnderecos = (Collection) sessao
				.getAttribute("colecaoEnderecos");
		
        Fachada fachada = Fachada.getInstancia();

		if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()) {

			DynaValidatorForm clienteActionForm = (DynaValidatorForm) actionForm;

			// Radio que indica qual o endereco de correspondencia
			Long enderecoCorrespondenciaSelecionado = (Long) clienteActionForm
					.get("enderecoCorrespondenciaSelecao");
	        String nome = clienteActionForm.get("nome").toString();

			// Se o end. de correspondencia for escolhido então o objeto deve
			// ser alterado
			if (enderecoCorrespondenciaSelecionado != null) {
				Iterator iterator = colecaoEnderecos.iterator();

				// Varre a colecão para descobrir o objeto que tem o endereço de
				// correspondencia
				while (iterator.hasNext()) {
					ClienteEndereco clienteEndereco = (ClienteEndereco) iterator
							.next();

					if (obterTimestampIdObjeto(clienteEndereco) == enderecoCorrespondenciaSelecionado
							.longValue()) {
						// Indica que o objeto possui o endereço de
						// correspondencia
						clienteEndereco
								.setIndicadorEnderecoCorrespondencia(ConstantesSistema.INDICADOR_ENDERECO_CORRESPONDENCIA);
					} else {
						// Indica que o objeto não possui o endereço de
						// correspondencia
						clienteEndereco
								.setIndicadorEnderecoCorrespondencia(ConstantesSistema.INDICADOR_NAO_ENDERECO_CORRESPONDENCIA);
					}
				}

			} else {
				// Nenhum endereço foi indicado como endereço de correspondencia
				throw new ActionServletException(
						"atencao.endereco_correspondencia.nao_selecionado");

			}
			
			/**
			 * Autor: Mariana Victor
			 * Data:  04/01/2010
			 * RM_3320 - [FS0016] Verificar Duplicidade de cliente
			 */
			if (this.getSistemaParametro().getIndicadorDuplicidadeCliente().toString()
					.equals(ConstantesSistema.SIM.toString())) {
				
				FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
				filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
				filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, PermissaoEspecial.INSERIR_CLIENTE_COM_MESMO_NOME_E_ENDERECO));
										
				Collection colecaoUsuarioPermisao = fachada.pesquisar(filtroUsuarioPemissaoEspecial, UsuarioPermissaoEspecial.class.getName());
				
				if (colecaoUsuarioPermisao == null || colecaoUsuarioPermisao.isEmpty()) {
					FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
					filtroClienteEndereco.adicionarParametro(new ParametroSimples(FiltroClienteEndereco.NOME, nome.toUpperCase()));

					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoTipo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("cliente");
					
					Collection<ClienteEndereco> colecaoClienteEndereco = fachada.pesquisar(filtroClienteEndereco, ClienteEndereco.class.getName());
					
					if (colecaoClienteEndereco != null && !colecaoClienteEndereco.isEmpty()){
						Iterator iterator = colecaoClienteEndereco.iterator();
						
						while (iterator.hasNext()) {
							ClienteEndereco clienteEnderecoIterator = (ClienteEndereco) iterator.next();
							
							Iterator iteratorEnderecos = colecaoEnderecos.iterator();
							while (iteratorEnderecos.hasNext()) {
								ClienteEndereco clienteEndereco = (ClienteEndereco) iteratorEnderecos
										.next();
								
								if (clienteEndereco.getEnderecoFormatado().equals(
										clienteEnderecoIterator.getEnderecoFormatado())) {
									throw new ActionServletException("atencao.duplicidade.cliente", null,
										"Cliente");
								}
							}
						}
					}	
					
				}
				
			}
			

		} else {
			// O usuário deve preencher pelo menos um endereço e marcá-lo como
			// endereço de correspondência
			// Mostra o erro
			throw new ActionServletException(
					"atencao.endereco_correspondencia.nao_selecionado");

		}
		return retorno;
	}
}
