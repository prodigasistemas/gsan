package gcom.gui.relatorio.faturamento;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.faturamento.FiltrarRelatorioDevolucaoPagamentosDuplicidadeHelper;
import gcom.relatorio.faturamento.RelatorioDevolucaoPagamentosDuplicidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1129] Gerar Relatório Devolução dos Pagamentos em Duplicidade
 * 
 * @author Hugo Leonardo
 *
 * @date 10/03/2011
 */

public class GerarRelatorioDevolucaoPagamentosDuplicidadeAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		   
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		   
		// Form
		GerarRelatorioDevolucaoPagamentosDuplicidadeActionForm form = (GerarRelatorioDevolucaoPagamentosDuplicidadeActionForm) actionForm;
		
		FiltrarRelatorioDevolucaoPagamentosDuplicidadeHelper helper = new FiltrarRelatorioDevolucaoPagamentosDuplicidadeHelper();
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		boolean peloMenosUmParametroInformado = false;
		
		this.validarLocalidade(httpServletRequest, form);
		
		String periodoReferencia = "";
		
		String referenciaInicial = form.getMesAnoReferenciaInicial();
        String referenciaFinal = form.getMesAnoReferenciaFinal();
		
        if (referenciaInicial != null && !referenciaInicial.equals("") &&
                !Util.validarMesAno(referenciaInicial)) {
            
        	throw new ActionServletException("atencao.adicionar_debito_ano_mes_referencia_invalido",null, "Inicial");
        }
        
        if (referenciaFinal != null && !referenciaFinal.equals("") &&
                !Util.validarMesAno(referenciaFinal)) {
            
        	throw new ActionServletException("atencao.adicionar_debito_ano_mes_referencia_invalido",null, "Final");
        }
        
        SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
        
        int mesAnoFaturamento = sistemaParametro.getAnoMesFaturamento();
        
        referenciaInicial = Util.formatarMesAnoParaAnoMesSemBarra(referenciaInicial);
        referenciaFinal = Util.formatarMesAnoParaAnoMesSemBarra(referenciaFinal);
        
        int anoMesInicio = new Integer(referenciaInicial);
        int anoMesFim = new Integer(referenciaFinal);
        
        // [FS0002] - Validar datas do Período
        if(anoMesInicio > anoMesFim){
        	
        	throw new ActionServletException("atencao.mes_ano_inicial.maior.mes_ano_final");
        }
        
        if( anoMesInicio > mesAnoFaturamento){
        	
        	throw new ActionServletException("atencao.mes_ano_inicial.maior.mes_ano_faturamento");
        }
        
        if( anoMesFim > mesAnoFaturamento){
        	
        	throw new ActionServletException("atencao.mes_ano_final.maior.mes_ano_faturamento");
        }
        
        periodoReferencia = form.getMesAnoReferenciaInicial() + " a " + form.getMesAnoReferenciaFinal();
        
        helper.setAnoMesReferenciaInicial( referenciaInicial);
        helper.setAnoMesReferenciaFinal( referenciaFinal);
        peloMenosUmParametroInformado = true;
        
		// Gerencia
        String gerenciaDescricao = " -- ";
		if ( form.getIdGerencia() != null && 
				!form.getIdGerencia().equals("-1")) {
			
			helper.setIdGerencia(form.getIdGerencia());
			peloMenosUmParametroInformado = true;
			
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples( FiltroGerenciaRegional.ID, 
					form.getIdGerencia()));
			Collection colecaoGerenciaRegional = 
				this.getFachada().pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
			GerenciaRegional gerencia = (GerenciaRegional) Util.retonarObjetoDeColecao(colecaoGerenciaRegional);
			
			gerenciaDescricao = "" + gerencia.getId() + " - " + gerencia.getNome();
		}
		
		// Unidade Negócio
		String unidadeDescricao = " -- ";
		if ( form.getIdUnidadeNegocio() != null && 
				!form.getIdUnidadeNegocio().equals("-1")){
			
			helper.setIdUnidadeNegocio(form.getIdUnidadeNegocio());
			peloMenosUmParametroInformado = true;
			
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples( FiltroUnidadeNegocio.ID, 
					form.getIdUnidadeNegocio()));
			Collection colecaoUnidadeNegocio = 
				this.getFachada().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
			UnidadeNegocio unidadeNegocio = (UnidadeNegocio) Util.retonarObjetoDeColecao(colecaoUnidadeNegocio);
			
			unidadeDescricao = "" + unidadeNegocio.getId() + " - " + unidadeNegocio.getNome();
		}
		
		// Localidade
		if ( form.getIdLocalidade() != null && 
				!form.getIdLocalidade().equals("")){
			
			helper.setIdLocalidade(form.getIdLocalidade());
			peloMenosUmParametroInformado = true;
		}
		
		if ( form.getNomeLocalidade() != null && 
				!form.getNomeLocalidade().equals("")){
			
			helper.setNomeLocalidade(form.getNomeLocalidade());
			peloMenosUmParametroInformado = true;
		}
		
		// Perfil Imóvel
		String perfilDescricao = " -- ";
		if ( form.getIdPerfilImovel() != null && 
				!form.getIdPerfilImovel().equals("-1")){
			
			helper.setIdPerfilImovel(form.getIdPerfilImovel());
			peloMenosUmParametroInformado = true;
			
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
			filtroImovelPerfil.adicionarParametro(new ParametroSimples( FiltroImovelPerfil.ID, 
					form.getIdPerfilImovel()));
			Collection colecaoPerfilImovel = 
				this.getFachada().pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());
			ImovelPerfil imovelPerfil = (ImovelPerfil) Util.retonarObjetoDeColecao(colecaoPerfilImovel);
			
			perfilDescricao = "" + imovelPerfil.getId() + " - " + imovelPerfil.getDescricao();
		}
		
		// Imóvel Categoria
		String categoriaDescricao = " -- ";
		if ( form.getIdCategoriaImovel() != null && 
				!form.getIdCategoriaImovel().equals("-1")){
			
			helper.setIdCategoriaImovel(form.getIdCategoriaImovel());
			peloMenosUmParametroInformado = true;
			
			FiltroCategoria filtroCategoria = new FiltroCategoria();
			filtroCategoria.adicionarParametro(new ParametroSimples( FiltroCategoria.CODIGO, 
					form.getIdCategoriaImovel()));
			Collection colecaoCategoria = 
				this.getFachada().pesquisar(filtroCategoria, Categoria.class.getName());
			Categoria categoria = (Categoria) Util.retonarObjetoDeColecao(colecaoCategoria);
			
			categoriaDescricao = "" + categoria.getId() + " - " + categoria.getDescricao();
		}
		
		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		 
		TarefaRelatorio relatorio = new RelatorioDevolucaoPagamentosDuplicidade((Usuario)
				(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		relatorio.addParametro("filtrarRelatorioDevolucaoPagamentosDuplicidadeHelper", helper);
		
		relatorio.addParametro("gerenciaDescricao", gerenciaDescricao);
		relatorio.addParametro("unidadeDescricao", unidadeDescricao);
		relatorio.addParametro("perfilDescricao", perfilDescricao);
		relatorio.addParametro("categoriaDescricao", categoriaDescricao);
		relatorio.addParametro("periodoReferencia", periodoReferencia);
		
		try {	
			
			retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
						httpServletResponse, actionMapping);
	
		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");
	
			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");
	
		} catch (RelatorioVazioException ex1) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "");
		}
		
		sessao.removeAttribute("limpar");
			
		return retorno;
	}
	
	private void validarLocalidade(HttpServletRequest httpServletRequest,
			GerarRelatorioDevolucaoPagamentosDuplicidadeActionForm form){
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(
				FiltroLocalidade.ID, form.getIdLocalidade()));

		Collection<Localidade> localidadePesquisada = this.getFachada().pesquisar(
				filtroLocalidade, Localidade.class.getName());

		// Se nenhum usuário for encontrado a mensagem é enviada para a página
		if ( !Util.isVazioOrNulo(localidadePesquisada)) {
			Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(localidadePesquisada);
		
			if(form.getIdGerencia() != null && !form.getIdGerencia().equals("-1") 
					&& localidade.getGerenciaRegional().getId().compareTo(new Integer(form.getIdGerencia())) != 0){
				
				FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
				
				filtroGerenciaRegional.adicionarParametro(new ParametroSimples( FiltroGerenciaRegional.ID, 
						form.getIdGerencia()));		
	
				Collection colecaoGerencia = 
					this.getFachada().pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());
				
				GerenciaRegional gerenciaRegional = (GerenciaRegional)Util.retonarObjetoDeColecao(colecaoGerencia);
				
				form.setIdLocalidade("");
				form.setNomeLocalidade("");
				
				throw new ActionServletException("atencao.localidade_nao_percente_gerencia_regional", null, gerenciaRegional.getNome());
			}
			
			if(form.getIdUnidadeNegocio() != null && !form.getIdUnidadeNegocio().equals("-1") 
					&& localidade.getUnidadeNegocio().getId().compareTo(new Integer(form.getIdUnidadeNegocio())) != 0){
				
				FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
				
				filtroUnidadeNegocio.adicionarParametro(
						new ParametroSimples(FiltroUnidadeNegocio.ID, form.getIdUnidadeNegocio()));		
	
				Collection colecaoUnidadeNegocio = 
					this.getFachada().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());
				
				UnidadeNegocio unidadeNegocio = (UnidadeNegocio)Util.retonarObjetoDeColecao(colecaoUnidadeNegocio);
				
				form.setIdLocalidade("");
				form.setNomeLocalidade("");
				
				throw new ActionServletException("atencao.localidade_nao_percente_unidade_negocio", null, unidadeNegocio.getNome());
			}
		}
	}
}
