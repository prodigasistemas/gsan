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
package gcom.gerencial.bean;

import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class CobrancaAcaoHelper {

	private Integer id;

	private String descricao;

	private Collection colecaoCobrancaAcaoSituacao;

	private Date realizacaoEmitir;

	private Date realizacaoEncerrar;

	private int somatorioQuantidadesDocumentos;

	private BigDecimal somatorioValoresDocumentos;
	
	private String indicadorDefinitivo;
	
	private boolean geraOrdemServico = false;
	
	private Integer numeroDiasRemuneracaoTerceiro = null;

	public CobrancaAcaoHelper(Integer id, String descricao, Integer idServicoTipo, 
		Date realizacaoEmitir, Date realizacaoEncerrar, int somatorioQuantidadesDocumentos, BigDecimal somatorioValoresDocumentos, Integer numeroDiasRemuneracaoTerceiro) {
		this(id, descricao, idServicoTipo, realizacaoEmitir, realizacaoEncerrar, 
				somatorioQuantidadesDocumentos, somatorioValoresDocumentos);
		this.numeroDiasRemuneracaoTerceiro = numeroDiasRemuneracaoTerceiro;
	}

	/**
	 * @return Retorna o campo colecaoCobrancaAcaoSituacao.
	 */
	public Collection getColecaoCobrancaAcaoSituacao() {
		return colecaoCobrancaAcaoSituacao;
	}

	/**
	 * @param colecaoCobrancaAcaoSituacao
	 *            O colecaoCobrancaAcaoSituacao a ser setado.
	 */
	public void setColecaoCobrancaAcaoSituacao(
			Collection colecaoCobrancaAcaoSituacao) {
		this.colecaoCobrancaAcaoSituacao = colecaoCobrancaAcaoSituacao;
	}

	/**
	 * @return Retorna o campo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            O descricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public CobrancaAcaoHelper(Integer id, String descricao,
			Integer idServicoTipo,
			Date realizacaoEmitir, Date realizacaoEncerrar,
			int somatorioQuantidadesDocumentos,
			BigDecimal somatorioValoresDocumentos) {
		this.id = id;
		this.descricao = descricao;
		this.geraOrdemServico = idServicoTipo != null;
		this.realizacaoEmitir = realizacaoEmitir;
		this.realizacaoEncerrar = realizacaoEncerrar;
		this.somatorioQuantidadesDocumentos = somatorioQuantidadesDocumentos;
		this.somatorioValoresDocumentos = somatorioValoresDocumentos;
	}

	/**
	 * @return Retorna o campo realizacaoEmitir.
	 */
	public String getRealizacaoEmitir() {
		String dataEmitir = Util.formatarData(realizacaoEmitir);
		return dataEmitir;
	}

	/**
	 * @param realizacaoEmitir
	 *            O realizacaoEmitir a ser setado.
	 */
	public void setRealizacaoEmitir(Date realizacaoEmitir) {
		this.realizacaoEmitir = realizacaoEmitir;
	}

	/**
	 * @return Retorna o campo realizacaoEncerrar.
	 */
	public String getRealizacaoEncerrar() {
		String dataEncerrar = Util.formatarData(realizacaoEncerrar);
		return dataEncerrar;
	}

	/**
	 * @param realizacaoEncerrar
	 *            O realizacaoEncerrar a ser setado.
	 */
	public void setRealizacaoEncerrar(Date realizacaoEncerrar) {
		this.realizacaoEncerrar = realizacaoEncerrar;
	}

	public int getSomatorioQuantidadesDocumentos() {
		return somatorioQuantidadesDocumentos;
	}

	public void setSomatorioQuantidadesDocumentos(
			int somatorioQuantidadesDocumentos) {
		this.somatorioQuantidadesDocumentos = somatorioQuantidadesDocumentos;
	}

	public BigDecimal getSomatorioValoresDocumentos() {
		return somatorioValoresDocumentos;
	}

	public void setSomatorioValoresDocumentos(
			BigDecimal somatorioValoresDocumentos) {
		this.somatorioValoresDocumentos = somatorioValoresDocumentos;
	}

	public String getIndicadorDefinitivo() {
		return indicadorDefinitivo;
	}

	public void setIndicadorDefinitivo(String indicadorDefinitivo) {
		this.indicadorDefinitivo = indicadorDefinitivo;
	}

	public boolean isGeraOrdemServico() {
		return geraOrdemServico;
	}

	public void setGeraOrdemServico(boolean geraOrdemServico) {
		this.geraOrdemServico = geraOrdemServico;
	}

	/**
	 * @return Retorna o campo numeroDiasRemuneracaoTerceiro.
	 */
	public Integer getNumeroDiasRemuneracaoTerceiro() {
		return numeroDiasRemuneracaoTerceiro;
	}

	/**
	 * @param numeroDiasRemuneracaoTerceiro O numeroDiasRemuneracaoTerceiro a ser setado.
	 */
	public void setNumeroDiasRemuneracaoTerceiro(
			Integer numeroDiasRemuneracaoTerceiro) {
		this.numeroDiasRemuneracaoTerceiro = numeroDiasRemuneracaoTerceiro;
	}

}
