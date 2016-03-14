package interview.blog;

public class BlogPost
{
    private String uuid;
    private String title;
    private long pubDate;
    private String author;
    private String authorEmail;
    private String pullQuote;
    private String body;
    
    private String pullQuoteAsHtml;
    private String bodyAsHtml;

    public BlogPost() {
    }

    public BlogPost( String uuid, 
                     String title, 
                     long pubDate, 
                     String author, 
                     String authorEmail, 
                     String pullQuote, 
                     String body,
                     String pullQuoteAsHtml,
                     String bodyAsHtml ) {
        this.uuid = uuid;
        this.title = title;
        this.pubDate = pubDate;
        this.author = author;
        this.authorEmail = authorEmail;
        this.pullQuote = pullQuote;
        this.body = body;
        this.pullQuoteAsHtml = pullQuoteAsHtml;
        this.bodyAsHtml = bodyAsHtml;
    }
    
    public BlogPost clone(){
        return new BlogPost( uuid, title, pubDate, author, authorEmail, pullQuote, body, pullQuoteAsHtml, bodyAsHtml );
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid( String uuid ) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public long getPubDate() {
        return pubDate;
    }

    public void setPubDate( long pubDate ) {
        this.pubDate = pubDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor( String author ) {
        this.author = author;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail( String authorEmail ) {
        this.authorEmail = authorEmail;
    }

    public String getPullQuote() {
        return pullQuote;
    }

    public void setPullQuote( String pullQuote ) {
        this.pullQuote = pullQuote;
    }

    public String getBody() {
        return body;
    }

    public void setBody( String body ) {
        this.body = body;
    }

    public String getPullQuoteAsHtml() {
        return pullQuoteAsHtml;
    }

    public void setPullQuoteAsHtml( String pullQuoteAsHtml ) {
        this.pullQuoteAsHtml = pullQuoteAsHtml;
    }

    public String getBodyAsHtml() {
        return bodyAsHtml;
    }

    public void setBodyAsHtml( String bodyAsHtml ) {
        this.bodyAsHtml = bodyAsHtml;
    }
    
}
