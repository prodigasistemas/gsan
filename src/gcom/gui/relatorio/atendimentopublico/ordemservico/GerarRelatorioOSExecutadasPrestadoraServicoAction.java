package gcom.gui.relatorio.atendimentopublico.ordemservico;

import gcom.cadastro.geografico.FiltroMicrorregiao;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.FiltroRegiao;
import gcom.cadastro.geografico.Microrregiao;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.geografico.Regiao;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.ordemservico.RelatorioOSExecutadasPrestadoraServico;
import gcom.relatorio.atendimentopublico.ordemservico.RelatorioOSExecutadasPrestadoraServicoSintetico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * [UC1163]  Gerar  Relatório de OS executadas por Prestadora de Serviço
 * 
 * @author Vivianne Sousa
 *
 * @date 12/04/2011
 */
public class GerarRelatorioOSExecutadasPrestadoraServicoAction extends ExibidorProcessamentoTarefaRelatorio {
	
	@Override
	public ActionForward execute(
			ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws Exception {

		ActionForward retorno = null;
		
		GerarRelatorioOSExecutadasPrestadoraServicoActionForm form = 
			(GerarRelatorioOSExecutadasPrestadoraServicoActionForm) actionForm;
		

		Integer idGerencia = null; 
		Integer idUnidade = null; 
		Integer idLocalidade = null; 
		
		Date dataEncerramentoInicial = Util.converteStringParaDate(form.getPeriodoEncerramentoInicial());
		Date dataEncerramentoFinal = Util.converteStringParaDate(form.getPeriodoEncerramentoFinal());
		int qtdeDias = Util.obterQuantidadeDiasEntreDuasDatas(dataEncerramentoInicial,dataEncerramentoFinal);
		
		if(qtdeDias > 31){
			throw new ActionServletException("atencao.periodo_informado_superior");
		}
		
		if(form.getOpcaoRelatorio() == null){
			throw new ActionServletException("atencao.campo.informado", null, "Opção do Relatório");
		}
		String opcaoRelatorio = form.getOpcaoRelatorio();
		
		String filtroGerencia = "";
		String filtroUnidade = "";
		String filtroLocalidade = "";
		
		if(form.getIdGerencia() != null && !form.getIdGerencia().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
			idGerencia = new Integer(form.getIdGerencia());
			filtroGerencia = pesquisarGerenciaRegional(idGerencia);
		}
		if(form.getIdUnidadeNegocio() != null && !form.getIdUnidadeNegocio().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
			idUnidade = new Integer(form.getIdUnidadeNegocio());
			filtroUnidade = pesquisarUnidadeNegocio(idUnidade);
		}
		if(form.getCodigoLocalidade() != null && !form.getCodigoLocalidade().equals("")){
			idLocalidade = new Integer(form.getCodigoLocalidade());
			filtroLocalidade = pesquisarLocalidade(idLocalidade);
		}
		
		String filtroRegiao = pesquisarRegiao(form.getColecaoRegiao());
		String filtroMicroregiao = pesquisarMicroregiao(form.getColecaoMicrorregiao());
		String filtroMunicipio = pesquisarMunicipio(form.getColecaoMunicipio());
		
		
		if(opcaoRelatorio.equals("1")){
			//Analítico  
			
			String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}
			RelatorioOSExecutadasPrestadoraServico relatorio = new RelatorioOSExecutadasPrestadoraServico
			((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
			relatorio.addParametro("dataEncerramentoInicial", dataEncerramentoInicial);
			relatorio.addParametro("dataEncerramentoFinal", dataEncerramentoFinal);
			relatorio.addParametro("idGerencia", idGerencia);
			relatorio.addParametro("idUnidade", idUnidade);
			relatorio.addParametro("idLocalidade", idLocalidade);
			relatorio.addParametro("idsRegiao", form.getColecaoRegiao());
			relatorio.addParametro("idsMicroregiao", form.getColecaoMicrorregiao());
			relatorio.addParametro("idsMunicipio", form.getColecaoMunicipio());
			
			relatorio.addParametro("filtroGerencia", filtroGerencia);
			relatorio.addParametro("filtroUnidade", filtroUnidade);
			relatorio.addParametro("filtroLocalidade", filtroLocalidade);
			relatorio.addParametro("filtroRegiao", filtroRegiao);
			relatorio.addParametro("filtroMicroregiao", filtroMicroregiao);
			relatorio.addParametro("filtroMunicipio", filtroMunicipio);
			
			relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
			
			try {
				retorno = processarExibicaoRelatorio(relatorio,
				tipoRelatorio, httpServletRequest, httpServletResponse,actionMapping);

			} catch (RelatorioVazioException ex) {
				reportarErros(httpServletRequest, "atencao.relatorio.vazio");
				retorno = actionMapping.findForward("telaAtencaoPopup");
			}
		}else if(opcaoRelatorio.equals("2")){
			//Resumido
			
			String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}
			RelatorioOSExecutadasPrestadoraServicoSintetico relatorio = new RelatorioOSExecutadasPrestadoraServicoSintetico
			((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
			relatorio.addParametro("dataEncerramentoInicial", dataEncerramentoInicial);
			relatorio.addParametro("dataEncerramentoFinal", dataEncerramentoFinal);
			relatorio.addParametro("idGerencia", idGerencia);
			relatorio.addParametro("idUnidade", idUnidade);
			relatorio.addParametro("idLocalidade", idLocalidade);
			relatorio.addParametro("idsRegiao", form.getColecaoRegiao());
			relatorio.addParametro("idsMicroregiao", form.getColecaoMicrorregiao());
			relatorio.addParametro("idsMunicipio", form.getColecaoMunicipio());
			
			relatorio.addParametro("filtroGerencia", filtroGerencia);
			relatorio.addParametro("filtroUnidade", filtroUnidade);
			relatorio.addParametro("filtroLocalidade", filtroLocalidade);
			relatorio.addParametro("filtroRegiao", filtroRegiao);
			relatorio.addParametro("filtroMicroregiao", filtroMicroregiao);
			relatorio.addParametro("filtroMunicipio", filtroMunicipio);
			
			relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
			
			try {
				retorno = processarExibicaoRelatorio(relatorio,
				tipoRelatorio, httpServletRequest, httpServletResponse,actionMapping);

			} catch (RelatorioVazioException ex) {
				reportarErros(httpServletRequest, "atencao.relatorio.vazio");
				retorno = actionMapping.findForward("telaAtencaoPopup");
			}
			
		}else if(opcaoRelatorio.equals("3")){
			//TXT Analítico
			
			StringBuilder headerTxt = new StringBuilder();
			String referencia = Util.formatarData(dataEncerramentoInicial) + " a " + Util.formatarData(dataEncerramentoFinal);
			headerTxt.append(referencia + ";");
			headerTxt.append("PONTO FORTE;");
			
			headerTxt.append(filtroGerencia + ";");
			headerTxt.append(filtroUnidade + ";");
			headerTxt.append(filtroLocalidade + ";");
			headerTxt.append(filtroRegiao + ";");
			headerTxt.append(filtroMicroregiao + ";");
			headerTxt.append(filtroMunicipio + ";");
			
			Fachada.getInstancia().gerarTxtOSExecutadasPrestadoraServico(dataEncerramentoInicial,
			dataEncerramentoFinal,idGerencia,idUnidade,idLocalidade,form.getColecaoRegiao(),
			form.getColecaoMicrorregiao(), form.getColecaoMunicipio(), headerTxt.toString());
			
			retorno = actionMapping.findForward("telaSucesso");
			
			montarPaginaSucesso(httpServletRequest, "Txt Analítico gerado com sucesso.",
					"Gerar outro Relatório de OS Executadas por Prestadora de Serviço ",
					"exibirGerarRelatorioOSExecutadasPrestadoraServicoAction.do?menu=sim");
			
		}
		
		return retorno;
	}

	private String pesquisarGerenciaRegional(Integer idGerencia){
		
		String descricao = "";
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		filtroGerenciaRegional.adicionarParametro(
				new ParametroSimples(FiltroQuadra.ID,idGerencia));

		Collection colecaoGerenciaRegional = 
			this.getFachada().pesquisar(filtroGerenciaRegional,GerenciaRegional.class.getName());

		if(colecaoGerenciaRegional != null && !colecaoGerenciaRegional.isEmpty()){
			GerenciaRegional gerencia = (GerenciaRegional)Util.retonarObjetoDeColecao(colecaoGerenciaRegional);
			descricao = gerencia.getNome();
		}
		return descricao;
	}
	private String pesquisarUnidadeNegocio(Integer idUnidadeNegocio){
		
		String descricao = "";
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		filtroUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroUnidadeNegocio.ID,idUnidadeNegocio));		

		Collection colecaoUnidadeNegocio = 
			this.getFachada().pesquisar(filtroUnidadeNegocio,UnidadeNegocio.class.getName());

		if(colecaoUnidadeNegocio != null && !colecaoUnidadeNegocio.isEmpty()){
			UnidadeNegocio unidadeNegocio = (UnidadeNegocio)Util.retonarObjetoDeColecao(colecaoUnidadeNegocio);
			descricao = unidadeNegocio.getNome();
		}
		return descricao;
	}
	private String pesquisarLocalidade(Integer idLocalidade) {
		String descricao = "";
		Fachada fachada = Fachada.getInstancia();
		// Pesquisa a localidade na base
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, idLocalidade));

		Collection<Localidade> localidadePesquisada = fachada.pesquisar(
				filtroLocalidade, Localidade.class.getName());

		// obtem o imovel pesquisado
		if (localidadePesquisada != null && !localidadePesquisada.isEmpty()) {
			Localidade localidade = localidadePesquisada.iterator().next();
			descricao =  localidade.getDescricao();
		}
		return descricao;
	}
	private String pesquisarRegiao(String[] idsRegiao){
		
		String descricao = "";
		
		if(idsRegiao != null && idsRegiao.length > 0  && !((String)idsRegiao[0]).equals("-1")){
			Collection colecaoIds = new ArrayList();
			for (int i = 0; i < idsRegiao.length; i++) {
				colecaoIds.add((String)idsRegiao[i]);
			}
			
			FiltroRegiao filtroRegiao = new FiltroRegiao();

			filtroRegiao.adicionarParametro(new ParametroSimplesIn(FiltroRegiao.ID,colecaoIds));

			Collection<Regiao> colecaoRegiao = this.getFachada().pesquisar(filtroRegiao,Regiao.class.getName());

			if (colecaoRegiao == null || colecaoRegiao.isEmpty()) {
				
				Iterator iterator = colecaoRegiao.iterator();
				int cont = 0;
				while (iterator.hasNext()) {
					Regiao regiao = (Regiao) iterator.next();
					cont++;
					
					if(cont == 5){
						descricao = descricao + "...";
						break;
					}
					if(cont == 1){
						descricao = regiao.getNome();
					}else{
						descricao = descricao + "," + regiao.getNome() ;
					}
					
				}
				
			}
		}
		return descricao;
	}
	private String pesquisarMicroregiao(String[] idsMicrorregiao){
		
		String descricao = "";
		
		if(idsMicrorregiao != null && idsMicrorregiao.length > 0 && !((String)idsMicrorregiao[0]).equals("-1")){
			Collection colecaoIds = new ArrayList();
			for (int i = 0; i < idsMicrorregiao.length; i++) {
				colecaoIds.add((String)idsMicrorregiao[i]);
			}
			
			FiltroMicrorregiao filtroMicrorregiao = new FiltroMicrorregiao();

			filtroMicrorregiao.adicionarParametro(new ParametroSimplesIn(FiltroMicrorregiao.ID,colecaoIds));

			Collection<Microrregiao> colecaoMicrorregiao = this.getFachada().pesquisar(filtroMicrorregiao,Microrregiao.class.getName());

			if (colecaoMicrorregiao == null || colecaoMicrorregiao.isEmpty()) {
				
				Iterator iterator = colecaoMicrorregiao.iterator();
				int cont = 0;
				while (iterator.hasNext()) {
					Microrregiao microrregiao = (Microrregiao) iterator.next();
					cont++;
					
					if(cont == 5){
						descricao = descricao + "...";
						break;
					}
					if(cont == 1){
						descricao = microrregiao.getNome();
					}else{
						descricao = descricao + "," + microrregiao.getNome() ;
					}
					
				}
				
			}
		}
		return descricao;
	}
	
	private String pesquisarMunicipio(String[] idsMunicipio){
		
		String descricao = "";
		
		if(idsMunicipio != null && idsMunicipio.length > 0 && !((String)idsMunicipio[0]).equals("-1")){
			Collection colecaoIds = new ArrayList();
			for (int i = 0; i < idsMunicipio.length; i++) {
				colecaoIds.add((String)idsMunicipio[i]);
			}
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimplesIn(FiltroMunicipio.ID,colecaoIds));
			
			Collection<Municipio> colecaoMunicipio = this.getFachada().pesquisar(filtroMunicipio,Municipio.class.getName());

			if (colecaoMunicipio == null || colecaoMunicipio.isEmpty()) {
				
				Iterator iterator = colecaoMunicipio.iterator();
				int cont = 0;
				while (iterator.hasNext()) {
					Municipio municipio = (Municipio) iterator.next();
					cont++;
					
					if(cont == 5){
						descricao = descricao + "...";
						break;
					}
					if(cont == 1){
						descricao = municipio.getNome();
					}else{
						descricao = descricao + "," + municipio.getNome() ;
					}
					
				}
				
			}
		}
		return descricao;
	}
}
