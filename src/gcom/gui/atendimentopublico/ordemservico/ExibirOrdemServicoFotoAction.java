package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroOrdemServicoFoto;
import gcom.atendimentopublico.ordemservico.OrdemServicoFoto;
import gcom.gui.GcomAction;
import gcom.util.ImagemUtil;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ExibirOrdemServicoFotoAction extends GcomAction {

		public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

			OrdemServicoFoto osfoto = obterFotoOrdemServico(request.getParameter("id"));

			try {
				InputStream input = ImagemUtil.carregarImagemDoServidorDeArquivos(osfoto.getCaminhoFoto() + "/" + osfoto.getNomeFoto());

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

		private OrdemServicoFoto obterFotoOrdemServico(String idOSFoto) {
			Filtro filtro = new FiltroOrdemServicoFoto();
			filtro.adicionarParametro(new ParametroSimples(FiltroOrdemServicoFoto.ID, idOSFoto));

			Collection<?> colecaoOsFoto = getFachada().pesquisar(filtro, OrdemServicoFoto.class.getName());

			return (OrdemServicoFoto) colecaoOsFoto.iterator().next();
		}
	}