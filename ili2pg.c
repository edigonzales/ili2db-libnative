#include <stdlib.h>
#include <stdio.h>

#include <libili2db.h>

int main(int argc, char **argv) {
    graal_isolate_t *isolate = NULL;
    graal_isolatethread_t *thread = NULL;

    if (graal_create_isolate(NULL, &isolate, &thread) != 0) {
        fprintf(stderr, "graal_create_isolate error\n");
        return 1;
    }

    char * settings = "{ \"dbhost\" : \"localhost\", \"dbport\" : \"54321\", \"dbdatabase\" : \"edit\", \"dbusr\" : \"admin\", \"dbpwd\" : \"admin\", \"dburl\" : \"jdbc:postgresql://localhost:54321/edit\", \"dbschema\" : \"npl_2551\", \"defaultSrsCode\" : \"2056\", \"strokeArcs\" : \"enable\", \"disableValidation\" : true, \"models\" : \"SO_Nutzungsplanung_20171118\", \"doSchemaImport\" : true, \"function\" : \"import\", \"file\" : \"./lib/src/test/data/2551.xtf\" }";
    printf("%d\n", ili2pg(thread, settings));

    if (graal_detach_thread(thread) != 0) {
        fprintf(stderr, "graal_detach_thread error\n");
        return 1;
    }

    return 0;
}