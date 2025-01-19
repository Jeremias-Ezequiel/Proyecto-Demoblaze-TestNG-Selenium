pipeline{
    agent any

    stages{
        stage('Preparacion'){
            steps{
                echo 'Preparando el entorno'
            }
        }
        stage('Ejecutar Pruebas'){
            steps{
                echo 'Ejecutando pruebas con Selenium y TestNG'
                bat './shellScript.bat'
            }
        }
    }
    post{
        always{
            echo 'Pipeline finalizada'
            allure includeProperties: false, jdk: '', results: [[path: '\\target\\allure-results']]
            cleanWs()       
        }
        success{
            echo 'Las pruebas fueron exitosas'
        }
    }
}
