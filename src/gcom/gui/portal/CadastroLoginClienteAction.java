package gcom.gui.portal;

import gcom.cadastro.EnvioEmail;
import gcom.cadastro.cliente.ClienteLogin;
import gcom.cadastro.cliente.FiltroClienteLogin;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.gui.GcomAction;
import gcom.util.Criptografia;
import gcom.util.Util;
import gcom.util.email.ErroEmailException;
import gcom.util.email.ModeloEmailConfirmacao;
import gcom.util.email.ServicosEmail;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;
import gcom.util.sms.ServicoSMS;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CadastroLoginClienteAction extends GcomAction {
    
    private static final String FORWARD = "cadastro-login-cliente";
    
	public static final String ATRIBUTO_ERRO = "portal-err-cadastro-login-cliente";
    public static final String ATRIBUTO_ERRO_FORM = "portal-err-cadastro-login-cliente-form";
    public static final String ATRIBUTO_SUCESSO = "portalSucesso";

    private static final String PARAMETRO_ACAO = "acao";
    private static final String PESQUISAR = "pesquisar";
    private static final String CADASTRAR = "cadastrar";
    
    private CadastroLoginClienteActionForm form;
    private HttpServletRequest request;
    private String acao;
    private ActionErrors errors;

    public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
        this.form = (CadastroLoginClienteActionForm) actionForm;
        this.request = request;
        this.acao = request.getParameter(PARAMETRO_ACAO);
        
        resetar();
        
        if (verificarAcao(PESQUISAR))
            pesquisarEndereco();
        
        if (verificarAcao(CADASTRAR))
            cadastrar();

        return mapping.findForward(FORWARD);
    }

    private void resetar() {
        if (acao == null) {
            form.reset(request);
        }
    }

    private void pesquisarEndereco() {
        validarMatriculaInformada();

        if (errors.isEmpty()) {
            try {
                form.setMatriculaSelecionada(form.getMatriculaInformada());
                form.setEnderecoImovel(getFachada().pesquisarEnderecoFormatado(Integer.valueOf(form.getMatriculaSelecionada())));
                form.setMatriculaInformada(null);
            } catch (Exception e) {
                salvarErros(e);
            }
        }
    }

    private void validarMatriculaInformada() {
        errors = new ActionErrors();
        
        if (form.matriculaInformadaInvalida()) {
            errors.add(ATRIBUTO_ERRO, new ActionError("errors.portal.obrigatorio", "a Matrícula do Imóvel"));
        } else if (verificarExistenciaImovel()) {
            errors.add(ATRIBUTO_ERRO, new ActionError("errors.portal.invalida", "Matrícula do Imóvel"));
        }
        
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            request.setAttribute(ATRIBUTO_ERRO, errors);
        }
    }

    private boolean verificarExistenciaImovel() {
        Integer quantidade = getFachada().verificarExistenciaImovel(Integer.valueOf(form.getMatriculaInformada()));
        return errors.isEmpty() && quantidade != 1;
    }

    private void cadastrar() {
        try {
            errors = form.validate();
            
            if (errors.isEmpty()) {
            	ClienteLogin cadastroNovo = new ClienteLogin(form);
            	cadastroNovo.setSenha(Criptografia.encriptarSenha(form.getSenha()));
            	
            	ClienteLogin cadastro = pesquisarCadastro();
            	
            	Integer id = null;
				if (cadastro != null) {
					cadastroNovo.setId(id);
					id = cadastro.getId();
					getFachada().atualizar(cadastroNovo);

				} else {
					id = (Integer) getFachada().inserir(cadastroNovo);
				}
            	
                enviarEmail(id);
                ServicoSMS.enviarSMS(cadastroNovo.getCelular(), ServicoSMS.MSG_CONFICMACAO_CADASTRO_PORTAL);
                request.setAttribute(ATRIBUTO_SUCESSO, true);
                
                form.reset(request);
            } else {
                saveErrors(request, errors);
                request.setAttribute(ATRIBUTO_ERRO_FORM, errors);
            }
        } catch (Exception e) {
            salvarErros(e);
        }
    }
    
	private void enviarEmail(Integer id) {
		SistemaParametro parametros = getFachada().pesquisarParametrosDoSistema();

		EnvioEmail envioEmail = getFachada().pesquisarEnvioEmail(EnvioEmail.ENVIO_CADASTRO_PORTAL);
		
	
		String receptor = form.getEmail();
		String remetente = envioEmail.getEmailRemetente();
		String titulo = envioEmail.getTituloMensagem();
		String urlConfirmacao = parametros.getUrlAcessoInternet() + "/gsan/validacao-email.do&id=" + id; 
		String mensagem = ModeloEmailConfirmacao.getMensagem(form.getNome()) + "\n" + urlConfirmacao;
		Collection<String> destinatarios = new ArrayList<String>();
		 destinatarios.add(receptor);
 
		try {
			ServicosEmail.enviarMensagemHTML(destinatarios, remetente, "COSANPA", titulo, mensagem);
		} catch (ErroEmailException e) {
			salvarErros(e);
		}
	}
	
    private boolean verificarAcao(String acao) {
        return this.acao != null && this.acao.trim().equals(acao);
    }
    
    private void salvarErros(Exception e) {
        e.printStackTrace();
        form.reset(request);
        request.removeAttribute(ATRIBUTO_SUCESSO);
        errors.add(ATRIBUTO_ERRO, new ActionError("errors.portal.erro_inesperado"));
        saveErrors(request, errors);
        request.setAttribute(ATRIBUTO_ERRO, errors);
    }
    
    @SuppressWarnings("unchecked")
    private ClienteLogin pesquisarCadastro() {
		Filtro filtro = new FiltroClienteLogin();
		filtro.adicionarParametro(new ParametroSimples(FiltroClienteLogin.EMAIL, form.getEmail()));
		filtro.adicionarParametro(new ParametroSimples(FiltroClienteLogin.CPF_CNPJ, Util.removerSimbolosPontuacao(form.getCpfOuCnpj())));

		return (ClienteLogin) Util.retonarObjetoDeColecao(
				getFachada().pesquisar(filtro, ClienteLogin.class.getName()));
	}
    
}