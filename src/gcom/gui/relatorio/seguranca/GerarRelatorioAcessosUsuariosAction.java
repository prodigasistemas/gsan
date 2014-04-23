package gcom.gui.relatorio.seguranca;

import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.seguranca.FiltrarRelatorioAcessosUsuariosHelper;
import gcom.relatorio.seguranca.RelatorioAcessosPorUsuarios;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1040] Gerar Relatório de Acessos por Usuário
 * 
 * @author Hugo Leonardo
 *
 * @date 12/07/2010
 */

public class GerarRelatorioAcessosUsuariosAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		   
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		   
		// Form
		GerarRelatorioAcessosUsuariosActionForm form = (GerarRelatorioAcessosUsuariosActionForm) actionForm;
		
		FiltrarRelatorioAcessosUsuariosHelper helper = new FiltrarRelatorioAcessosUsuariosHelper();
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		boolean peloMenosUmParametroInformado = false;
		
		// Unidade Organizacional
		if (form.getIdsUnidadeOrganizacional() != null && !form.getIdsUnidadeOrganizacional().equals("-1") ){
			//&& form.getIdsUnidadeOrganizacional().length > 0) {
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getIdsUnidadeOrganizacional();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}
			if(colecao.size() > 0){
				helper.setUnidadeOrganizacional(colecao);
				peloMenosUmParametroInformado = true;
			}
		}
		
		// Grupo Acesso
		if(form.getIdsGrupoAcesso() != null && !form.getIdsGrupoAcesso().equals("-1") ){
				//&& form.getIdsGrupoAcesso().length > 0){
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getIdsGrupoAcesso();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}
			
			if(colecao.size() > 0){
				helper.setGrupoAcesso(colecao);
				peloMenosUmParametroInformado = true;
			}
		}
		
		// Modulo
		if ( form.getModulo() != null && 
				!form.getModulo().equals("-1")) {
			
			helper.setModulo(form.getModulo());
			peloMenosUmParametroInformado = true;
		}
		
		// situação usuário
		if (form.getIdsSituacaoUsuario() != null && !form.getIdsSituacaoUsuario().equals("-1") ){
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getIdsSituacaoUsuario();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}
			if(colecao.size() > 0){
				helper.setSituacaoUsuario(colecao);
				peloMenosUmParametroInformado = true;
			}
		}
		
		// funcionalidade
		if ( form.getIdFuncionalidade() != null && 
				!form.getIdFuncionalidade().equals("")){
			
			helper.setFuncionalidade(form.getIdFuncionalidade());
			peloMenosUmParametroInformado = true;
		}
		
		// Operacao
		if ( form.getOperacao() != null && 
				!form.getOperacao().equals("-1")) {
			
			helper.setOperacao(form.getOperacao());
			peloMenosUmParametroInformado = true;
		}
		
		// Usuario Tipo
		if(form.getIdsUsuarioTipo() != null && !form.getIdsUsuarioTipo().equals("-1") ){
				//&& form.getIdsUsuarioTipo().length > 0){
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getIdsUsuarioTipo();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}
			if(colecao.size() > 0){
				helper.setUsuarioTipo(colecao);
				peloMenosUmParametroInformado = true;
			}
		}
	
		// Usuario
		if(form.getIdUsuario() != null && !form.getIdUsuario().equals("")){
			helper.setUsuario(form.getIdUsuario());
			peloMenosUmParametroInformado = true;
		}
		
		// Permissão Especial
		if (form.getIdsPermissaoEspecial() != null && !form.getIdsPermissaoEspecial().equals("-1") ){
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getIdsPermissaoEspecial();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}
			if(colecao.size() > 0){
				helper.setPermissaoEspecial(colecao);
				peloMenosUmParametroInformado = true;
			}
		}
		
		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		 
		TarefaRelatorio relatorio = new RelatorioAcessosPorUsuarios((Usuario)
				(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		relatorio.addParametro("filtrarRelatorioAcessosUsuariosHelper", helper);
		
		try {	
			
			retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
						httpServletResponse, actionMapping);
	
		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");
	
			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");
	
		} catch (RelatorioVazioException ex1) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "");
		}
			
			return retorno;
		}
	
}
