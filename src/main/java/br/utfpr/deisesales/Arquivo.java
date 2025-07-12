package br.utfpr.deisesales;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class Arquivo {
    private static final String ARQUIVO = File.separator + "arquivo.txt";
    private static final String ARQUIVO_LOCAL = "arquivo2.txt";

    public void lerArquivoTXT() {
        Path caminho = Path.of(ARQUIVO_LOCAL);
        List<String> conteudo = null;
        try {
            conteudo = Files.readAllLines(caminho);

            // imprime o conteúdo do arquivo
            conteudo.stream().forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void escreverArquivoVariasLinhas() {
        final Path path = Paths.get(ARQUIVO_LOCAL); // retorna o caminho do arquivo em Path

        // Cria uma lista de strings, onde cada string é uma linha
        List<String> linhas = Arrays.asList(
                "escrevendo de novooooooo",
                "hehehehehe",
                "hahahahaha",
                "deu certo"
        );

        try {
            // StandardOpenOption.APPEND vai adicionar as linhas no final do arquivo
            Files.write(path, linhas, StandardOpenOption.APPEND);
            System.out.println("Arquivo escrito novamente com sucesso");

        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void escreverArquivoTXT() {
        final Path path = Paths.get(ARQUIVO_LOCAL); // retorna o caminho do arquivo em Path
        String separadorLinha = System.lineSeparator(); // Obtém o separador de linha do SO da maquina

        // StandardOpenOption é uma enumeração que define as opções de abertura do arquivo
        // CREATE é para criar o arquivo se ele não existir
        // WRITE é para escrever no arquivo
        // APPEND é para adicionar o conteúdo no final do arquivo
        // TRUNCATE_EXISTING é para deletar o arquivo se ele já existir
        // se nao passar parametro usa como padrao CREATE, WRITE e TRUNCATE_EXISTING
        try {
            Files.writeString(path, "aprendiiiiiii" + separadorLinha);
            Files.writeString(path, "estou criando" + separadorLinha, StandardOpenOption.APPEND);
            Files.writeString(path, "estou escrevendo" + separadorLinha, StandardOpenOption.APPEND);
            Files.writeString(path, "estou lendo arquivos" + separadorLinha, StandardOpenOption.APPEND);

        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }

        System.out.println("Arquivo escrito com sucesso");
    }



    public void criarArquivo() {

        // System.getProperty("user.home") puxa o diretório raiz do SO do usuário
        // e cria um caminho para o arquivo: /home/deise/arquivo.txt
        Path caminho = Path.of(System.getProperty("user.home") + ARQUIVO);

        try {
            Files.createFile(caminho);
        } catch (IOException e) {
            System.out.println("Erro ao criar o arquivo: " + e.getMessage());
        }
    }

    public void criarNovoArquivo() {
        // Cria um arquivo no diretório atual do projeto
        // caminho: ./praticando-nio-arquivos/arquivo.txt
        Path caminho = Path.of(ARQUIVO_LOCAL);

        File arquivo = new File(caminho.toAbsolutePath().toString());
        boolean existeArquivo;

        try {
            existeArquivo = arquivo.createNewFile();
            System.out.println("Novo arquivo criado: " + existeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao criar novo arquivo: " + e.getMessage());
        }
    }
}
