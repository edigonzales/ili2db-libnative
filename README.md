# ili2db-libnative

```
./gradlew clean lib:build shadowJar
native-image --no-fallback --no-server -cp lib/build/libs/lib-all.jar --shared -H:Name=libili2db
```

macOS:
```
./gradlew clean lib:build shadowJar && \
native-image --no-fallback --no-server -cp lib/build/libs/lib-all.jar --shared -H:Name=libili2db && \
cc -Wall -I. -L. -lili2db ili2pg.c -o ili2pg
```

Linux:
```
sudo apt-get install build-essential
sudo apt install libstdc++-8-dev
```

```
./gradlew clean lib:build shadowJar && \
native-image --no-fallback --no-server -cp lib/build/libs/lib-all.jar --shared -H:Name=libili2db && \
ll
todo
```



## Database

```
docker volume prune

docker-compose build
docker-compose down
docker-compose up
```

## JSON settings
```
{
    "dbhost" : "localhost",
    "dbport" : "54321",
    "dbdatabase" : "edit",
    "dbusr" : "admin",
    "dbpwd" : "admin",
    "dburl" : "jdbc:postgresql://localhost:54321/edit",
    "dbschema" : "npl_2551",
    "defaultSrsCode" : "2056",
    "strokeArcs" : "enable",
    "disableValidation" : true,
    "models" : "SO_Nutzungsplanung_20171118",
    "doSchemaImport" : true,
    "function" : "import",
    "file" : "./src/test/data/2551.xtf"
}
```

```
{ "dbhost" : "localhost", "dbport" : "54321", "dbdatabase" : "edit", "dbusr" : "admin", "dbpwd" : "admin", "dburl" : "jdbc:postgresql://localhost:54321/edit", "dbschema" : "npl_2551", "defaultSrsCode" : "2056", "strokeArcs" : "enable", "disableValidation" : true, "models" : "SO_Nutzungsplanung_20171118", "doSchemaImport" : true, "function" : "import", "file" : "./src/test/data/2551.xtf" }
```

```
{ \"dbhost\" : \"localhost\", \"dbport\" : \"54321\", \"dbdatabase\" : \"edit\", \"dbusr\" : \"admin\", \"dbpwd\" : \"admin\", \"dburl\" : \"jdbc:postgresql:\/\/localhost:54321\/edit\", \"dbschema\" : \"npl_2551\", \"defaultSrsCode\" : \"2056\", \"strokeArcs\" : \"enable\", \"disableValidation\": true, \"models\" : \"SO_Nutzungsplanung_20171118\", \"doSchemaImport\" : true, \"function\" : \"import\", \"file\" : \".\/src\/test\/data\/2551.xtf\" }
```

Mir nicht klar, ob die forward slash escaped werden müssen. Compiler gibt eine Warnung aus: ` warning: unknown escape sequence '\/' [-Wunknown-escape-sequence]`.




## Links

- https://github.com/borkdude/sci/blob/master/doc/libsci.md
- https://www.textfixer.com/tools/remove-line-breaks.php
- https://www.freeformatter.com/json-escape.html#ad-output
