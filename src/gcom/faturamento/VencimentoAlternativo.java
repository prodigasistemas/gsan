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
package gcom.faturamento;

import gcom.cadastro.imovel.Imovel;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class VencimentoAlternativo extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_VENCIMENTO_ALTERNATIVO_INSERIR = 54; //Operacao.OPERACAO_INSERIR_VENCIMENTO_ALTERNATIVO
	public static final int ATRIBUTOS_VENCIMENTO_ALTERNATIVO_EXCLUIR = 55; //Operacao.OPERACAO_EXCLUIR_VENCIMENTO_ALTERNATIVO

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Date dataImplantacao;

	/**
	 * persistent field
	 */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_VENCIMENTO_ALTERNATIVO_INSERIR, ATRIBUTOS_VENCIMENTO_ALTERNATIVO_EXCLUIR})
    private Short dateVencimento;

	/**
	 * persistent field
	 */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_VENCIMENTO_ALTERNATIVO_EXCLUIR})
    private Date dateExclusao;

	/**
	 * persistent field
	 */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_VENCIMENTO_ALTERNATIVO_INSERIR, ATRIBUTOS_VENCIMENTO_ALTERNATIVO_EXCLUIR})
    private Date ultimaAlteracao;

    /** persistent field */
    private Imovel imovel;

    /** full constructor */
    public VencimentoAlternativo(Date dataImplantacao, Short dateVencimento, Date dateExclusao, Date ultimaAlteracao, Imovel imovel) {
        this.dataImplantacao = dataImplantacao;
        this.dateVencimento = dateVencimento;
        this.dateExclusao = dateExclusao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.imovel = imovel;
    }

    /** default constructor */
    public VencimentoAlternativo() {
    }

    /** minimal constructor */
    public VencimentoAlternativo(Imovel imovel) {
        this.imovel = imovel;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataImplantacao() {
        return this.dataImplantacao;
    }

    public void setDataImplantacao(Date dataImplantacao) {
        this.dataImplantacao = dataImplantacao;
    }

    public Short getDateVencimento() {
        return this.dateVencimento;
    }

    public void setDateVencimento(Short dateVencimento) {
        this.dateVencimento = dateVencimento;
    }

    public Date getDateExclusao() {
        return this.dateExclusao;
    }

    public void setDateExclusao(Date dateExclusao) {
        this.dateExclusao = dateExclusao;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Imovel getImovel() {
        return this.imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
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
	
	public Filtro retornaFiltro(){
		FiltroVencimentoAlternativo filtroVencimentoAlternativo = new FiltroVencimentoAlternativo();
		
		filtroVencimentoAlternativo.adicionarParametro(
				new ParametroSimples(FiltroVencimentoAlternativo.ID, this.getId()));
		filtroVencimentoAlternativo.adicionarCaminhoParaCarregamentoEntidade("imovel");
		return filtroVencimentoAlternativo; 
	}

}
