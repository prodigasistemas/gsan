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
package gcom.faturamento.credito;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class CreditoARealizarGeral extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private short indicadorHistorico;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private CreditoARealizarHistorico creditoARealizarHistorico;

    /** nullable persistent field */
    private CreditoARealizar creditoARealizar;

    /** full constructor */
    public CreditoARealizarGeral(Integer id, short indicadorHistorico, Date ultimaAlteracao, CreditoARealizarHistorico creditoARealizarHistorico, CreditoARealizar creditoARealizar) {
        this.id = id;
        this.indicadorHistorico = indicadorHistorico;
        this.ultimaAlteracao = ultimaAlteracao;
        this.creditoARealizarHistorico = creditoARealizarHistorico;
        this.creditoARealizar = creditoARealizar;
    }

    /** default constructor */
    public CreditoARealizarGeral() {
    }

    /** minimal constructor */
    public CreditoARealizarGeral(Integer id, short indicadorHistorico, Date ultimaAlteracao) {
        this.id = id;
        this.indicadorHistorico = indicadorHistorico;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("crarId", getId())
            .toString();
    }

	/**
	 * @return Retorna o campo creditoARealizar.
	 */
	public CreditoARealizar getCreditoARealizar() {
		return creditoARealizar;
	}

	/**
	 * @param creditoARealizar O creditoARealizar a ser setado.
	 */
	public void setCreditoARealizar(CreditoARealizar creditoARealizar) {
		this.creditoARealizar = creditoARealizar;
	}

	/**
	 * @return Retorna o campo creditoARealizarHistorico.
	 */
	public CreditoARealizarHistorico getCreditoARealizarHistorico() {
		return creditoARealizarHistorico;
	}

	/**
	 * @param creditoARealizarHistorico O creditoARealizarHistorico a ser setado.
	 */
	public void setCreditoARealizarHistorico(
			CreditoARealizarHistorico creditoARealizarHistorico) {
		this.creditoARealizarHistorico = creditoARealizarHistorico;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo indicadorHistorico.
	 */
	public short getIndicadorHistorico() {
		return indicadorHistorico;
	}

	/**
	 * @param indicadorHistorico O indicadorHistorico a ser setado.
	 */
	public void setIndicadorHistorico(short indicadorHistorico) {
		this.indicadorHistorico = indicadorHistorico;
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
	
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroCreditoARealizarGeral filtroCreditoARealizarGeral = new FiltroCreditoARealizarGeral();
		
		filtroCreditoARealizarGeral.adicionarParametro(new ParametroSimples(FiltroCreditoARealizarGeral.ID, this.getId()));		
		
		filtroCreditoARealizarGeral.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarHistorico");
		filtroCreditoARealizarGeral.adicionarCaminhoParaCarregamentoEntidade("creditoARealizar");
		
		return filtroCreditoARealizarGeral; 
	}

}
