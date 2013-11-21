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
package gcom.arrecadacao.pagamento;

import gcom.interceptor.ObjetoGcom;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class PagamentoSituacao extends ObjetoGcom {
	
	private static final long serialVersionUID = 1L;

	//Constantes *********************************
	public final static Integer PAGAMENTO_CLASSIFICADO = new Integer(0);
	
	public final static Integer PAGAMENTO_EM_DUPLICIDADE = new Integer(1);

	//criado por pedro alexandre em 25/05/2006 para o UC[0276] 
	public final static Integer DOCUMENTO_INEXISTENTE = new Integer(2);
	
	public final static Integer FATURA_INEXISTENTE = new Integer(2);

	public final static Integer VALOR_EM_EXCESSO = new Integer(3);

	public final static Integer VALOR_A_BAIXAR = new Integer(4);
	
	public final static Integer VALOR_NAO_CONFERE = new Integer(5);
	
	public final static Integer MOVIMENTO_ABERTO = new Integer(7);
	
	public final static Integer DUPLICIDADE_EXCESSO_DEVOLVIDO = new Integer(9);
    
	public final static Integer DOCUMENTO_A_CONTABILIZAR = new Integer(10);
    
	/**TODO: COSANPA
	 * Mantis 615 - Detalhar contabilização de documentos inexistentes
	 * 
	 * @author Wellington Rocha
	 * @author Felipe Santos
	 * @date 01/08/2012*/
	
    public final static Integer DOCUMENTO_INEXISTENTE_DEBITO_PRESCRITO = new Integer(11);
    
    public final static Integer DOCUMENTO_INEXISTENTE_CONTA_PARCELADA = new Integer(12);
    
    public final static Integer DOCUMENTO_INEXISTENTE_CONTA_CANCELADA = new Integer(13);
    
    public final static Integer DOCUMENTO_INEXISTENTE_ERRO_PROCESSAMENTO = new Integer(14);
	//********************************************
	
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private String descricaoAbreviada;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Short indicadorUso;

    /** full constructor */
    public PagamentoSituacao(String descricao, String descricaoAbreviada, Date ultimaAlteracao, Short indicadorUso) {
    	this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorUso = indicadorUso;
    }

    /** default constructor */
    public PagamentoSituacao() {
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

    public String getDescricaoAbreviada() {
        return this.descricaoAbreviada;
    }

    public void setDescricaoAbreviada(String descricaoAbreviada) {
        this.descricaoAbreviada = descricaoAbreviada;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void  setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
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
}
