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
package gcom.cobranca.bean;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;



/**
 * [UC 0653] Pesquisar Comando Negativação
 * 
 * @author Kássia Albuquerque
 * @date 14/11/2007
 */

public class ParametrosComandoNegativacaoHelper {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idNagativacaoCriterio;
	private Date ultimaAlteracaoNegComando;
	private Integer idNegativador;
	
	// DADOS GERAIS
    private String negativador;
    private Integer qtdInclusoes; 
    private BigDecimal valorTotalDebito;
    private Integer qtdItensIncluidos;
    private String tituloComando;
    private String descricaoSolicitacao;
    private Short simularNegativacao;
    private Date dataExecucao;
    private Integer idUsuario;
    private String usuarioResponsavel;
    private Integer qtdMaxInclusoes;
    private Collection colecaoTitularNegativacao;
    private Integer idComandoNegativacaoSimulado;
    private Short indicadorOrgaoPublico;
    
    //DADOS DO DEBITO
    
    private Integer referenciaInicial;
    private Integer referenciaFinal; 
    private Date vencimentoInicial;
    private Date vencimentoFinal;
    private BigDecimal valoMinimoDebito;
    private BigDecimal valoMaximoDebito;
    private Integer qtdMinimaContas;
    private Integer qtdMaximaContas;
    private Short indicadorContaRevisao;
    private Short indicadorGuiaPagamento;
    private Short indicadorParcelamentoAtraso;
    private Integer numDiasAtrasoParcelamento;
    private Short indicadorCartaParcelamentoAtraso;
    private Integer numDiasAtrasoAposRecCarta;
	private Short indicadorContaNomeCliente;
   
	//DADOS DO IMOVEL
    
    private  Integer idCliente;
    private  String nomeCliente;
    private String tipoRelClie;
    private Short indicadorEspCobranca;
    private Short indicadorSitCobranca;
    private Collection colecaoSitLigacaoAgua;
    private Collection colecaoSitLigacaoEsgoto;    
    private Collection colecaoSubcategoria;
    private Collection colecaoPerfilImovel;
    private Collection colecaoTipoCliente;
	private Short indicadorBaixaRenda;
	
    //DADOS DA LOCALIZAÇÃO
    
    private Collection colecaoGrupoCobranca;
    private Collection colecaoGerenciaRegional;
    private Collection colecaoUnidadeNegocio;
    private Collection colecaoEloPolo;
    private String locInicial;
    private String locFinal;
    private String setComInicial;
    private String setComFinal;
    private Integer idLocInicial;
    private Integer idLocFinal;
    private Integer codSetComInicial;
    private Integer codSetComFinal;
    
    //DADOS DA EXCLUSÃO
    
    private Collection colecaoMotivoRetorno;
    private Integer quantidadeDias;
	
	public ParametrosComandoNegativacaoHelper() {
	}

	public ParametrosComandoNegativacaoHelper(
			 	String negativador,
			    Integer qtdInclusoes, 
			    BigDecimal valorTotalDebito,
			    Integer qtdItensIncluidos,
			    String tituloComando,
			    String descricaoSolicitacao,
			    Short simularNegativacao,
			    Date dataExecucao,
			    String usuarioResponsavel,
			    Integer qtdMaxInclusoes,
			    Collection colecaoTitularNegativacao,
			    Integer referenciaInicial,
			    Integer referenciaFinal, 
			    Date vencimentoInicial,
			    Date vencimentoFinal,
			    BigDecimal valoMinimoDebito,
			    BigDecimal valoMaximoDebito,
			    Integer qtdMinimaContas,
			    Integer qtdMaximaContas,
			    Short indicadorContaRevisao,
			    Short indicadorGuiaPagamento,
			    Short indicadorParcelamentoAtraso,
			    Integer numDiasAtrasoParcelamento,
			    Short indicadorCartaParcelamentoAtraso,
			    Integer numDiasAtrasoAposRecCarta,
			    Integer idCliente,
			    String nomeCliente,
			    String tipoRelClie,
			    Short indicadorEspCobranca,
			    Short indicadorSitCobranca,
			    Collection colecaoSubcategoria,
			    Collection colecaoPerfilImovel,
			    Collection colecaoTipoCliente,
			    Collection colecaoGrupoCobranca,
			    Collection colecaoGerenciaRegional,
			    Collection colecaoUnidadeNegocio,
			    Collection colecaoEloPolo,
			    String locInicial,
			    String locFinal,
			    String setComInicial,
			    String setComFinal
			    
			) {
		
		   	this.negativador=negativador;
		    this.qtdInclusoes= qtdInclusoes;
		    this.valorTotalDebito=valorTotalDebito;
		    this.qtdItensIncluidos=qtdItensIncluidos;
		    this.tituloComando=tituloComando;
		    this.descricaoSolicitacao=descricaoSolicitacao;
		    this.simularNegativacao=simularNegativacao;
		    this.dataExecucao=dataExecucao;
		    this.usuarioResponsavel=usuarioResponsavel;
		    this.qtdMaxInclusoes=qtdMaxInclusoes;
		    this.colecaoTitularNegativacao = colecaoTitularNegativacao;
			this.referenciaInicial=referenciaInicial;
		    this.referenciaFinal=referenciaFinal;
		    this.vencimentoInicial=vencimentoInicial;
		    this.vencimentoFinal=vencimentoFinal;
		    this.valoMinimoDebito=valoMinimoDebito;
		    this.valoMaximoDebito=valoMaximoDebito;
		    this.qtdMinimaContas=qtdMinimaContas;
		    this.qtdMaximaContas=qtdMaximaContas;
		    this.indicadorContaRevisao=indicadorContaRevisao;
		    this.indicadorGuiaPagamento=indicadorGuiaPagamento;
		    this.indicadorParcelamentoAtraso=indicadorParcelamentoAtraso;
		    this.numDiasAtrasoParcelamento=numDiasAtrasoParcelamento;
		    this.indicadorCartaParcelamentoAtraso=indicadorCartaParcelamentoAtraso;
		    this.numDiasAtrasoAposRecCarta= numDiasAtrasoAposRecCarta;
		    this.idCliente= idCliente;  
		    this.nomeCliente=nomeCliente;
		    this.tipoRelClie=tipoRelClie;
		    this.indicadorEspCobranca=indicadorEspCobranca;
		    this.indicadorSitCobranca=indicadorSitCobranca;
		    this.colecaoSubcategoria=colecaoSubcategoria;
		    this.colecaoPerfilImovel=colecaoPerfilImovel;
		    this.colecaoTipoCliente=colecaoTipoCliente;
		    this.colecaoGrupoCobranca=colecaoGrupoCobranca;
		    this.colecaoGerenciaRegional=colecaoGerenciaRegional;
		    this.colecaoUnidadeNegocio=colecaoUnidadeNegocio;
		    this.colecaoEloPolo=colecaoEloPolo;
		    this.locInicial=locInicial;
		    this.locFinal=locFinal;
		    this.setComInicial=setComInicial;
		    this.setComFinal=setComFinal;
			      
		
	}

	/**
	 * @return Retorna o campo dataExecucao.
	 */
	public Date getDataExecucao() {
		return dataExecucao;
	}

	/**
	 * @param dataExecucao O dataExecucao a ser setado.
	 */
	public void setDataExecucao(Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	/**
	 * @return Retorna o campo descricaoSolicitacao.
	 */
	public String getDescricaoSolicitacao() {
		return descricaoSolicitacao;
	}

	/**
	 * @param descricaoSolicitacao O descricaoSolicitacao a ser setado.
	 */
	public void setDescricaoSolicitacao(String descricaoSolicitacao) {
		this.descricaoSolicitacao = descricaoSolicitacao;
	}

	/**
	 * @return Retorna o campo indicadorCartaParcelamentoAtraso.
	 */
	public Short getIndicadorCartaParcelamentoAtraso() {
		return indicadorCartaParcelamentoAtraso;
	}

	/**
	 * @param indicadorCartaParcelamentoAtraso O indicadorCartaParcelamentoAtraso a ser setado.
	 */
	public void setIndicadorCartaParcelamentoAtraso(
			Short indicadorCartaParcelamentoAtraso) {
		this.indicadorCartaParcelamentoAtraso = indicadorCartaParcelamentoAtraso;
	}

	/**
	 * @return Retorna o campo indicadorContaRevisao.
	 */
	public Short getIndicadorContaRevisao() {
		return indicadorContaRevisao;
	}

	/**
	 * @param indicadorContaRevisao O indicadorContaRevisao a ser setado.
	 */
	public void setIndicadorContaRevisao(Short indicadorContaRevisao) {
		this.indicadorContaRevisao = indicadorContaRevisao;
	}

	/**
	 * @return Retorna o campo indicadorEspCobranca.
	 */
	public Short getIndicadorEspCobranca() {
		return indicadorEspCobranca;
	}

	/**
	 * @param indicadorEspCobranca O indicadorEspCobranca a ser setado.
	 */
	public void setIndicadorEspCobranca(Short indicadorEspCobranca) {
		this.indicadorEspCobranca = indicadorEspCobranca;
	}

	/**
	 * @return Retorna o campo indicadorGuiaPagamento.
	 */
	public Short getIndicadorGuiaPagamento() {
		return indicadorGuiaPagamento;
	}

	/**
	 * @param indicadorGuiaPagamento O indicadorGuiaPagamento a ser setado.
	 */
	public void setIndicadorGuiaPagamento(Short indicadorGuiaPagamento) {
		this.indicadorGuiaPagamento = indicadorGuiaPagamento;
	}

	/**
	 * @return Retorna o campo indicadorParcelamentoAtraso.
	 */
	public Short getIndicadorParcelamentoAtraso() {
		return indicadorParcelamentoAtraso;
	}

	/**
	 * @param indicadorParcelamentoAtraso O indicadorParcelamentoAtraso a ser setado.
	 */
	public void setIndicadorParcelamentoAtraso(Short indicadorParcelamentoAtraso) {
		this.indicadorParcelamentoAtraso = indicadorParcelamentoAtraso;
	}

	/**
	 * @return Retorna o campo indicadorSitCobranca.
	 */
	public Short getIndicadorSitCobranca() {
		return indicadorSitCobranca;
	}

	/**
	 * @param indicadorSitCobranca O indicadorSitCobranca a ser setado.
	 */
	public void setIndicadorSitCobranca(Short indicadorSitCobranca) {
		this.indicadorSitCobranca = indicadorSitCobranca;
	}

	/**
	 * @return Retorna o campo locFinal.
	 */
	public String getLocFinal() {
		return locFinal;
	}

	/**
	 * @param locFinal O locFinal a ser setado.
	 */
	public void setLocFinal(String locFinal) {
		this.locFinal = locFinal;
	}

	/**
	 * @return Retorna o campo locInicial.
	 */
	public String getLocInicial() {
		return locInicial;
	}

	/**
	 * @param locInicial O locInicial a ser setado.
	 */
	public void setLocInicial(String locInicial) {
		this.locInicial = locInicial;
	}

	/**
	 * @return Retorna o campo negativador.
	 */
	public String getNegativador() {
		return negativador;
	}

	/**
	 * @param negativador O negativador a ser setado.
	 */
	public void setNegativador(String negativador) {
		this.negativador = negativador;
	}

	/**
	 * @return Retorna o campo nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * @param nomeCliente O nomeCliente a ser setado.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * @return Retorna o campo numDiasAtrasoAposRecCarta.
	 */
	public Integer getNumDiasAtrasoAposRecCarta() {
		return numDiasAtrasoAposRecCarta;
	}

	/**
	 * @param numDiasAtrasoAposRecCarta O numDiasAtrasoAposRecCarta a ser setado.
	 */
	public void setNumDiasAtrasoAposRecCarta(Integer numDiasAtrasoAposRecCarta) {
		this.numDiasAtrasoAposRecCarta = numDiasAtrasoAposRecCarta;
	}

	/**
	 * @return Retorna o campo numDiasAtrasoParcelamento.
	 */
	public Integer getNumDiasAtrasoParcelamento() {
		return numDiasAtrasoParcelamento;
	}

	/**
	 * @param numDiasAtrasoParcelamento O numDiasAtrasoParcelamento a ser setado.
	 */
	public void setNumDiasAtrasoParcelamento(Integer numDiasAtrasoParcelamento) {
		this.numDiasAtrasoParcelamento = numDiasAtrasoParcelamento;
	}

	/**
	 * @return Retorna o campo qtdInclusoes.
	 */
	public Integer getQtdInclusoes() {
		return qtdInclusoes;
	}

	/**
	 * @param qtdInclusoes O qtdInclusoes a ser setado.
	 */
	public void setQtdInclusoes(Integer qtdInclusoes) {
		this.qtdInclusoes = qtdInclusoes;
	}

	/**
	 * @return Retorna o campo qtdItensIncluidos.
	 */
	public Integer getQtdItensIncluidos() {
		return qtdItensIncluidos;
	}

	/**
	 * @param qtdItensIncluidos O qtdItensIncluidos a ser setado.
	 */
	public void setQtdItensIncluidos(Integer qtdItensIncluidos) {
		this.qtdItensIncluidos = qtdItensIncluidos;
	}

	/**
	 * @return Retorna o campo qtdMaximaContas.
	 */
	public Integer getQtdMaximaContas() {
		return qtdMaximaContas;
	}

	/**
	 * @param qtdMaximaContas O qtdMaximaContas a ser setado.
	 */
	public void setQtdMaximaContas(Integer qtdMaximaContas) {
		this.qtdMaximaContas = qtdMaximaContas;
	}

	/**
	 * @return Retorna o campo qtdMaxInclusoes.
	 */
	public Integer getQtdMaxInclusoes() {
		return qtdMaxInclusoes;
	}

	/**
	 * @param qtdMaxInclusoes O qtdMaxInclusoes a ser setado.
	 */
	public void setQtdMaxInclusoes(Integer qtdMaxInclusoes) {
		this.qtdMaxInclusoes = qtdMaxInclusoes;
	}

	/**
	 * @return Retorna o campo qtdMinimaContas.
	 */
	public Integer getQtdMinimaContas() {
		return qtdMinimaContas;
	}

	/**
	 * @param qtdMinimaContas O qtdMinimaContas a ser setado.
	 */
	public void setQtdMinimaContas(Integer qtdMinimaContas) {
		this.qtdMinimaContas = qtdMinimaContas;
	}

	/**
	 * @return Retorna o campo referenciaFinal.
	 */
	public Integer getReferenciaFinal() {
		return referenciaFinal;
	}

	/**
	 * @param referenciaFinal O referenciaFinal a ser setado.
	 */
	public void setReferenciaFinal(Integer referenciaFinal) {
		this.referenciaFinal = referenciaFinal;
	}

	/**
	 * @return Retorna o campo referenciaInicial.
	 */
	public Integer getReferenciaInicial() {
		return referenciaInicial;
	}

	/**
	 * @param referenciaInicial O referenciaInicial a ser setado.
	 */
	public void setReferenciaInicial(Integer referenciaInicial) {
		this.referenciaInicial = referenciaInicial;
	}

	/**
	 * @return Retorna o campo setComFinal.
	 */
	public String getSetComFinal() {
		return setComFinal;
	}

	/**
	 * @param setComFinal O setComFinal a ser setado.
	 */
	public void setSetComFinal(String setComFinal) {
		this.setComFinal = setComFinal;
	}

	/**
	 * @return Retorna o campo setComInicial.
	 */
	public String getSetComInicial() {
		return setComInicial;
	}

	/**
	 * @param setComInicial O setComInicial a ser setado.
	 */
	public void setSetComInicial(String setComInicial) {
		this.setComInicial = setComInicial;
	}

	/**
	 * @return Retorna o campo simularNegativacao.
	 */
	public Short getSimularNegativacao() {
		return simularNegativacao;
	}

	/**
	 * @param simularNegativacao O simularNegativacao a ser setado.
	 */
	public void setSimularNegativacao(Short simularNegativacao) {
		this.simularNegativacao = simularNegativacao;
	}

	/**
	 * @return Retorna o campo tipoRelClie.
	 */
	public String getTipoRelClie() {
		return tipoRelClie;
	}

	/**
	 * @param tipoRelClie O tipoRelClie a ser setado.
	 */
	public void setTipoRelClie(String tipoRelClie) {
		this.tipoRelClie = tipoRelClie;
	}

	/**
	 * @return Retorna o campo tituloComando.
	 */
	public String getTituloComando() {
		return tituloComando;
	}

	/**
	 * @param tituloComando O tituloComando a ser setado.
	 */
	public void setTituloComando(String tituloComando) {
		this.tituloComando = tituloComando;
	}

	/**
	 * @return Retorna o campo usuarioResponsavel.
	 */
	public String getUsuarioResponsavel() {
		return usuarioResponsavel;
	}

	/**
	 * @param usuarioResponsavel O usuarioResponsavel a ser setado.
	 */
	public void setUsuarioResponsavel(String usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}

	/**
	 * @return Retorna o campo valoMaximoDebito.
	 */
	public BigDecimal getValoMaximoDebito() {
		return valoMaximoDebito;
	}

	/**
	 * @param valoMaximoDebito O valoMaximoDebito a ser setado.
	 */
	public void setValoMaximoDebito(BigDecimal valoMaximoDebito) {
		this.valoMaximoDebito = valoMaximoDebito;
	}

	/**
	 * @return Retorna o campo valoMinimoDebito.
	 */
	public BigDecimal getValoMinimoDebito() {
		return valoMinimoDebito;
	}

	/**
	 * @param valoMinimoDebito O valoMinimoDebito a ser setado.
	 */
	public void setValoMinimoDebito(BigDecimal valoMinimoDebito) {
		this.valoMinimoDebito = valoMinimoDebito;
	}

	/**
	 * @return Retorna o campo valorTotalDebito.
	 */
	public BigDecimal getValorTotalDebito() {
		return valorTotalDebito;
	}

	/**
	 * @param valorTotalDebito O valorTotalDebito a ser setado.
	 */
	public void setValorTotalDebito(BigDecimal valorTotalDebito) {
		this.valorTotalDebito = valorTotalDebito;
	}

	/**
	 * @return Retorna o campo vencimentoFinal.
	 */
	public Date getVencimentoFinal() {
		return vencimentoFinal;
	}

	/**
	 * @param vencimentoFinal O vencimentoFinal a ser setado.
	 */
	public void setVencimentoFinal(Date vencimentoFinal) {
		this.vencimentoFinal = vencimentoFinal;
	}

	/**
	 * @return Retorna o campo vencimentoInicial.
	 */
	public Date getVencimentoInicial() {
		return vencimentoInicial;
	}

	/**
	 * @param vencimentoInicial O vencimentoInicial a ser setado.
	 */
	public void setVencimentoInicial(Date vencimentoInicial) {
		this.vencimentoInicial = vencimentoInicial;
	}

	/**
	 * @return Retorna o campo idCliente.
	 */
	public Integer getIdCliente() {
		return idCliente;
	}

	/**
	 * @param idCliente O idCliente a ser setado.
	 */
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * @return Retorna o campo colecaoTitularNegativacao.
	 */
	public Collection getColecaoTitularNegativacao() {
		return colecaoTitularNegativacao;
	}

	/**
	 * @param colecaoTitularNegativacao O colecaoTitularNegativacao a ser setado.
	 */
	public void setColecaoTitularNegativacao(Collection colecaoTitularNegativacao) {
		this.colecaoTitularNegativacao = colecaoTitularNegativacao;
	}

	/**
	 * @return Retorna o campo colecaoEloPolo.
	 */
	public Collection getColecaoEloPolo() {
		return colecaoEloPolo;
	}

	/**
	 * @param colecaoEloPolo O colecaoEloPolo a ser setado.
	 */
	public void setColecaoEloPolo(Collection colecaoEloPolo) {
		this.colecaoEloPolo = colecaoEloPolo;
	}

	/**
	 * @return Retorna o campo colecaoGerenciaRegional.
	 */
	public Collection getColecaoGerenciaRegional() {
		return colecaoGerenciaRegional;
	}

	/**
	 * @param colecaoGerenciaRegional O colecaoGerenciaRegional a ser setado.
	 */
	public void setColecaoGerenciaRegional(Collection colecaoGerenciaRegional) {
		this.colecaoGerenciaRegional = colecaoGerenciaRegional;
	}

	/**
	 * @return Retorna o campo colecaoGrupoCobranca.
	 */
	public Collection getColecaoGrupoCobranca() {
		return colecaoGrupoCobranca;
	}

	/**
	 * @param colecaoGrupoCobranca O colecaoGrupoCobranca a ser setado.
	 */
	public void setColecaoGrupoCobranca(Collection colecaoGrupoCobranca) {
		this.colecaoGrupoCobranca = colecaoGrupoCobranca;
	}

	/**
	 * @return Retorna o campo colecaoUnidadeNegocio.
	 */
	public Collection getColecaoUnidadeNegocio() {
		return colecaoUnidadeNegocio;
	}

	/**
	 * @param colecaoUnidadeNegocio O colecaoUnidadeNegocio a ser setado.
	 */
	public void setColecaoUnidadeNegocio(Collection colecaoUnidadeNegocio) {
		this.colecaoUnidadeNegocio = colecaoUnidadeNegocio;
	}

	/**
	 * @return Retorna o campo colecaoPerfilImovel.
	 */
	public Collection getColecaoPerfilImovel() {
		return colecaoPerfilImovel;
	}

	/**
	 * @param colecaoPerfilImovel O colecaoPerfilImovel a ser setado.
	 */
	public void setColecaoPerfilImovel(Collection colecaoPerfilImovel) {
		this.colecaoPerfilImovel = colecaoPerfilImovel;
	}

	/**
	 * @return Retorna o campo colecaoSubcategoria.
	 */
	public Collection getColecaoSubcategoria() {
		return colecaoSubcategoria;
	}

	/**
	 * @param colecaoSubcategoria O colecaoSubcategoria a ser setado.
	 */
	public void setColecaoSubcategoria(Collection colecaoSubcategoria) {
		this.colecaoSubcategoria = colecaoSubcategoria;
	}

	/**
	 * @return Retorna o campo colecaoTipoCliente.
	 */
	public Collection getColecaoTipoCliente() {
		return colecaoTipoCliente;
	}

	/**
	 * @param colecaoTipoCliente O colecaoTipoCliente a ser setado.
	 */
	public void setColecaoTipoCliente(Collection colecaoTipoCliente) {
		this.colecaoTipoCliente = colecaoTipoCliente;
	}

	/**
	 * @return Retorna o campo idUsuario.
	 */
	public Integer getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario O idUsuario a ser setado.
	 */
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return Retorna o campo idNagativacaoCriterio.
	 */
	public Integer getIdNagativacaoCriterio() {
		return idNagativacaoCriterio;
	}

	/**
	 * @param idNagativacaoCriterio O idNagativacaoCriterio a ser setado.
	 */
	public void setIdNagativacaoCriterio(Integer idNagativacaoCriterio) {
		this.idNagativacaoCriterio = idNagativacaoCriterio;
	}

	/**
	 * @return Retorna o campo ultimaAlteracaoNegComando.
	 */
	public Date getUltimaAlteracaoNegComando() {
		return ultimaAlteracaoNegComando;
	}

	/**
	 * @param ultimaAlteracaoNegComando O ultimaAlteracaoNegComando a ser setado.
	 */
	public void setUltimaAlteracaoNegComando(Date ultimaAlteracaoNegComando) {
		this.ultimaAlteracaoNegComando = ultimaAlteracaoNegComando;
	}

	/**
	 * @return Retorna o campo idNegativador.
	 */
	public Integer getIdNegativador() {
		return idNegativador;
	}

	/**
	 * @param idNegativador O idNegativador a ser setado.
	 */
	public void setIdNegativador(Integer idNegativador) {
		this.idNegativador = idNegativador;
	}

	/**
	 * @return Retorna o campo idComandoNegativacaoSimulado.
	 */
	public Integer getIdComandoNegativacaoSimulado() {
		return idComandoNegativacaoSimulado;
	}

	/**
	 * @param idComandoNegativacaoSimulado O idComandoNegativacaoSimulado a ser setado.
	 */
	public void setIdComandoNegativacaoSimulado(Integer idComandoNegativacaoSimulado) {
		this.idComandoNegativacaoSimulado = idComandoNegativacaoSimulado;
	}

	/**
	 * @return Retorna o campo colecaoSitLigacaoAgua.
	 */
	public Collection getColecaoSitLigacaoAgua() {
		return colecaoSitLigacaoAgua;
	}

	/**
	 * @param colecaoSitLigacaoAgua O colecaoSitLigacaoAgua a ser setado.
	 */
	public void setColecaoSitLigacaoAgua(Collection colecaoSitLigacaoAgua) {
		this.colecaoSitLigacaoAgua = colecaoSitLigacaoAgua;
	}

	/**
	 * @return Retorna o campo colecaoSitLigacaoEsgoto.
	 */
	public Collection getColecaoSitLigacaoEsgoto() {
		return colecaoSitLigacaoEsgoto;
	}

	/**
	 * @param colecaoSitLigacaoEsgoto O colecaoSitLigacaoEsgoto a ser setado.
	 */
	public void setColecaoSitLigacaoEsgoto(Collection colecaoSitLigacaoEsgoto) {
		this.colecaoSitLigacaoEsgoto = colecaoSitLigacaoEsgoto;
	}

	/**
	 * @return Retorna o campo idLocFinal.
	 */
	public Integer getIdLocFinal() {
		return idLocFinal;
	}

	/**
	 * @param idLocFinal O idLocFinal a ser setado.
	 */
	public void setIdLocFinal(Integer idLocFinal) {
		this.idLocFinal = idLocFinal;
	}

	/**
	 * @return Retorna o campo idLocInicial.
	 */
	public Integer getIdLocInicial() {
		return idLocInicial;
	}

	/**
	 * @param idLocInicial O idLocInicial a ser setado.
	 */
	public void setIdLocInicial(Integer idLocInicial) {
		this.idLocInicial = idLocInicial;
	}

	/**
	 * @return Retorna o campo codSetComFinal.
	 */
	public Integer getCodSetComFinal() {
		return codSetComFinal;
	}

	/**
	 * @param codSetComFinal O codSetComFinal a ser setado.
	 */
	public void setCodSetComFinal(Integer codSetComFinal) {
		this.codSetComFinal = codSetComFinal;
	}

	/**
	 * @return Retorna o campo codSetComInicial.
	 */
	public Integer getCodSetComInicial() {
		return codSetComInicial;
	}

	/**
	 * @param codSetComInicial O codSetComInicial a ser setado.
	 */
	public void setCodSetComInicial(Integer codSetComInicial) {
		this.codSetComInicial = codSetComInicial;
	}

	public Short getIndicadorBaixaRenda() {
		return indicadorBaixaRenda;
	}

	public void setIndicadorBaixaRenda(Short indicadorBaixaRenda) {
		this.indicadorBaixaRenda = indicadorBaixaRenda;
	}

	public Collection getColecaoMotivoRetorno() {
		return colecaoMotivoRetorno;
	}

	public void setColecaoMotivoRetorno(Collection colecaoMotivoRetorno) {
		this.colecaoMotivoRetorno = colecaoMotivoRetorno;
	}

	public Integer getQuantidadeDias() {
		return quantidadeDias;
	}

	public void setQuantidadeDias(Integer quantidadeDias) {
		this.quantidadeDias = quantidadeDias;
	}

	public Short getIndicadorContaNomeCliente() {
		return indicadorContaNomeCliente;
	}

	public void setIndicadorContaNomeCliente(Short indicadorContaNomeCliente) {
		this.indicadorContaNomeCliente = indicadorContaNomeCliente;
	}

	public Short getIndicadorOrgaoPublico() {
		return indicadorOrgaoPublico;
	}

	public void setIndicadorOrgaoPublico(Short indicadorOrgaoPublico) {
		this.indicadorOrgaoPublico = indicadorOrgaoPublico;
	}

  
}
