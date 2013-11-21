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
package gcom.gui.micromedicao;

import gcom.micromedicao.ContratoEmpresaAditivo;
import gcom.micromedicao.ItemServicoContrato;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0xxx] Informar Valor de Item de Servico Por Contrato
 * 
 * @author Hugo Leonardo, Diogo Peixoto
 * @date 22/07/2010, 19/05/2011
 */

public class ExibirInformarItensContratoServicoActionForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String idEmpresa;
	
	private String idContrato;

	private String idNumeroContrato;

	private String dtInicioContrato;
	
	private String dtFimContrato;
	
	private String idItemContrato;
	
	private String valorItemContrato;

	private String valorGlobalContrato;
	
	private String percentualTaxaSucesso;
	
	private String dataInicioAditivo;
	
	private String dataFimAditivo;
	
	private String valorAditivoContrato;
	
	private String percentualTaxaSucessoAdivito;
	
	private String idContEmp;
	
	private Collection<ItemServicoContrato> colecaoItensContrato = new ArrayList<ItemServicoContrato>();

	private Collection<ContratoEmpresaAditivo> colecaoAditivo = new ArrayList<ContratoEmpresaAditivo>();
	
	private String observacao;

	private String idServicoTipo;

	private String descricaoServicoTipo;
	
	private String percentualServicosNaoExecutados;
	
	private String percentualMultaAplicar;
	
	private String quantidadeOrcadaItemContrato;
	
	private String valorOrcadoItemContrato;

	public void getReset(){
		this.idEmpresa = null;
		this.idContrato = null;
		this.idNumeroContrato = null;
		this.dtInicioContrato = null;
		this.dtFimContrato = null;
		this.idItemContrato = null;
		this.valorItemContrato = null;
	}
	
	public String getDtFimContrato() {
		return dtFimContrato;
	}

	public void setDtFimContrato(String dtFimContrato) {
		this.dtFimContrato = dtFimContrato;
	}

	public String getDtInicioContrato() {
		return dtInicioContrato;
	}

	public void setDtInicioContrato(String dtInicioContrato) {
		this.dtInicioContrato = dtInicioContrato;
	}

	public String getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(String idContrato) {
		this.idContrato = idContrato;
	}
	
	public String getIdNumeroContrato() {
		return idNumeroContrato;
	}

	public void setIdNumeroContrato(String idNumeroContrato) {
		this.idNumeroContrato = idNumeroContrato;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Collection<ItemServicoContrato> getColecaoItensContrato() {
		return colecaoItensContrato;
	}

	public void setColecaoItensContrato(
			Collection<ItemServicoContrato> colecaoItensContrato) {
		this.colecaoItensContrato = colecaoItensContrato;
	}

	public String getIdItemContrato() {
		return idItemContrato;
	}

	public void setIdItemContrato(String idItemContrato) {
		this.idItemContrato = idItemContrato;
	}

	public String getValorItemContrato() {
		return valorItemContrato;
	}

	public void setValorItemContrato(String valorItemContrato) {
		this.valorItemContrato = valorItemContrato;
	}

	public String getPercentualTaxaSucesso() {
		return percentualTaxaSucesso;
	}

	public void setPercentualTaxaSucesso(String percentualTaxaSucesso) {
		this.percentualTaxaSucesso = percentualTaxaSucesso;
	}

	public String getValorGlobalContrato() {
		return valorGlobalContrato;
	}

	public void setValorGlobalContrato(String valorGlobalContrato) {
		this.valorGlobalContrato = valorGlobalContrato;
	}

	public String getDataFimAditivo() {
		return dataFimAditivo;
	}

	public void setDataFimAditivo(String dataFimAditivo) {
		this.dataFimAditivo = dataFimAditivo;
	}

	public String getDataInicioAditivo() {
		return dataInicioAditivo;
	}

	public void setDataInicioAditivo(String dataInicioAditivo) {
		this.dataInicioAditivo = dataInicioAditivo;
	}

	public String getPercentualTaxaSucessoAditivo() {
		return percentualTaxaSucessoAdivito;
	}

	public void setPercentualTaxaSucessoAditivo(String percentualTaxaSucessoAdivito) {
		this.percentualTaxaSucessoAdivito = percentualTaxaSucessoAdivito;
	}

	public String getValorAditivoContrato() {
		return valorAditivoContrato;
	}

	public void setValorAditivoContrato(String valorAditivoContrato) {
		this.valorAditivoContrato = valorAditivoContrato;
	}

	public String getIdContEmp() {
		return idContEmp;
	}

	public void setIdContEmp(String idContEmp) {
		this.idContEmp = idContEmp;
	}

	public Collection<ContratoEmpresaAditivo> getColecaoAditivo() {
		return colecaoAditivo;
	}

	public void setColecaoAditivo(Collection<ContratoEmpresaAditivo> colecaoAditivo) {
		this.colecaoAditivo = colecaoAditivo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getDescricaoServicoTipo() {
		return descricaoServicoTipo;
	}

	public void setDescricaoServicoTipo(String descricaoServicoTipo) {
		this.descricaoServicoTipo = descricaoServicoTipo;
	}

	public String getIdServicoTipo() {
		return idServicoTipo;
	}

	public void setIdServicoTipo(String idServicoTipo) {
		this.idServicoTipo = idServicoTipo;
	}

	public String getPercentualTaxaSucessoAdivito() {
		return percentualTaxaSucessoAdivito;
	}

	public void setPercentualTaxaSucessoAdivito(String percentualTaxaSucessoAdivito) {
		this.percentualTaxaSucessoAdivito = percentualTaxaSucessoAdivito;
	}

	public String getPercentualServicosNaoExecutados() {
		return percentualServicosNaoExecutados;
	}

	public void setPercentualServicosNaoExecutados(
			String percentualServicosNaoExecutados) {
		this.percentualServicosNaoExecutados = percentualServicosNaoExecutados;
	}

	public String getPercentualMultaAplicar() {
		return percentualMultaAplicar;
	}

	public void setPercentualMultaAplicar(String percentualMultaAplicar) {
		this.percentualMultaAplicar = percentualMultaAplicar;
	}

	public String getQuantidadeOrcadaItemContrato() {
		return quantidadeOrcadaItemContrato;
	}

	public void setQuantidadeOrcadaItemContrato(String quantidadeOrcadaItemContrato) {
		this.quantidadeOrcadaItemContrato = quantidadeOrcadaItemContrato;
	}

	public String getValorOrcadoItemContrato() {
		return valorOrcadoItemContrato;
	}

	public void setValorOrcadoItemContrato(String valorOrcadoItemContrato) {
		this.valorOrcadoItemContrato = valorOrcadoItemContrato;
	}
}