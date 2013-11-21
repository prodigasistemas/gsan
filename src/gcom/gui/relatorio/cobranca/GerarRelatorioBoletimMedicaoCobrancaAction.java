package gcom.gui.relatorio.cobranca;

import java.util.Collection;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cobranca.FiltrarRelatorioBoletimMedicaoCobrancaHelper;
import gcom.relatorio.cobranca.RelatorioBoletimMedicaoCobranca;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1152] Emissão Boletim Medição Cobrança
 * 
 * @author Mariana Victor
 *
 * @date 21/03/2011
 */
public class GerarRelatorioBoletimMedicaoCobrancaAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;

		Fachada fachada = Fachada.getInstancia();
		   
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		// Form
		GerarRelatorioBoletimMedicaoCobrancaForm form = (GerarRelatorioBoletimMedicaoCobrancaForm) actionForm;
		
		FiltrarRelatorioBoletimMedicaoCobrancaHelper helper = new FiltrarRelatorioBoletimMedicaoCobrancaHelper();
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		boolean peloMenosUmParametroInformado = false;
		
		// Ano Mes
		if ( form.getMesAnoReferencia() != null && 
				!form.getMesAnoReferencia().equals("")) {
			
			String anoMes = Util.formatarMesAnoParaAnoMesSemBarra(form.getMesAnoReferencia());
			helper.setMesAnoReferencia(anoMes);
			peloMenosUmParametroInformado = true;
		}
		
		// Grupo de Cobrança
		if(form.getGrupoCobranca() != null && 
				!form.getGrupoCobranca().equals("-1")){
			
			FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
			filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(FiltroCobrancaGrupo.ID, form.getGrupoCobranca()));
			
			Collection colecao = fachada.pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName());
			
			if (colecao != null && !colecao.isEmpty()) {
				CobrancaGrupo cobrancaGrupo = (CobrancaGrupo) Util.retonarObjetoDeColecao(colecao);
				
				helper.setNomeGrupoCobranca(cobrancaGrupo.getDescricao());
				helper.setGrupoCobranca(form.getGrupoCobranca());
				peloMenosUmParametroInformado = true;
			}
		}
		
		// Gerência Regional 
		if(form.getGerenciaRegional() != null && 
				!form.getGerenciaRegional().equals("-1")){
			
			helper.setGerenciaRegional(form.getGerenciaRegional());
		}
		
		// Unidade de Negócio
		if(form.getUnidadeNegocio() != null && 
				!form.getUnidadeNegocio().equals("-1")){
			
			helper.setUnidadeNegocio(form.getUnidadeNegocio());
		}
		
		// Localidade Inicial
		if(form.getIdLocalidadeInicial() != null && 
				!form.getIdLocalidadeInicial().equals("")){
			if (verificarExistenciaLocalidade(form.getIdLocalidadeInicial())) {
				helper.setLocalidadeInicial(form.getIdLocalidadeInicial());
			} else {
				throw new ActionServletException(
						"atencao.pesquisa.localidade_inicial_inexistente");
			}
		}
		
		// Localidade Final
		if(form.getIdLocalidadeFinal() != null && 
				!form.getIdLocalidadeFinal().equals("")){
			if (verificarExistenciaLocalidade(form.getIdLocalidadeFinal())) {
				helper.setLocalidadeFinal(form.getIdLocalidadeFinal());
			} else {
				throw new ActionServletException(
						"atencao.pesquisa.localidade_final_inexistente");
			}
		}
		
		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		TarefaRelatorio relatorio = null;
		
		try {
			
			relatorio = new RelatorioBoletimMedicaoCobranca((Usuario)
					(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			if (tipoRelatorio  == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}
			
			relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
			
			relatorio.addParametro("filtrarRelatorioBoletimMedicaoCobrancaHelper", helper);
			
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
	
	private boolean verificarExistenciaLocalidade(String idLocalidade) {
		if (idLocalidade != null && !idLocalidade.equals("")) {
			
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidade));
			Collection colecaoLocalidade = Fachada.getInstancia().pesquisar(filtroLocalidade, Localidade.class.getName());
			
			if(colecaoLocalidade.isEmpty()){
				return false;
			} else {
				return true;
			}
		}
		
		return false;
	}

}
