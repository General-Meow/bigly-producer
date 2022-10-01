pipeline {
  agent {
    kubernetes {
      yaml '''
        apiVersion: v1
        kind: Pod
        metadata:
          labels:
            some-label: some-label-value
        spec:
          containers:
          - name: builder-container
            image: ubuntu:latest
            command:
            - cat
            tty: true
        '''
      }
    }

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
