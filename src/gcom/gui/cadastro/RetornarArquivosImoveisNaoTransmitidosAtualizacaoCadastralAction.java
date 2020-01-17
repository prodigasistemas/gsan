package gcom.gui.cadastro;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.FiltroArquivoTextoAtualizacaoCadastral;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.tarefa.TarefaException;
import gcom.util.Util;
import gcom.util.ZipUtil;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

public class RetornarArquivosImoveisNaoTransmitidosAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ConsultarArquivoTextoAtualizacaoCadastralActionForm form = (ConsultarArquivoTextoAtualizacaoCadastralActionForm) actionForm;

		Integer idEmpresa = null;
		if (!form.getIdEmpresa().equals("") && !form.getIdEmpresa().equals("0")) {
			idEmpresa = Integer.valueOf(form.getIdEmpresa());
		}
		
		List<Integer> ids = getIdsArquivos(form);
		
		verificarArquivosEmCampo(ids);
		
		List<ArquivoTextoAtualizacaoCadastral> listaArquivoTexto = getFachada().regerarArquivosAtualizacaoCadastral(
				ids,
				ArquivoTextoAtualizacaoCadastral.TIPO_ARQUIVO_TRANSMISSAO,
				null,
				idEmpresa);

		if (listaArquivoTexto != null && !listaArquivoTexto.isEmpty()) {
			try {
				String nomeArquivoZIP = getNomeArquivo(form.getIdLocalidade());
				File arquivoZIP = new File(nomeArquivoZIP);
				ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(arquivoZIP));

				for (ArquivoTextoAtualizacaoCadastral arquivoTexto : listaArquivoTexto) {
					ZipUtil.adicionarEmZip(zip, arquivoTexto.getDescricaoArquivo(), arquivoTexto.getArquivoTexto());
				}

				ZipUtil.download(response, zip, nomeArquivoZIP, arquivoZIP);
				zip.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new TarefaException("Erro ao gerar o arquivo zip", e);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new TarefaException("Erro ao gerar o arquivo txt", e);
			}
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	private void verificarArquivosEmCampo(List<Integer> ids) {
		
		for (Integer id : ids) {
			Filtro filtro = new FiltroArquivoTextoAtualizacaoCadastral();
			filtro.adicionarParametro(new ParametroSimples(FiltroArquivoTextoAtualizacaoCadastral.ID, id));

			Collection<ArquivoTextoAtualizacaoCadastral> colecao = getFachada().pesquisar(filtro, ArquivoTextoAtualizacaoCadastral.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				ArquivoTextoAtualizacaoCadastral arquivo = (ArquivoTextoAtualizacaoCadastral) Util.retonarObjetoDeColecao(colecao);

				if (arquivo.getSituacaoTransmissaoLeitura().getId().intValue() < SituacaoTransmissaoLeitura.EM_CAMPO.intValue()) {
					throw new ActionServletException("atencao.arquivo_deve_estar_em_campo");
				}
			}
		}
	}

	private List<Integer> getIdsArquivos(ConsultarArquivoTextoAtualizacaoCadastralActionForm form) {
		List<Integer> ids = new ArrayList<Integer>();

		for (String id : form.getIdsRegistros()) {
			ids.add(Integer.parseInt(id));
		}
		return ids;
	}

	private String getNomeArquivo(String idLocalidade) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String data = formatter.format(new Date());

		return "ROTAS_RECADASTRAMENTO_NAO_TRANSMITIDOS_" + data;
	}
}
