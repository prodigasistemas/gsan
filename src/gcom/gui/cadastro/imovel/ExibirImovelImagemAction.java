package gcom.gui.cadastro.imovel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.imovel.FiltroImovelImagem;
import gcom.cadastro.imovel.ImovelImagem;
import gcom.gui.GcomAction;
import gcom.util.ImagemUtil;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

public class ExibirImovelImagemAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

		ImovelImagem imovelImagem = obterImovelImagem(request.getParameter("id"));

		try {
			InputStream input = ImagemUtil.carregarImagemDoServidorDeArquivos(String.format("/cadastro%s", imovelImagem.getCaminhoImagem()));

			response.setContentType("image/jpeg");

			OutputStream output = response.getOutputStream();
			ImagemUtil.copiar(input, output, false);

			input.close();
			output.flush();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private ImovelImagem obterImovelImagem(String idImovelImagem) {
		Filtro filtro = new FiltroImovelImagem();
		filtro.adicionarParametro(new ParametroSimples(FiltroImovelImagem.ID, idImovelImagem));

		Collection<?> colecaoImovelImagem = getFachada().pesquisar(filtro, ImovelImagem.class.getName());

		return (ImovelImagem) colecaoImovelImagem.iterator().next();
	}
}