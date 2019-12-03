This project is the backend module to connect MySQL database and populate content in JSON format for frontend applications to fetch and maintain Disneyland attractions

1. Enable MySQL server
2. Create a new database in MySQL with disneyland as the name and utf8_general_ci as the encoding
3. Import data from db.sql in the root directory of the git repository to the database
4. Import disney-attraction-backend project to Eclipse IDE. Wait until the building process is complete
5. Make sure all build path related directories are properly created. Right-click the project, click Build Path and Configure Build Path to see if there are any missing directories
6. Check db.properties in src\main\resources of the backend project to see if database connection parameters are properly defined
7. Check application.properties to see if the image path is properly defined. Make sure the directory exists at the corresponding location
8. Right-click the project, click Maven and Update Project
9. Right-click the project, click Run as, Maven Install to see if the project can be built successfully
10. Right-click the project, click Run as, Run Configurations, Maven Build. Add two goals as followings
11. Maven War build (Name), ${workspace_loc:/disneyland-attraction-backend} (Base directory) and clean install (Goals)
12. Tomcat Run (Name), ${workspace_loc:/disneyland-attraction-backend} (Base directory) and tomcat7:run (Goals)
13. Click Tomcat Run and click Run at the bottom to deploy the backend project to Tomcat
14. Access any APIs (e.g. http://localhost:8080/locationAttraction) to see if the backend project is running properly
15. Update WebConfig.java for CORS related issues

