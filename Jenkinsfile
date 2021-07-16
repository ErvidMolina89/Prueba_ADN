pipeline {
  //Donde se va a ejecutar el Pipeline
  agent any

  //Opciones específicas de Pipeline dentro del Pipeline
  options {
    buildDiscarder(logRotator(numToKeepStr: '3'))
    disableConcurrentBuilds()
  }

  //Una sección que define las herramientas “preinstaladas” en Jenkins
  tools {
    jdk 'JDK8_Mac' //Verisión preinstalada en la Configuración del Master
  }

  stages {
    stage('Build') {
        steps {
          echo '------------>Build<------------'
          sh './gradlew build -x test'
        }
    }
    stage('Unit Tests') {
      steps {
        sh './gradlew clean'
        echo '------------>Unit Tests<------------'
        sh './gradlew test'
      }
    }

    stage('Static Code Analysis') {
      steps {
        echo '------------>Análisis de código estático<------------'
        withSonarQubeEnv('Sonar') {
          sh "${tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner"
        }
      }
    }
  }

  post {
    failure {
      echo 'This will run only if failed'
      mail (
        to: 'eduardo.grosso@ceiba.com.co',
        subject: "Failed Pipeline:${currentBuild.fullDisplayName}",
        body: "Something is wrong with ${env.BUILD_URL}")
    }
  }
}