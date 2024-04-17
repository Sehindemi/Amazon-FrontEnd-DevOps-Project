pipeline {
    agent any
    tools{
        jdk 'jdk17'
        nodejs 'node16'
    }
    environment{
        SCANNER_HOME=tool 'sonar-scanner'
    }
    stages {
        stage('CW') {
            steps {
                cleanWs()
            } 
        }
        stage('GIT CO') {
            steps {
                git branch: 'main', url: 'https://github.com/Sehindemi/Amazon-FrontEnd-DevOps-Project.git'
            }
        }
        stage('SONAR CA') {
            steps {
                script{
                    withSonarQubeEnv('sonar-server') {
                        sh ''' 
                        $SCANNER_HOME/bin/sonar-scanner -Dsonar.projectName=Amazon \
                        -Dsonar.projectKey=Amazon 
                        ''' 
                        
                        
                    }
                }
            }
        }
        stage('QG') {
            steps {
                script{
                    waitForQualityGate abortPipeline: false, credentialsId: 'Sonar-token'
                }
            }
        }
        stage('NPM') {
            steps {
                sh 'npm install'
            }
        }
        stage('OWASP FS SCAN') {
            steps {
                dependencyCheck additionalArguments: '--scan ./ --disableYarnAudit --disableNodeAudit', odcInstallation: 'DP'
                dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
            }
        }
        stage('TRIVY FS SCAN') {
            steps {
                sh "trivy fs . > trivyfs.txt"
            }
        }
        stage("DB & DP"){
            steps{
                script{
                   withDockerRegistry(credentialsId: 'docker', toolName: 'docker'){   
                       sh '''
                       docker build -t amazon .
                       docker tag amazon sehindemitech/amazon:latest 
                       docker push sehindemitech/amazon:latest 
                       '''
                    }
                }
            }
        } 
        stage("TRIVY IMAGE"){
            steps{
                sh "trivy image sehindemitech/amazon:latest > TRIVYIMAGE.txt" 
            }
        }
        stage("AMAZON APP"){
            steps{
                sh "docker run -d --name amazon -p 3000:3000 sehindemitech/amazon:latest" 
            }
        }
        
    }
}
