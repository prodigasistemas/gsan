package gcom.gui.atendimentopublico.ordemservico;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import gcom.atendimentopublico.ordemservico.OrdemServicoFoto;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.util.ImagemUtil;

public class Imagem {

	public static byte[] getImagem(Integer idFoto) {
		byte[] bytes = null;
		try {
			List<OrdemServicoFoto> fotos = (ArrayList<OrdemServicoFoto>) Fachada.getInstancia()
					.pesquisarFotosOrdemServico(idFoto, false);

			if (fotos != null && !fotos.isEmpty()) {
				InputStream input = ImagemUtil.carregarImagemDoServidorDeArquivos(fotos.get(0).getCaminhoFoto());
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				int nRead;
				byte[] data = new byte[1024];
				while ((nRead = input.read(data, 0, data.length)) != -1) {
					buffer.write(data, 0, nRead);
				}

				buffer.flush();
				bytes = buffer.toByteArray();
			} else {
				throw new ActionServletException("atencao.ordem.servico.nao.possui.foto");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bytes;
	}
}
