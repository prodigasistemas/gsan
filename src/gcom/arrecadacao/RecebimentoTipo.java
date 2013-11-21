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
package gcom.arrecadacao;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class RecebimentoTipo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	
	//Constantes *******************************************************************************************
	public final static Integer RECEBIMENTOS_CLASSIFICADOS = new Integer(1);
	
	public final static Integer RECEBIMENTOS_NAO_CLASSIFICADOS = new Integer(2);

	public final static Integer TOTAL_RECEBIMENTOS = new Integer(3);

	public final static Integer DEVOLUCOES_CLASSIFICADAS = new Integer(4);

	public final static Integer DEVOLUCOES_NAO_CLASSIFICADAS = new Integer(5);
	
	public final static Integer TOTAL_DEVOLUCOES = new Integer(6);
	
	public final static Integer ARRECADACAO_LIQUIDA = new Integer(7);
	
	public final static Integer RECEBIMENTO_MESES_ANTERIORES_CLASSIFICADAS_NO_MES = new Integer(8);
	
	public final static Integer DEVOLUCOES_RECEBIMENTO_MESES_ANTERIORES_CLASSIFICADAS_NO_MES = new Integer(9);
	
	public final static Integer BAIXA_RECEBIMENTOS_NAO_CLASSIFICADOS = new Integer(10);
	
	public final static Integer RECEBIMENTOS_VALORES_CONTABILIZADOS_COMO_PERDAS = new Integer(11);
	
	public final static Integer RESUMO_RECEBIMENTOS_NAO_CLASSIFICADOS = new Integer(12);
	
	public final static Integer RESUMO_DEVOLUCOES_NAO_CLASSIFICADAS = new Integer(13);
		
	public final static Integer TOTAL_DESCONTOS = new Integer(14);
	
	public final static Integer RECEBIMENTO_DE_VALORES_DA_CAMPANHA_SOLIDARIEDADE_DA_CRIANCA = new Integer(15);
	
	public final static Integer RECEBIMENTO_MESES_ATE_31_12_2012_CLASSIFICADOS_NO_MES = new Integer (16);
	//********************************************************************************************************

	
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    private Short numeroSequencia;

    /**
	 * @return Retorna o campo numeroSequencia.
	 */
	public Short getNumeroSequencia() {
		return numeroSequencia;
	}

	/**
	 * @param numeroSequencia O numeroSequencia a ser setado.
	 */
	public void setNumeroSequencia(Short numeroSequencia) {
		this.numeroSequencia = numeroSequencia;
	}

	/** full constructor */
    public RecebimentoTipo (String descricao, Short indicadorUso, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public RecebimentoTipo () {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
