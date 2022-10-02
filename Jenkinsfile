pipeline {
  agent {
    kubernetes {
      inheritFrom 'java-build-container'
    }
  }

  stages {
    stage('Test') {
      steps {
        sh './gradlew clean test'
      }
    }
    stage('Build') {
      steps {
        sh './gradlew build'
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
