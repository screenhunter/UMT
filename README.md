#UMT
User Management Tutorial using Google Apps

##Step 0: Add google plugin to eclipse
Refer to [this link](https://developers.google.com/eclipse/docs/download)

##Step 1: Create project and add in dependencies.
###Part 1:	Create a new *Web Application Project* in eclipse
- Name: UserManagement
- package: um
- Uncheck **Use Google Web Toolkit**
- Make sure **Generate project sample code** is checked

###Part 2:	Add JQuery to your project
- Download the latest version of JQuery from [here](http://jquery.com/download/)
- Create a folder **js** in the war folder of the project
- Copy both **jquery-{version}.js** and **jquery-{version}.min.js** to the js folder

###Part 3:	Add Bootstrap to your project
- Download the latest version of Bootstrap from [here](http://getbootstrap.com/getting-started/)
- Copy the css and fonts folders into the war folder
- Copy the contents of the Bootstrap js folder into the war js folder

###Part 4:	Add AngularJs to your project
- Download the latest version of AngularJs from [here](https://angularjs.org)
- Copy **angular.js** and **angular.min.js** to the js folder
- Copy **angular-route.js**, **angular-route.min.js**, **angular-sanitize.js**, and **angular-sanitize.min.js** to the js folder
 
 At the end of Step 1, your war directory should be fairly similar to:
![war folder contents](http://i.imgur.com/CmbdcoX.png)

##Step 2:	Creating a Guest View.
For the application we will create a guest view that will be publicly accessible.  To do this, we will create a folder, guest, in which we will add all the dedicated files for a guest.

###Part 1:	Configuring the Project Template
- In the war folder, create a new folder named **guest** (This folder will group all files for a guest.
- Create a file named **guest.html** in the guest folder
- In the html file, define the type of file, html, and language, en.
- Add an empty head, and a body with a hello world message.

Thus, the html should look similar to:
![guest.html contents](http://i.imgur.com/pVBn1le.png)

Executing the project should give the following guest page:
![display page](http://i.imgur.com/NIwXOd2.png)

Next, add some header and meta information to the page.
- Add meta information about the site to the head of the guest page:
![meta information](http://i.imgur.com/c2srHWH.png)
- Add a title to the page after the meta information. For this tutorial, we will give it the title **UMS - User Management System - Guest View**
- Executing the project now shows the title as the tab name:
![tab name](http://i.imgur.com/i8RpZ9r.png)

Now add Bootstrap and AngularJS to the guest.html file.
- Add the jQuery and Bootstrap libraries to the head:
![libraries](http://i.imgur.com/TxvNzhe.png)
Note that though we added both the development and production libraries, we opted to use the development libraries here. In production, you may even link to public repositories for the libraries.
- To test the Bootstrap library, change the Hello World message to a quote with some Bootstrap styling:
![bootstrap test](http://i.imgur.com/An3DKcd.png)
- To add AngularJS, we first modify the html tag by including the attribute **data-ng-app** (an AngularJS directive) to indicate that this file should be used as a template.
![html JS directive](http://i.imgur.com/tAeyyKG.png)
- Then in the head, after the Bootstrap library, add the AngularJS library.
![angularJS library](INSERT LINK HERE)
- Add a small AngularJS test at the beginning of the body to check that it is running properly.
![angularJS test](INSERT LINK HERE)
..- The Bootstrap **well** followed by the class **well-lg** creates the effect of an accent box with margins
..- The row class just groups everything together. It would be useful if there was multi-column content.
..- The lead class in the paragraph emphasizes the text as the leading content in the well
..- The {{}} indicates an AngularJS expression that needs to be evaluated and replaced in the file during execution.
- Thus, executing the project gives:
![AngularJS test](INSERT LINK HERE)
Note that if AngularJS was not running properly, the **3** would appear as **{{1+2}}**

###Part 2:	Single page navigation with a main menu
The goal of this part is to create a single page with various sections with the main menu always present on the screen and navigation control. No functionality or text will be entered, but it is a simple example of using Bootstrap to create a fast prototype of a site