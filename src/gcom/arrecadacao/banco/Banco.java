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
package gcom.arrecadacao.banco;

import gcom.util.filtro.Filtro;
import gcom.util.tabelaauxiliar.abreviada.TabelaAuxiliarAbreviada;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Banco extends TabelaAuxiliarAbreviada implements Serializable {
	
	private static final long serialVersionUID = 1L;

//  /*  /** identifier field */
//    private Integer id;
//
//    /** persistent field */
//    private String nomeAbreviado;
//
//    /** persistent field */
//    private int numeroSequenciaRecebimentoFita;
//
//    /** persistent field */
//    private int numeroSequenciaEnvioFita;
//
//    /** nullable persistent field */
//    private String nomeBanco;
//
//    /** nullable persistent field */
//    private Short indicadorUso;
//
//    /** nullable persistent field */
//    private Date ultimaAlteracao;
//
//    /** full constructor */
//    public Banco(String nomeAbreviado, int numeroSequenciaRecebimentoFita, int numeroSequenciaEnvioFita, String nomeBanco, Short indicadorUso, Date ultimaAlteracao) {
//        this.nomeAbreviado = nomeAbreviado;
//        this.numeroSequenciaRecebimentoFita = numeroSequenciaRecebimentoFita;
//        this.numeroSequenciaEnvioFita = numeroSequenciaEnvioFita;
//        this.nomeBanco = nomeBanco;
//        this.indicadorUso = indicadorUso;
//        this.ultimaAlteracao = ultimaAlteracao;
//    }
//
//    /** default constructor */
//    public Banco() {
//    }
//
//    /** minimal constructor */
//    public Banco(String nomeAbreviado, int numeroSequenciaRecebimentoFita, int numeroSequenciaEnvioFita) {
//        this.nomeAbreviado = nomeAbreviado;
//        this.numeroSequenciaRecebimentoFita = numeroSequenciaRecebimentoFita;
//        this.numeroSequenciaEnvioFita = numeroSequenciaEnvioFita;
//    }
//
//    public Integer getId() {
//        return this.id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getNomeAbreviado() {
//        return this.nomeAbreviado;
//    }
//
//    public void setNomeAbreviado(String nomeAbreviado) {
//        this.nomeAbreviado = nomeAbreviado;
//    }
//
//    public int getNumeroSequenciaRecebimentoFita() {
//        return this.numeroSequenciaRecebimentoFita;
//    }
//
//    public void setNumeroSequenciaRecebimentoFita(int numeroSequenciaRecebimentoFita) {
//        this.numeroSequenciaRecebimentoFita = numeroSequenciaRecebimentoFita;
//    }
//
//    public int getNumeroSequenciaEnvioFita() {
//        return this.numeroSequenciaEnvioFita;
//    }
//
//    public void setNumeroSequenciaEnvioFita(int numeroSequenciaEnvioFita) {
//        this.numeroSequenciaEnvioFita = numeroSequenciaEnvioFita;
//    }
//
//    public String getNomeBanco() {
//        return this.nomeBanco;
//    }
//
//    public void setNomeBanco(String nomeBanco) {
//        this.nomeBanco = nomeBanco;
//    }
//
//    public Short getIndicadorUso() {
//        return this.indicadorUso;
//    }
//
//    public void setIndicadorUso(Short indicadorUso) {
//        this.indicadorUso = indicadorUso;
//    }
//
//    public Date getUltimaAlteracao() {
//        return this.ultimaAlteracao;
//    }
//
//    public void setUltimaAlteracao(Date ultimaAlteracao) {
//        this.ultimaAlteracao = ultimaAlteracao;
//    }
//    
//    
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Filtro retornaFiltro() {
		return null;
	}

}
