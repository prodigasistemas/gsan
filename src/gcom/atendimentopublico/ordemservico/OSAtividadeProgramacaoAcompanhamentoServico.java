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

import gcom.integracao.ServicoTerceiroAcompanhamentoServico;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OSAtividadeProgramacaoAcompanhamentoServico implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private short indicadorAtualizacaoOS;

    /** persistent field */
    private short indicadorTransmissaoOS;

    /** persistent field */
    private Date dataUltimaAlteracao;
    
    /** persistent field */
    private BigDecimal qtdMaterialExedente;

    /** persistent field */
    private OrdemServicoSituacao ordemServicoSituacao;

    private Atividade atividade;
    
    private EquipamentosEspeciais equipamentoFaltante;
    
    private OSProgramacaoAcompanhamentoServico osProgramacaoAcompanhamentoServico;
    
    private ServicoTerceiroAcompanhamentoServico servicoTerceiroAcompanhamentoServico;
    
	/** persistent field */
	private short indicadorExcluido;

    public OSProgramacaoAcompanhamentoServico getOsProgramacaoAcompanhamentoServico() {
		return osProgramacaoAcompanhamentoServico;
	}

	public void setOsProgramacaoAcompanhamentoServico(
			OSProgramacaoAcompanhamentoServico osProgramacaoAcompanhamentoServico) {
		this.osProgramacaoAcompanhamentoServico = osProgramacaoAcompanhamentoServico;
	}

	public Integer getId() {
        return this.id;
    }

    public void setId(Integer oatsId) {
        this.id = oatsId;
    }

    public short getIndicadorAtualizacaoOS() {
        return this.indicadorAtualizacaoOS;
    }

    public void setIndicadorAtualizacaoOS(short oatsIcatualizaos) {
        this.indicadorAtualizacaoOS = oatsIcatualizaos;
    }

    public short getIndicadorTransmissaoOS() {
        return this.indicadorTransmissaoOS;
    }

    public void setIndicadorTransmissaoOS(short oatsIctransmissao) {
        this.indicadorTransmissaoOS = oatsIctransmissao;
    }

    public Date getDataUltimaAlteracao() {
        return this.dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(Date oatsTmultimaalteracao) {
        this.dataUltimaAlteracao = oatsTmultimaalteracao;
    }

    public OrdemServicoSituacao getOrdemServicoSituacao() {
        return this.ordemServicoSituacao;
    }

    public void setOrdemServicoSituacao(OrdemServicoSituacao ordemServicoSituacao) {
        this.ordemServicoSituacao = ordemServicoSituacao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("oatsId", getId())
            .toString();
    }

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public EquipamentosEspeciais getEquipamentoFaltante() {
		return equipamentoFaltante;
	}

	public void setEquipamentoFaltante(EquipamentosEspeciais equipamentoFaltante) {
		this.equipamentoFaltante = equipamentoFaltante;
	}

	public short getIndicadorExcluido() {
		return indicadorExcluido;
	}

	public void setIndicadorExcluido(short indicadorExcluido) {
		this.indicadorExcluido = indicadorExcluido;
	}

	public ServicoTerceiroAcompanhamentoServico getServicoTerceiroAcompanhamentoServico() {
		return servicoTerceiroAcompanhamentoServico;
	}

	public void setServicoTerceiroAcompanhamentoServico(
			ServicoTerceiroAcompanhamentoServico servicoTerceiroAcompanhamentoServico) {
		this.servicoTerceiroAcompanhamentoServico = servicoTerceiroAcompanhamentoServico;
	}

	public BigDecimal getQtdMaterialExedente() {
		return qtdMaterialExedente;
	}

	public void setQtdMaterialExedente(BigDecimal qtdMaterialExedente) {
		this.qtdMaterialExedente = qtdMaterialExedente;
	}
}
