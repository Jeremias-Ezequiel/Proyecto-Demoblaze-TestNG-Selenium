pipeline{
    agent any
    
    environment {
        ALLURE_RESULTS = 'allure-results' // Directorio donde se almacenan los resultados de las pruebas
        ALLURE_REPORT = 'allure-report'   // Directorio donde se generará el reporte de Allure
    }

    stages{
        stage('Limpiar resultados Allure') {
            steps {
                script {
                    // Limpiar la carpeta allure-results
                    deleteDir() // Borra todo en el workspace
                    sh 'rm -rf allure-results/*' // Limpiar carpeta allure-results en sistemas Unix/Linux
                    // o en Windows puedes usar: bat 'del /F /Q allure-results\\*'
                }
            }
        }
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
