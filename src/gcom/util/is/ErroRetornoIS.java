package gcom.util.is;

import java.util.Properties;

import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.bean.ImovelNaoFaturadoRetornoIsDTO;
import gcom.util.IoUtil;

public class ErroRetornoIS {

	public final static String NOME_ARQUIVO_PROPRIEDADES = "/properties/mensagensErroRetornoIS.properties";
	
	public static String CSTP_NULL = "erro.is.cstp.null";
	
	public ErroRetornoIS() {
			
	}
 	
	public static Properties propriedades;
	
	public static ImovelNaoFaturadoRetornoIsDTO lancarErroSemLeituraConsumoTipo(Imovel imovel) {
		ImovelNaoFaturadoRetornoIsDTO retorno = new ImovelNaoFaturadoRetornoIsDTO();
		propriedades = IoUtil.carregaPropriedades("mensagensErroRetornoIS.properties"); 
		String erro = propriedades.getProperty(CSTP_NULL);
		retorno.setImovel(imovel);
		retorno.setDescricao(erro);
		
        return retorno;
	}
	
	
}
	
