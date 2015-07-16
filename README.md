# UMT
User Management Tutorial using Google Apps

Step 0: Add google plugin to eclipse
----
Refer to [this link](https://developers.google.com/eclipse/docs/download)

Step 1: Create project and add in dependencies.
----
1.	Create a new "Web Application Project" in eclipse
	- Name: UserManagement
	- package: um
	- Uncheck "Use Google Web Toolkit"
    - Make sure "Generate project sample code" is checked
2.	Add JQuery to your project
	- Download the latest version of JQuery from [here] (http://jquery.com/download/)
    - Create a folder "js" in the war folder of the project
    - Copy both jquery-{version}.js and jquery-{version}.min.js to the js folder
3.	Add Bootstrap to your project
	- Download the latest version of Bootstrap from [here] (http://getbootstrap.com/getting-started/)
    - Copy the css and fonts folders into the war folder
    - Copy the contents of the Bootstrap js folder into the js folder
 in the war folder
 4.	Add AngularJs to your project
 	- Download the latest version of AngularJs from [here] (https://angularjs.org)
    - Copy angular.js and angular.min.js to the js folder
    - Copy angular-route.js, angular-route.min.js, angular-sanitize.js, and angular-sanitize.min.js to the js folder
At the end of step 1, your war directory should be failry similar to:![war folder contents]({{site.baseurl}}/imgur.com/CmbdcoX)
	