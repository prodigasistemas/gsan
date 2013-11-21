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
package gcom.relatorio;

import gcom.batch.ControladorBatchLocal;
import gcom.batch.ControladorBatchLocalHome;
import gcom.batch.Processo;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;

import javax.ejb.CreateException;

/**
 * Classe responsável por analisar cada relatorio
 * 
 * @author Thiago Toscano
 * @date 25/05/2006
 */
public class GerenciadorExecucaoTarefaRelatorio {

	/**
	 * Método responsável por verificar se a tarefaRelatorio será exibida online
	 * ou será armazenada em batch
	 * 
	 * @author Thiago Toscano
	 * @date 25/05/2006
	 * 
	 * @param tarefaRelatorio
	 * @param tipoTarefa
	 */
	public static RelatorioProcessado analisarExecucao(
			TarefaRelatorio tarefaRelatorio, int tipoTarefa) throws ControladorException {
		RelatorioProcessado retorno = null;
		// pegando a quantidade de registro dessa tarefa
		int quantidadeRegistroGerado = tarefaRelatorio
				.calcularTotalRegistrosRelatorio();

		String nomeClasseRelatorio = tarefaRelatorio.getClass().getSimpleName();

		int quantidadeMaximaOnLineRelatorio = ConstantesExecucaoRelatorios.get(nomeClasseRelatorio);

		// se a quantidade a ser processada for maior que a permitida
		if (quantidadeMaximaOnLineRelatorio == ConstantesExecucaoRelatorios.QUANTIDADE_NAO_INFORMADA
				|| quantidadeRegistroGerado > quantidadeMaximaOnLineRelatorio) {

			// throw new
			// ActionServletException("atencao.execucao.relatorio.batch");
				
				if (SistemaParametro.INDICADOR_AUTORIZACAO_RELATORIO.equals(ConstantesSistema.NAO)) {
					getControladorBatch().iniciarProcessoRelatorio(tarefaRelatorio);
				} else {
					getControladorBatch().iniciarProcessoRelatorioControleAutorizacao(tarefaRelatorio);	
					
				}
		
				

		} else {

			// caso contrario executa e monta o relatorio processado
			// Fazer depois -- toda tarefa deverá passar pelo agendador,
			// mesmo
			// se for imediata
			byte[] dados = (byte[]) tarefaRelatorio.executar();
			retorno = new RelatorioProcessado(dados, tipoTarefa);
		}

		return retorno;

	}

	/**
	 * Retorna o identificador do processo associado com o relatório
	 * 
	 * @author Rodrigo Silveira
	 * @date 25/09/2006
	 * 
	 * @param nomeRelatorio
	 * @return
	 */
	public static int obterProcessoRelatorio(String nomeRelatorio) {

		int retornoCodigoProcesso = 0;
		
		if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_BAIRRO_MANTER)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_MANTER_BAIRRO;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_CLIENTE_MANTER)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_MANTER_CLIENTE;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_SETOR_COMERCIAL_MANTER)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_MANTER_SETOR_COMERCIAL;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_LOCALIDADE_MANTER)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_MANTER_LOCALIDADE;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_LOGRADOURO_MANTER)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_MANTER_LOGRADOURO;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_QUADRA_MANTER)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_MANTER_QUADRA;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_IMOVEL_MANTER)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_MANTER_IMOVEL;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_TARIFA_SOCIAL_MANTER)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_MANTER_TARIFA_SOCIAL;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_SUBCATEGORIA_MANTER)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_MANTER_SUBCATEGORIA;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_ROTA_MANTER)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_MANTER_ROTA;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_CATEGORIA_MANTER)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_MANTER_CATEGORIA;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_CRONOGRAMA_FATURAMENTO_MANTER)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_MANTER_CRONOGRAMA_FATURAMENTO;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_CRONOGRAMA_COBRANCA_MANTER)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_MANTER_CRONOGRAMA_COBRANCA;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_CRITERIO_COBRANCA_MANTER)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_MANTER_CRITERIO_COBRANCA;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_PERFIL_PARCELAMENTO_MANTER)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_MANTER_PERFIL_PARCELAMENTO;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_MOVIMENTO_ARRECADADOR_MANTER)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_MANTER_MOVIMENTO_ARRECADADOR;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_MENSAGEM_CONTA_MANTER)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_MANTER_MENSAGEM_CONTA;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_AVISO_BANCARIO_MANTER)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_MANTER_AVISO_BANCARIO;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_GUIA_DEVOLUCAO_MANTER)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_MANTER_GUIA_DEVOLUCAO;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_IMOVEL_OUTROS_CRITERIOS_MANTER)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_MANTER_IMOVEL_OUTROS_CRITERIOS;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_DADOS_ECONOMIA_IMOVEL)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_DADOS_ECONOMIA_IMOVEL;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_DADOS_TARIFA_SOCIAL)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_DADOS_TARIFA_SOCIAL;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_HIDROMETRO_MANTER)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_MANTER_HIDROMETRO;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_RESOLUCAO_DIRETORIA_MANTER)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_MANTER_RESOLUCAO_DIRETORIA;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_RESUMO_ARRECADACAO)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_RESUMO_ARRECADACAO;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_GERAR_RELACAO_DEBITOS)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_GERAR_RELACAO_DEBITOS;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_MOVIMENTO_DEBITO_AUTOMATICO_BANCO)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_MOVIMENTO_DEBITO_AUTOMATICO_BANCO;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_RESUMO_FATURAMENTO_SITUACAO_ESPECIAL)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_RESUMO_FATURAMENTO_SITUACAO_ESPECIAL;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_DEVOLUCAO)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_DEVOLUCAO;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_PAGAMENTO)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_PAGAMENTO;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_CONSULTAR_REGISTRO_ATENDIMENTO)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_CONSULTAR_REGISTRO_ATENDIMENTO;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_2_VIA_CONTA)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_SEGUNDA_VIA_CONTA;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_PARCELAMENTO)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_PARCELAMENTO;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_EXTRATO_DEBITO)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_EXTRATO_DEBITO;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_GUIA_PAGAMENTO_EMITIR)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_EMITIR_GUIA_PAGAMENTO;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_GUIA_DEVOLUCAO)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_GUIA_DEVOLUCAO;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_ROTEIRO_PROGRAMACAO)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_ROTEIRO_PROGRAMACAO;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_CONSULTAR_REGISTRO_ATENDIMENTO_VIA_CLIENTE)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_CONSULTAR_REGISTRO_ATENDIMENTO_VIA_CLIENTE;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_ORDEM_SERVICO)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_ORDEM_SERVICO;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_OPERACAO_CONSULTAR)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_CONSULTAR_OPERACAO;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_NUMERACAO_RA_MANUAL)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_NUMERACAO_RA_MANUAL;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_EXECUCAO_OS)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_ACOMPANHAMENTO_EXECUCAO_OS;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_MOVIMENTO_ARRECADADOR)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_MOVIMENTO_ARRECADADOR;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_MAPA_CONTROLE_CONTA)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_MAPA_CONTROLE_CONTA;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_COMPARATIVOS_LEITURAS_E_ANORMALIDADES)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_COMPARATIVO_LEITURAS_E_ANORMALIDADE;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_2_VIA_CONTA_TIPO_2)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_SEGUNDA_VIA_CONTA_TIPO_2;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_CLIENTES_ESPECIAIS)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_CLIENTES_ESPECIAIS;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_IMOVEL_ENDERECO)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_IMOVEL_ENDERECO;

		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_EMITIR_HISTOGRAMA_AGUA_ECONOMIA)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_HISTOGRAMA_AGUA_ECONOMIA;

		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_EMITIR_HISTOGRAMA_AGUA_LIGACAO)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_HISTOGRAMA_AGUA_LIGACAO;

		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_CADASTRO_CONSUMIDORES_INSCRICAO)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_CADASTRO_CONSUMIDORES_INSCRICAO;

		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_CONTA_TIPO_2)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_CONTA_TIPO_2;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_ORDEM_CORTE)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_ORDEM_CORTE;
		
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_FATURA_CLIENTE_RESPONSAVEL)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_FATURA_CLIENTE_RESPONSAVEL;

		} else if (nomeRelatorio.equals(ConstantesRelatorios.BOLETIM_CADASTRO)) {
			
			retornoCodigoProcesso = Processo.GERAR_BOLETIM_CADASTRO;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_VOLUMES_FATURADOS)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_VOLUMES_FATURADOS;

		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_VOLUMES_FATURADOS_RESUMIDO)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_VOLUMES_FATURADOS_RESUMIDO;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_CONTAS_EM_REVISAO)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_CONTAS_EM_REVISAO;

		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_CONTAS_EM_REVISAO_RESUMIDO)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_CONTAS_EM_REVISAO_RESUMIDO;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_GERAR_CURVA_ABC_DEBITOS)) {
			
			retornoCodigoProcesso = Processo.GERAR_CURVA_ABC_DEBITOS;
		
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_ANORMALIDADE_CONSUMO)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_ANORMALIDADE_CONSUMO;
		
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_EMITIR_HISTOGRAMA_ESGOTO_ECONOMIA)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_HISTOGRAMA_ESGOTO_ECONOMIA;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_EMITIR_HISTOGRAMA_ESGOTO_LIGACAO)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_HISTOGRAMA_ESGOTO_LIGACAO;

		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_ORCAMENTO_SINP)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_ORCAMENTO_SINP;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_IMOVEIS_SITUACAO_LIGACAO_AGUA)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_IMOVEIS_SITUACAO_LIGACAO_AGUA;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_IMOVEIS_FATURAS_ATRASO_AGRUPADAS_LOCALIZACAO)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_IMOVEIS_FATURAS_ATRASO_AGRUPADA_LOCALIZACAO;			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_IMOVEIS_CONSUMO_MEDIO)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_IMOVEIS_CONSUMO_MEDIO;			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_IMOVEIS_ULTIMOS_CONSUMOS_AGUA)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_IMOVEIS_ULTIMOS_CONSUMOS_AGUA;			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_EMITIR_ORDEM_SERVICO_SELETIVA)) {
			
			retornoCodigoProcesso = Processo.GERAR_EMITIR_ORDEM_SERVICO_SELETIVA;
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_EMITIR_ORDEM_SERVICO_SELETIVA_SUGESTAO)) {
			
			retornoCodigoProcesso = Processo.GERAR_EMITIR_ORDEM_SERVICO_SELETIVA_SUGESTAO;
		
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_IMOVEIS_ATIVOS_NAO_MEDIDOS)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_IMOVEIS_ATIVOS_NAO_MEDIDOS;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_ORDEM_FISCALIZACAO)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_ORDEM_FISCALIZACAO;

		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_IMOVEIS_FATURAS_RECENTES_DIA_FATURAS_ANTIGAS_ATRASO)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_IMOVEIS_FATURAS_RECENTES_DIA_FATURAS_ANTIGAS_ATRASO;
		}else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_COMPARATIVO_FATURAMENTO_ARRECADACAO_EXPURGO)) {
			
			retornoCodigoProcesso = Processo.GERAR_COMPARATIVO_FATURAMENTO_ARRECADACAO_EXPURGO;
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_IMOVEIS_TIPO_CONSUMO)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_IMOVEIS_TIPO_CONSUMO;
			
		} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_BOLETIM_CADASTRO)) {
			
			retornoCodigoProcesso = Processo.GERAR_RELATORIO_BOLETIM_CADASTRO;
			
		} else if(nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_CLIENTES_NEGATIVADOS)) {
            
            retornoCodigoProcesso = Processo.GERAR_RELATORIO_ACOMPANHAMENTO_CLIENTES_NEGATIVADOS;
            
        } else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_AVISO_ANORMALIDADE)) {
        	
        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_AVISO_ANORMALIDADE;
        	
        } else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_FATURAMENTO_LIGACOES_MEDICAO_INDIVIDUALIZADA)) {
        	
        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_FATURAMENTO_LIGACOES_MEDICAO_INDIVIDUALIZADA;
        	
        }  else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_GESTAO_SERVICOS_UPA)) {
        	
        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_GESTAO_SERVICOS_UPA;
        	
        }  else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_VOLUMES_CONSUMIDOS_NAO_FATURADOS)) {
        	
        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_VOLUMES_CONSUMIDOS_NAO_FATURADOS;
        	
        } else if(nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_ANALISE_CONSUMO)){
        	
        	retornoCodigoProcesso = Processo.RELATORIO_ANALISE_CONSUMO;
        	
        } else if(nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_IMOVEIS_FATURAS_ATRASO_DESCRITAS_LOCALIZACAO)){
        	
        	retornoCodigoProcesso = Processo.RELATORIO_IMOVEIS_FATURAS_ATRASO_DESCRITAS_LOCALIZACAO;
        	
        } else if(nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_GERAR_DADOS_LEITURA)){
        	
        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_DADOS_LEITURA;
        	
        } else if(nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_FATURAS_AGRUPADAS)){
        	
        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_FATURAS_AGRUPADAS;
        	
        } else if(nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_ROTAS_ONLINE_POR_EMPRESA)){
        	
        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_ROTAS_ONLINE_POR_EMPRESA;
        	
        } else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_PAGAMENTOS_CONTAS_COBRANCA_EMPRESA)){
        	
        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_PAGAMENTOS_CONTAS_COBRANCA_EMPRESA;
        	
        } else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_CONTAS_CANCELADAS)){
        	
        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_CONTAS_CANCELADAS;
        	
        } else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_CONTAS_RETIFICADAS)){
        	
        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_CONTAS_RETIFICADAS;
        	
        } else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_CONTAS_CANCELADAS_SINTETICO)){
        	
        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_CONTAS_CANCELADAS;
        	
        } else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_CONTAS_RETIFICADAS_SINTETICO)){
        	
        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_CONTAS_RETIFICADAS;
        	
        }else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_CADASTRO_CONSUMIDORES_INSCRICAO)){
        	
        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_CADASTRO_CONSUMIDORES_INSCRICAO;
        	
        } else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_FATURAMENTO)){
        	
        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_ACOMPANHAMENTO_FATURAMENTO;
        	
        }else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_IMOVEIS_COM_ACORDO)){
        	
        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_IMOVEIS_COM_ACORDO;
        	
        }else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_RESUMO_DISTRITO_OPERACIONAL)){
        	
        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_RESUMO_DISTRITO_OPERACIONAL;
        	
        }else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_RESUMO_ZONA_ABASTECIMENTO)){
        	
        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_RESUMO_DISTRITO_OPERACIONAL;
        	
        }else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_LEITURA_CONSULTAR_ARQUIVO_TEXTOS)){
        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_LEITURA_CONSULTAR_ARQUIVOS_TEXTOS;
        
        }else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_IMOVEIS_FATURAS_ATRASO_AGRUPADAS_CLIENTE)){
        	
        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_IMOVEIS_FATURAS_ATRASO_AGRUPADA_CLIENTE;
        }else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_IMOVEIS_FATURAS_ATRASO_DESCRITAS_CLIENTE)){

        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_IMOVEIS_FATURAS_ATRASO_DESCRITO_CLIENTE;

        }else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_LEITURISTA)){

        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_ACOMPANHAMENTO_LEITURISTA;

        }else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_JUROS_MULTAS_DEBITOS_CANCELADOS)){

        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_JUROS_MULTAS_DEBITOS_CANCELADOS;

        }else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_ANORMALIDADE_LEITURA_PERIODO)){

        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_ANORMALIDADE_LEITURA_PERIODO;
        }else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_IMOVEIS_PROGRAMAS_ESPECIAIS_ANALITICO)){

        	retornoCodigoProcesso = Processo.RELATORIO_IMOVEIS_PROGRAMAS_ESPECIAIS_ANALITICO;
        }else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_IMOVEIS_PROGRAMAS_ESPECIAIS_SINTETICO)){

        	retornoCodigoProcesso = Processo.RELATORIO_IMOVEIS_PROGRAMAS_ESPECIAIS_SINTETICO;
        }else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_DOCUMENTOS_A_RECEBER)){

        	retornoCodigoProcesso = Processo.RELATORIO_DOCUMENTOS_A_RECEBER;
        }else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_EMITIR_ORDEM_SERVICO_SELETIVA_ANALITICO)){

        	retornoCodigoProcesso = Processo.GERAR_EMITIR_ORDEM_SERVICO_SELETIVA;
        }else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_COLETA_MEDIDOR_ENERGIA)){

        	retornoCodigoProcesso = Processo.RELATORIO_COLETA_MEDIDOR_ENERGIA;
        }else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_PAGAMENTO_ENTIDADES_BENEFICENTES_ANALITICO)){

        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_PAGAMENTO_ENTIDADES_BENEFIC;
        }else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_PAGAMENTO_ENTIDADES_BENEFICENTES_SINTETICO)){

        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_PAGAMENTO_ENTIDADES_BENEFIC;
        }else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_RESUMO_LIGACOES_CAPACIDADE_HIDROMETRO)){

        	retornoCodigoProcesso = Processo.RELATORIO_RESUMO_LIGACOES_CAPACIDADE_HIDROMETRO;
        }else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_RESUMO_DADOS_CAS)){

        	retornoCodigoProcesso = Processo.RELATORIO_RESUMO_DADOS_CAS;
        }else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_ACESSOS_POR_USUARIO)){

        	retornoCodigoProcesso = Processo.RELATORIO_ACESSOS_POR_USUARIO;
        }else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_FUNCIONALIDADE_E_OPERACAO_POR_GRUPO)){

        	retornoCodigoProcesso = Processo.RELATORIO_FUNCIONALIDADE_E_OPERACAO_POR_GRUPO;
        }else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_BOLETIM_MEDICAO)){

        	retornoCodigoProcesso = Processo.RELATORIO_BOLETIM_MEDICAO;
        }else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_ANORMALIDADE_POR_AMOSTRAGEM)){

        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_ANORMALIDADE_POR_AMOSTRAGEM;
        }else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_ALTERACOES_SISTEMA_COLUNA_USUARIO)){

        	retornoCodigoProcesso = Processo.RELATORIO_ALTERACOES_SISTEMA_COLUNA;
        }else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_ALTERACOES_SISTEMA_COLUNA_LOCALIDADE)){

        	retornoCodigoProcesso = Processo.RELATORIO_ALTERACOES_SISTEMA_COLUNA;
        }else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_REGISTRO_ATENDIMENTO_ANALITICO)){

        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_ACOMPANHAMENTO_REGISTRO_ATENDIMENTO;
        }else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_REGISTRO_ATENDIMENTO_SINTETICO_ENCERRADO)){

        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_ACOMPANHAMENTO_REGISTRO_ATENDIMENTO;
        }else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_REGISTRO_ATENDIMENTO_SINTETICO_ABERTO)){

        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_ACOMPANHAMENTO_REGISTRO_ATENDIMENTO;
        }
        else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_ATUALIZACAO_CADASTRAL_VIA_INTERNET)){

        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_ATUALIZACAO_CADASTRAL_INTERNET;
        }
        else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_IMOVEIS_ALTERACAO_INSCRICAO_VIA_BATCH)){

        	retornoCodigoProcesso = Processo.GERAR_RELATORIO_IMOVEIS_ALTERACAO_INSCRICAO_VIA_BATCH;
        }
        else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_RELIGACAO_CLIENTES_INADIPLENTES)){

        	retornoCodigoProcesso = Processo.RELATORIO_RELIGACAO_CLIENTES_INADIPLENTES;
    	}
        else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_BOLETIM_MEDICAO_ARQUIVO_TXT)){

    		retornoCodigoProcesso = Processo.RELATORIO_BOLETIM_MEDICAO_ARQUIVO_TXT;
    	}
        else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_CONTAS_BAIXADAS_CONTABILMENTE)){

    		retornoCodigoProcesso = Processo.RELATORIO_CONTAS_BAIXADAS_CONTABILMENTE;
    		
    	} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_CLIENTES_NEGATIVADOS_SINTETICO)) {
            
            retornoCodigoProcesso = Processo.GERAR_RELATORIO_ACOMPANHAMENTO_CLIENTES_NEGATIVADOS_SINTETICO;
            
    	} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_IMOVEIS_DOACOES_ENTIDADE)) {
            
            retornoCodigoProcesso = Processo.GERAR_RELATORIO_IMOVEIS_COM_DOACOES;

    	} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_IMOVEIS_DOACOES_IMOVEL)) {
            
            retornoCodigoProcesso = Processo.GERAR_RELATORIO_IMOVEIS_COM_DOACOES;
            
    	}  else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_OS_COBRANCA_RESULTADO)) {
            
            retornoCodigoProcesso = Processo.GERAR_RELATORIO_ACOMPANHAMENTO_OS_COBRANCA_RESULTADO;
            
    	} else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_ORDEM_SERVICO_FISCALIZACAO_SINTETICO)) {
            
            retornoCodigoProcesso = Processo.GERAR_RELATORIO_ORDENS_SERVICO_FISCALIZACAO_SINTETICO;
            
    	}  else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_ORDEM_SERVICO_FISCALIZACAO_ANALITICO)) {
            
            retornoCodigoProcesso = Processo.GERAR_RELATORIO_ORDENS_SERVICO_FISCALIZACAO_ANALITICO;
            
    	}  else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_TRANSFERENCIA_PAGAMENTO)) {
            
            retornoCodigoProcesso = Processo.GERAR_RELATORIO_TRANSFERENCIA_PAGAMENTO;
            
    	}   else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_DOCUMENTO_VISITA_COBRANCA)) {
            
            retornoCodigoProcesso = Processo.GERAR_RELATORIO_DOCUMENTO_VISITA_COBRANCA;
            
    	} /**TODO: COSANPA
		 * @author Adriana Muniz
		 * @date 02/12/2011
		 * 
		 * Identificação do processo relatório de debito
		 * */
        else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_DEBITO)) {
    			
    			retornoCodigoProcesso = Processo.GERAR_RELATORIO_DEBITO;
    	}
		
		/**TODO: COSANPA
		 * @author Wellington Rocha	
		 * @date 27/12/2012
		 * 
		 * Identificação do processo relatório ra
		 * */
        else if (nomeRelatorio.equals(ConstantesRelatorios.RELATORIO_REGISTRO_ATENDIMENTO_POR_UNIDADE_POR_USUARIO)) {
    			
    			retornoCodigoProcesso = Processo.GERAR_RELATORIO_REGISTRO_ATENDIMENTO_POR_UNIDADE_POR_USUARIO;
    	}

		return retornoCodigoProcesso;
	}

	private static ControladorBatchLocal getControladorBatch() {
		ControladorBatchLocalHome localHome = null;
		ControladorBatchLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorBatchLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_BATCH_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}

}