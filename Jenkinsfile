node('unix') {
    stage('Git checkout') {
        checkout scm
    }
    stage('Run tests') {
        withMaven(globalMavenSettingsConfig: '', jdk: '', maven: 'Default', mavenSettingsConfig: '', traceability: true) {
            sh 'mvn clean test -Dtest=${tag} -Dtype.browser=${browser}'
        }
    }
    stage('Allure') {
        allure includeProperties: false, jdk: '', results: [[path: 'target/reports/allure-results']]
    }
}
