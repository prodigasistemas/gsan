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
package gcom.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;

import java.io.Serializable;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class FiscalizacaoSituacaoEsgoto implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private FiscalizacaoSituacaoEsgotoPK comp_id;

    /** persistent field */
    private Date ultimaAlteracao;
    
    private short indicadorConsumoFixado;

    /** nullable persistent field */
    private LigacaoEsgotoSituacao ligacaoEsgotoSituacaoByLestId;

    /** nullable persistent field */
    private FiscalizacaoSituacao fiscalizacaoSituacao;

    /** persistent field */
    private LigacaoEsgotoSituacao ligacaoEsgotoSituacaoByLestIdnova;
    
    /** persistent field */
    private SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao;
    
    /** persistent field */
    private Short numeroMesesFatura;
    
    
    public final static short INDICADOR_SIM = new Short("1");

	/**
	 * @return Retorna o campo comp_id.
	 */
	public FiscalizacaoSituacaoEsgotoPK getComp_id() {
		return comp_id;
	}

	/**
	 * @param comp_id O comp_id a ser setado.
	 */
	public void setComp_id(FiscalizacaoSituacaoEsgotoPK comp_id) {
		this.comp_id = comp_id;
	}

	/**
	 * @return Retorna o campo fiscalizacaoSituacao.
	 */
	public FiscalizacaoSituacao getFiscalizacaoSituacao() {
		return fiscalizacaoSituacao;
	}

	/**
	 * @param fiscalizacaoSituacao O fiscalizacaoSituacao a ser setado.
	 */
	public void setFiscalizacaoSituacao(FiscalizacaoSituacao fiscalizacaoSituacao) {
		this.fiscalizacaoSituacao = fiscalizacaoSituacao;
	}

	/**
	 * @return Retorna o campo ligacaoEsgotoSituacaoByLestId.
	 */
	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacaoByLestId() {
		return ligacaoEsgotoSituacaoByLestId;
	}

	/**
	 * @param ligacaoEsgotoSituacaoByLestId O ligacaoEsgotoSituacaoByLestId a ser setado.
	 */
	public void setLigacaoEsgotoSituacaoByLestId(
			LigacaoEsgotoSituacao ligacaoEsgotoSituacaoByLestId) {
		this.ligacaoEsgotoSituacaoByLestId = ligacaoEsgotoSituacaoByLestId;
	}

	/**
	 * @return Retorna o campo ligacaoEsgotoSituacaoByLestIdnova.
	 */
	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacaoByLestIdnova() {
		return ligacaoEsgotoSituacaoByLestIdnova;
	}

	/**
	 * @param ligacaoEsgotoSituacaoByLestIdnova O ligacaoEsgotoSituacaoByLestIdnova a ser setado.
	 */
	public void setLigacaoEsgotoSituacaoByLestIdnova(
			LigacaoEsgotoSituacao ligacaoEsgotoSituacaoByLestIdnova) {
		this.ligacaoEsgotoSituacaoByLestIdnova = ligacaoEsgotoSituacaoByLestIdnova;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public short getIndicadorConsumoFixado() {
		return indicadorConsumoFixado;
	}

	public void setIndicadorConsumoFixado(short indicadorConsumoFixado) {
		this.indicadorConsumoFixado = indicadorConsumoFixado;
	}

	/**
	 * @return Retorna o campo solicitacaoTipoEspecificacao.
	 */
	public SolicitacaoTipoEspecificacao getSolicitacaoTipoEspecificacao() {
		return solicitacaoTipoEspecificacao;
	}

	/**
	 * @param solicitacaoTipoEspecificacao O solicitacaoTipoEspecificacao a ser setado.
	 */
	public void setSolicitacaoTipoEspecificacao(
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao) {
		this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
	}

	public Short getNumeroMesesFatura() {
		return numeroMesesFatura;
	}

	public void setNumeroMesesFatura(Short numeroMesesFatura) {
		this.numeroMesesFatura = numeroMesesFatura;
	}
	
	

}
