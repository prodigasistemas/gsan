package gcom.gui.cobranca.spcserasa;

import gcom.batch.Processo;
import gcom.cobranca.Negativador;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.FiltroNegativador;
import gcom.spcserasa.bean.DadosNegativacaoPorImovelHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os
 * parâmetros para realização da inserção de um Comando de Negativação 
 * (Por Matrícula de Imóveis)
 * 
 * @author Ana Maria
 * @date 06/11/2007
 */
public class ExibirInserirComandoNegativacaoMatriculaImovelAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirInserirComandoNegativacaoMatriculaImovelAction");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		InserirComandoNegativacaoActionForm inserirComandoNegativacaoActionForm = (InserirComandoNegativacaoActionForm) actionForm;

	    boolean processoiniciado = fachada.isProcessoEmExecucao(Processo.GERAR_RESUMO_DIARIO_NEGATIVACAO);
	    
	    if(processoiniciado){
			throw new ActionServletException("atencao.negativacao_por_imovel_nao_negativar");		    	
	    }
		
		//Remover Imóvel
		Collection<DadosNegativacaoPorImovelHelper> colecaoDadosNegativacaoPorImovelHelper = null;
		if(httpServletRequest.getParameter("idImovelRemover") != null){
			Integer idImovelRemover = new Integer(httpServletRequest.getParameter("idImovelRemover"));
			colecaoDadosNegativacaoPorImovelHelper = (Collection)sessao.getAttribute("colecaoDadosNegativacaoPorImovelHelper");			
			if(colecaoDadosNegativacaoPorImovelHelper != null && !colecaoDadosNegativacaoPorImovelHelper.isEmpty()){
				DadosNegativacaoPorImovelHelper dadosNegativacaoPorImovelHelper = new DadosNegativacaoPorImovelHelper();
				dadosNegativacaoPorImovelHelper.setIdImovel(idImovelRemover);
				colecaoDadosNegativacaoPorImovelHelper.remove(dadosNegativacaoPorImovelHelper);		
				sessao.setAttribute("colecaoDadosNegativacaoPorImovelHelper", colecaoDadosNegativacaoPorImovelHelper);
			}
		}
			
        //Pesquisar o Negativador
    	if (inserirComandoNegativacaoActionForm.getIdNegativador() != null) {
    		
    		FiltroNegativador filtroNegativador = new FiltroNegativador();    		
    		filtroNegativador.adicionarParametro(new ParametroSimples(
    				FiltroNegativador.ID, inserirComandoNegativacaoActionForm.getIdNegativador()));
    		filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");
    		
    		Collection negativadores = fachada.pesquisar(filtroNegativador,
    						Negativador.class.getName());
    		
    		if (negativadores != null && !negativadores.isEmpty()) {    		
    			Negativador negativador = (Negativador) Util.retonarObjetoDeColecao(negativadores);
    			inserirComandoNegativacaoActionForm.setNomeNegativador(negativador.getCliente().getNome());		
    		}
    	}
    	
        String usuario = inserirComandoNegativacaoActionForm.getUsuario();
        
        //Pesquisa Usuario 
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
				httpServletRequest.setAttribute("corUsuario","exception");
				inserirComandoNegativacaoActionForm
        		.setUsuario(null);
				inserirComandoNegativacaoActionForm
            		.setNomeUsuario(ConstantesSistema.USUARIO_INEXISTENTE);
			}
        }


        if(inserirComandoNegativacaoActionForm.getIndicadorBaixaRenda() == null){
        	//CRC4496 - adicionado por Vivianne Sousa - analista:Adriana - 29/06/2010
        	//Imóvel com Baixa Renda - exibir com opção "NÃO" selecionada    		
        	inserirComandoNegativacaoActionForm.setIndicadorBaixaRenda(ConstantesSistema.CONFIRMADA);   
        }
        
        if(inserirComandoNegativacaoActionForm.getIndicadorContaNomeCliente() == null){
        	//Exigir ao Menos uma Conta em Nome do Cliente Negativado  - exibir com opção "Sim" selecionada    		
        	inserirComandoNegativacaoActionForm.setIndicadorContaNomeCliente(ConstantesSistema.CONFIRMADA);   
        }
        
        if(inserirComandoNegativacaoActionForm.getIndicadorImovelCategoriaPublico() == null){
        	//RM3388 - adicionado por Ivan Sergio - analista:Adriana - 26/01/2011
        	//Imóvel de Categoria Público - exibir com opção "NÃO" selecionada    		
        	inserirComandoNegativacaoActionForm.setIndicadorImovelCategoriaPublico(ConstantesSistema.NAO_CONFIRMADA);   
        }
        
		return retorno;

	}

}
