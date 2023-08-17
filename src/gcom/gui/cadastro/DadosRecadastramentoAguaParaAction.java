package gcom.gui.cadastro;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.axiom.om.ds.ByteArrayDataSource.ByteArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.jboss.util.file.Files;

import gcom.atualizacaocadastral.ImagemRetorno;
import gcom.cadastro.cliente.CadastroAguaPara;
import gcom.cadastro.imovel.bean.ConsultarRecadastramentoAguaParaHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ImagemUtil;

public class DadosRecadastramentoAguaParaAction extends GcomAction {

	private String caminhoJboss = System.getProperty("jboss.server.home.dir");
	private HttpServletRequest request;
	
	@Override
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

		ActionForward retorno = actionMapping.findForward("dadosRecadastramentoAguaPara");
		
		HttpSession sessao = httpServletRequest.getSession(false);	
		this.request = httpServletRequest;
		
		Usuario usuarioLogado = this.getUsuarioLogado(httpServletRequest);

		if (!getFachada().verificarPermissaoRecadastramentoAguaPara(usuarioLogado))
			throw new ActionServletException("atencao.usuario_sem_permissao_recadastramento_bolsa_agua");

		DadosRecadastramentoAguaParaActionForm form = (DadosRecadastramentoAguaParaActionForm) actionForm;

		String cpf = form.getCpf().replace(";", "");

		Collection colecaoHelper = (Collection) sessao.getAttribute("colecaoConsultarRecadastramentoAguaParaHelper");

		ConsultarRecadastramentoAguaParaHelper consultarRecadastramentoAguaParaHelper = new ConsultarRecadastramentoAguaParaHelper();

		for (Iterator iterator = colecaoHelper.iterator(); iterator.hasNext();) {
			ConsultarRecadastramentoAguaParaHelper consultarRecadastramentoAguaPara = (ConsultarRecadastramentoAguaParaHelper) iterator
					.next();
			if (consultarRecadastramentoAguaPara.getCpf().equals(cpf)) {
				consultarRecadastramentoAguaParaHelper = consultarRecadastramentoAguaPara;
				break;
			}
		}
		sessao.setAttribute("consultarRecadastramentoAguaParaHelper", consultarRecadastramentoAguaParaHelper);
		CadastroAguaPara cadastro = getFachada().pesquisarRecadastramentoAguaParaPorCpf(cpf);
		  sessao.setAttribute("jaAnalisado", false);
		if(cadastro.getSituacao().equals(CadastroAguaPara.ACEITO)||cadastro.getSituacao().equals(CadastroAguaPara.RECUSADO)) {
		  sessao.setAttribute("jaAnalisado", true);
		}
		if(isAprovar()) {
			cadastro.setSituacao(CadastroAguaPara.ACEITO);
			getFachada().atualizar(cadastro);
			retorno = actionMapping.findForward("telaSucesso");
			montarPaginaSucesso(httpServletRequest, "Recadastramento do cliente de cpf "
					+ cpf
					+ " atualizado com sucesso.",
					"Realizar outra avaliação de Cadastro",
					"filtrarRecadastramentoAguaParaAction.do");
		} else if (isNegar()) {
			retorno = actionMapping.findForward("telaSucesso");
			cadastro.setSituacao(CadastroAguaPara.RECUSADO);
			getFachada().atualizar(cadastro);
			montarPaginaSucesso(httpServletRequest, "Recadastramento do cliente de cpf "
					+ cpf
					+ " atualizado com sucesso.",
					"Realizar outra avaliação de Cadastro",
					"filtrarRecadastramentoAguaParaAction.do");
		}
		
		List<ImagemRetorno> imagens = this.buscarArquivosCliente(cpf);
		  sessao.setAttribute("colecaoImagens", imagens);
		 
		
		return retorno;
	}

	private List<ImagemRetorno> buscarArquivosCliente(String cpf) throws IOException {
		
		File[] arquivos = this.recuperarArquivoS(cpf);

		List<ImagemRetorno> imagensRetorno = new ArrayList<>();
		int i = 0;
		ImagemRetorno imagemRetorno;
		
		for (int j = arquivos.length; i < j; i++) {
			File imagem = arquivos[i];
			imagemRetorno = new ImagemRetorno(imagem.getName(), this.getPathImagemRetorno(imagem.getName()));
			imagensRetorno.add(imagemRetorno);
		}
			
		return imagensRetorno;
	}

	private File[] recuperarArquivoS(String cpf) throws IOException {
		File pasta = new File(caminhoJboss + "/arquivos/portal/cadastro_agua_para", cpf);
		
		File arquivos[] = pasta.listFiles();
		
		return arquivos;
	}
	
	
	private String getPathImagemRetorno(String nomeArquivo) {
		StringBuilder path = new StringBuilder("/cadastro_agua_para/");
		
		String[] splitNomeArquivo = nomeArquivo.split("_");
		return path.append(splitNomeArquivo[0])
					.append("/")
					.append(nomeArquivo)
					.toString();
	}
	
	private boolean isAprovar() {
		String action = (String) request.getParameter("action");
		return action != null && action.equals("aprovar");
	}
	private boolean isNegar() {
		String action = (String) request.getParameter("action");
		return action != null && action.equals("negar");
	}
	
}
