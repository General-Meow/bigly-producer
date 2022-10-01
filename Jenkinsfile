pipeline {
    agent any

    stages {
        stage('Test') {
          steps {
            container('builder-container') {
              sh './gradlew clean test'
            }
          }
        }
        stage('Build') {
          steps {
            container('builder-container') {
              sh './gradlew build'
            }
          }
        }
        stage('Deploy') {
          steps {
            container('builder-container') {
              script {
                  echo 'Deploying....'
              }
            }
          }
        }
    }
}
