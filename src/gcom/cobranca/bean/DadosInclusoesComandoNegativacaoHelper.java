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

import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Date;

/**
 * [UC 0653] Pesquisar Comando Negativação
 * 
 * @author Kássia Albuquerque
 * @date 31/10/2007
 */

public class DadosInclusoesComandoNegativacaoHelper {
	
	private static final long serialVersionUID = 1L;
	
    private String nomeCliente;
    private Integer quantidadeInclusoes; 
    private BigDecimal valorTotalDebito;
    private Integer quantidadeItensIncluidos;
    private Integer idImovel;
    private String numeroCpf;
    private String numeroCnpj;
    private BigDecimal valorDebito;
    private String descricaoCobrancaSituacao;
    private Date dataSituacaoDebito;
    private Short indicadorAceito;
    private Short indicadorCorrecao;
    private Integer codigoExclusaoTipo;
    private String nomeUsuario;
	
	
	public DadosInclusoesComandoNegativacaoHelper() {
	}

	public DadosInclusoesComandoNegativacaoHelper(
            String nomeCliente,
            Integer quantidadeInclusoes, 
            BigDecimal valorTotalDebito,
            Integer quantidadeItensIncluidos,
            Integer idImovel,
            String numeroCpf,
            String numeroCnpj,
            BigDecimal valorDebito,
            String descricaoCobrancaSituacao,
            Date dataSituacaoDebito,
            Short indicadorAceito,
            Short indicadorCorrecao,
            Integer codigoExclusaoTipo,
            String nomeUsuario
			) {
		
            this.nomeCliente = nomeCliente;
            this.quantidadeInclusoes =  quantidadeInclusoes;
            this.valorTotalDebito = valorTotalDebito;
            this.quantidadeItensIncluidos = quantidadeItensIncluidos;
            this.idImovel = idImovel;
            this.numeroCpf = numeroCpf;
            this.numeroCnpj = numeroCnpj;
            this.valorDebito = valorDebito;
            this.descricaoCobrancaSituacao = descricaoCobrancaSituacao;
            this.dataSituacaoDebito = dataSituacaoDebito;
            this.indicadorAceito = indicadorAceito;
            this.indicadorCorrecao = indicadorCorrecao;
            this.codigoExclusaoTipo = codigoExclusaoTipo;
            this.nomeUsuario = nomeUsuario;
		
		
	}

    public Integer getCodigoExclusaoTipo() {
        return codigoExclusaoTipo;
    }

    public void setCodigoExclusaoTipo(Integer codigoExclusaoTipo) {
        this.codigoExclusaoTipo = codigoExclusaoTipo;
    }

    public Date getDataSituacaoDebito() {
        return dataSituacaoDebito;
    }

    public void setDataSituacaoDebito(Date dataSituacaoDebito) {
        this.dataSituacaoDebito = dataSituacaoDebito;
    }

    public String getDescricaoCobrancaSituacao() {
        return descricaoCobrancaSituacao;
    }

    public void setDescricaoCobrancaSituacao(String descricaoCobrancaSituacao) {
        this.descricaoCobrancaSituacao = descricaoCobrancaSituacao;
    }

    public Integer getIdImovel() {
        return idImovel;
    }

    public void setIdImovel(Integer idImovel) {
        this.idImovel = idImovel;
    }

    public Short getIndicadorAceito() {
        return indicadorAceito;
    }

    public void setIndicadorAceito(Short indicadorAceito) {
        this.indicadorAceito = indicadorAceito;
    }

    public Short getIndicadorCorrecao() {
        return indicadorCorrecao;
    }

    public void setIndicadorCorrecao(Short indicadorCorrecao) {
        this.indicadorCorrecao = indicadorCorrecao;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNumeroCnpj() {
        return numeroCnpj;
    }

    public void setNumeroCnpj(String numeroCnpj) {
        this.numeroCnpj = numeroCnpj;
    }

    public String getNumeroCpf() {
        return numeroCpf;
    }

    public void setNumeroCpf(String numeroCpf) {
        this.numeroCpf = numeroCpf;
    }

    public Integer getQuantidadeInclusoes() {
        return quantidadeInclusoes;
    }

    public void setQuantidadeInclusoes(Integer quantidadeInclusoes) {
        this.quantidadeInclusoes = quantidadeInclusoes;
    }

    public Integer getQuantidadeItensIncluidos() {
        return quantidadeItensIncluidos;
    }

    public void setQuantidadeItensIncluidos(Integer quantidadeItensIncluidos) {
        this.quantidadeItensIncluidos = quantidadeItensIncluidos;
    }

    public BigDecimal getValorDebito() {
        return valorDebito;
    }

    public void setValorDebito(BigDecimal valorDebito) {
        this.valorDebito = valorDebito;
    }

    public BigDecimal getValorTotalDebito() {
        return valorTotalDebito;
    }

    public void setValorTotalDebito(BigDecimal valorTotalDebito) {
        this.valorTotalDebito = valorTotalDebito;
    }

    public String getInclusaoAceita() {
        String inclusaoAceita = "NÃO";
        
        if(getIndicadorAceito()!= null && getIndicadorAceito().equals(ConstantesSistema.SIM)){
            inclusaoAceita = "SIM";
        }
        
        return inclusaoAceita;
    }
	
    
    public String getInclusaoCorrigida() {
        String inclusaoCorrigida = "";
        
        if(getIndicadorAceito()!= null && getIndicadorAceito().equals(ConstantesSistema.NAO)){
            if(getIndicadorCorrecao()!= null && getIndicadorCorrecao().equals(ConstantesSistema.SIM)){
                inclusaoCorrigida = "SIM";
            }else{
                inclusaoCorrigida = "NÃO";
            }
        }
        
        return inclusaoCorrigida;
    }
    
    public String getSituacaoInclusao() {
        String situacaoInclusao = "";
        
        if(getIndicadorAceito()!= null && getIndicadorAceito().equals(ConstantesSistema.NAO)){
        	 situacaoInclusao = "Rejeitada";       	
        }else if (getIndicadorAceito()!= null && getIndicadorAceito().equals(ConstantesSistema.SIM)){ 
          if(getCodigoExclusaoTipo() == null){
              situacaoInclusao = "Aceita";
          }else if(getCodigoExclusaoTipo().equals(1)){
              situacaoInclusao = "Excluída";
          }else if(getCodigoExclusaoTipo().equals(2)){
              situacaoInclusao = "Excluída-On Line";
          }   
        }
        
        return situacaoInclusao;
    }
    
    public String getCpfCnpj() {
        String cpfCnpj = "";
        if(getNumeroCpf() != null){
        	cpfCnpj = Util.formatarCPFApresentacao(getNumeroCpf());
        }else if(getNumeroCnpj() != null){
        	cpfCnpj = getNumeroCnpj();
        }
        return cpfCnpj;
    }
    
    
}
