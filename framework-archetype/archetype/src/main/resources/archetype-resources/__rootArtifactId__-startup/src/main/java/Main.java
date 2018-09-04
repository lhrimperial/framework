#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import com.github.framework.starter.core.ApplicationContexts;
import com.github.framework.starter.core.FrameBootApplication;
import org.springframework.boot.SpringApplication;

/**
 *
 */
@FrameBootApplication(appName = "${rootArtifactId}")
public class Main {

    public static void main(String[] args){
        ApplicationContexts.setProfileIfNotExists("dev");
        SpringApplication.run(Main.class);
    }
}
