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
package gcom.cadastro.imovel;

import gcom.atendimentopublico.ligacaoagua.LigacaoAgua;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.SituacaoAtualizacaoCadastral;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.QuadraFace;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.CobrancaSituacaoTipo;
import gcom.faturamento.FaturamentoSituacaoMotivo;
import gcom.faturamento.FaturamentoSituacaoTipo;
import gcom.faturamento.FaturamentoTipo;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.Rota;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Hibernate CodeGenerator
 * @created 1 de Junho de 2004
 */
@ControleAlteracao()
public class Imovel extends ObjetoTransacao {
	
	
	private static final long serialVersionUID = 1L;

	public static final int ATRIBUTOS_IMOVEL_INSERIR = 9; //Operacao.OPERACAO_IMOVEL_INSERIR
	public static final int ATRIBUTOS_IMOVEL_ATUALIZAR = 17; //Operacao.OPERACAO_IMOVEL_ATUALIZAR
	public static final int ATRIBUTOS_IMOVEL_TRANSFERIR = 1831; //Operacao.OPERACAO_TRANSFERIR_IMOVEL_LOGRADOURO
	public static final int ATRIBUTOS_IMOVEL_REMOVER = 292; //Operacao.OPERACAO_IMOVEL_REMOVER
	public static final int ATRIBUTOS_EXCLUIR_NEGATIVACAO_ONLINE = 1132; // Operacao.OPERACAO_EXCLUIR_NEGATIVACAO_ONLINE
	public static final int ATRIBUTOS_ATUALIZAR_DADOS_IMOVEL_PROMAIS = 1681; //Operacao.OPERACAO_ATUALIZAR_DADOS_IMOVEL_PROMAIS
	public static final int ALTERAR_IMOVEL_FATURAMENTO = 1705;
	public static final int OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL = 1509;
	
	
	/**
	 * identifier field
	 */
	private Integer id;

	/**
	 * persistent field
	 */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private short lote;

	/**
	 * persistent field
	 */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private short subLote;

	/**
	 * persistent field
	 */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private Short testadaLote;

	private Short numeroReparcelamentoConsecutivos;

	/**
	 * persistent field
	 */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER,OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
	private String numeroImovel;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_ATUALIZAR_DADOS_IMOVEL_PROMAIS,OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
	private String nomeImovel;

	/**
	 * persistent field
	 */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER,OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
	private String complementoEndereco;

	/**
	 * nullable persistent field
	 */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER,
			TarifaSocialDadoEconomia.ATRIBUTOS_INSERIR_TARIFA_SOCIAL,
			TarifaSocialDadoEconomia.ATRIBUTOS_MANTER_TARIFA_SOCIAL})
	private BigDecimal areaConstruida;

	/**
	 * nullable persistent field
	 */
	private Short indicadorImovelCondominio;

	/**
	 * nullable persistent field
	 */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private BigDecimal volumeReservatorioSuperior;

	/**
	 * nullable persistent field
	 */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private BigDecimal volumeReservatorioInferior;

	/**
	 * nullable persistent field
	 */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private BigDecimal volumePiscina;

	/**
	 * nullable persistent field
	 */
	private Date dataSupressaoParcial;

	/**
	 * nullable persistent field
	 */
	private Date dataInfracao;

	/**
	 * persistent field
	 */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private Short numeroPontosUtilizacao;

	/**
	 * persistent field
	 */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER,
			TarifaSocialDadoEconomia.ATRIBUTOS_INSERIR_TARIFA_SOCIAL,
			TarifaSocialDadoEconomia.ATRIBUTOS_MANTER_TARIFA_SOCIAL,OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
	private Short numeroMorador;

	/**
	 * nullable persistent field
	 */
	private Short numeroRetificacao;

	/**
	 * nullable persistent field
	 */
	private Short numeroLeituraExcecao;

	/**
	 * nullable persistent field
	 */
	private Short numeroParcelamento;

	/**
	 * nullable persistent field
	 */
	private Short numeroReparcelamento;

	/**
	 * nullable persistent field
	 */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private Short diaVencimento;

	/**
	 * nullable persistent field
	 */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER,
			TarifaSocialDadoEconomia.ATRIBUTOS_INSERIR_TARIFA_SOCIAL,TarifaSocialDadoEconomia.ATRIBUTOS_MANTER_TARIFA_SOCIAL})
	private BigDecimal numeroIptu;

	/**
	 * nullable persistent field
	 */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER,
			TarifaSocialDadoEconomia.ATRIBUTOS_INSERIR_TARIFA_SOCIAL,TarifaSocialDadoEconomia.ATRIBUTOS_MANTER_TARIFA_SOCIAL})
	private Long numeroCelpe;

	/**
	 * nullable persistent field
	 */
	private Short indicadorConta;

	/** persistent field */
//	@ControleAlteracao(value=FiltroImovel.IMOVEL_CONTA_ENVIO,
//			funcionalidade={ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private ImovelContaEnvio imovelContaEnvio;

	/**
	 * nullable persistent field
	 */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private Short indicadorEmissaoExtratoFaturamento;

	/**
	 * nullable persistent field
	 */
	private Short indicadorDebitoConta;

	/**
	 * nullable persistent field
	 */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private Short indicadorExclusao;

	/**
	 * nullable persistent field
	 */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private String coordenadaX;

	/**
	 * nullable persistent field
	 */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private String coordenadaY;

	/**
	 * nullable persistent field
	 */
	private LigacaoEsgoto ligacaoEsgoto;

	/**
	 * nullable persistent field
	 */
	@ControleAlteracao(value=FiltroImovel.LIGACAO_AGUA)
	private LigacaoAgua ligacaoAgua;

	/**
	 * nullable persistent field
	 */
	private ImovelEnderecoAnterior imovelEnderecoAnterior;

	/**
	 * persistent field
	 */
	@ControleAlteracao(value=FiltroImovel.IMOVEL_PRINCIPAL, funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR})
	private gcom.cadastro.imovel.Imovel imovelPrincipal;

	/**
	 * persistent field
	 */
	private gcom.cadastro.imovel.Imovel imovelCondominio;

	/**
	 * persistent field
	 */
	@ControleAlteracao(value=FiltroImovel.LIGACAO_ESGOTO_SITUACAO,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR, LigacaoEsgoto.ATRIBUTOS_EFETUAR_LIGACAO_ESGOTO, 
				LigacaoEsgoto.ATRIBUTOS_EFETUAR_MUDANCA_SITUACAO_FATURAMENTO_LIGACAO_ESGOTO,OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})	
	private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;

	/**
	 * persistent field
	 */
	private HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico;

	/**
	 * persistent field
	 */
	@ControleAlteracao(value=FiltroImovel.ANORMALIDADE_INFORMADA, funcionalidade={ALTERAR_IMOVEL_FATURAMENTO})
	private LeituraAnormalidade leituraAnormalidade;

	/**
	 * persistent field
	 */
	private gcom.cadastro.imovel.EloAnormalidade eloAnormalidade;

	/**
	 * persistent field
	 */
	@ControleAlteracao(value=FiltroImovel.SETOR_COMERCIAL,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private SetorComercial setorComercial;

	/**
	 * persistent field
	 */
	@ControleAlteracao(value=FiltroImovel.AREA_CONSTRUIDA_FAIXA,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER,
			TarifaSocialDadoEconomia.ATRIBUTOS_INSERIR_TARIFA_SOCIAL, TarifaSocialDadoEconomia.ATRIBUTOS_MANTER_TARIFA_SOCIAL})
	private gcom.cadastro.imovel.AreaConstruidaFaixa areaConstruidaFaixa;

	/**7
	 * persistent field
	 */
	@ControleAlteracao(value=FiltroImovel.PAVIMENTO_CALCADA,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private gcom.cadastro.imovel.PavimentoCalcada pavimentoCalcada;

	/**
	 * persistent field
	 */
	@ControleAlteracao(value=FiltroImovel.IMOVEL_PERFIL,
			funcionalidade={OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
	private gcom.cadastro.imovel.ImovelPerfil imovelPerfil;

	/**
	 * persistent field
	 */
	@ControleAlteracao(value=FiltroImovel.RESERVATORIO_VOLUME_FAIXA_SUPERIOR,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private gcom.cadastro.imovel.ReservatorioVolumeFaixa reservatorioVolumeFaixaSuperior;

	/**
	 * persistent field
	 */
	@ControleAlteracao(value=FiltroImovel.RESERVATORIO_VOLUME_FAIXA_INFERIOR,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private gcom.cadastro.imovel.ReservatorioVolumeFaixa reservatorioVolumeFaixaInferior;

	/**
	 * persistent field
	 */
	@ControleAlteracao(value=FiltroImovel.LOCALIDADE,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private Localidade localidade;

	/**
	 * persistent field
	 */
	@ControleAlteracao(value=FiltroImovel.QUADRA,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private Quadra quadra;

	/**
	 * persistent field
	 */
	private CobrancaSituacaoTipo cobrancaSituacaoTipo;

	/**
	 * 
	 */
    @ControleAlteracao(value=FiltroImovel.COBRANCA_SITUACAO, 
    		funcionalidade={ATRIBUTOS_EXCLUIR_NEGATIVACAO_ONLINE})
	private CobrancaSituacao cobrancaSituacao;

	/**
	 * persistent field
	 */
	@ControleAlteracao(value=FiltroImovel.PAVIMENTO_RUA,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private gcom.cadastro.imovel.PavimentoRua pavimentoRua;

	/**
	 * persistent field
	 */
	@ControleAlteracao(value=FiltroImovel.ENDERECO_REFERENCIA,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private EnderecoReferencia enderecoReferencia;

	/**
	 * persistent field
	 */
	@ControleAlteracao(value=FiltroImovel.CADASTRO_OCORRENCIA,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER,OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
	private gcom.cadastro.imovel.CadastroOcorrencia cadastroOcorrencia;

	/**
	 * persistent field
	 */
	@ControleAlteracao(value=FiltroImovel.POCO_TIPO,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private gcom.cadastro.imovel.PocoTipo pocoTipo;

	/**
	 * persistent field
	 */
	@ControleAlteracao(value=FiltroImovel.LIGACAO_AGUA_SITUACAO,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,LigacaoAgua.ATRIBUTOS_EFETUAR_RESTABELECIMENTO_LIGACAO_AGUA, 
				LigacaoAgua.ATRIBUTOS_EFETUAR_RELIGACAO_AGUA, LigacaoAgua.ATRIBUTOS_EFETUAR_SUPRESSAO_LIGACAO_AGUA,
				LigacaoAgua.ATRIBUTOS_RELIGAR_IMOVEIS_CORTADOS_COM_CONSUMO_REAL,OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
	private LigacaoAguaSituacao ligacaoAguaSituacao;

	/**
	 * persistent field
	 */
	@ControleAlteracao(value=FiltroImovel.DESPEJO,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private gcom.cadastro.imovel.Despejo despejo;

	/**
	 * persistent field
	 */
	private FaturamentoSituacaoTipo faturamentoSituacaoTipo;

	/**
	 * persistent field
	 */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private gcom.cadastro.imovel.FonteAbastecimento fonteAbastecimento;

	/**
	 * persistent field
	 */
	@ControleAlteracao(value=FiltroImovel.PISCINA_VOLUME_FAIXA,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private gcom.cadastro.imovel.PiscinaVolumeFaixa piscinaVolumeFaixa;
	
	/**
	 * persistent field
	 */
	@ControleAlteracao(value=FiltroImovel.IMOVEL_TIPO_HABITACAO,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private gcom.cadastro.imovel.ImovelTipoHabitacao imovelTipoHabitacao;
	
	/**
	 * persistent field
	 */
	@ControleAlteracao(value=FiltroImovel.IMOVEL_TIPO_PROPRIEDADE,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private gcom.cadastro.imovel.ImovelTipoPropriedade imovelTipoPropriedade;


	/**
	 * persistent field
	 */
	@ControleAlteracao(value=FiltroImovel.IMOVEL_TIPO_CONSTRUCAO,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private gcom.cadastro.imovel.ImovelTipoConstrucao imovelTipoConstrucao;
	

	/**
	 * persistent field
	 */
	@ControleAlteracao(value=FiltroImovel.IMOVEL_TIPO_COBERTURA,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private gcom.cadastro.imovel.ImovelTipoCobertura imovelTipoCobertura;
	
	/**
	 * persistent field
	 */
	
	private gcom.faturamento.consumotarifa.ConsumoTarifa consumoTarifa;

	/**
	 * nullable persistent field
	 */
	private Date ultimaAlteracao;
	
//	@ControleAlteracao(FiltroImovel.IMOVEL_SUBCATEGORIAS)
	private Set imovelSubcategorias;

//	@ControleAlteracao(FiltroImovel.CLIENTES_IMOVEIS)
	private Set clienteImoveis;

	private Set medicaoHistoricos;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER,OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL})
	private Short quantidadeEconomias;

	private FaturamentoTipo faturamentoTipo;

	private FaturamentoSituacaoMotivo faturamentoSituacaoMotivo;

	private Set consumosHistoricos;

	private Short indicadorSuspensaoAbastecimento;

	@ControleAlteracao(value=FiltroImovel.BAIRRO,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER,ATRIBUTOS_IMOVEL_TRANSFERIR})
	private LogradouroBairro logradouroBairro;
	
	@ControleAlteracao(value=FiltroImovel.LOGRADOURO_CEP,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER,ATRIBUTOS_IMOVEL_TRANSFERIR})
	private LogradouroCep logradouroCep;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
    private Short indicadorJardim;
    
	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
    private Integer numeroSequencialRota;
	
	@ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
    private Integer numeroSequencialRotaEntrega;
	
	@ControleAlteracao(value=FiltroImovel.ROTA_ENTREGA,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
    private Rota rotaEntrega;
	
	@ControleAlteracao(value=FiltroImovel.ROTA_ALTERNATIVA,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
    private Rota rotaAlternativa;
    
	@ControleAlteracao(value=FiltroImovel.FUNCIONARIO,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
    private Funcionario funcionario;
    
    private SituacaoAtualizacaoCadastral situacaoAtualizacaoCadastral;
    
    @ControleAlteracao(value=FiltroImovel.LOGRADOURO_TIPO_PERIMETRO_INICIAL,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR})
    private Logradouro perimetroInicial;
    
    @ControleAlteracao(value=FiltroImovel.LOGRADOURO_TIPO_PERIMETRO_FINAL,
			funcionalidade={ATRIBUTOS_IMOVEL_INSERIR})
    private Logradouro perimetroFinal;
    
    /**
	 * persistent field
	 */
	@ControleAlteracao(value=FiltroImovel.QUADRA_FACE,
	funcionalidade={ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
	private QuadraFace quadraFace;
    
    private Set contas;
    
    @ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
    private String numeroMedidorEnergia;
    
    @ControleAlteracao(funcionalidade={ATRIBUTOS_IMOVEL_INSERIR,ATRIBUTOS_IMOVEL_ATUALIZAR,ATRIBUTOS_IMOVEL_REMOVER})
    private Date dataVisitaComercial;
    
    private Short indicadorVencimentoMesSeguinte;
    
    private String informacoesComplementares;
    
    private Integer indicadorReincidenciaInfracao;
    
    private Integer codigoDebitoAutomatico;
    
    private Integer numeroQuadraEntrega;
    
    private Integer anoMesExclusaoTarifaSocial;
    
    private Short indicadorNivelInstalacaoEsgoto;
    
    private Short indicadorImovelAreaComum;
    
    private Integer categoriaPrincipalId;
    
    private Integer subCategoriaPrincipalId;
    
	/**
	 * Constante do indicador de conta quando o indicador for para o responsavel
	 */
	public final static Short INDICADOR_CONTA_RESPONSAVEL = new Short("1");

	/**
	 * Constante do indicador de conta o indicador quando for para o imovel
	 */
	public final static Short INDICADOR_CONTA_IMOVEL = new Short("2");

	/**
	 * Constante do indicador de conta quando o indicador for nao pagavel para o
	 * imovel e pagavel para o responsavel
	 */
	public final static Short INDICADOR_CONTA_NAO_PAGAVEL_PARA_IMOVEL_PAGAVEL_PARA_RESPONSAVEL = new Short(
			"3");

	/**
	 * Description of the Field
	 */
	public final static Short IMOVEL_CONDOMINIO = new Short("1");

	public final static Short IMOVEL_NAO_CONDOMINIO = new Short("2");

	/**
	 * Situação do Imovel Excluído
	 */
	public final static Short IMOVEL_EXCLUIDO = new Short("1");
	
	/**
	 * Indicador do Jardim
	 */
	public final static Short INDICADOR_JARDIM_SIM = new Short("1");
	
	public final static Short INDICADOR_DEBITO_AUTOMATICO = new Short("1");
	
	public final static Short INDICADOR_NAO_DEBITO_AUTOMATICO = new Short("2");

	public Imovel(
			short lote,
			short subLote,
			Short testadaLote,
			String numeroImovel,
			String complementoEndereco,
			BigDecimal areaConstruida,
			Short indicadorImovelCondominio,
			BigDecimal volumeReservatorioSuperior,
			BigDecimal volumeReservatorioInferior,
			BigDecimal volumePiscina,
			Date dataSupressaoParcial,
			Date dataInfracao,
			Short numeroPontosUtilizacao,
			Short numeroMorador,
			Short numeroRetificacao,
			Short numeroLeituraExcecao,
			Short numeroParcelamento,
			Short numeroReparcelamento,
			Short diaVencimento,
			BigDecimal numeroIptu,
			Long numeroCelpe,
			Short indicadorConta,
			Short indicadorEmissaoExtratoFaturamento,
			Short indicadorDebitoConta,
			Short indicadorExclusao,
			String coordenadaX,
			String coordenadaY,
			LigacaoEsgoto ligacaoEsgoto,
			LigacaoAgua ligacaoAgua,
			ImovelEnderecoAnterior imovelEnderecoAnterior,
			gcom.cadastro.imovel.Imovel imovelPrincipal,
			gcom.cadastro.imovel.Imovel imovelCondominio,
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao,
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico,
			LeituraAnormalidade leituraAnormalidade,
			gcom.cadastro.imovel.EloAnormalidade eloAnormalidade,
			SetorComercial setorComercial,
			gcom.cadastro.imovel.AreaConstruidaFaixa areaConstruidaFaixa,
			gcom.cadastro.imovel.PavimentoCalcada pavimentoCalcada,
			gcom.cadastro.imovel.ImovelPerfil imovelPerfil,
			gcom.cadastro.imovel.ReservatorioVolumeFaixa reservatorioVolumeFaixaSuperior,
			gcom.cadastro.imovel.ReservatorioVolumeFaixa reservatorioVolumeFaixaInferior,
			Localidade localidade, Quadra quadra,
			CobrancaSituacaoTipo cobrancaSituacaoTipo,
			gcom.cadastro.imovel.PavimentoRua pavimentoRua,
			EnderecoReferencia enderecoReferencia,
			gcom.cadastro.imovel.CadastroOcorrencia cadastroOcorrencia,
			gcom.cadastro.imovel.PocoTipo pocoTipo,
			LigacaoAguaSituacao ligacaoAguaSituacao,
			gcom.cadastro.imovel.Despejo despejo,
			FaturamentoSituacaoTipo faturamentoSituacaoTipo,
			gcom.cadastro.imovel.FonteAbastecimento fonteAbastecimento,
			gcom.cadastro.imovel.PiscinaVolumeFaixa piscinaVolumeFaixa,
			Date ultimaAlteracao, LogradouroCep logradouroCep) {
		this.lote = lote;
		this.subLote = subLote;
		this.testadaLote = testadaLote;
		this.numeroImovel = numeroImovel;
		this.complementoEndereco = complementoEndereco;
		this.areaConstruida = areaConstruida;
		this.indicadorImovelCondominio = indicadorImovelCondominio;
		this.volumeReservatorioSuperior = volumeReservatorioSuperior;
		this.volumeReservatorioInferior = volumeReservatorioInferior;
		this.volumePiscina = volumePiscina;
		this.dataSupressaoParcial = dataSupressaoParcial;
		this.dataInfracao = dataInfracao;
		this.numeroPontosUtilizacao = numeroPontosUtilizacao;
		this.numeroMorador = numeroMorador;
		this.numeroRetificacao = numeroRetificacao;
		this.numeroLeituraExcecao = numeroLeituraExcecao;
		this.numeroParcelamento = numeroParcelamento;
		this.numeroReparcelamento = numeroReparcelamento;
		this.diaVencimento = diaVencimento;
		this.numeroIptu = numeroIptu;
		this.numeroCelpe = numeroCelpe;
		this.indicadorConta = indicadorConta;
		this.indicadorEmissaoExtratoFaturamento = indicadorEmissaoExtratoFaturamento;
		this.indicadorDebitoConta = indicadorDebitoConta;
		this.indicadorExclusao = indicadorExclusao;
		this.coordenadaX = coordenadaX;
		this.coordenadaY = coordenadaY;
		this.ligacaoEsgoto = ligacaoEsgoto;
		this.ligacaoAgua = ligacaoAgua;
		this.imovelEnderecoAnterior = imovelEnderecoAnterior;
		this.imovelPrincipal = imovelPrincipal;
		this.imovelCondominio = imovelCondominio;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.hidrometroInstalacaoHistorico = hidrometroInstalacaoHistorico;
		this.leituraAnormalidade = leituraAnormalidade;
		this.eloAnormalidade = eloAnormalidade;
		this.setorComercial = setorComercial;
		this.areaConstruidaFaixa = areaConstruidaFaixa;
		this.pavimentoCalcada = pavimentoCalcada;
		this.imovelPerfil = imovelPerfil;
		this.reservatorioVolumeFaixaSuperior = reservatorioVolumeFaixaSuperior;
		this.reservatorioVolumeFaixaInferior = reservatorioVolumeFaixaInferior;
		this.localidade = localidade;
		this.quadra = quadra;
		this.cobrancaSituacaoTipo = cobrancaSituacaoTipo;
		this.pavimentoRua = pavimentoRua;
		this.enderecoReferencia = enderecoReferencia;
		this.cadastroOcorrencia = cadastroOcorrencia;
		this.pocoTipo = pocoTipo;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.despejo = despejo;
		this.faturamentoSituacaoTipo = faturamentoSituacaoTipo;
		this.fonteAbastecimento = fonteAbastecimento;
		this.piscinaVolumeFaixa = piscinaVolumeFaixa;
		this.ultimaAlteracao = ultimaAlteracao;
		this.logradouroCep = logradouroCep;
	}

	/**
	 * Constructor for the Imovel object
	 * 
	 * @param id
	 *            Description of the Parameter
	 * @param numeroImovel
	 *            Description of the Parameter
	 * @param logradouro
	 *            Description of the Parameter
	 * @param quadra
	 *            Description of the Parameter
	 * @param enderecoReferencia
	 *            Description of the Parameter
	 * @param cep
	 *            Description of the Parameter
	 * @param complementoEndereco
	 *            Description of the Parameter
	 */
	public Imovel(Integer id, String numeroImovel, LogradouroCep logradouroCep,
			Quadra quadra, EnderecoReferencia enderecoReferencia,
			String complementoEndereco, SetorComercial setorComercial,
			Localidade localidade) {
		this.id = id;
		this.numeroImovel = numeroImovel;
		this.logradouroCep = logradouroCep;
		this.quadra = quadra;
		this.enderecoReferencia = enderecoReferencia;
		this.complementoEndereco = complementoEndereco;
		this.setorComercial = setorComercial;
		this.localidade = localidade;
	}
	//Construido por Sávio Luiz para setar o id no objeto imóvel
	public Imovel(Integer id) {
		this.id = id;
	}

	public Imovel(Integer id, String numeroImovel, LogradouroCep logradouroCep,
			LogradouroBairro logradouroBairro, Quadra quadra,
			EnderecoReferencia enderecoReferencia, String complementoEndereco,
			SetorComercial setorComercial, Localidade localidade) {
		this.id = id;
		this.numeroImovel = numeroImovel;
		this.logradouroCep = logradouroCep;
		this.logradouroBairro = logradouroBairro;
		this.quadra = quadra;
		this.enderecoReferencia = enderecoReferencia;
		this.complementoEndereco = complementoEndereco;
		this.setorComercial = setorComercial;
		this.localidade = localidade;
	}

	public Imovel(Integer id, String numeroImovel, LogradouroCep logradouroCep,
			LogradouroBairro logradouroBairro, Quadra quadra,
			EnderecoReferencia enderecoReferencia, String complementoEndereco,
			SetorComercial setorComercial, Localidade localidade,
			Date ultimaAlteracao) {
		this.id = id;
		this.numeroImovel = numeroImovel;
		this.logradouroCep = logradouroCep;
		this.logradouroBairro = logradouroBairro;
		this.quadra = quadra;
		this.enderecoReferencia = enderecoReferencia;
		this.complementoEndereco = complementoEndereco;
		this.setorComercial = setorComercial;
		this.localidade = localidade;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	// ---------Fim Construtores Antigos

	/**
	 * full constructor
	 * 
	 * @param lote
	 *            Description of the Parameter
	 * @param subLote
	 *            Description of the Parameter
	 * @param testadaLote
	 *            Description of the Parameter
	 * @param numeroImovel
	 *            Description of the Parameter
	 * @param complementoEndereco
	 *            Description of the Parameter
	 * @param areaConstruida
	 *            Description of the Parameter
	 * @param indicadorImovelCondominio
	 *            Description of the Parameter
	 * @param volumeReservatorioSuperior
	 *            Description of the Parameter
	 * @param volumeReservatorioInferior
	 *            Description of the Parameter
	 * @param volumePiscina
	 *            Description of the Parameter
	 * @param dataSupressaoParcial
	 *            Description of the Parameter
	 * @param dataInfracao
	 *            Description of the Parameter
	 * @param numeroPontosUtilizacao
	 *            Description of the Parameter
	 * @param numeroMorador
	 *            Description of the Parameter
	 * @param numeroRetificacao
	 *            Description of the Parameter
	 * @param numeroLeituraExcecao
	 *            Description of the Parameter
	 * @param numeroParcelamento
	 *            Description of the Parameter
	 * @param numeroReparcelamento
	 *            Description of the Parameter
	 * @param diaVencimento
	 *            Description of the Parameter
	 * @param numeroIptu
	 *            Description of the Parameter
	 * @param numeroCelpe
	 *            Description of the Parameter
	 * @param indicadorConta
	 *            Description of the Parameter
	 * @param indicadorEmissaoExtratoFaturamento
	 *            Description of the Parameter
	 * @param indicadorDebitoConta
	 *            Description of the Parameter
	 * @param indicadorExclusao
	 *            Description of the Parameter
	 * @param coordenadaX
	 *            Description of the Parameter
	 * @param coordenadaY
	 *            Description of the Parameter
	 * @param ligacaoEsgoto
	 *            Description of the Parameter
	 * @param tarifaSocialDado
	 *            Description of the Parameter
	 * @param ligacaoAgua
	 *            Description of the Parameter
	 * @param imovelEnderecoAnterior
	 *            Description of the Parameter
	 * @param imovelPrincipal
	 *            Description of the Parameter
	 * @param imovelCondominio
	 *            Description of the Parameter
	 * @param ligacaoEsgotoSituacao
	 *            Description of the Parameter
	 * @param logradouro
	 *            Description of the Parameter
	 * @param hidrometroInstalacaoHistorico
	 *            Description of the Parameter
	 * @param leituraAnormalidade
	 *            Description of the Parameter
	 * @param eloAnormalidade
	 *            Description of the Parameter
	 * @param setorComercial
	 *            Description of the Parameter
	 * @param areaConstruidaFaixa
	 *            Description of the Parameter
	 * @param pavimentoCalcada
	 *            Description of the Parameter
	 * @param imovelPerfil
	 *            Description of the Parameter
	 * @param reservatorioVolumeFaixaSuperior
	 *            Description of the Parameter
	 * @param reservatorioVolumeFaixaInferior
	 *            Description of the Parameter
	 * @param localidade
	 *            Description of the Parameter
	 * @param quadra
	 *            Description of the Parameter
	 * @param cobrancaSituacaoTipo
	 *            Description of the Parameter
	 * @param pavimentoRua
	 *            Description of the Parameter
	 * @param enderecoReferencia
	 *            Descrição do parâmetro
	 * @param nomeConta
	 *            Descrição do parâmetro
	 * @param cadastroOcorrencia
	 *            Description of the Parameter
	 * @param pocoTipo
	 *            Description of the Parameter
	 * @param ligacaoAguaSituacao
	 *            Description of the Parameter
	 * @param despejo
	 *            Description of the Parameter
	 * @param cep
	 *            Description of the Parameter
	 * @param faturamentoSituacaoTipo
	 *            Description of the Parameter
	 * @param fonteAbastecimento
	 *            Description of the Parameter
	 * @param piscinaVolumeFaixa
	 *            Description of the Parameter
	 * @param cobrancaSituacao
	 *            Description of the Parameter
	 * @param ultimaAlteracao
	 *            Descrição do parâmetro
	 */
	public Imovel(
			short lote,
			short subLote,
			Short testadaLote,
			Short numeroReparcelamentoConsecutivos,
			String numeroImovel,
			String complementoEndereco,
			BigDecimal areaConstruida,
			Short indicadorImovelCondominio,
			BigDecimal volumeReservatorioSuperior,
			BigDecimal volumeReservatorioInferior,
			BigDecimal volumePiscina,
			Date dataSupressaoParcial,
			Date dataInfracao,
			Short numeroPontosUtilizacao,
			Short numeroMorador,
			Short numeroRetificacao,
			Short numeroLeituraExcecao,
			Short numeroParcelamento,
			Short numeroReparcelamento,
			Short diaVencimento,
			BigDecimal numeroIptu,
			Long numeroCelpe,
			Short indicadorConta,
			Short indicadorEmissaoExtratoFaturamento,
			Short indicadorDebitoConta,
			Short indicadorExclusao,
			String coordenadaX,
			String coordenadaY,
			LigacaoEsgoto ligacaoEsgoto,
			LigacaoAgua ligacaoAgua,
			ImovelEnderecoAnterior imovelEnderecoAnterior,
			gcom.cadastro.imovel.Imovel imovelPrincipal,
			gcom.cadastro.imovel.Imovel imovelCondominio,
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao,
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico,
			LeituraAnormalidade leituraAnormalidade,
			gcom.cadastro.imovel.EloAnormalidade eloAnormalidade,
			SetorComercial setorComercial,
			gcom.cadastro.imovel.AreaConstruidaFaixa areaConstruidaFaixa,
			gcom.cadastro.imovel.PavimentoCalcada pavimentoCalcada,
			gcom.cadastro.imovel.ImovelPerfil imovelPerfil,
			gcom.cadastro.imovel.ReservatorioVolumeFaixa reservatorioVolumeFaixaSuperior,
			gcom.cadastro.imovel.ReservatorioVolumeFaixa reservatorioVolumeFaixaInferior,
			Localidade localidade, Quadra quadra,
			CobrancaSituacaoTipo cobrancaSituacaoTipo,
			gcom.cadastro.imovel.PavimentoRua pavimentoRua,
			EnderecoReferencia enderecoReferencia,
			gcom.cadastro.imovel.CadastroOcorrencia cadastroOcorrencia,
			gcom.cadastro.imovel.PocoTipo pocoTipo,
			LigacaoAguaSituacao ligacaoAguaSituacao,
			gcom.cadastro.imovel.Despejo despejo,
			FaturamentoSituacaoTipo faturamentoSituacaoTipo,
			gcom.cadastro.imovel.FonteAbastecimento fonteAbastecimento,
			gcom.cadastro.imovel.PiscinaVolumeFaixa piscinaVolumeFaixa,
			FaturamentoTipo faturamentoTipo, Date ultimaAlteracao,
			LogradouroCep logradouroCep) {
		this.lote = lote;
		this.subLote = subLote;
		this.testadaLote = testadaLote;
		this.numeroImovel = numeroImovel;
		this.numeroReparcelamentoConsecutivos = numeroReparcelamentoConsecutivos;
		this.complementoEndereco = complementoEndereco;
		this.areaConstruida = areaConstruida;
		this.indicadorImovelCondominio = indicadorImovelCondominio;
		this.volumeReservatorioSuperior = volumeReservatorioSuperior;
		this.volumeReservatorioInferior = volumeReservatorioInferior;
		this.volumePiscina = volumePiscina;
		this.dataSupressaoParcial = dataSupressaoParcial;
		this.dataInfracao = dataInfracao;
		this.numeroPontosUtilizacao = numeroPontosUtilizacao;
		this.numeroMorador = numeroMorador;
		this.numeroRetificacao = numeroRetificacao;
		this.numeroLeituraExcecao = numeroLeituraExcecao;
		this.numeroParcelamento = numeroParcelamento;
		this.numeroReparcelamento = numeroReparcelamento;
		this.diaVencimento = diaVencimento;
		this.numeroIptu = numeroIptu;
		this.numeroCelpe = numeroCelpe;
		this.indicadorConta = indicadorConta;
		this.indicadorEmissaoExtratoFaturamento = indicadorEmissaoExtratoFaturamento;
		this.indicadorDebitoConta = indicadorDebitoConta;
		this.indicadorExclusao = indicadorExclusao;
		this.coordenadaX = coordenadaX;
		this.coordenadaY = coordenadaY;
		this.ligacaoEsgoto = ligacaoEsgoto;
		this.ligacaoAgua = ligacaoAgua;
		this.imovelEnderecoAnterior = imovelEnderecoAnterior;
		this.imovelPrincipal = imovelPrincipal;
		this.imovelCondominio = imovelCondominio;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.hidrometroInstalacaoHistorico = hidrometroInstalacaoHistorico;
		this.leituraAnormalidade = leituraAnormalidade;
		this.eloAnormalidade = eloAnormalidade;
		this.setorComercial = setorComercial;
		this.areaConstruidaFaixa = areaConstruidaFaixa;
		this.pavimentoCalcada = pavimentoCalcada;
		this.imovelPerfil = imovelPerfil;
		this.reservatorioVolumeFaixaSuperior = reservatorioVolumeFaixaSuperior;
		this.reservatorioVolumeFaixaInferior = reservatorioVolumeFaixaInferior;
		this.localidade = localidade;
		this.quadra = quadra;
		this.cobrancaSituacaoTipo = cobrancaSituacaoTipo;
		this.pavimentoRua = pavimentoRua;
		this.enderecoReferencia = enderecoReferencia;

		this.cadastroOcorrencia = cadastroOcorrencia;
		this.pocoTipo = pocoTipo;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.despejo = despejo;
		this.faturamentoSituacaoTipo = faturamentoSituacaoTipo;
		this.fonteAbastecimento = fonteAbastecimento;
		this.piscinaVolumeFaixa = piscinaVolumeFaixa;
		this.faturamentoTipo = faturamentoTipo;
		this.ultimaAlteracao = ultimaAlteracao;
		this.logradouroCep = logradouroCep;
	}

	/**
	 * Constructor for the Imovel object
	 * 
	 * @param id
	 *            Description of the Parameter
	 * @param numeroImovel
	 *            Description of the Parameter
	 * @param logradouro
	 *            Description of the Parameter
	 * @param quadra
	 *            Description of the Parameter
	 * @param enderecoReferencia
	 *            Description of the Parameter
	 * @param cep
	 *            Description of the Parameter
	 * @param complementoEndereco
	 *            Description of the Parameter
	 */
	public Imovel(Integer id, String numeroImovel, Quadra quadra,
			EnderecoReferencia enderecoReferencia, String complementoEndereco,
			FaturamentoTipo faturamentoTipo) {
		this.id = id;
		this.numeroImovel = numeroImovel;
		this.quadra = quadra;
		this.enderecoReferencia = enderecoReferencia;
		this.complementoEndereco = complementoEndereco;
		this.faturamentoTipo = faturamentoTipo;
	}

	/**
	 * default constructor
	 */
	public Imovel() {
	}

	/**
	 * minimal constructor
	 * 
	 * @param lote
	 *            Description of the Parameter
	 * @param subLote
	 *            Description of the Parameter
	 * @param testadaLote
	 *            Description of the Parameter
	 * @param numeroImovel
	 *            Description of the Parameter
	 * @param complementoEndereco
	 *            Description of the Parameter
	 * @param numeroPontosUtilizacao
	 *            Description of the Parameter
	 * @param numeroMorador
	 *            Description of the Parameter
	 * @param imovelPrincipal
	 *            Description of the Parameter
	 * @param imovelCondominio
	 *            Description of the Parameter
	 * @param ligacaoEsgotoSituacao
	 *            Description of the Parameter
	 * @param logradouro
	 *            Description of the Parameter
	 * @param hidrometroInstalacaoHistorico
	 *            Description of the Parameter
	 * @param leituraAnormalidade
	 *            Description of the Parameter
	 * @param eloAnormalidade
	 *            Description of the Parameter
	 * @param setorComercial
	 *            Description of the Parameter
	 * @param areaConstruidaFaixa
	 *            Description of the Parameter
	 * @param pavimentoCalcada
	 *            Description of the Parameter
	 * @param imovelPerfil
	 *            Description of the Parameter
	 * @param reservatorioVolumeFaixaSuperior
	 *            Description of the Parameter
	 * @param reservatorioVolumeFaixaInferior
	 *            Description of the Parameter
	 * @param localidade
	 *            Description of the Parameter
	 * @param quadra
	 *            Description of the Parameter
	 * @param cobrancaSituacaoTipo
	 *            Description of the Parameter
	 * @param pavimentoRua
	 *            Description of the Parameter
	 * @param enderecoImovelReferencia
	 *            Description of the Parameter
	 * @param nomeConta
	 *            Descrição do parâmetro
	 * @param cadastroOcorrencia
	 *            Description of the Parameter
	 * @param pocoTipo
	 *            Description of the Parameter
	 * @param ligacaoAguaSituacao
	 *            Description of the Parameter
	 * @param despejo
	 *            Description of the Parameter
	 * @param cep
	 *            Description of the Parameter
	 * @param faturamentoSituacaoTipo
	 *            Description of the Parameter
	 * @param fonteAbastecimento
	 *            Description of the Parameter
	 * @param piscinaVolumeFaixa
	 *            Description of the Parameter
	 * @param cobrancaSituacao
	 *            Description of the Parameter
	 */
	public Imovel(
			short lote,
			short subLote,
			Short testadaLote,
			String numeroImovel,
			String complementoEndereco,
			Short numeroPontosUtilizacao,
			Short numeroMorador,
			gcom.cadastro.imovel.Imovel imovelPrincipal,
			gcom.cadastro.imovel.Imovel imovelCondominio,
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao,
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico,
			LeituraAnormalidade leituraAnormalidade,
			gcom.cadastro.imovel.EloAnormalidade eloAnormalidade,
			SetorComercial setorComercial,
			gcom.cadastro.imovel.AreaConstruidaFaixa areaConstruidaFaixa,
			gcom.cadastro.imovel.PavimentoCalcada pavimentoCalcada,
			gcom.cadastro.imovel.ImovelPerfil imovelPerfil,
			gcom.cadastro.imovel.ReservatorioVolumeFaixa reservatorioVolumeFaixaSuperior,
			gcom.cadastro.imovel.ReservatorioVolumeFaixa reservatorioVolumeFaixaInferior,
			Localidade localidade, Quadra quadra,
			CobrancaSituacaoTipo cobrancaSituacaoTipo,
			gcom.cadastro.imovel.PavimentoRua pavimentoRua,
			EnderecoReferencia enderecoImovelReferencia,
			gcom.cadastro.imovel.CadastroOcorrencia cadastroOcorrencia,
			gcom.cadastro.imovel.PocoTipo pocoTipo,
			LigacaoAguaSituacao ligacaoAguaSituacao,
			gcom.cadastro.imovel.Despejo despejo,
			FaturamentoSituacaoTipo faturamentoSituacaoTipo,
			gcom.cadastro.imovel.FonteAbastecimento fonteAbastecimento,
			gcom.cadastro.imovel.PiscinaVolumeFaixa piscinaVolumeFaixa) {
		this.lote = lote;
		this.subLote = subLote;
		this.testadaLote = testadaLote;
		this.numeroImovel = numeroImovel;
		this.complementoEndereco = complementoEndereco;
		this.numeroPontosUtilizacao = numeroPontosUtilizacao;
		this.numeroMorador = numeroMorador;
		this.imovelPrincipal = imovelPrincipal;
		this.imovelCondominio = imovelCondominio;
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
		this.hidrometroInstalacaoHistorico = hidrometroInstalacaoHistorico;
		this.leituraAnormalidade = leituraAnormalidade;
		this.eloAnormalidade = eloAnormalidade;
		this.setorComercial = setorComercial;
		this.areaConstruidaFaixa = areaConstruidaFaixa;
		this.pavimentoCalcada = pavimentoCalcada;
		this.imovelPerfil = imovelPerfil;
		this.reservatorioVolumeFaixaSuperior = reservatorioVolumeFaixaSuperior;
		this.reservatorioVolumeFaixaInferior = reservatorioVolumeFaixaInferior;
		this.localidade = localidade;
		this.quadra = quadra;
		this.cobrancaSituacaoTipo = cobrancaSituacaoTipo;
		this.pavimentoRua = pavimentoRua;
		this.enderecoReferencia = enderecoImovelReferencia;
		this.cadastroOcorrencia = cadastroOcorrencia;
		this.pocoTipo = pocoTipo;
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
		this.despejo = despejo;
		this.faturamentoSituacaoTipo = faturamentoSituacaoTipo;
		this.fonteAbastecimento = fonteAbastecimento;
		this.piscinaVolumeFaixa = piscinaVolumeFaixa;

	}

	public Imovel(Integer id, gcom.cadastro.localidade.Localidade localidade,
			gcom.cadastro.localidade.SetorComercial setorComercial,
			gcom.cadastro.localidade.Quadra quadra, short lote, short subLote,
			FaturamentoSituacaoTipo faturamentoSituacaoTipo) {

		this.id = id;
		this.localidade = localidade;
		this.setorComercial = setorComercial;
		this.quadra = quadra;
		this.lote = lote;
		this.subLote = subLote;
		this.faturamentoSituacaoTipo = faturamentoSituacaoTipo;
	}

	/**
	 * Gets the id attribute of the Imovel object
	 * 
	 * @return The id value
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Sets the id attribute of the Imovel object
	 * 
	 * @param id
	 *            The new id value
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the lote attribute of the Imovel object
	 * 
	 * @return The lote value
	 */
	public short getLote() {
		return this.lote;
	}

	/**
	 * Sets the lote attribute of the Imovel object
	 * 
	 * @param lote
	 *            The new lote value
	 */
	public void setLote(short lote) {
		this.lote = lote;
	}

	/**
	 * Gets the subLote attribute of the Imovel object
	 * 
	 * @return The subLote value
	 */
	public short getSubLote() {
		return this.subLote;
	}

	/**
	 * Sets the subLote attribute of the Imovel object
	 * 
	 * @param subLote
	 *            The new subLote value
	 */
	public void setSubLote(short subLote) {
		this.subLote = subLote;
	}

	/**
	 * Gets the testadaLote attribute of the Imovel object
	 * 
	 * @return The testadaLote value
	 */
	public Short getTestadaLote() {
		return this.testadaLote;
	}

	/**
	 * Sets the testadaLote attribute of the Imovel object
	 * 
	 * @param testadaLote
	 *            The new testadaLote value
	 */
	public void setTestadaLote(Short testadaLote) {
		this.testadaLote = testadaLote;
	}

	/**
	 * Gets the numeroImovel attribute of the Imovel object
	 * 
	 * @return The numeroImovel value
	 */
	public String getNumeroImovel() {
		return this.numeroImovel;
	}

	/**
	 * Sets the numeroImovel attribute of the Imovel object
	 * 
	 * @param numeroImovel
	 *            The new numeroImovel value
	 */
	public void setNumeroImovel(String numeroImovel) {
		this.numeroImovel = numeroImovel;
	}

	/**
	 * Gets the complementoEndereco attribute of the Imovel object
	 * 
	 * @return The complementoEndereco value
	 */
	public String getComplementoEndereco() {
		return this.complementoEndereco;
	}

	/**
	 * Gets the ultimaAlteracao attribute of the Imovel object
	 * 
	 * @return The ultimaAlteracao value
	 */

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	/**
	 * Sets the ultimaAlteracao attribute of the Imovel object
	 * 
	 * @param ultimaAlteracao
	 *            The new complementoEndereco value
	 */

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * Sets the complementoEndereco attribute of the Imovel object
	 * 
	 * @param complementoEndereco
	 *            The new complementoEndereco value
	 */
	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}

	/**
	 * Gets the areaConstruida attribute of the Imovel object
	 * 
	 * @return The areaConstruida value
	 */
	public BigDecimal getAreaConstruida() {
		return this.areaConstruida;
	}

	/**
	 * Sets the areaConstruida attribute of the Imovel object
	 * 
	 * @param areaConstruida
	 *            The new areaConstruida value
	 */
	public void setAreaConstruida(BigDecimal areaConstruida) {
		this.areaConstruida = areaConstruida;
	}

	/**
	 * Gets the indicadorImovelCondominio attribute of the Imovel object
	 * 
	 * @return The indicadorImovelCondominio value
	 */
	public Short getIndicadorImovelCondominio() {
		return this.indicadorImovelCondominio;
	}

	/**
	 * Sets the indicadorImovelCondominio attribute of the Imovel object
	 * 
	 * @param indicadorImovelCondominio
	 *            The new indicadorImovelCondominio value
	 */
	public void setIndicadorImovelCondominio(Short indicadorImovelCondominio) {
		this.indicadorImovelCondominio = indicadorImovelCondominio;
	}

	/**
	 * Gets the volumeReservatorioSuperior attribute of the Imovel object
	 * 
	 * @return The volumeReservatorioSuperior value
	 */
	public BigDecimal getVolumeReservatorioSuperior() {
		return this.volumeReservatorioSuperior;
	}

	/**
	 * Sets the volumeReservatorioSuperior attribute of the Imovel object
	 * 
	 * @param volumeReservatorioSuperior
	 *            The new volumeReservatorioSuperior value
	 */
	public void setVolumeReservatorioSuperior(
			BigDecimal volumeReservatorioSuperior) {
		this.volumeReservatorioSuperior = volumeReservatorioSuperior;
	}

	/**
	 * Gets the volumeReservatorioInferior attribute of the Imovel object
	 * 
	 * @return The volumeReservatorioInferior value
	 */
	public BigDecimal getVolumeReservatorioInferior() {
		return this.volumeReservatorioInferior;
	}

	/**
	 * Sets the volumeReservatorioInferior attribute of the Imovel object
	 * 
	 * @param volumeReservatorioInferior
	 *            The new volumeReservatorioInferior value
	 */
	public void setVolumeReservatorioInferior(
			BigDecimal volumeReservatorioInferior) {
		this.volumeReservatorioInferior = volumeReservatorioInferior;
	}

	/**
	 * Gets the volumePiscina attribute of the Imovel object
	 * 
	 * @return The volumePiscina value
	 */
	public BigDecimal getVolumePiscina() {
		return this.volumePiscina;
	}

	/**
	 * Sets the volumePiscina attribute of the Imovel object
	 * 
	 * @param volumePiscina
	 *            The new volumePiscina value
	 */
	public void setVolumePiscina(BigDecimal volumePiscina) {
		this.volumePiscina = volumePiscina;
	}

	/**
	 * Gets the dataSupressaoParcial attribute of the Imovel object
	 * 
	 * @return The dataSupressaoParcial value
	 */
	public Date getDataSupressaoParcial() {
		return this.dataSupressaoParcial;
	}

	/**
	 * Sets the dataSupressaoParcial attribute of the Imovel object
	 * 
	 * @param dataSupressaoParcial
	 *            The new dataSupressaoParcial value
	 */
	public void setDataSupressaoParcial(Date dataSupressaoParcial) {
		this.dataSupressaoParcial = dataSupressaoParcial;
	}

	/**
	 * Gets the dataInfracao attribute of the Imovel object
	 * 
	 * @return The dataInfracao value
	 */
	public Date getDataInfracao() {
		return this.dataInfracao;
	}

	/**
	 * Sets the dataInfracao attribute of the Imovel object
	 * 
	 * @param dataInfracao
	 *            The new dataInfracao value
	 */
	public void setDataInfracao(Date dataInfracao) {
		this.dataInfracao = dataInfracao;
	}

	/**
	 * Gets the numeroPontosUtilizacao attribute of the Imovel object
	 * 
	 * @return The numeroPontosUtilizacao value
	 */
	public Short getNumeroPontosUtilizacao() {
		return this.numeroPontosUtilizacao;
	}

	/**
	 * Sets the numeroPontosUtilizacao attribute of the Imovel object
	 * 
	 * @param numeroPontosUtilizacao
	 *            The new numeroPontosUtilizacao value
	 */
	public void setNumeroPontosUtilizacao(Short numeroPontosUtilizacao) {
		this.numeroPontosUtilizacao = numeroPontosUtilizacao;
	}

	/**
	 * Gets the numeroMorador attribute of the Imovel object
	 * 
	 * @return The numeroMorador value
	 */
	public Short getNumeroMorador() {
		return this.numeroMorador;
	}

	/**
	 * Sets the numeroMorador attribute of the Imovel object
	 * 
	 * @param numeroMorador
	 *            The new numeroMorador value
	 */
	public void setNumeroMorador(Short numeroMorador) {
		this.numeroMorador = numeroMorador;
	}

	/**
	 * Gets the numeroRetificacao attribute of the Imovel object
	 * 
	 * @return The numeroRetificacao value
	 */
	public Short getNumeroRetificacao() {
		return this.numeroRetificacao;
	}

	/**
	 * Sets the numeroRetificacao attribute of the Imovel object
	 * 
	 * @param numeroRetificacao
	 *            The new numeroRetificacao value
	 */
	public void setNumeroRetificacao(Short numeroRetificacao) {
		this.numeroRetificacao = numeroRetificacao;
	}

	/**
	 * Gets the numeroLeituraExcecao attribute of the Imovel object
	 * 
	 * @return The numeroLeituraExcecao value
	 */
	public Short getNumeroLeituraExcecao() {
		return this.numeroLeituraExcecao;
	}

	/**
	 * Sets the numeroLeituraExcecao attribute of the Imovel object
	 * 
	 * @param numeroLeituraExcecao
	 *            The new numeroLeituraExcecao value
	 */
	public void setNumeroLeituraExcecao(Short numeroLeituraExcecao) {
		this.numeroLeituraExcecao = numeroLeituraExcecao;
	}

	/**
	 * Gets the numeroParcelamento attribute of the Imovel object
	 * 
	 * @return The numeroParcelamento value
	 */
	public Short getNumeroParcelamento() {
		return this.numeroParcelamento;
	}

	/**
	 * Sets the numeroParcelamento attribute of the Imovel object
	 * 
	 * @param numeroParcelamento
	 *            The new numeroParcelamento value
	 */
	public void setNumeroParcelamento(Short numeroParcelamento) {
		this.numeroParcelamento = numeroParcelamento;
	}

	/**
	 * Gets the numeroReparcelamento attribute of the Imovel object
	 * 
	 * @return The numeroReparcelamento value
	 */
	public Short getNumeroReparcelamento() {
		return this.numeroReparcelamento;
	}

	/**
	 * Sets the numeroReparcelamento attribute of the Imovel object
	 * 
	 * @param numeroReparcelamento
	 *            The new numeroReparcelamento value
	 */
	public void setNumeroReparcelamento(Short numeroReparcelamento) {
		this.numeroReparcelamento = numeroReparcelamento;
	}

	/**
	 * Gets the diaVencimento attribute of the Imovel object
	 * 
	 * @return The diaVencimento value
	 */
	public Short getDiaVencimento() {
		return this.diaVencimento;
	}

	/**
	 * Sets the diaVencimento attribute of the Imovel object
	 * 
	 * @param diaVencimento
	 *            The new diaVencimento value
	 */
	public void setDiaVencimento(Short diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	/**
	 * Gets the numeroIptu attribute of the Imovel object
	 * 
	 * @return The numeroIptu value
	 */
	public BigDecimal getNumeroIptu() {
		return this.numeroIptu;
	}

	/**
	 * Sets the numeroIptu attribute of the Imovel object
	 * 
	 * @param numeroIptu
	 *            The new numeroIptu value
	 */
	public void setNumeroIptu(BigDecimal numeroIptu) {
		this.numeroIptu = numeroIptu;
	}

	/**
	 * Gets the numeroCelpe attribute of the Imovel object
	 * 
	 * @return The numeroCelpe value
	 */
	public Long getNumeroCelpe() {
		return this.numeroCelpe;
	}

	/**
	 * Sets the numeroCelpe attribute of the Imovel object
	 * 
	 * @param numeroCelpe
	 *            The new numeroCelpe value
	 */
	public void setNumeroCelpe(Long numeroCelpe) {
		this.numeroCelpe = numeroCelpe;
	}

	/**
	 * Gets the indicadorConta attribute of the Imovel object
	 * 
	 * @return The indicadorConta value
	 */
	public Short getIndicadorConta() {
		return this.indicadorConta;
	}

	/**
	 * Sets the indicadorConta attribute of the Imovel object
	 * 
	 * @param indicadorConta
	 *            The new indicadorConta value
	 */
	public void setIndicadorConta(Short indicadorConta) {
		this.indicadorConta = indicadorConta;
	}

	/**
	 * Gets the indicadorEmissaoExtratoFaturamento attribute of the Imovel
	 * object
	 * 
	 * @return The indicadorEmissaoExtratoFaturamento value
	 */
	public Short getIndicadorEmissaoExtratoFaturamento() {
		return this.indicadorEmissaoExtratoFaturamento;
	}

	/**
	 * Sets the indicadorEmissaoExtratoFaturamento attribute of the Imovel
	 * object
	 * 
	 * @param indicadorEmissaoExtratoFaturamento
	 *            The new indicadorEmissaoExtratoFaturamento value
	 */
	public void setIndicadorEmissaoExtratoFaturamento(
			Short indicadorEmissaoExtratoFaturamento) {
		this.indicadorEmissaoExtratoFaturamento = indicadorEmissaoExtratoFaturamento;
	}

	/**
	 * Gets the indicadorDebitoConta attribute of the Imovel object
	 * 
	 * @return The indicadorDebitoConta value
	 */
	public Short getIndicadorDebitoConta() {
		return this.indicadorDebitoConta;
	}

	/**
	 * Sets the indicadorDebitoConta attribute of the Imovel object
	 * 
	 * @param indicadorDebitoConta
	 *            The new indicadorDebitoConta value
	 */
	public void setIndicadorDebitoConta(Short indicadorDebitoConta) {
		this.indicadorDebitoConta = indicadorDebitoConta;
	}

	/**
	 * Gets the indicadorExclusao attribute of the Imovel object
	 * 
	 * @return The indicadorExclusao value
	 */
	public Short getIndicadorExclusao() {
		return this.indicadorExclusao;
	}

	/**
	 * Sets the indicadorExclusao attribute of the Imovel object
	 * 
	 * @param indicadorExclusao
	 *            The new indicadorExclusao value
	 */
	public void setIndicadorExclusao(Short indicadorExclusao) {
		this.indicadorExclusao = indicadorExclusao;
	}

	/**
	 * Gets the coordenadaX attribute of the Imovel object
	 * 
	 * @return The coordenadaX value
	 */
	public String getCoordenadaX() {
		return this.coordenadaX;
	}

	/**
	 * Sets the coordenadaX attribute of the Imovel object
	 * 
	 * @param coordenadaX
	 *            The new coordenadaX value
	 */
	public void setCoordenadaX(String coordenadaX) {
		this.coordenadaX = coordenadaX;
	}

	/**
	 * Gets the coordenadaY attribute of the Imovel object
	 * 
	 * @return The coordenadaY value
	 */
	public String getCoordenadaY() {
		return this.coordenadaY;
	}

	/**
	 * Sets the coordenadaY attribute of the Imovel object
	 * 
	 * @param coordenadaY
	 *            The new coordenadaY value
	 */
	public void setCoordenadaY(String coordenadaY) {
		this.coordenadaY = coordenadaY;
	}

	/**
	 * Gets the ligacaoEsgoto attribute of the Imovel object
	 * 
	 * @return The ligacaoEsgoto value
	 */
	public LigacaoEsgoto getLigacaoEsgoto() {
		return this.ligacaoEsgoto;
	}

	/**
	 * Sets the ligacaoEsgoto attribute of the Imovel object
	 * 
	 * @param ligacaoEsgoto
	 *            The new ligacaoEsgoto value
	 */
	public void setLigacaoEsgoto(LigacaoEsgoto ligacaoEsgoto) {
		this.ligacaoEsgoto = ligacaoEsgoto;
	}

	/**
	 * Gets the ligacaoAgua attribute of the Imovel object
	 * 
	 * @return The ligacaoAgua value
	 */
	public LigacaoAgua getLigacaoAgua() {
		return this.ligacaoAgua;
	}

	/**
	 * Sets the ligacaoAgua attribute of the Imovel object
	 * 
	 * @param ligacaoAgua
	 *            The new ligacaoAgua value
	 */
	public void setLigacaoAgua(LigacaoAgua ligacaoAgua) {
		this.ligacaoAgua = ligacaoAgua;
	}

	/**
	 * Gets the imovelEnderecoAnterior attribute of the Imovel object
	 * 
	 * @return The imovelEnderecoAnterior value
	 */
	public ImovelEnderecoAnterior getImovelEnderecoAnterior() {
		return this.imovelEnderecoAnterior;
	}

	/**
	 * Sets the imovelEnderecoAnterior attribute of the Imovel object
	 * 
	 * @param imovelEnderecoAnterior
	 *            The new imovelEnderecoAnterior value
	 */
	public void setImovelEnderecoAnterior(
			ImovelEnderecoAnterior imovelEnderecoAnterior) {
		this.imovelEnderecoAnterior = imovelEnderecoAnterior;
	}

	/**
	 * Gets the imovelPrincipal attribute of the Imovel object
	 * 
	 * @return The imovelPrincipal value
	 */
	public gcom.cadastro.imovel.Imovel getImovelPrincipal() {
		return this.imovelPrincipal;
	}

	/**
	 * Sets the imovelPrincipal attribute of the Imovel object
	 * 
	 * @param imovelPrincipal
	 *            The new imovelPrincipal value
	 */
	public void setImovelPrincipal(gcom.cadastro.imovel.Imovel imovelPrincipal) {
		this.imovelPrincipal = imovelPrincipal;
	}

	/**
	 * Gets the imovelCondominio attribute of the Imovel object
	 * 
	 * @return The imovelCondominio value
	 */
	public gcom.cadastro.imovel.Imovel getImovelCondominio() {
		return this.imovelCondominio;
	}

	/**
	 * Sets the imovelCondominio attribute of the Imovel object
	 * 
	 * @param imovelCondominio
	 *            The new imovelCondominio value
	 */
	public void setImovelCondominio(gcom.cadastro.imovel.Imovel imovelCondominio) {
		this.imovelCondominio = imovelCondominio;
	}

	/**
	 * Gets the ligacaoEsgotoSituacao attribute of the Imovel object
	 * 
	 * @return The ligacaoEsgotoSituacao value
	 */
	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return this.ligacaoEsgotoSituacao;
	}

	/**
	 * Sets the ligacaoEsgotoSituacao attribute of the Imovel object
	 * 
	 * @param ligacaoEsgotoSituacao
	 *            The new ligacaoEsgotoSituacao value
	 */
	public void setLigacaoEsgotoSituacao(
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	/**
	 * Gets the hidrometroInstalacaoHistorico attribute of the Imovel object
	 * 
	 * @return The hidrometroInstalacaoHistorico value
	 */
	public HidrometroInstalacaoHistorico getHidrometroInstalacaoHistorico() {
		return this.hidrometroInstalacaoHistorico;
	}

	/**
	 * Sets the hidrometroInstalacaoHistorico attribute of the Imovel object
	 * 
	 * @param hidrometroInstalacaoHistorico
	 *            The new hidrometroInstalacaoHistorico value
	 */
	public void setHidrometroInstalacaoHistorico(
			HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico) {
		this.hidrometroInstalacaoHistorico = hidrometroInstalacaoHistorico;
	}

	/**
	 * Gets the leituraAnormalidade attribute of the Imovel object
	 * 
	 * @return The leituraAnormalidade value
	 */
	public LeituraAnormalidade getLeituraAnormalidade() {
		return this.leituraAnormalidade;
	}

	/**
	 * Sets the leituraAnormalidade attribute of the Imovel object
	 * 
	 * @param leituraAnormalidade
	 *            The new leituraAnormalidade value
	 */
	public void setLeituraAnormalidade(LeituraAnormalidade leituraAnormalidade) {
		this.leituraAnormalidade = leituraAnormalidade;
	}

	/**
	 * Gets the eloAnormalidade attribute of the Imovel object
	 * 
	 * @return The eloAnormalidade value
	 */
	public gcom.cadastro.imovel.EloAnormalidade getEloAnormalidade() {
		return this.eloAnormalidade;
	}

	/**
	 * Sets the eloAnormalidade attribute of the Imovel object
	 * 
	 * @param eloAnormalidade
	 *            The new eloAnormalidade value
	 */
	public void setEloAnormalidade(
			gcom.cadastro.imovel.EloAnormalidade eloAnormalidade) {
		this.eloAnormalidade = eloAnormalidade;
	}

	/**
	 * Gets the setorComercial attribute of the Imovel object
	 * 
	 * @return The setorComercial value
	 */
	public SetorComercial getSetorComercial() {
		return this.setorComercial;
	}

	/**
	 * Sets the setorComercial attribute of the Imovel object
	 * 
	 * @param setorComercial
	 *            The new setorComercial value
	 */
	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}

	/**
	 * Gets the areaConstruidaFaixa attribute of the Imovel object
	 * 
	 * @return The areaConstruidaFaixa value
	 */
	public gcom.cadastro.imovel.AreaConstruidaFaixa getAreaConstruidaFaixa() {
		return this.areaConstruidaFaixa;
	}

	/**
	 * Sets the areaConstruidaFaixa attribute of the Imovel object
	 * 
	 * @param areaConstruidaFaixa
	 *            The new areaConstruidaFaixa value
	 */
	public void setAreaConstruidaFaixa(
			gcom.cadastro.imovel.AreaConstruidaFaixa areaConstruidaFaixa) {
		this.areaConstruidaFaixa = areaConstruidaFaixa;
	}

	/**
	 * Gets the pavimentoCalcada attribute of the Imovel object
	 * 
	 * @return The pavimentoCalcada value
	 */
	public gcom.cadastro.imovel.PavimentoCalcada getPavimentoCalcada() {
		return this.pavimentoCalcada;
	}

	/**
	 * Sets the pavimentoCalcada attribute of the Imovel object
	 * 
	 * @param pavimentoCalcada
	 *            The new pavimentoCalcada value
	 */
	public void setPavimentoCalcada(
			gcom.cadastro.imovel.PavimentoCalcada pavimentoCalcada) {
		this.pavimentoCalcada = pavimentoCalcada;
	}

	/**
	 * Gets the imovelPerfil attribute of the Imovel object
	 * 
	 * @return The imovelPerfil value
	 */
	public gcom.cadastro.imovel.ImovelPerfil getImovelPerfil() {
		return this.imovelPerfil;
	}

	/**
	 * Sets the imovelPerfil attribute of the Imovel object
	 * 
	 * @param imovelPerfil
	 *            The new imovelPerfil value
	 */
	public void setImovelPerfil(gcom.cadastro.imovel.ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
	}

	/**
	 * Gets the reservatorioVolumeFaixaSuperior attribute of the Imovel object
	 * 
	 * @return The reservatorioVolumeFaixaSuperior value
	 */
	public gcom.cadastro.imovel.ReservatorioVolumeFaixa getReservatorioVolumeFaixaSuperior() {
		return this.reservatorioVolumeFaixaSuperior;
	}

	/**
	 * Sets the reservatorioVolumeFaixaSuperior attribute of the Imovel object
	 * 
	 * @param reservatorioVolumeFaixaSuperior
	 *            The new reservatorioVolumeFaixaSuperior value
	 */
	public void setReservatorioVolumeFaixaSuperior(
			gcom.cadastro.imovel.ReservatorioVolumeFaixa reservatorioVolumeFaixaSuperior) {
		this.reservatorioVolumeFaixaSuperior = reservatorioVolumeFaixaSuperior;
	}

	/**
	 * Gets the reservatorioVolumeFaixaInferior attribute of the Imovel object
	 * 
	 * @return The reservatorioVolumeFaixaInferior value
	 */
	public gcom.cadastro.imovel.ReservatorioVolumeFaixa getReservatorioVolumeFaixaInferior() {
		return this.reservatorioVolumeFaixaInferior;
	}

	/**
	 * Sets the reservatorioVolumeFaixaInferior attribute of the Imovel object
	 * 
	 * @param reservatorioVolumeFaixaInferior
	 *            The new reservatorioVolumeFaixaInferior value
	 */
	public void setReservatorioVolumeFaixaInferior(
			gcom.cadastro.imovel.ReservatorioVolumeFaixa reservatorioVolumeFaixaInferior) {
		this.reservatorioVolumeFaixaInferior = reservatorioVolumeFaixaInferior;
	}

	/**
	 * Gets the localidade attribute of the Imovel object
	 * 
	 * @return The localidade value
	 */
	public Localidade getLocalidade() {
		return this.localidade;
	}

	/**
	 * Sets the localidade attribute of the Imovel object
	 * 
	 * @param localidade
	 *            The new localidade value
	 */
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	/**
	 * Gets the quadra attribute of the Imovel object
	 * 
	 * @return The quadra value
	 */
	public Quadra getQuadra() {
		return this.quadra;
	}

	/**
	 * Sets the quadra attribute of the Imovel object
	 * 
	 * @param quadra
	 *            The new quadra value
	 */
	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}

	/**
	 * Gets the cobrancaSituacaoTipo attribute of the Imovel object
	 * 
	 * @return The cobrancaSituacaoTipo value
	 */
	public CobrancaSituacaoTipo getCobrancaSituacaoTipo() {
		return this.cobrancaSituacaoTipo;
	}

	/**
	 * Sets the cobrancaSituacaoTipo attribute of the Imovel object
	 * 
	 * @param cobrancaSituacaoTipo
	 *            The new cobrancaSituacaoTipo value
	 */
	public void setCobrancaSituacaoTipo(
			CobrancaSituacaoTipo cobrancaSituacaoTipo) {
		this.cobrancaSituacaoTipo = cobrancaSituacaoTipo;
	}

	/**
	 * Gets the pavimentoRua attribute of the Imovel object
	 * 
	 * @return The pavimentoRua value
	 */
	public gcom.cadastro.imovel.PavimentoRua getPavimentoRua() {
		return this.pavimentoRua;
	}

	/**
	 * Sets the pavimentoRua attribute of the Imovel object
	 * 
	 * @param pavimentoRua
	 *            The new pavimentoRua value
	 */
	public void setPavimentoRua(gcom.cadastro.imovel.PavimentoRua pavimentoRua) {
		this.pavimentoRua = pavimentoRua;
	}

	/**
	 * Gets the enderecoImovelReferencia attribute of the Imovel object
	 * 
	 * @return The enderecoImovelReferencia value
	 */
	public EnderecoReferencia getEnderecoReferencia() {
		return this.enderecoReferencia;
	}

	/**
	 * Sets the enderecoImovelReferencia attribute of the Imovel object
	 * 
	 * @param enderecoReferencia
	 *            O novo valor de enderecoImovelReferencia
	 */
	public void setEnderecoReferencia(EnderecoReferencia enderecoReferencia) {
		this.enderecoReferencia = enderecoReferencia;
	}

	/**
	 * Gets the cadastroOcorrencia attribute of the Imovel object
	 * 
	 * @return The cadastroOcorrencia value
	 */
	public gcom.cadastro.imovel.CadastroOcorrencia getCadastroOcorrencia() {
		return this.cadastroOcorrencia;
	}

	/**
	 * Sets the cadastroOcorrencia attribute of the Imovel object
	 * 
	 * @param cadastroOcorrencia
	 *            The new cadastroOcorrencia value
	 */
	public void setCadastroOcorrencia(
			gcom.cadastro.imovel.CadastroOcorrencia cadastroOcorrencia) {
		this.cadastroOcorrencia = cadastroOcorrencia;
	}

	/**
	 * Gets the pocoTipo attribute of the Imovel object
	 * 
	 * @return The pocoTipo value
	 */
	public gcom.cadastro.imovel.PocoTipo getPocoTipo() {
		return this.pocoTipo;
	}

	/**
	 * Sets the pocoTipo attribute of the Imovel object
	 * 
	 * @param pocoTipo
	 *            The new pocoTipo value
	 */
	public void setPocoTipo(gcom.cadastro.imovel.PocoTipo pocoTipo) {
		this.pocoTipo = pocoTipo;
	}

	/**
	 * Gets the ligacaoAguaSituacao attribute of the Imovel object
	 * 
	 * @return The ligacaoAguaSituacao value
	 */
	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return this.ligacaoAguaSituacao;
	}

	/**
	 * Sets the ligacaoAguaSituacao attribute of the Imovel object
	 * 
	 * @param ligacaoAguaSituacao
	 *            The new ligacaoAguaSituacao value
	 */
	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	/**
	 * Gets the despejo attribute of the Imovel object
	 * 
	 * @return The despejo value
	 */
	public gcom.cadastro.imovel.Despejo getDespejo() {
		return this.despejo;
	}

	/**
	 * Sets the despejo attribute of the Imovel object
	 * 
	 * @param despejo
	 *            The new despejo value
	 */
	public void setDespejo(gcom.cadastro.imovel.Despejo despejo) {
		this.despejo = despejo;
	}

	/**
	 * Gets the faturamentoSituacaoTipo attribute of the Imovel object
	 * 
	 * @return The faturamentoSituacaoTipo value
	 */
	public FaturamentoSituacaoTipo getFaturamentoSituacaoTipo() {
		return this.faturamentoSituacaoTipo;
	}

	/**
	 * Sets the faturamentoSituacaoTipo attribute of the Imovel object
	 * 
	 * @param faturamentoSituacaoTipo
	 *            The new faturamentoSituacaoTipo value
	 */
	public void setFaturamentoSituacaoTipo(
			FaturamentoSituacaoTipo faturamentoSituacaoTipo) {
		this.faturamentoSituacaoTipo = faturamentoSituacaoTipo;
	}

	/**
	 * Gets the fonteAbastecimento attribute of the Imovel object
	 * 
	 * @return The fonteAbastecimento value
	 */
	public gcom.cadastro.imovel.FonteAbastecimento getFonteAbastecimento() {
		return this.fonteAbastecimento;
	}

	/**
	 * Sets the fonteAbastecimento attribute of the Imovel object
	 * 
	 * @param fonteAbastecimento
	 *            The new fonteAbastecimento value
	 */
	public void setFonteAbastecimento(
			gcom.cadastro.imovel.FonteAbastecimento fonteAbastecimento) {
		this.fonteAbastecimento = fonteAbastecimento;
	}

	/**
	 * Gets the piscinaVolumeFaixa attribute of the Imovel object
	 * 
	 * @return The piscinaVolumeFaixa value
	 */
	public gcom.cadastro.imovel.PiscinaVolumeFaixa getPiscinaVolumeFaixa() {
		return this.piscinaVolumeFaixa;
	}

	/**
	 * Sets the piscinaVolumeFaixa attribute of the Imovel object
	 * 
	 * @param piscinaVolumeFaixa
	 *            The new piscinaVolumeFaixa value
	 */
	public void setPiscinaVolumeFaixa(
			gcom.cadastro.imovel.PiscinaVolumeFaixa piscinaVolumeFaixa) {
		this.piscinaVolumeFaixa = piscinaVolumeFaixa;
	}

	public FaturamentoSituacaoMotivo getFaturamentoSituacaoMotivo() {
		return this.faturamentoSituacaoMotivo;
	}

	public void setFaturamentoSituacaoMotivo(
			FaturamentoSituacaoMotivo faturamentoSituacaoMotivo) {
		this.faturamentoSituacaoMotivo = faturamentoSituacaoMotivo;
	}

	public Short getIndicadorSuspensaoAbastecimento() {
		return this.indicadorSuspensaoAbastecimento;
	}

	public void setIndicadorSuspensaoAbastecimento(
			Short indicadorSuspensaoAbastecimento) {
		this.indicadorSuspensaoAbastecimento = indicadorSuspensaoAbastecimento;
	}

	/**
	 * Gets the endereco attribute of the Imovel object
	 * 
	 * @return The endereco value
	 */
	public String getEnderecoFormatado() {
		String endereco = null;

		// verifica se o logradouro do imovel é diferente de null
		if (this.getLogradouroCep() != null
				&& this.getLogradouroCep().getLogradouro() != null
				&& !this.getLogradouroCep().getLogradouro().getId().equals(
						new Integer("0"))) {

			// verifica se o logradouro tipo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTipo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTipo().equals("")) {
				// concatena o logradouro tipo do imovel
				endereco = this.getLogradouroCep().getLogradouro()
						.getLogradouroTipo().getDescricao().trim();
			}
			// verifica se o logradouro titulo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTitulo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTitulo().equals("")) {
				// concatena o logradouro titulo do imovel
				endereco = endereco
						+ " "
						+ this.getLogradouroCep().getLogradouro()
								.getLogradouroTitulo().getDescricao().trim();
			}

			// concatena o logradouro do imovel
			endereco = endereco + " "
					+ this.getLogradouroCep().getLogradouro().getNome().trim();

			if (this.getEnderecoReferencia() != null
					&& !this.getEnderecoReferencia().equals("")) {
				if (this.getEnderecoReferencia().getDescricao() != null
						&& !this.getEnderecoReferencia().getDescricao().equals(
								"")) {
					endereco = endereco
							+ " - "
							+ this.getEnderecoReferencia().getDescricao()
									.trim();
				}
			}
			if (this.getNumeroImovel() != null
					&& !this.getNumeroImovel().equals("")) {
				// concate o numero do imovel
				endereco = endereco + " - " + this.getNumeroImovel().trim();
			}

			if (this.getComplementoEndereco() != null
					&& !this.getComplementoEndereco().equalsIgnoreCase("")) {
				endereco = endereco + " - "
						+ this.getComplementoEndereco().trim();
			}

			if (this.getLogradouroBairro() != null
					&& this.getLogradouroBairro().getBairro() != null
					&& this.getLogradouroBairro().getBairro().getId()
							.intValue() != 0) {
				endereco = endereco
						+ " - "
						+ this.getLogradouroBairro().getBairro().getNome()
								.trim();

				if (this.getLogradouroBairro().getBairro().getMunicipio() != null
						&& this.getLogradouroBairro().getBairro()
								.getMunicipio().getId().intValue() != 0) {
					endereco = endereco
							+ " "
							+ this.getLogradouroBairro().getBairro()
									.getMunicipio().getNome().trim();
				}

				if (this.getLogradouroBairro().getBairro().getMunicipio()
						.getUnidadeFederacao() != null
						&& this.getLogradouroBairro().getBairro()
								.getMunicipio().getUnidadeFederacao().getId()
								.intValue() != 0) {
					endereco = endereco
							+ " "
							+ this.getLogradouroBairro().getBairro()
									.getMunicipio().getUnidadeFederacao()
									.getSigla().trim();
				}
			}

			if (this.getLogradouroCep() != null
					&& this.getLogradouroCep().getCep() != null) {
				// concatena o cep formatado do imóvel
				endereco = endereco
						+ " "
						+ this.getLogradouroCep().getCep().getCepFormatado()
								.trim();
			}
			
			if (this.getPerimetroInicial() != null) {
				endereco = endereco + " ENTRE " + this.getPerimetroInicial().getDescricaoFormatada() + " E " 
						+ this.getPerimetroFinal().getDescricaoFormatada();
			}

		}

		return endereco;
	}

	/**
	 * Gets the endereco attribute of the Imovel object
	 * 
	 * @return The endereco value
	 */
	public String getEnderecoFormatadoAbreviado() {
		String endereco = null;

		// verifica se o logradouro do imovel é diferente de null
		if (this.getLogradouroCep() != null
				&& this.getLogradouroCep().getLogradouro() != null
				&& !this.getLogradouroCep().getLogradouro().getId().equals(
						new Integer("0"))) {

			// verifica se o logradouro tipo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTipo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTipo().equals("")) {
				if (this.getLogradouroCep().getLogradouro().getLogradouroTipo()
						.getDescricaoAbreviada() != null
						&& !this.getLogradouroCep().getLogradouro()
								.getLogradouroTipo().getDescricaoAbreviada()
								.equals("")) {
					// concatena o logradouro tipo do imovel
					endereco = this.getLogradouroCep().getLogradouro()
							.getLogradouroTipo().getDescricaoAbreviada().trim();
				}
			}
			// verifica se o logradouro titulo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTitulo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTitulo().equals("")) {
				if (this.getLogradouroCep().getLogradouro()
						.getLogradouroTitulo().getDescricaoAbreviada() != null
						&& !this.getLogradouroCep().getLogradouro()
								.getLogradouroTitulo().getDescricaoAbreviada()
								.equals("")) {
					// concatena o logradouro titulo do imovel
					endereco = endereco
							+ " "
							+ this.getLogradouroCep().getLogradouro()
									.getLogradouroTitulo()
									.getDescricaoAbreviada().trim();
				}
			}

			// concatena o logradouro do imovel
			endereco = endereco + " "
					+ this.getLogradouroCep().getLogradouro().getNome().trim();

			if (this.getEnderecoReferencia() != null
					&& !this.getEnderecoReferencia().equals("")) {
				if (this.getEnderecoReferencia().getDescricaoAbreviada() != null
						&& !this.getEnderecoReferencia()
								.getDescricaoAbreviada().equals("")) {
					endereco = endereco
							+ ", "
							+ this.getEnderecoReferencia()
									.getDescricaoAbreviada().trim();
				}
			}

			// concate o numero do imovel
			endereco = endereco + " " + this.getNumeroImovel().trim();

			if (this.getComplementoEndereco() != null
					&& !this.getComplementoEndereco().equalsIgnoreCase("")) {
				endereco = endereco + " - "
						+ this.getComplementoEndereco().trim();
			}

			if (this.getLogradouroBairro() != null
					&& this.getLogradouroBairro().getBairro() != null
					&& this.getLogradouroBairro().getBairro().getId()
							.intValue() != 0) {
				endereco = endereco
						+ " - "
						+ this.getLogradouroBairro().getBairro().getNome()
								.trim();

				if (this.getLogradouroBairro().getBairro().getMunicipio() != null
						&& this.getLogradouroBairro().getBairro()
								.getMunicipio().getId().intValue() != 0) {
					endereco = endereco
							+ " "
							+ this.getLogradouroBairro().getBairro()
									.getMunicipio().getNome().trim();
				}

				if (this.getLogradouroBairro().getBairro().getMunicipio()
						.getUnidadeFederacao() != null
						&& this.getLogradouroBairro().getBairro()
								.getMunicipio().getUnidadeFederacao().getId()
								.intValue() != 0) {
					endereco = endereco
							+ " "
							+ this.getLogradouroBairro().getBairro()
									.getMunicipio().getUnidadeFederacao()
									.getSigla().trim();
				}
			}

			if (this.getLogradouroCep() != null
					&& this.getLogradouroCep().getCep() != null) {
				// concatena o cep formatado do imóvel
				endereco = endereco
						+ " "
						+ this.getLogradouroCep().getCep().getCepFormatado()
								.trim();
			}
			
			if (this.getPerimetroInicial() != null) {
				endereco = endereco + " ENTRE " + this.getPerimetroInicial().getDescricaoFormatada() + " E " 
						+ this.getPerimetroFinal().getDescricaoFormatada();
			}

		}

		return endereco;
	}
	
	public String getEnderecoTipoTituloLogradouro() {
		String endereco = null;

		// verifica se o logradouro do imovel é diferente de null
		if (this.getLogradouroCep() != null
				&& this.getLogradouroCep().getLogradouro() != null
				&& !this.getLogradouroCep().getLogradouro().getId().equals(
						new Integer("0"))) {
			
			// verifica se o logradouro tipo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTipo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTipo().equals("")) {
				if (this.getLogradouroCep().getLogradouro().getLogradouroTipo()
						.getDescricao() != null
						&& !this.getLogradouroCep().getLogradouro()
								.getLogradouroTipo().getDescricao()
								.equals("")) {
					// concatena o logradouro tipo do imovel
					endereco = this.getLogradouroCep().getLogradouro()
							.getLogradouroTipo().getDescricao().trim();
				}
			}
			// verifica se o logradouro titulo do imovel é diferente de null
			if (this.getLogradouroCep().getLogradouro().getLogradouroTitulo() != null
					&& !this.getLogradouroCep().getLogradouro()
							.getLogradouroTitulo().equals("")) {
				if (this.getLogradouroCep().getLogradouro()
						.getLogradouroTitulo().getDescricao() != null
						&& !this.getLogradouroCep().getLogradouro()
								.getLogradouroTitulo().getDescricao()
								.equals("")) {
					// concatena o logradouro titulo do imovel
					endereco = endereco
							+ " "
							+ this.getLogradouroCep().getLogradouro()
									.getLogradouroTitulo()
									.getDescricao().trim();
				}
			}

			// concatena o logradouro do imovel
				endereco = endereco + " "
						+ this.getLogradouroCep().getLogradouro().getNome().trim();
			
			if (this.getEnderecoReferencia() != null
					&& !this.getEnderecoReferencia().equals("")) {
				if (this.getEnderecoReferencia().getDescricaoAbreviada() != null
						&& !this.getEnderecoReferencia().getDescricao().equals(
								"")) {
					endereco = endereco + ", "
							+ this.getEnderecoReferencia().getDescricao().trim();
				}
			}

		}

		return endereco;
	}

	/*
	 * Retorna a inscrição do imóvel.
	 */
	public String getInscricaoFormatada() {
		String inscricao = null;

		String zeroUm = "0";
		String zeroDois = "00";
		String zeroTres = "000";

		String localidade, setorComercial, quadra, lote, subLote;

		localidade = String.valueOf(this.getLocalidade().getId().intValue());
		setorComercial = String.valueOf(this.getSetorComercial().getCodigo());
		quadra = String.valueOf(this.getQuadra().getNumeroQuadra());
		lote = String.valueOf(this.getLote());
		subLote = String.valueOf(this.getSubLote());

		if (String.valueOf(this.getLocalidade().getId().intValue()).length() < 3
				&& String.valueOf(this.getLocalidade().getId().intValue())
						.length() > 1) {
			localidade = zeroUm + this.getLocalidade().getId().intValue();
		} else if (String.valueOf(this.getLocalidade().getId().intValue())
				.length() < 3) {
			localidade = zeroDois + this.getLocalidade().getId().intValue();
		}

		if (String.valueOf(this.getSetorComercial().getCodigo()).length() < 3
				&& String.valueOf(this.getSetorComercial().getCodigo())
						.length() > 1) {
			setorComercial = zeroUm + this.getSetorComercial().getCodigo();
		} else if (String.valueOf(this.getSetorComercial().getCodigo())
				.length() < 3) {
			setorComercial = zeroDois + this.getSetorComercial().getCodigo();
		}

		/*if (String.valueOf(this.getQuadra().getNumeroQuadra()).length() < 4
			&& String.valueOf(this.getQuadra().getNumeroQuadra()).length() > 2) {
			quadra = zeroUm + this.getQuadra().getNumeroQuadra();
		} else if (String.valueOf(this.getQuadra().getNumeroQuadra()).length() < 3
				&& String.valueOf(this.getQuadra().getNumeroQuadra()).length() > 1) {
			quadra = zeroDois + this.getQuadra().getNumeroQuadra();
		} else if (String.valueOf(this.getQuadra().getNumeroQuadra()).length() < 2) {
			quadra = zeroTres + this.getQuadra().getNumeroQuadra();
		}*/
		
		if (String.valueOf(this.getQuadra().getNumeroQuadra()).length() < 3
				&& String.valueOf(this.getQuadra().getNumeroQuadra())
						.length() > 1) {
			quadra = zeroUm + this.getQuadra().getNumeroQuadra();
		} else if (String.valueOf(this.getQuadra().getNumeroQuadra())
				.length() < 3) {
			quadra = zeroDois + this.getQuadra().getNumeroQuadra();
		}

		if (String.valueOf(this.getLote()).length() < 4
				&& String.valueOf(this.getLote()).length() > 2) {
			lote = zeroUm + this.getLote();
		} else if (String.valueOf(this.getLote()).length() < 3
				&& String.valueOf(this.getLote()).length() > 1) {
			lote = zeroDois + this.getLote();
		} else if (String.valueOf(this.getLote()).length() < 2) {
			lote = zeroTres + this.getLote();
		}

		if (String.valueOf(this.getSubLote()).length() < 3
				&& String.valueOf(this.getSubLote()).length() > 1) {
			subLote = zeroUm + this.getSubLote();
		} else if (String.valueOf(this.getSubLote()).length() < 3) {
			subLote = zeroDois + this.getSubLote();
		}

		inscricao = localidade + "." + setorComercial + "." + quadra + "."
				+ lote + "." + subLote;

		return inscricao;
	}
	
	public String getInscricaoFormatadaSemPonto() {
		String inscricao = null;

		String zeroUm = "0";
		String zeroDois = "00";
		String zeroTres = "000";

		String localidade, setorComercial, quadra, lote, subLote;

		localidade = String.valueOf(this.getLocalidade().getId().intValue());
		setorComercial = String.valueOf(this.getSetorComercial().getCodigo());
		quadra = String.valueOf(this.getQuadra().getNumeroQuadra());
		lote = String.valueOf(this.getLote());
		subLote = String.valueOf(this.getSubLote());

		if (String.valueOf(this.getLocalidade().getId().intValue()).length() < 3
				&& String.valueOf(this.getLocalidade().getId().intValue())
						.length() > 1) {
			localidade = zeroUm + this.getLocalidade().getId().intValue();
		} else if (String.valueOf(this.getLocalidade().getId().intValue())
				.length() < 3) {
			localidade = zeroDois + this.getLocalidade().getId().intValue();
		}

		if (String.valueOf(this.getSetorComercial().getCodigo()).length() < 3
				&& String.valueOf(this.getSetorComercial().getCodigo())
						.length() > 1) {
			setorComercial = zeroUm + this.getSetorComercial().getCodigo();
		} else if (String.valueOf(this.getSetorComercial().getCodigo())
				.length() < 3) {
			setorComercial = zeroDois + this.getSetorComercial().getCodigo();
		}

		/*if (String.valueOf(this.getQuadra().getNumeroQuadra()).length() < 4
			&& String.valueOf(this.getQuadra().getNumeroQuadra()).length() > 2) {
			quadra = zeroUm + this.getQuadra().getNumeroQuadra();
		} else if (String.valueOf(this.getQuadra().getNumeroQuadra()).length() < 3
			&& String.valueOf(this.getQuadra().getNumeroQuadra()).length() > 1) {
			quadra = zeroDois + this.getQuadra().getNumeroQuadra();
		} else if (String.valueOf(this.getQuadra().getNumeroQuadra()).length() < 2) {
			quadra = zeroTres + this.getQuadra().getNumeroQuadra();
		}*/
		
		if (String.valueOf(this.getQuadra().getNumeroQuadra()).length() < 3
				&& String.valueOf(this.getQuadra().getNumeroQuadra())
						.length() > 1) {
			quadra = zeroUm + this.getQuadra().getNumeroQuadra();
		} else if (String.valueOf(this.getQuadra().getNumeroQuadra())
				.length() < 3) {
			quadra = zeroDois + this.getQuadra().getNumeroQuadra();
		}

		if (String.valueOf(this.getLote()).length() < 4
				&& String.valueOf(this.getLote()).length() > 2) {
			lote = zeroUm + this.getLote();
		} else if (String.valueOf(this.getLote()).length() < 3
				&& String.valueOf(this.getLote()).length() > 1) {
			lote = zeroDois + this.getLote();
		} else if (String.valueOf(this.getLote()).length() < 2) {
			lote = zeroTres + this.getLote();
		}

		if (String.valueOf(this.getSubLote()).length() < 3
				&& String.valueOf(this.getSubLote()).length() > 1) {
			subLote = zeroUm + this.getSubLote();
		} else if (String.valueOf(this.getSubLote()).length() < 3) {
			subLote = zeroDois + this.getSubLote();
		}

		inscricao = localidade + setorComercial + quadra + lote + subLote;

		return inscricao;
	}

	/**
	 * Description of the Method
	 * 
	 * @return Description of the Return Value
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	/**
	 * Description of the Method
	 * 
	 * @return Description of the Return Value
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getLogradouroCep().getLogradouro())
				.append(getLocalidade()).append(getNumeroImovel()).append(
						getQuadra()).append(getSetorComercial()).append(
						getComplementoEndereco()).append(
						getLogradouroCep().getCep()).toHashCode();
	}

	/**
	 * Retorna o valor de imovelSubcategorias
	 * 
	 * @return O valor de imovelSubcategorias
	 */
	public Set getImovelSubcategorias() {
		return imovelSubcategorias;
	}

	/**
	 * Seta o valor de imovelSubcategorias
	 * 
	 * @param imovelSubcategorias
	 *            O novo valor de imovelSubcategorias
	 */
	public void setImovelSubcategorias(Set imovelSubcategorias) {
		this.imovelSubcategorias = imovelSubcategorias;
	}

	/**
	 * Retorna o valor de clienteImoveis
	 * 
	 * @return O valor de clienteImoveis
	 */
	public Set getClienteImoveis() {
		return clienteImoveis;
	}

	/**
	 * Seta o valor de clienteImoveis
	 * 
	 * @param clienteImoveis
	 *            O novo valor de clienteImoveis
	 */
	public void setClienteImoveis(Set clienteImoveis) {
		this.clienteImoveis = clienteImoveis;
	}

	/**
	 * Gets the quantidadeEconomias attribute of the Imovel object
	 * 
	 * @return The quantidadeEconomias value
	 */
	public Short getQuantidadeEconomias() {
		return quantidadeEconomias;
	}

	/**
	 * Sets the quantidadeEconomias attribute of the Imovel object
	 * 
	 * @param quantidadeEconomias
	 *            The new quantidadeEconomias value
	 */
	public void setQuantidadeEconomias(Short quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	/**
	 * Gets the consumoTarifa attribute of the Imovel object
	 * 
	 * @return The consumoTarifa value
	 */
	public gcom.faturamento.consumotarifa.ConsumoTarifa getConsumoTarifa() {
		return consumoTarifa;
	}

	/**
	 * Sets the consumoTarifa attribute of the Imovel object
	 * 
	 * @param consumoTarifa
	 *            The new consumoTarifa value
	 */
	public void setConsumoTarifa(
			gcom.faturamento.consumotarifa.ConsumoTarifa consumoTarifa) {
		this.consumoTarifa = consumoTarifa;
	}

	public Set getMedicaoHistoricos() {
		return medicaoHistoricos;
	}

	public void setMedicaoHistoricos(Set medicaoHistoricos) {
		this.medicaoHistoricos = medicaoHistoricos;
	}

	public FaturamentoTipo getFaturamentoTipo() {
		return faturamentoTipo;
	}

	public void setFaturamentoTipo(FaturamentoTipo faturamentoTipo) {
		this.faturamentoTipo = faturamentoTipo;
	}

	public Short getNumeroReparcelamentoConsecutivos() {
		return numeroReparcelamentoConsecutivos;
	}

	public void setNumeroReparcelamentoConsecutivos(
			Short numeroReparcelamentoConsecutivos) {
		this.numeroReparcelamentoConsecutivos = numeroReparcelamentoConsecutivos;
	}

	public Set getConsumosHistoricos() {
		return consumosHistoricos;
	}

	public void setConsumosHistoricos(Set consumosHistoricos) {
		this.consumosHistoricos = consumosHistoricos;
	}

	public LogradouroBairro getLogradouroBairro() {
		return logradouroBairro;
	}

	public void setLogradouroBairro(LogradouroBairro logradouroBairro) {
		this.logradouroBairro = logradouroBairro;
	}

	public LogradouroCep getLogradouroCep() {
		return logradouroCep;
	}

	public void setLogradouroCep(LogradouroCep logradouroCep) {
		this.logradouroCep = logradouroCep;
	}

	public gcom.cadastro.imovel.ImovelTipoCobertura getImovelTipoCobertura() {
		return imovelTipoCobertura;
	}

	public void setImovelTipoCobertura(
			gcom.cadastro.imovel.ImovelTipoCobertura imovelTipoCobertura) {
		this.imovelTipoCobertura = imovelTipoCobertura;
	}

	public gcom.cadastro.imovel.ImovelTipoConstrucao getImovelTipoConstrucao() {
		return imovelTipoConstrucao;
	}

	public void setImovelTipoConstrucao(
			gcom.cadastro.imovel.ImovelTipoConstrucao imovelTipoConstrucao) {
		this.imovelTipoConstrucao = imovelTipoConstrucao;
	}

	public gcom.cadastro.imovel.ImovelTipoHabitacao getImovelTipoHabitacao() {
		return imovelTipoHabitacao;
	}

	public void setImovelTipoHabitacao(
			gcom.cadastro.imovel.ImovelTipoHabitacao imovelTipoHabitacao) {
		this.imovelTipoHabitacao = imovelTipoHabitacao;
	}

	public gcom.cadastro.imovel.ImovelTipoPropriedade getImovelTipoPropriedade() {
		return imovelTipoPropriedade;
	}

	public void setImovelTipoPropriedade(
			gcom.cadastro.imovel.ImovelTipoPropriedade imovelTipoPropriedade) {
		this.imovelTipoPropriedade = imovelTipoPropriedade;
	}

	public Filtro retornaFiltro() {
		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
				this.getId()));
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgoto");
//		filtroImovel
//				.adicionarCaminhoParaCarregamentoEntidade("tarifaSocialDado");
		// filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua");
		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("imovelEnderecoAnterior");
		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("imovelPrincipal");
		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("imovelCondominio");
		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("hidrometroInstalacaoHistorico");
		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("leituraAnormalidade");
		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("eloAnormalidade");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("areaConstruidaFaixa");
		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("pavimentoCalcada");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelPerfil");
		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("reservatorioVolumeFaixaSuperior");
		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("reservatorioVolumeFaixaInferior");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
		
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadraFace");
		
		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("cobrancaSituacaoTipo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("pavimentoRua");
		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		// filtroImovel.adicionarCaminhoParaCarregamentoEntidade("nomeConta");
		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("cadastroOcorrencia");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("pocoTipo");
		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("despejo");
		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoTipo");
		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("fonteAbastecimento");
		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("piscinaVolumeFaixa");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("consumoTarifa");
		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("faturamentoTipo");
		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoMotivo");
		filtroImovel
				.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("rotaAlternativa");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("rotaEntrega");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua");
//		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("imovelDoacao");
		return filtroImovel;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	public ImovelContaEnvio getImovelContaEnvio() {
		return imovelContaEnvio;
	}

	public void setImovelContaEnvio(ImovelContaEnvio imovelContaEnvio) {
		this.imovelContaEnvio = imovelContaEnvio;
	}

	public CobrancaSituacao getCobrancaSituacao() {
		return cobrancaSituacao;
	}

	public void setCobrancaSituacao(CobrancaSituacao cobrancaSituacao) {
		this.cobrancaSituacao = cobrancaSituacao;
	}

    public Short getIndicadorJardim() {
        return indicadorJardim;
    }

    public void setIndicadorJardim(Short indicadorJardim) {
        this.indicadorJardim = indicadorJardim;
    }

    public Integer getNumeroSequencialRota() {
        return numeroSequencialRota;
    }

    public void setNumeroSequencialRota(Integer numeroSequencialRota) {
        this.numeroSequencialRota = numeroSequencialRota;
    }

	public String getNomeImovel() {
		return nomeImovel;
	}

	public void setNomeImovel(String nomeImovel) {
		this.nomeImovel = nomeImovel;
	}

	public Set getContas() {
		return contas;
	}

	public void setContas(Set contas) {
		this.contas = contas;
	}

	@Override
	public void initializeLazy() {
		initilizarCollectionLazy(this.getImovelSubcategorias());
		initilizarCollectionLazy(this.getClienteImoveis());
		getInscricaoFormatada();
		if (getLogradouroCep() != null)	logradouroCep.initializeLazy();
		if (getLogradouroBairro() != null) logradouroBairro.initializeLazy();
		if (getAreaConstruidaFaixa() != null) areaConstruidaFaixa.initializeLazy();
		if (getImovelPerfil() != null) imovelPerfil.initializeLazy();
		if (getImovelContaEnvio() != null) imovelContaEnvio.initializeLazy();
		if (getLocalidade() != null) localidade.initializeLazy();
		if (getQuadra() != null) quadra.initializeLazy();
		if (getQuadraFace() != null) quadraFace.initializeLazy();
		if (getRotaAlternativa() != null) this.rotaAlternativa.initializeLazy();
		if (getRotaEntrega() != null) this.rotaEntrega.initializeLazy();
		
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"localidade.descricao","inscricaoFormatada", "ligacaoAgua.dataReligacao"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Localidade", "Inscricao", "Data Rel. Agua"};
		return labels;		
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	// ------- Fim Registra transação ---------------
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getId() + "";
	}

    public Cliente getClienteUsuario(){
    	if (clienteImoveis != null){
    		for (Iterator iterator = clienteImoveis.iterator(); iterator.hasNext();) {
				ClienteImovel clienteImovel = (ClienteImovel) iterator.next();
				if (clienteImovel != null && 
					clienteImovel.getClienteRelacaoTipo().getId().shortValue() == ClienteRelacaoTipo.USUARIO.shortValue()){
					return clienteImovel.getCliente(); 
				}				
			} 
    	}
    	return null;
    }
	/**
	 * @return Retorna o campo numeroSequencialRotaEntrega.
	 */
	public Integer getNumeroSequencialRotaEntrega() {
		return numeroSequencialRotaEntrega;
	}

	/**
	 * @param numeroSequencialRotaEntrega O numeroSequencialRotaEntrega a ser setado.
	 */
	public void setNumeroSequencialRotaEntrega(Integer numeroSequencialRotaEntrega) {
		this.numeroSequencialRotaEntrega = numeroSequencialRotaEntrega;
	}

	/**
	 * @return Retorna o campo rotaEntrega.
	 */
	public Rota getRotaEntrega() {
		return rotaEntrega;
	}

	/**
	 * @param rotaEntrega O rotaEntrega a ser setado.
	 */
	public void setRotaEntrega(Rota rotaEntrega) {
		this.rotaEntrega = rotaEntrega;
	}
	
	public String getMatriculaFormatada() {

		String matriculaImovelFormatada = "";
		
		Integer matriculaImovel = this.id;
		
		matriculaImovelFormatada = "" + matriculaImovel;
		
		int quantidadeCaracteresImovel = matriculaImovel.toString()
					.length();
			matriculaImovelFormatada = matriculaImovelFormatada.substring(0,
					quantidadeCaracteresImovel - 1)
					+ "."
					+ matriculaImovelFormatada.substring(
							quantidadeCaracteresImovel - 1,
							quantidadeCaracteresImovel);

		return matriculaImovelFormatada;
	}
	
	public SituacaoAtualizacaoCadastral getSituacaoAtualizacaoCadastral() {
		return situacaoAtualizacaoCadastral;
	}

	public void setSituacaoAtualizacaoCadastral(
			SituacaoAtualizacaoCadastral situacaoAtualizacaoCadastral) {
		this.situacaoAtualizacaoCadastral = situacaoAtualizacaoCadastral;
	}
	
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovelTipoHabitacao");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovelTipoPropriedade");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovelTipoConstrucao");
		filtro.adicionarCaminhoParaCarregamentoEntidade("imovelTipoCobertura");		
		return filtro;
	}

	public QuadraFace getQuadraFace() {
		return quadraFace;
	}

	public void setQuadraFace(QuadraFace quadraFace) {
		this.quadraFace = quadraFace;
	}

	/**
	 * @return Retorna o campo perimetroFinal.
	 */
	public Logradouro getPerimetroFinal() {
		return perimetroFinal;
	}

	/**
	 * @param perimetroFinal O perimetroFinal a ser setado.
	 */
	public void setPerimetroFinal(Logradouro perimetroFinal) {
		this.perimetroFinal = perimetroFinal;
	}

	/**
	 * @return Retorna o campo perimetroInicial.
	 */
	public Logradouro getPerimetroInicial() {
		return perimetroInicial;
	}

	/**
	 * @param perimetroInicial O perimetroInicial a ser setado.
	 */
	public void setPerimetroInicial(Logradouro perimetroInicial) {
		this.perimetroInicial = perimetroInicial;
	}

	/**
	 *@return Retorna o campo de rota alternativa
	 */
	public Rota getRotaAlternativa() {
		return rotaAlternativa;
	}	
	
	/**
	 * @param rotaAlternativa O camp de rota alternativa
	 */
	public void setRotaAlternativa(Rota rotaAlternativa) {
		this.rotaAlternativa = rotaAlternativa;
	}

	public String getNumeroMedidorEnergia() {
		return numeroMedidorEnergia;
	}

	public void setNumeroMedidorEnergia(String numeroMedidorEnergia) {
		this.numeroMedidorEnergia = numeroMedidorEnergia;
	}

	public Date getDataVisitaComercial() {
		return dataVisitaComercial;
	}

	public void setDataVisitaComercial(Date dataVisitaComercial) {
		this.dataVisitaComercial = dataVisitaComercial;
	}
	
	public Short getIndicadorVencimentoMesSeguinte() {
		return indicadorVencimentoMesSeguinte;
	}

	public void setIndicadorVencimentoMesSeguinte(
			Short indicadorVencimentoMesSeguinte) {
		this.indicadorVencimentoMesSeguinte = indicadorVencimentoMesSeguinte;
	}

	public String getInformacoesComplementares() {
		return informacoesComplementares;
	}

	public void setInformacoesComplementares(String informacoesComplementares) {
		this.informacoesComplementares = informacoesComplementares;
	}


	public Integer getIndicadorReincidenciaInfracao() {
		return indicadorReincidenciaInfracao;
	}

	public void setIndicadorReincidenciaInfracao(
			Integer indicadorReincidenciaInfracao) {
		this.indicadorReincidenciaInfracao = indicadorReincidenciaInfracao;
	}

	public Integer getCodigoDebitoAutomatico() {
		return codigoDebitoAutomatico;
	}

	public void setCodigoDebitoAutomatico(Integer codigoDebitoAutomatico) {
		this.codigoDebitoAutomatico = codigoDebitoAutomatico;
	}
	
	/**
	 * TODO : COSANPA
	 * Adicionando método que verifica se o imóvel faz parte 
	 * de algum condomínio
	 * 
	 * @return boolean
	 */
	public boolean isImovelCondominio() {

		if (this.getIndicadorImovelCondominio().shortValue() == ConstantesSistema.NAO
				&& this.getImovelCondominio() != null) {
			return true;
		} else {
			return false;
		}
	}

	public Integer getNumeroQuadraEntrega() {
		return numeroQuadraEntrega;
	}

	public void setNumeroQuadraEntrega(Integer numeroQuadraEntrega) {
		this.numeroQuadraEntrega = numeroQuadraEntrega;
	}

	public Integer getAnoMesExclusaoTarifaSocial() {
		return anoMesExclusaoTarifaSocial;
	}

	public void setAnoMesExclusaoTarifaSocial(Integer anoMesExclusaoTarifaSocial) {
		this.anoMesExclusaoTarifaSocial = anoMesExclusaoTarifaSocial;
	}

	public Short getIndicadorNivelInstalacaoEsgoto() {
		return indicadorNivelInstalacaoEsgoto;
	}

	public void setIndicadorNivelInstalacaoEsgoto(
			Short indicadorNivelInstalacaoEsgoto) {
		this.indicadorNivelInstalacaoEsgoto = indicadorNivelInstalacaoEsgoto;
	}

	public Short getIndicadorImovelAreaComum() {
		return indicadorImovelAreaComum;
	}

	public void setIndicadorImovelAreaComum(Short indicadorImovelAreaComum) {
		this.indicadorImovelAreaComum = indicadorImovelAreaComum;
	}

	public Integer getCategoriaPrincipalId() {
		return categoriaPrincipalId;
	}

	public void setCategoriaPrincipalId(Integer categoriaPrincipalId) {
		this.categoriaPrincipalId = categoriaPrincipalId;
	}

	public Integer getSubCategoriaPrincipalId() {
		return subCategoriaPrincipalId;
	}

	public void setSubCategoriaPrincipalId(Integer subCategoriaPrincipalId) {
		this.subCategoriaPrincipalId = subCategoriaPrincipalId;
	}
	
	
}
