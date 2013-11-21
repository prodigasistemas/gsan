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
package gcom.gui.relatorio.atendimentopublico.ordemservico;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1163]  Gerar  Relatório de OS executadas por Prestadora de Serviço
 * 
 * @author Vivianne Sousa
 *
 * @date 12/04/2011
 */
public class GerarRelatorioOSExecutadasPrestadoraServicoActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String periodoEncerramentoInicial;
	private String periodoEncerramentoFinal;
	private String empresa;
	private String idGerencia;
	private String idUnidadeNegocio;
	private String codigoLocalidade;
	private String descricaoLocalidade;
	private String[] colecaoRegiao;
	private String[] colecaoMicrorregiao;
	private String[] colecaoMunicipio;
	private String opcaoRelatorio;
	
	public GerarRelatorioOSExecutadasPrestadoraServicoActionForm(String periodoEncerramentoInicial, String periodoEncerramentoFinal, String empresa, String idGerencia, String idUnidadeNegocio, String codigoLocalidade, String descricaoLocalidade, String[] colecaoRegiao, String[] colecaoMicrorregiao, String[] colecaoMunicipio, String opcaoRelatorio) {
		super();
		// TODO Auto-generated constructor stub
		this.periodoEncerramentoInicial = periodoEncerramentoInicial;
		this.periodoEncerramentoFinal = periodoEncerramentoFinal;
		this.empresa = empresa;
		this.idGerencia = idGerencia;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.codigoLocalidade = codigoLocalidade;
		this.descricaoLocalidade = descricaoLocalidade;
		this.colecaoRegiao = colecaoRegiao;
		this.colecaoMicrorregiao = colecaoMicrorregiao;
		this.colecaoMunicipio = colecaoMunicipio;
		this.opcaoRelatorio = opcaoRelatorio;
	}
	public GerarRelatorioOSExecutadasPrestadoraServicoActionForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCodigoLocalidade() {
		return codigoLocalidade;
	}
	public void setCodigoLocalidade(String codigoLocalidade) {
		this.codigoLocalidade = codigoLocalidade;
	}
	public String[] getColecaoMicrorregiao() {
		return colecaoMicrorregiao;
	}
	public void setColecaoMicrorregiao(String[] colecaoMicrorregiao) {
		this.colecaoMicrorregiao = colecaoMicrorregiao;
	}
	public String[] getColecaoMunicipio() {
		return colecaoMunicipio;
	}
	public void setColecaoMunicipio(String[] colecaoMunicipio) {
		this.colecaoMunicipio = colecaoMunicipio;
	}
	public String[] getColecaoRegiao() {
		return colecaoRegiao;
	}
	public void setColecaoRegiao(String[] colecaoRegiao) {
		this.colecaoRegiao = colecaoRegiao;
	}
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getIdGerencia() {
		return idGerencia;
	}
	public void setIdGerencia(String idGerencia) {
		this.idGerencia = idGerencia;
	}
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	public String getOpcaoRelatorio() {
		return opcaoRelatorio;
	}
	public void setOpcaoRelatorio(String opcaoRelatorio) {
		this.opcaoRelatorio = opcaoRelatorio;
	}
	public String getPeriodoEncerramentoFinal() {
		return periodoEncerramentoFinal;
	}
	public void setPeriodoEncerramentoFinal(String periodoEncerramentoFinal) {
		this.periodoEncerramentoFinal = periodoEncerramentoFinal;
	}
	public String getPeriodoEncerramentoInicial() {
		return periodoEncerramentoInicial;
	}
	public void setPeriodoEncerramentoInicial(String periodoEncerramentoInicial) {
		this.periodoEncerramentoInicial = periodoEncerramentoInicial;
	}
	
}