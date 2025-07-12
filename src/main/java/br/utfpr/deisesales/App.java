package br.utfpr.deisesales;

public class App {
    public static void main( String[] args ) {

        Arquivo arquivo = new Arquivo();
        arquivo.criarArquivo();
        arquivo.criarNovoArquivo();

        arquivo.lerArquivoTXT();
        arquivo.escreverArquivoTXT();
        arquivo.lerArquivoTXT();
        arquivo.escreverArquivoVariasLinhas();
        arquivo.lerArquivoTXT();
    }
}
