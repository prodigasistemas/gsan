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
* Ivan Sérgio Virginio da Silva Júnior
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
package gcom.relatorio.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.bean.RelatorioOSFiscalizacaoHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * [UC1176] Gerar Ordem de Fiscalização para Ordem de Serviço Encerrada 
 * [SB0005] – Gerar Formulário em formato pdf
 * @author Vivianne Sousa
 * @date 02/06/2011
 */

public class RelatorioOSFiscalizacao extends TarefaRelatorio {

	private static final long serialVersionUID = -7034984685957706140L;
	
	public RelatorioOSFiscalizacao(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_OS_FISCALIZACAO);
	}
	
	public RelatorioOSFiscalizacao(Usuario usuario, String nomeRelatorio) {
		super(usuario, nomeRelatorio);
	}
	
	@Deprecated
	public RelatorioOSFiscalizacao() {
		super(null, "");
	}
	
	
	protected Collection<RelatorioOSFiscalizacaoBean> inicializarBeanRelatorio(
			Collection colecaoEmitirContaHelper, Collection colecaoOSReferidaRetornoTipo) {
		
		Collection<RelatorioOSFiscalizacaoBean> retorno = new ArrayList<RelatorioOSFiscalizacaoBean>();
		
		RelatorioSituacaoEncontradaDetailBean detailBean = null;
		Collection colecaoDetailBean = null;
		if(colecaoOSReferidaRetornoTipo != null && !colecaoOSReferidaRetornoTipo.isEmpty()){
			Iterator iterOSReferidaRetornoTipo = colecaoOSReferidaRetornoTipo.iterator();
			colecaoDetailBean = new ArrayList();
			while (iterOSReferidaRetornoTipo.hasNext()) {
				OsReferidaRetornoTipo oSReferidaRetornoTipo = (OsReferidaRetornoTipo) iterOSReferidaRetornoTipo.next();
				detailBean = new RelatorioSituacaoEncontradaDetailBean(oSReferidaRetornoTipo.getDescricao());
				colecaoDetailBean.add(detailBean);
			}
		}
		
		Iterator iterHelper = colecaoEmitirContaHelper.iterator();
		while (iterHelper.hasNext()) {
			RelatorioOSFiscalizacaoHelper helper = (RelatorioOSFiscalizacaoHelper) iterHelper.next();
			
			RelatorioOSFiscalizacaoHelper helper2 = null;
			if(iterHelper.hasNext()){
				helper2 = (RelatorioOSFiscalizacaoHelper) iterHelper.next();
			}

			RelatorioOSFiscalizacaoBean bean = new RelatorioOSFiscalizacaoBean(helper,helper2,colecaoDetailBean);
			retorno.add(bean);
		}
		
		return retorno;
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 * 
	 */
	public Object executar() throws TarefaException {
		
		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------
		
		Fachada fachada = Fachada.getInstancia();

		Collection colecaoOSFiscalizacaoGeradas = (Collection) getParametro("colecaoOSFiscalizacaoGeradas");
		Integer idGrupoCobranca = (Integer)getParametro("idGrupoCobranca");
		Collection colecaoOSReferidaRetornoTipo = (Collection) getParametro("colecaoOSReferidaRetornoTipo");
		
		Collection colecaoEmitirOSFiscalizacaoHelper = fachada.recuperaDadosOsFiscalizacao(
				colecaoOSFiscalizacaoGeradas,idGrupoCobranca);
	
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
//		String nomeEmpresa = (String) getParametro("nomeEmpresa");
//		
//		String cnpjEmpresa = "";
//		if (sistemaParametro.getCnpjEmpresa() != null) {
//			cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
//		}
		
//		String idUsuario = "";
//		Usuario usuario = this.getUsuario();
//		String nomeUsuario = "";
		
			
		
		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map<String, String> parametros = new HashMap();
		
//		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
//		parametros.put("imagemConta", sistemaParametro.getImagemConta());
//		parametros.put("nomeEmpresa",nomeEmpresa);
//		parametros.put("cnpjEmpresa", cnpjEmpresa);
//		parametros.put("idUsuario", idUsuario);
//		parametros.put("nomeUsuario", nomeUsuario);

		
		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("bairro");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("cep");
		
		
		Collection colecaoSistemaParametro = fachada.pesquisar(filtroSistemaParametro,SistemaParametro.class.getName());
		
		SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro.iterator().next();
		
		
		String nomeEmpresa = sistemaParametro.getNomeEmpresa();
		String nomeAbreviadoEmpresa = sistemaParametro.getNomeAbreviadoEmpresa();
		String enderecoEmpresa = sistemaParametro.getEnderecoFormatado();
		String cepEmpresa = sistemaParametro.getCep().getCepFormatado();
		String cnpjEmpresa = Util.formatarCnpj(sistemaParametro.getCnpjEmpresa());
		String telefoneGeral = Util.formatarTelefone(sistemaParametro.getNumeroTelefone());
		String numeroAtendimento =  sistemaParametro.getNumero0800Empresa();

		parametros.put("nomeEmpresa", nomeEmpresa);
		parametros.put("nomeAbreviadoEmpresa", nomeAbreviadoEmpresa);
		parametros.put("enderecoEmpresa", enderecoEmpresa);
		parametros.put("cepEmpresa", cepEmpresa);
		parametros.put("cnpjEmpresa", cnpjEmpresa);
		parametros.put("telefoneGeral", telefoneGeral);
		parametros.put("numeroAtendimento", numeroAtendimento);
		parametros.put("dataCorrente", Util.formatarData(new Date()));
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		
		
//		String empresa = "\n	  	"+nomeEmpresa +" - "+cnpjEmpresa;
		
		Collection dadosRelatorio = colecaoEmitirOSFiscalizacaoHelper;

		Collection<RelatorioOSFiscalizacaoBean> colecaoBean = 
			this.inicializarBeanRelatorio(dadosRelatorio,colecaoOSReferidaRetornoTipo);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) colecaoBean);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_OS_FISCALIZACAO, parametros, ds,
				tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_OS_FISCALIZACAO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;

	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		Integer qtdeOSFiscalizacaoGeradas = (Integer) getParametro("qtdeOSFiscalizacaoGeradas");
		
		return qtdeOSFiscalizacaoGeradas;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioOSFiscalizacao", this);
	}
	
}
