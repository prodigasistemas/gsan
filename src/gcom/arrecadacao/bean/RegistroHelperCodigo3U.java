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
package gcom.arrecadacao.bean;

import java.io.Serializable;

/**
 * [UC0721] - Distribuir dados do Registro de Movimento do Arrecadador da Ficha de Compensação
 * Autor: Vivianne Sousa
 * Data: 23/11/2007
 */
public class RegistroHelperCodigo3U implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public RegistroHelperCodigo3U() {
	}

	private String codigoBancoCompensacao;
    
    private String loteServico;
    
    private String codigoRegistro;
    
    private String numeroSequencialRegLote;
    
    private String codSegmentoRegDetalhe;
    
    private String usoExclusivo06;
    
    private String codigoMovimento;
    
    private String jurosMultaEncargos;
    
    private String valorDescontoConcedido;
    
    private String valorAbatConcedidoCancelado;
    
    private String valorIOFRecolhido;
    
    private String valorPagoSacado;
    
    private String valorLiquidoASerCreditado;
    
    private String valorOutrasDespesas;
    
    private String valorOutrosCreditos;
    
    private String dataOcorrencia;
    
    private String dataEfetivacaoCredito;
    
    private String codigoOcorrenciaSacado;
    
    private String dataOcorrenciaSacado;
    
    private String valorOcorrenciaSacado;
    
    private String complOcorrenciaSacado;
    
    private String codigoBanco;
    
    private String nossoNumero;
    
    private String usoExclusivo24;

    public String getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(String codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

    public String getCodigoBancoCompensacao() {
        return codigoBancoCompensacao;
    }

    public void setCodigoBancoCompensacao(String codigoBancoCompensacao) {
        this.codigoBancoCompensacao = codigoBancoCompensacao;
    }

    public String getCodigoMovimento() {
        return codigoMovimento;
    }

    public void setCodigoMovimento(String codigoMovimento) {
        this.codigoMovimento = codigoMovimento;
    }

    public String getCodigoOcorrenciaSacado() {
        return codigoOcorrenciaSacado;
    }

    public void setCodigoOcorrenciaSacado(String codigoOcorrenciaSacado) {
        this.codigoOcorrenciaSacado = codigoOcorrenciaSacado;
    }

    public String getCodSegmentoRegDetalhe() {
        return codSegmentoRegDetalhe;
    }

    public void setCodSegmentoRegDetalhe(String codSegmentoRegDetalhe) {
        this.codSegmentoRegDetalhe = codSegmentoRegDetalhe;
    }

    public String getComplOcorrenciaSacado() {
        return complOcorrenciaSacado;
    }

    public void setComplOcorrenciaSacado(String complOcorrenciaSacado) {
        this.complOcorrenciaSacado = complOcorrenciaSacado;
    }

    public String getDataEfetivacaoCredito() {
        return dataEfetivacaoCredito;
    }

    public void setDataEfetivacaoCredito(String dataEfetivacaoCredito) {
        this.dataEfetivacaoCredito = dataEfetivacaoCredito;
    }

    public String getDataOcorrencia() {
        return dataOcorrencia;
    }

    public void setDataOcorrencia(String dataOcorrencia) {
        this.dataOcorrencia = dataOcorrencia;
    }

    public String getDataOcorrenciaSacado() {
        return dataOcorrenciaSacado;
    }

    public void setDataOcorrenciaSacado(String dataOcorrenciaSacado) {
        this.dataOcorrenciaSacado = dataOcorrenciaSacado;
    }

    public String getCodigoRegistro() {
        return codigoRegistro;
    }

    public void setCodigoRegistro(String codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
    }

    public String getJurosMultaEncargos() {
        return jurosMultaEncargos;
    }

    public void setJurosMultaEncargos(String jurosMultaEncargos) {
        this.jurosMultaEncargos = jurosMultaEncargos;
    }

    public String getLoteServico() {
        return loteServico;
    }

    public void setLoteServico(String loteServico) {
        this.loteServico = loteServico;
    }

    public String getNossoNumero() {
        return nossoNumero;
    }

    public void setNossoNumero(String nossoNumero) {
        this.nossoNumero = nossoNumero;
    }

    public String getNumeroSequencialRegLote() {
        return numeroSequencialRegLote;
    }

    public void setNumeroSequencialRegLote(String numeroSequencialRegLote) {
        this.numeroSequencialRegLote = numeroSequencialRegLote;
    }

    public String getUsoExclusivo06() {
        return usoExclusivo06;
    }

    public void setUsoExclusivo06(String usoExclusivo06) {
        this.usoExclusivo06 = usoExclusivo06;
    }

    public String getUsoExclusivo24() {
        return usoExclusivo24;
    }

    public void setUsoExclusivo24(String usoExclusivo24) {
        this.usoExclusivo24 = usoExclusivo24;
    }

    public String getValorAbatConcedidoCancelado() {
        return valorAbatConcedidoCancelado;
    }

    public void setValorAbatConcedidoCancelado(String valorAbatConcedidoCancelado) {
        this.valorAbatConcedidoCancelado = valorAbatConcedidoCancelado;
    }

    public String getValorDescontoConcedido() {
        return valorDescontoConcedido;
    }

    public void setValorDescontoConcedido(String valorDescontoConcedido) {
        this.valorDescontoConcedido = valorDescontoConcedido;
    }

    public String getValorIOFRecolhido() {
        return valorIOFRecolhido;
    }

    public void setValorIOFRecolhido(String valorIOFRecolhido) {
        this.valorIOFRecolhido = valorIOFRecolhido;
    }

    public String getValorLiquidoASerCreditado() {
        return valorLiquidoASerCreditado;
    }

    public void setValorLiquidoASerCreditado(String valorLiquidoASerCreditado) {
        this.valorLiquidoASerCreditado = valorLiquidoASerCreditado;
    }

    public String getValorOcorrenciaSacado() {
        return valorOcorrenciaSacado;
    }

    public void setValorOcorrenciaSacado(String valorOcorrenciaSacado) {
        this.valorOcorrenciaSacado = valorOcorrenciaSacado;
    }

    public String getValorOutrasDespesas() {
        return valorOutrasDespesas;
    }

    public void setValorOutrasDespesas(String valorOutrasDespesas) {
        this.valorOutrasDespesas = valorOutrasDespesas;
    }

    public String getValorOutrosCreditos() {
        return valorOutrosCreditos;
    }

    public void setValorOutrosCreditos(String valorOutrosCreditos) {
        this.valorOutrosCreditos = valorOutrosCreditos;
    }

    public String getValorPagoSacado() {
        return valorPagoSacado;
    }

    public void setValorPagoSacado(String valorPagoSacado) {
        this.valorPagoSacado = valorPagoSacado;
    }
    
    

     
    
}
