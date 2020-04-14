# COURSE REGISTRATION SYSTEM
"A final project of course registration system in Java."

# Description
We are designing a course registration system. 

This system is based on Client-Server model. It is completely running on Grapical Interface and is able to run on two different computers (namely server and client) with the help of Internet.

These are the following directories in the repository and their descriptions.

	PreProject Exercise:
		Implementation of graphical user interface for a given courseCatalogue system.
		Using GUI buttons to load data from file, search for specific data, View or Browse all data
		Asking user to insert new Data via GUI, Refreshing the usage and syntax of various GUI objects.


	Design:
		Design of the project.
		Class diagrams, modular and deployment view.


	CourseRegiSys:
		Contains the main course registration project.
		/src - contains all of the source files.
		
		Inside src/, we have two packages namely server and client. 

		Server is responsible for all the server side processing and contains two more packages inside it (ServerController and ServerModel). These packages are designed to assure that the Server is following MVC and our designed class diagram.

		Client is responsible for getting user input and showing the server response. It mainly utilizes the graphical interface and eases for user to access the system. Client package again has two packages (ClientController and ClientView) which are there to make it follow MVC and designed class diagram.



Authors

	Punit Patel     -   30064251

		punit.patel1@ucalgary.ca

	Tom Pritchard   -   30053765
		
		tom.pritchard1@ucalgary.ca

	Armaan Mohar    -   30055449

		armaan.mohar1@ucalgary.ca


# Usage

The following steps will give you idea of how to use the system.

	If you are hosting server,
		1) Run the Server by running the ServerCommunication
		2) Then wait for client to connect
		3) System will let the client join automatically, while you can track it on the console
		4) Server will ask you if you want to run the server or close it when there are no clients connected, you must respond to this message by pressing 'y' or 'n'. No clients will be able to join if you don't press anything on console.
		5) To stop system, you must wait for all clients to disconnect and then press 'y'.

	If you are client:
		1) Run the Client side but running ClientCommunication
		2) Enter your details, now you can use the system.
