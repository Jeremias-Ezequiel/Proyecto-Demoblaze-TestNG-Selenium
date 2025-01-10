pipeline{
    agent any
    parameters{
        choice(name : 'BROWSER', choices : ['chrome','edge','firefox','safari'])
    }

    stages{
        stage('Build with the browser selected in headless mode'){
            steps{
                echo "Running the test with: ${params.BROWSER}"
                bat "./mvnw clean test -Dgroups='smoke' -Dheadless -Dbrowser='${params.BROWSER}'"
            }
        }
    }
}