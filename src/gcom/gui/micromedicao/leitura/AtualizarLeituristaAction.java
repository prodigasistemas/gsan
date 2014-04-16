package gcom.gui.micromedicao.leitura;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.funcionario.Funcionario;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.leitura.AtualizarLeituristaActionForm;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarLeituristaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarLeituristaActionForm form = (AtualizarLeituristaActionForm) actionForm;

		Leiturista leiturista = (Leiturista) sessao.getAttribute("leiturista");
		
		// Validar se IMEI possui 15 caracteres
		if (form.getNumeroImei() != null && form.getNumeroImei().toString().length() != 15) {
			throw new ActionServletException("atencao.imei.invalido");
		}
		 
		// Validar se IMEI já está cadastrado
		if (form.getNumeroImei() != null && !form.getNumeroImei().equals(leiturista.getNumeroImei())) {
			FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);
			filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.USUARIO);
			filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.IMEI, form.getNumeroImei()));
			
			Collection pesquisa = fachada.pesquisar(filtroLeiturista, Leiturista.class.getName());
			
			if (pesquisa != null && pesquisa.size() > 0) {
				Leiturista l = (Leiturista) Util.retonarObjetoDeColecao(pesquisa);
				
				String nomeLeiturista = l.getId().toString();
				
				if(l.getCliente() != null){
					nomeLeiturista = l.getCliente().getNome();
				}else if(l.getFuncionario() != null){
					nomeLeiturista = l.getFuncionario().getNome();
				}else if(l.getUsuario() != null){
					nomeLeiturista = l.getUsuario().getNomeUsuario();
				}
				
				throw new ActionServletException("atencao.imei.ja.cadastrado", null, nomeLeiturista);
			}
		}
		
		if(form.getIdCliente()!=null && !form.getIdCliente().trim().equals("")){
			// Cliente		
			Cliente cliente = new Cliente();
			cliente.setId(new Integer(form.getIdCliente()));
			leiturista.setCliente(cliente);
			leiturista.setFuncionario(null);
		}else if(form.getIdFuncionario()!=null && !form.getIdFuncionario().trim().equals("")){
			// Funcionario
			Funcionario funcionario = new Funcionario();
			funcionario.setId(new Integer(form
					.getIdFuncionario()));
			leiturista.setFuncionario(funcionario);
			leiturista.setCliente(null);
		}else{
			//Erro Informar um cliente ou funcionario
			throw new ActionServletException("atencao.cliente_ou_funcionario");
			
		}
				
				
		leiturista.setCodigoDDD(form.getDdd());
		leiturista.setNumeroFone(form.getTelefone());
		leiturista.setNumeroImei(form.getNumeroImei());

		//Indicador de uso 
		leiturista.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		

		// Empresa		
		Empresa empresa = null;
		
		if (form
				.getEmpresaID() != null && !form
				.getEmpresaID().equals("") && !form
				.getEmpresaID().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			empresa = new Empresa();
			
			empresa.setId(new Integer(form
					.getEmpresaID()));
		}else{
			throw new ActionServletException("atencao.empresa_leituristica_nao_informado"); 
		}
		
		//Setando
		leiturista.setEmpresa(empresa);
		
		// Usuario
		if ( form.getLoginUsuario() != null && !form.getLoginUsuario().equals( "" ) ){
			// Filtra Usuario
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ComparacaoTexto(FiltroUsuario.LOGIN, form.getLoginUsuario() ) );		
			
			// Recupera Usuário
			Collection<Usuario> colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = colecaoUsuario.iterator().next();
				
				leiturista.setUsuario( usuario );
			}
		}else{
			leiturista.setUsuario( null );
		}
		
		leiturista.setIndicadorUso(new Short(form.getIndicadorUso()));

		fachada.atualizarLeiturista(leiturista);

		montarPaginaSucesso(httpServletRequest, "Leiturista de código "
				+ leiturista.getId().toString() + " atualizada com sucesso.",
				"Realizar outra Manutenção de Leiturista ",
				"exibirFiltrarLeituristaAction.do?menu=sim");
		return retorno;
	}
}
