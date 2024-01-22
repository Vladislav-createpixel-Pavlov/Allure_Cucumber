node('unix') {
    stage('Git checkout') {
        checkout scmGit(branches: [[name: '*/main']], extensions: [],
            userRemoteConfigs: [[url: 'https://github.com/Vladislav-createpixel-Pavlov/Allure_Cucumber']])
    }
    stage('Run tests') {
        withMaven(globalMavenSettingsConfig: '', jdk: '', maven: 'Default', mavenSettingsConfig: '', traceability: true) {
            sh 'mvn clean test -Dtype.browser=${browser} -Dgroups=${tag}'
        }
    }
    stage('Create allure report') {
        allure includeProperties: false, jdk: '', results: [[path: 'target/reports/allure-results']]
    }
}
