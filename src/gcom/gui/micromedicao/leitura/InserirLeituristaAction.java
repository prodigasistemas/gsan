package gcom.gui.micromedicao.leitura;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.leitura.InserirLeituristaActionForm;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action utilizado para inserir um Leiturista no banco
 * 
 * [UC0588] Inserir Leiturista Permite inserir um Leiturista
 * 
 * @author Thiago Tenório
 * @since 22/07/2007
 */
public class InserirLeituristaAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		// Usuario logado no sistema
		Usuario usuarioLogado = (Usuario) sessao
				.getAttribute(Usuario.USUARIO_LOGADO);

		InserirLeituristaActionForm inserirLeituristaActionForm = (InserirLeituristaActionForm) actionForm;
		
		// Validar se IMEI possui 15 caracteres
		if (inserirLeituristaActionForm.getNumeroImei() != null && inserirLeituristaActionForm.getNumeroImei().length() != 15) {
			throw new ActionServletException("atencao.imei.invalido");
		}
		
		// Validar se IMEI já está cadastrado
		if (inserirLeituristaActionForm.getNumeroImei() != null && !inserirLeituristaActionForm.getNumeroImei().trim().equals("")) {
			Long numeroImei = new Long(inserirLeituristaActionForm.getNumeroImei());
			
			FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
			filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.IMEI, numeroImei));
			
			Collection pesquisa = fachada.pesquisar(filtroLeiturista, Leiturista.class.getName());
			
			if (pesquisa != null && pesquisa.size() > 0) {
				Leiturista leiturista = (Leiturista) Util.retonarObjetoDeColecao(pesquisa);
				throw new ActionServletException("atencao.imei.ja.cadastrado", null, leiturista.getCliente().getNome());
			}
		}

		// Cria um Leiturista setando os valores informados pelo
		// usuário na pagina para ser inserido no banco
		Leiturista leiturista = new Leiturista();

		// Validamos o Funcionario
		if (inserirLeituristaActionForm.getIdFuncionario() != null && 
			!inserirLeituristaActionForm.getIdFuncionario().trim().equals("")) {
			
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();
	
			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, inserirLeituristaActionForm
							.getIdFuncionario()));
	
			Collection colFuncionario = fachada.pesquisar(filtroFuncionario,
					Funcionario.class.getName());
	
			if (colFuncionario == null || !colFuncionario.iterator().hasNext()) {
				// O funcionario não existe
				throw new ActionServletException("atencao.funcionario.inexistente",
						null, "Funcionario");
				
			}
		}

		// Validamos o cliente
		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,
				inserirLeituristaActionForm.getIdCliente()));

		Collection colCliente = fachada.pesquisar(filtroCliente, Cliente.class
				.getName());

		if (colCliente == null || !colCliente.iterator().hasNext()) {
			// O cliente não existe
			throw new ActionServletException("atencao.cliente.inexistente",
					null, "Cliente");
		}

		// Funcionario
		if (inserirLeituristaActionForm.getIdFuncionario() != null && 
			!inserirLeituristaActionForm.getIdFuncionario().trim().equals("")) {
			
			Funcionario funcionario = new Funcionario();
			funcionario.setId(new Integer(inserirLeituristaActionForm
					.getIdFuncionario()));
	
			leiturista.setFuncionario(funcionario);
		}

		// Cliente
		Cliente cliente = new Cliente();
		cliente.setId(new Integer(inserirLeituristaActionForm.getIdCliente()));

		leiturista.setCliente(cliente);

		// Telefone
		leiturista.setNumeroFone(inserirLeituristaActionForm.getTelefone());
		
		// Numero do IMEI
		leiturista.setNumeroImei(new Long(inserirLeituristaActionForm.getNumeroImei()));

		// Código DDD do Municipio
		leiturista.setCodigoDDD(inserirLeituristaActionForm.getDdd());
		
		//Indicador de uso 
		leiturista.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		

		// Empresa
		if (Util.validarNumeroMaiorQueZERO(inserirLeituristaActionForm
				.getEmpresaID())) {
			// Constrói o filtro para pesquisa da Empresa
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.ID, inserirLeituristaActionForm
							.getEmpresaID()));

			Collection colecaoEmpresa = (Collection) fachada.pesquisar(
					filtroEmpresa, Empresa.class.getName());

			// setando
			leiturista.setEmpresa((Empresa) colecaoEmpresa.iterator().next());
		}
		
		// Inserimos o usuário
		if ( inserirLeituristaActionForm.getLoginUsuario() != null && !inserirLeituristaActionForm.getLoginUsuario().equals( "" ) ){
			// Filtra Usuario
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ComparacaoTexto(FiltroUsuario.LOGIN, inserirLeituristaActionForm.getLoginUsuario() ) );		
			
			// Recupera Usuário
			Collection<Usuario> colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = colecaoUsuario.iterator().next();
				
				leiturista.setUsuario( usuario );
			}
		}		

		// Ultima alteração
		leiturista.setUltimaAlteracao(new Date());

		// Insere um Leiturista na base, mas fazendo, antes,
		// algumas verificações no ControladorMicromediçãoSEJB.
		fachada.inserirLeiturista(leiturista, usuarioLogado);

		// Exibe a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Leiturista "
				+ leiturista.getId() + " inserido com sucesso.",
				"Inserir outro Leiturista",
				"exibirInserirLeituristaAction.do?menu=sim");

		return retorno;

	}

}
