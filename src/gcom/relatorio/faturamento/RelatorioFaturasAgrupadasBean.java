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

import java.math.BigDecimal;

import gcom.relatorio.RelatorioBean;

public class RelatorioFaturasAgrupadasBean implements RelatorioBean {

	private Integer idResponsavel;
	
	private String nomeResponsavel;
	
	private String enderecoResponsavel;
	
	private String numeroFatura;
	
	private String controle;
	
	private String referencia;
	
	private String emissao;
	
	private String vencimento;
	
	private String matricula;

	private String nomeUsuario;
	
	private String enderecoUsuario;
	
	private String unidade;
	
	private String sistema;
	
	private String hidrometro;
	
	private String tipo;
	
	private String leitura;
	
	private String consumo;
	
	private BigDecimal retencao;
	
	private BigDecimal valor;
	
	private String codigoBarras;
	
	private String codigoBarrasFormatado;
	
	private String representacaoNumericaCodBarraFormatada;
	
	private String representacaoNumericaCodBarraSemDigito;
	
	private BigDecimal valorTotal;
	
	private String valorTotalExtenso;
	
	private Integer totalFaturasResponsavel;
	
	private BigDecimal percentualAliquota;
	
	private String idFatura;
	
	private Integer indicadorContaHist; 
	
	private Integer idFaturaItem;
	
	public RelatorioFaturasAgrupadasBean() {
		
	}

	public RelatorioFaturasAgrupadasBean(Integer idResponsavel,
			String nomeResponsavel, String enderecoResponsavel,
			String numeroFatura, String controle, String referencia,
			String emissao, String vencimento, String matricula,
			String nomeUsuario, String enderecoUsuario, String unidade,
			String sistema, String hidrometro, String tipo, String leitura,
			String consumo, BigDecimal retencao, BigDecimal valor,
			String codigoBarras, String codigoBarrasFormatado, String representacaoNumericaCodBarraFormatada,
			BigDecimal valorTotal, String valorTotalExtenso, Integer totalFaturasResponsavel, String representacaoNumericaCodBarraSemDigito) {
		this.idResponsavel = idResponsavel;
		this.nomeResponsavel = nomeResponsavel;
		this.enderecoResponsavel = enderecoResponsavel;
		this.numeroFatura = numeroFatura;
		this.controle = controle;
		this.referencia = referencia;
		this.emissao = emissao;
		this.vencimento = vencimento;
		this.matricula = matricula;
		this.nomeUsuario = nomeUsuario;
		this.enderecoUsuario = enderecoUsuario;
		this.unidade = unidade;
		this.sistema = sistema;
		this.hidrometro = hidrometro;
		this.tipo = tipo;
		this.leitura = leitura;
		this.consumo = consumo;
		this.retencao = retencao;
		this.valor = valor;
		this.codigoBarras = codigoBarras;
		this.codigoBarrasFormatado = codigoBarrasFormatado;
		this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
		this.valorTotal = valorTotal;
		this.valorTotalExtenso = valorTotalExtenso;
		this.totalFaturasResponsavel = totalFaturasResponsavel;
		this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
	}

	
	public Integer getIdFaturaItem() {
		return idFaturaItem;
	}

	public void setIdFaturaItem(Integer idFaturaItem) {
		this.idFaturaItem = idFaturaItem;
	}

	public Integer getIndicadorContaHist() {
		return indicadorContaHist;
	}

	public void setIndicadorContaHist(Integer indicadorContaHist) {
		this.indicadorContaHist = indicadorContaHist;
	}

	/**
	 * @return Retorna o campo valorTotal.
	 */
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	/**
	 * @param valorTotal O valorTotal a ser setado.
	 */
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	/**
	 * @return Retorna o campo valorTotalExtenso.
	 */
	public String getValorTotalExtenso() {
		return valorTotalExtenso;
	}

	/**
	 * @param valorTotalExtenso O valorTotalExtenso a ser setado.
	 */
	public void setValorTotalExtenso(String valorTotalExtenso) {
		this.valorTotalExtenso = valorTotalExtenso;
	}

	/**
	 * @return Retorna o campo consumo.
	 */
	public String getConsumo() {
		return consumo;
	}

	/**
	 * @param consumo O consumo a ser setado.
	 */
	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}

	/**
	 * @return Retorna o campo controle.
	 */
	public String getControle() {
		return controle;
	}

	/**
	 * @param controle O controle a ser setado.
	 */
	public void setControle(String controle) {
		this.controle = controle;
	}

	/**
	 * @return Retorna o campo emissao.
	 */
	public String getEmissao() {
		return emissao;
	}

	/**
	 * @param emissao O emissao a ser setado.
	 */
	public void setEmissao(String emissao) {
		this.emissao = emissao;
	}

	/**
	 * @return Retorna o campo enderecoResponsavel.
	 */
	public String getEnderecoResponsavel() {
		return enderecoResponsavel;
	}

	/**
	 * @param enderecoResponsavel O enderecoResponsavel a ser setado.
	 */
	public void setEnderecoResponsavel(String enderecoResponsavel) {
		this.enderecoResponsavel = enderecoResponsavel;
	}

	/**
	 * @return Retorna o campo enderecoUsuario.
	 */
	public String getEnderecoUsuario() {
		return enderecoUsuario;
	}

	/**
	 * @param enderecoUsuario O enderecoUsuario a ser setado.
	 */
	public void setEnderecoUsuario(String enderecoUsuario) {
		this.enderecoUsuario = enderecoUsuario;
	}

	/**
	 * @return Retorna o campo hidrometro.
	 */
	public String getHidrometro() {
		return hidrometro;
	}

	/**
	 * @param hidrometro O hidrometro a ser setado.
	 */
	public void setHidrometro(String hidrometro) {
		this.hidrometro = hidrometro;
	}

	/**
	 * @return Retorna o campo idResponsavel.
	 */
	public Integer getIdResponsavel() {
		return idResponsavel;
	}

	/**
	 * @param idResponsavel O idResponsavel a ser setado.
	 */
	public void setIdResponsavel(Integer idResponsavel) {
		this.idResponsavel = idResponsavel;
	}

	/**
	 * @return Retorna o campo leitura.
	 */
	public String getLeitura() {
		return leitura;
	}

	/**
	 * @param leitura O leitura a ser setado.
	 */
	public void setLeitura(String leitura) {
		this.leitura = leitura;
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
	 * @return Retorna o campo nomeResponsavel.
	 */
	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	/**
	 * @param nomeResponsavel O nomeResponsavel a ser setado.
	 */
	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	/**
	 * @return Retorna o campo nomeUsuario.
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	/**
	 * @param nomeUsuario O nomeUsuario a ser setado.
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	/**
	 * @return Retorna o campo numeroFatura.
	 */
	public String getNumeroFatura() {
		return numeroFatura;
	}

	/**
	 * @param numeroFatura O numeroFatura a ser setado.
	 */
	public void setNumeroFatura(String numeroFatura) {
		this.numeroFatura = numeroFatura;
	}

	/**
	 * @return Retorna o campo referencia.
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * @param referencia O referencia a ser setado.
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	/**
	 * @return Retorna o campo retencao.
	 */
	public BigDecimal getRetencao() {
		return retencao;
	}

	/**
	 * @param retencao O retencao a ser setado.
	 */
	public void setRetencao(BigDecimal retencao) {
		this.retencao = retencao;
	}

	/**
	 * @return Retorna o campo sistema.
	 */
	public String getSistema() {
		return sistema;
	}

	/**
	 * @param sistema O sistema a ser setado.
	 */
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	/**
	 * @return Retorna o campo tipo.
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo O tipo a ser setado.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	/**
	 * @return Retorna o campo valor.
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * @param valor O valor a ser setado.
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
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
	 * @return Retorna o campo codigoBarras.
	 */
	public String getCodigoBarras() {
		return codigoBarras;
	}

	/**
	 * @param codigoBarras O codigoBarras a ser setado.
	 */
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	/**
	 * @return Retorna o campo codigoBarrasFormatado.
	 */
	public String getCodigoBarrasFormatado() {
		return codigoBarrasFormatado;
	}

	/**
	 * @param codigoBarrasFormatado O codigoBarrasFormatado a ser setado.
	 */
	public void setCodigoBarrasFormatado(String codigoBarrasFormatado) {
		this.codigoBarrasFormatado = codigoBarrasFormatado;
	}

	/**
	 * @return Retorna o campo totalFaturasResponsavel.
	 */
	public Integer getTotalFaturasResponsavel() {
		return totalFaturasResponsavel;
	}

	/**
	 * @param totalFaturasResponsavel O totalFaturasResponsavel a ser setado.
	 */
	public void setTotalFaturasResponsavel(Integer totalFaturasResponsavel) {
		this.totalFaturasResponsavel = totalFaturasResponsavel;
	}

	/**
	 * @return Retorna o campo representacaoNumericaCodBarraFormatada.
	 */
	public String getRepresentacaoNumericaCodBarraFormatada() {
		return representacaoNumericaCodBarraFormatada;
	}

	/**
	 * @param representacaoNumericaCodBarraFormatada O representacaoNumericaCodBarraFormatada a ser setado.
	 */
	public void setRepresentacaoNumericaCodBarraFormatada(
			String representacaoNumericaCodBarraFormatada) {
		this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
	}

	/**
	 * @return Retorna o campo representacaoNumericaCodBarraSemDigito.
	 */
	public String getRepresentacaoNumericaCodBarraSemDigito() {
		return representacaoNumericaCodBarraSemDigito;
	}

	/**
	 * @param representacaoNumericaCodBarraSemDigito O representacaoNumericaCodBarraSemDigito a ser setado.
	 */
	public void setRepresentacaoNumericaCodBarraSemDigito(
			String representacaoNumericaCodBarraSemDigito) {
		this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
	}

	public BigDecimal getPercentualAliquota() {
		return percentualAliquota;
	}

	public void setPercentualAliquota(BigDecimal percentualAliquota) {
		this.percentualAliquota = percentualAliquota;
	}

	public String getIdFatura() {
		return idFatura;
	}

	public void setIdFatura(String idFatura) {
		this.idFatura = idFatura;
	}
}
