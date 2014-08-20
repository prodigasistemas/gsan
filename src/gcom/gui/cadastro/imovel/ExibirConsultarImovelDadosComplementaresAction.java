package gcom.gui.cadastro.imovel;

import gcom.arrecadacao.ContratoDemanda;
import gcom.arrecadacao.bean.ContratoDemandaHelper;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelCadastroOcorrencia;
import gcom.cadastro.imovel.FiltroImovelEloAnormalidade;
import gcom.cadastro.imovel.FiltroImovelImagem;
import gcom.cadastro.imovel.FiltroImovelRamoAtividade;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelCadastroOcorrencia;
import gcom.cadastro.imovel.ImovelEloAnormalidade;
import gcom.cadastro.imovel.ImovelImagem;
import gcom.cadastro.imovel.ImovelRamoAtividade;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroContratoDemanda;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0473] Consultar Imovel
 * 
 * @author Rafael Santos
 * @since 07/09/2006
 * 
 */
public class ExibirConsultarImovelDadosComplementaresAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);

		ConsultarImovelActionForm consultarImovelActionForm = (ConsultarImovelActionForm) actionForm;

		if (isLimparDadosTela(httpServletRequest)) {

			httpServletRequest.removeAttribute("idImovelDadosComplementaresNaoEncontrado");
			limparFormSessao(consultarImovelActionForm, sessao);
						
        }else if( isImovelInformadoTelaDadosAdicionais(consultarImovelActionForm)
        			|| isImovelInformadoOutraTela(sessao) ){

        	consultarImovelActionForm.setIdImovelDadosComplementares(
        			definirIdImovelASerPesquisado(consultarImovelActionForm, sessao, httpServletRequest));
        	
        	Imovel imovel = obterImovelASerPesquisado(consultarImovelActionForm, sessao);
        	
			boolean isNovoImovelPesquisado = isNovoImovelPesquisado(consultarImovelActionForm, sessao);

			if (imovel != null) {
				
				
				sessao.setAttribute("imovelDadosComplementares", imovel);
				sessao.setAttribute("idImovelPrincipalAba", imovel.getId().toString());
				
				consultarImovelActionForm.setIdImovelDadosComplementares(imovel.getId().toString());
				
                if (imovel.getIndicadorExclusao().equals(ConstantesSistema.SIM)) {
					httpServletRequest.setAttribute("imovelExcluido", true);
				}
                
				if (isNovoImovelPesquisado) {

					httpServletRequest.removeAttribute("idImovelDadosComplementaresNaoEncontrado");

					setarDadosNoFormESessao(consultarImovelActionForm, imovel,sessao);
				}
			} else {
				limparFormSessao(consultarImovelActionForm, sessao);

				httpServletRequest.setAttribute("idImovelDadosComplementaresNaoEncontrado", "true");				
				consultarImovelActionForm.setMatriculaImovelDadosComplementares("IM�VEL INEXISTENTE");				
			}

		} else {
			
			String idAux = consultarImovelActionForm.getIdImovelDadosComplementares();
			
			httpServletRequest.removeAttribute("idImovelDadosComplementaresNaoEncontrado");
			
			limparFormSessao(consultarImovelActionForm, sessao);
			
			consultarImovelActionForm.setIdImovelDadosComplementares(idAux);			
			
		}

		return actionMapping.findForward("consultarImovelDadosComplementares");
	}

	/**
	 * Esse m�todo seta os dados necess�rios do Imovel
	 * no form e alguns na sess�o (cole��es).
	 * 
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private void setarDadosNoFormESessao(ConsultarImovelActionForm consultarImovelActionForm, 
			Imovel imovel,HttpSession sessao) {

		// seta na tela a inscri��o do imovel
		consultarImovelActionForm.setMatriculaImovelDadosComplementares(
			this.getFachada().pesquisarInscricaoImovelExcluidoOuNao(new Integer(consultarImovelActionForm.getIdImovelDadosComplementares().trim())));

		// seta a situa��o de agua
		consultarImovelActionForm.setSituacaoAguaDadosComplementares(
			imovel.getLigacaoAguaSituacao().getDescricao());
		
		// seta a situa��o de esgoto
		consultarImovelActionForm.setSituacaoEsgotoDadosComplementares(
			imovel.getLigacaoEsgotoSituacao().getDescricao());

		// consumo tarifa
		if (imovel.getConsumoTarifa() != null) {
			consultarImovelActionForm.setTarifaConsumoDadosComplementares(
				imovel.getConsumoTarifa().getDescricao());
		}
		
		// numero retificacao
		if (imovel.getNumeroRetificacao() != null) {
			consultarImovelActionForm.setQuantidadeRetificacoesDadosComplementares(
				imovel.getNumeroRetificacao().toString());
		}
		
		// numero parcelamento
		if (imovel.getNumeroParcelamento() != null) {
			consultarImovelActionForm.setQuantidadeParcelamentosDadosComplementares(
				imovel.getNumeroParcelamento().toString());
		}
		
		// numero reparcelamento
		if (imovel.getNumeroReparcelamento() != null) {
			consultarImovelActionForm.setQuantidadeReparcelamentoDadosComplementares(
				imovel.getNumeroReparcelamento().toString());
		}
		
		// numero reparcelamento consecutivos
		if (imovel.getNumeroReparcelamentoConsecutivos() != null) {
			consultarImovelActionForm.setQuantidadeReparcelamentoConsecutivosDadosComplementares(
				imovel.getNumeroReparcelamentoConsecutivos().toString());
		}
		
		// cobranca situacao
//		if (imovel.getCobrancaSituacao() != null) {
//			consultarImovelActionForm.setSituacaoCobrancaDadosComplementares(
//				imovel.getCobrancaSituacao().getDescricao());
//		}else{
//			consultarImovelActionForm.setSituacaoCobrancaDadosComplementares(null);
//		}
		
		// funcionario
		if (imovel.getFuncionario() != null) {
			consultarImovelActionForm.setIdFuncionario(""+imovel.getFuncionario().getId());
			consultarImovelActionForm.setNomeFuncionario(imovel.getFuncionario().getNome());
		} else {
			consultarImovelActionForm.setIdFuncionario("");
			consultarImovelActionForm.setNomeFuncionario("");
		}
		
		// cadastro ocorrencia
		if (imovel.getCadastroOcorrencia() != null) {
			consultarImovelActionForm.setDescricaoOcorrenciaDadosComplementares(
				imovel.getCadastroOcorrencia().getDescricao());
		}
		
		// elo anormalidade
		if (imovel.getEloAnormalidade() != null) {
			consultarImovelActionForm.setDescricaoAnormalidadeDadosComplementares(
				imovel.getEloAnormalidade().getDescricao());
		}
		
		//descricao das informacoes complementares
		if(imovel.getInformacoesComplementares() != null){
			consultarImovelActionForm.setInformacoesComplementares(imovel.getInformacoesComplementares());
		} else {
			consultarImovelActionForm.setInformacoesComplementares("");
		}

		sessao.setAttribute("colecaoVencimentosAlternativos",
				this.getFachada().pesquisarVencimentoAlternativoImovel(
						new Integer(consultarImovelActionForm.getIdImovelDadosComplementares().trim())));

		sessao.setAttribute("colecaoDebitosAutomaticos",
				this.getFachada().pesquisarDebitosAutomaticosImovel(
						new Integer(consultarImovelActionForm.getIdImovelDadosComplementares().trim())));
		
		sessao.setAttribute("colecaoFaturamentosSituacaoHistorico",
				this.getFachada().pesquisarFaturamentosSituacaoHistorico(
						new Integer(consultarImovelActionForm.getIdImovelDadosComplementares().trim())));
		
		sessao.setAttribute("colecaoCobrancasSituacaoHistorico",
				this.getFachada().pesquisarCobrancasSituacaoHistorico(
						new Integer(consultarImovelActionForm.getIdImovelDadosComplementares().trim())));
		
		setarColecaoCadastroOcorrenciaSessao(consultarImovelActionForm, sessao);
		
		setarColecaoEloAnormalidadeSessao(consultarImovelActionForm, sessao);
		
    	setarColecaoImovelRamoAtividadeSessao(imovel, sessao);	
    	
    	setarColecaoDadosImovelCobrancaSituacaoSessao(consultarImovelActionForm,sessao);

    	setarColecaoDadosNegativadorMovimentoRegSessao(consultarImovelActionForm,sessao);

    	setarColecaoContratoDemandaHelper(imovel,sessao);
    	
    	setarColecaoMatriculasAssociadas(imovel,sessao);
    	
    	pesquisarImovelImagens(imovel, sessao);
	}

	/**
	 * Esse m�todo consulta e seta na sess�o 
	 * uma cole��o de elo anormalidade do imovel.
	 *
	 *@since 05/10/2009
	 *@author Marlon Patrick
	 */
	private void setarColecaoImovelRamoAtividadeSessao(Imovel imovel,
			HttpSession sessao) {
		
		FiltroImovelRamoAtividade filtroImovelRamoAtividade = new FiltroImovelRamoAtividade();
        filtroImovelRamoAtividade.adicionarCaminhoParaCarregamentoEntidade("comp_id.imovel");
        filtroImovelRamoAtividade.adicionarCaminhoParaCarregamentoEntidade("comp_id.ramo_atividade");
        
        filtroImovelRamoAtividade.adicionarParametro(new ParametroSimples(
                FiltroImovelRamoAtividade.IMOVEL_ID, new Integer(imovel.getId())));
        
        sessao.setAttribute("colecaoImovelRamosAtividade", 
        		this.getFachada().pesquisar(filtroImovelRamoAtividade,
        				ImovelRamoAtividade.class.getName()));
	}

	/**
	 * Esse m�todo consulta e seta na sess�o 
	 * uma cole��o de elo anormalidade do imovel.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private void setarColecaoEloAnormalidadeSessao(
			ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {
		
		FiltroImovelEloAnormalidade filtroImovelEloAnormalidade = new FiltroImovelEloAnormalidade();
		filtroImovelEloAnormalidade.adicionarParametro(
			new ParametroSimples(FiltroImovelEloAnormalidade.IMOVEL_ID, 
					consultarImovelActionForm.getIdImovelDadosComplementares().trim()));
		
		filtroImovelEloAnormalidade.setCampoOrderBy(FiltroImovelEloAnormalidade.DATA_ANORMALIDADE);
		filtroImovelEloAnormalidade.adicionarCaminhoParaCarregamentoEntidade("eloAnormalidade");
		
		sessao.setAttribute("colecaoImovelEloAnormalidade",
				this.getFachada().pesquisar(filtroImovelEloAnormalidade, 
						ImovelEloAnormalidade.class.getName()));
	}

	/**
	 * Esse m�todo consulta e seta na sess�o 
	 * uma cole��o de cadastro ocorrencia do imovel.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private void setarColecaoCadastroOcorrenciaSessao(
			ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {
		
		FiltroImovelCadastroOcorrencia filtroImovelCadastroOcorrencia = 
			new FiltroImovelCadastroOcorrencia();
		
		filtroImovelCadastroOcorrencia.adicionarParametro(
			new ParametroSimples(FiltroImovelCadastroOcorrencia.IMOVEL_ID, 
					consultarImovelActionForm.getIdImovelDadosComplementares().trim()));
		
		filtroImovelCadastroOcorrencia.setCampoOrderBy(FiltroImovelCadastroOcorrencia.DATA_OCORRENCIA);
		filtroImovelCadastroOcorrencia.adicionarCaminhoParaCarregamentoEntidade("cadastroOcorrencia");
		
		sessao.setAttribute("colecaoImovelCadastroOcorrencia",
				this.getFachada().pesquisar(filtroImovelCadastroOcorrencia, 
						ImovelCadastroOcorrencia.class.getName()));
	}

	/**
	 *Esse m�todo limpa todos os atributos do form
	 *e os atributos na sesss�o 
	 *que s�o usados pelo action e/ou jsp.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private void limparFormSessao(ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {
		
		sessao.removeAttribute("imovelDadosComplementares");
		sessao.removeAttribute("colecaoVencimentosAlternativos");
		sessao.removeAttribute("colecaoDebitosAutomaticos");
		sessao.removeAttribute("colecaoFaturamentosSituacaoHistorico");
		sessao.removeAttribute("colecaoCobrancasSituacaoHistorico");
		sessao.removeAttribute("idImovelPrincipalAba");			
		sessao.removeAttribute("colecaoImovelCadastroOcorrencia");
		sessao.removeAttribute("colecaoImovelEloAnormalidade");
		sessao.removeAttribute("colecaoImovelRamosAtividade");
		sessao.removeAttribute("contratoDemandaHelper");
		sessao.removeAttribute("colecaoMatriculasAssociadas");

		consultarImovelActionForm.setIdImovelDadosComplementares(null);
		consultarImovelActionForm.setIdImovelDadosCadastrais(null);
		consultarImovelActionForm.setIdImovelAnaliseMedicaoConsumo(null);
		consultarImovelActionForm.setIdImovelHistoricoFaturamento(null);
		consultarImovelActionForm.setIdImovelDebitos(null);
		consultarImovelActionForm.setIdImovelPagamentos(null);
		consultarImovelActionForm.setIdImovelDevolucoesImovel(null);
		consultarImovelActionForm.setIdImovelDocumentosCobranca(null);
		consultarImovelActionForm.setIdImovelParcelamentosDebitos(null);
		consultarImovelActionForm.setIdImovelRegistroAtendimento(null);
		consultarImovelActionForm.setImovIdAnt(null);
		
		consultarImovelActionForm.setMatriculaImovelDadosComplementares(null);
		consultarImovelActionForm.setSituacaoAguaDadosComplementares(null);
		consultarImovelActionForm.setSituacaoEsgotoDadosComplementares(null);
		consultarImovelActionForm.setTarifaConsumoDadosComplementares(null);
		consultarImovelActionForm.setQuantidadeRetificacoesDadosComplementares(null);
		consultarImovelActionForm.setQuantidadeParcelamentosDadosComplementares(null);
		consultarImovelActionForm.setQuantidadeReparcelamentoDadosComplementares(null);
		consultarImovelActionForm.setQuantidadeReparcelamentoConsecutivosDadosComplementares(null);
//		consultarImovelActionForm.setSituacaoCobrancaDadosComplementares(null);
		consultarImovelActionForm.setDescricaoOcorrenciaDadosComplementares(null);
		consultarImovelActionForm.setIdFuncionario(null);
		consultarImovelActionForm.setNomeFuncionario(null);
		consultarImovelActionForm.setDescricaoAnormalidadeDadosComplementares(null);
		consultarImovelActionForm.setInformacoesComplementares(null);
	}

	/**
	 * Caso o usu�rio tenha clicado no bot�o de limpar
	 * esse m�todo retornar� true.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isLimparDadosTela(HttpServletRequest httpServletRequest) {
		return Util.verificarNaoVazio(httpServletRequest.getParameter("limparForm"));
	}

	/**
	 * Esse m�todo verifica se j� foi informado um im�vel em outra tela.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isImovelInformadoOutraTela(HttpSession sessao) {
		return Util.verificarNaoVazio((String)sessao.getAttribute("idImovelPrincipalAba"));
	}

	/**
	 * Esse m�todo verifica se o im�vel foi informado na tela
	 * de Dados Adicionais
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isImovelInformadoTelaDadosAdicionais(ConsultarImovelActionForm consultarImovelActionForm) {
		return Util.verificarNaoVazio(consultarImovelActionForm.getIdImovelDadosComplementares());
	}
	
	/**
	 * Esse m�todo retorna o id do im�vel a ser pesquisado,
	 * verificando se � o id possivelmente informado pelo usu�rio na tela 
	 * de dados adicionais ou se o id j� informado em uma outra tela.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private String definirIdImovelASerPesquisado(
			ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao, HttpServletRequest httpServletRequest) {
		
		String idImovelPrincipalAba = (String)sessao.getAttribute("idImovelPrincipalAba");
		
		if( isImovelInformadoTelaDadosAdicionais(consultarImovelActionForm)
				&& isImovelInformadoOutraTela(sessao)){
		
			if( !Util.verificarNaoVazio(httpServletRequest.getParameter("indicadorNovo")) ){        				
					return idImovelPrincipalAba;            		
			}

		}else if(isImovelInformadoOutraTela(sessao)){
				return idImovelPrincipalAba;            		
		}

		return consultarImovelActionForm.getIdImovelDadosComplementares();
	}

	/**
	 * Consulta o Imovel com todas as informa��es necess�rias,
	 * ou simplesmetne pega o Imovel da sess�o caso o mesmo j�
	 * tenha sido pesquisado.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private Imovel obterImovelASerPesquisado(ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {
		
		Imovel imovel = null;

		if(sessao.getAttribute("imovelDadosComplementares") == null){
			imovel = Fachada.getInstancia().consultarImovelDadosComplementares(new Integer(consultarImovelActionForm.getIdImovelDadosComplementares().trim()));

		}else{
			imovel = (Imovel) sessao.getAttribute("imovelDadosComplementares");
			
			if( !imovel.getId().toString().equals(consultarImovelActionForm.getIdImovelDadosComplementares().trim()) ){
				imovel = Fachada.getInstancia().consultarImovelDadosComplementares(new Integer(consultarImovelActionForm.getIdImovelDadosComplementares().trim()));
			}
		}
		return imovel;
	}

	/**
	 * Esse m�todo retorna true se foi necess�rio consultar um novo imovel.
	 * Caso o im�vel seja o mesmo j� consultado anteriormente ele retorna false.
	 *
	 *@since 22/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isNovoImovelPesquisado(ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {
		
		if(sessao.getAttribute("imovelDadosComplementares") == null){
			return true;
		}
		
		Imovel imovelAux = (Imovel) sessao.getAttribute("imovelDadosComplementares");
		
		if( !imovelAux.getId().toString().equals(consultarImovelActionForm.getIdImovelDadosComplementares().trim()) ){
			return true;
		}

		return false;
	}
	
	/**
	 *@since 03/05/2010
	 *@author Vivianne Sousa
	 */
	private void setarColecaoDadosImovelCobrancaSituacaoSessao(
			ConsultarImovelActionForm consultarImovelActionForm,HttpSession sessao) {
		
		sessao.setAttribute("colecaoDadosImovelCobrancaSituacao",
				this.getFachada().pesquisarDadosImovelCobrancaSituacaoPorImovel(
						new Integer(consultarImovelActionForm.getIdImovelDadosComplementares().trim())));
	}

	/**
	 *@since 03/05/2010
	 *@author Vivianne Sousa
	 */
	private void setarColecaoDadosNegativadorMovimentoRegSessao(
			ConsultarImovelActionForm consultarImovelActionForm,HttpSession sessao) {
		
		sessao.setAttribute("colecaoDadosNegativadorMovimentoReg",
				this.getFachada().consultarDadosNegativadorMovimentoReg(
						new Integer(consultarImovelActionForm.getIdImovelDadosComplementares().trim())));
	}
	

	/**
	 *@since 06/01/2011
	 *@author Mariana Victor
	 */
	private void setarColecaoContratoDemandaHelper(Imovel imovel,HttpSession sessao) {
		ContratoDemandaHelper contratoDemandaHelper = null;
		
		FiltroContratoDemanda filtroContratoDemanda = new FiltroContratoDemanda();
		filtroContratoDemanda.adicionarParametro(
				new ParametroSimples(FiltroContratoDemanda.IMOVEL, imovel.getId()));
		filtroContratoDemanda.adicionarParametro(
				new ParametroNulo(FiltroContratoDemanda.DATACONTRATOENCERRAMENTO));
		Collection colecaoContratoDemanda = this.getFachada().pesquisar(
				filtroContratoDemanda, ContratoDemanda.class.getName());
		
		if (colecaoContratoDemanda != null && !colecaoContratoDemanda.isEmpty()) {
			Object[] consumoContratado = this.getFachada().consultarConsumoCadastrado(imovel.getId());
			if (consumoContratado != null) {
				contratoDemandaHelper = new ContratoDemandaHelper();
				contratoDemandaHelper.setContratoDemanda((ContratoDemanda) Util.retonarObjetoDeColecao(colecaoContratoDemanda));
				contratoDemandaHelper.setConsumoContratado(consumoContratado[0].toString());
				contratoDemandaHelper.setValorTarifa(Util.formatarBigDecimalParaStringComVirgula(
						(BigDecimal) consumoContratado[1]));
			}
		}
		
		if (contratoDemandaHelper != null) {
			sessao.setAttribute("contratoDemandaHelper", contratoDemandaHelper);
		} else {
			sessao.removeAttribute("contratoDemandaHelper");
		}
	}
	

	/**
	 *@since 06/01/2011
	 *@author Mariana Victor
	 */
	private void setarColecaoMatriculasAssociadas(Imovel imovel, HttpSession sessao) {
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(
				new ParametroSimples(FiltroImovel.ID, imovel.getId()));
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(
				FiltroImovel.CONSUMO_TARIFA);
		Collection colecaoImovel = this.getFachada().pesquisar(
				filtroImovel, Imovel.class.getName());
		if (colecaoImovel != null && !colecaoImovel.isEmpty()) {
			Imovel imovelRetornado = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
			Collection colecaoMatriculasAssociadas = this.getFachada().consultarMatriculasAssociadas(
					imovelRetornado.getConsumoTarifa().getId(), imovelRetornado.getId());
			if (colecaoMatriculasAssociadas != null && !colecaoMatriculasAssociadas.isEmpty()) {
				sessao.setAttribute("colecaoMatriculasAssociadas", colecaoMatriculasAssociadas);
			} else {
				sessao.removeAttribute("colecaoMatriculasAssociadas");
			}
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarImovelImagens(Imovel imovel, HttpSession sessao) {
		FiltroImovelImagem filtro = new FiltroImovelImagem();
		filtro.adicionarParametro(new ParametroSimples(FiltroImovelImagem.IMOVEL_ID, imovel.getId()));
		
		sessao.setAttribute("colecaoImagens", Fachada.getInstancia().pesquisar(filtro, ImovelImagem.class.getName()));
	}
}
