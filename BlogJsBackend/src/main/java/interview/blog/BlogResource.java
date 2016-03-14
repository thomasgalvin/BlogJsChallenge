package interview.blog;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import io.dropwizard.jersey.caching.CacheControl;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path( "posts" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
@Api(value="posts", description="CRUD for blog posts")
public class BlogResource
{
    private BlogDB db;
    
    public BlogResource(BlogDB db){
        this.db = db;
    }
    
    @GET
    @CacheControl( noCache = true )
    @ApiOperation(value="Retrieves a blog post, without the body, given its UUID", response=BlogPostList.class )
    public BlogPostList getAll(){
        List<BlogPost> result = db.get();
        
        for( BlogPost post : result ){
            post.setBody(null);
            post.setBodyAsHtml(null);
        }
        
        return new BlogPostList( result );
    }
    
    @GET
    @Path( "/{id}" )
    public BlogPost get( @PathParam("id") String id ){
        BlogPost result = db.get(id);
        return result;
    }
    
    @POST
    @ApiOperation( value = "Creates a new Blog Post or overwrites an existing Blog Post. Returns the UUID.",
                   response = UuidWrapper.class )
    public UuidWrapper postStore( BlogPost blogPost ){
        String result = db.store( blogPost );
        return new UuidWrapper( result );
    }
    
    @PUT
    @Path( "/{id}" )
    @ApiOperation( value = "Creates a new Blog Post or overwrites an existing Blog Post. Returns the UUID.",
                   response = UuidWrapper.class )
    public UuidWrapper putStore( @PathParam("id") String id, BlogPost blogPost ){
        blogPost.setUuid( id );
        String result = db.store( blogPost );
        return new UuidWrapper( result );
    }
}
