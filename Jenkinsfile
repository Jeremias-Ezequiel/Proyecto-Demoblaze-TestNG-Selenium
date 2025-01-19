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
        stage('Publicar reporte de Allure'){
            steps{
                allure includeProperties: false, jdk: '', results: [[path: '\\target\\allure-results']]
            }
        }
    }
    post{
        always{
            echo 'Pipeline finalizada'
            cleanWs()       
        }
        success{
            echo 'Las pruebas fueron exitosas'
        }
    }
}
