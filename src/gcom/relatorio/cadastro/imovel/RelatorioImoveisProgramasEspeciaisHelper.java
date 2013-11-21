/*
 * Copyright (C) 2007-2007 the GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
 * Foundation, Inc., 59 Temple Place – Suite 330, Boston, MA 02111-1307, USA
 */

/*
 * GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
package gcom.relatorio.cadastro.imovel;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Classe que irá auxiliar no formato de entrada da pesquisa do relatorio de
 * imoveis por Programas Especiais
 * 
 * @author Hugo Leonardo
 * @date 18/01/2010
 */
public class RelatorioImoveisProgramasEspeciaisHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//private Integer idUnidadeNegocio;
	//private String nomeUnidadeNegocio;
	private Integer idRegiaoDesenvolvimento;
	private String nomeRegiaoDesenvolvimento;
	private Integer idLocalidade;
	private String nomeLocalidade;
	private String idImovel;
	private String nomeUsuario;
	private String situacaoMedicao;
	private BigDecimal valorConta;
	private Integer consumoAgua;
	private String endereco;
	
	private Integer qtdImoveisSemHidr;
	private BigDecimal valorContasSemHidr;
	private Integer qtdImoveisComHidr;
	private BigDecimal valorContasComHidr;

	
	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSituacaoMedicao() {
		return situacaoMedicao;
	}

	public void setSituacaoMedicao(String situacaoMedicao) {
		this.situacaoMedicao = situacaoMedicao;
	}

	public Integer getConsumoAgua() {
		return consumoAgua;
	}
	
	public void setConsumoAgua(Integer consumoAgua) {
		this.consumoAgua = consumoAgua;
	}

	public BigDecimal getValorConta() {
		return valorConta;
	}

	public void setValorConta(BigDecimal valorConta) {
		this.valorConta = valorConta;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public Integer getQtdImoveisComHidr() {
		return qtdImoveisComHidr;
	}
	
	public void setQtdImoveisComHidr(Integer qtdImoveisComHidr) {
		this.qtdImoveisComHidr = qtdImoveisComHidr;
	}

	public Integer getQtdImoveisSemHidr() {
		return qtdImoveisSemHidr;
	}

	public void setQtdImoveisSemHidr(Integer qtdImoveisSemHidr) {
		this.qtdImoveisSemHidr = qtdImoveisSemHidr;
	}
	
	public BigDecimal getValorContasComHidr() {
		return valorContasComHidr;
	}
	
	public void setValorContasComHidr(BigDecimal valorContasComHidr) {
		this.valorContasComHidr = valorContasComHidr;
	}

	public BigDecimal getValorContasSemHidr() {
		return valorContasSemHidr;
	}

	public void setValorContasSemHidr(BigDecimal valorContasSemHidr) {
		this.valorContasSemHidr = valorContasSemHidr;
	}

	public Integer getIdRegiaoDesenvolvimento() {
		return idRegiaoDesenvolvimento;
	}

	public void setIdRegiaoDesenvolvimento(Integer idRegiaoDesenvolvimento) {
		this.idRegiaoDesenvolvimento = idRegiaoDesenvolvimento;
	}

	public String getNomeRegiaoDesenvolvimento() {
		return nomeRegiaoDesenvolvimento;
	}

	public void setNomeRegiaoDesenvolvimento(String nomeRegiaoDesenvolvimento) {
		this.nomeRegiaoDesenvolvimento = nomeRegiaoDesenvolvimento;
	}
	
}