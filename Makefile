<<<<<<< HEAD
runBDTests:
		javac -cp ../junit5.jar:. BackendDeveloperTests.java
			java -jar ../junit5.jar --class-path=. --select-class=BackendDeveloperTests
clean:
	rm *.class
=======
runFDTests:
	javac Frontend.java
	javac -cp .:../junit5.jar FrontendDeveloperTests.java 
	java -jar ../junit5.jar -cp=. --select-class=FrontendDeveloperTests

clean:
	rm *class
>>>>>>> frontend
