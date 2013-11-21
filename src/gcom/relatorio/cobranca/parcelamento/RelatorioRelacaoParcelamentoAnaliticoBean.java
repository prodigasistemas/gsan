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
package gcom.relatorio.cobranca.parcelamento;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * [UC0878] Gerar Relação de Parcelamento - Visão Analitica
 * 
 * Bean que preencherá o relatorio
 * 
 * @author Bruno Barros
 * @date 04/02/2009
 *
 */
public class RelatorioRelacaoParcelamentoAnaliticoBean implements RelatorioBean {
	
	private String nome;
	private String matricula;
	private String telefone;
	private String unidade;
	private String numero;
	private Integer vencimento;
	private BigDecimal debito;
	private BigDecimal valorParcela;
	private BigDecimal valorEntrada;
	private Date data;
	private Integer parcela;
	private Integer qtdParcelasQuitadas;
	private Integer qtdParcelasCobradas;
	private Integer qtdParcelasACobrar;
	private BigDecimal valorParcelasQuitadas;
	private BigDecimal valorCobradas;
	private BigDecimal valorACobrar;
	private Integer idGerencia;
	private Integer idLocalidade;
	private Integer idMunicipio;
	private String descricaoGerencia;
	private String descricaoLocalidade;
	private String descricaoMunicipio;
	private String login;
	
	
	/**
	 * 
	 * Cria uma instancia do bean para o relatório
	 * 
	 * @param nome
	 * @param matricula
	 * @param telefone
	 * @param unidade
	 * @param numero
	 * @param vencimento
	 * @param debito
	 * @param valorParcelamento
	 * @param valorEntrada
	 * @param data
	 * @param parcela
	 * @param qtdParcelasQuitadas
	 * @param qtdParcelasCobradas
	 * @param qtdParcelasACobrar
	 * @param valorParcelasQuitadas
	 * @param valorCobradas
	 * @param valorACobrar
	 * @param idGerencia
	 * @param idLocalidade
	 * @param idMunicipio
	 * @param descricaoMunicipio
	 */
	public RelatorioRelacaoParcelamentoAnaliticoBean(
			String nome, 
			String matricula, 
			String telefone, 
			String unidade, 
			String numero, 
			Integer vencimento, 
			BigDecimal debito, 
			BigDecimal valorParcela, 
			BigDecimal valorEntrada, 
			Date data, 
			Integer parcela, 
			Integer qtdParcelasQuitadas, 
			Integer qtdParcelasCobradas, 
			Integer qtdParcelasACobrar, 
			BigDecimal valorParcelasQuitadas, 
			BigDecimal valorCobradas, 
			BigDecimal valorACobrar, 
			Integer idGerencia, 
			Integer idLocalidade,
			Integer idMunicipio,
			String descricaoGerencia, 
			String descricaoLocalidade,
			String descricaoMunicipio,
			String login) {
		super();

		this.nome = nome;
		this.matricula = matricula;
		this.telefone = telefone;
		this.unidade = unidade;
		this.numero = numero;
		this.vencimento = vencimento;
		this.debito = debito;
		this.valorParcela = valorParcela;
		this.valorEntrada = valorEntrada;
		this.data = data;
		this.parcela = parcela;
		this.qtdParcelasQuitadas = qtdParcelasQuitadas;
		this.qtdParcelasCobradas = qtdParcelasCobradas;
		this.qtdParcelasACobrar = qtdParcelasACobrar;
		this.valorParcelasQuitadas = valorParcelasQuitadas;
		this.valorCobradas = valorCobradas;
		this.valorACobrar = valorACobrar;
		this.idGerencia = idGerencia;
		this.idLocalidade = idLocalidade;
		this.idMunicipio = idMunicipio;
		this.descricaoGerencia = idGerencia + " " + descricaoGerencia;
		this.descricaoLocalidade = idLocalidade + " " + descricaoLocalidade;
		if(idMunicipio != null && descricaoMunicipio != null){
			this.descricaoMunicipio = idMunicipio + " " + descricaoMunicipio;
		}
		this.login = login;
	}
	public Date getData() {
		return data;
	}
	public BigDecimal getDebito() {
		return debito;
	}
	public Integer getIdGerencia() {
		return idGerencia;
	}
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	public String getMatricula() {
		return matricula;
	}
	public String getNome() {
		return nome;
	}
	public String getNumero() {
		return numero;
	}
	public Integer getParcela() {
		return parcela;
	}
	public Integer getQtdParcelasACobrar() {
		return qtdParcelasACobrar;
	}
	public Integer getQtdParcelasCobradas() {
		return qtdParcelasCobradas;
	}
	public Integer getQtdParcelasQuitadas() {
		return qtdParcelasQuitadas;
	}
	public String getTelefone() {
		return telefone;
	}
	public String getUnidade() {
		return unidade;
	}
	public BigDecimal getValorACobrar() {
		return valorACobrar;
	}
	public BigDecimal getValorCobradas() {
		return valorCobradas;
	}
	public BigDecimal getValorEntrada() {
		return valorEntrada;
	}
	public BigDecimal getValorParcela() {
		return valorParcela;
	}
	public BigDecimal getValorParcelasQuitadas() {
		return valorParcelasQuitadas;
	}
	public Integer getVencimento() {
		return vencimento;
	}
	public String getDescricaoGerencia() {
		return descricaoGerencia;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Integer getIdMunicipio() {
		return idMunicipio;
	}
	public String getDescricaoMunicipio() {
		return descricaoMunicipio;
	}
}