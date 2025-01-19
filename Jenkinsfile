pipeline{
    agent any
    
    environment {
        ALLURE_RESULTS = 'allure-results' // Directorio donde se almacenan los resultados de las pruebas
        ALLURE_REPORT = 'allure-report'   // Directorio donde se generará el reporte de Allure
    }

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
        stage('Generar reporte allure'){
            steps{
                script{
                    bat './openReport.bat'
                }
            }
        }
        stage('Publicar reporte de Allure'){
            steps{
                allure includeProperties: false, jdk:'', results: [[path: 'env.ALLURE_RESULTS']]
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
