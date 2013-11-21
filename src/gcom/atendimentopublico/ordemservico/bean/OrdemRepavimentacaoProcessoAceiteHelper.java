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
package gcom.atendimentopublico.ordemservico.bean;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoPavimento;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;

import java.math.BigDecimal;
import java.util.Date;


/**
 * [UC1019] - Filtrar Ordens de Repavimentacao em Processo de Aceite;
 * 
 * Classe facilitadora para o retorno do filtro a ser usado no manter.
 * 
 * @author Hugo Leonardo
 * @date 14/05/2010
 */
public class OrdemRepavimentacaoProcessoAceiteHelper {

    private Integer id;

    private BigDecimal areaPavimentoRua;

    private BigDecimal areaPavimentoCalcada;

    private BigDecimal areaPavimentoRuaRetorno;

    private BigDecimal areaPavimentoCalcadaRetorno;

    private Date dataGeracao;
    
    private OrdemServico ordemServico; 

    private PavimentoRua pavimentoRua;
    
    private PavimentoCalcada pavimentoCalcada;
    
    private PavimentoRua pavimentoRuaRetorno;
    
    private PavimentoCalcada pavimentoCalcadaRetorno;
    
    private Integer idUnidadeResponsavel;
    
    private Integer situacaoAceite;
    
    private String periodoAceiteServicoInicial;
    
	private String periodoAceiteServicoFinal;
	
	private String periodoRetornoServicoInicial;
	
	private String periodoRetornoServicoFinal;
	
	private String endereco;
	
	private Integer idUnidadeOrganizacional;

	
	/**
	 * @return Retorna o campo areaPavimentoCalcada.
	 */
	public BigDecimal getAreaPavimentoCalcada() {
		return areaPavimentoCalcada;
	}

	/**
	 * @param areaPavimentoCalcada O areaPavimentoCalcada a ser setado.
	 */
	public void setAreaPavimentoCalcada(BigDecimal areaPavimentoCalcada) {
		this.areaPavimentoCalcada = areaPavimentoCalcada;
	}

	/**
	 * @return Retorna o campo areaPavimentoCalcadaRetorno.
	 */
	public BigDecimal getAreaPavimentoCalcadaRetorno() {
		return areaPavimentoCalcadaRetorno;
	}

	/**
	 * @param areaPavimentoCalcadaRetorno O areaPavimentoCalcadaRetorno a ser setado.
	 */
	public void setAreaPavimentoCalcadaRetorno(
			BigDecimal areaPavimentoCalcadaRetorno) {
		this.areaPavimentoCalcadaRetorno = areaPavimentoCalcadaRetorno;
	}

	/**
	 * @return Retorna o campo areaPavimentoRua.
	 */
	public BigDecimal getAreaPavimentoRua() {
		return areaPavimentoRua;
	}

	/**
	 * @param areaPavimentoRua O areaPavimentoRua a ser setado.
	 */
	public void setAreaPavimentoRua(BigDecimal areaPavimentoRua) {
		this.areaPavimentoRua = areaPavimentoRua;
	}

	/**
	 * @return Retorna o campo areaPavimentoRuaRetorno.
	 */
	public BigDecimal getAreaPavimentoRuaRetorno() {
		return areaPavimentoRuaRetorno;
	}

	/**
	 * @param areaPavimentoRuaRetorno O areaPavimentoRuaRetorno a ser setado.
	 */
	public void setAreaPavimentoRuaRetorno(BigDecimal areaPavimentoRuaRetorno) {
		this.areaPavimentoRuaRetorno = areaPavimentoRuaRetorno;
	}

	/**
	 * @return Retorna o campo dataGeracao.
	 */
	public Date getDataGeracao() {
		return dataGeracao;
	}

	/**
	 * @param dataGeracao O dataGeracao a ser setado.
	 */
	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo ordemServico.
	 */
	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	/**
	 * @param ordemServico O ordemServico a ser setado.
	 */
	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	/**
	 * @return Retorna o campo pavimentoCalcada.
	 */
	public PavimentoCalcada getPavimentoCalcada() {
		return pavimentoCalcada;
	}

	/**
	 * @param pavimentoCalcada O pavimentoCalcada a ser setado.
	 */
	public void setPavimentoCalcada(PavimentoCalcada pavimentoCalcada) {
		this.pavimentoCalcada = pavimentoCalcada;
	}

	/**
	 * @return Retorna o campo pavimentoCalcadaRetorno.
	 */
	public PavimentoCalcada getPavimentoCalcadaRetorno() {
		return pavimentoCalcadaRetorno;
	}

	/**
	 * @param pavimentoCalcadaRetorno O pavimentoCalcadaRetorno a ser setado.
	 */
	public void setPavimentoCalcadaRetorno(PavimentoCalcada pavimentoCalcadaRetorno) {
		this.pavimentoCalcadaRetorno = pavimentoCalcadaRetorno;
	}

	/**
	 * @return Retorna o campo pavimentoRua.
	 */
	public PavimentoRua getPavimentoRua() {
		return pavimentoRua;
	}

	/**
	 * @param pavimentoRua O pavimentoRua a ser setado.
	 */
	public void setPavimentoRua(PavimentoRua pavimentoRua) {
		this.pavimentoRua = pavimentoRua;
	}

	/**
	 * @return Retorna o campo pavimentoRuaRetorno.
	 */
	public PavimentoRua getPavimentoRuaRetorno() {
		return pavimentoRuaRetorno;
	}

	/**
	 * @param pavimentoRuaRetorno O pavimentoRuaRetorno a ser setado.
	 */
	public void setPavimentoRuaRetorno(PavimentoRua pavimentoRuaRetorno) {
		this.pavimentoRuaRetorno = pavimentoRuaRetorno;
	}

	/**
	 * @return Retorna o campo idUnidadeResponsavel.
	 */
	public Integer getIdUnidadeResponsavel() {
		return idUnidadeResponsavel;
	}

	/**
	 * @param idUnidadeResponsavel O idUnidadeResponsavel a ser setado.
	 */
	public void setIdUnidadeResponsavel(Integer idUnidadeResponsavel) {
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
	 * @return Retorna o campo endereco.
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco O endereco a ser setado.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
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

	public Integer getSituacaoAceite() {
		return situacaoAceite;
	}

	public void setSituacaoAceite(Integer situacaoAceite) {
		this.situacaoAceite = situacaoAceite;
	}
	

	public Integer getIdUnidadeOrganizacional() {
		return idUnidadeOrganizacional;
	}

	public void setIdUnidadeOrganizacional(Integer idUnidadeOrganizacional) {
		this.idUnidadeOrganizacional = idUnidadeOrganizacional;
	}

	public OSPavimentoHelper getOsPavimentoHelper(OrdemServicoPavimento ordemServicoPavimento){
    	
    	OSPavimentoHelper osPavimentoHelper = new OSPavimentoHelper();
    	
    	osPavimentoHelper.setAreaPavimentoCalcada(ordemServicoPavimento.getAreaPavimentoCalcada());
    	osPavimentoHelper.setAreaPavimentoCalcadaRetorno(ordemServicoPavimento.getAreaPavimentoCalcadaRetorno());
    	osPavimentoHelper.setAreaPavimentoRua(ordemServicoPavimento.getAreaPavimentoRua());
    	osPavimentoHelper.setAreaPavimentoRuaRetorno(ordemServicoPavimento.getAreaPavimentoRuaRetorno());
    	osPavimentoHelper.setDataGeracao(ordemServicoPavimento.getDataGeracao());
    	osPavimentoHelper.setId(ordemServicoPavimento.getId());    
    	osPavimentoHelper.setOrdemServico(ordemServicoPavimento.getOrdemServico());
    	osPavimentoHelper.setPavimentoCalcada(ordemServicoPavimento.getPavimentoCalcada());
    	osPavimentoHelper.setPavimentoCalcadaRetorno(ordemServicoPavimento.getPavimentoCalcadaRetorno());
    	osPavimentoHelper.setPavimentoRua(ordemServicoPavimento.getPavimentoRua());
    	osPavimentoHelper.setPavimentoRuaRetorno(ordemServicoPavimento.getPavimentoRuaRetorno());
//    	osPavimentoHelper.setPeriodoEncerramentoOsInicial(ordemServicoPavimento.getPeriodoEncerramentoOsInicial());
//    	osPavimentoHelper.setPeriodoEncerramentoOsFinal(ordemServicoPavimento.getPeriodoEncerramentoOsFinal());
//    	osPavimentoHelper.setPeriodoRetornoServicoInicial(ordemServicoPavimento.getPeriodoRetornoServicoInicial());
//    	osPavimentoHelper.setPeriodoRetornoServicoFinal(ordemServicoPavimento.getPeriodoRetornoServicoFinal());
    	
    	return osPavimentoHelper;
    }
   
}
