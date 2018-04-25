## Exercises

### Learn configuration
1. Change configuration name to "appconfig-changed.xml"
2. Set views location to /WEB-INF/pages

### Learn controllers
1. Create new jsp file which has a form to input name and age
2. Create a controller for InformationManagement and return above created jsp file
3. Create post method which accepts above form submit and retuen another jsp page as 
the confirmation.

### Learn services and autowiring
1. Create service class to log name and age submitted via the above form

### Learn beans and configuration
1. Create bean that return User object (First create a User class)  
2. Autowire created bean in UserController
3. Connect "/user/super" to return jsp page with the user attributes