pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                ./gradlew clean test
            }
        }
        stage('Build') {
            steps {
                ./gradle build
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
