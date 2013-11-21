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
* Yara Taciane de Souza
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
package gcom.gui.atendimentopublico.ordemservico;

import javax.servlet.ServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Hugo Leonardo
 * @created 14/05/2010
 */

public class FiltrarOrdemRepavimentacaoProcessoAceiteActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idUnidadeResponsavel;
	private String descricaoUnidadeResponsavel;
	private String situacaoAceite;
	private String situacaoAceiteDescricao;
	private String periodoAceiteServicoInicial;
	private String periodoAceiteServicoFinal;
	private String periodoRetornoServicoInicial;
	private String periodoRetornoServicoFinal;
	private String indicadorAtualizar;
	private String[] idRegistro;
	private String[] registrosHidden;
	
	private String ordensServicoSelecionadas;
	private String dataExecucao;	
	private String idPavimentoCalcadaRet;
	private String descricaoPavimentoCalcadaRet;
	private String idPavimentoRuaRet;	
	private String descricaoPavimentoRuaRet;
	private String areaPavimentoCalcadaRet;
	private String areaPavimentoRuaRet;
	private String escolhaRelatorio;
	private String manterPaginaAux;
	
	private String idOrdemServico;
	private String idOrdemServicoPavimento;
	private String dataAceite;	
    private String indicadorSituacaoAceite;
    private String idUnidadeOrganizacional;
	private String descricaoUnidadeOrganizacional;
    private String motivo;
    
    private String idMotivoRejeicao;
    private String periodoRejeicaoInicial;
	private String periodoRejeicaoFinal;
	
	public void reset(ActionMapping arg0, ServletRequest arg1) {
		// TODO Auto-generated method stub
		super.reset(arg0, arg1);
		this.idUnidadeResponsavel = "";
		this.descricaoUnidadeResponsavel=""; 
		this.situacaoAceite = "";
		this.periodoAceiteServicoInicial = "";
		this.periodoAceiteServicoFinal = "";
		this.periodoRetornoServicoInicial = "";
		this.periodoRetornoServicoFinal = "";	
		this.indicadorAtualizar = "";
		//this.idRegistro = "";
		
		this.dataExecucao="";	
		this.idPavimentoCalcadaRet="";
		this.descricaoPavimentoCalcadaRet="";
		this.idPavimentoRuaRet="";	
		this.descricaoPavimentoRuaRet="";
		
		this.areaPavimentoCalcadaRet ="";
		this.areaPavimentoRuaRet = "";
		
		this.idMotivoRejeicao = "";
		this.periodoRejeicaoInicial = "";
		this.periodoRejeicaoFinal = "";
	}

	/**
	 * @return Retorna o campo idUnidadeResponsavel.
	 */
	public String getIdUnidadeResponsavel() {
		return idUnidadeResponsavel;
	}

	/**
	 * @param idUnidadeResponsavel O idUnidadeResponsavel a ser setado.
	 */
	public void setIdUnidadeResponsavel(String idUnidadeResponsavel) {
		this.idUnidadeResponsavel = idUnidadeResponsavel;
	}

	/**
	 * @return Retorna o campo periodoRetornoServicoFinal.
	 */
	public String getPeriodoRetornoServicoFinal() {
		return periodoRetornoServicoFinal;
	}

	/**
	 * @param periodoRetornoServicoFinal O periodoRetornoServicoFinal a ser setado.
	 */
	public void setPeriodoRetornoServicoFinal(String periodoRetornoServicoFinal) {
		this.periodoRetornoServicoFinal = periodoRetornoServicoFinal;
	}

	/**
	 * @return Retorna o campo periodoRetornoServicoInicial.
	 */
	public String getPeriodoRetornoServicoInicial() {
		return periodoRetornoServicoInicial;
	}

	/**
	 * @param periodoRetornoServicoInicial O periodoRetornoServicoInicial a ser setado.
	 */
	public void setPeriodoRetornoServicoInicial(String periodoRetornoServicoInicial) {
		this.periodoRetornoServicoInicial = periodoRetornoServicoInicial;
	}

	/**
	 * @return Retorna o campo indicadorAtualizar.
	 */
	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}

	/**
	 * @param indicadorAtualizar O indicadorAtualizar a ser setado.
	 */
	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}

	/**
	 * @return Retorna o campo descricaoUnidadeResponsavel.
	 */
	public String getDescricaoUnidadeResponsavel() {
		return descricaoUnidadeResponsavel;
	}

	/**
	 * @param descricaoUnidadeResponsavel O descricaoUnidadeResponsavel a ser setado.
	 */
	public void setDescricaoUnidadeResponsavel(String descricaoUnidadeResponsavel) {
		this.descricaoUnidadeResponsavel = descricaoUnidadeResponsavel;
	}

	/**
	 * @return Retorna o campo idRegistro.
	 */
	public String[] getIdRegistro() {
		return idRegistro;
	}

	/**
	 * @param idRegistro O idRegistro a ser setado.
	 */
	public void setIdRegistro(String[] idRegistro) {
		this.idRegistro = idRegistro;
	}

	/**
	 * @return Retorna o campo dataExecucao.
	 */
	public String getDataExecucao() {
		return dataExecucao;
	}

	/**
	 * @param dataExecucao O dataExecucao a ser setado.
	 */
	public void setDataExecucao(String dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	/**
	 * @return Retorna o campo descricaoPavimentoCalcadaRet.
	 */
	public String getDescricaoPavimentoCalcadaRet() {
		return descricaoPavimentoCalcadaRet;
	}

	/**
	 * @param descricaoPavimentoCalcadaRet O descricaoPavimentoCalcadaRet a ser setado.
	 */
	public void setDescricaoPavimentoCalcadaRet(String descricaoPavimentoCalcadaRet) {
		this.descricaoPavimentoCalcadaRet = descricaoPavimentoCalcadaRet;
	}

	/**
	 * @return Retorna o campo descricaoPavimentoRuaRet.
	 */
	public String getDescricaoPavimentoRuaRet() {
		return descricaoPavimentoRuaRet;
	}

	/**
	 * @param descricaoPavimentoRuaRet O descricaoPavimentoRuaRet a ser setado.
	 */
	public void setDescricaoPavimentoRuaRet(String descricaoPavimentoRuaRet) {
		this.descricaoPavimentoRuaRet = descricaoPavimentoRuaRet;
	}

	/**
	 * @return Retorna o campo idPavimentoCalcadaRet.
	 */
	public String getIdPavimentoCalcadaRet() {
		return idPavimentoCalcadaRet;
	}

	/**
	 * @param idPavimentoCalcadaRet O idPavimentoCalcadaRet a ser setado.
	 */
	public void setIdPavimentoCalcadaRet(String idPavimentoCalcadaRet) {
		this.idPavimentoCalcadaRet = idPavimentoCalcadaRet;
	}

	/**
	 * @return Retorna o campo idPavimentoRuaRet.
	 */
	public String getIdPavimentoRuaRet() {
		return idPavimentoRuaRet;
	}

	/**
	 * @param idPavimentoRuaRet O idPavimentoRuaRet a ser setado.
	 */
	public void setIdPavimentoRuaRet(String idPavimentoRuaRet) {
		this.idPavimentoRuaRet = idPavimentoRuaRet;
	}

	/**
	 * @return Retorna o campo areaPavimentoCalcadaRet.
	 */
	public String getAreaPavimentoCalcadaRet() {
		return areaPavimentoCalcadaRet;
	}

	/**
	 * @param areaPavimentoCalcadaRet O areaPavimentoCalcadaRet a ser setado.
	 */
	public void setAreaPavimentoCalcadaRet(String areaPavimentoCalcadaRet) {
		this.areaPavimentoCalcadaRet = areaPavimentoCalcadaRet;
	}

	/**
	 * @return Retorna o campo areaPavimentoRuaRet.
	 */
	public String getAreaPavimentoRuaRet() {
		return areaPavimentoRuaRet;
	}

	/**
	 * @param areaPavimentoRuaRet O areaPavimentoRuaRet a ser setado.
	 */
	public void setAreaPavimentoRuaRet(String areaPavimentoRuaRet) {
		this.areaPavimentoRuaRet = areaPavimentoRuaRet;
	}

	/**
	 * @return Returns the escolhaRelatorio.
	 */
	public String getEscolhaRelatorio() {
		return escolhaRelatorio;
	}

	/**
	 * @param escolhaRelatorio The escolhaRelatorio to set.
	 */
	public void setEscolhaRelatorio(String escolhaRelatorio) {
		this.escolhaRelatorio = escolhaRelatorio;
	}

	/**
	 * @return Returns the manterPaginaAux.
	 */
	public String getManterPaginaAux() {
		return manterPaginaAux;
	}

	public String getSituacaoAceiteDescricao() {
		return situacaoAceiteDescricao;
	}

	public void setSituacaoAceiteDescricao(String situacaoAceiteDescricao) {
		this.situacaoAceiteDescricao = situacaoAceiteDescricao;
	}

	/**
	 * @param manterPaginaAux The manterPaginaAux to set.
	 */
	public void setManterPaginaAux(String manterPaginaAux) {
		this.manterPaginaAux = manterPaginaAux;
	}

	public String getPeriodoAceiteServicoFinal() {
		return periodoAceiteServicoFinal;
	}

	public void setPeriodoAceiteServicoFinal(String periodoAceiteServicoFinal) {
		this.periodoAceiteServicoFinal = periodoAceiteServicoFinal;
	}

	public String getPeriodoAceiteServicoInicial() {
		return periodoAceiteServicoInicial;
	}

	public void setPeriodoAceiteServicoInicial(String periodoAceiteServicoInicial) {
		this.periodoAceiteServicoInicial = periodoAceiteServicoInicial;
	}

	public String getOrdensServicoSelecionadas() {
		return ordensServicoSelecionadas;
	}

	public void setOrdensServicoSelecionadas(String ordensServicoSelecionadas) {
		this.ordensServicoSelecionadas = ordensServicoSelecionadas;
	}

	public String getSituacaoAceite() {
		return situacaoAceite;
	}

	public void setSituacaoAceite(String situacaoAceite) {
		this.situacaoAceite = situacaoAceite;
	}

	public String[] getRegistrosHidden() {
		return registrosHidden;
	}

	public void setRegistrosHidden(String[] registrosHidden) {
		this.registrosHidden = registrosHidden;
	}

	public String getDataAceite() {
		return dataAceite;
	}

	public void setDataAceite(String dataAceite) {
		this.dataAceite = dataAceite;
	}

	public String getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public String getIdOrdemServicoPavimento() {
		return idOrdemServicoPavimento;
	}

	public void setIdOrdemServicoPavimento(String idOrdemServicoPavimento) {
		this.idOrdemServicoPavimento = idOrdemServicoPavimento;
	}

	public String getIndicadorSituacaoAceite() {
		return indicadorSituacaoAceite;
	}

	public void setIndicadorSituacaoAceite(String indicadorSituacaoAceite) {
		this.indicadorSituacaoAceite = indicadorSituacaoAceite;
	}

	public String getIdUnidadeOrganizacional() {
		return idUnidadeOrganizacional;
	}

	public void setIdUnidadeOrganizacional(String idUnidadeOrganizacional) {
		this.idUnidadeOrganizacional = idUnidadeOrganizacional;
	}

	public String getDescricaoUnidadeOrganizacional() {
		return descricaoUnidadeOrganizacional;
	}

	public void setDescricaoUnidadeOrganizacional(
			String descricaoUnidadeOrganizacional) {
		this.descricaoUnidadeOrganizacional = descricaoUnidadeOrganizacional;
	}

	/**
	 * @return Returns the motivo.
	 */
	public String getMotivo() {
		return motivo;
	}

	/**
	 * @param motivo The motivo to set.
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getIdMotivoRejeicao() {
		return idMotivoRejeicao;
	}

	public void setIdMotivoRejeicao(String idMotivoRejeicao) {
		this.idMotivoRejeicao = idMotivoRejeicao;
	}

	public String getPeriodoRejeicaoFinal() {
		return periodoRejeicaoFinal;
	}

	public void setPeriodoRejeicaoFinal(String periodoRejeicaoFinal) {
		this.periodoRejeicaoFinal = periodoRejeicaoFinal;
	}

	public String getPeriodoRejeicaoInicial() {
		return periodoRejeicaoInicial;
	}

	public void setPeriodoRejeicaoInicial(String periodoRejeicaoInicial) {
		this.periodoRejeicaoInicial = periodoRejeicaoInicial;
	}
	
}

