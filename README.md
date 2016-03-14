# Javascript Challenge: Blog #

This project is designed to test your skills as a front-end web developer,
using the same tools and technologies our team employs on a daily basis.

This project includes a Java-based web service which exposes a REST API for a 
blog. The API itself is pretty simple; it doesn't even authenticate users. 

The goal of this exercise is to create a basic blog application, capable of:

*   Showing a list of all blog posts. Don't worry about pagination or 
    sorting. Just display them in the order that the server returns them.
    Each post has a "pull quote" which should appear on this page.
*   Show a single blog post. Instead of the pull quote, this page should
    show the post's body HTML.
*   Edit a post. Display a UI that allows the user to edit the post's title,
    author, author email, pull quote, and body, then save it on the server.
    
You can use whatever tools you're most comfortable with. Google anything you
need to look up. Download whatever libraries or frameworks you like. Ask 
questions if you need something clarified. Work like you'd really work.

This exercise is intended to take between one and two hours. It doesn't need
to be perfectly styled, unit tested, or the Ur example of prototypical 
Javascript object orientation. We're mostly looking to see if you can produce
working Javascript in a fairly-real-world setting.
    
## Necessary Tools ##

The majority of the software my team develops runs on Java and is built with
Maven. This project is no different. To get started, make sure you have 
[Java version 1.8.45 or later](https://java.com/en/download/) installed on 
your machine. Then, [download](https://maven.apache.org/download.cgi) and
[install](https://maven.apache.org/install.html) Apache Maven 3.3 or later.

When installation is complete, you should be able to run the following 
commands and see something similar to the following output:

```
> java -version
java version "1.8.0_45"
Java(TM) SE Runtime Environment (build 1.8.0_45-b14)
Java HotSpot(TM) 64-Bit Server VM (build 25.45-b02, mixed mode)

> mvn -v
Apache Maven 3.3.9 (bb52d8502b132ec0a5a3f4c09453c07478323dc5; 2015-11-10T11:41:47-05:00)
Maven home: /Users/thomas/Applications/bin/maven/3.3.9
Java version: 1.8.0_45, vendor: Oracle Corporation
Java home: /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre
Default locale: en_US, platform encoding: UTF-8
OS name: "mac os x", version: "10.11.3", arch: "x86_64", family: "mac"
```

## The Source ##

Most of the projects inside `BlogJsChallenge` are related to the server, and
you can safely ignore them. If you think in Java and want to understand the
data structures and APIs, you can browse through 
`BlogJsChallenge/BlogJsBackend`.

The web application, the part you'll be working on, lives in 
`BlogJsChallenge/BlogJsFrontend`. A shell for the web app already exists, 
based on  [HTML 5 Boilerplate](https://html5boilerplate.com/). jQuery and 
AngularJS are included, but you don't have to use them. Use whatever libraries 
and tools you're most comfortable with.

The layout of the application is also based on the Boilerplate template. The 
main file is `index.html`, the main style sheet is `css/styles.css`, and
the main Javascript file is `js/main.js`. Third-party libraries and frameworks
can be added to `js/vendor`.

Feel free to rename things if it  makes more sense in your framework to do so.

## The API ##

There are four methods you need to worry about:

* GET  <http://localhost:8080/api/posts> - gets a list of all blog posts
* GET  <http://localhost:8080/api/posts/{id}> - gets a single blog post
* POST <http://localhost:8080/api/posts> - stores a blog post
* PUT  <http://localhost:8080/api/posts/{id}> - stores a blog post with the given UUID

This API is documented at <http://localhost:8080/api/swagger>, and includes 
example data.

A blog post contains the following data:

```javascript
{
  "uuid": "string",
  "title": "string",
  "pubDate": 0,
  "author": "string",
  "authorEmail": "string",
  "pullQuote": "string",
  "body": "string",
  "pullQuoteAsHtml": "string",
  "bodyAsHtml": "string"
}
```

`pullQuote` and `body` are 
[Markdown](https://daringfireball.net/projects/markdown/) formatted text. This 
is what should be  displayed in the editor. When a blog post is saved, the 
server will convert the Markdown into HTML, and store these values in 
`pullQuoteAsHtml` and `bodyAsHtml`. These are the values that should be shown 
when displaying a blog post. `postDate` is a `long` containing a Unix 
timestamp; you can get the displayable time using `new Date(post.pubDate)`.

## Building the Project ##

Once you clone the project to your machine, building is as simple as:

```
> cd ~/dev/BlogJsChallenge
> mvn clean install
```

Maven will then download all of the necessary libraries, compile the Java 
source code, package up the web app, and create a distribution.

## Running the project ##

The project includes a script that will allow you to start the server and
run the web app from the source directory. You can run it by executing the
following commands:

```
> cd BlogJsChallenge/BlogJsDist/target/BlogJsDist-1.0-dist
> chmod a+x dev.sh
> ./dev.sh
```

The will start the server and the REST API. It will also serve up the web app
from `BlogJsChallenge/BlogJsFrontend/src/main/html/blog/`. This means you
can edit the web app in source and view your changes by refreshing the page,
no rebuild required.

You can access the web app at <http://localhost:8080/>