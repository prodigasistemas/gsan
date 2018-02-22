package gcom.gui.cobranca.cobrancaporresultado;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class RetirarSituacaoCobrancaAction extends GcomAction {

	private FormFile arquivoCarregado;

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = mapping.findForward("telaSucesso");
		RetirarSituacaoCobrancaActionForm form = (RetirarSituacaoCobrancaActionForm) actionForm;
		HttpSession sessao = request.getSession(false);
		arquivoCarregado = form.getArquivo();
		validar();

		try {
			int total = getFachada().retirarSituacaoCobranca(escreverArquivo(), (Usuario) sessao.getAttribute("usuarioLogado"));
			montarPaginaSucesso(request, "Arquivo carregado com sucesso.\n\nTotal de Imóveis Atualizados: " + total, "Carregar outro arquivo", "exibirRetirarSituacaoCobrancaAction.do?menu=sim");
		} catch (Exception e) {
			throw new ActionServletException("atencao.arquivo_invalido", e);
		}

		return retorno;
	}

	private BufferedReader escreverArquivo() throws FileNotFoundException, IOException {
		File arquivo = new File(arquivoCarregado.getFileName());
		FileOutputStream out = new FileOutputStream(arquivo);
		out.write(arquivoCarregado.getFileData());
		out.flush();
		out.close();

		BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "LATIN1"));
		arquivo.delete();
		
		return buffer;
	}

	private void validar() {
		if (!arquivoCarregado.getFileName().endsWith(".txt")) {
			throw new ActionServletException("atencao.arquivo_invalido");
		}
	}
}
