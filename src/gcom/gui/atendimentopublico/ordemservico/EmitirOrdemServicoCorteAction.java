package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1054] Gerar Ordem de Corte
 * 
 * Este Caso Uso permite realizar a emissão de Documentos de Ordem de Corte
 * de forma individual para um determinado imóvel.
 * 
 * @author Hugo Amorim
 * @data 08/02/2010
 *
 */
public class EmitirOrdemServicoCorteAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("telaSucesso");

		EmitirOrdemCorteForm form =
			(EmitirOrdemCorteForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		OrdemServico ordemServicoCorte = new OrdemServico();
		
		if(form.getMatriculaImovel()!=null
				&& !form.getMatriculaImovel().equals("")){
			
			FiltroImovel filtro = new FiltroImovel();
			
			filtro.adicionarParametro(
					new ParametroSimples(
							FiltroImovel.ID, 
							form.getMatriculaImovel()));
			
			Collection colecaoimovel = 
				fachada.pesquisar(filtro, Imovel.class.getName());
			
			Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoimovel);

			ordemServicoCorte.setImovel(imovel);
		}
		
		String referenciaInicial = "01/0001";
		String referenciaFinal = "12/9999";
		String dataVencimentoInicial = "01/01/0001";
		String dataVencimentoFinal = "31/12/9999";

		// Para auxiliar na formatação de uma data
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		String mesInicial = referenciaInicial.substring(0, 2);
		String anoInicial = referenciaInicial.substring(3, referenciaInicial.length());
		String anoMesInicial = anoInicial + mesInicial;
		String mesFinal = referenciaFinal.substring(0, 2);
		String anoFinal = referenciaFinal.substring(3, referenciaFinal.length());
		String anoMesFinal = anoFinal + mesFinal;

		Date dataVencimentoDebitoI;
		Date dataVencimentoDebitoF;

		try {
			dataVencimentoDebitoI = formatoData.parse(dataVencimentoInicial);
		} catch (ParseException ex) {
			dataVencimentoDebitoI = null;
		}
		try {
			dataVencimentoDebitoF = formatoData.parse(dataVencimentoFinal);
		} catch (ParseException ex) {
			dataVencimentoDebitoF = null;
		}

		// seta valores constantes para chamar o metodo que consulta debitos do imovel
		Integer tipoImovel = new Integer(1);
		Integer indicadorPagamento = new Integer(1);
		Integer indicadorConta = new Integer(1);
		Integer indicadorDebito = new Integer(2);
		Integer indicadorCredito = new Integer(2);
		Integer indicadorNotas = new Integer(2);
		Integer indicadorGuias = new Integer(2);
		Integer indicadorAtualizar = new Integer(1);

		// Obtendo os débitos do imovel
		ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = fachada
				.obterDebitoImovelOuCliente(tipoImovel.intValue(),
						form.getMatriculaImovel().toString().trim(), null, null,
						anoMesInicial, anoMesFinal,
						dataVencimentoDebitoI,
						dataVencimentoDebitoF, indicadorPagamento
								.intValue(), indicadorConta
								.intValue(), indicadorDebito
								.intValue(), indicadorCredito
								.intValue(), indicadorNotas
								.intValue(), indicadorGuias
								.intValue(), indicadorAtualizar
								.intValue(), null);
		
		Collection<ContaValoresHelper> colecaoContaValores = colecaoDebitoImovel.getColecaoContasValores();
		
		if(colecaoContaValores == null || colecaoContaValores .isEmpty()){
			throw new ActionServletException("atencao.imovel.sem.debito",form.getMatriculaImovel().toString().trim());
		}
		
		Collection<CobrancaDocumento> colecaoCobrancaDocumento =
			this.getFachada().pesquisarCobrancaDocumentoValidoImovel(
					Integer.parseInt(form.getMatriculaImovel()),DocumentoTipo.AVISO_CORTE,
					CobrancaAcao.AVISO_CORTE);
		
		if(Util.isVazioOrNulo(colecaoCobrancaDocumento)){
			throw new ActionServletException("atencao.imovel_sem_documento_do_tipo",
					form.getMatriculaImovel().toString(),"AVISO CORTE");
		}
		
			
	    Integer idOsGerada = fachada.gerarOrdemServicoCorte(ordemServicoCorte, usuario);
	    
	    sessao.setAttribute("idOsGerada", idOsGerada.toString());
		
	    form.setIndicadorPermitirEmitir("sim");
	    form.setIndicadorPermitirGerarOs("nao");
	    form.setOrdemServico(idOsGerada.toString());
	    
		montarPaginaSucesso(httpServletRequest,
				"Ordem de Serviço de Corte " +idOsGerada+ " gerada com sucesso.",
				"Emitir Ordem de Corte",
				"exibirEmitirOrdemCorteAction.do");
		
		return retorno;
	}
}
