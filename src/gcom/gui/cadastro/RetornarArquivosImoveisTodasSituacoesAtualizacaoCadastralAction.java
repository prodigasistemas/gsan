package gcom.gui.cadastro;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
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
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;
import gcom.util.ZipUtil;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

public class RetornarArquivosImoveisTodasSituacoesAtualizacaoCadastralAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, HttpServletRequest request,	HttpServletResponse response) throws ErroRepositorioException, IOException, ControladorException {
		
		ConsultarArquivoTextoAtualizacaoCadastralActionForm form = (ConsultarArquivoTextoAtualizacaoCadastralActionForm) actionForm;
		
		Integer idEmpresa = null;
		if (!form.getIdEmpresa().equals("") && !form.getIdEmpresa().equals("0")) {
			idEmpresa = Integer.valueOf(form.getIdEmpresa());
		}
		
		verificarArquivosEmCampo(form.getIdsRegistros());
		
		
		List<ArquivoTextoAtualizacaoCadastral> listaArquivoTexto = getFachada().gerarArquivosAtualizacaoCadastralTodasSituacoes(form.getIdsRegistros(), ArquivoTextoAtualizacaoCadastral.TIPO_ARQUIVO_TODOS, Util.converteStringParaDate(form.getDataUltimaTransmissao()), idEmpresa);
		
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
		} else {
			throw new ActionServletException("atencao.arquivo_vazio");
		}
		
		
		
		return null;
		
	}
	
	@SuppressWarnings("unchecked")
	private void verificarArquivosEmCampo(String[] idsArquivos) {
		for (int i = 0; i < idsArquivos.length; i++) {
			String idArquivo = idsArquivos[i];
			
			Filtro filtro = new FiltroArquivoTextoAtualizacaoCadastral();
			filtro.adicionarParametro(new ParametroSimples(FiltroArquivoTextoAtualizacaoCadastral.ID, idArquivo));

			Collection<ArquivoTextoAtualizacaoCadastral> colecao = getFachada().pesquisar(filtro, ArquivoTextoAtualizacaoCadastral.class.getName());

			if (colecao != null && !colecao.isEmpty()) {
				ArquivoTextoAtualizacaoCadastral arquivo = (ArquivoTextoAtualizacaoCadastral) Util.retonarObjetoDeColecao(colecao);

				if (arquivo.getSituacaoTransmissaoLeitura().getId().intValue() < SituacaoTransmissaoLeitura.EM_CAMPO.intValue()) {
					throw new ActionServletException("atencao.arquivo_deve_estar_em_campo");
				}
			}
		}
	}

	private String getNomeArquivo(String idLocalidade) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String data = formatter.format(new Date());

		return "ROTAS_RECADASTRAMENTO_TODAS_SITUACOES_" + data;
	}
	
}
