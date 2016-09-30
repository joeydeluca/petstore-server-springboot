# petstore-server-springboot
SpringBoot Pet Store Demo

This SpringBoot application serves as a very basic demo pet store. It exposes an API to manage pets. 

<b>It includes:</b>
<ul>
  <li>Spring Security - Authentication</li>
  <li>Spring Security - Authorization</li>
  <li>JSR 107 Caching</li>
  <li>JSR 303 Validation</li>
  <li>In memory H2 DB for local development</li>
  <li>MySQL support on production</li>
  <li>Tests</li>
</ul>

<h4>Spring Security</h4>
I am using Spring Security for authentication and authorization using JWT tokens.

<h4>Caching</h4>
Using Spring's Caching Abstraction, which is fully JSR 107 compliant. The default implementation is using Spring's in memory ConcurrentHashMap.

<h4>Validation</h4>
Validation is achieved using JSR 303 annotations on the entities. Validation failures will throw a ValidationException. Which contain a collection of all validation failures and returns with http status 412 - PRECONDITION_FAILED.

<h4>Persistence</h4>
The application itself is "persistent ignorant" and makes use of "Repositories" for datastore abstraction. When running locally it will use an in-memory h2 database. When deployed to the cloud, it looks for database related environment variables. If they are provided then the app will use the specified database.

<h4>Testing</h4>
Automated tests are setup using maven.

<h4>Cloud Deployment</h4>
The SpringBoot app is Heroku Ready and contains the appropriate Procfile.

<h4>Client<h4>
The client is an Angular2 application located in a separate repo: https://github.com/joeydeluca/petstore-client-angular2

