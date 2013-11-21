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
package gcom.gui.cadastro;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Arthur Carvalho
 * @date 14/05/2008
 */
public class InserirEmpresaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String descricao;
	
	private String descricaoAbreviada;
	
	private String indicadorEmpresa;
	
	private String email;
	
	private Short indicadorUso;
	
	private Short indicadorEmpresaPrincipal;
    
	private String indicadorEmpresaCobranca;
    
	private String dataInicioContratoCobranca;
    
	private String percentualPagamento;

	private Short indicadorLeitura;
	
	private String quantidadeMinimaContas;
    
	private String percentualDaFaixa;
    
	private String percentualDaFaixaInformado;
    
    public Short getIndicadorLeitura() {
		return indicadorLeitura;
	}



	public void setIndicadorLeitura(Short indicadorLeitura) {
		this.indicadorLeitura = indicadorLeitura;
	}



	public String getDataInicioContratoCobranca() {
        return dataInicioContratoCobranca;
    }


    
    public void setDataInicioContratoCobranca(String dataInicioContratoCobranca) {
        this.dataInicioContratoCobranca = dataInicioContratoCobranca;
    }


    
    public String getPercentualPagamento() {
        return percentualPagamento;
    }


    
    public void setPercentualPagamento(String percentualPagamento) {
        this.percentualPagamento = percentualPagamento;
    }


    public String getIndicadorEmpresaCobranca() {
        return indicadorEmpresaCobranca;
    }

    
    public void setIndicadorEmpresaCobranca(String indicadorEmpresaCobranca) {
        this.indicadorEmpresaCobranca = indicadorEmpresaCobranca;
    }

    public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIndicadorEmpresa() {
		return indicadorEmpresa;
	}

	public void setIndicadorEmpresa(String indicadorEmpresa) {
		this.indicadorEmpresa = indicadorEmpresa;
	}

	public Short getIndicadorEmpresaPrincipal() {
		return indicadorEmpresaPrincipal;
	}

	public void setIndicadorEmpresaPrincipal(Short indicadorEmpresaPrincipal) {
		this.indicadorEmpresaPrincipal = indicadorEmpresaPrincipal;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getPercentualDaFaixa() {
		return percentualDaFaixa;
	}

	public void setPercentualDaFaixa(String percentualDaFaixa) {
		this.percentualDaFaixa = percentualDaFaixa;
	}

	public String getQuantidadeMinimaContas() {
		return quantidadeMinimaContas;
	}

	public void setQuantidadeMinimaContas(String quantidadeMinimaContas) {
		this.quantidadeMinimaContas = quantidadeMinimaContas;
	}

	public String getPercentualDaFaixaInformado() {
		return percentualDaFaixaInformado;
	}

	public void setPercentualDaFaixaInformado(String percentualDaFaixaInformado) {
		this.percentualDaFaixaInformado = percentualDaFaixaInformado;
	}
	
}
