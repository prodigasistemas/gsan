package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Action de execução do filtro de pesquisa
 * 
 * @author Thiago Nascimento
 *
 */
public class PesquisarRotaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("PesquisarRotaAction");
		
		PesquisarRotaActionForm form = (PesquisarRotaActionForm)actionForm;
		
		//Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltroRota filtroRota = new FiltroRota();
		
		filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.EMPRESA);
		filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.FATURAMENTO_GRUPO);
		filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.SETOR_COMERCIAL);
		filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.LOCALIDADE);
		filtroRota.adicionarCaminhoParaCarregamentoEntidade(FiltroRota.LEITURA_TIPO);
		
		boolean peloMenosUmParametro = false;
		
		if(!form.getIdGrupoFaturamento().equals("-1")){
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.FATURAMENTO_GRUPO_ID,new Integer(form.getIdGrupoFaturamento())));
			peloMenosUmParametro = true;
		}
		
		if(form.getIdLocalidade()!=null && !form.getIdLocalidade().trim().equals("")){
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.LOCALIDADE_ID,new Integer(form.getIdLocalidade())));
			peloMenosUmParametro = true;
		}
		
		if(form.getCodigoSetorComercial()!=null && !form.getCodigoSetorComercial().trim().equals("")){
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.SETOR_COMERCIAL_CODIGO,new Integer(form.getCodigoSetorComercial())));
			peloMenosUmParametro = true;
		}
		
		if(form.getCodigoRota()!=null && !form.getCodigoRota().trim().equals("")){
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.CODIGO_ROTA,new Integer(form.getCodigoRota())));
			peloMenosUmParametro = true;
		}
		
		if(!form.getEmpresaLeituristica().equals("-1")){
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.EMPRESA_ID,new Integer(form.getEmpresaLeituristica())));
			peloMenosUmParametro = true;
		}
		
		if(form.getIndicadorUso()!=null && !form.getIndicadorUso().trim().equals("3")){
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.INDICADOR_USO,new Integer(form.getIndicadorUso())));
			peloMenosUmParametro = true;
		}
		
		if(form.getIndicadorRotaAlternativa()!= null){
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.INDICADOR_ROTA_ALTERNATIVA,new Integer(form.getIndicadorRotaAlternativa())));
			peloMenosUmParametro = true;
		}
		
		if (!peloMenosUmParametro) {
			throw new ActionServletException(
			"atencao.filtro.nenhum_parametro_informado");
		}
		
		Collection colecao = Fachada.getInstancia().pesquisar(filtroRota,Rota.class.getName());
		
		if(colecao!=null && !colecao.isEmpty()){
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroRota, Rota.class.getName());
			colecao = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			
			sessao.setAttribute("rotas",colecao);
		}else {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Rota");
		}
		
		return retorno;
	}
	
}
