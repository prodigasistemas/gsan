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

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class NegativadorExclusaoMotivo extends ObjetoTransacao implements Serializable {
	
	public static final String PAGAMENTO_DA_DIVIDA  = new String("PAGAMENTO DA DIVIDA");
	public static final String RENEGOCIACAO_DA_DIVIDA  = new String("RENEGOCIAÇÃO DA DIVIDA");
	public static final String MOTIVO_NAO_IDENTIFICADO  = new String("MOTIVO NÃO IDENTIFICADO");

	public Filtro retornaFiltro() {
		FiltroNegativadorExclusaoMotivo filtroNegativadorExclusaoMotivo = new FiltroNegativadorExclusaoMotivo();
		filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorExclusaoMotivo.ID,this.getId()));		
		return filtroNegativadorExclusaoMotivo;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricaoExclusaoMotivo();
	}
	
	@Override
	public void initializeLazy() {
		getDescricaoExclusaoMotivo();
	}

	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    private Short codigoExclusaoMotivo;
    
    /** persistent field */
    private String descricaoExclusaoMotivo;

    /** persistent field */
    private Short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.cobranca.Negativador negativador;
    
    /** persistent field */
    private gcom.cobranca.CobrancaDebitoSituacao cobrancaDebitoSituacao;

    /** persistent field */
    private Set negativadorMovimentoReg;

    /** full constructor */
    public NegativadorExclusaoMotivo(Integer id, short codigoExclusaoMotivo,String descricaoExclusaoMotivo, short indicadorUso, Date ultimaAlteracao, gcom.cobranca.Negativador negativador, Set negativadorMovimentoReg) {
        this.id = id;
        this.codigoExclusaoMotivo = codigoExclusaoMotivo;
        this.descricaoExclusaoMotivo = descricaoExclusaoMotivo;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.negativador = negativador;
        this.negativadorMovimentoReg = negativadorMovimentoReg;
    }

    /** default constructor */
    public NegativadorExclusaoMotivo() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoExclusaoMotivo() {
        return this.descricaoExclusaoMotivo;
    }

    public void setDescricaoExclusaoMotivo(String descricaoExclusaoMotivo) {
        this.descricaoExclusaoMotivo = descricaoExclusaoMotivo;
    }

    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
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

	public gcom.cobranca.CobrancaDebitoSituacao getCobrancaDebitoSituacao() {
		return cobrancaDebitoSituacao;
	}

	public void setCobrancaDebitoSituacao(
			gcom.cobranca.CobrancaDebitoSituacao cobrancaDebitoSituacao) {
		this.cobrancaDebitoSituacao = cobrancaDebitoSituacao;
	}

	/**
	 * @return Retorna o campo codigoExclusaoMotivo.
	 */
	public Short getCodigoExclusaoMotivo() {
		return codigoExclusaoMotivo;
	}

	/**
	 * @param codigoExclusaoMotivo O codigoExclusaoMotivo a ser setado.
	 */
	public void setCodigoExclusaoMotivo(Short codigoExclusaoMotivo) {
		this.codigoExclusaoMotivo = codigoExclusaoMotivo;
	}

	

	
	
	
	

}
