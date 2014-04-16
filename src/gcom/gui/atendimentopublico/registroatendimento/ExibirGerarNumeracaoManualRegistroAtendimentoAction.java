package gcom.gui.atendimentopublico.registroatendimento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os
 * parâmetros para geração da relação numérica
 * 
 * @author Raphael Rossiter
 * @date 06/11/2006
 */
public class ExibirGerarNumeracaoManualRegistroAtendimentoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("gerarNumeracaoManualRegistroAtendimento");

		GerarNumeracaoManualRegistroAtendimentoActionForm form = 
		(GerarNumeracaoManualRegistroAtendimentoActionForm) actionForm;

		//HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		Integer digitoModulo11 = 
		Util.obterDigitoVerificadorModulo11(Long.parseLong(sistemaParametro.getUltimoRAManual().toString()));
		
		String sequencialString = sistemaParametro.getUltimoRAManual().toString();
		int complementoZeros = 9 - sequencialString.length();
		String sequencialStringFinal =  sequencialString;
		
		for (int y=0; y < complementoZeros; y++){
			sequencialStringFinal = "0" + sequencialStringFinal;
		}
		
		form.setUltimoValorGerado(sequencialStringFinal + "-" + digitoModulo11);
		
		//Carregamento Inicial
		String menu = httpServletRequest.getParameter("menu");
		String desfazer = httpServletRequest.getParameter("desfazer");
		
		if ((menu != null && !menu.equalsIgnoreCase("")) ||
			(desfazer != null && !desfazer.equalsIgnoreCase(""))){
			
			form.setQuantidade("");
			form.setIdUnidadeOrganizacional("");
			form.setDescricaoUnidadeOrganizacional("");
			
			httpServletRequest.setAttribute("nomeCampo", "quantidade");
		}
		
		
		//Pesquisar Unidade
		String pesquisarUnidade = httpServletRequest.getParameter("pesquisarUnidade");
		
		if (pesquisarUnidade != null && !pesquisarUnidade.equalsIgnoreCase("")){
			
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID,
			form.getIdUnidadeOrganizacional()));
			
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
			FiltroUnidadeOrganizacional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
			Collection colecaoUnidade = fachada.pesquisar(filtroUnidadeOrganizacional,
			UnidadeOrganizacional.class.getName());

			if (colecaoUnidade == null || colecaoUnidade.isEmpty()) {

				form.setIdUnidadeOrganizacional("");
				form.setDescricaoUnidadeOrganizacional("Unidade Organizacional inexistente");

				httpServletRequest.setAttribute("corUnidade", "exception");
				httpServletRequest.setAttribute("nomeCampo", "idUnidadeOrganizacional");

			} else {
				
				UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util
				.retonarObjetoDeColecao(colecaoUnidade);

				form.setIdUnidadeOrganizacional(unidadeOrganizacional.getId().toString());
				form.setDescricaoUnidadeOrganizacional(unidadeOrganizacional.getDescricao());

				httpServletRequest.setAttribute("nomeCampo", "gerar");
			}
		}

		
		
		return retorno;
	}
}
