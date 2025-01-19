pipeline{
    agent any

    stages{
        stage('Preparacion'){
            steps{
                echo 'Preparando el entorno'
                bat 'chmod +x shellScript.sh'
            }
        }
        stage('Ejecutar Pruebas'){
            steps{
                echo 'Ejecutando pruebas con Selenium y TestNG'
                bat './shellScript.sh'
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
