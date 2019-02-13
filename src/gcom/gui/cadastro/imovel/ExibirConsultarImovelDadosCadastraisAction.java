package gcom.gui.cadastro.imovel;

import java.text.SimpleDateFormat;

import gcom.atualizacaocadastral.ImovelControleAtualizacaoCadastral;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.bean.IntegracaoQuadraFaceHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.ComunicadoEmitirConta;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 1° Aba do [UC0472] Consultar Imóvel
 * 
 * @author Rafael Santos
 * @since 07/09/2006
 */
public class ExibirConsultarImovelDadosCadastraisAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        HttpSession sessao = httpServletRequest.getSession(false);

        ConsultarImovelActionForm consultarImovelActionForm = (ConsultarImovelActionForm) actionForm;
        
        if( isLimparDadosTela(httpServletRequest)){

    		httpServletRequest.removeAttribute("idImovelDadosCadastraisNaoEncontrado");

        	limparFormSessao(consultarImovelActionForm, sessao);
        	consultarImovelActionForm.setDataProcessamento("");
			
        }else if( isImovelInformadoTelaDadosCadastrais(consultarImovelActionForm) 
        			|| isImovelInformadoOutraTela(sessao) ){
        	
			consultarImovelActionForm.setIdImovelDadosCadastrais(
            	definirIdImovelASerPesquisado(consultarImovelActionForm, sessao,httpServletRequest) );
        	
	        Imovel imovel = obterImovelASerPesquisado(consultarImovelActionForm,sessao);
	        
	        //deve ser chamado antes dos novos valores da sessão serem setados
	        boolean isNovoImovelPesquisado = isNovoImovelPesquisado(consultarImovelActionForm, sessao);
	
            if (imovel != null) {
            	
                sessao.setAttribute("imovelDadosCadastrais", imovel);
                sessao.setAttribute("idImovelPrincipalAba", imovel.getId().toString());
                ImovelControleAtualizacaoCadastral imovelControleAtualizacaoCadastral = Fachada.getInstancia().pesquisarImovelControleAtualizacao(imovel.getId());
                SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
               
                consultarImovelActionForm.setIdImovelDadosCadastrais(imovel.getId().toString());
                if (imovelControleAtualizacaoCadastral != null ) {
                	if (imovelControleAtualizacaoCadastral.getDataProcessamento() != null)
                		consultarImovelActionForm.setDataProcessamento(formatador.format(imovelControleAtualizacaoCadastral.getDataProcessamento()));
                	
                	ComunicadoEmitirConta comunicado = Fachada.getInstancia().pesquisarUltimoComunicadoGerado(imovel.getId(), ComunicadoEmitirConta.IRREGULARIDADE_CADASTRO);
                	
                	if (comunicado != null)
                		consultarImovelActionForm.setDataEmissaoComunicadoIrregularidadeFaturamento(formatador.format(comunicado.getDataGeracao()));
                }
                
                if (imovel.getIndicadorExclusao().equals(ConstantesSistema.SIM)) {
					httpServletRequest.setAttribute("imovelExcluido", true);
				}

                if(isNovoImovelPesquisado){
                	
	                httpServletRequest.removeAttribute("idImovelDadosCadastraisNaoEncontrado");
	                
	    	        setarDadosImovelNoFormESessao(consultarImovelActionForm,imovel, sessao, httpServletRequest);
                }

            } else {
                limparFormSessao(consultarImovelActionForm, sessao);

                httpServletRequest.setAttribute("idImovelDadosCadastraisNaoEncontrado", "true");
                consultarImovelActionForm.setMatriculaImovelDadosCadastrais("IMÓVEL INEXISTENTE");
            }
        }else{
        	String idImovelAux = consultarImovelActionForm.getIdImovelDadosCadastrais();

         	httpServletRequest.removeAttribute("idImovelDadosCadastraisNaoEncontrado");

            limparFormSessao(consultarImovelActionForm, sessao);

        	consultarImovelActionForm.setIdImovelDadosCadastrais(idImovelAux);
        	consultarImovelActionForm.setDataProcessamento("");
        }

        return actionMapping.findForward("consultarImovelDadosCadastrais");
    }

	/**
	 * Esse método seta os dados necessários do Imovel
	 * no form e alguns na sessão (coleções).
	 * 
	 *@since 21/09/2009
	 *@author Marlon Patrick
	 */
	private void setarDadosImovelNoFormESessao(ConsultarImovelActionForm consultarImovelActionForm, 
			Imovel imovel,HttpSession sessao, HttpServletRequest httpServletRequest) {
		
		Fachada fachada = Fachada.getInstancia();

        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		consultarImovelActionForm.setMatriculaImovelDadosCadastrais(fachada.pesquisarInscricaoImovelExcluidoOuNao(new Integer(consultarImovelActionForm.getIdImovelDadosCadastrais().trim())));
		
		consultarImovelActionForm.setEnderecoImovelDadosCadastrais(fachada.pesquisarEndereco(new Integer(consultarImovelActionForm.getIdImovelDadosCadastrais().trim())));
		
		sessao.setAttribute("enderecoImovelDadosCadastrais",consultarImovelActionForm.getEnderecoImovelDadosCadastrais());
		
		if(imovel.getLigacaoAguaSituacao() != null){
			consultarImovelActionForm.setSituacaoAguaDadosCadastrais(imovel.getLigacaoAguaSituacao().getDescricao());
		} else {
			consultarImovelActionForm.setSituacaoAguaDadosCadastrais("");
		}

		if(imovel.getLigacaoEsgotoSituacao() != null){
			consultarImovelActionForm.setSituacaoEsgotoDadosCadastrais(imovel.getLigacaoEsgotoSituacao().getDescricao());
		} else {
			consultarImovelActionForm.setSituacaoEsgotoDadosCadastrais("");
		}
		
		sessao.setAttribute("imovelClientes",fachada.pesquisarClientesImovelExcluidoOuNao
				(new Integer(consultarImovelActionForm.getIdImovelDadosCadastrais().trim()))  );
		
		sessao.setAttribute("imovelSubcategorias",fachada.pesquisarCategoriasImovel
				(new Integer(consultarImovelActionForm.getIdImovelDadosCadastrais().trim()))   );
		
		if(imovel.getImovelPerfil() != null){
			consultarImovelActionForm.setImovelPerfilDadosCadastrais(imovel.getImovelPerfil().getDescricao());
		} else {
			consultarImovelActionForm.setImovelPerfilDadosCadastrais("");
		}
		if(imovel.getDespejo() != null){
			consultarImovelActionForm.setDespejoDadosCadastrais(imovel.getDespejo().getDescricao());
		} else {
			consultarImovelActionForm.setDespejoDadosCadastrais("");
		}
		
		if(imovel.getAreaConstruida() != null){
			consultarImovelActionForm.setAreaConstruidaDadosDadosCadastrais(Util.formatarMoedaReal(imovel.getAreaConstruida()));	

		}else if(imovel.getAreaConstruidaFaixa() != null ){
			if(imovel.getAreaConstruidaFaixa().getMenorFaixa() != null && imovel.getAreaConstruidaFaixa().getMaiorFaixa() != null){
				consultarImovelActionForm.setAreaConstruidaDadosDadosCadastrais(imovel.getAreaConstruidaFaixa().getMenorFaixa().toString()+ " a " + imovel.getAreaConstruidaFaixa().getMaiorFaixa().toString());
			}
		} else {
			consultarImovelActionForm.setAreaConstruidaDadosDadosCadastrais("");
		}
		
		if(imovel.getTestadaLote() != null){
			consultarImovelActionForm.setTestadaLoteDadosCadastrais(imovel.getTestadaLote().toString());
		} else {
			consultarImovelActionForm.setTestadaLoteDadosCadastrais("");
		}

		if(imovel.getVolumeReservatorioInferior() != null){
			consultarImovelActionForm.setVolumeReservatorioInferiorDadosCadastrais(Util.formatarMoedaReal(imovel.getVolumeReservatorioInferior()));	
		
		}else if(imovel.getReservatorioVolumeFaixaInferior() != null){
			if(imovel.getReservatorioVolumeFaixaInferior().getVolumeMenorFaixa() != null && imovel.getReservatorioVolumeFaixaInferior().getVolumeMaiorFaixa() != null){
				consultarImovelActionForm.setVolumeReservatorioInferiorDadosCadastrais(Util.formatarMoedaReal(imovel.getReservatorioVolumeFaixaInferior().getVolumeMenorFaixa())+" a "+ Util.formatarMoedaReal(imovel.getReservatorioVolumeFaixaInferior().getVolumeMaiorFaixa()));
			}
		} else {
			consultarImovelActionForm.setVolumeReservatorioInferiorDadosCadastrais("");
		}
		
		if(imovel.getVolumeReservatorioSuperior() != null){
			consultarImovelActionForm.setVolumeReservatorioSuperiorDadosCadastrais(Util.formatarMoedaReal(imovel.getVolumeReservatorioSuperior()));	
		
		}else if(imovel.getReservatorioVolumeFaixaSuperior() != null){
			if(imovel.getReservatorioVolumeFaixaSuperior().getVolumeMenorFaixa() != null && imovel.getReservatorioVolumeFaixaSuperior().getVolumeMaiorFaixa() != null){
				consultarImovelActionForm.setVolumeReservatorioSuperiorDadosCadastrais(Util.formatarMoedaReal(imovel.getReservatorioVolumeFaixaSuperior().getVolumeMenorFaixa())+" a "+ Util.formatarMoedaReal(imovel.getReservatorioVolumeFaixaSuperior().getVolumeMaiorFaixa()));
			}
		} else {
			consultarImovelActionForm.setVolumeReservatorioSuperiorDadosCadastrais("");
		}

		if(imovel.getVolumePiscina() != null){
			consultarImovelActionForm.setVolumePiscinaDadosCadastrais(Util.formatarMoedaReal(imovel.getVolumePiscina()));	
		
		}else if(imovel.getPiscinaVolumeFaixa() != null){
			if(imovel.getPiscinaVolumeFaixa().getVolumeMenorFaixa() != null && imovel.getPiscinaVolumeFaixa().getVolumeMaiorFaixa() != null){
				consultarImovelActionForm.setVolumePiscinaDadosCadastrais(Util.formatarMoedaReal(imovel.getPiscinaVolumeFaixa().getVolumeMenorFaixa())+" a "+ Util.formatarMoedaReal(imovel.getPiscinaVolumeFaixa().getVolumeMaiorFaixa()));
			}
		} else {
			consultarImovelActionForm.setVolumePiscinaDadosCadastrais("");
		}
		
		if(imovel.getFonteAbastecimento() != null){
			consultarImovelActionForm.setFonteAbastecimentoDadosCadastrais(imovel.getFonteAbastecimento().getDescricao());	
		} else {
			consultarImovelActionForm.setFonteAbastecimentoDadosCadastrais("");
		}
		
		if(imovel.getPocoTipo() != null){
			consultarImovelActionForm.setPocoTipoDadosCadastrais(imovel.getPocoTipo().getDescricao());
		} else {
			consultarImovelActionForm.setPocoTipoDadosCadastrais("");
		}

		IntegracaoQuadraFaceHelper integracao = fachada.integracaoQuadraFace(imovel.getId());
		
		if(integracao.getDistritoOperacional() != null){
			consultarImovelActionForm.setDistritoOperacionalDadosCadastrais(integracao.getDistritoOperacional().getDescricao());
		} else {
			consultarImovelActionForm.setDistritoOperacionalDadosCadastrais("");
		}

		if(integracao.getBacia() != null){
			consultarImovelActionForm.setDivisaoEsgotoDadosCadastrais(integracao.getBacia().getSistemaEsgoto().getDivisaoEsgoto().getDescricao());
		} else {
			consultarImovelActionForm.setDivisaoEsgotoDadosCadastrais("");
		}

		if(imovel.getPavimentoRua() != null){
			consultarImovelActionForm.setPavimentoRuaDadosCadastrais(imovel.getPavimentoRua().getDescricao());
		} else {
			consultarImovelActionForm.setPavimentoRuaDadosCadastrais("");
		}

		if(imovel.getPavimentoCalcada() != null){
			consultarImovelActionForm.setPavimentoCalcadaDadosCadastrais(imovel.getPavimentoCalcada().getDescricao());
		} else {
			consultarImovelActionForm.setPavimentoCalcadaDadosCadastrais("");
		}

		if(imovel.getNumeroIptu() != null){
			consultarImovelActionForm.setNumeroIptuDadosCadastrais(imovel.getNumeroIptu());
		} else {
			consultarImovelActionForm.setNumeroIptuDadosCadastrais("");
		}

		if(imovel.getNumeroCelpe() != null){
			consultarImovelActionForm.setNumeroCelpeDadosCadastrais(imovel.getNumeroCelpe().toString());
		} else {
			consultarImovelActionForm.setNumeroCelpeDadosCadastrais("");
		}

		if(imovel.getCoordenadaX() != null){
			consultarImovelActionForm.setCoordenadaXDadosCadastrais(imovel.getCoordenadaX());
		} else {
			consultarImovelActionForm.setCoordenadaXDadosCadastrais("");
		}

		if(imovel.getCoordenadaY() != null){
			consultarImovelActionForm.setCoordenadaYDadosCadastrais(imovel.getCoordenadaY());
		} else {
			consultarImovelActionForm.setCoordenadaYDadosCadastrais("");
		}

		if(imovel.getCadastroOcorrencia() != null){
			consultarImovelActionForm.setCadastroOcorrenciaDadosCadastrais(imovel.getCadastroOcorrencia().getDescricao());
		} else {
			consultarImovelActionForm.setCadastroOcorrenciaDadosCadastrais("");
		}

		if(imovel.getEloAnormalidade() != null){
			consultarImovelActionForm.setEloAnormalidadeDadosCadastrais(imovel.getEloAnormalidade().getDescricao());
		} else {
			consultarImovelActionForm.setEloAnormalidadeDadosCadastrais("");
		}
		
		if(imovel.getIndicadorImovelCondominio() != null){
			if(imovel.getIndicadorImovelCondominio().equals(ConstantesSistema.SIM)){
				consultarImovelActionForm.setIndicadorImovelCondominioDadosCadastrais("SIM");		
			}else{
				consultarImovelActionForm.setIndicadorImovelCondominioDadosCadastrais("NÃO");
			}
		} else {
			consultarImovelActionForm.setIndicadorImovelCondominioDadosCadastrais("");
		}
		
		if(imovel.getImovelCondominio() != null){
			consultarImovelActionForm.setImovelCondominioDadosCadastrais(imovel.getImovelCondominio().getId().toString() );
		} else {
			consultarImovelActionForm.setImovelCondominioDadosCadastrais("");
		}

		if(imovel.getImovelPrincipal() != null){
			consultarImovelActionForm.setImovelPrincipalDadosCadastrais(imovel.getImovelPrincipal().getId().toString());
		} else {
			consultarImovelActionForm.setImovelPrincipalDadosCadastrais("");
		}
		
		//Indicador Nivel Esgoto
		if (sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(
			SistemaParametro.EMPRESA_CAER)){
			httpServletRequest.setAttribute("apresentarIndicadorNivelInstalacaoEsgoto", true);
			if(imovel.getIndicadorNivelInstalacaoEsgoto() != null){
				if(imovel.getIndicadorNivelInstalacaoEsgoto().shortValue() == 1){
					consultarImovelActionForm.setIndicadorNivelInstalacaoEsgotoDadosCadastrais("SIM");	
				}else{
					consultarImovelActionForm.setIndicadorNivelInstalacaoEsgotoDadosCadastrais("NÃO");
				}
			} else {
				consultarImovelActionForm.setIndicadorNivelInstalacaoEsgotoDadosCadastrais("");
			}
		} else { 
			httpServletRequest.removeAttribute("apresentarIndicadorNivelInstalacaoEsgoto");	
		}
	        

		if(imovel.getNumeroPontosUtilizacao() != null){
			consultarImovelActionForm.setNumeroPontosUtilizacaoDadosCadastrais(imovel.getNumeroPontosUtilizacao().toString());
		} else {
			consultarImovelActionForm.setNumeroPontosUtilizacaoDadosCadastrais("");
		}

		if(imovel.getNumeroMorador() != null){
			consultarImovelActionForm.setNumeroMoradoresDadosCadastrais(imovel.getNumeroMorador().toString());	
		} else {
			consultarImovelActionForm.setNumeroMoradoresDadosCadastrais("");
		}

		if(imovel.getIndicadorJardim() != null){
			if(imovel.getIndicadorJardim().shortValue() == 1){
				consultarImovelActionForm.setJardimDadosCadastrais("SIM");	
			}else{
				consultarImovelActionForm.setJardimDadosCadastrais("NÃO");
			}
		} else {
			consultarImovelActionForm.setJardimDadosCadastrais("");
		}

		if(imovel.getImovelTipoHabitacao() != null){
			consultarImovelActionForm.setTipoHabitacaoDadosCadastrais(imovel.getImovelTipoHabitacao().getDescricao());	
		} else {
			consultarImovelActionForm.setTipoHabitacaoDadosCadastrais("");
		}

		if(imovel.getImovelTipoPropriedade() != null){
			consultarImovelActionForm.setTipoPropriedadeDadosCadastrais(imovel.getImovelTipoPropriedade().getDescricao());	
		} else {
			consultarImovelActionForm.setTipoPropriedadeDadosCadastrais("");
		}

		if(imovel.getImovelTipoConstrucao() != null){
			consultarImovelActionForm.setTipoConstrucaoDadosCadastrais(imovel.getImovelTipoConstrucao().getDescricao());	
		} else {
			consultarImovelActionForm.setTipoConstrucaoDadosCadastrais("");
		}

		if(imovel.getImovelTipoCobertura() != null){
			consultarImovelActionForm.setTipoCoberturaDadosCadastrais(imovel.getImovelTipoCobertura().getDescricao());	
		} else {
			consultarImovelActionForm.setTipoCoberturaDadosCadastrais("");
		}
		
		if(imovel.getObservacaoCategoria() != null){
			consultarImovelActionForm.setObservacaoCategoriaDadosCadastrais(imovel.getObservacaoCategoria().toString());	
		} else {
			consultarImovelActionForm.setObservacaoCategoriaDadosCadastrais("");
		}
		try{
			Municipio municipio = imovel.getLocalidade().getMunicipio();
			if(municipio != null){
				consultarImovelActionForm.setDescricaoMunicipio(municipio.getNome());
				httpServletRequest.setAttribute("desabilitaMunicipioLocalidade", "OK");
			}
		}catch (NullPointerException e) {
			//Localidade não possui município associado
		}
	}

	/**
	 * Esse método retorna true se foi necessário consultar um novo imovel.
	 * Caso o imóvel seja o mesmo já consultado anteriormente ele retorna false.
	 *
	 *@since 21/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isNovoImovelPesquisado(ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {
		
		if(sessao.getAttribute("imovelDadosCadastrais") == null){
			return true;
		}
		
		Imovel imovelAux = (Imovel) sessao.getAttribute("imovelDadosCadastrais");
		
		if( !imovelAux.getId().toString().equals(consultarImovelActionForm.getIdImovelDadosCadastrais().trim()) ){
			return true;
		}

		return false;
	}

	/**
	 * Consulta o Imovel com todas as informações necessárias,
	 * ou simplesmetne pega o Imovel da sessão caso o mesmo já
	 * tenha sido pesquisado.
	 *
	 *@since 21/09/2009
	 *@author Marlon Patrick
	 */
	private Imovel obterImovelASerPesquisado(ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {
		
		Imovel imovel = null;

		if(sessao.getAttribute("imovelDadosCadastrais") == null){
			imovel = Fachada.getInstancia().consultarImovelDadosCadastrais(new Integer(consultarImovelActionForm.getIdImovelDadosCadastrais().trim()));

		}else{
			imovel = (Imovel) sessao.getAttribute("imovelDadosCadastrais");
			
			if( !imovel.getId().toString().equals(consultarImovelActionForm.getIdImovelDadosCadastrais().trim()) ){
				imovel = Fachada.getInstancia().consultarImovelDadosCadastrais(new Integer(consultarImovelActionForm.getIdImovelDadosCadastrais().trim()));
			}
		}
		return imovel;
	}

	/**
	 * Esse método retorna o id do imóvel a ser pesquisado,
	 * verificando se é o id possivelmente informado pelo usuário na tela 
	 * de dados cadastrais ou se o id já informado em uma outra tela.
	 *
	 *@since 21/09/2009
	 *@author Marlon Patrick
	 */
	private String definirIdImovelASerPesquisado(
			ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao, HttpServletRequest httpServletRequest) {
		
		String idImovelPrincipalAba = (String)sessao.getAttribute("idImovelPrincipalAba");
		
		if( isImovelInformadoTelaDadosCadastrais(consultarImovelActionForm)
				&& isImovelInformadoOutraTela(sessao)){
		
			if( !Util.verificarNaoVazio(httpServletRequest.getParameter("indicadorNovo")) ){        				
					return idImovelPrincipalAba;            		
			}

		}else if(isImovelInformadoOutraTela(sessao)){
				return idImovelPrincipalAba;            		
		}

		return consultarImovelActionForm.getIdImovelDadosCadastrais();
	}

	/**
	 * Esse método verifica se já foi informado um imóvel em outra tela.
	 *
	 *@since 21/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isImovelInformadoOutraTela(HttpSession sessao) {
		return Util.verificarNaoVazio((String)sessao.getAttribute("idImovelPrincipalAba"));
	}

	/**
	 * Esse método verifica se o imóvel foi informado na tela
	 * de Dados Cadastrais
	 *
	 *@since 21/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isImovelInformadoTelaDadosCadastrais(ConsultarImovelActionForm consultarImovelActionForm) {
		return Util.verificarNaoVazio(consultarImovelActionForm.getIdImovelDadosCadastrais());
	}

	/**
	 * Caso o usuário tenha clicado no botão de limpar
	 * esse método retornará true.
	 *
	 *@since 21/09/2009
	 *@author Marlon Patrick
	 */
	private boolean isLimparDadosTela(HttpServletRequest httpServletRequest) {
		return Util.verificarNaoVazio(httpServletRequest.getParameter("limparForm"));
	}

	/**
	 *Esse método limpa todos os atributos do form
	 *e os atributos na sesssão 
	 *que são usados pelo action e/ou jsp.
	 *
	 *@since 21/09/2009
	 *@author Marlon Patrick
	 */
	private void limparFormSessao(ConsultarImovelActionForm consultarImovelActionForm,
			HttpSession sessao) {

		sessao.removeAttribute("imovelDadosCadastrais");
		sessao.removeAttribute("imovelClientes");
		sessao.removeAttribute("enderecoImovelDadosCadastrais");
		sessao.removeAttribute("imovelSubcategorias");
		sessao.removeAttribute("idImovelPrincipalAba");
		sessao.removeAttribute("desabilitaMunicipioLocalidade");
		
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
		
		consultarImovelActionForm.setMatriculaImovelDadosCadastrais(null);
		consultarImovelActionForm.setEnderecoImovelDadosCadastrais(null);
		consultarImovelActionForm.setSituacaoAguaDadosCadastrais(null);
		consultarImovelActionForm.setSituacaoEsgotoDadosCadastrais(null);
		consultarImovelActionForm.setImovelPerfilDadosCadastrais(null);
		consultarImovelActionForm.setDespejoDadosCadastrais(null);
		consultarImovelActionForm.setAreaConstruidaDadosDadosCadastrais(null);
		consultarImovelActionForm.setTestadaLoteDadosCadastrais(null);
		consultarImovelActionForm.setVolumeReservatorioInferiorDadosCadastrais(null);
		consultarImovelActionForm.setVolumeReservatorioSuperiorDadosCadastrais(null);
		consultarImovelActionForm.setVolumePiscinaDadosCadastrais(null);
		consultarImovelActionForm.setFonteAbastecimentoDadosCadastrais(null);
		consultarImovelActionForm.setPocoTipoDadosCadastrais(null);
		consultarImovelActionForm.setDistritoOperacionalDadosCadastrais(null);
		consultarImovelActionForm.setPavimentoRuaDadosCadastrais(null);
		consultarImovelActionForm.setPavimentoCalcadaDadosCadastrais(null);
		consultarImovelActionForm.setNumeroIptuDadosCadastrais(null);
		consultarImovelActionForm.setNumeroCelpeDadosCadastrais(null);
		consultarImovelActionForm.setCoordenadaXDadosCadastrais(null);
		consultarImovelActionForm.setCoordenadaYDadosCadastrais(null);
		consultarImovelActionForm.setCadastroOcorrenciaDadosCadastrais(null);
		consultarImovelActionForm.setEloAnormalidadeDadosCadastrais(null);
		consultarImovelActionForm.setIndicadorImovelCondominioDadosCadastrais(null);
		consultarImovelActionForm.setImovelCondominioDadosCadastrais(null);
		consultarImovelActionForm.setImovelPrincipalDadosCadastrais(null);
		consultarImovelActionForm.setNumeroPontosUtilizacaoDadosCadastrais(null);
		consultarImovelActionForm.setNumeroMoradoresDadosCadastrais(null);
		consultarImovelActionForm.setTipoHabitacaoDadosCadastrais(null);
		consultarImovelActionForm.setTipoPropriedadeDadosCadastrais(null);
		consultarImovelActionForm.setTipoConstrucaoDadosCadastrais(null);
		consultarImovelActionForm.setTipoCoberturaDadosCadastrais(null);
		consultarImovelActionForm.setJardimDadosCadastrais(null);
		consultarImovelActionForm.setDivisaoEsgotoDadosCadastrais(null);
		consultarImovelActionForm.setDescricaoMunicipio(null);
		
		consultarImovelActionForm.setIndicadorNivelInstalacaoEsgotoDadosCadastrais("");
	}
}
