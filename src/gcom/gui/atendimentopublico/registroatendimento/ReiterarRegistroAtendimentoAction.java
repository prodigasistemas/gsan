package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.RAReiteracao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <<Descrição da Classe>>
 * 
 * @author lms
 * @date 20/09/2006
 */
public class ReiterarRegistroAtendimentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
								 ActionForm actionForm, 
								 HttpServletRequest httpServletRequest,
								 HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");		
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		ReiterarRegistroAtendimentoActionForm form = (ReiterarRegistroAtendimentoActionForm) actionForm;
		
		RegistroAtendimento registroAtendimento = fachada.obterDadosRegistroAtendimento(Integer.parseInt(httpServletRequest.getParameter("numeroRA"))).getRegistroAtendimento();
		
		
		RAReiteracao raReiteracao = new RAReiteracao();
		
		String protocoloAtendimento = (String)sessao.getAttribute("protocoloAtendimento");
		raReiteracao.setNumeroProtocoloAtendimento(protocoloAtendimento);
		
		Cliente cliente = null;
		UnidadeOrganizacional unidade = null;
		if(form.getIdClienteSolicitante() != null && !form.getIdClienteSolicitante().equals("")){
			cliente = new Cliente();
			cliente.setId(new Integer(form.getIdClienteSolicitante()));
		}else if(form.getIdUnidadeSolicitante() != null && !form.getIdUnidadeSolicitante().equals("")){
			unidade = new UnidadeOrganizacional();
			unidade.setId(new Integer(form.getIdUnidadeSolicitante()));
		}else{
			raReiteracao.setSolicitante(form.getNomeSolicitante());
		}
		raReiteracao.setUnidadeOrganizacional(unidade);
		raReiteracao.setCliente(cliente);

		raReiteracao.setRegistroAtendimento(registroAtendimento);
//		raReiteracao.setPontoReferencia(form.getPontoReferencia());
		raReiteracao.setObservacao(form.getObservacao());
		
		if (sessao.getAttribute("colecaoEnderecos") != null) {

			Collection enderecos = (Collection) sessao.getAttribute("colecaoEnderecos");

			if (!enderecos.isEmpty()) {
				
				ClienteEndereco endereco = (ClienteEndereco)enderecos.iterator().next();
				
				raReiteracao.setComplementoEndereco(endereco.getComplemento());
				raReiteracao.setIndicadorEnderecoCorrespondencia(endereco.getIndicadorEnderecoCorrespondencia());
				raReiteracao.setLogradouroBairro(endereco.getLogradouroBairro());
				raReiteracao.setLogradouroCep(endereco.getLogradouroCep());
				raReiteracao.setNumeroImovel(new Integer(endereco.getNumero().trim()));
				raReiteracao.setPerimetroInicial(endereco.getPerimetroInicial());
				raReiteracao.setPerimetroFinal(endereco.getPerimetroFinal());
				
			}
//			else{
//	        	throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Endereço");
//			}
		}
//		else{
//        	throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Endereço");
//		}

		Collection colecaoFonesSolicitante = (Collection)sessao.getAttribute("colecaoFonesSolicitante");
		if(colecaoFonesSolicitante == null || colecaoFonesSolicitante.isEmpty()){
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Fones do Solicitante");
		}
		
		//gera a ordem de serviço
		fachada.reiterarRegistroAtendimento(registroAtendimento, usuario, raReiteracao, colecaoFonesSolicitante);
		
		//Exibe a página de sucesso
		montarPaginaSucesso(httpServletRequest, 
				"Registro de Atendimento de número " + registroAtendimento.getId() + " reiterado com sucesso.", 
				"", 
				"exibirGerarOrdemServicoAction.do", 
				"exibirConsultarRegistroAtendimentoAction.do?numeroRA=" + registroAtendimento.getId(), 
				"Voltar");

		
		
		return retorno;
	}

}
