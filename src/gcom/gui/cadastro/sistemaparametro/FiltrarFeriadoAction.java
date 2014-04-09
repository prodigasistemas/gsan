package gcom.gui.cadastro.sistemaparametro;

import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.abreviada.FiltroTabelaAuxiliarAbreviada;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * [UC0536]FILTRAR FERIADO
 * 
 * @author Kássia Albuquerque
 * @date 19/01/2006
 */



public class FiltrarFeriadoAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("exibirManterFeriado");
			
			Fachada fachada = Fachada.getInstancia();
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			FiltrarFeriadoActionForm form = (FiltrarFeriadoActionForm) actionForm;
			
			
			// Recupera todos os campos da página para ser colocada no filtro
			// posteriormente
			
			String indicadorTipoFeriado = form.getIndicadorTipoFeriado();
			Integer idMunicipio = null;
			String descricaoMunicipio = form.getDescricaoMunicipio();
			Date dataFeriadoInicio = null;
			Date dataFeriadoFim = null;
			String descricaoFeriado = form.getDescricaoFeriado();
	
			
			// Indicador Atualizar
			String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
			
			if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
				
				sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
			} else {
				
				sessao.removeAttribute("indicadorAtualizar");
			}
			
			
			boolean peloMenosUmParametroInformado = false;
			
			if (indicadorTipoFeriado.equals("1") ) {
				
				// Indicador de Tipo do  feriado
				peloMenosUmParametroInformado = true;
				
								
				// Data do Feriado
				if ((form.getDataFeriadoInicio() != null && form.getDataFeriadoFim() != null)&&(!form.getDataFeriadoInicio().equalsIgnoreCase("")&& !form.getDataFeriadoFim().equalsIgnoreCase(""))) {
					
					peloMenosUmParametroInformado = true;
					dataFeriadoInicio =  Util.converteStringParaDate(form.getDataFeriadoInicio());
					dataFeriadoFim = Util.converteStringParaDate(form.getDataFeriadoFim());
				}
				
				//	Descrição do Feriado
				if (descricaoFeriado != null && !descricaoFeriado.equalsIgnoreCase("")) {
					
					peloMenosUmParametroInformado = true;
				}
				
				// Erro caso o usuário mandou filtrar sem nenhum parâmetro
				if (!peloMenosUmParametroInformado) {
					throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
				}
				
			}else if(indicadorTipoFeriado.equals("2")){
				
				 // 	Indicador de Tipo do  feriado
				peloMenosUmParametroInformado = true;
				
				// Municipio
				if (form.getIdMunicipio() != null	&& !form.getIdMunicipio().trim().equals("")) {

					peloMenosUmParametroInformado = true;
					idMunicipio = Integer.parseInt(form.getIdMunicipio());	

					if (descricaoMunicipio == null|| descricaoMunicipio.equals("")) {
						FiltroTabelaAuxiliarAbreviada filtroTabelaAuxiliarAbreviada = new FiltroTabelaAuxiliarAbreviada();
						
						filtroTabelaAuxiliarAbreviada.adicionarParametro(new ParametroSimples(FiltroTabelaAuxiliarAbreviada.ID,
								idMunicipio));

						Collection colecaoMunicipio = fachada.pesquisar(filtroTabelaAuxiliarAbreviada,
								Municipio.class.getName());

						if (colecaoMunicipio == null || colecaoMunicipio.isEmpty()) {
							throw new ActionServletException(
									"atencao.municipio_inexistente");
						}					
					}

				}
				
				
				// Data do Feriado
				if ((form.getDataFeriadoInicio() != null && form.getDataFeriadoFim() != null)&&(!form.getDataFeriadoInicio().equalsIgnoreCase("")&& !form.getDataFeriadoFim().equalsIgnoreCase(""))) {
					
					peloMenosUmParametroInformado = true;
					dataFeriadoInicio =  Util.converteStringParaDate(form.getDataFeriadoInicio());
					dataFeriadoFim = Util.converteStringParaDate(form.getDataFeriadoFim());					
				}
				
				//	Descrição do Feriado
				if (descricaoFeriado != null && !descricaoFeriado.equalsIgnoreCase("")) {
					
					peloMenosUmParametroInformado = true;
				}
				
				// Erro caso o usuário mandou filtrar sem nenhum parâmetro
				if (!peloMenosUmParametroInformado) {
					throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
				}
				
			}else{
				
				// Indicador de Tipo do  feriado
				peloMenosUmParametroInformado = true;
				
				// Municipio
				if (form.getIdMunicipio() != null	&& !form.getIdMunicipio().trim().equals("")) {

					peloMenosUmParametroInformado = true;
					idMunicipio = Integer.parseInt(form.getIdMunicipio());

					if (descricaoMunicipio == null|| descricaoMunicipio.equals("")) {
						FiltroTabelaAuxiliarAbreviada filtroTabelaAuxiliarAbreviada = new FiltroTabelaAuxiliarAbreviada();
						
						filtroTabelaAuxiliarAbreviada.adicionarParametro(new ParametroSimples(FiltroTabelaAuxiliarAbreviada.ID,
								idMunicipio));

						Collection colecaoMunicipio = fachada.pesquisar(filtroTabelaAuxiliarAbreviada,
								Municipio.class.getName());

						if (colecaoMunicipio == null || colecaoMunicipio.isEmpty()) {
							throw new ActionServletException(
									"atencao.municipio_inexistente");
						}						
					}
				}
				
				
				// Data do Feriado
				if ((form.getDataFeriadoInicio() != null && form.getDataFeriadoFim() != null)&&(!form.getDataFeriadoInicio().equalsIgnoreCase("")&& !form.getDataFeriadoFim().equalsIgnoreCase(""))) {
					
					peloMenosUmParametroInformado = true;
					dataFeriadoInicio =  Util.converteStringParaDate(form.getDataFeriadoInicio());
					dataFeriadoFim = Util.converteStringParaDate(form.getDataFeriadoFim());
				}
				
				//	Descrição do Feriado
				if (descricaoFeriado != null && !descricaoFeriado.equalsIgnoreCase("")) {
					
					peloMenosUmParametroInformado = true;
				}
				
				// Erro caso o usuário mandou filtrar sem nenhum parâmetro
				if (!peloMenosUmParametroInformado) {
					throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
				}
			}
			
			Integer totalRegistros = fachada.pesquisarFeriadoCount(
					Short.parseShort(indicadorTipoFeriado), descricaoFeriado, dataFeriadoInicio,
							dataFeriadoFim, idMunicipio);
			
			retorno = this.controlarPaginacao(httpServletRequest, retorno, totalRegistros);
			
			Collection colecaoFeriado = fachada.pesquisarFeriado(
					Short.parseShort(indicadorTipoFeriado), descricaoFeriado, dataFeriadoInicio, 
					dataFeriadoFim, idMunicipio,(Integer) httpServletRequest.getAttribute("numeroPaginasPesquisa"));

			if (colecaoFeriado != null && !colecaoFeriado.isEmpty()) {
				sessao.setAttribute("colecaoFeriado", colecaoFeriado);
			} else {
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			}
			
		return retorno;		
		
	}
}
	
