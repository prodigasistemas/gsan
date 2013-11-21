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
package gcom.faturamento.bean;

import java.io.Serializable;
import java.util.Collection;

/** @author Hibernate CodeGenerator */
public class EmitirContaTipo2Helper implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nomeCliente;
	private String endereco; 
	private String bairro;
	private String enderecoLinha3; 
	private String mesAnoConta;
	private String dataVencimento;
	private String inscricao;
	private String matricula;
	private String rota;
	private String categoria;
	private String qtdeEconomia;
	private String hidrometro;
	private String volFaturado; 
	private String descricaoTipoConsumo;
	private String numeroConta;
	private String dtLeituraAnterior;
	private String dtLeituraAtual;
	private String diasConsumo;
	private String leituraAnterior;
	private String leituraAtual;
	private String consumoMedio;
	private String mesAno1Conta;
	private String consumo1Conta;
	private String mesAno2Conta;
	private String consumo2Conta;
	private String mesAno3Conta;
	private String consumo3Conta;
	private String mesAno4Conta;
	private String consumo4Conta;
	private String mesAno5Conta;
	private String consumo5Conta;
	private String mesAno6Conta;
	private String consumo6Conta;
	private Collection colecaoContaLinhasDescricaoServicosTarifasTotalHelper;
	private String msgConta;
	private String msgLinha1Conta;
	private String msgLinha2Conta;
	private String msgLinha3Conta;
	private String msgLinha4Conta;
	private String msgLinha5Conta;
	private String datasVencimentos;

	private String valorMedioTurbidez;
	private String padraoTurbidez;
	private String valorMedioPh;
	private String valorMedioCor;
	private String valorMedioCloro;
	private String valorMedioFluor;
	private String valorMedioFerro;
	private String valorMedioColiformesTotais;
	private String valorMedioColiformesfecais;
	
	private String padraoPh;
	private String padraoCor;
	private String padraoCloro;
	private String padraoFluor;
	private String padraoFerro;
	private String padraoColiformesTotais;
	private String padraoColiformesfecais;
	
	private String representacaoNumericaCodBarraFormatada;
	private String representacaoNumericaCodBarraSemDigito;
	private String valorTotalConta;
	private String origem;
	private String grupoFaturamento;
	private String sequencialImpressao;
	private String indicadorCodigoBarras;
	private String codigoRotaSequencialRota;

	public EmitirContaTipo2Helper() {
	}
	
	public EmitirContaTipo2Helper(String idConta, String nomeCliente, String endereco, 
			String bairro, String mesAnoConta, String dataVencimento, String inscricao,
			String matricula, String rota, String categoria, String qtdeEconomia,
			String hidrometro, String volFaturado, String descricaoTipoConsumo,
			String numeroConta, String dtLeituraAnterior, String dtLeituraAtual,
			String diasConsumo, String leituraAnterior, String leituraAtual,
			String consumoMedio, String mesAno1Conta, String consumo1Conta,
			String mesAno2Conta, String consumo2Conta, String mesAno3Conta,
			String consumo3Conta, String mesAno4Conta, String consumo4Conta,
			String mesAno5Conta, String consumo5Conta, String mesAno6Conta,
			String consumo6Conta, Collection colecaoContaLinhasDescricaoServicosTarifasTotalHelper,
			String msgConta, String msgLinha1Conta,
			String msgLinha2Conta, String valorMedioTurbidez, String padraoTurbidez,
			String valorMedioPh, String valorMedioCor, String valorMedioCloro,
			String valorMedioFluor, String valorMedioFerro, String valorMedioColiformesTotais,
			String valorMedioColiformesfecais, String representacaoNumericaCodBarraFormatada,
			String representacaoNumericaCodBarraSemDigito,String enderecoLinha3,
			String padraoPh, String padraoCor, String padraoCloro,
			String padraoFluor, String padraoFerro, 
			String padraoColiformesTotais,	String padraoColiformesfecais,String origem,String datasVencimentos) {
		
			this.nomeCliente = nomeCliente;
			this.endereco = endereco;
			this.bairro = bairro;
			this.mesAnoConta = mesAnoConta;
			this.dataVencimento = dataVencimento;
			this.inscricao = inscricao;
			this.matricula = matricula;
			this.rota = rota;
			this.categoria = categoria;
			this.qtdeEconomia = qtdeEconomia;
			this.hidrometro = hidrometro;
			this.volFaturado = volFaturado;
			this.descricaoTipoConsumo = descricaoTipoConsumo;
			this.numeroConta = numeroConta;
			this.dtLeituraAnterior = dtLeituraAnterior;
			this.dtLeituraAtual = dtLeituraAtual;
			this.diasConsumo = diasConsumo;
			this.leituraAnterior = leituraAnterior;
			this.leituraAtual = leituraAtual;
			this.consumoMedio = consumoMedio;
			this.mesAno1Conta = mesAno1Conta;
			this.consumo1Conta = consumo1Conta;
			this.mesAno2Conta = mesAno2Conta;
			this.consumo2Conta = consumo2Conta;
			this.mesAno3Conta = mesAno3Conta;
			this.consumo3Conta = consumo3Conta;
			this.mesAno4Conta = mesAno4Conta;
			this.consumo4Conta = consumo4Conta;
			this.mesAno5Conta = mesAno5Conta;
			this.consumo5Conta = consumo5Conta;
			this.mesAno6Conta = mesAno6Conta;
			this.consumo6Conta = consumo6Conta;
			this.colecaoContaLinhasDescricaoServicosTarifasTotalHelper = 
				colecaoContaLinhasDescricaoServicosTarifasTotalHelper;
			this.msgConta = msgConta;
			this.msgLinha1Conta = msgLinha1Conta;
			this.msgLinha2Conta = msgLinha2Conta;
			this.valorMedioTurbidez = valorMedioTurbidez;
			this.padraoTurbidez = padraoTurbidez;
			this.valorMedioPh = valorMedioPh;
			this.valorMedioCor = valorMedioCor;
			this.valorMedioCloro = valorMedioCloro;
			this.valorMedioFluor = valorMedioFluor;
			this.valorMedioFerro = valorMedioFerro;
			this.valorMedioColiformesTotais = valorMedioColiformesTotais;
			this.valorMedioColiformesfecais = valorMedioColiformesfecais;
			this.representacaoNumericaCodBarraFormatada = representacaoNumericaCodBarraFormatada;
			this.representacaoNumericaCodBarraSemDigito = representacaoNumericaCodBarraSemDigito;
			this.enderecoLinha3 = enderecoLinha3;
			this.padraoPh = padraoPh;
			this.padraoCor = padraoCor;
			this.padraoCloro = padraoCloro;
			this.padraoFluor = padraoFluor;
			this.padraoFerro = padraoFerro;
			this.padraoColiformesTotais = padraoColiformesTotais;
			this.padraoColiformesfecais = padraoColiformesfecais;
			this.origem = origem;
			this.datasVencimentos = datasVencimentos;
		
	}
	
	public void setDatasVencimentos(String datasVencimentos) {
		this.datasVencimentos = datasVencimentos;
	}

	public String getMsgLinha5Conta() {
		return msgLinha5Conta;
	}

	public void setMsgLinha5Conta(String msgLinha5Conta) {
		this.msgLinha5Conta = msgLinha5Conta;
	}

	public String getMsgLinha3Conta() {
		return msgLinha3Conta;
	}

	public void setMsgLinha3Conta(String msgLinha3Conta) {
		this.msgLinha3Conta = msgLinha3Conta;
	}

	public String getMsgLinha4Conta() {
		return msgLinha4Conta;
	}

	public void setMsgLinha4Conta(String msgLinha4Conta) {
		this.msgLinha4Conta = msgLinha4Conta;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Collection getColecaoContaLinhasDescricaoServicosTarifasTotalHelper() {
		return colecaoContaLinhasDescricaoServicosTarifasTotalHelper;
	}

	public void setColecaoContaLinhasDescricaoServicosTarifasTotalHelper(
			Collection colecaoContaLinhasDescricaoServicosTarifasTotalHelper) {
		this.colecaoContaLinhasDescricaoServicosTarifasTotalHelper = colecaoContaLinhasDescricaoServicosTarifasTotalHelper;
	}

	public String getConsumo1Conta() {
		return consumo1Conta;
	}

	public void setConsumo1Conta(String consumo1Conta) {
		this.consumo1Conta = consumo1Conta;
	}

	public String getConsumo2Conta() {
		return consumo2Conta;
	}

	public void setConsumo2Conta(String consumo2Conta) {
		this.consumo2Conta = consumo2Conta;
	}

	public String getConsumo3Conta() {
		return consumo3Conta;
	}

	public void setConsumo3Conta(String consumo3Conta) {
		this.consumo3Conta = consumo3Conta;
	}

	public String getConsumo4Conta() {
		return consumo4Conta;
	}

	public void setConsumo4Conta(String consumo4Conta) {
		this.consumo4Conta = consumo4Conta;
	}

	public String getConsumo5Conta() {
		return consumo5Conta;
	}

	public void setConsumo5Conta(String consumo5Conta) {
		this.consumo5Conta = consumo5Conta;
	}

	public String getConsumo6Conta() {
		return consumo6Conta;
	}

	public void setConsumo6Conta(String consumo6Conta) {
		this.consumo6Conta = consumo6Conta;
	}

	public String getConsumoMedio() {
		return consumoMedio;
	}

	public void setConsumoMedio(String consumoMedio) {
		this.consumoMedio = consumoMedio;
	}

	//utilizado no tipo de conta 2
	public String getDatasVencimentos(){
		return this.datasVencimentos;
	}
	

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getDescricaoTipoConsumo() {
		return descricaoTipoConsumo;
	}

	public void setDescricaoTipoConsumo(String descricaoTipoConsumo) {
		this.descricaoTipoConsumo = descricaoTipoConsumo;
	}

	public String getDiasConsumo() {
		return diasConsumo;
	}

	public void setDiasConsumo(String diasConsumo) {
		this.diasConsumo = diasConsumo;
	}

	public String getDtLeituraAnterior() {
		return dtLeituraAnterior;
	}

	public void setDtLeituraAnterior(String dtLeituraAnterior) {
		this.dtLeituraAnterior = dtLeituraAnterior;
	}

	public String getDtLeituraAtual() {
		return dtLeituraAtual;
	}

	public void setDtLeituraAtual(String dtLeituraAtual) {
		this.dtLeituraAtual = dtLeituraAtual;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getHidrometro() {
		return hidrometro;
	}

	public void setHidrometro(String hidrometro) {
		this.hidrometro = hidrometro;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getLeituraAnterior() {
		return leituraAnterior;
	}

	public void setLeituraAnterior(String leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}

	public String getLeituraAtual() {
		return leituraAtual;
	}

	public void setLeituraAtual(String leituraAtual) {
		this.leituraAtual = leituraAtual;
	}

	public String getMesAno1Conta() {
		return mesAno1Conta;
	}

	public void setMesAno1Conta(String mesAno1Conta) {
		this.mesAno1Conta = mesAno1Conta;
	}

	public String getMesAno2Conta() {
		return mesAno2Conta;
	}

	public void setMesAno2Conta(String mesAno2Conta) {
		this.mesAno2Conta = mesAno2Conta;
	}

	public String getMesAno3Conta() {
		return mesAno3Conta;
	}

	public void setMesAno3Conta(String mesAno3Conta) {
		this.mesAno3Conta = mesAno3Conta;
	}

	public String getMesAno4Conta() {
		return mesAno4Conta;
	}

	public void setMesAno4Conta(String mesAno4Conta) {
		this.mesAno4Conta = mesAno4Conta;
	}

	public String getMesAno5Conta() {
		return mesAno5Conta;
	}

	public void setMesAno5Conta(String mesAno5Conta) {
		this.mesAno5Conta = mesAno5Conta;
	}

	public String getMesAno6Conta() {
		return mesAno6Conta;
	}

	public void setMesAno6Conta(String mesAno6Conta) {
		this.mesAno6Conta = mesAno6Conta;
	}

	public String getMesAnoConta() {
		return mesAnoConta;
	}

	public void setMesAnoConta(String mesAnoConta) {
		this.mesAnoConta = mesAnoConta;
	}

	public String getMsgConta() {
		return msgConta;
	}

	public void setMsgConta(String msgConta) {
		this.msgConta = msgConta;
	}

	public String getMsgLinha1Conta() {
		return msgLinha1Conta;
	}

	public void setMsgLinha1Conta(String msgLinha1Conta) {
		this.msgLinha1Conta = msgLinha1Conta;
	}

	public String getMsgLinha2Conta() {
		return msgLinha2Conta;
	}

	public void setMsgLinha2Conta(String msgLinha2Conta) {
		this.msgLinha2Conta = msgLinha2Conta;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getPadraoTurbidez() {
		return padraoTurbidez;
	}

	public void setPadraoTurbidez(String padraoTurbidez) {
		this.padraoTurbidez = padraoTurbidez;
	}

	public String getQtdeEconomia() {
		return qtdeEconomia;
	}

	public void setQtdeEconomia(String qtdeEconomia) {
		this.qtdeEconomia = qtdeEconomia;
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

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}

	public String getValorMedioCloro() {
		return valorMedioCloro;
	}

	public void setValorMedioCloro(String valorMedioCloro) {
		this.valorMedioCloro = valorMedioCloro;
	}

	public String getValorMedioColiformesfecais() {
		return valorMedioColiformesfecais;
	}

	public void setValorMedioColiformesfecais(String valorMedioColiformesfecais) {
		this.valorMedioColiformesfecais = valorMedioColiformesfecais;
	}

	public String getValorMedioColiformesTotais() {
		return valorMedioColiformesTotais;
	}

	public void setValorMedioColiformesTotais(String valorMedioColiformesTotais) {
		this.valorMedioColiformesTotais = valorMedioColiformesTotais;
	}

	public String getValorMedioCor() {
		return valorMedioCor;
	}

	public void setValorMedioCor(String valorMedioCor) {
		this.valorMedioCor = valorMedioCor;
	}

	public String getValorMedioFerro() {
		return valorMedioFerro;
	}

	public void setValorMedioFerro(String valorMedioFerro) {
		this.valorMedioFerro = valorMedioFerro;
	}

	public String getValorMedioFluor() {
		return valorMedioFluor;
	}

	public void setValorMedioFluor(String valorMedioFluor) {
		this.valorMedioFluor = valorMedioFluor;
	}

	public String getValorMedioPh() {
		return valorMedioPh;
	}

	public void setValorMedioPh(String valorMedioPh) {
		this.valorMedioPh = valorMedioPh;
	}

	public String getValorMedioTurbidez() {
		return valorMedioTurbidez;
	}

	public void setValorMedioTurbidez(String valorMedioTurbidez) {
		this.valorMedioTurbidez = valorMedioTurbidez;
	}

	public String getVolFaturado() {
		return volFaturado;
	}

	public void setVolFaturado(String volFaturado) {
		this.volFaturado = volFaturado;
	}

	public String getValorTotalConta() {
		return valorTotalConta;
	}

	public void setValorTotalConta(String valorTotalConta) {
		this.valorTotalConta = valorTotalConta;
	}

	public String getEnderecoLinha3() {
		return enderecoLinha3;
	}

	public void setEnderecoLinha3(String enderecoLinha3) {
		this.enderecoLinha3 = enderecoLinha3;
	}

	public String getPadraoCloro() {
		return padraoCloro;
	}

	public void setPadraoCloro(String padraoCloro) {
		this.padraoCloro = padraoCloro;
	}

	public String getPadraoColiformesfecais() {
		return padraoColiformesfecais;
	}

	public void setPadraoColiformesfecais(String padraoColiformesfecais) {
		this.padraoColiformesfecais = padraoColiformesfecais;
	}

	public String getPadraoColiformesTotais() {
		return padraoColiformesTotais;
	}

	public void setPadraoColiformesTotais(String padraoColiformesTotais) {
		this.padraoColiformesTotais = padraoColiformesTotais;
	}

	public String getPadraoCor() {
		return padraoCor;
	}

	public void setPadraoCor(String padraoCor) {
		this.padraoCor = padraoCor;
	}

	public String getPadraoFerro() {
		return padraoFerro;
	}

	public void setPadraoFerro(String padraoFerro) {
		this.padraoFerro = padraoFerro;
	}

	public String getPadraoFluor() {
		return padraoFluor;
	}

	public void setPadraoFluor(String padraoFluor) {
		this.padraoFluor = padraoFluor;
	}

	public String getPadraoPh() {
		return padraoPh;
	}

	public void setPadraoPh(String padraoPh) {
		this.padraoPh = padraoPh;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getGrupoFaturamento() {
		return grupoFaturamento;
	}

	public void setGrupoFaturamento(String grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}

	public String getSequencialImpressao() {
		return sequencialImpressao;
	}

	public void setSequencialImpressao(String sequencialImpressao) {
		this.sequencialImpressao = sequencialImpressao;
	}

	public String getIndicadorCodigoBarras() {
		return indicadorCodigoBarras;
	}

	public void setIndicadorCodigoBarras(String indicadorCodigoBarras) {
		this.indicadorCodigoBarras = indicadorCodigoBarras;
	}

	public String getCodigoRotaSequencialRota() {
		return codigoRotaSequencialRota;
	}

	public void setCodigoRotaSequencialRota(String codigoRotaSequencialRota) {
		this.codigoRotaSequencialRota = codigoRotaSequencialRota;
	}
	
}
