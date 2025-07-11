# Manipulação de Arquivos e Diretórios em Java

Este repositório demonstra como manipular arquivos e diretórios em Java utilizando as APIs `java.nio` e `java.io`, disponíveis a partir do JDK 7+.

---

## 📁 File.separator

A vantagem de usar `File.separator` é garantir a portabilidade entre sistemas operacionais:

```java
public static final String ROOT = File.separator + "home" + File.separator + "deise" + File.separator + "Downloads" + File.separator + "pasta_teste";
```

**Explicação**:  
A classe `File` possui o atributo `File.separator`, que adapta automaticamente o separador de diretórios conforme o sistema operacional.

```java
private static final FileSystem FS = DefaultFileSystem.getFileSystem();
```

---

## ⚙️ try-with-resources no NIO

Com `java.nio`, não é necessário se preocupar com o fechamento manual de arquivos. O `try` comum já gerencia o fechamento dos recursos graças à interface `AutoCloseable`.

![Referência](https://github.com/deisesalless/java-nio-arquivos/blob/main/nio_in_java.png)

---

## 📦 Classe `File`

A classe `File` implementa diversas interfaces e fornece métodos úteis:

- `exists()` - Verifica se o arquivo ou diretório existe.
- `isFile()` - Verifica se é um arquivo.
- `isDirectory()` - Verifica se é um diretório.
- `getName()` - Retorna o nome do arquivo ou diretório.
- `getPath()` - Retorna o caminho do arquivo ou diretório em formato string.
- `delete()` - Deleta o arquivo ou diretório.
- `list()` - Retorna um array com os nomes de arquivos e diretórios no diretório.
- `mkdir()` - Cria um diretório.
- `length()` - Retorna o tamanho do arquivo em bytes.
- `canRead()`, `canWrite()`, `canExecute()` - Verifica permissões de leitura, escrita e execução, respectivamente.

---

## 🧭 Interface `Path` e Classe `Paths`

### Classe `Paths`

Classe final com métodos utilitários para criar objetos `Path`:

```java
Path path = Paths.get("caminho/do/arquivo.txt");
```

### Métodos úteis da interface `Path`

- `toString()` – Retorna o caminho como String.
- `getFileName()` – Retorna o nome do arquivo.
- `getParent()` – Retorna o caminho pai.
- `getRoot()` – Retorna o root do caminho (ex: / (linux) ou C:\ (windows)).
- `resolve(String other)` – Anexa outro caminho.
- `relativize(Path other)` – Calcula o caminho relativo entre dois Path.
- `normalize()` – Remove . e .. do caminho.
- `toAbsolutePath()` – Converte para caminho absoluto.
- `toRealPath()` – Retorna o caminho real (resolvendo links simbólicos).
- `startsWith(Path other)`– Verifica início.
- `endsWith(Path other)` – Verifica fim.
- `subpath(int beginIndex, int endIndex)`  – Retorna parte do caminho.
- `compareTo(Path other)` – Compara dois caminhos.
- `equals(Object other)` – Compara se são iguais.

---

## 📂 Classe `Files`

Classe final muito útil para manipulação de arquivos e diretórios:

- `exists(Path)` – Verifica se o arquivo existe.
- `delete(Path)` – Deleta arquivo ou diretório.
- `copy(Path, Path)` – Copia arquivos.
- `move(Path, Path)` – Move ou renomeia.
- `readAllBytes(Path)` – Lê todo o conteúdo como byte[].
- `readAllLines(Path)` – Lê todas as linhas como List<String>.
- `write(Path, byte[] ou List<String>)` – Escreve conteúdo no arquivo.
- `createFile(Path)` – Cria um novo arquivo.
- `createDirectory(Path)` – Cria diretório.
- `walk(Path)` – Percorre diretórios recursivamente (stream).
- `isDirectory(Path)` / `isRegularFile(Path)` – Verificações de tipo.
- `list(Path)` – Lista arquivos de um diretório (não recursivo).

---

## 🧪 Exemplo Prático

```java
public class UsingNIO2 {
    private static final String MY_FILE = System.getProperty("user.home") + "/Downloads/file-using-nio2.txt";

    public UsingNIO2() {
        try {
            readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFile() throws IOException {
        final Path path = Paths.get(MY_FILE);

        // StandardOpenOption define como o arquivo será aberto:
        // CREATE: cria se não existir
        // WRITE: escreve
        // APPEND: adiciona no final
        // TRUNCATE_EXISTING: apaga o conteúdo anterior
        Files.writeString(path, "aprendiiiiiii a criar e escrever arquivos");
        System.out.println("Arquivo escrito com sucesso");
    }

    private void readFile() throws IOException {
        final Path path = Paths.get(MY_FILE);
        List<String> arquivo = Files.readAllLines(path);
        arquivo.forEach(System.out::println);
    }

    public static void main(String[] args) {
        new UsingNIO2();
    }
}
```

---

## ✅ Requisitos

- Java 7 ou superior
- JDK configurado no JAVA_HOME

---

## 🧑‍💻 Autora

[Deise Sales](https://github.com/deisesalless)

---
