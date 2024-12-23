# Proyecto Demoblaze - QA Automation - Java con Selenium

Este proyecto toma casos de prueba funcionales que fueron diseñados en un proyecto previo de QA Manual.
[Proyecto Demoblaze - Manual](https://drive.google.com/drive/folders/1NNrSTctmmEbDrJe57r2TpkuZCK-QMZbM?usp=sharing)

## Objetivos del Proyecto

- Convertir casos de prueba manuales en pruebas automatizadas para optimizar el proceso de validacion de funcionalidades en una aplicacion web. 
- Implementar herramientas de automatizacion para asegurar la robustes y escalabilidad de las pruebas.
- Familiarizarme con el ciclo completo de QA Automation, desde la planificacion hasta la ejecucion y generacion de reportes. 

## Tecnologias Utilizadas

- Lenguaje : Java
- Building Tool : Maven (con Maven Wrapper)
- Librerias : 
    - Log4j 
    - Allure-TestNG 
    - slf4j(api y simple) 
    - TestNG 
    - JSoup 
    - Selenium 
    - Poiji 
    - Commons
- Reporte : Allure
- Plugin : Surefire-plugin

## Configuracion del Ambiente

Para ejecutar el proyecto, asegurate de cumplir con los siguientes requisitos y pasos de instalacion:

1. **Java Development Kit (JDK):**
    - Descarga e instala [Java JDK 8 o superior](https://www.oracle.com/java/technologies/javase-downloads.html)
    - Configura la variable de entorno 'JAVA_HOME'
2. **Apache Maven:**
    - Si prefieres usar Maven global, descarga e instala [Maven](https://maven.apache.org/download.cgi)
3. **Allure Report:**
    - Descarga e instala Allure para generar reportes.
    - Sigue las instrucciones en la [Documentacion oficial de Allure](https://docs.qameta.io/allure/)

## Instalacion y Configuracion

**Uso de Maven Wrapper**

Este proyecto incluye Maven Wrapper, lo que significa que no es necesario instalar Maven Globalmente en tu máquina. Puedes usar los siguientes comandos para gestionar las dependencias y ejecutar las pruebas :

1. En sistemas Unix/Linux/Mac :
``` bash
./mvnw clean install
./mvnw test
```

2. En sistemas Windows :
``` bash
    mvnw.cmd clean install
    mvnw.cmd test
```

3. Ejecutar archivos Shell Script :
``` bash
./<nombre-archivo-con-extension-.sh>
```

El Maven Wrapper descargara automaticamente la version requerida de Maven si no esta disponible. 

## Ejecucion de las pruebas 

1. Clona este repositorio en tu maquina local :
``` bash
git clone <URL-del-repositorio>
```

2. Navega al directorio del proyecto :
``` bash
cd <nombre-del-directorio>
```

3. Ejecuta las pruebas usando Maven Wrapper (ejemplo) :
``` bash
./shellScript.sh
```

4. Visualiza los reportes con el archivo shells cript :
``` bash
./openReport.sh
```

## Correr tests por terminal

- Ejecutar por grupos : 
    - ./mvnw clean test -Dgroups="<nombre-del-grupo>"
    - Ejemplo : ./mvnw clean test -Dgroups="smoke"
- Ejecutar un paquete : 
    - ./mvnw clean test -Dtest="<package>"
    - Ejemplo : ./mvnw clean test -Dtest="com.package.my.package.**"
- Ejecutar una clase :
    - ./mvnw clean test -Dtest="<nombre-de-la-clase>"
    - Ejemplo : ./mvnw clean test -Dtest="LoginTests"
- Ejecutar un metodo :
    - ./mvnw clean test -Dtest="<nombre-de-la-clase>#<nombre-del-metodo>"
    - Ejemplo : ./mvnw clean test -Dtest="LoginTest#verificarLoginPageTest"