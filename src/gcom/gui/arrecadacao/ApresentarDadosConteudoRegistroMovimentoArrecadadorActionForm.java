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
package gcom.gui.arrecadacao;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade gerar o formulário que irá apresentar os dados do conteúdo do registro
 * de movimento do arrecadador
 *
 * @author Raphael Rossiter
 * @date 21/03/2006
 */
public class ApresentarDadosConteudoRegistroMovimentoArrecadadorActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String codigoRegistro;
	private String identificacaoClienteEmpresa;
	private String agenciaDebito;
	private String identificacaoClienteBanco;
	private String dataOpcaoExclusao;
	private String descricaoMovimento;
	private String ocorrencia;
	private String indicadorAceitacao;
	private String dataVencimentoDebito;
	private String valorDebito;
	private String codigoMoeda;
	private String codigoRetorno;
	private String mesAnoReferenciaConta;
	private String digitoVerificadorConta;
	private String identificacaoAgenciaContaDigitoCreditada;
	private String dataPagamento;
	private String dataPrevistaCredito;
	private String valorRecebido;
	private String valorTarifa;
	private String nsr;
	private String codigoAgenciaArrecadadora;
	private String formaArrecadacaoCaptura;
	private String numeroAutenticacaoCaixaOUCodigoTransacao;
	private String formaPagamento;
	private String codigoAgencia;
	private String nomeAgencia;
	private String nomeLogradouro;
	private String numero;
	private String codigoCep;
	private String sufixoCep;
	private String nomeCidade;
	private String siglaUnidadeFederacao;
	private String situacaoAgencia;
	
	private String identificacaoProduto;
	private String identificacaoSegmento;
	private String identificacaoValorRealOUReferencia;
	private String digitoVerificadorGeral;
	private String identificacaoClienteEmpresaCodigoBarras;
	private String valorPagamento;
	private String identificacaoEmpresa;
	private String tipoPagamento;
	private String codigoLocalidade;
	private String matriculaImovel;
	private String mesAnoReferenciaContaCodigoBarras;
	private String digitoVerificadorContaModulo10;
	private String codigoTipoDebito;
	private String anoEmissaoGuiaPagamento;
	private String sequencialDocumentoCobranca;
	private String codigoTipoDocumento;
	private String codigoCliente;
	private String sequencialFaturaClienteResponsavel;
	
	private String codigoOrigemConta;
	private String qualificacao;
	private String mesAno;
	private String numeroDocumento;
	
	private String identificacaoDocumento;
	
    //codigo de barras da Ficha de Compensação
    private String codigoBanco;
    private String fatorVencimento;
    private String valorCodigoBarras;
    private String nossoNumero;
    private String tipoCarteira;
	
	public String getMesAno() {
		return mesAno;
	}
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
	public String getQualificacao() {
		return qualificacao;
	}
	public void setQualificacao(String qualificacao) {
		this.qualificacao = qualificacao;
	}
	public String getCodigoOrigemConta() {
		return codigoOrigemConta;
	}
	public void setCodigoOrigemConta(String codigoOrigemConta) {
		this.codigoOrigemConta = codigoOrigemConta;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getAgenciaDebito() {
		return agenciaDebito;
	}
	public void setAgenciaDebito(String agenciaDebito) {
		this.agenciaDebito = agenciaDebito;
	}
	public String getCodigoAgencia() {
		return codigoAgencia;
	}
	public void setCodigoAgencia(String codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}
	public String getCodigoAgenciaArrecadadora() {
		return codigoAgenciaArrecadadora;
	}
	public void setCodigoAgenciaArrecadadora(String codigoAgenciaArrecadadora) {
		this.codigoAgenciaArrecadadora = codigoAgenciaArrecadadora;
	}
	public String getCodigoCep() {
		return codigoCep;
	}
	public void setCodigoCep(String codigoCep) {
		this.codigoCep = codigoCep;
	}
	public String getCodigoMoeda() {
		return codigoMoeda;
	}
	public void setCodigoMoeda(String codigoMoeda) {
		this.codigoMoeda = codigoMoeda;
	}
	public String getCodigoRegistro() {
		return codigoRegistro;
	}
	public void setCodigoRegistro(String codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}
	public String getCodigoRetorno() {
		return codigoRetorno;
	}
	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}
	public String getDataOpcaoExclusao() {
		return dataOpcaoExclusao;
	}
	public void setDataOpcaoExclusao(String dataOpcaoExclusao) {
		this.dataOpcaoExclusao = dataOpcaoExclusao;
	}
	public String getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public String getDataPrevistaCredito() {
		return dataPrevistaCredito;
	}
	public void setDataPrevistaCredito(String dataPrevistaCredito) {
		this.dataPrevistaCredito = dataPrevistaCredito;
	}
	public String getDataVencimentoDebito() {
		return dataVencimentoDebito;
	}
	public void setDataVencimentoDebito(String dataVencimentoDebito) {
		this.dataVencimentoDebito = dataVencimentoDebito;
	}
	public String getDescricaoMovimento() {
		return descricaoMovimento;
	}
	public void setDescricaoMovimento(String descricaoMovimento) {
		this.descricaoMovimento = descricaoMovimento;
	}
	public String getDigitoVerificadorConta() {
		return digitoVerificadorConta;
	}
	public void setDigitoVerificadorConta(String digitoVerificadorConta) {
		this.digitoVerificadorConta = digitoVerificadorConta;
	}
	public String getFormaArrecadacaoCaptura() {
		return formaArrecadacaoCaptura;
	}
	public void setFormaArrecadacaoCaptura(String formaArrecadacaoCaptura) {
		this.formaArrecadacaoCaptura = formaArrecadacaoCaptura;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public String getIdentificacaoAgenciaContaDigitoCreditada() {
		return identificacaoAgenciaContaDigitoCreditada;
	}
	public void setIdentificacaoAgenciaContaDigitoCreditada(
			String identificacaoAgenciaContaDigitoCreditada) {
		this.identificacaoAgenciaContaDigitoCreditada = identificacaoAgenciaContaDigitoCreditada;
	}
	public String getIdentificacaoClienteBanco() {
		return identificacaoClienteBanco;
	}
	public void setIdentificacaoClienteBanco(String identificacaoClienteBanco) {
		this.identificacaoClienteBanco = identificacaoClienteBanco;
	}
	public String getIdentificacaoClienteEmpresa() {
		return identificacaoClienteEmpresa;
	}
	public void setIdentificacaoClienteEmpresa(String identificacaoClienteEmpresa) {
		this.identificacaoClienteEmpresa = identificacaoClienteEmpresa;
	}
	public String getIndicadorAceitacao() {
		return indicadorAceitacao;
	}
	public void setIndicadorAceitacao(String indicadorAceitacao) {
		this.indicadorAceitacao = indicadorAceitacao;
	}
	public String getMesAnoReferenciaConta() {
		return mesAnoReferenciaConta;
	}
	public void setMesAnoReferenciaConta(String mesAnoReferenciaConta) {
		this.mesAnoReferenciaConta = mesAnoReferenciaConta;
	}
	public String getNomeAgencia() {
		return nomeAgencia;
	}
	public void setNomeAgencia(String nomeAgencia) {
		this.nomeAgencia = nomeAgencia;
	}
	public String getNomeCidade() {
		return nomeCidade;
	}
	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}
	public String getNomeLogradouro() {
		return nomeLogradouro;
	}
	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}
	public String getNsr() {
		return nsr;
	}
	public void setNsr(String nsr) {
		this.nsr = nsr;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getNumeroAutenticacaoCaixaOUCodigoTransacao() {
		return numeroAutenticacaoCaixaOUCodigoTransacao;
	}
	public void setNumeroAutenticacaoCaixaOUCodigoTransacao(
			String numeroAutenticacaoCaixaOUCodigoTransacao) {
		this.numeroAutenticacaoCaixaOUCodigoTransacao = numeroAutenticacaoCaixaOUCodigoTransacao;
	}
	public String getOcorrencia() {
		return ocorrencia;
	}
	public void setOcorrencia(String ocorrencia) {
		this.ocorrencia = ocorrencia;
	}
	public String getSiglaUnidadeFederacao() {
		return siglaUnidadeFederacao;
	}
	public void setSiglaUnidadeFederacao(String siglaUnidadeFederacao) {
		this.siglaUnidadeFederacao = siglaUnidadeFederacao;
	}
	public String getSituacaoAgencia() {
		return situacaoAgencia;
	}
	public void setSituacaoAgencia(String situacaoAgencia) {
		this.situacaoAgencia = situacaoAgencia;
	}
	public String getSufixoCep() {
		return sufixoCep;
	}
	public void setSufixoCep(String sufixoCep) {
		this.sufixoCep = sufixoCep;
	}
	public String getValorDebito() {
		return valorDebito;
	}
	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}
	public String getValorRecebido() {
		return valorRecebido;
	}
	public void setValorRecebido(String valorRecebido) {
		this.valorRecebido = valorRecebido;
	}
	public String getValorTarifa() {
		return valorTarifa;
	}
	public void setValorTarifa(String valorTarifa) {
		this.valorTarifa = valorTarifa;
	}
	
	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
	    /**@todo: finish this method, this is just the skeleton.*/
	    return null;
	}
	public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
	}
	public String getAnoEmissaoGuiaPagamento() {
		return anoEmissaoGuiaPagamento;
	}
	public void setAnoEmissaoGuiaPagamento(String anoEmissaoGuiaPagamento) {
		this.anoEmissaoGuiaPagamento = anoEmissaoGuiaPagamento;
	}
	public String getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getCodigoLocalidade() {
		return codigoLocalidade;
	}
	public void setCodigoLocalidade(String codigoLocalidade) {
		this.codigoLocalidade = codigoLocalidade;
	}
	public String getCodigoTipoDebito() {
		return codigoTipoDebito;
	}
	public void setCodigoTipoDebito(String codigoTipoDebito) {
		this.codigoTipoDebito = codigoTipoDebito;
	}
	public String getCodigoTipoDocumento() {
		return codigoTipoDocumento;
	}
	public void setCodigoTipoDocumento(String codigoTipoDocumento) {
		this.codigoTipoDocumento = codigoTipoDocumento;
	}
	public String getDigitoVerificadorContaModulo10() {
		return digitoVerificadorContaModulo10;
	}
	public void setDigitoVerificadorContaModulo10(
			String digitoVerificadorContaModulo10) {
		this.digitoVerificadorContaModulo10 = digitoVerificadorContaModulo10;
	}
	public String getDigitoVerificadorGeral() {
		return digitoVerificadorGeral;
	}
	public void setDigitoVerificadorGeral(String digitoVerificadorGeral) {
		this.digitoVerificadorGeral = digitoVerificadorGeral;
	}
	public String getIdentificacaoClienteEmpresaCodigoBarras() {
		return identificacaoClienteEmpresaCodigoBarras;
	}
	public void setIdentificacaoClienteEmpresaCodigoBarras(
			String identificacaoClienteEmpresaCodigoBarras) {
		this.identificacaoClienteEmpresaCodigoBarras = identificacaoClienteEmpresaCodigoBarras;
	}
	public String getIdentificacaoEmpresa() {
		return identificacaoEmpresa;
	}
	public void setIdentificacaoEmpresa(String identificacaoEmpresa) {
		this.identificacaoEmpresa = identificacaoEmpresa;
	}
	public String getIdentificacaoProduto() {
		return identificacaoProduto;
	}
	public void setIdentificacaoProduto(String identificacaoProduto) {
		this.identificacaoProduto = identificacaoProduto;
	}
	public String getIdentificacaoSegmento() {
		return identificacaoSegmento;
	}
	public void setIdentificacaoSegmento(String identificacaoSegmento) {
		this.identificacaoSegmento = identificacaoSegmento;
	}
	public String getIdentificacaoValorRealOUReferencia() {
		return identificacaoValorRealOUReferencia;
	}
	public void setIdentificacaoValorRealOUReferencia(
			String identificacaoValorRealOUReferencia) {
		this.identificacaoValorRealOUReferencia = identificacaoValorRealOUReferencia;
	}
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getMesAnoReferenciaContaCodigoBarras() {
		return mesAnoReferenciaContaCodigoBarras;
	}
	public void setMesAnoReferenciaContaCodigoBarras(
			String mesAnoReferenciaContaCodigoBarras) {
		this.mesAnoReferenciaContaCodigoBarras = mesAnoReferenciaContaCodigoBarras;
	}
	public String getSequencialDocumentoCobranca() {
		return sequencialDocumentoCobranca;
	}
	public void setSequencialDocumentoCobranca(String sequencialDocumentoCobranca) {
		this.sequencialDocumentoCobranca = sequencialDocumentoCobranca;
	}
	public String getSequencialFaturaClienteResponsavel() {
		return sequencialFaturaClienteResponsavel;
	}
	public void setSequencialFaturaClienteResponsavel(
			String sequencialFaturaClienteResponsavel) {
		this.sequencialFaturaClienteResponsavel = sequencialFaturaClienteResponsavel;
	}
	public String getTipoPagamento() {
		return tipoPagamento;
	}
	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	public String getValorPagamento() {
		return valorPagamento;
	}
	public void setValorPagamento(String valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
    public String getCodigoBanco() {
        return codigoBanco;
    }
    public void setCodigoBanco(String codigoBanco) {
        this.codigoBanco = codigoBanco;
    }
    public String getFatorVencimento() {
        return fatorVencimento;
    }
    public void setFatorVencimento(String fatorVencimento) {
        this.fatorVencimento = fatorVencimento;
    }
    public String getNossoNumero() {
        return nossoNumero;
    }
    public void setNossoNumero(String nossoNumero) {
        this.nossoNumero = nossoNumero;
    }
    public String getTipoCarteira() {
        return tipoCarteira;
    }
    public void setTipoCarteira(String tipoCarteira) {
        this.tipoCarteira = tipoCarteira;
    }
    public String getValorCodigoBarras() {
        return valorCodigoBarras;
    }
    public void setValorCodigoBarras(String valorCodigoBarras) {
        this.valorCodigoBarras = valorCodigoBarras;
    }
	public String getIdentificacaoDocumento() {
		return identificacaoDocumento;
	}
	public void setIdentificacaoDocumento(String identificacaoDocumento) {
		this.identificacaoDocumento = identificacaoDocumento;
	}
    
    

}
