package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.bean.DadosNegativacaoPorImovelHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usu�rio a tela que receber� os
 * par�metros para realiza��o da inser��o de um Comando de Negativa��o 
 * (Por Matr�cula de Im�veis)
 * 
 * @author Ana Maria
 * @date 06/11/2007
 */
public class AdicionarMatriculaImovelNegativacaoAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("adicionarComandoNegativacaPorMatricula");
		
		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		InserirComandoNegativacaoActionForm inserirComandoNegativacaoActionForm = (InserirComandoNegativacaoActionForm) actionForm;
		
		if(inserirComandoNegativacaoActionForm.getMatriculaImovelDebitos() == null 
				|| inserirComandoNegativacaoActionForm.getMatriculaImovelDebitos().equals("")){
			throw new ActionServletException(
						"atencao.pesquisar_imovel");
		}
		
		Collection<DadosNegativacaoPorImovelHelper> colecaoDadosNegativacaoPorImovelHelper = null;
				
		if(sessao.getAttribute("colecaoDadosNegativacaoPorImovelHelper") != null){
			colecaoDadosNegativacaoPorImovelHelper = (Collection)sessao.getAttribute("colecaoDadosNegativacaoPorImovelHelper");
		}else{
			colecaoDadosNegativacaoPorImovelHelper = new ArrayList<DadosNegativacaoPorImovelHelper>();
		}
		
		//Cliente Selecionado
		Integer idClienteImovel = new Integer(inserirComandoNegativacaoActionForm.getClienteSelecionado());
		ClienteImovel clienteImovel = null;
		
		/*
		 * Felipe Santos - 06/12/2011
		 * 
		 * Verifica se ClienteImovel tem rela��o RESPONS�VEL PELO PARCELAMENTO
		 */
		if(idClienteImovel.equals(new Integer(0))){
			//cliente respons�vel pelo parcelamento
			clienteImovel = (ClienteImovel)sessao.getAttribute("clienteParcelamento");
		}else{
			FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();		
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.ID,idClienteImovel));
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
			Collection colecaoClienteImovel = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
			clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(colecaoClienteImovel);
		}
		//RM4097 - adicionado por Vivianne Sousa - 29/12/2010 - Ana Cristina
		//[FS0031] � Verificar exist�ncia de conta em nome do cliente
		if(inserirComandoNegativacaoActionForm.getIndicadorContaNomeCliente().equals("" + ConstantesSistema.SIM)){
			
			Collection colecaoContasIds = null;
			if (sessao.getAttribute("colecaoContasIds")!= null &&
					!((Collection) sessao.getAttribute("colecaoContasIds")).isEmpty()){
				
				colecaoContasIds = (Collection)sessao.getAttribute("colecaoContasIds");
				
				boolean existeClienteConta = fachada.verificarSeExisteClienteConta(
						clienteImovel.getCliente().getId(),colecaoContasIds);
				
				if(!existeClienteConta){
					//Caso n�o exista nenhuma conta em nome do cliente selecionado 
					//para negativa��o, exibir a mensagem �N�o h� nenhuma conta que comp�e 
					//o d�bito do im�vel <<Matr�cula do Im�vel>> em nome do cliente 
					//<<Nome do Cliente Selecionado para Negativa��o>>. N�o � poss�vel negativ�-lo.�
					
					throw new ActionServletException("atencao.nao_ha_conta_cliente",
							clienteImovel.getImovel().getId().toString(), 
							clienteImovel.getCliente().getNome());
				}
				
			}
			
		}
		
		
		
		//Atualizar Im�vel j� adicionadao(link)
		if(httpServletRequest.getParameter("atualizar") != null){
			if(inserirComandoNegativacaoActionForm.getIdImovelDebitos() != null){
				Integer idImovelAtualizar = new Integer(inserirComandoNegativacaoActionForm.getIdImovelDebitos());			
				if(colecaoDadosNegativacaoPorImovelHelper != null && !colecaoDadosNegativacaoPorImovelHelper.isEmpty()){
					Iterator dadosNegativacaoPorImovelHelperIterator = colecaoDadosNegativacaoPorImovelHelper.iterator();
					while (dadosNegativacaoPorImovelHelperIterator.hasNext()) {
						DadosNegativacaoPorImovelHelper helper = (DadosNegativacaoPorImovelHelper) dadosNegativacaoPorImovelHelperIterator.next();
						if(helper.getIdImovel().equals(idImovelAtualizar)){
							helper.setIdCliente(clienteImovel.getCliente().getId());
							helper.setIdClienteRelacaoTipo(clienteImovel.getClienteRelacaoTipo().getId());
							if(clienteImovel.getCliente().getCpf() != null){
								helper.setCpfCliente(clienteImovel.getCliente().getCpfFormatado());
							}
							if(clienteImovel.getCliente().getCnpj() != null){
								helper.setCpfCliente(clienteImovel.getCliente().getCnpjFormatado());
							}							
						}
					}
					//sessao.setAttribute("colecaoDadosNegativacaoPorImovelHelper", colecaoDadosNegativacaoPorImovelHelper);
				}
			}
		}else{ //Inserir im�vel	selecionado		
			List colecaoConta = (List)sessao.getAttribute("colecaoContaValores");
			List colecaoGuias = (List)sessao.getAttribute("colecaoGuiaPagamentoValores");
			Integer qtdItensDebitoImovel = colecaoConta.size() + colecaoGuias.size();
			String totalDebitosImovel = (String)sessao.getAttribute("valorTotalSemAcrescimo");				
		
			//Dados do Cliente Selecionado e dos d�bitos do Im�vel
			DadosNegativacaoPorImovelHelper helper = new DadosNegativacaoPorImovelHelper();
			helper.setIdImovel(new Integer(inserirComandoNegativacaoActionForm.getIdImovelDebitos()));
			helper.setIdCliente(clienteImovel.getCliente().getId());
			helper.setIdClienteRelacaoTipo(clienteImovel.getClienteRelacaoTipo().getId());
			if(clienteImovel.getCliente().getCpf() != null){
				helper.setCpfCliente(clienteImovel.getCliente().getCpfFormatado());
			}
			if(clienteImovel.getCliente().getCnpj() != null){
				helper.setCpfCliente(clienteImovel.getCliente().getCnpjFormatado());
			}
			helper.setColecaoConta(colecaoConta);
			helper.setColecaoGuias(colecaoGuias);
			helper.setQtdItensDebitoImovel(qtdItensDebitoImovel);
			helper.setTotalDebitosImovel(Util.formatarMoedaRealparaBigDecimal(totalDebitosImovel));
			
			//[FS0011] Verificar matr�cula do im�vel j� existe na lista
			if(colecaoDadosNegativacaoPorImovelHelper.contains(helper)){
				throw new ActionServletException(
						"atencao.imovel_ja_existe_lista");
			}
			colecaoDadosNegativacaoPorImovelHelper.add(helper);		
		}
		
		sessao.setAttribute("colecaoDadosNegativacaoPorImovelHelper", colecaoDadosNegativacaoPorImovelHelper);	
		
    	//Realizar um reload na tela
    	httpServletRequest.setAttribute("reloadPage", "OK");
    	
		return retorno;

	}
}
