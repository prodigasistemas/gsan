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
* Anderson Italo Felinto de Lima
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
package gcom.relatorio.cobranca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.cobranca.FiltroImovelNaoGerado;
import gcom.cobranca.ImovelNaoGerado;
import gcom.fachada.Fachada;
import gcom.gui.cobranca.MotivoNaoGeracaoDocumentoActionForm;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

/**
 * Descrição da classe
 * Classe responsável pelo processamento dos
 * parametros informados e consequente 
 * montagem dos registros exibidos posteriormente
 * pelo relatório
 * 
 * @author Anderson Italo
 * @date 26/11/2009
 */
public class RelatorioMotivoNaoGeracaoDocumentoCobranca extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioMotivoNaoGeracaoDocumentoCobranca(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_FILTRAR_DOCUMENTO_COBRANCA);
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		
		Fachada fachada = Fachada.getInstancia();
		Map<String, Object> parametros = new HashMap<String, Object>();
		 
		SistemaParametro sistemaParametro = Fachada.getInstancia().pesquisarParametrosDoSistema();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		Integer tipoRelatorio = (Integer) getParametro("tipoRelatorio");
		Integer indicadorSintetico =  (Integer)getParametro("sintetico");
		Integer indicadorCronograma =  (Integer)getParametro("indicadorCronograma");
		Integer idCobrancaGrupo = (Integer) getParametro("idCobrancaGrupo");
		CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = (CobrancaAcaoAtividadeCronograma) getParametro("cobrancaAcaoAtividadeCronograma");
		CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando = (CobrancaAcaoAtividadeComando) getParametro("cobrancaAcaoAtividadeComando");
		MotivoNaoGeracaoDocumentoActionForm form = (MotivoNaoGeracaoDocumentoActionForm)getParametro("form");
		
		Integer gerencia = null;
		Integer unidade = null;
		Integer localidade = null;
		Integer setorComercial = null;
		Integer quadra = null;
		
		if(form.getGerenciaRegional() != null && !"-1".equals(form.getGerenciaRegional()))
			gerencia = new Integer(form.getGerenciaRegional());
		if(form.getUnidadeNegocio() != null && !"-1".equals(form.getUnidadeNegocio()))
			unidade = new Integer(form.getUnidadeNegocio());
		if(form.getIdLocalidade() != null && !"".equals(form.getIdLocalidade()))
			localidade = new Integer(form.getIdLocalidade());
		if(form.getIdSetorComercial() != null && !"".equals(form.getIdSetorComercial()))
			setorComercial = new Integer(form.getIdSetorComercial());
		if(form.getIdQuadra() != null && !"".equals(form.getIdQuadra()))
			quadra = new Integer(form.getIdQuadra());
		
		
		

		byte[] retorno = null;
		
		if (indicadorSintetico.intValue() == 1){
				
			Integer totalImoveisComando = 0;
			if (indicadorCronograma.intValue() == 1){
				/*5.2.	Imóveis no Comando: total de imóveis do Grupo de Cobrança ( quantidade 
				 * de IMOV_ID com IMOV_ICEXCLUSAO = 2 e QDRA_ID = QDRA_ID da tabela QUADRA com 
				 * ROTA_ID = ROTA_ID da tabela ROTA com CBGR_ID = CBGR_ID do grupo em questão)*/				
				totalImoveisComando = fachada.pesquisarQuantidadeImoveisPorGrupoCobranca(idCobrancaGrupo,gerencia,unidade,localidade,setorComercial,quadra);
			}else{
				//8.2.	Imóveis no Comando: total de imóveis do Comando Eventual 
				totalImoveisComando = pesquisaTotalImoveisComandoEventual(fachada, cobrancaAcaoAtividadeComando,form);
			}
			
			
			FiltroImovelNaoGerado filtroImovelNaoGerado = new FiltroImovelNaoGerado();
			if (indicadorCronograma.intValue() == 1){
				/*5.4.	Imóveis que não geraram documento (quantidade de imóveis a partir 
				 * da tabela IMOVEL_NAO_GERADO com CAAC_ID = CAAC_ID do comando)*/
				
				filtroImovelNaoGerado.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelNaoGerado.IMOVEL);
				filtroImovelNaoGerado.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelNaoGerado.LOCALIDADE);
				filtroImovelNaoGerado.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelNaoGerado.UNIDADE_NEGOCIO);
				filtroImovelNaoGerado.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelNaoGerado.GERENCIA_REGIONAL);
				filtroImovelNaoGerado.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelNaoGerado.SETOR_COMERCIAL);
				filtroImovelNaoGerado.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelNaoGerado.QUADRA);
				filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(
						FiltroImovelNaoGerado.ID_COBRANCA_ACAO_ATIVIDADE_CRONOGRAMA, cobrancaAcaoAtividadeCronograma.getId()));
				
				
				if(form.getGerenciaRegional() != null && !"-1".equals(form.getGerenciaRegional()))
					filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(FiltroImovelNaoGerado.GERENCIA_REGIONAL_ID,new Integer(form.getGerenciaRegional())));
				if(form.getUnidadeNegocio() != null && !"-1".equals(form.getUnidadeNegocio()))
					filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(FiltroImovelNaoGerado.UNIDADE_NEGOCIO_ID,new Integer(form.getUnidadeNegocio())));
				if(form.getIdLocalidade() != null && !"".equals(form.getIdLocalidade()))
					filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(FiltroImovelNaoGerado.LOCALIDADE_ID,new Integer(form.getIdLocalidade())));
				if(form.getIdSetorComercial() != null && !"".equals(form.getIdSetorComercial()))
					filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(FiltroImovelNaoGerado.SETOR_COMERCIAL_CODIGO,new Integer(form.getIdSetorComercial())));
				if(form.getIdQuadra() != null && !"".equals(form.getIdQuadra()))
					filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(FiltroImovelNaoGerado.QUADRA_NUMERO,new Integer(form.getIdQuadra())));
								
				
			}else{
				/*8.4.	Imóveis que não geraram documento (quantidade de imóveis a partir 
				 * da tabela IMOVEL_NAO_GERADO com CACM_ID = CACM_ID do comando)*/
				filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(
						FiltroImovelNaoGerado.ID_COBRANCA_ACAO_ATIVIDADE_COMANDO, cobrancaAcaoAtividadeComando.getId()));
				
			}
			filtroImovelNaoGerado.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelNaoGerado.MOTIVO_NAO_GERACAO_DOCUMENTO_COBRANCA);
			filtroImovelNaoGerado.setCampoOrderBy(FiltroImovelNaoGerado.ID_MOTIVO_NAO_GERACAO_DOCUMENTO_COBRANCA);
			Collection colecaoImovelNaoGerado = fachada.pesquisar(filtroImovelNaoGerado, ImovelNaoGerado.class.getName());
			
			//seta os pametros
			parametros.put("imoveisComando", totalImoveisComando);
			if (indicadorCronograma.intValue() == 1){
				/*5.3.	Imóveis que geraram documento quantidade de documentos gerados no 
				 * comando  ( CAAC_QTDOCUMENTOS da tabela COBRANCA_ACAO_ATIVIDADE_CRONOG  
				 * com CAAC_ID = CAAC_ID do comando em questão)*/
				if (cobrancaAcaoAtividadeCronograma.getQuantidadeDocumentos() != null){
					parametros.put("imoveisGeraramDocumento", cobrancaAcaoAtividadeCronograma.getQuantidadeDocumentos());
				}else{
					parametros.put("imoveisGeraramDocumento", 0);
				}
				
			}else{
				/*8.3.	Imóveis que geraram documento quantidade de documentos gerados no 
				 * comando  ( CAAC_QTDOCUMENTOS da tabela COBRANCA_ACAO_ATIVIDADE_CRONOG  
				 * com CACM_ID = CACM_ID do comando em questão)*/
				if (cobrancaAcaoAtividadeComando.getQuantidadeDocumentos() != null){
					parametros.put("imoveisGeraramDocumento", cobrancaAcaoAtividadeComando.getQuantidadeDocumentos());
				}else{
					parametros.put("imoveisGeraramDocumento", 0);
				}
				
			}
			parametros.put("imoveisNaoGeraramDocumento", colecaoImovelNaoGerado.size());
			
			List<RelatorioMotivoNaoGeracaoDocumentoCobrancaSinteticoBean> beans = new ArrayList<RelatorioMotivoNaoGeracaoDocumentoCobrancaSinteticoBean>();
			
			if (colecaoImovelNaoGerado != null && !colecaoImovelNaoGerado.isEmpty()){
				/*5.5.	Descrição do Motivo: (a partir da tabela MOTIVO_NAO_GERACAO_DOCUMENTO 
				 * com MNGD_ID = MNGD_ID da tabela IMOVEL_NAO_GERADO com CAAC_ID = CAAC_ID do comando)
				 * 8.5.	Descrição do Motivo: (a partir da tabela MOTIVO_NAO_GERACAO_DOCUMENTO 
				 * com MNGD_ID = MNGD_ID da tabela IMOVEL_NAO_GERADO com CACM_ID = CACM_ID do comando)*/
				
				RelatorioMotivoNaoGeracaoDocumentoCobrancaSinteticoBean bean = new RelatorioMotivoNaoGeracaoDocumentoCobrancaSinteticoBean();
				int quantidadeImoveisPorMotivo = 0;
				ImovelNaoGerado imovelNaoGeradoAnterior = null;
				
				
				/* 5.6.	Caso tenha sido escolhida a opção sintético:
				 * 8.6.	Caso tenha sido escolhida a opção sintético
				 * 	5.7.1.	Matrícula dos Imóveis não gerados no comando (a 
				 * 	partir da tabela IMOVEL_NAO_GERADO com CAAC_ID = CAAC_ID do Comando)
				 * 	8.7.1.	Matrícula dos Imóveis não gerados no comando (a partir da 
				 * tabela IMOVEL_NAO_GERADO com CACM_ID = CACM_ID do Comando)*/
				for (Iterator colecaoImovelNaoGeradoIterator = colecaoImovelNaoGerado
						.iterator(); colecaoImovelNaoGeradoIterator.hasNext();) {
					ImovelNaoGerado imovelNaoGerado = (ImovelNaoGerado) colecaoImovelNaoGeradoIterator.next();
				
					if (imovelNaoGeradoAnterior != null){
						if (imovelNaoGerado.getMotivoNaoGeracaoDocCobranca().getId().intValue() != imovelNaoGeradoAnterior
								.getMotivoNaoGeracaoDocCobranca().getId().intValue()){
							
							bean.setQuantidadeImoveisPorMotivo(quantidadeImoveisPorMotivo);
							beans.add(bean);
							bean = new RelatorioMotivoNaoGeracaoDocumentoCobrancaSinteticoBean();
							quantidadeImoveisPorMotivo = 0;
						}
					}
					
					quantidadeImoveisPorMotivo++;
					
					bean.setDescricaoMotivo(imovelNaoGerado.getMotivoNaoGeracaoDocCobranca().getDescricao());
					
					imovelNaoGeradoAnterior = imovelNaoGerado; 
					
					if (!colecaoImovelNaoGeradoIterator.hasNext()){
						bean.setQuantidadeImoveisPorMotivo(quantidadeImoveisPorMotivo);
						beans.add(bean);
					}
				}
			}else{
				RelatorioMotivoNaoGeracaoDocumentoCobrancaSinteticoBean bean = new RelatorioMotivoNaoGeracaoDocumentoCobrancaSinteticoBean();
				beans.add(bean);
			}
			
			retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_MOTIVO_NAO_GERACAO_DOCUMENTO_COBRANCA_SINTETICO,
					parametros, new RelatorioDataSource(beans), tipoRelatorio);
			
		}else{
			
			List<RelatorioMotivoNaoGeracaoDocumentoCobrancaAnaliticoBean> beans = new ArrayList<RelatorioMotivoNaoGeracaoDocumentoCobrancaAnaliticoBean>();
			
			Integer totalImoveisComando = 0;
			if (indicadorCronograma.intValue() == 1){
				
				/*5.2.	Imóveis no Comando: total de imóveis do Grupo de Cobrança ( quantidade 
				 * de IMOV_ID com IMOV_ICEXCLUSAO = 2 e QDRA_ID = QDRA_ID da tabela QUADRA com 
				 * ROTA_ID = ROTA_ID da tabela ROTA com CBGR_ID = CBGR_ID do grupo em questão)*/
				totalImoveisComando = fachada.pesquisarQuantidadeImoveisPorGrupoCobranca(idCobrancaGrupo,gerencia,unidade,localidade,setorComercial,quadra);
				
			}else{
				//8.2.	Imóveis no Comando: total de imóveis do Comando Eventual 
				totalImoveisComando = pesquisaTotalImoveisComandoEventual(fachada, cobrancaAcaoAtividadeComando,form);
			}
			
			FiltroImovelNaoGerado filtroImovelNaoGerado = new FiltroImovelNaoGerado();
			if (indicadorCronograma.intValue() == 1){
				/*5.4.	Imóveis que não geraram documento (quantidade de imóveis a partir 
				 * da tabela IMOVEL_NAO_GERADO com CAAC_ID = CAAC_ID do comando)*/
				

				filtroImovelNaoGerado.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelNaoGerado.IMOVEL);
				filtroImovelNaoGerado.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelNaoGerado.LOCALIDADE);
				filtroImovelNaoGerado.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelNaoGerado.UNIDADE_NEGOCIO);
				filtroImovelNaoGerado.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelNaoGerado.GERENCIA_REGIONAL);
				/*filtroImovelNaoGerado.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelNaoGerado.SETOR_COMERCIAL);
				filtroImovelNaoGerado.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelNaoGerado.QUADRA);
				filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(
						FiltroImovelNaoGerado.ID_COBRANCA_ACAO_ATIVIDADE_CRONOGRAMA, cobrancaAcaoAtividadeCronograma.getId()));*/
				
				
				if(form.getGerenciaRegional() != null && !"-1".equals(form.getGerenciaRegional()))
					filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(FiltroImovelNaoGerado.GERENCIA_REGIONAL_ID,new Integer(form.getGerenciaRegional())));
				if(form.getUnidadeNegocio() != null && !"-1".equals(form.getUnidadeNegocio()))
					filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(FiltroImovelNaoGerado.UNIDADE_NEGOCIO_ID,new Integer(form.getUnidadeNegocio())));
				if(form.getIdLocalidade() != null && !"".equals(form.getIdLocalidade()))
					filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(FiltroImovelNaoGerado.LOCALIDADE_ID,new Integer(form.getIdLocalidade())));
				if(form.getIdSetorComercial() != null && !"".equals(form.getIdSetorComercial()))
					filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(FiltroImovelNaoGerado.SETOR_COMERCIAL_CODIGO,new Integer(form.getIdSetorComercial())));
				if(form.getIdQuadra() != null && !"".equals(form.getIdQuadra()))
					filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(FiltroImovelNaoGerado.QUADRA_NUMERO,new Integer(form.getIdQuadra())));
				
				filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(
						FiltroImovelNaoGerado.ID_COBRANCA_ACAO_ATIVIDADE_CRONOGRAMA, cobrancaAcaoAtividadeCronograma.getId()));
				
			}else{
				/*8.4.	Imóveis que não geraram documento (quantidade de imóveis a partir 
				 * da tabela IMOVEL_NAO_GERADO com CACM_ID = CACM_ID do comando)*/
				filtroImovelNaoGerado.adicionarParametro(new ParametroSimples(
						FiltroImovelNaoGerado.ID_COBRANCA_ACAO_ATIVIDADE_COMANDO, cobrancaAcaoAtividadeComando.getId()));
			}
			filtroImovelNaoGerado.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelNaoGerado.MOTIVO_NAO_GERACAO_DOCUMENTO_COBRANCA);
			filtroImovelNaoGerado.setCampoOrderBy(FiltroImovelNaoGerado.ID_MOTIVO_NAO_GERACAO_DOCUMENTO_COBRANCA);
			
			Collection colecaoImovelNaoGerado = fachada.pesquisar(filtroImovelNaoGerado, ImovelNaoGerado.class.getName());
			
			//seta os pametros
			parametros.put("imoveisComando", totalImoveisComando);
			if (indicadorCronograma.intValue() == 1){
				/*5.3.	Imóveis que geraram documento quantidade de documentos gerados no 
				 * comando  ( CAAC_QTDOCUMENTOS da tabela COBRANCA_ACAO_ATIVIDADE_CRONOG  
				 * com CAAC_ID = CAAC_ID do comando em questão)*/
				if (cobrancaAcaoAtividadeCronograma.getQuantidadeDocumentos() != null){
					parametros.put("imoveisGeraramDocumento", cobrancaAcaoAtividadeCronograma.getQuantidadeDocumentos());
				}else{
					parametros.put("imoveisGeraramDocumento", 0);
				}
				
			}else{
				/*8.3.	Imóveis que geraram documento quantidade de documentos gerados no 
				 * comando  ( CAAC_QTDOCUMENTOS da tabela COBRANCA_ACAO_ATIVIDADE_CRONOG  
				 * com CACM_ID = CACM_ID do comando em questão)*/
				if (cobrancaAcaoAtividadeComando.getQuantidadeDocumentos() != null){
					parametros.put("imoveisGeraramDocumento", cobrancaAcaoAtividadeComando.getQuantidadeDocumentos());
				}else{
					parametros.put("imoveisGeraramDocumento", 0);
				}
				
			}
			parametros.put("imoveisNaoGeraramDocumento", colecaoImovelNaoGerado.size());
			
			if (colecaoImovelNaoGerado != null && !colecaoImovelNaoGerado.isEmpty()){
				/*5.5.	Descrição do Motivo: (a partir da tabela MOTIVO_NAO_GERACAO_DOCUMENTO 
				 * com MNGD_ID = MNGD_ID da tabela IMOVEL_NAO_GERADO com CAAC_ID = CAAC_ID do comando)
				 * 8.5.	Descrição do Motivo: (a partir da tabela MOTIVO_NAO_GERACAO_DOCUMENTO 
				 * com MNGD_ID = MNGD_ID da tabela IMOVEL_NAO_GERADO com CACM_ID = CACM_ID do comando)*/
				
				RelatorioMotivoNaoGeracaoDocumentoCobrancaAnaliticoBean bean = new RelatorioMotivoNaoGeracaoDocumentoCobrancaAnaliticoBean();
				int quantidadeImoveisPorMotivo = 0;
				int i = 0;
				ImovelNaoGerado imovelNaoGeradoAnterior = null;
				
				
				/* 5.6.	Caso tenha sido escolhida a opção sintético:
				 * 8.6.	Caso tenha sido escolhida a opção sintético
				 * 	5.7.1.	Matrícula dos Imóveis não gerados no comando (a 
				 * 	partir da tabela IMOVEL_NAO_GERADO com CAAC_ID = CAAC_ID do Comando)
				 * 	8.7.1.	Matrícula dos Imóveis não gerados no comando (a partir da 
				 * tabela IMOVEL_NAO_GERADO com CACM_ID = CACM_ID do Comando)*/
				for (Iterator colecaoImovelNaoGeradoIterator = colecaoImovelNaoGerado
						.iterator(); colecaoImovelNaoGeradoIterator.hasNext();) {
					ImovelNaoGerado imovelNaoGerado = (ImovelNaoGerado) colecaoImovelNaoGeradoIterator.next();
				
					if (imovelNaoGeradoAnterior != null){
						if (imovelNaoGerado.getMotivoNaoGeracaoDocCobranca().getId().intValue() != imovelNaoGeradoAnterior
								.getMotivoNaoGeracaoDocCobranca().getId()){
							
							bean.setQuantidadeImoveisPorMotivo(quantidadeImoveisPorMotivo);
							beans.add(bean);
							bean = new RelatorioMotivoNaoGeracaoDocumentoCobrancaAnaliticoBean();
							quantidadeImoveisPorMotivo = 0;
						}
					}
					
					quantidadeImoveisPorMotivo++;
					i++;
					
					switch (i) {
					case 1:
						if (bean.getMatricula1() != null){
							bean.setMatricula1(bean.getMatricula1() + "    " + imovelNaoGerado.getImovel().getId().toString());
						}else{
							bean.setMatricula1(imovelNaoGerado.getImovel().getId().toString());
						}
						break;
					case 2:
						if (bean.getMatricula2() != null){
							bean.setMatricula2(bean.getMatricula2() + "    " + imovelNaoGerado.getImovel().getId().toString());
						}else{
							bean.setMatricula2(imovelNaoGerado.getImovel().getId().toString());
						}
						break;
					case 3:
						if (bean.getMatricula3() != null){
							bean.setMatricula3(bean.getMatricula3() + "    " + imovelNaoGerado.getImovel().getId().toString());
						}else{
							bean.setMatricula3(imovelNaoGerado.getImovel().getId().toString());
						}
						break;
					case 4:
						if (bean.getMatricula4() != null){
							bean.setMatricula4(bean.getMatricula4() + "    " + imovelNaoGerado.getImovel().getId().toString());
						}else{
							bean.setMatricula4(imovelNaoGerado.getImovel().getId().toString());
						}
						break;
					case 5:
						if (bean.getMatricula5() != null){
							bean.setMatricula5(bean.getMatricula5() + "    " + imovelNaoGerado.getImovel().getId().toString());
						}else{
							bean.setMatricula5(imovelNaoGerado.getImovel().getId().toString());
						}
						break;
					case 6:
						if (bean.getMatricula6() != null){
							bean.setMatricula6(bean.getMatricula6() + "    " + imovelNaoGerado.getImovel().getId().toString());
						}else{
							bean.setMatricula6(imovelNaoGerado.getImovel().getId().toString());
						}
						break;
					case 7:
						if (bean.getMatricula7() != null){
							bean.setMatricula7(bean.getMatricula7() + "    " + imovelNaoGerado.getImovel().getId().toString());
						}else{
							bean.setMatricula7(imovelNaoGerado.getImovel().getId().toString());
						}
						break;
					case 8:
						if (bean.getMatricula8() != null){
							bean.setMatricula8(bean.getMatricula8() + "    " + imovelNaoGerado.getImovel().getId().toString());
						}else{
							bean.setMatricula8(imovelNaoGerado.getImovel().getId().toString());
						}
						break;
					case 9:
						if (bean.getMatricula9() != null){
							bean.setMatricula9(bean.getMatricula9() + "    " + imovelNaoGerado.getImovel().getId().toString());
						}else{
							bean.setMatricula9(imovelNaoGerado.getImovel().getId().toString());
						}
						break;
					case 10:
						if (bean.getMatricula10() != null){
							bean.setMatricula10(bean.getMatricula10() + "    " + imovelNaoGerado.getImovel().getId().toString());
						}else{
							bean.setMatricula10(imovelNaoGerado.getImovel().getId().toString());
						}
						break;
					default:
						break;
					}
					
					bean.setDescricaoMotivo(imovelNaoGerado.getMotivoNaoGeracaoDocCobranca().getDescricao());
					
					imovelNaoGeradoAnterior = imovelNaoGerado; 
					
					if (i == 10){
						i=0;
					}
					
					if (!colecaoImovelNaoGeradoIterator.hasNext()){
						bean.setQuantidadeImoveisPorMotivo(quantidadeImoveisPorMotivo);
						beans.add(bean);
					}
				}
			}else{
				RelatorioMotivoNaoGeracaoDocumentoCobrancaAnaliticoBean bean = new RelatorioMotivoNaoGeracaoDocumentoCobrancaAnaliticoBean();
				beans.add(bean);
			}
			
			retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_MOTIVO_NAO_GERACAO_DOCUMENTO_COBRANCA_ANALITICO,
					parametros, new RelatorioDataSource(beans), tipoRelatorio);
		}
		
		return retorno;
	}

	
	private Integer pesquisaTotalImoveisComandoEventual(Fachada fachada, CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando, MotivoNaoGeracaoDocumentoActionForm form) {
		
		Integer totalImoveisRetorno = 0;
		if (cobrancaAcaoAtividadeComando.getCliente() != null || cobrancaAcaoAtividadeComando.getSuperior() != null){
			
			//1.1.1.	Caso o Cliente Superior esteja preenchido
			if (cobrancaAcaoAtividadeComando.getSuperior() != null){
				
				/*1.1.1.2.	O sistema seleciona os imóveis para cada um dos clientes 
				 * subordinados e para o próprio Cliente Superior(a partir da tabela 
				 * CLIENTE_IMOVEL com CLIE_ID=código de cada cliente subordinado, acrescido 
				 * dos imóveis com CLIE_ID = Código do Cliente Superior)*/
				FiltroCliente filtroCliente = new FiltroCliente();
				filtroCliente.adicionarParametro(new ParametroSimples(
						FiltroCliente.CLIENTE_RESPONSAVEL_ID, cobrancaAcaoAtividadeComando.getSuperior().getId()));
				
				Collection colecaoClientes = fachada.pesquisar(filtroCliente, Cliente.class.getName());
				Collection idsClientes = new ArrayList();
				idsClientes.add(cobrancaAcaoAtividadeComando.getSuperior().getId());
				
				for (Iterator colecaoClienteIterator = colecaoClientes.iterator(); colecaoClienteIterator
						.hasNext();) {
					Cliente cliente = (Cliente) colecaoClienteIterator.next();
					idsClientes.add(cliente.getId());
				}
				
				FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
				filtroClienteImovel.adicionarParametro(new ParametroSimplesIn(
						FiltroClienteImovel.CLIENTE_ID, idsClientes));
				
				Collection colecaoClienteImoveis = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
				totalImoveisRetorno = colecaoClienteImoveis.size();
				
			}
			
			//1.1.2.	Caso o Cliente esteja preenchido
			if (cobrancaAcaoAtividadeComando.getCliente() != null){
				/*1.1.2.1.	O sistema seleciona os imóveis relacionados com o cliente 
				 * informado (a partir da tabela CLIENTE_IMOVEL com CLIE_ID=código do cliente 
				 * e CRTP_ID=id do tipo de relação do cliente com o imóvel (se preenchida)*/
				
				FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
				filtroClienteImovel.adicionarParametro(new ParametroSimples(
						FiltroClienteImovel.CLIENTE_ID, cobrancaAcaoAtividadeComando.getCliente().getId()));
				
				if(cobrancaAcaoAtividadeComando.getClienteRelacaoTipo() != null){
					filtroClienteImovel.adicionarParametro(new ParametroSimples(
							FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, cobrancaAcaoAtividadeComando.getClienteRelacaoTipo().getId()));
				}
				
				Collection colecaoClienteImovel = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());
				totalImoveisRetorno = colecaoClienteImovel.size();
			}
			
		}else{
			
			/*8.2.	Imóveis no Comando: total de imóveis do Comando Eventual 
			 * [SB0003 - Obter imóveis do Comando Eventual]
			 * 1.2.1.	A partir da lista de rotas do comando ( tabela COBRANCA_ATIVIDADE_COMAND_ROTA 
			 * com CACM_ID = CACM_ID do comando em questão)
			 * 1.2.2.	Para cada rota da lista, o sistema: 
			 * 1.2.3.	Seleciona os imóveis das quadras pertencentes à rota( IMOV_ID com IMOV_ICEXCLUSAO = 2 
			 * e QDRA_ID = QDRA_ID da tabela QUDRA com ROTA_ID = ROTA_ID de cada rota da lista de rotas;*/
			
			
			Integer gerencia = null;
			Integer unidade = null;
			Integer localidade = null;
			Integer setorComercial = null;
			Integer quadra = null;
			
			if(form.getGerenciaRegional() != null && !"-1".equals(form.getGerenciaRegional()))
				gerencia = new Integer(form.getGerenciaRegional());
			if(form.getUnidadeNegocio() != null && !"-1".equals(form.getUnidadeNegocio()))
				unidade = new Integer(form.getUnidadeNegocio());
			if(form.getIdLocalidade() != null && !"".equals(form.getIdLocalidade()))
				localidade = new Integer(form.getIdLocalidade());
			if(form.getIdSetorComercial() != null && !"".equals(form.getIdSetorComercial()))
				setorComercial = new Integer(form.getIdSetorComercial());
			if(form.getIdQuadra() != null && !"".equals(form.getIdQuadra()))
				quadra = new Integer(form.getIdQuadra());
				
			
			totalImoveisRetorno = fachada.pesquisarQuantidadeImoveisPorComandoEventual(cobrancaAcaoAtividadeComando.getId(),
					gerencia,unidade,localidade,setorComercial,quadra);
		}
		return totalImoveisRetorno;
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioMotivoNaoGeracaoDocumentoCobranca", this);
	}

}
