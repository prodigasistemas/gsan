package gcom.gui.faturamento.conta;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.CalcularValoresAguaEsgotoHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class CalcularValoresContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirInserirConta");
        
        Fachada fachada = Fachada.getInstancia();

        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        //Instância do formulário que está sendo utilizado
        InserirContaActionForm inserirContaActionForm = (InserirContaActionForm) actionForm;
        
        //Recebendo os dados enviados pelo formulário
        String imovelID = inserirContaActionForm.getIdImovel();
        String mesAnoConta = inserirContaActionForm.getMesAnoConta();
        Integer situacaoAguaConta = new Integer(inserirContaActionForm
		.getSituacaoAguaConta());

		Integer situacaoEsgotoConta = new Integer(inserirContaActionForm
			.getSituacaoEsgotoConta());
		String consumoAgua = inserirContaActionForm.getConsumoAgua();
        String consumoEsgoto = inserirContaActionForm.getConsumoEsgoto();
        String percentualEsgoto = inserirContaActionForm.getPercentualEsgoto();
        
        //Carrega as coleções de acordo com os objetos da sessão
        Collection colecaoDebitoCobrado = null;
        if (sessao.getAttribute("colecaoDebitoCobrado") != null){
        	colecaoDebitoCobrado = (Collection) sessao.getAttribute("colecaoDebitoCobrado"); 
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
        
        
        Collection<CalcularValoresAguaEsgotoHelper> valoresConta = null;
        
        //[SF0001] - Determinar Valores para Faturamento de Água e/ou Esgoto
        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
        
	    valoresConta = fachada.calcularValoresConta(mesAnoConta, imovelID,
	    situacaoAguaConta, situacaoEsgotoConta, colecaoCategoriaOUSubcategoria, consumoAgua, consumoEsgoto, 
	    percentualEsgoto, null, usuarioLogado);
			 
        
        
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
        
        if(valoresConta != null){
	        //      Consumo de Esgoto
			Integer consumoAgua2 = fachada.calcularConsumoTotalAguaOuEsgotoPorCategoria(
					valoresConta,
							ConstantesSistema.CALCULAR_AGUA);
			
			if(consumoAgua2 != null)
			{
				inserirContaActionForm.setConsumoAgua(consumoAgua2.toString());
			}
			Integer consumoEsgoto2 = fachada.calcularConsumoTotalAguaOuEsgotoPorCategoria(
					valoresConta,
							ConstantesSistema.CALCULAR_ESGOTO);
			
			if(consumoEsgoto2 != null)
			{
				inserirContaActionForm.setConsumoEsgoto(consumoEsgoto2.toString());
			}
        }
        
        BigDecimal valorTotalConta = new BigDecimal("0");
        
        valorTotalConta = valorTotalConta.add(valorTotalAgua);
        valorTotalConta = valorTotalConta.add(valorTotalEsgoto);
        valorTotalConta = valorTotalConta.add(valorTotalDebitosConta);


        // [FS0008] - Verificar valor da conta igual a zero
		if (valorTotalConta.equals(new BigDecimal("0.00"))) {
			throw new ActionServletException("atencao.valor_conta_igual_zero");
		}
		
		
		//Arredondando os valores obtidos para duas casas decimais
		valorTotalAgua.setScale(2, BigDecimal.ROUND_HALF_UP);
		valorTotalEsgoto.setScale(2, BigDecimal.ROUND_HALF_UP);
		valorTotalDebitosConta.setScale(2, BigDecimal.ROUND_HALF_UP);
		valorTotalConta.setScale(2, BigDecimal.ROUND_HALF_UP);
        
		
        //Exibindo os valores calculados
        inserirContaActionForm.setValorAgua(Util.formatarMoedaReal(valorTotalAgua));
        inserirContaActionForm.setValorEsgoto(Util.formatarMoedaReal(valorTotalEsgoto));
        inserirContaActionForm.setValorDebito(Util.formatarMoedaReal(valorTotalDebitosConta));
        inserirContaActionForm.setValorTotal(Util.formatarMoedaReal(valorTotalConta)); 
        
        //setando o valor do percentual
        httpServletRequest.setAttribute("percentualEsgoto", Util.formatarMoedaRealparaBigDecimal(percentualEsgoto).toString().replace('.',','));        
        
        
        /*
		 * Colocado por Raphael Rossiter em 17/04/2007
		 * Objetivo: Manipulação dos objetos que serão exibidos no formulário de acordo com a empresa
		 */
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		httpServletRequest.setAttribute("empresaNome", sistemaParametro.getNomeAbreviadoEmpresa().trim());
		
		
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
