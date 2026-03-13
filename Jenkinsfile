pipeline {
    agent any
    tools {
        maven 'Maven 3' // Name as configured in Jenkins global tools
    }
    stages {
        stage('Build & Test') {
            steps {
                bat 'mvn clean test'
            }
        }
        stage('Allure Report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
            }
        }
    }
}
