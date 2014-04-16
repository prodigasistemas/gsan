package gcom.gui.cadastro;

import gcom.cadastro.imovel.FiltroImovelEloAnormalidade;
import gcom.cadastro.imovel.ImovelEloAnormalidade;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Exibe o caso de uso [UC0491] Informar Ocorrência de Cadastro e/ou Anormalidade de Elo
 * 
 * @author Tiago Moreno
 * @date 20/11/2006
 */
public class ExibirFotoAnormalidadeEloAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		
		Fachada fachada = Fachada.getInstancia();
				
		//Mudar Isso Quando Tiver esquema de segurança
		//HttpSession sessao = httpServletRequest.getSession(false);
		
		String imovelEloAnormalidade = httpServletRequest.getParameter("id");
		OutputStream out = null;
		
			FiltroImovelEloAnormalidade filtroImovelEloAnormalidade = new FiltroImovelEloAnormalidade();
			filtroImovelEloAnormalidade.adicionarParametro(
					new ParametroSimples(FiltroImovelEloAnormalidade.ID, imovelEloAnormalidade));
			Collection cImovelEloAnormalidade = fachada.pesquisar(
					filtroImovelEloAnormalidade, ImovelEloAnormalidade.class.getName());
			ImovelEloAnormalidade imovelEA = (ImovelEloAnormalidade) cImovelEloAnormalidade.iterator().next();
			
			
		String mimeType = "image/jpeg";
		
		try {
			httpServletResponse.setContentType(mimeType);
			out = httpServletResponse.getOutputStream();
			
			out.write(imovelEA.getFotoAnormalidade());
			out.flush();
			out.close();
		} catch (IOException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

		}
		
		return null;
	}
}
