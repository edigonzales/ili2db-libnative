package ch.so.agi.ili2db.libnative;

import java.nio.file.Paths;

import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CTypeConversion;

import ch.ehi.ili2db.base.Ili2db;
import ch.ehi.ili2db.base.Ili2dbException;
import ch.ehi.ili2db.gui.Config;
import ch.ehi.ili2pg.PgMain;

public class Ili2dbLib {
    
    // TOOD: 
    // - Alles was getestet werden soll, ausserhalb der Methode? (static?)
    
    @CEntryPoint(name = "ili2pg")
    public static int ili2pg(IsolateThread thread, CCharPointer settings, CCharPointer fileName) {
        
        System.out.println("*******");
        System.out.println(CTypeConversion.toJavaString(settings));
        
        Config config = new Config();
        new PgMain().initConfig(config);
        config.setFunction(Config.FC_IMPORT);
        config.setDoImplicitSchemaImport(true);
        config.setConfigReadFromDb(true);
        //config.setModels("DM01AVCH24LV95D");
        config.setModels("SO_Nutzungsplanung_20171118");
        Config.setStrokeArcs(config,Config.STROKE_ARCS_ENABLE);
        //config.setModeldir(Paths.get("../model").toFile().getAbsolutePath()+";"+"http://models.geo.admin.ch");
        config.setValidation(false);
        config.setItfTransferfile(true);
        //config.setDatasetName(tile)
        config.setDbusr("admin");
        config.setDbpwd("admin");
        config.setDburl("jdbc:postgresql://localhost:54321/edit");
        config.setDbschema("npl_2551");
        config.setDefaultSrsCode("2056");
        config.setXtffile(CTypeConversion.toJavaString(fileName));
        System.out.println(config.getXtffile());
        try {
            Ili2db.run(config, null);
        } catch (Ili2dbException e) {
            e.printStackTrace();
            return 1;
        }

        
        return 0;
    }
    

}
