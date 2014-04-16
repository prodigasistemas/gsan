package gcom.gui.micromedicao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.AtualizarItemServicoActionForm;
import gcom.micromedicao.FiltroItemServico;
import gcom.micromedicao.ItemServico;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * Atualizar Item de Servico
 *
 * @author Rodrigo de Abreu Cabral	
 * @date 04/08/2010
 */


public class AtualizarItemServicoAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		AtualizarItemServicoActionForm form = (AtualizarItemServicoActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
				
		String id = form.getId();
		String descricao = form.getDescricao();
        String descricaoAbreviada = form.getDescricaoAbreviada();
        String codigoItem = form.getCodigoItem();
		
		if(descricao == null || descricao.equals("")){
        	
        	//Descrição não informada
        	throw new ActionServletException("atencao.descricao_sistema_abastecimento_nao_informado");
        } else{
        	//[FS0005] - Verificar descrição do item já informado.
        	FiltroItemServico filtroItemServico = new FiltroItemServico();
        	filtroItemServico.adicionarParametro(
        			new ParametroSimples(FiltroItemServico.DESCRICAO, descricao));
        	
        	Collection colecaoItemServico = Fachada.getInstancia().pesquisar(
        			filtroItemServico, ItemServico.class.getName());
        	
        	
        	if ( colecaoItemServico != null  && !colecaoItemServico.isEmpty()) {

        		ItemServico itemServico = (ItemServico) Util.retonarObjetoDeColecao(colecaoItemServico);
        		
        		if (!itemServico.getId().toString().equals(id) ) {
        		
        			throw new ActionServletException("atencao.descricao_existente",null,descricao);
        		}
        	}
      
        	
        }
		if(descricaoAbreviada == null || descricaoAbreviada.equals("")){
        	
        	//Descrição Abreviada não informada
        	throw new ActionServletException("atencao.descricao_abreviada_nao_informada");
        //[FS0006] - Verificar descrição abreviada do item já informado.
		} else if (descricaoAbreviada != null && !descricaoAbreviada.equals("")){
        	FiltroItemServico filtroItemServico = new FiltroItemServico();
        	filtroItemServico.adicionarParametro(
        			new ParametroSimples(FiltroItemServico.DESCRICAO_ABREVIADA, descricaoAbreviada));
        	
        	Collection colecaoItemServico = Fachada.getInstancia().pesquisar(
        			filtroItemServico, ItemServico.class.getName());
        	if ( colecaoItemServico != null && !colecaoItemServico.isEmpty() ) {
        		
        		ItemServico itemServico = (ItemServico) Util.retonarObjetoDeColecao(colecaoItemServico);
        		
        		if (!itemServico.getId().toString().equals(id) ) {
        			throw new ActionServletException("atencao.descricao_abreviada_tipo_debito_ja_existente",null,descricaoAbreviada);	        	
        		}
        	}
        } 
                 
        //[FS0007] - Verificar código do item já informado.
        if(codigoItem != null && !codigoItem.equals("")){
        	FiltroItemServico filtroItemServico = new FiltroItemServico();
        	filtroItemServico.adicionarParametro(
        			new ParametroSimples(FiltroItemServico.CODIGO_ITEM, codigoItem));
        	
        	Collection colecaoItemServico = Fachada.getInstancia().pesquisar(
        			filtroItemServico, ItemServico.class.getName());
        	if ( colecaoItemServico != null && !colecaoItemServico.isEmpty()) {
        		
        		ItemServico itemServico = (ItemServico) Util.retonarObjetoDeColecao(colecaoItemServico);
        		
        		if (!itemServico.getId().toString().equals(id) ) {
        			throw new ActionServletException("atencao.codigo_existente",null,form.getCodigoItem());	        	
        		}
        	}
        } 
		
		ItemServico itemServico = (ItemServico) sessao.getAttribute("atualizarItemServico");
		
		//Atualiza a entidade com os valores do formulário
		itemServico.setDescricao(form.getDescricao());
		itemServico.setDescricaoAbreviada(form.getDescricaoAbreviada());
		itemServico.setIndicadorUso(new Short(form.getIndicadorUso()));	
		
		if (form.getCodigoConstanteCalculo() != null && !form.getCodigoConstanteCalculo().equals("")){
		itemServico.setCodigoConstanteCalculo(new Integer(form.getCodigoConstanteCalculo()));
		}
		
		if (codigoItem != null && !codigoItem.equals("")){
			itemServico.setCodigoItem(new Long(codigoItem));
		}
		
		itemServico.setUltimaAlteracao(new Date());
		
		//------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
			Operacao.OPERACAO_MANTER_ITEM_SERVICO,
			itemServico.getId(),itemServico.getId(),
			new UsuarioAcaoUsuarioHelper(usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));
			
		registradorOperacao.registrarOperacao(itemServico);
		//------------ REGISTRAR TRANSAÇÃO ----------------
    	
		//atualiza na base de dados de Item de Serviço
		 fachada.atualizar(itemServico);
		
		//Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Item de Contrato " + itemServico.getDescricao() + 
				" atualizado com sucesso.", 
				"Realizar outra Manutenção de Item de Contrato",
				"exibirFiltrarItemServicoAction.do?menu=sim");
		
		return retorno;
	}
	
}	      
    



