package interview.blog;

public class DebuggerMain
{
    public static void main( String[] args )
    {
        try
        {
            System.setProperty( BlogServer.WEB_APP_FILESYSTEM_LOCATION, "../BlogJsFrontend/src/main/html/blog/" );
            BlogServer.main(args);
        }
        catch( Throwable t )
        {
            t.printStackTrace();
        }
    }
}
