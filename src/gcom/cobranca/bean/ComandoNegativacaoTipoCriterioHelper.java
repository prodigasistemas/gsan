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
package gcom.cobranca.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * [UC 0653] Pesquisar Comando Negativação
 * 
 * @author Kássia Albuquerque
 * @date 22/10/2007
 */

public class ComandoNegativacaoTipoCriterioHelper {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idNegativador; 
	private String titulo;
	private String tipoPesquisaTitulo; 
	private short comandoSimulado;
	private Integer codigoCliente; 
	private Integer idTipoRelacao; 
	private Integer idGrupoCobranca; 
	private Integer idGerenciaRegional; 
	private Integer idUnidadeNegocio;
	private Integer idEloPolo;
	private Integer codigoLocalidadeInicial; 
	private Integer codigoSetorComercialInicial; 
	private Integer codigoLocalidadeFinal;
	private Integer codigoSetorComercialFinal; 
	private Date geracaoComandoDataInicial;
	private Date geracaoComandoDataFinal;
	private Date execucaoComandoDataInicial; 
	private Date execucaoComandoDataFinal;
	private Integer referenciaDebitoDataInicial; 
	private Integer referenciaDebitoDataFinal;
	private Date vencimentoDebitoDataInicial; 
	private Date vencimentoDebitoDataFinal;
	private BigDecimal valorDebitoInicial;
	private BigDecimal valorDebitoFinal;
	private int numeroContasInicial;
	private int numeroContasFinal;
	private short cartaParcelamentoAtraso; 
	private Integer situacaoComando;
    private Short indicadorContaNomeCliente;
	
	public ComandoNegativacaoTipoCriterioHelper() {

	}

	/**
	 * @return Retorna o campo cartaParcelamentoAtraso.
	 */
	public short getCartaParcelamentoAtraso() {
		return cartaParcelamentoAtraso;
	}




	/**
	 * @param cartaParcelamentoAtraso O cartaParcelamentoAtraso a ser setado.
	 */
	public void setCartaParcelamentoAtraso(short cartaParcelamentoAtraso) {
		this.cartaParcelamentoAtraso = cartaParcelamentoAtraso;
	}




	/**
	 * @return Retorna o campo codigoCliente.
	 */
	public Integer getCodigoCliente() {
		return codigoCliente;
	}




	/**
	 * @param codigoCliente O codigoCliente a ser setado.
	 */
	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}




	/**
	 * @return Retorna o campo codigoLocalidadeFinal.
	 */
	public Integer getCodigoLocalidadeFinal() {
		return codigoLocalidadeFinal;
	}




	/**
	 * @param codigoLocalidadeFinal O codigoLocalidadeFinal a ser setado.
	 */
	public void setCodigoLocalidadeFinal(Integer codigoLocalidadeFinal) {
		this.codigoLocalidadeFinal = codigoLocalidadeFinal;
	}




	/**
	 * @return Retorna o campo codigoLocalidadeInicial.
	 */
	public Integer getCodigoLocalidadeInicial() {
		return codigoLocalidadeInicial;
	}




	/**
	 * @param codigoLocalidadeInicial O codigoLocalidadeInicial a ser setado.
	 */
	public void setCodigoLocalidadeInicial(Integer codigoLocalidadeInicial) {
		this.codigoLocalidadeInicial = codigoLocalidadeInicial;
	}




	/**
	 * @return Retorna o campo codigoSetorComercialFinal.
	 */
	public Integer getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}




	/**
	 * @param codigoSetorComercialFinal O codigoSetorComercialFinal a ser setado.
	 */
	public void setCodigoSetorComercialFinal(Integer codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}




	/**
	 * @return Retorna o campo codigoSetorComercialInicial.
	 */
	public Integer getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}




	/**
	 * @param codigoSetorComercialInicial O codigoSetorComercialInicial a ser setado.
	 */
	public void setCodigoSetorComercialInicial(Integer codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}




	/**
	 * @return Retorna o campo comandoSimulado.
	 */
	public short getComandoSimulado() {
		return comandoSimulado;
	}




	/**
	 * @param comandoSimulado O comandoSimulado a ser setado.
	 */
	public void setComandoSimulado(short comandoSimulado) {
		this.comandoSimulado = comandoSimulado;
	}




	/**
	 * @return Retorna o campo execucaoComandoDataFinal.
	 */
	public Date getExecucaoComandoDataFinal() {
		return execucaoComandoDataFinal;
	}




	/**
	 * @param execucaoComandoDataFinal O execucaoComandoDataFinal a ser setado.
	 */
	public void setExecucaoComandoDataFinal(Date execucaoComandoDataFinal) {
		this.execucaoComandoDataFinal = execucaoComandoDataFinal;
	}




	/**
	 * @return Retorna o campo execucaoComandoDataInicial.
	 */
	public Date getExecucaoComandoDataInicial() {
		return execucaoComandoDataInicial;
	}




	/**
	 * @param execucaoComandoDataInicial O execucaoComandoDataInicial a ser setado.
	 */
	public void setExecucaoComandoDataInicial(Date execucaoComandoDataInicial) {
		this.execucaoComandoDataInicial = execucaoComandoDataInicial;
	}




	/**
	 * @return Retorna o campo geracaoComandoDataFinal.
	 */
	public Date getGeracaoComandoDataFinal() {
		return geracaoComandoDataFinal;
	}




	/**
	 * @param geracaoComandoDataFinal O geracaoComandoDataFinal a ser setado.
	 */
	public void setGeracaoComandoDataFinal(Date geracaoComandoDataFinal) {
		this.geracaoComandoDataFinal = geracaoComandoDataFinal;
	}




	/**
	 * @return Retorna o campo geracaoComandoDataInicial.
	 */
	public Date getGeracaoComandoDataInicial() {
		return geracaoComandoDataInicial;
	}




	/**
	 * @param geracaoComandoDataInicial O geracaoComandoDataInicial a ser setado.
	 */
	public void setGeracaoComandoDataInicial(Date geracaoComandoDataInicial) {
		this.geracaoComandoDataInicial = geracaoComandoDataInicial;
	}




	/**
	 * @return Retorna o campo idEloPolo.
	 */
	public Integer getIdEloPolo() {
		return idEloPolo;
	}




	/**
	 * @param idEloPolo O idEloPolo a ser setado.
	 */
	public void setIdEloPolo(Integer idEloPolo) {
		this.idEloPolo = idEloPolo;
	}




	/**
	 * @return Retorna o campo idGerenciaRegional.
	 */
	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}




	/**
	 * @param idGerenciaRegional O idGerenciaRegional a ser setado.
	 */
	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}




	/**
	 * @return Retorna o campo idGrupoCobranca.
	 */
	public Integer getIdGrupoCobranca() {
		return idGrupoCobranca;
	}




	/**
	 * @param idGrupoCobranca O idGrupoCobranca a ser setado.
	 */
	public void setIdGrupoCobranca(Integer idGrupoCobranca) {
		this.idGrupoCobranca = idGrupoCobranca;
	}




	/**
	 * @return Retorna o campo idNegativador.
	 */
	public Integer getIdNegativador() {
		return idNegativador;
	}




	/**
	 * @param idNegativador O idNegativador a ser setado.
	 */
	public void setIdNegativador(Integer idNegativador) {
		this.idNegativador = idNegativador;
	}




	/**
	 * @return Retorna o campo idTipoRelacao.
	 */
	public Integer getIdTipoRelacao() {
		return idTipoRelacao;
	}




	/**
	 * @param idTipoRelacao O idTipoRelacao a ser setado.
	 */
	public void setIdTipoRelacao(Integer idTipoRelacao) {
		this.idTipoRelacao = idTipoRelacao;
	}




	/**
	 * @return Retorna o campo idUnidadeNegocio.
	 */
	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}




	/**
	 * @param idUnidadeNegocio O idUnidadeNegocio a ser setado.
	 */
	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}




	/**
	 * @return Retorna o campo numeroContasFinal.
	 */
	public int getNumeroContasFinal() {
		return numeroContasFinal;
	}




	/**
	 * @param numeroContasFinal O numeroContasFinal a ser setado.
	 */
	public void setNumeroContasFinal(int numeroContasFinal) {
		this.numeroContasFinal = numeroContasFinal;
	}




	/**
	 * @return Retorna o campo numeroContasInicial.
	 */
	public int getNumeroContasInicial() {
		return numeroContasInicial;
	}




	/**
	 * @param numeroContasInicial O numeroContasInicial a ser setado.
	 */
	public void setNumeroContasInicial(int numeroContasInicial) {
		this.numeroContasInicial = numeroContasInicial;
	}




	/**
	 * @return Retorna o campo referenciaDebitoDataFinal.
	 */
	public Integer getReferenciaDebitoDataFinal() {
		return referenciaDebitoDataFinal;
	}




	/**
	 * @param referenciaDebitoDataFinal O referenciaDebitoDataFinal a ser setado.
	 */
	public void setReferenciaDebitoDataFinal(Integer referenciaDebitoDataFinal) {
		this.referenciaDebitoDataFinal = referenciaDebitoDataFinal;
	}




	/**
	 * @return Retorna o campo referenciaDebitoDataInicial.
	 */
	public Integer getReferenciaDebitoDataInicial() {
		return referenciaDebitoDataInicial;
	}




	/**
	 * @param referenciaDebitoDataInicial O referenciaDebitoDataInicial a ser setado.
	 */
	public void setReferenciaDebitoDataInicial(Integer referenciaDebitoDataInicial) {
		this.referenciaDebitoDataInicial = referenciaDebitoDataInicial;
	}




	/**
	 * @return Retorna o campo situacaoComando.
	 */
	public Integer getSituacaoComando() {
		return situacaoComando;
	}




	/**
	 * @param situacaoComando O situacaoComando a ser setado.
	 */
	public void setSituacaoComando(Integer situacaoComando) {
		this.situacaoComando = situacaoComando;
	}




	/**
	 * @return Retorna o campo tipoPesquisaTitulo.
	 */
	public String getTipoPesquisaTitulo() {
		return tipoPesquisaTitulo;
	}




	/**
	 * @param tipoPesquisaTitulo O tipoPesquisaTitulo a ser setado.
	 */
	public void setTipoPesquisaTitulo(String tipoPesquisaTitulo) {
		this.tipoPesquisaTitulo = tipoPesquisaTitulo;
	}




	/**
	 * @return Retorna o campo titulo.
	 */
	public String getTitulo() {
		return titulo;
	}




	/**
	 * @param titulo O titulo a ser setado.
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}




	/**
	 * @return Retorna o campo valorDebitoFinal.
	 */
	public BigDecimal getValorDebitoFinal() {
		return valorDebitoFinal;
	}




	/**
	 * @param valorDebitoFinal O valorDebitoFinal a ser setado.
	 */
	public void setValorDebitoFinal(BigDecimal valorDebitoFinal) {
		this.valorDebitoFinal = valorDebitoFinal;
	}




	/**
	 * @return Retorna o campo valorDebitoInicial.
	 */
	public BigDecimal getValorDebitoInicial() {
		return valorDebitoInicial;
	}




	/**
	 * @param valorDebitoInicial O valorDebitoInicial a ser setado.
	 */
	public void setValorDebitoInicial(BigDecimal valorDebitoInicial) {
		this.valorDebitoInicial = valorDebitoInicial;
	}




	/**
	 * @return Retorna o campo vencimentoDebitoDataFinal.
	 */
	public Date getVencimentoDebitoDataFinal() {
		return vencimentoDebitoDataFinal;
	}




	/**
	 * @param vencimentoDebitoDataFinal O vencimentoDebitoDataFinal a ser setado.
	 */
	public void setVencimentoDebitoDataFinal(Date vencimentoDebitoDataFinal) {
		this.vencimentoDebitoDataFinal = vencimentoDebitoDataFinal;
	}




	/**
	 * @return Retorna o campo vencimentoDebitoDataInicial.
	 */
	public Date getVencimentoDebitoDataInicial() {
		return vencimentoDebitoDataInicial;
	}




	/**
	 * @param vencimentoDebitoDataInicial O vencimentoDebitoDataInicial a ser setado.
	 */
	public void setVencimentoDebitoDataInicial(Date vencimentoDebitoDataInicial) {
		this.vencimentoDebitoDataInicial = vencimentoDebitoDataInicial;
	}

	public Short getIndicadorContaNomeCliente() {
		return indicadorContaNomeCliente;
	}

	public void setIndicadorContaNomeCliente(Short indicadorContaNomeCliente) {
		this.indicadorContaNomeCliente = indicadorContaNomeCliente;
	}

	
	
	
}