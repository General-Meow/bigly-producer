pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                script {
                  ./gradlew clean test
                }
            }
        }
        stage('Build') {
            steps {
                script {
                    ./gradle build
                 }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    echo 'Deploying....'
                }
            }
        }
    }
}
