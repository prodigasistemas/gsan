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
package gcom.relatorio.arrecadacao.pagamento;

import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GuiaPagamentoRelatorioHelper {
	
	private Integer idImovel;
	private Integer idCliente;
	private Date dataVencimento;
	private Integer idLocalidade;
	private String descLocalidade;
	private BigDecimal valorDebito;
	private Date dataEmissao;
	private String nomeCliente; 
	private Integer idTipoDebito;
	private String descTipoDebito;
    private Short numeroPrestacaoDebito;
    private Short numeroPrestacaoTotal;
    private String observacao;
    private Short indicadorEmitirObservacao;
	
	private String matricula;
	private String inscricao; 
	private String enderecoImovel; 
	private String enderecoClienteResponsavel;
	private String representacaoNumericaCodBarraFormatada;
	private String representacaoNumericaCodBarraSemDigito;
	private String idGuiaPagamento;
	
	private String cpfCliente;
	private String cnpjCliente;
	
	
	/* Ficha de Compensação
	 * Adicionado por: Mariana Victor
	 * Data: 02/03/2011
	 * */
	private String nossoNumero;
	private String sacadoParte01;
	private String sacadoParte02;
	private String enderecoImovelSacado;
	
	private String nomeImovel;
	private String subRelatorio;
	
	/** minimal constructor */
    public GuiaPagamentoRelatorioHelper(
    		Integer idImovel,
    		Date dataVencimento,
    		Integer idLocalidade,
    		String descLocalidade,
    		BigDecimal valorDebito,
    		Date dataEmissao,
    		Integer idTipoDebito,
    		String descTipoDebito,
            Short numeroPrestacaoDebito,
            Short numeroPrestacaoTotal,
            String observacao,
            Short indicadorEmitirObservacao) {
        this.idImovel = idImovel;
        this.dataVencimento = dataVencimento;
        this.idLocalidade = idLocalidade;
        this.descLocalidade = descLocalidade;
        this.valorDebito = valorDebito;
        this.dataEmissao = dataEmissao;
        this.idTipoDebito = idTipoDebito;
        this.descTipoDebito = descTipoDebito;
        this.numeroPrestacaoDebito = numeroPrestacaoDebito;
        this.numeroPrestacaoTotal = numeroPrestacaoTotal;
        this.observacao = observacao;
        this.indicadorEmitirObservacao = indicadorEmitirObservacao;
        
    }
    
    public GuiaPagamentoRelatorioHelper(
    		Integer idImovel,
    		Date dataVencimento,
    		Integer idLocalidade,
    		String descLocalidade,
    		BigDecimal valorDebito,
    		Date dataEmissao,
    		Integer idTipoDebito,
    		String descTipoDebito,
            Short numeroPrestacaoDebito,
            Short numeroPrestacaoTotal,
            String observacao,
            Short indicadorEmitirObservacao,
            String nomeImovel) {
        this.idImovel = idImovel;
        this.dataVencimento = dataVencimento;
        this.idLocalidade = idLocalidade;
        this.descLocalidade = descLocalidade;
        this.valorDebito = valorDebito;
        this.dataEmissao = dataEmissao;
        this.idTipoDebito = idTipoDebito;
        this.descTipoDebito = descTipoDebito;
        this.numeroPrestacaoDebito = numeroPrestacaoDebito;
        this.numeroPrestacaoTotal = numeroPrestacaoTotal;
        this.observacao = observacao;
        this.indicadorEmitirObservacao = indicadorEmitirObservacao;
        this.nomeImovel = nomeImovel;
        
    }
    public GuiaPagamentoRelatorioHelper() {
    }

	public String getDescTipoDebito() {
		return descTipoDebito;
	}
	public void setDescTipoDebito(String descTipoDebito) {
		this.descTipoDebito = descTipoDebito;
	}
	public String getRepresentacaoNumericaCodBarraFormatada() {
		return representacaoNumericaCodBarraFormatada;
	}
	public void setRepresentacaoNumericaCodBarraFormatada(
			String representacaoNumericaCodBarraFormatada) {
		this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
	}
	public String getRepresentacaoNumericaCodBarraSemDigito() {
		return representacaoNumericaCodBarraSemDigito;
	}
	public void setRepresentacaoNumericaCodBarraSemDigito(
			String representacaoNumericaCodBarraSemDigito) {
		this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public Integer getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public BigDecimal getValorDebito() {
		return valorDebito;
	}


	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}


	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public String getEnderecoClienteResponsavel() {
		return enderecoClienteResponsavel;
	}
	public void setEnderecoClienteResponsavel(String enderecoClienteResponsavel) {
		this.enderecoClienteResponsavel = enderecoClienteResponsavel;
	}
	public String getEnderecoImovel() {
		return enderecoImovel;
	}
	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	public String getInscricao() {
		return inscricao;
	}
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
	public String getDescLocalidade() {
		return descLocalidade;
	}

	public void setDescLocalidade(String descLocalidade) {
		this.descLocalidade = descLocalidade;
	}

	public Integer getIdTipoDebito() {
		return idTipoDebito;
	}

	public void setIdTipoDebito(Integer idTipoDebito) {
		this.idTipoDebito = idTipoDebito;
	}

	public String getIdGuiaPagamento() {
		return idGuiaPagamento;
	}

	public void setIdGuiaPagamento(String idGuiaPagamento) {
		this.idGuiaPagamento = idGuiaPagamento;
	}

	//Data de Validade da Guia de Pagamento
	//último dia do proximo mês do mês/ano da data de vencimento da guia de pagamento
	public String getDataValidade(){
		
		int anoVencimento = Util.getAno(getDataVencimento()); 
		int mesVencimento = Util.getMes(getDataVencimento()); 
		
		Calendar calendarUltimoDiaMesAnoDataVencimento  = new GregorianCalendar();
		
		calendarUltimoDiaMesAnoDataVencimento.set(Calendar.YEAR, anoVencimento);
		calendarUltimoDiaMesAnoDataVencimento.set(Calendar.MONTH, mesVencimento - 1);
		calendarUltimoDiaMesAnoDataVencimento.set(Calendar.DAY_OF_MONTH,
				calendarUltimoDiaMesAnoDataVencimento.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		Date dateDataVencimentoMais3Dias = Util.adicionarNumeroDiasDeUmaData(getDataVencimento(),3);
		Date dateDataCorrenteMais3Dias = Util.adicionarNumeroDiasDeUmaData(new Date(),3);
		Date dateMaiorData = null;
		
//		 retorna 
//		 -1 se a data1 for menor que a data2, 
//		 0 se as datas forem iguais,
//		 1 se a data1 for maior que a data2.
		
		if (Util.compararData(dateDataVencimentoMais3Dias,dateDataCorrenteMais3Dias) >= 0){
			
			if (Util.compararData(dateDataVencimentoMais3Dias,calendarUltimoDiaMesAnoDataVencimento.getTime()) >= 0){
				dateMaiorData = dateDataVencimentoMais3Dias;
			}else{
				dateMaiorData = calendarUltimoDiaMesAnoDataVencimento.getTime();
			}
			
		}else{
			
			if (Util.compararData(dateDataCorrenteMais3Dias,calendarUltimoDiaMesAnoDataVencimento.getTime()) >= 0){
				dateMaiorData = dateDataCorrenteMais3Dias;
			}else{
				dateMaiorData = calendarUltimoDiaMesAnoDataVencimento.getTime();
			}
		}
		
		
//		String anoMesValidade = Util.getAnoMesComoString(getDataVencimento());
//		Calendar calendario = new GregorianCalendar();
//
//		calendario.set(Calendar.YEAR, new Integer(anoMesValidade.substring(0, 4)).intValue());
//		calendario.set(Calendar.MONTH, new Integer(anoMesValidade.substring(4, 6)).intValue());
//		calendario.set(Calendar.DAY_OF_MONTH, calendario.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		
		return Util.formatarData(dateMaiorData);
	}
	
	public String getMatriculaFormatada(){
		String matriculaFormatada = Util.adicionarZerosEsquedaNumero(9, getMatricula());
		matriculaFormatada = matriculaFormatada.substring(0, 8)
				+ "." + matriculaFormatada.substring(8, 9);
		return  matriculaFormatada ;
	}

    public Short getNumeroPrestacaoDebito() {
        return numeroPrestacaoDebito;
    }

    public void setNumeroPrestacaoDebito(Short numeroPrestacaoDebito) {
        this.numeroPrestacaoDebito = numeroPrestacaoDebito;
    }

    public Short getNumeroPrestacaoTotal() {
        return numeroPrestacaoTotal;
    }

    public void setNumeroPrestacaoTotal(Short numeroPrestacaoTotal) {
        this.numeroPrestacaoTotal = numeroPrestacaoTotal;
    }

    public String getPrestacaoFormatada(){
        String prestacaoFormatada = "";
        
        if(getNumeroPrestacaoDebito() != null &&
                getNumeroPrestacaoTotal() != null){
            prestacaoFormatada = prestacaoFormatada + getNumeroPrestacaoDebito() + "/" + getNumeroPrestacaoTotal();
        }
        
        return  prestacaoFormatada ;
    }

	/**
	 * @return Retorna o campo observacao.
	 */
	public String getObservacao() {
		return observacao;
	}

	/**
	 * @param observacao O observacao a ser setado.
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	/**
	 * @return Retorna o campo indicadorEmitirObservacao.
	 */
	public Short getIndicadorEmitirObservacao() {
		return indicadorEmitirObservacao;
	}

	/**
	 * @param indicadorEmitirObservacao O indicadorEmitirObservacao a ser setado.
	 */
	public void setIndicadorEmitirObservacao(Short indicadorEmitirObservacao) {
		this.indicadorEmitirObservacao = indicadorEmitirObservacao;
	}

	public String getCnpjCliente() {
		return cnpjCliente;
	}

	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getEnderecoImovelSacado() {
		return enderecoImovelSacado;
	}

	public void setEnderecoImovelSacado(String enderecoImovelSacado) {
		this.enderecoImovelSacado = enderecoImovelSacado;
	}

	public String getNossoNumero() {
		return nossoNumero;
	}

	public void setNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
	}

	public String getSacadoParte01() {
		return sacadoParte01;
	}

	public void setSacadoParte01(String sacadoParte01) {
		this.sacadoParte01 = sacadoParte01;
	}

	public String getSacadoParte02() {
		return sacadoParte02;
	}

	public void setSacadoParte02(String sacadoParte02) {
		this.sacadoParte02 = sacadoParte02;
	}

	public String getNomeImovel() {
		return nomeImovel;
	}

	public void setNomeImovel(String nomeImovel) {
		this.nomeImovel = nomeImovel;
	}

	public String getSubRelatorio() {
		return subRelatorio;
	}

	public void setSubRelatorio(String subRelatorio) {
		this.subRelatorio = subRelatorio;
	}
    
}
