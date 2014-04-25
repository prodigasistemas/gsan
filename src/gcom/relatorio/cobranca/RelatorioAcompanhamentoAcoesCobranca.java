package gcom.relatorio.cobranca;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o Relatório de Débitos
 * 
 * @author Genival Barbosa
 * @date 24/08/2009
 * 
 */
public class RelatorioAcompanhamentoAcoesCobranca extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;

	public RelatorioAcompanhamentoAcoesCobranca(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_ACOES_COBRANCA);
	}

	@Deprecated
	public RelatorioAcompanhamentoAcoesCobranca() {
		super(null, "");
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		Fachada fachada = Fachada.getInstancia();
		
		Object obj = null;
		Object[] dados = null;
		byte[] retorno = null;
		
		
		String idCobrancaAcao = (String) getParametro("idCobrancaAcao");
		String dataInicial = (String) getParametro("dataInicial");
		String dataFinal = (String) getParametro("dataFinal");
		String chkEstado = (String) getParametro("chkEstado");
		String chkGerencia = (String) getParametro("chkGerencia");
		String idGerenciaRegional = (String) getParametro("idGerenciaRegional");
		String chkUnidade = (String) getParametro("chkUnidade");
		String idUnidadeNegocio = (String) getParametro("idUnidadeNegocio");
		String chkLocalidade = (String) getParametro("chkLocalidade");
		String idLocalidade = (String) getParametro("idLocalidade");
		String idEmpresa = (String) getParametro("idEmpresa");
		
		Integer tipoRelatorio = 0;
		
		RelatorioAcompanhamentoAcoesCobrancaHelper helper = new RelatorioAcompanhamentoAcoesCobrancaHelper(idCobrancaAcao,dataInicial,
				dataFinal, chkEstado, chkGerencia, idGerenciaRegional,
				chkUnidade, idUnidadeNegocio, chkLocalidade, 
				idLocalidade, idEmpresa);
		
		List colecaoAcaoCobranca = null;
		if(chkEstado != null && chkEstado.equals("on") && (chkGerencia == null || chkGerencia.equals(""))  
				&& idGerenciaRegional.equals("-1")
				&& (chkUnidade == null || chkUnidade.equals("")) 
				&& idUnidadeNegocio.equals("-1") 
				&& (chkLocalidade == null || chkLocalidade.equals(""))
				&& idLocalidade.equals("-1")){
			//relatorio 1
			tipoRelatorio = 1;						
			System.out.println("relatorio1");
						
		}else if(chkEstado != null && chkEstado.equals("on") && chkGerencia != null && chkGerencia.equals("on")
				&& idGerenciaRegional.equals("-1")
				&& (chkUnidade == null || chkUnidade.equals(""))  
				&& idUnidadeNegocio.equals("-1") 
				&& (chkLocalidade == null || chkLocalidade.equals(""))
				&& idLocalidade.equals("-1")){
			//relatorio 2
			tipoRelatorio = 2;
			System.out.println("relatorio2");
			
		}else if((chkEstado == null || chkEstado.equals(""))  
				&& chkGerencia != null && chkGerencia.equals("on") 
				&& !idGerenciaRegional.equals("-1")	
				&& (chkUnidade == null || chkUnidade.equals("")) 
				&& idUnidadeNegocio.equals("-1") 
				&& (chkLocalidade == null || chkLocalidade.equals(""))
				&& idLocalidade.equals("-1")){
			//relatorio 3
			tipoRelatorio = 3;
			System.out.println("relatorio3");
			
		}else if(chkEstado != null && chkEstado.equals("on") && chkGerencia != null && chkGerencia.equals("on") 
				&& idGerenciaRegional.equals("-1")	&& chkUnidade != null && chkUnidade.equals("on") 
				&& idUnidadeNegocio.equals("-1") 
				&& (chkLocalidade == null || chkLocalidade.equals(""))
				&& idLocalidade.equals("-1")){
			//relatorio 4
			tipoRelatorio = 4;
			System.out.println("relatorio4");
			
		}else if((chkEstado == null || chkEstado.equals("")) 
				&& (chkGerencia == null || chkGerencia.equals(""))  
				&& idGerenciaRegional.equals("-1")	&& chkUnidade != null && chkUnidade.equals("on") 
				&& !idUnidadeNegocio.equals("-1") 
				&& (chkLocalidade == null || chkLocalidade.equals(""))
				&& idLocalidade.equals("-1")){
			//relatorio 5
			tipoRelatorio = 5;
			System.out.println("relatorio5");
		
		}else if(chkEstado != null && chkEstado.equals("on") && chkGerencia != null && chkGerencia.equals("on") 
				&& idGerenciaRegional.equals("-1")	&& chkUnidade != null && chkUnidade.equals("on") 
				&& idUnidadeNegocio.equals("-1") && chkLocalidade != null && chkLocalidade.equals("on") 
				&& idLocalidade.equals("-1")){
			//relatorio 6
			tipoRelatorio = 6;
			System.out.println("relatorio6");
			
		}else if((chkEstado == null || chkEstado.equals("")) 
				&& (chkGerencia == null || chkGerencia.equals(""))  
				&& idGerenciaRegional.equals("-1")	
				&& (chkUnidade == null || chkUnidade.equals(""))  
				&& idUnidadeNegocio.equals("-1") && chkLocalidade != null && chkLocalidade.equals("on")
				&& !idLocalidade.equals("-1")){
			//relatorio 7
			tipoRelatorio = 7;
			System.out.println("relatorio7");
			
		}else if((chkEstado == null || chkEstado.equals("")) 
				&& chkGerencia != null && chkGerencia.equals("on")  
				&& !idGerenciaRegional.equals("-1")	&& chkUnidade != null && chkUnidade.equals("on")  
				&& idUnidadeNegocio.equals("-1") 
				&& (chkLocalidade == null || chkLocalidade.equals("")) 
				&& idLocalidade.equals("-1")){
			//relatorio 8
			tipoRelatorio = 8;
			System.out.println("realtorio8");
		
		}else if((chkEstado == null || chkEstado.equals("")) 
				&& chkGerencia != null && chkGerencia.equals("on")  
				&& !idGerenciaRegional.equals("-1")	&& chkUnidade != null && chkUnidade.equals("on")  
				&& idUnidadeNegocio.equals("-1") && chkLocalidade != null && chkLocalidade.equals("on")
				&& idLocalidade.equals("-1")){
			//relatorio 9
			tipoRelatorio = 9;
			System.out.println("relatorio9");
			
		}else if((chkEstado == null || chkEstado.equals("")) 
				&& (chkGerencia == null || chkGerencia.equals(""))   
				&& idGerenciaRegional.equals("-1")	&& chkUnidade != null && chkUnidade.equals("on")  
				&& !idUnidadeNegocio.equals("-1") && chkLocalidade != null && chkLocalidade.equals("on")
				&& idLocalidade.equals("-1")){
			//relatorio 10
			tipoRelatorio = 10;
			System.out.println("relatorio10");
		
		}else{			
			//colocar uma mensagem de erro avisando que as combinações não são adequadas para um relatorio
			throw new ActionServletException(
            "atencao.selecao.invalida");
		}
		
		colecaoAcaoCobranca = fachada.consultarColecaoAcaoCobranca(helper);
		
		Collection<RelatorioAcompanhamentoAcoesCobrancaBean> colecaoBean = new ArrayList<RelatorioAcompanhamentoAcoesCobrancaBean>();
		
		
		if(colecaoAcaoCobranca != null)
		{
		
			//	pra cada objeto obter a categoria
			
			String idLocalidadeAnterior;
			
			String idDocumentoBean = null;
			String idGerenciaBean = null;
			String nomeGerenciaBean = null;
			String idUnidadeBean = null;
			String nomeUnidadeBean = null;
			String idLocalidadeBean = null;
			String nomeLocalidadeBean = null;
			String idCobrancaAcaoSituacaoBean = null;
			String nomeCobrancaAcaoSituacaoBean = null;
			String quantidadeSituacaoBean = null;
			String valorSituacaoBean = null;
			String idEmpresaBean = "-1";
			String nomeEmpresaBean = "";
			String idCobrancaAcaoBean = null;
			String nomeCobrancaAcaoBean = null;
			int count = 0;
			RelatorioAcompanhamentoAcoesCobrancaBean bean = null;
			String ordemSituacoes[] = new String[6]; 
			int construtor = -1;
			

			for (int i = 0; i < colecaoAcaoCobranca.size(); i++) {
				obj = colecaoAcaoCobranca.get(i);
	
				if (obj instanceof Object[]) {
					dados = (Object[]) obj;
					
					idLocalidadeAnterior = idLocalidadeBean;
					
					if(!idEmpresa.trim().equals("-1"))
					{
						idGerenciaBean = dados[0].toString();              
						nomeGerenciaBean = dados[1].toString();            
						idUnidadeBean = dados[2].toString();               
						nomeUnidadeBean = dados[3].toString();		
						idLocalidadeBean = dados[4].toString();            
						nomeLocalidadeBean = dados[5].toString();          
						idCobrancaAcaoSituacaoBean = dados[6].toString();  
						nomeCobrancaAcaoSituacaoBean = dados[7].toString();
						quantidadeSituacaoBean = dados[8].toString();      
						valorSituacaoBean = dados[9].toString();          
						idEmpresaBean = dados[10].toString();              
						nomeEmpresaBean = dados[11].toString();            
						idCobrancaAcaoBean = dados[12].toString();         
						nomeCobrancaAcaoBean = dados[13].toString();
					}else {
						idGerenciaBean = dados[0].toString();              
						nomeGerenciaBean = dados[1].toString();            
						idUnidadeBean = dados[2].toString();               
						nomeUnidadeBean = dados[3].toString();		
						idLocalidadeBean = dados[4].toString();            
						nomeLocalidadeBean = dados[5].toString();          
						idCobrancaAcaoSituacaoBean = dados[6].toString();  
						nomeCobrancaAcaoSituacaoBean = dados[7].toString();
						quantidadeSituacaoBean = dados[8].toString();      
						valorSituacaoBean = dados[9].toString();                      
						idCobrancaAcaoBean = dados[10].toString();         
						nomeCobrancaAcaoBean = dados[11].toString();
					}
					
					if (idLocalidadeAnterior == null) {
						idLocalidadeAnterior = idLocalidadeBean;
						bean = 
							new RelatorioAcompanhamentoAcoesCobrancaBean(idCobrancaAcaoBean, nomeCobrancaAcaoBean,
									dataInicial, dataFinal, chkEstado, chkGerencia, chkUnidade, chkLocalidade, 
									idDocumentoBean, idGerenciaBean, nomeGerenciaBean, idUnidadeBean,
									nomeUnidadeBean,idLocalidadeBean, nomeLocalidadeBean, 
									idCobrancaAcaoSituacaoBean, nomeCobrancaAcaoSituacaoBean, quantidadeSituacaoBean, 
									valorSituacaoBean, idEmpresaBean, nomeEmpresaBean, tipoRelatorio);
					}
					
					if (idLocalidadeAnterior.equals(idLocalidadeBean)) {
						count++;
					} else {
						colecaoBean.add(bean);
						
						bean = 
							new RelatorioAcompanhamentoAcoesCobrancaBean(idCobrancaAcaoBean, nomeCobrancaAcaoBean,
									dataInicial, dataFinal, chkEstado, chkGerencia, chkUnidade, chkLocalidade, 
									idDocumentoBean, idGerenciaBean, nomeGerenciaBean, idUnidadeBean,
									nomeUnidadeBean,idLocalidadeBean, nomeLocalidadeBean, 
									idCobrancaAcaoSituacaoBean, nomeCobrancaAcaoSituacaoBean, quantidadeSituacaoBean, 
									valorSituacaoBean, idEmpresaBean, nomeEmpresaBean, tipoRelatorio);
						count = 1;
					}
					construtor = -1;					
					for(int l =0; l<6 ;l++){
						if(ordemSituacoes[l]!=null && ordemSituacoes[l].equals(idCobrancaAcaoSituacaoBean)){
							construtor = l;								
						}							
					}
					if (construtor==0){
						bean.setIdCobrancaAcaoSituacao1(idCobrancaAcaoSituacaoBean);
						bean.setNomeCobrancaAcaoSituacao1(nomeCobrancaAcaoSituacaoBean);
						bean.setQuantidadeSituacao1(quantidadeSituacaoBean);
						bean.setValorSituacao1(valorSituacaoBean);							
					}else if(construtor ==1){
						bean.setIdCobrancaAcaoSituacao2(idCobrancaAcaoSituacaoBean);
						bean.setNomeCobrancaAcaoSituacao2(nomeCobrancaAcaoSituacaoBean);
						bean.setQuantidadeSituacao2(quantidadeSituacaoBean);
						bean.setValorSituacao2(valorSituacaoBean);
					}else if(construtor ==2){
						bean.setIdCobrancaAcaoSituacao3(idCobrancaAcaoSituacaoBean);
						bean.setNomeCobrancaAcaoSituacao3(nomeCobrancaAcaoSituacaoBean);
						bean.setQuantidadeSituacao3(quantidadeSituacaoBean);
						bean.setValorSituacao3(valorSituacaoBean);
					}else if(construtor ==3){
						bean.setIdCobrancaAcaoSituacao4(idCobrancaAcaoSituacaoBean);
						bean.setNomeCobrancaAcaoSituacao4(nomeCobrancaAcaoSituacaoBean);
						bean.setQuantidadeSituacao4(quantidadeSituacaoBean);
						bean.setValorSituacao4(valorSituacaoBean);
					}else if(construtor ==4){
						bean.setIdCobrancaAcaoSituacao5(idCobrancaAcaoSituacaoBean);
						bean.setNomeCobrancaAcaoSituacao5(nomeCobrancaAcaoSituacaoBean);
						bean.setQuantidadeSituacao5(quantidadeSituacaoBean);
						bean.setValorSituacao5(valorSituacaoBean);
					}else if(construtor ==5){
						bean.setIdCobrancaAcaoSituacao6(idCobrancaAcaoSituacaoBean);
						bean.setNomeCobrancaAcaoSituacao6(nomeCobrancaAcaoSituacaoBean);
						bean.setQuantidadeSituacao6(quantidadeSituacaoBean);
						bean.setValorSituacao6(valorSituacaoBean);
					}else if ((count == 1 && ordemSituacoes[count-1] == null)){						
						bean.setIdCobrancaAcaoSituacao1(idCobrancaAcaoSituacaoBean);
						bean.setNomeCobrancaAcaoSituacao1(nomeCobrancaAcaoSituacaoBean);
						bean.setQuantidadeSituacao1(quantidadeSituacaoBean);
						bean.setValorSituacao1(valorSituacaoBean);
						ordemSituacoes[count-1]= idCobrancaAcaoSituacaoBean; 
					} else if ((count == 2 && ordemSituacoes[count-1] == null)){
						bean.setIdCobrancaAcaoSituacao2(idCobrancaAcaoSituacaoBean);
						bean.setNomeCobrancaAcaoSituacao2(nomeCobrancaAcaoSituacaoBean);
						bean.setQuantidadeSituacao2(quantidadeSituacaoBean);
						bean.setValorSituacao2(valorSituacaoBean);
						ordemSituacoes[count-1]= idCobrancaAcaoSituacaoBean;
					} else if ((count == 3 && ordemSituacoes[count-1] == null)){
						bean.setIdCobrancaAcaoSituacao3(idCobrancaAcaoSituacaoBean);
						bean.setNomeCobrancaAcaoSituacao3(nomeCobrancaAcaoSituacaoBean);
						bean.setQuantidadeSituacao3(quantidadeSituacaoBean);
						bean.setValorSituacao3(valorSituacaoBean);
						ordemSituacoes[count-1]= idCobrancaAcaoSituacaoBean;
					} else if ((count == 4 && ordemSituacoes[count-1] == null)){
						bean.setIdCobrancaAcaoSituacao4(idCobrancaAcaoSituacaoBean);
						bean.setNomeCobrancaAcaoSituacao4(nomeCobrancaAcaoSituacaoBean);
						bean.setQuantidadeSituacao4(quantidadeSituacaoBean);
						bean.setValorSituacao4(valorSituacaoBean);
						ordemSituacoes[count-1]= idCobrancaAcaoSituacaoBean;
					} else if ((count == 5 && ordemSituacoes[count-1] == null)){
						bean.setIdCobrancaAcaoSituacao5(idCobrancaAcaoSituacaoBean);
						bean.setNomeCobrancaAcaoSituacao5(nomeCobrancaAcaoSituacaoBean);
						bean.setQuantidadeSituacao5(quantidadeSituacaoBean);
						bean.setValorSituacao5(valorSituacaoBean);
						ordemSituacoes[count-1]= idCobrancaAcaoSituacaoBean;
					} else if ((count == 6 && ordemSituacoes[count-1] == null)){
						bean.setIdCobrancaAcaoSituacao6(idCobrancaAcaoSituacaoBean);
						bean.setNomeCobrancaAcaoSituacao6(nomeCobrancaAcaoSituacaoBean);
						bean.setQuantidadeSituacao6(quantidadeSituacaoBean);
						bean.setValorSituacao6(valorSituacaoBean);
						ordemSituacoes[count-1]= idCobrancaAcaoSituacaoBean;
						
					}
					else {
						construtor = -1;
						for(int j =0; j<6 ;j++){
							if(ordemSituacoes[j]!=null && ordemSituacoes[j].equals(idCobrancaAcaoSituacaoBean)){
								construtor = j;								
							}							
						}
						if (construtor==0){
							bean.setIdCobrancaAcaoSituacao1(idCobrancaAcaoSituacaoBean);
							bean.setNomeCobrancaAcaoSituacao1(nomeCobrancaAcaoSituacaoBean);
							bean.setQuantidadeSituacao1(quantidadeSituacaoBean);
							bean.setValorSituacao1(valorSituacaoBean);							
						}else if(construtor ==1){
							bean.setIdCobrancaAcaoSituacao2(idCobrancaAcaoSituacaoBean);
							bean.setNomeCobrancaAcaoSituacao2(nomeCobrancaAcaoSituacaoBean);
							bean.setQuantidadeSituacao2(quantidadeSituacaoBean);
							bean.setValorSituacao2(valorSituacaoBean);
						}else if(construtor ==2){
							bean.setIdCobrancaAcaoSituacao3(idCobrancaAcaoSituacaoBean);
							bean.setNomeCobrancaAcaoSituacao3(nomeCobrancaAcaoSituacaoBean);
							bean.setQuantidadeSituacao3(quantidadeSituacaoBean);
							bean.setValorSituacao3(valorSituacaoBean);
						}else if(construtor ==3){
							bean.setIdCobrancaAcaoSituacao4(idCobrancaAcaoSituacaoBean);
							bean.setNomeCobrancaAcaoSituacao4(nomeCobrancaAcaoSituacaoBean);
							bean.setQuantidadeSituacao4(quantidadeSituacaoBean);
							bean.setValorSituacao4(valorSituacaoBean);
						}else if(construtor ==4){
							bean.setIdCobrancaAcaoSituacao5(idCobrancaAcaoSituacaoBean);
							bean.setNomeCobrancaAcaoSituacao5(nomeCobrancaAcaoSituacaoBean);
							bean.setQuantidadeSituacao5(quantidadeSituacaoBean);
							bean.setValorSituacao5(valorSituacaoBean);
						}else if(construtor ==5){
							bean.setIdCobrancaAcaoSituacao6(idCobrancaAcaoSituacaoBean);
							bean.setNomeCobrancaAcaoSituacao6(nomeCobrancaAcaoSituacaoBean);
							bean.setQuantidadeSituacao6(quantidadeSituacaoBean);
							bean.setValorSituacao6(valorSituacaoBean);
						}
					}
				}
				
			}					
		
			if (bean != null) {
				colecaoBean.add(bean);
			}else {
				throw new ActionServletException(
	            "atencao.pesquisa.nenhumresultado");
			}
			
			int tipoFormatoRelatorio = (Integer) getParametro("tipoRelatorio");
			
			//Parâmetros do relatório
			Map parametros = new HashMap();
			
			
			//Recupera o AnoMesFaturamento de Sistema Parametro
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			
			parametros.put("imagem", sistemaParametro.getImagemRelatorio());
	
			RelatorioDataSource ds = new RelatorioDataSource((List) colecaoBean);
	
			// exporta o relatório em pdf e retorna o array de bytes
			retorno = gerarRelatorio(
					ConstantesRelatorios.RELATORIO_ACOMPANHAMENTO_ACOES_COBRANCA,
					parametros, ds, tipoFormatoRelatorio);
		}else {
			throw new ActionServletException(
            "atencao.pesquisa.nenhumresultado");
		}
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_ACOMPANHAMENTO_ACOES_COBRANCA, 0);
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void agendarTarefaBatch() {
		// TODO Auto-generated method stub

	}
}
