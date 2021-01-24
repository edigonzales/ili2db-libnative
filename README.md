# ili2db-libnative

```
./gradlew clean lib:build shadowJar
native-image --no-fallback --no-server -cp lib/build/libs/lib-all.jar --shared -H:Name=libili2db
```

macOS:
```
cc -Wall -I. -L. -lili2db ili2pg.c -o ili2pg
```

Linux:
```
todo
```

## Database

```
docker volume prune

docker-compose build
docker-compose down
docker-compose up
```

## Links

- https://github.com/borkdude/sci/blob/master/doc/libsci.md