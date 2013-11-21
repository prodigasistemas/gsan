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
package gcom.micromedicao;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


@ControleAlteracao()
public class ItemServico extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	public static final int OPERACAO_INSERIR_ITEM_SERVICO = 1663;
	public static final int OPERACAO_MANTER_ITEM_SERVICO = 1668;
	public static final int OPERACAO_REMOVER_ITEM_SERVICO = 1669;
		
	
	private Integer id;
	
	@ControleAlteracao(funcionalidade={OPERACAO_INSERIR_ITEM_SERVICO,OPERACAO_MANTER_ITEM_SERVICO,OPERACAO_REMOVER_ITEM_SERVICO})
	private String descricao;
	
	@ControleAlteracao(funcionalidade={OPERACAO_INSERIR_ITEM_SERVICO,OPERACAO_MANTER_ITEM_SERVICO,OPERACAO_REMOVER_ITEM_SERVICO})
	private String descricaoAbreviada;
	
	@ControleAlteracao(funcionalidade={OPERACAO_INSERIR_ITEM_SERVICO,OPERACAO_MANTER_ITEM_SERVICO,OPERACAO_REMOVER_ITEM_SERVICO})
	private Short indicadorUso;
	
	@ControleAlteracao(funcionalidade={OPERACAO_INSERIR_ITEM_SERVICO,OPERACAO_MANTER_ITEM_SERVICO,OPERACAO_REMOVER_ITEM_SERVICO})
	private Integer codigoConstanteCalculo;
	
	@ControleAlteracao(funcionalidade={OPERACAO_INSERIR_ITEM_SERVICO,OPERACAO_MANTER_ITEM_SERVICO,OPERACAO_REMOVER_ITEM_SERVICO})
	private Date ultimaAlteracao;

	@ControleAlteracao(funcionalidade={OPERACAO_INSERIR_ITEM_SERVICO,OPERACAO_MANTER_ITEM_SERVICO,OPERACAO_REMOVER_ITEM_SERVICO})
	private Long codigoItem;
	
	public final static Integer CODIGO_CONSTANTE_1 = 1;
	public final static Integer CODIGO_CONSTANTE_2 = 2;
	public final static Integer CODIGO_CONSTANTE_3 = 3;
	public final static Integer CODIGO_CONSTANTE_4 = 4;
	public final static Integer CODIGO_CONSTANTE_5 = 5;
	public final static Integer CODIGO_CONSTANTE_6 = 6;
	public final static Integer CODIGO_CONSTANTE_7 = 7;
	public final static Integer CODIGO_CONSTANTE_8 = 8;
	public final static Integer CODIGO_CONSTANTE_9 = 9;
	public final static Integer CODIGO_CONSTANTE_10 = 10;
	public final static Integer CODIGO_CONSTANTE_11 = 11;
	public final static Integer CODIGO_CONSTANTE_12 = 12;
	public final static Integer CODIGO_CONSTANTE_13 = 13;
	public final static Integer CODIGO_CONSTANTE_14 = 14;
	public final static Integer CODIGO_CONSTANTE_15 = 15;
	public final static Integer CODIGO_CONSTANTE_16 = 16;
	public final static Integer CODIGO_CONSTANTE_17 = 17;
		
	    
	    public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getDescricaoAbreviada() {
			return descricaoAbreviada;
		}

		public void setDescricaoAbreviada(String descricaoAbreviada) {
			this.descricaoAbreviada = descricaoAbreviada;
		}

		public Integer getCodigoConstanteCalculo() {
			return codigoConstanteCalculo;
		}

		public void setCodigoConstanteCalculo(Integer codigoConstanteCalculo) {
			this.codigoConstanteCalculo = codigoConstanteCalculo;
		}

		public Date getUltimaAlteracao() {
			return ultimaAlteracao;
		}

		public void setUltimaAlteracao(Date ultimaAlteracao) {
			this.ultimaAlteracao = ultimaAlteracao;
		}

		public Short getIndicadorUso() {
	        return indicadorUso;
	    }

	    public void setIndicadorUso(Short indicadorUso) {
	        this.indicadorUso = indicadorUso;
	    }

	    
	    public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		
    public Long getCodigoItem() {
			return codigoItem;
		}

		public void setCodigoItem(Long codigoItem) {
			this.codigoItem = codigoItem;
		}

	public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }


	@Override
	public Filtro retornaFiltro() {
		FiltroItemServico filtroItemServico = new FiltroItemServico();
    	filtroItemServico.adicionarParametro(new ParametroSimples(FiltroItemServico.ID,this.getId()));		
       	return filtroItemServico;	
    }


	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
		
		
		@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarParametro(new ParametroSimples(FiltroItemServico.ID,this.getId()));
		
		return filtro;
	}
		
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId().toString();
	}
		
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"descricao"};
		return labels;		
	}
		
	@Override
		public String[] retornarLabelsInformacoesOperacaoEfetuada() {
			String []labels = {"Descricao"};
			return labels;		
		}
}
