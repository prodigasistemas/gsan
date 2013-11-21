/**
 * 
 */
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
 * Rômulo Aurélio de Melo Souza Filho
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
package gcom.cobranca;

import gcom.cadastro.empresa.Empresa;

import java.io.Serializable;

public class RelatorioPagamentosContasCobrancaEmpresaHelper implements
		Serializable {
	private static final long serialVersionUID = 1L;
	private Empresa empresa;

	private int referenciaPagamentoInicial;

	private int referenciaPagamentoFinal;

	private String opcaoTotalizacao;

	private Integer codigoLocalidade;

	private Integer codigoGerencia;

	private Integer unidadeNegocio;

	public Integer getCodigoGerencia() {
		return codigoGerencia;
	}

	public void setCodigoGerencia(Integer codigoGerencia) {
		this.codigoGerencia = codigoGerencia;
	}

	public Integer getCodigoLocalidade() {
		return codigoLocalidade;
	}

	public void setCodigoLocalidade(Integer codigoLocalidade) {
		this.codigoLocalidade = codigoLocalidade;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getOpcaoTotalizacao() {
		return opcaoTotalizacao;
	}

	public void setOpcaoTotalizacao(String opcaoTotalizacao) {
		this.opcaoTotalizacao = opcaoTotalizacao;
	}

	public int getReferenciaPagamentoFinal() {
		return referenciaPagamentoFinal;
	}

	public void setReferenciaPagamentoFinal(int referenciaPagamentoFinal) {
		this.referenciaPagamentoFinal = referenciaPagamentoFinal;
	}

	public int getReferenciaPagamentoInicial() {
		return referenciaPagamentoInicial;
	}

	public void setReferenciaPagamentoInicial(int referenciaPagamentoInicial) {
		this.referenciaPagamentoInicial = referenciaPagamentoInicial;
	}

	public Integer getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(Integer unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	/**
	 * Construtor de RelatorioPagamentosContasCobrancaEmpresaHelper 
	 * 
	 * @param empresa
	 * @param referenciaPagamentoInicial
	 * @param referenciaPagamentoFinal
	 * @param opcaoEmissao
	 * @param tipoFormatoRelatorio
	 * @param opcaoTotalizacao
	 * @param codigoLocalidade
	 * @param codigoGerencia
	 * @param unidadeNegocio
	 */
	public RelatorioPagamentosContasCobrancaEmpresaHelper(Empresa empresa, int referenciaPagamentoInicial, int referenciaPagamentoFinal,  String opcaoTotalizacao, Integer codigoLocalidade, Integer codigoGerencia, Integer unidadeNegocio) {
		super();
		// TODO Auto-generated constructor stub
		this.empresa = empresa;
		this.referenciaPagamentoInicial = referenciaPagamentoInicial;
		this.referenciaPagamentoFinal = referenciaPagamentoFinal;
		this.opcaoTotalizacao = opcaoTotalizacao;
		this.codigoLocalidade = codigoLocalidade;
		this.codigoGerencia = codigoGerencia;
		this.unidadeNegocio = unidadeNegocio;
	}

}
