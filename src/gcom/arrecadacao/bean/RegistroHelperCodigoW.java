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
 * Data: 28/01/2008
 */
public class RegistroHelperCodigoW implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public RegistroHelperCodigoW() {
	}

	private String codigoBancoCompensacao;
    
    private String loteServico;
    
    private String codigoRegistro;
    
    private String numeroSequencialRegLote;
    
    private String codigoMovimento;
    
    private String agenciaMantedoraConta;
    
    private String digitoVerificadorAgencia;
    
    private String numeroContaCorrente;
    
    private String digitoVerificadorConta;
    
    private String digitoVerificadorAgConta;
    
    private String nossoNumero;
    
    private String numeroDocCobranca;
    
    private String dataVencimentoTitulo;
    
    private String valorNominalTitulo;
    
    private String valorPagoSacado;
    
    private String dataOcorrencia;
    
    private String dataEfetivacaoCredito;

    public String getAgenciaMantedoraConta() {
        return agenciaMantedoraConta;
    }

    public void setAgenciaMantedoraConta(String agenciaMantedoraConta) {
        this.agenciaMantedoraConta = agenciaMantedoraConta;
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

    public String getCodigoRegistro() {
        return codigoRegistro;
    }

    public void setCodigoRegistro(String codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
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

    public String getDataVencimentoTitulo() {
        return dataVencimentoTitulo;
    }

    public void setDataVencimentoTitulo(String dataVencimentoTitulo) {
        this.dataVencimentoTitulo = dataVencimentoTitulo;
    }

    public String getDigitoVerificadorAgConta() {
        return digitoVerificadorAgConta;
    }

    public void setDigitoVerificadorAgConta(String digitoVerificadorAgConta) {
        this.digitoVerificadorAgConta = digitoVerificadorAgConta;
    }

    public String getDigitoVerificadorAgencia() {
        return digitoVerificadorAgencia;
    }

    public void setDigitoVerificadorAgencia(String digitoVerificadorAgencia) {
        this.digitoVerificadorAgencia = digitoVerificadorAgencia;
    }

    public String getDigitoVerificadorConta() {
        return digitoVerificadorConta;
    }

    public void setDigitoVerificadorConta(String digitoVerificadorConta) {
        this.digitoVerificadorConta = digitoVerificadorConta;
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

    public String getNumeroContaCorrente() {
        return numeroContaCorrente;
    }

    public void setNumeroContaCorrente(String numeroContaCorrente) {
        this.numeroContaCorrente = numeroContaCorrente;
    }

    public String getNumeroDocCobranca() {
        return numeroDocCobranca;
    }

    public void setNumeroDocCobranca(String numeroDocCobranca) {
        this.numeroDocCobranca = numeroDocCobranca;
    }

    public String getNumeroSequencialRegLote() {
        return numeroSequencialRegLote;
    }

    public void setNumeroSequencialRegLote(String numeroSequencialRegLote) {
        this.numeroSequencialRegLote = numeroSequencialRegLote;
    }

    public String getValorNominalTitulo() {
        return valorNominalTitulo;
    }

    public void setValorNominalTitulo(String valorNominalTitulo) {
        this.valorNominalTitulo = valorNominalTitulo;
    }

    public String getValorPagoSacado() {
        return valorPagoSacado;
    }

    public void setValorPagoSacado(String valorPagoSacado) {
        this.valorPagoSacado = valorPagoSacado;
    }
    
    public String getIdentificacaoAgenciaContaDigitoCreditada(){
        String identificacaoAgenciaContaDigitoCreditada = "";
        
        identificacaoAgenciaContaDigitoCreditada = this.agenciaMantedoraConta ; 
        identificacaoAgenciaContaDigitoCreditada = 
            identificacaoAgenciaContaDigitoCreditada + "-" +this.digitoVerificadorAgencia; 
        identificacaoAgenciaContaDigitoCreditada = 
            identificacaoAgenciaContaDigitoCreditada + "/" + this.numeroContaCorrente; 
        identificacaoAgenciaContaDigitoCreditada = 
            identificacaoAgenciaContaDigitoCreditada + "-" + this.digitoVerificadorConta; 
        identificacaoAgenciaContaDigitoCreditada = 
            identificacaoAgenciaContaDigitoCreditada + "/" + this.digitoVerificadorAgConta; 

        return identificacaoAgenciaContaDigitoCreditada;
    }

	

	
}
