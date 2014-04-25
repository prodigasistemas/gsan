package gcom.gui.cadastro.sistemaparametro;


import gcom.cadastro.geografico.FiltroMunicipioFeriado;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.MunicipioFeriado;
import gcom.cadastro.sistemaparametro.FiltroNacionalFeriado;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.abreviada.FiltroTabelaAuxiliarAbreviada;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0535] Manter Feriado [SB0001]Atualizar Feriado
 *
 *
 * @author Kássia Albuquerque
 * @date 24/01/2007
 */

public class ExibirAtualizarFeriadoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("atualizarFeriado");
			
			AtualizarFeriadoActionForm atualizarFeriadoActionForm = (AtualizarFeriadoActionForm)actionForm;
			
			HttpSession sessao = httpServletRequest.getSession(false);
			
			Fachada fachada = Fachada.getInstancia();
			
			String idFeriado = null;
			String idMunicipio = atualizarFeriadoActionForm.getIdMunicipio();
			String tipoFeriado = null;
			
			if (httpServletRequest.getParameter("idFeriado") != null) {
				//tela do manter
				idFeriado = (String) httpServletRequest.getParameter("idFeriado");
				tipoFeriado = (String) httpServletRequest.getParameter("tipoFeriado");
				sessao.setAttribute("idFeriado", idFeriado);
				sessao.setAttribute("tipoFeriado", tipoFeriado);
				
			} else if (sessao.getAttribute("idFeriado") != null) {
				//tela do filtrar
				idFeriado = (String) sessao.getAttribute("idFeriado");
				tipoFeriado = (String) sessao.getAttribute("tipoFeriado");
				/*sessao.removeAttribute("tipoFeriado");
				sessao.removeAttribute("idFeriado");*/
				
			}else if(httpServletRequest.getParameter("idRegistroInseridoAtualizar") != null){
				//link na tela de sucesso do inserir Perfil Serviço
				idFeriado = (String)httpServletRequest.getParameter("idRegistroInseridoAtualizar");
				tipoFeriado = (String) httpServletRequest.getParameter("tipoFeriado");
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarFeriadoAction.do?menu=sim");
				sessao.setAttribute("idFeriado", idFeriado);
				
			}
			
			if (tipoFeriado.equals("2")) {
				
				//------Parte que trata do código quando o usuário tecla enter
				
				if ((idMunicipio != null && !idMunicipio.equals(""))&& (httpServletRequest.getParameter("pesquisa") != null &&
						httpServletRequest.getParameter("pesquisa").equals("S"))) {

					FiltroTabelaAuxiliarAbreviada filtroTabelaAuxiliarAbreviada = new FiltroTabelaAuxiliarAbreviada();

					filtroTabelaAuxiliarAbreviada.adicionarParametro(new ParametroSimples(FiltroTabelaAuxiliarAbreviada.ID, 
							atualizarFeriadoActionForm.getIdMunicipio()));

					Collection colecaoMunicipio = fachada.pesquisar(filtroTabelaAuxiliarAbreviada, Municipio.class.getName());

					if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {
						
						Municipio municipio = (Municipio) colecaoMunicipio.iterator().next();
						atualizarFeriadoActionForm.setDescricaoMunicipio(municipio.getNome());
						
					} else {
						httpServletRequest.setAttribute("municipioEncontrado", "exception");
						atualizarFeriadoActionForm.setIdMunicipio("");
						atualizarFeriadoActionForm.setDescricaoMunicipio("MUNICIPIO INEXISTENTE");
					}

				}else{
					
					
					//------Inicio da parte que verifica se vem da página de
					// 		feriado_manter.jsp
					
					MunicipioFeriado municipioFeriado = null;
					
					if (idFeriado == null){
						
						if (httpServletRequest.getAttribute("idRegistroAtualizar") == null){
							municipioFeriado = (MunicipioFeriado) sessao.getAttribute("idRegistroAtualizar");
						}else{
							idFeriado = (String) httpServletRequest.getAttribute("idRegistroAtualizar").toString();
						}
					}
					
					if (municipioFeriado == null){
					
						if (idFeriado != null && !idFeriado.equals("")) {
			
							FiltroMunicipioFeriado filtroMunicipioFeriado = new FiltroMunicipioFeriado();
							
							filtroMunicipioFeriado.adicionarCaminhoParaCarregamentoEntidade("municipio");
			
							filtroMunicipioFeriado.adicionarParametro(new ParametroSimples(FiltroMunicipioFeriado.ID, idFeriado));
			
							Collection colecaoMunicipioFeriado = fachada.pesquisar(filtroMunicipioFeriado, MunicipioFeriado.class
									.getName());
			
							if (colecaoMunicipioFeriado != null && !colecaoMunicipioFeriado.isEmpty()) {
								
								municipioFeriado = (MunicipioFeriado) Util.retonarObjetoDeColecao(colecaoMunicipioFeriado);
								
							}
						}
					}
					
					//  ------  O feriado foi encontrado
					
					atualizarFeriadoActionForm.setIndicadorTipoFeriado(tipoFeriado);

					atualizarFeriadoActionForm.setCodigoFeriado(municipioFeriado.getId().toString());

					if(municipioFeriado.getMunicipio()!= null && !municipioFeriado.getMunicipio().equals("")){
						atualizarFeriadoActionForm.setIdMunicipio(municipioFeriado.getMunicipio().getId().toString());
						atualizarFeriadoActionForm.setDescricaoMunicipio(municipioFeriado.getMunicipio().getNome());
					}		
					atualizarFeriadoActionForm.setDataFeriado(Util.formatarData(municipioFeriado.getDataFeriado()));
					
					atualizarFeriadoActionForm.setDescricaoFeriado(municipioFeriado.getDescricaoFeriado());
					
					sessao.setAttribute("municipioFeriado", municipioFeriado);
					
					// ------ Fim da parte que verifica se vem da página de feriado_manter.jsp
					
				}
			}else{
				
				//------  O feriado foi encontrado
				
				NacionalFeriado nacionalFeriado = null;
				
				if (idFeriado == null){
					
					if (httpServletRequest.getAttribute("idRegistroAtualizar") == null){
						nacionalFeriado = (NacionalFeriado) sessao.getAttribute("idRegistroAtualizar");
					}else{
						idFeriado = (String) httpServletRequest.getAttribute("idRegistroAtualizar").toString();
					}
				}
				
				if (nacionalFeriado == null){
					
					if (idFeriado != null && !idFeriado.equals("")) {
		
						FiltroNacionalFeriado filtroNacionalFeriado = new FiltroNacionalFeriado();
						
						filtroNacionalFeriado.adicionarParametro(new ParametroSimples(FiltroNacionalFeriado.ID, idFeriado));
		
						Collection colecaoFeriado = fachada.pesquisar(filtroNacionalFeriado, NacionalFeriado.class
								.getName());
		
						if (colecaoFeriado != null && !colecaoFeriado.isEmpty()) {
							
							nacionalFeriado = (NacionalFeriado) Util.retonarObjetoDeColecao(colecaoFeriado);
							
						}
					}
				}
				
				atualizarFeriadoActionForm.setIndicadorTipoFeriado(tipoFeriado);
				
				atualizarFeriadoActionForm.setCodigoFeriado(nacionalFeriado.getId().toString());
	
				atualizarFeriadoActionForm.setDataFeriado(Util.formatarData(nacionalFeriado.getData()));
				
				atualizarFeriadoActionForm.setDescricaoFeriado(nacionalFeriado.getDescricao());
				
				sessao.setAttribute("nacionalFeriado", nacionalFeriado);
				
				// ------ Fim da parte que verifica se vem da página de feriado_manter.jsp
			}
			
			return retorno;
	}

}
					
		


