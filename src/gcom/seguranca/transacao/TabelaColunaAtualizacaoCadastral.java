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

import gcom.cadastro.imovel.ImovelSubcategoriaAtualizacaoCadastral;
import gcom.interceptor.ObjetoGcom;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Date;



/** @author Hibernate CodeGenerator */
public class TabelaColunaAtualizacaoCadastral extends ObjetoGcom {
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String colunaValorAnterior;

    /** nullable persistent field */
    private String colunaValorAtual;

    /** nullable persistent field */
    
    private Short indicadorAutorizado;
    
    /** nullable persistent field */
    private Date dataValidacao;
    
    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private TabelaAtualizacaoCadastral tabelaAtualizacaoCadastral;
    
    private TabelaColuna tabelaColuna;
    
	private Usuario usuario;
    
    public TabelaColunaAtualizacaoCadastral(Integer id, String colunaValorAnterior, String colunaValorAtual, Short indicadorAutorizado, 
    		Date dataValidacao, Date ultimaAlteracao, TabelaAtualizacaoCadastral tabelaAtualizacaoCadastral, TabelaColuna tabelaColuna) {

		this.id = id;
		this.colunaValorAnterior = colunaValorAnterior;
		this.colunaValorAtual = colunaValorAtual;
		this.indicadorAutorizado = indicadorAutorizado;
		this.dataValidacao = dataValidacao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.tabelaAtualizacaoCadastral = tabelaAtualizacaoCadastral;
		this.tabelaColuna = tabelaColuna;
	}

	/** default constructor */
    public TabelaColunaAtualizacaoCadastral() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	
	public String getColunaValorAnterior() {
		return colunaValorAnterior;
	}

	public void setColunaValorAnterior(String colunaValorAnterior) {
		this.colunaValorAnterior = colunaValorAnterior;
	}

	public String getColunaValorAtual() {
		return colunaValorAtual;
	}

	public void setColunaValorAtual(String colunaValorAtual) {
		this.colunaValorAtual = colunaValorAtual;
	}

	public Date getDataValidacao() {
		return dataValidacao;
	}

	public void setDataValidacao(Date dataValidacao) {
		this.dataValidacao = dataValidacao;
	}

	public Short getIndicadorAutorizado() {
		return indicadorAutorizado;
	}

	public void setIndicadorAutorizado(Short indicadorAutorizado) {
		this.indicadorAutorizado = indicadorAutorizado;
	}

	public TabelaAtualizacaoCadastral getTabelaAtualizacaoCadastral() {
		return tabelaAtualizacaoCadastral;
	}

	public void setTabelaAtualizacaoCadastral(
			TabelaAtualizacaoCadastral tabelaAtualizacaoCadastral) {
		this.tabelaAtualizacaoCadastral = tabelaAtualizacaoCadastral;
	}

	public TabelaColuna getTabelaColuna() {
		return tabelaColuna;
	}

	public void setTabelaColuna(TabelaColuna tabelaColuna) {
		this.tabelaColuna = tabelaColuna;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
}