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
package gcom.cadastro.imovel.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Hibernate CodeGenerator
 * @created 1 de Junho de 2004
 */
public class ImovelCobrarDoacaoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** * Constantes ** */
	public static final short INDICADOR_HISTORICO = 2;

	public static final short NUMERO_PRESTACAO_DEBITO = 1;

	public static final short NUMERO_PRESTACAO_COBRADA = 0;

	public static final BigDecimal TAXA_JURO_FINANCIAMENTO = new BigDecimal(0);

	public static final int DEBITO_CREDITO_SITUACAO_ATUAL = 0;

	public static final int COBRANCA_FORMA = 1;

	/** * Campos internos ** */
	private Integer idImovel;

	private Integer idDebitoTipo;

	private Integer anoMesReferenciaContabil;

	private BigDecimal valorDebito;

	private Integer idLocalidade;

	private Integer idQuadra;

	private Integer codigoSetorComercial;

	private Integer numeroQuadra;

	private Short numeroLote;

	private Short numeroSubLote;

	private Integer financiamentoTipo;

	private Integer lancamentoItemContabil;

	private Integer anoMesReferenciaInicial;
	
	private Integer anoMesReferenciaFinal;
	
	private Integer idImovelDoacao;

	public ImovelCobrarDoacaoHelper(Integer idImovel, Integer idDebitoTipo,
			Integer anoMesReferenciaContabil, BigDecimal valorDebito,
			Integer idLocalidade, Integer idQuadra,
			Integer codigoSetorComercial, Integer numeroQuadra,
			Short numeroLote, Short numeroSubLote, Integer financiamentoTipo,
			Integer lancamentoItemContabil) {

		this.idImovel = idImovel;
		this.idDebitoTipo = idDebitoTipo;
		this.anoMesReferenciaContabil = anoMesReferenciaContabil;
		this.valorDebito = valorDebito;
		this.idLocalidade = idLocalidade;
		this.idQuadra = idQuadra;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.numeroLote = numeroLote;
		this.numeroSubLote = numeroSubLote;
		this.financiamentoTipo = financiamentoTipo;
		this.lancamentoItemContabil = lancamentoItemContabil;
	}

	public ImovelCobrarDoacaoHelper(Integer idImovel, Integer idDebitoTipo,
			BigDecimal valorDebito, Integer idLocalidade, Integer idQuadra,
			Integer codigoSetorComercial, Integer numeroQuadra,
			Short numeroLote, Short numeroSubLote, Integer financiamentoTipo,
			Integer lancamentoItemContabil) {

		this.idImovel = idImovel;
		this.idDebitoTipo = idDebitoTipo;
		this.valorDebito = valorDebito;
		this.idLocalidade = idLocalidade;
		this.idQuadra = idQuadra;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.numeroLote = numeroLote;
		this.numeroSubLote = numeroSubLote;
		this.financiamentoTipo = financiamentoTipo;
		this.lancamentoItemContabil = lancamentoItemContabil;
	}
	

	
	public ImovelCobrarDoacaoHelper(Integer idImovel, Integer idDebitoTipo,
		    BigDecimal valorDebito,
			Integer idLocalidade, Integer idQuadra,
			Integer codigoSetorComercial, Integer numeroQuadra,
			Short numeroLote, Short numeroSubLote, Integer financiamentoTipo,
			Integer lancamentoItemContabil,Integer anoMesReferenciaFinal,Integer idImovelDoacao) {

		this.idImovel = idImovel;
		this.idDebitoTipo = idDebitoTipo;
		this.valorDebito = valorDebito;
		this.idLocalidade = idLocalidade;
		this.idQuadra = idQuadra;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.numeroLote = numeroLote;
		this.numeroSubLote = numeroSubLote;
		this.financiamentoTipo = financiamentoTipo;
		this.lancamentoItemContabil = lancamentoItemContabil;
		this.anoMesReferenciaFinal = anoMesReferenciaFinal;
		this.idImovelDoacao = idImovelDoacao;
	}
	
	public ImovelCobrarDoacaoHelper(Integer idImovel, Integer idDebitoTipo,
		    BigDecimal valorDebito,
			Integer idLocalidade, Integer idQuadra,
			Integer codigoSetorComercial, Integer numeroQuadra,
			Short numeroLote, Short numeroSubLote, Integer financiamentoTipo,
			Integer lancamentoItemContabil,Integer anoMesReferenciaFinal,Integer idImovelDoacao,Integer anoMesReferenciaInicial) {

		this.idImovel = idImovel;
		this.idDebitoTipo = idDebitoTipo;
		this.valorDebito = valorDebito;
		this.idLocalidade = idLocalidade;
		this.idQuadra = idQuadra;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.numeroLote = numeroLote;
		this.numeroSubLote = numeroSubLote;
		this.financiamentoTipo = financiamentoTipo;
		this.lancamentoItemContabil = lancamentoItemContabil;
		this.anoMesReferenciaFinal = anoMesReferenciaFinal;
		this.idImovelDoacao = idImovelDoacao;
		this.anoMesReferenciaInicial = anoMesReferenciaInicial;
	}

	public Integer getAnoMesReferenciaContabil() {
		return anoMesReferenciaContabil;
	}

	public void setAnoMesReferenciaContabil(Integer anoMesReferenciaContabil) {
		this.anoMesReferenciaContabil = anoMesReferenciaContabil;
	}

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Integer getFinanciamentoTipo() {
		return financiamentoTipo;
	}

	public void setFinanciamentoTipo(Integer financiamentoTipo) {
		this.financiamentoTipo = financiamentoTipo;
	}

	public Integer getIdDebitoTipo() {
		return idDebitoTipo;
	}

	public void setIdDebitoTipo(Integer idDebitoTipo) {
		this.idDebitoTipo = idDebitoTipo;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public Integer getIdQuadra() {
		return idQuadra;
	}

	public void setIdQuadra(Integer idQuadra) {
		this.idQuadra = idQuadra;
	}

	public Integer getLancamentoItemContabil() {
		return lancamentoItemContabil;
	}

	public void setLancamentoItemContabil(Integer lancamentoItemContabil) {
		this.lancamentoItemContabil = lancamentoItemContabil;
	}

	public Short getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(Short numeroLote) {
		this.numeroLote = numeroLote;
	}

	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Short getNumeroSubLote() {
		return numeroSubLote;
	}

	public void setNumeroSubLote(Short numeroSubLote) {
		this.numeroSubLote = numeroSubLote;
	}

	public BigDecimal getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}

	public Integer getAnoMesReferenciaFinal() {
		return anoMesReferenciaFinal;
	}

	public void setAnoMesReferenciaFinal(Integer anoMesReferenciaFinal) {
		this.anoMesReferenciaFinal = anoMesReferenciaFinal;
	}

	public Integer getIdImovelDoacao() {
		return idImovelDoacao;
	}

	public void setIdImovelDoacao(Integer idImovelDoacao) {
		this.idImovelDoacao = idImovelDoacao;
	}

	public Integer getAnoMesReferenciaInicial() {
		return anoMesReferenciaInicial;
	}

	public void setAnoMesReferenciaInicial(Integer anoMesReferenciaInicial) {
		this.anoMesReferenciaInicial = anoMesReferenciaInicial;
	}
}
