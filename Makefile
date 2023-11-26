runBDTests:
		javac -cp ../junit5.jar:. BackendDeveloperTests.java
			java -jar ../junit5.jar --class-path=. --select-class=BackendDeveloperTests
clean:
	rm *.class
