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
package gcom.seguranca.acesso;

import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.AtributoGrupo;
import gcom.seguranca.transacao.TabelaLinhaAlteracao;
import gcom.util.Util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OperacaoEfetuada implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */    
    private gcom.seguranca.acesso.Operacao operacao;
    
    private Set tabelaLinhaAlteracoes;
    
    private Set usuarioAlteracoes; 

    private int argumentoValor;
    
    private Integer idObjetoPrincipal;  
    
    private TabelaLinhaAlteracao tabelaLinhaAlteracaoPrincipal;
    
    private AtributoGrupo atributoGrupo;
    
	public TabelaLinhaAlteracao getTabelaLinhaAlteracaoPrincipal() {
		return tabelaLinhaAlteracaoPrincipal;
	}

	public void setTabelaLinhaAlteracaoPrincipal(
			TabelaLinhaAlteracao tabelaLinhaAlteracaoPrincipal) {
		this.tabelaLinhaAlteracaoPrincipal = tabelaLinhaAlteracaoPrincipal;
	}

    private String dadosAdicionais;
       
    /**
	 * @return Returns the argumentoValor.
	 */
	public int getArgumentoValor() {
		return argumentoValor;
	}

	/**
	 * @param argumentoValor The argumentoValor to set.
	 */
	public void setArgumentoValor(Integer argumentoValor) {
		if (argumentoValor != null) {
			this.argumentoValor = argumentoValor;
		} else {
			this.argumentoValor = new Integer(-1);
		}
	}

	/** full constructor */
    public OperacaoEfetuada(Date ultimaAlteracao, gcom.seguranca.acesso.Operacao operacao) {
        this.ultimaAlteracao = ultimaAlteracao;
        this.operacao = operacao;
    }

    /** default constructor */
    public OperacaoEfetuada() {
    }

    /** minimal constructor */
    public OperacaoEfetuada(gcom.seguranca.acesso.Operacao operacao) {
        this.operacao = operacao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    } 

    public gcom.seguranca.acesso.Operacao getOperacao() {
        return this.operacao;
    }

    public void setOperacao(gcom.seguranca.acesso.Operacao operacao) {
        this.operacao = operacao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Set getTabelaLinhaAlteracoes() {
		return tabelaLinhaAlteracoes;
	}

	public void setTabelaLinhaAlteracoes(Set tabelaLinhaAlteracoes) {
		this.tabelaLinhaAlteracoes = tabelaLinhaAlteracoes;
	}

	public Set getUsuarioAlteracoes() {
		return usuarioAlteracoes;
	}

	public void setUsuarioAlteracoes(Set usuarioAlteracoes) {
		this.usuarioAlteracoes = usuarioAlteracoes;
	}

	public Integer getIdObjetoPrincipal() {
		return idObjetoPrincipal;
	}

	public void setIdObjetoPrincipal(Integer idObjetoPrincipal) {
		this.idObjetoPrincipal = idObjetoPrincipal;
	}

	public String getDadosAdicionais() {
		return dadosAdicionais;
	}

	public void setDadosAdicionais(String dadosAdicionais) {
		this.dadosAdicionais = dadosAdicionais;
	}

	/**
	 * Preenche 
	 * @param objetoTransacao
	 * @return 
	 */
	public boolean preencherDadosAdicionais(ObjetoTransacao objetoTransacao) {
//		boolean houveAlteracao = false;
		String dadosAntigos = getDadosAdicionais();
		if (objetoTransacao != null){		
			String[] atributos = objetoTransacao.retornarAtributosInformacoesOperacaoEfetuada();
			if (atributos != null && getDadosAdicionais() == null){
				String[] labels = objetoTransacao.retornarLabelsInformacoesOperacaoEfetuada();
				String dadosAdicionais = "";
				for (int i = 0; i < atributos.length; i++) {
					Object valor = objetoTransacao.get(atributos[i]);										
					String valorFormatado = formatarConteudo(valor, atributos[i]);
					dadosAdicionais += labels[i] + ":" + 
					    (valor == null ? "" : valorFormatado) +"$";
				}			
				setDadosAdicionais(dadosAdicionais);
			}
		}		
		return !(dadosAntigos == dadosAdicionais);
	}
	
	private String formatarConteudo(Object conteudo, String nomeAtributo){
    	String retorno = "";
    	if (conteudo != null){
    		if (conteudo instanceof Date){
    			retorno = Util.formatarDataComHora((Date)conteudo);
    		} else if (conteudo instanceof Integer){
    			if (nomeAtributo.toLowerCase().indexOf("anomesreferencia") != -1){
    				retorno = Util.formatarAnoMesParaMesAno((Integer) conteudo);	
    			} else {
    				retorno = conteudo.toString();
    			}
    		} else if (conteudo instanceof BigDecimal){    			
    			retorno = Util.formataBigDecimal((BigDecimal) conteudo, 2, true);
    		} else {
    			retorno = conteudo.toString();
    		}    		
    	}
		return retorno; 		
	}

	public String[][] formatarDadosAdicionais(){
		String[][] valores = null;
		if (dadosAdicionais != null) {
			StringTokenizer stk = new StringTokenizer(dadosAdicionais,"$");
			valores = new String[stk.countTokens()][2];
			int i = 0;
			while (stk.hasMoreElements()) {
				String element = (String) stk.nextElement();
				int ind = element.indexOf(":");
				if (ind != -1){
					String label = element.substring(0,ind);
					String valor = element.substring(ind+1, element.length());
					valores[i][0] = label;
					valores[i][1] = valor;
					i++;
				}		
			}
		}
		return valores;
	}

	public AtributoGrupo getAtributoGrupo() {
		return atributoGrupo;
	}

	public void setAtributoGrupo(AtributoGrupo atributoGrupo) {
		this.atributoGrupo = atributoGrupo;
	}

	public void setArgumentoValor(int argumentoValor) {
		this.argumentoValor = argumentoValor;
	}
 
	
}
