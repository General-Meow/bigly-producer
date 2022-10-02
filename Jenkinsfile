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
    - name: "jnlp"
      image: "jenkins/inbound-agent"
      env:
      - name: JENKINS_URL
        value: "http://jenkins.build.svc.cluster.local:8080"
    - name: builder-container
      image: "amazoncorretto:17.0.4-alpine3.16"
      command: ["cat"]
      tty: true
      volumeMounts:
      - name: docker-dir
        mountPath: "/var/run"
  volumes:
    - name: dockersock
      hostPath:
        path: /var/run/docker.sock
    - name: docker-dir
      emptyDir: {}
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
