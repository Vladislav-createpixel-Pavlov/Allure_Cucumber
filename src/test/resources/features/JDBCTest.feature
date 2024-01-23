# language: ru
@all
@JDBC_Test
Функция: Проверка в базе данных при добавлении новой записи
  Предыстория:
    * Создано соединение с БД

  @JDBC_Test_correct
  Сценарий: Успешное добавление новой записи - сценарий 1
    * Выполнение подсчета количества записей до добавления
    * Добавление товара c параметрами: Наименование: "Картофель", Тип: "VEGETABLE", Экзотический: 0
    * Выполнение подсчета количества записей после добавления
    * Проверка добавления записи
    * Выполнение подсчета количества записей до добавления
    * Удаление записи
    * Добавление товара c параметрами: Наименование: "Mango", Тип: "FRUIT", Экзотический: 1
    * Выполнение подсчета количества записей после добавления
    * Проверка добавления записи
    * Удаление записи
    * Закрытие содинения