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
package gcom.seguranca.transacao;

import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class TabelaLinhaColunaAlteracao implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String conteudoColunaAnterior;

    /** nullable persistent field */
    private String conteudoColunaAtual;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.seguranca.transacao.TabelaColuna tabelaColuna;

    /** persistent field */
    private gcom.seguranca.transacao.TabelaLinhaAlteracao tabelaLinhaAlteracao;
    
    private short indicadorAtualizada;

    public short getIndicadorAtualizada() {
		return indicadorAtualizada;
	}

	public void setIndicadorAtualizada(short indicadorAtualizada) {
		this.indicadorAtualizada = indicadorAtualizada;
	}

	/** full constructor */
    public TabelaLinhaColunaAlteracao(String conteudoColunaAnterior, String conteudoColunaAtual, Date ultimaAlteracao, gcom.seguranca.transacao.TabelaColuna tabelaColuna, gcom.seguranca.transacao.TabelaLinhaAlteracao tabelaLinhaAlteracao) {
        this.conteudoColunaAnterior = conteudoColunaAnterior;
        this.conteudoColunaAtual = conteudoColunaAtual;
        this.ultimaAlteracao = ultimaAlteracao;
        this.tabelaColuna = tabelaColuna;
        this.tabelaLinhaAlteracao = tabelaLinhaAlteracao;
    }

    /** default constructor */
    public TabelaLinhaColunaAlteracao() {
    }

    /** minimal constructor */
    public TabelaLinhaColunaAlteracao(gcom.seguranca.transacao.TabelaColuna tabelaColuna, gcom.seguranca.transacao.TabelaLinhaAlteracao tabelaLinhaAlteracao) {
        this.tabelaColuna = tabelaColuna;
        this.tabelaLinhaAlteracao = tabelaLinhaAlteracao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConteudoColunaAnterior() {
        return this.conteudoColunaAnterior;
    }

    /**
     * Recebe um object e converte para string aplicando a formatação adequada 
     * @param conteudoColunaAnterior
     */
    public void setConteudoColunaAnterior(Object conteudoColunaAnterior) {
        this.conteudoColunaAnterior = formatarConteudo(conteudoColunaAnterior);
    }
    
    /**
     * Este método determina como será a formatação dos conteudos exibidos
     * no registrar transação.  Para cada tipo, é definido uma formatação.
     * @param conteudo
     * @return
     */
    private String formatarConteudo(Object conteudo){
    	String retorno = "";
    	if (conteudo != null){
    		retorno = conteudo.toString();
    		if (conteudo instanceof Date){
    			retorno = Util.formatarDataComHora((Date)conteudo);
    		} else if (conteudo instanceof Short && tabelaColuna != null 
    				&& tabelaColuna.getColuna() != null 
    				&& tabelaColuna.getColuna().indexOf("_ic") != -1){
    			if (ConstantesSistema.INDICADOR_USO_ATIVO.equals(conteudo)){
    				retorno = "Sim";
    			} else {
    				retorno = "Nao";
    			}
    		} else if (conteudo instanceof Integer){
    			Integer conteudoInt = (Integer) conteudo;
    			if (tabelaColuna != null 
    				&& tabelaColuna.getColuna() != null){
    				if (tabelaColuna.getColuna().indexOf("_am") != -1){
    					retorno = Util.formatarAnoMesParaMesAno(conteudoInt);
    				} else if (tabelaColuna.getColuna().indexOf("psex_id") != -1){
    					
    					switch (conteudoInt) {
						case 1:
							retorno = "MASCULINO";
							break;
						case 2:
							retorno = "FEMININO";
							break;
						default:
							retorno = "INDEFINIDO";
							break;
						}    					
    				}
    			} 
    		} else if (conteudo instanceof BigDecimal){
    			//  Numero do IPTU eh BigDecimal mas nao deve ser formatado com #.###,## 
    			if (tabelaColuna != null 
        				&& tabelaColuna.getColuna() != null 
        				&& (tabelaColuna.getColuna().equals("imec_nniptu")
        				   || tabelaColuna.getColuna().equals("imov_nniptu"))){
    				retorno = conteudo + "";
    			} else {
    				retorno = Util.formataBigDecimal((BigDecimal) conteudo, 2, true);	
    			}
    			
    		} 
    		if (retorno != null && retorno.length() > 50) {
				retorno = retorno.substring(0, 49);
    		}
    	}
		return retorno; 
    }

    public String getConteudoColunaAtual() {
        return this.conteudoColunaAtual;
    }

    public void setConteudoColunaAtual(Object conteudoColunaAtual) {
        this.conteudoColunaAtual = formatarConteudo(conteudoColunaAtual);
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.seguranca.transacao.TabelaColuna getTabelaColuna() {
        return this.tabelaColuna;
    }

    public void setTabelaColuna(gcom.seguranca.transacao.TabelaColuna tabelaColuna) {
        this.tabelaColuna = tabelaColuna;
    }

    public gcom.seguranca.transacao.TabelaLinhaAlteracao getTabelaLinhaAlteracao() {
        return this.tabelaLinhaAlteracao;
    }

    public void setTabelaLinhaAlteracao(gcom.seguranca.transacao.TabelaLinhaAlteracao tabelaLinhaAlteracao) {
        this.tabelaLinhaAlteracao = tabelaLinhaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
