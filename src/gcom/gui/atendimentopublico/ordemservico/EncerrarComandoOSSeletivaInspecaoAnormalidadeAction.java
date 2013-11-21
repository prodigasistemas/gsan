package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ComandoOrdemSeletiva;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.batch.Processo;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1197] Encerrar Comandos de OS Seletiva de Inspeção de Anormalidade
 *
 * @author Vivianne Sousa
 * @created 21/07/2011
 */
public class EncerrarComandoOSSeletivaInspecaoAnormalidadeAction extends ExibidorProcessamentoTarefaRelatorio  {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException  {

		
		ActionForward retorno = actionMapping.findForward("telaSucesso");


		ConsultarComandosOSSeletivaInspecaoAnormalidadeActionForm form = (ConsultarComandosOSSeletivaInspecaoAnormalidadeActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		Integer idRegistro = new Integer(form.getIdRegistro()) ;
		
//		String idEmpresa = form.getIdEmpresa();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		ComandoOrdemSeletiva  comandoOrdemSeletiva = fachada.pesquisarComandoOSSeletiva(idRegistro);
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		Short idMotivoEncerramento = null;
		if (comandoOrdemSeletiva.getDataEncerramento() != null) {
			throw new ActionServletException("atencao.comando.ja_encerrado", 
					null, "Comandos de OS Seletiva de Inspeção de Anormalidade");
		} 
		
		Date dataPrevista = comandoOrdemSeletiva.getDataGeracao();
		if(sistemaParametro.getQtdeDiasValidadeOSAnormalidadeFiscalizacao() != null){
			dataPrevista = Util.adicionarNumeroDiasDeUmaData(dataPrevista,sistemaParametro.getQtdeDiasValidadeOSAnormalidadeFiscalizacao());
		}
		
		Date dataAtual = new Date();
		
		if(dataAtual.compareTo(dataPrevista) >= 0){
			idMotivoEncerramento = AtendimentoMotivoEncerramento.CANCELADO_POR_DERCURSO_DE_PRAZO;
		}else{
			if(comandoOrdemSeletiva.getIndicadorGeracaoTxt().equals(ConstantesSistema.NAO)){
				idMotivoEncerramento = AtendimentoMotivoEncerramento.DESISTENCIA_DO_USUARIO;
			}else{
				throw new ActionServletException("atencao.nao_e_possivel_encerrar_comando");
			}
		}
		
		Map parametros = new HashMap();
		parametros.put("idMotivoEncerramento", idMotivoEncerramento);
		parametros.put("idRegistro", idRegistro);
		
		fachada.inserirProcessoIniciadoParametrosLivres(parametros, 
          		Processo.ENCERRAR_COMANDO_OS_SELETIVA_INSP_ANORM, usuarioLogado);
			
		//montando página de sucesso
		montarPaginaSucesso(httpServletRequest,
			"Encerramento do Comando  de OS Seletiva de Inspeção de Anormalidade Enviado para Processamento", 
			"Voltar",
			"exibirConsultarComandosOSSeletivaInspecaoAnormalidadeAction.do?menu=sim");
	
		return retorno;
	}

}
