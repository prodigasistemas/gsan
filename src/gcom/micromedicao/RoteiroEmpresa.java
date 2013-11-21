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

import gcom.cadastro.empresa.Empresa;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class RoteiroEmpresa extends ObjetoTransacao{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Empresa empresa;

    /** persistent field */
    private gcom.micromedicao.Leiturista leiturista;

    /** full constructor */
    public RoteiroEmpresa(Integer id, Short indicadorUso, Date ultimaAlteracao, Empresa empresa, gcom.micromedicao.Leiturista leiturista) {
        this.id = id;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.empresa = empresa;
        this.leiturista = leiturista;
    }

    /** default constructor */
    public RoteiroEmpresa() {
    }

    /** minimal constructor */
    public RoteiroEmpresa(Integer id, Date ultimaAlteracao, Empresa empresa, gcom.micromedicao.Leiturista leiturista) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.empresa = empresa;
        this.leiturista = leiturista;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public gcom.micromedicao.Leiturista getLeiturista() {
        return this.leiturista;
    }

    public void setLeiturista(gcom.micromedicao.Leiturista leiturista) {
        this.leiturista = leiturista;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	@Override
	public Filtro retornaFiltro() {
		FiltroRoteiroEmpresa filtro = new FiltroRoteiroEmpresa();
		
		filtro. adicionarCaminhoParaCarregamentoEntidade("empresa");
		filtro. adicionarCaminhoParaCarregamentoEntidade("leiturista");
		filtro. adicionarCaminhoParaCarregamentoEntidade("leiturista.cliente");
		filtro. adicionarCaminhoParaCarregamentoEntidade("leiturista.funcionario");
		filtro. adicionarParametro(
				new ParametroSimples(FiltroRoteiroEmpresa.ID_ROTEIRO, this.getId()));
		return filtro; 
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	@Override
	public String[] retornarAtributosSelecionadosRegistro() {
		String[] atributos = {"empresa", "leiturista"};
		return atributos;
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String[] atributos = {"id", "empresa.descricao", "leiturista.cliente.nome", 
			"leiturista.funcionario.nome"};
		return atributos;
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String[] labels = {"Código do Roteiro Empresa", "Empresa", "Nome do Cliente", 
			"Nome do Funcionário"};
		return labels;
	}
}
