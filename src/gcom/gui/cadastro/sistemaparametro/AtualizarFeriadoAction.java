package gcom.gui.cadastro.sistemaparametro;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.MunicipioFeriado;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
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
 * @author Kássia Albuquerque
 * @date 24/01/2007
 */


public class AtualizarFeriadoAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			ActionForward retorno = actionMapping.findForward("telaSucesso");
			
			AtualizarFeriadoActionForm atualizarFeriadoActionForm = (AtualizarFeriadoActionForm) actionForm;
			
			Fachada fachada = Fachada.getInstancia();
			
			HttpSession sessao = httpServletRequest.getSession(false);		
			
			Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
			
			
			/*if ((atualizarFeriadoActionForm.getIdMunicipio() != null && !atualizarFeriadoActionForm.getIdMunicipio().equals(""))) {

				FiltroTabelaAuxiliarAbreviada filtroTabelaAuxiliarAbreviada = new FiltroTabelaAuxiliarAbreviada();

				filtroTabelaAuxiliarAbreviada.adicionarParametro(new ParametroSimples(FiltroTabelaAuxiliarAbreviada.ID, atualizarFeriadoActionForm.getIdMunicipio()));

				Collection colecaoMunicipio = fachada.pesquisar(filtroTabelaAuxiliarAbreviada,Municipio.class.getName());

				if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {
					Municipio municipio = (Municipio) colecaoMunicipio.iterator().next();
					atualizarFeriadoActionForm.setDescricaoMunicipio(municipio.getNome());
				} else {
					httpServletRequest.setAttribute("municipioEncontrado", "exception");
					atualizarFeriadoActionForm.setIdMunicipio("");
					atualizarFeriadoActionForm.setDescricaoMunicipio("MUNICIPIO INEXISTENTE");
				}

			}*/
			
			MunicipioFeriado municipioFeriado = null;
			NacionalFeriado nacionalFeriado = null;
			String tipoFeriado = null;
						
			if (atualizarFeriadoActionForm.getIdMunicipio() != null && 
					!atualizarFeriadoActionForm.getIdMunicipio().trim().equals("")) {
				
				FiltroTabelaAuxiliarAbreviada filtroTabelaAuxiliarAbreviada = new FiltroTabelaAuxiliarAbreviada();

				filtroTabelaAuxiliarAbreviada.adicionarParametro(new ParametroSimples(FiltroTabelaAuxiliarAbreviada.ID, atualizarFeriadoActionForm.getIdMunicipio()));

				Collection colecaoMunicipio = fachada.pesquisar(filtroTabelaAuxiliarAbreviada,Municipio.class.getName());

				if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {
					Municipio municipio = (Municipio) colecaoMunicipio.iterator().next();
					atualizarFeriadoActionForm.setDescricaoMunicipio(municipio.getNome());
				} else {
					httpServletRequest.setAttribute("municipioEncontrado", "exception");
					atualizarFeriadoActionForm.setIdMunicipio("");
					atualizarFeriadoActionForm.setDescricaoMunicipio("MUNICIPIO INEXISTENTE");
				}

				municipioFeriado = (MunicipioFeriado) sessao.getAttribute("municipioFeriado");
				atualizarFeriadoActionForm.setFormValuesMunicipal( municipioFeriado);
				tipoFeriado= "Municipal";
				
			} else {
				
				nacionalFeriado = (NacionalFeriado) sessao.getAttribute("nacionalFeriado");
				atualizarFeriadoActionForm.setFormValuesNacional( nacionalFeriado);
				tipoFeriado= "Nacional";
				
			}
			
			//atualiza na base de dados feriado
			 fachada.atualizarFeriado(nacionalFeriado,municipioFeriado,usuarioLogado);
			
			//Monta a página de sucesso
			montarPaginaSucesso(httpServletRequest, "Feriado "+ tipoFeriado + " " +"de código "+ 
					atualizarFeriadoActionForm.getCodigoFeriado() +" atualizado com sucesso.", 
					"Realizar outra Manutenção de Feriado","exibirFiltrarFeriadoAction.do?menu=sim");
		
		
				
			return retorno;
	}
		
}
	
	      
    




