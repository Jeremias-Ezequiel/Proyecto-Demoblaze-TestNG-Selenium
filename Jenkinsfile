pipeline{
    agent any
    tools{
        maven
    }

    stages{
        stage('Preparacion'){
            steps{
                echo 'Preparando el entorno'
                sh 'chmod +x shellScript.sh'
                sh 'chmod +x openReport.sh'
            }
        }
        stage('Ejecutar Pruebas'){
            steps{
                echo 'Ejecutando pruebas con Selenium y TestNG'
                sh './shellScript.sh'
            }
        }
        stage('Publicar resultado'){
            steps{
                echo 'Publicando resultados'
                junit '**/target/surefire-reports/*.xml'
                archiveArtifacts artifacts: '**/target/screenshots/*.png', allowEmptyArchive: true
            }
        }
    }
    post{
        always{
            echo 'Pipeline finalizada'
            cleanWs()       
        }
    }
}