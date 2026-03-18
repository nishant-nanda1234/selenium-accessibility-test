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
        stage('Applitools Visual Test') {
            steps {
                // Set Applitools API key as environment variable (replace with your actual key or use Jenkins credentials)
                withEnv(['APPLITOOLS_API_KEY=your_applitools_api_key']) {
                    bat 'mvn -Dtest=SampleUiTest test'
                }
            }
        }
        stage('Allure Report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
            }
        }
    }
}
