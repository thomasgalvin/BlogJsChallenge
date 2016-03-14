package interview.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.dirkraft.dropwizard.fileassets.FileAssetsBundle;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import java.io.File;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlogServer extends Application<BlogConfig> {
    private static final Logger logger = LoggerFactory.getLogger( BlogServer.class );
    private BlogDB db = new BlogDB();
    public static final String WEB_APP_FILESYSTEM_LOCATION = "web.app.location";
    private String webAppFilesystemLocation;

    @Override
    public void initialize( Bootstrap<BlogConfig> bootstrap ) {
        webAppFilesystemLocation = System.getProperty( WEB_APP_FILESYSTEM_LOCATION );
        
        if( !StringUtils.isBlank( webAppFilesystemLocation ) ){
            File location = new File( webAppFilesystemLocation );
            logger.info( "Serving web app from " + location.getAbsolutePath() );
            System.out.println( "Serving web app from " + location.getAbsolutePath() );
            
            FileAssetsBundle webAppBundle = new FileAssetsBundle(
                location.getAbsolutePath(),
                "/",
                "index.html",
                "blog web app"
            );
            bootstrap.addBundle( webAppBundle );
        }
        else{
            logger.info( "Serving web app from jar" );
            System.out.println( "Serving web app from jar" );
            bootstrap.addBundle( new AssetsBundle( "/blog", "/", "index.html", "blog web app" ) );
        }
        
        final SwaggerBundleConfiguration swaggerConfig = new SwaggerBundleConfiguration();
        swaggerConfig.setResourcePackage( "interview.blog" );
        SwaggerBundle swaggerBundle = new SwaggerBundle() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration( Configuration configuration ) {
                return swaggerConfig;
            }
        };
        bootstrap.addBundle( swaggerBundle );

        Populator populator = new Populator();
        populator.populate( db );
    }

    @Override
    public void run( BlogConfig config, Environment environment ) throws Exception {
        ObjectMapper mapper = environment.getObjectMapper();
        mapper.enable( SerializationFeature.INDENT_OUTPUT );

        environment.jersey().register( new BlogResource( db ) );
    }

    public static void main( String[] args ) {
        try {
            System.setProperty( "dw.server.rootPath", "/api/*" );

            String[] applicationArgs = null;
            if( args != null ) {
                applicationArgs = new String[ args.length + 1 ];
                applicationArgs[0] = "server";
                System.arraycopy( args, 0, applicationArgs, 1, args.length );
            }
            else {
                applicationArgs = new String[]{ "server" };
            }

            BlogServer server = new BlogServer();
            server.run( applicationArgs );
        }
        catch( Throwable t ) {
            logger.info( "Error in main", t );
            t.printStackTrace();
        }
    }

}
