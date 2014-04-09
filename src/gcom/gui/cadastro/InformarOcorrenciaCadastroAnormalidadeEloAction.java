package gcom.gui.cadastro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * implementa o método de Informar no caso de uso [UC0491] Informar Ocorrência de Cadastro e/ou Anormalidade de Elo
 * 
 * @author Tiago Moreno
 * @date 20/11/2006
 */
public class InformarOcorrenciaCadastroAnormalidadeEloAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		//instanciando a fachada
		Fachada fachada = Fachada.getInstancia();
		
		//setando o retorno para a tela de susseco
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		//declarando as variaveis que serao recuperadas do form
		String idImovel = null;
		String idOcorrenciaCadastro = null;
		String idAnormalidadeElo = null;
		String dataOcorrenciaCadastro = null;
		String dataAnormalidadeElo = null;
		byte[] uploadPictureCadastro = null;
		byte[] uploadPictureAnormalidade = null;
		
		//essa rotina recupera os dados da Jsp inicial sem usar ActionForm
			try {
				DiskFileUpload upload = new DiskFileUpload();

				//Parse the Request
				List items = upload.parseRequest(httpServletRequest);

				if (items != null) {
					FileItem item = null;

					//Pega uma Lista de Itens do Form
					Iterator iter = items.iterator();
					while (iter.hasNext()) {

						item = (FileItem) iter.next();
						if (item.getFieldName().equals("idImovel")) {
							idImovel = item.getString();
						}
						if (item.getFieldName().equals("cadastroOcorrencia")) {
							idOcorrenciaCadastro = item.getString();
						}
						if (item.getFieldName().equals("anormalidade")) {
							idAnormalidadeElo = item.getString();
						}
						if (item.getFieldName().equals("dataOcorrenciaCadastro")) {
							dataOcorrenciaCadastro = item.getString();
						}
						if (item.getFieldName().equals("dataAnormalidadeElo")) {
							dataAnormalidadeElo = item.getString();
						}
						if (item.getFieldName().equals("uploadPictureCadastro")) {
							if (item.get().length != 0){
								//condiciona ao arquivo ser do tipo JPG ou GIF
								if (item.getName().toUpperCase().endsWith(".JPG")){
									//condiciona o arquivo ser menor que 300Kb ou 307200 Bytes
									if (item.getSize() < 307200) {
										uploadPictureCadastro = item.get();
									}else {
										throw new ActionServletException("atencao.imagem_tamanho_maximo_300kb");
									}
								
								} else {
									throw new ActionServletException("atencao.arquivo_jpg_gif");
								}
							}
						}
						if (item.getFieldName().equals("uploadPictureAnormalidade")) {
							if (item.get().length != 0){
								//condiciona ao arquivo ser do tipo JPG ou GIF
								if (item.getName().toUpperCase().endsWith(".JPG")){
									//condiciona o arquivo ser menor que 300Kb ou 307200 Bytes
									if (item.getSize() < 307200) {
										uploadPictureAnormalidade = item.get();
									}else {
										throw new ActionServletException("atencao.imagem_tamanho_maximo");
									}
								} else {
									throw new ActionServletException("atencao.arquivo_jpg_gif");
								}
							}
						}
					}
				}
			} catch (FileUploadException e) {
				throw new ActionServletException("erro.sistema", e);
			}
			
			if (idOcorrenciaCadastro != null && !idOcorrenciaCadastro.equalsIgnoreCase("")){
				if (dataOcorrenciaCadastro == null || dataOcorrenciaCadastro.equalsIgnoreCase("")){
					throw new ActionServletException("atencao.data_ocorrencia");
				}
			}
			
			if (idAnormalidadeElo != null && !idAnormalidadeElo.equalsIgnoreCase("")){
				if (dataAnormalidadeElo == null || dataAnormalidadeElo.equalsIgnoreCase("")){
					throw new ActionServletException("atencao.data_anormalidade");
				}
			}
		
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
			
        Collection colecaoIdCadastroOcorrenciaRemover = (Collection) sessao.getAttribute("imovelCadastroOcorrenciaRemover");
        Collection colecaoIdAnormalidadeRemover = (Collection) sessao.getAttribute("imovelAnormalidadeRemover");
		//executando o metodo do ControladorImovel responsavel pelas validacoes e insercao no banco
		fachada.informarOcorrenciaCadastroAnormalidadeElo(
				idImovel,
				idOcorrenciaCadastro,
				idAnormalidadeElo,
				dataOcorrenciaCadastro,
				dataAnormalidadeElo,
				uploadPictureCadastro,
				uploadPictureAnormalidade,
				usuarioLogado,
				colecaoIdCadastroOcorrenciaRemover,
				colecaoIdAnormalidadeRemover);
		
		//montando pagina de sucesso
		montarPaginaSucesso(httpServletRequest, "Ocorrência e/ou Anormalidade do Imóvel inserida com sucesso.",
				"Informar outro Ocorrência/Anormalidade do Imóvel",
				"exibirInformarOcorrenciaCadastroAnormalidadeEloAction.do?menu=sim");
		
		
		return retorno;
	}
}
