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
package gcom.faturamento;

import gcom.cobranca.FiltroDocumentoCobranca;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DocumentoNaoEntregue extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private Date dataTentativaEntrega;

    /** persistent field */
    private short numeroTentativa;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.cadastro.cliente.Cliente cliente;

    /** persistent field */
    private gcom.faturamento.conta.Fatura fatura;

    /** persistent field */
    private gcom.cobranca.CobrancaDocumento cobrancaDocumento;

    /** persistent field */
    private gcom.cobranca.DocumentoTipo documentoTipo;

    /** persistent field */
    private gcom.faturamento.conta.MotivoNaoEntregaDocumento motivoNaoEntregaDocumento;

    /** persistent field */
    private gcom.faturamento.GuiaPagamentoGeral guiaPagamentoGeral;

    /** persistent field */
    private gcom.faturamento.conta.ContaGeral contaGeral;

    /** full constructor */
    public DocumentoNaoEntregue(Integer id, Date dataTentativaEntrega, short numeroTentativa, Date ultimaAlteracao, gcom.cadastro.cliente.Cliente cliente, gcom.faturamento.conta.Fatura fatura, gcom.cobranca.CobrancaDocumento cobrancaDocumento, gcom.cobranca.DocumentoTipo documentoTipo, gcom.faturamento.conta.MotivoNaoEntregaDocumento motivoNaoEntregaDocumento, gcom.faturamento.GuiaPagamentoGeral guiaPagamentoGeral, gcom.faturamento.conta.ContaGeral contaGeral) {
        this.id = id;
        this.dataTentativaEntrega = dataTentativaEntrega;
        this.numeroTentativa = numeroTentativa;
        this.ultimaAlteracao = ultimaAlteracao;
        this.cliente = cliente;
        this.fatura = fatura;
        this.cobrancaDocumento = cobrancaDocumento;
        this.documentoTipo = documentoTipo;
        this.motivoNaoEntregaDocumento = motivoNaoEntregaDocumento;
        this.guiaPagamentoGeral = guiaPagamentoGeral;
        this.contaGeral = contaGeral;
    }

    /** default constructor */
    public DocumentoNaoEntregue() {
    }

	public gcom.cadastro.cliente.Cliente getCliente() {
		return cliente;
	}

	public void setCliente(gcom.cadastro.cliente.Cliente cliente) {
		this.cliente = cliente;
	}

	public gcom.cobranca.CobrancaDocumento getCobrancaDocumento() {
		return cobrancaDocumento;
	}

	public void setCobrancaDocumento(
			gcom.cobranca.CobrancaDocumento cobrancaDocumento) {
		this.cobrancaDocumento = cobrancaDocumento;
	}

	public gcom.faturamento.conta.ContaGeral getContaGeral() {
		return contaGeral;
	}

	public void setContaGeral(gcom.faturamento.conta.ContaGeral contaGeral) {
		this.contaGeral = contaGeral;
	}

	public Date getDataTentativaEntrega() {
		return dataTentativaEntrega;
	}

	public void setDataTentativaEntrega(Date dataTentativaEntrega) {
		this.dataTentativaEntrega = dataTentativaEntrega;
	}

	public gcom.cobranca.DocumentoTipo getDocumentoTipo() {
		return documentoTipo;
	}

	public void setDocumentoTipo(gcom.cobranca.DocumentoTipo documentoTipo) {
		this.documentoTipo = documentoTipo;
	}

	public gcom.faturamento.conta.Fatura getFatura() {
		return fatura;
	}

	public void setFatura(gcom.faturamento.conta.Fatura fatura) {
		this.fatura = fatura;
	}

	public gcom.faturamento.GuiaPagamentoGeral getGuiaPagamentoGeral() {
		return guiaPagamentoGeral;
	}

	public void setGuiaPagamentoGeral(
			gcom.faturamento.GuiaPagamentoGeral guiaPagamentoGeral) {
		this.guiaPagamentoGeral = guiaPagamentoGeral;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public gcom.faturamento.conta.MotivoNaoEntregaDocumento getMotivoNaoEntregaDocumento() {
		return motivoNaoEntregaDocumento;
	}

	public void setMotivoNaoEntregaDocumento(
			gcom.faturamento.conta.MotivoNaoEntregaDocumento motivoNaoEntregaDocumento) {
		this.motivoNaoEntregaDocumento = motivoNaoEntregaDocumento;
	}

	public short getNumeroTentativa() {
		return numeroTentativa;
	}

	public void setNumeroTentativa(short numeroTentativa) {
		this.numeroTentativa = numeroTentativa;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	   public String toString() {	        return new ToStringBuilder(this)	            .append("id", getId())	            .toString();	    }	    		public String[] retornaCamposChavePrimaria(){			String[] retorno = new String[1];			retorno[0] = "id";			return retorno;		}			    public Filtro retornaFiltro(){	    	FiltroDocumentoCobranca filtroDocumentoNaoEntregue = new FiltroDocumentoCobranca();	    	filtroDocumentoNaoEntregue.adicionarParametro(new ParametroSimples(FiltroDocumentoCobranca.ID,this.getId()));	    	filtroDocumentoNaoEntregue.adicionarCaminhoParaCarregamentoEntidade("documentoTipo");						return filtroDocumentoNaoEntregue;		}

}
