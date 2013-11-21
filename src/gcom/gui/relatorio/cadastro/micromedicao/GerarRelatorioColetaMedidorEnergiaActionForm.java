/*
* Copyright (C) 2007-2007 the GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
* Foundation, Inc., 59 Temple Place – Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
package gcom.gui.relatorio.cadastro.micromedicao;


import org.apache.struts.action.ActionForm;

/**
 * [UC0999] Gerar Relatório de Coleta de Medidor de Energia.
 * 
 * @author Hugo Leonardo
 *
 * @date 08/03/2010
 */

public class GerarRelatorioColetaMedidorEnergiaActionForm extends ActionForm {
	
private static final long serialVersionUID = 1L;
	
	private String faturamentoGrupo;
	
	private String localidadeInicial;
	private String nomeLocalidadeInicial;
	
	private String codigoSetorComercialInicial;
	private String setorComercialInicialDescricao;
	
	private String codigoSetorComercialFinal;
	private String setorComercialFinalDescricao;
	
	private String rotaInicial;
	private String sequencialRotaInicial;

	private String localidadeFinal;
	private String nomeLocalidadeFinal;
	
	private String rotaFinal;
	private String sequencialRotaFinal;
	
	public void reset(){
		
		this.localidadeInicial = null;
		this.nomeLocalidadeInicial = null;
		
		this.codigoSetorComercialInicial = null;
		this.setorComercialInicialDescricao = null;
		
		this.rotaInicial = null;
		this.sequencialRotaInicial = null;

		this.localidadeFinal = null;
		this.nomeLocalidadeFinal = null;
		
		this.codigoSetorComercialFinal = null;
		this.setorComercialFinalDescricao = null;

		this.rotaFinal = null;
		this.sequencialRotaFinal = null;
		
		this.faturamentoGrupo = null;
	}

	public String getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(String localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public String getNomeLocalidadeInicial() {
		return nomeLocalidadeInicial;
	}

	public void setNomeLocalidadeInicial(String nomeLocalidadeInicial) {
		this.nomeLocalidadeInicial = nomeLocalidadeInicial;
	}

	public String getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(String localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public String getNomeLocalidadeFinal() {
		return nomeLocalidadeFinal;
	}

	public void setNomeLocalidadeFinal(String nomeLocalidadeFinal) {
		this.nomeLocalidadeFinal = nomeLocalidadeFinal;
	}

	public String getRotaFinal() {
		return rotaFinal;
	}

	public void setRotaFinal(String rotaFinal) {
		this.rotaFinal = rotaFinal;
	}

	public String getRotaInicial() {
		return rotaInicial;
	}

	public void setRotaInicial(String rotaInicial) {
		this.rotaInicial = rotaInicial;
	}

	public String getSequencialRotaFinal() {
		return sequencialRotaFinal;
	}

	public void setSequencialRotaFinal(String sequencialRotaFinal) {
		this.sequencialRotaFinal = sequencialRotaFinal;
	}

	public String getSequencialRotaInicial() {
		return sequencialRotaInicial;
	}

	public void setSequencialRotaInicial(String sequencialRotaInicial) {
		this.sequencialRotaInicial = sequencialRotaInicial;
	}

	public String getFaturamentoGrupo() {
		return faturamentoGrupo;
	}

	public void setFaturamentoGrupo(String faturamentoGrupo) {
		this.faturamentoGrupo = faturamentoGrupo;
	}

	public String getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}

	public void setCodigoSetorComercialFinal(String codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}

	public String getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}

	public void setCodigoSetorComercialInicial(String codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}

	public String getSetorComercialFinalDescricao() {
		return setorComercialFinalDescricao;
	}

	public void setSetorComercialFinalDescricao(String setorComercialFinalDescricao) {
		this.setorComercialFinalDescricao = setorComercialFinalDescricao;
	}

	public String getSetorComercialInicialDescricao() {
		return setorComercialInicialDescricao;
	}

	public void setSetorComercialInicialDescricao(
			String setorComercialInicialDescricao) {
		this.setorComercialInicialDescricao = setorComercialInicialDescricao;
	}
	
}