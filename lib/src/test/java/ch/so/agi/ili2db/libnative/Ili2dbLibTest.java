package ch.so.agi.ili2db.libnative;

import org.junit.jupiter.api.Test;

import ch.ehi.ili2db.gui.Config;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Ili2dbLibTest {
    Logger logger = LoggerFactory.getLogger(Ili2dbLibTest.class);

    @Test
    public void parseJsonString() throws IOException {
        String jsonString = "{\n"
                + "    \"dbhost\" : \"localhost\",\n"
                + "    \"dbport\" : \"54321\",\n"
                + "    \"dbdatabase\" : \"edit\",\n"
                + "    \"dbusr\" : \"admin\",\n"
                + "    \"dbpwd\" : \"admin\",\n"
                + "    \"dburl\" : \"jdbc:postgresql://localhost:54321/edit\",\n"
                + "    \"dbschema\" : \"npl_2551\",\n"
                + "    \"defaultSrsCode\" : \"2056\",\n"
                + "    \"strokeArcs\" : \"enable\",\n"
                + "    \"disableValidation\" : true,\n"
                + "    \"models\" : \"SO_Nutzungsplanung_20171118\",\n"
                + "    \"doSchemaImport\" : true,\n"
                + "    \"function\" : \"import\",\n"
                + "    \"file\" : \"./src/test/data/2551.xtf\"\n"
                + "}";
        
        logger.info(jsonString);
        
        Config settings = Ili2dbLib.json2config(jsonString);
        
        assertEquals("localhost", settings.getDbhost());
        assertEquals("enable", settings.getStrokeArcs(settings));
        assertTrue(settings.isDoImplicitSchemaImport());
        assertFalse(settings.isValidation());
    }
}
