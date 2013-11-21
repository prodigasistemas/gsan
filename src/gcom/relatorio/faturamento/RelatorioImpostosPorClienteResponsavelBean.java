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
package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

/**
 * [UC0919] gERAR
 * Esta classe representa o Bean para a geraçao de relatório dos impostos por cliente
 * responsável - de acordo com um ano, mês de referência
 * 
 * - o design do relatório padrão é o: relatorioRelacaoImpostosPorClienteResponsavel
 * 
 * @author José Guilherme Macedo Vieira
 * @date 22/06/2009
 */
public class RelatorioImpostosPorClienteResponsavelBean implements RelatorioBean {
	
	//O id e o nome do cliente no formato XXXXX - NOMEDOCLIENTE
	//onde XXXXX é o ID do CLIENTE
	private String clienteIdNome;
	
	private String cnpjCliente;

	//O id do imovel
	private String imovelID;
	
	private String valorFatura;
	
	private String idFatura;
	
	private Integer idImpostoTipo;
	
	private String valorImposto;

	private String descricaoImposto;
		
	private String percentualAliquota;
	
	public RelatorioImpostosPorClienteResponsavelBean() {
	
	}
	
	public String getDescricaoImposto() {
		return descricaoImposto;
	}

	public void setDescricaoImposto(String descricaoImposto) {
		this.descricaoImposto = descricaoImposto;
	}

	public Integer getIdImpostoTipo() {
		return idImpostoTipo;
	}

	public void setIdImpostoTipo(Integer idImpostoTipo) {
		this.idImpostoTipo = idImpostoTipo;
	}

	public String getPercentualAliquota() {
		return percentualAliquota;
	}

	public void setPercentualAliquota(String percentualAliquota) {
		this.percentualAliquota = percentualAliquota;
	}


	public String getValorImposto() {
		return valorImposto;
	}

	public void setValorImposto(String valorImposto) {
		this.valorImposto = valorImposto;
	}

	public String getValorFatura() {
		return valorFatura;
	}
	public void setValorFatura(String valorFatura) {
		this.valorFatura = valorFatura;
	}
	public String getClienteIdNome() {
		return clienteIdNome;
	}
	public void setClienteIdNome(String clienteIdNome) {
		this.clienteIdNome = clienteIdNome;
	}
	public String getImovelID() {
		return imovelID;
	}
	public void setImovelID(String imovelID) {
		this.imovelID = imovelID;
	}

	public String getIdFatura() {
		return idFatura;
	}

	public void setIdFatura(String idFatura) {
		this.idFatura = idFatura;
	}

	public String getCnpjCliente() {
		return cnpjCliente;
	}

	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}
}