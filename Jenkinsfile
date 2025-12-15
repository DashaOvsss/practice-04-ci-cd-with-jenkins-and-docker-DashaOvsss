pipeline {
    agent {
        label 'agent-node-label'
    }

    environment {
        APP_NAME = 'dasha_ovs'
        DOCKER_IMAGE_NAME = 'dasha_ovs'
        GOCACHE = "/home/jenkins/.cache/go-build/"
    }

    stages {

        stage('Clone Repository') {
            steps {
                checkout scm
            }
        }

        stage('Compile') {
            agent {
                docker {
                    image 'golang:1.23.3'
                    reuseNode true
                }
            }
            steps {
                sh "CGO_ENABLED=0 GOOS=linux GOARCH=amd64 go build -a -ldflags '-w -s -extldflags \"-static\"' -o ${env.APP_NAME} ./src/go"
            }
        }

        stage('Unit Testing') {
            agent {
                docker {
                    image 'golang:1.23.3'
                    reuseNode true
                }
            }
            steps {
                sh '''
                cd src/go
                go test ./...
                '''
            }
        }

        stage('Archive Artifact and Build Docker Image') {
            parallel {

                stage('Archive Artifact') {
                    steps {
                        sh "tar -czf ${env.APP_NAME}-${env.BUILD_NUMBER}.tar.gz ${env.APP_NAME}"
                    }
                }

                stage('Build Docker Image') {
                    steps {
                        sh """
                        docker build \
                        --build-arg APP_NAME=${env.APP_NAME} \
                        -t ${env.DOCKER_IMAGE_NAME}:${env.BUILD_NUMBER} .
                        """
                    }
                }
            }
        }
    }

    post {
        success {
            archiveArtifacts artifacts: "${env.APP_NAME}-${env.BUILD_NUMBER}.tar.gz"
        }
        always {
            echo 'Pipeline finished'
        }
    }
}
