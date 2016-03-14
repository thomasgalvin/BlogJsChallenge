package interview.blog;

import java.util.ArrayList;
import java.util.List;

public class BlogPostList
{
    private List<BlogPost> posts = new ArrayList();

    public BlogPostList(){}
    
    public BlogPostList( List<BlogPost> posts ){
        this.posts.addAll( posts );
    }
    
    public List<BlogPost> getPosts() {
        return posts;
    }

    public void setPosts( List<BlogPost> posts ) {
        this.posts = posts;
    }
    
}
