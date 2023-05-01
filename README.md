# JavaJerseyRestfull

## Projeto construído com:
1. IntelliJ
2. Java 20
3. Maven
4. Jersey Framework
5. Postgre SQL


## Rodando o projeto (Linux)
1. Install SDKMAN
```bash 
curl -s "https://get.sdkman.io" | bash 
source "$HOME/.sdkman/bin/sdkman-init.sh"
```
2. Na pasta do projeto rode, para instalar a versão 20-amzn do java:
```bash
sdk env init
```
3. Após, baixe as dependências contidas no pom.
4. Compile o projeto com
```bash
mvn -T 1.5C clean install
```
5. Criar um link simbólico do arquivo .war para o servidor Wildfly:
```bash
ln -s $HOME/path...toproject/JavaJerseyRestfull/target/JavaJerseyRestfull.war $HOME/path/to/wildfly/standalone/deployments/
```
6. Executar os scripts SQL no banco de dados, que estão no arquivo:
```bash
/src/java/resources/db.migration/v1.sql
```
7. Caso necessário, mudar as configurações do banco de dados em:
```bash
/src/java/resources/db.properties
```
8. Iniciar o Wildfly com:
```bash
$HOME/path...to...wildfly/bin/standalone.sh 
```