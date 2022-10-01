pipeline {
    agent any

    stages {
        stage('Test') {
          steps {
            container('build') {
              sh './gradlew clean test'
            }
          }
        }
        stage('Build') {
          steps {
            container('build') {
              sh './gradlew build'
            }
          }
        }
        stage('Deploy') {
          steps {
            container('build') {
              script {
                  echo 'Deploying....'
              }
            }
          }
        }
    }
}
