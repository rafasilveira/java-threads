# Exercício Java Threads

Exercício para a disciplina de Programação Concorrente. O objetivo é calcular o ganho de performance ao paralelizar a aplicação de um filtro em uma imagem.

## Serial
### Uso
Argumentos:
- arquivo de entrada
- arquivo de saida
- tamanho do filtro (ímpar, entre 3 e 15)

### Argumentos
```bash
javac Serial.java
java Serial res/borboleta.bmp out/saida.bmp 13
```
## Threads
### Argumentos:
- arquivo de entrada
- arquivo de saida
- tamanho do filtro (ímpar, entre 3 e 15)
- número de threads

### Uso
```bash
javac Threads.java
java Threads res/borboleta.bmp out/saida.bmp 13 5
```