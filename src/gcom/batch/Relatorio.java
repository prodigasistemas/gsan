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
package gcom.batch;

import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.util.SistemaException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Relatorio implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	public static final int MANTER_BAIRRO = 1;
	
	public static final int MANTER_CLIENTE = 2;
	
	public static final int MANTER_SETOR_COMERCIAL = 3;
	
	public static final int MANTER_LOCALIDADE = 4;
	
	public static final int MANTER_LOGRADOURO = 5;
	
	public static final int MANTER_QUADRA = 6;
	
	public static final int MANTER_IMOVEL = 7;
	
	public static final int MANTER_TARIFA_SOCIAL = 8;
	
	public static final int MANTER_SUBCATEGORIA = 9;
	
	public static final int MANTER_ROTA = 10;
	
	public static final int MANTER_CATEGORIA = 11;
	
	public static final int MANTER_CRONOGRAMA_FATURAMENTO = 12;
	
	public static final int MANTER_CRONOGRAMA_COBRANCA = 13;
	
	public static final int MANTER_CRITERIO_COBRANCA = 14;
	
	public static final int MANTER_PERFIL_PARCELAMENTO = 15;
	
	public static final int MANTER_MOVIMENTO_ARRECADADOR = 16;
	
	public static final int MANTER_MENSAGEM_CONTA = 17;
	
	public static final int MANTER_AVISO_BANCARIO = 18;
	
	public static final int MANTER_GUIA_DEVOLUCAO = 19;
	
	public static final int MANTER_IMOVEL_OUTROS_CRITERIOS = 20;
	
	public static final int DADOS_ECONOMIA_IMOVEL = 21;
	
	public static final int DADOS_TARIFA_SOCIAL = 22;
	
	public static final int MANTER_HIDROMETRO = 23;
	
	public static final int MANTER_RESOLUCAO_DIRETORIA = 24;
	
	public static final int RESUMO_ARRECADACAO = 25;
	
	public static final int GERAR_RELACAO_DEBITOS = 26;
	
	public static final int MOVIMENTO_DEBITO_AUTOMATICO_BANCO = 27;
	
	public static final int RESUMO_FATURAMENTO_SITUACAO_ESPECIAL = 28;
	
	public static final int DEVOLUCAO = 29;
	
	public static final int PAGAMENTO = 30;
	
	public static final int CONSULTAR_REGISTRO_ATENDIMENTO = 31;
	
	public static final int SEGUNDA_VIA_CONTA = 32;
	
	public static final int PARCELAMENTO = 33;
	
	public static final int EXTRATO_DEBITO = 34;
	
	public static final int EMITIR_GUIA_PAGAMENTO = 35;
	
	public static final int GUIA_DEVOLUCAO = 36;
	
	public static final int ROTEIRO_PROGRAMACAO = 37;
	
	public static final int CONSULTAR_REGISTRO_ATENDIMENTO_VIA_CLIENTE = 38;
	
	public static final int CONSULTAR_REGISTRO_ATENDIMENTO_VIA_CLIENTE_COSANPA = 159;
	
	public static final int ORDEM_SERVICO = 39;
	
	public static final int CONSULTAR_OPERACAO = 40;
	
	public static final int NUMERACAO_RA_MANUAL = 41;
	
	public static final int ACOMPANHAMENTO_EXECUCAO_OS = 42;
	
	public static final int MOVIMENTO_ARRECADADOR = 43;

	public static final int FATURAMENTO_LIGACOES_MEDICAO_INDIVIDUALIZADA = 44;
	
	public static final int ANALISE_FATURAMENTO = 45;
	
	public static final int RESUMO_FATURAMENTO = 46;
	
	public static final int CONTAS_EMITIDAS = 47;
	
	public static final int MAPA_CONTROLE_CONTA = 48;
	
	public static final int MEDICAO_CONSUMO_LIGACAO_AGUA = 49;
	
	public static final int RESUMO_CONTA_LOCALIDADE = 50;
	
	public static final int RESUMO_IMOVEL_MEDICAO = 51;
	
	public static final int SEGUNDA_VIA_CONTA_TIPO_2 = 52;
	
	public static final int EXTRATO_DEBITO_CLIENTE = 53;
	
	public static final int COMPARATIVO_LEITURAS_E_ANORMALIDADES = 54;
	
	public static final int ANALITICO_FATURAMENTO = 61; 
	
	// public static final int CONSULTAR_DEBITOS = 53;

	public static final int EMITIR_PROTOCOLO_DOCUMENTO_COBRANCA = 55;
	
	public static final int RELACAO_PARCELAMENTO = 56;
	
	public static final int CLIENTES_ESPECIAIS = 57;
	
	public static final int IMOVEL_ENDERECO = 58;
	
	public static final int HISTOGRAMA_AGUA_POR_ECONOMIA = 59;
	
	public static final int HISTOGRAMA_AGUA_LIGACAO = 60;
	
	public static final int CONTA_TIPO_2 = 62;
	
	public static final int ACOMPANHAMENTO_MOVIMENTO_ARRECADADORES_POR_NSA = 63;
	
	public static final int RESUMO_DEVEDORES_DUVIDOSOS = 64;
	
	public static final int ORDEM_CORTE = 65;
	
	public static final int AVISO_DEBITO = 66;	

	public static final int FATURA_CLIENTE_RESPONSAVEL = 67;
	
	public static final int BOLETIM_CADASTRO = 68;
	
	public static final int GERAR_DADOS_PARA_LEITURA = 69;

	public static final int CADASTRO_CONSUMIDORES_INSCRICAO = 70;
	
	public static final int VOLUMES_FATURADOS = 71;
	
	public static final int VOLUMES_FATURADOS_RESUMIDO = 72;
	
	public static final int CONTAS_EM_REVISAO = 74;
	
	public static final int CONTAS_EM_REVISAO_RESUMIDO = 75;
	
	public static final int GERAR_INDICES_ACRESCIMOS_IMPONTUALIDADE = 76;
	
	public static final int GERAR_CURVA_ABC_DEBITOS = 77;

	public static final int QUALIDADE_AGUA = 78;
	
	public static final int ANORMALIDADE_CONSUMO = 79;
	
	public static final int HISTOGRAMA_ESGOTO_LIGACAO = 80;
	
	public static final int HISTOGRAMA_ESGOTO_POR_ECONOMIA = 81;
	
	public static final int ANALISE_CONSUMO = 82;
	
	public static final int ORCAMENTO_SINP = 83;
	
	public static final int IMOVEIS_SITUACAO_LIGACAO_AGUA = 84;
	
	public static final int IMOVEIS_FATURAS_ATRASO_AGRUPADAS_LOCALIZACAO = 85;

	public static final int IMOVEIS_FATURAS_ATRASO_AGRUPADAS_CLIENTE = 139;
	
	public static final int IMOVEIS_CONSUMO_MEDIO = 86;

	public static final int RELATORIO_RELACAO_OS_ENCERRADAS_PENDENTES = 87;
	
	public static final int RELATORIO_RESUMO_OS_ENCERRADAS_PENDENTES = 88;
	
	public static final int IMOVEIS_ULTIMOS_CONSUMOS_AGUA = 89;

	public static final int RELATORIO_EMITIR_ORDEM_SERVICO_SELETIVA = 90;
	
	public static final int RELATORIO_EMITIR_ORDEM_SERVICO_SELETIVA_SUGESTAO = 91;
	
	public static final int QUADRO_METAS_ACUMULADO = 92;
	
	public static final int RELATORIO_ORDEM_FISCALIZACAO = 95;

	public static final int IMOVEIS_TIPO_CONSUMO  = 93;
	
	public static final int IMOVEIS_ATIVOS_NAO_MEDIDO = 94;	
	
	public static final int IMOVEIS_FATURAS_RECENTES_DIA_FATURAS_ANTIGAS_ATRASO = 96;
	
	public static final int COMPARATIVO_FATURAMENTO_ARRECADACAO_EXPURGO = 97;
	
	public static final int EVOLUCAO_CONTAS_A_RECEBER_CONTABIL  = 98;
	
	public static final int SALDO_CONTAS_A_RECEBER_CONTABIL  = 99;
	
	public static final int RELATORIO_BOLETIM_CADASTRO  = 100;
	
	public static final int CERTIDAO_NEGATIVA = 101;
	
	public static final int MANTER_NEGATIVADOR_REGISTRO_TIPO = 102;
	
	public static final int MANTER_NEGATIVADOR_RETORNO_MOTIVO = 103;
	
	public static final int MANTER_NEGATIVADOR_EXCLUSAO_MOTIVO = 104;
	
	public static final int MANTER_NEGATIVADOR_CONTRATO = 105;
	
	public static final int MANTER_NEGATIVADOR = 106;

	public static final int QUADRO_METAS_EXERCICIO = 107;
	
	public static final int GERAR_RELATORIO_ACOMPANHAMENTO_CLIENTES_NEGATIVADOS = 108;
	
	public static final int GERAR_RELATORIO_NEGATIVACOES_EXCLUIDAS = 109;

	public static final int RELATORIO_RELACAO_OS_CONCLUIDAS = 110;
    
    public static final int CONTAS_BAIXADAS_CONTABILMENTE = 111;
    
    public static final int RELATORIO_BOLETIM_ORDENS_SERVICO_CONCLUIDAS = 112;
    
    public static final int RELATORIO_CONTAS_CANCELADAS = 113;
    
    public static final int RELATORIO_CONTAS_RETIFICADAS = 114; 
    
    public static final int RELATORIO_CONTAS_CANCELADAS_SINTETICO = 136;
    
    public static final int RELATORIO_CONTAS_RETIFICADAS_SINTETICO = 137;   
    
	public static final int RELATORIO_RESULTADO_SIMULACAO = 115;
	
	public static final int RELATORIO_AVISO_ANORMALIDADE = 116;
	
	public static final int RELATORIO_RELACAO_SERVICO_ACOMPANHAMENTO_REPAVIMENTACAO = 117;
	
	public static final int RELATORIO_PARAMETROS_CONTABEIS = 118;

	public static final int RELATORIO_VOLUMES_CONSUMIDOS_NAO_FATURADOS = 119;
	
	public static final int RELATORIO_GESTAO_SERVICOS_UPA = 120;	
	
	public static final int IMOVEIS_FATURAS_ATRASO_DESCRITAS_LOCALIZACAO = 121;

	public static final int IMOVEIS_FATURAS_ATRASO_DESCRITAS_CLIENTE = 138;

	public static final int RELATORIO_GERAR_DADOS_LEITURA = 122;
	
	public static final int RELATORIO_FATURAS_AGRUPADAS = 123;
	
	public static final int RELATORIO_ROTAS_ONLINE_POR_EMPRESA = 125;
	
	public static final int ANALISE_IMOVEL_CORPORATIVO_GRANDE = 126;
	
	public static final int RELATORIO_PAGAMENTOS_CONTAS_COBRANCA_EMPRESA = 127;
	
	public static final int RELATORIO_PAGAMENTOS_CONTAS_COBRANCA_EMPRESA_SINTETICO = 128;
	
	public static final int RELATORIO_GUIA_PAGAMENTO_EM_ATRASO = 129;
	
	public static final int RELATORIO_ACOMPANHAMENTO_FATURAMENTO = 130;
	
	public static final int RELATORIO_IMOVEIS_COM_ACORDO = 131;
	
	public static final int RELATORIO_RESUMO_DISTRITO_OPERACIONAL = 132;
	
	public static final int RELATORIO_RESUMO_ZONA_ABASTECIMENTO = 133;
	
	public static final int RELATORIO_BOLETIM_CUSTO_ATUALIZACAO_CADASTRAL = 134; 
	
	public static final int RELATORIO_ANALISAR_METAS_CICLO = 135;
	
	public static final int RELATORIO_ACOMPANHAMENTO_ACOES_COBRANCA = 140;
	
	public static final int RELATORIO_ACOMPANHAMENTO_LEITURISTA = 144;

	public static final int RELATORIO_HISTORICO_MEDICAO_POCO = 141;
	
	public static final int RELATORIO_JUROS_MULTAS_DEBITOS_CANCELADOS = 142;

	public static final int RELATORIO_DEBITO_COBRADO_CONTA = 143;

	public static final int RELATORIO_COMANDO_DOCUMENTO_COBRANCA = 145;
	
	public static final int RELATORIO_ACRESCIMO_POR_IMPONTUALIDADE = 147;
	
	public static final int RELATORIO_ANORMALIDADE_LEITURA_PERIODO = 146;
	
	public static final int RELATORIO_IMOVEL_PROGRAMA_ESPECIAL = 147;
	
	public static final int RELATORIO_PAGAMENTO_ENTIDADES_BENEFICENTES_ANALITICO = 148;

	public static final int RELATORIO_IMOVEIS_PROGRAMAS_ESPECIAIS_ANALITICO = 149;
	
	public static final int RELATORIO_IMOVEIS_PROGRAMAS_ESPECIAIS_SINTETICO = 150;
	
	public static final int RELATORIO_PAGAMENTO_ENTIDADES_BENEFICENTES_SINTETICO = 151;
	
	public static final int RELATORIO_DOCUMENTOS_A_RECEBER = 152;
	
	public static final int RELATORIO_EMITIR_ORDEM_SERVICO_SELETIVA_ANALITICO = 153;
	
	public static final int RELATORIO_COLETA_MEDIDOR_ENERGIA = 154;
	
	public static final int RELATORIO_EMITIR_BOLETIM_CADASTRO_INDIVIDUAL = 155;
	
	public static final int RELATORIO_RESUMO_LIGACOES_CAPACIDADE_HIDROMETRO = 156;

	public static final int RELATORIO_RESUMO_ACOES_COBRANCA_EVENTUAIS = 157;

	public static final int RELATORIO_DEMONSTRATIVO_SINTETICO_LIGACOES = 160;
	
	public static final int RELATORIO_RESUMO_RECEITA_SINTETICO = 161;
	
	public static final int RELATORIO_RESUMO_RECEITA_ANALITICO = 162;
	
	public static final int RELATORIO_RESUMO_DADOS_CAS = 163;
	
	public static final int RELATORIO_RELACAO_ORDEM_REPAVIMENTACAO_PROCESSO_ACEITE = 164;

	public static final int RELATORIO_NOTIFICACAO_DEBITOS_IMPRESSAO_SIMULTANEA = 165;

	public static final int RELATORIO_FUNCIONALIDADE_E_OPERACAO_POR_GRUPO = 166;
	
	public static final int RELATORIO_ACESSOS_POR_USUARIO = 167;
	
	public static final int RELATORIO_ANALISE_PAGAMENTO_CARTAO_DEBITO = 168;
	
	public static final int RELATORIO_BOLETIM_MEDICAO = 169;
	
	public static final int RELATORIO_ANORMALIDADE_POR_AMOSTRAGEM = 170;
	
	public static final int  REL_ALTERACOES_NO_SISTEMA_COLUNA = 171;
	
	public static final int  RELATORIO_ACOMPANHAMENTO_RA = 172;
	
	public static final int RELATORIO_ATUALIZACAO_CADASTRAL_VIA_INTERNET = 173;
	
	public static final int RELATORIO_RESUMO_ATUALIZACAO_CADASTRAL_VIA_INTERNET = 174;
	
	public static final int RELATORIO_SOLICITACAO_ACESSO = 176;
	
	/** identifier field */
    public static final int RELATORIO_MONITORAR_LEITURA_MOBILE = 175;   
    
    public static final int RELATORIO_BOLETIM_CUSTO_PAVIMENTO = 177;
    
    public static final int MANTER_CUSTO_PAVIMENTO = 178;
    
    public static final int RELATORIO_IMOVEIS_ALTERACAO_INSCRICAO_VIA_BATCH = 179;
    
    public static final int RELATORIO_RELIGACAO_CLIENTES_INADIPLENTES = 180;
	
	public static final int RELATORIO_BOLETIM_MEDICAO_ARQUIVO_TXT = 181;
	
	public static final int RELATORIO_DEVOLUCAO_PAGAMENTO_DUPLICIDADE = 182;
	
	public static final int GERAR_RELATORIO_ACOMPANHAMENTO_CLIENTES_NEGATIVADOS_SINTETICO = 183;
	
	public static final int GERAR_RELATORIO_RESUMO_QTDE_IMOVEIS_EXCLUIDOS_TARIFA_SOCIAL = 184;
	
	public static final int GERAR_RELATORIO_RESUMO_QTDE_IMOVEIS_EXCLUIDOS_TARIFA_SOCIAL_TIPO_2 = 185;
	
	public static final int MANTER_GERENCIA_REGIONAL = 186;

	public static final int GERAR_RELATORIO_OS_EXECUTADAS_PRESTADORA_SERVICO = 186;
	
	public static final int GERAR_RELATORIO_OS_EXECUTADAS_PRESTADORA_SERVICO_SINTETICO = 187;

	public static final int GERAR_RELATORIO_ACESSO_SPC = 188;	
	
	public static final int RELATORIO_OS_FISCALIZACAO = 189;
	
	public static final int RELATORIO_OS_SITUACAO_ANALITICO = 190;
	
	public static final int GERAR_RELATORIO_IMOVEIS_COM_LEITURAS_TIPO_1 = 191;
	
	public static final int GERAR_RELATORIO_IMOVEIS_COM_LEITURAS_TIPO_2 = 192;
	
	public static final int GERAR_RELATORIO_IMOVEIS_COM_LEITURAS_TIPO_3 = 193;
	
	public static final int GERAR_RELATORIO_IMOVEIS_COM_LEITURAS_TIPO_4 = 194;
	
	public static final int GERAR_RELATORIO_IMOVEIS_COM_LEITURAS_TIPO_5 = 195;
	
	public static final int GERAR_RELATORIO_IMOVEIS_COM_LEITURAS_TIPO_6 = 196;
	
	public static final int GERAR_RELATORIO_IMOVEIS_COM_LEITURAS_TIPO_7 = 197;

	public static final int RELATORIO_IMOVEIS_DOACOES_IMOVEL = 198;

	public static final int RELATORIO_IMOVEIS_DOACOES_ENTIDADE = 199;

	public static final int RELATORIO_ACOMPANHAMENTO_OS_COBRANCA_RESULTADO = 200;
	
	public static final int RELATORIO_OS_SITUACAO_SINTETICO = 201;
	
	public static final int RELATORIO_ACOMPANHAMENTO_BOLETIM_MEDICAO = 202;	
	
	public static final int RELATORIO_DOCUMENTOS_PARCELAMENTO_LOJA_VIRTUAL = 203;
	
	public static final int RELATORIO_ESTRUTURA_TARIFARIA_LOJA_VIRTUAL = 204;
	
	public static final int RELATORIO_ORDEM_SERVICO_FISCALIZACAO_ANALITICO = 205;
	
	public static final int RELATORIO_ORDEM_SERVICO_FISCALIZACAO_SINTETICO = 206;
	
	public static final int RELATORIO_TRANSFERENCIA_PAGAMENTO = 207;
	
	public static final int RELATORIO_DOCUMENTO_VISITA_COBRANCA = 210;
	
 /**
     * TODO : COSANPA
     * Pamela Gatinho - 08/09/2011
     */
    public static final int RELATORIO_LEITURAS_REALIZADAS = 1001;
    
    /**
     * TODO : COSANPA
     * Pamela Gatinho - 15/09/2011
     */
    public static final int RELATORIO_CONTAS_RETIDAS = 1000;
    
    /**
     * TODO : COSANPA
     * Pamela Gatinho - 22/09/2011
     */
    public static final int RELATORIO_MEDICAO_FATURAMENTO = 1002;
    
    /**
     * TODO : COSANPA
     * @autor Adriana Muniz - 22/12/2011
     */
    public static final int SIMULADOR_DEBITO = 1003;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricaoRelatorio;

    /** nullable persistent field */
    private String descricaoAbreviada;

    /** persistent field */
    private short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Set relatoriosGerados;

    /** full constructor */
    public Relatorio(String descricaoRelatorio, String descricaoAbreviada, short indicadorUso, Date ultimaAlteracao, Set relatoriosGerados) {
        this.descricaoRelatorio = descricaoRelatorio;
        this.descricaoAbreviada = descricaoAbreviada;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.relatoriosGerados = relatoriosGerados;
    }

    /** default constructor */
    public Relatorio() {
    }

    /** minimal constructor */
    public Relatorio(short indicadorUso, Set relatoriosGerados) {
        this.indicadorUso = indicadorUso;
        this.relatoriosGerados = relatoriosGerados;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoRelatorio() {
        return this.descricaoRelatorio;
    }

    public void setDescricaoRelatorio(String descricaoRelatorio) {
        this.descricaoRelatorio = descricaoRelatorio;
    }

    public String getDescricaoAbreviada() {
        return this.descricaoAbreviada;
    }

    public void setDescricaoAbreviada(String descricaoAbreviada) {
        this.descricaoAbreviada = descricaoAbreviada;
    }

    public short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Set getRelatoriosGerados() {
        return this.relatoriosGerados;
    }

    public void setRelatoriosGerados(Set relatoriosGerados) {
        this.relatoriosGerados = relatoriosGerados;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    
    
    public byte[] gerarRelatorio(String nomeRelatorio, Map parametrosRelatorio,
            RelatorioDataSource relatorioDataSource)
            throws RelatorioVazioException {

        // valor de retorno
        ByteArrayOutputStream retorno = new ByteArrayOutputStream();
        byte[] retornoArray = null;

        // cria uma instância da classe JasperReport que vai conter o relatório
        // criado
        JasperReport jasperReport = null;

        InputStream stream = null;

        try {

            // carrega o arquivo do relatório (jasper) já compilado
            stream = (ConstantesRelatorios.getURLRelatorio("/relatorio.jasper"))
                    .openStream();

            // carrega o relatório compilado
            jasperReport = (JasperReport) JRLoader.loadObject(stream);

            stream.close();

            JasperPrint jasperPrint = null;
           
            // Setar o Locale para brasileiro para todos os relatorios
            parametrosRelatorio.put( "REPORT_LOCALE", new Locale( "pt", "BR" ) );

            //setar o locale brasileiro para todos os relatórios
            parametrosRelatorio.put("REPORT_LOCALE", new Locale("pt", "BR"));
           
            jasperPrint = JasperFillManager.fillReport(jasperReport,
                    parametrosRelatorio, relatorioDataSource);

            JRPdfExporter exporterPDF = new JRPdfExporter();
            exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT,
                    jasperPrint);
            exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM,
                    retorno);
            exporterPDF.exportReport();

            retornoArray = retorno.toByteArray();

        } catch (JRException ex) {
            // erro ao cria o relatório
            ex.printStackTrace();
            throw new SistemaException(ex, "Erro ao criar relatório");
        } catch (IOException e) {
            e.printStackTrace();
            throw new SistemaException(e, "Erro ao criar relatório");
		}finally{
			if (stream != null) {
				try {
					stream.close();					
				} catch (IOException e) {
					e.printStackTrace();
					throw new SistemaException(e, "Erro ao criar relatório");
				}
			}
		}


        // retorna o relatório gerado

        return retornoArray;
    }


}
	