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
package gcom.relatorio.cadastro.imovel;

import java.io.Serializable;
import java.util.Date;


/**
 * [UC1121] Gerar Relatório de Imóveis com Alteração de Inscrição Via Batch
 * 
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * do relatorio de Imóveis com Alteração de Inscrição Via Batch
 * 
 * @author Hugo Leonardo
 * @date 19/01/2011
 */
public class FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer localidadeInicial;
	private Integer localidadeFinal;
	private Integer setorComercialInicial;
	private Integer setorComercialFinal;
	private Integer quadraInicial;
	private Integer quadraFinal;
	private Integer loteInicial;
	private Integer loteFinal;
	private Integer subLoteInicial;
	private Integer subLoteFinal;
	private Integer escolhaRelatorio;
	private Date dataInicio;
	private Date dataFim;

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Integer getEscolhaRelatorio() {
		return escolhaRelatorio;
	}

	public void setEscolhaRelatorio(Integer escolhaRelatorio) {
		this.escolhaRelatorio = escolhaRelatorio;
	}

	public Integer getLocalidadeFinal() {
		return localidadeFinal;
	}

	public void setLocalidadeFinal(Integer localidadeFinal) {
		this.localidadeFinal = localidadeFinal;
	}

	public Integer getLocalidadeInicial() {
		return localidadeInicial;
	}

	public void setLocalidadeInicial(Integer localidadeInicial) {
		this.localidadeInicial = localidadeInicial;
	}

	public Integer getLoteFinal() {
		return loteFinal;
	}

	public void setLoteFinal(Integer loteFinal) {
		this.loteFinal = loteFinal;
	}

	public Integer getLoteInicial() {
		return loteInicial;
	}

	public void setLoteInicial(Integer loteInicial) {
		this.loteInicial = loteInicial;
	}

	public Integer getQuadraFinal() {
		return quadraFinal;
	}

	public void setQuadraFinal(Integer quadraFinal) {
		this.quadraFinal = quadraFinal;
	}

	public Integer getQuadraInicial() {
		return quadraInicial;
	}

	public void setQuadraInicial(Integer quadraInicial) {
		this.quadraInicial = quadraInicial;
	}

	public Integer getSetorComercialFinal() {
		return setorComercialFinal;
	}

	public void setSetorComercialFinal(Integer setorComercialFinal) {
		this.setorComercialFinal = setorComercialFinal;
	}

	public Integer getSetorComercialInicial() {
		return setorComercialInicial;
	}

	public void setSetorComercialInicial(Integer setorComercialInicial) {
		this.setorComercialInicial = setorComercialInicial;
	}

	public Integer getSubLoteFinal() {
		return subLoteFinal;
	}

	public void setSubLoteFinal(Integer subLoteFinal) {
		this.subLoteFinal = subLoteFinal;
	}

	public Integer getSubLoteInicial() {
		return subLoteInicial;
	}

	public void setSubLoteInicial(Integer subLoteInicial) {
		this.subLoteInicial = subLoteInicial;
	}
	
}