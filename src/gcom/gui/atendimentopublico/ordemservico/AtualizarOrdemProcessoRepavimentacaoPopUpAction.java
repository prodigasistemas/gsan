package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroOrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoPavimento;
import gcom.cadastro.imovel.FiltroPavimentoRua;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de Exibir Atualizar Negativador Exclusao Motivo
 * 
 * @author Yara Taciane
 * @created 04/01/2008
 */

public class AtualizarOrdemProcessoRepavimentacaoPopUpAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Inicializando Variaveis
		ActionForward retorno = actionMapping.findForward("atualizarOrdemProcessoRepavimentacaoPopUp");
		
		AtualizarOrdemProcessoRepavimentacaoPopUpActionForm form = (AtualizarOrdemProcessoRepavimentacaoPopUpActionForm) actionForm;

		//Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		boolean temPermissao = verificaUnidadeUsuario(usuario, fachada);
		
		if (!temPermissao) {
			throw new ActionServletException("atencao.nao_possui_permissao_para_atualizar");
		}
		
		String dataExecucao = null;
		String idPavimentoRuaRet = null;
		String areaPavimentoRuaRet = null;
		
		
		if(form.getIdPavimentoRuaRet()!= null && !"".equals(form.getIdPavimentoRuaRet())){
			idPavimentoRuaRet = form.getIdPavimentoRuaRet();
		}
		
		if(form.getAreaPavimentoRuaRet()!= null && !"".equals(form.getAreaPavimentoRuaRet())){
			areaPavimentoRuaRet = form.getAreaPavimentoRuaRet();
		}
		
		
		OrdemServicoPavimento ordemServicoPavimento = null;
		
		 if (httpServletRequest.getAttribute("ordemServicoPavimento") != null) {
			 ordemServicoPavimento = (OrdemServicoPavimento) httpServletRequest.getAttribute("ordemServicoPavimento");        	
	        
		}else if(sessao.getAttribute("ordemServicoPavimento")!= null ){
			ordemServicoPavimento = (OrdemServicoPavimento) sessao.getAttribute("ordemServicoPavimento");
		}
					
		
		
		FiltroPavimentoRua filtroPavimentoRua = new FiltroPavimentoRua();
		filtroPavimentoRua.adicionarParametro(new ParametroSimples(FiltroPavimentoRua.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));	
		if ( idPavimentoRuaRet != null && !idPavimentoRuaRet.equals("")) {
			filtroPavimentoRua.adicionarParametro(new ParametroSimples(FiltroPavimentoRua.ID,idPavimentoRuaRet));
		}
		Collection colecaoPavimentoRua = fachada.pesquisar(filtroPavimentoRua, PavimentoRua.class.getName());		
    	PavimentoRua pavimentoRuaRetorno = (PavimentoRua) Util.retonarObjetoDeColecao(colecaoPavimentoRua);

    	
    	if(form.getDataExecucao()!= null && !"".equals(form.getDataExecucao())){
			
			dataExecucao = this.validarDataExecucao( form.getDataExecucao(), ordemServicoPavimento);		
		}
    	
    	
    	ordemServicoPavimento.setPavimentoRuaRetorno(pavimentoRuaRetorno);	
        ordemServicoPavimento.setDataExecucao(Util.converteStringParaDate(dataExecucao));
		ordemServicoPavimento.setAreaPavimentoRuaRetorno(Util.formatarMoedaRealparaBigDecimal(areaPavimentoRuaRet));
		
		//
		Integer validacaoAceite = calculaPercentualMetragemEValidaRetorno( ordemServicoPavimento.getAreaPavimentoRua(), 
				ordemServicoPavimento.getAreaPavimentoRuaRetorno() ) ;
			
		

		//1.3.4.2.2.2.	Caso haja convergência entre e o tipo de pavimento 
		//              informado no retorno e o tipo de pavimento de mandado 
		if ( (ordemServicoPavimento.getPavimentoRua().getId().toString().equals(
				ordemServicoPavimento.getPavimentoRuaRetorno().getId().toString()) && 
				validacaoAceite != null && validacaoAceite.toString().equals("1")) || 
				(ordemServicoPavimento.getPavimentoRua().getId().toString().equals(
						ordemServicoPavimento.getPavimentoRuaRetorno().getId().toString()) &&						
						(ordemServicoPavimento.getAreaPavimentoRua().compareTo(ordemServicoPavimento.getAreaPavimentoRuaRetorno()) == 0) )) {
			
			//[SB0002] ? Efetuar Aceite Automático			
			ordemServicoPavimento.setIndicadorAceite(ConstantesSistema.SIM);
			ordemServicoPavimento.setDataAceite(Util.converteStringParaDate(dataExecucao));
			ordemServicoPavimento.setUsuarioAceite(Usuario.USUARIO_BATCH);
			
			if ( ordemServicoPavimento.getDescricaoMotivoAceite() != null &&
					 !ordemServicoPavimento.getDescricaoMotivoAceite().equals("") ) {
				 
				 String descricaoJaCadastrada = ordemServicoPavimento.getDescricaoMotivoAceite();
				 ordemServicoPavimento.setDescricaoMotivoAceite( descricaoJaCadastrada + " ACEITE AUTOMATICO");
				 
			 } else {
				 
				 ordemServicoPavimento.setDescricaoMotivoAceite("ACEITE AUTOMATICO");
			 }
		}
		
		if(form.getObservacao()!= null && !form.getObservacao().equals("")){
			ordemServicoPavimento.setObservacao(form.getObservacao());
		}
		
		fachada.atualizar(ordemServicoPavimento);
			
		httpServletRequest.setAttribute("fecharPopup", "OK");
		
		
		return retorno;

	}
	
	/**
	 * Verifica se usuario em Permissao para atualizar o 
	 * retorno das ordens de Serviço atraves do botão confirmar demandas.
	 * 
	 * @author Arthur Carvalho
	 * 
	 * @date 26/04/2010
	 * @param usuario
	 * @param fachada
	 * @return boolean
	 */
	 private boolean verificaUnidadeUsuario( Usuario usuario, Fachada fachada) {
	    	
	    	boolean temPermissao = false;
	    	
	    	Collection colecaoUnidadesResponsaveis = null;
	    	FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
	    	
			if ( usuario != null && usuario.getUnidadeOrganizacional() != null && 
					usuario.getUnidadeOrganizacional().getUnidadeTipo() != null && 
					usuario.getUnidadeOrganizacional().getUnidadeTipo().getId() != null &&
					(usuario.getUnidadeOrganizacional().getUnidadeTipo().getId().intValue() == 
						UnidadeTipo.UNIDADE_TIPO_REPAVIMENTADORA_ID.intValue()) ) { 
				
				filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
						FiltroUnidadeOrganizacional.ID, usuario.getUnidadeOrganizacional().getId()));
				filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
						FiltroUnidadeOrganizacional.ID_UNIDADE_TIPO,UnidadeTipo.UNIDADE_TIPO_REPAVIMENTADORA_ID));
		
				colecaoUnidadesResponsaveis = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
				
				if ( colecaoUnidadesResponsaveis != null && !colecaoUnidadesResponsaveis.isEmpty() ) {
					temPermissao = true;
				}
		    
			}
			return temPermissao;
	    }

	    /**
	 *  [SB0003] - Imprimir relação das ordens
	 *  1.331
	 * Metodo responsavel por verificar se a metragem informada no retorno esta compreendida
	 * entre o percentual de variação permitido.
	 * 
	 * @author Arthur Carvalho
	 * @date 26/07/2010
	 * @param metragem
	 * @param metragemRetono
	 * @return
	 */
	public Integer calculaPercentualMetragemEValidaRetorno(BigDecimal metragem, BigDecimal metragemRetono ) {
			Integer indicadorAceiteComCalculoPercentualConvergencia = null;
			BigDecimal percentualConvergenciaRepavimentacao = new BigDecimal(0);
			
			SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
			percentualConvergenciaRepavimentacao = sistemaParametro.getPercentualConvergenciaRepavimentacao();
			
			//1.3.3.    Total Ordens Aceitas 
			if ( percentualConvergenciaRepavimentacao != null ) { 
				 
				if ( metragem.add(metragem.multiply(percentualConvergenciaRepavimentacao).divide(
								new BigDecimal(100))).compareTo(metragemRetono) >= 0 &&
									metragem.subtract(metragem.multiply(
										percentualConvergenciaRepavimentacao).divide(
												new BigDecimal(100))).compareTo(metragemRetono) <= 0 ) {
					
					indicadorAceiteComCalculoPercentualConvergencia = 1;
				} else {
					indicadorAceiteComCalculoPercentualConvergencia = 2;
				}
			} 

			return indicadorAceiteComCalculoPercentualConvergencia;
		}
	
	/**
	 * [FS0003] ? Validar da Data de Execução.
	 * @param dataExecucao
	 * @return
	 */
	public String validarDataExecucao(String dataExecucao , OrdemServicoPavimento ordemServicoPavimento){
			
		Date dtExecucao = Util.converteStringParaDate(dataExecucao);			
		Date dataAtualSemHora = Util.formatarDataSemHora(new Date());
		
		if ( ordemServicoPavimento.getPavimentoRuaRetorno() == null ) {
			
			FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
			filtroOrdemServico.adicionarParametro( new ParametroSimples(FiltroOrdemServico.ID,  
					ordemServicoPavimento.getOrdemServico().getId()));
			
			Collection colecaoOrdemServico = Fachada.getInstancia().pesquisar(
					filtroOrdemServico, OrdemServico.class.getName());
			
			if ( colecaoOrdemServico != null ) {
				OrdemServico ordemServico = (OrdemServico) Util.retonarObjetoDeColecao(colecaoOrdemServico); 
				
				if ( dtExecucao.before(ordemServico.getDataEncerramento()) ){
					throw new ActionServletException(
							"atencao.data.execucao.menor.data.encerramento", null,
							Util.formatarData(ordemServico.getDataEncerramento())  );	
				}
			}
			
			
		} else {
			if ( ordemServicoPavimento.getIndicadorAceite() == null ) {
				
				if ( dtExecucao.before(ordemServicoPavimento.getDataExecucao()) ){
					throw new ActionServletException(
							"atencao.data.execucao.menor.data.retorno", null,
								ordemServicoPavimento.getDataExecucao().toString() );	
				}
			} else if ( ordemServicoPavimento.getIndicadorAceite().toString().equals("2") &&
					dtExecucao.before(ordemServicoPavimento.getDataAceite() ) ) {
				throw new ActionServletException(
						"atencao.data.execucao.menor.data.aceite", null,
							ordemServicoPavimento.getDataAceite().toString() );
				
			}
			
		}
		
		
		if(Util.compararData(dtExecucao, dataAtualSemHora) == 1){
			String dataAtual = Util.formatarData(new Date());
			throw new ActionServletException(
					"atencao.data.superior.data.corrente", null,
					dataAtual);				
			
		}
		
		
		
		
		
		return dataExecucao; 
	}
}
