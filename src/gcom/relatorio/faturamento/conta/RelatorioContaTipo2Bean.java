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
package gcom.relatorio.faturamento.conta;

import gcom.faturamento.bean.EmitirContaTipo2Helper;
import gcom.relatorio.RelatorioBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * [UC ] 
 * @author Vivianne Sousa
 * @date 28/06/2007
 */
public class RelatorioContaTipo2Bean implements RelatorioBean {
	
	private JRBeanCollectionDataSource arrayJRDetailConta1;
	private ArrayList arrayRelatorioContaTipo2DetailConta1Bean;
	
	private String nomeCliente1;
	private String endereco1; 
	private String bairro1;
	private String endereco1Linha3; 
	private String mesAnoConta1;
	private String dataVencimento1;
	private String inscricao1;
	private String matricula1;
	private String rota1;
	private String categoria1;
	private String qtdeEconomia1;
	private String hidrometro1;
	private String volFaturado1; 
	private String descricaoTipoConsumo1;
	private String numeroConta1;
	private String dtLeituraAnterior1;
	private String dtLeituraAtual1;
	private String diasConsumo1;
	private String leituraAnterior1;
	private String leituraAtual1;
	private String consumoMedio1;
	private String mesAno1Conta1;
	private String consumo1Conta1;
	private String mesAno2Conta1;
	private String consumo2Conta1;
	private String mesAno3Conta1;
	private String consumo3Conta1;
	private String mesAno4Conta1;
	private String consumo4Conta1;
	private String mesAno5Conta1;
	private String consumo5Conta1;
	private String mesAno6Conta1;
	private String consumo6Conta1;
	private Collection colecaoContaLinhasDescricaoServicosTarifasTotalHelper1;
	private String msgConta1;
	private String datasVencimento1;
	private String msgLinha1Conta1;
	private String msgLinha2Conta1;
	private String msgLinha3Conta1;
	private String msgLinha4Conta1;
	private String msgLinha5Conta1;
	private String valorMedioTurbidez1;
	private String valorMedioPh1;
	private String valorMedioCor1;
	private String valorMedioCloro1;
	private String valorMedioFluor1;
	private String valorMedioFerro1;
	private String valorMedioColiformesTotais1;
	private String valorMedioColiformesfecais1;
	private String representacaoNumericaCodBarraFormatada1;
	private String representacaoNumericaCodBarraSemDigito1; 
	private String valorTotalConta1;
	private String origem1;
	private String grupoFaturamento1;
	private String sequencialImpressao1;
	private String indicadorCodigoBarras1;
	private String codigoRotaSequencialRota1;
	
	private String padraoTurbidez;
	private String padraoPh;
	private String padraoCor;
	private String padraoCloro;
	private String padraoFluor;
	private String padraoFerro;
	private String padraoColiformesTotais;
	private String padraoColiformesfecais;

	
	private JRBeanCollectionDataSource arrayJRDetailConta2;
	private ArrayList arrayRelatorioContaTipo2DetailConta2Bean;
	
	private String nomeCliente2;
	private String endereco2; 
	private String bairro2;
	private String endereco2Linha3; 
	private String mesAnoConta2;
	private String dataVencimento2;
	private String inscricao2;
	private String matricula2;
	private String rota2;
	private String categoria2;
	private String qtdeEconomia2;
	private String hidrometro2;
	private String volFaturado2; 
	private String descricaoTipoConsumo2;
	private String numeroConta2;
	private String dtLeituraAnterior2;
	private String dtLeituraAtual2;
	private String diasConsumo2;
	private String leituraAnterior2;
	private String leituraAtual2;
	private String consumoMedio2;
	private String mesAno1Conta2;
	private String consumo1Conta2;
	private String mesAno2Conta2;
	private String consumo2Conta2;
	private String mesAno3Conta2;
	private String consumo3Conta2;
	private String mesAno4Conta2;
	private String consumo4Conta2;
	private String mesAno5Conta2;
	private String consumo5Conta2;
	private String mesAno6Conta2;
	private String consumo6Conta2;
	private Collection colecaoContaLinhasDescricaoServicosTarifasTotalHelper2;
	private String msgConta2;
	private String datasVencimento2;
	private String msgLinha1Conta2;
	private String msgLinha2Conta2;
	private String msgLinha3Conta2;
	private String msgLinha4Conta2;
	private String msgLinha5Conta2;
	private String valorMedioTurbidez2;
	private String padraoTurbidez2;
	private String valorMedioPh2;
	private String valorMedioCor2;
	private String valorMedioCloro2;
	private String valorMedioFluor2;
	private String valorMedioFerro2;
	private String valorMedioColiformesTotais2;
	private String valorMedioColiformesfecais2;
	private String representacaoNumericaCodBarraFormatada2;
	private String representacaoNumericaCodBarraSemDigito2; 
	private String valorTotalConta2;
	private String origem2;
	private String grupoFaturamento2;
	private String sequencialImpressao2;
	private String indicadorCodigoBarras2;
	private String codigoRotaSequencialRota2;
	
    public RelatorioContaTipo2Bean(EmitirContaTipo2Helper emitirContaTipo2HelperConta1,
    		EmitirContaTipo2Helper emitirContaTipo2HelperConta2) {
    	
		this.arrayRelatorioContaTipo2DetailConta1Bean = new ArrayList();
		this.arrayRelatorioContaTipo2DetailConta1Bean.addAll(
			this.gerarDetail(emitirContaTipo2HelperConta1.getColecaoContaLinhasDescricaoServicosTarifasTotalHelper(),1));
		
		this.arrayJRDetailConta1 = new JRBeanCollectionDataSource(this.arrayRelatorioContaTipo2DetailConta1Bean);
		
		this.nomeCliente1 = emitirContaTipo2HelperConta1.getNomeCliente();
		this.endereco1 = emitirContaTipo2HelperConta1.getEndereco(); 
		this.bairro1 = emitirContaTipo2HelperConta1.getBairro();
		this.endereco1Linha3 = emitirContaTipo2HelperConta1.getEnderecoLinha3();
		this.mesAnoConta1 = emitirContaTipo2HelperConta1.getMesAnoConta();
		this.dataVencimento1 = emitirContaTipo2HelperConta1.getDataVencimento();
		this.inscricao1 = emitirContaTipo2HelperConta1.getInscricao();
		this.matricula1 = emitirContaTipo2HelperConta1.getMatricula();
		
		this.rota1 = emitirContaTipo2HelperConta1.getRota();
		this.categoria1 = emitirContaTipo2HelperConta1.getCategoria();
		this.qtdeEconomia1 = emitirContaTipo2HelperConta1.getQtdeEconomia();
		this.hidrometro1 = emitirContaTipo2HelperConta1.getHidrometro();
		this.volFaturado1 = emitirContaTipo2HelperConta1.getVolFaturado(); 
		this.descricaoTipoConsumo1 = emitirContaTipo2HelperConta1.getDescricaoTipoConsumo();
		this.numeroConta1 = emitirContaTipo2HelperConta1.getNumeroConta();
		this.dtLeituraAnterior1 = emitirContaTipo2HelperConta1.getDtLeituraAnterior();
		this.dtLeituraAtual1 = emitirContaTipo2HelperConta1.getDtLeituraAtual();
		this.diasConsumo1 = emitirContaTipo2HelperConta1.getDiasConsumo();
		this.leituraAnterior1 = emitirContaTipo2HelperConta1.getLeituraAnterior();
		this.leituraAtual1 = emitirContaTipo2HelperConta1.getLeituraAtual();
		this.consumoMedio1 = emitirContaTipo2HelperConta1.getConsumoMedio();
		this.mesAno1Conta1 = emitirContaTipo2HelperConta1.getMesAno1Conta();
		this.consumo1Conta1 = emitirContaTipo2HelperConta1.getConsumo1Conta();
		this.mesAno2Conta1 = emitirContaTipo2HelperConta1.getMesAno2Conta();
		this.consumo2Conta1 = emitirContaTipo2HelperConta1.getConsumo2Conta();
		this.mesAno3Conta1 = emitirContaTipo2HelperConta1.getMesAno3Conta();
		this.consumo3Conta1 = emitirContaTipo2HelperConta1.getConsumo3Conta();
		this.mesAno4Conta1 = emitirContaTipo2HelperConta1.getMesAno4Conta();
		this.consumo4Conta1 = emitirContaTipo2HelperConta1.getConsumo4Conta();
		this.mesAno5Conta1 = emitirContaTipo2HelperConta1.getMesAno5Conta();
		this.consumo5Conta1 = emitirContaTipo2HelperConta1.getConsumo5Conta();
		this.mesAno6Conta1 = emitirContaTipo2HelperConta1.getMesAno6Conta();
		this.consumo6Conta1 = emitirContaTipo2HelperConta1.getConsumo6Conta();
		this.msgConta1 = emitirContaTipo2HelperConta1.getMsgConta();
		this.datasVencimento1 = emitirContaTipo2HelperConta1.getDatasVencimentos();
		this.msgLinha1Conta1 = emitirContaTipo2HelperConta1.getMsgLinha1Conta();
		this.msgLinha2Conta1 = emitirContaTipo2HelperConta1.getMsgLinha2Conta();
		this.msgLinha3Conta1 = emitirContaTipo2HelperConta1.getMsgLinha3Conta();
		this.msgLinha4Conta1 = emitirContaTipo2HelperConta1.getMsgLinha4Conta();
		this.msgLinha5Conta1 = emitirContaTipo2HelperConta1.getMsgLinha5Conta();
		this.valorMedioTurbidez1 = emitirContaTipo2HelperConta1.getValorMedioTurbidez();
		this.valorMedioPh1 = emitirContaTipo2HelperConta1.getValorMedioPh();
		this.valorMedioCor1 = emitirContaTipo2HelperConta1.getValorMedioCor();
		this.valorMedioCloro1 = emitirContaTipo2HelperConta1.getValorMedioCloro();
		this.valorMedioFluor1 = emitirContaTipo2HelperConta1.getValorMedioFluor();
		this.valorMedioFerro1 = emitirContaTipo2HelperConta1.getValorMedioFerro();
		this.valorMedioColiformesTotais1 = emitirContaTipo2HelperConta1.getValorMedioColiformesTotais();
		this.valorMedioColiformesfecais1 = emitirContaTipo2HelperConta1.getValorMedioColiformesfecais();
		this.representacaoNumericaCodBarraFormatada1 = emitirContaTipo2HelperConta1.getRepresentacaoNumericaCodBarraFormatada();
		this.representacaoNumericaCodBarraSemDigito1 = emitirContaTipo2HelperConta1.getRepresentacaoNumericaCodBarraSemDigito(); 
		this.valorTotalConta1 = emitirContaTipo2HelperConta1.getValorTotalConta();
		this.origem1 = emitirContaTipo2HelperConta1.getOrigem();
		this.grupoFaturamento1 = emitirContaTipo2HelperConta1.getGrupoFaturamento();
		this.sequencialImpressao1 = emitirContaTipo2HelperConta1.getSequencialImpressao();
		this.indicadorCodigoBarras1 = emitirContaTipo2HelperConta1.getIndicadorCodigoBarras();
		this.codigoRotaSequencialRota1 = emitirContaTipo2HelperConta1.getCodigoRotaSequencialRota();
		
		this.padraoTurbidez = emitirContaTipo2HelperConta1.getPadraoTurbidez();
		this.padraoPh = emitirContaTipo2HelperConta1.getPadraoPh();
		this.padraoCor = emitirContaTipo2HelperConta1.getPadraoCor();
		this.padraoCloro = emitirContaTipo2HelperConta1.getPadraoCloro();
		this.padraoFluor = emitirContaTipo2HelperConta1.getPadraoFluor();
		this.padraoFerro = emitirContaTipo2HelperConta1.getPadraoFerro();
		this.padraoColiformesTotais = emitirContaTipo2HelperConta1.getPadraoColiformesTotais();
		this.padraoColiformesfecais = emitirContaTipo2HelperConta1.getPadraoColiformesfecais();
		
		this.arrayRelatorioContaTipo2DetailConta2Bean = new ArrayList();
		this.arrayRelatorioContaTipo2DetailConta2Bean.addAll(
			this.gerarDetail(emitirContaTipo2HelperConta2.getColecaoContaLinhasDescricaoServicosTarifasTotalHelper(),2));				

		this.arrayJRDetailConta2 = new JRBeanCollectionDataSource(this.arrayRelatorioContaTipo2DetailConta2Bean);
    	
		this.nomeCliente2 = emitirContaTipo2HelperConta2.getNomeCliente();
		this.endereco2 = emitirContaTipo2HelperConta2.getEndereco(); 
		this.bairro2 = emitirContaTipo2HelperConta2.getBairro();
		this.endereco2Linha3 = emitirContaTipo2HelperConta2.getEnderecoLinha3();
		this.mesAnoConta2 = emitirContaTipo2HelperConta2.getMesAnoConta();
		this.dataVencimento2 = emitirContaTipo2HelperConta2.getDataVencimento();
		this.inscricao2 = emitirContaTipo2HelperConta2.getInscricao();
		this.matricula2 = emitirContaTipo2HelperConta2.getMatricula();
		this.rota2 = emitirContaTipo2HelperConta2.getRota();
		this.categoria2 = emitirContaTipo2HelperConta2.getCategoria();
		this.qtdeEconomia2 = emitirContaTipo2HelperConta2.getQtdeEconomia();
		this.hidrometro2 = emitirContaTipo2HelperConta2.getHidrometro();
		this.volFaturado2 = emitirContaTipo2HelperConta2.getVolFaturado(); 
		this.descricaoTipoConsumo2 = emitirContaTipo2HelperConta2.getDescricaoTipoConsumo();
		this.numeroConta2 = emitirContaTipo2HelperConta2.getNumeroConta();
		this.dtLeituraAnterior2 = emitirContaTipo2HelperConta2.getDtLeituraAnterior();
		this.dtLeituraAtual2 = emitirContaTipo2HelperConta2.getDtLeituraAtual();
		this.diasConsumo2 = emitirContaTipo2HelperConta2.getDiasConsumo();
		this.leituraAnterior2 = emitirContaTipo2HelperConta2.getLeituraAnterior();
		this.leituraAtual2 = emitirContaTipo2HelperConta2.getLeituraAtual();
		this.consumoMedio2 = emitirContaTipo2HelperConta2.getConsumoMedio();
		this.mesAno1Conta2 = emitirContaTipo2HelperConta2.getMesAno1Conta();
		this.consumo1Conta2 = emitirContaTipo2HelperConta2.getConsumo1Conta();
		this.mesAno2Conta2 = emitirContaTipo2HelperConta2.getMesAno2Conta();
		this.consumo2Conta2 = emitirContaTipo2HelperConta2.getConsumo2Conta();
		this.mesAno3Conta2 = emitirContaTipo2HelperConta2.getMesAno3Conta();
		this.consumo3Conta2 = emitirContaTipo2HelperConta2.getConsumo3Conta();
		this.mesAno4Conta2 = emitirContaTipo2HelperConta2.getMesAno4Conta();
		this.consumo4Conta2 = emitirContaTipo2HelperConta2.getConsumo4Conta();
		this.mesAno5Conta2 = emitirContaTipo2HelperConta2.getMesAno5Conta();
		this.consumo5Conta2 = emitirContaTipo2HelperConta2.getConsumo5Conta();
		this.mesAno6Conta2 = emitirContaTipo2HelperConta2.getMesAno6Conta();
		this.consumo6Conta2 = emitirContaTipo2HelperConta2.getConsumo6Conta();
		this.msgConta2 = emitirContaTipo2HelperConta2.getMsgConta();
		this.datasVencimento2 = emitirContaTipo2HelperConta2.getDatasVencimentos();
		this.msgLinha1Conta2 = emitirContaTipo2HelperConta2.getMsgLinha1Conta();
		this.msgLinha2Conta2 = emitirContaTipo2HelperConta2.getMsgLinha2Conta();
		this.msgLinha3Conta2 = emitirContaTipo2HelperConta2.getMsgLinha3Conta();
		this.msgLinha4Conta2 = emitirContaTipo2HelperConta2.getMsgLinha4Conta();
		this.msgLinha5Conta2 = emitirContaTipo2HelperConta2.getMsgLinha5Conta();
		this.valorMedioTurbidez2 = emitirContaTipo2HelperConta2.getValorMedioTurbidez();
		this.valorMedioPh2 = emitirContaTipo2HelperConta2.getValorMedioPh();
		this.valorMedioCor2 = emitirContaTipo2HelperConta2.getValorMedioCor();
		this.valorMedioCloro2 = emitirContaTipo2HelperConta2.getValorMedioCloro();
		this.valorMedioFluor2 = emitirContaTipo2HelperConta2.getValorMedioFluor();
		this.valorMedioFerro2 = emitirContaTipo2HelperConta2.getValorMedioFerro();
		this.valorMedioColiformesTotais2 = emitirContaTipo2HelperConta2.getValorMedioColiformesTotais();
		this.valorMedioColiformesfecais2 = emitirContaTipo2HelperConta2.getValorMedioColiformesfecais();
		this.representacaoNumericaCodBarraFormatada2 = emitirContaTipo2HelperConta2.getRepresentacaoNumericaCodBarraFormatada();
		this.representacaoNumericaCodBarraSemDigito2 = emitirContaTipo2HelperConta2.getRepresentacaoNumericaCodBarraSemDigito(); 
		this.valorTotalConta2 = emitirContaTipo2HelperConta2.getValorTotalConta();
		this.origem2 = emitirContaTipo2HelperConta2.getOrigem();
		this.grupoFaturamento2 = emitirContaTipo2HelperConta2.getGrupoFaturamento();
		this.sequencialImpressao2 = emitirContaTipo2HelperConta2.getSequencialImpressao();
		this.indicadorCodigoBarras2 = emitirContaTipo2HelperConta2.getIndicadorCodigoBarras();
		this.codigoRotaSequencialRota2 = emitirContaTipo2HelperConta2.getCodigoRotaSequencialRota();
   }

    
    private Collection gerarDetail(Collection colecaoLinhasServicos,int tipoRelatorio){
    	
		Collection colecaoDetail = new ArrayList();
		
		if(colecaoLinhasServicos != null && !colecaoLinhasServicos.isEmpty()){
	    	Iterator iterator = colecaoLinhasServicos.iterator();		
			while (iterator.hasNext()) {
				
				ContaLinhasDescricaoServicosTarifasTotalHelper linhasDescricaoServicosTarifasTotalHelper 
					= (ContaLinhasDescricaoServicosTarifasTotalHelper) iterator.next();
				
				Object relatorio =  null;
				
				if(tipoRelatorio == 1){
					relatorio = new RelatorioContaTipo2DetailConta1Bean(
						linhasDescricaoServicosTarifasTotalHelper.getDescricaoServicosTarifas(), 
						linhasDescricaoServicosTarifasTotalHelper.getValor());
					
				}else{
					relatorio = new RelatorioContaTipo2DetailConta2Bean(
						linhasDescricaoServicosTarifasTotalHelper.getDescricaoServicosTarifas(), 
						linhasDescricaoServicosTarifasTotalHelper.getValor());
					
				}
				
			
				colecaoDetail.add(relatorio);
	    	
			}
		}
		
		return colecaoDetail;
    }
    
	public String getMsgLinha5Conta1() {
		return msgLinha5Conta1;
	}


	public void setMsgLinha5Conta1(String msgLinha5Conta1) {
		this.msgLinha5Conta1 = msgLinha5Conta1;
	}


	public String getMsgLinha5Conta2() {
		return msgLinha5Conta2;
	}


	public void setMsgLinha5Conta2(String msgLinha5Conta2) {
		this.msgLinha5Conta2 = msgLinha5Conta2;
	}


	public String getMsgLinha3Conta1() {
		return msgLinha3Conta1;
	}


	public void setMsgLinha3Conta1(String msgLinha3Conta1) {
		this.msgLinha3Conta1 = msgLinha3Conta1;
	}


	public String getMsgLinha3Conta2() {
		return msgLinha3Conta2;
	}


	public void setMsgLinha3Conta2(String msgLinha3Conta2) {
		this.msgLinha3Conta2 = msgLinha3Conta2;
	}


	public String getMsgLinha4Conta1() {
		return msgLinha4Conta1;
	}


	public void setMsgLinha4Conta1(String msgLinha4Conta1) {
		this.msgLinha4Conta1 = msgLinha4Conta1;
	}


	public String getMsgLinha4Conta2() {
		return msgLinha4Conta2;
	}


	public void setMsgLinha4Conta2(String msgLinha4Conta2) {
		this.msgLinha4Conta2 = msgLinha4Conta2;
	}


	public String getMatricula1() {
		return matricula1;
	}

	public void setMatricula1(String matricula1) {
		this.matricula1 = matricula1;
	}

	public String getMatricula2() {
		return matricula2;
	}

	public void setMatricula2(String matricula2) {
		this.matricula2 = matricula2;
	}

	public JRBeanCollectionDataSource getArrayJRDetailConta1() {
		return arrayJRDetailConta1;
	}

	public void setArrayJRDetailConta1(
			JRBeanCollectionDataSource arrayJRDetailConta1) {
		this.arrayJRDetailConta1 = arrayJRDetailConta1;
	}

	public JRBeanCollectionDataSource getArrayJRDetailConta2() {
		return arrayJRDetailConta2;
	}

	public void setArrayJRDetailConta2(
			JRBeanCollectionDataSource arrayJRDetailConta2) {
		this.arrayJRDetailConta2 = arrayJRDetailConta2;
	}


	public ArrayList getArrayRelatorioContaTipo2DetailConta1Bean() {
		return arrayRelatorioContaTipo2DetailConta1Bean;
	}

	public void setArrayRelatorioContaTipo2DetailConta1Bean(
			ArrayList arrayRelatorioContaTipo2DetailConta1Bean) {
		this.arrayRelatorioContaTipo2DetailConta1Bean = arrayRelatorioContaTipo2DetailConta1Bean;
	}

	public ArrayList getArrayRelatorioContaTipo2DetailConta2Bean() {
		return arrayRelatorioContaTipo2DetailConta2Bean;
	}

	public void setArrayRelatorioContaTipo2DetailConta2Bean(
			ArrayList arrayRelatorioContaTipo2DetailConta2Bean) {
		this.arrayRelatorioContaTipo2DetailConta2Bean = arrayRelatorioContaTipo2DetailConta2Bean;
	}

	public String getBairro1() {
		return bairro1;
	}

	public void setBairro1(String bairro1) {
		this.bairro1 = bairro1;
	}

	public String getBairro2() {
		return bairro2;
	}

	public void setBairro2(String bairro2) {
		this.bairro2 = bairro2;
	}

	public String getCategoria1() {
		return categoria1;
	}

	public void setCategoria1(String categoria1) {
		this.categoria1 = categoria1;
	}

	public String getCategoria2() {
		return categoria2;
	}

	public void setCategoria2(String categoria2) {
		this.categoria2 = categoria2;
	}

	public Collection getColecaoContaLinhasDescricaoServicosTarifasTotalHelper1() {
		return colecaoContaLinhasDescricaoServicosTarifasTotalHelper1;
	}

	public void setColecaoContaLinhasDescricaoServicosTarifasTotalHelper1(
			Collection colecaoContaLinhasDescricaoServicosTarifasTotalHelper1) {
		this.colecaoContaLinhasDescricaoServicosTarifasTotalHelper1 = colecaoContaLinhasDescricaoServicosTarifasTotalHelper1;
	}

	public Collection getColecaoContaLinhasDescricaoServicosTarifasTotalHelper2() {
		return colecaoContaLinhasDescricaoServicosTarifasTotalHelper2;
	}

	public void setColecaoContaLinhasDescricaoServicosTarifasTotalHelper2(
			Collection colecaoContaLinhasDescricaoServicosTarifasTotalHelper2) {
		this.colecaoContaLinhasDescricaoServicosTarifasTotalHelper2 = colecaoContaLinhasDescricaoServicosTarifasTotalHelper2;
	}

	public String getConsumo1Conta1() {
		return consumo1Conta1;
	}

	public void setConsumo1Conta1(String consumo1Conta1) {
		this.consumo1Conta1 = consumo1Conta1;
	}

	public String getConsumo1Conta2() {
		return consumo1Conta2;
	}

	public void setConsumo1Conta2(String consumo1Conta2) {
		this.consumo1Conta2 = consumo1Conta2;
	}

	public String getConsumo2Conta1() {
		return consumo2Conta1;
	}

	public void setConsumo2Conta1(String consumo2Conta1) {
		this.consumo2Conta1 = consumo2Conta1;
	}

	public String getConsumo2Conta2() {
		return consumo2Conta2;
	}

	public void setConsumo2Conta2(String consumo2Conta2) {
		this.consumo2Conta2 = consumo2Conta2;
	}

	public String getConsumo3Conta1() {
		return consumo3Conta1;
	}

	public void setConsumo3Conta1(String consumo3Conta1) {
		this.consumo3Conta1 = consumo3Conta1;
	}

	public String getConsumo3Conta2() {
		return consumo3Conta2;
	}

	public void setConsumo3Conta2(String consumo3Conta2) {
		this.consumo3Conta2 = consumo3Conta2;
	}

	public String getConsumo4Conta1() {
		return consumo4Conta1;
	}

	public void setConsumo4Conta1(String consumo4Conta1) {
		this.consumo4Conta1 = consumo4Conta1;
	}

	public String getConsumo4Conta2() {
		return consumo4Conta2;
	}

	public void setConsumo4Conta2(String consumo4Conta2) {
		this.consumo4Conta2 = consumo4Conta2;
	}

	public String getConsumo5Conta1() {
		return consumo5Conta1;
	}

	public void setConsumo5Conta1(String consumo5Conta1) {
		this.consumo5Conta1 = consumo5Conta1;
	}

	public String getConsumo5Conta2() {
		return consumo5Conta2;
	}

	public void setConsumo5Conta2(String consumo5Conta2) {
		this.consumo5Conta2 = consumo5Conta2;
	}

	public String getConsumo6Conta1() {
		return consumo6Conta1;
	}

	public void setConsumo6Conta1(String consumo6Conta1) {
		this.consumo6Conta1 = consumo6Conta1;
	}

	public String getConsumo6Conta2() {
		return consumo6Conta2;
	}

	public void setConsumo6Conta2(String consumo6Conta2) {
		this.consumo6Conta2 = consumo6Conta2;
	}

	public String getConsumoMedio1() {
		return consumoMedio1;
	}

	public void setConsumoMedio1(String consumoMedio1) {
		this.consumoMedio1 = consumoMedio1;
	}

	public String getConsumoMedio2() {
		return consumoMedio2;
	}

	public void setConsumoMedio2(String consumoMedio2) {
		this.consumoMedio2 = consumoMedio2;
	}

	public String getDatasVencimento1() {
		return datasVencimento1;
	}

	public void setDatasVencimento1(String datasVencimento1) {
		this.datasVencimento1 = datasVencimento1;
	}

	public String getDatasVencimento2() {
		return datasVencimento2;
	}

	public void setDatasVencimento2(String datasVencimento2) {
		this.datasVencimento2 = datasVencimento2;
	}

	public String getDataVencimento1() {
		return dataVencimento1;
	}

	public void setDataVencimento1(String dataVencimento1) {
		this.dataVencimento1 = dataVencimento1;
	}

	public String getDataVencimento2() {
		return dataVencimento2;
	}

	public void setDataVencimento2(String dataVencimento2) {
		this.dataVencimento2 = dataVencimento2;
	}

	public String getDescricaoTipoConsumo1() {
		return descricaoTipoConsumo1;
	}

	public void setDescricaoTipoConsumo1(String descricaoTipoConsumo1) {
		this.descricaoTipoConsumo1 = descricaoTipoConsumo1;
	}

	public String getDescricaoTipoConsumo2() {
		return descricaoTipoConsumo2;
	}

	public void setDescricaoTipoConsumo2(String descricaoTipoConsumo2) {
		this.descricaoTipoConsumo2 = descricaoTipoConsumo2;
	}

	public String getDiasConsumo1() {
		return diasConsumo1;
	}

	public void setDiasConsumo1(String diasConsumo1) {
		this.diasConsumo1 = diasConsumo1;
	}

	public String getDiasConsumo2() {
		return diasConsumo2;
	}

	public void setDiasConsumo2(String diasConsumo2) {
		this.diasConsumo2 = diasConsumo2;
	}

	public String getDtLeituraAnterior1() {
		return dtLeituraAnterior1;
	}

	public void setDtLeituraAnterior1(String dtLeituraAnterior1) {
		this.dtLeituraAnterior1 = dtLeituraAnterior1;
	}

	public String getDtLeituraAnterior2() {
		return dtLeituraAnterior2;
	}

	public void setDtLeituraAnterior2(String dtLeituraAnterior2) {
		this.dtLeituraAnterior2 = dtLeituraAnterior2;
	}

	public String getDtLeituraAtual1() {
		return dtLeituraAtual1;
	}

	public void setDtLeituraAtual1(String dtLeituraAtual1) {
		this.dtLeituraAtual1 = dtLeituraAtual1;
	}

	public String getDtLeituraAtual2() {
		return dtLeituraAtual2;
	}

	public void setDtLeituraAtual2(String dtLeituraAtual2) {
		this.dtLeituraAtual2 = dtLeituraAtual2;
	}

	public String getEndereco1() {
		return endereco1;
	}

	public void setEndereco1(String endereco1) {
		this.endereco1 = endereco1;
	}

	public String getEndereco2() {
		return endereco2;
	}

	public void setEndereco2(String endereco2) {
		this.endereco2 = endereco2;
	}

	public String getHidrometro1() {
		return hidrometro1;
	}

	public void setHidrometro1(String hidrometro1) {
		this.hidrometro1 = hidrometro1;
	}

	public String getHidrometro2() {
		return hidrometro2;
	}

	public void setHidrometro2(String hidrometro2) {
		this.hidrometro2 = hidrometro2;
	}

	public String getInscricao1() {
		return inscricao1;
	}

	public void setInscricao1(String inscricao1) {
		this.inscricao1 = inscricao1;
	}

	public String getInscricao2() {
		return inscricao2;
	}

	public void setInscricao2(String inscricao2) {
		this.inscricao2 = inscricao2;
	}

	public String getLeituraAnterior1() {
		return leituraAnterior1;
	}

	public void setLeituraAnterior1(String leituraAnterior1) {
		this.leituraAnterior1 = leituraAnterior1;
	}

	public String getLeituraAnterior2() {
		return leituraAnterior2;
	}

	public void setLeituraAnterior2(String leituraAnterior2) {
		this.leituraAnterior2 = leituraAnterior2;
	}

	public String getLeituraAtual1() {
		return leituraAtual1;
	}

	public void setLeituraAtual1(String leituraAtual1) {
		this.leituraAtual1 = leituraAtual1;
	}

	public String getLeituraAtual2() {
		return leituraAtual2;
	}

	public void setLeituraAtual2(String leituraAtual2) {
		this.leituraAtual2 = leituraAtual2;
	}

	public String getMesAno1Conta1() {
		return mesAno1Conta1;
	}

	public void setMesAno1Conta1(String mesAno1Conta1) {
		this.mesAno1Conta1 = mesAno1Conta1;
	}

	public String getMesAno1Conta2() {
		return mesAno1Conta2;
	}

	public void setMesAno1Conta2(String mesAno1Conta2) {
		this.mesAno1Conta2 = mesAno1Conta2;
	}

	public String getMesAno2Conta1() {
		return mesAno2Conta1;
	}

	public void setMesAno2Conta1(String mesAno2Conta1) {
		this.mesAno2Conta1 = mesAno2Conta1;
	}

	public String getMesAno2Conta2() {
		return mesAno2Conta2;
	}

	public void setMesAno2Conta2(String mesAno2Conta2) {
		this.mesAno2Conta2 = mesAno2Conta2;
	}

	public String getMesAno3Conta1() {
		return mesAno3Conta1;
	}

	public void setMesAno3Conta1(String mesAno3Conta1) {
		this.mesAno3Conta1 = mesAno3Conta1;
	}

	public String getMesAno3Conta2() {
		return mesAno3Conta2;
	}

	public void setMesAno3Conta2(String mesAno3Conta2) {
		this.mesAno3Conta2 = mesAno3Conta2;
	}

	public String getMesAno4Conta1() {
		return mesAno4Conta1;
	}

	public void setMesAno4Conta1(String mesAno4Conta1) {
		this.mesAno4Conta1 = mesAno4Conta1;
	}

	public String getMesAno4Conta2() {
		return mesAno4Conta2;
	}

	public void setMesAno4Conta2(String mesAno4Conta2) {
		this.mesAno4Conta2 = mesAno4Conta2;
	}

	public String getMesAno5Conta1() {
		return mesAno5Conta1;
	}

	public void setMesAno5Conta1(String mesAno5Conta1) {
		this.mesAno5Conta1 = mesAno5Conta1;
	}

	public String getMesAno5Conta2() {
		return mesAno5Conta2;
	}

	public void setMesAno5Conta2(String mesAno5Conta2) {
		this.mesAno5Conta2 = mesAno5Conta2;
	}

	public String getMesAno6Conta1() {
		return mesAno6Conta1;
	}

	public void setMesAno6Conta1(String mesAno6Conta1) {
		this.mesAno6Conta1 = mesAno6Conta1;
	}

	public String getMesAno6Conta2() {
		return mesAno6Conta2;
	}

	public void setMesAno6Conta2(String mesAno6Conta2) {
		this.mesAno6Conta2 = mesAno6Conta2;
	}

	public String getMesAnoConta1() {
		return mesAnoConta1;
	}

	public void setMesAnoConta1(String mesAnoConta1) {
		this.mesAnoConta1 = mesAnoConta1;
	}

	public String getMesAnoConta2() {
		return mesAnoConta2;
	}

	public void setMesAnoConta2(String mesAnoConta2) {
		this.mesAnoConta2 = mesAnoConta2;
	}

	public String getMsgConta1() {
		return msgConta1;
	}

	public void setMsgConta1(String msgConta1) {
		this.msgConta1 = msgConta1;
	}

	public String getMsgConta2() {
		return msgConta2;
	}

	public void setMsgConta2(String msgConta2) {
		this.msgConta2 = msgConta2;
	}

	public String getMsgLinha1Conta1() {
		return msgLinha1Conta1;
	}

	public void setMsgLinha1Conta1(String msgLinha1Conta1) {
		this.msgLinha1Conta1 = msgLinha1Conta1;
	}

	public String getMsgLinha1Conta2() {
		return msgLinha1Conta2;
	}

	public void setMsgLinha1Conta2(String msgLinha1Conta2) {
		this.msgLinha1Conta2 = msgLinha1Conta2;
	}

	public String getMsgLinha2Conta1() {
		return msgLinha2Conta1;
	}

	public void setMsgLinha2Conta1(String msgLinha2Conta1) {
		this.msgLinha2Conta1 = msgLinha2Conta1;
	}

	public String getMsgLinha2Conta2() {
		return msgLinha2Conta2;
	}

	public void setMsgLinha2Conta2(String msgLinha2Conta2) {
		this.msgLinha2Conta2 = msgLinha2Conta2;
	}

	public String getNomeCliente1() {
		return nomeCliente1;
	}

	public void setNomeCliente1(String nomeCliente1) {
		this.nomeCliente1 = nomeCliente1;
	}

	public String getNomeCliente2() {
		return nomeCliente2;
	}

	public void setNomeCliente2(String nomeCliente2) {
		this.nomeCliente2 = nomeCliente2;
	}

	public String getNumeroConta1() {
		return numeroConta1;
	}

	public void setNumeroConta1(String numeroConta1) {
		this.numeroConta1 = numeroConta1;
	}

	public String getNumeroConta2() {
		return numeroConta2;
	}

	public void setNumeroConta2(String numeroConta2) {
		this.numeroConta2 = numeroConta2;
	}

	public String getPadraoTurbidez2() {
		return padraoTurbidez2;
	}

	public void setPadraoTurbidez2(String padraoTurbidez2) {
		this.padraoTurbidez2 = padraoTurbidez2;
	}

	public String getQtdeEconomia1() {
		return qtdeEconomia1;
	}

	public void setQtdeEconomia1(String qtdeEconomia1) {
		this.qtdeEconomia1 = qtdeEconomia1;
	}

	public String getQtdeEconomia2() {
		return qtdeEconomia2;
	}

	public void setQtdeEconomia2(String qtdeEconomia2) {
		this.qtdeEconomia2 = qtdeEconomia2;
	}

	public String getRepresentacaoNumericaCodBarraFormatada1() {
		return representacaoNumericaCodBarraFormatada1;
	}

	public void setRepresentacaoNumericaCodBarraFormatada1(
			String representacaoNumericaCodBarraFormatada1) {
		this.representacaoNumericaCodBarraFormatada1 = representacaoNumericaCodBarraFormatada1;
	}

	public String getRepresentacaoNumericaCodBarraFormatada2() {
		return representacaoNumericaCodBarraFormatada2;
	}

	public void setRepresentacaoNumericaCodBarraFormatada2(
			String representacaoNumericaCodBarraFormatada2) {
		this.representacaoNumericaCodBarraFormatada2 = representacaoNumericaCodBarraFormatada2;
	}

	public String getRepresentacaoNumericaCodBarraSemDigito1() {
		return representacaoNumericaCodBarraSemDigito1;
	}

	public void setRepresentacaoNumericaCodBarraSemDigito1(
			String representacaoNumericaCodBarraSemDigito1) {
		this.representacaoNumericaCodBarraSemDigito1 = representacaoNumericaCodBarraSemDigito1;
	}

	public String getRepresentacaoNumericaCodBarraSemDigito2() {
		return representacaoNumericaCodBarraSemDigito2;
	}

	public void setRepresentacaoNumericaCodBarraSemDigito2(
			String representacaoNumericaCodBarraSemDigito2) {
		this.representacaoNumericaCodBarraSemDigito2 = representacaoNumericaCodBarraSemDigito2;
	}

	public String getRota1() {
		return rota1;
	}

	public void setRota1(String rota1) {
		this.rota1 = rota1;
	}

	public String getRota2() {
		return rota2;
	}

	public void setRota2(String rota2) {
		this.rota2 = rota2;
	}

	public String getValorMedioCloro1() {
		return valorMedioCloro1;
	}

	public void setValorMedioCloro1(String valorMedioCloro1) {
		this.valorMedioCloro1 = valorMedioCloro1;
	}

	public String getValorMedioCloro2() {
		return valorMedioCloro2;
	}

	public void setValorMedioCloro2(String valorMedioCloro2) {
		this.valorMedioCloro2 = valorMedioCloro2;
	}

	public String getValorMedioColiformesfecais1() {
		return valorMedioColiformesfecais1;
	}

	public void setValorMedioColiformesfecais1(String valorMedioColiformesfecais1) {
		this.valorMedioColiformesfecais1 = valorMedioColiformesfecais1;
	}

	public String getValorMedioColiformesfecais2() {
		return valorMedioColiformesfecais2;
	}

	public void setValorMedioColiformesfecais2(String valorMedioColiformesfecais2) {
		this.valorMedioColiformesfecais2 = valorMedioColiformesfecais2;
	}

	public String getValorMedioColiformesTotais1() {
		return valorMedioColiformesTotais1;
	}

	public void setValorMedioColiformesTotais1(String valorMedioColiformesTotais1) {
		this.valorMedioColiformesTotais1 = valorMedioColiformesTotais1;
	}

	public String getValorMedioColiformesTotais2() {
		return valorMedioColiformesTotais2;
	}

	public void setValorMedioColiformesTotais2(String valorMedioColiformesTotais2) {
		this.valorMedioColiformesTotais2 = valorMedioColiformesTotais2;
	}

	public String getValorMedioCor1() {
		return valorMedioCor1;
	}

	public void setValorMedioCor1(String valorMedioCor1) {
		this.valorMedioCor1 = valorMedioCor1;
	}

	public String getValorMedioCor2() {
		return valorMedioCor2;
	}

	public void setValorMedioCor2(String valorMedioCor2) {
		this.valorMedioCor2 = valorMedioCor2;
	}

	public String getValorMedioFerro1() {
		return valorMedioFerro1;
	}

	public void setValorMedioFerro1(String valorMedioFerro1) {
		this.valorMedioFerro1 = valorMedioFerro1;
	}

	public String getValorMedioFerro2() {
		return valorMedioFerro2;
	}

	public void setValorMedioFerro2(String valorMedioFerro2) {
		this.valorMedioFerro2 = valorMedioFerro2;
	}

	public String getValorMedioFluor1() {
		return valorMedioFluor1;
	}

	public void setValorMedioFluor1(String valorMedioFluor1) {
		this.valorMedioFluor1 = valorMedioFluor1;
	}

	public String getValorMedioFluor2() {
		return valorMedioFluor2;
	}

	public void setValorMedioFluor2(String valorMedioFluor2) {
		this.valorMedioFluor2 = valorMedioFluor2;
	}

	public String getValorMedioPh1() {
		return valorMedioPh1;
	}

	public void setValorMedioPh1(String valorMedioPh1) {
		this.valorMedioPh1 = valorMedioPh1;
	}

	public String getValorMedioPh2() {
		return valorMedioPh2;
	}

	public void setValorMedioPh2(String valorMedioPh2) {
		this.valorMedioPh2 = valorMedioPh2;
	}

	public String getValorMedioTurbidez1() {
		return valorMedioTurbidez1;
	}

	public void setValorMedioTurbidez1(String valorMedioTurbidez1) {
		this.valorMedioTurbidez1 = valorMedioTurbidez1;
	}

	public String getValorMedioTurbidez2() {
		return valorMedioTurbidez2;
	}

	public void setValorMedioTurbidez2(String valorMedioTurbidez2) {
		this.valorMedioTurbidez2 = valorMedioTurbidez2;
	}

	public String getVolFaturado1() {
		return volFaturado1;
	}

	public void setVolFaturado1(String volFaturado1) {
		this.volFaturado1 = volFaturado1;
	}

	public String getVolFaturado2() {
		return volFaturado2;
	}

	public void setVolFaturado2(String volFaturado2) {
		this.volFaturado2 = volFaturado2;
	}

	public String getValorTotalConta1() {
		return valorTotalConta1;
	}

	public void setValorTotalConta1(String valorTotalConta1) {
		this.valorTotalConta1 = valorTotalConta1;
	}

	public String getValorTotalConta2() {
		return valorTotalConta2;
	}

	public void setValorTotalConta2(String valorTotalConta2) {
		this.valorTotalConta2 = valorTotalConta2;
	}


	public String getEndereco1Linha3() {
		return endereco1Linha3;
	}


	public void setEndereco1Linha3(String endereco1Linha3) {
		this.endereco1Linha3 = endereco1Linha3;
	}


	public String getEndereco2Linha3() {
		return endereco2Linha3;
	}


	public void setEndereco2Linha3(String endereco2Linha3) {
		this.endereco2Linha3 = endereco2Linha3;
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


	public String getPadraoTurbidez() {
		return padraoTurbidez;
	}


	public void setPadraoTurbidez(String padraoTurbidez) {
		this.padraoTurbidez = padraoTurbidez;
	}


	public String getOrigem1() {
		return origem1;
	}


	public void setOrigem1(String origem1) {
		this.origem1 = origem1;
	}


	public String getOrigem2() {
		return origem2;
	}


	public void setOrigem2(String origem2) {
		this.origem2 = origem2;
	}


	public String getGrupoFaturamento1() {
		return grupoFaturamento1;
	}


	public void setGrupoFaturamento1(String grupoFaturamento1) {
		this.grupoFaturamento1 = grupoFaturamento1;
	}


	public String getGrupoFaturamento2() {
		return grupoFaturamento2;
	}


	public void setGrupoFaturamento2(String grupoFaturamento2) {
		this.grupoFaturamento2 = grupoFaturamento2;
	}


	public String getSequencialImpressao1() {
		return sequencialImpressao1;
	}


	public void setSequencialImpressao1(String sequencialImpressao1) {
		this.sequencialImpressao1 = sequencialImpressao1;
	}


	public String getSequencialImpressao2() {
		return sequencialImpressao2;
	}

	public void setSequencialImpressao2(String sequencialImpressao2) {
		this.sequencialImpressao2 = sequencialImpressao2;
	}

	public String getIndicadorCodigoBarras1() {
		return indicadorCodigoBarras1;
	}

	public void setIndicadorCodigoBarras1(String indicadorCodigoBarras1) {
		this.indicadorCodigoBarras1 = indicadorCodigoBarras1;
	}

	public String getIndicadorCodigoBarras2() {
		return indicadorCodigoBarras2;
	}


	public void setIndicadorCodigoBarras2(String indicadorCodigoBarras2) {
		this.indicadorCodigoBarras2 = indicadorCodigoBarras2;
	}

	public String getCodigoRotaSequencialRota1() {
		return codigoRotaSequencialRota1;
	}

	public void setCodigoRotaSequencialRota1(String codigoRotaSequencialRota1) {
		this.codigoRotaSequencialRota1 = codigoRotaSequencialRota1;
	}

	public String getCodigoRotaSequencialRota2() {
		return codigoRotaSequencialRota2;
	}

	public void setCodigoRotaSequencialRota2(String codigoRotaSequencialRota2) {
		this.codigoRotaSequencialRota2 = codigoRotaSequencialRota2;
	}
	
}
