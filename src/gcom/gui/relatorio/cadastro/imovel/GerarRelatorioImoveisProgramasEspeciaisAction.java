package gcom.gui.relatorio.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisProgramasEspeciaisHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisProgramasEspeciaisAnalitico;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisProgramasEspeciaisSintetico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0979] Gerar Relatório de Imóveis em Programas Especiais
 * 
 * @author Hugo Leonardo, Ivan Sergio
 * @date 14/01/2010
 * 		 14/09/2010 - CRC4734: Retirar do filtro o perfil do imóvel e obter as contas a partir
 * 					  de fatura item da fatura selecionada;
 */

public class GerarRelatorioImoveisProgramasEspeciaisAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		   
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		   
		// Form
		GerarRelatorioImoveisProgramasEspeciaisActionForm form = 
			(GerarRelatorioImoveisProgramasEspeciaisActionForm) actionForm;
		
		FiltrarRelatorioImoveisProgramasEspeciaisHelper helper = 
			new FiltrarRelatorioImoveisProgramasEspeciaisHelper();
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		SistemaParametro sistemaParametro = this.getSistemaParametro();
		
		// mesAno Referencia 
		if (form.getMesAnoReferencia() != null && 
			!form.getMesAnoReferencia().equals("") && !form.getMesAnoReferencia().equals("00/0000")) {
			
			Integer anoMesReferencia = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoReferencia());
			Integer anoMesFaturamento =  sistemaParametro.getAnoMesFaturamento();
				
			//Validar o Ano/Mes Referencia
			if(anoMesFaturamento.intValue() >= anoMesReferencia.intValue()){
				helper.setMesAnoReferencia(Util.formatarMesAnoComBarraParaAnoMes(
				form.getMesAnoReferencia()).toString());
			}else{
				throw new ActionServletException("atencao.anomesfaturamento.menor_igual_anomesreferencia", 
						null, Util.formatarAnoMesParaMesAno(anoMesFaturamento.toString()));
			} 
		}else {
			throw new ActionServletException("atencao.anomesreferencia.invalida", null, form.getMesAnoReferencia());
		}
		
		// Perfil dos Imoveis
		if (sistemaParametro.getPerfilProgramaEspecial() != null) {
			Integer idPerfilImovelEspecial = sistemaParametro.getPerfilProgramaEspecial().getId();
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
			filtroImovelPerfil.adicionarParametro(new ParametroSimples(
					FiltroImovelPerfil.ID, idPerfilImovelEspecial));
			ImovelPerfil imovelPerfilProgramaEspecial = (ImovelPerfil) Util.retonarObjetoDeColecao(
					Fachada.getInstancia().pesquisar(
							filtroImovelPerfil, ImovelPerfil.class.getName()));
			
			helper.setPerfilImovel(imovelPerfilProgramaEspecial.getId().toString());
			helper.setNomePerfilImovel(imovelPerfilProgramaEspecial.getDescricao());
		}
			
		//Tipo de Relatório Analitico ou Sintetico
		if ( form.getTipo() != null && 
				!form.getTipo().equals("")){
			
			helper.setTipo(form.getTipo());
		}
	
		// opcao Totalizacao
		if(form.getOpcaoTotalizacao() != null && !form.getOpcaoTotalizacao().equals("-1")){
			helper.setOpcaoTotalizacao(form.getOpcaoTotalizacao());
		}
		
		// região Desenvolvimento
		if(form.getRegiaoDesenvolvimento() != null && !form.getRegiaoDesenvolvimento().equals("-1")){
			helper.setIdRegiaoDesenvolvimento(form.getRegiaoDesenvolvimento());
		}
		
		//Localidade
		if(form.getIdLocalidade() != null && !form.getIdLocalidade().equals("")){
			helper.setIdLocalidade(form.getIdLocalidade());
		}
		
		//Se = 0 Analitico  
		TarefaRelatorio relatorio = null;
		
		if( helper.getTipo().equals("0")){
			
			 relatorio = 
				new RelatorioImoveisProgramasEspeciaisAnalitico((Usuario)
						(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			 
		//Se = 1 Sintetico 	
		}else if( helper.getTipo().equals("1")){
			 relatorio = 
				new RelatorioImoveisProgramasEspeciaisSintetico((Usuario)
						(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			
		}
		
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		relatorio.addParametro("filtrarRelatorioImoveisProgramasEspeciaisHelper", helper);
		
		try {	
			
			retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
						httpServletResponse, actionMapping);
	
		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");
	
			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");
	
		} catch (RelatorioVazioException ex1) {
			throw new ActionServletException("atencao.nao.existe.dados_relatorio_anomesreferencia", null, "");
		}
			
			return retorno;
		}
	
}
