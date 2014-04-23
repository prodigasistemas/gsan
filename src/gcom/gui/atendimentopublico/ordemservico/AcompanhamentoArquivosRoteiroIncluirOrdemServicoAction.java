package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao;
import gcom.atendimentopublico.ordemservico.ProgramacaoRoteiro;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1199] – Acompanhamento Arquivos Roteiro Incluir Ordem Servico Action
 * 
 * @author Thúlio Araújo
 *
 * @date 15/08/2011
 */
public class AcompanhamentoArquivosRoteiroIncluirOrdemServicoAction extends GcomAction {

	Fachada fachada = Fachada.getInstancia();
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("incluirOrdemServicoAcompanhamentoArquivosRoteiro");
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// Form
		AcompanharRoteiroProgramacaoOrdemServicoActionForm 
		acompanharActionForm = (AcompanharRoteiroProgramacaoOrdemServicoActionForm) actionForm;
		
		// Precisa pegar a unidade do usuario do login que esta na sessao
		Integer idUnidadeLotacao = usuario.getUnidadeOrganizacional().getId();
		Date dataRoteiro = Util.converteStringParaDate(acompanharActionForm.getDataProgramacao());
		acompanharActionForm.setDataRoteiro(Util.formatarData(dataRoteiro));
		acompanharActionForm.setUnidadeLotacao(""+idUnidadeLotacao);
		
		// [SB0005] - Incluir Ordem de Serviço na Programação
		this.incluirOrdemServicoProgramacao(acompanharActionForm,usuario);
		
		httpServletRequest.setAttribute("fecharPopup", "true");
		
		return retorno;
	}
	
	private void incluirOrdemServicoProgramacao(AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanharActionForm,
			Usuario usuario){

		Date dataRoteiro = Util.converteStringParaDate(acompanharActionForm.getDataRoteiro());
		
		// Colocado por Raphael Rossiter em 12/03/2007
		Integer idUnidadeLotacao = new Integer(acompanharActionForm.getUnidadeLotacao());
		
		OrdemServico os = 
		Fachada.getInstancia().recuperaOSPorId(new Integer(acompanharActionForm.getIdOrdemServico()));
		
		// Verifica Existencia da Ordem de Servico
		if (os == null || os.equals("")){
			throw new ActionServletException("atencao.ordem_servico.inexistente");
		}
		
		ProgramacaoRoteiro programacaoRoteiro = fachada.pesquisarProgramacaoRoteiro(usuario.getUnidadeOrganizacional().getId(),dataRoteiro);
		
		// [FS0015] - Verificar registro de atendimento e ordem de serviço
		this.pesquisarRegistroAtendimento(acompanharActionForm, usuario.getUnidadeOrganizacional().getId());
		
		String numeroRa = acompanharActionForm.getNumeroRA();
		if(numeroRa != null && !numeroRa.equals("")){
			
			if(os.getRegistroAtendimento() == null || (os.getRegistroAtendimento().getId().intValue() != new Integer(numeroRa).intValue())){
				String[] parametros = new String[2];
				parametros[0] = os.getId().toString();
				parametros[1] = numeroRa;
				throw new ActionServletException("atencao.registro_nao_pertence_ordem_servico",null,parametros);					
			}
		}
		
		if(os.getRegistroAtendimento() == null){
			String[] parametros = new String[2];
			parametros[0] = os.getId().toString();
			parametros[1] = numeroRa;
			throw new ActionServletException("atencao.registro_nao_pertence_ordem_servico",null,parametros);					
		}
		
		//[FS0003] - Verificar possibilidade da inclusão da ordem de serviço
		fachada.validarInclusaoOsNaProgramacao(os, idUnidadeLotacao);
		
		//Validar carga do trabalho
		if (fachada.validarCargaTrabalhoEquipe(Util.converterStringParaInteger(acompanharActionForm.getIdEquipe()),
				programacaoRoteiro.getId(),Util.converterStringParaInteger(acompanharActionForm.getIdOrdemServico()),
				usuario.getUnidadeOrganizacional().getId())){
			throw new ActionServletException("atencao.limite_carga_trabalho_excedido");
		}
		
		//[SB0004 - Reordena Sequencial de Programação - Inclusão de Ordem de Serviço]
		short maiorSequencial = new Short(acompanharActionForm.getSequencialProgramacao());
		short sequencial = new Short(acompanharActionForm.getSequencialProgramacaoPrevisto());
		short sequencialRetorno = 0;
		
		//[FS0004] - Verificar sequencial invalido
		if (sequencial > maiorSequencial){
			throw new ActionServletException("atencao.sequencial_programacao_maior_limite",null,""+maiorSequencial);
		}else if(maiorSequencial != sequencial){
			sequencialRetorno = fachada.reordenaSequencialOSProgramacao(dataRoteiro,sequencial,
					Util.converterStringParaInteger(acompanharActionForm.getIdEquipe()));
		}else{
			sequencialRetorno = sequencial;
		}
		
		// [SB0011] - Incluir Programação
		OrdemServicoProgramacao ordemServicoProgramacao = new OrdemServicoProgramacao();
		
		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setId(new Integer(acompanharActionForm.getIdOrdemServico()));
		
		ordemServicoProgramacao.setProgramacaoRoteiro(programacaoRoteiro);
		ordemServicoProgramacao.setOrdemServico(ordemServico);
		
		Equipe equipe = new Equipe();
		equipe.setId(new Integer(acompanharActionForm.getIdEquipe()));
		
		ordemServicoProgramacao.setEquipe(equipe);
		ordemServicoProgramacao.setUsuarioProgramacao(usuario);
		ordemServicoProgramacao.setUsuarioFechamento(null);
		ordemServicoProgramacao.setOsProgramNaoEncerMotivo(null);
		ordemServicoProgramacao.setNnSequencialProgramacao(sequencialRetorno);
		ordemServicoProgramacao.setIndicadorAtivo(ConstantesSistema.SIM);
		ordemServicoProgramacao.setIndicadorEquipePrincipal(ConstantesSistema.SIM);
		ordemServicoProgramacao.setUltimaAlteracao(new Date());
		ordemServicoProgramacao.setSituacaoFechamento(null);

		fachada.incluirOrdemServicoProgramacao(ordemServicoProgramacao, usuario);
		
		fachada.inserirOrdemProgramacoAcompanhamentoServico(new Integer(acompanharActionForm.getIdEquipe()), dataRoteiro);
	}
	
	private void pesquisarRegistroAtendimento(AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanharActionForm, Integer unidId) {
		
		ObterDadosRegistroAtendimentoHelper obter = 
			fachada.obterDadosRegistroAtendimento(new Integer(
					acompanharActionForm.getNumeroRA()));

		if (obter.getRegistroAtendimento() != null) {
			RegistroAtendimento registroAtendimento = obter.getRegistroAtendimento();
			
			if (!registroAtendimento.getUnidadeAtual().getId().equals(unidId)){
				throw new ActionServletException("atencao.registro.atendimento.nao_associado",
						Integer.toString(registroAtendimento.getId()), Integer.toString(unidId));
			}
			
			if (registroAtendimento.getCodigoSituacao() == 2){
				throw new ActionServletException("atencao.situacao.ra.pendente");				
			}
			
		} else {
			throw new ActionServletException("atencao.registro.atendimento.inexistente");
		}
	}
}
