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
package gcom.financeiro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.zip.ZipOutputStream;

import gcom.cadastro.ControladorCadastroLocal;
import gcom.cadastro.ControladorCadastroLocalHome;
import gcom.cadastro.EnvioEmail;
import gcom.financeiro.bean.GerarIntegracaoContabilidadeHelper;
import gcom.financeiro.lancamento.LancamentoOrigem;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.ZipUtil;
import gcom.util.email.ServicosEmail;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;

/**
 * Controlador Financeiro CAEMA
 *
 * @author Sávio Luiz
 * @date 28/04/2008
 */
public class ControladorFinanceiroCAEMASEJB extends ControladorFinanceiro implements SessionBean {

	private static final long serialVersionUID = 1L;
	
	//==============================================================================================================
	// MÉTODOS EXCLUSIVOS DA CAEMA
	//==============================================================================================================
	
	/**
	 * Retorna o controladorCadastro
	 * 
	 * @author Thiago Tenório
	 * @date 18/08/2006
	 * 
	 */
	private ControladorCadastroLocal getControladorCadastro() {
		ControladorCadastroLocalHome localHome = null;
		ControladorCadastroLocal local = null;

		ServiceLocator locator = null;
		try {
			locator = ServiceLocator.getInstancia();
			localHome = (ControladorCadastroLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_CADASTRO_SEJB);

			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * este caso de uso gera a integração para a contabilidade
	 *
	 * [UC0469] Gerar Integração para a Contabilidade
	 *
	 * @author Arthur Carvalho
	 * @date 18/02/2009
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @param data
	 * @throws ControladorException
	 */
	public void gerarIntegracaoContabilidade(String idLancamentoOrigem, String anoMes, String data) throws ControladorException{
		
		/*
		 * Pesquisa os dados para gerar a integração para a contabilidade.
		 * 
		 * 0 - número do cartão
		 * 1 - código tipo
		 * 2 - número folha
		 * 3 - indicador linha
		 * 4 - prefixo contábil
		 * 5 - número conta
		 * 6 - número dígito
		 * 7 - número terceiros
		 * 8 - código referência
		 * 9 - valor lançamento
		 * 10 - indicador débito crédito
		 * 11 - número cartão 2
		 * 12 - número versão
		 * 13 - id da localidade
		 * 14 - código centro custo
		 * 
		 */
		Collection<Object[]> colecaoDadosGerarIntegracao = null;

		colecaoDadosGerarIntegracao = this.pesquisarGerarIntegracaoContabilidade/*Caern*/(idLancamentoOrigem, anoMes);
		
		/** definição das variáveis */
		StringBuilder gerarIntegracaoTxt = new StringBuilder();
		
		String dataFormatada = data.replace("/","");
		String diaMes = dataFormatada.substring(0,4);
		String ano = dataFormatada.substring(6 ,8);
		dataFormatada = diaMes + ano;
		/*
		 * Caso a coleção dos dados não esteja vazia
		 */
		if(colecaoDadosGerarIntegracao != null && !colecaoDadosGerarIntegracao.isEmpty()){
			
			/** definição das variáveis */
			
			Short numeroCartao = null;
			BigDecimal valorLancamento = null;
			Integer numeroSequencial = 0;
			String indicadorCentroCusto = null;
			/*
			 * Laço para gerar o txt 
			 */
			Iterator iterator = colecaoDadosGerarIntegracao.iterator();
			while (iterator.hasNext()){
				GerarIntegracaoContabilidadeHelper gerarIntegracaoContabilidadeHelper = (GerarIntegracaoContabilidadeHelper)iterator.next();

				//número do cartão 
				numeroCartao = gerarIntegracaoContabilidadeHelper.getNumeroCartao();
		
				//valor do lançamento
				valorLancamento = (BigDecimal) gerarIntegracaoContabilidadeHelper.getValorLancamento();
				
				//Indicador Centro Custo
				indicadorCentroCusto = gerarIntegracaoContabilidadeHelper.getIndicadorCentroCusto();
				
				//Numero Historico Debito ou Credito
				
				/*
				 * Inicio da geração do txt
				 */
				//Cartao
				
				gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(2,numeroCartao + ""));
				//Sequencial
				gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumeroTruncando(3, numeroSequencial + ""));
				//Separar o Campo com virgula
				gerarIntegracaoTxt.append(",");
				//data completa
				gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(6,dataFormatada));
				//Separar o Campo com virgula
				gerarIntegracaoTxt.append(",");
				//Conta Debito
				gerarIntegracaoTxt.append(gerarIntegracaoContabilidadeHelper.getNumeroContaDebito()+"");
				//Separar o Campo com virgula
				gerarIntegracaoTxt.append(",");
				//Conta Credito
				gerarIntegracaoTxt.append(gerarIntegracaoContabilidadeHelper.getNumeroContaCredito()+"");
				//Separar o Campo com virgula
				gerarIntegracaoTxt.append(",");
				//Valor Lancamento
				gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(13 , (valorLancamento + "")));
				//Separar o Campo com virgula
				gerarIntegracaoTxt.append(",");
				//Numero Historico de Credito ou Debito
				gerarIntegracaoTxt.append(gerarIntegracaoContabilidadeHelper.getNumeroHistoricoCreditoOuCredito());

				//Separar o Campo com virgula
				gerarIntegracaoTxt.append(",");
				//MesAno
				gerarIntegracaoTxt.append("\"" + Util.completaString(Util.formatarAnoMesParaMesAno(Integer.parseInt(anoMes)) , 7 )+"\"");
				
				//Quebra de Linha
				gerarIntegracaoTxt.append(System.getProperty("line.separator"));
				
				if (indicadorCentroCusto != null && indicadorCentroCusto.equals("1")){
					
					//Exibir codigo de credito ou debito
					if(gerarIntegracaoContabilidadeHelper.getCodigoCentroCustoCredito() != 0){
						//Codigo Custo Credito
						gerarIntegracaoTxt.append(gerarIntegracaoContabilidadeHelper.getCodigoCentroCustoCredito()+"");	
					} else {
						//Codigo Custo Debito
						gerarIntegracaoTxt.append(gerarIntegracaoContabilidadeHelper.getCodigoCentroCustoDebito()+"");	
					}
					
					//Separar o Campo com virgula
					gerarIntegracaoTxt.append(",");
					
					//Valor Lancamento do centro de custo
					gerarIntegracaoTxt.append(Util.adicionarZerosEsquedaNumero(13 , (valorLancamento + "")));
					
					//Quebra de Linha
					gerarIntegracaoTxt.append(System.getProperty("line.separator"));
						
				}
				
				numeroSequencial = numeroSequencial +1;
			}
			/*
			 * Determina se o arquivo é de faturamento ou arrecadação 
			 * para concatenar no nome do arquivo .zip
			 */
			String descricaoLancamento = "";
			if(idLancamentoOrigem.equals(LancamentoOrigem.FATURAMENTO + "")){
				descricaoLancamento = "FATURAMENTO";
			}else if(idLancamentoOrigem.equals(LancamentoOrigem.ARRECADACAO + "")){
				descricaoLancamento = "ARRECADACAO";
			}
			
			/*
			 * Gerando o arquivo zip
			 */
			String nomeZip = "CONTABILIDADE_" + descricaoLancamento + "_" + (data.replace("/","_"));
			BufferedWriter out = null;
			ZipOutputStream zos = null;
			File compactadoTipo = new File(nomeZip + ".zip");
			File leituraTipo = new File(nomeZip + ".txt");

			/*
			 * Caso oarquivo txt não esteja vazio 
			 * adiciona o txt ao arquivo zip.
			 */
			if (gerarIntegracaoTxt != null && gerarIntegracaoTxt.length() != 0) {
				try {
					System.out.println("CRIANDO ZIP");
					zos = new ZipOutputStream(new FileOutputStream(compactadoTipo));

					out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(leituraTipo.getAbsolutePath())));
					out.write(gerarIntegracaoTxt.toString());
					out.flush();
					ZipUtil.adicionarArquivo(zos, leituraTipo);
					zos.close();
					leituraTipo.delete();
					out.close();
					
					
				} catch (IOException ex) {					
					throw new ControladorException("erro.sistema", ex);
				}
				
				try {
					
					// Envia de Arquivo por email
					EnvioEmail envioEmail = this.getControladorCadastro()
						.pesquisarEnvioEmail(
							EnvioEmail.GERAR_INTEGRACAO_PARA_CONTABILIDADE);

					String emailRemetente = envioEmail.getEmailRemetente();
					String tituloMensagem = envioEmail.getTituloMensagem();
					String corpoMensagem = envioEmail.getCorpoMensagem();
					String emailReceptor = envioEmail.getEmailReceptor();
					
					ServicosEmail.enviarMensagemArquivoAnexado(
							emailReceptor,emailRemetente, tituloMensagem, corpoMensagem,
							compactadoTipo);
				} catch (Exception e) {
					System.out.println("Erro ao enviar email.");
				}	
				
			}
			//caso não exista informação para os dados informados
		}else{
			if(idLancamentoOrigem.equals(LancamentoOrigem.FATURAMENTO + "")){
				throw new ControladorException("atencao.pesquisa.nenhum_registro_tabela", null,"Resumo Faturamento");
			}else if(idLancamentoOrigem.equals(LancamentoOrigem.ARRECADACAO + "")){
				throw new ControladorException("atencao.pesquisa.nenhum_registro_tabela", null,"Resumo Faturamento");
			}
		}
	}
	
	
	/**
	 * Este metodo é utilizado para pesquisar os registros q serão
	 * usados para contrução do txt do caso de uso
	 *
	 * [UC0469] Gerar Integração para a Contabilidade
	 *
	 * @author Arthur Carvalho
	 * @date 18/02/2009
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @return
	 * @throws ControladorException
	 */
	public Collection pesquisarGerarIntegracaoContabilidade(String idLancamentoOrigem, String anoMes) 
		throws ControladorException{
		
		Collection colecaoObjetoGerar = null;
		Collection colecaoGerarIntegracaoContabilidade = null;
		
		try {

			colecaoObjetoGerar = repositorioFinanceiro.pesquisarGerarIntegracaoContabilidadeCaema(idLancamentoOrigem, anoMes);
			
			if(!colecaoObjetoGerar.isEmpty()){
				Iterator iteratorPesquisa = colecaoObjetoGerar.iterator();
				
				colecaoGerarIntegracaoContabilidade = new ArrayList();
				GerarIntegracaoContabilidadeHelper gerarIntegracaoContabilidadeHelper = null;
				Object[] objetoGerar = null;
				
				while(iteratorPesquisa.hasNext()){
					gerarIntegracaoContabilidadeHelper = new GerarIntegracaoContabilidadeHelper();
					
					objetoGerar = (Object[]) iteratorPesquisa.next();
					
					//indicador debito credito
					if(objetoGerar[10] != null){
						gerarIntegracaoContabilidadeHelper.setIndicadorDebitoConta(new Integer((Short) objetoGerar[10]));
					}
					
					//numero cartao
					if (gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta() != null
							&& gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(1)){
						gerarIntegracaoContabilidadeHelper.setNumeroCartao(new Integer((Short) objetoGerar[0]).shortValue());
					} else if (gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta() != null
							&& gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(2)){
						gerarIntegracaoContabilidadeHelper.setNumeroCartao(new Integer((Short) objetoGerar[0]).shortValue());
					} else if (gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta() != null
							&& gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(3)) {
						gerarIntegracaoContabilidadeHelper.setNumeroCartao(new Integer((Short) objetoGerar[0]).shortValue());
					}
					
					//lancamento tipo
					if(objetoGerar[1] != null){
						gerarIntegracaoContabilidadeHelper.setIdTipoLancamento(new Integer((Short) objetoGerar[1]));
					}
					
					//folha
					if(objetoGerar[2] != null){
						gerarIntegracaoContabilidadeHelper.setFolha(new Integer((Short) objetoGerar[2]));
					}
					
					//linha
					if(objetoGerar[3] != null){
						gerarIntegracaoContabilidadeHelper.setIndicadorLinha(new Integer((Short)objetoGerar[3]));
					}
					
					//prefixo contabil
					if(objetoGerar[4] != null){
						gerarIntegracaoContabilidadeHelper.setNumeroPrefixoContabil((String) objetoGerar[4]);
					}
					
					// numero da conta
					if(objetoGerar[5] != null && gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(1)){
						String numero = ((String) objetoGerar[5]).trim();
						gerarIntegracaoContabilidadeHelper.setNumeroContaCredito(numero);
						gerarIntegracaoContabilidadeHelper.setNumeroContaDebito("0");
					}else if(objetoGerar[5] != null && gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta() != null
							&& gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(2)){
						String numero = ((String) objetoGerar[5]).trim();
						gerarIntegracaoContabilidadeHelper.setNumeroContaDebito(numero);
						gerarIntegracaoContabilidadeHelper.setNumeroContaCredito("0");
					}
					
					//digito
					if(objetoGerar[6] != null){
						gerarIntegracaoContabilidadeHelper.setDigito(new Integer(((String) objetoGerar[6]).trim()));
					}
					
					//terceiros
					if(objetoGerar[7] != null){
						gerarIntegracaoContabilidadeHelper.setTerceiros(new Integer(((String) objetoGerar[7]).trim()));
					}
					
					//referencia
					if(objetoGerar[8] != null){
						gerarIntegracaoContabilidadeHelper.setReferencial(new Integer(((String) objetoGerar[8]).trim()));
					}
					
					//valor lancamento
					if(objetoGerar[9] != null){
						gerarIntegracaoContabilidadeHelper.setValorLancamento((BigDecimal)objetoGerar[9]);
					}
					
					//Cartao2
					if(objetoGerar[11] != null){
						gerarIntegracaoContabilidadeHelper.setCartao2(new Integer((Short) objetoGerar[11]));
					}
					
					//Versao
					if(objetoGerar[12] != null){
						gerarIntegracaoContabilidadeHelper.setVersao(new Integer((Short) objetoGerar[12]));
					}
					
					//id localidade
					if(objetoGerar[13] != null){
						gerarIntegracaoContabilidadeHelper.setIdLocalidade((Integer)objetoGerar[13]);
					}
					
					//codigo centro custo
					if(objetoGerar[14] != null && gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta() != null
							&& gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(1)){
						gerarIntegracaoContabilidadeHelper.setCodigoCentroCustoCredito(new Integer(((String) objetoGerar[14]).trim()));
						gerarIntegracaoContabilidadeHelper.setCodigoCentroCustoDebito(new Integer(0));
					}else if(objetoGerar[14] != null && gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta() != null
							&& gerarIntegracaoContabilidadeHelper.getIndicadorDebitoConta().equals(2)){
						gerarIntegracaoContabilidadeHelper.setCodigoCentroCustoDebito(new Integer(((String) objetoGerar[14]).trim()));
						gerarIntegracaoContabilidadeHelper.setCodigoCentroCustoCredito(new Integer(0));
					}
					
					colecaoGerarIntegracaoContabilidade.add(gerarIntegracaoContabilidadeHelper);
				
					//Indicador Centro de Custo
					if ( objetoGerar[15] != null && (((Short)objetoGerar[15]).intValue() == 1) ){
						gerarIntegracaoContabilidadeHelper.setIndicadorCentroCusto( "" + objetoGerar[15] );
					}
					
					//Numero Hitorico Debito
					if ( objetoGerar[16] != null && (((Short) objetoGerar[10]).intValue() == 1 ) ) {
						gerarIntegracaoContabilidadeHelper.setNumeroHistoricoCreditoOuCredito("" + objetoGerar[16] );
					}
					
					//Numero Hitorico Credito
					if ( objetoGerar[17] != null && (((Short) objetoGerar[10]).intValue() == 2 ) ) {
						gerarIntegracaoContabilidadeHelper.setNumeroHistoricoCreditoOuCredito("" + objetoGerar[17] );
					}
				}
				
			}
			
		} catch (ErroRepositorioException ex) {
			sessionContext.setRollbackOnly();
			throw new ControladorException("erro.sistema", ex);
		}
		
		return colecaoGerarIntegracaoContabilidade;
	}
	
}
