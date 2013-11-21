package gcom.gui.cobranca.cobrancaporresultado;

import java.util.Collection;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.ComandoEmpresaCobrancaConta;
import gcom.cobranca.FiltroComandoEmpresaCobrancaConta;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1169] Movimentar Ordens de Serviço de Cobrança por Resultado
 * 
 * Action responsável por montar todo o esquema do 
 * processo de movimentar ordens de serviço de cobrança por resultado.
 *
 * @author Mariana Victor
 * @date 10/05/2011
 */
public class ExibirMovimentarOrdemServicoAction extends GcomAction {

    /**
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

        //Localiza o action no objeto
        ActionForward retorno = actionMapping.findForward("movimentarOrdemServicoEmitirOS");
        
        MovimentarOrdemServicoActionForm form = (MovimentarOrdemServicoActionForm) actionForm;
    	
        //Obtém a instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        
        this.getSessao(httpServletRequest).removeAttribute("colecaoQuantidadeContas");
		this.getSessao(httpServletRequest).removeAttribute("colecaoFaixa");
		this.getSessao(httpServletRequest).removeAttribute("colecaoQtdeContas");
		this.getSessao(httpServletRequest).removeAttribute("colecaoQtdeClientes");
		this.getSessao(httpServletRequest).removeAttribute("colecaoValorTotalDivida");
        
        if (httpServletRequest.getParameter("desfazer") != null
        		&& httpServletRequest.getParameter("desfazer").equalsIgnoreCase("true")) {

        	this.limparForm(form);
        	this.limparSessao(sessao);
        	
        	this.pesquisarColecoes(sessao, form);
        }
      
        if (httpServletRequest.getParameter("comando") != null
        		&& !httpServletRequest.getParameter("comando").equals("")) {
        	
        	form.setIdComandoContaCobranca(httpServletRequest.getParameter("comando"));
        	
        	FiltroComandoEmpresaCobrancaConta filtroComandoEmpresaCobrancaConta = new FiltroComandoEmpresaCobrancaConta();
        	filtroComandoEmpresaCobrancaConta.adicionarParametro(
        			new ParametroSimples(FiltroComandoEmpresaCobrancaConta.ID, form.getIdComandoContaCobranca()));
        	filtroComandoEmpresaCobrancaConta.adicionarCaminhoParaCarregamentoEntidade(FiltroComandoEmpresaCobrancaConta.EMPRESA);

        	Collection<ComandoEmpresaCobrancaConta> colecaoComando = 
				this.getFachada().pesquisar(filtroComandoEmpresaCobrancaConta, ComandoEmpresaCobrancaConta.class.getName());
        	
        	if (colecaoComando == null || colecaoComando.isEmpty()) {
    				throw new ActionServletException("atencao.comando.inexistente");
        	} else {
	        	ComandoEmpresaCobrancaConta comando = (ComandoEmpresaCobrancaConta) Util.retonarObjetoDeColecao(colecaoComando);
	        	
	        	if (comando.getDataExecucao() == null) {
	        		throw new ActionServletException("atencao.comando.nao_executado.nao_possivel.movimentacao");
	        	}
	        	
	        	if (comando.getIndicadorGeracaoTxt() == null || comando.getIndicadorGeracaoTxt().compareTo(new Integer(2)) == 0) {
	        		throw new ActionServletException("atencao.comando.arquivo_txt.nao_gerado.nao_possivel.movimentacao");
	        	}
	        	
	        	if (comando.getDataEncerramento() != null) {
	        		throw new ActionServletException("atencao.comando.ja_encerrado.nao_possivel.movimentacao");
	        	}
	        	
	        	form.setIdEmpresa(comando.getEmpresa().getId().toString());
        	}
			
        	this.pesquisarColecoes(sessao, form);
		}

        //Monta o Status do Wizard
        StatusWizard statusWizard = new StatusWizard(
                "movimentarOrdemServicoWizardAction", "movimentarOrdemServicoAction",
                "cancelarMovimentarOrdemServicoAction", null);
        
        //monta a primeira aba do processo
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        1, "abaEmitirOSA.gif", "abaEmitirOSD.gif",
                        "exibirMovimentarOrdemServicoEmitirOSAction",
                        "movimentarOrdemServicoEmitirOSAction"));
        
        //monta a segunda aba do processo
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        2, "abaGerarOSA.gif", "abaGerarOSD.gif",
                        "exibirMovimentarOrdemServicoGerarOSAction",
                        "movimentarOrdemServicoGerarOSAction"));
        
        //monta a terceira aba do processo
        statusWizard
                .inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
                        3, "abaEncerrarOSA.gif", "abaEncerrarOSD.gif",
                        "exibirMovimentarOrdemServicoEncerrarOSAction",
                        "movimentarOrdemServicoEncerrarOSAction"));

        statusWizard.setCaminhoActionDesfazer("exibirMovimentarOrdemServicoAction.do");
        statusWizard.setCaminhoActionVoltarFiltro("exibirConsultarComandosContasCobrancaEmpresaAction");
        statusWizard.setNomeBotaoConcluir("Emitir OS");
        
        //manda o statusWizard para a sessão
        sessao.setAttribute("statusWizard", statusWizard);
        
        //retorna o mapeamento contido na variável retorno
        return retorno;
    }
    
    private void limparForm(MovimentarOrdemServicoActionForm form){

    	form.setNumeroOSInicial("");
    	form.setNumeroOSFinal("");
    	
    	form.setTipoDivEscolhida("");
    	form.setTipoPesquisa("");
    	
    	form.setIdsCategoria(null);
    	form.setIdsImovelPerfil(null);
    	form.setIdsGerenciaRegional(null);
    	form.setIdsUnidadeNegocio(null);
    	
    	form.setIdLocalidadeOrigem("");
    	form.setIdLocalidadeDestino("");
    	form.setNomeLocalidadeOrigem("");
    	form.setNomeLocalidadeDestino("");
    	
    	form.setIdSetorComercialDestino("");
    	form.setIdSetorComercialOrigem("");
    	form.setCodigoSetorComercialDestino("");
    	form.setCodigoSetorComercialOrigem("");
    	form.setDescricaoSetorComercialDestino("");
    	form.setDescricaoSetorComercialOrigem("");
    	
    	form.setCodigoQuadraInicial("");
    	form.setDescricaoQuadraInicial("");
    	form.setCodigoQuadraFinal("");
    	form.setDescricaoQuadraFinal("");
    	
    	form.setValorMinimo("");
    	form.setValorMaximo("");
    	form.setQtdContas("");
    	form.setQtdClientes("");
    	form.setValorTotalDivida("");
    	form.setQtdeTotalClientes("");
    	
    	form.setColecaoInformada("");
    	form.setTotalSelecionado("");
    	
    	form.setIdTipoServico("");
    	form.setMatriculasImoveis(new String[10]);
    	form.setIdsLigacaoAguaSituacao(null);
    	
    	form.setNumerosOS(new String[10]);
    	form.setIdMotivoEncerramento("");
    	form.setDataEncerramento("");
    	form.setHoraEncerramento("");
    	form.setObservacaoEncerramento("");
    }
    
    private void limparSessao(HttpSession sessao){

    	//1ª aba
    	sessao.removeAttribute("colecaoQuantidadeContas");
    	sessao.removeAttribute("colecaoFaixa");
    	sessao.removeAttribute("colecaoQtdeContas");
    	sessao.removeAttribute("colecaoQtdeClientes");
    	sessao.removeAttribute("colecaoValorTotalDivida");
    	sessao.removeAttribute("colecaoOSEmpresaCobranca");
    	sessao.removeAttribute("colecaoOSRegistroAtendimento");
    	sessao.removeAttribute("quadraInicialInexistente");
    	sessao.removeAttribute("setorComercialOrigemInexistente");
    	sessao.removeAttribute("localidadeOrigemInexistente");
    	sessao.removeAttribute("quadraFinalInexistente");
    	sessao.removeAttribute("setorComercialDestinoInexistente");
    	sessao.removeAttribute("localidadeDestinoInexistente");
    	

    	//3ª aba
    	sessao.removeAttribute("motivoInformado");
    	sessao.removeAttribute("habilitaNumerosOS");
    	
    }
    
    private void pesquisarColecoes(
    		HttpSession sessao,
			MovimentarOrdemServicoActionForm form) {
    	// Coleção de Categoria
		FiltroCategoria filtroCategoria = new FiltroCategoria();
		filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);

		Collection<Categoria> colecaoCategoria = 
			this.getFachada().pesquisar(filtroCategoria, Categoria.class.getName());

		sessao.setAttribute("colecaoCategoria", colecaoCategoria);
		
		// Coleção de Perfil de Imóvel
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(
				FiltroImovelPerfil.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);

		Collection<ImovelPerfil> colecaoImovelPerfil = 
			this.getFachada().pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());

		sessao.setAttribute("colecaoImovelPerfil", colecaoImovelPerfil);
		
		// Coleção de Gerencia Regional
		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
				FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);

		Collection<GerenciaRegional> colecaoGerenciaRegional = 
			this.getFachada().pesquisar(
				filtroGerenciaRegional, 
				GerenciaRegional.class.getName());

		sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);

		// Coleção de Unidade de Negócio
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
				FiltroUnidadeNegocio.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);

		Collection<UnidadeNegocio> colecaoUnidadeNegocio = 
			this.getFachada().pesquisar(
				filtroUnidadeNegocio, 
				UnidadeNegocio.class.getName());

		sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);

		// Coleção de Tipos de Serviço
		FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
		filtroServicoTipo.adicionarParametro(new ParametroSimples(
				FiltroServicoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroServicoTipo.adicionarParametro(new ParametroSimples(
				FiltroServicoTipo.INDICADOR_EMPRESA_COBRANCA, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroServicoTipo.setCampoOrderBy(FiltroServicoTipo.DESCRICAO);

		Collection<ServicoTipo> colecaoServicoTipo = 
			this.getFachada().pesquisar(
				filtroServicoTipo, 
				ServicoTipo.class.getName());

		sessao.setAttribute("colecaoServicoTipo", colecaoServicoTipo);

		// Coleção de Situação da Ligação da Água
		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();
		filtroLigacaoAguaSituacao.setCampoOrderBy(FiltroLigacaoAguaSituacao.DESCRICAO);
		filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
				FiltroLigacaoAguaSituacao.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao = 
			this.getFachada().pesquisar(
				filtroLigacaoAguaSituacao, 
				LigacaoAguaSituacao.class.getName());

		sessao.setAttribute("colecaoLigacaoAguaSituacao", colecaoLigacaoAguaSituacao);

		// Coleção de Motivo de Encerramento de OS
		FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
		filtroAtendimentoMotivoEncerramento.setCampoOrderBy(FiltroAtendimentoMotivoEncerramento.DESCRICAO);
		filtroAtendimentoMotivoEncerramento.adicionarParametro(new ParametroSimples(
				FiltroAtendimentoMotivoEncerramento.INDICADOR_DUPLICIDADE, ConstantesSistema.NAO));
		filtroAtendimentoMotivoEncerramento.adicionarParametro(new ParametroSimples(
				FiltroAtendimentoMotivoEncerramento.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<AtendimentoMotivoEncerramento> colecaoAtendimentoMotivoEncerramento = 
			this.getFachada().pesquisar(
				filtroAtendimentoMotivoEncerramento, 
				AtendimentoMotivoEncerramento.class.getName());

		sessao.setAttribute("colecaoAtendimentoMotivoEncerramento", colecaoAtendimentoMotivoEncerramento);
    }
}
