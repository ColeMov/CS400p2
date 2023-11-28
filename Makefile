runFDTests:
	javac Frontend.java
	javac -cp .:../junit5.jar FrontendDeveloperTests.java 
	java -jar ../junit5.jar -cp=. --select-class=FrontendDeveloperTests

clean:
	rm *class
