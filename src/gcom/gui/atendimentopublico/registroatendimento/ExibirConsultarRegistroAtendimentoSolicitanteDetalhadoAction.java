package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitanteFone;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.SolicitanteFone;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de consulta de solicitantes do ra.
 * 
 * @author Rafael Pinto
 * @created 14/08/2006
 */
public class ExibirConsultarRegistroAtendimentoSolicitanteDetalhadoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("consultarRegistroAtendimentoSolicitanteDetalhado");
		
		Fachada fachada = Fachada.getInstancia();
		
		ConsultarRegistroAtendimentoSolicitanteActionForm consultarRegistroAtendimentoSolicitante = 
				(ConsultarRegistroAtendimentoSolicitanteActionForm) actionForm;
		
		RegistroAtendimentoSolicitante registroAtendimentoSolicitante = 
			pesquisarRegistroAtendimentoSolicitante(new Integer(consultarRegistroAtendimentoSolicitante.getSolicitanteId()));
		
		Cliente cliente = registroAtendimentoSolicitante.getCliente();
		
		UnidadeOrganizacional unidadeSolicitante = 
			registroAtendimentoSolicitante.getUnidadeOrganizacional();

		consultarRegistroAtendimentoSolicitante.setIndicadorPrincipal(
				""+registroAtendimentoSolicitante.getIndicadorSolicitantePrincipal());
		//Caso o principal solicitante do registro de atendimento seja um cliente
		//obter os dados do cliente
		if(cliente != null){
		
			consultarRegistroAtendimentoSolicitante.setClienteSolicitante(""+cliente.getId());
			consultarRegistroAtendimentoSolicitante.setNomeSolicitante(cliente.getNome());
		//Caso o principal solicitante do registro de atendimento seja uma unidade
		//obter os dados da unidade
		}else if(unidadeSolicitante != null){

			consultarRegistroAtendimentoSolicitante.setIdUnidadeSolicitante(""+unidadeSolicitante.getId());
			consultarRegistroAtendimentoSolicitante.setUnidadeSolicitante(unidadeSolicitante.getDescricao());	

		//Caso o principal solicitante do registro de atendimento não seja um cliente, nem uma unidade
		//obter os dados do solicitante
		}else{
			consultarRegistroAtendimentoSolicitante.setNomeSolicitante(registroAtendimentoSolicitante.getSolicitante());
		}
		
		Funcionario funcionario = registroAtendimentoSolicitante.getFuncionario();
		
		if(funcionario != null){
			consultarRegistroAtendimentoSolicitante.setIdFuncionarioResponsavel(""+funcionario.getId());
			consultarRegistroAtendimentoSolicitante.setFuncionarioResponsavel(funcionario.getNome());
		}

		//Caso de Uso [UC0423]
		String enderecoSolicitante = 
			fachada.obterEnderecoSolicitanteRA(registroAtendimentoSolicitante.getID());
		
		consultarRegistroAtendimentoSolicitante.setEnderecoSolicitante(enderecoSolicitante);
		consultarRegistroAtendimentoSolicitante.setPontoReferencia(registroAtendimentoSolicitante.getPontoReferencia());
		
		Collection<SolicitanteFone> colecaoSolicitanteFone = 
			this.consultarSolicitanteFone(registroAtendimentoSolicitante.getID());
		
		consultarRegistroAtendimentoSolicitante.setColecaoSolicitanteFone(colecaoSolicitanteFone);
		
		//PROTOCOLO DE ATENDIMENTO
		if (registroAtendimentoSolicitante.getNumeroProtocoloAtendimento() != null){
			consultarRegistroAtendimentoSolicitante.setProtocoloAtendimento(
			registroAtendimentoSolicitante.getNumeroProtocoloAtendimento());
		}
		
		return retorno;
	}
	
	private RegistroAtendimentoSolicitante pesquisarRegistroAtendimentoSolicitante(Integer idRASolicitante){
		
		Fachada fachada = Fachada.getInstancia();
		
		Collection<RegistroAtendimentoSolicitante> colecaoSolicitantes = null; 

		FiltroRegistroAtendimentoSolicitante filtroRegistroAtendimentoSolicitante = 
			new FiltroRegistroAtendimentoSolicitante();
		
		filtroRegistroAtendimentoSolicitante.adicionarParametro(
				new ParametroSimples(FiltroRegistroAtendimentoSolicitante.ID,
				new Integer(idRASolicitante)));
		
		filtroRegistroAtendimentoSolicitante.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
		filtroRegistroAtendimentoSolicitante.adicionarCaminhoParaCarregamentoEntidade("funcionario");
		filtroRegistroAtendimentoSolicitante.adicionarCaminhoParaCarregamentoEntidade("cliente");
		
		colecaoSolicitantes = 
			fachada.pesquisar(filtroRegistroAtendimentoSolicitante, RegistroAtendimentoSolicitante.class.getName());
		
		RegistroAtendimentoSolicitante registroAtendimentoSolicitante = null;
		
		if (colecaoSolicitantes != null && !colecaoSolicitantes.isEmpty()) {

			registroAtendimentoSolicitante = 
				(RegistroAtendimentoSolicitante) Util.retonarObjetoDeColecao(colecaoSolicitantes);

		} else {
			throw new ActionServletException("atencao.naocadastrado",null, "Registro Atendimento Solicitante");
		}
		
		return registroAtendimentoSolicitante;
	}

	/**
	 * Consulta o solicitante fone pelo id do registro atendimentoSolicitante
	 * 
	 * @author Rafael Pinto
	 * @created 09/08/2006
	 */
	private Collection<SolicitanteFone> consultarSolicitanteFone(Integer idRegistroAtendimentoSolicitante){

		Fachada fachada = Fachada.getInstancia();

		Collection<SolicitanteFone> colecaoSolicitanteFone = null; 

		FiltroSolicitanteFone filtroSolicitanteFone = new FiltroSolicitanteFone();

		filtroSolicitanteFone.adicionarParametro(
			new ParametroSimples(FiltroSolicitanteFone.REGISTRO_ATENDIMENTO_SOLICITANTE_ID,
					idRegistroAtendimentoSolicitante));
		
		filtroSolicitanteFone.adicionarCaminhoParaCarregamentoEntidade("foneTipo");
		
		colecaoSolicitanteFone = fachada.pesquisar(filtroSolicitanteFone,
				SolicitanteFone.class.getName());

		
		return colecaoSolicitanteFone;
	}

}
