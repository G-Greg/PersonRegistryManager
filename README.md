# Person Registry Manager

## Start

### Az alkalmazás elindításához a következő parancsot kell kiadni
```
docker-compose up --build
```

Az alkalmazás három konténerben fog futni. 
| App        | Port      |
| :-------- | -------:   |
| Spring Boot backend | :8080 |
| React frontend      |&emsp;&emsp;  :3000|
| Postgres database | :5432|

A főoldalon a személy létrehozása ürlap jelenik meg. 
A felső menüsávon pedig megtalálható az adatbázis listázása. A táblázatban közvetlen lehetőségünk van szerkeszteni az adatokat. Az adatok első induláskor betöltődnek az adatbázisban, összesen 4 darab példa adat.

