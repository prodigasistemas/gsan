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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * 
 * @author Sávio Luiz
 * @since 14/05/2007
 */
public class DadosPesquisaCobrancaDocumentoHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer idDocumento;

	private Date dataEmissao;

	private Integer idImovel;

	private BigDecimal valorDocumento;
	
	private Integer idCobrancaCriterio;

	private BigDecimal percentualValorMinimoCobrancaCriterio;
	
	private BigDecimal percentualQuantidadeMinimaCobrancaCriterio;
	
	private BigDecimal valorLimitePrioridadeCobrancaCriterio;
	
	private Integer idFiscalizacao;
	
	private Integer idEsferaPoder;
	
	private Integer idSituacaoAcao;
	
	private Integer idSituacaoDebito;
	
	private Short icAntesDepois;
	
	private Date dataSituacaoAcao;
	
	private Date dataSituacaoDebito;
	
	private Integer idCategoria;
	
	private Short icAcimaLimite;
	
	private Integer idMotivoEncerramento;
	
	private Integer idOrdemServico;

	private Integer idLocalidade;
	
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
	 * @return Retorna o campo idOrdemServico.
	 */
	public Integer getIdOrdemServico() {
		return idOrdemServico;
	}

	/**
	 * @param idOrdemServico O idOrdemServico a ser setado.
	 */
	public void setIdOrdemServico(Integer idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public DadosPesquisaCobrancaDocumentoHelper(Integer idDocumento, Date dataEmissao, Integer idImovel, BigDecimal valorDocumento, Integer idCobrancaCriterio, Integer idSituacaoAcao) {
		super();
		// TODO Auto-generated constructor stub
		this.idDocumento = idDocumento;
		this.dataEmissao = dataEmissao;
		this.idImovel = idImovel;
		this.valorDocumento = valorDocumento;
		this.idCobrancaCriterio = idCobrancaCriterio;
		this.idSituacaoAcao = idSituacaoAcao;
	}

	public DadosPesquisaCobrancaDocumentoHelper(Integer idDocumento, Date dataEmissao, Integer idImovel, BigDecimal valorDocumento, Integer idCobrancaCriterio, Integer idSituacaoAcao, 
			Integer idLocalidade, Integer idCategoria, Integer idEsferaPoder,
			Integer idSituacaoDebito) {
		this(idDocumento, dataEmissao,idImovel, valorDocumento, idCobrancaCriterio, idSituacaoAcao);
		this.idLocalidade = idLocalidade;
		this.idCategoria = idCategoria;
		this.idEsferaPoder = idEsferaPoder;
		this.idSituacaoDebito = idSituacaoDebito;
	}

	public Date getDataSituacaoAcao() {
		return dataSituacaoAcao;
	}

	public void setDataSituacaoAcao(Date dataSituacaoAcao) {
		this.dataSituacaoAcao = dataSituacaoAcao;
	}

	public Date getDataSituacaoDebito() {
		return dataSituacaoDebito;
	}

	public void setDataSituacaoDebito(Date dataSituacaoDebito) {
		this.dataSituacaoDebito = dataSituacaoDebito;
	}

	public Short getIcAcimaLimite() {
		return icAcimaLimite;
	}

	public void setIcAcimaLimite(Short icAcimaLimite) {
		this.icAcimaLimite = icAcimaLimite;
	}

	public Short getIcAntesDepois() {
		return icAntesDepois;
	}

	public void setIcAntesDepois(Short icAntesDepois) {
		this.icAntesDepois = icAntesDepois;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Integer getIdEsferaPoder() {
		return idEsferaPoder;
	}

	public void setIdEsferaPoder(Integer idEsferaPoder) {
		this.idEsferaPoder = idEsferaPoder;
	}

	public Integer getIdFiscalizacao() {
		return idFiscalizacao;
	}

	public void setIdFiscalizacao(Integer idFiscalizacao) {
		this.idFiscalizacao = idFiscalizacao;
	}

	public Integer getIdMotivoEncerramento() {
		return idMotivoEncerramento;
	}

	public void setIdMotivoEncerramento(Integer idMotivoEncerramento) {
		this.idMotivoEncerramento = idMotivoEncerramento;
	}

	public Integer getIdSituacaoAcao() {
		return idSituacaoAcao;
	}

	public void setIdSituacaoAcao(Integer idSituacaoAcao) {
		this.idSituacaoAcao = idSituacaoAcao;
	}

	/**
	 * 
	 */
	public DadosPesquisaCobrancaDocumentoHelper() {

	}
	
	/**
	 * 
	 */
	public DadosPesquisaCobrancaDocumentoHelper(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * @param conta
	 * @param valorPago
	 * @param valorMulta
	 * @param valorJurosMora
	 * @param valoratualizacaoMonetaria
	 */
	public DadosPesquisaCobrancaDocumentoHelper(Integer idDocumento,
			Date dataEmissao, Integer idImovel, BigDecimal valorDocumento,
			BigDecimal percentualValorMinimoCobrancaCriterio,BigDecimal percentualQuantidadeMinimaCobrancaCriterio,BigDecimal valorLimitePrioridadeCobrancaCriterio) {
		this.idDocumento = idDocumento;
		this.dataEmissao = dataEmissao;
		this.idImovel = idImovel;
		this.valorDocumento = valorDocumento;
		this.percentualValorMinimoCobrancaCriterio = percentualValorMinimoCobrancaCriterio;
		this.percentualQuantidadeMinimaCobrancaCriterio = percentualQuantidadeMinimaCobrancaCriterio;
		this.valorLimitePrioridadeCobrancaCriterio = valorLimitePrioridadeCobrancaCriterio;
	}
	
	public DadosPesquisaCobrancaDocumentoHelper(Integer idDocumento, Date dataEmissao, Integer idImovel, BigDecimal valorDocumento, Integer idCobrancaCriterio) {
		super();
		// TODO Auto-generated constructor stub
		this.idDocumento = idDocumento;
		this.dataEmissao = dataEmissao;
		this.idImovel = idImovel;
		this.valorDocumento = valorDocumento;
		this.idCobrancaCriterio = idCobrancaCriterio;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Integer getIdCobrancaCriterio() {
		return idCobrancaCriterio;
	}

	public void setIdCobrancaCriterio(Integer idCobrancaCriterio) {
		this.idCobrancaCriterio = idCobrancaCriterio;
	}

	public BigDecimal getPercentualQuantidadeMinimaCobrancaCriterio() {
		return percentualQuantidadeMinimaCobrancaCriterio;
	}

	public void setPercentualQuantidadeMinimaCobrancaCriterio(
			BigDecimal percentualQuantidadeMinimaCobrancaCriterio) {
		this.percentualQuantidadeMinimaCobrancaCriterio = percentualQuantidadeMinimaCobrancaCriterio;
	}

	public BigDecimal getPercentualValorMinimoCobrancaCriterio() {
		return percentualValorMinimoCobrancaCriterio;
	}

	public void setPercentualValorMinimoCobrancaCriterio(
			BigDecimal percentualValorMinimoCobrancaCriterio) {
		this.percentualValorMinimoCobrancaCriterio = percentualValorMinimoCobrancaCriterio;
	}

	public BigDecimal getValorLimitePrioridadeCobrancaCriterio() {
		return valorLimitePrioridadeCobrancaCriterio;
	}

	public void setValorLimitePrioridadeCobrancaCriterio(
			BigDecimal valorLimitePrioridadeCobrancaCriterio) {
		this.valorLimitePrioridadeCobrancaCriterio = valorLimitePrioridadeCobrancaCriterio;
	}

	public Integer getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public BigDecimal getValorDocumento() {
		return valorDocumento;
	}

	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento = valorDocumento;
	}

	/**
	 * Description of the Method
	 * 
	 * @param other
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof DadosPesquisaCobrancaDocumentoHelper)) {
			return false;
		}
		DadosPesquisaCobrancaDocumentoHelper castOther = (DadosPesquisaCobrancaDocumentoHelper) other;

		return new EqualsBuilder().append(this.getIdDocumento(),
				castOther.getIdDocumento()).isEquals();
	}

	public Integer getIdSituacaoDebito() {
		return idSituacaoDebito;
	}

	public void setIdSituacaoDebito(Integer idSituacaoDebito) {
		this.idSituacaoDebito = idSituacaoDebito;
	}

}