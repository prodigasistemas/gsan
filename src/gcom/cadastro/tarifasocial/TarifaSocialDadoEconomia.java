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
package gcom.cadastro.tarifasocial;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelEconomia;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class TarifaSocialDadoEconomia extends ObjetoTransacao { 
	
	private static final long serialVersionUID = 1L;

	public static final int ATRIBUTOS_INSERIR_TARIFA_SOCIAL = 595; // Operacao.OPERACAO_INSERIR_TARIFA_SOCIAL
	
	public static final int ATRIBUTOS_MANTER_TARIFA_SOCIAL = 754; // Operacao.OPERACAO_MANTER_TARIFA_SOCIAL
	
	public Filtro retornaFiltro() {

		FiltroTarifaSocialDadoEconomia filtro = new FiltroTarifaSocialDadoEconomia();

		filtro.adicionarParametro(new ParametroSimples(
				FiltroTarifaSocialDadoEconomia.ID, this.getId()));

		filtro.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtro.adicionarCaminhoParaCarregamentoEntidade("rendaTipo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("tarifaSocialCartaoTipo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("tarifaSocialRevisaoMotivo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("tarifaSocialExclusaoMotivo");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovelEconomia");
		return filtro;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	/** identifier field */
	private Integer id;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_TARIFA_SOCIAL, ATRIBUTOS_MANTER_TARIFA_SOCIAL})
	private Long numeroCartaoProgramaSocial;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_TARIFA_SOCIAL,ATRIBUTOS_MANTER_TARIFA_SOCIAL})
	private Short numeroMesesAdesao;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_TARIFA_SOCIAL,ATRIBUTOS_MANTER_TARIFA_SOCIAL})
	private Integer consumoCelpe;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_TARIFA_SOCIAL,ATRIBUTOS_MANTER_TARIFA_SOCIAL})
	private BigDecimal valorRendaFamiliar;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_TARIFA_SOCIAL,ATRIBUTOS_MANTER_TARIFA_SOCIAL})
	private Date dataValidadeCartao;

	/** nullable persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	// private gcom.cadastro.tarifasocial.TarifaSocialDado tarifaSocialDado;
	/** persistent field */
	@ControleAlteracao(value=FiltroTarifaSocialDadoEconomia.RENDA_TIPO,
			funcionalidade={ATRIBUTOS_INSERIR_TARIFA_SOCIAL,ATRIBUTOS_MANTER_TARIFA_SOCIAL})
	private gcom.cadastro.tarifasocial.RendaTipo rendaTipo;

	/** persistent field */
	@ControleAlteracao(value=FiltroTarifaSocialDadoEconomia.TARIFA_SOCIAL_CARTAO_TIPO, 
			funcionalidade={ATRIBUTOS_INSERIR_TARIFA_SOCIAL,ATRIBUTOS_MANTER_TARIFA_SOCIAL})	
	private gcom.cadastro.tarifasocial.TarifaSocialCartaoTipo tarifaSocialCartaoTipo;

	/** persistent field */
//	@ControleAlteracao(value=FiltroTarifaSocialDadoEconomia.IMOVEL_ECONOMIA, 
//			funcionalidade={ATRIBUTOS_INSERIR_TARIFA_SOCIAL,ATRIBUTOS_MANTER_TARIFA_SOCIAL})		
	private ImovelEconomia imovelEconomia;

	/** nullable persistent field */
	private Date dataImplantacao;

	/** nullable persistent field */
//	@ControleAlteracao(funcionalidade={ATRIBUTOS_INSERIR_TARIFA_SOCIAL,ATRIBUTOS_MANTER_TARIFA_SOCIAL})
	private Date dataExclusao;

	/** nullable persistent field */
	private Date dataRevisao;

	/** nullable persistent field */
	@ControleAlteracao(value=FiltroTarifaSocialDadoEconomia.MOTIVO_REVISAO,
			funcionalidade={ATRIBUTOS_INSERIR_TARIFA_SOCIAL,ATRIBUTOS_MANTER_TARIFA_SOCIAL})		
	private Short quantidadeRecadastramento;

	/** nullable persistent field */
	private Date dataRecadastramento;

	/** persistent field */
	@ControleAlteracao(value=FiltroTarifaSocialDadoEconomia.MOTIVO_REVISAO,
			funcionalidade={ATRIBUTOS_INSERIR_TARIFA_SOCIAL,ATRIBUTOS_MANTER_TARIFA_SOCIAL})	
	private TarifaSocialRevisaoMotivo tarifaSocialRevisaoMotivo;

	/** persistent field */
	@ControleAlteracao(value=FiltroTarifaSocialDadoEconomia.MOTIVO_EXCLUSAO,
			funcionalidade={ATRIBUTOS_INSERIR_TARIFA_SOCIAL,ATRIBUTOS_MANTER_TARIFA_SOCIAL})	
	private TarifaSocialExclusaoMotivo tarifaSocialExclusaoMotivo;

	/** persistent field */
//	@ControleAlteracao(value=FiltroTarifaSocialDadoEconomia.IMOVEL,
//			funcionalidade={ATRIBUTOS_MANTER_TARIFA_SOCIAL, ATRIBUTOS_INSERIR_TARIFA_SOCIAL})		
	private Imovel imovel;

	/** full constructor */
	public TarifaSocialDadoEconomia(
			Long numeroCartaoProgramaSocial,
			Short numeroMesesAdesao,
			Integer consumoCelpe,
			BigDecimal valorRendaFamiliar,
			Date dataValidadeCartao,
			Date ultimaAlteracao,
			gcom.cadastro.tarifasocial.RendaTipo rendaTipo,
			gcom.cadastro.tarifasocial.TarifaSocialCartaoTipo tarifaSocialCartaoTipo,
			ImovelEconomia imovelEconomia) {
		this.numeroCartaoProgramaSocial = numeroCartaoProgramaSocial;
		this.numeroMesesAdesao = numeroMesesAdesao;
		this.consumoCelpe = consumoCelpe;
		this.valorRendaFamiliar = valorRendaFamiliar;
		this.dataValidadeCartao = dataValidadeCartao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.rendaTipo = rendaTipo;
		this.tarifaSocialCartaoTipo = tarifaSocialCartaoTipo;
		this.imovelEconomia = imovelEconomia;
	}

	/** default constructor */
	public TarifaSocialDadoEconomia() {
	}

	/** minimal constructor */
	public TarifaSocialDadoEconomia(
			gcom.cadastro.tarifasocial.RendaTipo rendaTipo,
			gcom.cadastro.tarifasocial.TarifaSocialCartaoTipo tarifaSocialCartaoTipo,
			ImovelEconomia imovelEconomia) {
		this.rendaTipo = rendaTipo;
		this.tarifaSocialCartaoTipo = tarifaSocialCartaoTipo;
		this.imovelEconomia = imovelEconomia;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getNumeroCartaoProgramaSocial() {
		return this.numeroCartaoProgramaSocial;
	}

	public void setNumeroCartaoProgramaSocial(Long numeroCartaoProgramaSocial) {
		this.numeroCartaoProgramaSocial = numeroCartaoProgramaSocial;
	}

	public Short getNumeroMesesAdesao() {
		return this.numeroMesesAdesao;
	}

	public void setNumeroMesesAdesao(Short numeroMesesAdesao) {
		this.numeroMesesAdesao = numeroMesesAdesao;
	}

	public Integer getConsumoCelpe() {
		return this.consumoCelpe;
	}

	public void setConsumoCelpe(Integer consumoCelpe) {
		this.consumoCelpe = consumoCelpe;
	}

	public BigDecimal getValorRendaFamiliar() {
		return this.valorRendaFamiliar;
	}

	public void setValorRendaFamiliar(BigDecimal valorRendaFamiliar) {
		this.valorRendaFamiliar = valorRendaFamiliar;
	}

	public Date getDataValidadeCartao() {
		return this.dataValidadeCartao;
	}

	public String getDataValidadeCartaoFormatada() {
		if (this.dataValidadeCartao != null) {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			return formato.format(this.dataValidadeCartao);
		} else {
			return "";
		}
	}

	public void setDataValidadeCartao(Date dataValidadeCartao) {
		this.dataValidadeCartao = dataValidadeCartao;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gcom.cadastro.tarifasocial.RendaTipo getRendaTipo() {
		return this.rendaTipo;
	}

	public void setRendaTipo(gcom.cadastro.tarifasocial.RendaTipo rendaTipo) {
		this.rendaTipo = rendaTipo;
	}

	public gcom.cadastro.tarifasocial.TarifaSocialCartaoTipo getTarifaSocialCartaoTipo() {
		return this.tarifaSocialCartaoTipo;
	}

	public void setTarifaSocialCartaoTipo(
			gcom.cadastro.tarifasocial.TarifaSocialCartaoTipo tarifaSocialCartaoTipo) {
		this.tarifaSocialCartaoTipo = tarifaSocialCartaoTipo;
	}

	public ImovelEconomia getImovelEconomia() {
		return this.imovelEconomia;
	}

	public void setImovelEconomia(ImovelEconomia imovelEconomia) {
		this.imovelEconomia = imovelEconomia;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * @return Retorna o campo dataExclusao.
	 */
	public Date getDataExclusao() {
		return dataExclusao;
	}

	/**
	 * @param dataExclusao
	 *            O dataExclusao a ser setado.
	 */
	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	/**
	 * @return Retorna o campo dataImplantacao.
	 */
	public Date getDataImplantacao() {
		return dataImplantacao;
	}

	/**
	 * @param dataImplantacao
	 *            O dataImplantacao a ser setado.
	 */
	public void setDataImplantacao(Date dataImplantacao) {
		this.dataImplantacao = dataImplantacao;
	}

	/**
	 * @return Retorna o campo dataRecadastramento.
	 */
	public Date getDataRecadastramento() {
		return dataRecadastramento;
	}

	/**
	 * @param dataRecadastramento
	 *            O dataRecadastramento a ser setado.
	 */
	public void setDataRecadastramento(Date dataRecadastramento) {
		this.dataRecadastramento = dataRecadastramento;
	}

	/**
	 * @return Retorna o campo dataRevisao.
	 */
	public Date getDataRevisao() {
		return dataRevisao;
	}

	/**
	 * @param dataRevisao
	 *            O dataRevisao a ser setado.
	 */
	public void setDataRevisao(Date dataRevisao) {
		this.dataRevisao = dataRevisao;
	}

	/**
	 * @return Retorna o campo quantidadeRecadastramento.
	 */
	public Short getQuantidadeRecadastramento() {
		return quantidadeRecadastramento;
	}

	/**
	 * @param quantidadeRecadastramento
	 *            O quantidadeRecadastramento a ser setado.
	 */
	public void setQuantidadeRecadastramento(Short quantidadeRecadastramento) {
		this.quantidadeRecadastramento = quantidadeRecadastramento;
	}

	/**
	 * @return Retorna o campo tarifaSocialExclusaoMotivo.
	 */
	public TarifaSocialExclusaoMotivo getTarifaSocialExclusaoMotivo() {
		return tarifaSocialExclusaoMotivo;
	}

	/**
	 * @param tarifaSocialExclusaoMotivo
	 *            O tarifaSocialExclusaoMotivo a ser setado.
	 */
	public void setTarifaSocialExclusaoMotivo(
			TarifaSocialExclusaoMotivo tarifaSocialExclusaoMotivo) {
		this.tarifaSocialExclusaoMotivo = tarifaSocialExclusaoMotivo;
	}

	/**
	 * @return Retorna o campo tarifaSocialRevisaoMotivo.
	 */
	public TarifaSocialRevisaoMotivo getTarifaSocialRevisaoMotivo() {
		return tarifaSocialRevisaoMotivo;
	}

	/**
	 * @param tarifaSocialRevisaoMotivo
	 *            O tarifaSocialRevisaoMotivo a ser setado.
	 */
	public void setTarifaSocialRevisaoMotivo(
			TarifaSocialRevisaoMotivo tarifaSocialRevisaoMotivo) {
		this.tarifaSocialRevisaoMotivo = tarifaSocialRevisaoMotivo;
	}

	/**
	 * @return Retorna o campo imovel.
	 */
	public Imovel getImovel() {
		return imovel;
	}

	/**
	 * @param imovel
	 *            O imovel a ser setado.
	 */
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	@Override
	public void initializeLazy() {
		retornaCamposChavePrimaria();
		getTarifaSocialCartaoTipo();
		getTarifaSocialExclusaoMotivo();
		getTarifaSocialRevisaoMotivo();
		if (getImovel() != null){
			getImovel().initializeLazy();
		}
		if (getImovelEconomia() != null){
			getImovelEconomia().initializeLazy();
		}
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"imovel.inscricaoFormatada"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Inscricao"};
		return labels;		
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		String retornoDesc = "";
		if (imovelEconomia != null){
			retornoDesc = imovelEconomia.getDescricaoParaRegistroTransacao();
		} else {
//			retornoDesc = this.getId() + " ";
//			if (imovel != null){
//				Cliente cliente = imovel.getClienteUsuario();
//				if (cliente != null){
//					retornoDesc += cliente.getDescricaoParaRegistroTransacao();	
//				}				 	
//			}
		}
		return retornoDesc ;
	}
}
