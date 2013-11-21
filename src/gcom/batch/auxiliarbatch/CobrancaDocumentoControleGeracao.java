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
package gcom.batch.auxiliarbatch;

import gcom.batch.ProcessoIniciado;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class CobrancaDocumentoControleGeracao implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer quantidadeCobrancaDocumento;
    
    private Integer quantidadeCobrancaDocumentoItem;

    private BigDecimal valorTotalCobrancaDocumentos;

    private Date ultimaAlteracao;

    private ProcessoIniciado processoIniciado;
    
    private CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma;
    
    private CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando;
    
    public CobrancaDocumentoControleGeracao() {
		super();
	}

	public CobrancaDocumentoControleGeracao(Integer quantidadeCobrancaDocumento, Integer quantidadeCobrancaDocumentoItem, BigDecimal valorTotalCobrancaDocumentos, Date ultimaAlteracao, ProcessoIniciado processoIniciado, CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma, CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando) {
		super();
		// TODO Auto-generated constructor stub
		this.quantidadeCobrancaDocumento = quantidadeCobrancaDocumento;
		this.quantidadeCobrancaDocumentoItem = quantidadeCobrancaDocumentoItem;
		this.valorTotalCobrancaDocumentos = valorTotalCobrancaDocumentos;
		this.ultimaAlteracao = ultimaAlteracao;
		this.processoIniciado = processoIniciado;
		this.cobrancaAcaoAtividadeCronograma = cobrancaAcaoAtividadeCronograma;
		this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ProcessoIniciado getProcessoIniciado() {
		return processoIniciado;
	}
	public void setProcessoIniciado(ProcessoIniciado processoIniciado) {
		this.processoIniciado = processoIniciado;
	}
	public Integer getQuantidadeCobrancaDocumento() {
		return quantidadeCobrancaDocumento;
	}
	public void setQuantidadeCobrancaDocumento(Integer quantidadeCobrancaDocumento) {
		this.quantidadeCobrancaDocumento = quantidadeCobrancaDocumento;
	}
	public Integer getQuantidadeCobrancaDocumentoItem() {
		return quantidadeCobrancaDocumentoItem;
	}
	public void setQuantidadeCobrancaDocumentoItem(
			Integer quantidadeCobrancaDocumentoItem) {
		this.quantidadeCobrancaDocumentoItem = quantidadeCobrancaDocumentoItem;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public BigDecimal getValorTotalCobrancaDocumentos() {
		return valorTotalCobrancaDocumentos;
	}
	public void setValorTotalCobrancaDocumentos(
			BigDecimal valorTotalCobrancaDocumentos) {
		this.valorTotalCobrancaDocumentos = valorTotalCobrancaDocumentos;
	}
	public CobrancaAcaoAtividadeComando getCobrancaAcaoAtividadeComando() {
		return cobrancaAcaoAtividadeComando;
	}
	public void setCobrancaAcaoAtividadeComando(
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando) {
		this.cobrancaAcaoAtividadeComando = cobrancaAcaoAtividadeComando;
	}
	public CobrancaAcaoAtividadeCronograma getCobrancaAcaoAtividadeCronograma() {
		return cobrancaAcaoAtividadeCronograma;
	}
	public void setCobrancaAcaoAtividadeCronograma(
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma) {
		this.cobrancaAcaoAtividadeCronograma = cobrancaAcaoAtividadeCronograma;
	}
}