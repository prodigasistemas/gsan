package gcom.gui.atendimentopublico;

import gcom.cadastro.EnvioEmail;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.email.ErroEmailException;
import gcom.util.email.ServicosEmail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável para recuperar os dados da pagina de melhorias do gcom e
 * mandada para o e-mail
 * 
 * @author Sávio Luiz
 * @created 15 de Fevereiro de 2007
 */
public class InformarMelhoriasGcomAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		Fachada fachada = Fachada.getInstancia();

		MelhoriasGcomActionForm melhoriasGcomActionForm = (MelhoriasGcomActionForm) actionForm;

		StringBuilder mensagemEmail = new StringBuilder();

		String descricaoAcesso = "ASSUNTO: ";
		if (melhoriasGcomActionForm.getIdAssunto() != null) {
			if (melhoriasGcomActionForm.getIdAssunto().equals("1")) {
				descricaoAcesso += "CADASTRO";
			}
			if (melhoriasGcomActionForm.getIdAssunto().equals("2")) {
				descricaoAcesso += "COBRANÇA";
			}
			if (melhoriasGcomActionForm.getIdAssunto().equals("3")) {
				descricaoAcesso += "GERENCIAL";
			}
			if (melhoriasGcomActionForm.getIdAssunto().equals("4")) {
				descricaoAcesso += "MICROMEDIÇÃO";
			}
			if (melhoriasGcomActionForm.getIdAssunto().equals("5")) {
				descricaoAcesso += "ARRECADAçÂO";
			}
			if (melhoriasGcomActionForm.getIdAssunto().equals("6")) {
				descricaoAcesso += "FATURAMENTO";
			}
			if (melhoriasGcomActionForm.getIdAssunto().equals("7")) {
				descricaoAcesso += "ATENDIMENTO";
			}
		}

		mensagemEmail.append(descricaoAcesso);
		mensagemEmail.append(System.getProperty("line.separator"));
		mensagemEmail.append(System.getProperty("line.separator"));

		mensagemEmail.append("DADOS DO USUÁRIO:");
		mensagemEmail.append(System.getProperty("line.separator"));
		mensagemEmail.append("LOGIN: ");
		if (melhoriasGcomActionForm.getLoginUsuario() != null) {
			mensagemEmail.append(melhoriasGcomActionForm.getLoginUsuario().toUpperCase());
		}
		mensagemEmail.append(System.getProperty("line.separator"));
		mensagemEmail.append("NOME: ");
		if (melhoriasGcomActionForm.getNomeUsuario() != null) {
			mensagemEmail.append(melhoriasGcomActionForm.getNomeUsuario().toUpperCase());
		}
		mensagemEmail.append(System.getProperty("line.separator"));
		mensagemEmail.append("UNIDADE LOTAÇÃO: ");
		if (melhoriasGcomActionForm.getUnidadeLotacao() != null) {
			mensagemEmail.append(melhoriasGcomActionForm.getUnidadeLotacao().toUpperCase());
		}
		mensagemEmail.append(System.getProperty("line.separator"));
		mensagemEmail.append("TELEFONE: ");
		if (melhoriasGcomActionForm.getTelefone() != null) {
			mensagemEmail.append(melhoriasGcomActionForm.getTelefone().toUpperCase());
		}
		mensagemEmail.append(System.getProperty("line.separator"));
		mensagemEmail.append("EMAIL: ");
		if (melhoriasGcomActionForm.getEmail() != null) {
			mensagemEmail.append(melhoriasGcomActionForm.getEmail());
		}

		mensagemEmail.append(System.getProperty("line.separator"));
		mensagemEmail.append(System.getProperty("line.separator"));

		mensagemEmail.append("DADOS ESPECÍFICOS:");
		mensagemEmail.append(System.getProperty("line.separator"));
		mensagemEmail.append("MATRÍCULA DO IMÓVEL: ");
		if (melhoriasGcomActionForm.getMatirculaImovel() != null) {
			mensagemEmail.append(melhoriasGcomActionForm.getMatirculaImovel().toUpperCase());
		}
		mensagemEmail.append(System.getProperty("line.separator"));
		mensagemEmail.append("CÓDIGO DO CLIENTE: ");
		if (melhoriasGcomActionForm.getCodigoCliente() != null) {
			mensagemEmail.append(melhoriasGcomActionForm.getCodigoCliente().toUpperCase());
		}
		mensagemEmail.append(System.getProperty("line.separator"));
		mensagemEmail.append("NÚMERO RA: ");
		if (melhoriasGcomActionForm.getNumeroRA() != null) {
			mensagemEmail.append(melhoriasGcomActionForm.getNumeroRA().toUpperCase());
		}
		mensagemEmail.append(System.getProperty("line.separator"));
		mensagemEmail.append("NÙMERO OS: ");
		if (melhoriasGcomActionForm.getNumeroOS() != null) {
			mensagemEmail.append(melhoriasGcomActionForm.getNumeroOS().toUpperCase());
		}
		mensagemEmail.append(System.getProperty("line.separator"));
		mensagemEmail.append("DETALHAMENTO: ");
		if (melhoriasGcomActionForm.getDetalhamento() != null) {
			mensagemEmail.append(melhoriasGcomActionForm.getDetalhamento().toUpperCase());
		}
		
		EnvioEmail envioEmail = fachada.pesquisarEnvioEmail(EnvioEmail.ENTRE_EM_CONTATO);

		try {
			ServicosEmail.enviarMensagem(melhoriasGcomActionForm.getEmail(),
					envioEmail.getEmailReceptor(),envioEmail.getTituloMensagem(),
					mensagemEmail.toString());
		} catch (ErroEmailException e) {
			throw new ActionServletException("erro.envio.mensagem");
		}

		// montando página de sucesso
		montarPaginaSucesso(httpServletRequest,
				"Mensagem enviada com sucesso.", "", "");
		return retorno;
	}
}
