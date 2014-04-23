package gcom.gui.micromedicao;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaCriterio;
import gcom.faturamento.FaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.Rota;
import gcom.micromedicao.leitura.FiltroLeituraTipo;
import gcom.micromedicao.leitura.FiltroLeiturista;
import gcom.micromedicao.leitura.LeituraTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action form de manter rota
 * 
 * @author Vivianne Sousa
 * @created03/04/2006
 */
public class AtualizarRotaAction extends GcomAction {
	/**
	 * @author Vivianne Sousa
	 * @date 03/04/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	InserirRotaActionForm inserirRotaActionForm = (InserirRotaActionForm) actionForm;
        ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        HttpSession sessao = httpServletRequest.getSession(false);
    
        String idRota = (String)sessao.getAttribute("idRegistroAtualizacao");

        String codigoRota =  inserirRotaActionForm.getCodigoRota();
        String idLocalidade = inserirRotaActionForm.getIdLocalidade();
		String codigoSetorComercial = inserirRotaActionForm.getCodigoSetorComercial();
		String idCobrancaGrupo = inserirRotaActionForm.getCobrancaGrupo(); 			
		String idFaturamentoGrupo = inserirRotaActionForm.getFaturamentoGrupo(); 				
		String idLeituraTipo = inserirRotaActionForm.getLeituraTipo(); 
		String idEmpresaLeituristica = inserirRotaActionForm.getEmpresaLeituristica(); 
		String idEmpresaCobranca = inserirRotaActionForm.getEmpresaCobranca();
		String idCobrancaCriterio = inserirRotaActionForm.getCobrancaCriterio();
		String indicadorAjusteConsumo = inserirRotaActionForm.getIndicadorAjusteConsumo();
        String numeroDiasConsumoAjuste = inserirRotaActionForm.getNumeroDiasConsumoAjuste();
		String indicadorFiscalizarCortado = inserirRotaActionForm.getIndicadorFiscalizarCortado();			
		String indicadorFiscalizarSuprimido = inserirRotaActionForm.getIndicadorFiscalizarSuprimido();
		String indicadorTransmissaoOffline = inserirRotaActionForm.getIndicadorTransmissaoOffline();
		String indicadorGerarFalsaFaixa = inserirRotaActionForm.getIndicadorGerarFalsaFaixa();			
		String indicadorGerarFiscalizacao = inserirRotaActionForm.getIndicadorGerarFiscalizacao();			
		String indicadorUso = inserirRotaActionForm.getIndicadorUso();
        String idLeiturista = inserirRotaActionForm.getIdLeiturista();
        String idEmpresaEntregaContas = inserirRotaActionForm.getEmpresaEntregaContas();
        String indicadorRotaAlternativa = inserirRotaActionForm.getIndicadorRotaAlternativa();
        String indicadorSequencialLeitura = inserirRotaActionForm.getIndicadorSequencialLeitura();
        String indicadorImpressaoTermicaFinalGrupo = inserirRotaActionForm.getIndicadorImpressaoTermicaFinalGrupo();
        
		
		String percentualGeracaoFaixaFalsa = null ;
		if (inserirRotaActionForm.getPercentualGeracaoFaixaFalsa() != null){
			percentualGeracaoFaixaFalsa = inserirRotaActionForm.getPercentualGeracaoFaixaFalsa().toString().
			replace(",", ".");
		}
		
		String percentualGeracaoFiscalizacao = null ;
		if (inserirRotaActionForm.getPercentualGeracaoFiscalizacao() != null){
			percentualGeracaoFiscalizacao = inserirRotaActionForm.getPercentualGeracaoFiscalizacao().toString().
			replace(",", ".");
		}
		
		String numeroLimiteImoveis = null;
		if(inserirRotaActionForm.getNumeroLimiteImoveis() != null 
				&& !inserirRotaActionForm.getNumeroLimiteImoveis().equals("")){
			
			numeroLimiteImoveis = inserirRotaActionForm.getNumeroLimiteImoveis();
		}
		
		//Critério de Cobrança
		Collection collectionRotaAcaoCriterio = null;
	       
        if (sessao.getAttribute("collectionRotaAcaoCriterio") != null) {
        	collectionRotaAcaoCriterio = (Collection) sessao
                    .getAttribute("collectionRotaAcaoCriterio");
        }
		
        
        validacaoRota (idLocalidade, codigoSetorComercial, codigoRota, idCobrancaGrupo, idFaturamentoGrupo, idLeituraTipo
	    		, idEmpresaLeituristica, idEmpresaEntregaContas, indicadorFiscalizarCortado
	    		, indicadorFiscalizarSuprimido, indicadorTransmissaoOffline, indicadorGerarFalsaFaixa, indicadorGerarFiscalizacao
	    		, percentualGeracaoFaixaFalsa, percentualGeracaoFiscalizacao, idLeiturista
	    		, numeroLimiteImoveis, inserirRotaActionForm, indicadorUso
	    		, collectionRotaAcaoCriterio,httpServletRequest,indicadorSequencialLeitura, numeroDiasConsumoAjuste, indicadorImpressaoTermicaFinalGrupo);
		
        if(inserirRotaActionForm.getNumeroLimiteImoveis() == null 
				|| inserirRotaActionForm.getNumeroLimiteImoveis().equals("")){
			
			numeroLimiteImoveis = null;
		}
        
        Rota rotaNova =  new Rota();

        rotaNova.setId(new Integer(idRota));
        rotaNova.setCodigo(new Short(codigoRota)); 
        rotaNova.setIndicadorAjusteConsumo(new Integer(indicadorAjusteConsumo).shortValue());
        
        if(numeroDiasConsumoAjuste != null && !numeroDiasConsumoAjuste.equals("")){
            
            rotaNova.setNumeroDiasConsumoAjuste( new Integer(numeroDiasConsumoAjuste) );
            
        }else{
            rotaNova.setNumeroDiasConsumoAjuste(null);
        }

        rotaNova.setIndicadorFiscalizarCortado(new Integer(indicadorFiscalizarCortado).shortValue());
        rotaNova.setIndicadorFiscalizarSuprimido(new Integer(indicadorFiscalizarSuprimido).shortValue());
        rotaNova.setIndicadorTransmissaoOffline(new Integer(indicadorTransmissaoOffline).shortValue());
        rotaNova.setIndicadorSequencialLeitura(new Integer(indicadorSequencialLeitura));
        rotaNova.setIndicadorGerarFalsaFaixa(new Integer(indicadorGerarFalsaFaixa).shortValue());
        rotaNova.setIndicadorImpressaoTermicaFinalGrupo(new Integer(indicadorImpressaoTermicaFinalGrupo).shortValue());
        
        if(percentualGeracaoFaixaFalsa != null && !percentualGeracaoFaixaFalsa.equals("")) {
            rotaNova.setPercentualGeracaoFaixaFalsa(new BigDecimal(percentualGeracaoFaixaFalsa));	
        }
        
        rotaNova.setIndicadorGerarFiscalizacao(new Integer(indicadorGerarFiscalizacao).shortValue());
                     
        if(percentualGeracaoFiscalizacao != null && !percentualGeracaoFiscalizacao.equals("")) {
        	 rotaNova.setPercentualGeracaoFiscalizacao(new BigDecimal(percentualGeracaoFiscalizacao));	
        }        
		
        SetorComercial setorComercial = null;
        if(codigoSetorComercial != null && !codigoSetorComercial.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	
        	//Filtro para descobrir id do setor comercial
        	FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

        	filtroSetorComercial.adicionarParametro(
        		new ParametroSimples(
    				FiltroSetorComercial.ID_LOCALIDADE, 
    				new Integer(idLocalidade))); 

    		filtroSetorComercial.adicionarParametro(
    			new ParametroSimples(
    				FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, 
    				new Integer(codigoSetorComercial)));

    		Collection<SetorComercial> colectionSetorComerciais = 
    			this.getFachada().pesquisar(
    				filtroSetorComercial, 
    				SetorComercial.class.getName());
        	
    		Integer idSetorComercial =  
    			colectionSetorComerciais.iterator().next().getId();
    		
    		
        	setorComercial = new SetorComercial();
        	setorComercial.setId(new Integer (idSetorComercial));

        	rotaNova.setSetorComercial(setorComercial);

		}
		
        if(indicadorRotaAlternativa != null){
        	SetorComercial setorComercialTemp = rotaNova.getSetorComercial();
        	
        	if(setorComercialTemp != null){
        		if(!this.verificaExistenciaQuadraAtivaParaRota(idRota) && !indicadorRotaAlternativa.equals("2")){
        			rotaNova.setIndicadorRotaAlternativa(new Integer(indicadorRotaAlternativa).shortValue());		
            	}else if(indicadorRotaAlternativa.equals("2")){
            		rotaNova.setIndicadorRotaAlternativa(new Integer(indicadorRotaAlternativa).shortValue());
            	}else{
            		throw new ActionServletException("atencao.rota_nao_pode_virar_alternativa");
            	}
        	}	  	
        }
	
        CobrancaGrupo cobrancaGrupo = null;
        if(idCobrancaGrupo != null && !idCobrancaGrupo.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	cobrancaGrupo = new CobrancaGrupo();
        	cobrancaGrupo.setId(new Integer (idCobrancaGrupo));
        	rotaNova.setCobrancaGrupo(cobrancaGrupo);
		}
        

        FaturamentoGrupo faturamentoGrupo = null;
        if(idFaturamentoGrupo != null && !idFaturamentoGrupo.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){

        	faturamentoGrupo = new FaturamentoGrupo();
        	faturamentoGrupo.setId(new Integer (idFaturamentoGrupo));
        	
        	rotaNova.setFaturamentoGrupo(faturamentoGrupo);
		}

        LeituraTipo leituraTipo = null;
        if(idLeituraTipo != null && !idLeituraTipo.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	leituraTipo = new LeituraTipo();
        	leituraTipo.setId(new Integer (idLeituraTipo));
        	rotaNova.setLeituraTipo(leituraTipo);
		}

        Empresa empresaLeituristica = null;
        if(idEmpresaLeituristica != null && !idEmpresaLeituristica.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
          	empresaLeituristica = new Empresa();
        	empresaLeituristica.setId(new Integer (idEmpresaLeituristica));
        	rotaNova.setEmpresa(empresaLeituristica);
		}
        
        Empresa empresaEntregaContas = null;
        if(idEmpresaEntregaContas != null && !idEmpresaEntregaContas.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	empresaEntregaContas = new Empresa();
        	empresaEntregaContas.setId(new Integer (idEmpresaEntregaContas));
        	rotaNova.setEmpresaEntregaContas(empresaEntregaContas);
		}
        
        Empresa empresaCobranca = null;
        if (idEmpresaCobranca != null && !idEmpresaCobranca.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
        	empresaCobranca = new Empresa();
        	empresaCobranca.setId(new Integer (idEmpresaCobranca));
        	rotaNova.setEmpresaCobranca(empresaCobranca);
        }

        CobrancaCriterio cobrancaCriterio = null;
        if(idCobrancaCriterio != null && !idCobrancaCriterio.equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	cobrancaCriterio = new CobrancaCriterio();
        	cobrancaCriterio.setId(new Integer (idCobrancaCriterio));
		}
                
        rotaNova.setIndicadorUso(new Integer(indicadorUso).shortValue());
        rotaNova.setUltimaAlteracao(Util.converteStringParaDateHora(inserirRotaActionForm.getUltimaAlteracao()));
        rotaNova.setIndicadorAjusteConsumo( Short.parseShort( inserirRotaActionForm.getIndicadorAjusteConsumo() ) );
        
        if ( idLeiturista != null && !idLeiturista.equals( "" )  ){
            Leiturista leiturista = new Leiturista();
            leiturista.setId( Integer.parseInt( idLeiturista ) );
            rotaNova.setLeiturista( leiturista );
        }    
        
        //Verificar a existencia de Rota associada
    	FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
    	filtroSetorComercial.adicionarParametro( new ParametroSimples ( FiltroSetorComercial.ID_LOCALIDADE,
    			new Integer(idLocalidade) ) );
    	filtroSetorComercial.adicionarParametro( new ParametroSimples ( FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
    			new Integer(codigoSetorComercial) ) );
    	
    	Collection setorComercialAssociadoRota = this.getFachada()
    			.pesquisar( filtroSetorComercial, SetorComercial.class.getName() );

    	Iterator iteratorComercialAssociadoRota = setorComercialAssociadoRota.iterator();
    	SetorComercial setor = null;
    
    	while ( iteratorComercialAssociadoRota.hasNext() ) {
    	
    		setor = (SetorComercial) iteratorComercialAssociadoRota.next();
            
    		if ( setor.getIndicadorSetorAlternativo().equals( ConstantesSistema.INDICADOR_USO_DESATIVO) &&
    				new Short(indicadorRotaAlternativa).equals( ConstantesSistema.INDICADOR_USO_ATIVO) ) {
    			
    			throw new ActionServletException("atencao.setor_comercial_rota_nao_alternativo");
    		}
    		
    		if ( setor.getIndicadorSetorAlternativo().equals( ConstantesSistema.INDICADOR_USO_ATIVO) &&
    				new Short(indicadorRotaAlternativa).equals( ConstantesSistema.INDICADOR_USO_DESATIVO) ) {
    			
    			throw new ActionServletException("atencao.setor_comercial_rota_alternativo");
    		}
    		
    		
    	}
    	
    	// Número Limite Imoveis por Rota.
		if(numeroLimiteImoveis != null 
				&& !numeroLimiteImoveis.equals("")){
			
			rotaNova.setNumeroLimiteImoveis( new Integer(numeroLimiteImoveis));
		}
     
        //------------ REGISTRAR TRANSAÇÃO ----------------
        /*rotaNova.setOperacaoEfetuada(operacaoEfetuada);
        rotaNova.adicionarUsuario(Usuario.USUARIO_TESTE, 
        		UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
        registradorOperacao.registrarOperacao(rotaNova);*/
        //------------ REGISTRAR TRANSAÇÃO ----------------  
        
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        
        this.getFachada().atualizarRota(rotaNova,idLocalidade,collectionRotaAcaoCriterio, usuarioLogado);
               
		sessao.removeAttribute("collectionRotaAcaoCriterio");
		sessao.removeAttribute("idRegistroAtualizacao");

		
        //Monta a página de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
            montarPaginaSucesso(httpServletRequest, "Rota de código "
                    + inserirRotaActionForm.getCodigoRota() 
                    + " da localidade "
                    + idLocalidade
                    + " do setor "
                    + codigoSetorComercial
                    + " atualizada com sucesso.",
                    "Realizar outra Manutenção de Rota", "exibirFiltrarRotaAction.do?desfazer=S");
        }
        
        
       return retorno;
    }
    
    
    
    private void validacaoRota (String idLocalidade
    		,String codigoSetorComercial
    		,String codigoRota
    		,String idCobrancaGrupo
    		,String idFaturamentoGrupo
    		,String idLeituraTipo
    		,String idEmpresaLeituristica
    		,String idEmpresaEntregaContas
    		,String indicadorFiscalizarCortado
    		,String indicadorFiscalizarSuprimido
    		,String indicadorTransmissaoOffline
    		,String indicadorGerarFalsaFaixa
    		,String indicadorGerarFiscalizacao
    		,String percentualGeracaoFaixaFalsa		
    		,String percentualGeracaoFiscalizacao
            ,String idLeiturista
            ,String numeroLimiteImoveis
            ,InserirRotaActionForm form
    		,String indicadorUso
    		,Collection collectionRotaAcaoCriterioNovos
    		,HttpServletRequest httpServletRequest 
    		,String indicadorSequencialLeitura
            ,String numeroDiasConsumoAjuste,
            String indicadorImpressaoTermicaFinalGrupo){
    	
    	//Localidade é obrigatório.
		if ((idLocalidade == null) || (idLocalidade.equals(""))) {
			httpServletRequest.setAttribute("nomeCampo","idLocalidade");
			throw new ActionServletException("atencao.localidade_nao_informada");
		}else if (Util.validarValorNaoNumerico(idLocalidade)){
			//Localidade não numérico.
			httpServletRequest.setAttribute("nomeCampo","idLocalidade");
			throw new ActionServletException("atencao.nao.numerico",
					null,"Localidade");		
		}
		
		// Setor Comercial é obrigatório.
		if ((codigoSetorComercial == null) || (codigoSetorComercial.equals(""))) {
			httpServletRequest.setAttribute("nomeCampo","codigoSetorComercial");
			throw new ActionServletException(
					"atencao.codigo_setor_comercial_nao_informado");
		}else if(Util.validarValorNaoNumerico(codigoSetorComercial)){
			//Setor Comercial não numérico.
			httpServletRequest.setAttribute("nomeCampo","codigoSetorComercial");
			throw new ActionServletException("atencao.nao.numerico",
					null,"Setor Comercial");		
		}else{
			verificaExistenciaCodSetorComercial(idLocalidade,
					codigoSetorComercial,httpServletRequest);
		}
        
        if(Util.validarValorDiferenteZero(numeroDiasConsumoAjuste)){
            //Quantidade de Ajuste de Consumo não numérico ou zero.
            httpServletRequest.setAttribute("nomeCampo","numeroDiasConsumoAjuste");
            throw new ActionServletException("atencao.nao.numerico",
                    null,"Quantidade de Dias de Consumo");    
        }   
        
        if(Util.validarValorDiferenteZero(numeroLimiteImoveis)){
            //Numero limite de Imóveis não numérico ou zero.
            httpServletRequest.setAttribute("nomeCampo","numeroLimiteImoveis");
            throw new ActionServletException("atencao.nao.numerico",
                    null,"Limite de Imóveis por Rota");  
        }
	
		 //O código da rota é obrigatório. 
		if (( codigoRota == null) || (codigoRota.equals(""))){ 
			httpServletRequest.setAttribute("nomeCampo","codigoRota");
			throw new ActionServletException(
				"atencao.rota_codigo_nao_informado"); 
		}

		// O grupo de faturamento é obrigatório.
		if ((idFaturamentoGrupo == null)
				|| (idFaturamentoGrupo.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","faturamentoGrupo");
			throw new ActionServletException(
					"atencao.faturamento_grupo_nao_informado");
		}
		
		// O grupo de cobrança é obrigatório.
		if ((idCobrancaGrupo == null)
				|| (idCobrancaGrupo.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","cobrancaGrupo");
			throw new ActionServletException(
					"atencao.cobranca_grupo_nao_informado");
		}

		// O tipo de leitura é obrigatório.
		if ((idLeituraTipo == null)
				|| (idLeituraTipo.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","leituraTipo");
			throw new ActionServletException("atencao.leitura_tipo_nao_informado");
		}

		// A empresa leituristica é obrigatória.
		if ((idEmpresaLeituristica == null)
				|| (idEmpresaLeituristica.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","empresaLeituristica");
			throw new ActionServletException(
					"atencao.empresa_leituristica_nao_informado");
		}

		//A empresa de entrega das contas é obrigatória.
		if ((idEmpresaEntregaContas == null)
				|| (idEmpresaEntregaContas.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","idEmpresaEntregaContas");
			throw new ActionServletException(
					"atencao.empresa_entrega_contas_nao_informado");
		}
		
		
		// O identificador de fiscalizar cortado é obrigatório.
		if ((indicadorFiscalizarCortado == null)
				|| (indicadorFiscalizarCortado.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","indicadorFiscalizarCortado");
			throw new ActionServletException(
					"atencao.fiscaliza_cortados_nao_informado");
		}

		// O identificador de fiscalizar suprido é obrigatório.
		if ((indicadorFiscalizarSuprimido == null)
				|| (indicadorFiscalizarSuprimido.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","indicadorFiscalizarSuprimido");
			throw new ActionServletException(
					"atencao.fiscaliza_suprimidos_nao_informado");
		}
		
		// O identificador de Transmissao Offline é obrigatório.
		if ((indicadorTransmissaoOffline == null)
				|| (indicadorTransmissaoOffline.equals(""+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","indicadorTransmissaoOffline");
			throw new ActionServletException(
					"atencao.indicador_transmissao_offline_nao_informado");
		}
		
		// O identificador de Transmissao Offline é obrigatório.
		if ((indicadorSequencialLeitura == null)
				|| (indicadorSequencialLeitura.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo",
					"indicadorSequencialLeitura");
			throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio",null,"Indicador Sequencial de Leitura");
		}
		
		if ((indicadorImpressaoTermicaFinalGrupo == null)
				|| (indicadorImpressaoTermicaFinalGrupo.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo",
					"indicadorImpressaoTermicaFinalGrupo");
			throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio",null,"Indicador Impressao Termica");
		}
		
		// Sistema Parametro vai ser utilizado na validação de 
		//Percentual de Faixa Falsa e
		//Percentual de Fiscalização de Leitura 
		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		
		Collection<SistemaParametro> collectionSistemaParametro = this.getFachada()
				.pesquisar(filtroSistemaParametro,
						SistemaParametro.class.getName());
		SistemaParametro sistemaParametro = collectionSistemaParametro.iterator().next();
		
		
		//O identificador de gerar faixa falsa é obrigatório.
		if ((indicadorGerarFalsaFaixa == null)
				|| (indicadorGerarFalsaFaixa.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","indicadorGerarFalsaFaixa");
			throw new ActionServletException("atencao.gera_faixa_nao_informado");
		}else if((percentualGeracaoFaixaFalsa == null || percentualGeracaoFaixaFalsa.equalsIgnoreCase("")) && 
				indicadorGerarFalsaFaixa.equals("" + ConstantesSistema.SIM) 
				&& sistemaParametro.getIndicadorUsoFaixaFalsa().equals(SistemaParametro.INDICADOR_USO_FAIXA_FALSA_ROTA)) {
			// Percentual de Faixa Falsa é obrigatório
			//caso o indicador de geração de fiscalização de leitura seja SIM e
			//o indicador de uso do percentual para geração de fiscalização de leitura 
			//na tabela SISTEMA_PARAMETRO (PARM_ICUSOPERCENTUALFISCALIZACAOLEITURA) 
			//corresponda ao valor 2=USA PERCENTUAL DA ROTA
		
			httpServletRequest.setAttribute("nomeCampo","percentualGeracaoFaixaFalsa");
			throw new ActionServletException(
					"atencao.percentual_faixa_falsa_nao_informado");
		}

		
		// O identificador de gerar fiscalização é obrigatório.
		if ((indicadorGerarFiscalizacao == null)
				|| (indicadorGerarFiscalizacao.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			httpServletRequest.setAttribute("nomeCampo","indicadorGerarFiscalizacao");
			throw new ActionServletException(
					"atencao.gera_fiscalizacao_leitura_nao_informado");
		}else if((percentualGeracaoFiscalizacao == null || percentualGeracaoFiscalizacao.equalsIgnoreCase("")) && 
				indicadorGerarFiscalizacao.equals("" + ConstantesSistema.SIM) 
				&& sistemaParametro.getIndicadorPercentualFiscalizacaoLeitura().equals(SistemaParametro.INDICADOR_PERCENTUAL_FISCALIZACAO_LEITURA_ROTA)) {
			// Percentual de Fiscalização de Leitura é obrigatório
			// caso o indicador de geração de faixa falsa seja SIM e 
			//o indicador de uso do percentual para geração da faixa falsa 
			//na tabela SISTEMA_PARAMETRO (PARM_ICUSOPERCENTUALFAIXAFALSA) 
			//corresponda ao valor 2=USA PERCENTUAL DA ROTA
			httpServletRequest.setAttribute("nomeCampo","percentualGeracaoFiscalizacao");
			   throw new ActionServletException(
					"atencao.percentual_fiscalizacao_leitura_nao_informado");
		}
		

		//[FS0010] Verificar inexistência de alguma ação de cobrança
		if (collectionRotaAcaoCriterioNovos == null){
			//É necessário informar o critério de cobrança da rota para todas as ações de cobrança
			throw new ActionServletException(
			"atencao.criterio_cobranca_rota.informar");
			
		}
		FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
		filtroCobrancaAcao.adicionarParametro(new ParametroSimples
				(FiltroCobrancaCriterio.INDICADOR_USO,ConstantesSistema.SIM));
		Collection<CobrancaGrupo> collectionCobrancaAcao = 
			this.getFachada().pesquisar(filtroCobrancaAcao, CobrancaAcao.class.getName());
		
		if (collectionRotaAcaoCriterioNovos.size() < collectionCobrancaAcao.size()){
			//É necessário informar o critério de cobrança da rota para todas as ações de cobrança
			throw new ActionServletException(
			"atencao.criterio_cobranca_rota.informar");	
		}
        
        // [FS0012] - Verificar leiturista na empresa informada
        if ( idLeituraTipo != null && !idLeituraTipo.equals( "" ) && idLeiturista != null && !idLeiturista.equals( "" )  ){
            FiltroLeiturista filtro = new FiltroLeiturista();
            filtro.adicionarParametro( new ParametroSimples( FiltroLeiturista.ID, idLeiturista ) );
            Collection<Leiturista> colLeiturista = this.getFachada().pesquisar( filtro, Leiturista.class.getName() );
            
            if ( colLeiturista.size() > 0 ){
                Leiturista leiturista = colLeiturista.iterator().next();
                
                // Se a empresa do leiturista for diferente da empresa informada
                if ( idEmpresaLeituristica != null && !idEmpresaLeituristica.equals( leiturista.getEmpresa().getId().toString() ) ){
                    throw new ActionServletException("atencao.leiturista.empresa_leitura");
                }
            } else {
                throw new ActionServletException("atencao.leiturista.informar");
            }
        }
        
        // [FS0013] - Verificar obrigatóridade do leiturista para o tipo de leitura informado
        if ( (LeituraTipo.CELULAR_MOBILE.toString().equals( idLeituraTipo )||
				LeituraTipo.LEITURA_E_ENTRADA_SIMULTANEA.toString().equals( idLeituraTipo )) &&
             ( idLeiturista == null || idLeiturista.equals( "" ) ) ){
            FiltroLeituraTipo filtro = new FiltroLeituraTipo();
            filtro.adicionarParametro( new ParametroSimples( FiltroLeituraTipo.ID, idLeituraTipo ) );
            Collection<LeituraTipo> colLeituraTipo = this.getFachada().pesquisar( filtro, LeituraTipo.class.getName() );
            LeituraTipo leituraTipo = colLeituraTipo.iterator().next();       
            
            throw new ActionServletException("atencao.leiturista.tipo_leitura", null, leituraTipo.getDescricao() );
        }
        
		// [FS0013] ? Validar Limite de  Divisão de Imóveis por Tipo de Leitura.
		if(numeroLimiteImoveis != null && !numeroLimiteImoveis.equals("") 
				&& !LeituraTipo.LEITURA_E_ENTRADA_SIMULTANEA.toString().equals( idLeituraTipo )){
			
			form.setNumeroLimiteImoveis("");
			throw new ActionServletException("atencao.tipo_leitura_divisao_rota", null, "");
		}
    }

    
    
    private void verificaExistenciaCodSetorComercial(String idDigitadoEnterLocalidade,
			String codigoDigitadoEnterSetorComercial,HttpServletRequest httpServletRequest) {

	//Verifica se o código do Setor Comercial foi digitado
	if ((codigoDigitadoEnterSetorComercial != null && !codigoDigitadoEnterSetorComercial.toString()
			.trim().equalsIgnoreCase(""))&& (idDigitadoEnterLocalidade != null && 
			!idDigitadoEnterLocalidade.toString().trim().equalsIgnoreCase(""))) {
	
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		if (idDigitadoEnterLocalidade != null
			&& !idDigitadoEnterLocalidade.toString().trim().equalsIgnoreCase("")) {
			
			filtroSetorComercial.adicionarParametro(new ParametroSimples(
			FiltroSetorComercial.ID_LOCALIDADE, new Integer(
			idDigitadoEnterLocalidade)));
		}
		
		filtroSetorComercial.adicionarParametro(new ParametroSimples(
		FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(
		codigoDigitadoEnterSetorComercial)));
		
		Collection<SetorComercial> setorComerciais = this.getFachada().pesquisar(
		filtroSetorComercial, SetorComercial.class.getName());
		
		
		if (setorComerciais == null || setorComerciais.isEmpty()) {
			//o setor comercial não foi encontrado
			//Setor Comercial não existe para esta localidade
			httpServletRequest.setAttribute("nomeCampo","codigoSetorComercial");
			throw new ActionServletException(
			"atencao.setor_comercial_nao_existe");				
		
		}
		
	
	}
	
	}

    
    private boolean verificaExistenciaQuadraAtivaParaRota(String idRota){
    	
    	boolean retorno = false;

    	FiltroQuadra filtroQuadra = new FiltroQuadra();
    	filtroQuadra.adicionarParametro(
    		new ParametroSimples(
    			FiltroQuadra.ROTA_ID,
    			idRota));
    	filtroQuadra.adicionarParametro(
       		new ParametroSimples(
       			FiltroQuadra.INDICADORUSO,
       			ConstantesSistema.INDICADOR_USO_ATIVO));
    	
    	Collection colecaoQuadra = this.getFachada().pesquisar(
    			filtroQuadra, Quadra.class.getName());
    	if(colecaoQuadra != null && !colecaoQuadra.isEmpty()){
    		retorno = true;
    	}
    	
    	return retorno;
    }


}
 
