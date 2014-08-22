package gcom.gui.relatorio.cobranca;

import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.CategoriaTipo;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroCategoriaTipo;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.DocumentosReceberFaixaDiasVencidos;
import gcom.cobranca.FiltroDocumentosReceberFaixaDiasVencidos;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesInterfaceGSAN;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * [UC990] Gerar Relatório de Documentos a Receber
 *
 * @author Hugo Amorim
 * @date 22/02/2010
 *
 */
public class ExibirGerarRelatorioDocumentosAReceberAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
	
		RelatorioDocumentosAReceberForm form = (RelatorioDocumentosAReceberForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		if(Util.verificarNaoVazio(form.getIdLocalidade())){
			pesquisarLocalidade(form,sessao);			
		}
		
		// Verifica se faixa está no request, se sim coloca na coleção		
		if(sessao.getAttribute("faixaAdicionar")!=null){
			
			carregarFaixas(form,sessao);
	
		}
		
		if(form.getIndicadorInclusaoValorSemParcelas() == null) {
			form.setIndicadorInclusaoValorSemParcelas("2");
		}
		
		carregarComboboxEListbox(form,httpServletRequest);
		
		return actionMapping.findForward("exibirGerarRelatorioDocumentosAReceberAction");
	}
	
	private void carregarFaixas(RelatorioDocumentosAReceberForm form,
			HttpSession sessao) {
		
		FaixaHelper helper = 
			(FaixaHelper) sessao.getAttribute("faixaAdicionar");
		
		if(form.getColecaoFaixas()==null){
			form.setColecaoFaixas(new ArrayList<FaixaHelper>());
		}
		
		for (Iterator iterator = form.getColecaoFaixas().iterator(); iterator.hasNext();) {
			FaixaHelper faixaItera = (FaixaHelper) iterator.next();
			
			if(helper.verificarFaixa(faixaItera)){
				
				String[] parametros = {
						helper.getValorInicial().toString(),
						helper.getValorFinal().toString(),
						faixaItera.getDescricao()};
				
				throw new ActionServletException("atencao.faixa_ja_existe",parametros);
			}
			
		}
		
		form.getColecaoFaixas().add(helper);
		
		List colecaoFaixasParaOrdenar = (List) form.getColecaoFaixas();
		
	    Collections.sort(colecaoFaixasParaOrdenar,  
		        new Comparator() {  
		           public int compare(Object left, Object right) {  
		           FaixaHelper leftKey = (FaixaHelper) left;  
		           FaixaHelper rightKey = (FaixaHelper) right;  
	               return leftKey.getValorInicial().compareTo(rightKey.getValorInicial());  
	             }  
        }); 
	    
	    form.setColecaoFaixas(colecaoFaixasParaOrdenar);
		
		if(form.getColecaoFaixas().size()>0){
			form.setIcInformouFaixa("SIM");
		}else{
			form.setIcInformouFaixa("NAO");
		}
		
		sessao.removeAttribute("faixaAdicionar");
		
	}

	private void carregarComboboxEListbox(RelatorioDocumentosAReceberForm form,HttpServletRequest httpServletRequest) {
		carregarColecaoUnidadesNegocios(form);
		carregarColecaoCategoriasTipos(form);
		carregarColecaoCategorias(form);
		carregarColecaoPerfisImovel(form);
		carregarColecaoEsferasPoder(form);
		carregarColecaoOpcaoTotalizacao(form);
		carregarColecaoGerencias(form);
		carregarColecaoFaixas(form,httpServletRequest);
	}
	
	private void carregarColecaoFaixas(RelatorioDocumentosAReceberForm form,HttpServletRequest httpServletRequest) {
		FiltroDocumentosReceberFaixaDiasVencidos
			filtroFaixas = new FiltroDocumentosReceberFaixaDiasVencidos();
		
		filtroFaixas.setConsultaSemLimites(true);
		filtroFaixas.setCampoOrderBy(FiltroDocumentosReceberFaixaDiasVencidos.VALOR_MENOR_FAIXA);
		filtroFaixas.adicionarParametro(
				new ParametroSimples(
						FiltroDocumentosReceberFaixaDiasVencidos.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<DocumentosReceberFaixaDiasVencidos> colecaoFaixas = 
			this.getFachada().pesquisar(filtroFaixas, DocumentosReceberFaixaDiasVencidos.class.getName());


		if(form.getColecaoFaixas()==null){
			form.setColecaoFaixas(new ArrayList<FaixaHelper>());
		}
		
		if(form.getColecaoFaixas().isEmpty() && !colecaoFaixas.isEmpty()){
			
			httpServletRequest.setAttribute("icInformouFaixa", "SIM");
			
			for (Iterator iterator = colecaoFaixas.iterator(); iterator
					.hasNext();) {
				DocumentosReceberFaixaDiasVencidos documentosReceberFaixaDiasVencidos = 
					(DocumentosReceberFaixaDiasVencidos) iterator.next();
				
				FaixaHelper helper = new FaixaHelper(
						documentosReceberFaixaDiasVencidos.getDescricaoFaixa(),
						documentosReceberFaixaDiasVencidos.getValorInicialFaixa(),
						documentosReceberFaixaDiasVencidos.getValorFinalFaixa());
				
				form.getColecaoFaixas().add(helper);
				
			}
			
		}	
		
	}

	private void carregarColecaoGerencias(RelatorioDocumentosAReceberForm form) {
		
		
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		
		filtroGerenciaRegional.setConsultaSemLimites(true);
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		filtroGerenciaRegional.adicionarParametro(
				new ParametroSimples(FiltroGerenciaRegional.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<GerenciaRegional> colecaoGerenciaRegional = 
			this.getFachada().pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());


		if ( Util.isVazioOrNulo(colecaoGerenciaRegional)) {
			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_NAO_CADASTRADO, 
					"Gerência Regional");
		}
		
		form.setColecaoGerencias(colecaoGerenciaRegional);
		
	}

	private void carregarColecaoCategoriasTipos(
			RelatorioDocumentosAReceberForm form) {
		FiltroCategoriaTipo filtroCategoriaTipo = new FiltroCategoriaTipo();
		
		filtroCategoriaTipo.setConsultaSemLimites(true);
		filtroCategoriaTipo.setCampoOrderBy(FiltroCategoriaTipo.DESCRICAO);


		Collection<CategoriaTipo> colecaoCategoriaTipo = 
			this.getFachada().pesquisar(filtroCategoriaTipo, CategoriaTipo.class.getName());


		if ( Util.isVazioOrNulo(colecaoCategoriaTipo)) {
			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_NAO_CADASTRADO, 
					"Tipo Categoria");
		}
		
		form.setColecaoCategoriasTipo(colecaoCategoriaTipo);
		
	}

	private void carregarColecaoOpcaoTotalizacao(
			RelatorioDocumentosAReceberForm form) {
		
		Collection<OpcaoTotalizacaoHelper> 
			colecaoOpcoesTotalizacoes
				= new ArrayList<OpcaoTotalizacaoHelper>();
		
		String[] opcoes = {"Estado"
				,"Estado por Gerência Regional"
				,"Estado por Unidade de Negócio"
				,"Estado por Gerência Regional e por Localidade"
				,"Estado por Unidade de Negócio e por Localidade"
				,"Gerência Regional"
				,"Gerência Regional por Localidade"
				,"Unidade de Negócio"
				,"Unidade de Negócio por Localidade"
				,"Localidade"};
		
		for (int i = 0; i < opcoes.length; i++) {
			String opcao = opcoes[i];

			colecaoOpcoesTotalizacoes.add(new OpcaoTotalizacaoHelper(i+1, opcao));
			
		}
		
		form.setColecaoOpcoesTotalizacoes(colecaoOpcoesTotalizacoes);
		
	}

	/**
	 * Método consulta os perfis de imóvel ativos
	 * e seta essa coleção no FORM para que seja exibida na tela.
	 * 
	 * @author Marlon Patrick
	 * @since 07/10/2009
	 */
	private void carregarColecaoPerfisImovel(RelatorioDocumentosAReceberForm form){
		
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.setConsultaSemLimites(true);
		filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);		
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(
				FiltroImovelPerfil.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<ImovelPerfil> colecaoImoveisPerfis = this.getFachada().pesquisar(
				filtroImovelPerfil, ImovelPerfil.class.getName());

		if ( Util.isVazioOrNulo(colecaoImoveisPerfis)) {
			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_NAO_CADASTRADO, 
					ConstantesInterfaceGSAN.LABEL_GSAN_PERFIL_IMOVEL);
		}
		
		form.setColecaoPerfisImovel(colecaoImoveisPerfis);
	}


	/**
	 * Método consulta as Unidades de Negócio ativas
	 * e seta essa coleção no FORM para que seja exibida na tela.
	 * 
	 * @author Marlon Patrick
	 * @since 07/10/2009
	 */
	private void carregarColecaoUnidadesNegocios(RelatorioDocumentosAReceberForm form){
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		filtroUnidadeNegocio.setConsultaSemLimites(true);
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);		
		filtroUnidadeNegocio.adicionarParametro(
				new ParametroSimples(FiltroUnidadeNegocio.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));		

		Collection<UnidadeNegocio> colecaoUnidadeNegocio = 
			this.getFachada().pesquisar(filtroUnidadeNegocio,UnidadeNegocio.class.getName());

		if ( Util.isVazioOrNulo(colecaoUnidadeNegocio)) {
			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_NAO_CADASTRADO, 
					ConstantesInterfaceGSAN.LABEL_GSAN_UNIDADE_NEGOCIO);
		}
		
		form.setColecaoUnidades(colecaoUnidadeNegocio);
	}
	
	/**
	 * Método consulta as categorias ativas
	 * e seta essa coleção no FORM para que seja exibida na tela.
	 * 
	 * @author Marlon Patrick
	 * @since 07/10/2009
	 */
	private void carregarColecaoCategorias(RelatorioDocumentosAReceberForm form){
		
		FiltroCategoria filtroCategoria = new FiltroCategoria();
		
		filtroCategoria.setConsultaSemLimites(true);
		filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
		filtroCategoria.adicionarParametro(
				new ParametroSimples(FiltroCategoria.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<Categoria> colecaoCategoria = 
			this.getFachada().pesquisar(filtroCategoria, Categoria.class.getName());


		if ( Util.isVazioOrNulo(colecaoCategoria)) {
			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_NAO_CADASTRADO, 
					ConstantesInterfaceGSAN.LABEL_GSAN_CATEGORIA);
		}
		
		form.setColecaoCategorias(colecaoCategoria);
	}
	
	/**
	 * Método consulta as esferas do poder ativas
	 * e seta essa coleção no FORM para que seja exibida na tela.
	 * 
	 * @author Marlon Patrick
	 * @since 07/10/2009
	 */
	private void carregarColecaoEsferasPoder(RelatorioDocumentosAReceberForm form){
		
		FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();
		filtroEsferaPoder.setConsultaSemLimites(true);
		filtroEsferaPoder.setCampoOrderBy(FiltroEsferaPoder.DESCRICAO);
		filtroEsferaPoder.adicionarParametro(
				new ParametroSimples(FiltroEsferaPoder.INDICADOR_USO, 
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<EsferaPoder> colecaoEsferaPoder = 
			this.getFachada().pesquisar(filtroEsferaPoder,EsferaPoder.class.getName());

		if ( Util.isVazioOrNulo(colecaoEsferaPoder)) {
			throw new ActionServletException(ConstantesInterfaceGSAN.ATENCAO_NAO_CADASTRADO, 
					ConstantesInterfaceGSAN.LABEL_GSAN_ESFERA_PODER);
		}
		
		form.setColecaoEsferasPoder(colecaoEsferaPoder);
	}
	

	private void pesquisarLocalidade(RelatorioDocumentosAReceberForm form, HttpSession sessao){

		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,form.getIdLocalidade()));
		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO
				,ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<Localidade> colecaoLocalidade = 
			this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());
	
		if ( Util.isVazioOrNulo(colecaoLocalidade)) {
				form.setIdLocalidade("");
				form.setNomeLocalidade("Localidade Inexistente");
				sessao.removeAttribute("localidadeEncontrada");
				return;
		}
		
		Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);
		
		form.setIdLocalidade(localidade.getId().toString());
		form.setNomeLocalidade(localidade.getDescricao());	
		sessao.setAttribute("localidadeEncontrada","");
	}
}
