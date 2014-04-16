package gcom.gui.cobranca;

import gcom.cobranca.FiltroResolucaoDiretoria;
import gcom.cobranca.ResolucaoDiretoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * Permite pesquisar resoluções de diretoria 
 * [UC0223] Pesquisar Resolução de Diretoria
 * 
 * @author Vivianne Sousa
 * @since 19/04/2006
 */
public class PesquisarResolucaoDiretoriaAction extends GcomAction {

	/**
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
				.findForward("pesquisarResolucaoDiretoriaAction");
		
		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		PesquisarResolucaoDiretoriaActionForm pesquisarResolucaoDiretoriaActionForm = (PesquisarResolucaoDiretoriaActionForm)actionForm;
		
		String numeroResolucaoDiretoria = pesquisarResolucaoDiretoriaActionForm.getNumeroResolucaoDiretoria();
		String dataInicioVigencia = pesquisarResolucaoDiretoriaActionForm.getDataInicioVigencia();
		String dataFimVigencia = pesquisarResolucaoDiretoriaActionForm.getDataFimVigencia();

		validacaoFinal(pesquisarResolucaoDiretoriaActionForm);
		
		FiltroResolucaoDiretoria filtroResolucaoDiretoria = new FiltroResolucaoDiretoria();

		filtroResolucaoDiretoria.setCampoOrderBy(FiltroResolucaoDiretoria.NUMERO);
		
		boolean peloMenosUmParametroInformado = false;
		
		if (numeroResolucaoDiretoria != null && (!numeroResolucaoDiretoria.equalsIgnoreCase(""))){
			peloMenosUmParametroInformado = true;		
			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(
					FiltroResolucaoDiretoria.NUMERO, numeroResolucaoDiretoria));
		}
		
		if (dataInicioVigencia != null && (!dataInicioVigencia.equalsIgnoreCase(""))){
			peloMenosUmParametroInformado = true;
			Date dataInicioVigenciaDate = Util.converteStringParaDate(dataInicioVigencia);
			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(
					FiltroResolucaoDiretoria.DATA_VIGENCIA_INICIO, dataInicioVigenciaDate));
		}
		
		if (dataFimVigencia != null && (!dataFimVigencia.equalsIgnoreCase(""))){
			peloMenosUmParametroInformado = true;
			Date dataFimVigenciaDate = Util.converteStringParaDate(dataFimVigencia);
			filtroResolucaoDiretoria.adicionarParametro(new ParametroSimples(
					FiltroResolucaoDiretoria.DATA_VIGENCIA_FIM, dataFimVigenciaDate));
		}
				
		// [FS0002] Verificar preenchimento dos campos
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		// Filtrando os dados...
		filtroResolucaoDiretoria.isConsultaSemLimites();
		Collection<ResolucaoDiretoria> collectionResolucaoDiretoria =  fachada.pesquisar(filtroResolucaoDiretoria,ResolucaoDiretoria.class.getName()); 
			
  	  	if (collectionResolucaoDiretoria == null || collectionResolucaoDiretoria.isEmpty()) {
  	  		// [FS0004] Nenhum registro encontrado
  	  		throw new ActionServletException(
  	  				"atencao.pesquisa.nenhumresultado", null, "resolução de diretoria");
  	  	} /*else if (collectionResolucaoDiretoria.size() > ConstantesSistema.NUMERO_MAXIMO_REGISTROS_PESQUISA) {
  	  		// [FS0003] Muitos registros encontrados
  	  		throw new ActionServletException("atencao.pesquisa.muitosregistros");
  	  	}*/ else {
  	  		sessao.setAttribute("collectionResolucaoDiretoria", collectionResolucaoDiretoria);
  	  	} 
		

		return retorno;

	}
	
	
	private void validacaoFinal(PesquisarResolucaoDiretoriaActionForm form) {
		Date DataInicioVigencia = Util.converteStringParaDate(form.getDataInicioVigencia());
		Date DataFimVigencia = Util.converteStringParaDate(form.getDataFimVigencia());
		
		if (DataInicioVigencia != null && DataFimVigencia != null) {
			if (!DataInicioVigencia.equals("") && !DataFimVigencia.equals("")) {

				if (DataInicioVigencia.after(DataFimVigencia) || Util.datasIguais(DataInicioVigencia,DataFimVigencia) ) {
					//O Término de Vigência deve ser maior que o Início da Vigência
					throw new ActionServletException(
							"atencao.termino_maior_inicio_vigencia");
				}

			}
		}
		
	}

}
