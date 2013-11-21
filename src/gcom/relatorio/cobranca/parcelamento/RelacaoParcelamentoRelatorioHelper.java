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
import java.util.Date;

public class RelacaoParcelamentoRelatorioHelper {
	
	private String situacao;
	private String localidade;
	private String municipio;
	private String cliente;
	private String ddd;
	private String telefone;
	private String matricula;
	private Integer parcelamento;
	private Date dataParcelamento;
	private String vencimento;
    private BigDecimal debitoTotal;
    private BigDecimal valorEntrada;
    private BigDecimal valorParcelamento;
    private Short numeroParcelamento;
    private Integer idLocalidade;
    private Integer idMunicipio;
    private Integer idGerencia;
    private String gerencia;
    private String unidade;
    
    public RelacaoParcelamentoRelatorioHelper(){    	
    }
    
	public RelacaoParcelamentoRelatorioHelper(String situacao, String localidade, String municipio, String cliente, String ddd, 
			String telefone, String matricula, Integer parcelamento, Date dataParcelamento, String vencimento, 
			BigDecimal debitoTotal, BigDecimal valorEntrada, BigDecimal valorParcelamento, Short numeroParcelamento,
			Integer idLocalidade, Integer idMunicipio, Integer idGerencia, String gerencia, String unidade) {		
		this.situacao = situacao;
		this.localidade = localidade;
		this.municipio = municipio;
		this.cliente = cliente;
		this.ddd = ddd;
		this.telefone = telefone;
		this.matricula = matricula;
		this.parcelamento = parcelamento;
		this.dataParcelamento = dataParcelamento;
		this.vencimento = vencimento;
		this.debitoTotal = debitoTotal;
		this.valorEntrada = valorEntrada;
		this.valorParcelamento = valorParcelamento;
		this.numeroParcelamento = numeroParcelamento;
		this.idLocalidade = idLocalidade;
		this.idMunicipio = idMunicipio;
		this.idGerencia = idGerencia;
		this.gerencia = gerencia;
		this.unidade = unidade;
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
	public Integer getIdGerencia() {
		return idGerencia;
	}

	/**
	 * @param idGerencia O idGerencia a ser setado.
	 */
	public void setIdGerencia(Integer idGerencia) {
		this.idGerencia = idGerencia;
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
	public Date getDataParcelamento() {
		return dataParcelamento;
	}
	/**
	 * @param dataParcelamento O dataParcelamento a ser setado.
	 */
	public void setDataParcelamento(Date dataParcelamento) {
		this.dataParcelamento = dataParcelamento;
	}
	/**
	 * @return Retorna o campo ddd.
	 */
	public String getDdd() {
		return ddd;
	}
	/**
	 * @param ddd O ddd a ser setado.
	 */
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	/**
	 * @return Retorna o campo debitoTotal.
	 */
	public BigDecimal getDebitoTotal() {
		return debitoTotal;
	}
	/**
	 * @param debitoTotal O debitoTotal a ser setado.
	 */
	public void setDebitoTotal(BigDecimal debitoTotal) {
		this.debitoTotal = debitoTotal;
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
	 * @return Retorna o campo matricula.
	 */
	public String getMatricula() {
		return matricula;
	}
	/**
	 * @param matricula O matricula a ser setado.
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	/**
	 * @return Retorna o campo numeroParcelamento.
	 */
	public Short getNumeroParcelamento() {
		return numeroParcelamento;
	}
	/**
	 * @param numeroParcelamento O numeroParcelamento a ser setado.
	 */
	public void setNumeroParcelamento(Short numeroParcelamento) {
		this.numeroParcelamento = numeroParcelamento;
	}
	/**
	 * @return Retorna o campo parcelamento.
	 */
	public Integer getParcelamento() {
		return parcelamento;
	}
	/**
	 * @param parcelamento O parcelamento a ser setado.
	 */
	public void setParcelamento(Integer parcelamento) {
		this.parcelamento = parcelamento;
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
	 * @return Retorna o campo valorParcelamento.
	 */
	public BigDecimal getValorParcelamento() {
		return valorParcelamento;
	}
	/**
	 * @param valorParcelamento O valorParcelamento a ser setado.
	 */
	public void setValorParcelamento(BigDecimal valorParcelamento) {
		this.valorParcelamento = valorParcelamento;
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
	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
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

	public Integer getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
}