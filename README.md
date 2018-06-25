# Weather

Projekt MAVEN wysyłający powiadomienia SMS o pogodzie.

## Pierwsze kroki


```
git clone https://github.com/orlinskimikael/weather.git
```

### Wymagania wstępne

Do pobrania i wygenerowania biblioteki wymagane są:

* GIT - https://git-scm.com/
* JAVA 8 (JDK i JRE) - http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html?printOnly=1
* MAVEN - https://maven.apache.org/


Należy również pamiętać o ustawieniu odpowiednich zmiennych środowiskowych *(przykładowe ścieżki)*:

```
JAVA_HOME = C:\Program Files\Java\jdk-10.0.1
JDK_HOME = %JAVA_HOME%
JRE_HOME = C:\Program Files\Java\jre-10.0.1
M2_HOME = C:\Program Files\Apache\apache-maven-3.5.3
MAVEN_HOME = C:\Program Files\Apache\apache-maven-3.5.3
PATH = $PATH;C:\Program Files\Java\jdk-10.0.1\bin;C:\Program Files\Java\jre-10.0.1\bin;C:\Program Files\Git\bin;
```

### Budowanie, instalacja i uruchomienie

Przed rozpoczęciem budowania należu uzupełnić plik **src/main/resources/application.properties** właściwymi danymi.

Budowanie biblioteki należy wykonać za pomocą narzędzia MAVEN za pomocą polecenia *(polecenie wykonujemy z poziomu folderu w którym umieszczony jest plik pom.xml projektu)*:

```
mvn clean install
```

Po wykonaniu polecenia biblioteka zostanie umieszczona w lokalnym repozytorium maven-a oraz w folderze **target** projektu.

Uruchomienie:

```
java -jar target/weather-1.0.0.jar
```

## Problemy


## Autor

* **Michał Orliński**
* email: orlinski.michal.it@gmail.com