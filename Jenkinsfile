pipeline {
  //Donde se va a ejecutar el Pipeline
  agent {
    label 'Slave_Mac'
  }

  //Opciones específicas de Pipeline dentro del Pipeline
  options {
    	buildDiscarder(logRotator(numToKeepStr: '3'))
 	    disableConcurrentBuilds()
  }

  //Una sección que define las herramientas “preinstaladas” en Jenkins
  tools {
    jdk 'JDK8_Mac' //Verisión preinstalada en la Configuración del Master
  }

  //Aquí comienzan los “items” del Pipeline
  stages{

    stage('Build') {
      steps {
        echo "------------>Build<------------"
        sh './gradlew build -x test'
      }
    }
    stage('Compile & Unit Tests') {
      steps{
        echo "------------>>Clean<------------"
	    sh './gradlew clean'
	    echo "------------>Unit Tests<------------"
	    sh './gradlew test'
      }
    }
    stage('Static Code Analysis') {
      steps{
        echo '------------>Análisis de código estático<------------'
        withSonarQubeEnv('Sonar') {
        sh "${tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=sonar-project.properties"
        }
        }
    }
  }

  post {
    success {
      echo 'This will run only if successful'
      junit 'domain/build/test-results/testDebugUnitTest/*.xml'
    }
    failure {
      echo 'This will run only if failed'
      mail (to: 'ervid.molina@ceiba.com.co',
      subject: "Failed Pipeline:${currentBuild.fullDisplayName}",
      body: "Something is wrong with ${env.BUILD_URL}")
    }
  }
}
