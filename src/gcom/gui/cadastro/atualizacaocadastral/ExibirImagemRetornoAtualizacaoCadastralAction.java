package gcom.gui.cadastro.atualizacaocadastral;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atualizacaocadastral.FiltroImagemRetorno;
import gcom.atualizacaocadastral.ImagemRetorno;
import gcom.gui.GcomAction;
import gcom.util.ImagemUtil;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

public class ExibirImagemRetornoAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

		ImagemRetorno imagemRetorno = obterImagemRetorno(request.getParameter("id"));

		try {
			InputStream input = ImagemUtil.carregarImagemDoServidorDeArquivos(String.format("/recadastramento%s", imagemRetorno.getPathImagem()));
			
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

	private ImagemRetorno obterImagemRetorno(String idImagemRetorno) {
		Filtro filtro = new FiltroImagemRetorno();
		filtro.adicionarParametro(new ParametroSimples(FiltroImagemRetorno.ID, idImagemRetorno));

		Collection<?> colecao = getFachada().pesquisar(filtro, ImagemRetorno.class.getName());

		return (ImagemRetorno) colecao.iterator().next();
	}
}
