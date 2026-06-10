# Keisa — CLI minimalista

Keisa es una pequeña herramienta de línea de comandos escrita en Java para evaluar y ejecutar comandos simples (SET, ADD, SUB, MUL, DIV, PRINT, VARS, HELP, CLEAR). Está diseñada para ser ligera y compilable como binario nativo mediante GraalVM.

---

## Tabla de contenido

- [Instalación](#instalación)
- [Uso](#uso)
- [Arquitectura](#arquitectura)
- [Enlaces importantes](#enlaces-importantes)
- [Buenas prácticas](#buenas-prácticas-y-sugerencias)
- [Contribuciones](#contribuciones)

---

## Instalación

Requisitos mínimos:
- JDK 17+ (el POM usa source/target 25, pero la mayoría de flujos funcionan con JDK 17+)
- Maven
- (Opcional) GraalVM y native-image para generar binario nativo

Pasos rápidos:

- Compilar y ejecutar con JVM:

```bash
mvn -DskipTests package
java -jar target/keisa-0.0.1.jar
```

- Compilar como imagen nativa (requiere GraalVM + native-image):

```bash
# desde el directorio del proyecto
mvn -DskipTests package
# el plugin nativo configurado en pom.xml intentará generar `keisa-cli` durante package
# asegúrate de tener GraalVM y `native-image` en PATH
```

- Instalador Windows incluido (opcional): `instalar.ps1` descarga el ejecutable `keisa-cli.exe` desde la rama `main`.

---

## Uso

Inicia la CLI y escribe comandos interactivos:

```text
# Ejecutar la aplicación
java -jar target/keisa-0.0.1.jar

# Ejemplos dentro de la sesión:
SET x 10
ADD x 5
PRINT x
VARS
EXIT
```

Si construyes la imagen nativa, ejecuta `./keisa-cli` (Linux/macOS) o `keisa-cli.exe` en Windows.

---

## Arquitectura

Estructura principal del proyecto:

- `uk.jimsimrodev.Keisa` — punto de entrada
- `logica.MiniScrip` — motor REPL y despachador de comandos
- `parser` — parseo de input (`CommandParser`, `CommandInput`)
- `commands` — implementaciones de comandos (SET, ADD, SUB, ...)
- `logica` — `SymbolTable`, lógica de evaluación
- `view` — `TerminalPrinter`, `BannerLoader`

Diagrama (Mermaid):

```mermaid
graph TD
  CLI[Keisa (main)] --> REPL[MiniScrip]
  REPL --> Parser[CommandParser]
  REPL --> CommandsDir[Commands]
  REPL --> SymbolTable[SymbolTable]
  REPL --> View[TerminalPrinter / BannerLoader]
  CommandsDir -->|execute| SymbolTable
  Parser --> CommandInput[CommandInput]
```

---

## Enlaces importantes

- 🔗 GitHub: https://github.com/JimsimroDev/keisa-cli
- 📦 Releases / binarios: (ver la sección `target/` o la release en GitHub)
- 🧾 POM / Build: `pom.xml` (GraalVM native plugin configurado)

---

## Buenas prácticas y sugerencias

- No versionar artefactos de build (ej. `target/`, `*.jar`, `*.class`, `keisa-cli.exe`) — ya hay `.gitignore` incluido.
- Para generar binarios nativos, usa una versión estable de GraalVM y verifica `native-image` compatible con tu JDK.
- Añadir checksums (SHA256) y firma para binarios distribuidos.
- Incluir pruebas unitarias para la lógica en `logica/` y el parseador en `parser/` para facilitar refactorings.
- Automatizar builds y releases en CI (GitHub Actions) que publiquen artefactos y ejecutables por plataforma.

---

## Contribuciones

- Abrir issues para bugs y mejoras.
- Fork -> branch con cambios -> PR con descripción y tests.
- Sigue el estilo de commits (Conventional Commits) y agrega pruebas cuando corresponda.

---

Si quieres, puedo generar un ejemplo de GitHub Actions para compilar y publicar artefactos nativos por plataforma.

