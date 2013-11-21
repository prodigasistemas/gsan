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
package gcom.relatorio.financeiro;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

/**
 * [UC0824] Gerar Relatorio de Parametros Contábeis
 * 
 * @author Bruno Barros
 * @data 08/07/2008
 */
public class RelatorioParametrosContabeisFaturamentoBean implements RelatorioBean {

	private String tipoLancamento;
	private String itemLancamento;
	private String itemLancamentoContabil;
	private String categoria;
	private String contaContabilDebito;
	private String contaContabilCredito;
	private BigDecimal valorContabilizado;

    public RelatorioParametrosContabeisFaturamentoBean(String tipoLancamento, String itemLancamento, String itemLancamentoContabil, String categoria, String contaContabilDebito, String contaContabilCredito, BigDecimal valorContabilizado) {
		super();
		this.tipoLancamento = tipoLancamento;
		this.itemLancamento = itemLancamento;
		this.itemLancamentoContabil = itemLancamentoContabil;
		this.categoria = categoria;
		this.contaContabilDebito = contaContabilDebito;
		this.contaContabilCredito = contaContabilCredito;
		this.valorContabilizado = valorContabilizado;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getContaContabilCredito() {
		return contaContabilCredito;
	}
	public void setContaContabilCredito(String contaContabilCredito) {
		this.contaContabilCredito = contaContabilCredito;
	}
	public String getContaContabilDebito() {
		return contaContabilDebito;
	}
	public void setContaContabilDebito(String contaContabilDebito) {
		this.contaContabilDebito = contaContabilDebito;
	}
	public String getItemLancamento() {
		return itemLancamento;
	}
	public void setItemLancamento(String itemLancamento) {
		this.itemLancamento = itemLancamento;
	}
	public String getItemLancamentoContabil() {
		return itemLancamentoContabil;
	}
	public void setItemLancamentoContabil(String itemLancamentoContabil) {
		this.itemLancamentoContabil = itemLancamentoContabil;
	}
	public String getTipoLancamento() {
		return tipoLancamento;
	}
	public void setTipoLancamento(String tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}
	public BigDecimal getValorContabilizado() {
		return valorContabilizado;
	}
	public void setValorContabilizado(BigDecimal valorContabilizado) {
		this.valorContabilizado = valorContabilizado;
	}   
}
