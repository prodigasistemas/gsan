package gcom.gui.cadastro;

import gcom.cadastro.imovel.FiltroImovelCadastroOcorrencia;
import gcom.cadastro.imovel.ImovelCadastroOcorrencia;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Exibe o caso de uso [UC0491] Informar Ocorrência de Cadastro e/ou Anormalidade de Elo
 * 
 * @author Tiago Moreno
 * @date 20/11/2006
 */
public class ExibirFotoOcorrenciaCadastroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {


		Fachada fachada = Fachada.getInstancia();
				
		String imovelCadastroOcorrencia = httpServletRequest.getParameter("id");
		OutputStream out = null;
		
		FiltroImovelCadastroOcorrencia filtroImovelCadastroOcorrencia = new FiltroImovelCadastroOcorrencia();
		filtroImovelCadastroOcorrencia.adicionarParametro(
				new ParametroSimples(FiltroImovelCadastroOcorrencia.ID, imovelCadastroOcorrencia));
		Collection cImovelCadastroOcorrencia = fachada.pesquisar(
				filtroImovelCadastroOcorrencia, ImovelCadastroOcorrencia.class.getName());
		ImovelCadastroOcorrencia imovelCO = (ImovelCadastroOcorrencia) cImovelCadastroOcorrencia.iterator().next();
		
		String mimeType = "image/jpeg";
		
		try {
			httpServletResponse.setContentType(mimeType);
			out = httpServletResponse.getOutputStream();
			
			out.write(imovelCO.getFotoOcorrencia());
			out.flush();
			out.close();
		} catch (IOException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

		}
		
		return null;

	}

}
