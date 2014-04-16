package gcom.gui.faturamento;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.conta.FiltroConta;
import gcom.faturamento.conta.FiltroContaHistorico;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
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
 * action responsável pela exibição da página de consultar histórico de faturamento
 * 
 * @author  pedro alexandre
 * @created 04 de Janeiro de 2006
 */
public class ExibirConsultarHistoricoFaturamentoAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {
    	
    	//cria a variável de retorno e seta o mapeamento para a tela de consultar histórico do faturamento
        ActionForward retorno = actionMapping.findForward("exibirConsultarHistoricoFaturamento");

        //cria uma instância da fachada
        Fachada fachada = Fachada.getInstancia();
                
        //cria uma instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //recupera o form de consultar histórico do faturamento
        ConsultarHistoricoFaturamentoActionForm consultarHistoricoFaturamentoActionForm = (ConsultarHistoricoFaturamentoActionForm) actionForm;
        
        //recupera a flag de limpar o form  
        String limparForm = httpServletRequest.getParameter("limparForm");
                        
        //se a flag não estiver vazia
        if (limparForm != null && !limparForm.equalsIgnoreCase("")){
        	//remove a coleção de conta e imóvel da sessão  
        	sessao.removeAttribute("colecaoContaImovel");
        }
        
        Collection colecaoContaHistoricoImovel = new ArrayList();
        Collection colecaoContaImovel = new ArrayList();
        Collection colecaoDebitoACobrarHistoricoImovel = new ArrayList();
        Collection colecaoDebitoACobrarImovel = new ArrayList();
        Collection colecaoCreditoARealizarHistoricoImovel = new ArrayList();
        Collection colecaoCreditoARealizarImovel = new ArrayList();
        Collection colecaoGuiaPagamentoHistoricoImovel = new ArrayList();
        Collection colecaoGuiaPagamentoImovel = new ArrayList();
        /*Pesquisar o imóvel a partir da matrícula do imóvel informada na página
        ====================================================================== */
        //recupera o código do imóvel
        String idImovel = consultarHistoricoFaturamentoActionForm.getIdImovel();
        
        //recupera a flag de recarregar a página
        String reloadPage = httpServletRequest.getParameter("reloadPage");
        
        //verifica se o código imóvel não é nulo
        if (idImovel != null && !idImovel.equalsIgnoreCase("") &&
           (reloadPage == null || reloadPage.equalsIgnoreCase(""))){
        	
        	//recupera o objeto imóvel da coleção
        	Imovel objetoImovel = fachada.pesquisarImovelDigitado(new Integer(idImovel));        	
        	       	
           	//Caso o imóvel informado pelo usuário esteja cadastrado no sistema
           	//Seta os dados o imóvel no form
           	//Caso contrário seta as informações o imóvel para vazio 
           	//e indica ao usuário que o imóvel informado não existe 
            if (objetoImovel != null ) {
              consultarHistoricoFaturamentoActionForm.setIdImovel("" + objetoImovel.getId());
              consultarHistoricoFaturamentoActionForm.setDescricaoImovel("" + objetoImovel.getInscricaoFormatada());
              httpServletRequest.setAttribute("idImovelNaoEncontrado", "true");
              
              /*Pesquisar o cliente usuário do imóvel selecionado
          	  ====================================================================== */
          	  //cria o filtro de cliente imóvel
          	  FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
          	
          	  //Objetos que serão retornados pelo hibernate.
          	  //carrega o cliente do imóvel no filtro
          	  filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
          	
          	  //seta o código do imóvel no filtro
          	  filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel));
          	
          	  //seta o tipo de relação do cliente com o imóvel no filtro 
          	  filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO, ClienteRelacaoTipo.USUARIO));
          	
          	  //seta o fim da motivo da relação no filtro
          	  filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.FIM_RELACAO_MOTIVO));
          	
          	  //pesquisa a relação do cliente imóvel
          	  Collection colecaoClienteImovel = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
          	
          	  // Verifica existência do cliente usuário
          	  if (colecaoClienteImovel == null || colecaoClienteImovel.isEmpty()){
          		throw new ActionServletException(
                          "atencao.naocadastrado", null, "cliente do tipo usuário foi");
          	  }
          	
          	  //recupera o objeto ClienteImovel da coleção
          	  ClienteImovel objetoClienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(colecaoClienteImovel);
          	        	
          	  /*Pesquisar as contas do imóvel a partir da matrícula do imóvel informada na página
          	  ====================================================================== */
          	  //cria o filtro de conta
          	  FiltroConta filtroConta = new FiltroConta();
          	
          	  //seta a ordenação da pesquisa no filtro
          	  filtroConta.setCampoOrderBy(FiltroConta.REFERENCIA + " desc");

          	  //Elimina o limite de 50 registro para a pesquisa
          	  filtroConta.setConsultaSemLimites(true);
          	
          	  //Objetos que serão retornados pelo hibernate
          	  filtroConta.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");
          	
          	        	        	        	        	        	             
          	  //seta o código do imóvel no filtro
          	  filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.IMOVEL_ID, objetoImovel.getId()));
          	  
          	        	
          	  //pesquisa a coleção de contas do imóvel 
          	  colecaoContaImovel = fachada.pesquisar(filtroConta, Conta.class.getName());
          	  
          	  /*Fim da Pesquisa de contas do imóvel
          	   ===================================================================== */

          	  
          	  /*Pesquisar os históricos das contas histórico do imóvel a partir da matrícula do imóvel informada na página
          	  ====================================================================== */
          	  //cria o filtro de histórico da conta
          	  FiltroContaHistorico filtroContaHistorico = new FiltroContaHistorico();
          	
          	  //seta a ordenação da pesquisa no filtro
          	  filtroContaHistorico.setCampoOrderBy(FiltroContaHistorico.ANO_MES_REFERENCIA + " desc");

          	  //Elimina o limite de 50 registro para a pesquisa
          	  filtroContaHistorico.setConsultaSemLimites(true);
          	
          	  //Objetos que serão retornados pelo hibernate
          	  filtroContaHistorico.adicionarCaminhoParaCarregamentoEntidade("debitoCreditoSituacaoAtual");
          	
          	  //seta o código do imóvel no filtro
          	  filtroContaHistorico.adicionarParametro(new ParametroSimples(FiltroContaHistorico.IMOVEL_ID, objetoImovel.getId()));
          	        	
          	  //pesquisa a coleção de histórico das contas do imóvel 
          	  colecaoContaHistoricoImovel = fachada.pesquisar(filtroContaHistorico, ContaHistorico.class.getName());
          	  
          	  /*Fim da Pesquisa de histórico das contas do imóvel
          	   ===================================================================== */

          	  
          	  //Carregando as informações do imóvel no formulário de exibição.
          	  //seta a inscrição do imóvel no form
          	  consultarHistoricoFaturamentoActionForm.setInscricaoImovel(objetoImovel.getInscricaoFormatada());      	
          	  //seta o nome do cliente no form 
          	  consultarHistoricoFaturamentoActionForm.setNomeClienteUsuario(objetoClienteImovel.getCliente().getNome());        	
          	  //seta a descrição da situação de água no form
          	  consultarHistoricoFaturamentoActionForm.setSituacaoAguaImovel(objetoImovel.getLigacaoAguaSituacao().getDescricao());        	
          	  //seta a descrição da situação de água no form
          	  consultarHistoricoFaturamentoActionForm.setSituacaoEsgotoImovel(objetoImovel.getLigacaoEsgotoSituacao().getDescricao());
          	
          	  //coloca na sessão a coleção com as contas do imóvel selecionado
          	  sessao.setAttribute("colecaoContaImovel", colecaoContaImovel); 
          	  sessao.setAttribute("colecaoContaHistoricoImovel", colecaoContaHistoricoImovel); 
          	  
          	  
          	  
          	 /* Pesquisar os debitos a cobrar e os debitos a cobrar historico do imóvel 
          	  * a partir da matrícula do imóvel informada na página
          	  ====================================================================== */
          	  colecaoDebitoACobrarImovel = fachada.obterDebitoACobrarImovel(objetoImovel.getId());
          	  colecaoDebitoACobrarHistoricoImovel = fachada.obterDebitoACobrarHistoricoImovel(objetoImovel.getId());
          	  
          	 //coloca na sessão a coleção com os debitos do imóvel selecionado
//           sessao.setAttribute("colecaoDebitoACobrarImovel", colecaoDebitoACobrarImovel); 
//           sessao.setAttribute("colecaoDebitoACobrarHistoricoImovel", colecaoDebitoACobrarHistoricoImovel); 
          	  
          	 /*Fim da Pesquisa de debitos a cobrar e os debitos a cobrar historico do imóvel
         	   ===================================================================== */
          	  
          	  
          	/* Pesquisar os creditos a realizar e os creditos a realizar historico do imóvel 
           	  * a partir da matrícula do imóvel informada na página
           	  ====================================================================== */
           	  colecaoCreditoARealizarImovel = fachada.obterCreditoARealizarImovel(objetoImovel.getId());
           	  colecaoCreditoARealizarHistoricoImovel = fachada.obterCreditoARealizarHistoricoImovel(objetoImovel.getId());
           	  
           	 //coloca na sessão a coleção com os creditos do imóvel selecionado
//           sessao.setAttribute("colecaoCreditoARealizarImovel", colecaoCreditoARealizarImovel); 
//           sessao.setAttribute("colecaoCreditoARealizarHistoricoImovel", colecaoCreditoARealizarHistoricoImovel); 
           	  
           	 /*Fim da Pesquisa de creditos a realizar e os creditos a realizar historico do imóvel
          	   ===================================================================== */
           	  
           	  
           	/* Pesquisar os creditos a realizar e os creditos a realizar historico do imóvel 
        	  * a partir da matrícula do imóvel informada na página
        	  ====================================================================== */
        	  colecaoGuiaPagamentoImovel = fachada.obterGuiaPagamentoImovel(objetoImovel.getId());
        	  colecaoGuiaPagamentoHistoricoImovel = fachada.obterGuiaPagamentoHistoricoImovel(objetoImovel.getId());
        	  
        	 //coloca na sessão a coleção com os creditos do imóvel selecionado
//        	  sessao.setAttribute("colecaoGuiaPagamentoImovel", colecaoGuiaPagamentoImovel); 
//        	  sessao.setAttribute("colecaoGuiaPagamentoHistoricoImovel", colecaoGuiaPagamentoHistoricoImovel); 
       	  
        	 /*Fim da Pesquisa de creditos a realizar e os creditos a realizar historico do imóvel
       	       ===================================================================== */
                                                   
            } else {
              consultarHistoricoFaturamentoActionForm.setIdImovel("");
              httpServletRequest.setAttribute("idImovelNaoEncontrado", "exception");
              consultarHistoricoFaturamentoActionForm.setDescricaoImovel("Matrícula Inexistente");
            }
        	       	
        }else{
        	//remove da sessão a coleção das contas do imóvel
        	sessao.removeAttribute("colecaoContaImovel");
        	sessao.removeAttribute("colecaoContaHistoricoImovel");
        	
//			sessao.removeAttribute("colecaoDebitoACobrarImovel"); 
//			sessao.removeAttribute("colecaoDebitoACobrarHistoricoImovel"); 
//			sessao.removeAttribute("colecaoCreditoARealizarImovel"); 
//          sessao.removeAttribute("colecaoCreditoARealizarHistoricoImovel");
//          sessao.removeAttribute("colecaoGuiaPagamentoImovel"); 
//          sessao.removeAttribute("colecaoGuiaPagamentoHistoricoImovel");
        	
        }
        
        httpServletRequest.setAttribute("colecaoContaImovel", colecaoContaImovel); 
        httpServletRequest.setAttribute("colecaoContaHistoricoImovel", colecaoContaHistoricoImovel); 
        
        httpServletRequest.setAttribute("colecaoDebitoACobrarImovel", colecaoDebitoACobrarImovel); 
        httpServletRequest.setAttribute("colecaoDebitoACobrarHistoricoImovel", colecaoDebitoACobrarHistoricoImovel); 
        
        httpServletRequest.setAttribute("colecaoCreditoARealizarImovel", colecaoCreditoARealizarImovel); 
        httpServletRequest.setAttribute("colecaoCreditoARealizarHistoricoImovel", colecaoCreditoARealizarHistoricoImovel);
        
        httpServletRequest.setAttribute("colecaoGuiaPagamentoImovel", colecaoGuiaPagamentoImovel); 
        httpServletRequest.setAttribute("colecaoGuiaPagamentoHistoricoImovel", colecaoGuiaPagamentoHistoricoImovel);
        
        setarTamanhoColacoesRequest(colecaoContaHistoricoImovel, colecaoContaImovel,
                 colecaoDebitoACobrarHistoricoImovel, colecaoDebitoACobrarImovel,
                 colecaoCreditoARealizarHistoricoImovel, colecaoCreditoARealizarImovel,
                 colecaoGuiaPagamentoHistoricoImovel, colecaoGuiaPagamentoImovel, httpServletRequest);
        
        //retorna o mapeamento contido na variável retorno
        return retorno;
    }
    
    
    private void setarTamanhoColacoesRequest(
    		Collection colecaoContaHistoricoImovel,
            Collection colecaoContaImovel,
            Collection colecaoDebitoACobrarHistoricoImovel,
            Collection colecaoDebitoACobrarImovel,
            Collection colecaoCreditoARealizarHistoricoImovel,
            Collection colecaoCreditoARealizarImovel,
            Collection colecaoGuiaPagamentoHistoricoImovel,
            Collection colecaoGuiaPagamentoImovel,
            HttpServletRequest httpServletRequest) {

        Integer tamanhoColecaoContas = 0;
        Integer tamanhoColecaoDebitos = 0;
        Integer tamanhoColecaoCreditos = 0;
        Integer tamanhoColecaoGuias = 0;
        
        if(colecaoContaImovel != null){
        	tamanhoColecaoContas = colecaoContaImovel.size();
        }
        if(colecaoContaHistoricoImovel != null){
        	tamanhoColecaoContas = tamanhoColecaoContas + colecaoContaHistoricoImovel.size();
        }
        
        if(colecaoDebitoACobrarImovel != null){
        	tamanhoColecaoDebitos = colecaoDebitoACobrarImovel.size();
        }
        if(colecaoDebitoACobrarHistoricoImovel != null){
        	tamanhoColecaoDebitos = tamanhoColecaoDebitos + colecaoDebitoACobrarHistoricoImovel.size();
        }
        
        if(colecaoCreditoARealizarImovel != null){
        	tamanhoColecaoCreditos = colecaoCreditoARealizarImovel.size();
        }
        if(colecaoCreditoARealizarHistoricoImovel != null){
        	tamanhoColecaoCreditos = tamanhoColecaoCreditos + colecaoCreditoARealizarHistoricoImovel.size();
        }
        
        if(colecaoGuiaPagamentoImovel != null){
        	tamanhoColecaoGuias = colecaoGuiaPagamentoImovel.size();
        }
        if(colecaoGuiaPagamentoHistoricoImovel != null){
        	tamanhoColecaoGuias = tamanhoColecaoGuias + colecaoGuiaPagamentoHistoricoImovel.size();
        }
        
        httpServletRequest.setAttribute("tamanhoColecaoContas", tamanhoColecaoContas);
        httpServletRequest.setAttribute("tamanhoColecaoDebitos", tamanhoColecaoDebitos); 
        httpServletRequest.setAttribute("tamanhoColecaoCreditos", tamanhoColecaoCreditos); 
        httpServletRequest.setAttribute("tamanhoColecaoGuias", tamanhoColecaoGuias); 
		
		
	}
}


