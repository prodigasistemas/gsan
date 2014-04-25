package gcom.gui.cadastro.tarifasocial;

import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ManterTarifaSocialAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Instancia da Fachada
		Fachada fachada = Fachada.getInstancia();

		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		RegistroAtendimento registroAtendimento = null;
		RegistroAtendimentoUnidade registroAtendimentoUnidade = null;
		
		if (sessao.getAttribute("pesquisaImovel") == null) {

			registroAtendimento = (RegistroAtendimento) sessao
					.getAttribute("ra");

//			AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
//			atendimentoMotivoEncerramento.setId(15);
//
//			registroAtendimento
//					.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);

			registroAtendimentoUnidade = new RegistroAtendimentoUnidade();
			registroAtendimentoUnidade
					.setRegistroAtendimento(registroAtendimento);
			registroAtendimentoUnidade.setUnidadeOrganizacional(usuarioLogado
					.getUnidadeOrganizacional());
			registroAtendimentoUnidade.setUsuario(usuarioLogado);
			AtendimentoRelacaoTipo atendimentoRelacaoTipo = new AtendimentoRelacaoTipo();
			atendimentoRelacaoTipo.setId(AtendimentoRelacaoTipo.ENCERRAR);
			registroAtendimentoUnidade
					.setAtendimentoRelacaoTipo(atendimentoRelacaoTipo);
			registroAtendimentoUnidade.setUltimaAlteracao(new Date());

		}

		// Imóvel que está sendo trabalhado
		Imovel imovelSessao = (Imovel) sessao.getAttribute("imovelTarifa");

		Collection colecaoTarifaSocialHelperAtualizar = (Collection) sessao
				.getAttribute("colecaoTarifaSocialHelperAtualizar");
		
		// Imóveis Anteriores do Usuários que foram excluídos da Tarifa Social 
		Collection colecaoImoveisExcluidosTarifaSocial = (Collection) sessao
				.getAttribute("colecaoImoveisExcluidosTarifaSocial");

		// Para apenas uma economia
		Collection colecaoTarifaSocialExcluida = (Collection) sessao
				.getAttribute("colecaoTarifaSocialExcluida");

		// Recadastramento
		Collection colecaoTarifasSociaisRecadastradas = (Collection) sessao
				.getAttribute("colecaoTarifasSociaisRecadastradas");
		
		AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = fachada.manterTarifaSocial(imovelSessao,
						colecaoTarifaSocialHelperAtualizar,
						colecaoImoveisExcluidosTarifaSocial,
						colecaoTarifaSocialExcluida,
						colecaoTarifasSociaisRecadastradas,usuarioLogado);

		if (sessao.getAttribute("pesquisaImovel") == null) {
            registroAtendimento.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
            registroAtendimento.setDataEncerramento(new Date());
			
			//Colocado por Raphael Rossiter em 10/03/2008
			ObterDadosRegistroAtendimentoHelper registroAtendimentoHelper = 
			fachada.obterDadosRegistroAtendimento(registroAtendimento.getId());
			
			SolicitacaoTipoEspecificacao especificacao = registroAtendimentoHelper
			.getRegistroAtendimento().getSolicitacaoTipoEspecificacao();
			
			if (especificacao.getDebitoTipo() != null){
				
				fachada.encerrarRegistroAtendimento(registroAtendimento,registroAtendimentoUnidade, 
				usuarioLogado, especificacao.getDebitoTipo().getId(), especificacao.getValorDebito(), 1, "100", false,null,false);
			}
			else{
				fachada.encerrarRegistroAtendimento(registroAtendimento,registroAtendimentoUnidade, 
				usuarioLogado, null, null, null, null, false,null,false );
			}
			
		}

		montarPaginaSucesso(httpServletRequest, "Imóvel de matrícula "
				+ imovelSessao.getId()
				+ " mantido na tarifa social com sucesso.",
				"Manter outra tarifa social",
				"exibirManterTarifaSocialAction.do?menu=sim");

		return retorno;
	}
}
