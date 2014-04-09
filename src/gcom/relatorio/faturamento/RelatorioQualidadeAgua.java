package gcom.relatorio.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroQualidadeAgua;
import gcom.faturamento.QualidadeAgua;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de bairro manter de água
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class RelatorioQualidadeAgua extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioQualidadeAgua(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_BAIRRO_MANTER);
	}

	@Deprecated
	public RelatorioQualidadeAgua() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param bairros
	 *            Description of the Parameter
	 * @param bairroParametros
	 *            Description of the Parameter
	 * @return Descrição do retorno
	 * @exception RelatorioVazioException
	 *                Descrição da exceção
	 */
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroQualidadeAgua filtroQualidadeAgua = (FiltroQualidadeAgua) getParametro("filtroQualidadeAgua");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioQualidadeAguaBean relatorioBean = null;

		String[] ordenacao = new String[2];
		ordenacao[0] = " localidade.id ";
		ordenacao[1] = " setorComercial.codigo ";
		
		filtroQualidadeAgua.limparCamposOrderBy();
		
		filtroQualidadeAgua.setCampoOrderBy(ordenacao);
		filtroQualidadeAgua.setConsultaSemLimites(true);
		
		if(filtroQualidadeAgua.getColecaoCaminhosParaCarregamentoEntidades() == null ||
				filtroQualidadeAgua.getColecaoCaminhosParaCarregamentoEntidades().isEmpty()){
			
			filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("setorComercial.municipio");
			filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("fonteCaptacao");
			filtroQualidadeAgua.adicionarCaminhoParaCarregamentoEntidade("sistemaAbastecimento");
		}

		Collection colecaoQualidade = fachada.pesquisar(filtroQualidadeAgua, QualidadeAgua.class
				.getName());

		// se a coleção de parâmetros da analise não for vazia
		if (colecaoQualidade != null && !colecaoQualidade.isEmpty()) {

			// coloca a coleção de parâmetros da analise no iterator
			Iterator qualidadeIterator = colecaoQualidade.iterator();

			// laço para criar a coleção de parâmetros da analise
			while (qualidadeIterator.hasNext()) {
				
				boolean aparecerSubRelatorio = false;
				
				QualidadeAgua qualidadeAgua = (QualidadeAgua) qualidadeIterator.next();

				String municipio = "";
				
				if (qualidadeAgua.getSetorComercial() != null && qualidadeAgua.getSetorComercial().getMunicipio() != null) {
					municipio = qualidadeAgua.getSetorComercial().getMunicipio().getNome();
				}
				
				String sistemaAbastecimento = "";
				
				if (qualidadeAgua.getSistemaAbastecimento() != null){
					
					if (qualidadeAgua.getSistemaAbastecimento().getDescricaoAbreviada() != null
							&& !qualidadeAgua.getSistemaAbastecimento().getDescricaoAbreviada().equals("")){
						
						sistemaAbastecimento = qualidadeAgua.getSistemaAbastecimento().getDescricaoAbreviada();
						
					}else{
						sistemaAbastecimento = qualidadeAgua.getSistemaAbastecimento().getDescricao();
					}

				}
				
				String fonteCaptacao = "";
				
				if(qualidadeAgua.getFonteCaptacao() != null){
					fonteCaptacao = qualidadeAgua.getFonteCaptacao().getDescricao();
				}
				
				String localidade ="";
				
				if( qualidadeAgua.getLocalidade() != null){
					localidade = qualidadeAgua.getLocalidade().getId() + "";
				}
				
				String setorComercial = "";
				
				if( qualidadeAgua.getSetorComercial() != null){
					setorComercial = qualidadeAgua.getSetorComercial().getCodigo() + "";
				}
				
				String numeroIndiceTurbidez = "0.00";
				
				if( qualidadeAgua.getNumeroIndiceTurbidez() != null ) {
					numeroIndiceTurbidez = qualidadeAgua.getNumeroIndiceTurbidez() + "";
				}
				
				String numeroCloroResidual = "0.00";
				
				if( qualidadeAgua.getNumeroCloroResidual() != null ) {
					numeroCloroResidual = qualidadeAgua.getNumeroCloroResidual() + "";
				}
				
				String numeroIndicePh = "0.00";
				
				if( qualidadeAgua.getNumeroIndicePh() != null ) {
					numeroIndicePh = qualidadeAgua.getNumeroIndicePh() + "";
				}
				
				String numeroIndiceCor= "0.00";
				
				if( qualidadeAgua.getNumeroIndiceCor() != null ) {
					numeroIndiceCor = qualidadeAgua.getNumeroIndiceCor() + "";
				}
				
				String numeroIndiceFluor = "0.00";
				
				if( qualidadeAgua.getNumeroIndiceFluor() != null ) {
					numeroIndiceFluor = qualidadeAgua.getNumeroIndiceFluor() + "";
				}
				
				String numeroIndiceFerro= "0.00";
				
				if( qualidadeAgua.getNumeroIndiceFerro() != null ) {
					numeroIndiceFerro = qualidadeAgua.getNumeroIndiceFerro() + "";
				}
				
				String numeroIndiceColiformesFecais = "0.00";
				
				if( qualidadeAgua.getNumeroIndiceColiformesFecais() != null ) {
					numeroIndiceColiformesFecais = qualidadeAgua.getNumeroIndiceColiformesFecais() + "";
				}
				
				String numeroIndiceColiformesTotais = "0.00";
				
				if( qualidadeAgua.getNumeroIndiceColiformesTotais() != null ) {
					numeroIndiceColiformesTotais = qualidadeAgua.getNumeroIndiceColiformesTotais() + "";
				}
				
				String numeroNitrato = "0.00";
				
				if( qualidadeAgua.getNumeroNitrato() != null ) {
					numeroNitrato = qualidadeAgua.getNumeroNitrato() + "";
				}
				
				String numeroIndiceAlcalinidade = "0.00";
				
				if ( qualidadeAgua.getNumeroIndiceAlcalinidade() != null){
					
					numeroIndiceAlcalinidade = qualidadeAgua.getNumeroIndiceAlcalinidade() + "";
					
				}
				
				//===================================================================================
				
				//turbidez
				String qtdTurbidezAnalisada = "0";
				String qtdTurbidezConforme = "0";
				String qtdTurbidezExigidas = "0";
				if(qualidadeAgua.getQuantidadeTurbidezAnalisadas() != null){
					qtdTurbidezAnalisada = qualidadeAgua.getQuantidadeTurbidezAnalisadas() + "";
					aparecerSubRelatorio = true;
				}
				if(qualidadeAgua.getQuantidadeTurbidezConforme() != null){
					qtdTurbidezConforme = qualidadeAgua.getQuantidadeTurbidezConforme() + "";
					aparecerSubRelatorio = true;
				}
				if(qualidadeAgua.getQuantidadeTurbidezExigidas() != null){
					qtdTurbidezExigidas = qualidadeAgua.getQuantidadeTurbidezExigidas() + "";
					aparecerSubRelatorio = true;
				}
				
				//Cor
				String qtdCorAnalisada = "0";
				String qtdCorConforme = "0";
				String qtdCorExigidas = "0";
				if(qualidadeAgua.getQuantidadeCorAnalisadas() != null){
					qtdCorAnalisada = qualidadeAgua.getQuantidadeCorAnalisadas() + "";
					aparecerSubRelatorio = true;
				}
				if(qualidadeAgua.getQuantidadeCorConforme() != null){
					qtdCorConforme = qualidadeAgua.getQuantidadeCorConforme() + "";
					aparecerSubRelatorio = true;
				}
				if(qualidadeAgua.getQuantidadeCorExigidas() != null){
					qtdCorExigidas = qualidadeAgua.getQuantidadeCorExigidas() + "";
					aparecerSubRelatorio = true;
				}
				
				//Cloro
				String qtdCloroAnalisada = "0";
				String qtdCloroConforme = "0";
				String qtdCloroExigidas = "0";
				if(qualidadeAgua.getQuantidadeCloroAnalisadas() != null){
					qtdCloroAnalisada = qualidadeAgua.getQuantidadeCloroAnalisadas() + "";
					aparecerSubRelatorio = true;
				}
				if(qualidadeAgua.getQuantidadeCloroConforme() != null){
					qtdCloroConforme = qualidadeAgua.getQuantidadeCloroConforme() + "";
					aparecerSubRelatorio = true;
				}
				if(qualidadeAgua.getQuantidadeCloroExigidas() != null){
					qtdCloroExigidas = qualidadeAgua.getQuantidadeCloroExigidas() + "";
					aparecerSubRelatorio = true;
				}
				
				//Fluor
				String qtdFluorAnalisada = "0";
				String qtdFluorConforme = "0";
				String qtdFluorExigidas = "0";
				if(qualidadeAgua.getQuantidadeFluorAnalisadas() != null){
					qtdFluorAnalisada = qualidadeAgua.getQuantidadeFluorAnalisadas() + "";
					aparecerSubRelatorio = true;
				}
				if(qualidadeAgua.getQuantidadeFluorConforme() != null){
					qtdFluorConforme = qualidadeAgua.getQuantidadeFluorConforme() + "";
					aparecerSubRelatorio = true;
				}
				if(qualidadeAgua.getQuantidadeFluorExigidas() != null){
					qtdFluorExigidas = qualidadeAgua.getQuantidadeFluorExigidas() + "";
					aparecerSubRelatorio = true;
				}
				
				//ColiformerTotais
				String qtdColiformerTotaisAnalisada = "0";
				String qtdColiformerTotaisConforme = "0";
				String qtdColiformerTotaisExigidas = "0";
				if(qualidadeAgua.getQuantidadeColiformesFecaisAnalisadas() != null){
					qtdColiformerTotaisAnalisada = qualidadeAgua.getQuantidadeColiformesTotaisAnalisadas() + "";
					aparecerSubRelatorio = true;
				}
				if(qualidadeAgua.getQuantidadeColiformesTotaisConforme() != null){
					qtdColiformerTotaisConforme = qualidadeAgua.getQuantidadeColiformesTotaisConforme() + "";
					aparecerSubRelatorio = true;
				}
				if(qualidadeAgua.getQuantidadeColiformesTotaisExigidas() != null){
					qtdColiformerTotaisExigidas = qualidadeAgua.getQuantidadeColiformesTotaisExigidas() + "";
					aparecerSubRelatorio = true;
				}
				
				//ColiformesFecais
				String qtdColiformesFecaisAnalisada = "0";
				String qtdColiformesFecaisConforme = "0";
				String qtdColiformesFecaisExigidas = "0";
				if(qualidadeAgua.getQuantidadeColiformesFecaisAnalisadas() != null){
					qtdColiformesFecaisAnalisada = qualidadeAgua.getQuantidadeColiformesFecaisAnalisadas() + "";
					aparecerSubRelatorio = true;
				}
				if(qualidadeAgua.getQuantidadeColiformesFecaisConforme() != null){
					qtdColiformesFecaisConforme = qualidadeAgua.getQuantidadeColiformesFecaisConforme() + "";
					aparecerSubRelatorio = true;
				}
				if(qualidadeAgua.getQuantidadeColiformesFecaisExigidas() != null){
					qtdColiformesFecaisExigidas = qualidadeAgua.getQuantidadeColiformesFecaisExigidas() + "";
					aparecerSubRelatorio = true;
				}
				
				//ColiformesTermotolerantes
				String qtdColiformesTermotolerantesAnalisada = "0";
				String qtdColiformesTermotolerantesConforme = "0";
				String qtdColiformesTermotolerantesExigidas = "0";
				if(qualidadeAgua.getQuantidadeColiformesTermotolerantesAnalisadas() != null){
					qtdColiformesTermotolerantesAnalisada = qualidadeAgua.getQuantidadeColiformesTermotolerantesAnalisadas() + "";
					aparecerSubRelatorio = true;
				}
				if(qualidadeAgua.getQuantidadeColiformesTermotolerantesConforme() != null){
					qtdColiformesTermotolerantesConforme = qualidadeAgua.getQuantidadeColiformesTermotolerantesConforme() + "";
					aparecerSubRelatorio = true;
				}
				if(qualidadeAgua.getQuantidadeColiformesTermotolerantesExigidas() != null){
					qtdColiformesTermotolerantesExigidas = qualidadeAgua.getQuantidadeColiformesTermotolerantesExigidas() + "";
					aparecerSubRelatorio = true;
				}
				
				String indiceColiformesTermotolerantes = "0.00";
				if(qualidadeAgua.getNumeroIndiceColiformesTermotolerantes() != null){
					indiceColiformesTermotolerantes = qualidadeAgua.getNumeroIndiceColiformesTermotolerantes() + "";
				}
				
				//Alcalinidade
				String qtdAlcalinidadeExigidas = "0";
				String qtdAlcalinidadeAnalisadas = "0";
				String qtdAlcalinidadeConforme = "0";
				
				if (qualidadeAgua.getQuantidadeAlcalinidadeAnalisadas() != null){
					qtdAlcalinidadeAnalisadas = qualidadeAgua.getQuantidadeAlcalinidadeAnalisadas() + "";
					aparecerSubRelatorio = true;
				}
				
				if (qualidadeAgua.getQuantidadeAlcalinidadeConforme() != null){
					qtdAlcalinidadeConforme = qualidadeAgua.getQuantidadeAlcalinidadeConforme() + "";
					aparecerSubRelatorio = true;
				}
				
				if (qualidadeAgua.getQuantidadeAlcalinidadeExigidas() != null){
					qtdAlcalinidadeExigidas = qualidadeAgua.getQuantidadeAlcalinidadeExigidas() + "";
					aparecerSubRelatorio = true;
				}
				
				relatorioBean = new RelatorioQualidadeAguaBean(
						municipio,
						Util.formatarAnoMesParaMesAno(qualidadeAgua.getAnoMesReferencia()),
						localidade,
						setorComercial,
						fonteCaptacao,
						numeroIndiceTurbidez,
						numeroCloroResidual,
						numeroIndicePh,
						numeroIndiceCor,
						numeroIndiceFluor,
						numeroIndiceFerro,
						numeroIndiceColiformesFecais,
						numeroIndiceColiformesTotais,
						numeroNitrato,
						qtdTurbidezExigidas,
						qtdTurbidezAnalisada,
						qtdTurbidezConforme,
						qtdCorExigidas,
						qtdCorAnalisada,
						qtdCorConforme,
						qtdCloroExigidas,
						qtdCloroAnalisada,
						qtdCloroConforme,
						qtdFluorExigidas,
						qtdFluorAnalisada,
						qtdFluorConforme,
						qtdColiformerTotaisExigidas,
						qtdColiformerTotaisAnalisada,
						qtdColiformerTotaisConforme,
						qtdColiformesFecaisExigidas,
						qtdColiformesFecaisAnalisada,
						qtdColiformesFecaisConforme,
						qtdColiformesTermotolerantesExigidas,
						qtdColiformesTermotolerantesAnalisada,
						qtdColiformesTermotolerantesConforme,
						indiceColiformesTermotolerantes,
						aparecerSubRelatorio,
						numeroIndiceAlcalinidade,
						qtdAlcalinidadeExigidas,
						qtdAlcalinidadeAnalisadas,
						qtdAlcalinidadeConforme,
						sistemaAbastecimento
						);

				// adiciona o bean a coleção
				relatorioBeans.add(relatorioBean);
			}
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		String nomeEmpresa = sistemaParametro.getNomeAbreviadoEmpresa();
		
		if (nomeEmpresa.equals("CAERN")) {
			retorno = gerarRelatorio(
					ConstantesRelatorios.RELATORIO_GERAR_QUALIDADE_AGUA_CAERN,
					parametros, ds, tipoFormatoRelatorio);
		} else {
			retorno = gerarRelatorio(
					ConstantesRelatorios.RELATORIO_GERAR_QUALIDADE_AGUA,
					parametros, ds, tipoFormatoRelatorio);
		}

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.QUALIDADE_AGUA,
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
		int retorno = 0;

		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
				(FiltroQualidadeAgua) getParametro("filtroQualidadeAgua"),
				QualidadeAgua.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioQualidadeAgua", this);

	}

}
