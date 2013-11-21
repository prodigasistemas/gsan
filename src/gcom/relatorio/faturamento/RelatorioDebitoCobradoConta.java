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
package gcom.relatorio.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.DebitoCobradoHistorico;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.cadastro.imovel.RelatorioDebitoCobradoContaHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Title: GCOM
 * Description: Sistema de Gestão Comercial
 * Copyright: Copyright (c) 2004
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * @author Arthur Carvalho
 * @version 1.0
 */

public class RelatorioDebitoCobradoConta extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioDebitoCobradoConta(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_DEBITO_COBRADO_CONTA);
	}
	
	public RelatorioDebitoCobradoConta(Usuario usuario, String nomeRelatorio) {
		super(usuario, nomeRelatorio);		
	}


	@Override
	public int calcularTotalRegistrosRelatorio() {
		// TODO Auto-generated method stub
		return 0;
	}

	private Collection inicializarBeanRelatorio(
			Collection colecaoDados,int tipo) {
		
		Collection retorno = new ArrayList();

		Iterator iter = colecaoDados.iterator();
		DebitoCobrado debitoCobrado = null;
		DebitoCobradoHistorico debitoCobradoHistorico = null;
		RelatorioDebitoCobradoContaBean bean = null;
		while (iter.hasNext()) {
			bean = new RelatorioDebitoCobradoContaBean();
			if(tipo == 1){
				debitoCobrado = (DebitoCobrado) iter.next();	 			
				bean.setTipoDebito(debitoCobrado.getDebitoTipo().getDescricao());
				/**TODO:COSANPA
				 * DATA: 05/07/2011
				 * autor: Adriana Muniz
				 * Alteração para atender a debitos que não possuam ano mês referência
				 * */
				if(debitoCobrado.getAnoMesReferenciaDebito() != null)
					bean.setMesAnoReferencia(Util.formatarMesAnoReferencia(debitoCobrado.getAnoMesReferenciaDebito()));
			    bean.setMesAnoCobranca(Util.formatarMesAnoReferencia(debitoCobrado.getAnoMesCobrancaDebito()));
			    bean.setParcela(debitoCobrado.getNumeroPrestacaoDebito()+"/"+debitoCobrado.getNumeroTotalParcelasMenosBonus());
			    bean.setValorParcela(Util.formataBigDecimal(debitoCobrado.getValorPrestacao(),2,true));
			    
			}else if (tipo == 2){
				debitoCobradoHistorico = (DebitoCobradoHistorico) iter.next();
				bean.setTipoDebito(debitoCobradoHistorico.getDebitoTipo().getDescricao());
			    bean.setMesAnoReferencia(Util.formatarMesAnoReferencia(debitoCobradoHistorico.getAnoMesReferenciaDebito()));
			    bean.setMesAnoCobranca(Util.formatarMesAnoReferencia(debitoCobradoHistorico.getAnoMesCobrancaDebito()));
			    bean.setParcela(debitoCobradoHistorico.getNumeroPrestacaoDebito()+"/"+debitoCobradoHistorico.getNumeroTotalParcelasMenosBonus());
			    bean.setValorParcela(Util.formataBigDecimal(debitoCobradoHistorico.getValorPrestacao(),2,true));
			}				
			retorno.add(bean);
			
		}
		
		return retorno;
	}
	@Override
	public Object executar() throws TarefaException {
		// TODO Auto-generated method stub
		
//		 ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		Fachada fachada = Fachada.getInstancia();

		RelatorioDebitoCobradoContaHelper helper = (RelatorioDebitoCobradoContaHelper)getParametro("helper");
		
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map<String, String> parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("imagemConta", sistemaParametro.getImagemConta());
		parametros.put("mesAno",helper.getMesAnoConta());
		parametros.put("idImovel",helper.getIdImovel());
		parametros.put("situacaoConta",helper.getSituacaoConta());
		parametros.put("situacaoEsgoto",helper.getSituacaoLigacaoEsgoto());
		parametros.put("situacaoAgua",helper.getSituacaoLigacaoAgua());
		
		Collection colecaoBean = null;
		if(helper.getColecaoDebitosCobrados() != null && !helper.getColecaoDebitosCobrados().isEmpty()){
			colecaoBean = this.inicializarBeanRelatorio(helper.getColecaoDebitosCobrados(),1);
		}else if (helper.getColecaoDebitosCobradosHistorico() != null && !helper.getColecaoDebitosCobradosHistorico().isEmpty()){
			colecaoBean = this.inicializarBeanRelatorio(helper.getColecaoDebitosCobradosHistorico(),2);
		}
		
		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) colecaoBean);
		
		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_DEBITO_COBRADO_CONTA, 
				parametros, ds,	tipoFormatoRelatorio);
		
//		 ------------------------------------
		// Grava o relatório no sistema
		try {			
			
		  persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_DEBITO_COBRADO_CONTA,
						idFuncionalidadeIniciada);
				
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------
		
		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		// TODO Auto-generated method stub

	}


}