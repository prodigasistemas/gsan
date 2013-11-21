package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.bean.MonitorarLeituraMobilePopupHelper;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.util.Util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirMonitorarLeituraMobilePopupAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirMonitorarLeituraMobilePopupAction");

		ConsultarArquivoTextoLeituraActionForm form = (ConsultarArquivoTextoLeituraActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Pegamos as informações passadas pelo request		
		String anoMes = httpServletRequest.getParameter("anoMes");
		String grupoFaturamento = httpServletRequest.getParameter("grupoFaturamento");
		String nomeLocalidade = httpServletRequest.getParameter("nomeLocalidade");
		String nomeEmpresa = httpServletRequest.getParameter("nomeEmpresa");
		String nomeLeiturista = httpServletRequest.getParameter("nomeLeiturista");
		String tipoServico = httpServletRequest.getParameter("tipoServico");
		String situacaoTextoLeitura = httpServletRequest.getParameter("situacaoTextoLeitura");
		String idRota = httpServletRequest.getParameter("idRota");
		String cdRota = httpServletRequest.getParameter("cdRota");
		
		if(sessao.getAttribute("anoMes")!=null && anoMes == null){
			anoMes = sessao.getAttribute("anoMes")+"";
		}
		
		if(sessao.getAttribute("grupoFaturamento")!=null && grupoFaturamento == null){
			grupoFaturamento = sessao.getAttribute("grupoFaturamento")+"";
		}		

		if(sessao.getAttribute("nomeLocalidade")!=null && nomeLocalidade == null){
			nomeLocalidade = sessao.getAttribute("nomeLocalidade")+"";
		}

		if(sessao.getAttribute("nomeEmpresa")!=null && nomeEmpresa == null){
			nomeEmpresa = (String) sessao.getAttribute("nomeEmpresa");
		}		
		
		if(sessao.getAttribute("nomeLeiturista")!=null && nomeLeiturista == null){
			nomeLeiturista = sessao.getAttribute("nomeLeiturista")+"";
		}

		if(sessao.getAttribute("tipoServico")!=null && tipoServico == null){
			tipoServico = sessao.getAttribute("tipoServico")+"";
		}

		if(sessao.getAttribute("situacaoTextoLeitura")!=null && situacaoTextoLeitura == null){
			situacaoTextoLeitura = (String) sessao.getAttribute("situacaoTextoLeitura");
		}
		
		if(sessao.getAttribute("idRota")!=null && idRota == null){
			idRota = (String) sessao.getAttribute("idRota");
		}

		if(sessao.getAttribute("cdRota")!=null && cdRota == null){
			cdRota = (String) sessao.getAttribute("cdRota");
		}
		
		Short contaImpressa = null;
		
		if ( form.getContaImpressa() != null && !form.getContaImpressa().equals( "" ) ){
			contaImpressa = Short.parseShort( form.getContaImpressa() );
		}
		
		Short tipoMedicao = null;
		
		String descricaoTipoMedicao = "";
		
		if ( form.getTipoMedicao() != null && !form.getTipoMedicao().equals( "" )){
			tipoMedicao = Short.parseShort( form.getTipoMedicao() );
			
			if ( tipoMedicao.intValue() == 1 ){
				descricaoTipoMedicao = "MEDIDOS";
			} else {
				descricaoTipoMedicao = "NÃO MEDIDOS";
			}
		} else {
			descricaoTipoMedicao = "TODOS";
		}
		
		String descricaoImovelImpresso = "";
		
		if ( form.getContaImpressa() != null && !form.getContaImpressa().equals( "" )){
			if ( contaImpressa.intValue() == 1 ){
				descricaoImovelImpresso = "SIM";
			} else {
				descricaoImovelImpresso = "NÃO";
			}
		} else {
			descricaoImovelImpresso = "TODOS";
		}
		
		
		// Pesquisamos as informações
		Collection<MonitorarLeituraMobilePopupHelper> colHelper = 
			Fachada.getInstancia().pesquisarImoveisMonitorarLeiturasTransmitidas( 
					Integer.parseInt(idRota), 
					Integer.parseInt( Util.formatarMesAnoParaAnoMesSemBarra( anoMes ) ), 
					contaImpressa, 
					tipoMedicao );				
		
		Boolean temPermissao = Fachada.getInstancia().verificarPermissaoEspecialAtiva( PermissaoEspecial.CONSULTAR_ARQUIVO_TEXTO_GERENCIAL, this.getUsuarioLogado(httpServletRequest) );
		
		sessao.setAttribute( "temPermissao", temPermissao );
		sessao.setAttribute( "colecao", colHelper );		
		sessao.setAttribute( "anoMes", anoMes);
		sessao.setAttribute( "grupoFaturamento", grupoFaturamento);
		sessao.setAttribute( "nomeLocalidade", nomeLocalidade );
		sessao.setAttribute( "nomeEmpresa", nomeEmpresa );
		sessao.setAttribute( "nomeLeiturista", nomeLeiturista );
		sessao.setAttribute( "tipoServico", tipoServico);
		sessao.setAttribute( "situacaoTextoLeitura", situacaoTextoLeitura );
		sessao.setAttribute( "idRota", idRota );
		sessao.setAttribute( "cdRota", cdRota );
		sessao.setAttribute( "tipoMedicao", descricaoTipoMedicao );		
		sessao.setAttribute( "imovelImpresso", descricaoImovelImpresso );
		sessao.setAttribute( "quantidade", colHelper.size() );

		return retorno;
	}

}
