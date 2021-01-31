package ch.so.agi.ili2db.libnative;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CTypeConversion;

import com.fasterxml.jackson.databind.ObjectMapper;

import ch.ehi.ili2db.base.Ili2db;
import ch.ehi.ili2db.base.Ili2dbException;
import ch.ehi.ili2db.gui.Config;
import ch.ehi.ili2pg.PgMain;

public class Ili2dbLib {
    
    @CEntryPoint(name = "ili2pg")
    public static int ili2pg(IsolateThread thread, CCharPointer settings) {
        try {
            Config config = json2config(CTypeConversion.toJavaString(settings));                        
            Ili2db.run(config, null);
        } catch (Ili2dbException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            return 1;
        }
        return 0;
    }
    
    public static Config json2config(String jsonString) throws IOException {
        
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(jsonString, Map.class);
        
        Config config = new Config();
        new PgMain().initConfig(config);
        
        if (!map.containsKey("function")) {
            throw new IllegalArgumentException("missing function parameter");
        } else {
            String function = (String) map.get("function");
            
            if (function.equalsIgnoreCase("import")) {
                config.setFunction(Config.FC_IMPORT);
            }
        }
        
        // TODO if/else/exceptions etc.
        config.setDoImplicitSchemaImport(true);
        config.setConfigReadFromDb(true);
        config.setModels((String) map.get("models"));
        
        config.setDbhost((String) map.get("dbhost"));
        config.setDbport((String) map.get("dbport"));
        config.setDbusr((String) map.get("dbusr"));
        config.setDbusr((String) map.get("dbusr"));
        config.setDbpwd((String) map.get("dbpwd"));
        config.setDburl((String) map.get("dburl"));
        config.setDbschema((String) map.get("dbschema"));

        config.setDefaultSrsCode((String) map.get("defaultSrsCode"));

        if (map.containsKey("strokeArcs")) {
            Config.setStrokeArcs(config, Config.STROKE_ARCS_ENABLE);
        }
        
        if ((Boolean) map.get("disableValidation")) {
            config.setValidation(false);
        }
        
        if ((Boolean) map.get("doSchemaImport")) {
            config.setDoImplicitSchemaImport(true);
        }

        String fileName = (String) map.get("file");
        if (fileName.toLowerCase().endsWith("itf")) {
            config.setItfTransferfile(false);
        } else {
            config.setItfTransferfile(true);
        }
        config.setXtffile(new File(fileName).getAbsolutePath());

        // config.setModeldir(Paths.get("../model").toFile().getAbsolutePath()+";"+"http://models.geo.admin.ch");
        // config.setDatasetName(tile)

        return config;
    }
}
