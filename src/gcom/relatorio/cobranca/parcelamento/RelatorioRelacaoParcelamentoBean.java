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

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Ana Maria
 * @date 15/05/2007
 */
public class RelatorioRelacaoParcelamentoBean implements RelatorioBean {
	
  private String situacao;
  private String localidade;
  private String municipio;
  private String cliente;
  private String telefone;
  private String matricula;
  private String idParcelamento;
  private BigDecimal valorDebito;
  private BigDecimal valorEntrada;
  private BigDecimal valorParcelas;
  private String dataParcelamento;
  private String vencimento;
  private String numeroParcelas;
  private String idLocalidade;
  private String idMunicipio;
  private String idGerencia;
  private String gerencia;
  private String unidade;
  

	public RelatorioRelacaoParcelamentoBean(String situacao, String localidade, String municipio,
			String cliente, String telefone, String matricula,
			String idParcelamento, BigDecimal valorDebito, BigDecimal valorEntrada,
			BigDecimal valorParcelas, String dataParcelamento, String vencimento,
			String numeroParcelas, String idLocalidade, String idMunicipio, String idGerencia, String gerencia, String unidade) {
		this.situacao = situacao;
		this.localidade = localidade;
		this.municipio = municipio;
		this.idMunicipio = idMunicipio;
		this.cliente = cliente;
		this.telefone = telefone;
		this.matricula = matricula;
		this.idParcelamento = idParcelamento;
		this.valorDebito = valorDebito;
		this.valorEntrada = valorEntrada;
		this.valorParcelas = valorParcelas;
		this.dataParcelamento = dataParcelamento;
		this.vencimento = vencimento;
		this.numeroParcelas = numeroParcelas;
		this.idLocalidade = idLocalidade;
		this.idGerencia = idGerencia;
		this.gerencia = gerencia;
		this.unidade = unidade;
	}

	/**
	 * @return Retorna o campo cliente.
	 */
	public String getCliente() {
		return cliente;
	}

	/**
	 * @param cliente O cliente a ser setado.
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return Retorna o campo dataParcelamento.
	 */
	public String getDataParcelamento() {
		return dataParcelamento;
	}

	/**
	 * @param dataParcelamento O dataParcelamento a ser setado.
	 */
	public void setDataParcelamento(String dataParcelamento) {
		this.dataParcelamento = dataParcelamento;
	}

	/**
	 * @return Retorna o campo idParcelamento.
	 */
	public String getIdParcelamento() {
		return idParcelamento;
	}

	/**
	 * @param idParcelamento O idParcelamento a ser setado.
	 */
	public void setIdParcelamento(String idParcelamento) {
		this.idParcelamento = idParcelamento;
	}

	/**
	 * @return Retorna o campo localidade.
	 */
	public String getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return Retorna o campo matrícula.
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * @param matrícula O matrícula a ser setado.
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * @return Retorna o campo numeroParcelas.
	 */
	public String getNumeroParcelas() {
		return numeroParcelas;
	}

	/**
	 * @param numeroParcelas O numeroParcelas a ser setado.
	 */
	public void setNumeroParcelas(String numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	/**
	 * @return Retorna o campo situacao.
	 */
	public String getSituacao() {
		return situacao;
	}

	/**
	 * @param situacao O situacao a ser setado.
	 */
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	/**
	 * @return Retorna o campo telefone.
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone O telefone a ser setado.
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * @return Retorna o campo valorDebito.
	 */
	public BigDecimal getValorDebito() {
		return valorDebito;
	}

	/**
	 * @param valorDebito O valorDebito a ser setado.
	 */
	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}

	/**
	 * @return Retorna o campo valorEntrada.
	 */
	public BigDecimal getValorEntrada() {
		return valorEntrada;
	}

	/**
	 * @param valorEntrada O valorEntrada a ser setado.
	 */
	public void setValorEntrada(BigDecimal valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	/**
	 * @return Retorna o campo valorParcelas.
	 */
	public BigDecimal getValorParcelas() {
		return valorParcelas;
	}

	/**
	 * @param valorParcelas O valorParcelas a ser setado.
	 */
	public void setValorParcelas(BigDecimal valorParcelas) {
		this.valorParcelas = valorParcelas;
	}

	/**
	 * @return Retorna o campo vencimento.
	 */
	public String getVencimento() {
		return vencimento;
	}

	/**
	 * @param vencimento O vencimento a ser setado.
	 */
	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
	}

	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public String getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * @return Retorna o campo gerencia.
	 */
	public String getGerencia() {
		return gerencia;
	}

	/**
	 * @param gerencia O gerencia a ser setado.
	 */
	public void setGerencia(String gerencia) {
		this.gerencia = gerencia;
	}

	/**
	 * @return Retorna o campo idGerencia.
	 */
	public String getIdGerencia() {
		return idGerencia;
	}

	/**
	 * @param idGerencia O idGerencia a ser setado.
	 */
	public void setIdGerencia(String idGerencia) {
		this.idGerencia = idGerencia;
	}

	/**
	 * @return Retorna o campo unidade.
	 */
	public String getUnidade() {
		return unidade;
	}

	/**
	 * @param unidade O unidade a ser setado.
	 */
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
}