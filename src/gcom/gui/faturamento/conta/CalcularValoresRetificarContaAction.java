/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.faturamento.conta;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.bean.CalcularValoresAguaEsgotoHelper;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifa;
import gcom.faturamento.conta.Conta;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.math.BigDecimal;

import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.transacao.TabelaColuna;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class CalcularValoresRetificarContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirRetificarConta");
        
        Fachada fachada = Fachada.getInstancia();

        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        //Instância do formulário que está sendo utilizado
        RetificarContaActionForm retificarContaActionForm = (RetificarContaActionForm) actionForm;
        
        Conta contaAtual = (Conta) sessao.getAttribute("contaRetificar");
        
        //Recebendo os dados enviados pelo formulário
        String imovelID = retificarContaActionForm.getIdImovel();
        String mesAnoConta = retificarContaActionForm.getMesAnoConta();
        Integer situacaoAguaConta = new Integer(retificarContaActionForm.getSituacaoAguaConta());
        Integer situacaoEsgotoConta = new Integer(retificarContaActionForm.getSituacaoEsgotoConta());
        String consumoAgua = retificarContaActionForm.getConsumoAgua();
        String consumoEsgoto = retificarContaActionForm.getConsumoEsgoto();
        String percentualEsgoto = retificarContaActionForm.getPercentualEsgoto();
        
        //Carrega as coleções de acordo com os objetos da sessão
        Collection colecaoDebitoCobrado = null;
        if (sessao.getAttribute("colecaoDebitoCobrado") != null){
        	colecaoDebitoCobrado = (Collection) sessao.getAttribute("colecaoDebitoCobrado"); 
        }
        
        Collection colecaoCreditoRealizado = new Vector();
		if (sessao.getAttribute("colecaoCreditoRealizado") != null) {
			colecaoCreditoRealizado = (Collection) sessao
					.getAttribute("colecaoCreditoRealizado");
		}

        //RM4132 - adicionado por Vivianne Sousa - 17/02/2011 - analista:Jeferson Pedrosa
		SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
        verificarValoresLeituraAnteriorEAtualPoco(retificarContaActionForm.getLeituraAnteriorPoco(),
    		retificarContaActionForm.getLeituraAtualPoco(), 
    		retificarContaActionForm.getConsumoFaturadoPoco(),sistemaParametro);

		
		//Alterado por Raphael Rossiter em 17/04/2007
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
		
		
		//[SF0001] - Determinar Valores para Faturamento de Água e/ou Esgoto
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
        
        // Adicionado por Bruno Barros, 25/07/2008
        // Verificar se foi informado a tarifa de consumo da conta no retificar
        // Caso sim, calcular com o novo ConsumoTarifa informado, senão, 
        // utilizar o da conta
        // Carregamos todas as tarifas de consumo ativas
        if ( sessao.getAttribute( "colecaoConsumoTarifa" ) == null ){
            FiltroConsumoTarifa filtro = new FiltroConsumoTarifa();
            filtro.adicionarParametro( new ParametroSimples( FiltroConsumoTarifa.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO ) );
            Collection<ConsumoTarifa> colConsumoTarifa = Fachada.getInstancia().pesquisar( filtro, ConsumoTarifa.class.getName() );
            
            sessao.setAttribute( "colecaoConsumoTarifa", colConsumoTarifa );
        }
        
        Integer idConsumoTarifa = contaAtual.getConsumoTarifa().getId();
        
        if ( retificarContaActionForm.getIdConsumoTarifa() != null &&
             !retificarContaActionForm.getIdConsumoTarifa().equals( ConstantesSistema.NUMERO_NAO_INFORMADO ) ){
            idConsumoTarifa = Integer.parseInt( retificarContaActionForm.getIdConsumoTarifa() );            
        }
        
        // Verificamos se a conta possue tarifa de consumo
        retificarContaActionForm.setIdConsumoTarifa( idConsumoTarifa+"" );
		
		Collection<CalcularValoresAguaEsgotoHelper> valoresConta = fachada.calcularValoresConta(mesAnoConta, imovelID,
	    situacaoAguaConta, situacaoEsgotoConta, colecaoCategoriaOUSubcategoria, consumoAgua, consumoEsgoto, 
	    percentualEsgoto, idConsumoTarifa, usuarioLogado);
        
        // Verificar a permissão especial da tarifa de consumo
        boolean temPermissaoAlterarTarifaConsumo = fachada.verificarPermissaoEspecial( PermissaoEspecial.ALTERAR_TARIFA_CONSUMO_RETIFICAR_CONTA , (Usuario) sessao.getAttribute("usuarioLogado") );
        httpServletRequest.setAttribute("temPermissaoAlterarTarifaConsumo",temPermissaoAlterarTarifaConsumo);        
        
        // Fim alterador por Bruno Barros, 25/07/2008		 
        
        //Cálcula o valor total dos débitos de uma conta de acordo com o informado pelo usuário
        BigDecimal valorTotalDebitosConta = fachada.calcularValorTotalDebitoConta(colecaoDebitoCobrado,
        httpServletRequest.getParameterMap());
        
        
        //Cálcula o valor total dos créditos de uma conta de acordo com o informado pelo usuário
        BigDecimal valorTotalCreditosConta = fachada.calcularValorTotalCreditoConta(colecaoCreditoRealizado,
        httpServletRequest.getParameterMap());
        
        
        
        //Totalizando os valores de água e esgoto
        BigDecimal valorTotalAgua = new BigDecimal("0.00");
        BigDecimal valorTotalEsgoto = new BigDecimal("0.00");
        
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
        
        valorTotalConta = valorTotalConta.subtract(valorTotalCreditosConta);
        
        if(valoresConta != null){
	        //      Consumo de Esgoto
			Integer consumoAgua2 = fachada.calcularConsumoTotalAguaOuEsgotoPorCategoria(
					valoresConta,
							ConstantesSistema.CALCULAR_AGUA);
			
			if(consumoAgua2 != null)
			{
				retificarContaActionForm.setConsumoAgua(consumoAgua2.toString());
			}
			Integer consumoEsgoto2 = fachada.calcularConsumoTotalAguaOuEsgotoPorCategoria(
					valoresConta,
							ConstantesSistema.CALCULAR_ESGOTO);
			
			if(consumoEsgoto2 != null)
			{
				retificarContaActionForm.setConsumoEsgoto(consumoEsgoto2.toString());
			}
        }
        //[FS0008] - Verificar valor da conta igual a zero
        if (valorTotalConta.equals(new BigDecimal("0.00")) && 
            	(valorTotalCreditosConta == null || valorTotalCreditosConta.equals(new BigDecimal("0.00")))) {
			throw new ActionServletException("atencao.valor_conta_igual_zero");
		}
		else if (valorTotalConta.compareTo(new BigDecimal("0.00")) == -1){	
			throw new ActionServletException("atencao.valor_conta_negativo");
		}
		
		
		//Arredondando os valores obtidos para duas casas decimais
		valorTotalAgua.setScale(2, BigDecimal.ROUND_HALF_UP);
		valorTotalEsgoto.setScale(2, BigDecimal.ROUND_HALF_UP);
		valorTotalDebitosConta.setScale(2, BigDecimal.ROUND_HALF_UP);
		valorTotalCreditosConta.setScale(2, BigDecimal.ROUND_HALF_UP);
		valorTotalConta.setScale(2, BigDecimal.ROUND_HALF_UP);
        
		
		//Exibindo os valores calculados
		retificarContaActionForm.setValorAgua(Util.formatarMoedaReal(valorTotalAgua));
		retificarContaActionForm.setValorEsgoto(Util.formatarMoedaReal(valorTotalEsgoto));
		retificarContaActionForm.setValorDebito(Util.formatarMoedaReal(valorTotalDebitosConta));
		retificarContaActionForm.setValorCredito(Util.formatarMoedaReal(valorTotalCreditosConta));
		retificarContaActionForm.setValorTotal(Util.formatarMoedaReal(valorTotalConta)); 
        
		
		/*
		 * Colocado por Raphael Rossiter em 17/04/2007
		 * Objetivo: Manipulação dos objetos que serão exibidos no formulário de acordo com a empresa
		 */
//		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		httpServletRequest.setAttribute("empresaNome", sistemaParametro.getNomeAbreviadoEmpresa().trim());
		
		   
		//verifica se o usuário tem permissão especial.
		boolean temPermissaoParaRetificarContaNorma = 
			fachada.verificarPermissaoEspecial(PermissaoEspecial.RETIFICAR_CONTA_NORMA_REVISAO_FATURAMENTO, usuarioLogado);	
		 //[FS0038]-habilitar/desabilitar campos para retificação
        habilitarCampos(retificarContaActionForm.getMotivoRetificacaoID(),httpServletRequest,sistemaParametro,temPermissaoParaRetificarContaNorma);
        
		
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
	 /* [FS0039] – Verificar valores de Leitura Anterior e Atual do Poço
	  * Vivianne Sousa - 17/02/2011
	  * RM4132 - analista responsável:Jeferson Pedrosa
	 */
	public void verificarValoresLeituraAnteriorEAtualPoco(String leituraAnteriorPocoString,
			String leituraAtualPocoString, String consumoFaturadoPocoString,SistemaParametro sistemaParametro) {
		
		if(sistemaParametro.getIndicadorNormaRetificacao().equals(ConstantesSistema.SIM)){
			
			if(leituraAnteriorPocoString != null && leituraAtualPocoString != null && consumoFaturadoPocoString != null &&
					!leituraAnteriorPocoString.equalsIgnoreCase("") && !leituraAtualPocoString.equalsIgnoreCase("") 
					&& !consumoFaturadoPocoString.equalsIgnoreCase("")){
				//Caso exista informação do Volume de Poço, da Leitura Anterior Poço e da Leitura Atual Poço:
				
				Integer leituraAnteriorPoco = new Integer(leituraAnteriorPocoString);
				Integer leituraAtualPoco =  new Integer(leituraAtualPocoString);
				Integer consumoFaturadoPoco = new Integer(consumoFaturadoPocoString);
				
				int diferencaLeituraAnteriorELeituraAtual = 0;

				if(leituraAtualPoco.intValue() > leituraAnteriorPoco.intValue()){
					diferencaLeituraAnteriorELeituraAtual = leituraAtualPoco.intValue() - leituraAnteriorPoco.intValue();
				}else if(leituraAnteriorPoco.intValue() > leituraAtualPoco.intValue()){
					diferencaLeituraAnteriorELeituraAtual = leituraAnteriorPoco.intValue() - leituraAtualPoco.intValue();
				}
				
				if(diferencaLeituraAnteriorELeituraAtual != consumoFaturadoPoco.intValue()){
					throw new ActionServletException("atencao.leitura_poco_fora_faixa");
				}
			}
		}
	
	}
	
	/*[FS0038]-habilitar/desabilitar campos para retificação
	 * Vivianne Sousa - 14/02/2011
	 * RM4132 - analista responsável:Jeferson Pedrosa
	 */
	public void habilitarCampos(String idMotivoSelecionado,HttpServletRequest httpServletRequest,
			SistemaParametro sistemaParametro,boolean temPermissaoParaRetificarContaNorma){
		
		if(sistemaParametro.getIndicadorNormaRetificacao().equals(ConstantesSistema.SIM)){
			//Caso a Empresa esteja na Norma de Retificação de Conta 
			
			String habilitaSituacaoAgua = "2";
			String habilitaConsumoAgua = "2";
			String habilitaLeituraAnterior = "2";
			String habilitaLeituraAtual = "2";
			String habilitaSituacaoEsgoto = "2";
			String habilitaConsumoEsgoto = "2";
			String habilitaPercentualEsgoto = "2";
			String habilitaPercentualColeta = "2";
			String habilitaVolumePoco = "2";
			String habilitaLeituraAnteriorPoco = "2";
			String habilitaLeituraAtualPoco = "2";
			String habilitaListaCategoriasEQuantidadesEconomias = "2";
			String habilitaListaDebitos = "2";
			String habilitaListaCreditos = "2";
			
			if(idMotivoSelecionado != null && !idMotivoSelecionado.equalsIgnoreCase("")
				&& !idMotivoSelecionado.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
				
				Collection colecaoTabelaColuna = getFachada().pesquisaTabelaColunaContaMotivoRetificacaoColuna(new Integer(idMotivoSelecionado));		
				
				Iterator iterTabelaColuna = colecaoTabelaColuna.iterator();
				
				while (iterTabelaColuna.hasNext()) {
					TabelaColuna tabelaColuna = (TabelaColuna) iterTabelaColuna.next();
					
					String nomeAbreviado = tabelaColuna.getNomeAbreviado();
					
					if (nomeAbreviado != null) {
						
						if(nomeAbreviado.equalsIgnoreCase("SITAGU")){
							habilitaSituacaoAgua = "1";
						}else if(nomeAbreviado.equalsIgnoreCase("CONAGU")){
							habilitaConsumoAgua = "1";
						}else if(nomeAbreviado.equalsIgnoreCase("LANAGU")){
							habilitaLeituraAnterior = "1";
						}else if(nomeAbreviado.equalsIgnoreCase("LATAGU")){
							habilitaLeituraAtual = "1";
						}else if(nomeAbreviado.equalsIgnoreCase("SITESG")){
							habilitaSituacaoEsgoto = "1";
						}else if(nomeAbreviado.equalsIgnoreCase("CONESG")){
							habilitaConsumoEsgoto = "1";
						}else if(nomeAbreviado.equalsIgnoreCase("PERESG")){
							habilitaPercentualEsgoto = "1";
						}else if(nomeAbreviado.equalsIgnoreCase("PERCOL")){
							habilitaPercentualColeta = "1";
						}else if(nomeAbreviado.equalsIgnoreCase("VOLPOC")){
							habilitaVolumePoco = "1";
						}else if(nomeAbreviado.equalsIgnoreCase("LANPOC")){
							habilitaLeituraAnteriorPoco = "1";
						}else if(nomeAbreviado.equalsIgnoreCase("LATPOC")){
							habilitaLeituraAtualPoco = "1";
						}else if(nomeAbreviado.equalsIgnoreCase("CATECO")){
							habilitaListaCategoriasEQuantidadesEconomias = "1";
						}else if(nomeAbreviado.equalsIgnoreCase("DEBITO")){
							habilitaListaDebitos = "1";
						}else if(nomeAbreviado.equalsIgnoreCase("CREDIT")){
							habilitaListaCreditos = "1";
						}
					}
				}
			}
					
			httpServletRequest.setAttribute("habilitaSituacaoAgua",habilitaSituacaoAgua);
			httpServletRequest.setAttribute("habilitaConsumoAgua",habilitaConsumoAgua);
			httpServletRequest.setAttribute("habilitaLeituraAnterior",habilitaLeituraAnterior);
			httpServletRequest.setAttribute("habilitaLeituraAtual",habilitaLeituraAtual);
			httpServletRequest.setAttribute("habilitaSituacaoEsgoto",habilitaSituacaoEsgoto);
			httpServletRequest.setAttribute("habilitaConsumoEsgoto",habilitaConsumoEsgoto);
			httpServletRequest.setAttribute("habilitaPercentualEsgoto",habilitaPercentualEsgoto);
			httpServletRequest.setAttribute("habilitaPercentualColeta",habilitaPercentualColeta);
			httpServletRequest.setAttribute("habilitaVolumePoco",habilitaVolumePoco);
			httpServletRequest.setAttribute("habilitaLeituraAnteriorPoco",habilitaLeituraAnteriorPoco);
			httpServletRequest.setAttribute("habilitaLeituraAtualPoco",habilitaLeituraAtualPoco);
			httpServletRequest.setAttribute("habilitaListaCategoriasEQuantidadesEconomias",habilitaListaCategoriasEQuantidadesEconomias);
			httpServletRequest.setAttribute("habilitaListaDebitos",habilitaListaDebitos);
			httpServletRequest.setAttribute("habilitaListaCreditos",habilitaListaCreditos);

		}
		
	}
}

