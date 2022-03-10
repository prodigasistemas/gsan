package gcom.arrecadacao;

import java.math.BigDecimal;

public class GeradorQrCodePIX {
	
	public static String chave = "testqrcode01@bb.com.br";
	
	
	public static String gerarStringQrCodeConta(BigDecimal valor, String nomeCliente, String cidade) {
		StringBuilder qrcode = new StringBuilder();
		
		qrcode.append("000201")
			.append("26580014br.gov.bcb.pix").append("0136").append(chave)
			.append("52040000")
			.append("5303986")
			.append("5802BR")
			.append("5913").append(nomeCliente)
			.append("6008").append(cidade)
			.append("62070503***")
			.append("63041D3D").append(cidade);
		return qrcode.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(GeradorQrCodePIX.gerarStringQrCodeConta(new BigDecimal(10), "Pamela Gatinho", "Belém"));
	}
}
