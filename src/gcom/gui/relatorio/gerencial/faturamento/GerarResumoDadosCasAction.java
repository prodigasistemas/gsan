package gcom.gui.relatorio.gerencial.faturamento;

import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.gerencial.faturamento.bean.FiltrarResumoDadosCasHelper;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.gerencial.faturamento.RelatorioResumoDadosCas;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC01017] Gerar Resumo com Dados Para o CAS
 * @author Daniel Alves
 * @data 30/04/2010
 *
 */
public class GerarResumoDadosCasAction extends ExibidorProcessamentoTarefaRelatorio{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		
		// Form
		ExibirGerarResumoDadosCasActionForm form = 
			(ExibirGerarResumoDadosCasActionForm) actionForm;
		
		FiltrarResumoDadosCasHelper filtro = 
			new FiltrarResumoDadosCasHelper();
		
		// Mês/Ano do Faturamento
		Integer anoMes = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoFaturamento());
		
		SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
		
		if(sistemaParametro.getAnoMesFaturamento().intValue() < anoMes.intValue()){
			throw new ActionServletException("atencao.mes_ano.faturamento.inferior", 
				null,""+ Util.formatarMesAnoReferencia(sistemaParametro.getAnoMesFaturamento()) );
		}
		
		//this.getFachada().validarDadosUnResumoParaResumoDadosCas(anoMes);
		
		filtro.setAnoMesReferencia(anoMes);

		// Opção de Totalização
		int opcaoTotalizacao = Integer.parseInt(form.getOpcaoTotalizacao());
		filtro.setOpcaoTotalizacao(opcaoTotalizacao);
		
		// Gerenci Regional
		if (opcaoTotalizacao == 6 && 
			form.getGerenciaRegional() != null && 
			!form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {

			filtro.setGerenciaRegional(Integer.parseInt(form.getGerenciaRegional()));
		}

		
		// Unidade de Negocio
		if (opcaoTotalizacao == 10 && 
			form.getUnidadeNegocio() != null && 
			!form.getUnidadeNegocio().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {

			filtro.setUnidadeNegocio(Integer.parseInt(form.getUnidadeNegocio()));
		}
			
		// Localidade		
		if (opcaoTotalizacao == 17 && 
			form.getLocalidade() != null && 
			!form.getLocalidade().equals("") ) {
				
			Collection<Integer> colecao = new ArrayList<Integer>();
			
			//verifica se existe a localidade escolhida
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(
					new ParametroSimples(FiltroLocalidade.ID, 
					form.getLocalidade()));
			
			// Recupera Localidade
			Collection colecaoLocalidade = 
				this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
		
			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				colecao.add(new Integer(form.getLocalidade()));
						
				filtro.setLocalidade(Integer.parseInt(form.getLocalidade()));
			}else{
				throw new ActionServletException(
    					"atencao.pesquisa_inexistente", null,"Localidade");
			}
			
		}
		
		//Município
		if(opcaoTotalizacao == 20){
			String municipio = form.getMunicipio();
			if(municipio != null && !municipio.equals("")){
				FiltroMunicipio filtroMunicipio = new FiltroMunicipio();
				filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, municipio));
				Collection colecaoMunicipio = 
							this.getFachada().pesquisar(filtroMunicipio, Municipio.class.getName());
				if(colecaoMunicipio != null && !colecaoMunicipio.isEmpty()){
					filtro.setMunicipio(Integer.valueOf(municipio));
				}else{
					throw new ActionServletException("atencao.pesquisa.municipio_inexistente");
				}
			}
		}
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioResumoDadosCas relatorio = 
			new RelatorioResumoDadosCas(this.getUsuarioLogado(httpServletRequest));
		
		relatorio.addParametro("filtrarResumoDadosCasHelper", filtro);
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