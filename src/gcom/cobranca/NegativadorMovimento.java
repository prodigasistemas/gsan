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

package gcom.cobranca;

import gcom.interceptor.ObjetoTransacao;
import gcom.spcserasa.FiltroNegativadorMovimento;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativadorMovimento extends ObjetoTransacao implements Serializable {

	public Filtro retornaFiltro() {
		FiltroNegativadorMovimento filtroNegativadorExclusaoMotivo = new FiltroNegativadorMovimento();
		filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimento.ID,this.getId()));		
		return filtroNegativadorExclusaoMotivo;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

	public static short CODIGO_MOVIMENTO_INCLUSAO = 1;
	
	public static short CODIGO_MOVIMENTO_EXCLUSAO = 2;
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private short codigoMovimento;

    /** nullable persistent field */
    private Date dataEnvio;

    /** persistent field */
    private Date dataProcessamentoEnvio;

    /** nullable persistent field */
    private Date dataRetorno;

    /** nullable persistent field */
    private Date dataProcessamentoRetorno;

    /** nullable persistent field */
    private Integer numeroSequencialEnvio;

    /** nullable persistent field */
    private Integer numeroSequencialRetorno;

    /** nullable persistent field */
    private Integer numeroRegistrosEnvio;

    /** nullable persistent field */
    private Integer numeroRegistrosRetorno;

    /** nullable persistent field */
    private BigDecimal valorTotalEnvio;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.cobranca.Negativador negativador;

    /** persistent field */
    private gcom.cobranca.NegativacaoComando negativacaoComando;

    /** persistent field */
    private Set negativadorMovimentoReg;

    /** full constructor */
    public NegativadorMovimento(Integer id, short codigoMovimento, Date dataEnvio, Date dataProcessamentoEnvio, Date dataRetorno, Date dataProcessamentoRetorno, Integer numeroSequencialEnvio, Integer numeroSequencialRetorno, Integer numeroRegistrosEnvio, Integer numeroRegistrosRetorno, BigDecimal valorTotalEnvio, Date ultimaAlteracao, gcom.cobranca.Negativador negativador, gcom.cobranca.NegativacaoComando negativacaoComando, Set negativadorMovimentoReg) {
        this.id = id;
        this.codigoMovimento = codigoMovimento;
        this.dataEnvio = dataEnvio;
        this.dataProcessamentoEnvio = dataProcessamentoEnvio;
        this.dataRetorno = dataRetorno;
        this.dataProcessamentoRetorno = dataProcessamentoRetorno;
        this.numeroSequencialEnvio = numeroSequencialEnvio;
        this.numeroSequencialRetorno = numeroSequencialRetorno;
        this.numeroRegistrosEnvio = numeroRegistrosEnvio;
        this.numeroRegistrosRetorno = numeroRegistrosRetorno;
        this.valorTotalEnvio = valorTotalEnvio;
        this.ultimaAlteracao = ultimaAlteracao;
        this.negativador = negativador;
        this.negativacaoComando = negativacaoComando;
        this.negativadorMovimentoReg = negativadorMovimentoReg;
    }

    /** default constructor */
    public NegativadorMovimento() {
    }

    /** minimal constructor */
    public NegativadorMovimento(Integer id, short codigoMovimento, Date dataProcessamentoEnvio, gcom.cobranca.Negativador negativador, gcom.cobranca.NegativacaoComando negativacaoComando, Set negativadorMovimentoReg) {
        this.id = id;
        this.codigoMovimento = codigoMovimento;
        this.dataProcessamentoEnvio = dataProcessamentoEnvio;
        this.negativador = negativador;
        this.negativacaoComando = negativacaoComando;
        this.negativadorMovimentoReg = negativadorMovimentoReg;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getCodigoMovimento() {
        return this.codigoMovimento;
    }

    public void setCodigoMovimento(short codigoMovimento) {
        this.codigoMovimento = codigoMovimento;
    }

    public Date getDataEnvio() {
        return this.dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public Date getDataProcessamentoEnvio() {
        return this.dataProcessamentoEnvio;
    }

    public void setDataProcessamentoEnvio(Date dataProcessamentoEnvio) {
        this.dataProcessamentoEnvio = dataProcessamentoEnvio;
    }

    public Date getDataRetorno() {
        return this.dataRetorno;
    }

    public void setDataRetorno(Date dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    public Date getDataProcessamentoRetorno() {
        return this.dataProcessamentoRetorno;
    }

    public void setDataProcessamentoRetorno(Date dataProcessamentoRetorno) {
        this.dataProcessamentoRetorno = dataProcessamentoRetorno;
    }

    public Integer getNumeroSequencialEnvio() {
        return this.numeroSequencialEnvio;
    }

    public void setNumeroSequencialEnvio(Integer numeroSequencialEnvio) {
        this.numeroSequencialEnvio = numeroSequencialEnvio;
    }

    public Integer getNumeroSequencialRetorno() {
        return this.numeroSequencialRetorno;
    }

    public void setNumeroSequencialRetorno(Integer numeroSequencialRetorno) {
        this.numeroSequencialRetorno = numeroSequencialRetorno;
    }

    public Integer getNumeroRegistrosEnvio() {
        return this.numeroRegistrosEnvio;
    }

    public void setNumeroRegistrosEnvio(Integer numeroRegistrosEnvio) {
        this.numeroRegistrosEnvio = numeroRegistrosEnvio;
    }

    public Integer getNumeroRegistrosRetorno() {
        return this.numeroRegistrosRetorno;
    }

    public void setNumeroRegistrosRetorno(Integer numeroRegistrosRetorno) {
        this.numeroRegistrosRetorno = numeroRegistrosRetorno;
    }

    public BigDecimal getValorTotalEnvio() {
        return this.valorTotalEnvio;
    }

    public void setValorTotalEnvio(BigDecimal valorTotalEnvio) {
        this.valorTotalEnvio = valorTotalEnvio;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.cobranca.Negativador getNegativador() {
        return this.negativador;
    }

    public void setNegativador(gcom.cobranca.Negativador negativador) {
        this.negativador = negativador;
    }

    public gcom.cobranca.NegativacaoComando getNegativacaoComando() {
        return this.negativacaoComando;
    }

    public void setNegativacaoComando(gcom.cobranca.NegativacaoComando negativacaoComando) {
        this.negativacaoComando = negativacaoComando;
    }

    public Set getNegativadorMovimentoReg() {
        return this.negativadorMovimentoReg;
    }

    public void setNegativadorMovimentoReg(Set negativadorMovimentoReg) {
        this.negativadorMovimentoReg = negativadorMovimentoReg;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
