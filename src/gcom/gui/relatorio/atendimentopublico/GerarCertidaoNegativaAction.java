package gcom.gui.relatorio.atendimentopublico;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.atendimentopublico.GerarCertidaoNegativaActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.atendimentopublico.RelatorioCertidaoNegativa;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC00725] Gerar Relatório de Imóveis por Situação da Ligação de Água
 * 
 * @author Rafael Pinto
 *
 * @date 28/11/2007
 */

public class GerarCertidaoNegativaAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("emitirCertidaoNegativa");
		
		// Form
		GerarCertidaoNegativaActionForm form = 
			(GerarCertidaoNegativaActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		// Imovel que foi informado
		Imovel imo = null;
		
		if (form.getIdImovel() != null && 
			!form.getIdImovel().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			imo = new Imovel();
			imo.setId( Integer.valueOf( form.getIdImovel() ) );
			
			FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
			filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, imo.getId()));
			filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, ClienteRelacaoTipo.RESPONSAVEL));
			filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
			filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo.esferaPoder");
			
			Collection colecaoClienteImovel = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
			
			if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {
				ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(colecaoClienteImovel);
				
				if (clienteImovel.getCliente().getClienteTipo().getEsferaPoder().getIndicadorPermiteCertidaoNegativaDebitosParaImovel().equals(ConstantesSistema.NAO)) {
					throw new ActionServletException("atencao.esfera_poder_responsavel_nao_permite_geracao_certidao_negativa");
				}
			}
			
		}		
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		Usuario usuarioLogado = this.getUsuarioLogado( httpServletRequest );
		
		TarefaRelatorio relatorio = 
			new RelatorioCertidaoNegativa( usuarioLogado );		
		
		relatorio.addParametro("imovel", imo);
		relatorio.addParametro("usuarioLogado", usuarioLogado);
		
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}	

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		retorno = 
			processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
				httpServletResponse, actionMapping);

		return retorno;
	}
	
}
