package gcom.gui.cadastro.localidade;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
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
 * @author Thiago Tenório
 * @date 30/10/2006
 */
public class ExibirAtualizarGerenciaRegionalAction extends GcomAction {
	/**
	 * [UC00] Atualizar Gerência Regional
	 * 
	 * Este caso de uso permite alterar uma valor de uma Gerência Regional
	 * 
	 * @author Thiago Tenório, Ivan Sérgio
	 * @date 02/10/2007, 11/06/2007
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarGerenciaRegional");

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarGerenciaRegionalActionForm atualizarGerenciaRegionalActionForm = 
			(AtualizarGerenciaRegionalActionForm) actionForm;

		String removerEndereco = (String) httpServletRequest.getParameter("removerEndereco");
		String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		
		// Remove o endereco informado.
		if (removerEndereco != null
				&& !removerEndereco.trim().equalsIgnoreCase("")) {

			if (sessao.getAttribute("colecaoEnderecos") != null) {
				Collection enderecos = (Collection) sessao
						.getAttribute("colecaoEnderecos");
				if (!enderecos.isEmpty()) {
					enderecos.remove(enderecos.iterator().next());
				}
			}
		} else {
			if (httpServletRequest.getParameter("menu") != null) {
				atualizarGerenciaRegionalActionForm.setGerenciaRegionalID("");
				atualizarGerenciaRegionalActionForm.setNome("");
				atualizarGerenciaRegionalActionForm.setNomeAbreviado("");
			}

			Fachada fachada = Fachada.getInstancia();
			
			String idGerenciaRegional = null;
		
			if (httpServletRequest.getParameter("idRegistroAtualizacao") != null
					&& !httpServletRequest.getParameter("idRegistroAtualizacao")
							.equals("")) {
				sessao.removeAttribute("gerenciaRegional");
				sessao.removeAttribute("colecaoGerenciaRegionalTela");
			}

			// Verifica se veio do filtrar ou do manter
			if (httpServletRequest.getParameter("manter") != null) {
				sessao.setAttribute("manter", true);
			} else if (httpServletRequest.getParameter("filtrar") != null) {
				sessao.removeAttribute("manter");
			}
		
			// Usado para o botao Voltar
			if (sessao.getAttribute("gerenciaRegional") != null) {
	   			//Definindo a volta do botão Voltar p Filtrar Localidade
	        	sessao.setAttribute("voltar", "manter");
	        }else {
	        	sessao.setAttribute("voltar", "filtrar");
	        }
		
			if(objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")){
				switch (Integer.parseInt(objetoConsulta)) {
		            
					//Gerente da Localidade
	            	case 1:
		            	
		            	this.pesquisarCliente(atualizarGerenciaRegionalActionForm);
		            	break;
	
		            default:
		                break;
		        }
			} 
					
				
					
			
			// Verifica se o servicoCobrancaValor já está na sessão, em caso
			// afirmativo
			// significa que o usuário já entrou na tela e apenas selecionou algum
			// item que deu um reload na tela e em caso negativo significa que ele
			// está entrando pela primeira vez
			if (sessao.getAttribute("colecaoGerenciaRegionalTela") == null) {
				if (sessao.getAttribute("gerenciaRegional") != null) {
					GerenciaRegional gerenciaRegional = (GerenciaRegional) sessao.getAttribute("gerenciaRegional");
	
					sessao.setAttribute("idGerenciaRegional", gerenciaRegional.getId().toString());
					sessao.setAttribute("gerenciaRegional", gerenciaRegional);
					
					atualizarGerenciaRegionalActionForm.setGerenciaRegionalID(gerenciaRegional.getId().toString());
					atualizarGerenciaRegionalActionForm.setNome(gerenciaRegional.getNome());
					
					atualizarGerenciaRegionalActionForm.setCnpjGerenciaRegional(gerenciaRegional.getCnpjGerenciaRegional());
					
					atualizarGerenciaRegionalActionForm.setNomeAbreviado(gerenciaRegional.getNomeAbreviado());
					atualizarGerenciaRegionalActionForm.setTelefone(gerenciaRegional.getFone());
					atualizarGerenciaRegionalActionForm.setRamal(gerenciaRegional.getRamalFone());
					atualizarGerenciaRegionalActionForm.setFax(gerenciaRegional.getFax());
					atualizarGerenciaRegionalActionForm.setEmail(gerenciaRegional.getEmail());
					
					if(gerenciaRegional.getCliente() != null 
							&& !gerenciaRegional.getCliente().equals("")){
						atualizarGerenciaRegionalActionForm.setIdCliente(
								gerenciaRegional.getCliente().getId().toString());
						atualizarGerenciaRegionalActionForm.setNomeCliente(
								gerenciaRegional.getCliente().getNome());
					}	
					
					if(gerenciaRegional.getIndicadorUso() != null) {
						atualizarGerenciaRegionalActionForm.setIndicadorUso(gerenciaRegional
								.getIndicadorUso().toString());
					}else {
						// Seta como Ativo
						Short indicadorUso = 1;
						gerenciaRegional.setIndicadorUso(indicadorUso);
						atualizarGerenciaRegionalActionForm.setIndicadorUso(gerenciaRegional
								.getIndicadorUso().toString());
					}
					
					Collection colecaoEnderecos = null;
	
					if (gerenciaRegional.getEnderecoFormatado() != null) {
						colecaoEnderecos = new ArrayList();
						Localidade endereco = new Localidade();
						
						endereco.setLogradouroCep(gerenciaRegional.getLogradouroCep());
						endereco.setLogradouroBairro(gerenciaRegional.getLogradouroBairro());
						endereco.setComplementoEndereco(gerenciaRegional.getComplementoEndereco());
						endereco.setNumeroImovel(gerenciaRegional.getNumeroImovel());
						endereco.setEnderecoReferencia(gerenciaRegional.getEnderecoReferencia());
						
						colecaoEnderecos.add(endereco);
						sessao.setAttribute("colecaoEnderecos", colecaoEnderecos);
					}
	
					
					sessao.setAttribute("gerenciaRegionalAtualizar", gerenciaRegional);
					sessao.removeAttribute("gerenciaRegional");

				} else if((httpServletRequest.getParameter("desfazer") != null
					  && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) || 
					  httpServletRequest.getParameter("idRegistroAtualizacao") != null) {

					GerenciaRegional gerenciaRegional = null;
	
					idGerenciaRegional = null;
	
					if (httpServletRequest.getParameter("idRegistroAtualizacao") == null
							|| httpServletRequest.getParameter(
									"idRegistroAtualizacao").equals("")) {
						gerenciaRegional = (GerenciaRegional) sessao
								.getAttribute("gerenciaRegional");
					} else {
						idGerenciaRegional = (String) httpServletRequest
								.getParameter("idRegistroAtualizacao");
						sessao.setAttribute("idRegistroAtualizacao",
								idGerenciaRegional);
					}
					
					if (idGerenciaRegional == null) {
						gerenciaRegional = (GerenciaRegional) sessao.getAttribute("gerenciaRegionalAtualizar");
						idGerenciaRegional = gerenciaRegional.getId().toString();
					}
	
					if (idGerenciaRegional != null) {
	
	

						filtroGerenciaRegional
								.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
						filtroGerenciaRegional
								.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
						filtroGerenciaRegional
								.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
						filtroGerenciaRegional
								.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
						filtroGerenciaRegional
								.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
						filtroGerenciaRegional
								.adicionarCaminhoParaCarregamentoEntidade("cliente");
						
						filtroGerenciaRegional
								.adicionarParametro(new ParametroSimples(
										FiltroGerenciaRegional.ID,
										idGerenciaRegional));
	
						Collection<GerenciaRegional> colecaoGerenciaRegional = fachada
								.pesquisar(filtroGerenciaRegional,
										GerenciaRegional.class.getName());
	
						if (colecaoGerenciaRegional == null
								|| colecaoGerenciaRegional.isEmpty()) {
							throw new ActionServletException(
									"atencao.atualizacao.timestamp");
						}
						httpServletRequest.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
						gerenciaRegional = (GerenciaRegional) colecaoGerenciaRegional.iterator().next();
					}
	
					atualizarGerenciaRegionalActionForm.setGerenciaRegionalID(gerenciaRegional.getId().toString());
					atualizarGerenciaRegionalActionForm.setNome(gerenciaRegional.getNome());
					atualizarGerenciaRegionalActionForm.setNomeAbreviado(gerenciaRegional.getNomeAbreviado());
					atualizarGerenciaRegionalActionForm.setTelefone(gerenciaRegional.getFone());
					atualizarGerenciaRegionalActionForm.setRamal(gerenciaRegional.getRamalFone());
					atualizarGerenciaRegionalActionForm.setFax(gerenciaRegional.getFax());
					atualizarGerenciaRegionalActionForm.setEmail(gerenciaRegional.getEmail());
					
					if(gerenciaRegional.getCliente() != null){
						atualizarGerenciaRegionalActionForm.setIdCliente(
								gerenciaRegional.getCliente().getId().toString());
						atualizarGerenciaRegionalActionForm.setNomeCliente(
								gerenciaRegional.getCliente().getNome());
					}
					
					if(gerenciaRegional.getIndicadorUso() != null) {
						atualizarGerenciaRegionalActionForm.setIndicadorUso(gerenciaRegional
								.getIndicadorUso().toString());
					}else {
						// Seta como Ativo
						Short indicadorUso = 1;
						gerenciaRegional.setIndicadorUso(indicadorUso);
						atualizarGerenciaRegionalActionForm.setIndicadorUso(gerenciaRegional
								.getIndicadorUso().toString());
					}
	
					sessao.setAttribute("gerenciaRegionalAtualizar", gerenciaRegional);
					
					if (gerenciaRegional.getEnderecoFormatado() != null) {
						Collection colecaoEnderecos = new ArrayList();
						Localidade endereco = new Localidade();
						
						endereco.setLogradouroCep(gerenciaRegional.getLogradouroCep());
						endereco.setLogradouroBairro(gerenciaRegional.getLogradouroBairro());
						endereco.setComplementoEndereco(gerenciaRegional.getComplementoEndereco());
						endereco.setNumeroImovel(gerenciaRegional.getNumeroImovel());
						endereco.setEnderecoReferencia(gerenciaRegional.getEnderecoReferencia());
						
						colecaoEnderecos.add(endereco);
						sessao.setAttribute("colecaoEnderecos", colecaoEnderecos);
					}

				}
			}
		}

		httpServletRequest.setAttribute("colecaoGerenciaRegionalTela", sessao
				.getAttribute("colecaoGerenciaRegionalTipoValorTela"));
		
        //Codigo Cliente
		if(atualizarGerenciaRegionalActionForm.getIdCliente() != null && 
			!atualizarGerenciaRegionalActionForm.getIdCliente().equals("") && 
			atualizarGerenciaRegionalActionForm.getNomeCliente() != null && 
			!atualizarGerenciaRegionalActionForm.getNomeCliente().equals("")){
							
			httpServletRequest.setAttribute("gerenteLocalidadeEncontrado","true");
		}	
		
		return retorno;
	}
	
	/**
	 * Pesquisa Cliente 
	 *
	 * @author Rafael Pinto
	 * @date 15/08/2006
	 */
	private void pesquisarCliente(AtualizarGerenciaRegionalActionForm form) {
		
		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.adicionarParametro(
			new ParametroSimples(FiltroCliente.ID, 
			new Integer(form.getIdCliente())));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoCliente = 
			this.getFachada().pesquisar(filtroCliente,Cliente.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoCliente != null && !colecaoCliente.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			Cliente cliente = 
				(Cliente) Util.retonarObjetoDeColecao(colecaoCliente);

			form.setIdCliente(cliente.getId().toString());
			form.setNomeCliente(cliente.getNome());
			

		} else {
			form.setIdCliente("");
			form.setNomeCliente("Cliente inexistente");
		}
	}
}
