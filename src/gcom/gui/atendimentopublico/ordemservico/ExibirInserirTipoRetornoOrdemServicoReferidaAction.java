package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipoReferencia;
import gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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
 * @date 26/07/2006
 */
public class ExibirInserirTipoRetornoOrdemServicoReferidaAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de um tipo de retorno da ordem de serviço referida
	 * 
	 * [UC0396] Inserir Tipo de Retorno da Ordem de Serviço Referida
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
								 ActionForm actionForm, 
								 HttpServletRequest httpServletRequest,
								 HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("inserirTipoRetornoOrdemServicoReferida");		
		InserirTipoRetornoOrdemServicoReferidaActionForm form = (InserirTipoRetornoOrdemServicoReferidaActionForm) actionForm;		
		Fachada fachada = Fachada.getInstancia();		
		HttpSession sessao = httpServletRequest.getSession(false);
		
        //Constrói filtro para pesquisa dos serviços tipo referência
		FiltroServicoTipoReferencia filtroServicoTipoReferencia  = new FiltroServicoTipoReferencia();
		filtroServicoTipoReferencia.setCampoOrderBy(FiltroServicoTipoReferencia.DESCRICAO);
		filtroServicoTipoReferencia.adicionarParametro(new ParametroSimples(FiltroServicoTipoReferencia.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		sessao.setAttribute("colecaoServicoTipoReferencia", fachada.pesquisar(filtroServicoTipoReferencia, ServicoTipoReferencia.class.getName(), "SERVICO_TIPO_REFERENCIA"));
		 
	    //Constrói o filtro para pesquisa dos motivos de encerramento
		FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
		filtroAtendimentoMotivoEncerramento.setCampoOrderBy(FiltroAtendimentoMotivoEncerramento.DESCRICAO);
		filtroAtendimentoMotivoEncerramento.adicionarParametro(new ParametroSimples(FiltroAtendimentoMotivoEncerramento.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			
		sessao.setAttribute("colecaoAtendimentoMotivoEncerramento", fachada.pesquisar(filtroAtendimentoMotivoEncerramento, AtendimentoMotivoEncerramento.class.getName(), "ATENDIMENTO_MOTIVO_ENCERRAMENTO"));
		
		OsReferidaRetornoTipo osReferidaRetornoTipo = form.getOsReferidaRetornoTipo();
		
		if (Util.validarNumeroMaiorQueZERO(form.getServicoTipoReferencia())) {
			//Constrói o filtro para pesquisa do serviço tipo referência
			filtroServicoTipoReferencia = new FiltroServicoTipoReferencia();
			filtroServicoTipoReferencia.adicionarParametro(new ParametroSimples(FiltroImovel.ID, form.getServicoTipoReferencia()));
			//Realiza a pesquisa serviço tipo referência
			ServicoTipoReferencia servicoTipoReferencia = (ServicoTipoReferencia) fachada.pesquisar(filtroServicoTipoReferencia, ServicoTipoReferencia.class.getName()).iterator().next();
			
			httpServletRequest.setAttribute("servicoTipoReferencia", servicoTipoReferencia);
			
			servicoTipoReferencia.getIndicadorExistenciaOsReferencia();
			
			osReferidaRetornoTipo.setServicoTipoReferencia(servicoTipoReferencia);
		}
		
		if (Util.validarNumeroMaiorQueZERO(form.getAtendimentoMotivoEncerramento())) {
			//Constrói o filtro para pesquisa do atendimento motivo encerramento
			filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
			filtroAtendimentoMotivoEncerramento.adicionarParametro(new ParametroSimples(FiltroAtendimentoMotivoEncerramento.ID, form.getAtendimentoMotivoEncerramento()));
			//Realiza a pesquisa serviço atendimento motivo encerramento
			AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = (AtendimentoMotivoEncerramento) fachada.pesquisar(filtroAtendimentoMotivoEncerramento, AtendimentoMotivoEncerramento.class.getName()).iterator().next();

			httpServletRequest.setAttribute("atendimentoMotivoEncerramento", atendimentoMotivoEncerramento);
			
			osReferidaRetornoTipo.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
			
			//[FS0003] - Validar atendimento do motivo de encerramento.
			fachada.validarAtendimentoMotivoEncerramento(osReferidaRetornoTipo);
			
		}
		
		httpServletRequest.setAttribute("deferimento", form.getDeferimento());
			
		return retorno;
	}

}
