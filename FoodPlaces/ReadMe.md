# RestFul API Restaurant
## Мавлетова Карина Радиовна, БПИ216
### Какие есть таблицы в базе данных:
- Таблица ресторанов
```
create table restaurants(
    id serial primary key,
    name varchar(20),
    address varchar(20),
    rating FLOAT
);
```
- Таблицы с меню каждого ресторана:
```
create table RabbitMenu(
    id serial primary key,
    name varchar(20),
    description varchar(20),
    price INT,
    count INT
);

create table GoldenMenu(
   id serial primary key,
   name varchar(20),
   description varchar(20),
   price INT,
   count INT
);
```

### :white_check_mark: Получения списка всех ресторанов:
- Чтобы получить список всех ресторанов нужно отправить запрос на endpoint restaurants
- Далее будет выведена следующая информация про ресторан: название, адрес, рейтинг
- Информация о каждом ресторане отделяется ;

### :white_check_mark: Просмотр меню конкретного ресторана:
- Для того чтобы получить менб ресторана нужно обратиться по endpoint menu/id, где id соответствует id ресторана
- Меню ресторана содержит наименование блюда, цену и описание
  ### Возможные проблемы:
    - ресторана с таким то id не сущесвует, будет выведено соотв. сообщение

### :white_check_mark: Создание заказа на доставку еды:
- Нужно обратиться по endpoint orders и в теле запроса через json передать id ресторана, id блюда и кол-во блюда
  ### Возможные проблемы:
    - ресторана с таким то id не сущесвует
    - блюда с таким то id не существует
    - заказанное кол-во блюда не соотв. кол-во в ресторане
Во всех случях будет получены соотв. сообщения.
 




 

