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
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class ContratoEmpresaServico extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_CONTRATO_EMPRESA_SERVICO_INFORMAR = 1661; //Operacao.OPERACAO_INFORMAR_ITEM_SERVICO_CONTRATO

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	@ControleAlteracao(value=FiltroContratoEmpresaServico.EMPRESA, funcionalidade={ATRIBUTOS_CONTRATO_EMPRESA_SERVICO_INFORMAR})
	private Empresa empresa;
	
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CONTRATO_EMPRESA_SERVICO_INFORMAR})
	private String descricaoContrato;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CONTRATO_EMPRESA_SERVICO_INFORMAR})
	private Date dataInicioContrato;
	
	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CONTRATO_EMPRESA_SERVICO_INFORMAR})
	private Date dataFimContrato;

	/** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CONTRATO_EMPRESA_SERVICO_INFORMAR})
	private Date ultimaAlteracao;
	
	//@ControleAlteracao(value=FiltroContratoEmpresaServico.ITEM_SERVICO_CONTRATOS, funcionalidade={ATRIBUTOS_CONTRATO_EMPRESA_SERVICO_INFORMAR})
	//@ControleAlteracao(funcionalidade={ATRIBUTOS_CONTRATO_EMPRESA_SERVICO_INFORMAR})
	private Set itemServicoContratos;

	private BigDecimal valorGlobalContrato;
	
	private String observacao;
	
	private BigDecimal percentualTaxaSucesso;
	
	
	/** full constructor */
	public ContratoEmpresaServico(String descricaoContrato, Empresa empresa, Date dataInicioContrato,
			 Date dataFimContrato, Date ultimaAlteracao) {
		
		this.empresa = empresa;
		this.descricaoContrato = descricaoContrato;
		this.dataInicioContrato = dataInicioContrato;
		this.dataFimContrato = dataFimContrato;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/** default constructor */
	public ContratoEmpresaServico() {
		
	}
	
	public String getDescricaoContrato() {
		return descricaoContrato;
	}

	public void setDescricaoContrato(String descricaoContrato) {
		this.descricaoContrato = descricaoContrato;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Date getDataFimContrato() {
		return dataFimContrato;
	}

	public void setDataFimContrato(Date dataFimContrato) {
		this.dataFimContrato = dataFimContrato;
	}

	public Date getDataInicioContrato() {
		return dataInicioContrato;
	}

	public void setDataInicioContrato(Date dataInicioContrato) {
		this.dataInicioContrato = dataInicioContrato;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public Set getItemServicoContratos() {
		return itemServicoContratos;
	}

	public void setItemServicoContratos(Set itemServicoContratos) {
		this.itemServicoContratos = itemServicoContratos;
	}

	public BigDecimal getValorGlobalContrato() {
		return valorGlobalContrato;
	}

	public void setValorGlobalContrato(BigDecimal valorGlobalContrato) {
		this.valorGlobalContrato = valorGlobalContrato;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public void initializeLazy() {
		
		initilizarCollectionLazy(this.getItemServicoContratos());
		if (getEmpresa() != null) empresa.initializeLazy();
	}

	public Filtro retornaFiltro() {
		FiltroContratoEmpresaServico filtroContratoEmpresaServico = new FiltroContratoEmpresaServico();

		filtroContratoEmpresaServico.adicionarParametro(new ParametroSimples(FiltroContratoEmpresaServico.ID,
				this.getId()));
		
		return filtroContratoEmpresaServico;
	}
	
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoEmpresaServico.EMPRESA);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroContratoEmpresaServico.ITEM_SERVICO_CONTRATOS);
		
		return filtro;
	}
	
	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
	
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public BigDecimal getPercentualTaxaSucesso() {
		return percentualTaxaSucesso;
	}

	public void setPercentualTaxaSucesso(BigDecimal percentualTaxaSucesso) {
		this.percentualTaxaSucesso = percentualTaxaSucesso;
	}	
	
}
