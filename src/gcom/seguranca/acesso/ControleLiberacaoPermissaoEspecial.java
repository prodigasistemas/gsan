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

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

@ControleAlteracao
/** @author Hibernate CodeGenerator */
public class ControleLiberacaoPermissaoEspecial extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
	
	/** identifier field */
	private Integer id;

	@ControleAlteracao(FiltroControleLiberacaoPermissaoEspecial.FUNCIONALIDADE)
	/** persistent field */
	private gcom.seguranca.acesso.Funcionalidade funcionalidade;
	
	@ControleAlteracao(FiltroControleLiberacaoPermissaoEspecial.PERMISSAO_ESPECIAL)
	/** persistent field */
	private gcom.seguranca.acesso.PermissaoEspecial permissaoEspecial;

	@ControleAlteracao(funcionalidade = 
	{OPERACAO_INSERIR_CONTROLE_LIBERACAO_PERMISSAO_ESPECIAL, OPERACAO_MANTER_CONTROLE_LIBERACAO_PERMISSAO_ESPECIAL})
	/** nullable persistent field */
	private Short indicadorUso;

	/** nullable persistent field */
	private Date ultimaAlteracao;
	
	
    public static final int OPERACAO_INSERIR_CONTROLE_LIBERACAO_PERMISSAO_ESPECIAL = 1654;	
	public static final int OPERACAO_MANTER_CONTROLE_LIBERACAO_PERMISSAO_ESPECIAL = 1673;
		

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	/** full constructor */
	public ControleLiberacaoPermissaoEspecial(
			PermissaoEspecial permissaoEspecial, Funcionalidade funcionalidade,
			Short indicadorUso, Date ultimaAlteracao) {
		
		this.permissaoEspecial = permissaoEspecial;
		this.funcionalidade = funcionalidade;		
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/** default constructor */
	public ControleLiberacaoPermissaoEspecial() {
	}

	/** minimal constructor */
	public ControleLiberacaoPermissaoEspecial(gcom.seguranca.acesso.Funcionalidade funcionalidade, gcom.seguranca.acesso.PermissaoEspecial permissaoEspecial) {
		this.permissaoEspecial = permissaoEspecial;
		this.funcionalidade = funcionalidade;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public gcom.seguranca.acesso.Funcionalidade getFuncionalidade() {
		return funcionalidade;
	}

	public void setFuncionalidade(
			gcom.seguranca.acesso.Funcionalidade funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	public gcom.seguranca.acesso.PermissaoEspecial getPermissaoEspecial() {
		return permissaoEspecial;
	}

	public void setPermissaoEspecial(
			gcom.seguranca.acesso.PermissaoEspecial permissaoEspecial) {
		this.permissaoEspecial = permissaoEspecial;
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
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param other
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof ControleLiberacaoPermissaoEspecial)) {
			return false;
		}
		ControleLiberacaoPermissaoEspecial castOther = (ControleLiberacaoPermissaoEspecial) other;

		return (this.getId().equals(castOther.getId()));
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada(){
		String []atributos = {
				"funcionalidade.descricao", 
				"permissaoEspecial.descricao"};
		
		return atributos;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada(){
		String []labels = { 
				"Funcionalidade",
				"Permissao Especial"
				};
			return labels;		
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId().toString();
	}
	
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroControleLiberacaoPermissaoEspecial.FUNCIONALIDADE);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroControleLiberacaoPermissaoEspecial.PERMISSAO_ESPECIAL);
		
		return filtro;
	}
	
	public Filtro retornaFiltro() {
		FiltroControleLiberacaoPermissaoEspecial filtro = new FiltroControleLiberacaoPermissaoEspecial();

		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroControleLiberacaoPermissaoEspecial.FUNCIONALIDADE);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroControleLiberacaoPermissaoEspecial.PERMISSAO_ESPECIAL);
		filtro.adicionarParametro(new ParametroSimples(FiltroControleLiberacaoPermissaoEspecial.ID,
				this.getId()));
		
		
		return filtro;
	}

}
