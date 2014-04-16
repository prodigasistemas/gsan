package gcom.gui.faturamento.conta;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.Subcategoria;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.CalcularValoresAguaEsgotoHelper;
import gcom.faturamento.conta.ContaMotivoInclusao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirContaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Instância do formulário que está sendo utilizado
		InserirContaActionForm inserirContaActionForm = (InserirContaActionForm) actionForm;

		// Recebendo os dados enviados pelo formulário
		String imovelIdJSP = inserirContaActionForm.getIdImovel();
		String mesAnoContaJSP = inserirContaActionForm.getMesAnoConta();
		String vencimentoContaJSP = inserirContaActionForm.getVencimentoConta();
		Integer situacaoAguaContaJSP = new Integer(inserirContaActionForm
				.getSituacaoAguaConta());
		
		Integer situacaoEsgotoContaJSP = new Integer(inserirContaActionForm
			.getSituacaoEsgotoConta());
		
		Integer motivoInclusaoContaJSP = new Integer(inserirContaActionForm
				.getMotivoInclusaoID());
		
		String consumoAguaJSP = inserirContaActionForm.getConsumoAgua();
		String consumoEsgotoJSP = inserirContaActionForm.getConsumoEsgoto();
		String percentualEsgotoJSP = inserirContaActionForm
				.getPercentualEsgoto();
		
		Map<String, String[]> requestMap = httpServletRequest.getParameterMap();

		// Carrega as coleções de acordo com os objetos da sessão
		Collection colecaoDebitoCobrado = new Vector();
		if (sessao.getAttribute("colecaoDebitoCobrado") != null) {
			colecaoDebitoCobrado = (Collection) sessao
					.getAttribute("colecaoDebitoCobrado");
		}
		
		
		Collection colecaoCategoriaOUSubcategoria = null;
		
		if (sessao.getAttribute("colecaoCategoria") != null){
			
			colecaoCategoriaOUSubcategoria = (Collection) sessao.getAttribute("colecaoCategoria");
			
			this.atualizarQuantidadeEconomiasCategoria(colecaoCategoriaOUSubcategoria, 
			httpServletRequest);
			
		}
		else{
			
			colecaoCategoriaOUSubcategoria = (Collection) sessao.getAttribute("colecaoSubcategoria");
			
			this.atualizarQuantidadeEconomiasSubcategoria(colecaoCategoriaOUSubcategoria, 
			httpServletRequest);
			
		}
		
				
		// [FS0015] - Verificar se foi efetuado o cálculo da conta

		//==============================================================================================
		Collection<CalcularValoresAguaEsgotoHelper> valoresConta = null;
		
		
			
		//[SF0001] - Determinar Valores para Faturamento de Água e/ou Esgoto
		//********************************************************************
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
		
		valoresConta = fachada.calcularValoresConta(mesAnoContaJSP, imovelIdJSP,
		situacaoAguaContaJSP, situacaoEsgotoContaJSP,
		colecaoCategoriaOUSubcategoria, consumoAguaJSP, consumoEsgotoJSP,
		percentualEsgotoJSP, null, usuarioLogado);
			
		
		//Cálcula o valor total dos débitos de uma conta de acordo com o informado pelo usuário
        BigDecimal valorTotalDebitosConta = fachada.calcularValorTotalDebitoConta(colecaoDebitoCobrado,
        httpServletRequest.getParameterMap());
		
		
		//Totalizando os valores de água e esgoto
        BigDecimal valorTotalAgua = new BigDecimal("0");
        BigDecimal valorTotalEsgoto = new BigDecimal("0");
        
        if (valoresConta != null && !valoresConta.isEmpty()){
        	
        	Iterator valoresContaIt = valoresConta.iterator();
        	CalcularValoresAguaEsgotoHelper valoresContaObjeto = null;
        	
        	while (valoresContaIt.hasNext()){
        		
        		valoresContaObjeto = (CalcularValoresAguaEsgotoHelper) valoresContaIt.next();
        		
        		//Valor Faturado de Água
        		if (valoresContaObjeto.getValorFaturadoAguaCategoria() != null){
        			valorTotalAgua = valorTotalAgua.add(valoresContaObjeto.getValorFaturadoAguaCategoria());
        		}
        		
        		//Valor Faturado de Esgoto
        		if (valoresContaObjeto.getValorFaturadoEsgotoCategoria() != null){
        			valorTotalEsgoto = valorTotalEsgoto.add(valoresContaObjeto.getValorFaturadoEsgotoCategoria());
        		}
     
        	}
            
        }
        
        
        BigDecimal valorTotalConta = new BigDecimal("0.00");
        
        valorTotalConta = valorTotalConta.add(valorTotalAgua);
        valorTotalConta = valorTotalConta.add(valorTotalEsgoto);
        valorTotalConta = valorTotalConta.add(valorTotalDebitosConta);

        
        // [FS0008] - Verificar valor da conta igual a zero
		if (valorTotalConta.equals(new BigDecimal("0.00"))) {
			throw new ActionServletException("atencao.valor_conta_igual_zero");
		}
		//**************************************************************************************
		
		
		
		
		//Invertendo o formato para yyyyMM (sem a barra)
		mesAnoContaJSP = Util.formatarMesAnoParaAnoMesSemBarra(mesAnoContaJSP);



		// Carregando as informações do imóvel
		FiltroImovel filtroImovel = new FiltroImovel();

		// Objetos que serão retornados pelo hibernate
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra.setorComercial.localidade");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra.rota");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("consumoTarifa");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
		

		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
				imovelIdJSP));

		Collection<Imovel> colecaoImovel = fachada.pesquisar(filtroImovel,
				Imovel.class.getName());

		// [FS0001] - Verificar existência da matrícula do imóvel
		if (colecaoImovel == null || colecaoImovel.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"imóvel");
		}

		Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);

		// LigacaoAguaSituacao
		LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
		ligacaoAguaSituacao.setId(situacaoAguaContaJSP);

		// LigacaoEsgotoSituacao
		LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
		ligacaoEsgotoSituacao.setId(situacaoEsgotoContaJSP);

		// Data de vencimento da conta
		// Para auxiliar na formatação de uma data
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

		Date dataVencimentoConta;

		try {
			dataVencimentoConta = formatoData.parse(vencimentoContaJSP);
		} catch (ParseException ex) {
			dataVencimentoConta = null;
		}

		// Motivo da inclusão da conta
		ContaMotivoInclusao contaMotivoInclusao = new ContaMotivoInclusao();
		contaMotivoInclusao.setId(motivoInclusaoContaJSP);
		
		//CRC4202 - adicionado por Vivianne Sousa - 21/09/2010 - analista:Adriana Ribeiro
		Integer leituraAnterior = null;
		if(inserirContaActionForm.getLeituraAnteriorAgua() != null && !inserirContaActionForm.getLeituraAnteriorAgua().trim().equalsIgnoreCase("")) {	
			leituraAnterior = new Integer(inserirContaActionForm.getLeituraAnteriorAgua());
		}
		Integer leituraAtual = null;
		if(inserirContaActionForm.getLeituraAtualAgua() != null && !inserirContaActionForm.getLeituraAtualAgua().trim().equalsIgnoreCase("")) {	
			leituraAtual = new Integer(inserirContaActionForm.getLeituraAtualAgua());
		}
		
		
		// [SF002] - Gerar dados da conta, [SF003] - Gerar os débitos cobrados

        Integer idConta = fachada.inserirConta(new Integer(mesAnoContaJSP), imovel,
        colecaoDebitoCobrado, ligacaoAguaSituacao, ligacaoEsgotoSituacao,
        colecaoCategoriaOUSubcategoria, consumoAguaJSP, consumoEsgotoJSP,
        percentualEsgotoJSP, dataVencimentoConta, valoresConta,
        contaMotivoInclusao, requestMap, usuarioLogado,
        leituraAnterior,leituraAtual);
		

        
		montarPaginaSucesso(httpServletRequest, "Conta " + Util.formatarMesAnoReferencia(new Integer(mesAnoContaJSP).intValue())
				+ " do imóvel " + imovel.getId() + " inserida com sucesso.",
				"Inserir outra Conta",
				"exibirInserirContaAction.do?menu=sim",
				"exibirManterContaAction.do?idImovelRequest="
				+ imovel.getId() , "Atualizar Conta(s) do Imovel " + imovel.getId(), 
				"Emitir 2ª Via de Conta", "gerarRelatorio2ViaContaAction.do?idConta="+ idConta);

		return retorno;
	}
	
	
	
	private Integer atualizarQuantidadeEconomiasCategoria(Collection colecaoCategorias, HttpServletRequest httpServletRequest){
		
		/*
		 * Atualizando a quantidade de economias por categoria de acordo com o
		 * informado pelo usuário
		 */ 
		
		Integer qtdEconnomia = 0;
		
		if (colecaoCategorias != null && !colecaoCategorias.isEmpty()){
			
			Iterator colecaoCategoriaIt = colecaoCategorias.iterator();
			Categoria categoria;
			Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
			String qtdPorEconomia;
			
			while (colecaoCategoriaIt.hasNext()) {
				categoria = (Categoria) colecaoCategoriaIt.next();

				if (requestMap.get("categoria" + categoria.getId().intValue()) != null) {

					qtdPorEconomia = (requestMap.get("categoria"
							+ categoria.getId().intValue()))[0];

					if (qtdPorEconomia == null
							|| qtdPorEconomia.equalsIgnoreCase("")) {

						throw new ActionServletException(
								"atencao.campo_texto.obrigatorio", null,
								"Quantidade de economias");
					}

					categoria.setQuantidadeEconomiasCategoria(new Integer(
							qtdPorEconomia));
					
					qtdEconnomia = Util.somaInteiros(qtdEconnomia,new Integer(qtdPorEconomia));
				}
			}
		}
		
		return qtdEconnomia;
	}
	
	
	
	private Integer atualizarQuantidadeEconomiasSubcategoria(Collection colecaoSubcategorias, HttpServletRequest httpServletRequest){
		
		/*
		 * Atualizando a quantidade de economias por subcategoria de acordo com o
		 * informado pelo usuário
		 */ 
		
		Integer qtdEconnomia = 0;
		
		if (colecaoSubcategorias != null && !colecaoSubcategorias.isEmpty()){
			
			Iterator colecaoSubcategoriaIt = colecaoSubcategorias.iterator();
			Subcategoria subcategoria;
			Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
			String qtdPorEconomia;
			
			while (colecaoSubcategoriaIt.hasNext()) {
				subcategoria = (Subcategoria) colecaoSubcategoriaIt.next();

				if (requestMap.get("subcategoria" + subcategoria.getId().intValue()) != null) {

					qtdPorEconomia = (requestMap.get("subcategoria"
							+ subcategoria.getId().intValue()))[0];

					if (qtdPorEconomia == null
							|| qtdPorEconomia.equalsIgnoreCase("")) {

						throw new ActionServletException(
								"atencao.campo_texto.obrigatorio", null,
								"Quantidade de economias");
					}

					subcategoria.setQuantidadeEconomias(new Integer(
							qtdPorEconomia));
					
					qtdEconnomia = Util.somaInteiros(qtdEconnomia,new Integer(qtdPorEconomia));
				}
			}
		}
		
		return qtdEconnomia;
	}
	
}
