# Guia Completo para Manipulação de Arquivos e Diretórios em Java

Este guia explora as funcionalidades para manipular arquivos e diretórios em Java, utilizando principalmente as modernas APIs `java.nio` e, em menor grau, `java.io`, ambas disponíveis a partir do **JDK 7+**. Aprenderemos sobre portabilidade, gerenciamento de recursos e as classes fundamentais para interagir com o sistema de arquivos.

<br>

## ⚙️ Como é a herança das classes

![Referência](https://github.com/deisesalless/java-nio-arquivos/blob/main/nio_in_java.png)

<br><br>

## 🌎 Portabilidade com `File.separator`

Ao lidar com caminhos de arquivo, é crucial garantir que seu código funcione em diferentes sistemas operacionais. É aí que entra o **`File.separator`**.

```java
public static final String ROOT = File.separator + "home" + File.separator + "pc-poderoso-chefao" + File.separator + "Downloads" + File.separator + "pasta_teste";
```

<br>

## ♻️ Gerenciamento de Recursos com try-with-resources

Com as APIs de `java.nio`, a preocupação em fechar manualmente os arquivos é coisa do passado. Graças à interface `AutoCloseable`, você pode usar o bloco `try-with-resources` para garantir que os recursos de E/S sejam automaticamente liberados quando não forem mais necessários.

```java
// Exemplo conceitual (não parte do seu código, mas ilustra o uso)
try (InputStream is = Files.newInputStream(path);
     BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
    // Leitura do arquivo
} catch (IOException e) {
    e.printStackTrace();
}
```

Isso torna seu código mais limpo e menos propenso a vazamentos de recursos.

<br>

## 🗄️ A Classe `File`

A classe `File` (do pacote `java.io`) é uma das classes mais antigas para manipulação de arquivos e diretórios. Embora as APIs `java.nio` sejam geralmente preferidas para novas implementações, `File` ainda oferece métodos úteis para operações básicas:
    
- `exists()`: Verifica se o arquivo ou diretório existe.
- `isFile()`: Confere se o caminho aponta para um arquivo.
- `isDirectory()`: Confere se o caminho aponta para um diretório.
- `getName()`: Retorna o nome do arquivo ou diretório.
- `getPath()`: Retorna o caminho completo como uma string.
- `delete()`: Deleta o arquivo ou diretório (se vazio).
- `list()`: Retorna um array de strings com os nomes dos itens dentro de um diretório.
- `mkdir()`: Cria um único diretório.
- `length()`: Retorna o tamanho do arquivo em bytes.
- `canRead()`, `canWrite()`, `canExecute()`: Verificam as permissões de leitura, escrita e execução, respectivamente.

<br>

## 🗺️ `Path` e `Paths` para Navegação de Caminhos

As APIs `java.nio.file` introduziram a interface `Path` e a classe utilitária `Paths`, que oferecem uma forma mais robusta e eficiente de lidar com caminhos no sistema de arquivos.

<br>

### Classe `Paths`

É uma classe `final` que fornece métodos estáticos para criar instâncias de Path de forma conveniente:

```java
Path path = Paths.get("caminho/do/arquivo.txt");
Path absolutePath = Paths.get("/home", "usuario", "documento.pdf");
```

<br>

### Métodos Úteis da Interface `Path`

A interface `Path` oferece métodos poderosos para manipular e inspecionar caminhos:

- `toString()`: Retorna o caminho como uma String.
- `getFileName()`: Retorna o nome do arquivo ou diretório no final do caminho.
- `getParent()`: Retorna o caminho para o diretório pai.
- `getRoot()`: Retorna o componente raiz do caminho (ex: / no Linux, C:\ no Windows).
- `resolve(Path other)` / `resolve(String other)`: Anexa um caminho adicional ao caminho atual.
- `relativize(Path other)`: Calcula o caminho relativo entre dois objetos Path.
- `normalize()`: Remove elementos redundantes do caminho (como . ou ..).
- `toAbsolutePath()`: Converte o caminho para sua representação absoluta.
- `toRealPath(LinkOption... options)`: Retorna o caminho real, resolvendo quaisquer links simbólicos.
- `startsWith(Path other)`: Verifica se o caminho começa com o Path especificado.
- `endsWith(Path other)`: Verifica se o caminho termina com o Path especificado.
- `subpath(int beginIndex, int endIndex)`: Retorna uma porção do caminho.
- `compareTo(Path other)`: Compara dois caminhos.
- `equals(Object other)`: Verifica se dois caminhos são iguais.

<br><br>

## 📁 A Classe `Files` para Operações de E/S

A classe `Files` é uma classe utilitária `final` (do pacote `java.nio.file`) que oferece uma vasta gama de métodos estáticos para operações eficientes em arquivos e diretórios, sendo a preferida para a maioria das operações modernas de E/S.

- `exists(Path)`: Verifica a existência de um arquivo ou diretório.
- `delete(Path)`: Deleta um arquivo ou diretório (se vazio).
- `copy(Path source, Path target, CopyOption... options)`: Copia um arquivo de um local para outro.
- `move(Path source, Path target, CopyOption... options)`: Move ou renomeia um arquivo ou diretório.
- `readAllBytes(Path)`: Lê todo o conteúdo de um arquivo como um array de bytes.
- `readAllLines(Path, Charset cs)`: Lê todas as linhas de um arquivo em uma List<String>.
- `write(Path, byte[] / Iterable<? extends CharSequence>, OpenOption...)`: Escreve o conteúdo (bytes ou linhas) em um arquivo.
- `createFile(Path, FileAttribute<?>... attrs)`: Cria um novo arquivo vazio.
- `createDirectory(Path, FileAttribute<?>... attrs)`: Cria um novo diretório.
- `walk(Path start, FileVisitOption... options)`: Percorre uma árvore de diretórios recursivamente, retornando um Stream de Paths.
- `isDirectory(Path)` / `isRegularFile(Path)`: Verificam o tipo de um caminho.
- `list(Path dir)`: Retorna um Stream de Paths para os itens em um diretório (não recursivo).

<br><br>

## 🚀 Métodos Comuns de Leitura e Escrita

Para ilustrar as operações de E/S mais comuns, vamos descrever alguns métodos essenciais.

### Método para Ler Arquivo TXT

Este método tem como objetivo ler o conteúdo de um arquivo texto. Ele tipicamente utiliza a interface `Path` e a classe `Files` para operações de E/S eficientes e seguras.

### Método para Escrever em Arquivo TXT

Demonstra como escrever linhas em um arquivo. É possível escrever linha a linha, e o uso de `System.lineSeparator()` é fundamental. Isso garante que o código se adapte ao separador de linha (`\n` no Linux/macOS, `\r\n` no Windows) de acordo com o sistema operacional, proporcionando maior portabilidade e evitando problemas de quebra de linha.

### Método para Escrever Várias Linhas em Arquivo

Este método exemplifica uma forma mais eficiente e limpa de escrever múltiplos conteúdos em um arquivo. Ao organizar tudo o que você deseja escrever em uma `List<String>` e usar um método apropriado da classe `Files` (como `Files.write()`), o código se torna mais objetivo, evitando repetições de operações de append linha a linha e melhorando a performance.

### Método para Criar Arquivo

Mostra como criar um arquivo utilizando `System.getProperty("user.home")` para se adaptar ao diretório home do usuário, independentemente do sistema operacional.

Importante: Ao criar arquivos fora do diretório `root` (raiz), é obrigatório que o caminho completo do diretório pai já exista no sistema operacional. Caso contrário, ocorrerá uma exceção.

- **Exemplo (cria arquivo no diretório home):**
```java
// Cria o arquivo no diretório home do usuário. Pode exigir permissões específicas,
// especialmente em sistemas Linux onde o diretório home pode ter restrições.

Path filePath = Paths.get(System.getProperty("user.home") + ARQUIVO_NOME);

```

- **Exemplo (cria arquivo em subpasta):**
```java
// Cria o arquivo dentro da pasta 'Teste' que está em 'Downloads' no diretório home.
// Garante compatibilidade com o SO ao usar File.separator.

Path filePath = Paths.get(System.getProperty("user.home") + File.separator + "Downloads" + File.separator + "Teste" + ARQUIVO_NOME);

// Certifique-se de que a pasta 'Teste' exista ou crie-a antes.

Files.createDirectories(filePath.getParent());
```

### Método para Criar Novo Arquivo (Caminho Fixo)

Este método demonstra como você pode passar um caminho direto e explícito para um objeto `Path` (`Path.of(ARQUIVO_LOCAL)`). Isso é particularmente útil em cenários onde o código será executado em um ambiente com caminho e sistema operacional pré-determinados, como em um robô, serviço em contêiner ou sistema embarcado com configurações de diretório fixas.

Importante: Ao criar arquivos fora do diretório `root` (raiz), é obrigatório que o caminho completo do diretório pai já exista no sistema operacional. Caso contrário, ocorrerá uma exceção.

- **Exemplo de caminho fixo:**
```java
Path caminho = Path.of(ARQUIVO_LOCAL);

// Exemplo de valor para ARQUIVO_LOCAL:
// "/home/pc-poderoso-chefao/Documentos/Projetos/praticando-nio-arquivos/arquivo.txt"
```

<br>

## ✅ Requisitos

- Java 7 ou superior
- JDK configurado no JAVA_HOME

---

## 🧑‍💻 Autora

[Deise Sales](https://github.com/deisesalless)

---

## Links de Referência do Projeto:

- Classe `App.java` **(Principal)**: https://github.com/deisesalless/java-nio-arquivos/blob/main/src/main/java/br/utfpr/deisesalless/App.java
    - Este arquivo contém a lógica principal e invoca os métodos de leitura, escrita de arquivo TXT e escrita de várias linhas.
- Classe `Arquivo.java` **(Utilitários)**: https://github.com/deisesalless/java-nio-arquivos/blob/main/src/main/java/br/utfpr/deisesalless/Arquivo.java
    - Contém a implementação dos métodos utilitários para manipulação de arquivos.
