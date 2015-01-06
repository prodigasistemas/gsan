package gcom.gui.cadastro.atualizacaocadastral;

import gcom.atualizacaocadastral.FiltroImagemRetorno;
import gcom.atualizacaocadastral.ImagemRetorno;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirImagemRetornoAtualizacaoCadastralAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		Fachada fachada = Fachada.getInstancia();
		
		String idImagemRetorno = httpServletRequest.getParameter("id");
		
		FiltroImagemRetorno filtro = new FiltroImagemRetorno();
		filtro.adicionarParametro(new ParametroSimples(FiltroImagemRetorno.ID, idImagemRetorno));
		
		Collection colecaoImagemRetorno = fachada.pesquisar(filtro, ImagemRetorno.class.getName());
		ImagemRetorno imagemRetorno = (ImagemRetorno) colecaoImagemRetorno.iterator().next();
		
		try {
			String caminhoJboss = System.getProperty("jboss.server.home.dir");
			FileInputStream in = new FileInputStream(new File(caminhoJboss + imagemRetorno.getPathImagem()));

			httpServletResponse.setContentType("image/jpeg");

			OutputStream out = httpServletResponse.getOutputStream();
			byte[] outputByte = new byte[1024];
			while (in.read(outputByte, 0, 1024) != -1) {
				out.write(outputByte, 0, 1024);
			}
			
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
