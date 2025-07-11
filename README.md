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

- `exists()`
- `isFile()`
- `isDirectory()`
- `getName()`
- `getPath()`
- `delete()`
- `list()`
- `mkdir()`
- `length()`
- `canRead()`, `canWrite()`, `canExecute()`

---

## 🧭 Interface `Path` e Classe `Paths`

### Classe `Paths`

Classe final com métodos utilitários para criar objetos `Path`:

```java
Path path = Paths.get("caminho/do/arquivo.txt");
```

### Métodos úteis da interface `Path`

- `toString()`
- `getFileName()`
- `getParent()`
- `getRoot()`
- `resolve(String other)`
- `relativize(Path other)`
- `normalize()`
- `toAbsolutePath()`
- `toRealPath()`
- `startsWith(Path other)`
- `endsWith(Path other)`
- `subpath(int beginIndex, int endIndex)`
- `compareTo(Path other)`
- `equals(Object other)`

---

## 📂 Classe `Files`

Classe final muito útil para manipulação de arquivos e diretórios:

- `exists(Path)`
- `delete(Path)`
- `copy(Path, Path)`
- `move(Path, Path)`
- `readAllBytes(Path)`
- `readAllLines(Path)`
- `write(Path, byte[] ou List<String>)`
- `createFile(Path)`
- `createDirectory(Path)`
- `walk(Path)`
- `isDirectory(Path)` / `isRegularFile(Path)`
- `list(Path)`

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
