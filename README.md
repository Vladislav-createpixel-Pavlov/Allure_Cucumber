Репозиторий для JDBC и Selenium тестов
В файле application.properties можно выбрать настройки для запуска тестов:
  type.browser - отвечает за тип браузера(драйвера) для прогона тестов: 
    edge*,
    firefox,
    chrome
  type.driver - отвечает за тип драйвера:
    local - для прогона тестов на локальном ПК
    remote - для прогона тестов на удаленном сервере(Selenoid)
* - реализация только на OC Windows family
