/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ch.so.agi.ili2db.libnative;

import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.type.CCharPointer;

public class Ili2db {
    
    @CEntryPoint(name = "ili2pg")
    public int ili2pg(IsolateThread thread, CCharPointer settings) {
        
        System.out.println("*******");
        System.out.println(settings);
        
        
        
        return 0;
    }
}
