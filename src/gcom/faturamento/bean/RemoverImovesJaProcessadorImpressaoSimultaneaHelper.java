package gcom.faturamento.bean;

import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class RemoverImovesJaProcessadorImpressaoSimultaneaHelper {
	
	public class DadosImovelRemoverImovesJaProcessadorImpressaoSimultanea{
		private Integer idImovel;
		
		private Short indicadorImovelImpresso;
		
		private boolean medidoAgua;
		private boolean medidoPoco;
		
		private Integer leituraAgua;
		private Integer anormalidadeAgua;
		
		private Integer leituraPoco;
		private Integer anormalidadePoco;		
		
		private Short indicadorEmissaoConta;
		
		private String[] linhas;
		
		public String[] getLinhas() {
			return linhas;
		}

		public DadosImovelRemoverImovesJaProcessadorImpressaoSimultanea( Object[] objects ){
			
			String[] linhas = new String[ objects.length ];
			int i = 0;
			
			for (Object obj : objects) {
				
				String linha = (String) obj;
				linhas[i] = linha;
				++i;
				
				// Tipo da linha
				Integer tipoLinha = Integer.parseInt( linha.substring( 0, 1 ) );
				
				if ( tipoLinha.intValue() == 1 ){
					// Matricula do Imovel
					Integer idImovel = Integer.parseInt( linha.substring( 1, 10 ) );
				
					this.idImovel = idImovel;
					
					Short medicaoTipo = Short.parseShort( linha.substring( 10, 11 ) );
					
					if ( medicaoTipo.intValue() == MedicaoTipo.LIGACAO_AGUA ){
						this.medidoAgua = true;
						
						String strLeituraAgua = linha.substring( 37, 44 ).trim();
        				String strAnormalidadeAgua = linha.substring( 44, 45 ).trim();
        				String strIndicadorEmissaoConta = linha.substring( 107, 108 ).trim();        				
        				
        				if ( !strLeituraAgua.equals( "" ) ){
        					this.leituraAgua = Util.converterStringParaInteger( strLeituraAgua );	
        				}
        				
        				if ( !strAnormalidadeAgua.equals( "0" ) ){
        					this.anormalidadeAgua = Util.converterStringParaInteger( strAnormalidadeAgua );	
        				}    
        				
        				if ( !strIndicadorEmissaoConta.equals( "" ) ){
        					this.indicadorEmissaoConta = Short.parseShort( strIndicadorEmissaoConta );	
        				} else {
        					this.indicadorEmissaoConta = 2;
        				}
					} else if ( medicaoTipo.intValue() == MedicaoTipo.POCO ){
						this.medidoPoco = true;
						
						String strLeituraPoco = linha.substring( 37, 44 ).trim();
        				String strAnormalidadePoco = linha.substring( 44, 45 ).trim();
        				String strIndicadorEmissaoConta = linha.substring( 107, 108 ).trim();
        				
        				if ( !strLeituraPoco.equals( "" ) ){
        					this.leituraPoco = Util.converterStringParaInteger( strLeituraPoco );	
        				}
        				
        				if ( !strAnormalidadePoco.equals( "0" ) ){
        					this.anormalidadePoco = Util.converterStringParaInteger( strAnormalidadePoco );	
        				}
        				
        				if ( !strIndicadorEmissaoConta.equals( "" ) ){
        					this.indicadorEmissaoConta = Short.parseShort( strIndicadorEmissaoConta );	
        				} else {
        					this.indicadorEmissaoConta = 2;
        				}        				
					}
				}
			}
			
			this.linhas = linhas;
		}

		public Integer getAnormalidadeAgua() {
			return anormalidadeAgua;
		}

		public Integer getAnormalidadePoco() {
			return anormalidadePoco;
		}

		public Integer getIdImovel() {
			return idImovel;
		}

		public Short getIndicadorEmissaoConta() {
			return indicadorEmissaoConta;
		}

		public Short getIndicadorImovelImpresso() {
			return indicadorImovelImpresso;
		}

		public Integer getLeituraAgua() {
			return leituraAgua;
		}

		public Integer getLeituraPoco() {
			return leituraPoco;
		}

		public boolean isMedidoAgua() {
			return medidoAgua;
		}

		public boolean isMedidoPoco() {
			return medidoPoco;
		}
	}
	
	private Collection<DadosImovelRemoverImovesJaProcessadorImpressaoSimultanea> colDadosFormatados = new ArrayList();
	
	private StringBuffer registrosRotaMarcacao = new StringBuffer();
	
	public Collection<DadosImovelRemoverImovesJaProcessadorImpressaoSimultanea> getColDadosFormatados() {
		return colDadosFormatados;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RemoverImovesJaProcessadorImpressaoSimultaneaHelper( BufferedReader buffer ) throws IOException{
		
		String linha = null;
		Integer idImovelAnterior = null;
		Collection colLinhas = new ArrayList();
		
		while( ( linha = buffer.readLine() ) != null ){
			
			if ( linha.charAt( 0 ) == '5' ){
				registrosRotaMarcacao.append( linha + "\n" );
			} else {			
				Integer idImovel = Integer.parseInt( linha.substring( 1, 10 ) );
			
				if ( idImovelAnterior == null ){
					idImovelAnterior = idImovel;
				}
			
				if ( idImovel.intValue() == idImovelAnterior.intValue() ){
					colLinhas.add( linha );
				} else {
					DadosImovelRemoverImovesJaProcessadorImpressaoSimultanea dados = new DadosImovelRemoverImovesJaProcessadorImpressaoSimultanea( colLinhas.toArray() );
					this.colDadosFormatados.add( dados );
					idImovelAnterior = idImovel;
					colLinhas = new ArrayList();
					colLinhas.add( linha );
				}
			}
		}
		
		// Adicionamos o ultimo
		DadosImovelRemoverImovesJaProcessadorImpressaoSimultanea dados = new DadosImovelRemoverImovesJaProcessadorImpressaoSimultanea( colLinhas.toArray() );
		this.colDadosFormatados.add( dados );
	}

	public StringBuffer getRegistrosRotaMarcacao() {
		return registrosRotaMarcacao;
	}

	public void setRegistrosRotaMarcacao(StringBuffer registrosRotaMarcacao) {
		this.registrosRotaMarcacao = registrosRotaMarcacao;
	}

}
