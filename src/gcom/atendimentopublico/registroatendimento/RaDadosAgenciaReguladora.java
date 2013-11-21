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
package gcom.atendimentopublico.registroatendimento;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class RaDadosAgenciaReguladora extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String agenciaReguladora;

    /** persistent field */
    private short codigoSituacao;

    /** persistent field */
    private short codigoSituacaoArpe;

    /** persistent field */
    private Date dataPrevisaoOriginal;

    /** persistent field */
    private Date dataPrevisaoAtual;

    /** persistent field */
    private Date dataReclamacao;

    /** nullable persistent field */
    private String descricaoReclamacao;

    /** nullable persistent field */
    private Date dataRetorno;

    /** nullable persistent field */
    private String descricaoRetorno;

    /** nullable persistent field */
    private String contato;

    /** nullable persistent field */
    private String emailContato;

    /** nullable persistent field */
    private String orgaoContato;

    /** nullable persistent field */
    private Short dddContato;

    /** nullable persistent field */
    private String foneContato;

    /** nullable persistent field */
    private String ramalContato;

    /** nullable persistent field */
    private String faxContato;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento atendimentoMotivoEncerramento;

    /** persistent field */
    private gcom.atendimentopublico.registroatendimento.AgenciaReguladoraMotRetorno agenciaReguladoraMotRetorno;

    /** persistent field */
    private gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimento;

    /** persistent field */
    private gcom.atendimentopublico.registroatendimento.AgenciaReguladoraMotReclamacao agenciaReguladoraMotReclamacao;

    /** full constructor */
    public RaDadosAgenciaReguladora(String agenciaReguladora, short codigoSituacao, short codigoSituacaoArpe, Date dataPrevisaoOriginal, Date dataPrevisaoAtual, Date dataReclamacao, String descricaoReclamacao, Date dataRetorno, String descricaoRetorno, String contato, String emailContato, String orgaoContato, Short dddContato, String foneContato, String ramalContato, String faxContato, Date ultimaAlteracao, gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento atendimentoMotivoEncerramento, gcom.atendimentopublico.registroatendimento.AgenciaReguladoraMotRetorno agenciaReguladoraMotRetorno, gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimento, gcom.atendimentopublico.registroatendimento.AgenciaReguladoraMotReclamacao agenciaReguladoraMotReclamacao) {
        this.agenciaReguladora = agenciaReguladora;
        this.codigoSituacao = codigoSituacao;
        this.codigoSituacaoArpe = codigoSituacaoArpe;
        this.dataPrevisaoOriginal = dataPrevisaoOriginal;
        this.dataPrevisaoAtual = dataPrevisaoAtual;
        this.dataReclamacao = dataReclamacao;
        this.descricaoReclamacao = descricaoReclamacao;
        this.dataRetorno = dataRetorno;
        this.descricaoRetorno = descricaoRetorno;
        this.contato = contato;
        this.emailContato = emailContato;
        this.orgaoContato = orgaoContato;
        this.dddContato = dddContato;
        this.foneContato = foneContato;
        this.ramalContato = ramalContato;
        this.faxContato = faxContato;
        this.ultimaAlteracao = ultimaAlteracao;
        this.atendimentoMotivoEncerramento = atendimentoMotivoEncerramento;
        this.agenciaReguladoraMotRetorno = agenciaReguladoraMotRetorno;
        this.registroAtendimento = registroAtendimento;
        this.agenciaReguladoraMotReclamacao = agenciaReguladoraMotReclamacao;
    }

    /** default constructor */
    public RaDadosAgenciaReguladora() {
    }

    /** minimal constructor */
    public RaDadosAgenciaReguladora(short codigoSituacao, short codigoSituacaoArpe, Date dataPrevisaoOriginal, Date dataPrevisaoAtual, Date dataReclamacao, Date ultimaAlteracao, gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento atendimentoMotivoEncerramento, gcom.atendimentopublico.registroatendimento.AgenciaReguladoraMotRetorno agenciaReguladoraMotRetorno, gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimento, gcom.atendimentopublico.registroatendimento.AgenciaReguladoraMotReclamacao agenciaReguladoraMotReclamacao) {
        this.codigoSituacao = codigoSituacao;
        this.codigoSituacaoArpe = codigoSituacaoArpe;
        this.dataPrevisaoOriginal = dataPrevisaoOriginal;
        this.dataPrevisaoAtual = dataPrevisaoAtual;
        this.dataReclamacao = dataReclamacao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.atendimentoMotivoEncerramento = atendimentoMotivoEncerramento;
        this.agenciaReguladoraMotRetorno = agenciaReguladoraMotRetorno;
        this.registroAtendimento = registroAtendimento;
        this.agenciaReguladoraMotReclamacao = agenciaReguladoraMotReclamacao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAgenciaReguladora() {
        return this.agenciaReguladora;
    }

    public void setAgenciaReguladora(String agenciaReguladora) {
        this.agenciaReguladora = agenciaReguladora;
    }

    public short getCodigoSituacao() {
        return this.codigoSituacao;
    }

    public void setCodigoSituacao(short codigoSituacao) {
        this.codigoSituacao = codigoSituacao;
    }

    public short getCodigoSituacaoArpe() {
        return this.codigoSituacaoArpe;
    }

    public void setCodigoSituacaoArpe(short codigoSituacaoArpe) {
        this.codigoSituacaoArpe = codigoSituacaoArpe;
    }

    public Date getDataPrevisaoOriginal() {
        return this.dataPrevisaoOriginal;
    }

    public void setDataPrevisaoOriginal(Date dataPrevisaoOriginal) {
        this.dataPrevisaoOriginal = dataPrevisaoOriginal;
    }

    public Date getDataPrevisaoAtual() {
        return this.dataPrevisaoAtual;
    }

    public void setDataPrevisaoAtual(Date dataPrevisaoAtual) {
        this.dataPrevisaoAtual = dataPrevisaoAtual;
    }

    public Date getDataReclamacao() {
        return this.dataReclamacao;
    }

    public void setDataReclamacao(Date dataReclamacao) {
        this.dataReclamacao = dataReclamacao;
    }

    public String getDescricaoReclamacao() {
        return this.descricaoReclamacao;
    }

    public void setDescricaoReclamacao(String descricaoReclamacao) {
        this.descricaoReclamacao = descricaoReclamacao;
    }

    public Date getDataRetorno() {
        return this.dataRetorno;
    }

    public void setDataRetorno(Date dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    public String getDescricaoRetorno() {
        return this.descricaoRetorno;
    }

    public void setDescricaoRetorno(String descricaoRetorno) {
        this.descricaoRetorno = descricaoRetorno;
    }

    public String getContato() {
        return this.contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEmailContato() {
        return this.emailContato;
    }

    public void setEmailContato(String emailContato) {
        this.emailContato = emailContato;
    }

    public String getOrgaoContato() {
        return this.orgaoContato;
    }

    public void setOrgaoContato(String orgaoContato) {
        this.orgaoContato = orgaoContato;
    }

    public Short getDddContato() {
        return this.dddContato;
    }

    public void setDddContato(Short dddContato) {
        this.dddContato = dddContato;
    }

    public String getFoneContato() {
        return this.foneContato;
    }

    public void setFoneContato(String foneContato) {
        this.foneContato = foneContato;
    }

    public String getRamalContato() {
        return this.ramalContato;
    }

    public void setRamalContato(String ramalContato) {
        this.ramalContato = ramalContato;
    }

    public String getFaxContato() {
        return this.faxContato;
    }

    public void setFaxContato(String faxContato) {
        this.faxContato = faxContato;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento getAtendimentoMotivoEncerramento() {
        return this.atendimentoMotivoEncerramento;
    }

    public void setAtendimentoMotivoEncerramento(gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento atendimentoMotivoEncerramento) {
        this.atendimentoMotivoEncerramento = atendimentoMotivoEncerramento;
    }

    public gcom.atendimentopublico.registroatendimento.AgenciaReguladoraMotRetorno getAgenciaReguladoraMotRetorno() {
        return this.agenciaReguladoraMotRetorno;
    }

    public void setAgenciaReguladoraMotRetorno(gcom.atendimentopublico.registroatendimento.AgenciaReguladoraMotRetorno agenciaReguladoraMotRetorno) {
        this.agenciaReguladoraMotRetorno = agenciaReguladoraMotRetorno;
    }

    public gcom.atendimentopublico.registroatendimento.RegistroAtendimento getRegistroAtendimento() {
        return this.registroAtendimento;
    }

    public void setRegistroAtendimento(gcom.atendimentopublico.registroatendimento.RegistroAtendimento registroAtendimento) {
        this.registroAtendimento = registroAtendimento;
    }

    public gcom.atendimentopublico.registroatendimento.AgenciaReguladoraMotReclamacao getAgenciaReguladoraMotReclamacao() {
        return this.agenciaReguladoraMotReclamacao;
    }

    public void setAgenciaReguladoraMotReclamacao(gcom.atendimentopublico.registroatendimento.AgenciaReguladoraMotReclamacao agenciaReguladoraMotReclamacao) {
        this.agenciaReguladoraMotReclamacao = agenciaReguladoraMotReclamacao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
    
    public Filtro retornaFiltro() {
		FiltroRaDadosAgenciaReguladora filtroRaDadosAgenciaReguladora = new FiltroRaDadosAgenciaReguladora();

		filtroRaDadosAgenciaReguladora.adicionarCaminhoParaCarregamentoEntidade("atendimentoMotivoEncerramento");
		filtroRaDadosAgenciaReguladora.adicionarCaminhoParaCarregamentoEntidade("agenciaReguladoraMotRetorno");
		filtroRaDadosAgenciaReguladora.adicionarCaminhoParaCarregamentoEntidade("registroAtendimento");
		filtroRaDadosAgenciaReguladora.adicionarCaminhoParaCarregamentoEntidade("agenciaReguladoraMotReclamacao");
		
		return filtroRaDadosAgenciaReguladora;
	}

}
