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
package gcom.gerencial.cobranca.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Este caso de uso gera o resumo da pendência
 *
 * [UC0335] Gerar Resumo da Pendência
 *
 * @author Roberta Costa
 * @date 19/05/2006
 */
public class ResumoPendenciaAcumuladoHelper implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * @since 02/06/2006
	 */
	private String estado;
	private String tipoCategoria;
	private String categoria;
	private String tipoSituacaoAguaEsgoto;
	private Integer anoMesReferencia;
	private Integer quantidadeLigacoes;
	private Integer quantidadeDocumento;
	private BigDecimal valorPendente;

	/**
	 * @return Retorna o campo estado.
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado O estado a ser setado.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return Retorna o campo tipoSituacaoAguaEsgoto.
	 */
	public String getTipoSituacaoAguaEsgoto() {
		return tipoSituacaoAguaEsgoto;
	}

	/**
	 * @param tipoSituacaoAguaEsgoto O tipoSituacaoAguaEsgoto a ser setado.
	 */
	public void setTipoSituacaoAguaEsgoto(String tipoSituacaoAguaEsgoto) {
		this.tipoSituacaoAguaEsgoto = tipoSituacaoAguaEsgoto;
	}

	/**
	 * Construtor de ResumoPendenciaAcumuladoHelper 
	 * 
	 */
	public ResumoPendenciaAcumuladoHelper() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Retorna o campo anoMesReferencia.
	 */
	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	/**
	 * @param anoMesReferencia O anoMesReferencia a ser setado.
	 */
	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	/**
	 * @return Retorna o campo categoria.
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria O categoria a ser setado.
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return Retorna o campo quantidadeDocumento.
	 */
	public Integer getQuantidadeDocumento() {
		return quantidadeDocumento;
	}

	/**
	 * @param quantidadeDocumento O quantidadeDocumento a ser setado.
	 */
	public void setQuantidadeDocumento(Integer quantidadeDocumento) {
		this.quantidadeDocumento = quantidadeDocumento;
	}

	/**
	 * @return Retorna o campo quantidadeLigacoes.
	 */
	public Integer getQuantidadeLigacoes() {
		return quantidadeLigacoes;
	}

	/**
	 * @param quantidadeLigacoes O quantidadeLigacoes a ser setado.
	 */
	public void setQuantidadeLigacoes(Integer quantidadeLigacoes) {
		this.quantidadeLigacoes = quantidadeLigacoes;
	}

	/**
	 * @return Retorna o campo tipoCategoria.
	 */
	public String getTipoCategoria() {
		return tipoCategoria;
	}

	/**
	 * @param tipoCategoria O tipoCategoria a ser setado.
	 */
	public void setTipoCategoria(String tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}

	/**
	 * @return Retorna o campo valorPendente.
	 */
	public BigDecimal getValorPendente() {
		return valorPendente;
	}

	/**
	 * @param valorPendente O valorPendente a ser setado.
	 */
	public void setValorPendente(BigDecimal valorPendente) {
		this.valorPendente = valorPendente;
	}

	/**
	 * Construtor de ResumoPendenciaAcumuladoHelper 
	 * 
	 * @param estado
	 * @param tipoCategoria
	 * @param categoria
	 * @param tipoSituacaoAguaEsgoto
	 * @param anoMesReferencia
	 * @param quantidadeLigacoes
	 * @param quantidadeDocumento
	 * @param valorPendente
	 */
	public ResumoPendenciaAcumuladoHelper(String estado, String tipoCategoria, String categoria, String tipoSituacaoAguaEsgoto, Integer anoMesReferencia, Integer quantidadeLigacoes, Integer quantidadeDocumento, BigDecimal valorPendente) {
		super();
		// TODO Auto-generated constructor stub
		this.estado = estado;
		this.tipoCategoria = tipoCategoria;
		this.categoria = categoria;
		this.tipoSituacaoAguaEsgoto = tipoSituacaoAguaEsgoto;
		this.anoMesReferencia = anoMesReferencia;
		this.quantidadeLigacoes = quantidadeLigacoes;
		this.quantidadeDocumento = quantidadeDocumento;
		this.valorPendente = valorPendente;
	}
}