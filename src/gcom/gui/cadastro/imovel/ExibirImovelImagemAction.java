package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelImagem;
import gcom.cadastro.imovel.ImovelImagem;
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

public class ExibirImovelImagemAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		Fachada fachada = Fachada.getInstancia();
		
		String idImovelImagem = httpServletRequest.getParameter("id");
		
		FiltroImovelImagem filtro = new FiltroImovelImagem();
		filtro.adicionarParametro(new ParametroSimples(FiltroImovelImagem.ID, idImovelImagem));
		
		Collection colecaoImovelImagem = fachada.pesquisar(filtro, ImovelImagem.class.getName());
		ImovelImagem imovelImagem = (ImovelImagem) colecaoImovelImagem.iterator().next();
		
		try {
			FileInputStream in = new FileInputStream(new File(imovelImagem.getCaminhoImagem()));

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
