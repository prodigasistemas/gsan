package gcom.gui.cadastro.funcionario;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.funcionario.FuncionarioCargo;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
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
 * @author Rômulo Aurelio
 * @date 04/04/2007
 */
public class InserirFuncionarioAction extends GcomAction{
	
	/**
	 * Este caso de uso permite a inclusão de um novo Funcionário
	 * 
	 * [UC0842] Inserir Funcionário
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 04/04/2007
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

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirFuncionarioActionForm form = (InserirFuncionarioActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		//CARREGANDO O OBJETO FUNCIONÁRIO
		Funcionario funcionario = this.getFuncionario(form, fachada);
		
		//INSERINDO FUNCIONÁRIO
		fachada.inserirFuncionario(funcionario, usuarioLogado);
		
		montarPaginaSucesso(httpServletRequest, "Funcionario de matricula "
				+ funcionario.getId() + " inserida com sucesso.",
				"Inserir outro Funcionario",
				"exibirInserirFuncionarioAction.do?menu=sim",
				"exibirAtualizarFuncionarioAction.do?idFuncionario="
				+ funcionario.getId() , "Atualizar Funcionario inserido");
				
	
		return retorno;
	}


	/**
	 * [UC0842] Inserir Funcionário
	 *
	 * Carregando os dados do funcionário a partir do que foi informado no formulário
	 *
	 * @author Raphael Rossiter
	 * @date 17/06/2009
	 *
	 * @param form
	 * @param fachada
	 * @return Funcionario
	 */
	private Funcionario getFuncionario(InserirFuncionarioActionForm form, Fachada fachada){
		
		Funcionario funcionario= new Funcionario();
		
		//MATRÍCULA
		funcionario.setId(new Integer(form.getMatricula()));
		
		//NOME
		funcionario.setNome(form.getNome());
		
		//CPF
		if(form.getNumeroCpf() != null && !form.getNumeroCpf().equals("")){
			funcionario.setNumeroCpf(form.getNumeroCpf());
		}
		
		//DATA DE NASCIMENTO
		if(form.getDataNascimento() != null && !form.getDataNascimento().equals("")){
			funcionario.setDataNascimento(Util.converteStringParaDate(form.getDataNascimento()));
		}
		
		//CARGO
		String idFuncionarioCargo = form.getFuncionarioCargo();
		
		if (idFuncionarioCargo != null && !idFuncionarioCargo.trim().equals(
			"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			FuncionarioCargo funcionarioCargo = new FuncionarioCargo();

			funcionarioCargo.setId(new Integer(idFuncionarioCargo));

			funcionario.setFuncionarioCargo(funcionarioCargo);

		}
		
		//EMPRESA
		String idEmpresa = form.getEmpresa();

		if (idEmpresa != null && !idEmpresa.trim().equals(
			"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			Empresa empresa = new Empresa();

			empresa.setId(new Integer(idEmpresa));

			funcionario.setEmpresa(empresa);

		}
		
		//UNIDADE ORGANIZACIONAL
		String idUnidade = form.getIdUnidade();
		
		FiltroUnidadeOrganizacional filtroUnidadeEmpresa = new FiltroUnidadeOrganizacional();

    	filtroUnidadeEmpresa.adicionarParametro(new ParametroSimples(
    	FiltroUnidadeOrganizacional.ID, idUnidade));
		
		Collection<UnidadeOrganizacional> colecaoUnidadeEmpresa = fachada.pesquisar(filtroUnidadeEmpresa,
		UnidadeOrganizacional.class.getName());

		if(colecaoUnidadeEmpresa== null || colecaoUnidadeEmpresa.isEmpty()){
			throw new ActionServletException("atencao.unidade.organizacional.inexistente");
		} 
		
		UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
		unidadeOrganizacional.setId(new Integer(idUnidade));
		
		funcionario.setUnidadeOrganizacional(unidadeOrganizacional);
		
		//ÚLTIMA ALTERAÇÃO
		funcionario.setUltimaAlteracao(new Date());
		
		return funcionario;

	}
}
