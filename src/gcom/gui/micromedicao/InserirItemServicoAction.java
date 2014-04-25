package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.FiltroItemServico;
import gcom.micromedicao.ItemServico;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirItemServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		//Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirItemServicoActionForm form = (InserirItemServicoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		String descricao = form.getDescricao();
		String descricaoAbreviada = form.getDescricaoAbreviada();
		String codigoConstanteCalculo = form.getCodigoConstanteCalculo();
		String codigoItem = form.getCodigoItem();


		if(descricao == null || descricao.equals("")){

			//Descrição não informada
			throw new ActionServletException("atencao.descricao_sistema_abastecimento_nao_informado");
		} else{
			//[FS0003] - Verificar descrição do item já informado.
			FiltroItemServico filtroItemServico = new FiltroItemServico();
			filtroItemServico.adicionarParametro(
					new ParametroSimples(FiltroItemServico.DESCRICAO, descricao));

			Collection colecaoItemServico = Fachada.getInstancia().pesquisar(
					filtroItemServico, ItemServico.class.getName());
			if ( colecaoItemServico != null  && !colecaoItemServico.isEmpty()) {
				throw new ActionServletException("atencao.descricao_existente",null,descricao);	        	
			}


		}
		if(descricaoAbreviada == null || descricaoAbreviada.equals("")){

			//Descrição Abreviada não informada
			throw new ActionServletException("atencao.descricao_abreviada_nao_informada");
			//[FS0004] - Verificar descrição abreviada do item já informado.
		} else {
			FiltroItemServico filtroItemServico = new FiltroItemServico();
			filtroItemServico.adicionarParametro(
					new ParametroSimples(FiltroItemServico.DESCRICAO_ABREVIADA, descricaoAbreviada));

			Collection colecaoItemServico = Fachada.getInstancia().pesquisar(
					filtroItemServico, ItemServico.class.getName());
			if ( colecaoItemServico != null && !colecaoItemServico.isEmpty() ) {
				throw new ActionServletException("atencao.descricao_abreviada_tipo_debito_ja_existente",null,descricaoAbreviada);	        	
			}
		} 

		//[FS0005] - Verificar constante de cálculo já informado
		if(codigoConstanteCalculo != null && !codigoConstanteCalculo.equals("")){
			FiltroItemServico filtroItemServico = new FiltroItemServico();
			filtroItemServico.adicionarParametro(
					new ParametroSimples(FiltroItemServico.CODIGO_CONSTANTE_CALCULO, new Integer (codigoConstanteCalculo)));

			Collection colecaoItemServico = Fachada.getInstancia().pesquisar(
					filtroItemServico, ItemServico.class.getName());
			if ( colecaoItemServico != null && !colecaoItemServico.isEmpty() ) {
				throw new ActionServletException("atencao.codigo_constante_calculo_existente",null,form.getCodigoConstanteCalculo());	        	
			}
		}  

		//[FS0006] - Verificar código do item já informado.
		if(codigoItem != null && !codigoItem.equals("")){
			FiltroItemServico filtroItemServico = new FiltroItemServico();
			filtroItemServico.adicionarParametro(
					new ParametroSimples(FiltroItemServico.CODIGO_ITEM, new Long (codigoItem)));

			Collection colecaoItemServico = Fachada.getInstancia().pesquisar(
					filtroItemServico, ItemServico.class.getName());
			if ( colecaoItemServico != null && !colecaoItemServico.isEmpty()) {
				throw new ActionServletException("atencao.codigo_existente",null,form.getCodigoItem());	        	
			}
		} 

		//Criar o objeto itemServico que será inserido na base
		ItemServico itemServico = new ItemServico();


		itemServico.setDescricao(descricao);
		itemServico.setDescricaoAbreviada(descricaoAbreviada);
		itemServico.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		if (codigoConstanteCalculo != null && !codigoConstanteCalculo.equals("")){
			itemServico.setCodigoConstanteCalculo(new Integer (codigoConstanteCalculo));
		}
		itemServico.setUltimaAlteracao(new Date());
		if (codigoItem != null && !codigoItem.equals("")){
			itemServico.setCodigoItem(new Long (codigoItem));
		}

		//------------ REGISTRAR TRANSAÇÃO ----------------
		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_INSERIR_ITEM_SERVICO, itemServico.getId(), itemServico.getId(),
				new UsuarioAcaoUsuarioHelper(usuario, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		registradorOperacao.registrarOperacao(itemServico);
		//------------ REGISTRAR TRANSAÇÃO ----------------

		Integer codigoItemServicoInserido = 
			(Integer) this.getFachada().inserir(itemServico);

		montarPaginaSucesso(httpServletRequest,
				"Item de Contrato - "  + itemServico.getDescricao().toUpperCase() 
				+ " inserido com sucesso.",
				"Inserir outro Item de Contrato",
				"exibirInserirItemServicoAction.do?menu=sim",
				"exibirAtualizarItemServicoAction.do?menu=sim&idRegistroAtualizacao=" + 
				codigoItemServicoInserido, "Atualizar Item de Contrato Inserido");



		//devolve o mapeamento de retorno
		return retorno;
	}

}
