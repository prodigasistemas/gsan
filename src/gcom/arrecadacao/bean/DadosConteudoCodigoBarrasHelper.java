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

/**
 * Classe que irá auxiliar no formato do retorno da pesquisa dos itens de um
 * determinado movimento do arrecadador
 *
 * @author Raphael Rossiter
 * @date 22/03/2006
 */
public class DadosConteudoCodigoBarrasHelper {
	
	private String identificacaoProduto;
	private String identificacaoSegmento;
	private String identificacaoValorRealOUReferencia;
	private String digitoVerificadorGeral;
	private String valorPagamento;
	private String identificacaoEmpresa;
	private String tipoPagamento;
	private String codigoLocalidade;
	private String matriculaImovel;
	private String mesAnoReferenciaConta;
	private String digitoVerificadorContaModulo10;
	private String codigoTipoDebito;
	private String anoEmissaoGuiaPagamento;
	private String sequencialDocumentoCobranca;
	private String codigoTipoDocumento;
	private String codigoCliente;
	private String sequencialFaturaClienteResponsavel;
	
	private String codigoOrigemConta;
	private String numeroDocumento;
	private String qualificacao;
	private String mesAno;
    private String identificacaoTituloNoBanco;
    
    private String identificacaoDocumento;
    
    //codigo de barras da Ficha de Compensação
    private String codigoBanco;
    private String codigoMoeda;
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
	public String getMesAnoReferenciaConta() {
		return mesAnoReferenciaConta;
	}
	public void setMesAnoReferenciaConta(String mesAnoReferenciaConta) {
		this.mesAnoReferenciaConta = mesAnoReferenciaConta;
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
	public String getIdentificacaoSegmento() {
		return identificacaoSegmento;
	}
	public void setIdentificacaoSegmento(String identificacaoSegmento) {
		this.identificacaoSegmento = identificacaoSegmento;
	}
    public String getIdentificacaoTituloNoBanco() {
        return identificacaoTituloNoBanco;
    }
    public void setIdentificacaoTituloNoBanco(String identificacaoTituloNoBanco) {
        this.identificacaoTituloNoBanco = identificacaoTituloNoBanco;
    }
    public String getCodigoBanco() {
        return codigoBanco;
    }
    public void setCodigoBanco(String codigoBanco) {
        this.codigoBanco = codigoBanco;
    }
    public String getCodigoMoeda() {
        return codigoMoeda;
    }
    public void setCodigoMoeda(String codigoMoeda) {
        this.codigoMoeda = codigoMoeda;
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
