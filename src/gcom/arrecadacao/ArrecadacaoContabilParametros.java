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
package gcom.arrecadacao;

import gcom.cadastro.imovel.Categoria;
import gcom.financeiro.ContaContabil;
import gcom.financeiro.lancamento.LancamentoItem;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.financeiro.lancamento.LancamentoTipo;

import java.util.Date;

/**
 * 
 * Descrição da classe 
 *
 * @author Thiago Toscano
 * @date 16/05/2006
 */
public class ArrecadacaoContabilParametros {

    private Integer id;

    private String descricaoHistoricoCredito;

    private String descricaoHistoricoDebito;

    private Date ultimaAlteracao;

    private RecebimentoTipo recebimentoTipo;

    private LancamentoTipo lancamentoTipo;

    private LancamentoItem lancamentoItem;

    private LancamentoItemContabil lancamentoItemContabil;

    private Categoria categoria;

    private ContaContabil contaContabilDebito;

    private ContaContabil contaContabilCredito;

	public ArrecadacaoContabilParametros() {
		  
	}

	public ArrecadacaoContabilParametros(Integer id, String descricaoHistoricoCredito, String descricaoHistoricoDebito, Date ultimaAlteracao, RecebimentoTipo recebimentoTipo, LancamentoTipo lancamentoTipo, LancamentoItem lancamentoItem, LancamentoItemContabil lancamentoItemContabil, Categoria categoria, ContaContabil contaContabilDebito, ContaContabil contaContabilCredito) {

		// TODO Auto-generated constructor stub
		this.id = id;
		this.descricaoHistoricoCredito = descricaoHistoricoCredito;
		this.descricaoHistoricoDebito = descricaoHistoricoDebito;
		this.ultimaAlteracao = ultimaAlteracao;
		this.recebimentoTipo = recebimentoTipo;
		this.lancamentoTipo = lancamentoTipo;
		this.lancamentoItem = lancamentoItem;
		this.lancamentoItemContabil = lancamentoItemContabil;
		this.categoria = categoria;
		this.contaContabilDebito = contaContabilDebito;
		this.contaContabilCredito = contaContabilCredito;
	}

	/**
	 * @return Retorna o campo categoria.
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria O categoria a ser setado.
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return Retorna o campo contaContabilCredito.
	 */
	public ContaContabil getContaContabilCredito() {
		return contaContabilCredito;
	}

	/**
	 * @param contaContabilCredito O contaContabilCredito a ser setado.
	 */
	public void setContaContabilCredito(ContaContabil contaContabilCredito) {
		this.contaContabilCredito = contaContabilCredito;
	}

	/**
	 * @return Retorna o campo contaContabilDebito.
	 */
	public ContaContabil getContaContabilDebito() {
		return contaContabilDebito;
	}

	/**
	 * @param contaContabilDebito O contaContabilDebito a ser setado.
	 */
	public void setContaContabilDebito(ContaContabil contaContabilDebito) {
		this.contaContabilDebito = contaContabilDebito;
	}

	/**
	 * @return Retorna o campo descricaoHistoricoCredito.
	 */
	public String getDescricaoHistoricoCredito() {
		return descricaoHistoricoCredito;
	}

	/**
	 * @param descricaoHistoricoCredito O descricaoHistoricoCredito a ser setado.
	 */
	public void setDescricaoHistoricoCredito(String descricaoHistoricoCredito) {
		this.descricaoHistoricoCredito = descricaoHistoricoCredito;
	}

	/**
	 * @return Retorna o campo descricaoHistoricoDebito.
	 */
	public String getDescricaoHistoricoDebito() {
		return descricaoHistoricoDebito;
	}

	/**
	 * @param descricaoHistoricoDebito O descricaoHistoricoDebito a ser setado.
	 */
	public void setDescricaoHistoricoDebito(String descricaoHistoricoDebito) {
		this.descricaoHistoricoDebito = descricaoHistoricoDebito;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo lancamentoItem.
	 */
	public LancamentoItem getLancamentoItem() {
		return lancamentoItem;
	}

	/**
	 * @param lancamentoItem O lancamentoItem a ser setado.
	 */
	public void setLancamentoItem(LancamentoItem lancamentoItem) {
		this.lancamentoItem = lancamentoItem;
	}

	/**
	 * @return Retorna o campo lancamentoItemContabil.
	 */
	public LancamentoItemContabil getLancamentoItemContabil() {
		return lancamentoItemContabil;
	}

	/**
	 * @param lancamentoItemContabil O lancamentoItemContabil a ser setado.
	 */
	public void setLancamentoItemContabil(
			LancamentoItemContabil lancamentoItemContabil) {
		this.lancamentoItemContabil = lancamentoItemContabil;
	}

	/**
	 * @return Retorna o campo lancamentoTipo.
	 */
	public LancamentoTipo getLancamentoTipo() {
		return lancamentoTipo;
	}

	/**
	 * @param lancamentoTipo O lancamentoTipo a ser setado.
	 */
	public void setLancamentoTipo(LancamentoTipo lancamentoTipo) {
		this.lancamentoTipo = lancamentoTipo;
	}

	/**
	 * @return Retorna o campo recebimentoTipo.
	 */
	public RecebimentoTipo getRecebimentoTipo() {
		return recebimentoTipo;
	}

	/**
	 * @param recebimentoTipo O recebimentoTipo a ser setado.
	 */
	public void setRecebimentoTipo(RecebimentoTipo recebimentoTipo) {
		this.recebimentoTipo = recebimentoTipo;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}