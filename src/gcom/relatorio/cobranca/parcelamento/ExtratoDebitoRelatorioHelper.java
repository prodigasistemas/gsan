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
package gcom.relatorio.cobranca.parcelamento;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.CobrancaDocumentoItem;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.debito.DebitoACobrar;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

public class ExtratoDebitoRelatorioHelper {
	
	private Collection<CobrancaDocumentoItem>  colecaoCobrancaDocumentoItemContas;
	
	private Collection<CobrancaDocumentoItem>  colecaoCobrancaDocumentoItemGuiasPagamento;
	
	private Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemDebitosACobrar;
	
	private Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemCreditoARealizar;

    
    /** Campo adicionado para utilização no caso de uso [UC0251] */
    private CobrancaDocumento documentoCobranca ;
	
	public CobrancaDocumento getDocumentoCobranca() {
        return documentoCobranca;
    }

    public void setDocumentoCobranca(CobrancaDocumento documentoCobranca) {
        this.documentoCobranca = documentoCobranca;
    }
    /** **********************************************************/


    public ExtratoDebitoRelatorioHelper() {
	}
	

	
	public ExtratoDebitoRelatorioHelper(Collection<CobrancaDocumentoItem>  colecaoCobrancaDocumentoItemContas, 
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemGuiasPagamento, 
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemDebitosACobrar,
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemCreditoARealizar) {
		
		this.colecaoCobrancaDocumentoItemContas = colecaoCobrancaDocumentoItemContas;
		this.colecaoCobrancaDocumentoItemGuiasPagamento = colecaoCobrancaDocumentoItemGuiasPagamento;
		this.colecaoCobrancaDocumentoItemDebitosACobrar = colecaoCobrancaDocumentoItemDebitosACobrar; 
		this.colecaoCobrancaDocumentoItemCreditoARealizar = colecaoCobrancaDocumentoItemCreditoARealizar;
		
	}

	public Collection<CobrancaDocumentoItem> getColecaoCobrancaDocumentoItemDebitosACobrar() {
		return colecaoCobrancaDocumentoItemDebitosACobrar;
	}

	public void setColecaoCobrancaDocumentoItemDebitosACobrar(
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemDebitosACobrar) {
		this.colecaoCobrancaDocumentoItemDebitosACobrar = colecaoCobrancaDocumentoItemDebitosACobrar;
	}

	public Collection<CobrancaDocumentoItem> getColecaoCobrancaDocumentoItemGuiasPagamento() {
		return colecaoCobrancaDocumentoItemGuiasPagamento;
	}

	public void setColecaoCobrancaDocumentoItemGuiasPagamento(
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemGuiasPagamento) {
		this.colecaoCobrancaDocumentoItemGuiasPagamento = colecaoCobrancaDocumentoItemGuiasPagamento;
	}

	public Collection<CobrancaDocumentoItem> getColecaoCobrancaDocumentoItemContas() {
		return colecaoCobrancaDocumentoItemContas;
	}

	public void setColecaoCobrancaDocumentoItemContas(
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemContas) {
		this.colecaoCobrancaDocumentoItemContas = colecaoCobrancaDocumentoItemContas;
	}

	 /*
     * Cálcula o valor total da conta (água + esgoto + débitos - creditos)
     * @author Vivianne Sousa
     * @created 12/09/2006
     */
    public BigDecimal getValorTotalConta(){
		
		BigDecimal retorno = new BigDecimal("0.00");
		
		Iterator iterator = this.getColecaoCobrancaDocumentoItemContas().iterator();
		
		while (iterator.hasNext()) {
			Conta conta = ((CobrancaDocumentoItem) iterator.next()).getContaGeral().getConta();
			
			if (conta != null){
				
				if (conta.getValorTotal() != null) {
					retorno = retorno.add(conta.getValorTotal());
				}
			}
			
		}
		
		retorno = retorno.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
		
		return retorno ;
	}
    
    /*
     * Cálcula o valor total das guias de pagamento(GPAG_VLDEBITO)
     * @author Vivianne Sousa
     * @created 12/09/2006
     */
    public BigDecimal getValorTotalGuiaPagamento(){
		
		BigDecimal retorno = new BigDecimal("0.00");
		
		Iterator iterator = this.getColecaoCobrancaDocumentoItemGuiasPagamento().iterator();
		
		while (iterator.hasNext()) {
			GuiaPagamento guiaPagamento = ((CobrancaDocumentoItem) iterator.next()).getGuiaPagamentoGeral().getGuiaPagamento();
			
			if (guiaPagamento.getValorDebito() != null){
				retorno = retorno.add(guiaPagamento.getValorDebito());
			}
		}
		
		retorno = retorno.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
		
		return retorno ;
	}
    
    
    /*
     * Calcula o valor total restante dos debitos a cobrar 
     * (DBAC_VLDEBITO - ((DBAC_VLDEBITO/DBAC_NNPRESTAODEBITO) * (DBAC_NNPRESTACAOCOBRADA + dbac_nnparcelabonus)))
     * @author Vivianne Sousa
     * @created 12/09/2006
     */
    public BigDecimal getValorTotalRestanteDebitosACobrar(){
		
		BigDecimal retorno = new BigDecimal("0.00");
		
		Iterator iterator = this.getColecaoCobrancaDocumentoItemDebitosACobrar().iterator();
		
		while (iterator.hasNext()) {
			
			
			CobrancaDocumentoItem cobrancaDocumentoItem = (CobrancaDocumentoItem) iterator.next();
			
			DebitoACobrar debitoACobrar = cobrancaDocumentoItem.getDebitoACobrarGeral().getDebitoACobrar();
			
			//ANTECIPAÇÃO DE PARCELAS
			if (cobrancaDocumentoItem.getNumeroParcelasAntecipadas() != null){
				retorno = retorno.add(cobrancaDocumentoItem.getValorItemCobrado());
			}
			else{
				retorno = retorno.add(debitoACobrar.getValorTotalComBonus());
			}
		}
		
		retorno = retorno.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
		
		return retorno ;
	}

	/**
	 * @return Retorna o campo colecaoCobrancaDocumentoItemCreditoARealizar.
	 */
	public Collection<CobrancaDocumentoItem> getColecaoCobrancaDocumentoItemCreditoARealizar() {
		return colecaoCobrancaDocumentoItemCreditoARealizar;
	}

	/**
	 * @param colecaoCobrancaDocumentoItemCreditoARealizar O colecaoCobrancaDocumentoItemCreditoARealizar a ser setado.
	 */
	public void setColecaoCobrancaDocumentoItemCreditoARealizar(
			Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItemCreditoARealizar) {
		this.colecaoCobrancaDocumentoItemCreditoARealizar = colecaoCobrancaDocumentoItemCreditoARealizar;
	}   
	
}
