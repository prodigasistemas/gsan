package gcom.gui.relatorio.cadastro.imovel;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.FiltroCobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisFaturasAtrasoHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisFaturasAtrasoAgrupadasCliente;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisFaturasAtrasoAgrupadasLocalizacao;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisFaturasAtrasoDescritasCliente;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisFaturasAtrasoDescritasLocalizacao;
import gcom.seguranca.acesso.usuario.Usuario;
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
 * [UC00725] Gerar Relatório de Imóveis por Situação da Ligação de Água
 * 
 * @author Rafael Pinto
 *
 * @date 28/11/2007
 */

public class GerarRelatorioImoveisFaturasAtrasoAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		GerarRelatorioImoveisFaturasAtrasoActionForm form = 
			(GerarRelatorioImoveisFaturasAtrasoActionForm) actionForm;
		
		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro = criarFiltro(form);
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		TarefaRelatorio relatorio = null;
		
		relatorio = definirTipoRelatorio(form, getUsuarioLogado(httpServletRequest));
		
		relatorio.addParametro("filtrarRelatorioImoveisFaturasAtrasoHelper", filtro);
		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		configurarParametrosRelatorio(relatorio, form);

		return 
			processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
				httpServletResponse, actionMapping);
	}

	/**
	 * Esse método instancia e retorna o tipo de relatorio correto de acordo com os filtros
	 * informados pelo usuário.
	 *
	 *@since 02/09/2009
	 *@author Marlon Patrick
	 */
	private TarefaRelatorio definirTipoRelatorio(GerarRelatorioImoveisFaturasAtrasoActionForm form,
			Usuario usuarioLogado) {
		
		boolean isCriterioFiltroLocalidade = form.getCriterioFiltro().equals("1");
		boolean isConsultaAgrupada = form.getTipo().equals("A");

		if(isConsultaAgrupada){
			
			if(isCriterioFiltroLocalidade){
				return
					new RelatorioImoveisFaturasAtrasoAgrupadasLocalizacao(usuarioLogado);				
			}
			
			return new RelatorioImoveisFaturasAtrasoAgrupadasCliente(usuarioLogado);			
		}
		
		if(isCriterioFiltroLocalidade){
			return 
				new RelatorioImoveisFaturasAtrasoDescritasLocalizacao(usuarioLogado);
		}
		
		return new RelatorioImoveisFaturasAtrasoDescritasCliente(usuarioLogado);

	}

	/**
	 * Este método cria e configura o filtro necessário a geração do relatório.
	 * 
	 *
	 *@since 25/08/2009
	 *@author Marlon Patrick
	 */
	private FiltrarRelatorioImoveisFaturasAtrasoHelper criarFiltro(
			GerarRelatorioImoveisFaturasAtrasoActionForm form) {
		
		FiltrarRelatorioImoveisFaturasAtrasoHelper filtro = 
			new FiltrarRelatorioImoveisFaturasAtrasoHelper();

		if (Util.verificarNaoVazio(form.getCriterioFiltro())) {			
			filtro.setCriterioFiltro(new Integer(form.getCriterioFiltro()));
		}

		if (Util.verificarNaoVazio(form.getGerenciaRegional())
				&& !form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			filtro.setGerenciaRegional(new Integer(form.getGerenciaRegional()));
		}
		
		if (Util.verificarNaoVazio(form.getUnidadeNegocio())
				&& !form.getUnidadeNegocio().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			filtro.setUnidadeNegocio(new Integer(form.getUnidadeNegocio()));
		}
			
		if (Util.verificarNaoVazio(form.getLocalidadeInicial())) {
			filtro.setLocalidadeInicial(new Integer(form.getLocalidadeInicial()));
		}
		
		if (Util.verificarNaoVazio(form.getSetorComercialInicial())) {
			filtro.setSetorComercialInicial(new Integer(form.getSetorComercialInicial()));
		}

		if (Util.verificarNaoVazio(form.getRotaInicial())) {
			filtro.setRotaInicial(new Short(form.getRotaInicial()));
		}

		if (Util.verificarNaoVazio(form.getSequencialRotaInicial())) {
			filtro.setSequencialRotalInicial(new Integer(form.getSequencialRotaInicial()));
		}

		if (Util.verificarNaoVazio(form.getLocalidadeFinal())) {
			filtro.setLocalidadeFinal(new Integer(form.getLocalidadeFinal()));
		}

		if (Util.verificarNaoVazio(form.getSetorComercialFinal())) {
			filtro.setSetorComercialFinal(new Integer(form.getSetorComercialFinal()));
		}
		
		if (Util.verificarNaoVazio(form.getRotaFinal())) {
			filtro.setRotaFinal(new Short(form.getRotaFinal()));
		}
		
		if (Util.verificarNaoVazio(form.getSequencialRotaFinal())) {
			filtro.setSequencialRotalFinal(new Integer(form.getSequencialRotaFinal()));
		}
		
		if (Util.verificarNaoVazio(form.getCodigoCliente())) {
			filtro.setCliente(new Integer(form.getCodigoCliente()));
		}

		if (Util.verificarNaoVazio(form.getCodigoClienteSuperior())) {
			filtro.setClienteSuperior(new Integer(form.getCodigoClienteSuperior()));
		}

		if (Util.verificarNaoVazio(form.getResponsavel())) {
			filtro.setResponsavel(new Integer(form.getResponsavel()));
		}

		if (Util.verificarNaoVazio(form.getTipoRelacao())
				&& !form.getTipoRelacao().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			filtro.setTipoRelacao(new Integer(form.getTipoRelacao()));
		}
		
		if (Util.verificarNaoVazio(form.getHidrometro()) && !form.getHidrometro().equals("0")){
			if(form.getHidrometro().equals("1")){
				filtro.setHidrometro("S");
			}else if(form.getHidrometro().equals("2")){
				filtro.setHidrometro("N");
			}
		}

		if ( !Util.isVazioOrNulo(form.getSituacaoLigacaoAgua())) {
			
			Collection<Integer> colecao = new ArrayList<Integer>();
			
			String[] array = form.getSituacaoLigacaoAgua();
			
			for(String situacao: array){
				if( !situacao.equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
					colecao.add(new Integer(situacao));					
				}
			}
			if(!colecao.isEmpty()){
				filtro.setSituacaoLigacaoAgua(colecao);
			}			
		}
		
		if ( !Util.isVazioOrNulo(form.getCategorias())) {
			
			Collection<Integer> colecao = new ArrayList<Integer>();
			
			String[] array = form.getCategorias();

			for(String categoria: array){
				if( !categoria.equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
					colecao.add(new Integer(categoria));					
				}
			}
			if(!colecao.isEmpty()){
				filtro.setCategorias(colecao);
			}
		}
		
		if ( !Util.isVazioOrNulo(form.getPerfilImovel())) {
			
			Collection<Integer> colecao = new ArrayList<Integer>();
			
			String[] array = form.getPerfilImovel();

			for(String perfilImovel: array){
				if( !perfilImovel.equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
					colecao.add(new Integer(perfilImovel));					
				}
			}
			if(!colecao.isEmpty()){
				filtro.setPerfisImovel(colecao);
			}
		}
		
		if (Util.verificarNaoVazio(form.getSituacaoCobranca())
				&& !form.getSituacaoCobranca().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			filtro.setSituacaoCobranca(new Integer(form.getSituacaoCobranca()));
		}

		if (Util.verificarNaoVazio(form.getQuantidadeFaturasAtrasoInicial())) {
			filtro.setQuantidadeFaturasAtrasoInicial(new Integer(form.getQuantidadeFaturasAtrasoInicial()));
		}
		
		if ( Util.verificarNaoVazio(form.getQuantidadeFaturasAtrasoFinal())) {
			filtro.setQuantidadeFaturasAtrasoFinal(new Integer(form.getQuantidadeFaturasAtrasoFinal()));
		}
		
		if (Util.verificarNaoVazio(form.getValorFaturasAtrasoInicial())) {
			filtro.setValorFaturasAtrasoInicial(new Float(form.getValorFaturasAtrasoInicial()));
		}
		
		if (Util.verificarNaoVazio(form.getValorFaturasAtrasoFinal())) {
			filtro.setValorFaturasAtrasoFinal(new Float(form.getValorFaturasAtrasoFinal()));
		}

		if (Util.verificarNaoVazio(form.getReferenciaFaturasAtrasoInicial())) {
			filtro.setReferenciaFaturasAtrasoInicial( Util.formatarMesAnoComBarraParaAnoMes( form.getReferenciaFaturasAtrasoInicial() ) );
		}
		
		if (Util.verificarNaoVazio(form.getReferenciaFaturasAtrasoFinal())) {
			filtro.setReferenciaFaturasAtrasoFinal( Util.formatarMesAnoComBarraParaAnoMes( form.getReferenciaFaturasAtrasoFinal() ) );
		}	
		
		if( !Util.isVazioOrNulo(form.getEsferaPoder())){
			Collection<Integer> colecao = new ArrayList<Integer>();
			
			String[] array = form.getEsferaPoder();

			for(String esferaPoder : array){
				if( !esferaPoder.equals(String.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))){
					colecao.add(new Integer(esferaPoder));					
				}
			}
			if(!colecao.isEmpty()){
				filtro.setEsferaPoder(colecao);
			}
		}
		return filtro;
	}
	
	/**
	 * Esse método adiciona os parametros no objeto TarefaRelatorio
	 * para que os mesmos sejam exibidos quando o relatório for gerado.
	 *
	 *@since 25/08/2009
	 *@author Marlon Patrick
	 */
	private void configurarParametrosRelatorio(TarefaRelatorio relatorio, GerarRelatorioImoveisFaturasAtrasoActionForm form){
		
		if(form.getGerenciaRegional() != null && !form.getGerenciaRegional().equalsIgnoreCase(ConstantesSistema.NUMERO_NAO_INFORMADO + "")){
			relatorio.addParametro("gerRegionalFiltro", form.getGerenciaRegional());
		}
		if(form.getUnidadeNegocio() != null && !form.getUnidadeNegocio().equalsIgnoreCase(ConstantesSistema.NUMERO_NAO_INFORMADO + "")){
			relatorio.addParametro("uniNegocioFiltro", form.getUnidadeNegocio());
		}
		if(form.getLocalidadeInicial() != null && !form.getLocalidadeInicial().equals("")){
			relatorio.addParametro("localidadeFiltro", form.getLocalidadeInicial() + " a " + form.getLocalidadeFinal());
		}
		if(form.getSetorComercialInicial() != null && !form.getSetorComercialInicial().equals("")){
			relatorio.addParametro("setorFiltro", form.getSetorComercialInicial() + " a " + form.getSetorComercialFinal());
		}
		if(form.getRotaInicial() != null && !form.getRotaInicial().equals("")){
			relatorio.addParametro("rotaFiltro", form.getRotaInicial() + " a " + form.getRotaFinal());
		}
		if(form.getSequencialRotaInicial() != null && !form.getSequencialRotaInicial().equals("")){
			relatorio.addParametro("seqRotaFiltro", form.getSequencialRotaInicial() + " a " + form.getSequencialRotaFinal());
		}
		if(form.getValorFaturasAtrasoInicial() != null && !form.getValorFaturasAtrasoInicial().equals("")){
			relatorio.addParametro("valorFaturasFiltro", form.getValorFaturasAtrasoInicial() + " a " + form.getValorFaturasAtrasoFinal());
		}
		if(form.getQuantidadeFaturasAtrasoInicial() != null && !form.getQuantidadeFaturasAtrasoInicial().equals("")){
			relatorio.addParametro("qtdFaturasFiltro", form.getQuantidadeFaturasAtrasoInicial() + " a " + form.getQuantidadeFaturasAtrasoFinal());
		}
		if(form.getReferenciaFaturasAtrasoInicial() != null && !form.getReferenciaFaturasAtrasoInicial().equals("")){
			relatorio.addParametro("refFaturasFiltro", form.getReferenciaFaturasAtrasoInicial() + " a " + form.getReferenciaFaturasAtrasoFinal());
		}

		if (Util.verificarNaoVazio(form.getCodigoClienteSuperior())) {
			if(Util.verificarNaoVazio(form.getNomeClienteSuperior())){
				relatorio.addParametro("clienteSuperiorFiltro",form.getCodigoClienteSuperior() + " - " + form.getNomeClienteSuperior());
			}else{
				Cliente cliente = Fachada.getInstancia().pesquisarClienteDigitado(new Integer(form.getCodigoClienteSuperior()));
				relatorio.addParametro("clienteSuperiorFiltro",cliente.getId() + " - " + cliente.getNome());
			}
		}
		
		if (Util.verificarNaoVazio(form.getCodigoCliente())) {						
			
			if(Util.verificarNaoVazio(form.getNomeCliente())){
				relatorio.addParametro("clienteFiltro",form.getCodigoCliente() + " - " + form.getNomeCliente());
			}else{
				Cliente cliente = Fachada.getInstancia().consultarCliente(new Integer(form.getCodigoCliente()));
				relatorio.addParametro("clienteFiltro",cliente.getId() + " - " + cliente.getNome());
			}

			
			if (Util.verificarNaoVazio(form.getTipoRelacao())
					&& !form.getTipoRelacao().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
				
				if(form.getTipoRelacao().equals(ClienteRelacaoTipo.USUARIO.toString())){
					relatorio.addParametro("tipoRelacaoFiltro",ClienteRelacaoTipo.USUARIO.toString());
				
				}else if(form.getTipoRelacao().equals(ClienteRelacaoTipo.PROPRIETARIO.toString())){
					relatorio.addParametro("tipoRelacaoFiltro",ClienteRelacaoTipo.PROPRIETARIO.toString());
					
				}else if(form.getTipoRelacao().equals(ClienteRelacaoTipo.RESPONSAVEL.toString())){
					relatorio.addParametro("tipoRelacaoFiltro",ClienteRelacaoTipo.RESPONSAVEL.toString());
				}
			}

			if (Util.verificarNaoVazio(form.getResponsavel())) {
				if(form.getResponsavel().equals("0")){
					relatorio.addParametro("responsavelFiltro","Indicado na Conta");				
				
				}else if(form.getResponsavel().equals("1")){
					relatorio.addParametro("responsavelFiltro","Atual do Imóvel");				
				
				}else if(form.getResponsavel().equals("2")){
					relatorio.addParametro("responsavelFiltro","Todos");				
				}
			}

		}
		
		if(Util.verificarNaoVazio(form.getHidrometro())) {
			if(form.getHidrometro().equals("0")){
				relatorio.addParametro("hidrometro", "TODOS");
			}else if(form.getHidrometro().equals("1")){
				relatorio.addParametro("hidrometro", "Com Hidrômetro");
			}else if(form.getHidrometro().equals("2")){
				relatorio.addParametro("hidrometro", "Sem Hidrômetro");
			}
		}

		if (Util.verificarNaoVazio(form.getSituacaoCobranca())
				&& !form.getSituacaoCobranca().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			FiltroCobrancaSituacao filtroSituacaoCobranca = new FiltroCobrancaSituacao();
			filtroSituacaoCobranca.adicionarParametro(
					new ParametroSimples(FiltroCobrancaSituacao.ID,form.getSituacaoCobranca()));

			Collection<CobrancaSituacao> colecaoSituacoesCobranca = 
				this.getFachada().pesquisar(filtroSituacaoCobranca, CobrancaSituacao.class.getName());

			relatorio.addParametro("situacaoCobrancaFiltro",colecaoSituacoesCobranca.iterator().next().getDescricao());
		}

		if( !Util.isVazioOrNulo(form.getEsferaPoder())){
			String esfera = "";
			String[] array = form.getEsferaPoder();
			
			for(String esferaTemp : array){
				if (!esferaTemp.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
					FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();
					filtroEsferaPoder.adicionarParametro(new ParametroSimples(FiltroEsferaPoder.ID, esferaTemp));
					Collection<EsferaPoder> colecao = Fachada.getInstancia().pesquisar(filtroEsferaPoder, EsferaPoder.class.getName());
					esfera += colecao.iterator().next().getDescricao() + " ";
				}				
			}
			relatorio.addParametro("esfPoderFiltro", esfera);
		}
		
		if( !Util.isVazioOrNulo(form.getSituacaoLigacaoAgua())){
			String ligacaoAgua = "";
			String[] array = form.getSituacaoLigacaoAgua();
			
			for(String ligacaoAguaTemp : array){
				if (!ligacaoAguaTemp.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
					FiltroLigacaoAguaSituacao filtro = new FiltroLigacaoAguaSituacao();
					filtro.adicionarParametro(new ParametroSimples(FiltroLigacaoAguaSituacao.ID, ligacaoAguaTemp));
					Collection<LigacaoAguaSituacao> colecao = Fachada.getInstancia().pesquisar(filtro, LigacaoAguaSituacao.class.getName());
					ligacaoAgua += colecao.iterator().next().getDescricaoAbreviado() + " ";
				}				
			}
			relatorio.addParametro("LigAguaFiltro", ligacaoAgua);
		}
		
		if( !Util.isVazioOrNulo(form.getCategorias())){
			String categoria = "";
			String[] array = form.getCategorias();
			
			for(String categoriaTemp:array){
				if (!categoriaTemp.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
					FiltroCategoria filtro = new FiltroCategoria();
					filtro.adicionarParametro(new ParametroSimples(FiltroCategoria.CODIGO, categoriaTemp));
					Collection<Categoria> colecao = Fachada.getInstancia().pesquisar(filtro, Categoria.class.getName());
					categoria += colecao.iterator().next().getDescricaoAbreviada() + " ";
				}				
			}
			relatorio.addParametro("categoriaFiltro", categoria );
		}
		
		if( !Util.isVazioOrNulo(form.getPerfilImovel())){
			String perfilImovel = "";
			String[] array = form.getPerfilImovel();
			
			for(String perfilImovelTemp : array){
				if (!perfilImovelTemp.equals(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
					FiltroImovelPerfil filtro = new FiltroImovelPerfil();
					filtro.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID, perfilImovelTemp));
					Collection<ImovelPerfil> colecao = Fachada.getInstancia().pesquisar(filtro, ImovelPerfil.class.getName());
					perfilImovel += colecao.iterator().next().getId() + " ";
				}				
			}
			relatorio.addParametro("perfisImovelFiltro", perfilImovel );
		}
	}
	
}
