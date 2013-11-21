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
package gcom.arrecadacao.aviso;

import gcom.arrecadacao.FiltroAvisoAcerto;
import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.banco.ContaBancaria;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AvisoAcerto extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	public Filtro retornaFiltro() {
		FiltroAvisoAcerto filtroAvisoAcerto = new FiltroAvisoAcerto();
        filtroAvisoAcerto.adicionarParametro(new ParametroSimples(FiltroAvisoBancario.ID, this.getId()));
        filtroAvisoAcerto.adicionarCaminhoParaCarregamentoEntidade("contaBancaria");
		return filtroAvisoAcerto ;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] ids = {"id"};
		return ids;
	}

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Integer indicadorCreditoDebito;

    /** nullable persistent field */
    private Date dataAcerto;

    /** nullable persistent field */
    private BigDecimal valorAcerto;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    /** nullable persistent field */
    private Integer indicadorArrecadacaoDevolucao;

    /** persistent field */
    private ContaBancaria contaBancaria;

    /** persistent field */
    private gcom.arrecadacao.aviso.AvisoBancario avisoBancario;

    /** full constructor */
    public AvisoAcerto(Integer indicadorCreditoDebito, Date dataAcerto, BigDecimal valorAcerto, Date ultimaAlteracao, ContaBancaria contaBancaria, gcom.arrecadacao.aviso.AvisoBancario avisoBancario, Integer indicadorArrecadacaoDevolucao) {
        this.indicadorCreditoDebito = indicadorCreditoDebito;
        this.dataAcerto = dataAcerto;
        this.valorAcerto = valorAcerto;
        this.ultimaAlteracao = ultimaAlteracao;
        this.contaBancaria = contaBancaria;
        this.avisoBancario = avisoBancario;
        this.indicadorArrecadacaoDevolucao = indicadorArrecadacaoDevolucao;
    }

    /** default constructor */
    public AvisoAcerto() {
    }

    /** minimal constructor */
    public AvisoAcerto(ContaBancaria contaBancaria, gcom.arrecadacao.aviso.AvisoBancario avisoBancario) {
        this.contaBancaria = contaBancaria;
        this.avisoBancario = avisoBancario;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIndicadorCreditoDebito() {
        return this.indicadorCreditoDebito;
    }

    public void setIndicadorCreditoDebito(Integer indicadorCreditoDebito) {
        this.indicadorCreditoDebito = indicadorCreditoDebito;
    }

    public Date getDataAcerto() {
        return this.dataAcerto;
    }

    public void setDataAcerto(Date dataAcerto) {
        this.dataAcerto = dataAcerto;
    }

    public BigDecimal getValorAcerto() {
        return this.valorAcerto;
    }

    public void setValorAcerto(BigDecimal valorAcerto) {
        this.valorAcerto = valorAcerto;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public ContaBancaria getContaBancaria() {
        return this.contaBancaria;
    }

    public void setContaBancaria(ContaBancaria contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public gcom.arrecadacao.aviso.AvisoBancario getAvisoBancario() {
        return this.avisoBancario;
    }

    public void setAvisoBancario(gcom.arrecadacao.aviso.AvisoBancario avisoBancario) {
        this.avisoBancario = avisoBancario;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	/**
	 * @return Retorna o campo indicadorArrecadacaoDevolucao.
	 */
	public Integer getIndicadorArrecadacaoDevolucao() {
		return indicadorArrecadacaoDevolucao;
	}

	/**
	 * @param indicadorArrecadacaoDevolucao O indicadorArrecadacaoDevolucao a ser setado.
	 */
	public void setIndicadorArrecadacaoDevolucao(
			Integer indicadorArrecadacaoDevolucao) {
		this.indicadorArrecadacaoDevolucao = indicadorArrecadacaoDevolucao;
	}

}
