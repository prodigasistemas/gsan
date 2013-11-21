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
package gcom.relatorio.cadastro;


public class RelatorioResumoQtdeImoveisExcluidosTarifaSocialHelper {
	
	private String descricaoGerencia;
	private Integer idGerencia ;
	private String descricaoLocalidade ;
	private Integer idLocalidade ;
	private String descricaoUnidadeNegocio ;
	private Integer idUnidadeNegocio ;
    private Integer motivoExclusao;
    private Integer qtdeCartas;
    private Integer qtdeExcluidos;
    private String descricaoMotivoExclusao;
    
	public RelatorioResumoQtdeImoveisExcluidosTarifaSocialHelper() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public RelatorioResumoQtdeImoveisExcluidosTarifaSocialHelper(String descricaoGerencia, Integer idGerencia, String descricaoLocalidade, Integer idLocalidade, String descricaoUnidadeNegocio, Integer idUnidadeNegocio, Integer motivoExclusao, Integer qtdeCartas, Integer qtdeExcluidos, String descricaoMotivoExclusao) {
		super();
		// TODO Auto-generated constructor stub
		this.descricaoGerencia = descricaoGerencia;
		this.idGerencia = idGerencia;
		this.descricaoLocalidade = descricaoLocalidade;
		this.idLocalidade = idLocalidade;
		this.descricaoUnidadeNegocio = descricaoUnidadeNegocio;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.motivoExclusao = motivoExclusao;
		this.qtdeCartas = qtdeCartas;
		this.qtdeExcluidos = qtdeExcluidos;
		this.descricaoMotivoExclusao = descricaoMotivoExclusao;
	}



	public String getDescricaoGerencia() {
		return descricaoGerencia;
	}

	public void setDescricaoGerencia(String descricaoGerencia) {
		this.descricaoGerencia = descricaoGerencia;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getDescricaoUnidadeNegocio() {
		return descricaoUnidadeNegocio;
	}

	public void setDescricaoUnidadeNegocio(String descricaoUnidadeNegocio) {
		this.descricaoUnidadeNegocio = descricaoUnidadeNegocio;
	}

	public Integer getIdGerencia() {
		return idGerencia;
	}

	public void setIdGerencia(Integer idGerencia) {
		this.idGerencia = idGerencia;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public Integer getMotivoExclusao() {
		return motivoExclusao;
	}

	public void setMotivoExclusao(Integer motivoExclusao) {
		this.motivoExclusao = motivoExclusao;
	}

	public Integer getQtdeCartas() {
		return qtdeCartas;
	}

	public void setQtdeCartas(Integer qtdeCartas) {
		this.qtdeCartas = qtdeCartas;
	}

	public Integer getQtdeExcluidos() {
		return qtdeExcluidos;
	}

	public void setQtdeExcluidos(Integer qtdeExcluidos) {
		this.qtdeExcluidos = qtdeExcluidos;
	}

	public String getDescricaoMotivoExclusao() {
		return descricaoMotivoExclusao;
	}

	public void setDescricaoMotivoExclusao(String descricaoMotivoExclusao) {
		this.descricaoMotivoExclusao = descricaoMotivoExclusao;
	}
	
}
